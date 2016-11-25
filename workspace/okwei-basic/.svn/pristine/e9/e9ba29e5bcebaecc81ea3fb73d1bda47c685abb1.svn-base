package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OBatchAssistatOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PushShelves")
public class PPushShelves implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long weiId;
	private String content;
	private Short pushType;
	private Date createTime;
	private Long productId;


	// Fields
	public PPushShelves() {
	}
	
	public PPushShelves(Long id, Long weiId, String content, Short pushType,
			Date createTime,Long productId) {
		super();
		this.id = id;
		this.weiId = weiId;
		this.content = content;
		this.pushType = pushType;
		this.createTime = createTime;
		this.productId = productId;
	}
	@Column(name = "ProductId")
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "WeiId")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	@Column(name = "Content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "PushType")
	public Short getPushType() {
		return pushType;
	}

	public void setPushType(Short pushType) {
		this.pushType = pushType;
	}
	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}