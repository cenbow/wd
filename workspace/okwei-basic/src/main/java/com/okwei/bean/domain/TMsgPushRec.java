package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMsgPushRec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MsgPushRec")
public class TMsgPushRec extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mprid;
	private Integer mpid;
	private String sendState;
	private Date sendDateTime;
	private Integer operatorId;
	private String msgIdAndroid;
	private String msgIdIos;
	private String productionMode;
	private Date createTime;
	private String appType;

	// Constructors

	/** default constructor */
	public TMsgPushRec() {
	}

	/** full constructor */
	public TMsgPushRec(Integer mpid, String sendState, Date sendDateTime, Integer operatorId, String msgIdAndroid, String msgIdIos, String productionMode,
			Date createTime, String appType) {
		this.mpid = mpid;
		this.sendState = sendState;
		this.sendDateTime = sendDateTime;
		this.operatorId = operatorId;
		this.msgIdAndroid = msgIdAndroid;
		this.msgIdIos = msgIdIos;
		this.productionMode = productionMode;
		this.createTime = createTime;
		this.appType = appType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MPRID", unique = true, nullable = false)
	public Integer getMprid() {
		return this.mprid;
	}

	public void setMprid(Integer mprid) {
		this.mprid = mprid;
	}

	@Column(name = "MPID")
	public Integer getMpid() {
		return this.mpid;
	}

	public void setMpid(Integer mpid) {
		this.mpid = mpid;
	}

	@Column(name = "SendState", length = 50)
	public String getSendState() {
		return this.sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	@Column(name = "SendDateTime", length = 0)
	public Date getSendDateTime() {
		return this.sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	@Column(name = "OperatorId")
	public Integer getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "Msg_id_Android", length = 50)
	public String getMsgIdAndroid() {
		return this.msgIdAndroid;
	}

	public void setMsgIdAndroid(String msgIdAndroid) {
		this.msgIdAndroid = msgIdAndroid;
	}

	@Column(name = "Msg_id_IOS", length = 50)
	public String getMsgIdIos() {
		return this.msgIdIos;
	}

	public void setMsgIdIos(String msgIdIos) {
		this.msgIdIos = msgIdIos;
	}

	@Column(name = "ProductionMode", length = 20)
	public String getProductionMode() {
		return this.productionMode;
	}

	public void setProductionMode(String productionMode) {
		this.productionMode = productionMode;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "AppType", length = 20)
	public String getAppType() {
		return this.appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

}