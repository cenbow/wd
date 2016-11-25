<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招商需求管理</title>
<script type="text/javascript" src="/statics/js/common/share.js"></script>
<script type="text/javascript" src="/statics/js/demand/mydemandlist.js"></script>
<style type="text/css">
.ndfxs_1-2_fenye div{
	float:right;
}
.mzh_fenxiang_no {
    border: none;
    padding: 0px;
    float: none;
    margin-left: 0px;
    position: relative;
}
.mzh_fenxiang_yes {
    border: none;
    padding: 0px;
    float: none;
    margin-left: 0px;
    position: relative;
}
.mzh_fenxiang{top: 15px;}
</style>
</head>
<body>
<form id="searcherForm" name="searcherForm" action="/demand/myDemandList" onsubmit="return false;">
<input type="hidden" name="state" id="txtState" value="${state }">
<input type="hidden" id="submitBtn">
<div class="fr conter_right">
      <div class="zhuag_suv bor_si fl bg_white">
      	<div class="oneci_ztai fl">
        	<div class="left_font tr fl f12 ft_c9">状态：</div>
            <div id="btnCheckStates" class="left_xuanzs fl f12">
            	<ul>
          			<li value="1" name="mzh_4_7_yes" class="${state ==1?'yes_bgs':'' }" >正在招商（<b class="left_xuanzs_b">${ShowingCount}</b>）</li>
          			<li value="2" name="mzh_4_7_yes" class="${state ==2?'yes_bgs':'' }">停止招商（<b class="left_xuanzs_b">${OffShelfCount}</b>）</li>
          			<li value="0" name="mzh_4_7_yes" class="${state ==0?'yes_bgs':'' }">草稿箱（<b class="left_xuanzs_b">${DraftCount}</b>）</li>
          			<li value="4" name="mzh_4_7_yes" class="${state ==4?'yes_bgs':'' }">待审核（<b class="left_xuanzs_b">${WaitCount}</b>）</li>
          			<li value="5" name="mzh_4_7_yes" class="${state ==5?'yes_bgs':'' }">未通过（<b class="left_xuanzs_b">${NoPass}</b>）</li>
                </ul>
            </div>
        </div>
      </div>
      <!-- 正在招商 -->
     
      <div class="gygl_xxk mt_20 bor_le bor_ri" id="id_wdcp_0" style="display: block;border-bottom: none;">          
        <table class="gygl_xxk_table bor_cls">
        <c:choose>
        <c:when test="${state==1 || state==2 }">
          <tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
            <td>图片</td>
            <td>招商标题</td>
            <td>招商地区</td>
            <td>已加盟代理商</td>
            <td>已加盟落地店</td>
            <td>操作</td>
          </tr>
          </c:when>
          <c:when test="${state==0}">
           <tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
                  <td>图片</td>
                  <td>招商标题</td>
                  <td>招商地区</td>
                  <td>操作</td>
              </tr>
          </c:when>
          <c:otherwise>
          	<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
                  <td>图片</td>
                  <td>招商标题</td>
                  <td>招商地区</td>
                  <td>申请状态</td>
                  <td>操作</td>
              </tr>
          </c:otherwise>
          </c:choose>
          <c:if test="${page !=null && page.list !=null && page.list.size()>0 }"> 
          <c:forEach items="${page.list }" var="item">
          <c:choose>
          	<c:when test="${state==1 || state ==2}">
          	<tr class="ndfxs_1-2_border">
            <td class="gygl_xxk_table_cz_td"><div class="gygl_xxk_table_cz_qx">
                <input name="checkDemand" type="checkbox" value="${item.demandId }" class="gygl_xxk_table_cz_qx_text"/>
                <img src="${item.mainImage }"/> </div></td>
            <td class="gygl_xxk_table_cz_td">
                <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.title }</div>
            </td>
            <td class="gygl_xxk_table_cz_td">
                <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.areaString }</div>
            </td>
            <td class="gygl_xxk_table_cz_td"><a href="javascript:;" class="gygl_xxk_table_cz_sj lin_28">${item.agentCount ==null ?0:item.agentCount }个</a></td>
            <td class="gygl_xxk_table_cz_td"><a href="javascript:;" class="gygl_xxk_table_cz_sj lin_28">${item.shopCount ==null ?0:item.shopCount }个</a></td>
            <td class="gygl_xxk_table_cz_td">
                <div class="gygl_xxk_table_cz_sj lin_28" style="width: 95px">
                    <div class="fl ft_lan ml_10 mb_10">
                        <div class="fl" name="shareDemand">
                            <div class="mzh_fenxiang_no" id="aaa">
                                <span class="fl">分享</span>
                                <div class="mzh_fenxiang" style="display: none;">
                                    <a href="javascript:shareTo('kj','${item.title }','http://${item.weiId}.<%=okweidomain%>/demand/demandInfo?demandID=${item.demandId}');"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
                                    <a href="javascript:shareTo('tx','${item.title }','http://${item.weiId}.<%=okweidomain%>/demand/demandInfo?demandID=${item.demandId}');"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
                                    <a href="javascript:shareTo('xl','${item.title }','http://${item.weiId}.<%=okweidomain%>/demand/demandInfo?demandID=${item.demandId}');"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- <a name="shareDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">分享</a> -->
                    <a name="editDemand" href="/demand/releasedemand?demandID=${item.demandId }" class="fl ft_lan ml_10 mb_10">编辑</a>
                    <c:if test="${state==1 }">
                    <a name="topDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">置顶</a>                    
                    <a name="offShelfDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10" onclick="win('提示','win_div_1','520px','250px')">停止招商</a>
                	</c:if>
                	<c:if test="${state==2 }">
                	<a name="onShelfDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">恢复</a>
                	<a name="deleteDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">删除</a>
                	</c:if>
                </div>
            </td>
          </tr>
          	</c:when>
          	<c:when test="${state==0 }">
          	<tr class="ndfxs_1-2_border">
                  <td class="gygl_xxk_table_cz_td"><div class="gygl_xxk_table_cz_qx">
                      <input name="checkDemand" value="${item.demandId }" type="checkbox" class="gygl_xxk_table_cz_qx_text"/>
                      <img src="${item.mainImage }"/> </div></td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.title }</div>
                  </td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.areaString }</div>
                  </td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj lin_28" style="width: 95px">
                          <a name="editDemand" href="/demand/releasedemand?demandID=${item.demandId }" class="fl ft_lan ml_10 mb_10">编辑</a>
                          <a name="deleteDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">删除</a>
                      </div>
                  </td>
              </tr>
          	</c:when>
          	<c:otherwise>
          	<tr class="ndfxs_1-2_border">
                  <td class="gygl_xxk_table_cz_td"><div class="gygl_xxk_table_cz_qx">
                      <input name="checkDemand" value="${item.demandId }" type="checkbox" class="gygl_xxk_table_cz_qx_text"/>
                      <img src="${item.mainImage }"/> </div></td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.title }</div>
                  </td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28">${item.areaString }</div>
                  </td>
                  <td class="gygl_xxk_table_cz_td">
                  	<c:if test="${state==4 }">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28 ft_c9">待审核</div>
                      </c:if>
                      <c:if test="${state==5 }">
                      <div class="gygl_xxk_table_cz_sj_fx lin_28 ft_c9">未通过（${item.noPassReason}）</div>
                      </c:if>
                  </td>
                  <td class="gygl_xxk_table_cz_td">
                      <div class="gygl_xxk_table_cz_sj lin_28" style="width: 95px">
                      	<a name="editDemand" href="/demand/releasedemand?demandID=${item.demandId }" class="fl ft_lan ml_10 mb_10">编辑</a>
                      	<a name="deleteDemand" href="javascript:;" class="fl ft_lan ml_10 mb_10">删除</a>
                      </div>
                  </td>
              </tr>
          	</c:otherwise>
          </c:choose>       
          </c:forEach>  
          <tr class="ndfxs_1-2_border td_no">
                <td colspan="8">
                    <div class="fiex_sel" id="divbatch">
                        <input id="checkall" type="checkbox" class="dis_b fl ml_10 mt13">
                        <label for="checkall" class="dis_b fl ml_5 mt13 ft_c6" style="margin-top: 13px;">全选</label>
                        <c:choose>
                        <c:when test="${state ==2 }">
                        	<a name="batchOnShelf" class="dis_b ml_20 fl mt_5 btn_hui28_pre shou">恢复</a>
                       	 	<a name="batchDelete" class="dis_b ml_10 fl mt_5 btn_hui28_pre shou">删除</a>
                        </c:when>
                        <c:when test="${state ==1 }">
                        	<a name="batchOffShelf" class="dis_b ml_20 fl mt_5 btn_hui28_pre shou">停止招商</a>
                        </c:when>
                        <c:otherwise>
                         	<a name="batchDelete" class="dis_b ml_10 fl mt_5 btn_hui28_pre shou">删除</a>
                        </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>  
            </c:if>     
        </table>
        
        <c:if test="${page ==null || page.list==null || page.list.size() <1 }">
        	<div style="width:100%;height:400px;text-align:center;font-size:18px;line-height:400px;">
        		暂无相关数据
        	</div>
        </c:if>
      </div>
      <div class="ndfxs_1-2_fenye">
		<pg:page pageResult="${page}" />
      </div>
    </div>
</form>
</body>
</html>