package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AShopRecommendLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ShopRecommendLog")
public class AShopRecommendLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7906529781440492228L;
	// Fields

	private Integer shopRecLogId;
	private Integer recId;
	private String operator;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AShopRecommendLog() {
	}

	/** full constructor */
	public AShopRecommendLog(Integer recId, String operator, String content,
			Date createTime) {
		this.recId = recId;
		this.operator = operator;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ShopRecLogID", unique = true, nullable = false)
	public Integer getShopRecLogId() {
		return this.shopRecLogId;
	}

	public void setShopRecLogId(Integer shopRecLogId) {
		this.shopRecLogId = shopRecLogId;
	}

	@Column(name = "RecID")
	public Integer getRecId() {
		return this.recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@Column(name = "Operator", length = 128)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "Content", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}