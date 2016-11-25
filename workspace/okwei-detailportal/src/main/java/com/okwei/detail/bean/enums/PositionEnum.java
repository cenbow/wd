package com.okwei.detail.bean.enums;

public enum PositionEnum {
    /**
     * 焦点图
     */
    jdt(1),
    /**
     * 网店精选
     */
    wdjx(2),
    /**
     * 猛料
     */
    ml(3);

    private final int Type;

    private PositionEnum(int step)
    {

        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
