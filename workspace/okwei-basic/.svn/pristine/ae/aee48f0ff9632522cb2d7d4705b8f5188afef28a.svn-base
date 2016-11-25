package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminFunctiondatadim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_functiondatadim")
public class AdminFunctiondatadim implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4922747778283647831L;
	private Integer dimid;
	private Integer functionid;
	private String chName;
	private String enName;
	private String dataRemark;
	private String funcData;
	private Date createTime;
	private Long creater;
	private Date updateTime;
	private Long updater;

	// Constructors

	/** default constructor */
	public AdminFunctiondatadim() {
	}

	/** full constructor */
	public AdminFunctiondatadim(Integer functionid, String chName,
			String enName, String dataRemark, String funcData,
			Date createTime, Long creater, Date updateTime,
			Long updater) {
		this.functionid = functionid;
		this.chName = chName;
		this.enName = enName;
		this.dataRemark = dataRemark;
		this.funcData = funcData;
		this.createTime = createTime;
		this.creater = creater;
		this.updateTime = updateTime;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dimid", unique = true, nullable = false)
	public Integer getDimid() {
		return this.dimid;
	}

	public void setDimid(Integer dimid) {
		this.dimid = dimid;
	}

	@Column(name = "functionid")
	public Integer getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(Integer functionid) {
		this.functionid = functionid;
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

	@Column(name = "DataRemark", length = 64)
	public String getDataRemark() {
		return this.dataRemark;
	}

	public void setDataRemark(String dataRemark) {
		this.dataRemark = dataRemark;
	}

	@Column(name = "FuncData", length = 64)
	public String getFuncData() {
		return this.funcData;
	}

	public void setFuncData(String funcData) {
		this.funcData = funcData;
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