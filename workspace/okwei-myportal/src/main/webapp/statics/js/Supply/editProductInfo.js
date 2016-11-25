var isPostting = false;
var pubProductType;//按用户身份编辑发布产品
$(function(){
	pubProductType = $("#pubProductType").val();
	//防止被缓存
	$("#cboxIsShowParam").prop("checked",false);
	$("#box_1").prop("checked",false);
	$("#box_2").prop("checked",false);
	$("#box_3").prop("checked",false);
		
	//编辑类目 模板
	$("#btnSelectClass,#btnSelectPModel").click(function(){
		if ($("#productType").val() == 1 && pubProductType == 3) {
			location.href="/publishProduct/index";
			return;
		}
		if($("#txtProductTitle").val() !=""){
			$.layer({
				title:'离开确认',
				shade: [0.2, '#000'],
			    area: ['auto','auto'],
			    border: [0],
			    //shadeClose: true,
			    dialog: {
			        msg: '编辑分类或模板将会离开该页面！</br>是否需要保存草稿？',
			        btns: 2,                    
			        type: 4,
			        btn: ['保存','不保存'],
			        yes: function(){
			        	if ($("#productType").val() == 1) {
			        		SubmitData("Draft");
						}else{
							SubmitData("Draft","/publishProduct/show");
						}
			        },
			        no: function(){
			        	var productID = $("#txtProductID").val();
			        	if(productID !="" && productID >0 && $("#productType").val() != 1){
			        		location.href="/publishProduct/show?productId="+productID;
			        	}else{
			        		location.href="/publishProduct/index";
			        	}			        	
			        }
			    }
			});
		}else{
			location.href="/publishProduct/index";
		}
	});
	
	//显示隐藏商品属性
	$("#cboxIsShowParam").click(function(){
		if($(this).prop("checked")==true){
			$("#divSetParam").slideDown();
		}
		else{
			$("#divSetParam").slideUp();
		}
	});
	$("#imgHideParamDiv").click(function(){
		$("#divSetParam").slideUp();
		$("#cboxIsShowParam").prop("checked",false);
	});
	
	//添加自定义属性
	$("#Fice_inp").on("click","[name='Add_imgs'] img",function(){
		if($("#Fice_inp .self_one").length>=10){
			alert("最多只能添加10个自定义参数！");
			return;
		}
				
		var paramDIV = $(this).closest(".self_one").clone();
		$(paramDIV).find("input").val("");
		$(paramDIV).appendTo($("#Fice_inp"));
		$(this).closest(".xiao_icon").append("<a name='Del_img' href='javascript:;'>" +
				"<img src='/statics/pic/delete_icon.png'></a>");
		$(this).parent().remove();
	});
	//删除自定义参数
	$("#Fice_inp").on("click","[name='Del_img'] img",function(){
		$(this).closest(".self_one").remove();
		PreviewParam();
	});
	
	//动态生成参数预览
	$("#divSetParam").on("blur","input[type='text']",function(){
		PreviewParam();
	}).on("change","select",function(){
		PreviewParam();
	}).on("click","input:checkbox",function(){
		PreviewParam();
	});
	
	
	//保存参数模板
	$("#btnSaveParamTemp").click(function(){
		var paramData = getSaveParamModelData();
		if(paramData != false){			
			ShowWin("保存模板","NefMban",$(this),function(_this,index){
				var mname = $("#txtName").val();
				var classId = $("#txtClassID").val();
				if(mname ==""){
					alert("请输入名称！");
					return;
				}				
				if(isPostting){
					return;
				}
				var josn = obj2Str({mname:mname,classId:classId,keyList:paramData});	
				var loadIndex = layer.load('正在努力的提交...');
				isPostting = true;
		        $.ajax({
		            url: "/Product/saveParamModel",
		            type: "post",
		            data: { josn:josn },
		            dataType : 'json',
		            success: function (data) {
		                if(data.state =="Success"){
		                	layer.close(index);
		                	alert("保存成功！",true);	  
		                	$("#classModelName").html(mname);
		                	$("#txtMID").val(data.message);		                	
		                	getParamModel(data.message);		                	   			                		                	
		                }else{
		                	alert(data.message);
		                }
				        isPostting = false;
				        layer.close(loadIndex);	
		            },
		            error: function () {
		                alert("数据提交失败！请稍后重试！");
				        isPostting = false;
				        layer.close(loadIndex);	
		            }
		        });			
			});
		}	
	});
	
	//商品价格输入限制 失去焦点事件
	txtWritefloat("txtDisplayPrice");
	txtWritefloat("txtProductPrice");
	txtWritefloat("txtProductComminss");
	txtWritefloat("txtProxyPrice");
	txtWritefloat("txtFloorPrice");
	$("#txtProductPrice,#txtProductComminss,#txtProxyPrice,#txtFloorPrice,#txtDefaultPrice,#txtAdvicePrice").bind("blur",function(){
		var price = $("#txtProductPrice").val();
		var comminss = $("#txtProductComminss").val();
		
		if(price !="" && (isNaN(price)|| price <0.01)){
			alert("请正确输入商品单价！");
			$("#txtProductPrice").val("");
			$("#txtTotal").val("");
			return;
		}	
		if (comminss == '') {
			comminss = 0;
		}
		if(price ==""){
			$("#txtTotal").val("");
			return;
		}
		if(pubProductType != 5){
			var displayPrice = $("#txtDisplayPrice").val();
			if(displayPrice !="" && (isNaN(displayPrice)|| displayPrice <0.01)){
				alert("请正确输入商品原价！");
				$("#txtDisplayPrice").val("");
				return;
			}
//			var productPrice =$("#txtProductPrice").val();
//			if(productPrice>=displayPrice)
//			{
//				alert("商品原价不能小于现价！");
//				$("#txtDisplayPrice").val("");
//				return false;
//			}
		}
		//代理价 、落地价 
		if(pubProductType == 3 || pubProductType == 4){//平台号品牌号
			var proxyPrice = $("#txtProxyPrice").val();
			var floorPrice = $("#txtFloorPrice").val();
			if(proxyPrice !="" && (isNaN(proxyPrice)|| proxyPrice <0.01)){
				alert("请正确输入代理价！");
				$("#txtProxyPrice").val("");
				return;
			}
			if(floorPrice !="" && (isNaN(floorPrice)|| floorPrice <0.01)){
				alert("请正确输入落地价！");
				$("#txtFloorPrice").val("");
				return;
			}
		}
		
		//成本价、建议价
		if(pubProductType == 5){//平台号子供应商
			var defaultPrice = $("#txtDefaultPrice").val();
			var advicePrice = $("#txtAdvicePrice").val();
			if(defaultPrice !="" && (isNaN(defaultPrice)|| defaultPrice <0.01)){
				alert("请正确输入成本价！");
				$("#txtDefaultPrice").val("");
				return;
			}
			if(advicePrice !="" && (isNaN(advicePrice)|| advicePrice <0.01)){
				alert("请正确输入建议价！");
				$("#txtAdvicePrice").val("");
				return;
			}
		}
		//供货价 零售价
		if(pubProductType==6){//代理分销区供应商
			var defaultPrice = $("#txtSellPrice").val(); //零售价
			var supplyPrice = $("#txtSupplyPrice").val();
			if(defaultPrice !="" && (isNaN(defaultPrice)|| defaultPrice <0.01)){
				alert("请正确输入零售价！");
				$("#txtSellPrice").val("");
				return;
			}
			if(supplyPrice !="" && (isNaN(supplyPrice)|| supplyPrice <0.01)){
				alert("请正确输入供货价！");
				$("#txtSupplyPrice").val("");
				return;
			}
		}
		$("#txtTotal").val(changeTwoDecimal_f(parseFloat(price)+parseFloat(comminss)));		
	});
	//商品数量输入限制 失去焦点事件
	txtWriteint("txtProductNum");
	
	//改变规格名称
	$(".pinpaixze_is select").change(function(){
		getStyleList(false);
	});
	
	//自定义规格
	$(".pinpaixze_is select").bind("change",function(){
		if($(this).val()!="自定义规格"){
			return;
		}
		
		ShowWin("自定义规格","NefMban",$(this).find("option:last"),function(_this,index){
			var txtName = $("#NefMban input").val();
			if(txtName ==""){
				alert("请输入名称！");
				return;
			}
			//是否重复
			var options = [];
			$(".pinpaixze_is select>option").each(function(index,item){
				options.push($(item).html());
			});
			if ($.inArray(txtName, options) > -1){
				alert("该规格已存在！");
				return;
			}
			
			$(_this).before("<option>"+txtName+"</option>");
			$(_this).prev("option").attr("selected",true);
			layer.close(index);
			getStyleList();
		});
	});
	
	//添加商品规格值
	$(".pinpaixze_pf").on("click",".add_heistimg",function(){
		
		var len = $(this).closest(".pincrig").find(".input_cols").length;
		if(len>=10){
			alert("每个规格最多只能添加10个！");
			return;
		}
		
		var value = $(this).closest(".add_heistimg").prev("input").val();
		if(value==""){
			alert("请输入规格值！");
			return;
		}
		
		var paramDIV =$(this).closest(".input_cols").clone();
		$(paramDIV).find("input").val("");
		$(paramDIV).appendTo($(this).closest(".pincrig"));
		$(this).parent().append("<div class='fl del_inputimg'>" +
				"<img src='/statics/images/delimg_hua.png'></div>");
		$(this).remove();
		getStyleList();
	});
	
	
	//规格值发生改变
	$(".pinpaixze_pf").on("change",".pincrig input",function(){
		var isSuccess = true;
		var tempValues = [];
		$(".pinpaixze_pf .pincrig input").each(function(index,item){
			if($(this).val()==""){
				return;
			}		
			if ($.inArray($(this).val(), tempValues) > -1) {
				alert("存在相同的规格值！"+$(this).val());
				$(this).val("");
				isSuccess = false;
				return false;
			}
			tempValues.push($(item).val());	 
		});
		if(!isSuccess){
			return;
		}
		//判断是否存在上传图片 存在 修改 不存在添加	
		var len = $(this).closest(".pincrig").find(".input_cols").length;
		var value = $(this).val();
		if($(this).closest(".pinpaixze_is").index()==0){
			var index = $(this).closest(".input_cols").index();
			if($("#Cofic .col_scimgs").eq(index).length>0){
				$("#Cofic .col_scimgs").eq(index).find(".scimg_col span.ft_c3").html(value);
			}else{
				var valueID = "valueImg" + len;
				var html ="<div class='col_scimgs fl'>";
					html +="	<div class='scimg_col fl'>";
					//html +=" 		<span class='dis_b ml_10 dis_bgnews fl'></span>";
					html +="		<span class='fl ml_10 lh_40 ft_c3 dis_b'>"+value+"</span>";
					html +="	</div>";
					html +="	<div class='scimg_del fl'>";
					html +="	    <div class='btn_wear30_pre shou ml_10 mt_5 fl w150'><span id='"+valueID+"'>上传图片</span></div>";
					html +="	</div>"; 
					html +="</div>";	     			
				$("#Cofic").append(html);
				UpLoadOneImgInit(valueID,"valueImg");
			}			
		}
		getStyleList();
	});	
	
	//删除商品规格值
	$(".pinpaixze_pf").on("click",".del_inputimg",function(){
		//如果是第一种规格 需要移除对应的上传图片
		if($(this).closest(".pinpaixze_is").index()==0){
			var index = $(this).closest(".input_cols").index();
			$("#Cofic .col_scimgs").eq(index).remove();
		}

		$(this).closest(".input_cols").remove();
		getStyleList();
	});
	
	//规格值的价格 数量 输入限制
	$("#Ute_inp").on("blur","input[name='sellPrice'],input[name='sellComminss']",function(){
		var txtPrice = $(this).parent().find("input[name='sellPrice']");
		var txtComminss=$(this).parent().find("input[name='sellComminss']");
		var txtTotal =$(this).parent().find("input[name='sellTotal']");
		
		var price = $(txtPrice).val();
		var comminss = $(txtComminss).val();
		
		if(price == "" || typeof(price) == "undefined" || price <= 0.01 || isNaN(price)){
			alert("请正确输入商品价格！");
			$(txtPrice).val("");
			$(txtTotal).val("");
			return;
		}	
		
		if(comminss == '' || typeof(price) == "undefined" || isNaN(comminss) || comminss<=0.01){
			comminss = 0;
		}
		$(txtTotal).val(parseFloat(price)+parseFloat(comminss));	
		
	}).on("keyup","input[name='sellPrice'],input[name='sellComminss'],input[name='proxyPrice'],input[name='floorPrice'],input[name='supplyPrice'],input[name='adSellPrice']",function(){   
		$(this).val($(this).val().replace(/[^0-9.]/g,''));    
	}).on("paste","input[name='sellPrice'],input[name='sellComminss']",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/[^0-9.]/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	
	$("#Ute_inp").on("blur","input[name='proxyPrice']",function(){
		var txtproxyPrice = $(this).parent().find("input[name='proxyPrice']");
		var proxyPrice = $(txtproxyPrice).val();
		if(proxyPrice == "" || typeof(proxyPrice) == "undefined" || proxyPrice <= 0.01 || isNaN(proxyPrice)){
			alert("请正确输入代理价！");
			$(proxyPrice).val("");
			return;
		}
	});
	$("#Ute_inp").on("blur","input[name='floorPrice']",function(){
		var txtfloorPrice = $(this).parent().find("input[name='floorPrice']");
		var floorPrice = $(txtfloorPrice).val();
		if(floorPrice == "" || typeof(floorPrice) == "undefined" || floorPrice <= 0.01 || isNaN(floorPrice)){
			alert("请正确输入落地价！");
			$(floorPrice).val("");
			return;
		}
	});
	$("#Ute_inp").on("blur","input[name='supplyPrice'],input[name='adSellPrice']",function(){
		var txtsupplyPrice = $(this).parent().find("input[name='supplyPrice']");
		var supplyPrice = $(txtsupplyPrice).val();
		var txtadSellPrice = $(this).parent().find("input[name='adSellPrice']");
		var adSellPrice = $(txtadSellPrice).val();
		if(supplyPrice == "" || typeof(supplyPrice) == "undefined" || supplyPrice <= 0.01 || isNaN(supplyPrice)){
			alert("请正确输入供货价！");
			$(supplyPrice).val("");
			return;
		}
		if(adSellPrice == "" || typeof(adSellPrice) == "undefined" || adSellPrice <= 0.01 || isNaN(adSellPrice)){
			alert("请正确输入零售价！");
			$(adSellPrice).val("");
			return;
		}
	});
	
	
	
	//每种规格数量 输入限制
	$("#Ute_inp").on("keyup","input[name='txtSellNum']",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).on("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	
	//显示隐藏商品规格设置
	$("#box_1").bind("click",function(){
		if($(this).prop("checked")==true){
			$("#Ute_inp").slideDown();
			$("#txtProductPrice").attr("disabled","disabled");
			$("#txtProductComminss").attr("disabled","disabled");
			$("#txtProductNum").attr("disabled","disabled");
			if(pubProductType == 3 || pubProductType == 4){//平台号品牌号
				$("#txtProxyPrice").attr("disabled","disabled");
				$("#txtFloorPrice").attr("disabled","disabled");
			}
			if(pubProductType==6){
				$("#txtSellPrice").attr("disabled","disabled");
				$("#txtSupplyPrice").attr("disabled","disabled");
			}
		}
		else{
			$("#Ute_inp").slideUp();
			$("#txtProductPrice").removeAttr("disabled");
			$("#txtProductComminss").removeAttr("disabled");
			$("#txtProductNum").removeAttr("disabled");
			if(pubProductType == 3 || pubProductType == 4){//平台号品牌号
				$("#txtProxyPrice").removeAttr("disabled","disabled");
				$("#txtFloorPrice").removeAttr("disabled","disabled");
			}
			if(pubProductType==6){
				$("#txtSellPrice").removeAttr("disabled","disabled");
				$("#txtSupplyPrice").removeAttr("disabled","disabled");
			}
		}
	});
	//删除某项商品规格
	$("#Foct_Shop").on("click",".inp_col6 img",function(){
		if (pubProductType == 3 || pubProductType == 4 ) {//平台号品牌号
			$(this).closest("ul").nextAll().andSelf().each(function(){
				$(this).remove();
			});
		} else {
			$(this).closest("ul").remove();
		}
	});
	//显示隐藏批发价格
	$("#box_2").click(function(){
		if($(this).prop("checked")==true){
			$("#Ute_inpFic").slideDown();
		}
		else{
			$("#Ute_inpFic").slideUp();
		}
	});
	
	//添加区间价格
	$('#AddPrice').click(function(){	
		var isSuccess =checkBatchPrice();
		if(!isSuccess){
			return;
		}
		
		if($("#PriceTjia").find(".title_shuru").length>=5){
			alert("阶梯价格不能大于5个！");
			return;
		}
		$('#PriceTjia').append('<div class="h40 title_shuru"><div class="shuru_price fl">'
				+'<input type="text" maxlength="10" class="btn_h30 w98" />&nbsp;件及以上： </div><div class="shuru_priceon fl">'
				+'<input type="text" maxlength="10" class="btn_h30 w98" /> &nbsp;元/件<a href="javascript:;" name="DelPrice">'
				+'<img src="/statics/pic/delete_icon.png" /></a> </div></div>');
		PreviewBatchPrice();
	});
	//删除区间价格
	$("#PriceTjia").on("click","[name='DelPrice']",function(){
		$(this).closest(".title_shuru").remove();
		PreviewBatchPrice();
	});
	
	//显示隐藏预定价格
	$("#box_3").click(function(){
		if($(this).prop("checked")==true){
			$("#YuDing").slideDown();
		}
		else{
			$("#YuDing").slideUp();
		}
	});
	//预定价格输入限制 失去焦点
	txtWritefloat("txtPreOrderPrice");
	$("#txtPreOrderPrice").bind("blur",function(){
		var value = $(this).val();		
		if((value !="" && isNaN(value))  || value < 0.01){
			alert("请正确填写预定价格");			
			$(this).val("");
		}
	});
	
	//删除图片
	$(".she_img").on("click",".img_dele ",function(){
		var upbtnID ="upimgbtn" +($(".she_img  li>div.own_img_one").length + 1);
		$(this).closest("li").append("<div class='own_img_one'>" 
				+"<img src='/statics/pic/xin_inselimg.png'>" 
				+"<div class='transparentDiv'><span id='"+upbtnID+"'></span></div></div>");
		$(this).closest(".own_img").remove();
		UpLoadOneImgInit(upbtnID,"productImg");
	});
	
	//保存店铺分类 一级分类
	$("#btnSaveShopClass").click(function(){
		ShowWin("保存分类","NefMban",$(this),function(_this,index){
			var scName = $("#NefMban input").val();
			if(scName ==""){
				alert("请输入分类名称！");
				return;
			}
			var isHave = false;
			$("#selShopClass option").each(function(index,item){
				if($(item).text() ==scName){
					alert("该分类名称已存在！");
					isHave = true;
					return;
				}
			});
			if(isHave || isPostting){
				return;
			}
			var loadIndex = layer.load('正在努力的提交...');
			isPostting = true;
	        $.ajax({
	            url: "/Product/saveShopClass",
	            type: "post",
	            data: { scName:scName },
	            success: function (data) {
	                data = eval(data);
	                if(data.state =="Success"){
	                	alert("保存成功！",true);
	                	layer.close(index);
	                	$("#selShopClass").append("<option selected='selected' value='"+data.message+"'>"+scName+"</option>");
	                	
	                }else{
	                	alert(data.message);
	                }
	    	        isPostting = false;
	    	        layer.close(loadIndex);
	            },
	            error: function () {
	                alert("数据提交失败！请稍后重试！");
	    	        isPostting = false;
	    	        layer.close(loadIndex);
	            }
	        });
		});
	});
	
	//保存店铺分类 二级分类
	$("#btnSaveShopClass2").click(function(){
		$("#add_tjelfj").html("");
		$("#add_tjelfj").html("" +
	            "<div class='fl mzh_width_100 mb_10'>" +
	            "<span class='fl mr_10'>二级分类名称：</span>" +
	            "<input type='text' class='xzgys_input' style='width: 150px;' name='childrenShopClassName'>" +
	            "<font class='fl ft_red fw_b shou ml_10 f22' onclick='javascript:delChildrenShopClass(this)'>×</font>" +
	            "</div>");
		showShopClassAdd("保存分类",'520px','400px',"win_div_4",$(this),function(_this,index){
			var scName = $.trim($("#oneShopClass").val());
			if(scName ==""){
				alert("请输入一级分类名称！");
				return;
			}
			var twoClass = [];
			$("input[name='childrenShopClassName']").each(function(){
				if ($.trim($(this).val()) == '') {
					alert("二级分类名称不能为空");
					return;
				}
				twoClass.push($.trim($(this).val()));
			});
//			var isHave = false;
//			var classname = [];
//			var classname2 = [];
//			classname2.push(scName);
//			//新增的二级分类
//			$("input[name='childrenShopClassName']").each(function(index2,item2){
//				classname.push($(item2).val());
//				classname2.push($(item2).val());
//			});
//			if (isRepeat(classname2)){
//				alert("输入的分类名称不能相同！");
//				isHave = true;
//				return;
//			}
//			//一级分类
//			$("#selShopClass option").each(function(index,item){
//				classname.push($(item).text());
//			});
//			//所有二级分类
//			$("#childrenShopClass span option").each(function(){
//				classname.push($(this).html());
//			});
//			if (isRepeat(classname)){
//				alert("该分类名称已存在！");
//				isHave = true;
//				return;
//			}
			if(isPostting){
				return;
			}
			var loadIndex = layer.load('正在努力的提交...');
			isPostting = true;
	        $.ajax({
	            url: "/Product/saveShopClassTwoLevle",
	            type: "post",
	            data: { scName:scName,scJson:obj2Str(twoClass) },
	            success: function (data) {
	                data = eval(data);
	                if(data.Statu == "Success"){
	                	alert("保存成功！",true);
	                	layer.close(index);
	                	var parentId = "";
	                	$.each(data.BaseModle, function (n, value) {
	                		if(value.level == 1){
	                			parentId = value.sid;
	                		} 
	                	});
	                	var t = 0;
	                	$.each(data.BaseModle, function (n, value) {
	                		if(value.level == 1){
	                			$("#selShopClass").append("<option selected='selected' value='"+value.sid+"'>"+value.sname+"</option>");
	                		} else if(value.level == 2){
	                			t = t + 1;
	                			var tp = '<span parentId="'+parentId+'"><option value="'+value.sid+'">'+value.sname+'</option></span>';
	                			if (t == 1) {
	                				tp = '<span parentId="'+parentId+'"><option selected="selected" value="'+value.sid+'">'+value.sname+'</option></span>';
								} 
	                			$("#childrenShopClass").append(tp);
	                		}
	                	});
	                	changeShopClass();
	                }else{
	                	alert(data.StatusReson);
	                }
	    	        isPostting = false;
	    	        layer.close(loadIndex);
	            },
	            error: function () {
	                alert("数据提交失败！请稍后重试！");
	    	        isPostting = false;
	    	        layer.close(loadIndex);
	            }
	        });
		});
	});
	
	//批发价数量文本框限制
	$("#PriceTjia").on("keyup",".shuru_price input",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).on("paste",".shuru_price input",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).on("blur",".shuru_price input",function(){	
		PreviewBatchPrice();
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  

	//批发价 价格文本框限制
	$("#PriceTjia").on("keyup",".shuru_priceon input",function(){   
		$(this).val($(this).val().replace(/[^0-9.]/g,''));    
	}).on("paste",".shuru_priceon input",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/[^0-9.]/g,''));     
	}).on("blur",".shuru_priceon input",function(){	
		PreviewBatchPrice();
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  

	//删除销售属性图片
	$("#Cofic").on("click",".imgsew a",function(){
		$(this).closest(".imgsew").remove();				
	});
	
	//PC Wap 详情切换
	$(".news_tabq a").click(function(){
		$(this).siblings().attr("class","tba_onefic");
		$(this).attr("class","tba_twofic");
		var index = $(this).index();
		if(index ==0){
			$("#PcNew_1").show();
			$("#PcNew_2").hide();
		}
		else{
			$("#PcNew_2").show();
			$("#PcNew_1").hide();
		}
	});
	
	//初始化批量上传控件
	UpLoadImgInit();
	//初始化单个商品图片上传控件
	$(".she_img .transparentDiv span").each(function(index,item){
		UpLoadOneImgInit($(this).attr("id"),"productImg");
	});
	$(".num_col .scimg_del .btn_wear30_pre span").each(function(index,item){
		UpLoadOneImgInit($(this).attr("id"),"valueImg");
	});
		
	//生成wapDes
	$("#btnCreateWapDes").click(function(){		
		var WAPDes = Filter(pcEditor.getContent());
		wapEditor.setContent(WAPDes);
		$(".news_tabq a").eq(1).click();
	});
	
	//保存草稿
	$("#btnSaveDraft").bind("click",function(){
		SubmitData("Draft");
	});
	//发布商品
	$("#btnSaveProduct").bind("click",function(){
		SubmitData();
	});
	//审核不通过
	$("#btnRejectDraft").bind("click",function(){
		showShopClassAdd("审核不通过原因",'520px','280px',"win_div_3",$(this),function(_this,index){
			var notPassIntro = $.trim($("#notPassIntro").val());
			if(notPassIntro ==""){
				alert("请输入不通过原因！");
				return;
			}
			SubmitData("AuditReject");
			layer.close(index);
		});
	});
	//审核通过并发布
	$("#btnPassSaveProduct").bind("click",function(){
		SubmitData("AuditPass");
	});
	
	//如果有数据 默认展开
	if($("#divSetParam .self_one").length>2){
		$("#cboxIsShowParam").click();
	}
	if($("#Foct_Shop ul").length>0){
		$("#box_1").click();
	}
	if($("#PriceTjia .title_shuru").length>0){
		$("#box_2").click();
	}
	if($("#txtPreOrderPrice").val()>0){
		$("#box_3").click();
	}
	
	//初始化百度编辑器
	EditorInit();
	//初始化二级分类
	changeShopClass();
	//店铺分类选择
	if($("#newId").val() == 1){
		$("#selShopClass").bind("change",function(){
			changeShopClass();
		});
	}
    
});
/* 发布产品 -- 添加二类分级 */
function addChildrenShopClass(){
	$("#add_tjelfj").append("" +
            "<div class='fl mzh_width_100 mb_10'>" +
            "<span class='fl mr_10'>二级分类名称：</span>" +
            "<input type='text' class='xzgys_input' style='width: 150px;' name='childrenShopClassName'>" +
            "<font class='fl ft_red fw_b shou ml_10 f22' onclick='javascript:delChildrenShopClass(this)'>×</font>" +
            "</div>");
}
/* 发布产品 -- 添加二类分级 -- 删除分类 */
function delChildrenShopClass(ts){
	$(ts).parent("div").remove();
}
//店铺分类联动
function changeShopClass(){
	var sId = $("#selShopClass").val();
	if(sId > 0){
		$("#selShopClass2").empty();
		$("#selShopClass2").append('<option value="-1">--请选择--</option>');
		$("#childrenShopClass span").each(function(){
			if(sId == $(this).attr("parentId")){
				$("#selShopClass2").append($(this).html());
			}
		});
	}
}

//保存商品信息获取数据
function SubmitData(type,backUrl){
	var doType = 1;//正式发布
	var auditStatus = 1;
	if(type =="Draft"){//草稿
		doType = 0;
	} else if(type =="AuditPass"){//审核子供应商产品-通过
		doType = 2;
		auditStatus = 3;
	} else if(type =="AuditReject"){//审核子供应商产品-不通过
		doType = 2;
		auditStatus = 2;
	}
	if (type !="AuditReject") {
		if(!checkSubmitData(type)){
			return;
		}
	}
	var productId = $("#txtProductID").val();//产品id
	var operation = $("#txtOperation").val();//操作类型
	var brandId = $("#txtBrandId").val();//产品id
	if(productId =="" || operation==2){
		productId=0;
	}
	var classId = $("#txtClassID").val();//商品分类
	if(classId ==""){
		classID =0;
	}
	var productTitle =$("#txtProductTitle").val();//标题
	var productMinTitle = $("#txtProductMinTitle").val();//副标题
	var keyWords = $("#txtkeyWords").val();//关键词
	var productImg = $(".own_img  input:radio:checked").val();//默认图片
	if(productImg ==undefined || productImg ==null){
		productImg ="";
	}
	var imgList ="";//图片列表
	$(".own_img  input:radio:not(:checked)").each(function(){
		if($(this).val() !=""){
			imgList += $(this).val() + ",";
			//imgList.push({imgPath:$(this).val()});
		}	
	});
	if (imgList.length > 0) {
		imgList = imgList.substring(0,imgList.length-1);
	}
	var count = $("#txtProductNum").val();//数量、库存
	if(count ==""){
		count=0;
	}
	var sellKeyList=getSubmitSpecData();//获取提交商品 规格 {"颜色":"红色，蓝色；紫色","尺寸":"XL；L；XXL"}
	//var preOrder = getSubmitPreOrder();//预定价(已去掉)
	var pcdes = pcEditor.getContent();//pc描述
	var appdes = wapEditor.getContent();//app描述
//    var reg2 = /(http:\/\/|https:\/\/)((\w|=|\?|\.|\/|&|-)+)/g;
//    var reg = new RegExp(reg2);
//    pcdes = pcdes.replace(reg, "");
//    appdes = appdes.replace(reg, "");
	var sid = 0;//店铺分类ID
	if($("#newId").val() == 1){
		sid =$("#selShopClass2").val();
		if (sid=="" || sid=="-1" || sid==null) {
			sid =$("#selShopClass").val();
		}
		if(sid=="" || sid==null){
			sid=0;
		}
	} else {
		sid =$("#selShopClass").val();
		if(sid=="" || sid==null){
			sid=0;
		}
	}
	var price =$("#txtProductPrice").val();//商品价格	
	if(price ==""){
		price =0;
	}
	var styleList = [];
	var batchPriceList = [];
	var freightId = 0;
	var commission = 0;
	var paramList = [];
	var mID = 0;
	var displayPrice = 0;
	//非平台号子供应商
	if(pubProductType != 5){
		styleList = getSubmitstyleList();//款式列表 展开款式列表
		batchPriceList = getSubmitBatchPrice();//批发价
		freightId = $("#selPostAge").val();//邮费模板id
		if(freightId =="" || freightId ==null){
			freightId =0;
		}
		commission = $("#txtProductComminss").val();//佣金
		if(commission =="" || isNaN(commission)|| commission <0.01){
			commission =0;
		}
		paramList = getSubmitParamData();//商品 参数数据
		mID = $("#txtMID").val();//商品属性模板ID
		if(mID ==""){
			mID =0;
		}
		displayPrice = $("#txtDisplayPrice").val();
	}
	var product = {};
	//普通微店主、供应商发布
	if(pubProductType == 1 || pubProductType == 2){
		product ={
				productId:productId,					//产品id
				typeNo:classId,							//商品分类
				mID:mID,								//商品属性模板ID
				productTitle:productTitle,				//标题
				productTitleMin:productMinTitle,		//副标题
				keyWords:keyWords,						//关键词
				paramList:paramList,					//商品 参数数据
				defaultImg:productImg,					//默认图片
				imgs:imgList,							//图片列表
				price:price,							//商品价格
				commission:commission,					//商品佣金
				inventory:count,						//默认商品数量、库存
				sellKeyList:sellKeyList,				//获取提交商品 规格 key => value
				styleList:styleList,					//款式列表
				batchPrice:batchPriceList,			    //批发价
				postFeeId:freightId,					//邮费模板id
				customTypeNo:sid,						//店铺分类id
				pcdes:pcdes,							//pc描述
				description:appdes,						//app描述
				brandId:brandId,
				displayPrice:displayPrice
				//type:state								//商品状态
		};
	} 
	//平台号、品牌号发布
	if(pubProductType == 3 || pubProductType == 4){
		var txtProxyPrice = $("#txtProxyPrice").val();
		if(txtProxyPrice ==""){
			txtProxyPrice =0;
		}
		var txtFloorPrice = $("#txtFloorPrice").val();
		if(txtFloorPrice ==""){
			txtFloorPrice =0;
		}
		var demandId = $("#selSupplyDemand").val();
		if(demandId=="" || demandId==null){
			demandId=0;
		}
		var notPassIntro = $.trim($("#notPassIntro").val());
		product ={
				productId:productId,					//产品id
				typeNo:classId,							//商品分类
				mID:mID,								//商品属性模板ID
				productTitle:productTitle,				//标题
				productTitleMin:productMinTitle,		//副标题
				keyWords:keyWords,						//关键词
				paramList:paramList,					//商品 参数数据
				defaultImg:productImg,					//默认图片
				imgs:imgList,							//图片列表
				price:price,							//商品价格
				commission:commission,					//商品佣金
				inventory:count,						//默认商品数量、库存
				sellKeyList:sellKeyList,				//获取提交商品 规格 key => value
				styleList:styleList,					//款式列表
				postFeeId:freightId,					//邮费模板id
				customTypeNo:sid,						//店铺分类id
				pcdes:pcdes,							//pc描述
				description:appdes,						//app描述
				//type:state,								//商品状态
				agentPrice:txtProxyPrice,				//代理价
				storePrice:txtFloorPrice,				//落地价
				auditStatus:auditStatus,				//审核状态
				notPassIntro:notPassIntro,				//如果不通过输入理由
				demandId:demandId,						//招商需求
				brandId:brandId,
				displayPrice:displayPrice
				
		};
	}
	//平台号子供应商发布
	if(pubProductType == 5){
		var defaultPrice =$("#txtDefaultPrice").val();//成本价
		if(defaultPrice ==""){
			defaultPrice =0;
		}
		var advicePrice =$("#txtAdvicePrice").val();//建议价
		if(advicePrice ==""){
			advicePrice =0;
		}
		product ={
				productId:productId,					//产品id
				typeNo:classId,						    //商品分类
				productTitle:productTitle,				//标题
				productTitleMin:productMinTitle,		//副标题
				keyWords:keyWords,						//关键词
				defaultImg:productImg,					//默认图片
				imgs:imgList,							//图片列表
				defaultPrice:defaultPrice,				//成本价
				advicePrice:advicePrice,				//建议价
				commission:commission,					//商品佣金
				inventory:count,						//默认商品数量、库存
				sellKeyList:sellKeyList,				//获取提交商品 规格 key => value
				customTypeNo:sid,						//店铺分类id
				pcdes:pcdes,							//pc描述
				description:appdes,						//app描述
				brandId:brandId
				//type:state								//商品状态
		};
	}
	//平台号子供应商发布
	if(pubProductType == 6){
		var defaultPrice =$("#txtSupplyPrice").val();//供货价
		if(defaultPrice ==""){
			defaultPrice =0;
		}
		var advicePrice =$("#txtSellPrice").val();//零售价
		if(advicePrice ==""){
			advicePrice =0;
		}
		product ={
				productId:productId,					//产品id
				typeNo:classId,						    //商品分类
				productTitle:productTitle,				//标题
				productTitleMin:productMinTitle,		//副标题
				keyWords:keyWords,						//关键词
				mID:mID,								//商品属性模板ID
				paramList:paramList,					//商品 参数数据
				defaultImg:productImg,					//默认图片
				imgs:imgList,							//图片列表
				costPrice:defaultPrice,					//成本价
				price:advicePrice,						//零售价
				commission:commission,					//商品佣金
				postFeeId:freightId,					//邮费模板id
				inventory:count,						//默认商品数量、库存
				sellKeyList:sellKeyList,				//获取提交商品 规格 key => value
				styleList:styleList,					//款式列表
				customTypeNo:sid,						//店铺分类id
				pcdes:pcdes,							//pc描述
				description:appdes,						//app描述
				brandId:brandId
				//type:state								//商品状态
		};
	}
	var josn = obj2Str(product);
	//如果正在提交 不处理
	if(isPostting){
		alert("正在努力提交中！请勿重复点击！");
		return;
	}
	var loadIndex = layer.load('正在努力的提交...');
	isPostting = true;
    $.ajax({
        url: "/Product/saveProduct",
        type: "post",
        data: { josn:josn,doType:doType },
        success: function (data) {
            data = eval(data);
            if(data.Statu =="1"){
            	alert("提交成功！",true);
            	if ($("#productType").val() == 1 && pubProductType == 3) {
        			location.href="/publishProduct/index";
        		} else {
        			$("#txtProductID").val(data.BaseModle.productid);
        			if(backUrl !=null && backUrl !=""){
        				location.href=backUrl+"?productId="+data.BaseModle.productid;
        			}else{
        				if ("0" == doType) {
        					location.href="/myProduct/list/OutLine/0/0";
						} else {
							location.href="/myProduct/list/Showing/0/0";
						}
        			}
        		}
            }else{
            	alert(data.StatusReson);
            }
            isPostting = false;
            layer.close(loadIndex);
        },
        error: function () {
            alert("数据提交失败！请稍后重试！");
            isPostting = false;
            layer.close(loadIndex);
        }
    });
}

//校验发布商品数据
function checkSubmitData(type){
	var isScuess = true;
	
	var classId = $("#txtClassID").val();
	if(classId =="" || classId<1){
		alert("分类数据错误!请刷新后重试！或重新选择分类！");
		return false;
	}
	
	//1.商品标题：用于商品展示，最多80个字符，必填。
	var title = $("#txtProductTitle").val();
	if(title==""){
		alert("请填写商品标题!");
		$("#txtProductTitle").focus();
		return false;
	}else if(title.length>80){
		alert("商品标题最大长度为80!");
		$("#txtProductTitle").focus();
		return false;
	} 
	var count = $("#txtProductNum").val();
	if(count==""){
		alert("请填写商品库存!");
		$("#txtProductNum").focus();
		return false;
	}
	if(pubProductType != 5){
		var displayPrice = $("#txtDisplayPrice").val();
		if(displayPrice==""){
			alert("请正确输入商品原价！");
			$("#txtDisplayPrice").val("");
			return false;
		}
//		var productPrice =$("#txtProductPrice").val();
//		if(productPrice>=displayPrice)
//		{
//			alert("商品原价不能小于现价！");
//			$("#txtDisplayPrice").val("");
//			return false;
//		}
	}
	if(pubProductType == 3 || pubProductType == 4){
		var txtProxyPrice = $("#txtProxyPrice").val();
		if(txtProxyPrice==""){
			alert("请填写代理价!");
			$("#txtProxyPrice").focus();
			return false;
		}
		var txtFloorPrice = $("#txtFloorPrice").val();
		if(txtFloorPrice==""){
			alert("请填写落地价!");
			$("#txtFloorPrice").focus();
			return false;
		}
	}
	if(pubProductType == 1 || pubProductType == 2){
		var price =$("#txtProductPrice").val();
		if(price==""){
			alert("请填写零售价!");
			$("#txtProductPrice").focus();
			return false;
		}
	}
	if(pubProductType != 5){
		//如果存在款式
		if($("#Foct_Shop ul").length >0){			
			var keys =[];
			$(".pincmas select").each(function(){
				var value = $(this).val();
				if(value !="--请选择--" && value !="自定义规格" && $.inArray(value, keys) > -1){
					alert("存在相同的规格名称！");
					isScuess = false;
					return false;
				}
				if(value!=0 && value !=-1){
					keys.push(value);
				}
				
				//如果存在值 规格名称不能为请选择
				var firstVal = $(this).closest(".pinpaixze_is").find(".pincrig input:first").val();
				if(firstVal != "" && (value=="--请选择--" || value=="自定义规格" )){
					alert("请选择或填写规格名称！");
					isScuess = false;
					return false;
				}	
				
				$("input[name='txtSellNum'],input[name='sellPrice']").each(function(){
					if($(this).val()==""){
						alert("请填写价格与数量！");
						$("#Ute_inp").show();
						$("#box_1").prop("checked",true);
						isScuess = false;
						$(this)[0].focus();
						return false;
					}
				});
			});
		}
	}
	if($("#newId").val() == 1){
		sid =$("#selShopClass2").val();
		if (sid=="" || sid=="-1" || sid==null) {
			sid =$("#selShopClass").val();
		}
		if(sid=="" || sid <= 0){
			alert("请选择店铺分类!");
			return false;
		}
	} else {
		sid =$("#selShopClass").val();
		if(sid=="" || sid <= 0){
			alert("请选择店铺分类!");
			return false;
		}
	}
		
	if(!isScuess){
		return false;
	}
	
	//草稿只需要校验上面的
	if(type=="Draft"){
		return true;
	}
	
	if(pubProductType != 5){
		//参数模板 校验
		if(!getSaveParamModelData()){
			return false;
		}
	}
	
	//2.主图 必填
	if($(".own_img  input:radio:checked").length<1){
		alert("请设定商品主图!");
		return false;
	}
	if(pubProductType == 5){
		if($("#txtDefaultPrice").val()==""){
			alert("请填写成本价！");
			$("#txtDefaultPrice")[0].focus();
			return false;
		}
		if($("#txtAdvicePrice").val()==""){
			alert("请填写建议价！");
			$("#txtAdvicePrice")[0].focus();
			return false;
		}
		if($("#txtProductNum").val()==""){
			alert("请填写商品库存！");
			$("#txtProductNum")[0].focus();
			return false;
		}
	} else if(pubProductType == 6){
		if($("#txtSellPrice").val()==""){
			alert("请填写零售价！");
			$("#txtDefaultPrice")[0].focus();
			return false;
		}
		if($("#txtSupplyPrice").val()==""){
			alert("请填写供货价！");
			$("#txtAdvicePrice")[0].focus();
			return false;
		}
		
	} else {
		////3.商品价格 必填 //4.商品数量：商品库存数量，必填。
		if($("#Foct_Shop ul").length ==0){
			if($("#txtProductPrice").val()==""){
				alert("请填写商品价格！");
				$("#txtProductPrice")[0].focus();
				return false;
			}
			if($("#txtProductNum").val()==""){
				alert("请填写商品数量！");
				$("#txtProductNum")[0].focus();
				return false;
			}
		}
	}
		
	if(!isScuess){
		return false;
	}
	
	//批发价格
	if($("#box_2").prop("checked") ==true){	
		isScuess =checkBatchPrice();
		if(!isScuess){
			return false;
		}		
	}

	//预定价格
	if($("#box_3").prop("checked") ==true){	
		var preOrderPrice = removeSpace($("#txtPreOrderPrice").val());
		if(preOrderPrice !="" && $("#txtPreOrderContent").val()==""){
			alert("请填写预定价格备注！");
			$("#box_3").prop("checked",true);
			$("#YuDing").show();
			$("#txtPreOrderContent")[0].focus();
			return false;
		}	
	}

	//5.商品详情：展示的商品详情，必填。
	var desc = pcEditor.getContent();
	if(desc ==""){
		alert("请填写商品详情！");
		return false;
	}
	if(pubProductType != 5){
		//6.店铺分类
		var sid =$("#selShopClass").val();
		if(sid=="" || sid==null){
			alert("请选择店铺分类！");
			return false;
		}
	}
	
	return true;
}

//获取提交商品 参数数据
function getSubmitParamData(){
	var paramList =[];
	$("#divSetParam .type_selec .selec_once").each(function(index,item){
		//key
		var attributeName = $(this).find(".clao_le span").html();
		var showtype = $(this).find(".clao_le span").attr("showtype");
		var attributeId =$(this).find(".clao_le span").attr("attributeid");
		//如果是文本框
		if(showtype ==1){
			var paramValue = $(this).find(".clao_rg  input").val();
			var avid =$(this).find(".clao_rg  input").attr("avid");
			if(paramValue ==""){
				return;
			}
			paramList.push({
				attributeID:attributeId,
				paramName:attributeName,
				aVID:avid,
				paramValue:paramValue
			});
		}else if(showtype ==2){ //如果是下拉框
			var paramValue = $(this).find(".clao_rg  select").val();		
			var avid = $(this).find(".clao_rg  select option:selected").attr("avid");
			if(paramValue ==""){
				return;
			}
			paramList.push({
				attributeID:attributeId,
				paramName:attributeName,
				aVID:avid,
				paramValue:paramValue
			});
			
		}else{//复选
			$(this).find(".clao_rg  input:checkbox:checked").each(function(){
				var paramValue = $(this).val();	
				var avid =  $(this).attr("avid");
				paramList.push({
					attributeID:attributeId,
					paramName:attributeName,
					aVID:avid,
					paramValue:paramValue
				});
			});			
		}	
	});
	
	$("#Fice_inp .self_one").each(function(){
		var attributeName = $(this).find("input[name='attributeName']").val();
		var attributeID =  $(this).find("input[name='attributeName']").attr("attributeid");
		var value =$(this).find("input[name='valueName']").val(); 
		var aVID = $(this).find("input[name='valueName']").attr("avid"); 		
		//结束本次循环 下一次
		if(attributeName =="" && value==""){
			return;
		}	
		if(attributeID==null || attributeID =="" || attributeID==undefined){
			attributeID =0;
		}
		if(aVID ==null || aVID =="" || aVID==undefined){
			aVID =0;
		}
		paramList.push({
			attributeID:attributeID,
			paramName:attributeName,
			aVID:aVID,
			paramValue:value
		});
	});	
	
	return paramList;
}
//获取提交商品 规格 key => value
function getSubmitSpecData(){
	var sellKeyList=[];
	$(".pinpaixze_is").each(function(kindex,kitem){
		var sellValueList=[];
		$(this).find(".pincrig input").each(function(vindex,vitem){
			var value = $(this).val();
			var defaultImg ="";
			if($(this).val() !=""){//如果该规格值 已填写
				if(kindex ==0){//如果是第一个Key 可能会上传图片
					defaultImg = $("#Cofic .col_scimgs").eq(vindex).find(".scimg_del .imgsew img").attr("src");
					if(defaultImg ==null || defaultImg==undefined){
						defaultImg ="";
					}					
				}				
				sellValueList.push({value:value,defaultImg:defaultImg});
			}
		});
		//如果该规格 存在规格值
		if(sellValueList.length>0){
			var attributeName = $(this).find(".pincmas select").val();
			sellKeyList.push({
				attributeName:attributeName,
				sort:kindex,
				sellValueList:sellValueList
			});
		}		
	});
	
	return sellKeyList;
}
//获取款式列表
function getSubmitstyleList(){
	var styleList =[];
	
	var keyName=[];
	$("#Ute_inp .size_titles li[name='liKeyName']").each(function(){
		keyName.push($(this).html());
	});
	//每个款式
	$("#Foct_Shop ul[name='styleDetail']").each(function(){
		var styleKVList =[];
		$(this).find("li div[name='divValueName']").each(function(index,item){
			var vname = $(this).html();
			styleKVList.push({attrbuteName:keyName[index],valueName:vname});
		});
		
		var conmision =$(this).find("li input[name='sellComminss']").val();
		if(conmision =="" || isNaN(conmision)|| conmision <0.01){
			conmision = parseFloat(0);
		}
		var price = parseFloat($(this).find("li input[name='sellTotal']").val());
		var count = parseInt($(this).find("li input[name='txtSellNum']").val());
		if (pubProductType == 3 || pubProductType == 4) {
			var proxyPrice = parseFloat($(this).next().find("li input[name='proxyPrice']").val());
			var floorPrice = parseFloat($(this).next().next().find("li input[name='floorPrice']").val());
			styleList.push({
				styleKVList:styleKVList,
				price:price,
				conmision:conmision,
				count:count,
				proxyPrice:proxyPrice,
				floorPrice:floorPrice
			});
		} else if (pubProductType == 6) {
			var bussinessNo = $(this).find("li input[name='txtBussinessNo']").val();
			var sellPrice = parseFloat($(this).find("li input[name='adSellPrice']").val());
			var supplyPrice = parseFloat($(this).find("li input[name='supplyPrice']").val());
			styleList.push({
				styleKVList:styleKVList,
				price:sellPrice,
				count:count,
				conmision:conmision,
				supplyPrice:supplyPrice,
				bussinessNo:bussinessNo
			});
		}else {
			var bussinessNo = $(this).find("li input[name='txtBussinessNo']").val();
			styleList.push({
				styleKVList:styleKVList,
				price:price,
				conmision:conmision,
				count:count,
				bussinessNo:bussinessNo
			});
		}
	});
	
	return styleList;
}

//获取批发价列表
function getSubmitBatchPrice(){
	var batchPriceList = [];
	if($("#box_2").prop("checked") ==true){
		$("#PriceTjia .title_shuru").each(function(index,item){
			var count = $(this).find(".shuru_price input").val();
			var pirce = $(this).find(".shuru_priceon  input").val();
			if(count =="" || pirce==""){
				return;
			}else{
				batchPriceList.push({Num:count,Price:pirce});
			}
		});
	}	
	return batchPriceList;
}

//获取预定价格对象
function getSubmitPreOrder(){
	var preOrder =null;
	if($("#box_3").prop("checked") ==true){
		var preOrderPrice = $("#txtPreOrderPrice").val();
		var content = $("#txtPreOrderContent").val();
		if(preOrderPrice !=null && preOrderPrice>0){
			preOrder = {preOrderPrice:preOrderPrice,content:content};
		}
	}	
	return preOrder;
}


//获取参数模板数据
function getSaveParamModelData(){

	var isSuccess =true;	
	var classid = $("#txtClassID").val();
	var keyList =[];
	$(".type_selec .selec_once").each(function(index,item){
		//key
		var keySpan = $(this).find(".clao_le span");
		var attributeName = $(keySpan).html();
		var attributeId = $(keySpan).attr("attributeid");
		var showtype = $(keySpan).attr("showtype");
		var isMust = $(keySpan).attr("ismust");
		//value
		var valueList =[];
		//如果是文本框
		if(showtype ==1){
			var valInput = $(this).find(".clao_rg  input");
			
			var value = $(valInput).val();
			var isDefault = 1;//文本框只能是默认的
			var avid =$(valInput).attr("avid");
			if(avid ==""){
				avid =0;
			}
			
			if(isMust ==1 && value==""){				
				alert("参数"+attributeName+"为必填参数！");
				$("#cboxIsShowParam").prop("checked","true");
				$("#divSetParam").show();
				$(this).find(".clao_rg  input")[0].focus();
				isSuccess = false;
				return false;
			}
			
			valueList.push({
				value:value,
				isDefault:isDefault,
				avid:avid
				});
		}else if(showtype ==2){ //如果是下拉框
			var selected = $(this).find(".clao_rg  select").val();
			if(isMust==1 && (selected == -1 || selected =="")){
				alert("参数"+attributeName+"为必选参数！");
				$("#cboxIsShowParam").prop("checked","true");
				$("#divSetParam").show();
				$(this).find(".clao_rg  select")[0].focus();
				isSuccess = false;
				return false;
			}
			$(this).find(".clao_rg  select option").each(function(){
				var value = $(this).val();				
				var avid =$(this).attr("avid");
				var isDefault = 0;//文本框只能是默认的
				if(value == selected){
					isDefault =1
				}	
				
				valueList.push({
					value:value,
					isDefault:isDefault,
					avid:avid
					});
			});
						
		}else{//复选
			var checkedNum = $(this).find(".clao_rg  input:checkbox:checked").length;
			if(isMust==1 && checkedNum<1){
				alert("参数"+attributeName+"为必选参数！");
				$("#cboxIsShowParam").prop("checked","true");
				$("#divSetParam").show();
				isSuccess = false;
				return false;
			}
			$(this).find(".clao_rg  input:checkbox").each(function(){
				var value = $(this).val();	
				var avid = $(this).attr("avid");
				//var sysAVID = $(this).attr("sysavid");
				var isDefault = 0;//文本框只能是默认的
				if($(this).attr("checked")=="checked"){
					isDefault =1
				}
				valueList.push({
					value:value,
					isDefault:isDefault,
					avid:avid
					});
			});			
		}
		//内层循环返回
		if(!isSuccess){
			return false;
		}
		
		keyList.push({
			attributeId:attributeId,
			classid:classid,
			attributeName:attributeName,
			valueList:valueList
			});		
	});
	//外层循环返回
	if(!isSuccess){
		return false;
	}
	
	$("#Fice_inp .self_one").each(function(){
		var attributeName = $(this).find("input[name='attributeName']").val();
		var attributeId = $(this).find("input[name='attributeName']").attr("attributeid");
		var value =$(this).find("input[name='valueName']").val(); 
		var avid =$(this).find("input[name='valueName']").attr("avid");
		//结束本次循环 下一次
		if(attributeName =="" && value==""){
			return;
		}
				
		if(attributeName =="" || value==""){
			alert("请正确填写自定义参数名称与值！");
			$("#cboxIsShowParam").prop("checked","true");
			$("#divSetParam").show();
			if(attributeName ==""){
				$(this).find("input[name='attributeName']")[0].focus();
			}else{
				$(this).find("input[name='valueName']")[0].focus();
			}
			isSuccess = false;
			return false;
		}
		
		if(attributeId ==null || attributeId==undefined){
			attributeId =0;
		}
		if(avid ==null || avid==undefined){
			avid =0;
		}
		
		keyList.push({
			attributeId:attributeId,
			classid:classid,
			attributeName:attributeName,
			showtype:1,
			isMust:1,
			valueList:[{
				avid:avid,
				value:value,
				isDefault:1,
			}]
			});	
		
	});
	//外层循环返回
	if(!isSuccess){
		return false;
	}
	
	return keyList;
}

function checkBatchPrice(){
	var isTrue =true;
	$("#PriceTjia").find(".title_shuru").each(function(){
		var count = $(this).find(".shuru_price input").val();
		var price = $(this).find(".shuru_priceon input").val();		
		
		if(count =="" || count< 2 || isNaN(count)){
			alert("请正确填写区间数量！");
			 $(this).find(".shuru_price input").val("");
			 $(this).find(".shuru_price input")[0].focus();
			 isTrue = false;
			 return false;
		}
		count = parseInt(count);	
		price = parseFloat(price);	
		
		if( price==""  || isNaN(price) || price< 0.01){
			alert("请正确填写区间价格！");
			$(this).find(".shuru_priceon input").val("");
			$(this).find(".shuru_priceon input")[0].focus();
			isTrue = false;
			return false;
		}
						
		if($(this).index()>0){
			var prevCount =parseInt($(this).prev().find(".shuru_price input").val());
			var prevPrice =parseFloat($(this).prev().find(".shuru_priceon input").val());
			if( count <= prevCount){
				alert("请正确填写批发数量!必须大于上一区间！");
				$(this).find(".shuru_price input").val("");
				$(this).find(".shuru_price input")[0].focus();
				isTrue = false;
				return false;
			}
			if(price >= prevPrice){
				alert("请正确填写批发价格!必须小于上一区间！");
				$(this).find(".shuru_priceon input").val("");
				$(this).find(".shuru_priceon input")[0].focus();
				isTrue = false;
				return false;
			}
		}
	});
	
	return isTrue;
}

//批发价格 预览效果
function PreviewBatchPrice(){
	$("#Ute_inpFic .yunln_bor .title_shuru").remove();
	$("#PriceTjia").find(".title_shuru").each(function(index,item){	
		var count = $(this).find(".shuru_price input").val();
		var price = $(this).find(".shuru_priceon input").val();
		if(count !="" && price !=""){		
			$("#Ute_inpFic .yunln_bor .blank2").before("<div class='h40 title_shuru'>" +
					"<div class='shuru_price fl f14'>"+count+"件及其以上："+price+"元/件</div></div>");      
		}
	});
}

function alert(msg,bool){
	if(bool){
		layer.msg(msg, 1, 1);//绿色的钩钩
	}else{
		layer.msg(msg, 1, 8);//不高兴的脸
	}	
}
//弹出层
function ShowWin(title,win_id,_this,callBack){
	$("#NefMban input").val("");
	var pagei = $.layer({
	   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	   btns: 2,
	   btn: ['确定','取消'],
	   yes: function(index){
	        //按钮【按钮一】的回调
		   if(callBack !=null){			   
			   callBack(_this,index);			   
		   }else{
			   layer.close(index);
		   }
	    },
	   title: title,
	   border: [0],
	   closeBtn: [0],
	   closeBtn: [0, true],
	   shadeClose: true,
	   area: ['450px','280px'],
	   page: {dom : '#'+ win_id}
   });
}
//提示
function tips(node){    
    layer.tips($(node).attr('name'), node, {
       guide:1,
       time: 1,
       maxWidth:240
   });
 }

//上传成功后替换商品图片
function repliceProductImg(li,imgUrl){
	
	var imgCount =  $("#divProductImg .own_img .own_yis").length;
    //获取图片的名称
    var tempID = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length);
    tempID = tempID.substring(0, tempID.lastIndexOf("."));
	var html='<div class="own_img">';
	html +='	<div class="img_dele"><img src="/statics/pic/delete_imgs.png"></div>';
	html +='	<div class="own_yis"><img src="http://img1.okimgs.com/'+imgUrl+'"></div>';
	html +=' 	<div class="she_ci"><input value="'+imgUrl+'" type="radio" id='+tempID+ ' name="productImg"><label for='+tempID+'>设为主图</label></div>';
    html +='  </div>';  
    $(li).children("div.own_img_one").remove();
	$(li).append(html);
	if(imgCount ==0){
		$("#"+tempID).attr("checked",true);
	}
	
}

// 文本框只能输入浮点型
function txtWritefloat(objID){
	//商品价格输入限制 失去焦点事件
	$("#"+objID).bind("keyup",function(){   
		$(this).val($(this).val().replace(/[^0-9.]/g,''));    
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/[^0-9.]/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  	
}
//文本框只能输入整数
function txtWriteint(objID){
	$("#"+objID).bind("keyup",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
}

function removeHTMLTag(str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag  
    str = str.replace(/[ | ]* /g,' '); //去除行尾空白  
    //str = str.replace(/ [\s| | ]* /g,' '); //去除多余空行  
    str=str.replace(/ /ig,'');//去掉   
    return str;
}

function Filter(str) {
	str = str.replace(/<\s*script[^>]*>(.|[\r\n])*?<\s*\/script[^>]*>/gi, '');
	str = str.replace(/<\s*style[^>]*>(.|[\r\n])*?<\s*\/style[^>]*>/gi, '');
	str = str.replace(/<\/?[^>]+>/g, '');
	str = str.replace(/\&[a-z]+;/gi, '');
    return str;
}

//动态生成属性预览
function PreviewParam(){
	$(".leimu_msg ul li").remove();
	$(".type_selec .selec_once").each(function(index,item){
		
		var attributeName =  $(this).find(".clao_le span").html();
		var showtype =  $(this).find(".clao_le span").attr("showtype");
		var value = "";
		if(showtype==1){
			value =$(this).find(".clao_rg  input").val();;
		}
		else if(showtype==2){
			value =$(this).find(".clao_rg  select").val();
		}else{
			$(this).find(".clao_rg  input:checkbox:checked").each(function(){
				value +=" "+ $(this).val();
			});
		}
		
		$(".leimu_msg ul").append("<li>"+attributeName+"：<span>"+value+"</span></li>");
	});
	
	$("#Fice_inp .self_one").each(function(){
		var attributeName = $(this).find("input[name='attributeName']").val();
		var value =$(this).find("input[name='valueName']").val(); 
		//结束本次循环 下一次
		if(attributeName ==""){
			return;
		}
		
		$(".leimu_msg ul").append("<li>"+attributeName+"：<span>"+value+"</span></li>");
	});
}

//动态创建款式列表
function getStyleList(isTitle){
	var keyValues=[];
	var keyNames=[];
	$(".pinpaixze_is").each(function(index,parentItem){		
		var valus=[];
		$(this).find(".pincrig input").each(function(index,item){
			if($(this).val() !=""){
				valus.push($(this).val());
			}
		});
		if(valus.length>0){
			keyNames.push($(this).find(".pincmas select option:selected").text());		
			keyValues.push(valus);
		}
	});	
	//设置好标题
	$(".size_titles").find(".coput_1,.coput_2,.coput_7").remove();
	if(keyNames.length>0){
		$.each(keyNames,function(index,item){	
			if(index==0){
				$(".size_titles .coput_3").before("<li name='liKeyName' class='coput_1'>"+item+"</li>");
			}else if(index==1){
				$(".size_titles .coput_3").before("<li name='liKeyName' class='coput_2'>"+item+"</li>");
			}else{
				$(".size_titles .coput_3").before("<li name='liKeyName' class='coput_7'>"+item+"</li>");
			}
		});
	}else{
		$(".size_titles .coput_3").before("<li name='liKeyName' class='coput_1'></li>");
	}

	//是否只修改标题
	if(isTitle == false){
		return;
	}
	//清空款式
	$("#Foct_Shop ul").remove();
	var styleList = getStyleAssembleList([],keyValues);
	
	//设置款式
	if(styleList ==null || styleList.length==0){
		return;
	}
	
	var txtPrice = $("#txtProductPrice").val();
	var txtComminss =$("#txtProductComminss").val();
	var txtTotal =$("#txtTotal").val();
	var txtNum = $("#txtProductNum").val();
	
	$.each(styleList,function(index,item){		
		var html ="<ul name='styleDetail'> ";
		var values = item.split("&");
		$.each(values,function(vindex,vitem){
			if(vindex==0){
				html +="<li class='inp_col1'><div name='divValueName' class='color_yase'>"+vitem+"</div></li>";
			}else if(vindex==1){
				html +="<li class='inp_col2'><div name='divValueName' class='color_yase'>"+vitem+"</div></li>";
			}else{
				html +="<li class='inp_col7'><div name='divValueName' class='color_yase'>"+vitem+"</div></li>";
			}
		});
		if(pubProductType == 1 || pubProductType == 2){//普通微店主、供应商
			html +="<li class='inp_col3'>";
			html +="	<div class='inps_san fl'>";
			html +="		<input type='text' placeholder='商品价格' maxlength='10' class='w66 btn_h30 fl dis_b' value='"+txtPrice+"' name='sellPrice'>";
			html +='		<span class="dis_b f14 fm_aria fw_b fl ft_c6">+</span>';
			html +='		<input type="text" placeholder="佣金" maxlength="10" class="w66 btn_h30 fl dis_b" value="'+txtComminss+'" name="sellComminss">';
			html +='		<span class="dis_b f14 fm_aria fw_b fl ft_c6">=</span>';
			html +='		<input type="text" placeholder="微店价" class="w66 btn_h30 fl dis_b" disabled="disabled" value="'+txtTotal+'" name="sellTotal">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col4">';
			html +='	<div class="inps_san_ones fl">';
			html +='		<input type="text" placeholder="数量" maxlength="10" class="w80 btn_h30 ml_30" name="txtSellNum" value="'+txtNum+'">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col5">';
			html +='	<div class="inps_san_ones fl">';
			html +='		<input type="text" placeholder="商家编码" maxlength="20" name="txtBussinessNo" class="w80 btn_h30" value="">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col6"> <a href="javascript:void(0);"><img src="/statics/pic/delete_icon.png"></a> </li>';
			html +='</ul>';
		} else if(pubProductType == 6){//代理区供应商
			var txtSellPrice = $("#txtSellPrice").val();
			var txtSupplyPrice = $("#txtSupplyPrice").val();
			html +="<li class='inp_col3'>";
			html +="	<div class='inps_san fl'>";
			html +='		<span class="dis_b f14 fm_aria fw_b fl ft_c6">零售价:</span>';
			html +="		<input type='text' placeholder='零售价' maxlength='10' class='w66 btn_h30 fl dis_b' value='"+txtSellPrice+"' name='adSellPrice'>";
			html +='		<span class="dis_b f14 fm_aria fw_b fl ft_c6">供货价:</span>';
			html +='		<input type="text" placeholder="供货价" maxlength="10" class="w66 btn_h30 fl dis_b" value="'+txtSupplyPrice+'" name="supplyPrice">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col4">';
			html +='	<div class="inps_san_ones fl">';
			html +='		<input type="text" placeholder="数量" maxlength="10" class="w80 btn_h30 ml_30" name="txtSellNum" value="'+txtNum+'">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col5">';
			html +='	<div class="inps_san_ones fl">';
			html +='		<input type="text" placeholder="商家编码" maxlength="20" name="txtBussinessNo" class="w80 btn_h30" value="">';
			html +='	</div>';
			html +='</li>';
			html +='<li class="inp_col6"> <a href="javascript:void(0);"><img src="/statics/pic/delete_icon.png"></a> </li>';
			html +='</ul>';
		} else if(pubProductType == 3 || pubProductType == 4){//平台号品牌号
			var txtProxyPrice = $("#txtProxyPrice").val();
			var txtFloorPrice = $("#txtFloorPrice").val();
			html +='<li class="inp_col3">';
			html +='<div class="inps_san fl">';
			html +='<input type="text" name="sellPrice" value="'+txtPrice+'" maxlength="10" class="w66 btn_h30 fl dis_b" placeholder="商品价格" />'; 
			html +='<span class="dis_b f14 fm_aria fw_b fl ft_c6">+</span><input type="text" name="sellComminss" value="'+txtComminss+'" class="w66 btn_h30 fl dis_b" placeholder="佣金" />'; 
			html +='<span class="dis_b f14 fm_aria fw_b fl ft_c6">=</span><input type="text" name="sellTotal" value="'+txtTotal+'" disabled="disabled" class="w66 btn_h30 fl dis_b" placeholder="微店价" />';
			html +='</div>';
			html +='</li>';
			html +='<li class="inp_col4">';
			html +='<div class="inps_san_ones fl">';
			html +='<input type="text" value="'+txtNum+'" maxlength="10" name="txtSellNum" class="w80 btn_h30 ml_30" placeholder="库存" />';
			html +='</div>';
			html +='</li>';
			html +='<li class="inp_col6"><a href="javascript:void(0);"><img src="/statics/pic/delete_icon.png" /></a></li>';
			html +='</ul>';
			html +='<ul>';
			html +='<li class="inp_col1">';
			html +='<div class="color_yase"></div>';
			html +='</li>';
			if (keyNames.length > 1) {
				html +='<li class="inp_col2">';
				html +='<div class="ul_fls"><ul><li></li></ul></div>';
				html +='</li>';
			}
			html +='<li class="inp_col3">';
			html +='<div class="inps_san fl">';
			html +='<span class="fl">代理价：</span> <input type="text" name="proxyPrice" value="'+txtProxyPrice+'" maxlength="10" class="w80 btn_h30 fl dis_b" placeholder="代理价" />';
			html +='</div>';
			html +='</li>';
			html +='<li class="inp_col4">';
			html +='<div class="inps_san_ones fl"></div>';
			html +='</li>';
			html +='<li class="inp_col6"></li>';
			html +='</ul>';
			html +='<ul>';
			html +='<li class="inp_col1">';
			html +='<div class="color_yase"></div>';
			html +='</li>';
			if (keyNames.length > 1) {
				html +='<li class="inp_col2">';
				html +='<div class="ul_fls"><ul><li></li></ul></div>';
				html +='</li>';
			}
			html +='<li class="inp_col3">';
			html +='<div class="inps_san fl">';
			html +='<span class="fl">落地价：</span><input type="text" name="floorPrice" value="'+txtFloorPrice+'" maxlength="10" class="w80 btn_h30 fl dis_b" placeholder="落地价" />';
			html +='</div>';
			html +='</li>';
			html +='<li class="inp_col4">';
			html +='<div class="inps_san_ones fl"></div>';
			html +='</li>';
			html +='<li class="inp_col6"></li>';
			html +='</ul>';
		}
		
		$("#Foct_Shop .blank2").before(html);
	});

}

//获取组装的库存列表
function getStyleAssembleList(first, baseData) {
    //最终返回结果
    var result = [];
    //每次接收递归的返回值
    var two = [];
    if (baseData.length > 1) {
        //将要被组合的
        first = baseData[0];
        baseData.splice(0, 1);
        //返回的结果
        two = getStyleAssembleList(first, baseData);
    }
    else {
        //最后一个 直接返回
        result = baseData[0];
    }

    if (first.length > 0 && two.length > 0) {
        $.each(first, function (firstIndex, firstItem) {
            $.each(two, function (twoIndex, twoItem) {
                result.push(firstItem + "&" + twoItem);
            });
        });
    }

    return result;
}

//获取属性模板
function getParamModel(mid){
	if(mid < 1){
		return;
	}
	
	$.ajax({
        url: "/Product/getParamModel",
        type: "post",
        data: { pModelID:mid },
        dataType : 'json',
        success: function (data) {
            if(data !=null && data !=""){
            	if(data.keyList !=null && data.keyList.length>0){
            		var paramHtml="";
            		var selfHtml="";
            		$.each(data.keyList,function(kindex,kitem){
            			//系统参数
            			if(kitem.sysAttributeID>0){
            				paramHtml +='<div class="selec_once fl">  ';
            				paramHtml +='	<div class="clao_le fl f14 tr ft_c3">';
            				paramHtml +='		<span ismust="'+kitem.isMust+'" attributeid="'+kitem.attributeId+'" showtype="'+kitem.showtype+'">'+kitem.attributeName+'</span>：';
            				paramHtml +=' 	</div>';
            				paramHtml +='	<div class="clao_rg fl">';
            				if(kitem.showtype ==1){
            					paramHtml +='		<input type="text" class="btn_h42 f14 w350" maxlength="16" value="'+kitem.valueList[0].value+'" avid="'+kitem.valueList[0].avid+'">';
                			}else if(kitem.showtype ==2){
                				paramHtml +='<select class="btn_h42 f14 w350">';
                				if(kitem.valueList !=null && kitem.valueList.length>0){
                					$.each(kitem.valueList,function(vindex,vitem){
                						if(vitem.isDefault==1){
                							paramHtml +='<option selected="selected" value="'+vitem.value+'" avid="'+vitem.avid+'">'+vitem.value+'</option>   ';
                						}else{
                							paramHtml +='<option value="'+vitem.value+'" avid="'+vitem.avid+'">'+vitem.value+'</option>   ';
                						}               						
                					});
                				}                				
                				paramHtml +='</select>';		                     
                			}else{
                				paramHtml +='<div class="fl check_boxfont">';
                				if(kitem.valueList !=null && kitem.valueList.length>0){
                					$.each(kitem.valueList,function(vindex,vitem){
                						if(vitem.isDefault==1){
                							paramHtml +='	<input type="checkbox" checked="checked" id="ck'+vitem.avid+'" value="'+vitem.value+'" avid="'+vitem.avid+'">';	  
                						}else{
                							paramHtml +='	<input type="checkbox" id="ck'+vitem.avid+'" value="'+vitem.value+'" avid="'+vitem.avid+'">';	  
                						}
                						paramHtml +='	<label for="ck'+vitem.avid+'">'+vitem.value+'</label> ';
                					});				           							                        
                				}
                				paramHtml +='</div>';	
                			}           				
            				paramHtml +='	</div>';
            				paramHtml +='</div>';           
            			}else{//自定义参数
            				selfHtml +='<div class="self_one fl">';
            				selfHtml +='	<div class="once_inps fl">';
            				selfHtml +='		<input type="text" maxlength="16" value="'+kitem.attributeName+'" attributeid="'+kitem.attributeId+'" class="btn_h42 tr pr_10 w104 f14" name="attributeName">：';
            				selfHtml +='	</div>';
            				selfHtml +='	<div class="once_inps fl">';
            				selfHtml +='		<input type="text" maxlength="16" value="'+kitem.valueList[0].value+'" avid="'+kitem.valueList[0].avid+'" class="btn_h42 f14 w222" name="valueName" value="">';
            				selfHtml +='	</div>';
            				selfHtml +='	<div class="fl xiao_icon"><a href="javascript:;" name="Del_img"><img src="/statics/pic/delete_icon.png"></a></div>';
            				selfHtml +='</div>';                 	                    
            			}
            		});
            		//替换到开始的html
            		$("#divSetParam .type_selec .selec_once").remove();
            		$("#divSetParam .type_selec .blank1").before(paramHtml);
            		
            		selfHtml +='<div class="self_one fl">';
            		selfHtml +='	<div class="once_inps fl">';
            		selfHtml +='		<input type="text" class="btn_h42 tr pr_10 w104 f14" maxlength="16" name="attributeName">：';
            		selfHtml +='	</div>';
            		selfHtml +='	<div class="once_inps fl">';
            		selfHtml +='		<input type="text" class="btn_h42 f14 w222" maxlength="16" name="valueName" value="">';
            		selfHtml +='	</div>';
            		selfHtml +='	<div class="fl xiao_icon"><a href="javascript:;" name="Add_imgs"><img src="/statics/pic/tianjia_icon.png"></a></div>';
            		selfHtml +='</div>';
                
            		$("#Fice_inp").html(selfHtml);
            		
            	}
            }
        },
        error: function () {
            alert("数据提交失败！请稍后重试！");
        }
    });		
}

//js对象 转换json 串
function obj2Str(obj) {
    switch (typeof (obj)) {
        case 'object':
            var ret = [];
            if (obj instanceof Array) {
                for (var i = 0, len = obj.length; i < len; i++) {
                    ret.push(obj2Str(obj[i]));
                }
                return '[' + ret.join(',') + ']';
            }
            else if (obj instanceof RegExp) {
                return obj.toString();
            }
            else {
                for (var a in obj) {
                    ret.push(a + ':' + obj2Str(obj[a]));
                }
                return '{' + ret.join(',') + '}';
            }
        case 'function':
            return 'function() {}';
        case 'number':
            return obj.toString();
        case 'string':
            return "\"" + obj.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function (a) { return ("\n" == a) ? "\\n" : ("\r" == a) ? "\\r" : ("\t" == a) ? "\\t" : ""; }) + "\"";
        case 'boolean':
            return obj.toString();
        default:
            return obj.toString();
    }
}


/*==================属性上传图片控件初始化==========================*/
function UpLoadImgInit() {
    var upImg = $("#btnUpImg").uploadify({
        'buttonText': '选择图片',
        'buttonClass': 'browser',
        'dataType': 'html',
        'removeCompleted': false,
        'swf': '/statics/js/lib/uploadify/uploadify.swf',//flash文件路径
        'debug': false,
        'width': '75',//按钮宽度
        'height': '21',//按钮高度
        'auto': true,
        'multi': true,
        'queueSizeLimit': 6,//图片最大上传数量
        'timeoutuploadLimit':6,//能同时上传的文件数目
        'fileTypeExts': '*.jpg;*.gif;*.png;*.jpeg',//文件类型
        'fileTypeDesc': '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
        'formData':{FileUpload:"proimage",cate:1,type:1},//参数
        'uploader': 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
        'fileSizeLimit': '500',//文件最大大小
        'progressData': 'speed',
        'removeCompleted': true,
        'removeTimeout': 0,
        'requestErrors': true,
        'onFallback': function () {
            alert("您的浏览器没有安装Flash");
        },
        'onUploadStart': function (file) {
        	return;
        	alert('id: ' + file.id
        			+ ' - 索引: ' + file.index 
        			+ ' - 文件名: ' + file.name 
        			+ ' - 文件大小: ' + file.size 
        			+ ' - 类型: ' + file.type
        			+ ' - 创建日期: ' + file.creationdate
        			+ ' - 修改日期: ' + file.modificationdate
        			+ ' - 文件状态: ' + file.filestatus );

/*        	var img = new Image()
        	img.src = "pic path"
        	alert(img.width,img.height)*/
        },
        'onProgress ': function () {

        },
        'onUploadSuccess': function (file, data, response) {
            if (response == true) {
                data = eval("("+data+")");
                if(data !=null && data.Status ==1)
                {
                	var li = $(".she_img  li>div.own_img_one").eq(0).parent();
                	repliceProductImg(li,data.ImgUrl);
                }
            }
        },
        'onDialogClose': function (swfuploadifyQueue) {
        	var len = $(".she_img  li>div.own_img_one").length;
            if (swfuploadifyQueue.filesSelected<= 6 && swfuploadifyQueue.filesSelected > len) {
                alert("最多只能选择"+len+"张图片!");
                upImg.uploadify('cancel', '*')
                return;               
            }
        }
    });
}

function UpLoadOneImgInit(objID,btnType) {
	var width =95;
	var height =28;
	var buttonText ="上传图片";
	var fileSize =100;
	if(btnType =="productImg"){
		width=121;
		height =121;
		buttonText ="";
		fileSize=500;
	}
	
    $("#" + objID).uploadify({
        'buttonText': buttonText,
        'buttonClass': 'browser',
        'dataType': 'html',
        'removeCompleted': false,
        'swf': '/statics/js/lib/uploadify/uploadify.swf',//flash文件路径
        'debug': false,
        'width': width,//按钮宽度
        'height': height,//按钮高度
        'auto': true,
        'multi': false,//是否多文件上传
        'queueSizeLimit': 1,//图片最大上传数量
        'timeoutuploadLimit':1,//能同时上传的文件数目
        'fileTypeExts': '*.jpg;*.gif;*.png;*.jpeg',//文件类型
        'fileTypeDesc': '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
        'formData':{FileUpload:"proimage",cate:1,type:1},//参数
        'uploader': 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
        'fileSizeLimit': fileSize,//文件最大大小
        'progressData': 'speed',
        'removeCompleted': true,
        'removeTimeout': 0,
        'requestErrors': true,
        'onFallback': function () {
            alert("您的浏览器没有安装Flash");
        },
        'onUploadStart': function (file) {

        },
        'onProgress ': function () {

        },
        'onUploadSuccess': function (file, data, response) {
            if (response == true) {
                data = eval("("+data+")");
                if(data !=null && data.Status ==1)
                {
                	if(btnType =="productImg"){
                		var li = $("#" + objID).closest("li");
                		repliceProductImg(li,data.ImgUrl);
                	}
                	else{
                		if($("#" + objID).closest(".scimg_del").find(".imgsew").length>0){
                			$("#" + objID).closest(".scimg_del").find(".imgsew img").attr("src","http://img1.okimgs.com/"+data.ImgUrl);                			
                		}else{
                    		var html ='<div class="fr imgsew">'
                    			+'			<img width="30" height="30" src="http://img1.okimgs.com/'+data.ImgUrl+'">'
                    			+'			<a href="javascript:;" name="delColor" class="ml_10 mr_20">删除</a>'
                    			+'		</div>';
                    		$("#" + objID).closest(".scimg_del").append(html);
                		}
                	}
                }
            }
        },
        'onDialogClose': function (swfuploadifyQueue) {

        }
    });   
}

//编辑器初始化
var pcEditor;
var wapEditor;
function EditorInit() {
  pcEditor = new baidu.editor.ui.Editor({
      toolbars: [[ 'source', '|', 'undo', 'redo', '|',
      'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript',
      'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
       'paragraph', 'fontfamily', 'fontsize', '|',
      'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
      'link', 'unlink', 'anchor', '|',
      'insertimage',
      'emotion', 'map', 'pagebreak', 'background', '|',
      'horizontal', 'spechars', '|', 'preview', 'help']],
      	//initialFrameWidth :870,//设置编辑器宽度
		initialFrameHeight:320,//设置编辑器高度
		scaleEnabled:true
  });
  pcEditor.render("pcEditor");
  pcEditor.ready(function () {
      //设置编辑器的内容
      pcEditor.setContent($("#pcContent").text());
      //获取html内容，返回: <p>hello</p>
      //pcEditor.getContent();
      //获取纯文本内容，返回: hello
      //var txt = myEditor.getContentTxt();
  });
  
  wapEditor = new baidu.editor.ui.Editor({
      toolbars: [[ 'insertimage']],
	  //toolbars: [],
      contextMenu:[],
      //initialFrameWidth :800,//设置编辑器宽度
		initialFrameHeight:350,//设置编辑器高度
		scaleEnabled:true
  });
  wapEditor.render("wapEditor");
  wapEditor.ready(function () {
      //设置编辑器的内容
  	wapEditor.setContent($("#wapContent").text());
      //获取html内容，返回: <p>hello</p>
      //wapEditor.getContent();
      //获取纯文本内容，返回: hello
      //var txt = myEditor.getContentTxt();
  });
}

function removeSpace(str){
	str.replace(/(^\s+)|(\s+$)/g,"");
	return str;
}

//转换成Decimal类型 2位小数
function changeTwoDecimal_f(x)
{ 
    var f_x = parseFloat(x);
    if (isNaN(f_x))
    {
//        location.href=location.href;
//        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x*100)/100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0)
    {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2)
    {
        s_x += '0';
    }
    return s_x;
}

function showShopClassAdd(title,width, height,win_id,_this,callBack){
	$("#win_div_4 input").val("");
	var pagei = $.layer({
	   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	   btns: 2,
	   btn: ['确定','取消'],
	   yes: function(index){
	        //按钮【按钮一】的回调
		   if(callBack !=null){			   
			   callBack(_this,index);			   
		   }else{
			   layer.close(index);
		   }
	    },
	   title: title,
	   border: [0],
	   closeBtn: [0],
	   closeBtn: [0, true],
	   shadeClose: true,
	   area: [ width, height ],
	   page: {dom : '#'+ win_id}
   });
}
//判断数组元素是否重复
function isRepeat(arr) {
	var hash = {};
	for ( var i in arr) {
		if (hash[arr[i]])
			return true;
		hash[arr[i]] = true;
	}
	return false;
}

