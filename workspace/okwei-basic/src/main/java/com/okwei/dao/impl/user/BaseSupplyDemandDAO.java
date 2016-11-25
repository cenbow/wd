package com.okwei.dao.impl.user;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import javassist.expr.NewArray;

import org.apache.struts2.views.freemarker.tags.ParamModel;
import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.URequiredKv;
import com.okwei.bean.domain.USupplierIndustryCategory;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.user.SupplyChannelDTO;
import com.okwei.bean.dto.user.SupplyDemandDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.RegionLevelEnum;
import com.okwei.bean.enums.SupplierIndustryType;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.DemandProductVO;
import com.okwei.bean.vo.SupplyDemandVO;
import com.okwei.bean.vo.user.ChannelInfoVO;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IBaseSupplyDemandDAO;
import com.okwei.util.DateUtils;

@Repository
public class BaseSupplyDemandDAO extends BaseDAO implements IBaseSupplyDemandDAO {

	@Override
	public void deleteDemandArea(int demandID) {
		if(demandID <1){
			return ;
		}
		
		String hql ="delete USupplyDemandArea u where u.demandId=?";
		super.executeHql(hql, demandID);	
	}
	

	@Override
	public void deleteDemandRequired(int demandID) {
		if(demandID <1){
			return ;
		}
		
		String hql ="delete UDemandRequired u where u.demandId=?";
		super.executeHql(hql, demandID);	
	}

	@Override
	public void deleteRequiredKV(int demandID) {
		if(demandID <1){
			return ;
		}
		
		String hql ="delete URequiredKv u where u.demandId=?";
		super.executeHql(hql, demandID);	
	}
	
	@Override
	public void deleteDemandProduct(int demandID){
		
		if(demandID <1){
			return ;
		}
		
		String hql ="delete UDemandProduct u where u.demandId=?";
		super.executeHql(hql, demandID);	
	}

	@Override
	public List<USupplyDemandArea> getDemandAreas(int demandID) {
		if(demandID <1){
			return null;
		}
		
		return getSupplyDemandAreaList(demandID,null,null);
	}
	
	@Override
	public List<USupplyDemandArea> getRequiredAddress(int requiredID){
		if(requiredID <1){
			return null;
		}
		
		return getSupplyDemandAreaList(null,requiredID,null);
	}

	public List<USupplyDemandArea> getDemandAreas(int demandID,Integer proviceCode){
		if(demandID <1){
			return null;
		}
		
		return getSupplyDemandAreaList(demandID,null,proviceCode);
	}
	
	private List<USupplyDemandArea> getSupplyDemandAreaList(Integer demandID,Integer requiredID,Integer proviceCode){
		StringBuilder sb=new StringBuilder(" from USupplyDemandArea u where 1=1 ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(demandID !=null && demandID >0){
			sb.append(" and u.demandId=:demandId"); 
			paramMap.put("demandId", demandID);
		}

		if(requiredID !=null && requiredID >0){
			sb.append(" and u.requiredId=:requiredId"); 
			paramMap.put("requiredId", requiredID);
		}
		
		if(proviceCode !=null && proviceCode>0){
			sb.append(" and u.province=:province"); 
			paramMap.put("province", proviceCode);
		}
		
		return super.findByMap(sb.toString(), paramMap);		
	}
	
