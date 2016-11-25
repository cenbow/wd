package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcUserAdnotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_UserADNotice")
public class PcUserAdnotice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uadId;
	private Long weiId;
	private String imgLog;
	private String url;
	private Short sort;
	private Short status;
	private String title;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PcUserAdnotice() {
	}

	/** full constructor */
	public PcUserAdnotice(Long weiId, String imgLog, String url, Short sort,
			Short status, String title, Date createTime) {
		this.weiId = weiId;
		this.imgLog = imgLog;
		this.url = url;
		this.sort = sort;
		this.status = status;
		this.title = title;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "UadID", unique = true, nullable = false)
	public Integer getUadId() {
		return this.uadId;
	}

	public void setUadId(Integer uadId) {
		this.uadId = uadId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ImgLog", length = 256)
	public String getImgLog() {
		return this.imgLog;
	}

	public void setImgLog(String imgLog) {
		this.imgLog = imgLog;
	}

	@Column(name = "Url", length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "Title", length = 64)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}