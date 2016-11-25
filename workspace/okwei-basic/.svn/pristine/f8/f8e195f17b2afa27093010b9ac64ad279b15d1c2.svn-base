package com.okwei.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import com.okwei.util.ObjectUtil;

/**
 * 
 * @ClassName: MySiteMeshFilter
 * @Description: 排除ueditor访问
 * @author xiehz
 * @date 2015年6月1日 下午5:35:18
 *
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String path = ((HttpServletRequest) request).getServletPath();
		if (ObjectUtil.isNotEmpty(path) && (path.indexOf("/ueditor/") > 0)) {
			filterChain.doFilter(request, response);
		} else {
			super.doFilter(request, response, filterChain);
		}
	}
}
