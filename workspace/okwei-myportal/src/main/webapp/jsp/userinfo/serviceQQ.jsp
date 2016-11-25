<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客服QQ设置</title>
<script type="text/javascript">
	$(function(){
		//添加QQ栏位
		$("#btnAddQQ").click(function(){			
			if($(".sel_qq li .kefiesd2 input").length <4){
				$(this).closest("li").before($("#tempLi").html());
			}
		});
		//删除QQ栏位
		$(".sel_qq li .kefiesd3 img").live("click",function(){
			$(this).closest("li").remove();		
		});
		
		$("#btnSubmit").bind("click",function(){
			submitQQ();
		});
	});
	
	//手机号 输入限制
	$(".sel_qq li .kefiesd2 input").live("keyup",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).live("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	
	//提交QQ
	function submitQQ(){
		var qqs = "";
		$(".sel_qq li .kefiesd2 input").each(function(){
			var val =  $(this).val();
			if(val =="" || val==null){
				return;
			}
			
			if(qqs ==""){
				qqs = val;
			}else{
				qqs +="|" + val;
			}
		});
		if(qqs ==""){
			alert("请设置客服QQ");
			return;
		}
		$("#btnSubmit").unbind("click");
		$.ajax({
		    url: "/userinfo/saveServiceQQ",
		    type: "post",
		    data: {
		    	QQs:qqs 	
	    	},
		    dataType : 'json',
		    success: function (data) {
		        if(data.state =="Success"){
		        	alert("保存成功",true);	        	
		        }else{
		        	alert(data.message);
		        }
	        	$("#btnSubmit").bind("click",function(){
	        		submitQQ();
	        	});
		    },
		    error: function () {
		        alert("数据提交失败！请稍后重试！");
		    	$("#btnSubmit").bind("click",function(){
		    		submitQQ();
		    	});
		    }
		});	
	}
</script>
</head> 
<body>
<div class="bor_si fl bg_white">
    	<div class="order_title fl f16">客服QQ设置 <div class="abs_img"><img src="/statics/images/img_absicon.png" /></div> </div>
        <div class="sel_qq ibsadd fl">
        	 <p class=" f14 ft_c9 pl mt_30" style="padding-left:70px;">最多设置4个客服QQ，将在商品详情中显示，方便买家进行咨询交流</p>
            <ul>
               <c:if test="${QQs !=null && fn:length(QQs)>0 }">      
               <c:forEach items="${QQs}" var="item" varStatus="temp">
               <li>               
				   	<span class="kefiesd1">客服QQ</span>
				       <span class="kefiesd2"><input type="text" maxlength="20" value="${item }" class="btn_h28 w250" /></span>
				       <c:if test="${temp.index >0 }">
				       <span class="kefiesd3"><img src="/statics/images/img_lan002.png" width="20" height="20" /></span>
						</c:if>
				</li>
				</c:forEach>
               </c:if>
               <c:if test="${QQs ==null && fn:length(QQs) ==0 }">
            	<li>
               		<span class="kefiesd1">客服QQ</span>
                	<span class="kefiesd2"><input value="${item }" maxlength="20" type="text" class="btn_h28 w250" /></span>                  
               </li>
              	</c:if>
              	<li style="margin-left:80px; margin-bottom:0; height:36px;">
               		<a href="javascript:;" id="btnAddQQ"><b class="f18">+</b>添加</a>
              	</li>
               <li style="margin-left:80px;"><input id="btnSubmit" type="submit" value="保存" class="buesBot" /></li>
            </ul>                    
       </div>
   </div> 
   <!-- 模板区 -->
   <ul id="tempLi" style="display:none;">
	   	<li>
	   	<span class="kefiesd1">客服QQ</span>
	       <span class="kefiesd2"><input maxlength="20" type="text" class="btn_h28 w250" /></span>
	       <span class="kefiesd3"><img  src="/statics/images/img_lan002.png" width="20" height="20" /></span>
	   </li>
   </ul>
</body>
</html>