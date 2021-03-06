package com.okwei.sso.web;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.enums.LoginTypeEnum;
import com.okwei.bean.enums.VerifyCodeType;
import com.okwei.bean.vo.LoginResult;
import com.okwei.common.JsonUtil;
import com.okwei.service.user.ILoginService;
import com.okwei.sso.bean.vo.LoginRedis;
import com.okwei.util.AES;
import com.okwei.util.DES;
import com.okwei.util.MD5Encrypt;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;
import com.okwei.util.SendSMSByMobile;

@Controller
@RequestMapping(value = "/")
public class LoginController
{
    @Autowired
    ILoginService loginService;
 
    @Resource
    private Producer captchaProducer = null; 

    @ResponseBody
    @RequestMapping(value = "/clearRedis",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String clearRedis(String str)
    {
        RedisUtil.delete(str);
        return "1";
    }

    /**
     * 登录页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model,HttpSession session)
    {
//        Object name = session.getAttribute("name");
//        model.addAttribute("list",name);
        return "login/index";
    }

    @RequestMapping(value = "/oldlogin")
    public String oldlogin(){
        return "login/oldlogin";
    }

    @RequestMapping(value = "/login.aspx")
    public String oldIndex(Model model,HttpSession session)
    {
//        Object name = session.getAttribute("name");
//        model.addAttribute("list",name);
        return "login/index";
    }

    @RequestMapping(value = "/exit.aspx")
    public String exitIndex(Model model,HttpSession session)
    {
//        Object name = session.getAttribute("name");
//        model.addAttribute("list",name);
        return "redirect:http://www.okwei.com";
    }

    /**
     * 注册页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/regist")
    public String regist(Model model)
    {
        return "login/regist";
    }

    /**
     * 注册页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/RegistNew.aspx")
    public String registNew(Model model)
    {
        return "login/regist";
    }

    /**
     * 找回密码，页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/findPassword")
    public String findPassword(Model model)
    {
        return "login/findPassword";
    }

    /**
     * 判断手机是否注册
     * 
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findPhone",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String findPhone(String phone)
    {
        Long weiid = loginService.getWeiSellerByPhone(phone);
        if(weiid == null)
        {
            return "0";
        }
        else
        {
            String guid = UUID.randomUUID().toString().toUpperCase();
            LoginRedis lr = new LoginRedis();
            lr.setPhone(phone);
            lr.setWeiid(weiid);
            RedisUtil.setObject(guid,lr,1800);
            return "{\"weiid\":\"" + weiid.toString() + "\",\"guid\":\"" + guid + "\"}";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/registUser",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String registUser(HttpServletRequest request,String sjWeiNo,String weiName,String mobile,String pwd,String code)
    {
        LoginResult result = new LoginResult();// 返回实体
        String ip = getIpAddr(request);
        result = loginService.registWeiUser(ip,sjWeiNo,weiName,mobile,pwd,code);
        return JsonUtil.objectToJson(result);
    }

    /**
     * 修改密码
     * 
     * @param guid
     * @param pass
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassWord",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String updatePassWord(String guid,String pass)
    {
        LoginResult result = new LoginResult();// 返回实体
        if(ObjectUtil.isEmpty(guid) || ObjectUtil.isEmpty(pass))
        {
            result.setMessage("参数不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        // 设置缓存
        LoginRedis lr = (LoginRedis) RedisUtil.getObject(guid);
        if(lr == null)
        {
            result.setMessage("缓存失效，请重新再试");
            result.setStatus(-3);
            return JsonUtil.objectToJson(result);
        }
        if(lr.getYzImgCode() != 1 || lr.getYzPhoneCode() != 1)
        {
            result.setMessage("没有验证图片验证码/手机验证码");
            result.setStatus(-2);
            return JsonUtil.objectToJson(result);
        }
        String md5Pwd = MD5Encrypt.encrypt(pass).toUpperCase();
        Long weiid = lr.getWeiid();
        if(loginService.updateUWeiSellerByPwd(weiid,md5Pwd))
        {
            result.setMessage("成功");
            result.setStatus(1);
        }
        else
        {
            result.setMessage("修改密码失败");
            result.setStatus(-2);
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 验证图片验证码
     * 
     * @param imgcode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getImgCode",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getImgCode(HttpSession session,String imgcode,String guid)
    {
        LoginResult result = new LoginResult();// 返回实体
        // 设置验证码
        LoginRedis lr = (LoginRedis) RedisUtil.getObject(guid);
        if(lr == null)
        {
            result.setMessage("缓存失效，请重新再试");
            result.setStatus(-3);
            return JsonUtil.objectToJson(result);
        }
        if(ObjectUtil.isEmpty(imgcode) || ObjectUtil.isEmpty(guid))
        {
            result.setMessage("请输入验证码");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        Object name = session.getAttribute("loginimg");
        session.removeAttribute("loginimg");// 清除验证码
        if(name == null)
        {
            result.setMessage("生成验证码错误");
            result.setStatus(-2);
        }
        else
        {
            if(!name.toString().equals(imgcode))
            {
                result.setMessage("验证码错误");
                result.setStatus(-1);
            }
            else
            {
                result.setMessage("成功");
                result.setStatus(1);
                lr.setYzImgCode(1);// 1表示已经验证过图片验证码
                RedisUtil.setObject(guid,lr,1800);
            }
        }
        return JsonUtil.objectToJson(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getImgVerCode",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getImgVerCode(HttpSession session,String code,String phone)
    {
        LoginResult result = new LoginResult();// 返回实体
        if(ObjectUtil.isEmpty(code))
        {
            result.setMessage("请输入验证码");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        Long weiid = loginService.getWeiSellerByPhone(phone);
        if(weiid != null && weiid > 1)
        {
            result.setMessage("此手机号码已经绑定微店号");
            result.setStatus(-4);
            return JsonUtil.objectToJson(result);
        }
        Object name = session.getAttribute("loginimg");
        session.removeAttribute("loginimg");
        if(name == null)
        {
            result.setMessage("生成验证码错误");
            result.setStatus(-2);
        }
        else
        {
            if(!name.toString().equals(code))
            {
                result.setMessage("验证码错误");
                result.setStatus(-1);
            }
            else
            {
                RedisUtil.setObject("RegistUser_Phone_" + phone,phone,600);// 验证图片验证码的手机号码
                result.setMessage("成功");
                result.setStatus(1);
            }
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 发送注册验证码
     * 
     * @param session
     * @param code
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/senPhoneCode",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String senPhoneCode(HttpServletRequest request,HttpSession session,String phone)
    {
        LoginResult result = new LoginResult();// 返回实体
        if(ObjectUtil.isEmpty(phone))
        {
            result.setMessage("输入参数不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        Object objRdp = RedisUtil.getObject("RegistUser_Phone_" + phone);
        String Rdphone = (objRdp == null ? "" : objRdp.toString());
        if(ObjectUtil.isEmpty(Rdphone))
        {
            result.setMessage("请先验证图片验证码");
            result.setStatus(-2);
            return JsonUtil.objectToJson(result);
        }
        Object objcount = RedisUtil.getObject("SendRegistPhoneCount_" + Rdphone);
        String countstr = (objcount == null ? "0" : objcount.toString());
        Integer count = ObjectUtil.isEmpty(countstr) ? 0 : StringToInt(countstr);
        count++;
        if(count > 3)
        {
            result.setMessage("此号码已经验证超过三次，请明天再试");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        RedisUtil.setObject("SendRegistPhoneCount_" + Rdphone,count.toString(),36000);
        // 添加短信记录
        String code = String.valueOf(Math.random()).substring(2,8);// 随机生成验证码
        TVerificationId tvId = new TVerificationId();
        tvId.setGetTime(new Date());
        tvId.setStatus(Short.parseShort("0"));
        tvId.setType(Short.parseShort(VerifyCodeType.register.toString()));
        tvId.setUserIp(getIpAddr(request));
        tvId.setVcode(code);
        tvId.setVerificationtext(phone);
        loginService.insertTVerificationId(tvId);
        // 发送短信
        SendSMSByMobile.sendSMS(Rdphone,"微店网注册验证码：" + code + "[微店网]");
        RedisUtil.setObject("RegistPhoneCode_" + Rdphone,code,300);// 短信验证码缓存5分钟
        result.setMessage("发送成功");
        result.setStatus(1);
        return JsonUtil.objectToJson(result);
    }

    /**
     * 发送手机验证码
     * 
     * @param guid
     *            标识
     * @param vcode
     *            图片验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCode",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getCode(HttpServletRequest request,HttpSession session,String guid)
    {
        LoginResult result = new LoginResult();// 返回实体
        if(ObjectUtil.isEmpty(guid))
        {
            result.setMessage("参数不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        // 设置验证码
        LoginRedis lr = (LoginRedis) RedisUtil.getObject(guid);
        if(lr == null)
        {
            result.setMessage("缓存失效，请重新再试");
            result.setStatus(-3);
            return JsonUtil.objectToJson(result);
        }
        if(lr.getYzImgCode() != 1)
        {
            result.setMessage("未进行图片验证码验证");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        // 手机发送短信验证码次数
        Object objcount = RedisUtil.getObject("SendPhoneCount_" + lr.getPhone());
        String countstr = (objcount == null ? "0" : objcount.toString());
        Integer count = StringToInt(countstr);// 发送次数
        count++;
        RedisUtil.setObject("SendPhoneCount_" + lr.getPhone(),count.toString(),36000);// 缓存是个小时
        if(count > 3)
        {
            result.setMessage("今天已经发送三次，请明天再试！");
            result.setStatus(-4);
            return JsonUtil.objectToJson(result);
        }
        String phone = lr.getPhone();// 获取手机号
        Long weiid = lr.getWeiid();// 获取手机号
        if(ObjectUtil.isEmpty(phone) || ObjectUtil.isEmpty(weiid))
        {
            result.setMessage("参数不能为空");
            result.setStatus(-5);
            return JsonUtil.objectToJson(result);
        }
        // 添加短信记录
        String code = String.valueOf(Math.random()).substring(2,8);// 随机生成验证码
        TVerificationId tvId = new TVerificationId();
        tvId.setGetTime(new Date());
        tvId.setStatus(Short.parseShort("0"));
        tvId.setType(Short.parseShort(VerifyCodeType.updatepassword.toString()));
        tvId.setUserIp(getIpAddr(request));
        tvId.setVcode(code);
        tvId.setVerificationtext(phone);
        tvId.setWeiNo(weiid);
        loginService.insertTVerificationId(tvId);
        // 发送短信
        SendSMSByMobile.sendSMS(phone,"找回密码验证码：" + code + "[微店网]");
        RedisUtil.setObject(guid + "_code",code,300);// 短信验证码缓存5分钟
        result.setMessage("发送成功");
        result.setStatus(1);
        lr.setPhoneCount(0);// 发送时重置验证验证码次数
        lr.setPhoneCode(code);// 设置手机验证码
        RedisUtil.setObject(guid,lr,1800);
        return JsonUtil.objectToJson(result);
    }

    /**
     * 验证手机验证码
     * 
     * @param guid
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPhoneCode",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getPhoneCode(String guid,String code)
    {
        LoginResult result = new LoginResult();// 返回实体
        if(ObjectUtil.isEmpty(guid) || ObjectUtil.isEmpty(code))
        {
            result.setMessage("请输入验证码");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        // 设置缓存
        LoginRedis lr = (LoginRedis) RedisUtil.getObject(guid);
        if(lr == null)
        {
            result.setMessage("缓存失效，请重新再试");
            result.setStatus(-3);
            return JsonUtil.objectToJson(result);
        }
        if(lr.getYzImgCode() != 1)
        {
            result.setMessage("未进行图片验证码验证");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        String phone = lr.getPhone();// 获取手机号
        Long weiid = lr.getWeiid();// 获取微店号
        String phonecode = lr.getPhoneCode();// 获取手机验证码
        Integer count = lr.getPhoneCount();// 验证码验证次数
        if(ObjectUtil.isEmpty(phone) || ObjectUtil.isEmpty(weiid) || ObjectUtil.isEmpty(phonecode))
        {
            result.setMessage("参数不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        // 验证次数
        count++;
        if(count > 3)
        {
            lr.setPhoneCount(count);
            lr.setPhoneCode("");
            RedisUtil.setObject(guid,lr,1800);
            result.setMessage("验证次数过多，请重新获取验证码");
            result.setStatus(-4);
            return JsonUtil.objectToJson(result);
        }
        // 验证短信验证码
        if(!code.equals(phonecode))
        {
            lr.setPhoneCount(count);
            RedisUtil.setObject(guid,lr,1800);
            result.setMessage("验证码错误，请重新输入");
            result.setStatus(-5);
            return JsonUtil.objectToJson(result);
        }
        lr.setYzPhoneCode(1);// 手机验证码验证成功
        RedisUtil.setObject(guid,lr,1800);
        result.setMessage("成功");
        result.setStatus(1);
        return JsonUtil.objectToJson(result);
    }

    /**
     * 验证是否出现验证码
     * 
     * @param username
     *            验证的用户名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkThing",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String checkThing(HttpServletRequest request,String weino)
    {
        if(ObjectUtil.isEmpty(weino))
        {
            return "0";
        }
        String ip = getIpAddr(request);
        Object objweiCount = RedisUtil.getObject("loginWeiNo_" + weino);
        String weiCount = (objweiCount == null ? "0" : objweiCount.toString());
        Integer weiNoLoginCount = (weiCount == null ? 0 : Integer.parseInt(weiCount));// 微店号登陆次数
        Object objIp = RedisUtil.getObject("loginIP_" + ip);
        String ipCount = (objIp == null ? "0" : objIp.toString());
        Integer ipLoginCount = (ipCount == null ? 0 : Integer.parseInt(ipCount));// IP登陆次数
        if(weiNoLoginCount >= 3 || ipLoginCount >= 20) //
        {
            return "1";
        }
        else
        {
            return "0";
        }
    }

    /**
     * 用户登陆
     * 
     * @param username
     *            用户名（微店号/手机号）
     * @param password
     *            用户密码
     * @param vcode
     *            验证码
     * @param back
     *            返回地址
     * @param isRemember
     *            是否记住密码（1记住密码，2不记住密码）
     * @param loginType
     *            是否用记住密码的方式登陆（1直接登陆，2用保存的cookie登陆）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getlogin",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getlogin(HttpServletRequest request,HttpServletResponse response,HttpSession session,String username,String password,String vcode,String back,Integer isRemember,
            Integer loginType)
    {
        LoginResult result = new LoginResult();// 返回实体
        // 参数处理
        if(ObjectUtil.isEmpty(username))
        {
            result.setMessage("微店号/手机号码不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        if(ObjectUtil.isEmpty(password))
        {
            result.setMessage("密码不能为空");
            result.setStatus(-1);
            return JsonUtil.objectToJson(result);
        }
        if(isRemember == null)
        {
            isRemember = 2;
        }
        if(loginType == null)
        {
            loginType = 1;
        }
        back = loginService.getDomainByBack(back);
        // 转换密码
        String md5Pwd = "";
        String desPwd = "";
        String yuanPwd = "";
        if(loginType == 1)
        { 
            md5Pwd = MD5Encrypt.encrypt(password).toUpperCase();
            desPwd = AES.aesEncrypt(password);// DES加密 
            yuanPwd = password;
        }
        else
        {
            md5Pwd = DES.decrypt(password,"");
            if(ObjectUtil.isEmpty(md5Pwd))
            { 
                md5Pwd = MD5Encrypt.encrypt(password).toUpperCase();
                desPwd = AES.aesEncrypt(password);// DES加密 
                yuanPwd = password;
            }
            else{
                yuanPwd = md5Pwd;
                desPwd = AES.aesEncrypt(md5Pwd);// DES加密 
                md5Pwd = MD5Encrypt.encrypt(md5Pwd).toUpperCase();
            }
        }
        Object name = session.getAttribute("loginimg");
        session.removeAttribute("loginimg");// 清除验证码
        String ip = getIpAddr(request);
        // 判断是否需要验证码
        Object objweiCount = RedisUtil.getObject("loginWeiNo_" + username);
        String weiCount = (objweiCount == null ? "0" : objweiCount.toString());
        Integer weiNoLoginCount = (weiCount == null ? 0 : Integer.parseInt(weiCount));// 微店号登陆次数
        Object objip = RedisUtil.getObject("loginIP_" + ip);
        String ipCount = (objip == null ? "0" : objip.toString());
        Integer ipLoginCount = (ipCount == null ? 0 : Integer.parseInt(ipCount));// IP登陆次数
        // 规则：同一微店号3次，同一IP20次。需要验证验证码
        if(weiNoLoginCount >= 3 || ipLoginCount >= 20)
        {
            if(ObjectUtil.isEmpty(vcode))
            {
                result.setMessage("请输入验证码");
                result.setStatus(3);
                return JsonUtil.objectToJson(result);
            }
            if(!vcode.equals(name))
            {
                result.setMessage("验证码错误");
                result.setStatus(3);
                return JsonUtil.objectToJson(result);
            }
        }
        else
        {
            weiNoLoginCount++;
            ipLoginCount++;
            RedisUtil.setObject("loginWeiNo_" + username,weiNoLoginCount.toString(),1800);
            RedisUtil.setObject("loginIP_" + ip,ipLoginCount.toString(),1800);
        }

        result = loginService.getUWeiSellerByLogin(username,md5Pwd,desPwd,ip,LoginTypeEnum.PCLogin);
        if(result != null && result.getStatus() == 1)
        {
            result.setOkweidomain(back);
            // 获取域名
            ResourceBundle bundle = ResourceBundle.getBundle("domain");
            String strDomain = ".okwei.com";
            if(bundle != null)
            {
                strDomain = bundle.getObject("okweidomain").toString();
            }
            // 设置cookie
            Cookie cookie = new Cookie("tiket",result.getMessage());
            cookie.setMaxAge(43200);
            cookie.setPath("/");
            cookie.setDomain(strDomain);
            response.addCookie(cookie);
            // 是否记住密码
            if(isRemember == 1)
            {
                // 设置微店号cookie
                Cookie cookieweiid = new Cookie("weiID",username);
                cookieweiid.setMaxAge(43200);
                cookieweiid.setPath("/");
                cookieweiid.setDomain(strDomain);
                response.addCookie(cookieweiid);
                // 设置密码cookie
                
                Cookie cookiepwd = new Cookie("userPwd",DES.encrypt(yuanPwd,""));
                cookiepwd.setMaxAge(43200);
                cookiepwd.setPath("/");
                cookiepwd.setDomain(strDomain);
                response.addCookie(cookiepwd);
            }
            else
            {
                // 删除cookies
                Cookie cookieweiiddel = new Cookie("weiID","");
                cookieweiiddel.setMaxAge(0);
                cookieweiiddel.setPath("/");
                response.addCookie(cookieweiiddel);
                Cookie cookiepwddel = new Cookie("userPwd","");
                cookiepwddel.setMaxAge(0);
                cookiepwddel.setPath("/");
                response.addCookie(cookiepwddel);
            }
            // 获取cookie里面的tiket，如果有，删除redis
            Cookie[] cookies = request.getCookies();
            String cookie_tiket = "";
            if(cookies != null)
            {
                for(Cookie c : cookies)
                {
                    if("tiket".equals(c.getName().trim()))
                    {
                        cookie_tiket = c.getValue();
                        break;
                    }
                }
            }
            if(!ObjectUtil.isEmpty(cookie_tiket) && !"notiket".equals(cookie_tiket))
            {
                RedisUtil.delete(cookie_tiket + "_IBS");
                RedisUtil.delete(cookie_tiket + "_SUB");
            }
            return JsonUtil.objectToJson(result);
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 图片验证码
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/getVImageCode")
    public String getKaptchaImage(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception
    {
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi,"jpg",out);
        session.setAttribute("loginimg",capText);
        try
        {
            out.flush();
        }
        finally
        {
            out.close();
        }
        return null;

    }

    private int StringToInt(String str)
    {
        if(str == null)
        {
            return 0;
        }

        try
        {
            return Integer.parseInt(str);
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    /**
     * 获取访问用户的客户端IP（适用于公网与局域网）.
     */
    public static final String getIpAddr(final HttpServletRequest request)
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
}
