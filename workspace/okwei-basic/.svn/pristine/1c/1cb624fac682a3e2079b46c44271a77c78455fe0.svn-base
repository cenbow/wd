package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UFriendsApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_FriendsApply")
public class UFriendsApply implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 875121900855108390L;
	// Fields

	private Long id;
	private Long weiId;
	private Long friendId;
	private Short status;
	private Date createTime;
	private String remark;

	// Constructors

	/** default constructor */
	public UFriendsApply() {
	}

	/** minimal constructor */
	public UFriendsApply(Long weiId, Long friendId) {
		this.weiId = weiId;
		this.friendId = friendId;
	}

	/** full constructor */
	public UFriendsApply(Long weiId, Long friendId, Short status,
			Date createTime, String remark) {
		this.weiId = weiId;
		this.friendId = friendId;
		this.status = status;
		this.createTime = createTime;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "FriendID", nullable = false)
	public Long getFriendId() {
		return this.friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}