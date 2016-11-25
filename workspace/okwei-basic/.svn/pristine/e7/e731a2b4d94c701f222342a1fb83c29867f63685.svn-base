package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBatchMarketLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BatchMarketLog")
public class TBatchMarketLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1389611587462424235L;
	// Fields

	private Integer logId;
	private Integer bmId;
	private Long verId;
	private String verName;
	private String verContent;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TBatchMarketLog() {
	}

	/** full constructor */
	public TBatchMarketLog(Long verId, String verName, String verContent,
			Date createTime) {
		this.verId = verId;
		this.verName = verName;
		this.verContent = verContent;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LogID", unique = true, nullable = false)
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Column(name = "VerID")
	public Long getVerId() {
		return this.verId;
	}

	public void setVerId(Long verId) {
		this.verId = verId;
	}

	@Column(name = "VerName", length = 32)
	public String getVerName() {
		return this.verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	@Column(name = "VerContent", length = 128)
	public String getVerContent() {
		return this.verContent;
	}

	public void setVerContent(String verContent) {
		this.verContent = verContent;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    @Column(name="BMID")
	public Integer getBmId() {
		return bmId;
	}

	public void setBmId(Integer bmId) {
		this.bmId = bmId;
	}

}