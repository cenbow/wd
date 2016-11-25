package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMsgPush entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MsgPush")
public class TMsgPush extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String msgType;
	private String title;
	private String imgSrc;
	private String subHead;
	private String imgLink;
	private String description;
	private String sendObj;
	private String sendMethod;
	private Date sendDateTimeAndroid;
	private Date sendDateTimeIos;
	private String sendStateAndroid;
	private String sendStateIos;
	private Date createTime;
	private Integer operator;
	private Integer sort;
	private String status;
	private Date bookingSendTime;
	private String msgIdIos;
	private Integer readNumber;
	private Integer zan;

	// Constructors

	/** default constructor */
	public TMsgPush() {
	}

	/** minimal constructor */
	public TMsgPush(String msgType, String subHead) {
		this.msgType = msgType;
		this.subHead = subHead;
	}

	/** full constructor */
	public TMsgPush(String msgType, String title, String imgSrc, String subHead, String imgLink, String description, String sendObj, String sendMethod,
			Date sendDateTimeAndroid, Date sendDateTimeIos, String sendStateAndroid, String sendStateIos, Date createTime, Integer operator, Integer sort,
			String status, Date bookingSendTime, String msgIdIos, Integer readNumber, Integer zan) {
		this.msgType = msgType;
		this.title = title;
		this.imgSrc = imgSrc;
		this.subHead = subHead;
		this.imgLink = imgLink;
		this.description = description;
		this.sendObj = sendObj;
		this.sendMethod = sendMethod;
		this.sendDateTimeAndroid = sendDateTimeAndroid;
		this.sendDateTimeIos = sendDateTimeIos;
		this.sendStateAndroid = sendStateAndroid;
		this.sendStateIos = sendStateIos;
		this.createTime = createTime;
		this.operator = operator;
		this.sort = sort;
		this.status = status;
		this.bookingSendTime = bookingSendTime;
		this.msgIdIos = msgIdIos;
		this.readNumber = readNumber;
		this.zan = zan;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "MsgType", nullable = false, length = 20)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "Title", length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ImgSrc", length = 500)
	public String getImgSrc() {
		return this.imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	@Column(name = "SubHead", nullable = false, length = 500)
	public String getSubHead() {
		return this.subHead;
	}

	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}

	@Column(name = "ImgLink", length = 500)
	public String getImgLink() {
		return this.imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	@Column(name = "Description", length = 8000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SendObj", length = 10)
	public String getSendObj() {
		return this.sendObj;
	}

	public void setSendObj(String sendObj) {
		this.sendObj = sendObj;
	}

	@Column(name = "SendMethod", length = 10)
	public String getSendMethod() {
		return this.sendMethod;
	}

	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}

	@Column(name = "SendDateTimeAndroid", length = 0)
	public Date getSendDateTimeAndroid() {
		return this.sendDateTimeAndroid;
	}

	public void setSendDateTimeAndroid(Date sendDateTimeAndroid) {
		this.sendDateTimeAndroid = sendDateTimeAndroid;
	}

	@Column(name = "SendDateTimeIOS", length = 0)
	public Date getSendDateTimeIos() {
		return this.sendDateTimeIos;
	}

	public void setSendDateTimeIos(Date sendDateTimeIos) {
		this.sendDateTimeIos = sendDateTimeIos;
	}

	@Column(name = "SendStateAndroid", length = 50)
	public String getSendStateAndroid() {
		return this.sendStateAndroid;
	}

	public void setSendStateAndroid(String sendStateAndroid) {
		this.sendStateAndroid = sendStateAndroid;
	}

	@Column(name = "SendStateIOS", length = 50)
	public String getSendStateIos() {
		return this.sendStateIos;
	}

	public void setSendStateIos(String sendStateIos) {
		this.sendStateIos = sendStateIos;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Operator")
	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "Status", length = 8)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BookingSendTime", length = 0)
	public Date getBookingSendTime() {
		return this.bookingSendTime;
	}

	public void setBookingSendTime(Date bookingSendTime) {
		this.bookingSendTime = bookingSendTime;
	}

	@Column(name = "Msg_id_IOS", length = 50)
	public String getMsgIdIos() {
		return this.msgIdIos;
	}

	public void setMsgIdIos(String msgIdIos) {
		this.msgIdIos = msgIdIos;
	}

	@Column(name = "ReadNumber")
	public Integer getReadNumber() {
		return this.readNumber;
	}

	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}

	@Column(name = "Zan")
	public Integer getZan() {
		return this.zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

}