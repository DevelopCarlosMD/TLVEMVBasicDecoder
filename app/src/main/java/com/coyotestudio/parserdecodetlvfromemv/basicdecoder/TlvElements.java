package com.coyotestudio.parserdecodetlvfromemv.basicdecoder;

import java.util.List;

public class TlvElements {

    protected TlvElements(List<TlvObj> aTlvs) {
        tlvs = aTlvs;
    }


    public List<TlvObj> getList() {
        return tlvs;
    }

    private final List<TlvObj> tlvs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TlvElements tlvElements = (TlvElements) o;

        return tlvs != null ? tlvs.equals(tlvElements.tlvs) : tlvElements.tlvs == null;
    }

    @Override
    public int hashCode() {
        return tlvs != null ? tlvs.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tlvs{" +
                "tlvs=" + tlvs +
                '}';
    }
}
