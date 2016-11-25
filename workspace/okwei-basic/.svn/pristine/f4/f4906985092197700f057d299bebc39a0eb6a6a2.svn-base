package com.okwei.dao.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.PayedRewardStatuus;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.VerifierRegionEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IAgentDAO;

@Repository
public class AgentDAO extends BaseDAO implements IAgentDAO
{

    @Override
    public UAgentApply getAgentApply(int applyId)
    {
        UAgentApply model = get(UAgentApply.class,applyId);
        return model;
    }

    @Override
    public UProductAgent getProductAgent(int parId)
    {
        UProductAgent model = get(UProductAgent.class,parId);
        return model;
    }

    @Override
    public List<UProductAgent> getProductAgentsList(long weiid,short status)
    {
        String hql = "from UProductAgent u where u.weiId=? and u.state=?";
        List<UProductAgent> list = super.find(hql,weiid,status);
        return list;
    }

    @Override
    public List<UProductAgent> getProductAgentsList(long weiid)
    {
        String hql = "from UProductAgent u where u.weiId=?";
        List<UProductAgent> list = super.find(hql,weiid);
        return list;
    }

    @Override
    public UProductShop getProductShop(int shopId)
    {
        UProductShop model = get(UProductShop.class,shopId);
        return model;
    }

    @Override
    public List<UProductShop> getProductShopsList(long weiid,short status)
    {
        String hql = "from UProductShop u where u.weiId=? and u.state=?";
        List<UProductShop> list = super.find(hql,weiid,status);
        return list;
    }

    @Override
    public List<UProductShop> getProductShopsList(long weiid)
    {
        String hql = "from UProductShop u where u.weiId=?";
        List<UProductShop> list = super.find(hql,weiid);
        return list;
    }

    @Override
    public USupplyDemand getSupplyDemand(int demandId)
    {
        USupplyDemand model = get(USupplyDemand.class,demandId);
        return model;
    }

    @Override
    public List<UAgenArea> getUAgenAreasByDemandId(int demandId)
    {
        String hql = "from UAgenArea u where u.demandId=?";
        List<UAgenArea> list = super.find(hql,demandId);
        return list;
    }

    @Override
    public int getDemandProductCount(List<Integer> demanIdList)
    {
        String hql = "select count(1) from UDemandProduct u where u.demandId in (:demandId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("demandId",demanIdList);
        long count = countByMap(hql,map);
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public List<USupplyDemand> getsupSupplyDemandsByIds(List<Integer> demanIdList)
    {
        if(demanIdList == null || demanIdList.size() < 1)
        {
            return null;
        }
        String hql = " from USupplyDemand u where u.demandId in (:demandId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("demandId",demanIdList);
        List<USupplyDemand> list = findByMap(hql,map);
        return list;
    }

    @Override
    public List<UAgentApply> getAgentApply(long weiId,int demandId)
    {
        String hql = "from UAgentApply u where u.demandId=? and u.weiId=?";
        List<UAgentApply> list = find(hql,demandId,weiId);
        return list;
    }

    @Override
    public List<UAgentApplyArea> getAgentApplyAreas(List<Integer> applyIds)
    {
        if(applyIds == null || applyIds.size() < 1)
        {
            return null;
        }
        String hql = "from UAgentApplyArea u where u.applyId in (:applyId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("applyId",applyIds);
        List<UAgentApplyArea> list = findByMap(hql,map);
        return list;
    }

    @Override
    public List<UProductAgent> getProductAgentsListByDemandId(int demandId)
    {
        String hql = "from UProductAgent u where u.demandId=?";
        List<UProductAgent> list = find(hql,demandId);
        return list;
    }

    @Override
    public List<UAgenArea> getUAgenAreasByChannelIds(List<Integer> channelIds)
    {
        if(channelIds == null || channelIds.size() < 1)
        {
            return null;
        }
        String hql = "from UAgenArea u where u.channelId in (:channelId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channelId",channelIds);
        List<UAgenArea> list = findByMap(hql,map);
        return list;
    }

    @Override
    public boolean insertUAgentApply(UAgentApply agent)
    {
        if(agent == null)
            return false;
        save(agent);
        return true;
    }

    @Override
    public boolean insertUAgentApplyArea(UAgentApplyArea area)
    {
        if(area == null)
            return false;
        save(area);
        return false;
    }

    @Override
    public Integer insertUProductAgent(UProductAgent agent)
    {
        if(agent == null)
            return null;
        save(agent);
        return 0;// gent.getParid();
    }