	@Override
	public PageResult<SupplyDemandVO> getDemandVos(SupplyDemandDTO dto,Limit limit) {
		
		StringBuilder sb = new StringBuilder(" select u.demandId as demandId,u.weiId as weiId ,u.title as title "
				+ " ,u.mainImage as mainImage,u.pcDetails as pcDetails,u.appDetails as appDetails,u.state as state "
				+ " ,u.minAgentReward as minAgentReward,u.maxAgentReward as maxAgentReward "
				+ " ,u.orderAmout as orderAmout "
				+ " ,u.agentCount as agentCount,u.auditTime as auditTime,u.noPassReason as noPassReason "
				+ " ,u.shopCount as shopCount,u.createTime as createTime "
				+ " from USupplyDemand u where 1=1 ");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(dto !=null)
		{
			if(dto.getWeiId() !=null && dto.getWeiId() >0){//发布人
				sb.append(" and u.weiId=:weiId ");
				paramMap.put("weiId", dto.getWeiId());
			}
			if(dto.getState() !=null){//状态
				sb.append(" and u.state =:state ");
				paramMap.put("state",Short.parseShort(dto.getState().toString()));
			}
			if(dto.getTitle() !=null && !"".equals(dto.getTitle())){
				sb.append(" and u.title like :title ");
				paramMap.put("title","%"+ dto.getTitle()+ "%");
			}
			if(dto.getStartTime() !=null){
				sb.append(" and u.createTime >= :startTime ");
				paramMap.put("startTime", dto.getStartTime());
			}
			if(dto.getEndTime() !=null){
				sb.append(" and u.createTime < :endTime ");
				paramMap.put("endTime",DateUtils.addDate(dto.getEndTime(), 1));
			}
			if(dto.getShowPCount() !=null && dto.getShowPCount() >0){
				sb.append("and u.productCount > 0");			
			}
			if(dto.getIndustryID() !=null && dto.getIndustryID() >0){
				Short[] categoryIds = new Short[]{
						Short.parseShort(SupplierIndustryType.brand.toString()),
						Short.parseShort(SupplierIndustryType.platform.toString())
						};
				String industryhql ="select distinct(weiId) from USupplierIndustryCategory sic "
						+ "where sic.categoryId=:categoryId and sic.type in(:types)" ;
				paramMap.put("categoryId", dto.getIndustryID());
				paramMap.put("types", categoryIds);	
				
				sb.append(" and u.weiId in( "+industryhql+" ) ");
			}
			
			//子查询
			if(dto.getProvice() !=null || dto.getCity() !=null){
				String areahql =" select distinct(demandId) from USupplyDemandArea sd where 1=1 ";
				if(dto.getProvice() != null && dto.getProvice() >0){
					areahql += " and sd.province=:province ";
					paramMap.put("province", dto.getProvice());										
				}
				if(dto.getCity() !=null && dto.getCity() >0){
					areahql += " and sd.city=:city ";
					paramMap.put("city", dto.getCity());		
				}
				
				sb.append(" and u.demandId in( "+areahql+" ) ");
			}			
		}
		sb.append(" order by u.topTime desc,u.createTime desc");
		return super.findPageResultTransByMap(sb.toString(),SupplyDemandVO.class, limit, paramMap);
	}
	
	@Override
	public List<USupplyDemand> getDemands(Long weiID){
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="from USupplyDemand u where u.weiId=:weiId and u.state=:state";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiId", weiID);
		params.put("state", Short.parseShort(DemandStateEnum.Showing.toString()));
		
		return super.findByMap(hql, params);	
	}

	@Override
	public int editDemandState(Integer[] demandIDs,long weiID,DemandStateEnum state) {
		if(demandIDs ==null || demandIDs.length <1 || weiID <1){
			return 0;
		}
		
		String hql ="update USupplyDemand u set u.state=:state"
				+ " where u.demandId in(:demandIds) and u.weiId=:weiId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", Short.parseShort(state.toString()));
		params.put("demandIds",demandIDs);
		params.put("weiId",weiID);

		if(state ==DemandStateEnum.Showing ){
			hql += " and u.state=:oldstate";
			params.put("oldstate",Short.parseShort(DemandStateEnum.OffShelf.toString()));
		}else if(state == DemandStateEnum.OffShelf){
			hql += " and u.state=:oldstate";
			params.put("oldstate",Short.parseShort(DemandStateEnum.Showing.toString()));
		}
				
