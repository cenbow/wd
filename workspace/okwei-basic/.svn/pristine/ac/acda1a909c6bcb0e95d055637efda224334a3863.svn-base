package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SZambiaActive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_ZambiaActive")
public class SZambiaActive implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4510861717837244227L;
	// Fields

	private Integer zambiaId;
	private Long shareId;
	private Long shareWeiId;
	private Long weiId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public SZambiaActive() {
	}

	/** full constructor */
	public SZambiaActive(Long shareId, Long weiId, Date createTime) {
		this.shareId = shareId;
		this.weiId = weiId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ZambiaID", unique = true, nullable = false)
	public Integer getZambiaId() {
		return this.zambiaId;
	}

	public void setZambiaId(Integer zambiaId) {
		this.zambiaId = zambiaId;
	}

	@Column(name = "ShareID")
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	
	@Column(name = "ShareWeiId")
	public Long getShareWeiId() {
		return this.shareWeiId;
	}

	public void setShareWeiId(Long shareWeiId) {
		this.shareWeiId = shareWeiId;
	}
	
	

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}