package com.okwei.dao.impl.user;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBatchPort;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenStaff;
import com.okwei.bean.domain.UChildrenSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UOtherLogin;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.enums.OtherLoginType;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.ILoginDAO;
import com.okwei.util.ObjectUtil;

@Repository
public class LoginDAO extends BaseDAO implements ILoginDAO
{

    @Override
    public UWeiSeller getUWeiSeller(String username,String md5Pwd)
    {
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(md5Pwd))
        {
            return null;
        }
        if(username.length() == 11)
        {
            String hql = "from UWeiSeller u where u.mobilePhone=? and u.passWord=? and u.mobileIsBind=2";
            UWeiSeller seller = getUniqueResultByHql(hql,username,md5Pwd);
            return seller;
        }
        else
        {
            Long weiid;
            try
            {
                weiid = Long.parseLong(username);
            }
            catch(Exception e)
            {
                return null;
            }
            String hql2 = "from UWeiSeller u where u.weiId=? and u.passWord=?";
            UWeiSeller seller = getUniqueResultByHql(hql2,weiid,md5Pwd);
            return seller;
        }
    } 

    @Override
    public UWeiSeller getUWeiSeller(String username,String md5Pwd,String desPwd)
    {
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(md5Pwd))
        {
            return null;
        }
        if(username.length() == 11)
        {
            String hql = "from UWeiSeller u where u.mobilePhone=? and (u.passWord=? or u.pwd=?) and u.mobileIsBind=2";
            UWeiSeller seller = getUniqueResultByHql(hql,username,md5Pwd,desPwd);
            return seller;
        }
        else
        {
            Long weiid;
            try
            {
                weiid = Long.parseLong(username);
            }
            catch(Exception e)
            {
                return null;
            }
            String hql2 = "from UWeiSeller u where u.weiId=? and (u.passWord=? or u.pwd=?)";
            UWeiSeller seller = getUniqueResultByHql(hql2,weiid,md5Pwd,desPwd);
            return seller;
        }
    }



    @Override
    public UWeiSeller getUWeiSeller(long weiid)
    {
        if(weiid < 1)
        {
            return null;
        }
        UWeiSeller model = get(UWeiSeller.class,weiid);
        return model;
    }

    @Override
    public USupplyer getUSupplyer(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        return get(USupplyer.class,weiid);
    }

    @Override
    public UShopInfo getUShopInfo(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        return get(UShopInfo.class,weiid);
    }

    @Override
    public UVerifier getUVerifier(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        return get(UVerifier.class,weiid);
    }

    @Override
    public UBatchPort getUBatchPort(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        return get(UBatchPort.class,weiid);
    }

    @Override
    public UYunVerifier getUYunVerifier(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        return get(UYunVerifier.class,weiid);
    }

    @Override
    public void insetUWeiSellerLoginLog(UWeiSellerLoginLog log)
    {
        if(log == null)
        {
            return;
        }
        save(log);
    }

    @Override
    public Long getUWeiSellerByPhone(String phone)
    {
        if(ObjectUtil.isEmpty(phone))
        {
            return 0L;
        }
        String hql = "from UWeiSeller u where u.mobilePhone=? and u.mobileIsBind=2";
        UWeiSeller seller = getUniqueResultByHql(hql,phone);
        if(seller != null && seller.getWeiId()!=null&& seller.getWeiId() > 1)
        {
            return seller.getWeiId();
        }
        return null;
    }

    @Override
    public boolean updateUWeiSellerByPwd(Long weiid,String md5Pwd)
    {
        if(weiid == null || weiid < 1 || ObjectUtil.isEmpty(md5Pwd))
        {
            return false;
        }
        UWeiSeller seller = get(UWeiSeller.class,weiid);
        if(seller != null)
        {
            seller.setPassWord(md5Pwd);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertTVerificationId(TVerificationId model)
    {
        if(model == null)
            return false;
        save(model);
        return true;
    }

    @Override
    public boolean updateUWeiSeller(Long weiid,String passWord)
    {
        if(weiid == null || weiid < 1 || ObjectUtil.isEmpty(passWord))
        {
            return false;
        }
        UWeiSeller user = get(UWeiSeller.class,weiid);
        if(user == null)
        {
            return false;
        }
        user.setPassWord(passWord);
        return true;
    }

    @Override
    public UChildrenUser getChildrenUser(String username,String md5Pwd)
    {
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(md5Pwd))
        {
            return null;
        }
        String hql = "from UChildrenUser u where u.childrenId=? and u.passWord=?";
        UChildrenUser childern = getUniqueResultByHql(hql,username,md5Pwd);
        return childern;
    }

    @Override
    public UWeiSeller getUWeiSellerByWeiId(Long weiid)
    {
        if(weiid == null || weiid < 1)
        {
            return null;
        }
        UWeiSeller seller = get(UWeiSeller.class,weiid);
        return seller;
    }

    @Override
    public boolean insertUWeiSeller(UWeiSeller seller)
    {
//        if(seller == null || seller.getWeiId() < 1)
//        {
//            return false;
//        }
        save(seller);
        return true;
    }

    @Override
    public boolean getUOtherLogin(long weiid,OtherLoginType type)
    {
        if(weiid < 1)
        {
            return false;
        }
        String hql = "select count(1) from UOtherLogin u where u.weiId=? and u.portType=?";
        long count = count(hql,weiid,Short.parseShort(type.toString()));
        if(count > 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public UOtherLogin getOtherLogin(long weiid,short type)
    {
        if(weiid < 1 || type < 1)
        {
            return null;
        }
        String hql = "from UOtherLogin u where u.weiId=? and u.portType=? order by createTime desc";
        UOtherLogin model = getUniqueResultByHql(hql,weiid,type);
        return model;
    }

    @Override
    public boolean delUOtherLogin(long weiid,short type)
    {
        UOtherLogin other = getOtherLogin(weiid,type);
        if(other != null)
        {
            delete(other);
            return true;
        }
        return false;
    }

    @Override
    public boolean getUOtherLogin(long weiid,short type)
    {
        if(weiid < 1)
        {
            return false;
        }
        String hql = "select count(1) from UOtherLogin u where u.weiId=? and u.portType=?";
        long count = count(hql,weiid,type);
        if(count > 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public UChildrenUser getChildrenUser(String childId)
    {
        if(ObjectUtil.isEmpty(childId))
            return null;
        UChildrenUser user = get(UChildrenUser.class,childId);
        return user;
    }

    @Override
    public UChildrenSupplyer getChildrenSupplyer(String childId)
    {
        if(ObjectUtil.isEmpty(childId))
            return null;
        UChildrenSupplyer user = get(UChildrenSupplyer.class,childId);
        return user;
    }

    @Override
    public UChildrenStaff getChildrenStaff(String childId)
    {
        if(ObjectUtil.isEmpty(childId))
            return null;
        UChildrenStaff user = get(UChildrenStaff.class,childId);
        return user;
    }

    @Override
    public UUserAssist getUUserAssist(long weiid)
    {
        if(weiid < 1)
            return null;
        UUserAssist model = get(UUserAssist.class,weiid);
        return model;
    }

    @Override
    public UOtherLogin getOtherLoginByOpenIDAndType(String openID,short type)
    {
        if(ObjectUtil.isEmpty(openID) || type < 1)
        {
            return null;
        }
        String hql = "from UOtherLogin u where u.openId=? and u.portType=? order by createTime desc";
        UOtherLogin model = getUniqueResultByHql(hql,openID,type);
        return model;
    }

    @Override
    public boolean insertOtherLogin(UOtherLogin login)
    {
        if(login == null)
            return false;
        UOtherLogin old = getOtherLoginByOpenIDAndType(login.getOpenId(),login.getPortType());
        if(old != null)
        {
            delete(old);
        }
        save(login);
        return true;
    }

    @Override
    public boolean insertUserAssist(UUserAssist assist)
    {
        if(assist == null)
            return false;
        save(assist);
        return true;
    }

    @Override
    public boolean insertShopInfo(UShopInfo info)
    {
        if(info == null)
            return false;
        save(info);
        return true;
    }

    @Override
    public UPlatformSupplyer selectU_PlatformSupplyer(Long weiId,Short state)
    {
        return getUniqueResultByHql(" from UPlatformSupplyer where weiId=? and state=?",weiId,state);
    }

    @Override
    public UBrandSupplyer selectU_BrandSupplyer(Long weiId,Short state)
    {
        return getUniqueResultByHql(" from UBrandSupplyer where weiId=? and state=?",weiId,state);
    }

	@Override
	public UPlatformSupplyer getPlatform(Long weiId) {
		// TODO 自动生成的方法存根
		return super.get(UPlatformSupplyer.class, weiId);
	}

	@Override
	public UBrandSupplyer getBrand(Long weiId) {
		// TODO 自动生成的方法存根
		return super.get(UBrandSupplyer.class, weiId);
	}

    @Override
    public void insertPShelverCount(PShelverCount psc)
    {
        save(psc);
    }
	
}
