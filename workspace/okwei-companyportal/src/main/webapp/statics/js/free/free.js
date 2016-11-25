/**
 * 
 */
$(function() {
	
	$(".mzh_ymzssy_6_mfsy").click(function(){
		freeget();
	});
	
	$(".mzh_ymzssy_6_mfsy_half").click(function(){
		halfget();
	});
	
});

function halfget()
{
	var weiid=$("#weiid").val();
	var porturl=$("#porturl").val();
	if(weiid<=0)//跳转登陆页面
	{
		url=porturl+"?back="+window.location.href;
		window.location.href=url;
		return;
	}
	//获取用户是否已经领取过药品
	$.ajax({
		url : "/free/CheckTaste",
		type : "post",
		data : {
			weiid:weiid,
			type:2
		},
		success : function(result) {			
			if(result.msg =="1"){
				window.location.href="/free/halftaste"
			}else if (result =="-1") {
				alert("该活动提供半价试用一次，您已提交过申请!");
			}else if (result =="2") {
				alert("登录已过期,请重新登录！");
			}
		},
		error : function() {
			alert("数据请求失败！请稍后重试！");
		}
	});
}
function freeget()
{
	var weiid=$("#weiid").val();
	var porturl=$("#porturl").val();
	if(weiid<=0)//跳转登陆页面
	{
		url=porturl+"?back="+window.location.href;
		window.location.href=url;
		return;
	}
	//获取用户是否已经领取过药品
	$.ajax({
		url : "/free/CheckTaste",
		type : "post",
		data : {
			weiid:weiid,
			type:1
		},
		success : function(result) {			
			if(result.msg =="1"){
				window.location.href="/free/taste"
			}else if (result =="-1") {
				alert("该活动提供免费试用一次，您已提交过申请!");
			}else if (result =="2") {
				alert("登录已过期,请重新登录！");
			}
			else if (result =="3") {
				alert("该活动已结束，感谢关注！");
			}
		},
		error : function() {
			alert("数据请求失败！请稍后重试！");
		}
	});
	
};
