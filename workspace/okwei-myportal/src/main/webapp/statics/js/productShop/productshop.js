//微店号文本框失去焦点时获得店铺名
$("[name=weiId]").blur(function(){
	if($.trim($(this).val())!=""){
		if(isNaN($(this).val())){
			$("#promptID").text("请输入正确的微店号");
			$("#shopNameID").text("");
			return;
		}
		$.ajax({
			type:"post",
			url:"/productShop/getShopName?weiId="+$(this).val(),
			success:function(result){
				var result = eval(result);
				if(result!=null){
					if(result.Statu == "Success")
		    		{   
		    			$("#shopNameID").text(result.BaseModle);
		    			$("#promptID").text(result.StatusReson);
		    		}
		    		else
		    		{
		    			$("#shopNameID").text("");
		    			$("#promptID").text(result.StatusReson);
		    		}
				}
			}
		})
	}
})
//添加落地店的弹层
function addProductShopDivLayer(title,win_id){
	$("[name=weiId]").val("");
	$("input[type=checkbox]").prop("checked",false);
	$("#shopNameID").text("");
	$("#promptID").text("");
	var pagei = $.layer({
	    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	    btns: 2,
		btn: ['提交','取消'],
	    title: title,
	    border: [0],
	    closeBtn: [0],
		closeBtn: [0, true],
	    shadeClose: false,
	    area: ['600','400'], 
	    page: {dom : '#'+ win_id},
		yes: function(){
			addProductShopAjax();
			layer.close(pagei);
		}
		});
}
function addProductShopAjax(){
	if($("#promptID").text()!=""){
		return;
	}
	if($.trim($("[name=weiId]").val())==""){
		alert("微店号不能为空");
		return;
	}
	$.ajax({
		type:"post",
		url:"/productShop/addProductShop",
		data:$("#addProductShopForm").serialize(),
		success:function(result){
			var result = eval(result);
			if(result!=null){
				if(result.Statu == "Success")
	    		{   
	    			alert("成功!",true);
	    			location.href='/productShop/getDownstreamStoreList/1';
	    		}
	    		else
	    		{
	    			alert(result.StatusReson);
	    		}
			}
		}
	})
}

//全选
function singleCheck(){
	var flag=true;
	var arr=$("[name=demandIdArr]");
	for(var i=0;i<arr.length;i++){
		if($(arr[i]).prop("checked")==true){
			flag=true;
		}else{
			flag=false;
			$("#checkAllDemand").prop("checked",flag);
			return;
		}
	}
	$("#checkAllDemand").prop("checked",flag);
}