    @Override
    public boolean insertUAgentArea(UAgenArea area)
    {
        if(area == null)
            return false;
        save(area);
        return true;
    }

    @Override
    public TRegional getTRegional(Integer code)
    {
        TRegional model = get(TRegional.class,code);
        return model;
    }

    @Override
    public TRegional getTRegionalParent(Integer code)
    {
        String hql = "from TRegional t where t.parent=?";
        TRegional model = super.getUniqueResultByHql(hql,code);
        return model;
    }

    @Override
    public PageResult<UAgentApply> getAgentApplyListByPage(long weiid,short status,Limit limit)
    {
        if(limit == null || weiid < 1)
        {
            return null;
        }
        if(status == -1)
        {
            String hql = "from UAgentApply u where u.weiId=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,weiid);
            return list;
        }
        else
        {
            String hql = "from UAgentApply u where u.weiId=? and u.state=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,weiid,status);
            return list;
        }
    }

    @Override
    public UAgentApplyArea getAgentApplyAreas(Integer applyid)
    {
        if(applyid == null || applyid < 1)
            return null;
        String hql = "from UAgentApplyArea u where u.applyId=?";
        UAgentApplyArea aaa = super.getUniqueResultByHql(hql,applyid);
        return aaa;
    }

    @Override
    public USupplyChannel getSupplyChannel(int channelId)
    {
        if(channelId < 1)
            return null;
        USupplyChannel model = get(USupplyChannel.class,channelId);
        return model;
    }

    @Override
    public Integer insertUSupplyChannel(USupplyChannel sc)
    {
        if(sc == null)
            return null;
        save(sc);
        return sc.getChannelId();
    }

    @Override
    public int getChannelCount(long supplyId,SupplyChannelTypeEnum channelType)
    {
        if(supplyId < 1 || channelType == null)
            return 0;
        String hql = "select count(1) from USupplyChannel u where u.supplyId=? and u.channelType=?";
        long count = super.count(hql,supplyId,Short.parseShort(channelType.toString()));
        return (int) count;
    }

    @Override
    public UAgenArea getAgenArea(int demandId,int channelld)
    {
        if(demandId < 1 || channelld < 1)
            return null;
        String hql = "from UAgenArea u where u.demandId=? and u.channelId=?";
        UAgenArea model = getUniqueResultByHql(hql,demandId,channelld);
        return model;
    }

    @Override
    public List<UAgentApplyFollowLog> getAgentApplyLog(Integer applyId)
    {
        if(applyId == null || applyId < 1)
        {
            return null;
        }
        String hql = "from UAgentApplyFollowLog u where u.applyId=? order by u.createTime desc";
        List<UAgentApplyFollowLog> list = find(hql,applyId);
        return list;
    }

    @Override
    public PageResult<USupplyChannel> getSupplyChannelPageResult(long supplyId,AgentStatusEnum status,Limit limit,SupplyChannelTypeEnum type)
    {
        if(supplyId < 1 || limit == null)
        {
            return null;
        }
        String hql = "from USupplyChannel u where u.supplyId=? and u.state=? and u.channelType=?";
        PageResult<USupplyChannel> pr = findPageResult(hql,limit,supplyId,Short.parseShort(status.toString()),Short.parseShort(type.toString()));
        return pr;
    }

    @Override
    public USupplyChannel getSupplyChannelByApplyId(int applyId)
    {
        if(applyId < 1)
            return null;
        String hql = "from USupplyChannel u where u.applyID=?";
        USupplyChannel model = getUniqueResultByHql(hql,applyId);
        return model;
    }

    @Override
    public UDemandRequired getDemandRequired(Integer requiredID)
    {
        if(requiredID == null || requiredID < 1)
            return null;
        UDemandRequired model = get(UDemandRequired.class,requiredID);
        return model;
    }

    @Override
    public USupplyDemandArea getSupplyDemandArea(Integer demandAreaID)
    {
        if(demandAreaID == null || demandAreaID < 1)
            return null;
        USupplyDemandArea model = get(USupplyDemandArea.class,demandAreaID);
        return model;
    }

    @Override
    public USupplyDemandArea getSupplyDemandArea(Integer demandID,Integer code)
    {
        if(demandID == null || demandID < 1 || code == null || code < 1)
            return null;
        String hql = "from USupplyDemandArea u where u.demandId=? and u.code=?";
        USupplyDemandArea model = getUniqueResultByHql(hql,demandID,code);
        return model;
    }

