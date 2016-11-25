package com.okwei.service.impl.user;

import java.util.List;

import org.hibernate.metamodel.source.annotations.entity.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.UBatchPort;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.VerfierStatusEnum;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.impl.user.LoginDAO;
import com.okwei.dao.user.IAgentDAO;
import com.okwei.dao.user.ILoginDAO;
import com.okwei.dao.user.IPShelverCountMgtDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IUserIdentityManage;
import com.okwei.util.BitOperation;

@Service
public class UserIdentityManage extends BaseService implements IUserIdentityManage
{
    @Autowired
    ILoginDAO LoginDAO;
    @Autowired
    IPShelverCountMgtDAO iPShelverCountMgtDAO;
    @Autowired
    IBaseDAO baseDAO;
    @Autowired
    IAgentDAO agentDAO;

    @Override
    public boolean addIdentity(long weiid,UserIdentityType type)
    {
        if(weiid < 1)
        {
            return false;
        }
        UUserAssist assist = LoginDAO.getUUserAssist(weiid);
        // 判断实体是否存在
        if(assist == null)
        {
            // 不存在的时候
            UWeiSeller seller = LoginDAO.getUWeiSeller(weiid);
            // 用户是否不存在
            if(seller == null)
            {
                return false;
            }
            else
            {
                // 用户存在添加表
                assist = new UUserAssist();
                // 如果改微店还没上架，就想辅助表添加改用户使用上架功能时的个人排序
                PShelverCount psc = iPShelverCountMgtDAO.getPShelverCount();
                if(psc == null)
                {
                    psc = new PShelverCount();
                    psc.setCount(0L);
                    assist.setWeiIdSort(1L);
                    LoginDAO.insertPShelverCount(psc);
                }
                else
                {
                    psc.setCount(psc.getCount() + 1);
                    assist.setWeiIdSort(psc.getCount() + 1);
                }
                if(type == UserIdentityType.commonUser)
                {
                    assist.setIdentity(Integer.parseInt(type.toString()));
                }
                else
                {
                    assist.setIdentity(Integer.parseInt(UserIdentityType.commonUser.toString()) + Integer.parseInt(type.toString()));
                }
                assist.setWeiId(weiid);
                LoginDAO.insertUserAssist(assist);
                return true;
            }
        }
        else
        {
            Integer identity = assist.getIdentity();
            if(identity == null || identity < 1)
            {
                identity = 0;
            }
            // 判断weiIdSort是否为空
            if(assist.getWeiIdSort() == null || assist.getWeiIdSort() <= 0)
            {
                PShelverCount psc = iPShelverCountMgtDAO.getPShelverCount();
                if(psc == null)
                {
                    psc = new PShelverCount();
                    psc.setCount(0L);
                    assist.setWeiIdSort(1L);
                    LoginDAO.insertPShelverCount(psc);
                }
                else
                {
                    psc.setCount(psc.getCount() + 1);
                    assist.setWeiIdSort(psc.getCount() + 1);
                }
            }
            if((identity & Integer.parseInt(type.toString())) > 0)
            {
                return false;// 已经添加了这个权限
            }
            else
            {
                identity = identity + Integer.parseInt(type.toString());
                assist.setIdentity(identity);
                return true;
            }
        }
    }

    @Override
    public boolean removeIdentity(long weiid,UserIdentityType type)
    {
        if(weiid < 1)
        {
            return false;
        }
        UUserAssist assist = LoginDAO.getUUserAssist(weiid);
        // 判断实体是否存在
        if(assist == null)
        {
            // 不存在的时候
            UWeiSeller seller = LoginDAO.getUWeiSeller(weiid);
            // 用户是否不存在
            if(seller == null)
            {
                return false;
            }
            else
            {
                // 用户存在添加表
                assist = new UUserAssist();
                // 如果改微店还没上架，就想辅助表添加改用户使用上架功能时的个人排序
                PShelverCount psc = iPShelverCountMgtDAO.getPShelverCount();
                if(psc == null)
                {
                    psc = new PShelverCount();
                    psc.setCount(0L);
                    assist.setWeiIdSort(1L);
                    LoginDAO.insertPShelverCount(psc);
                }
                else
                {
                    psc.setCount(psc.getCount() + 1);
                    assist.setWeiIdSort(psc.getCount() + 1);
                }
                assist.setIdentity(Integer.parseInt(UserIdentityType.commonUser.toString()));
                assist.setWeiId(weiid);
                LoginDAO.insertUserAssist(assist);
                return true;
            }
        }
        else
        {
            Integer identity = assist.getIdentity();
            if(identity == null || identity < 1)
            {
                identity = 0;
            }
            // 判断weiIdSort是否为空
            if(assist.getWeiIdSort() == null || assist.getWeiIdSort() <= 0)
            {
                PShelverCount psc = iPShelverCountMgtDAO.getPShelverCount();
                if(psc == null)
                {
                    psc = new PShelverCount();
                    psc.setCount(0L);
                    assist.setWeiIdSort(1L);
                    LoginDAO.insertPShelverCount(psc);
                }
                else
                {
                    psc.setCount(psc.getCount() + 1);
                    assist.setWeiIdSort(psc.getCount() + 1);
                }
            }
            if((identity & Integer.parseInt(type.toString())) > 0)
            {
                // 移除相应权限
                identity = identity - Integer.parseInt(type.toString());
                assist.setIdentity(identity);
                return true;
            }
            else
            {
                return false;// 不存在这个权限
            }
        }
    }
    
    
    /**
     * 添加UUserAssist表
     * @param weiid 微店号
     * @return
     */
    @Override
    public boolean insertUUserAssist(long weiid)
    {
        int idtype = getIdentityType(weiid);
        PShelverCount psc = iPShelverCountMgtDAO.getPShelverCount();
        UUserAssist assist = new UUserAssist();
        psc.setCount(psc.getCount() + 1);
        assist.setWeiIdSort(psc.getCount() + 1);
        assist.setWeiId(weiid);
        assist.setIdentity(idtype);
        LoginDAO.insertUserAssist(assist);
        return true;
    }

