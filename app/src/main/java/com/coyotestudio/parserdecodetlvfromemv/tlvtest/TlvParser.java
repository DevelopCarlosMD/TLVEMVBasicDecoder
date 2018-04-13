package com.coyotestudio.parserdecodetlvfromemv.tlvtest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TlvParser {

    //private final IBerTlvLogger log;
    private static final String TAG = TlvParser.class.getSimpleName();
    public TlvParser() {
    }

    public TlvObj parseConstructed(byte[] aBuf) {
        return parseConstructed(aBuf, 0, aBuf.length);
    }

    public TlvObj parseConstructed(byte[] aBuf, int aOffset, int aLen) {
        ParseResult result = parseWithResult(0, aBuf, aOffset, aLen);
        return result.tlv;
    }

    public TlvElements parse(byte[] aBuf) {
        return parse(aBuf, 0, aBuf.length);
    }

    public TlvElements parse(byte[] aBuf, final int aOffset, int aLen) {
        List<TlvObj> tlvs = new ArrayList<TlvObj>();
        if (aLen == 0) return new TlvElements(tlvs);

        int offset = aOffset;
        for (int i = 0; i < 100; i++) {
            ParseResult result = parseWithResult(0, aBuf, offset, aLen - offset);
            tlvs.add(result.tlv);

            if (result.offset >= aOffset + aLen) {
                break;
            }

            offset = result.offset;
        }

        return new TlvElements(tlvs);
    }

    private ParseResult parseWithResult(int aLevel, byte[] aBuf, int aOffset, int aLen) {
        String levelPadding = createLevelPadding(aLevel);
        if (aOffset + aLen > aBuf.length) {
            throw new IllegalStateException("Length is out of the range [offset=" + aOffset + ",  len=" + aLen + ", array.length=" + aBuf.length + ", level=" + aLevel + "]");
        }

        // tag
        int tagBytesCount = getTagBytesCount(aBuf, aOffset);
        TagBytes tag = createTag(levelPadding, aBuf, aOffset, tagBytesCount);


        // length
        int lengthBytesCount = getLengthBytesCount(aBuf, aOffset + tagBytesCount);
        int valueLength = getDataLength(aBuf, aOffset + tagBytesCount);


        // value
        if (tag.isConstructed()) {

            ArrayList<TlvObj> list = new ArrayList<TlvObj>();
            addChildren(aLevel, aBuf, aOffset, levelPadding, tagBytesCount, lengthBytesCount, valueLength, list);

            int resultOffset = aOffset + tagBytesCount + lengthBytesCount + valueLength;
            return new ParseResult(new TlvObj(tag, list), resultOffset);
        } else {
            // value
            byte[] value = new byte[valueLength];
            System.arraycopy(aBuf, aOffset + tagBytesCount + lengthBytesCount, value, 0, valueLength);
            int resultOffset = aOffset + tagBytesCount + lengthBytesCount + valueLength;
            //Log.i(TAG, "value1 " + levelPadding + " " + HexUtil.toFormattedHexString(value));
            //Log.i(TAG, "value2 " + levelPadding + " " + resultOffset);
            return new ParseResult(new TlvObj(tag, value), resultOffset);
        }

    }

    /**
     * @param aLevel          level for debug
     * @param aBuf            buffer
     * @param aOffset         offset (first byte)
     * @param levelPadding    level padding (for debug)
     * @param aTagBytesCount  tag bytes count
     * @param aDataBytesCount data bytes count
     * @param valueLength     length
     * @param list            list to add
     */
    private void addChildren(int aLevel, byte[] aBuf, int aOffset, String levelPadding, int aTagBytesCount, int aDataBytesCount, int valueLength, ArrayList<TlvObj> list) {
        int startPosition = aOffset + aTagBytesCount + aDataBytesCount;
        int len = valueLength;
        while (startPosition <= aOffset + valueLength) {
            ParseResult result = parseWithResult(aLevel + 1, aBuf, startPosition, len);
            list.add(result.tlv);

            startPosition = result.offset;
            len = valueLength - startPosition;
        }
    }

    private String createLevelPadding(int aLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aLevel * 4; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private static class ParseResult {

        private final TlvObj tlv;
        private final int offset;

        public ParseResult(TlvObj aTlv, int aOffset) {
            tlv = aTlv;
            offset = aOffset;
        }

        @Override
        public String toString() {
            return "ParseResult{" +
                    "tlv=" + tlv +
                    ", offset=" + offset +
                    '}';
        }

    }

    private TagBytes createTag(String aLevelPadding, byte[] aBuf, int aOffset, int aLength) {
        return new TagBytes(aBuf, aOffset, aLength);
    }

    private int getTagBytesCount(byte[] aBuf, int aOffset) {
        if ((aBuf[aOffset] & 0x1F) == 0x1F) { // see subsequent bytes
            int len = 2;
            for (int i = aOffset + 1; i < aOffset + 10; i++) {
                if ((aBuf[i] & 0x80) != 0x80) {
                    break;
                }
                len++;
            }
            return len;
        } else {
            return 1;
        }
    }


    private int getDataLength(byte[] aBuf, int aOffset) {

        int length = aBuf[aOffset] & 0xff;

        if ((length & 0x80) == 0x80) {
            int numberOfBytes = length & 0x7f;
            if (numberOfBytes > 3) {
                throw new IllegalStateException(String.format("At position %d the len is more then 3 [%d]", aOffset, numberOfBytes));
            }

            length = 0;
            for (int i = aOffset + 1; i < aOffset + 1 + numberOfBytes; i++) {
                length = length * 0x100 + (aBuf[i] & 0xff);
            }

        }
        return length;
    }

    private static int getLengthBytesCount(byte aBuf[], int aOffset) {

        int len = aBuf[aOffset] & 0xff;
        if ((len & 0x80) == 0x80) {
            return 1 + (len & 0x7f);
        } else {
            return 1;
        }
    }

}