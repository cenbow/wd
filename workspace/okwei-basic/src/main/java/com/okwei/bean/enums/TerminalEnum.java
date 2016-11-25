package com.okwei.bean.enums;

/**
 * 设备终端
 * @author Administrator
 *
 */
public enum TerminalEnum {
    /**
     * pc
     */
    pc(01),
    /**
     * app
     */
    app(2),
    /**
     * wap
     */
    wap(3);
    private final int step;

    private TerminalEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
