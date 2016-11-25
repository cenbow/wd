package com.okwei.bean.enums.agent;
/**
 * 代理商类型 主动报名及被动拉入
 * @author Administrator
 *
 */
public enum AgentAddType {
	/**
     * 主动报名
     */
	activein(1),
	/**
     * 被动拉入
     */
	unactivein(2);
    private final int Type;

    private AgentAddType(int step)
    {
        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
