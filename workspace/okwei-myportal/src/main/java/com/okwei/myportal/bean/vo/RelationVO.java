package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.okwei.util.ImgDomain;

/**
 * 
 * @ClassName: RelationVO
 * @Description: 上下游管理页面VO
 * @author xiehz
 * @date 2015年7月14日 上午10:19:36
 *
 */
public class RelationVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger weiId;
	private String weiName;
	// 微店logo
	private String images;
	private Integer province;
	private Integer city;
	private Integer district;
	private String regionDesc;
	private String qq;
	private Integer bmId;
	private String bmName;
	private Date registerTime;

	private Short isBatchSupplier;
	private Short isYunSupplier;

	public BigInteger getWeiId() {
		return weiId;
	}

	public void setWeiId(BigInteger weiId) {
		this.weiId = weiId;
	}

	public String getWeiName() {
		return weiName;
	}

	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}

	public Integer getProvince() {
		if(this.province==null)
			return 0;
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		if(this.city==null)
			return 0;
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		if(this.district==null)
			return 0;
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public String getRegionDesc() {
		return regionDesc;
	}

	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getBmId() {
		return bmId;
	}

	public void setBmId(Integer bmId) {
		this.bmId = bmId;
	}

	public String getBmName() {
		return bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = ImgDomain.GetFullImgUrl(images);
	}

	public Short getIsBatchSupplier() {
		return isBatchSupplier;
	}

	public void setIsBatchSupplier(Short isBatchSupplier) {
		this.isBatchSupplier = isBatchSupplier;
	}

	public Short getIsYunSupplier() {
		return isYunSupplier;
	}

	public void setIsYunSupplier(Short isYunSupplier) {
		this.isYunSupplier = isYunSupplier;
	}

}
