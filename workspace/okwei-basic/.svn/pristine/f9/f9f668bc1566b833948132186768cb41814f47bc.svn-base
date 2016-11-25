package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPurchases entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_Purchases")
public class PPurchases implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -165067603796975959L;
	private Long cid;
	private Long weiId;
	private Integer classId;
	private String content;
	private Date createTime;
	private Short state;
	private Integer count;

	// Constructors

	/** default constructor */
	public PPurchases() {
	}

	/** full constructor */
	public PPurchases(Long weiId, String content, Date createTime,
			Short state, Integer count) {
		this.weiId = weiId;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
		this.count = count;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Cid", unique = true, nullable = false)
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

	@Column(name="ClassID")
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "Content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "Count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}