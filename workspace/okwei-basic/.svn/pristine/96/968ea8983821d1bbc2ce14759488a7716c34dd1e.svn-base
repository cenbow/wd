package com.okwei.dao.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.vo.HqlParam;
import com.okwei.bean.vo.user.LandShopListVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IBasicAgentOrProductShopDAO;
import com.okwei.util.DateUtils;
@Repository
public class BasicAgentOrProductShopDAO extends BaseDAO implements IBasicAgentOrProductShopDAO  {

	@Override
	public List<Object[]> getMyDevelopAgent(Limit limit,MyAgentOrProductShopListDTO queryParam) {
		StringBuilder sb = new StringBuilder("select a.State,a.CreateTime,a.Reward,IFNULL(b.PayedReward,0),b.ChannelType,");
		sb.append("IFNULL((select p.SupplyName from U_PlatformSupplyer p where p.WeiID=a.SupplyID),\"\"),");
		sb.append("IFNULL((select b.SupplyName from U_BrandSupplyer b where b.WeiID=a.SupplyID),\"\"),");
		sb.append("a.Verifier,a.Provice,a.City,a.Area,a.Address,a.WeiID,a.LinkMan,a.Phone,a.SupplyID,a.DemandID,b.ChannelID,");
		sb.append("(select count(*) from U_SupplyChannel sc where sc.UpWeiID=a.WeiID and sc.ChannelType=2),a.Remarks,a.AuditTime,a.ApplyID,a.LicenseImg,a.Details,a.FollowVerifier ");
		sb.append("from U_AgentApply as a LEFT JOIN U_SupplyChannel as b ON a.ApplyID=b.ApplyID where 1=1");
		HqlParam hqlParam = GetHQLParam0(queryParam,sb);
		return super.queryBySql(hqlParam.getSb().toString()+" order by a.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize(),hqlParam.getParams().toArray());
	}
	
	public HqlParam GetHQLParam0(MyAgentOrProductShopListDTO queryParam,StringBuilder sb)
	{   
		HqlParam hqlParam = new HqlParam();
		List<Object> params = new ArrayList<Object>();
		if(null != queryParam)
		{   
			//认证员微店号
			if(queryParam.getVerifierWeiId() != null)
			{
				sb.append(" and (a.Verifier = ? or a.FollowVerifier=?)");
				params.add(queryParam.getVerifierWeiId());
				params.add(queryParam.getVerifierWeiId());
			}
			//申请人微店号
			if(queryParam.getApplyPersonWeiId() != null  && !"".equals(queryParam.getApplyPersonWeiId()) 
					&& isLong(queryParam.getApplyPersonWeiId()!=null?String.valueOf(queryParam.getApplyPersonWeiId()):""))
			{
				sb.append(" and a.WeiID = ?"); 
				params.add(Long.parseLong(String.valueOf(queryParam.getApplyPersonWeiId()).trim()));
			}
			//状态
			if(queryParam.getAuditState() != null && queryParam.getAuditState()!=-1)
			{
				sb.append(" and a.State = ?");
				params.add(queryParam.getAuditState());
			}
			//起始日期
			if(queryParam.getBeginTime() != null && !"".equals(queryParam.getBeginTime().trim()))
			{
				sb.append(" and a.CreateTime >= ? ");
				String beginTime = queryParam.getBeginTime().trim() + " 00:00:00";
				Date time = DateUtils.parseDateTime(beginTime);
				params.add(time);
			}
			//结束日期
			if(queryParam.getEndTime() != null && !"".equals(queryParam.getEndTime().trim()))
			{
				sb.append(" and a.CreateTime <= ? ");
				String endTime = queryParam.getEndTime().trim() + " 23:59:59";
				Date time = DateUtils.parseDateTime(endTime);
				params.add(time);
			}
		}
		hqlParam.setParams(params);
		hqlParam.setSb(sb);
		return hqlParam;
	}
	
	public HqlParam GetHQLParam(MyAgentOrProductShopListDTO queryParam,StringBuilder sb)
	{   
		HqlParam hqlParam = new HqlParam();
		List<Object> params = new ArrayList<Object>();
		if(null != queryParam)
		{   
			//认证员微店号
			if(queryParam.getVerifierWeiId() != null)
			{
				sb.append(" and c.Verifier = ?");
				params.add(queryParam.getVerifierWeiId());
			}
			//申请人微店号
			if(queryParam.getApplyPersonWeiId() != null  && !"".equals(queryParam.getApplyPersonWeiId()) 
					&& isLong(queryParam.getApplyPersonWeiId()!=null?String.valueOf(queryParam.getApplyPersonWeiId()):""))
			{
				sb.append(" and a.WeiID = ?"); 
				params.add(Long.parseLong(String.valueOf(queryParam.getApplyPersonWeiId()).trim()));
			}
			//状态
			if(queryParam.getAuditState() != null && queryParam.getAuditState()!=-1)
			{
				sb.append(" and c.State = ?");
				params.add(queryParam.getAuditState());
			}
			//起始日期
			if(queryParam.getBeginTime() != null && !"".equals(queryParam.getBeginTime().trim()))
			{
				sb.append(" and c.CreateTime >= ? ");
				String beginTime = queryParam.getBeginTime().trim() + " 00:00:00";
				Date time = DateUtils.parseDateTime(beginTime);
				params.add(time);
			}
			//结束日期
			if(queryParam.getEndTime() != null && !"".equals(queryParam.getEndTime().trim()))
			{
				sb.append(" and c.CreateTime <= ? ");
				String endTime = queryParam.getEndTime().trim() + " 23:59:59";
				Date time = DateUtils.parseDateTime(endTime);
				params.add(time);
			}
		}
		hqlParam.setParams(params);
		hqlParam.setSb(sb);
		return hqlParam;
	}
	
	// 判断字符串是否可转换为Long
	public static boolean isLong(String str)
	{
		boolean Result = false;
		try {
			Long.parseLong(str);
			Result = true;
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	@Override
	public long getMyDevelopAgentTotalAmount(MyAgentOrProductShopListDTO queryParam) {
		StringBuilder sb = new StringBuilder("select count(*) from U_AgentApply a where 1=1 ");
		HqlParam hqlParam = GetHQLParam0(queryParam,sb);
		return super.countBySql(hqlParam.getSb().toString(),hqlParam.getParams().toArray());
	}

	@Override
	public List<Object[]> getMyDevelopProductShop(Limit limit,
			MyAgentOrProductShopListDTO queryParam) {
		StringBuilder sb = new StringBuilder("select a.CreateTime,c.Reward,c.PayedReward,c.ChannelType,");
		sb.append("(select d.Title from U_SupplyDemand d where d.DemandID=a.DemandID),");
		sb.append("(select p.SupplyName from U_PlatformSupplyer p where p.WeiID=a.SupplyID),");
		sb.append("(select b.SupplyName from U_BrandSupplyer b where b.WeiID=a.SupplyID),");
		sb.append("a.WeiID,a.ChannelID from U_ProductShop a,U_SupplyChannel c where a.ChannelID=c.ChannelID ");
		HqlParam hqlParam = GetHQLParam(queryParam,sb);
		return super.queryBySql(hqlParam.getSb().toString()+" order by a.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize(),hqlParam.getParams().toArray());
	}


	@Override
	public long getMyDevelopProductShopTotalAmount(
			MyAgentOrProductShopListDTO queryParam) {
		StringBuilder sb = new StringBuilder("select count(1) from U_ProductShop a,U_SupplyChannel c where a.ChannelID=c.ChannelID ");
		HqlParam hqlParam = GetHQLParam(queryParam,sb);
		return super.countBySql(hqlParam.getSb().toString(),hqlParam.getParams().toArray());
	}

	@Override
	public List<UAgenArea> getUAgenAreaList(Integer parid) {
		String hql="from UAgenArea where parid=?";
		return super.find(hql, parid);
	}
	
	@Override
	public List<UAgentApplyArea> getUAgentApplyAreaList(Integer parid) {
		String hql="from UAgentApplyArea where applyID=?";
		return super.find(hql, parid);
	}

	@Override
	public List<UAgentApplyFollowLog> getUAgentApplyFollowLogList(Integer applyId) {
		String hql="from UAgentApplyFollowLog where applyId=?";
		return super.find(hql, applyId);
	}

	@Override
	public List<Object[]> getProductShopByPlatForm(Long supplyWeiId,Short state,Limit limit,String beginTime,String endTime) {
		//state=3表示落地店为已删除
		String sql="select s.WeiID,s.Province,s.City,s.Area,sc.CancelRemark,sc.CreateTime,sc.State,sc.Reward,sc.PayedReward,sc.Verifier,"
				+ "(select a.CompanyName from U_ProductAgent a where a.ChannelID=sc.UpWeiID),sc.UpWeiID,"
				+ "(select ps.SupplyName from U_PlatformSupplyer ps where s.SupplyID=ps.WeiID),s.Address,s.SupplyID,sc.CancelTime,s.ChannelID,s.LinkMan"
				+" ,(select d.Title from U_SupplyDemand d where d.DemandID=s.DemandID) "
				+ " from U_ProductShop s,U_SupplyChannel sc where  s.ChannelID=sc.ChannelID and sc.State!=3 ";
		//状态为全部时
		//
		List<Object> params = new ArrayList<Object>();
		//申请人微店号
		if(supplyWeiId!=null && supplyWeiId>0)
		{
			sql+=" and s.SupplyID=? "; 
			params.add(supplyWeiId);
		}
		//状态
		if(state != null && state!=-1)
		{
			sql+=" and sc.State = ?";
			params.add(state);
		}
		//起始日期
		if(beginTime != null && !"".equals(beginTime.trim()))
		{
			sql+=" and sc.CreateTime >= ? ";
			Date time = DateUtils.parseDateTime(beginTime.trim() + " 00:00:00");
			params.add(time);
		}
		//结束日期
		if(endTime != null && !"".equals(endTime.trim()))
		{
			sql+=" and sc.CreateTime <= ? ";
			Date time = DateUtils.parseDateTime(endTime.trim() + " 23:59:59");
			params.add(time);
		}
//		sql+=" order by sc.CreateTime desc LIMIT ";
		return super.queryBySql(sql+=" order by sc.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize(),params.toArray());
//		if(state!=null&&state!=-1){
//			sql+=" and sc.State=?";
//			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
//			return super.queryBySql(sql,supplyWeiId,state);
//		}else{
//			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
//			return super.queryBySql(sql,supplyWeiId);
//		}
	}

	@Override
	public long getProductShopByPlatFormTotalAmount(Long supplyWeiId,
			Short state,String beginTime,String endTime) {
		String sql="select count(*) from U_ProductShop s,U_SupplyChannel sc where  s.ChannelID=sc.ChannelID and sc.State!=3";
		//状态为全部时
		List<Object> params = new ArrayList<Object>();
		//申请人微店号
		if(supplyWeiId!=null && supplyWeiId>0)
		{
			sql+=" and s.SupplyID=? "; 
			params.add(supplyWeiId);
		}
		//状态
		if(state != null && state!=-1)
		{
			sql+=" and sc.State = ?";
			params.add(state);
		}
		//起始日期
		if(beginTime != null && !"".equals(beginTime.trim()))
		{
			sql+=" and sc.CreateTime >= ? ";
			Date time = DateUtils.parseDateTime(beginTime.trim() + " 00:00:00");
			params.add(time);
		}
		//结束日期
		if(endTime != null && !"".equals(endTime.trim()))
		{
			sql+=" and sc.CreateTime <= ? ";
			Date time = DateUtils.parseDateTime(endTime.trim() + " 23:59:59");
			params.add(time);
		}
		return super.countBySql(sql, params.toArray());
//		if(state!=null&&state!=-1){
//			sql+=" and sc.State=?";
//			return super.countBySql(sql,supplyWeiId,state);
//		}else{
//			return super.countBySql(sql,supplyWeiId);
//		}
	}

	@Override
	public int cancelOrRecoverProductShop(Short state, Integer shopId,
			String cancelReason) {
		//已取消
		if(state==Short.parseShort(AgentStatusEnum.Cancel.toString())){
			String hql="update USupplyChannel set state=?,cancelRemark=?,cancelTime=? where channelId=?";
			return super.update(hql, state,cancelReason,new Date(),shopId);
		}else{
			String hql="update USupplyChannel set state=? where channelId=?";
			return super.update(hql,state,shopId);
		}
		
	}

	@Override
	public List<UWeiSeller> getUWeiSellerList(List<Long> weiIdList) {
		String hql="from UWeiSeller where weiId in (:weiIdList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<USupplyDemand> getUSupplyDemandList(List<Integer> demandIdList) {
		String hql="from USupplyDemand where demandId in (:demandIdList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("demandIdList", demandIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<Object[]> getUDemandProductAmount(List<Integer> demandIdList) {
		String hql="select count(demandId),demandId from UDemandProduct where demandId in (:demandIdList) group by demandId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("demandIdList", demandIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<UAgenArea> getUAgentAreaList(List<Integer> parIdList) {
		String hql="from UAgenArea where channelId in (:parIdList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parIdList", parIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<Object[]> getMyDevelopProductShopApp(Limit limit,
			Long verifierWeiId,Short state,String beginTime,String endTime) {
		String sql="select s.WeiID,s.Province,s.City,s.Area,sc.CancelRemark,sc.CreateTime,sc.State,sc.Reward,sc.PayedReward,sc.Verifier,"
				+ "(select a.CompanyName from U_ProductAgent a where a.ChannelID=sc.UpWeiID),sc.UpWeiID,"
				+ "(select ps.SupplyName from U_PlatformSupplyer ps where s.SupplyID=ps.WeiID),s.Address,s.SupplyID,sc.CancelTime,s.ChannelID,s.LinkMan"
				+" ,(select d.Title from U_SupplyDemand d where d.DemandID=s.DemandID) "
				+ " from U_ProductShop s,U_SupplyChannel sc where  s.ChannelID=sc.ChannelID";
		//状态为全部时
		List<Object> params = new ArrayList<Object>();
		//申请人微店号
		if(verifierWeiId!=null && verifierWeiId>0)
		{
			sql+=" and sc.Verifier=? "; 
			params.add(verifierWeiId);
		}
		//状态
		if(state != null && state!=-1)
		{
			sql+=" and sc.State = ?";
			params.add(state);
		}
		//起始日期
		if(beginTime != null && !"".equals(beginTime.trim()))
		{
			sql+=" and sc.CreateTime >= ? ";
			Date time = DateUtils.parseDateTime(beginTime.trim() + " 00:00:00");
			params.add(time);
		}
		//结束日期
		if(endTime != null && !"".equals(endTime.trim()))
		{
			sql+=" and sc.CreateTime <= ? ";
			Date time = DateUtils.parseDateTime(endTime.trim() + " 23:59:59");
			params.add(time);
		}
		return super.queryBySql(sql+=" order by sc.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize(),params.toArray());

//		if(state!=null&&state!=-1){
//			sql+=" and sc.State=?";
//			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
//			return super.queryBySql(sql,verifierWeiId,state);
//		}else{
//			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
//			return super.queryBySql(sql,verifierWeiId);
//		}
	}

	@Override
	public List<Object[]> getMyDevelopProductShopApp(Limit limit,Long verifierWeiId,Short state,Integer demandId,Integer areaId) {
		String sql="select s.WeiID,s.Province,s.City,s.Area,sc.CancelRemark,sc.CreateTime,sc.State,sc.Reward,sc.PayedReward,sc.Verifier,"
				+ "(select a.CompanyName from U_ProductAgent a where a.ChannelID=sc.UpWeiID),sc.UpWeiID,"
				+ "(select ps.SupplyName from U_PlatformSupplyer ps where s.SupplyID=ps.WeiID),s.Address,s.SupplyID,sc.CancelTime,s.ChannelID"
				+ " from U_ProductShop s,U_SupplyChannel sc where sc.Verifier=? and sc.DemandID=? and s.Area=? and s.ChannelID=sc.ChannelID";
		//状态为全部时
		if(state!=null&&state!=-1){
			sql+=" and sc.State=?";
			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
			return super.queryBySql(sql,verifierWeiId,demandId,areaId,state);
		}else{
			sql+=" order by sc.CreateTime desc limit "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
			return super.queryBySql(sql,verifierWeiId,demandId,areaId);
		}
	}
	
	@Override
	public long getProductShopByVerifierTotalAmount(Long supplyWeiId,
			Short state ,String beginTime,String endTime) {
		String sql="select count(*) from U_ProductShop s,U_SupplyChannel sc where  s.ChannelID=sc.ChannelID";
		//状态为全部时
		List<Object> params = new ArrayList<Object>();
		//申请人微店号
		if(supplyWeiId!=null && supplyWeiId>0)
		{
			sql+=" and sc.Verifier=? "; 
			params.add(supplyWeiId);
		}
		//状态
		if(state != null && state!=-1)
		{
			sql+=" and sc.State = ?";
			params.add(state);
		}
		//起始日期
		if(beginTime != null && !"".equals(beginTime.trim()))
		{
			sql+=" and sc.CreateTime >= ? ";
			Date time = DateUtils.parseDateTime(beginTime.trim() + " 00:00:00");
			params.add(time);
		}
		//结束日期
		if(endTime != null && !"".equals(endTime.trim()))
		{
			sql+=" and sc.CreateTime <= ? ";
			Date time = DateUtils.parseDateTime(endTime.trim() + " 23:59:59");
			params.add(time);
		}
		return super.countBySql(sql, params.toArray());
//		if(state!=null&&state!=-1){
//			sql+=" and sc.State=?";
//			return super.countBySql(sql,supplyWeiId,state);
//		}else{
//			return super.countBySql(sql,supplyWeiId);
//		}
	}
	
	@Override
	public long getProductShopByVerifierTotalAmount(Long supplyWeiId,
			Short state,Integer demandId,Integer areaId) {
		String sql="select count(*) from U_ProductShop s,U_SupplyChannel sc where sc.Verifier=? and sc.DemandID=? and s.Area=? and s.ChannelID=sc.ChannelID";
		//状态为全部时
		if(state!=null&&state!=-1){
			sql+=" and sc.State=?";
			return super.countBySql(sql,supplyWeiId,demandId,areaId,state);
		}else{
			return super.countBySql(sql,supplyWeiId,demandId,areaId);
		}
	}

	@Override
	public List<Object[]> getMyDevelopAgentDetail(Integer parId) {
		StringBuilder sb = new StringBuilder("select a.State,a.CreateTime,a.AgentReward,a.PayedReward,a.AgentType,");
		sb.append("(select p.SupplyName from U_PlatformSupplyer p where p.WeiID=a.SupplyID),");
		sb.append("(select b.SupplyName from U_BrandSupplyer b where b.WeiID=a.SupplyID),");
		sb.append("a.Verifier,a.Provice,a.City,a.Area,a.Address,a.WeiID,a.LinkMan,a.Phone,a.SupplyID,a.DemandID,a.PARID,a.LinkMan,a.CreateTime from U_ProductAgent a where PARID=? ");
		return super.queryBySql(sb.toString(),parId);
	}

	@Override
	public List<Object[]> getProductShopAmountByAgentId(List<Long> parIdList) {
		String hql = "select count(s.agentId),s.agentId from UProductShop s where s.agentId in (:parIdList) group by s.agentId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parIdList", parIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public void batchUpdateProductShopState(Integer[] shopId, Short state) {
		if(shopId==null||shopId.length<1){
			return;
		}
		String hql = "update USupplyChannel set state = "+state+" where channelId in (:shopId)";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("shopId",shopId);
		super.executeHqlByMap(hql, map);
	}

	@Override
	public List<USupplyDemand> getUSupplyDemandList(Long weiId) {
		String hql = "from USupplyDemand where weiId = ? and state = ?";
		return super.find(hql,weiId,Short.parseShort(DemandStateEnum.Showing.toString()));
	}

	@Override
	public List<UProductShop> getUProductShopList(Long weiId, Long supplyWeiId) {
		String hql = "from UProductShop where weiId=? and supplyId=?";
		return super.find(hql,weiId,supplyWeiId);
	}

	@Override
	public void insertProductShop(UProductShop shop) {
		String sql = "insert into U_ProductShop(ChannelID,DemandID,WeiID,SupplyID,CreateTime,WeiName,LinkMan,Phone) values(?,?,?,?,?,?,?,?)";
		super.executeSql(sql,shop.getChannelId(),shop.getDemandId(),shop.getWeiId(),shop.getSupplyId(),shop.getCreateTime(),shop.getWeiName(),shop.getLinkMan(),shop.getPhone());
	}

	@Override
	public List<UBatchVerifierRegion> getVerifierRegion(Long weiId, Short type) {
		if(weiId==null || weiId<1 || type==null)
		   return null;
		String hql = " from UBatchVerifierRegion where weiId=? and type=? and status=1";
		Object[] obj=new Object[2];
		obj[0]=weiId;
		obj[1]=type;
		return super.find(hql, obj);
	}

	@Override
	public List<UProductShop> getLandShopListMyself(Long weiId,Limit limit) {
		// TODO 自动生成的方法存根
		if(weiId==null || weiId<0)
		  return null;
		String hql =" from UProductShop where weiId=? order by createTime ";
		return super.findPage(hql, limit.getPageId(), limit.getSize(), weiId);
	}

	@Override
	public List<UPlatformSupplyer> getPlatformSupplyerListByIds(List<Long> ids) {
		// TODO 自动生成的方法存根
		if(ids==null || ids.size()==0)
		  return null;
		String hql =" from UPlatformSupplyer where weiId in (:weiId)";
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("weiId", ids);
		return super.findByMap(hql, map);
	} 

	@Override
        public List<UBrandSupplyer> getBrandSupplyersList(List<Long> ids) {
            // TODO Auto-generated method stub
            if(ids==null || ids.size()==0)
                return null;
              String hql =" from UBrandSupplyer where weiId in (:weiId)";
              Map<String,Object> map =new HashMap<String,Object>();
              map.put("weiId", ids);
              return super.findByMap(hql, map);
        }

    @Override
	public List<USupplyDemand> getSupplyDemandListByIds(List<Integer> ids) {
		// TODO 自动生成的方法存根
		if(ids==null || ids.size()==0)
			  return null;
		String hql = " from USupplyDemand where demandId in (:demandId)";
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("demandId", ids);
		return super.findByMap(hql, map);
	}

	@Override
	public long getMyLandShopCount(Long weiId) {
		// TODO 自动生成的方法存根
		if(weiId==null || weiId<0)
			  return 0;
		String hql ="select count(*) from U_ProductShop where WeiID=?";
		return super.countBySql(hql, weiId);
	}

	@Override
	public List<USupplyChannel> getSupplyChannelList(Long weiid) {
		// TODO 自动生成的方法存根
		if(weiid==null || weiid<0)
		   return null;
		String hql = " from USupplyChannel where weiId=? and state=1";
		return super.find(hql, weiid);
	}

	@Override
	public List<USupplyChannel> getUBrandSupplyerByParam(Long weiId,
			Integer demandId) {
		// TODO 自动生成的方法存根
		String hql =" from USupplyChannel where weiId=? and demandId=?";
		return super.find(hql, weiId,demandId);
	}

}
