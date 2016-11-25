package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSearchHot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SearchHot")
public class TSearchHot extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long searchId;
	private String keyWord;
	private Long hot;

	// Constructors

	/** default constructor */
	public TSearchHot() {
	}

	/** full constructor */
	public TSearchHot(String keyWord, Long hot) {
		this.keyWord = keyWord;
		this.hot = hot;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "SearchID", unique = true, nullable = false)
	public Long getSearchId() {
		return this.searchId;
	}

	public void setSearchId(Long searchId) {
		this.searchId = searchId;
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