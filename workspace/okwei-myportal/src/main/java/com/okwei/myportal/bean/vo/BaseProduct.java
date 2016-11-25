package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.vo.order.ProductBatchPriceVO;
import com.okwei.bean.vo.order.ProductImgVO;
import com.okwei.bean.vo.product.ProductParamVO;
import com.okwei.bean.vo.product.ProductSellKeyVO;
import com.okwei.bean.vo.product.ProductStylesVO;

public class BaseProduct implements Serializable{

	private static final long serialVersionUID = 8487253711001929418L;
	/**
	 * 产品编号
	 */
	private long productId;
	/**
	 * 产品标题
	 */
	private String productTitle;
	/**
	 * 商品副标题
	 */
	private String productMinTitle;
	
	/**
	 * 分类ID
	 */
	private Integer classId;
	
	/**
	 * 分类信息实体
	 */
	private ProductClassVO classVO;
	/**
	 * 店铺分类ID
	 */
	private int sid;
	/**
	 * 产品默认图片
	 */
	private String productImg;
	/**
	 * 价格
	 */
	private double price;
	/**
	 * 佣金
	 */
	private double commission;
	/**
	 * 供应商weiid
	 */
	private long supplyerWeiid;
	/**
	 * 邮费模板ID
	 */
	private Integer freightId;
	/**
	 * 商品关键字
	 */
	private String keyWords;
	/**
	 * 商品属性列表
	 */
	private List<ProductParamVO> paramList;
	/**
	 * 图片列表
	 */
	private List<ProductImgVO> imgList;
	
	/**
	 * 款式列表
	 */
	private List<ProductStylesVO> styleList;
	
	/**
	 * 款式key列表
	 */
	private List<ProductSellKeyVO> sellKeyList;
	/**
	 * 款式Value 列表
	 */
	private List<ProductSellValueVO> sellValueList;
	/**
	 * 预定价格
	 */
	private PPreOrderVO preOrder;
	/**
	 * 批发价格列表
	 */
	private List<ProductBatchPriceVO> batchPriceList;
	
	public ProductClassVO getClassVO() {
		return classVO;
	}
	public void setClassVO(ProductClassVO classVO) {
		this.classVO = classVO;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductMinTitle() {
		return productMinTitle;
	}
	public void setProductMinTitle(String productMinTitle) {
		this.productMinTitle = productMinTitle;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public long getSupplyerWeiid() {
		return supplyerWeiid;
	}
	public void setSupplyerWeiid(long supplyerWeiid) {
		this.supplyerWeiid = supplyerWeiid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Integer getFreightId() {
		return freightId;
	}
	public void setFreightId(Integer freightId) {
		this.freightId = freightId;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public List<ProductParamVO> getParamList() {
		return paramList;
	}
	public void setParamList(List<ProductParamVO> paramList) {
		this.paramList = paramList;
	}
	public List<ProductImgVO> getImgList() {
		return imgList;
	}
	public void setImgList(List<ProductImgVO> imgList) {
		this.imgList = imgList;
	}
	public List<ProductStylesVO> getStyleList() {
		return styleList;
	}
	public void setStyleList(List<ProductStylesVO> styleList) {
		this.styleList = styleList;
	}

	public List<ProductSellKeyVO> getSellKeyList() {
		return sellKeyList;
	}
	public void setSellKeyList(List<ProductSellKeyVO> sellKeyList) {
		this.sellKeyList = sellKeyList;
	}
	public List<ProductSellValueVO> getSellValueList() {
		return sellValueList;
	}
	public void setSellValueList(List<ProductSellValueVO> sellValueList) {
		this.sellValueList = sellValueList;
	}
	public PPreOrderVO getPreOrder() {
		return preOrder;
	}
	public void setPreOrder(PPreOrderVO preOrder) {
		this.preOrder = preOrder;
	}
	public List<ProductBatchPriceVO> getBatchPriceList() {
		return batchPriceList;
	}
	public void setBatchPriceList(List<ProductBatchPriceVO> batchPriceList) {
		this.batchPriceList = batchPriceList;
	}
}
