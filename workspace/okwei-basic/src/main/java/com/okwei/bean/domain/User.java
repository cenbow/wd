package com.okwei.bean.domain;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "U_User")
public class User extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 锁住状态
	 */
	@Column(name = "isLocked")
	private byte isLocked;

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

	/**
	 * 角色
	 */

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "U_User_Role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet<Role>();

	/**
	 * 权限
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "U_User_Function", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "func_id") })
	private Set<Function> auths = new HashSet<Function>();

	/**
	 * 用于转换role
	 */
	@Transient
	private Map<String, Integer> roleTrans = new HashMap<String, Integer>();

	/**
	 * 用于转换auth
	 */
	@Transient
	private Map<String, Integer> authTrans = new HashMap<String, Integer>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public byte getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(byte isLocked) {
		this.isLocked = isLocked;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Function> getAuths() {
		return auths;
	}

	public void setAuths(Set<Function> auths) {
		this.auths = auths;
	}

	public Map<String, Integer> getRoleTrans() {
		return roleTrans;
	}

	public void setRoleTrans(Map<String, Integer> roleTrans) {
		this.roleTrans = roleTrans;
	}

	public Map<String, Integer> getAuthTrans() {
		return authTrans;
	}

	public void setAuthTrans(Map<String, Integer> authTrans) {
		this.authTrans = authTrans;
	}

	public User beforeCreate() {
		translateRole();
		translateAuth();
		return this;
	}

	/**
	 * 将装有role id的Map的值注入到role中
	 */
	private void translateRole() {
		Map<String, Integer> roleIds = getRoleTrans();
		if (roleIds.isEmpty()) {
			return;
		}
		getRoles().clear();
		for (String key : roleIds.keySet()) {
			Role role = new Role();
			role.setRoleId(roleIds.get(key));
			getRoles().add(role);
		}
	}

	/**
	 * 将装有auth id的Map的值注入到auth中
	 */
	private void translateAuth() {
		Map<String, Integer> authIds = getAuthTrans();
		if (authIds.isEmpty()) {
			return;
		}
		getAuths().clear();
		for (String key : authIds.keySet()) {
			Function auth = new Function();
			auth.setFuncId(authIds.get(key));
			getAuths().add(auth);
		}
	}

	/**
	 * @return
	 */
	public Set<String> getRolesAsString() {
		Set<String> roles = new HashSet<String>();
		for (Role role : getRoles()) {
			roles.add(role.getRoleCode());
		}
		return roles;
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> getAuthAsString() {
		Set<String> auths = new HashSet<String>();
		for (Function auth : getAuths()) {
			auths.add(auth.getFuncCode());
		}
		return auths;
	}

	public boolean hasRoles() {
		return !getRoles().isEmpty();
	}

	public boolean hasAuths() {
		return !getAuths().isEmpty();
	}

	public User encodePassword() {
		/* setPassword(PasswordEncoder.encode(getPassword())); */
		setPassword(getPassword());
		return this;
	}

}
