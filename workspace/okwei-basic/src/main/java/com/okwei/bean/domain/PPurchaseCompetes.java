package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPurchaseCompetes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PurchaseCompetes")
public class PPurchaseCompetes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137599470105144848L;
	private Long qid;
	private Long cid;
	private Long weiId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PPurchaseCompetes() {
	}

	/** full constructor */
	public PPurchaseCompetes(Long cid, Long weiId, Date createTime) {
		this.cid = cid;
		this.weiId = weiId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Qid", unique = true, nullable = false)
	public Long getQid() {
		return this.qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	@Column(name = "Cid")
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}