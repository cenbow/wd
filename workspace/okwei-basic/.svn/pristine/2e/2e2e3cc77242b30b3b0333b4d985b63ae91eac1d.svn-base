package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductComment")
public class PProductComment extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long commentId;
	private Long productId;
	private String content;
	private Long weiid;
	private Short statu;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PProductComment() {
	}

	public PProductComment(Long commentId, Long productId, String content, Long weiid, Short statu, Date createTime) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.content = content;
		this.weiid = weiid;
		this.statu = statu;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "CommentID")
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "Content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "Weiid")
	public Long getWeiid() {
		return this.weiid;
	}

	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}

	@Column(name = "Statu")
	public Short getStatu() {
		return this.statu;
	}

	public void setStatu(Short statu) {
		this.statu = statu;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}