package com.okwei.myportal.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UOtherLogin;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.MobileBindEnum;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.dao.IUserInfoDAO;
import com.okwei.util.DateUtils;

@Repository
public class UserInfoDAO extends BaseDAO implements IUserInfoDAO
{

    @Override
    public List<Object[]> getOrderCounts(Long weiid)
    {
        // TODO 自动生成的方法存根
        //String sql = "select count(State),State from O_SupplyerOrder where BuyerDel=1 and OrderType in (1,8,9) and BuyerID=" + weiid.toString() + "  group by State";
        String sql ="SELECT count(state),state FROM"
					+"("
					+"	SELECT "
					+"	("
					+"		case "
					+"		WHEN a.State =10 then 0"
					+"		WHEN a.State =3 and b.TailPayType=1 then 0"
					+"		WHEN a.State =12 and b.TailPayType=0 then 0"
					+"		WHEN a.State =12 and b.TailPayType =1 then 1"
					+"		ELSE a.State"
					+"		end"
					+"	) as state"
					+"	from O_SupplyerOrder a"
					+"	LEFT JOIN O_BookAssist b"
					+"	on a.SupplierOrderID = b.SupplierOrderID"
					+"	where a.BuyerID=? and a.OrderType in (1,8,9,12,19,20,24) "
					+"	and a.State in (0,1,2,3,5,10,12) and a.BuyerDel=1 "
					+") as c"
					+" GROUP BY state;";
    	
    	return queryBySql(sql,weiid);
    }

    @Override
    public Integer getTodayOrderCount(Long weiid)
    {
        // TODO 自动生成的方法存根
        String now = DateUtils.getDbDate();
        String begin = now + " 00:00:00";
        String end = now + " 23:59:59";
        String sql = "select count(1) from O_SupplyerOrder where BuyerDel=1 and BuyerID=" + weiid.toString() + " and OrderTime between '" + begin + "' and '" + end + "'";

        return Integer.parseInt(getUniqueSQLResult(sql).toString());
    }

    @Override
    public List<Object[]> getProductCounts(Long weiid)
    {
        // TODO 自动生成的方法存根
        String sql = "select count(State),State from P_ClassProducts where WeiID=" + weiid.toString() + " group by State";
        return queryBySql(sql);
    }

    @Override
    public Integer getYeserDayCount(Long weiid)
    {
        // TODO 自动生成的方法存根
        String now = DateUtils.getDbDate();
        String begin = now + " 00:00:00";
        String end = now + " 23:59:59";
        String sql = "select count(1) from U_WeiSeller where SponsorWeiID=" + weiid.toString() + " and States=1 and RegisterTime between '" + begin + "' and '" + end + "'";
        return Integer.parseInt(getUniqueSQLResult(sql).toString());
    }

    @Override
    public Integer getTotalCount(Long weiid)
    {
        // TODO 自动生成的方法存根
        String sql = "select count(1) from U_WeiSeller where SponsorWeiID=" + weiid.toString() + " and States=1";
        return Integer.parseInt(getUniqueSQLResult(sql).toString());
    }

    @Override
    public UShopInfo getUserInfo(Long weiid)
    {
        // TODO 自动生成的方法存根
        return get(UShopInfo.class,weiid);
    }

    @Override
    public UWeiSeller getWeiSellerInfo(Long weiid)
    {
        // TODO 自动生成的方法存根
        return get(UWeiSeller.class,weiid);
    }

    @Override
    public UWallet getWallet(Long weiid)
    {
        // TODO 自动生成的方法存根
        return get(UWallet.class,weiid);
    }

    @Override
    public List<UOtherLogin> getOtherLogin(Long weiid)
    {
        // TODO 自动生成的方法存根
        String hql = " from UOtherLogin p where p.weiId=?";
        Object[] b = new Object[1];
        b[0] = weiid;
        return find(hql,b);
    }

    @Override
    public UBatchSupplyer getBatchSupplyerInfo(Long weiid)
    {
        // TODO 自动生成的方法存根
        return get(UBatchSupplyer.class,weiid);
    }

    @Override
    public void saveShopInfo(UShopInfo shopinfo)
    {
        // TODO 自动生成的方法存根
        save(shopinfo);
    }

