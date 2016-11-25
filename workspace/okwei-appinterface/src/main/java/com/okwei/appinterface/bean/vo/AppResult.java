package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

import com.okwei.common.PageResult;

/**
 * 接口结果基类
 * 
 * @author xiehz
 *
 */
public class AppResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Status statu;

	private String statusreson;

	private Object baseModle;

	public Status getStatu() {
		return statu;
	}

	public void setStatu(Status statu) {
		this.statu = statu;
	}

	public String getStatusreson() {
		return statusreson;
	}

	public void setStatusreson(String statusreson) {
		this.statusreson = statusreson;
	}

	public Object getBaseModle() {
		return baseModle;
	}

	public void setBaseModle(Object baseModle) {
		this.baseModle = baseModle;
	}

	// / <summary>
	// / 接口返回状态
	// / </summary>
	public enum Status {
		// / <summary>
		// / 成功
		// / </summary>
		success(1),
		// / <summary>
		// / 第三方登录验证成功
		// / </summary>
		othLogSuccess(2),
		// / <summary>
		// / 登陆过期或未登录
		// / </summary>
		loginError(-1),
		// / <summary>
		// / 系统错误（try catch报错）
		// / </summary>
		systemError(-3),
		// / <summary>
		// / 参数问题
		// / </summary>
		paramError(-2),
		// / <summary>
		// / 参数问题
		// / </summary>
		validCodeError(-5),
		// / <summary>
		// / 新老数据问题
		// / </summary>
		newOldVersion(-6),
		// / <summary>
		// / 数据问题
		// / </summary>
		dataError(-4),
		// / <summary>
		// / 支付时需要图片验证（或者其他）
		// / </summary>
		needParam(-101);

		private final int step;

		private Status(int step) {

			this.step = step;
		}

		@Override
		public String toString() {
			return String.valueOf(this.step);
		}
	}

}
