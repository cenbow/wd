package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UTuizhuLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_TuizhuLog")
public class UTuizhuLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249337821215380990L;
	// Fields

	private Integer id;
	private Integer tid;
	private Long weiId;
	private Short type;
	private Long operator;
	private String content;
	private Short state;
	private Date processTime;
	private String detailPath;

	// Constructors

	/** default constructor */
	public UTuizhuLog() {
	}

	/** full constructor */
	public UTuizhuLog(Integer tid, Long weiId, Short type, Long operator,
			String content, Short state, Date processTime,
			String detailPath) {
		this.tid = tid;
		this.weiId = weiId;
		this.type = type;
		this.operator = operator;
		this.content = content;
		this.state = state;
		this.processTime = processTime;
		this.detailPath = detailPath;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TID")
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "Content", length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "ProcessTime", length = 0)
	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Column(name = "DetailPath", length = 100)
	public String getDetailPath() {
		return this.detailPath;
	}

	public void setDetailPath(String detailPath) {
		this.detailPath = detailPath;
	}

}