package com.okwei.pay.bean.enums;

public enum PayResultStateEnum {

    /**
     * 成功
     */
    Success(1),
    /**
     * 失败
     */
    Failure(2),
    /**
     * 重试
     */
    TryAgain(3);

    private final int step;

    private PayResultStateEnum(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
