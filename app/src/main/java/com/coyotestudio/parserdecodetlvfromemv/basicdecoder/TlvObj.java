package com.coyotestudio.parserdecodetlvfromemv.basicdecoder;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class TlvObj {


    private final TagBytes theTag;
    private final byte[] theValue;
    protected final List<TlvObj> theList;

    /**
     * Creates constructed TLV
     *
     * @param aTag  tag
     * @param aList set of nested TLVs
     */
    public TlvObj(TagBytes aTag, List<TlvObj> aList) {
        theTag = aTag;
        theList = aList;
        theValue = null;
    }

    /**
     * Creates primitive TLV
     *
     * @param aTag   tag
     * @param aValue value as byte[]
     */
    public TlvObj(TagBytes aTag, byte[] aValue) {
        theTag = aTag;
        theValue = aValue;
        theList = null;
    }

    //
    public TagBytes getTag() {
        return theTag;
    }

    public boolean isPrimitive() {
        return !theTag.isConstructed();
    }

    public boolean isConstructed() {
        return theTag.isConstructed();
    }

    //
    // getters
    //
    public String getHexValue(String tagContext) {
        if (isConstructed())
            throw new IllegalStateException("Tag is CONSTRUCTED " + Utilities.toHexString(theTag.bytes));
        return Utilities.toHexStringValue(theValue, tagContext);
    }


    public List<TlvObj> getValues() {
        if (isPrimitive()) throw new IllegalStateException("Tag is PRIMITIVE");
        return theList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TlvObj tlvObj = (TlvObj) o;

        if (theTag != null ? !theTag.equals(tlvObj.theTag) : tlvObj.theTag != null) return false;
        if (!Arrays.equals(theValue, tlvObj.theValue)) return false;
        return theList != null ? theList.equals(tlvObj.theList) : tlvObj.theList == null;
    }

    @Override
    public int hashCode() {
        int result = theTag != null ? theTag.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(theValue);
        result = 31 * result + (theList != null ? theList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "BerTlv{" +
                "theTag=" + theTag +
                ", theValue=" + Arrays.toString(theValue) +
                ", theList=" + theList +
                '}';
    }

}