		return super.executeHqlByMap(hql, params);
	}
	
	/**
	 * 获取渠道商总数 不区分落地店
	 * @param demandIDs
	 * @return
	 */
	public int getChannelCount(Integer[] demandIDs){
		if(demandIDs ==null || demandIDs.length <1){
			return  0;
		}
		
		String hql ="select count(u.channelId) from  USupplyChannel u"
				+ " where u.demandId in (:demandIds) and u.state=:state";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("demandIds", demandIDs);
		params.put("state", Short.parseShort(AgentStatusEnum.Normal.toString()));
		
		return (int) super.countByMap(hql, params);
	}
	
	/**
	 * 获取申请中的 代理商数量
	 * @param demandIDs
	 * @return
	 */
	public int getApplyingCount(Integer[] demandIDs){
		if(demandIDs ==null || demandIDs.length <1){
			return  0;
		}
		
		String hql ="select count(u.applyId) from UAgentApply u "
				+ " where u.demandId in(:demandIds) and u.state=:state";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("demandIds", demandIDs);
		params.put("state", Short.parseShort(ApplyAgentStatusEnum.Applying.toString()));
		
		return (int) super.countByMap(hql, params);
	}
	
	public void deleteDemandChannel(Integer[] demandIDs){
		if(demandIDs ==null || demandIDs.length <1){
			return ;
		}
		
		String hql ="update USupplyChannel u set u.state=:state where u.demandId in(:demandIds)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state",Short.parseShort(AgentStatusEnum.Delete.toString()));
		params.put("demandIds", demandIDs);
		
		super.executeHqlByMap(hql, params);
	}
	
	public void deleteDemandProduct(Integer[] demandIDs){
		if(demandIDs ==null || demandIDs.length <1){
			return ;
		}
		
		String hql ="delete UDemandProduct u where u.demandId in(:demandIds)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("demandIds", demandIDs);
		
		super.executeHqlByMap(hql, params);
	}

	@Override
	public List<USupplyChannel> getUserAgentList(long weiID) {
		if(weiID <1){
			return null;
		}
		String hql = "from USupplyChannel u where u.weiId=? and u.state=1";
		
		return super.find(hql, weiID);
	}

	@Override
	public List<UDemandProduct> getDemandProducts(Integer[] demandIDs) {
		if(demandIDs ==null || demandIDs.length <1 ){
			return null;
		}
		
		String hql ="from UDemandProduct u where u.demandId in(:demandIDs)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("demandIDs", demandIDs);
		
		return super.findByMap(hql, paramMap);
	}
	

	@Override
	public List<UDemandProduct> getDemandProducts(Integer demandID) {
		if(demandID == null || demandID <1){
			return null;
		}
		
		String hql ="from UDemandProduct u where u.demandId = ?";
		
		return super.find(hql, demandID);
	}


	@Override
	public List<PProducts> getProducts(Long[] productIDs, int topCount) {
		if(productIDs ==null || productIDs.length <1){
			return null;
		}
		if(topCount <1){ //设定默认值
			topCount = 10;
		}

		String hql = "from PProducts p where p.productId in(:productIds) and p.state=:state";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productIds", productIDs);
		paramMap.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
		
		
		return super.findPageByMap(hql, 1, topCount, paramMap);
	}

	@Override
	public List<USupplyDemandArea> getDemandAreas(Integer[] demandIDs) {
		if(demandIDs ==null || demandIDs.length <1){
			return null;
		}
		String hql ="from USupplyDemandArea u where u.demandId in(:demandIds)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("demandIds",demandIDs);
		
		return super.findByMap(hql, paramMap);
	}

	@Override
	public List<TRegional> getRegionals(Integer[] codes) {
		if(codes ==null || codes.length <1){
			return null;
		}
		String hql ="from TRegional t where t.code in(:codes)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("codes", codes);
		
		return super.findByMap(hql, paramMap);
	}




	@Override
	public List<UDemandRequired> getDemandRequireds(int demandID) {
		if(demandID <1){
			return null;
		}
		
		String hql ="from UDemandRequired u where u.demandId=? ";
		
		return super.find(hql, demandID);
	}


	@Override
	public List<URequiredKv> getDemandKvs(int demandID) {
		if(demandID <1){
			return null;
		}
		
		String hql ="from URequiredKv u where u.demandId=? ";
		
		return super.find(hql, demandID);
	}


	@Override
	public List<URequiredKv> getRequiredKvs(int requiredID) {
		if(requiredID <1){
			return null;
		}
		
		String hql ="from URequiredKv u where u.requiredId=? ";
		
		return super.find(hql, requiredID);
	}


	@Override
	public List<USupplierIndustryCategory> getCategorys(Long weiID,Short categoryType) {
		if(weiID <1){
			return null;
		}
		
		String hql ="from USupplierIndustryCategory u where u.weiId=? and type=?";
		
		return super.find(hql, weiID,categoryType);
	}


	@Override
	public List<TBussinessClass> getBussinessClassList(Integer[] classIDs) {
		if(classIDs == null || classIDs.length <1){
			return null;
		}
		String hql ="from TBussinessClass u where u.id in(:classIDs)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("classIDs", classIDs);
		
		return super.findByMap(hql,paramMap);
	}

	@Override
	public List<Object[]> getAgentsCountByDemand(Long weiID,String searchStr) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="select u.demandId, count(u.demandId) "
				+ "from USupplyChannel u, UProductAgent p,UAgenArea a "
				+ "where  u.channelId =p.channelId and u.channelId=a.channelId "
				+ "and u.state=:state and u.supplyId=:supplyId and u.channelType=:channelType";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state", Short.parseShort(AgentStatusEnum.Normal.toString())); 
		paramMap.put("channelType", Short.parseShort(SupplyChannelTypeEnum.Agent.toString()));
		
		
		if(searchStr !=null && !"".equals(searchStr)){
			hql +=" and( p.weiName like :searchStr or "
				+ "p.linkMan like :searchStr or "
				+ "p.phone like :searchStr or "
				+ "a.regionStr like :searchStr )";
			paramMap.put("searchStr", "%"+searchStr+"%");
		}
	
		hql +=" group by u.demandId";
		
		return super.findByMap(hql, paramMap);
	}
	

	@Override
	public List<Object[]> getAgentsCountByRegion(Long weiID, Integer demandID,Integer code,RegionLevelEnum reg,Integer dayNum) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		StringBuilder sb =new StringBuilder();
		if(reg == RegionLevelEnum.Province){			
			sb.append("select a.provice,count(a.provice) ");
		}
		else if(reg == RegionLevelEnum.City){
			sb.append("select a.city,count(a.city) ");
		}else{
			return null;
		}
		
		sb.append(" from USupplyChannel u,UAgenArea a ");
		sb.append(" where u.channelId =a.channelId and u.state=:state and u.channelType=:channelType ");
		sb.append(" and u.supplyId=:supplyId ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state",Short.parseShort(AgentStatusEnum.Normal.toString())); 
		paramMap.put("channelType", Short.parseShort(SupplyChannelTypeEnum.Agent.toString())); 
		
		if(demandID !=null && demandID >0){
			sb.append(" and u.demandId = :demandId ");
			paramMap.put("demandId", demandID);
		}
		
		if(dayNum !=null && dayNum>0){
			sb.append(" and u.createTime > :createdate ");
			Calendar now  =Calendar.getInstance();  
			now.setTime(new Date());
			now.set(Calendar.DATE, now.get(Calendar.DATE) - dayNum);  
			
			paramMap.put("createdate", now.getTime());
		}
		
		if(reg == RegionLevelEnum.Province){			
			sb.append(" group by a.provice ");
		}
		else if(reg == RegionLevelEnum.City){			
			sb.append(" and a.provice =:code ");
			paramMap.put("code", code);
			
			sb.append(" group by a.city ");
		}
						
		return super.findByMap(sb.toString(), paramMap);
	}


	@Override
	public List<Object[]> getShopCountByRegion(Long weiID, Integer demandID,
			Integer code, RegionLevelEnum reg,Integer dayNum) {

		if(weiID ==null || weiID <1){
			return null;
		}
		
		StringBuilder sb =new StringBuilder();
		if(reg == RegionLevelEnum.Province){			
			sb.append("select p.province,count(p.province) ");
		}
		else if(reg == RegionLevelEnum.City){
			sb.append("select p.city,count(p.city) ");
		}else{
			return null;
		}
		
		sb.append(" from USupplyChannel u,UProductShop p ");
		sb.append(" where u.channelId =p.channelId and u.state=:state and u.channelType=:channelType ");
		sb.append(" and u.supplyId=:supplyId ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state", Short.parseShort(AgentStatusEnum.Normal.toString()));
		paramMap.put("channelType",Short.parseShort(SupplyChannelTypeEnum.ground.toString()));
		
		if(demandID !=null && demandID >0){
			sb.append(" and u.demandId = :demandId ");
			paramMap.put("demandId", demandID);
		}
		
		if(dayNum !=null && dayNum>0){
			sb.append(" and u.createTime > :createdate ");
			Calendar now  =Calendar.getInstance();  
			now.setTime(new Date());
			now.set(Calendar.DATE, now.get(Calendar.DATE) - dayNum);  
			
			paramMap.put("createdate", now.getTime());
		}
		
		if(reg == RegionLevelEnum.Province){			
			sb.append(" group by p.province ");
		}
		else if(reg == RegionLevelEnum.City){			
			sb.append(" and p.province =:code ");
			paramMap.put("code", code);
			
			sb.append(" group by p.city ");
		}
	
		return super.findByMap(sb.toString(), paramMap);
	}


	@Override
	public List<Object[]> getShopCountByDemand(Long weiID, String searchStr) {
		if(weiID ==null || weiID <1){
			return null;
		}
				String hql ="select u.demandId, count(u.demandId) "
				+ " from USupplyChannel u ,UProductShop p where u.channelId=p.channelId"
				+ " and u.state=:state and u.channelType=:channelType and u.supplyId=:supplyId";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state", Short.parseShort(AgentStatusEnum.Normal.toString())); 
		paramMap.put("channelType", Short.parseShort(SupplyChannelTypeEnum.ground.toString())); 
		if(searchStr !=null && !"".equals(searchStr)){
			hql +=" and( p.weiName like :searchStr or "
					+ "p.linkMan like :searchStr or "
					+ "p.phone like :searchStr or "
					+ "p.regionStr like :searchStr )";
			paramMap.put("searchStr", "%"+searchStr+"%");
		}
		hql +=" group by u.demandId";
		
		return super.findByMap(hql, paramMap);
	}


	@Override
	public List<USupplyDemand> getDemands(Integer[] demandIDs) {
		if(demandIDs ==null || demandIDs.length <1){
			return null;
		}
		
		String hql ="from USupplyDemand u where u.demandId in(:demandIDs) ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("demandIDs", demandIDs);
		
		return super.findByMap(hql, paramMap);
	}


	@Override
	public List<UWeiSeller> getWeiSellers(Long[] weiIDs) {
		if(weiIDs ==null || weiIDs.length <1){
			return null;
		}
		
		String hql ="from UWeiSeller u where u.weiId in (:weiIDs)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("weiIDs", weiIDs);
		
		return super.findByMap(hql, paramMap);
	}
	
	@Override
	public List<USupplyer> getSupplyers(Long[] weiIDs){
		if(weiIDs ==null || weiIDs.length <1){
			return null;
		}
		
		String hql ="from USupplyer u where u.weiId in (:weiIDs)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("weiIDs", weiIDs);
		
		return super.findByMap(hql, paramMap);
	}


	@Override
	public PageResult<ChannelInfoVO> getSearchAgent(Long weiID, Integer demandID,
			String searchStr,Integer code,RegionLevelEnum reg,Limit limit) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="select p.weiName as weiName,p.linkMan as linkMan,p.phone as phone,a.regionStr as address, "
				+ " u.channelType as channelType,u.weiId  as weiId "
				+ " from USupplyChannel u, UProductAgent p,UAgenArea a "
				+ " where  u.channelId =p.channelId and u.channelId=a.channelId "
				+ " and u.state=:state and u.supplyId=:supplyId and u.channelType=:channelType";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state", Short.parseShort(AgentStatusEnum.Normal.toString())); 
		paramMap.put("channelType", Short.parseShort(SupplyChannelTypeEnum.Agent.toString())); 
		if(demandID !=null && demandID>0){
			hql += " and u.demandId=:demandId ";
			paramMap.put("demandId", demandID);
		}
		
		if(code !=null && code >0){
			if(reg == RegionLevelEnum.Province){
				hql += " and a.provice=:code ";
				paramMap.put("code", code);
			}else if(reg == RegionLevelEnum.City){
				hql +=" and a.city=:code";
				paramMap.put("code", code);
			}
		}
				
		if(searchStr !=null && !"".equals(searchStr)){
			hql +=" and( p.weiName like :searchStr or "
				+ "p.linkMan like :searchStr or "
				+ "p.phone like :searchStr or "
				+ "a.regionStr like:searchStr )";
			paramMap.put("searchStr", "%"+searchStr+"%");
		}
				
		return super.findPageResultTransByMap(hql, ChannelInfoVO.class, limit, paramMap);
	}


	@Override
	public PageResult<ChannelInfoVO> getSearchShop(Long weiID, Integer demandID,
			String searchStr,Integer code,RegionLevelEnum reg,Limit limit) {
		if(weiID ==null || weiID <1){
			return null;
		}

		String hql ="select p.weiName as weiName,p.linkMan as linkMan,p.phone  as phone,p.regionStr  as address, "
				+ " u.channelType as channelType,u.weiId as weiId "
				+ " from USupplyChannel u ,UProductShop p where u.channelId=p.channelId"
				+ " and u.state=:state and u.channelType=:channelType and u.supplyId=:supplyId";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		paramMap.put("state",Short.parseShort(AgentStatusEnum.Normal.toString())); 
		paramMap.put("channelType", Short.parseShort(SupplyChannelTypeEnum.ground.toString()));
		
		if(demandID !=null && demandID>0){
			hql += " and u.demandId=:demandId ";
			paramMap.put("demandId", demandID);
		}
		if(code !=null && code >0){
			if(reg == RegionLevelEnum.Province){
				hql += " and p.province=:code ";
				paramMap.put("code", code);
			}else if(reg == RegionLevelEnum.City){
				hql +=" and p.city=:code";
				paramMap.put("code", code);
			}
		}
		
		if(searchStr !=null && !"".equals(searchStr)){
			hql +=" and( p.weiName like :searchStr or "
					+ "p.linkMan like :searchStr or "
					+ "p.phone like :searchStr or "
					+ "p.regionStr like:searchStr )";
			paramMap.put("searchStr", "%"+searchStr+"%");
		}
		
		return super.findPageResultTransByMap(hql, ChannelInfoVO.class, limit, paramMap);
	}


	@Override
	public PageResult<ChannelRegionVO> getNoChannelRegions(Long weiID,
			Integer demandID, Limit limit) {
		if(weiID ==null || weiID <1){
			return null;
		}
				
		String agentHql ="select ua.code from UAgenArea ua where ua.supplyId=:supplyId"; 
		String shopHql ="select up.code forom UProductShop up where up.supplyId=:supplyId";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", weiID);
		
		if(demandID !=null && demandID>0){
			agentHql += " and ua.demandId =:demandId";
			shopHql += " and ua.demandId =:demandId";
			paramMap.put("demandId", demandID);
		}
		String hql ="select t.code,t.name as codeName from TRegional t "
				+ " where and t.level in(:levels) "
				+ " t.code not in("+agentHql+") and t.code not in("+shopHql+") ";
		
		Short[] levels = new Short[]{
				Short.parseShort(RegionLevelEnum.Province.toString()),
				Short.parseShort(RegionLevelEnum.City.toString()),
		};
		paramMap.put("levels",levels );
		
		return super.findPageResultTransByMap(hql, ChannelRegionVO.class, limit, paramMap);
	}


	@Override
	public PageResult<DemandProductVO> getDemandProducts(Integer demandID,Limit limit) {
		if(demandID ==null || demandID <1){
			return null;
		}
		//TODO:改成用IN
		String hql ="select p.productId as productID,p.defaultImg as productImg,p.productTitle as prodcutTitle, "
				+ " p.defaultPrice as price,p.defaultConmision as comminss,p.sid as sid "
				+ " from UDemandProduct u,PProducts p where u.productId =p.productId"
				+ " and p.state =:state and u.demandId=:demandId ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state",Short.parseShort(ProductStatusEnum.Showing.toString()));
		paramMap.put("demandId",demandID);
		
		return super.findPageResultTransByMap(hql, DemandProductVO.class, limit, paramMap);
	}
	@Override
	public List<PProductRelation> getProductRelations(Long[] productIDs){
		if(productIDs ==null || productIDs.length <1){
			return null;
		}
		String hql ="from PProductRelation p where p.productId in(:productIds)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productIds", productIDs);
		
		return super.findByMap(hql, params);
	}
	
	@Override
	public List<PShopClass> getShopClasses(Integer[] sids){
		if(sids ==null || sids.length <1){
			return null;
		}
		
		String hql ="from PShopClass p where p.sid in(:sids)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sids", sids);
		
		return super.findByMap(hql, params);
	}

	
	@Override
	public List<Object[]> getDemandProductCount(Integer[] demandIDs) {
		if(demandIDs == null || demandIDs.length <1){
			return null;
		}
		
//		String hql ="select u.demandId,count(u.productId) from UDemandProduct u "
//				+ "where demandId in(:demandId) group by u.demandId";
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("demandId", demandIDs);
		
		String hql ="select u.demandId,count(u.productId) "
				+ " from UDemandProduct u,PProducts p where u.productId =p.productId "
				+ " and p.state =:state and u.demandId in(:demandIds)"
				+ " group by u.demandId";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("demandIds", demandIDs);
		paramMap.put("state",(short) 1);
		
		return super.findByMap(hql, paramMap);
	}


	@Override
	public PageResult<DemandProductVO> getNoDemandProducts(Long weiID,
			String title,  Integer[] calssTypes, Limit limit) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="select p.productId as productID,p.defaultImg as productImg,p.productTitle as prodcutTitle "
				+ " ,p.defaultPrice as price, p.defaultConmision as comminss,p.sid as sid"
				+ " from PProducts p where p.state=:state and p.supplierWeiId=:weiId and p.productId not in("
				+ "		select productId from UDemandProduct dp where dp.demandId in("
				+ "			select demandId from USupplyDemand u where u.weiId=:weiId )"
				+ ")";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("weiId", weiID);
		paramMap.put("state",Short.parseShort(ProductStatusEnum.Showing.toString()));
		if(title !=null && title !=""){
			hql +=" and p.productTitle like :productTitle ";
			paramMap.put("productTitle", "%"+title+"%");
		}
		if(calssTypes !=null && calssTypes.length >0){
			hql +=" and p.sid in(:sid) ";
			paramMap.put("sid", calssTypes);
		}
				
		return super.findPageResultTransByMap(hql, DemandProductVO.class, limit, paramMap);
	}


	@Override
	public List<PShopClass> getShopClasses(Long weiID,Short level, Integer parentID) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="from PShopClass p where p.weiid =:weiid and state=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiid", weiID);
		
		if(level !=null && level >-1){
			hql += " and p.level=:level ";
			params.put("level", level);
		}
		if(parentID !=null && parentID >-1){		
			hql += " and p.paretId=:paretId ";
			params.put("paretId", parentID);
		}
			
		hql +=" ORDER BY p.sort ";
		return super.findByMap(hql, params);
	}


	@Override
	public List<Object[]> getMyDemandStateCount(Long weiID) {
		if(weiID ==null || weiID <1){
			return null;
		}
		
		String hql ="select u.state,count(u.state) from USupplyDemand u"
				+ " where u.weiId =? "
				+ " group by u.state order by u.state";
		
		return super.find(hql, weiID);
	}


	@Override
	public List<TRegional> getRegionals(Short lever, Integer parent) {
		if(lever ==null && parent ==null){
			return null;
		}
		
		String hql ="from TRegional t where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(lever !=null && lever >-1){
			hql +=" and t.level =:level ";
			params.put("level", lever);
		}
		if(parent !=null && parent > -1){
			hql +=" and t.parent =:parent ";
			params.put("parent", parent);
		}
				
		return super.findByMap(hql, params);
	}


	@Override
	public List<UAttention> getAttentions(Long weiID, Long[] attentions) {
		if(weiID ==null || weiID <1 || attentions ==null || attentions.length <1){
			return null;
		}
		
		String hql ="from UAttention u where u.attentioner=:attentioner and u.attTo in(:attTo)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("attentioner", weiID);
		params.put("attTo", attentions);
		
		return super.findByMap(hql, params);
	}


	@Override
	public List<PPriceShow> getPriceShows(Long[] weiIDs) {
		if(weiIDs ==null || weiIDs.length <1){
			return null;
		}
		
		String hql ="from PPriceShow p where p.weiId in(:weiIds)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiIds", weiIDs);
		
		return super.findByMap(hql, params);
	}

}
