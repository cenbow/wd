package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActOperateLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActOperateLog")
public class AActOperateLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1807599224348123109L;
	private Long actOpId;
	private Long actId;
	private Long operator;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AActOperateLog() {
	}

	/** full constructor */
	public AActOperateLog(Long actId, Long operator, String content,
			Date createTime) {
		this.actId = actId;
		this.operator = operator;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ActOpID", unique = true, nullable = false)
	public Long getActOpId() {
		return this.actOpId;
	}

	public void setActOpId(Long actOpId) {
		this.actOpId = actOpId;
	}

	@Column(name = "ActID")
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
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