package com.okwei.detail.web.filter;

import com.okwei.web.filter.BaseSSOFilter;

public class SSOFilter extends BaseSSOFilter {

    public SSOFilter(){
        super.isEnabled = false;
        super.isSubEnabled = false; 
    }  
//
//    FilterConfig filterConfig = null;
//
//    @Override
//    public void destroy() {
//	this.filterConfig = null;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//	request.setCharacterEncoding("utf-8");
//	response.setCharacterEncoding("utf-8");
//	HttpServletRequest httpRequest = (HttpServletRequest) request;
//	HttpServletResponse httpResponse = (HttpServletResponse) response;
//	/* 测试用 */
//
//	/*--------用的时候删除上面的------------*/
//
//	// 获取URL
//	String domainUrl = httpRequest.getRequestURL().toString();
//	// 获取参数tiket
//	String tiket = httpRequest.getParameter("tiket");
//	ResourceBundle bundle = ResourceBundle.getBundle("domain");
//	String strDomain = "";
//	if (bundle == null) {
//	    strDomain = ".okwei.com";
//	} else {
//	    strDomain = "." + bundle.getString("okweidomain");
//	}
//	// 如果链接的参数tiket不为空
//	if (!ObjectUtil.isEmpty(tiket)) {
//	    // 删除cookies
//	    Cookie cookie2 = new Cookie("tiket", tiket);
//	    cookie2.setMaxAge(0);
//	    cookie2.setPath("/");
//	    // cookie.setDomain("okwei.net");
//	    httpResponse.addCookie(cookie2);
//	    // 设置cookies
////	    Cookie cookie = new Cookie("tiket", tiket);
////	    cookie.setMaxAge(360000);
////	    cookie.setPath("/");
////	    // cookie.setDomain("okwei.net");
////	    httpResponse.addCookie(cookie);
//	    // 设置cookies
//	    Cookie cookie3 = new Cookie("tiket", tiket);
//	    cookie3.setMaxAge(360000);
//	    cookie3.setPath("/");
//	    cookie3.setDomain(strDomain);
//	    httpResponse.addCookie(cookie3);
//	    // 截取字符串
//	    domainUrl = domainUrl.replaceAll("(\\?|&){1,}tiket=.*", "");
//	    // 跳转当前页
//	    httpResponse.sendRedirect(domainUrl);
//	    return;
//	}
//
//	chain.doFilter(request, response);
//	return;
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//	this.filterConfig = filterConfig;
//    }

}
