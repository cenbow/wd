	
$(function(){
	
//	ml_20     id_allYes 
		//全选
		$("[name='id_allYes']").on("click",function(){
			$("[name='cls']").attr("checked",this.checked); 
			$("[name='id_allYes']").attr("checked",this.checked); 
			//统计选中的个数
			var i=0;
			//总价
			var total=0;
			$("input[name='cls']").each(function(){
				if(this.checked){ 
					total+=parseFloat($(this).parents("tr").find(".ft_red").html());
					i++;} 
			}); 
			if (i>0) {
				$(".wind_two a").css("background","#e60000");
			}else{
				$(".wind_two a").css("background","#c0c0c0");
			}
			//展示统计个数
			$("#valuse").html(i);
			$("#total").text(roundFun(total,2));
		});
		 
		
		$("[name=cls]").on("click",function(){
			var s= $("[name=cls]").size(); 
			//总价
			var total=0;
			//统计选中的个数
			var i=0;
			$("input[name='cls']").each(function(){
				if(this.checked){
				total+=parseFloat($(this).parents("tr").find(".ft_red").html());
				i++;
				} 
				
			}); 
			if (i>0) {
				$(".wind_two a").css("background","#e60000");
			}else{
				$(".wind_two a").css("background","#c0c0c0");
			}
			 //展示统计个数
			$("#valuse").html(i);
			$("#total").text(roundFun(total,2));
			if(s==i){
				$("[name='id_allYes']").attr("checked",true);   
			}else{
				$("[name='id_allYes']").attr("checked",false);   
			}
			
		})
	

	function roundFun(numberRound, roundDigit) // 四舍五入，保留位数为roundDigit
	{
		if (numberRound >= 0) {
			var tempNumber = parseInt((numberRound * Math.pow(10, roundDigit) + 0.5))
					/ Math.pow(10, roundDigit);
			return tempNumber;
		} else {
			numberRound1 = -numberRound
			var tempNumber = parseInt((numberRound1 * Math.pow(10, roundDigit) + 0.5))
					/ Math.pow(10, roundDigit);
			return -tempNumber;
		}
	}  	
		
	// 条件选择
	$(".left_xuanzs>ul>li").on("click", function() {
		// 如果点击的条件没有是没有选中，并且不为0
		if (!$(this).hasClass("yes_bgs") && !$(this).hasClass("zero")) {
			$(this).addClass("yes_bgs").siblings().removeClass("yes_bgs");
			
			var datatype = $("#type>li.yes_bgs").attr("data-type"); 
		 
			if ($("#yearTimeStr").val().length>0) {
				if ($("#monthTimeStr").val().length>0) {
					window.location.href = "rewardLists?dt=" + datatype+"&yearTimeStr="+ $("#yearTimeStr").val()+"&monthTimeStr="+ $("#monthTimeStr").val();
					return;
				}
				window.location.href = "rewardLists?dt=" + datatype+"&yearTimeStr="+ $("#yearTimeStr").val();
				return;
			}
			window.location.href = "rewardLists?dt=" + datatype ;
			return;
			}
	});
	
	
	$("#yearTimeStr").focusout(
			function(){ 
			if($("#yearTimeStr").val().length>0){
					$("#monthTimeStr").removeAttr("disabled");
				}else{
					$("#monthTimeStr").attr("disabled","disabled");;
				} 
//			
//			var datatype = $("#type>li.yes_bgs").attr("data-type"); 
//			var yearTimeStr = $("#yearTimeStr").val(); 
//			alert(yearTimeStr);
//			window.location.href = "rewardLists?dt=" + datatype+"&yearTimeStr"+yearTimeStr ;
		});
 
	
})
function getOrderNo1(ss){
	  $.ajax({
		    url: "/reward/getOrderNO",
		    type: "post",
		    data:{cls:ss},
		    dataType : 'json',
		    success: function (data) {
		    	var i=0;
		        if(data.state == 1){ 
		        	//跳转支付
		        	window.location.href = $("#paydomain").val() +"/pay/cashier?orderNo="+data.msg ;
		        }else{
		        	 alert(data.msg);
		        }
		        //全选多少个就减多少
		        $(".yes_bgs").find("b").html(ss-i);
		    },
		    error: function () {
		        alert("数据提交失败！请稍后重试！");
		    }
		});  
	
}

function getOrderNo(){
	var ds=document.getElementsByName("cls"); 
	  var strinh="";
	  $("input[name=cls]").each(function(index, element) { 
				if(this.checked){ 
					 strinh+=this.value+",";
					}
	    }); 
	  $.ajax({
		    url: "/reward/getOrderNO",
		    type: "post",
		    data:{cls:strinh},
		    dataType : 'json',
		    success: function (data) {
		    	var i=0;
		        if(data.state == 1){ 
		        	//跳转支付
		        	window.location.href = $("#paydomain").val() +"/pay/cashier?orderNo="+data.msg ;
		        }else{
		        	 alert(data.msg);
		        }
		        //全选多少个就减多少
		        $(".yes_bgs").find("b").html(ss-i);
		    },
		    error: function () {
		        alert("数据提交失败！请稍后重试！");
		    }
		});  
	
}
function onClear(){
	window.location.href = "/reward/rewardLists";
}
// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}