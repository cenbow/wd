package com.okwei.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;

import com.okwei.bean.vo.LoginUser;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;

public class BaseSSOFilter implements Filter
{
    /**
     * （优先级1）是否启用微店用户登陆检查，true需要登陆才能进入，false不用登陆页能进入
     */
    public boolean isEnabled;
    /**
     * （优先级2）是否启用子帐号检查，true如果登陆，有权限进入，false登陆页不能进入
     */
    public boolean isSubEnabled;
    /**
     * （优先级3）是否启用子供应商帐号检查,true可以有权限进入，false没权限进入
     */
    public boolean isSubSupplyEnabled = false;

    @Override
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException
    {
        request.setCharacterEncoding("utf-8");// 来源编码
        response.setCharacterEncoding("utf-8");// 返回编码
        HttpServletRequest httpRequest = (HttpServletRequest) request; 
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI().toLowerCase();
    	if(	requestURI.endsWith(".css") ||	requestURI.endsWith(".js")|| requestURI.endsWith(".ico") ||
    			requestURI.endsWith(".gif")|| requestURI.endsWith(".png")|| requestURI.endsWith(".jpg")||
    			requestURI.endsWith(".jpeg") || requestURI.endsWith(".swf") )
    	{
			//满足条件就继续执行
			chain.doFilter(request, response);
			return;
    	}
        String domainUrl = httpRequest.getRequestURL().toString();// 获取来源URL
        if(!ObjectUtil.isEmpty(httpRequest.getQueryString()) && !"null".equals(httpRequest.getQueryString()))
        {
            domainUrl += "?" + httpRequest.getQueryString();
        }
        String tiket = httpRequest.getParameter("tiket");// 获取参数tiket
        ResourceBundle bundle = ResourceBundle.getBundle("domain");// 获取当前的域名
        String strDomain = (bundle == null ? ".okwei.com" : ("." + bundle.getString("okweidomain")));// 域名
        // 传过来的参数，如果有tiket
        if(!ObjectUtil.isEmpty(tiket))
        {
            setCookies(httpResponse,"tiket","",0,"/",strDomain);// 先删除原有的tiket
            setCookies(httpResponse,"tiket","",0,"/",strDomain);// 先删除原有的tiket
            setCookies(httpResponse,"tiket",tiket,360000,"/",strDomain);// 设置cookie
            domainUrl = domainUrl.replaceAll("(\\?|&){1,}tiket=.*","");// 截取字符串
            httpResponse.sendRedirect(domainUrl);// 当前页去参数刷新
            return;
        }
        if(isEnabled)
        {
	        String cookie_tiket = getCookieByName(httpRequest,"tiket");// 获取tiket的值
	        domainUrl = URLEncoder.encode(domainUrl,"UTF-8");
	        // 如果cookie里面的tiket不为空，获取缓存的值
	        if(!ObjectUtil.isEmpty(cookie_tiket) && !"notiket".equals(cookie_tiket))
	        {
	            Object user = RedisUtil.getObject(cookie_tiket + "_IBS");
	            if(user != null)
	            {
	                RedisUtil.setExpire(cookie_tiket + "_IBS",60 * 30);// 延长时间
	                RedisUtil.setExpire(cookie_tiket,60 * 30);// 延长时间
	                chain.doFilter(request,response);
	            }
	            else
	            {
	                // 微店用户缓存为空，判断是否检验子账号
	                if(isSubEnabled)
	                {
	                    Object userSub = RedisUtil.getObject(cookie_tiket + "_SUB");
	                    if(userSub != null)
	                    {
	                        // 子供应商是否可以进入
	                        if(isSubSupplyEnabled)
	                        {
	                            RedisUtil.setExpire(cookie_tiket + "_SUB",60 * 30);// 延长时间
	                            chain.doFilter(request,response);
	                        }
	                        else
	                        {
	                            LoginUser sub = (LoginUser) userSub;
	                            if(sub.getAccountType() == 2)
	                            {
	                                // 如果是子供应商
	                                httpResponse.sendRedirect("http://www.okwei.com/?noSupplyPower");// 跳转到没有权限
	                            }
	                            else
	                            {
	                                RedisUtil.setExpire(cookie_tiket + "_SUB",60 * 30);// 延长时间
	                                chain.doFilter(request,response);
	                            }
	                        }
	                    }
	                    else
	                    {
	                        setCookies(httpResponse,"tiket","",0,"/",strDomain);// 先删除原有的tiket
	                        setCookies(httpResponse,"tiket","notiket",0,"/",strDomain);// 先删除原有的tiket
	                        httpResponse.sendRedirect("http://port" + strDomain + "/?a=1&back=" + domainUrl);
	                    }
	                }
	                else
	                {
	                    Object userSub = RedisUtil.getObject(cookie_tiket + "_SUB");
	                    if(userSub != null)
	                    {
	                        httpResponse.sendRedirect("http://www.okwei.com/?nopower");// 跳转到没有权限
	                    }
	                    else
	                    {
	                        setCookies(httpResponse,"tiket","",0,"/",strDomain);// 先删除原有的tiket
	                        setCookies(httpResponse,"tiket","notiket",0,"/",strDomain);// 先删除原有的tiket
	                        httpResponse.sendRedirect("http://port" + strDomain + "/?a=2&back=" + domainUrl);
	                    }
	                }
	            }
	        }
	        else
	        {
	            // tiket是否等于notiket，如果是，跳转登陆页
	            if("notiket".equals(cookie_tiket))
	            {
	                setCookies(httpResponse,"tiket","",0,"/",strDomain);// 先删除原有的tiket
	                setCookies(httpResponse,"tiket","notiket",0,"/",strDomain);// 先删除原有的tiket
	                httpResponse.sendRedirect("http://port" + strDomain + "/?a=3&back=" + domainUrl);
	            }
	            else
	            {
	                // 链接参数与cookie都没有tiket，去单点登陆站点获取
	                httpResponse.sendRedirect("http://port" + strDomain + "/getTiket?back=" + domainUrl);
	            }
	        }
	    }
	    else
	    {
	        chain.doFilter(request,response);
	    }
    }

    /**
     * 设置cookie
     * 
     * @param response
     *            响应实体
     * @param key
     *            cookie的键
     * @param value
     *            cookie的值
     * @param second
     *            时间，秒
     * @param path
     *            cookie的域名
     */
    private void setCookies(HttpServletResponse response,String key,String value,int second,String path,String domain)
    {
        if(!ObjectUtil.isEmpty(key) && !ObjectUtil.isEmpty(value) && second >= 0 && !ObjectUtil.isEmpty(path))
        {
            Cookie cookie = new Cookie(key,value);
            cookie.setMaxAge(second);
            cookie.setPath("/");
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * 获取指定cookie值
     * 
     * @param request
     *            请求实体
     * @param name
     *            cookie的键
     * @return cookie的值
     */
    private String getCookieByName(HttpServletRequest request,String name)
    {
        Cookie[] cookies = request.getCookies();
        if(ObjectUtil.isEmpty(name))
        {
            return null;
        }
        if(cookies != null)
        {
            for(Cookie cookie : cookies)
            {
                if(name.equals(cookie.getName().trim()))
                {
                    return cookie.getValue();
                }
            }
            return null;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

}
