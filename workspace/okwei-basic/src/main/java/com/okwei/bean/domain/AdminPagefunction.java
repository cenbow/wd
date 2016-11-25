package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminPagefunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_pagefunction")
public class AdminPagefunction implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3100580733120422606L;
	private Integer functionid;
	private Integer pageid;
	private String chName;
	private String enName;
	private String functionRemark;
	private Date createTime;
	private Long creater;
	private Date updateTime;
	private Long updater;

	// Constructors

	/** default constructor */
	public AdminPagefunction() {
	}

	/** full constructor */
	public AdminPagefunction(Integer pageid, String chName, String enName,
			String functionRemark, Date createTime, Long creater,
			Date updateTime, Long updater) {
		this.pageid = pageid;
		this.chName = chName;
		this.enName = enName;
		this.functionRemark = functionRemark;
		this.createTime = createTime;
		this.creater = creater;
		this.updateTime = updateTime;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "functionid", unique = true, nullable = false)
	public Integer getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(Integer functionid) {
		this.functionid = functionid;
	}

	@Column(name = "pageid")
	public Integer getPageid() {
		return this.pageid;
	}

	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}

	@Column(name = "ChName", length = 32)
	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	@Column(name = "EnName", length = 32)
	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name = "FunctionRemark", length = 64)
	public String getFunctionRemark() {
		return this.functionRemark;
	}

	public void setFunctionRemark(String functionRemark) {
		this.functionRemark = functionRemark;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Updater")
	public Long getUpdater() {
		return this.updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

}