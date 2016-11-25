package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.util.Date;

public class DistributorVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String qq;
	private Date date;
	private Integer commission_t;
	private Integer commission_m;
	private String region;
	private Integer count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCommission_t() {
		return commission_t;
	}

	public void setCommission_t(Integer commission_t) {
		this.commission_t = commission_t;
	}

	public Integer getCommission_m() {
		return commission_m;
	}

	public void setCommission_m(Integer commission_m) {
		this.commission_m = commission_m;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