    @Override
    public List<USupplyDemandArea> getSupplyDemandAreaList(Integer demandID)
    {
        if(demandID == null || demandID < 1)
            return null;
        String hql = "from USupplyDemandArea u where u.demandId=?";
        List<USupplyDemandArea> list = find(hql,demandID);
        return list;
    }

    @Override
    public PageResult<USupplyChannel> getSuperiorAgentPageResult(long weiid,Limit limit)
    {
        if(weiid < 1 || limit == null)
        {
            return null;
        }
        String hql = "from USupplyChannel u where u.weiId=? and u.state=?";
        PageResult<USupplyChannel> pr = findPageResult(hql,limit,weiid,Short.parseShort(AgentStatusEnum.Normal.toString()));
        return pr;
    }

    @Override
    public UBrandSupplyer getBrandSupplyer(long weiid)
    {
        if(weiid < 1)
            return null;
        UBrandSupplyer model = get(UBrandSupplyer.class,weiid);
        return model;
    }

    @Override
    public UPlatformSupplyer getUPlatformSupplyer(long weiid)
    {
        if(weiid < 1)
            return null;
        UPlatformSupplyer model = get(UPlatformSupplyer.class,weiid);
        return model;
    }

    @Override
    public int getProductCountByDemandID(Integer DemandID)
    {
        if(DemandID == null || DemandID < 1)
        {
            return 0;
        }
        String hql = "select count(1) from UDemandProduct u where u.demandId=?";
        long count = count(hql,DemandID);
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public int getPayedRewardCount(long supplyId)
    {
        if(supplyId < 1)
        {
            return 0;
        }
        String hql = "select count(1) from USupplyChannel u where u.supplyId=? and u.payedReward=?";
        long count = count(hql,supplyId,Short.parseShort(PayedRewardStatuus.No.toString()));
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public boolean isPayedRewardBySupplyId(int channelId)
    {
        if(channelId < 1)
        {
            return false;
        }
        USupplyChannel sc = get(USupplyChannel.class,channelId);
        if(sc == null)
        {
            return false;
        }
        if(sc.getPayedReward() == 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public int getApplyAgentByStatus(long supplyId,ApplyAgentStatusEnum status)
    {
        if(supplyId < 1)
        {
            return 0;
        }
        String hql = "select count(1) from UAgentApply u where u.supplyId=? and u.state=?";
        long count = count(hql,supplyId,Short.parseShort(status.toString()));
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public int getAgentByStatus(long supplyId,SupplyChannelTypeEnum type,AgentStatusEnum status)
    {
        if(supplyId < 1)
        {
            return 0;
        }
        String hql = "select count(1) from USupplyChannel u where u.supplyId=? and u.state=? and u.channelType=?";
        long count = count(hql,supplyId,Short.parseShort(status.toString()),Short.parseShort(type.toString()));
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public PageResult<UAgentApply> getAgentApplyListBySupplyId(long supplyId,short status,Limit limit)
    {
        if(limit == null || supplyId < 1)
        {
            return null;
        }
        if(status == -1)
        {
            String hql = "from UAgentApply u where u.supplyId=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,supplyId);
            return list;
        }
        else
        {
            String hql = "from UAgentApply u where u.supplyId=? and u.state=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,supplyId,status);
            return list;
        }
    }

    @Override
    public List<USupplyDemand> getSupplyDemandListBySupplyID(long supplyID,DemandStateEnum state)
    {
        if(supplyID < 0)
            return null;
        String hql = "from USupplyDemand u where u.weiId=? and u.state=?";
        List<USupplyDemand> list = find(hql,supplyID,Short.parseShort(state.toString()));
        return list;
    }

    @Override
    public List<USupplyDemand> getSupplyDemandListBySupplyID(long supplyID)
    {
        if(supplyID < 0)
            return null;
        String hql = "from USupplyDemand u where u.weiId=? ";
        List<USupplyDemand> list = find(hql,supplyID);
        return list;
    }

    @Override
    public List<USupplyChannel> getSupplyChannelList(int demandId,SupplyChannelTypeEnum type,AgentStatusEnum status)
    {
        if(demandId < 1)
            return null;
        String hql = "from USupplyChannel u where u.demandId=? and u.channelType=? and u.state=?";
        List<USupplyChannel> list = find(hql,demandId,Short.parseShort(type.toString()),Short.parseShort(status.toString()));
        return list;
    }

    @Override
    public int getRewardCount(long supplyID,SupplyChannelTypeEnum type,PayedRewardStatuus status)
    {
        if(supplyID < 1)
        {
            return 0;
        }
        String hql = "select count(1) from USupplyChannel u where u.supplyId=? and u.channelType=? and u.payedReward=?";
        long count = count(hql,supplyID,Short.parseShort(type.toString()),Short.parseShort(status.toString()));
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public List<USupplyChannel> getSupplyChannelLIstByWeiID(long weiID,SupplyChannelTypeEnum type,AgentStatusEnum status)
    {
        if(weiID < 1)
        {
            return null;
        }
        String hql = "from USupplyChannel u where u.weiId=? and u.channelType=? and u.state=?";
        List<USupplyChannel> list = find(hql,weiID,Short.parseShort(type.toString()),Short.parseShort(status.toString()));
        return list;
    }

    @Override
    public int getSupplyChannelLIstByWeiIDCount(long weiID,SupplyChannelTypeEnum type,AgentStatusEnum status)
    {
        if(weiID < 1)
        {
            return 0;
        }
        String hql = "select count(1) from USupplyChannel u where u.weiId=? and u.channelType=? and u.state=?";
        long count = count(hql,weiID,Short.parseShort(type.toString()),Short.parseShort(status.toString()));
        return Integer.parseInt(String.valueOf(count));
    }

    /**
     * 落地店
     */
    @Override
    public int getGroundCountByArea(int areaID,int demandID)
    {
        if(areaID < 1 || demandID < 1)
        {
            return 0;
        }
        String hql = "select count(1) from UProductShop u where u.city=? and u.demandId=?";
        long count = count(hql,areaID,demandID);
        return Integer.valueOf(String.valueOf(count));
    }

    @Override
    public List<USupplyChannel> getSupplyChannelListByIDS(Integer[] ids)
    {
        if(ids == null || ids.length < 1)
        {
            return null;
        }
        String hql = "from USupplyChannel u where u.channelId in (:channelId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channelId",ids);
        List<USupplyChannel> list = findByMap(hql,map);
        return list;
    }

    @Override
    public List<UAgentApply> getAgentApplyListByIDS(Integer[] ids)
    {
        if(ids == null || ids.length < 1)
        {
            return null;
        }
        String hql = "from UAgentApply u where u.applyId in (:applyId)";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("applyId",ids);
        List<UAgentApply> list = findByMap(hql,map);
        return list;
    }

    @Override
    public PageResult<UAgentApply> getAgentApplyListByWeiID(long weiid,short status,Limit limit)
    {
        if(limit == null || weiid < 1)
        {
            return null;
        }
        if(status == -1)
        {
            String hql = "from UAgentApply u where u.weiId=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,weiid);
            return list;
        }
        else
        {
            String hql = "from UAgentApply u where u.weiId=? and u.state=? order by u.createTime desc";
            PageResult<UAgentApply> list = findPageResult(hql,limit,weiid,status);
            return list;
        }
    }

    @Override
    public List<UBatchVerifierRegion> getBatchVerifierRegionListByCode(Integer code)
    {
        if(code == null || code < 1)
        {
            return null;
        }
        String hql = "from UBatchVerifierRegion u where u.code=? and u.type=?";
        List<UBatchVerifierRegion> list = find(hql,code,Short.parseShort(VerifierRegionEnum.Platform.toString()));
        return list;
    }

    @Override
    public boolean checkVerfierByArea(long verId,int code)
    {
        if(verId < 1 || code < 1)
        {
            return false;
        }
        String hql = "from UBatchVerifierRegion u where u.weiId=? and u.code=? and u.type=?";
        UBatchVerifierRegion vr = super.getUniqueResultByHql(hql,verId,code,Short.parseShort(VerifierRegionEnum.Platform.toString()));
        if(vr == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAttention(long weiID,long supplyID)
    {
        if(weiID<1||supplyID<1)
        {
            return false;
        }
        String hql = "from UAttention u where u.attentioner=? and u.attTo=?";
        UAttention model = getUniqueResultByHql(hql,weiID,supplyID);
        if(model !=null)
        {
            return true;
        }
        return false;
    }

	@Override
	public UWeiSeller getUser(Long weiId) {
		// TODO 自动生成的方法存根
		return super.get(UWeiSeller.class, weiId);
	} 
}
