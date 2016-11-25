package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: LoginInfo
 * @Description: TODO
 * @author xiehz
 * @date 2014年6月18日 下午3:06:18
 * 
 */
@Entity
@Table(name = "U_LoginInfo")
public class LoginInfo extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7145901302909577050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "onlineSec")
	private Integer onlineSecs;

	@Column(name = "login_ip")
	private String ip;

	@Column(name = "login_time")
	private Date loginTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOnlineSecs() {
		return onlineSecs;
	}

	public void setOnlineSecs(Integer onlineSecs) {
		this.onlineSecs = onlineSecs;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
