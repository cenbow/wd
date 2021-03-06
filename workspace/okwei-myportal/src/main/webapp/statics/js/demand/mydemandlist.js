/**
 * 
 */
$(function(){
	//$("#txtState").val("");
	//分页
   	var page = new Pagination( {
		formId: "searcherForm",
		isAjax : true,
		targetId : "navTab",
		submitId:"submitBtn",
		validateFn:function(){
			return true;
		}
	});
   	page.init();
   	
   	//分享
    $("div[name='shareDemand']").mouseenter(function(){
        $(this).find(".mzh_fenxiang_no").attr("class","mzh_fenxiang_yes");
        $(this).find(".mzh_fenxiang").show();
    });
    $("div[name='shareDemand']").mouseleave(function(){
        $(this).find(".mzh_fenxiang_yes").attr("class","mzh_fenxiang_no");
        $(this).find(".mzh_fenxiang").hide();
    });
   	
	//状态选择
	$("#btnCheckStates li").bind("click",function(){
		$("#txtState").val($(this).attr("value"));	
		$("#submitBtn").click();
	});
	
	//全选
	$("#checkall").bind("click",function(){
		if($(this).prop("checked") ==true){
			$("input[name='checkDemand']").prop("checked","checked");
		}else{
			$("input[name='checkDemand']").removeProp("checked");
		}
	});
	
	//单个删除
	$("a[name='deleteDemand']").bind("click",function(_this){
		var demandID = $(this).closest("tr").find("input[name='checkDemand']").val();		
		layer.confirm("停止该需求招商后招商信息将失效，确定停止招商？",function(){
			layer.closeAll();
			editDemandState(demandID,3);	
		});		
	});
	
	//单个下架
	$("a[name='offShelfDemand']").bind("click",function(){
		var demandID = $(this).closest("tr").find("input[name='checkDemand']").val();
		editDemandState(demandID,2);		
	});
	
	//单个上架
	$("a[name='onShelfDemand']").bind("click",function(){
		var demandID = $(this).closest("tr").find("input[name='checkDemand']").val();
		editDemandState(demandID,1);		
	});
	
	//单个置顶
	$("a[name='topDemand']").bind("click",function(){
		var demandID = $(this).closest("tr").find("input[name='checkDemand']").val();
		demandTop(demandID);		
	});
	
	//批量上架
	$("#divbatch a").bind("click",function(){
		var demandIDs =[];
		$("input[name='checkDemand']:checked").each(function(){
			demandIDs.push($(this).val());
		});
		if(demandIDs.length <1){
			return;
		}
		
		var name =$(this).attr("name");
		if(name=="batchOnShelf"){
			editDemandState(demandIDs,1);
		}else if(name=="batchDelete"){
			layer.confirm("停止该需求招商后招商信息将失效，确定停止招商？",function(index){
				layer.closeAll();
				editDemandState(demandIDs,3);					
			});		
		}else if(name =="batchOffShelf"){
			editDemandState(demandIDs,2);
		}
		
	});
	
});


function editDemandState(demandIDs,state){
	demandIDs = demandIDs.toString();
	if(demandIDs ==""){
		return;
	}
	$.ajax({
        url: "/demand/editDemandState",
        type: "post",
        data: {demandIDStr:demandIDs,state:state },
        success: function (data) {          
            if(data ==null){
            	return ;
            }
            data = eval(data);
            if(data.state == "Success"){
            	//alert("成功");
            	$("#submitBtn").click();
            }else{
            	alert(data.message);
            }         
        },
        error: function () {
           alert("数据提交失败，请稍后重试");
        }
    });
}



function demandTop(demandID){
	$.ajax({
        url: "/demand/demandTop",
        type: "post",
        data: {demandID:demandID},
        success: function (data) {          
            if(data ==null){
            	return ;
            }
            data = eval(data);
            if(data.state == "Success"){
            	alert("成功");
            	$("#submitBtn").click();
            }else{
            	alert(data.message);
            }         
        },
        error: function () {
           alert("数据提交失败，请稍后重试");
        }
    });
}

//分享
function shareTo(type, title, url) {
	title += "【微店网】";
	if (type == "kj") {
		ShareToQzone(title, url, "");
	} else if (type == "tx") {
		ShareToTencent(title, url, "");
	} else if (type == "xl") {
		ShareToSina(title, url, "");
	}
}
