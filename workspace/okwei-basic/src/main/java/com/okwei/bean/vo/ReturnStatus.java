package com.okwei.bean.vo;

/// <summary>
/// 接口返回状态
/// </summary>
public enum ReturnStatus {
	// / <summary>
	// / 成功
	// / </summary>
	Success(1),
	// / <summary>
	// / 第三方登录验证成功
	// / </summary>
	OthLogSuccess(2),
	// / <summary>
	// / 登陆过期或未登录
	// / </summary>
	LoginError(-1),
	// / <summary>
	// / 系统错误（try catch报错）
	// / </summary>
	SystemError(-3),
	// / <summary>
	// / 参数问题
	// / </summary>
	ParamError(-2),
	// / <summary>
	// / 参数问题
	// / </summary>
	ValidCodeError(-5),
	// / <summary>
	// / 新老数据问题
	// / </summary>
	NewOldVersion(-6),
	// / <summary>
	// / 数据问题
	// / </summary>
	DataError(-4),
	// / <summary>
	// / 支付时需要图片验证（或者其他）
	// / </summary>
	NeedParam(-101),

	/**
	 * 操作太频繁
	 */
	TooFrequent(-102),
	/**
	 * 数据已存在
	 */
	DataExists(-103);

	private final int step;

	private ReturnStatus(int step) {

		this.step = step;
	}

	public int getValue() {
		return step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
