package com.okwei.sso.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.api.Baidu;
import com.baidu.api.BaiduApiException;
import com.baidu.api.BaiduOAuthException;
import com.baidu.api.domain.User;
import com.baidu.api.store.BaiduCookieStore;
import com.baidu.api.store.BaiduStore;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.okwei.bean.domain.UOtherLogin;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.bean.enums.LoginTypeEnum;
import com.okwei.bean.enums.OtherLoginType;
import com.okwei.bean.vo.LoginPublicResult;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.LoginReturnAPP;
import com.okwei.bean.vo.user.LoginUserExtend;
import com.okwei.common.JsonUtil;
import com.okwei.service.user.ILoginService;
import com.okwei.sso.bean.util.BaiDuLoginUtil;
import com.okwei.sso.bean.util.WXLoginUtil;
import com.okwei.sso.bean.vo.WxLoginResult;
import com.okwei.sso.bean.vo.WxLoginToken;
import com.okwei.util.HttpRequestHelper;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/other")
public class OtherLoginController
{
   
    @Autowired
    ILoginService loginService;

    /**
     * 第三方注册页面
     * 
     * @return
     */
    @RequestMapping(value = "regist")
    public String regist(@RequestParam(required = false,defaultValue = "1") long w,Model model)
    {
        model.addAttribute("sjWeiNo",w);
        return "other/regist";
    }

