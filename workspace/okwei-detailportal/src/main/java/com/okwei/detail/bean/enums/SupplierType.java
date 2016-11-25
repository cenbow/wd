package com.okwei.detail.bean.enums;

public enum SupplierType {
    /**
     * 批发号
     */
    pfh(1),
    /**
     * 工厂号
     */
    gch(2);

    private final int Type;

    private SupplierType(int step)
    {

        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
