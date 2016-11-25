package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductClass")
public class PProductClass extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer classId;
	private Integer parentId;
	private String className;
	private Short step;
	private String headImg;
	private Integer sort;
	private String seotitle;
	private String seokeyword;
	private String seodes;

	// Constructors
	@Column(name = "Sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "SeoTitle", length = 128)
	public String getSeotitle() {
		return seotitle;
	}

	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}

	@Column(name = "SeoKeyWord", length = 128)
	public String getSeokeyword() {
		return seokeyword;
	}

	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}

	@Column(name = "SeoDes", length = 256)
	public String getSeodes() {
		return seodes;
	}

	public void setSeodes(String seodes) {
		this.seodes = seodes;
	}

	/** default constructor */
	public PProductClass() {
	}

	/** full constructor */
	public PProductClass(Integer parentId, String className, Short step) {
		this.parentId = parentId;
		this.className = className;
		this.step = step;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ClassID", unique = true, nullable = false)
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "ParentID")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "ClassName", length = 64)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "Step")
	public Short getStep() {
		return this.step;
	}

	public void setStep(Short step) {
		this.step = step;
	}

	@Column(name = "HeadImg", length = 128)
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}