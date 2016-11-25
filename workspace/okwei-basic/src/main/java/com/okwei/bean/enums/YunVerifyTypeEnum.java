package com.okwei.bean.enums;

public enum YunVerifyTypeEnum
{
    /**
     * 普通为店主
     */
    common(-1),
    /**
     * 见习认证员
     */
    jxVerify(1),
    /**
     * 正式认证员
     */
    zsVerify(2),
    /**
    * 代理商
    */
    dlsVerify(3);

    private final int step;

    private YunVerifyTypeEnum(int step)
    {
        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
