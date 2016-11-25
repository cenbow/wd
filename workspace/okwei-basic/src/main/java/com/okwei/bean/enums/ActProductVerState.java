package com.okwei.bean.enums;
/**
 * 显示抢购 产品审核状态
 * @author Administrator
 *
 */
public enum ActProductVerState {
	
    /**
     * 申请中
     */
    applying(0),
	/**
     * 通过
     */
    Ok(1),
    /**
     * 不通过
     */
    No(2);
    private final int Type;

    private ActProductVerState(int step)
    {
        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
