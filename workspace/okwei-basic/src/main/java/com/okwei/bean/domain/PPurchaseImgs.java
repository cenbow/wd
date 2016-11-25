package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPurchaseImgs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PurchaseImgs")
public class PPurchaseImgs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142537347817235599L;
	private Long cid;
	private String img;
	private Long qid;
	private Long pid;
	// Constructors

	/** default constructor */
	public PPurchaseImgs() {
	}
 
	/** full constructor */
	public PPurchaseImgs(Long cid, String img, Long qid) {
		this.cid = cid;
		this.img = img;
		this.qid=qid;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "Qid", unique = true, nullable = false)
	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}
	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "Cid")
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "Img", length = 256)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}


}