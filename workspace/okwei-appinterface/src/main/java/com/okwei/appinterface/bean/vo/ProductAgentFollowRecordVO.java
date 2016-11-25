package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductAgentFollowRecordVO implements Serializable {
	private String followDate;  //跟进日期
	private String content;  //跟进内容
	private Long followWeiId;  //跟进人
	
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getFollowWeiId() {
		return followWeiId;
	}
	public void setFollowWeiId(Long followWeiId) {
		this.followWeiId = followWeiId;
	}
	
	
}
