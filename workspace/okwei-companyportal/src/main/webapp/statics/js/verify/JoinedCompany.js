$(function(){
	
	//进度条
	ProgressBarInit();	
	//分页
   	var page = new Pagination( {
		formId: "searcherForm",
		isAjax : true,
		targetId : "navTab",
		submitId:"searchBtn",
		validateFn:checkInfo
	});
	page.init();
	//弹层
	$('.rlsiz').mouseover(function(){
		$(this).children('div').removeClass('none');
	}).mouseout(function(){
		$(this).children('div').addClass('none');
	});
	//checkBox 单选 触发筛选
	$("input[name='quickQuery']").click(function(){
		if($(this).prop("checked") ==true){
			$("#txtWeiID").val("");
			$("#txtCompanyName").val("");
			$("input[name='quickQuery']").prop("checked",false);
			$(this).prop("checked",true);
			$("#searchBtn").click();
		}
	});
	
	//文本框 回车键 触发筛选
	$("#txtWeiID,#txtCompanyName").keyup(function(event){
        if(event.keyCode == 13){
/*         	if($("#txtWeiID").val() !="" || $("#txtCompanyName").val() !=""){
        		$("#searchBtn").click();
        	}   */    
        	$("#searchBtn").click();
        }
    });
	
	//批发价数量文本框限制
	$("#txtWeiID").bind("keyup",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  

});

var checkInfo = function checkInfo(){
	//alert("xxx");
	return true;
}

function ProgressBarInit(){
	var joinCount = $("#txtMothJoined").val();
	if(joinCount =="" || joinCount==null){
		joinCount =0;
	}
	$("#spanThisMonthNum").html(joinCount);
	var imgWidth = $(".rule_two").width()-10;
	var divwidth = $(".tis_fot").width();
	if(joinCount ==0)
    {
         $(".bg_nebai").css("width","0%");
         $(".rel_usb").css("left","0px");
    }
    else if(joinCount>19)
    {
         $(".bg_nebai").css("width","100%");
         $(".rel_usb").css("left",imgWidth+"px");
         $(".tis_fot").css("left",(imgWidth-divwidth) +"px");
    }
    else
    {
        var percentage = joinCount/20;
        var fourDigit = percentage.toFixed(4);
        var result = fourDigit.slice(2,4)+"."+fourDigit.slice(4,6)+"%";
        $(".bg_nebai").css("width",result);
        $(".rel_usb").css("left",(imgWidth*fourDigit)+"px");
        $(".tis_fot").css("left",((imgWidth-divwidth)*fourDigit) +"px");
    }
	    
    if(joinCount<5)
    {
        $("#rewardAmout").html("500");
    }
    else if(joinCount<10)
    {
    	 $("#rewardAmout").html("1200");
    }
    else if(joinCount>=10)
    {
    	 $("#rewardAmout").html("2000");
    }
    else{
    	 $("#rewardAmout").html("0");
    }
}
