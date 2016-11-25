$(function(){
	/* 首页-分享 */
	$("#mzh_fenxiang").mouseover(function(){
	    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_yes");
	    $(".mzh_fenxiang").show();
	})
	$("#mzh_fenxiang").mouseout(function(){
	    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_no");
	    $(".mzh_fenxiang").hide();
	})
});
