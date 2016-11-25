package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductKeyWords entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductKeyWords")
public class PProductKeyWords extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long kwid;
	private Long productId;
	private String keyWord;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PProductKeyWords() {
	}

	@Id
	@GeneratedValue
	@Column(name = "KWID")
	public Long getKwid() {
		return kwid;
	}

	public void setKwid(Long kwid) {
		this.kwid = kwid;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "KeyWord", length = 64)
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}