    @ResponseBody
    @RequestMapping(value = "/otherRegist",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String otherRegist(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false,defaultValue = "1") long sjWeiNo,String mobile,String code,String pwd) throws MapperException
    {
        ReturnModel rm = new ReturnModel();
        String token = getCookieByKey(request,response,"token");
        rm = loginService.otherRegist(token,sjWeiNo,mobile,pwd,code);
        // rm.setBasemodle("038AA995-8413-46AE-B168-D6F8C10FBF41");
        // rm.setStatu(ReturnStatus.Success);
        return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * QQ登陆入口
     */
    @RequestMapping(value = "/qqLogin")
    public String qqLogin(HttpServletRequest request,HttpServletResponse response)
    {
        response.setContentType("text/html;charset=utf-8");
        try
        {
            Oauth auth = new Oauth();
            String url = auth.getAuthorizeURL(request);
            return "redirect:" + url;
        }
        catch(Exception e)
        {
            return "redirect:/";
        }
    }

    /**
     * QQ回调URL
     * 
     * @throws IOException
     */
    @RequestMapping(value = "/qqBack",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String qqBack(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html; charset=utf-8");
        LoginPublicResult loginRes = new LoginPublicResult();// 第三方登陆数据
        try
        {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null,openID = null;
            if(accessTokenObj.getAccessToken().equals(""))
            {
                // 我们的网站被CSRF攻击了或者用户取消了授权
                // 做一些数据统计工作
                System.out.print("没有获取到响应参数");
                return "redirect:/?error=1";
            }
            else
            {
                accessToken = accessTokenObj.getAccessToken();
                // 利用获取openID
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                loginRes.setOpenID(openID);
                loginRes.setHeadImg("http://base1.okimgs.com/images/logo.jpg");
                loginRes.setNickName("微店网用户");
                loginRes.setLoginType(Short.parseShort(OtherLoginType.qq.toString()));
                // 腾讯微博
                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken,openID);
                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                if(weiboUserInfoBean.getRet() == 0)
                {
                    loginRes.setHeadImg(weiboUserInfoBean.getAvatar().getAvatarURL100());
                }
                // QQ空间数据
                UserInfo qzoneUserInfo = new UserInfo(accessToken,openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if(userInfoBean.getRet() == 0)
                {
                    loginRes.setHeadImg(userInfoBean.getAvatar().getAvatarURL100());
                    loginRes.setNickName(userInfoBean.getNickname());
                }
            }
        }
        catch(QQConnectException e)
        {
            return "redirect:/?error=2";
        }
        if(loginRes == null || ObjectUtil.isEmpty(loginRes.getOpenID()))
        {
            return "redirect:/?error=3";
        }
        // 开始登陆
        return "redirect:" + LoginOrBind(request,response,loginRes);
    }

    /**
     * 微信登陆
     * 
     * @return
     */
    @RequestMapping(value = "/wxLogin")
    public String wxLogin(HttpServletRequest request,HttpServletResponse response)
    {
        response.setContentType("text/html;charset=utf-8");
        try
        {

            String wxTempStr = "wx" + UUID.randomUUID().toString().toUpperCase();
            request.getSession().setAttribute("wxState",wxTempStr);
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + WXLoginUtil.WxLoginAppid + "&redirect_uri=" + WXLoginUtil.WXRedirectURl
                    + "&response_type=code&scope=snsapi_login&state=" + wxTempStr;
            return "redirect:" + url;
        }
        catch(Exception e)
        {
            return "redirect:/";
        }
    }

    /**
     * 微信返回地址
     * 
     * @return
     */
    @RequestMapping(value = "/wxBack",method = {RequestMethod.POST,RequestMethod.GET})
    public String wxBack(HttpServletRequest request,HttpServletResponse response)
    {
        String code = request.getParameter("code");// 微信返回的code
        String state = request.getParameter("state");// 给过去的state
        if(request.getSession().getAttribute("wxState") != null)
        {
            String wxState = request.getSession().getAttribute("wxState").toString();
            request.getSession().removeAttribute("wxState");
            if(!wxState.equals(state))
            {
                return "redirect:/";
            }
        }
        String getToeknUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String reString = HttpRequestHelper.sendGet(getToeknUrl,"appid=" + WXLoginUtil.WxLoginAppid + "&secret=" + WXLoginUtil.WxLoginSecret + "&code=" + code
                + "&grant_type=authorization_code");
        if(!ObjectUtil.isEmpty(reString))
        {
            WxLoginToken token = (WxLoginToken) JsonUtil.json2Object2(reString,WxLoginToken.class);
            if(token != null && !ObjectUtil.isEmpty(token.getAccess_token()))
            {
                String getUrl = "https://api.weixin.qq.com/sns/userinfo";
                String result = HttpRequestHelper.sendGet(getUrl,"access_token=" + token.getAccess_token() + "&openid=" + token.getOpenid());
                if(!ObjectUtil.isEmpty(result))
                {
                    WxLoginResult wxres = (WxLoginResult) JsonUtil.json2Object(result,WxLoginResult.class);
                    if(wxres != null && !ObjectUtil.isEmpty(wxres.getUnionid()))
                    {
                        LoginPublicResult lpr = new LoginPublicResult();
                        lpr.setHeadImg(wxres.getHeadimgurl());
                        lpr.setLoginType(Short.parseShort(OtherLoginType.weixin.toString()));
                        lpr.setNickName(wxres.getNickname());
                        lpr.setOpenID(wxres.getUnionid());
                        return "redirect:" + LoginOrBind(request,response,lpr);
                    }
                }
            }
        }
        return "redirect:/";
    }

    /**
     * 百度登陆
     */
    @RequestMapping(value = "/bdLogin")
    public String bdLogin(HttpServletRequest request,HttpServletResponse response)
    {
        String clientId = BaiDuLoginUtil.CLIENTID;
        String clientSecret = BaiDuLoginUtil.CLIENTSECRET;
        String redirectUri = BaiDuLoginUtil.REDIRECTURI;
        BaiduStore store = new BaiduCookieStore(clientId,request,response);
        Baidu baidu = null;
        try
        {
            baidu = new Baidu(clientId,clientSecret,redirectUri,store,request);
            String state = baidu.getState();
            Map<String,String> params = new HashMap<String,String>();
            params.put("state",state);
            String authorizeUrl = baidu.getBaiduOAuth2Service().getAuthorizeUrl(params);
            return "redirect:" + authorizeUrl;
        }
        catch(BaiduOAuthException e)
        {
        }
        catch(BaiduApiException e)
        {
        }
        return "redirect:/?error=1";
    }

    /**
     * 百度返回地址
     * 
     * @return
     */
    @RequestMapping(value = "/bdBack")
    public String bdBack(HttpServletRequest request,HttpServletResponse response)
    {
        String clientId = BaiDuLoginUtil.CLIENTID;
        String clientSecret = BaiDuLoginUtil.CLIENTSECRET;
        String redirectUri = BaiDuLoginUtil.REDIRECTURI;
        BaiduStore store = new BaiduCookieStore(clientId,request,response);
        Baidu baidu = null;
        User loggedInUser = null;
        try
        {
            baidu = new Baidu(clientId,clientSecret,redirectUri,store,request);
            loggedInUser = baidu.getLoggedInUser();
        }
        catch(BaiduApiException e)
        {
        }
        catch(BaiduOAuthException e)
        {
        }
        if(loggedInUser != null)
        {
            LoginPublicResult lpr = new LoginPublicResult();
            lpr.setHeadImg("http://base1.okimgs.com/images/logo.jpg");
            lpr.setLoginType(Short.parseShort(OtherLoginType.baidu.toString()));
            lpr.setNickName(loggedInUser.getUname());
            lpr.setOpenID(String.valueOf(loggedInUser.getUid()));
            return "redirect:" + LoginOrBind(request,response,lpr);
        }
        // request.getRequestDispatcher("accesstoken.jsp").forward(request, response);
        return "redirect:/?error=1";
    }

    /**
     * 第三方登录跳转
     * 
     * @return
     */
    @RequestMapping(value = "/loginJump")
    public String loginJump()
    {
        return "";
    }

    /**
     * 登陆后绑定第三方账号
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/LoginBindByTiket")
    public String LoginBindByTiket(HttpServletRequest request,HttpServletResponse response,String tiket)
    {
        LoginUserExtend lue = new LoginUserExtend();
        String token = getCookieByKey(request,response,"token");
        if(ObjectUtil.isEmpty(token))
        {
            lue.setMessage("请先用第三方登陆后，再进行绑定");
            lue.setStatu((short) -1);
            return JsonUtil.objectToJson(lue);
        }
        if(ObjectUtil.isEmpty(tiket))
        {
            lue.setMessage("登陆失败");
            lue.setStatu((short) -1);
            return JsonUtil.objectToJson(lue);
        }
        lue = loginService.checkLoginByBind(token,tiket);
        return JsonUtil.objectToJson(lue);
    }

    /**
     * 第三方绑定
     * 
     * @param type
     * @return
     */
    @RequestMapping(value = "/bindLogin")
    public String bindLogin(HttpServletRequest request,HttpServletResponse response,String type,String bk)
    {
        // 获取域名
        ResourceBundle bundle = ResourceBundle.getBundle("domain");
        String strDomain = ".okwei.com";
        if(bundle != null)
        {
            strDomain = bundle.getString("okweidomain");
        }
        if("qq".equals(type))
        {
            // 绑定QQ
            Cookie cookie = new Cookie("bindLogin","bindLogin");
            cookie.setMaxAge(1000);
            cookie.setPath("/");
            cookie.setDomain(strDomain);
            response.addCookie(cookie);
            return "redirect:/other/qqLogin";
        }
        else if("wx".equals(type))
        {
            // 绑定微信
            Cookie cookie = new Cookie("bindLogin","bindLogin");
            cookie.setMaxAge(1000);
            cookie.setPath("/");
            cookie.setDomain(strDomain);
            response.addCookie(cookie);
            return "redirect:/other/wxLogin";
        }
        else if("ok".equals(type))
        {
            // 绑定成功跳转
            String bind = getCookieByKey(request,response,"bindLogin");
            if(ObjectUtil.isEmpty(bind))
            {
                if(ObjectUtil.isEmpty(bk))
                {
                    return "redirect:/other/bindLogin?type=ok";
                }
                else
                {
                    return "redirect:/other/bindLogin?type=ok&bk=" + bk;
                }
            }
            else
            {
                // 先删除cookie
                Cookie cookie = new Cookie("bindLogin","bindLogin");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setDomain(strDomain);
                response.addCookie(cookie);
                if(ObjectUtil.isEmpty(bk))
                {
                    return "redirect:http://set.okwei.com/userInfo/bindAccount";
                }
                else
                {
                    return "redirect:http://set.okwei.com/userInfo/bindAccount?bind=" + bk;
                }
            }
        }
        else
        {
            return "redirect:/?error=1";// 非绑定类型，跳转到登陆页
        }
    }

    /**
     * 登陆还是绑定
     * 
     * @param lpr
     * @return
     */
    public String LoginOrBind(HttpServletRequest request,HttpServletResponse response,LoginPublicResult lpr)
    {
        String tiket = getCookieByKey(request,response,"tiket");
        String bind = getCookieByKey(request,response,"bindLogin");
        if(!ObjectUtil.isEmpty(tiket) && !ObjectUtil.isEmpty(bind))
        {
            LoginUser user = (LoginUser) RedisUtil.getObject(tiket + "_IBS");// 用户是否登陆
            if(user != null)
            {
                // 绑定
                loginService.bindLogin(lpr,user.getWeiID());
                return "/other/bindLogin?type=ok";
            }
        }
        // 登陆注册
        return otherLoginByLPR(request,response,lpr);
    }

    /**
     * 获取backurl跳转地址,登陆或注册
     * 
     * @param request
     * @return
     */
    public String otherLoginByLPR(HttpServletRequest request,HttpServletResponse response,LoginPublicResult login)
    {
        String tiket = loginService.otherLogin(login);// 登陆或注册，返回tiekt
        if(!ObjectUtil.isEmpty(tiket))
        {
            if(tiket.equals("-1"))
            {
                // 这个第三方账号没有注册
                String token = UUID.randomUUID().toString().toUpperCase();
                RedisUtil.setObject(token,login,600);// 注册信息缓存到Redis
                setToken(request,response,token);
                return "/other/regist";
            }
            setResponse(request,response,tiket);// 设置tiket的cookie
        }
        String cookieVal = getCookieByKey(request,response,"backUrl");
        return loginService.getDomainByBack(cookieVal);
    }

    public void setToken(HttpServletRequest request,HttpServletResponse response,String token)
    {
        // 获取域名
        ResourceBundle bundle = ResourceBundle.getBundle("domain");
        String strDomain = ".okwei.com";
        if(bundle != null)
        {
            strDomain = bundle.getString("okweidomain");
        }
        // 先删除cookie
        Cookie bindLogin = new Cookie("token","token");
        bindLogin.setMaxAge(0);
        bindLogin.setPath("/");
        bindLogin.setDomain(strDomain);
        response.addCookie(bindLogin);
        // 设置cookie
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(600);
        cookie.setPath("/");
        cookie.setDomain(strDomain);
        response.addCookie(cookie);
    }

    /**
     * 第三方登陆成功后设置cookie
     */
    public void setResponse(HttpServletRequest request,HttpServletResponse response,String tiket)
    {
        // 获取域名
        ResourceBundle bundle = ResourceBundle.getBundle("domain");
        String strDomain = ".okwei.com";
        if(bundle != null)
        {
            strDomain = bundle.getString("okweidomain");
        }
        // 先删除cookie
        Cookie bindLogin = new Cookie("bindLogin","bindLogin");
        bindLogin.setMaxAge(0);
        bindLogin.setPath("/");
        bindLogin.setDomain(strDomain);
        response.addCookie(bindLogin);
        String cookie_tiket = getCookieByKey(request,response,"tiket");
        // 设置cookie
        Cookie cookie = new Cookie("tiket",tiket);
        cookie.setMaxAge(43200);
        cookie.setPath("/");
        cookie.setDomain(strDomain);
        response.addCookie(cookie);
        if(!ObjectUtil.isEmpty(cookie_tiket) && !"notiket".equals(cookie_tiket))
        {
            RedisUtil.delete(cookie_tiket + "_IBS");
            RedisUtil.delete(cookie_tiket + "_SUB");
        }
    }

    /**
     * 根据KEY回去cookie
     * 
     * @param key
     * @return
     */
    public String getCookieByKey(HttpServletRequest request,HttpServletResponse response,String key)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            for(Cookie c : cookies)
            {
                if(key.equals(c.getName().trim()))
                {
                    return c.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取访问用户的客户端IP（适用于公网与局域网）.
     */
    public String getIpAddr(HttpServletRequest request,HttpServletResponse response)
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
