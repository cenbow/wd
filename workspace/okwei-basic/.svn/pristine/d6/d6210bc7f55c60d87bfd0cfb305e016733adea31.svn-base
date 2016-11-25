package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BFootPhone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_FootPhone")
public class BFootPhone implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8146008190938353184L;
	private Integer id;
	private String phone;
	private String extensionNums;
	private Short type;

	// Constructors

	/** default constructor */
	public BFootPhone() {
	}

	/** full constructor */
	public BFootPhone(String phone, String extensionNums, Short type) {
		this.phone = phone;
		this.extensionNums = extensionNums;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ExtensionNums", length = 64)
	public String getExtensionNums() {
		return this.extensionNums;
	}

	public void setExtensionNums(String extensionNums) {
		this.extensionNums = extensionNums;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}