    /**
     * 获取用户身份值
     */
    public int getIdentityType(long weiid)
    {
        int i = 1;// 默认是微店号权限
        USupplyer supliyer = super.getById(USupplyer.class,weiid);// 是否供应商
        if(supliyer != null && supliyer.getType() != null)
        {
            i += supliyer.getType();
        }
        UYunVerifier verifier = super.getById(UYunVerifier.class,weiid);// 是否认证员
        if(verifier != null && verifier.getType() != null)
        {
            if(verifier.getType() == 1)
            {
                i += Short.parseShort(UserIdentityType.ordinary.toString());
            }
            else if(verifier.getType() == 2)
            {
                if(verifier.getInOrOut() != null && verifier.getInOrOut() == 1)
                {
                    i += Short.parseShort(UserIdentityType.batchverifier.toString());
                }
                else if(verifier.getInOrOut() != null && verifier.getInOrOut() == 2)
                {// 编外
                    i += Short.parseShort(UserIdentityType.percent.toString());
                }
            }
            else if(verifier.getType() == 3)
            {
                // 如果是代理商，分配认证员权限
                i += Integer.parseInt(UserIdentityType.batchverifier.toString());
                i += Integer.parseInt(UserIdentityType.percent.toString());
                i += Integer.parseInt(UserIdentityType.VerifierAgent.toString());
            }
        }

        UBatchPort port = super.getById(UBatchPort.class,weiid);// 批发号相关
        if(port != null && port.getStatus() != null && port.getStatus() == Short.parseShort(VerfierStatusEnum.PayIn.toString()))
        {
            i += Short.parseShort(UserIdentityType.batchverifierport.toString());
        }
        TBatchMarket market = getTBatchMarket(weiid);
        if(market != null && market.getIsAllIn() != null && market.getIsAllIn().shortValue() > 0)
        {
            i += Short.parseShort(UserIdentityType.market.toString()); // 16 * 16;
        }

        // 平台号，品牌号相关
        UBrandSupplyer bs = agentDAO.getBrandSupplyer(weiid);
        if(bs != null)
        {
            i += Integer.parseInt(UserIdentityType.BrandSupplier.toString());
        }
        else
        {
            UPlatformSupplyer ps = agentDAO.getUPlatformSupplyer(weiid);
            if(ps != null)
            {
                i += Integer.parseInt(UserIdentityType.PlatformSupplier.toString());
            }
        }
        List<USupplyChannel> list = agentDAO.getSupplyChannelLIstByWeiID(weiid,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
        if(list != null && list.size() > 0)
        {
            i += Integer.parseInt(UserIdentityType.Agent.toString());
        }
        list = agentDAO.getSupplyChannelLIstByWeiID(weiid,SupplyChannelTypeEnum.ground,AgentStatusEnum.Normal);
        if(list != null && list.size() > 0)
        {
            i += Integer.parseInt(UserIdentityType.Ground.toString());
        }
        return i;
    }

    public TBatchMarket getTBatchMarket(Long weiid)
    {
        String hqlString = " from TBatchMarket t where t.marketWeiId=? ";
        Object[] bb = new Object[1];
        bb[0] = weiid;
        TBatchMarket market = baseDAO.getUniqueResultByHql(hqlString,bb);
        return market;
    }

}
