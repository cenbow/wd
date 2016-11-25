package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UFriends entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Friends")
public class UFriends implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900830519824941982L;
	private Long id;
	private Long weiId;
	private Long friendId;
	private Short status;
	private Date createTime;
	private Short attentionState;

	// Constructors
	
	/** default constructor */
	public UFriends() {
	}

	

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
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
	@Column(name = "FriendID")
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
	
	@Column(name = "AttentionState")
	public Short getAttentionState() {
		return this.attentionState;
	}
	
	public void setAttentionState(Short attentionState) {
		this.attentionState = attentionState;
	}

}