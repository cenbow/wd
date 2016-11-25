package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPostAgeDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PostAgeDetails")
public class PPostAgeDetails extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer postDetailsId;
	private Integer freightId;
	private Short courierType;
	private String destination;
	private Short firstCount;
	private Double firstpiece;
	private Short moreCount;
	private Double morepiece;
	private Short status;

	// Constructors

	

	/** default constructor */
	public PPostAgeDetails() {
	}

	/** full constructor */
	public PPostAgeDetails(Integer freightId, Short courierType, String destination, Short firstCount, Double firstpiece, Short moreCount, Double morepiece,
			Short status) {
		this.freightId = freightId;
		this.courierType = courierType;
		this.destination = destination;
		this.firstCount = firstCount;
		this.firstpiece = firstpiece;
		this.moreCount = moreCount;
		this.morepiece = morepiece;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PostDetailsID", unique = true, nullable = false)
	public Integer getPostDetailsId() {
		return this.postDetailsId;
	}

	public void setPostDetailsId(Integer postDetailsId) {
		this.postDetailsId = postDetailsId;
	}

	@Column(name = "FreightID")
	public Integer getFreightId() {
		return this.freightId;
	}

	public void setFreightId(Integer freightId) {
		this.freightId = freightId;
	}

	@Column(name = "CourierType")
	public Short getCourierType() {
		return this.courierType;
	}

	public void setCourierType(Short courierType) {
		this.courierType = courierType;
	}

	@Column(name = "Destination", length = 4096)
	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Column(name = "FirstCount")
	public Short getFirstCount() {
		return this.firstCount;
	}

	public void setFirstCount(Short firstCount) {
		this.firstCount = firstCount;
	}

	@Column(name = "Firstpiece", precision = 18)
	public Double getFirstpiece() {
		return this.firstpiece;
	}

	public void setFirstpiece(Double firstpiece) {
		this.firstpiece = firstpiece;
	}

	@Column(name = "MoreCount")
	public Short getMoreCount() {
		return this.moreCount;
	}

	public void setMoreCount(Short moreCount) {
		this.moreCount = moreCount;
	}

	@Column(name = "Morepiece", precision = 18)
	public Double getMorepiece() {
		return this.morepiece;
	}

	public void setMorepiece(Double morepiece) {
		this.morepiece = morepiece;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}