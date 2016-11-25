package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.okwei.util.ImgDomain;



/**
 * PcRecommend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_Recommend")
public class PcRecommend implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5340730046778477610L;
	private Integer reId;
	private String title;
	private String content;
	private String urlImg;
	private String webUrl;
	private Short position;
	private Short status;
	private Integer sort;
	private Date createTime;
	private Date updateTime;
	private Long creater;
	private Long updater;
	private Long weiId;
	private String contentDetail;
	private String typeStr;
	
	

	// Constructors
	

	/** default constructor */
	public PcRecommend() {
	}

	/** full constructor */
	public PcRecommend(String title, String content, String urlImg, String webUrl, Short position, Short status, Integer sort, Date createTime,
			Date updateTime, Long creater, Long updater, Long weiId) {
		this.title = title;
		this.content = content;
		this.urlImg = urlImg;
		this.webUrl = webUrl;
		this.position = position;
		this.status = status;
		this.sort = sort;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.creater = creater;
		this.updater = updater;
		this.weiId = weiId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ReID", unique = true, nullable = false)
	public Integer getReId() {
		return this.reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@Column(name = "TypeStr")
	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	@Column(name = "Content", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UrlImg", length = 128)
	public String getUrlImg() {
		return this.urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = ImgDomain.GetFullImgUrl(urlImg);
	}

	@Column(name = "WebUrl", length = 128)
	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@Column(name = "Position")
	public Short getPosition() {
		return this.position;
	}

	public void setPosition(Short position) {
		this.position = position;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "Updater")
	public Long getUpdater() {
		return this.updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ContentDetail")
	public String getContentDetail() {
		return contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}
	
	

}