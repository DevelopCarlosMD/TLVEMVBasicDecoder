package com.coyotestudio.parserdecodetlvfromemv.basicdecoder;

import android.util.Log;

import java.nio.charset.Charset;


public class Utilities {

    public static final String TAG = Utilities.class.getSimpleName();
    private static final char[] CHARS_TABLES = "0123456789ABCDEF".toCharArray();
    private static final String HEXES = "0123456789ABCDEF";

    // the range in memory from a byte value
    static final byte[] BYTES = new byte[128];

    static {
        for (int i = 0; i < 10; i++) {
            BYTES['0' + i] = (byte) i;
            BYTES['A' + i] = (byte) (10 + i);
            BYTES['a' + i] = (byte) (10 + i);
        }
    }

    public static String toHexString(byte[] aBytes) {
        return toHexString(aBytes, aBytes.length);
    }

    public static String toHexStringValue(byte[] aBytes, String tagValidation) {

        //char[] dst   = new char[aLength * 2];
        String dato;
        //dato = dato.replaceAll("[^A-Za-z0-9\\s]","");
        //Log.i(TAG, "toHexString: " + dato);
        final StringBuilder hex = new StringBuilder(2 * aBytes.length);
        if (!tagValidation.equals(TagsEMV.UNKNOWN)) {
            dato = new String(aBytes, Charset.forName("UTF-8"));
            dato = dato.replaceAll("[^A-Za-z0-9\\s]", "");
            if (dato.length() > 3) {
                return dato;
            } else {
                for (final byte b : aBytes) {
                    hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
                }
                return hex.toString();
            }
        } else {

            for (final byte b : aBytes) {
                hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
            }
            dato = hex.toString();

        }
        return dato;


    }


    public static byte[] parseHex(String aHexString) {
        char[] src = aHexString.replace("\n", "").replace(" ", "").toUpperCase().toCharArray();
        byte[] dst = new byte[src.length / 2];

        Log.i(TAG, "parseHex: " + src.length);

        for (int si = 0, di = 0; di < dst.length; di++) {
            byte high = BYTES[src[si++] & 0x7f];
            byte low = BYTES[src[si++] & 0x7f];
            dst[di] = (byte) ((high << 4) + low);
        }

        return dst;
    }

    public static String toFormattedHexString(byte[] aBytes, int aOffset, int aLength) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(aLength);
        sb.append("] :");

        for (int si = aOffset, di = 0; si < aOffset + aLength; si++, di++) {
            byte b = aBytes[si];
            if (di % 4 == 0) {
                sb.append("  ");
            } else {
                sb.append(' ');
            }
            sb.append(CHARS_TABLES[(b & 0xf0) >>> 4]);
            sb.append(CHARS_TABLES[(b & 0x0f)]);

        }

        return sb.toString();

    }

    public static String toHexString(byte[] aBytes, int aLength) {
        final StringBuilder hex = new StringBuilder(2 * aLength);
        for (final byte b : aBytes) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

}
