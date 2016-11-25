package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsRecommandMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_RecommandMsg")
public class CtsRecommandMsg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recId;
	private Date beginTime;
	private String title;
	private String msgContent;
	private String msgImg;
	private Short msgType;
	private String msgUrl;
	private Short receiveType;
	private String extaMsg;
	private Short status;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;

	// Constructors

	/** default constructor */
	public CtsRecommandMsg() {
	}

	/** full constructor */
	public CtsRecommandMsg(Date beginTime, String title,
			String msgContent, String msgImg, Short msgType, String msgUrl,
			Short receiveType, String extaMsg, Short status,
			Date createTime, Date updateTime, String creater,
			String updater) {
		this.beginTime = beginTime;
		this.title = title;
		this.msgContent = msgContent;
		this.msgImg = msgImg;
		this.msgType = msgType;
		this.msgUrl = msgUrl;
		this.receiveType = receiveType;
		this.extaMsg = extaMsg;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.creater = creater;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RecID", unique = true, nullable = false)
	public Integer getRecId() {
		return this.recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@Column(name = "BeginTime", length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MsgContent", length = 512)
	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "MsgImg", length = 256)
	public String getMsgImg() {
		return this.msgImg;
	}

	public void setMsgImg(String msgImg) {
		this.msgImg = msgImg;
	}

	@Column(name = "MsgType")
	public Short getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	@Column(name = "MsgUrl", length = 256)
	public String getMsgUrl() {
		return this.msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	@Column(name = "ReceiveType")
	public Short getReceiveType() {
		return this.receiveType;
	}

	public void setReceiveType(Short receiveType) {
		this.receiveType = receiveType;
	}

	@Column(name = "ExtaMsg", length = 512)
	public String getExtaMsg() {
		return this.extaMsg;
	}

	public void setExtaMsg(String extaMsg) {
		this.extaMsg = extaMsg;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Creater", length = 32)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "Updater", length = 32)
	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

}