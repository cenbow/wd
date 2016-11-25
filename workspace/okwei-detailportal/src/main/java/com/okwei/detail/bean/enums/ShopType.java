package com.okwei.detail.bean.enums;

public enum ShopType {
    /**
     * 明星云商
     */
    starYs(1),
    /**
     * 明星微店主
     */
    starWdz(2);

    private final int Type;

    private ShopType(int step)
    {

        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
