package com.okwei.myportal.bean.enums;

public enum VerifyCodeType
{
    /**
     * 注册
     */
    register(1),
    /**
     * 绑定手机号码
     */
    bind(2),
    /**
     * 充值
     */
    recharge(3),
    /**
     * 提现
     */
    deposit(4),
    /**
     * 修改密码
     */
    updatepassword(5),
    /**
     * 钱包支付
     */
    pay(6),
    /**
     * 实名认证
     */
    realnameVerify(7),
    /**
     * 解绑
     */
    unbundl(8),
    /**
     * 其他
     */
    Other(0),
    /**
     * 修改支付密码
     */
    updatePayPassword(9);

    private final int step;

    private VerifyCodeType(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }

}
