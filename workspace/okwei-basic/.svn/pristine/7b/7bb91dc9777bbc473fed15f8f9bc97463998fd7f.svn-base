package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THouseCarouselLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_HouseCarouselLog")
public class THouseCarouselLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3693624752571870435L;
	// Fields

	private Integer id;
	private Long operater;
	private Integer operaterId;
	private String operateName;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public THouseCarouselLog() {
	}

	/** full constructor */
	public THouseCarouselLog(Long operater, Integer operaterId,
			String operateName, String content, Date createTime) {
		this.operater = operater;
		this.operaterId = operaterId;
		this.operateName = operateName;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Operater")
	public Long getOperater() {
		return this.operater;
	}

	public void setOperater(Long operater) {
		this.operater = operater;
	}

	@Column(name = "OperaterId")
	public Integer getOperaterId() {
		return this.operaterId;
	}

	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}

	@Column(name = "OperateName", length = 32)
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Column(name = "Content", length = 258)
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

}