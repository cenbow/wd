package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PBrandShevle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_BrandShevle")
public class PBrandShevle implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4479410178666302077L;
	// Fields

	private Long bid;
	private Integer brandId;
	private Long productId;
	private Integer classId;
	private Integer systemClassID;
	private Long supplyerId;
	private Date createTime;
	private Short type;

	// Constructors

	/** default constructor */
	public PBrandShevle() {
	}

	/** full constructor */
	public PBrandShevle(Integer brandId, Long productId, Integer classId,
			Long supplyerId, Date createTime, Short type) {
		this.brandId = brandId;
		this.productId = productId;
		this.classId = classId;
		this.supplyerId = supplyerId;
		this.createTime = createTime;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BID", unique = true, nullable = false)
	public Long getBid() {
		return this.bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	@Column(name = "SystemClassID")
	public Integer getSystemClassID() {
		return this.systemClassID;
	}

	public void setSystemClassID(Integer classId) {
		this.systemClassID = classId;
	}

	@Column(name = "SupplyerID")
	public Long getSupplyerId() {
		return this.supplyerId;
	}

	public void setSupplyerId(Long supplyerId) {
		this.supplyerId = supplyerId;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}