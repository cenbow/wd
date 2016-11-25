package com.okwei.cartportal.bean.enums;

public enum OrderProductType
{
    /**
     * 批发号零售
     */
    BRetail
    {
        public int getNo()
        {
            return 1;
        }
    },
    /**
     * 批发号批发
     */
    BWholesale
    {
        public int getNo()
        {
            return 2;
        }
    },
    /**
     * 批发号预订
     */
    BBook
    {
        public int getNo()
        {
            return 3;
        }
    },
    /**
     * 赠品区
     */
    BGifts
    {
        public int getNo()
        {
            return 4;
        }
    };
    public abstract int getNo();
}
