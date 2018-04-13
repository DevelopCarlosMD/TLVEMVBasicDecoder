package com.coyotestudio.parserdecodetlvfromemv.basicdecoder;

import java.util.Arrays;

/**
 * Created by J. Carlos Medina Díaz @_CarlosMD on 4/13/18.
 * carlos.medj@gmail.com
 */
public class TagBytes {

    public final byte[] bytes;

    public TagBytes(byte[] aBuf, int aOffset, int aLength) {
        byte[] temp = new byte[aLength];
        System.arraycopy(aBuf, aOffset, temp, 0, aLength);
        bytes = temp;
    }


    public boolean isConstructed() {
        return (bytes[0] & 0x20) != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagBytes tagBytes = (TagBytes) o;

        return Arrays.equals(bytes, tagBytes.bytes);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return (isConstructed() ? "+ " : "- ") + Utilities.toHexString(bytes, bytes.length);
    }

}
