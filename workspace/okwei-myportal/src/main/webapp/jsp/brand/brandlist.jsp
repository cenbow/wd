<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌认证</title>

<link rel="stylesheet" type="text/css" href="/statics/css/jumei_usercenter.min.css" />
<script type="text/javascript">
$(function(){
	//赛选类型
	$(".prels").mouseleave(function(){
		$('#Fcount').hide();
	}).mouseenter(function(){
		$('#Fcount').show();
	});
	//类型筛选 点击事件
	$("#Fcount input").click(function(){
		getClass();
	});
	//类型确定 触发搜索
	$("#btnenter").click(function(){
		$("#searchBtn").click();
	});
	//文本框 回车键 触发筛选
	$("#brandName").keyup(function(event){
        if(event.keyCode == 13){
        	$("#searchBtn").click();
        }
    });
	//下拉框
/* 	$("#brandState").change(function(){
		$("#searchBtn").click();
	}); */
	//分类显示隐藏
	$(".shou").mouseleave(function(){
		$(this).find(".ft_lan").show();
		$(this).find(".ft_lan").nextAll().hide();
	}).mouseenter(function(){
		$(this).find(".ft_lan").hide();
		$(this).find(".ft_lan").nextAll().show();
	});
	
	//分页
   	var page = new Pagination( {
		formId: "searcherForm",
		isAjax : true,
		targetId : "navTab",
		submitId:"searchBtn",
		validateFn:function(){
			return true;
		}
	});
	page.init();
	
	getClass();
});

//获取选中的分类
function getClass(){
	var classIDs ="";
	var classNames = "";
	$("#Fcount input:checked").each(function(){
		if(classIDs ==""){
			classIDs = $(this).val();
			classNames = $(this).attr("className");
		}else{
			classIDs +="|" + $(this).val();
			classNames +=" " +$(this).attr("className");
		}
	});
	$("#clasString").val(classIDs);
	$("#Tpyeof").val(classNames);
}

</script>
</head>
<body>
<form id="searcherForm" name="searcherForm" action="/brand/brandlist" onsubmit="return false;">
    <div class="fr conter_right">     
      <div class="outermost zitiims">
      	<span>品牌认证</span>
        <a  target="_blank" href="/brand/submitbrand" style="float:left;margin:18px 0 0 10px;"><b>+</b>&nbsp;品牌认证</a>
      </div>
      <div class="conter_right_xx">
        <div class="xh-shurk" style="margin-top:0; height:auto;">
          <ul>
            <li><span>品牌名：</span>
              <input id="brandName" name="brandName" type="text" value="${dto.brandName }" class="btn_h24 w164" />
            </li> 
            <li>
              <span class="fl" style="line-height:28px;">所属分类：</span>
              <div class="fl prels">
              	<input type="text" id="Tpyeof"  readonly="readonly" />
              	<input type="hidden" id="clasString" name="clasString" value="${dto.clasString }">
              	<div class="abchbox none" id="Fcount" style="display:none;">
              	<c:if test="${classList !=null && classList.size()>0 }">
              		<c:forEach items="${classList }" var="parent">
              		<div class="clear"></div>
              		<p class="f14 ft_c3">${parent.parentCName }</p>
              		<c:if test ="${parent.classChildVOs !=null && parent.classChildVOs.size()>0 }">
              		<div class="spabels">
              			<c:forEach items="${parent.classChildVOs }" var="child"> 
              			<span>
              				<input value="${child.classID }" id="class${child.classID}" className="${child.className }"
                    			   ${child.checked==true ?'checked="checked"':'' } type="checkbox" />&nbsp;&nbsp;
                            <label for="class${child.classID}">${child.className }</label>
            			</span>  
              			</c:forEach>
                    </div>
                    </c:if>
              		</c:forEach>
              	</c:if> 
              		<div class="clear"></div>               	
                    <a href="javascript:void(0);" class="dis_b btn_wear30_pre" id="btnenter" style="margin-left:106px;">确定</a>                
                </div>            
              </div>
            </li>
            
            <li><span>状态：</span>
              <select id="brandState" name="brandState" class="btn_h30 w164 mr_10">
              	<option value="-1">全部</option>
              	<option value="0" ${dto.brandState ==0 ?"selected='selected'":"" } >待审核</option>
              	<option value="1" ${dto.brandState ==1 ?"selected='selected'":"" }>通过</option>
              	<option value="2" ${dto.brandState ==2 ?"selected='selected'":"" }>不通过</option>
              </select>
            </li>
            <li>
              <input type="submit" id="searchBtn" value="查询" class="btn_submit_two" style="width: 80px;" />
            </li>
          </ul>
        </div>
      </div> 
      <div class="outermost bg_white mt13 fl tabnes">
      	<table>
        	<tr>
            	<td>品牌LOGO</td>
                <td>品牌名</td>
                <td width="300px;">所属分类</td>
                <td>状态</td>
                <td>操作</td> 
            </tr>   
            <c:if test="${page !=null && page.getList() !=null && page.getList().size()>0}">
            <c:forEach items="${page.getList()}" var="brand">
            <tr>
            	<td><img src="${brand.brandLOGO }" width="100" height="60" /></td>
                <td>${brand.brandName }</td>
                <td>
                	<div name="Idues" class="shou">
                		<c:if test="${brand.cfbVO !=null && brand.cfbVO.size()>0 }">
                		 <c:forEach items="${brand.cfbVO}" var="parent" varStatus="parentIndex">
                		 	<c:if test="${parentIndex.index<2 }">
                		 	<p><b class="mr_10">${parent.parentCName}</b>
                		 		<c:if test="${parent.classChildVOs !=null &&  parent.classChildVOs.size()>0}">
                		 		<c:forEach items="${parent.classChildVOs }" var="child">
		                    		${child.className }
		                    	</c:forEach>
                		 		</c:if>
                		 	</p>
                		 	</c:if>
                		 </c:forEach>
                		</c:if>
                		<c:if test="${brand.cfbVO !=null && brand.cfbVO.size()>2 }">
                        <span class="ft_lan" id="Slh_0">...</span>
                        <c:forEach items="${brand.cfbVO}" var="parent" varStatus="parentIndex"> 
                         <c:if test="${parentIndex.index>1 }">               
                        <div class="none" id="Buet_0">                      
                        	<p><b class="mr_10">${parent.parentCName}</b>
                        	<c:if test="${parent.classChildVOs !=null &&  parent.classChildVOs.size()>0}">
                		 		<c:forEach items="${parent.classChildVOs }" var="child">
		                    		${child.className }
		                    	</c:forEach>
		                    </c:if>
                        	</p>
                        </div>     
                        </c:if>            
                         </c:forEach>
                        </c:if>
  
                    </div>
                </td>
                <td>${brand.stateName }</td>
                <td><a target="_blank" href="/brand/brandinfo?brandID=${brand.brandID }" class="ft_lan">查看详情</a></td> 
            </tr>
            </c:forEach>
            </c:if>
        </table>
      </div> 
     	<div class="pull-right">
		<pg:page pageResult="${page}" />
	</div>
	<c:if test="${page ==null || page.getList() ==null || page.getList().size()==0 }">
      <!-- 1-5-2 品牌认证(无) -->
      <div class="outermost mt_47">
      	<div class="f18 mt_30 pt20 outermost tc fl">你还未提交相关品牌认证</div>
        <div class="fl mt_20"><a target="_blank" href="/brand/submitbrand" class="btn_yes42_pre dis_b f14" style="margin-left:446px;">马上提交</a></div>
      </div>
      </c:if>
    </div>
</form>
</body>
</html>