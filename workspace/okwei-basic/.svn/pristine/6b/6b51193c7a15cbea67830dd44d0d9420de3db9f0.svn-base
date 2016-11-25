package com.okwei.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.okwei.util.ObjectUtil;

/**
 * @ClassName:Pagination
 * @Description:页码标签类
 * @author xiehz
 * @date 2015年4月27日
 */
public class Pagination<T> extends TagSupport {

	private static final long serialVersionUID = 1L;

	private PageResult<T> pageResult;//分页对象
	
	private Boolean showSkip = false; //定制是否显示跳转页面
	
	private Boolean showCount = true; //定制是否显示总共条数

	public int doStartTag() throws JspException {
		if (ObjectUtil.isEmpty(pageResult) || pageResult.getTotalCount() < 1) {
			printPage("");
			return SKIP_BODY;
		}
		int pageId = pageResult.getPageIndex();
		// 拼写要输出到页面的HTML文本
		StringBuilder sb = new StringBuilder("<div class='pagination' ");
		sb.append(" size='");
		sb.append(pageResult.getPageSize());
		sb.append("'><ul>");

		/*sb.append("<span>共<label>");
		sb.append(pageResult.getTotalCount());
		sb.append("</label>条&nbsp;</span>");*/

		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(1);
		sb.append("'");
		if (!pageResult.getHasPrevious()) {
			sb.append(" class='disabled'");
		}
		sb.append(">首页</a></li>");
		sb.append("<li><a  href='javascript:;' pageId='");
		sb.append(pageResult.getPrev());
		sb.append("'");
		if (!pageResult.getHasPrevious()) {
			sb.append(" class='disabled'");
		}
		sb.append(">上一页</a></li>");
		int range_s = pageId - 5;
		int range_e = pageId + 4;
		if (range_s < 1) {
			range_e = range_e - range_s;
			range_s = 1;
		}
		if (range_e > pageResult.getPageCount()) {
			range_e = pageResult.getPageCount();
		}
		for (int i = range_s; i <= range_e; i++) {// 后置
			if (i == pageId) {
				sb.append("<li><a href='javascript:;' class='current disabled'>");
			} else {
				sb.append("<li><a href='javascript:;'>");
			}
			sb.append(i);
			sb.append("</a></li>");
		}

		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(pageResult.getNext());
		sb.append("'");
		if (!pageResult.getHasNext()) {
			sb.append(" class='disabled'");
		}
		sb.append(">下一页</a></li>");
		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(pageResult.getPageCount());
		sb.append("'");
		if (!pageResult.getHasNext()) {
			sb.append(" class='disabled'");
		}
		sb.append(">尾页</a></li>");
		
		
		/*sb.append("<span>&nbsp;共<label item='maxPageId'>");
		sb.append(pageResult.getPageCount());
		sb.append("页</label></span>");*/
		
		if(showSkip){
			sb.append("<li><a class='disabled' style='padding-bottom: 3px;'>到第&nbsp;<input style='width:50px;' type='text' id='pageId' name='pageId'>&nbsp;页 &nbsp;<input name='' type='button' act='gotoPage' value='确 定'></a></li>");
		}
		
		sb.append("<input type='hidden' id='pageId' name='pageId'/></ul>");
		sb.append("<span class='pageinfo'>共<em>");
		sb.append(pageResult.getPageCount());
		sb.append("</em>页");
		
		if (showCount) {
			sb.append("&nbsp;&nbsp;共" + pageResult.getTotalCount());
			sb.append("条信息</span>");
		} else {
			sb.append("</span>");
		}
		sb.append("</div>");
		// 把生成的HTML输出到响应中
		printPage(sb.toString());
		return SKIP_BODY; // 本标签主体为空,所以直接跳过主体
	}

	private void printPage(String sb) throws JspException {
		try {
			pageContext.getOut().println(sb);
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	public PageResult<T> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<T> pageResult) {
		this.pageResult = pageResult;
	}

	public boolean isShowSkip() {
		return showSkip;
	}

	public void setShowSkip(boolean showSkip) {
		this.showSkip = showSkip;
	}

	public Boolean getShowCount() {
		return showCount;
	}

	public void setShowCount(Boolean showCount) {
		this.showCount = showCount;
	}
}