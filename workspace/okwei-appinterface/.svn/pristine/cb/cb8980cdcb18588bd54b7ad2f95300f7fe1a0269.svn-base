package com.okwei.appinterface.util;

import com.okwei.appinterface.bean.vo.LoginPublicResult;
import com.okwei.appinterface.bean.vo.LoginReturnMsg;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.util.RedisUtil;

public class UserValidate
{

    /**
     * 根据 缓存票据 获取登陆信息（旧版）
     * 
     * @param tiket
     * @return
     */
    public static UWeiSellerLoginLog IsLoginReturnUser(String tiket)
    {
		if (!"".equals(tiket) && tiket != null) {
			UWeiSellerLoginLog seller = null;
			try {
				tiket = tiket + "-U";
				seller = (UWeiSellerLoginLog) RedisUtil.getObject(tiket);
				return seller;
			} catch (Exception e) {
			}
		}
		return null;
    }
	/**
     * 登陆获取用户登陆信息（改版）
     * @param tiket
     * @return
     */
    public static LoginReturnMsg IsLoginReturnUserMsg(String tiket)
    {
		if (!"".equals(tiket) && tiket != null) {
			LoginReturnMsg seller = null;
			try {
				//Object aaa= RedisUtil.GetObjectByTiket(tiket);
				seller = (LoginReturnMsg) RedisUtil.getObject(tiket);
				return seller;
			} catch (Exception e) {}
		}
        return null;
    }

    /**
     * 获取第三方登陆信息
     * 
     * @param tiket
     * @return
     */
    public static LoginPublicResult isLoginPublicResult(String tiket)
    {
		if (!"".equals(tiket) && tiket != null) {
			LoginPublicResult seller = null;
			try {
				seller = (LoginPublicResult) RedisUtil.getObject(tiket);
			} catch (Exception e) {
				return null;
			}
			if (seller != null) {
				return seller;
			}
		}
		return null;
    }

    /**
     * 登陆 心跳（延长tiket过期时间）
     * 
     * @param tiket
     * @return
     */
    public static boolean heartbeat(String tiket)
    {
		if (!"".equals(tiket) && tiket != null) {
			LoginReturnMsg userLog = IsLoginReturnUserMsg(tiket);
			UWeiSellerLoginLog userLog2 = IsLoginReturnUser(tiket);
			if (userLog != null) {
				RedisUtil.setObject(tiket, userLog, 86400);
			} 
			if(userLog2!=null)
			{
				String tiket2=tiket+"-U";
				RedisUtil.setObject(tiket2, userLog2, 86400);
			}
			return true;
		}
        return false;
    }
    
    /**
     * 心跳 UWeiSellerLoginLog
     * @param tiket
     * @return
     */
//    public static boolean heartbeat_old(String tiket)
//    {
//		if (!"".equals(tiket) && tiket != null) {
//			UWeiSellerLoginLog userLog = IsLoginReturnUser(tiket);
//			if (userLog != null) {
//				RedisUtil.SetTiketObject(tiket, userLog, 86400);
//				return true;
//			}
//		}
//         return false;
//    }

    /**
     * 退出登录，清楚redis缓存
     * 
     * @param tiket
     * @return
     */
    public static boolean loginOut(String tiket)
    {
		if (!"".equals(tiket) && tiket != null) {
			LoginReturnMsg userLog = IsLoginReturnUserMsg(tiket);
			UWeiSellerLoginLog userLog2 = IsLoginReturnUser(tiket);
			if (userLog != null) {
				RedisUtil.delete(tiket);
			}
			if (userLog2 != null) {
				RedisUtil.delete(tiket + "-U");
			}
			return true;
		}
		return false;
    }

}