    @Override
    public boolean getUserPhoneisBind(String phone)
    {
        String hql = "from UWeiSeller u where u.mobilePhone=? and u.mobileIsBind=?";
        Object[] b = new Object[2];
        b[0] = phone;
        b[1] = Short.parseShort(MobileBindEnum.Bind.toString());
        UWeiSeller userSeller = super.getUniqueResultByHql(hql,b);
        if(userSeller == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean getPhoneSendSMS(long weiid,short itype)
    {
        String timeStr = DateUtils.format(new Date(),"yyyy-MM-dd 00:00:00");
        String hql = "from TVerificationId t where t.weiNo=? and t.type=? and t.getTime>?";
        Object[] b = new Object[3];
        b[0] = weiid;
        b[1] = itype;
        b[2] = DateUtils.parseDateTime(timeStr);// DateUtils.format((new Date())),format)
        List<TVerificationId> vcList = super.find(hql,b);
        if(vcList == null || vcList.size() < 3)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean insertTVerificationID(String code,long weiid,short itype,String phone,String ip)
    {
        TVerificationId tvId = new TVerificationId();
        tvId.setGetTime(new Date());
        tvId.setStatus(Short.parseShort("0"));
        tvId.setType(itype);
        tvId.setUserIp(ip);
        tvId.setVcode(code);
        tvId.setVerificationtext(phone);
        tvId.setWeiNo(weiid);
        save(tvId);
        return true;
    }

    @Override
    public USupplyer getSupplyerInfo(Long weiid)
    {
        return get(USupplyer.class,weiid);
    }

    @Override
    public TBatchMarket getTBatchMarket(int bmid)
    {
        return get(TBatchMarket.class,bmid);
    }

    @Override
    public void updateShopInfo(UShopInfo shopinfo)
    {
        update(shopinfo);
    }

    @Override
    public void updateUWeiSeller(UWeiSeller user)
    {
        update(user);
    }

    @Override
    public void updateTVerificationId(Long weiid,String code)
    {
        String hql = "from TVerificationId t where t.weiNo=? and t.vcode=?";
        Object[] b = new Object[2];
        b[0] = weiid;
        b[1] = code;
        TVerificationId tvid = getUniqueResultByHql(hql,b);
        tvid.setStatus(Short.parseShort("1"));
        update(tvid);
    }

	@Override
	public void updateUWallet(UWallet wallet) {
		update(wallet);
	}

    @Override
    public List<UOtherLogin> getuolListByWeiId(Long weiid)
    {
        String hql = "from UOtherLogin u where u.weiId=?";
        Object[] b = new Object[1];
        b[0] = weiid;
        List<UOtherLogin> olList = find(hql,b); 
        return olList;
    }

    @Override
    public void updateUOtherLogin(Long weiid,short ltype)
    {
        String hql = "from UOtherLogin u where u.weiId=? and u.portType=?";
        Object[] b = new Object[2];
        b[0] = weiid;
        b[1] = ltype;
        UOtherLogin ol = getUniqueResultByHql(hql,b);
        if(ol!=null)
        {
            delete(ol);
        }
    }

	@Override
	public int getAttentionCount(long weiID) {
		if(weiID <1){
			return 0;
		}
		
		String sql ="SELECT COUNT(*) FROM ("
						+ "SELECT AttTo from "
						+" ( "
						+" SELECT AttTo from U_Attention where Attentioner =? and `Status`=1 "
						+" UNION ALL "
						+" SELECT WeiID from U_Supplyer where Type >0 "
						+" ) as c "
						+" GROUP BY AttTo HAVING COUNT(*)>1"
					+ ") as d";
		return (int) super.countBySql(sql, weiID);
	}

	@Override
	public int getAtteneionedCount(long weiID) {
		if(weiID <1){
			return 0;
		}
		
		String hql = "select count(*) from UAttentioned p where status in(1,2) AND p.attTo=?";
		return (int) super.count(hql, weiID);
	}

	@Override
	public int getProductCount(long weiID,short state) {
		if(weiID <1){
			return 0;
		}
		
		String hql ="select count(*) from PProducts p where p.supplierWeiId=? and p.state =?";
		
		return (int) super.count(hql, weiID,state);
	}

	@Override
	public List<Object[]> getSupplyOrderCount(long weiID) {
		if(weiID < 1){
			return null;
		}
        String sql ="SELECT count(state),state FROM"
					+"("
					+"	SELECT "
					+"	("
					+"		case "
					+"		WHEN a.State =10 then 0"
					+"		WHEN a.State =3 and b.TailPayType=1 then 0"
					+"		WHEN a.State =12 and b.TailPayType=0 then 0"
					+"		WHEN a.State =12 and b.TailPayType =1 then 1"
					+"		ELSE a.State"
					+"		end"
					+"	) as state"
					+"	from O_SupplyerOrder a"
					+"	LEFT JOIN O_BookAssist b"
					+"	on a.SupplierOrderID = b.SupplierOrderID"
					+"	where a.SupplyerID=? and a.OrderType in (1,8,9,12) "
					+"	and a.State in (0,1,2,3,5,10,12) and a.SellerDel=1"
					+") as c"
					+" GROUP BY state;";
    	
    	return queryBySql(sql,weiID);
	}
}
