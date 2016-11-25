package com.okwei.bean.domain;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AFreeWater entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_FreeWater")
public class AFreeWater implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6327941082614509154L;
	private Long weiId;
	private String name;
	private String phone;
	private String address;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AFreeWater() {
	}

	/** full constructor */
	public AFreeWater(String name, String phone, String address, Short state,
			Date createTime) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Address", length = 64)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}