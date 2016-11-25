package com.okwei.bean.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "U_Function")
public class Function extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1554690122999348374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "func_id")
	private Integer funcId;

	/**
	 * 功能名称
	 */
	@Column(name = "func_name")
	private String funcName;

	/**
	 * 功能链接
	 */
	@Column(name = "func_url")
	private String funcUrl;

	/**
	 * 父子级联关系
	 */
	@Column(name = "func_pid")
	private Integer funcPid;

	/**
	 * 菜单序号
	 */
	@Column(name = "func_seq_no")
	private Integer funcSeqNo;

	/**
	 * 菜单级别： -1: 非菜单； 0：菜单根节点； 1：1级菜单； 2：2级菜单； 依次类推...
	 */
	@Column(name = "func_level")
	private Integer funcLevel;

	/**
	 * 控制图片显示的样式
	 */
	@Column(name = "func_icon_class")
	private String funcIconclass;

	/**
	 * 权限编号
	 */
	@Column(name = "func_code")
	private String funcCode;

	/**
	 * 创建时间
	 */
	@Column(name = "c_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "u_time")
	private Date updateTime;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "auths", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "auths", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<Role>();

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public Integer getFuncPid() {
		return funcPid;
	}

	public void setFuncPid(Integer funcPid) {
		this.funcPid = funcPid;
	}

	public Integer getFuncSeqNo() {
		return funcSeqNo;
	}

	public void setFuncSeqNo(Integer funcSeqNo) {
		this.funcSeqNo = funcSeqNo;
	}

	public Integer getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(Integer funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getFuncIconclass() {
		return funcIconclass;
	}

	public void setFuncIconclass(String funcIconclass) {
		this.funcIconclass = funcIconclass;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
