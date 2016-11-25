package com.okwei.bean.vo.activity;

import java.io.Serializable;

public class AHomeAppVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer homeId;
	private Integer position;
	private String title;
	private String homeImage;
	private String bannerImage;
	private int classId;
	private String className;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHomeId() {
		return homeId;
	}
	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHomeImage() {
		return homeImage;
	}
	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}
	public String getBannerImage() {
		return bannerImage;
	}
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	

}
