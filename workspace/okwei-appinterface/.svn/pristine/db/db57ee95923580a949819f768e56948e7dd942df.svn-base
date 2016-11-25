package com.okwei.appinterface.web;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.bean.enums.LoginTypeEnum;
import com.okwei.bean.vo.LoginPublicResult;
import com.okwei.bean.vo.LoginResult;
import com.okwei.bean.vo.LoginReturnMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.LoginUserExtend;
import com.okwei.common.JsonUtil;
import com.okwei.service.user.ILoginService;
import com.okwei.util.AES;
import com.okwei.util.DES;
import com.okwei.util.MD5Encrypt;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;
import com.okwei.util.SendPushMessage;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * APP登陆接口　
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends SSOController
{
    @Autowired
    HttpServletRequest request;

    @Autowired
    ILoginService loginService;

    /**
     * 第三方注册
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerOthers")
    public String registerOthers(String mobile,String vcode,String pwd,String upweiid,String tokent)
    {
        ReturnModel rm = new ReturnModel();
        Log logger = LogFactory.getLog("OtherLogin");
        logger.error("OtherLogintokent111:" + tokent);

        long supweiid = 1;

        try
        {
            long supweiidl = ParseHelper.toLong(upweiid);

            if(supweiidl > 1)
            {
                supweiid = supweiidl;
            }
        }
        catch(Exception ex)
        {
            logger.error(ex.toString());
        }

        rm = loginService.appOtherRegist(mobile,vcode,pwd,supweiid,tokent);

        return JsonUtil.objectToJsonStrWithUpCaseForReturnModel(rm);
        // return JsonStr(rm).replace("Statu","statu");
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(String mobile,String vcode,String pwd,Long upweiid,String weiname)
    {
        ReturnModel rm = new ReturnModel();
        long supweiid = 1;
        if(upweiid != null && upweiid > 1)
        {
            supweiid = upweiid;
        }
        rm = loginService.appRegist(mobile,vcode,pwd,supweiid,weiname);
        return JsonUtil.objectToJsonStrWithUpCaseForReturnModel(rm);
    }

    /**
     * 第三方绑定
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginBind")
    public String loginBind(Integer itype,String phone,String psw,String tokent,String version)
    {
        // version 暂时没有用
        ReturnModel rm = new ReturnModel();
        if(itype != null && itype == 1001)
        {
            // 登陆绑定
            if(ObjectUtil.isEmpty(phone) || ObjectUtil.isEmpty(psw) || ObjectUtil.isEmpty(tokent))
            {
                rm.setStatu(ReturnStatus.DataError);// 返回状态
                rm.setBasemodle(1);
                rm.setStatusreson("数据有误");// 状态描述
            }
            else
            {
                LoginPublicResult lpr = (LoginPublicResult) RedisUtil.getObject(tokent);// 缓存获取数据
                if(lpr != null)
                {
                    LoginUserExtend lue = new LoginUserExtend();
                    lue = loginService.checkLoginByBind(lpr,phone,psw);
                    if(lue != null && lue.getStatu() == 1)
                    {
                        rm.setStatu(ReturnStatus.Success);// 返回状态
                        rm.setStatusreson("成功");// 状态描述
                        rm.setBasemodle(lue);
                    }
                    else if(lue != null && lue.getStatu() == 2)
                    {
                        rm.setStatu(ReturnStatus.Success);// 返回状态
                        rm.setStatusreson("成功");// 状态描述
                        rm.setBasemodle(lue);
                    }
                    else
                    {
                        rm.setStatu(ReturnStatus.DataError);// 返回状态
                        rm.setStatusreson(lue.getMessage());// 状态描述
                        rm.setBasemodle(lue);
                    }
                }
                else
                {
                    rm.setStatu(ReturnStatus.DataError);// 返回状态
                    rm.setStatusreson("tokent过期");// 状态描述
                }
            }
        }
        else
        {
            // 手机验证
            if(!ObjectUtil.isEmpty(phone) && isphone2(phone))
            {
                Long b = loginService.getWeiSellerByPhone(phone);
                if(b != null && b > 0)
                {
                    rm.setStatu(ReturnStatus.Success);// 返回状态
                    rm.setBasemodle(1);
                    rm.setStatusreson("已绑定");// 状态描述
                }
                else
                {
                    rm.setStatu(ReturnStatus.Success);// 返回状态
                    rm.setBasemodle(-1);
                    rm.setStatusreson("未绑定");// 状态描述
                }
            }
            else
            {
                rm.setStatu(ReturnStatus.DataError);// 返回状态
                rm.setBasemodle(-2);
                rm.setStatusreson("手机号不合法");// 状态描述
            }
        }

        return JsonUtil.objectToJsonStrWithUpCaseForReturnModel(rm);
        // return JsonStr(rm).replace("Statu","statu");
    }

    /**
     * 第三方登陆,两种成功情况，一登陆成功，二登陆成功没有绑定微店号
     * 
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/otherLogin")
    public String otherLogin(String headImg,Short loginType,String nickName,String openID,String version,String push_token) throws MapperException
    {
        // version版本号，暂时不用
        ReturnModel rm = new ReturnModel();
        if(ObjectUtil.isEmpty(openID))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("openID不能为空");
            return JsonUtil.objectToJsonStr(rm);
        }
        if(ObjectUtil.isEmpty(loginType) || loginType < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("登陆类型不能为空");
            return JsonUtil.objectToJsonStr(rm);
        }
        LoginPublicResult lpr = new LoginPublicResult();
        lpr.setHeadImg(headImg);
        lpr.setLoginType(loginType);
        lpr.setNickName(nickName);
        lpr.setOpenID(openID);
        LoginUserExtend lue = new LoginUserExtend();// 返回的数据
        lue = loginService.checkLoginByThird(lpr);
        if(lue != null && lue.getStatu() == 1)
        {
            rm.setStatu(ReturnStatus.Success);// 返回状态
            rm.setStatusreson("成功");// 状态描述
            rm.setBasemodle(lue);
            // 缓存登陆信息
            UWeiSellerLoginLog log = new UWeiSellerLoginLog();
            log.setLoginIp(getIpAddr());
            log.setLoginTime(new Date());
            log.setWeiId(lue.getLrm().getWeiId());
            log.setTiket(lue.getLrm().getTiket());
            log.setLoginType(Short.parseShort(LoginTypeEnum.APPLogin.toString()));
            log.setTerrminateType(Short.parseShort(LoginTypeEnum.APPLogin.toString()));
            loginService.insertUWeiSellerLoginLog(log);
            sendBingDevice(lue.getLrm().getWeiId() == null ? "" : lue.getLrm().getWeiId().toString(),push_token,"123456");
        }
        else if(lue != null && lue.getStatu() == 2)
        {
            rm.setStatu(ReturnStatus.Success);// 返回状态
            rm.setStatusreson("成功");// 状态描述
            rm.setBasemodle(lue);
        }
        else
        {
            rm.setStatu(ReturnStatus.DataError);// 返回状态
            rm.setStatusreson(lue.getMessage());// 状态描述
            rm.setBasemodle(lue);
        }
        return JsonUtil.objectToJsonStrWithUpCaseForReturnModel(rm);
        // return JsonUtil.objectToJsonStr(rm).replace("Statu","statu");
    }

    /**
     * 退出登陆
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut")
    public String loginOut()
    {
        String tiket = getTiket();
        if(!ObjectUtil.isEmpty(tiket))
        {
            RedisUtil.delete(tiket + "_IBS");
            RedisUtil.delete(tiket + "_SUB");
            RedisUtil.delete(tiket);
        }
        ReturnModel rm = new ReturnModel();
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return JsonStr(rm);
    }

    /**
     * 修改用户信息
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUserInfo")
    public String editUserInfo(String shopname,String img,String shopBussiness)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUser();
        if(user == null)
        {
            return loginError();// 登陆错误
        }
        UShopInfo info = new UShopInfo();
        info.setWeiId(user.getWeiID());
        if(!ObjectUtil.isEmpty(shopname))
        {
            info.setShopName(shopname);
        }
        if(!ObjectUtil.isEmpty(shopBussiness))
        {
            info.setShopBusContent(shopBussiness);
        }
        if(!ObjectUtil.isEmpty(img))
        {
            info.setShopImg(img);
        }
        if(loginService.updateShopInfo(info))
        {
            rm = loginService.appLoginReturnMsg(getTiket());
        }
        else
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("数据处理错误");
        }
        return JsonStr(rm);
    }

    /**
     * 登陆
     * 
     * @param request
     * @param wid
     * @param pwd
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/index")
    public String index(String wid,String pwd,String push_token) throws MapperException
    {
        ReturnModel rm = new ReturnModel();
        String ip = getIpAddr();// 获取IP
        rm = checkParam(wid,pwd,ip);// 校验参数
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return JsonUtil.objectToJsonStr(rm);// 校验失败
        }
        String md5Pwd = MD5Encrypt.encrypt(DES.decrypt(pwd,"")).toUpperCase();// 转换成MD5
        String aesPwd = AES.aesEncrypt(DES.decrypt(pwd,"")); // AES加密
        // 登录
        LoginResult lr = loginService.getUWeiSellerByLogin(wid,md5Pwd,aesPwd,ip,LoginTypeEnum.APPLogin);
        if(lr.getStatus() != 1)
        {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson(lr.getMessage());
            return JsonUtil.objectToJsonStr(rm);
        }
        rm = loginService.appLoginReturnMsg(lr.getMessage());
        sendBingDevice(wid,push_token,"123456");
        return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 校验参数
     * 
     * @param wid
     *            微店号
     * @param pwd
     *            微店密码（加密的）
     * @param ip
     *            来源IP
     * @return
     */
    public ReturnModel checkParam(String wid,String pwd,String ip)
    {
        ReturnModel rm = new ReturnModel();
        // 用户名不能为空
        if(ObjectUtil.isEmpty(wid))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("用户名不能为空");
            return rm;
        }
        // 密码不能为空
        if(ObjectUtil.isEmpty(pwd))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("密码不能为空");
            return rm;
        }
        // 验证密码是否可以解密
        String md5Pwd = DES.decrypt(pwd,"");
        if(ObjectUtil.isEmpty(md5Pwd))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("密码输入有误验证失败");
            return rm;
        }
        md5Pwd = MD5Encrypt.encrypt(md5Pwd).toUpperCase();
        // 判断用户名是否正确
        if(wid.indexOf("_") > 1)
        {
            // 子帐号登陆
            String[] users = wid.split("_");
            if(users.length != 2 || !regNum(users[0]) || !regNum(users[1]))
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("子帐号输入有误");
                return rm;
            }
        }
        else
        {
            // 微店号登陆
            if(!regNum(wid))
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("微店号输入有误");
                return rm;
            }
        }
        // 验证是否可以登陆

        String weiCount = RedisUtil.getString("loginWeiNo_" + wid);
        int weiNoLoginCount = (weiCount == null ? 0 : ParseHelper.toInt(weiCount));// 微店号登陆次数
        String ipCount = RedisUtil.getString("loginIP_" + ip);
        int ipLoginCount = (ipCount == null ? 0 : ParseHelper.toInt(ipCount));// IP登陆次数
        // 规则：同一微店号5次，同一IP100次。需要验证验证码
        if(weiNoLoginCount >= 5)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("此账号登陆次数过多，五分钟后再试试");
            return rm;
        }
        if(ipLoginCount >= 100)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("此IP登陆次数过多，五分钟后再试试");
            return rm;
        }
        // 记录缓存，十分钟
        weiNoLoginCount++;
        ipLoginCount++;
        RedisUtil.setObject("loginWeiNo_" + wid,weiNoLoginCount,300);
        RedisUtil.setObject("loginIP_" + ip,ipLoginCount,300);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("校验成功");
        return rm;
    }

    /***
     * 判断字符串是否是数字
     * 
     * @param str
     *            要判断的字符串
     * @return
     */
    public boolean regNum(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 获取访问用户的客户端IP（适用于公网与局域网）.
     */
    public String getIpAddr()
    {
        if(request == null)
        {
            return "";
            // throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if(StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString))
        {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString))
        {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString))
        {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for(final String str : arr)
        {
            if(!"unknown".equalsIgnoreCase(str))
            {
                ipString = str;
                break;
            }
        }
        return ipString;
    }

    @ResponseBody
    @RequestMapping(value = "/getRedis")
    public String getRedis(String tiket) throws MapperException
    {
        if(ObjectUtil.isEmpty(tiket))
        {
            return "xxx";
        }
        RedisUtil.setString(tiket + "-U",tiket,5000);
        ReturnModel rm = new ReturnModel();
        rm.setBasemodle(RedisUtil.getObject(tiket));
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return JsonUtil.objectToJsonStr(rm);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo(String tiket) throws MapperException
    {

        ReturnModel rm = new ReturnModel();
        if(ObjectUtil.isEmpty(tiket))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("tiket不能为空");
            return JsonUtil.objectToJsonStr(rm);
        }
        LoginReturnMsg msg = loginService.getLoginReturnMsgByTiekt(tiket);
        if(msg == null)
        {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson("您的身份已过期，请重新登录");
        }
        else
        {

            rm.setStatu(ReturnStatus.Success);
            rm.setBasemodle(msg);
            rm.setStatusreson("成功");
        }
        return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 手机验证
     * 
     * @param str
     * @return
     */
    public boolean isphone2(String str)
    {
        String regex = "^[1][1-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public boolean sendBingDevice(String weiid,String device,String token)
    {
        try
        {
            SendPushMessage send = new SendPushMessage();
            Long wid = Long.parseLong(weiid);
            if(!ObjectUtil.isEmpty(device))
            {
                String[] dev = device.split("\\:");
                if(dev == null || dev.length != 2)
                {
                    return send.sendBingDeviceMsg(wid,"",token);
                }
                else
                {
                    return send.sendBingDeviceMsg(wid,dev[1],token);
                }
            }
            else
            {
                return send.sendBingDeviceMsg(wid,"",token);
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
