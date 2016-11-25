	
$(function(){
	// 条件选择
	$(".left_xuanzs>ul>li").on("click", function() {
		// 如果点击的条件没有是没有选中，并且不为0
		if (!$(this).hasClass("yes_bgs") && !$(this).hasClass("zero")) {
			$(this).addClass("yes_bgs").siblings().removeClass("yes_bgs");
			
			var datatype = $("#type>li.yes_bgs").attr("data-type"); 
			
			window.location.href = "cashCoupon?dt=" + datatype ;
			}
	});
	 
	
	//全选
	$("#id_allYes").on("click",function(){
		$("[name=cls]").attr("checked",this.checked); 
	}); 
	
	$("[name=cls]").on("click",function(){
		var s= $("[name=cls]").size();   
		//统计选中的个数
		var i=0;
		$("input[name=cls]").each(function(){
			if(this.checked){  i++; } 
		});   
		 
		if(s==i){
			$("#id_allYes").attr("checked",true);   
		}else{
			$("#id_allYes").attr("checked",false);   
		}
		
	});
	
	
})

	//多选删除
	function deleteCashCoupon(){ 
		var ds=document.getElementsByName("cls");
		  var arr=new Array(ds.length);
		  var strinh="";
		  $("input[name=cls]").each(function(index, element) { 
					if(this.checked){
						 arr[index]=this.value;
						 strinh+=this.value+",";
						}
		    }); 
		  //获取该类型的数量
		 var ss = $(".yes_bgs").find("b").html();
		 $.ajax({
		    url: "/cashCoupon/delete",
		    type: "post",
		    data:{cls:strinh},
		    dataType : 'json',
		    success: function (data) {
		    	var i=0;
		        if(data.state =="Success"){ 
		        	  $("input[name=cls]").each(function(index, element) { 
							if(this.checked){  
							 	$(element).parents("tr").remove();i++;
								}
				    });
		        }
		        //全选多少个就减多少
		        $(".yes_bgs").find("b").html(ss-i);
		    },
		    error: function () {
		        alert("数据提交失败！请稍后重试！");
		    }
		});  
	};
	
	//删除单个记录
	function delteCashCoupon1(ss,object){ 
		   $.ajax({
			    url: "/cashCoupon/delete",
			    type: "post",
			    data:{cls:ss},
			    dataType : 'json',
			    success: function (data) {
			        if(data.state =="Success"){ 
						$(object).parents("tr").remove(); 
						 $(".yes_bgs").find("b").html(ss-1);
					    }else{
					    	alert("删除数据失败！请稍后重试！");
					    };
			        }
			     ,
			    error: function () {
			        alert("数据提交失败！请稍后重试！");
			    }
			});   
	};



// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}