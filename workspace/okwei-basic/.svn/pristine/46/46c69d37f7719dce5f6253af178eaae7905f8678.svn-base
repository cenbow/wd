package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_Activity")
public class AActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3441638192378470432L;
	private Long actId;
	private String title;
	private String demand;
	private Date applyEndTime;
	private Date applyBeginTime;
	private Date startTime;
	private Date endTime;
	private Short state;
	private Date createTime;
	private Long creator;
	private Date updateTime;
	private Integer sellerCount;
	private Integer type;

	// Constructors

	/** default constructor */
	public AActivity() {
	}

	/** full constructor */
	public AActivity(String title, String demand, Date applyEndTime,
			Date applyBeginTime, Date endTime, Short state,
			Date createTime, Long creator, Date updateTime,
			Integer sellerCount) {
		this.title = title;
		this.demand = demand;
		this.applyEndTime = applyEndTime;
		this.applyBeginTime = applyBeginTime;
		this.endTime = endTime;
		this.state = state;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.sellerCount = sellerCount;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ActID", unique = true, nullable = false)
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	@Column(name = "Title", length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Demand", length = 256)
	public String getDemand() {
		return this.demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	@Column(name = "ApplyEndTime", length = 0)
	public Date getApplyEndTime() {
		return this.applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	@Column(name = "ApplyBeginTime", length = 0)
	public Date getApplyBeginTime() {
		return this.applyBeginTime;
	}

	public void setApplyBeginTime(Date applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	@Column(name = "StartTime", length = 0)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	@Column(name = "EndTime", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	@Column(name = "Creator")
	public Long getCreator() {
		return this.creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "SellerCount")
	public Integer getSellerCount() {
		return this.sellerCount;
	}

	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}
	
	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


}