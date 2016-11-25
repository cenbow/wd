package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Message")
public class UMessage extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long messageId;
	private Long toWeiId;
	private Long weiId;
	private String message;
	private Short type;
	private Short state;
	private Date createTime;
	private String link;
	private Long keyValue;

	// Constructors

	/** default constructor */
	public UMessage() {
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Message", length = 64)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
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

	@Column(name = "Link", length = 256)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "KeyValue")
	public Long getKeyValue() {
		return this.keyValue;
	}

	public void setKeyValue(Long keyValue) {
		this.keyValue = keyValue;
	}

	@Id
	@GeneratedValue
	@Column(name = "MessageID")
	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	@Column(name = "ToWeiID")
	public Long getToWeiId() {
		return toWeiId;
	}

	public void setToWeiId(Long toWeiId) {
		this.toWeiId = toWeiId;
	}

}