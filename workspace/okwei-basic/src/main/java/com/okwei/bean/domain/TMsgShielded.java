package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMsgShielded entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MsgShielded")
public class TMsgShielded implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4006529972625309868L;
	private Long id;
	private Long weiId;
	private Long toWeiId;

	// Constructors

	/** default constructor */
	public TMsgShielded() {
	}

	/** full constructor */
	public TMsgShielded(Long weiId, Long toWeiId) {
		this.weiId = weiId;
		this.toWeiId = toWeiId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ToWeiID", nullable = false)
	public Long getToWeiId() {
		return this.toWeiId;
	}

	public void setToWeiId(Long toWeiId) {
		this.toWeiId = toWeiId;
	}

}