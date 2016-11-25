/**
 * 
 */
package com.okwei.bean.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @ClassName: LoginUserVO
 * @Description: 登陆状态的用户
 * @author xiehz
 * @date 2015年6月5日 下午4:32:42
 *
 */
public class LoginUserVO {

	@NotEmpty(message = "userName不能为空")
	@Size(max = 20, message = "userName长度不能超过20")
	private String userName;

	@NotEmpty(message = "密码不能为空")
	@Size(max = 128, message = "密码长度不能超过128")
	private String password;

	// @NotEmpty(message = "验证码不能为空")
	// @Size(max = 4, message = "验证码长度不能超过4")
	private String code;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
