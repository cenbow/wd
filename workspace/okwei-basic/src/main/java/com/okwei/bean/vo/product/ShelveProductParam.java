package com.okwei.bean.vo.product;

public class ShelveProductParam {
	
	private String fun;//offSell(下架)，delete（删除）,onSell(上架)
	//上架参数	
	private Long shelveWeiid;//来源的微店号
	
	private String shevleDes;//上架描述
	
	private Long shopClassId;//要上架到自己的店铺分类id
	
	//下架删除参数
	private Integer isAll;//是否全选（1是0否）

	private String ids;//产品id集合(json格式  List<Object> ){proNo:产品编号}

	private Short state;//(默认为0，  删除操作时填写：1销售中、 4已下架、 3草稿箱、  6待审核【平台号】、 7申请中【平台号子供应商 】)

	public Long getShopClassId() {
		return shopClassId;
	}

	public void setShopClassId(Long shopClassId) {
		this.shopClassId = shopClassId;
	}

	public Long getShelveWeiid() {
		return shelveWeiid;
	}

	public void setShelveWeiid(Long shelveWeiid) {
		this.shelveWeiid = shelveWeiid;
	}

	public String getShevleDes() {
		return shevleDes;
	}

	public void setShevleDes(String shevleDes) {
		this.shevleDes = shevleDes;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	public Integer getIsAll() {
		return isAll;
	}

	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}
}
