package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UFriendsShareZanLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_FriendsShareZanLog")
public class UFriendsShareZanLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257093860076914190L;
	private Long zanId;
	private Long friendShareId;
	private Long weiId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UFriendsShareZanLog() {
	}

	/** minimal constructor */
	public UFriendsShareZanLog(Long friendShareId, Long weiId) {
		this.friendShareId = friendShareId;
		this.weiId = weiId;
	}

	/** full constructor */
	public UFriendsShareZanLog(Long friendShareId, Long weiId,
			Date createTime) {
		this.friendShareId = friendShareId;
		this.weiId = weiId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ZanID", unique = true, nullable = false)
	public Long getZanId() {
		return this.zanId;
	}

	public void setZanId(Long zanId) {
		this.zanId = zanId;
	}

	@Column(name = "FriendShareID", nullable = false)
	public Long getFriendShareId() {
		return this.friendShareId;
	}

	public void setFriendShareId(Long friendShareId) {
		this.friendShareId = friendShareId;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}