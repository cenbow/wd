package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_user")
public class AdminUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1641695063185064141L;
	private Integer id;
	private Integer authorityid;
	private String loginname;
	private String realname;
	private Short status;
	private String password;
	private Long weiid;
	private Integer partid;
	private Integer groupid;
	private Integer roleid;
	private Date createtime;

	// Constructors

	/** default constructor */
	public AdminUser() {
	}

	/** full constructor */
	public AdminUser(Integer authorityid, String loginname, String realname,
			Short status, String password, Long weiid, Integer partid,
			Integer groupid, Integer roleid, Date createtime) {
		this.authorityid = authorityid;
		this.loginname = loginname;
		this.realname = realname;
		this.status = status;
		this.password = password;
		this.weiid = weiid;
		this.partid = partid;
		this.groupid = groupid;
		this.roleid = roleid;
		this.createtime = createtime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "authorityid")
	public Integer getAuthorityid() {
		return this.authorityid;
	}

	public void setAuthorityid(Integer authorityid) {
		this.authorityid = authorityid;
	}

	@Column(name = "loginname", length = 32)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "realname", length = 32)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "password", length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "weiid")
	public Long getWeiid() {
		return this.weiid;
	}

	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}

	@Column(name = "partid")
	public Integer getPartid() {
		return this.partid;
	}

	public void setPartid(Integer partid) {
		this.partid = partid;
	}

	@Column(name = "groupid")
	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	@Column(name = "roleid")
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}