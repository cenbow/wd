package com.okwei.myportal.bean.vo;

/**
 * 分享列表中对象的 信息
 * @author fh
 */
public class SMainDataVO {
	/**
	 * 分享ID
	 */
	private long shareId;
	
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 是否转发，0否，1是
	 */
	private int isForward;
	/**
	 * 产品数量
	 */
	private Integer pcount;
	/**
	 * 制作分享人
	 */
	private long makeWeiID;
	/**
	 * 分享人
	 */
	private long shareWeiID;
	/**
	 * 推广语
	 */
	private String describle;
	
	/**
	 * 发布时间
	 */
	private String createTime;
	
	/**
	 * 是否上架
	 */
	private Short onHomePage;
	/**
	 * 状态ID
	 */
	private Short status;
	 
	 
	public long getMakeWeiID() {
		return makeWeiID;
	}
	public void setMakeWeiID(long makeWeiID) {
		this.makeWeiID = makeWeiID;
	}
	public int getIsForward() {
		return isForward;
	}
	public void setIsForward(int isForward) {
		this.isForward = isForward;
	}
	public long getShareWeiID() {
		return shareWeiID;
	}
	public void setShareWeiID(long shareWeiID) {
		this.shareWeiID = shareWeiID;
	}
	public Short getOnHomePage() {
		return onHomePage;
	}
	public void setOnHomePage(Short onHomePage) {
		this.onHomePage = onHomePage;
	}
	public long getShareId() {
		return shareId;
	}
	public void setShareId(long shareId) {
		this.shareId = shareId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPcount() {
		return pcount;
	}
	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	
}
