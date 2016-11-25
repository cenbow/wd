package com.okwei.myportal.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UOtherLogin;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.MobileBindEnum;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.dao.user.IBasicAgentOrProductShopDAO;
import com.okwei.dao.user.IChildrenAccountDAO;
import com.okwei.myportal.bean.dto.ConstantParam;
import com.okwei.myportal.bean.enums.LoginType;
import com.okwei.myportal.bean.enums.VerifyCodeType;
import com.okwei.myportal.bean.util.DB;
import com.okwei.myportal.bean.util.DataColumn;
import com.okwei.myportal.bean.util.DataRow;
import com.okwei.myportal.bean.util.VerifyCode;
import com.okwei.myportal.bean.vo.AccountVO; 
import com.okwei.myportal.bean.vo.MsgKeyValue;
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.bean.vo.NoticeMsg;
import com.okwei.myportal.bean.vo.PhoneVerify;
import com.okwei.myportal.bean.vo.SettingsVO;
import com.okwei.myportal.bean.vo.ShopInfoVO;
import com.okwei.myportal.bean.vo.UserInfoCountVO;
import com.okwei.myportal.cons.Constants;
import com.okwei.myportal.dao.IUserInfoDAO;
import com.okwei.myportal.service.IMyProductService;
import com.okwei.myportal.service.IUserInfoService;
import com.okwei.service.impl.user.AgentService;
import com.okwei.service.user.IAgentService;
import com.okwei.util.BitOperation;
import com.okwei.util.ImgDomain;
import com.okwei.util.MD5Util;
import com.okwei.util.RedisUtil;

@Service
public class UserInfoService implements IUserInfoService {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	IUserInfoDAO userDao;
	@Autowired
	IMyProductService myProductService;
	@Autowired
	IAgentService agentService;
	@Autowired
	IBasicAgentOrProductShopDAO productShopDAO;
	@Autowired
	IChildrenAccountDAO childrenDAO;
	
