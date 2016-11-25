package com.okwei.bean.enums;

/**
 * 用户子帐号类型
 */
public enum UserChildenType
{
    /**
     * 子帐号员工
     */
    Employee(1),
    /**
     * 子帐号供应商
     */
    Supplier(2);

    private final int step;

    private UserChildenType(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
