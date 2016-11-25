package com.okwei.cartportal.web.filter;

import com.okwei.web.filter.BaseSSOFilter;

public class SSOFilter extends BaseSSOFilter
{
    public SSOFilter(){
        super.isEnabled = false;//
        super.isSubEnabled = false; 
    }  
//
//    FilterConfig filterConfig = null;
//
//    @Override
//    public void destroy()
//    {
//        this.filterConfig = null;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException
//    {
//    	request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        /* 测试用 */
///*
//        if(httpRequest != null)
//        {
//            chain.doFilter(request,response);
//            return;
//        }*/
//
//        /*--------用的时候删除上面的------------*/
//
//        // 获取URL
//        String domainUrl = httpRequest.getRequestURL().toString();
//        // 获取参数tiket
//        String tiket = httpRequest.getParameter("tiket");
//        ResourceBundle bundle = ResourceBundle.getBundle("domain");
//        String strDomain="";
//		if (bundle == null) 
//		{
//			strDomain=".okwei.com";
//		}
//		else
//		{
//			strDomain="."+bundle.getString("okweidomain");
//		}
//        // 如果链接的参数tiket不为空
//        if(!ObjectUtil.isEmpty(tiket))
//        {
//            // 删除cookies
//            Cookie cookie2 = new Cookie("tiket",tiket);
//            cookie2.setMaxAge(0);
//            cookie2.setPath("/");
//            // cookie.setDomain("okwei.net");
//            httpResponse.addCookie(cookie2);
//            // 设置cookies
////            Cookie cookie = new Cookie("tiket",tiket);
////            cookie.setMaxAge(360000);
////            cookie.setPath("/");
////            // cookie.setDomain("okwei.net");
////            httpResponse.addCookie(cookie);
//            // 设置cookies
//            Cookie cookie3 = new Cookie("tiket",tiket);
//            cookie3.setMaxAge(360000);
//            cookie3.setPath("/");
//            cookie3.setDomain(strDomain);
//            httpResponse.addCookie(cookie3);
//            // 截取字符串
//            domainUrl = domainUrl.replaceAll("(\\?|&){1,}tiket=.*","");
//            // 跳转当前页
//            httpResponse.sendRedirect(domainUrl);
//            return;
//        }
//        // 获取cookie里面的tiket
//        Cookie[] cookies = httpRequest.getCookies();
//        String cookie_tiket = "";
//        if(cookies != null)
//        {
//            for(Cookie cookie : cookies)
//            {
//                if("tiket".equals(cookie.getName().trim()))
//                {
//                    cookie_tiket = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        // 如果cookie里面的tiket不为空
//        if(!ObjectUtil.isEmpty(cookie_tiket) && !"notiket".equals(cookie_tiket))
//        {
//            String userWeiIdObject = RedisUtil.getString(cookie_tiket + "_java");
//            if(userWeiIdObject != null)
//            {
//                RedisUtil.setExpire(cookie_tiket + "_java",60 * 30);// 延长时间
//                RedisUtil.setExpire(cookie_tiket + "_IBS",60 * 30);// 延长时间
//                // 添加redis缓存的时间
//                chain.doFilter(request,response);
//                return;
//            }
//            else
//            {
//                // 删除cookie里面的tiket
//                Cookie cookie = new Cookie("tiket","");
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//                httpResponse.addCookie(cookie);
//                Cookie cookie2 = new Cookie("tiket","");
//                cookie2.setMaxAge(0);
//                cookie2.setDomain(strDomain);
//                cookie2.setPath("/");
//                httpResponse.addCookie(cookie2);
//                // 没有找到用户信息，没有登陆，跳转登录页
//                httpResponse.sendRedirect(domainUrl);
//                return;
//            }
//        }
//        else
//        {
//            if("notiket".equals(cookie_tiket))
//            {
//                // 没有登陆，跳转登录页
//                httpResponse.sendRedirect("http://port.okwei.com/login.aspx?back=" + domainUrl);
//                return;
//            }
//            // 链接参数与cookie都没有tiket，去单点登陆站点获取
//            httpResponse.sendRedirect("http://port.okwei.com/GetTiket.aspx?back=" + domainUrl);
//            return;
//        }
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException
//    {
//        this.filterConfig = filterConfig;
//    }

}
