package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductSearchHot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductSearchHot")
public class PProductSearchHot extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long searchId;
	private Long productId;
	private String keyWord;
	private Long hot;

	// Constructors

	/** default constructor */
	public PProductSearchHot() {
	}

	/** full constructor */
	public PProductSearchHot(Long searchId, Long productId, String keyWord, Long hot) {
		this.searchId = searchId;
		this.productId = productId;
		this.keyWord = keyWord;
		this.hot = hot;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SearchID")
	public Long getSearchId() {
		return this.searchId;
	}

	public void setSearchId(Long searchId) {
		this.searchId = searchId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "KeyWord", length = 64)
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "Hot")
	public Long getHot() {
		return this.hot;
	}

	public void setHot(Long hot) {
		this.hot = hot;
	}

}