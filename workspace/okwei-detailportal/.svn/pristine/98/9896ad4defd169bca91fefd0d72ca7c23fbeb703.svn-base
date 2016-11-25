package com.okwei.detail.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductComment;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.detail.dao.IProductDao;

@Repository
public class ProductDao extends BaseDAO implements IProductDao {

    @Override
    public PProducts getProducts(long proID) {
	return super.get(PProducts.class, proID);
    }

    @Override
    public List<PProductImg> getProImgList(long proID) {
	String hql = "from PProductImg where productId=?";
	return super.find(hql, new Object[] { proID });
    }

    @Override
    public List<PProductParamKv> getParamList(long proID) {
	String hql = "from PProductParamKv where productId=?";
	return super.find(hql, new Object[] { proID });
    }

    @Override
    public List<PProducts> getProList(long supWeiID, long proID) {
	String hql = "from PProducts where state=? and supplierWeiId=? and productId!=? order by updateTime desc";
	return super.findPage(hql, 0, 15, new Object[] { Short.parseShort(ProductStatusEnum.Showing.toString()), supWeiID, proID });
    }

    @Override
    public PProductAssist getProductAssist(long proID) {
	return super.get(PProductAssist.class, proID);
    }

    @Override
    public void saveAssist(PProductAssist entity) {
	super.save(entity);
    }

    @Override
    public void updateAssist(PProductAssist entity) {
	super.update(entity);
    }

    @Override
    public List<PProductComment> getComments(long proID, int index, int size) {
    String hql = "from PProductComment where productId=? order by createTime desc";
	return super.findPage(hql, index * size, size, new Object[] { proID });
    }

    @Override
    public List<UWeiSeller> getSellers(Long[] weiids) {
	String hql = "from UWeiSeller where weiId in(:weiids)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiids", weiids);
	return super.findByMap(hql, params);
    }

    @Override
    public PPostAgeModel getPostAgeModel(int freightId) {
	return super.get(PPostAgeModel.class, freightId);
    }

    @Override
    public List<PPostAgeDetails> getAgeDetails(int freightId) {
	String hql = "from PPostAgeDetails where freightId=? order by status";
	return super.find(hql, new Object[] { freightId });
    }

    @Override
    public long getSupProCount(long supWeiID) {
	String hql = "select count(1) from PProducts where state=? and supplierWeiId=?";
	return super.count(hql, new Object[] { Short.parseShort(ProductStatusEnum.Showing.toString()), supWeiID });
    }

    @Override
    public boolean getIsAttention(long userID, long supID) {
	String hql = "select count(1) from UAttention where attentioner=? and attTo=?";
	long count = super.count(hql, new Object[] { userID, supID });
	if (count > 0)
	    return true;
	return false;
    }

    @Override
    public void deleteAttention(long userID, long supID) {
	String hql = "delete from UAttention where attentioner=? and attTo=?";
	Object[] params = new Object[2];
	params[0] = userID;// 关注人
	params[1] = supID;// 被关注人
	super.executeHql(hql, params);
	hql = "delete from UAttentioned where attentioner=? and attTo=?";
	super.executeHql(hql, params);
    }

    @Override
    public void addAttention(UAttention entity) {
	super.save(entity);
    }

    @Override
    public void addAttention(UAttentioned entity) {
	super.save(entity);
    }

    @Override
    public boolean getIsAttentioned(long userID, long supID) {
	String hql = "select count(1) from UAttentioned where attentioner=? and attTo=?";
	if (super.count(hql, new Object[] { userID, supID }) > 0)
	    return true;
	return false;
    }

    @Override
    public UWallet getWallet(long weiid) {
	return super.get(UWallet.class, weiid);
    }

    @Override
    public List<PShevleBatchPrice> getBatchPrices(long id) {
	String hql = "from PShevleBatchPrice p where p.id=? and p.price is not null and p.price>0 order by p.count";
	return super.find(hql, new Object[] { id });
    }

    @Override
    public PPreOrder getPreOrder(long proID) {
	return super.get(PPreOrder.class, proID);
    }

    @Override
    public PClassProducts getClassProducts(long weiid, long proID) {
	String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
	return super.getUniqueResultByHql(hql, new Object[] { weiid, proID });
    }

    @Override
    public TShoppingCar getShopCart(long proID, long weiid, long styleid, long supid, short type, long shopWeiID) {
	String hql = "from TShoppingCar p where p.proNum=? and p.weiId=? and p.styleId=? and p.supplierWeiId=? and p.buyType=? and p.shopWeiID=?";
	return super.getUniqueResultByHql(hql, new Object[] { proID, weiid, styleid, supid, type, shopWeiID });
    }

    @Override
    public List<PProducts> getProList(Long[] proids) {
	String hql = "from PProducts where productId in(:proids) and state=:state ";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("proids", proids);
	params.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
	return super.findByMap(hql, params);
    }

}
