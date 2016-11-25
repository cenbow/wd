package com.okwei.pay.bean.enums;

public enum VerifierIdentityEnum {
    /**
     * 见习认证员
     */
    Ordinary(1),
    /**
     * 正式认证员
     */
    Percent(2),
    /**
     * 批发号认证员
     */
    BatchVerifier(4),
    /**
     * 批发号认证点
     */
    BatchVerifierPort(8);

    private final int step;

    private VerifierIdentityEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
