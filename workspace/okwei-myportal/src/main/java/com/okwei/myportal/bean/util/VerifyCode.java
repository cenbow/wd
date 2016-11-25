package com.okwei.myportal.bean.util;

import com.okwei.myportal.bean.enums.VerifyCodeType;
import com.okwei.myportal.bean.vo.PhoneVerify;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

public class VerifyCode
{
    /**
     * 插入各个业务的验证码
     * 
     * @param mobile
     * @param vtype
     * @return
     */
    public static String insertVerifyCode(String mobile,VerifyCodeType vtype)
    {
        String verifyCode = String.valueOf(Math.random()).substring(2,8);
        String tiket = mobile;
        if(vtype != null)
        {
            tiket += "-" + vtype.toString();
        }
        RedisUtil.setObject(tiket,verifyCode,300);
        // RedisUtil.SetTiketObject(tiket,verifyCode,300);// 五分钟内有效
        String content = "微店网验证码：" + verifyCode + "。请勿将验证码告知他人并确认是本人操作[微店网]";
        String lr = SendSMSByMobile.sendSMS(mobile,content);
        long sendResult = ParseHelper.toLong(lr);
        if(sendResult > 0)
        {
            return verifyCode;
        }
        else
        {
            return "-1";
        }
    }

    /**
     * 匹配各个业务的验证码，匹配正确后删除
     * 
     * @param mobile
     * @param vtype
     * @param verifyCode
     * @return
     */
    public static boolean checkVerifyCode(String mobile,VerifyCodeType vtype,String verifyCode)
    {
        String tiket = mobile;
        if(vtype != null)
        {
            tiket += "-" + vtype.toString();
        }
        // String comparevf = (String) RedisUtil.GetObjectByTiket(tiket);
        String comparevf = RedisUtil.getString(tiket);
        if(comparevf == null || !comparevf.equals(verifyCode))
        {
            return false;
        }
        else
            RedisUtil.delete(tiket);// .removeObject(tiket);
        return true;
    }

    /**
     * 发送短信
     * 
     * @param mobile
     * @param vtype
     *            短信的类型（ 枚举VerifyCodeType）
     * @return
     */
    public static Boolean insertVCode(String mobile,VerifyCodeType vtype)
    {
        String verifyCode = String.valueOf(Math.random()).substring(2,8);
        String tiket = mobile;
        if(vtype != null)
        {
            tiket += "-" + vtype.toString();
        }
        RedisUtil.setObject(tiket,verifyCode,300);
        // RedisUtil.SetTiketObject(tiket,verifyCode,300);// 五分钟内有效
        String content = "微店网验证码：" + verifyCode + "。请勿将验证码告知他人并确认是本人操作[微店网]";
        String lr = SendSMSByMobile.sendSMS(mobile,content);
        long sendResult = ParseHelper.toLong(lr);
        if(sendResult > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 发送短信
     * 
     * @param weiid
     *            微店号
     * @param mobile
     *            手机号
     * @param vtype
     *            发送类型
     * @return
     */
    public static Boolean insertVCode(Long weiid,String mobile,String code,Short itype)
    {
        String tiket = weiid.toString() + "-" + mobile + "-" + itype.toString();
        PhoneVerify pv = new PhoneVerify();
        pv.setCode(code);
        pv.setPhone(mobile);
        pv.setCount(0);
        RedisUtil.setObject(tiket,pv,300);
        // RedisUtil.SetTiketObject(tiket,verifyCode,300);// 五分钟内有效
        String content = "微店网验证码：" + code + "。请勿将验证码告知他人并确认是本人操作[微店网]";
        String lr = SendSMSByMobile.sendSMS(mobile,content);
        long sendResult = ParseHelper.toLong(lr);
        if(sendResult > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
