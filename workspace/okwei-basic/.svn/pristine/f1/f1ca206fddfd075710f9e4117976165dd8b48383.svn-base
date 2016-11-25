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
@Table(name = "U_Role")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6769720272431073142L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "role_name")
	private String roleName;

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

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "U_Role_Function", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "func_id") })
	private Set<Function> auths = new HashSet<Function>();

	@Transient
	private Map<String, Integer> authTrans = new HashMap<String, Integer>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Set<Function> getAuths() {
		return auths;
	}

	public void setAuths(Set<Function> auths) {
		this.auths = auths;
	}

	public Map<String, Integer> getAuthTrans() {
		return authTrans;
	}

	public void setAuthTrans(Map<String, Integer> authTrans) {
		this.authTrans = authTrans;
	}

	public Role beforeCreate() {
		translateAuths();
		return this;
	}

	private void translateAuths() {
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
	public Set<String> getAuthsAsString() {
		Set<String> auths = new HashSet<String>();
		for (Function auth : getAuths()) {
			auths.add(auth.getFuncCode());
		}
		return auths;
	}

	public boolean hasAuth() {
		return !getAuths().isEmpty();
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