	@Override
	public UserInfoCountVO getUserCounts(LoginUser user) {
		// TODO 自动生成的方法存根
		//先从缓存里取，若没有在找数据库
		UserInfoCountVO userInfoCount = (UserInfoCountVO) RedisUtil.getObject(ConstantParam.IBS_USERTONGJITOTAL+user.getWeiID());
		if(userInfoCount== null)
		{
			userInfoCount = new UserInfoCountVO(); 
//			//用户身份
			userInfoCount.setYun((short) 0);
			userInfoCount.setBatch((short) 0);
			userInfoCount.setVerifer((short) 0);
			Short type = user.getWeiType()==null?0:user.getWeiType();
			if(BitOperation.getIntegerSomeBit(type, 1)==1)
				userInfoCount.setYun((short) 1);
			if(BitOperation.getIntegerSomeBit(type, 2)==1)
				userInfoCount.setBatch((short) 1);
			if(BitOperation.getIntegerSomeBit(type, 6)==1)
				userInfoCount.setVerifer((short) 1);  
			//用户安全等级
			/*UWallet wallet = userDao.getWallet(user.getWeiID());
			List<UOtherLogin> otherlogin = userDao.getOtherLogin(user.getWeiID());
			
			UWeiSeller weiseller = userDao.getWeiSellerInfo(user.getWeiID());
			Integer step = 0;
			if(weiseller!=null && weiseller.getMobileIsBind()!=null && weiseller.getMobileIsBind()==2)
				step+=30;
			if(weiseller!=null && weiseller.getEmailIsBind()!=null && weiseller.getEmailIsBind()==2)
				step+=20;
			
			if(wallet!=null && wallet.getIdCard()!=null&&!wallet.getIdCard().equals("") && wallet.getRealName()!=null&&!wallet.getRealName().equals(""))
			    step+=30;
			if(otherlogin!=null&&otherlogin.size()>0)
				step+=20;
			userInfoCount.setSecurestep(step);
			
			if(step<50)
				userInfoCount.setStepstr("低");
			else if(step==50)
				userInfoCount.setStepstr("中");
			else 
				userInfoCount.setStepstr("高");
			*/	
			//订单统计 我的购买订单
			List<Object[]> orderCount = userDao.getOrderCounts(user.getWeiID());
			if(orderCount!=null && orderCount.size()>0)
			{
				for(Object[] ob:orderCount)
				{
					Integer count = ob[0]==null?0:Integer.parseInt(ob[0].toString());
					Integer state = ob[1]==null?0:Integer.parseInt(ob[1].toString());
					//OrderStatus ostatu = OrderStatus.valueOf(state.toString());
					switch(state)
					{
					case 0: userInfoCount.setNeedpaycount(count); break;
					case 1:userInfoCount.setNeedsendcount(count); break;
					case 2:userInfoCount.setNeedacceptcount(count); break;
					case 3:userInfoCount.setNeedsaycount(count); break;
					default:break;
					}
				}
			}
			
			//今天订单
/*			Integer todayCount = userDao.getTodayOrderCount(user.getWeiID());
			userInfoCount.setTodayordercout(todayCount);*/
			
			//商品数量统计  销售中 已下架 草稿箱
			List<Object[]> productCount = userDao.getProductCounts(user.getWeiID());
			if(productCount!=null && productCount.size()>0)
			{
				for(Object[] ob:productCount){
					Integer count = ob[0]==null?0:Integer.parseInt(ob[0].toString());
					Integer state = ob[1]==null?0:Integer.parseInt(ob[1].toString());
					switch(state)
					{
					case 0:userInfoCount.setOffcount(count); break;
					case 1:userInfoCount.setOncount(count); break;
					default:break;
					}
				}
			}
			int graftCount = userDao.getProductCount(user.getWeiID(), Short.parseShort(ProductStatusEnum.OutLine.toString()));
			userInfoCount.setGraftCount(graftCount);
			
			//昨天新增分销商
/*			Integer yeserdayCount = (Integer) RedisUtil.getObject(ConstantParam.IBS_USERYESERDAYPERSONCOUNT+user.getWeiID());
			if(yeserdayCount==null)
			{
				yeserdayCount=userDao.getYeserDayCount(user.getWeiID());
				RedisUtil.setObject(ConstantParam.IBS_USERYESERDAYPERSONCOUNT+user.getWeiID(), yeserdayCount, 36000);
			}*/
			//分销商总量
			/*Integer totalCount = (Integer) RedisUtil.getObject(ConstantParam.IBS_USERTOTALCOUNT+user.getWeiID());
			if(totalCount==null)
			{
				totalCount = userDao.getTotalCount(user.getWeiID());
				RedisUtil.setObject(ConstantParam.IBS_USERTOTALCOUNT+user.getWeiID(), totalCount, 36000);
			}
			userInfoCount.setYaddedcount(yeserdayCount);
			userInfoCount.setTotalcount(totalCount);*/
						
			//我的销售订单  待付款 待发货 待收货 售后中  只有供应商才会有
			if( (user.getYunS()!=null && user.getYunS() ==1) || (user.getBatchS() !=null && user.getBatchS() ==1) || (user.getPph() !=null && user.getPph() ==1)  || (user.getPth() !=null && user.getPth() ==1)){
				List<Object[]> supplyPCount = userDao.getSupplyOrderCount(user.getWeiID());
				if(supplyPCount!=null && supplyPCount.size()>0)
				{
					for(Object[] spc:supplyPCount){
						Integer count = spc[0]==null?0:Integer.parseInt(spc[0].toString());
						Integer state = spc[1]==null?0:Integer.parseInt(spc[1].toString());
						switch(state)
						{
						case 0:userInfoCount.setWaitPayCount(count);break;
						case 1:userInfoCount.setWaitSendCount(count);break;
						case 2:userInfoCount.setWaitAcceptCount(count);break;
						case 5:userInfoCount.setCustomeringCount(count);break;
						default:break;
						}
					}
				}
			}
			//上游供应商数量  
			Integer attentionCount =  (Integer) RedisUtil.getObject(ConstantParam.ATTENTIONCOUNT+user.getWeiID());
			if(attentionCount ==null){
				attentionCount = userDao.getAttentionCount(user.getWeiID());
				RedisUtil.setObject(ConstantParam.ATTENTIONCOUNT+user.getWeiID(), attentionCount, 36000);
			}
			userInfoCount.setAttedtionCount(attentionCount);
			
			//下游分销数量
			Integer attentionedCount =  (Integer) RedisUtil.getObject(ConstantParam.ATTENTIONEDCOUNT+user.getWeiID());
			if(attentionedCount ==null){
				attentionedCount = userDao.getAtteneionedCount(user.getWeiID());
				RedisUtil.setObject(ConstantParam.ATTENTIONEDCOUNT+user.getWeiID(), attentionedCount, 36000);
			}
			userInfoCount.setAttedtionedCount(attentionedCount);
			//12-17新增  
			if(user.getPth()==1||user.getPph()==1){
			    userInfoCount.setWaitVerifyCount(myProductService.getToHandleCount(user.getWeiID()));//待审核数量（平台号可见）
			    userInfoCount.setMySupplyCount(childrenDAO.getChildrenSupplyerTotalAmount(user.getWeiID(),-1,null));//我的下级供应商数量（平台号可见）
			    userInfoCount.setAgentCount(agentService.getAgentByStatus(user.getWeiID(),SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal));//代理商数量（品牌号，平台号可见）
			    userInfoCount.setGroundCount(agentService.getAgentByStatus(user.getWeiID(),SupplyChannelTypeEnum.ground,AgentStatusEnum.Normal));//落地店数量（品牌号，平台号可见）
			}  
			    
			//缓存整个统计对象
			RedisUtil.setObject(ConstantParam.IBS_USERTONGJITOTAL+user.getWeiID(), userInfoCount, 600);	
//			try {
//				userInfoCount.setNoticemsg(getNoticeMsg());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			
		}
		
		return userInfoCount;
	}
	
	
	private NoticeMsg getNoticeMsg(){
		NoticeMsg notic = new NoticeMsg();
		List<MsgKeyValue> glMsg = new ArrayList<MsgKeyValue>();
		List<MsgKeyValue> imgMsg = new ArrayList<MsgKeyValue>();
		Connection conn = DB.createConn();
		String sql = "select Title,ImageUrl,UrlStr from W_InfoNotice where Statu=1 order by Sort";
		PreparedStatement ps = DB.prepare(conn, sql);

		try {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			List<DataRow> row = new ArrayList<DataRow>();// 表所有行集合
			List<DataColumn> col = null;// 行所有列集合
			DataRow r = null; // 单独一行
			DataColumn c = null;// 单独一列
			// 此处开始循环读数据，每次往表格中插入一行记录
			while (rs.next()) {
				// 初始化行集合，
				// 初始化列集合
				MsgKeyValue tempkv = new MsgKeyValue();
				// 此处开始列循环，每次向一行对象插入一列
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = rs.getObject(columnName);
					
					switch(columnName)
					{
						case "Title":tempkv.setTitle(value==null?"":value.toString().trim());break;
						case "ImageUrl":tempkv.setImgstr(ImgDomain.GetFullImgUrl(value==null?"":value.toString().trim()));break;
						case "UrlStr":tempkv.setRefstr(value==null?"":value.toString().trim());break;
						default:break;
					}
				}

				
				if(glMsg.size()==10&&imgMsg.size()==5)
					break;
				
                if(tempkv.getImgstr().equals(""))
                {
                	if(glMsg.size()<10)
                	   glMsg.add(tempkv);
                }	
                else
                {
                	if(imgMsg.size()<5)
                	   imgMsg.add(tempkv);
                }
                	
			}
			// 得到数据表
			notic.setGlmsg(glMsg);
			notic.setImgmsg(imgMsg);
			rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(ps);
			DB.close(conn);
		}
		return notic;
	}


	@Override
	public ShopInfoVO getUShopInfo(LoginUser user) {
 		ShopInfoVO shopinfo = new ShopInfoVO();
		UShopInfo uShopInfo = userDao.getUserInfo(user.getWeiID());
		UBatchSupplyer uBatchSupplyer = userDao.getBatchSupplyerInfo(user.getWeiID());
		if (uShopInfo == null) {
			uShopInfo = new UShopInfo();
			uShopInfo.setWeiId(user.getWeiID());
			//shopinfo为空则取批发号信息
			if (uBatchSupplyer != null) {
				uShopInfo.setShopName(uBatchSupplyer.getShopName());
				uShopInfo.setShopImg(uBatchSupplyer.getShopPic());
				uShopInfo.setShopBusContent(uBatchSupplyer.getBusContent());
				shopinfo.setShowBatchSupplyer(1);
				shopinfo.setProvince(uBatchSupplyer.getProvince());
				shopinfo.setCity(uBatchSupplyer.getCity());
				shopinfo.setDistrict(uBatchSupplyer.getDistrict());
				shopinfo.setShopPosition(uBatchSupplyer.getShopPosition());
				TBatchMarket tBatchMarket = userDao.getTBatchMarket(uBatchSupplyer.getBmid());
				if (tBatchMarket != null) {
					shopinfo.setMarketName(tBatchMarket.getName());
				}
			} else {
				//批发号信息为空则取供应商信息
				USupplyer uSupplyer = userDao.getSupplyerInfo(user.getWeiID());
				if (uSupplyer != null) {
					uShopInfo.setShopName(uSupplyer.getCompanyName());
					uShopInfo.setShopImg(uSupplyer.getSupplierLogo());
				} else {
					//供应商信息为空则去微店用户信息
					UWeiSeller uWeiSeller = userDao.getWeiSellerInfo(user.getWeiID());
					if (uWeiSeller != null) {
						uShopInfo.setShopName(uWeiSeller.getWeiName());
						uShopInfo.setShopImg(uWeiSeller.getImages());
					}
				}
			}
			//图片为空则设置默认
			if (StringUtils.isEmpty(uShopInfo.getShopImg())) {
				uShopInfo.setShopImg(Constants.DEFAULT_AVATAR);
			}
			//保存信息到shopinfo表
			userDao.saveShopInfo(uShopInfo);
		} else if (uBatchSupplyer != null) {
			shopinfo.setShowBatchSupplyer(1);
			shopinfo.setProvince(uBatchSupplyer.getProvince());
			shopinfo.setCity(uBatchSupplyer.getCity());
			shopinfo.setDistrict(uBatchSupplyer.getDistrict());
			shopinfo.setShopPosition(uBatchSupplyer.getShopPosition());
			TBatchMarket tBatchMarket = userDao.getTBatchMarket(uBatchSupplyer.getBmid());
			if (tBatchMarket != null) {
				shopinfo.setMarketName(tBatchMarket.getName());
			}
		}
		shopinfo.setWeiId(uShopInfo.getWeiId());
		shopinfo.setShopName(uShopInfo.getShopName());
		shopinfo.setShopImg(uShopInfo.getShopImg());
		shopinfo.setShopBusContent(uShopInfo.getShopBusContent());
		return shopinfo;
	}


	@Override
	public int updateShopInfo(UShopInfo shopInfo) {
		int result = -1;
		try {
			userDao.updateShopInfo(shopInfo);
			result = 0;
		} catch (Exception e) {
			logger.equals(e);
		}
		return result;
	}


	@Override
	public SettingsVO findSettings(Long weiId) {
		SettingsVO settings = new SettingsVO();
		UWeiSeller weiSeller = userDao.getWeiSellerInfo(weiId);
		if (weiSeller != null) {
			if (StringUtils.isNotEmpty(weiSeller.getPassWord())) {
				settings.setLoginPwd(1);
			} 
			if (Constants.MOBILE_BIND.equals(weiSeller.getMobileIsBind()+"")) {
				settings.setBindPhone(1);
				if (weiSeller.getMobilePhone() != null && weiSeller.getMobilePhone().length() == 11) {
					settings.setPhoneStr(weiSeller.getMobilePhone().substring(0,3)+"*******"+weiSeller.getMobilePhone().substring(10));
				}
			}
		}
		UWallet wallet = userDao.getWallet(weiId);
		if (wallet != null) {
			if (StringUtils.isNotEmpty(wallet.getRealName())) {
				settings.setRealVerify(1);
				StringBuffer xing = new StringBuffer();
				for (int i = 1; i < wallet.getRealName().length(); i++) {
					xing.append("*");
				}
				settings.setRealName(xing.toString()+wallet.getRealName().substring(wallet.getRealName().length()-1));
			}
			if (StringUtils.isNotEmpty(wallet.getPayPassword())) {
				settings.setPayPwd(1);
			}
		}
		return settings;
	}


	@Override
	public UWeiSeller getUWeiSeller(Long weiId) {
		return userDao.getWeiSellerInfo(weiId);
	}


	@Override
	public int modifyUWeiSeller(UWeiSeller weiSeller) {
		int result = -1;
		try {
			userDao.updateUWeiSeller(weiSeller);
			result = 0;
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
	/**
     * 发送手机验证码
     */
    @Override
    public MsgResult insertVerification(long weiid,String phone)
    {
        return null;
    }

    /**
     * 判断手机号是否注册
     */
    @Override
    public boolean getUserByPhone(String phone)
    {
        boolean isbind = userDao.getUserPhoneisBind(phone);
        return isbind;
    }

    @Override
    public boolean getSendCeiling(long weiid,short itype)
    {
        boolean bool = userDao.getPhoneSendSMS(weiid,itype);
        return bool;
    }

    @Override
    public boolean insertSendSMS(long weiid,String phone,String ip,short itype)
    {
        String code = String.valueOf(Math.random()).substring(2,8);// 随机生成验证码
        boolean bool = userDao.insertTVerificationID(code,weiid,itype,phone,ip);
        if(!bool)
        {
            return false;
        }
        boolean issend = VerifyCode.insertVCode(weiid,phone,code,itype);
        return issend;
    }

    @Override
    public MsgResult getPhoneVerify(Long weiid,String phone,String yzm)
    {
        MsgResult mr = new MsgResult();
        String tiket = weiid.toString() + "-" + phone + "-" + VerifyCodeType.bind.toString();
        Object object = RedisUtil.getObject(tiket);
        // 判断redis里面是否为空
        if(object != null)
        {
            PhoneVerify pv = (PhoneVerify) object;
            if(pv == null || pv.getCount() > 2)
            {
                mr.setState(-1);
                mr.setMsg("验证码已失效，请重新获取");
                return mr;
            }
            // 验证码是否正确
            if(!yzm.equals(pv.getCode()))
            {
                // 验证次数加1
                int count = pv.getCount() + 1;
                if(count == 3)
                {
                    RedisUtil.delete(tiket);// 删除
                    mr.setState(-1);
                    mr.setMsg("验证码不正确，验证码已失效，请重新获取");
                    return mr;
                }
                pv.setCount(count);
                RedisUtil.setObject(tiket,pv,300);
                mr.setState(-1);
                mr.setMsg("验证码不正确");
                return mr;
            }
            else
            {
                // 验证正确
                UWeiSeller user = userDao.getWeiSellerInfo(weiid);
                user.setMobilePhone(phone);
                user.setMobileIsBind(Short.parseShort(MobileBindEnum.Bind.toString()));
                userDao.updateUWeiSeller(user);
                userDao.updateTVerificationId(weiid,yzm);
                mr.setState(1);
                mr.setMsg("手机绑定成功");
                return mr;
            }
        }
        else
        {
            mr.setState(-1);
            mr.setMsg("验证码已失效，请重新获取");
            return mr;
        }
    }

    @Override
    public MsgResult insertunBindPhone(Long weiid,String ip)
    {
        UWeiSeller user = userDao.getWeiSellerInfo(weiid);
        MsgResult mr = new MsgResult();
        if(user == null)
        {
            mr.setState(-1);
            mr.setMsg("请登录");
            return mr;
        }
        if(user.getMobileIsBind() != Short.parseShort(MobileBindEnum.Bind.toString()))
        {
            mr.setState(-1);
            mr.setMsg("您没有绑定手机号码");
            return mr;
        }
        String phone = user.getMobilePhone();
        boolean issend = insertSendSMS(weiid,phone,ip,Short.parseShort(VerifyCodeType.unbundl.toString()));// 发送短信
        if(issend)
        {
            mr.setState(1);
            mr.setMsg("发送成功");
            return mr;
        }
        mr.setState(-1);
        mr.setMsg("发送验证码失败");
        return mr;
    }

    @Override
    public MsgResult updateUnBind(Long weiid,String yzm)
    {
        MsgResult mr = new MsgResult();
        UWeiSeller user = userDao.getWeiSellerInfo(weiid);
        if(user == null)
        {
            mr.setState(-1);
            mr.setMsg("请登录");
            return mr;
        }
        if(user.getMobileIsBind() != Short.parseShort(MobileBindEnum.Bind.toString()))
        {
            mr.setState(-1);
            mr.setMsg("您没有绑定手机号码");
            return mr;
        }
        String phone = user.getMobilePhone();
        String key = weiid.toString() + "-" + phone + "-" + VerifyCodeType.unbundl.toString();
        Object obj = RedisUtil.getObject(key);
        if(obj == null)
        {
            mr.setState(-1);
            mr.setMsg("验证码已失效，请重新获取");
            return mr;
        }
        PhoneVerify pv = (PhoneVerify) obj;
        // 判断验证码
        if(!yzm.equals(pv.getCode()))
        {
            // 验证次数加1
            int count = pv.getCount() + 1;
            if(count == 3)
            {
                RedisUtil.delete(key);// 删除
                mr.setState(-1);
                mr.setMsg("验证码不正确，验证码已失效，请重新获取");
                return mr;
            }
            pv.setCount(count);
            RedisUtil.setObject(key,pv,300);
            mr.setState(-1);
            mr.setMsg("验证码不正确");
            return mr;
        }
        else
        {
            // 验证正确
            user.setMobileIsBind(Short.parseShort(MobileBindEnum.Nobind.toString()));
            userDao.updateUWeiSeller(user);
            userDao.updateTVerificationId(weiid,yzm);
            mr.setState(1);
            mr.setMsg("手机绑定成功");
            return mr;
        }
    }


	@Override
	public com.okwei.bean.domain.UWallet UWallet(Long weiId) {
		return userDao.getWallet(weiId);
	}


	@Override
	public int modifyUWallet(UWallet wallet) {
		int result = -1;
		try {
			userDao.updateUWallet(wallet);;
			result = 0;
		} catch (Exception e) {
			logger.equals(e);
		}
		return result;
	}


	@Override
	public MsgResult resetPwdByVerify(Long weiid, String phone, String yzm,
			String verifyCodeType,String pwd) {
        MsgResult mr = new MsgResult();
        String tiket = weiid.toString() + "-" + phone + "-" + verifyCodeType;
        Object object = RedisUtil.getObject(tiket);
        // 判断redis里面是否为空
        if(object != null)
        {
            PhoneVerify pv = (PhoneVerify) object;
            if(pv == null || pv.getCount() > 2)
            {
                mr.setState(-1);
                mr.setMsg("验证码已失效，请重新获取");
                return mr;
            }
            // 验证码是否正确
            if(!yzm.equals(pv.getCode()))
            {
                // 验证次数加1
                int count = pv.getCount() + 1;
                if(count == 3)
                {
                    RedisUtil.delete(tiket);// 删除
                    mr.setState(-1);
                    mr.setMsg("验证码不正确，验证码已失效，请重新获取");
                    return mr;
                }
                pv.setCount(count);
                RedisUtil.setObject(tiket,pv,300);
                mr.setState(-1);
                mr.setMsg("验证码不正确");
                return mr;
            }
            else
            {
                // 验证正确
            	if (VerifyCodeType.updatepassword.toString().equals(verifyCodeType)) {//修改登陆密码
            		UWeiSeller user = userDao.getWeiSellerInfo(weiid);
            		user.setPassWord(MD5Util.md5s(pwd));
            		userDao.updateUWeiSeller(user);
            		userDao.updateTVerificationId(weiid,yzm);
            		mr.setState(1);
//            		mr.setMsg("密码设置成功");
				} else if (VerifyCodeType.updatePayPassword.toString().equals(verifyCodeType)) {//修改支付密码
					UWallet wallet = userDao.getWallet(weiid);
					wallet.setPayPassword(MD5Util.md5s(pwd));
					userDao.updateUWallet(wallet);
					userDao.updateTVerificationId(weiid,yzm);
					mr.setState(1);
				}
                return mr;
            }
        }
        else
        {
            mr.setState(-1);
            mr.setMsg("验证码已失效，请重新获取");
            return mr;
        }
    }


    @Override
    public AccountVO getAccountVO(Long weiid)
    {
        AccountVO account = new AccountVO(); 
        account.setBindQQ(false);
        account.setBindWX(false);
        List<UOtherLogin> oList = userDao.getuolListByWeiId(weiid);
        if(oList==null||oList.size()<=0)
        {
            return account;
        }
        for(UOtherLogin ol:oList)
        {
            if(Short.parseShort(LoginType.qq.toString()) == ol.getPortType())
            {
                account.setBindQQ(true);
            }
            else if(Short.parseShort(LoginType.weixin.toString()) == ol.getPortType())
            {
                account.setBindWX(true);
            }
        }
        return account;
    }


    @Override
    public void updateUnLoginBind(Long weiid,short ltype)
    {
        userDao.updateUOtherLogin(weiid,ltype);
    }
}
