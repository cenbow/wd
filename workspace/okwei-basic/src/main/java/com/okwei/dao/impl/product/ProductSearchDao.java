package com.okwei.dao.impl.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActShowProducts;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.vo.activity.ActProductInfo;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.util.RedisUtil;

@Repository
public class ProductSearchDao extends BaseDAO implements IProductSearchDao {

	// 参加活动的产品
	private String actProkeyName = "Product_redis_piece_";
	//未参加活动的产品
	private String actProNotkeyName = "Product_redis_notin_piece_";
	//产品缓存
	private String productHeadName="Pro_Stock_piece_";
	//产品缓存 分区数量
	private int productPiecePageCount=10000;
	//活动产品分区数量
	private int actProPiecePageCount=1000;

	public PProducts getPProducts(Long productID) {
		String keyName = productHeadName +(productID % productPiecePageCount);
		Map<Long, PProducts> map = (Map<Long, PProducts>) RedisUtil.getObject(keyName);
		if(map!=null&&map.values().size()>0){
			if(map.containsKey(productID)){
				return map.get(productID);
			}
		}
		PProducts product=super.get(PProducts.class, productID);
		if(product!=null){
			if(map==null||map.values().size()<=0){
				map=new HashMap<Long, PProducts>();
			}
			map.put(productID, product);
		}
		return product;
	}
	
	public List<PProducts> find_PProducts(List<Long>productids){
		List<PProducts> productList=new ArrayList<PProducts>();
		if(productids!=null&&productids.size()>0){
			for (Long proid : productids) {
				productList.add(getPProducts(proid));
			}
		}
		return productList;
	}
	
	public void setPProduct(Long productId){
		PProducts product=super.get(PProducts.class, productId);
		String keyName = productHeadName +(productId % productPiecePageCount);
		Map<Long, PProducts> map = (Map<Long, PProducts>) RedisUtil.getObject(keyName);
		if(map==null||map.values().size()<=0){
			map=new HashMap<Long, PProducts>();
		}
		map.put(productId, product);
	}
	public void setPProduct(PProducts product){
		if(product==null||product.getProductId()==null)
			return ;
		String keyName = productHeadName +(product.getProductId() % productPiecePageCount);
		Map<Long, PProducts> map = (Map<Long, PProducts>) RedisUtil.getObject(keyName);
		if(map==null||map.values().size()<=0){
			map=new HashMap<Long, PProducts>();
		}
		map.put(product.getProductId(), product);
	}

	
	public void del_redis_productAct(Long productId){
		if (productId == null || productId <= 0)
			return ;
		// 分块进行缓存 缓存活动中的产品
		String keyName = actProkeyName + (productId % actProPiecePageCount);
		try {
			Map<Long, ActProductInfo> map = (Map<Long, ActProductInfo>) RedisUtil.getObject(keyName);
			if (map != null && map.values().size() > 0) {
				if (map.containsKey(productId)) {
					map.remove(productId);
					RedisUtil.setObject(keyName, map);
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void set_redis_productAct(Long productId){
		String keyName = actProkeyName + (productId % actProPiecePageCount);
		try {
			Map<Long, ActProductInfo> map = (Map<Long, ActProductInfo>) RedisUtil.getObject(keyName);
			if (map == null || map.values().size() <= 0) {
				map=new HashMap<Long, ActProductInfo>();
			}
			AActProductsShowTime actPro = getAActProductsShowTime(productId, false);
			if (actPro != null) {
				ActProductInfo info2 = returnActProductInfo(actPro);
				map.put(productId, info2);
				RedisUtil.setObject(keyName, map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 判断产品是否参加活动（未结束的活动产品）
	 * 
	 * @param productId
	 * @return
	 */
	public ActProductInfo get_ProductAct(Long productId) {
		if (productId == null || productId <= 0)
			return null;
		// 分块进行缓存 缓存活动中的产品
		String keyName = actProkeyName + (productId % actProPiecePageCount);
		
		Map<Long, ActProductInfo> map = (Map<Long, ActProductInfo>) RedisUtil.getObject(keyName);
		Date nowtime = new Date();
		boolean needNew = false;// 是否需要查询数据库 进行更新缓存
		if (map != null && map.values().size() > 0) {
			if (map.containsKey(productId)) {
				ActProductInfo info = map.get(productId);
				if (info!=null&&info.getEndTime().getTime() > nowtime.getTime())
					return info;
				else {
					map.remove(productId);
					needNew = true;
				}
			} else {
				needNew = true;
			}
		} else {
			map = new HashMap<Long, ActProductInfo>();
			needNew = true;
		}
		if (needNew) {//需要检测数据库 ，产品是否在活动中
			//缓存不在活动中的产品（短期缓存）
			String keyNameNot= actProNotkeyName + (productId % actProPiecePageCount);
			Map<Long, Long> mapNot =  (Map<Long, Long>) RedisUtil.getObject(keyNameNot);
			if(mapNot!=null&&mapNot.values().size()>0){
				if(mapNot.containsKey(productId))
					return null;
			}else {
				mapNot=new HashMap<Long, Long>();
			}
			// --------新增--------
			AActProductsShowTime actPro = getAActProductsShowTime(productId, false);
			if (actPro != null) {
				ActProductInfo info2 = returnActProductInfo(actPro);
				map.put(productId, info2);
				RedisUtil.setObject(keyName, map);
				return info2;
			}else {//如果产品不在活动中，那么进行短期缓存，以减轻数据库判断压力
				mapNot.put(productId, productId);
				RedisUtil.setObject(keyNameNot, mapNot,1800); 
			}
		}
		return null;
	}

	public AActProductsShowTime getAActProductsShowTime(Long productId, boolean isGoing) {
		StringBuilder sb = new StringBuilder();
		Date nowtime = new Date();
		sb.append(" from AActProductsShowTime a where a.productId=:proid ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", productId);
		if (isGoing) {
			sb.append(" and a.beginTime<=:nowtime and a.endTime>=:nowtime ");
			map.put("nowtime", nowtime);
		} else {
			sb.append(" and  a.endTime>=:nowtime order by a.beginTime asc");
			map.put("nowtime", nowtime);
		}
		List<AActProductsShowTime> list = super.findByMap(sb.toString(), map);
		if (list != null && list.size() > 0) {
			AActProductsShowTime result = list.get(0);
			AActShowProducts actShowProducts = super.get(AActShowProducts.class, result.getProActId());
			if (actShowProducts != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * 返回 ActProductInfo
	 * 
	 * @param cc
	 * @return
	 */
	private ActProductInfo returnActProductInfo(AActProductsShowTime cc) {
		if (cc != null) {
			AActivityProducts products = super.get(AActivityProducts.class, cc.getProActId());
			if (products != null) {
				AActivity activity = super.get(AActivity.class, products.getActId());
				ActProductInfo result = new ActProductInfo();
				result.setActiveType(activity.getType()==null?0:activity.getType());
				result.setProductId(cc.getProductId());
				result.setProActId(cc.getProActId());
				result.setBeginTime(cc.getBeginTime());
				result.setEndTime(cc.getEndTime());
				result.setActPid(cc.getActPid());
				result.setActId(activity.getActId()); 
				result.setActPrice(products.getPrice());
				return result;
			}
		}
		return null;
	}

}
