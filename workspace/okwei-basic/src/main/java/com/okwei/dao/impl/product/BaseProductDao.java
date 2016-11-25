package com.okwei.dao.impl.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProductClassTemp;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.enums.BrandStatus;
import com.okwei.bean.enums.ProductShelveStatu;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.ShelveType;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.product.ProductQuery;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.util.RedisUtil;
 
@Repository
public class BaseProductDao extends BaseDAO  implements IBaseProductDao  {
	
	@Override
	public List<PShopClass> findPShopClassList(Long weiId, Integer paretId) {
		if (weiId == null || weiId.longValue() <= 0)
			return null;
		String hqlString = " from PShopClass p where p.weiid=:weiid and p.state=1 and paretId=:paretId  order by  p.sort desc, p.createTime asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiid", weiId);
		map.put("paretId", paretId);
		return findByMap(hqlString, map);
	}
	
	public List<PProductClass> find_PProductClassAll() {
		String classKey = "ProClasslist160729";
		List<PProductClass> list = null;
		try {
			list = (List<PProductClass>) RedisUtil.getObject(classKey);
			if (list == null || list.size() <= 0) {
				String hql = " from PProductClass w where w.step>0 order by sort asc ";
				list = find(hql);
				if (list != null && list.size() > 0) {
					RedisUtil.setObject(classKey, list, 600);
				}
			}
			return list;
		} catch (Exception e) {
			String hql = " from PProductClass w where w.step>0 ";
			list = find(hql);
		}
		return list;
	}
	
	public List<PProductClass> find_PProductClass(Integer parentid){
		List<PProductClass> list=find_PProductClassAll();
		List<PProductClass> result=new ArrayList<PProductClass>();
		for (PProductClass cc : list) {
			if(cc.getParentId().intValue()==parentid){
				result.add(cc);
			}
		}
		return result;
	}
	

	@Override
	public List<PBrand> findBrandList(Long weiId, Integer classId) {
		if (weiId == null || weiId.longValue() <= 0)
			return null;
		StringBuffer sb = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiId", weiId);
		sb.append(" from PBrand b where b.brandId in (select c.brandId from PClassForBrand c where c.weiId=:weiId ");
		if (classId != null) {
			sb.append("and c.typeId=:typeId");
			map.put("typeId", classId);
		}
		sb.append(") and b.status=:status order by b.sort desc");
		map.put("status", Integer.valueOf(BrandStatus.pass.toString()));
		return findByMap(sb.toString(), map);
	}

	@Override
	public PageResult<PProducts> getProductsPage(ProductQuery param,
			Limit buildLimit) {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiId", param.getShopWeiId());
		hql.append("FROM PProducts p where p.productId in (select a.productId  FROM PClassProducts a where a.state=1 and a.weiId=:weiId");
		if (param.getClassId() != null && param.getClassId() > 0) {
			if (param.getClassLevel() != null && param.getClassLevel() > 0) {
				if (Long.valueOf("1").equals(param.getClassLevel())) {
					hql.append(" and (a.classId=:classId OR a.classId IN (SELECT sid FROM PShopClass WHERE paretId=:classId))");
					params.put("classId", param.getClassId());
				} else if(Long.valueOf("2").equals(param.getClassLevel())){
					hql.append(" and a.classId=:classId ");
					params.put("classId", param.getClassId());
				}
			}
		}
		hql.append(" order by a.sort desc,a.createTime desc) ");
		//1落地进货区2代理区下的产品需要挂钩招商需求
		if (Integer.valueOf("1").equals(param.getType()) || Integer.valueOf("2").equals(param.getType())) {
			hql.append(" and p.productId in ( select dp.productId FROM UDemandProduct dp)");
		}
		if (StringUtils.isNotEmpty(param.getKeyword())) {
			hql.append(" and productTitle like :productTitle ");
			params.put("productTitle", "%" + param.getKeyword() + "%");
		}
		return super.findPageResultByMap(hql.toString(), buildLimit, params);
	}

	@Override
	public PageResult<PBrand> findBrandPageResult(Long weiID, Integer classId,
			Limit limit) {
		StringBuffer sb = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiId", weiID);
		sb.append(" from PBrand b where b.brandId in (select c.brandId from PClassForBrand c where c.weiId=:weiId ");
		if (classId != null) {
			sb.append("and c.typeId=:typeId");
			map.put("typeId", classId);
		}
		sb.append(") and b.status=:status order by b.sort desc");
		map.put("status", Integer.valueOf(BrandStatus.pass.toString()));
		return super.findPageResultByMap(sb.toString(), limit, map);
	}
	
	public List<PProducts> findProductlistByIds(List<Long> productIds,Short productState){
		if (productIds!=null&&productIds.size()>0) {
		StringBuilder sb=new StringBuilder();
		Map<String,Object> map=new HashMap<String, Object>();
		sb.append(" from PProducts p where p.productId in (:proIds)");
		map.put("proIds", productIds);
		if(productState!=null){
			sb.append(" and p.state=:state");
			map.put("state", productState);
		}
		return findByMap(sb.toString(), map);
		}else{
			return null;
		}
	}
	
	public List<PClassProducts> find_ProductSelflist(List<Long> productIds,Long weiid){
		if (productIds!=null&&productIds.size()>0) {
		String hqlString=" from PClassProducts p where p.productId in (:proIds) and p.weiId=:weiid ";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("proIds", productIds);
		map.put("weiid", weiid);
		return super.findByMap(hqlString,map);
		}else{
			return null;
		}
	}
	
	public boolean is_Onshelves(Long productId,Long weiNo){
		if(productId==null||weiNo==null)
			return false;
		try {
			List<Long> list=new ArrayList<Long>();
			list.add(productId);
			List<PClassProducts> prolist=find_ProductSelflist(list, weiNo);
			if(prolist!=null&&prolist.size()>0){
				PClassProducts info=prolist.get(0);
				if(info!=null&&info.getState()!=null&&info.getState()==Short.parseShort(ProductShelveStatu.OnShelf.toString())){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return false;
	}
	
	public PClassProducts getShangjiaInfo(long id) {
		PClassProducts product = super.get(PClassProducts.class, id);//(hqlString, paramsObjects);
		return product;
	}

	
	
	public void update_PProductAssist(Long productId, int type, int count) {
		PProductAssist pProductAssist = super.get(PProductAssist.class, productId);
		if (pProductAssist!=null) { 
		String hql="update PProductAssist p ";
	
		switch (type) {
			case 1://销售量
				hql+="set p.totalCount=(case when (p.totalCount IS  NULL) then 1  else (p.totalCount+:count) end) ";
				break;
			case 2:
				String hql2="select count(p.commentId) from PProductComment p where p.productId=:productId";
				Map<String , Object> map2 =new HashMap<String, Object>();
				map2.put("productId", productId);
				long count_Pin =super.count(hql2, map2); 
				hql+="set p.evaluateCount=(case when (p.evaluateCount IS  NULL) then 1  else ("+count_Pin+"+:count) end) ";
				break;
			case 3:
				hql+="set p.zanCount=(case when (p.zanCount IS  NULL) then 1  else (p.zanCount+:count) end) ";
				break;
			case 4:
				hql+="set p.shelvesCount=(case when (p.shelvesCount IS  NULL) then 1  else (p.shelvesCount+:count) end) ";
				break;
			case 5:
				hql+="set p.collectCount=(case when (p.collectCount IS  NULL) then 1  else (p.collectCount+:count) end) ";
				break;
			case 6:
				hql+="set p.readCount=(case when (p.readCount IS  NULL) then 1  else (p.readCount+:count) end) ";
				break;
			case 7:
				hql+="set p.shareCount=(case when (p.shareCount IS  NULL) then 1  else (p.shareCount+:count) end) ";
				break;
		}
		hql+=" where p.productId=:productId";	
		Map<String , Object> map =new HashMap<String, Object>();
		map.put("count", count);
		map.put("productId", productId);
		super.executeHqlByMap(hql, map); 

		}else{
			PProducts pProducts = super.get(PProducts.class, productId);
			pProductAssist=new PProductAssist();
			pProductAssist.setProductId(productId);
			pProductAssist.setClassId(pProducts.getClassId());
			pProductAssist.setSupplierId(pProducts.getSupplierWeiId());
			super.save(pProductAssist);
		}
	}
	
	
	public List<PProductParamKv> getParamKVList(Long proId) {
		String hqlString = "  from PProductParamKv p where p.productId=? ";
		Object[] b = new Object[1];
		b[0] = proId;
		List<PProductParamKv> infoSeller = super.find(hqlString, b);
		return infoSeller;
	}

	@Override
	public PageResult<PPostAgeModel> findPostAgePageResult(Long weiID,
			Limit limit) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from PPostAgeModel where status=1 and supplierWeiId=:weiId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiId", weiID);
		return super.findPageResultByMap(sb.toString(), limit, map);
	}
	
	@Override
	public List<PPostAgeModel> findPostAgeList(Long weiID) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from PPostAgeModel where status=1 and supplierWeiId=:weiId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiId", weiID);
		return super.findByMap(sb.toString(), map);
	}

	@Override
	public Long[] getAgentWeiIdsBySupId(Long weiId, Integer demandId,
			Short states) {
		String hql = " from UAgentApply where demandId=? and supplyId=? and state=?";
		List<UAgentApply> agentList = find(hql, new Object[]{demandId,weiId,states});
		if (agentList != null && agentList.size() > 0) {
			ArrayList<Long> longlist = new ArrayList<Long>();
			for (UAgentApply agent : agentList) {
				longlist.add(agent.getWeiId());
			}
			longlist = ridRepeatByLong(longlist);
			Long[] agentIds = new Long[longlist.size()];
			int i = 0;
			for (Long id :longlist) {
				agentIds[i] = id;
				i++;
			}
			return agentIds;
		}
		return null;
	}
	@Override
	public Long[] getStoreWeiIdsBySupId(Long weiId, Integer demandId) {
		String hql = " from UProductShop where demandId=? and supplyId=?";
		List<UProductShop> storeList = find(hql, new Object[]{demandId,weiId});
		if (storeList != null && storeList.size() > 0) {
			ArrayList<Long> longlist = new ArrayList<Long>();
			for (UProductShop store : storeList) {
				longlist.add(store.getWeiId());
			}
			longlist = ridRepeatByLong(longlist);
			Long[] stores = new Long[longlist.size()];
			int i = 0;
			for (Long id :longlist) {
				stores[i] = id;
				i++;
			}
			return stores;
		}
		return null;
	}
	@Override
	public Long[] getAgentOrStoreWeiIdsBySupId(Long supplyId, Integer demandId,Short channelType,
			Short states) {
		String hql = " from USupplyChannel where supplyId=? and demandId=? and channelType=? and state=? ";
		List<USupplyChannel> list = find(hql, new Object[]{supplyId,demandId,channelType,states});
		if (list != null && list.size() > 0) {
			ArrayList<Long> longlist = new ArrayList<Long>();
			for (USupplyChannel channel : list) {
				longlist.add(channel.getWeiId());
			}
			longlist = ridRepeatByLong(longlist);
			Long[] ids = new Long[longlist.size()];
			int i = 0;
			for (Long id :longlist) {
				ids[i] = id;
				i++;
			}
			return ids;
		}
		return null;
	}
	
	@Override
	public void UPPClassProductsByCondition(Long[] weiid, Long productId, Short state, Short type) {
		if (weiid == null || productId == null || productId <= 0)
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proId", productId);
		map.put("weiid", weiid);
		map.put("type", type);
		map.put("state", state);
		String hql = " update PClassProducts p set p.state=:state,p.type=:type where p.weiId in (:weiid) and p.productId=:proId ";
		executeHqlByMap(hql, map);
	}
	@Override
	public void setShelveProduct(Long[] weiid,PProducts pro,Short type) {
		if (weiid == null || pro == null) {
			return;
		}
		for (Long id : weiid) {
			String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.supplierweiId=?";
			Object[] b = new Object[3];
			b[0] = id;
			b[1] = pro.getProductId();
			b[2] = pro.getSupplierWeiId();
			PClassProducts pcp = getUniqueResultByHql(hql, b);
			if (pcp == null){
				pcp = new PClassProducts();
				List<PShopClass> list = find("from PShopClass where weiid =? and sname=?", id,"其他");
				Long classId = 0l;
				if (list != null && list.size() > 0) {
					PShopClass sc = list.get(0);
					if (sc.getSid() != null) {
						classId = Long.valueOf(sc.getSid().toString());
					}
				} else {
					PShopClass psClass = new PShopClass();
					psClass.setCreateTime(new Date());
					psClass.setSname("其他");
					psClass.setWeiid(id);
					psClass.setSort((short) 0);
					psClass.setState((short) 1);
					psClass.setType((short) 1);
					psClass.setLevel((short) 1);
					Integer sid = (Integer) save(psClass);
					if (sid != null) {
						classId = Long.valueOf(sid.toString());
					}
				}
				pcp.setClassId(classId);
			}
			pcp.setProductId(pro.getProductId());
			pcp.setIsSendMyself((short) 0);
			pcp.setSort((short) -1);
			pcp.setReason("");
			pcp.setState((short) 1);
			pcp.setCreateTime(new Date());
			pcp.setWeiId(id);
			pcp.setSupplierweiId(pro.getSupplierWeiId());
			pcp.setShelvweiId(pro.getSupplierWeiId());
			pcp.setSendweiId(pro.getSupplierWeiId());
			UUserAssist userassist = updateUserAssist(id);
			pcp.setWeiIdsort(userassist.getWeiIdSort());
			pcp.setType(type);
			saveOrUpdate(pcp);
		}
	}
	@Override
	public void setShelveProducts(List<PProducts> productList,Short type,Short ShelveStatu,Long platformWid,Long weiId) throws Exception{
		for (PProducts prod : productList) {
			if (Short.valueOf(ProductShelveStatu.OnShelf.toString()).equals(ShelveStatu)) {
				String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.supplierweiId=?";
				Object[] b = new Object[3];
				b[0] = weiId;
				b[1] = prod.getProductId();
				b[2] = platformWid;
				PClassProducts pcp = getUniqueResultByHql(hql, b);
				if (pcp == null){
					pcp = new PClassProducts();
					List<PShopClass> list = find("from PShopClass where weiid =? and sname=?", weiId,"其他");
					Long classId = 0l;
					if (list != null && list.size() > 0) {
						PShopClass sc = list.get(0);
						if (sc.getSid() != null) {
							classId = Long.valueOf(sc.getSid().toString());
						}
					} else {
						PShopClass psClass = new PShopClass();
						psClass.setCreateTime(new Date());
						psClass.setSname("其他");
						psClass.setWeiid(weiId);
						psClass.setSort((short) 0);
						psClass.setState((short) 1);
						psClass.setType((short) 1);
						psClass.setLevel((short) 1);
						Integer sid = (Integer) save(psClass);
						if (sid != null) {
							classId = Long.valueOf(sid.toString());
						}
					}
					pcp.setClassId(classId);
				}
				pcp.setProductId(prod.getProductId());
				pcp.setIsSendMyself((short) 0);
				pcp.setSort((short) -1);
				pcp.setReason("");
				pcp.setState(ShelveStatu);
				pcp.setCreateTime(new Date());
				pcp.setWeiId(weiId);
				pcp.setSupplierweiId(prod.getSupplierWeiId());
				pcp.setShelvweiId(weiId);
				pcp.setSendweiId(prod.getSupplierWeiId());
				UUserAssist userassist = updateUserAssist(weiId);
				pcp.setWeiIdsort(userassist.getWeiIdSort());
				pcp.setType(Short.valueOf(SupplyChannelTypeEnum.Agent.toString()).equals(type) ? ShelveType.Proxy.getNo() : ShelveType.floor.getNo());
				saveOrUpdate(pcp);
			} else if (Short.valueOf(ProductShelveStatu.OffShelf.toString()).equals(ShelveStatu)) {
				String hql = "update PClassProducts p set p.state=0 where p.weiId=? and p.productId=? and p.supplierweiId=? ";
				executeHql(hql, weiId,prod.getProductId(),platformWid);
			}
		}
	}
	
	@Override
	public UUserAssist updateUserAssist(Long weiid)
	{
		//通过微店号查询辅助表
		String hql=" from UUserAssist p where p.weiId=?";
		Object[] b=new Object[1];
		b[0]=weiid;
		UUserAssist userassit = getUniqueResultByHql(hql, b);
		//如果改微店还没上架，就想辅助表添加改用户使用上架功能时的个人排序
		hql = " from PShelverCount";
		PShelverCount psc = getUniqueResultByHql(hql,null);
		if(psc == null)
		{
			psc = new PShelverCount();
			psc.setCount(0L);
		}
		if(userassit==null){
			UUserAssist ua = new UUserAssist();
			ua.setWeiId(weiid);
			ua.setWeiIdSort(psc.getCount()+1);
			save(ua);
		} else {
			userassit.setWeiId(weiid);
			userassit.setWeiIdSort(psc.getCount()+1);
			update(userassit);
		}
		psc.setCount(psc.getCount()+1);
		//更新记录表，数量加1
		saveOrUpdate(psc);
		return userassit;
	}
	
	/**
     * 去重复
     * */
    public ArrayList<Long> ridRepeatByLong(ArrayList<Long> list)
    {
        return new ArrayList<Long>(new LinkedHashSet<Long>(list));
    }

	@Override
	public List<PProductClassTemp> find_ProductClass(Integer parentId, Short level) {
		String hql="from PProductClassTemp p where p.step=:level";
		Map<String, Object> map=new HashMap<String, Object>();
		if (parentId!=null&&parentId>0) {
			hql+=" and parentId=:parentId";
			map.put("parentId", parentId); 
		} 
		map.put("level", level); 
		return findByMap(hql, map);
	}
	
	/**
	 * 一鍵上架 需求產品
	 * @param demandId
	 * @param weiid
	 */
	public void UP_onshelves(Integer demandId,Long weiid){
		String demandProducts=" from UDemandProduct u where u.demandId=? ";
		List<UDemandProduct> productlist=super.find(demandProducts, demandId);
		List<Long> productIdList=new ArrayList<Long>();
		if(productlist!=null&&productlist.size()>0){
			for (UDemandProduct dd : productlist) {
				 productIdList.add(dd.getProductId());
			}
			List<PProducts> products=findProductlistByIds(productIdList, Short.parseShort(ProductStatusEnum.Showing.toString()));
			List<PClassProducts> classProducts=find_ProductSelflist(productIdList, weiid);
			UUserAssist userassist =updateUserAssist(weiid);
			
			for (PProducts pp : products) {
				boolean isHave=false;
				if(classProducts!=null&&classProducts.size()>0){
					for (PClassProducts cc : classProducts) {
						 if(pp.getProductId().equals(cc.getProductId())){
							 isHave=true;
							 continue;
						 }
					}
				}
				
				if(!isHave){
					PClassProducts proMod=new PClassProducts();
					proMod.setProductId(pp.getProductId());
					proMod.setCreateTime(new Date());
					proMod.setIsSendMyself((short)1);
					proMod.setSort((short)-1);
					proMod.setState((short)1);
					proMod.setWeiId(weiid);
					proMod.setSupplierweiId(pp.getSupplierWeiId());
					proMod.setShelvweiId(pp.getSupplierWeiId());
					proMod.setWeiIdsort(userassist.getWeiIdSort());
					proMod.setType((short)0);
					super.save(proMod);
				}
			}
		}
		
	}

	@Override
	public List<PProducts> findProductsListByDemandId(Integer demandId) {
		String hql="from PProducts p where p.productId in (select productId from UDemandProduct dp where dp.demandId=:demandId)";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("demandId", demandId); 
		return findByMap(hql, map);
	}

	@Override
	public List<PClassProducts> findClassProducts(List<Long> shelveIds) {
		if (shelveIds!=null&&shelveIds.size()>0) {
			String hql="from PClassProducts p where p.id in (:ids)";
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("ids", shelveIds); 
			return findByMap(hql, map); 
		}else{
			return null;
		}
	}

	
}
