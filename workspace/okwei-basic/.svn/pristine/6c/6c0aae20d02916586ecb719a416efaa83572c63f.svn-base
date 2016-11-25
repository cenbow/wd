package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UFriendsShare entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_FriendsShare")
public class UFriendsShare implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3707241144260469924L;
	private Long id;
	private Long weiId;
	private Long shareId;
	private Date createTime;
	private Integer zanCount;
	private Short childSee;
	private Short fansSee; 
	private Short friendSee;

	// Constructors

	/** default constructor */
	public UFriendsShare() {
	}

	/** minimal constructor */
	public UFriendsShare(Long weiId, Long shareId) {
		this.weiId = weiId;
		this.shareId = shareId;
	}

	/** full constructor */
	public UFriendsShare(Long weiId, Long shareId, Date createTime,
			Integer zanCount) {
		this.weiId = weiId;
		this.shareId = shareId;
		this.createTime = createTime;
		this.zanCount = zanCount;
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

	@Column(name = "ShareID", nullable = false)
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ZanCount")
	public Integer getZanCount() {
		return this.zanCount;
	}

	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}
	
	@Column(name = "ChildSee")
	public Short getChildSee() {
		return this.childSee;
	}

	public void setChildSee(Short childSee) {
		this.childSee = childSee;
	}
	
	@Column(name = "FansSee")
	public Short getFansSee() {
		return this.fansSee;
	}

	public void setFansSee(Short fansSee) {
		this.fansSee = fansSee;
	}
	
	@Column(name = "FriendSee")
	public Short getFriendSee() {
		return this.friendSee;
	}

	public void setFriendSee(Short friendSee) {
		this.friendSee = friendSee;
	}
	

}