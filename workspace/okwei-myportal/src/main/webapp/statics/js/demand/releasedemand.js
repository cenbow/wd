/**
 * 
 */

var checkedIDs =[];
var isEdit = false;
$(function(){
	
	//初始化 已选择 商品ID数组
	$("#id_fbcp input[name='checkProduct']").each(function(){
		checkedIDs.push($(this).val());
	});
	//初始化 商品列表 分页
	getNoDemandProducts(1,true);
	//初始化 已选择 商品列表
	initCheckedProductPage();
	
	//初始化 店铺分类
	getShopClass(1);
	//初始化文件上传
	UpLoadFileInit();
	UpLoadImgInit();
	//初始化地区选择	
	initRegion();
	
	//初始化百度编辑器
	EditorInit();
	
	//如果是更新 则隐藏添加
	if($("#txtdemandID").val() >0){
		$("#id_baocunqian").hide();
	}
	
	//只能输入整数
	txtWriteint("txtOrderAmout");
	txtWriteint("txtShopReward");
	txtWriteint("txtAgentReward");
	
	//查询没有招商需求的商品
	$("#btnSearchProduct").bind("click",function(){
		getNoDemandProducts(1,true);
	});
	//tip
    $("[name=name_rzy]").each(function(i){
        $(this).mouseenter(function(){
            $(".hover_yes").show();
            $("[id^=hover_yes_]").hide();
            $("#hover_yes_"+i).show();
        })
        $(this).mouseleave(function(){
            $(".hover_yes").hide();
            $("[id^=hover_yes_]").hide();
            $("#hover_yes_"+i).hide();
        })
    });
	
	
	//一级分类change
	$("#selShopClassOne").bind("change",function(){
		$("#selShopClassTwo option:not(:first)").remove();
		getShopClass(2,$(this).val());
	});
	//全选
	$("input[name='checkall']").bind("click",function(){
		if($(this).prop("checked")==true){
			$(this).closest("table").find("input[type='checkbox']:visible ").prop("checked",true);
		}else{
			$(this).closest("table").find("input[type='checkbox']:visible").prop("checked",false);
		}		
	});
	
	//选择商品弹出层
	$("#btnSelectProduct").click(function(){
		ShowWin('选择招商产品','win_div_1',$(this),enterSelectProduct);
	});
	//已选择商品弹出层
	$("#btnMoreProduct").click(function(){
		ShowWin('已选招商产品','win_div_2',$(this),enterMoreProduct)
	});
	
    /* 添加量化要求 */
    $("#id_anniuLH").on("click",function(){
    	addNumRequire();
    })
    //移除量化要求
    $("img[name=close_LH]").live("click",function(){
        $(this).parent().remove();
    });
    //PC APP 详情
    $("#btnpctxt").bind("click",function(){
    	$("#PcNew_1").show();
    	$("#PcNew_2").hide();
    	$(this).attr("class","gygl_xxsm_left_yes");
    	$(this).siblings().attr("class","gygl_xxsm_left_no");
    });
    $("#btnwaptxt").bind("click",function(){
    	$("#PcNew_2").show();
    	$("#PcNew_1").hide();
    	$(this).attr("class","gygl_xxsm_left_yes");
    	$(this).siblings().attr("class","gygl_xxsm_left_no");
    });
    //生成移动端详情
	$("#btnCreateWapDes").click(function(){		
		var WAPDes = Filter(pcEditor.getContent());
		wapEditor.setContent(WAPDes);
		$("#btnwaptxt").click();
	});
	
	$("#btnSaveRequired").bind("click",function(){
		saveRequired();
	});
	
	//添加代理条件
	$("[name='addRequired']").live("click",function(){		
		clearRequired();
		$("#id_baocunqian").show();
		$("#id_baocunhou").hide();
		$("input[name='showRequired']").removeProp("checked");
		document.getElementById('requiredpanle').scrollIntoView();
	});
	//删除代理条件
	$("a[name='deleteRequired']").live("click",function(){		
		var _this =$(this); 
		layer.confirm("您确定要删除该区域代理商要求及条件设置么？",function(){
			layer.closeAll();
			var requiredID =_this.closest("div[name='divrequired']").attr("data");
			_this.closest("div[name='divrequired']").remove();
			if($("div[name='divrequired']").length <1){
				$("#id_baocunqian").show();
				$("#id_baocunhou").hide();
				document.getElementById('requiredpanle').scrollIntoView();
			}	
			
			var showRequiredID = $("#id_baocunhou").attr("data");
			if(requiredID == showRequiredID){
				$("#id_baocunhou").hide();
			}
		});						
	});
	//修改代理条件
	$("a[name='editRequired']").live("click",function(){
		isEdit = true;
		editRequired($(this).closest("div[name='divrequired']"));
	});
	$("#showRegionNames").parent().siblings("input").bind("click",function(){
		isEdit = true;
		var data =$("#id_baocunhou").attr("data");
		editRequired($("div[name='divrequired'][data='"+data+"']"));
	});
	
	
	//展开代理条件
	$("input[name='showRequired']").live("click",function(){			
		//赋值
		if($(this).prop("checked") == true){
			
			var parentDiv = $(this).closest("div[name='divrequired']");	
			parentDiv.siblings("div[name='divrequired']").find("input[name='showRequired']").removeProp("checked");
			
			var parentDivID = parentDiv.attr("id");
			var requiredID = parentDiv.attr("data");
			var regionIDs = parentDiv.find("input[name='txtRegionIDs']").val();
			var tempNameString = parentDiv.find("input[name='txtRegionNames']").val();
			var agentRequired = parentDiv.find("input[name='txtAgentRequired']").val();
			var support = parentDiv.find("input[name='txtSupport']").val();
			var requiredKVs = parentDiv.find("input[name='txtrequiredKVs']").val();
			var fileName = parentDiv.find("input[name='txtFileName']").val();
			var filePath = parentDiv.find("input[name='txtFilePath']").val();
			var agentReward = parentDiv.find("input[name='txtAgentReward']").val();
						
			$("#showRegionNames").html(tempNameString);
			$("#showAgentRequired").html(agentRequired);
			$("#showSupport").html(support);
			
			$("#showRequiredKVs").html("");
			requiredKVs = eval(requiredKVs);
			$.each(requiredKVs,function(index,item){
				$("#showRequiredKVs").append("<p>"+item.key+"："+item.value+"</p>");				
			});
			$("#showFileName").html(fileName);
			
			if(filePath !=""){
				$("#showFilePath").attr("href",filePath).show();
			}else{
				$("#showFilePath").hide();
			}
			
			$("#showAgentReward").html(agentReward);
			
			$("#id_baocunhou").attr("data",requiredID);
			$("#id_baocunqian").hide();
			$("#id_baocunhou").show();
			document.getElementById('requiredpanle').scrollIntoView();
		}else{
			$("#id_baocunhou").hide();
		}		
	});
	
	$("#btnSaveDraft").bind("click",function(){				
		if(isEdit){
			layer.confirm("您的代理商要求及条件设置未保存，是否需要保存？",function(){
				saveRequired();
				layer.closeAll();
				saveDemand(0);
			},function(){
				isEdit = false;
				saveDemand(0);
			});		
		}else{
			saveDemand(0);
		}
	});
	
	$("#btnSaveDemand").bind("click",function(){
		if(isEdit){
			layer.confirm("您的代理商要求及条件设置未保存，是否需要保存？",function(){
				saveRequired();
				layer.closeAll();
				saveDemand(4);
			},function(){
				isEdit = false;
				saveDemand(4);
			});		
		}else{
			saveDemand(4);
		}
	});
	
});


function saveDemand(saveType){
	
	var title =$("#txtTitle").val();
	var mainimg = $("#mainImage").attr("src");
	
	var pcDetails= pcEditor.getContent();
	var appDetails = wapEditor.getContent();
	var shopReward= $("#txtShopReward").val();
	var orderAmout = $("#txtOrderAmout").val();
	
	if(title ==""){
		alert("请填写招商标题");
		return;
	}
	if(title =="" || mainimg=="/statics/pic/xin_inselimg.png"){
		alert("请上传招商主图");
		return;
	}	
	if($("div[name='divrequired']").length <1){
		alert("请先设置招商要求及条件");
		return;
	}
	if(orderAmout =="" || orderAmout <0){
		alert("请设置落地店首单要求");
		return;
	}
	if(shopReward =="" || shopReward <0){
		alert("请设置落地店悬赏");
		return;
	}
	
	var dProductVOs =[];
	$("#id_fbcp tr input[name='checkProduct']").each(function(){
		dProductVOs.push({productID:$(this).val()});
	});
	
	var dRequiredVOs=[];
	$("div[name='divrequired']").each(function(){
						
		var regionVOs =[];
		var regionIDs = $(this).find("input[name='txtRegionIDs']").val();
		$.each(regionIDs.split("|"),function(index,item){
			if(item !=""){
				regionVOs.push({code:item});
			}
		});
		
		var requiredKVVOs=[];
		var requiredKVs = $(this).find("input[name='txtrequiredKVs']").val();
		if(requiredKVs !=null && requiredKVs !=""){
			requiredKVs = eval(requiredKVs);
			$.each(requiredKVs,function(index,item){
				requiredKVVOs.push({rkey:item.key,rvalue:item.value});
			});
		}
		
		var agentRequired = $(this).find("input[name='txtAgentRequired']").val();
		var support  = $(this).find("input[name='txtSupport']").val();
		var filePath  = $(this).find("input[name='txtFilePath']").val();
		var fileName  = $(this).find("input[name='txtFileName']").val();
		var agentReward  = $(this).find("input[name='txtAgentReward']").val();
		
		dRequiredVOs.push({
			agentRequired:agentRequired,
			support:support,
			contract:fileName,
			contractPath:filePath,
			agentReward:agentReward,
			regionVOs:regionVOs,
			requiredKVVOs:requiredKVVOs
		});		
	});	
	
	var demand = {
			title:title,
			mainImage:mainimg,
			dProductVOs : dProductVOs,
			orderAmout : orderAmout,
			shopReward:shopReward,
			pcDetails:pcDetails,
			appDetails:appDetails,
			dRequiredVOs:dRequiredVOs,
			state:saveType,
			demandId:$("#txtdemandID").val()
	};
	var loadIndex = layer.load('正在努力的提交...');
	
	$.ajax({
        url: "/demand/saveDemand",
        type: "post",
        data: {json:obj2Str(demand)},
        success: function (data) {     
        	if(data !=null && data !=""){
        		data = eval(data);        		
        		if(data.state=="Success"){
        			alert(data.message,true);
        			location.href="/demand/myDemandList";
        		}else{
        			alert(data.message);
        		}
        	}
        	
        	layer.close(loadIndex);
        },
        error: function () {
        	 layer.close(loadIndex);
        	 alert("数据提交失败，请稍后重试！");
        }
    });
	
}

//添加量化要求
function addNumRequire(key,value){
	if(key == undefined || key ==null){
		key ="";
	}
	if(value ==undefined || value ==null){
		value ="";
	}
	
    $("#id_addLH").append("" +
            "<div class='fl mb_10'>" +
            "<input value='"+key+ "' class='fbxq_jbxx_text2' name='txtRequiredKey'>" +
            "<span class='fl tc' style='width: 24px;color: #000;'>：</span>" +
            "<input value='"+value+"' class='fbxq_jbxx_text2' name='txtRequiredValue'>" +
            "<img src='/statics/pic/del_ficfuncyes.png' class='fl shou mt_5 ml_10' name='close_LH'>" +
            "</div>");
}

//保存地区条件
function saveRequired(){
	isEdit =false;
	var regionIDs = $("#selectedRegion").val();
	var regionNameStr = $("#showRegName").html();
	var agentRequired =$("#txtAgentRequired").val();
	var support =$("#txtSupport").val();
	var requiredKVs =[];
	$("#id_addLH>div").each(function(){
		var key = $(this).find("input[name='txtRequiredKey']").val();
		var value = $(this).find("input[name='txtRequiredValue']").val();
		
		if(key !="" && value !=""){
			requiredKVs.push({key:key,value:value});
		}		
	});
	var filePath = $("#txtFilePath").val();
	var fileName = $("#txtFileName").val();
	var agentReward =$("#txtAgentReward").val();	
	
	if(regionIDs ==""){
		alert("请选择区域");
		return;
	}
	if(agentRequired ==""){
		alert("请填写代理条件");
		return;
	}
	if(agentReward =="" || agentReward<0){
		alert("请填写代理悬赏");
		return;
	}
	
	var requiredCount;
	var requiredID = $("#id_baocunqian").attr("nowID");
	if(requiredID !=undefined && requiredID !=null){
		requiredCount = $("#"+requiredID).attr("data");
		$("#"+requiredID).remove();
	}else{
		requiredCount = $("div[name='divrequired']").length;
	}
	
	var html ="";	
	html+="<div name='divrequired' data='"+requiredCount+"' id='divrequired"+requiredCount+"' style='background: #FFFAEC;' class='fl mzh_width_100 f14 bor_bo pt5 pb5 line_42'>";
	html+="    <span style='text-indent: 20px;' class='fl'>指定地区："+regionNameStr;
	html+="		</span>";
	html+="    <div class='fr mr_20'>";
	html+="        <input type='checkbox' id='showRequired"+requiredCount+"' name='showRequired'>";
	html+="        <label for='showRequired"+requiredCount+"'>展开</label>";
	html+="    </div>";
	html+="    <a name='deleteRequired' class='fr ft_lan mr_10' href='javascript:;'>删除</a>";
	html+="    <a name='editRequired' class='fr ft_lan mr_10' href='javascript:;'>修改</a>";
	html+="    <a name='addRequired' class='fr ft_lan mr_10' href='javascript:;'>新增</a>";
	html+="    <input value='"+regionIDs+"' name='txtRegionIDs' type='hidden' />";
	html+="    <input value='"+regionNameStr+"' name='txtRegionNames' type='hidden' />"; 
	html+="    <input value='"+agentRequired+"' name='txtAgentRequired' type='hidden' />";
	html+="    <input value='"+support+"' name='txtSupport' type='hidden' />";
	html+="    <input value='"+obj2Str(requiredKVs)+"' name='txtrequiredKVs' type='hidden' />";
	html+="    <input value='"+fileName+"' name='txtFileName' type='hidden' />";
	html+="    <input value='"+filePath+"' name='txtFilePath' type='hidden' />";
	html+="    <input value='"+agentReward+"' name='txtAgentReward' type='hidden' />";
	html+="</div>";
	
	$("#id_baocunqian").before(html);
	//清空填写
	$("#id_baocunqian").hide();
	
	clearRequired();
	document.getElementById('requiredpanle').scrollIntoView();
}

function clearRequired(){
	$("#selectedRegion").val("");
	$("#selectedRegionName").val("");
	$("#showRegName").html("");
	$("#txtAgentRequired").val("");
	$("#txtSupport").val("");
	$("#id_addLH>div:not(:first)").remove();
	$("#id_addLH>div input").val("");
	$("#txtFilePath").val("");
	$("#txtFileName").val("");
	$("#txtAgentReward").val("");	
}

function editRequired(divRequired){
	
	var parentDiv = divRequired;		
	var requiredID = parentDiv.attr("data");
	var regionIDs = parentDiv.find("input[name='txtRegionIDs']").val();
	var tempNameString = parentDiv.find("input[name='txtRegionNames']").val();
	var agentRequired = parentDiv.find("input[name='txtAgentRequired']").val();
	var support = parentDiv.find("input[name='txtSupport']").val();
	var requiredKVs = parentDiv.find("input[name='txtrequiredKVs']").val();
	var fileName = parentDiv.find("input[name='txtFileName']").val();
	var filePath = parentDiv.find("input[name='txtFilePath']").val();
	var agentReward = parentDiv.find("input[name='txtAgentReward']").val();

	$("#id_addLH>div:not(:first)").remove();
	$("#selectedRegion").val(regionIDs);
	$("#showRegName").html(tempNameString);
	$("#txtAgentRequired").val(agentRequired);
	$("#txtSupport").val(support);
	$("#txtFileName").val(fileName);
	$("#txtFilePath").val(filePath);
	$("#txtAgentReward").val(agentReward);
	if(requiredKVs !=""){
		requiredKVs = eval(requiredKVs);
		$.each(requiredKVs,function(index,item){
			if(index ==0){
				var tempdiv = $("#id_addLH>div").first();
				tempdiv.find("input[name='txtRequiredKey']").val(item.key);
				tempdiv.find("input[name='txtRequiredValue']").val(item.value);
			}else{
				addNumRequire(item.key,item.value);
			}
		});
	}
		
	initRegion();
	$("#id_baocunqian").attr("nowID",divRequired.attr("id")).show();
	$("input[name='showRequired']").removeProp("checked");
	$("#id_baocunhou").hide();
	document.getElementById('requiredpanle').scrollIntoView();
}

//获取不能选择的区域
function getNoSetArea(){
	var objs;
	
	var nowID = $("#id_baocunqian").attr("nowID");
	if(nowID != undefined && nowID !=""){
		objs = $("#"+nowID).siblings("div[name='divrequired']");
		console.log("1");
	}else{
		objs =$("div[name='divrequired']");
		console.log("2");
	}
	
	var checked ="";
	objs.each(function(index,item){
		checked += $(this).find("input[name='txtRegionIDs']").val();
	});
	
	return checked;
}

function initRegion(){
	
	$("#id_xuanze,#btnEditReg").unbind();
	$("#id_xuanze,#btnEditReg").bind("click", function() {

		var setarea=$("#selectedRegion").val();
		var nosetarea=getNoSetArea();
		$(this).region({
			"id":"setareanew",
			"setarea" : setarea,
			"notsetarea" : nosetarea,
			"relation":false,
			"ok" : function(name, data) {				
				$("#selectedRegion").val(data);
				$("#selectedRegionName").val(name);
				
				var regionNames =[];
				$.each(name.split(","),function(index,item){
					if(item !=""){
						regionNames.push(item);
					}
				});
				var tempNameStr="";	
				$.each(regionNames,function(index,item){
					if(index<8){
						tempNameStr += item + " "
					}
				});

				if(regionNames.length >8){
					tempNameStr += "等<a class='ft_lan' href='javascript:;'>"+regionNames.length+"个省市</a>";		
				}
				$("#showRegName").html(tempNameStr);
			}
		});
	});

}

var enterMoreProduct = function(_this,index){
	//取消选择的 全部干掉
	if($("#id_fbcp input[name='checkProduct']").not(":checked").length <1){
		return;
	}
	
	$("#id_fbcp input[name='checkProduct']").not(":checked").each(function(index,item){
		checkedIDs.splice($.inArray($(this).val(), checkedIDs),1);
		$(this).closest("tr").remove();		
	});
	
	initCheckedProductPage();
	
	layer.close(index);	
}


//选择未加入需求的商品 确定事件
var enterSelectProduct = function (_this,index){
	var isChange = false;
	$("#tbcheckProduct input[name='checkProduct']").each(function(index,item){
		
		if($(this).prop("checked")==true){//是否选中
			//是否未选  则添加
			if ($.inArray($(this).val(), checkedIDs) <0){ 
				checkedIDs.push($(this).val());
				isChange = true;
				//添加html 至已选区域						
				var tr = $(this).closest("tr");
				var productID =tr.find("input[name='checkProduct']").val();
				var productImg = tr.find("img[name='productimg']").attr("src");
				var title = tr.find("span[name='title']").html();
				var price =tr.find("span[name='price']").html();
				var comminss = tr.find("span[name='comminss']").html();
				var agentprice = tr.find("span[name='agentprice']").html();
				var shopprice = tr.find("span[name='shopprice']").html();
				var classname = tr.find("span[name='classname']").html();
				
				var html ="<tr class='bor_bo'>";
            	html +="    <td><input checked='checked' value='"+productID+"' name='checkProduct' type='checkbox' class='ml_10'></td>";
            	html +="    <td><img name='productimg' src='"+productImg+"' width='60' height='60'></td>";
            	html +="    <td><span name='title'>"+title+"</span></td>";
            	html +="    <td>";
            	html +="        <p>零售价：￥<span name='price'>"+price+"</span></p>";
            	html +="        <p>(含佣金：<span name='comminss'>"+comminss+"</span>)</p>";
            	html +="        <p>代理价：<span name='agentprice'>"+agentprice+"</span></p>";
            	html +="        <p>落地价：<span name='shopprice'>"+shopprice+"</span></p>";
            	html +="     </td>";
            	html +="    <td><span name='classname'>"+classname+"</span></td>";
            	html +="</tr>";
            	
            	$("#id_fbcp tbody").append(html);						
			}
		}else{
			//是否已选 则删除
			isChange = true;
			if ($.inArray($(this).val(), checkedIDs) >-1){ 
				checkedIDs.splice($.inArray($(this).val(), checkedIDs),1);
				//删除 已选区域的 html
				$("#id_fbcp tbody input[value='"+$(this).val()+"']").closest("tr").remove();
			}
		}				
	});
			
	//是否需要重新初始化分页
	if(isChange){
		initCheckedProductPage();
	}
	
	if(checkedIDs.length > 0){
		$("#id_gdcp").show();
	}
	
	layer.close(index);			
}

/**
 * 获取商品列表
 */
var getNoDemandProducts =  function(pageIndex,isInit){
	
	var title =$("#txtProductName").val();
	var productType =0;
	var type1 = $("#selShopClassOne").val();
	var type2 =$("#selShopClassTwo").val();
	if(type1 !=null && type1>0){
		productType=type1;
	}
	if(type2 !=null && type2>0){
		productType = type2;
	}
		
	$.ajax({
        url: "/demand/getNoDemandProducts",
        type: "post",
        data: {title:title,classType:productType,pageIndex:pageIndex,pageSize:4 },
        success: function (data) {          
            if(data ==null || data==""){
            	return ;
            }
            data = eval(data);
            //初始化翻页
            if(isInit){
            	var page =new MyPage(data.totalCount,{
	            	pageFooter:5,
	            	parentID:"pageParentDiv",
	            	pageSize:data.pageSize,
	            	callBack:getNoDemandProducts
            	});
            }
            //数据绑定
            $("input[name='checkall']").removeProp("checked");
            $("#tbcheckProduct").find("tr:not(:first)").remove();
            var html ="";
            $.each(data.list,function(index,item){
            	html +="<tr class='bor_bo'>";
            	html +="    <td><input ";
            	
            	if ($.inArray(item.productID.toString(), checkedIDs) >-1){
            		html +=" checked='checked' ";
            	}
            	html +=					"value='"+item.productID+"' name='checkProduct' type='checkbox' class='ml_10'></td>";
            	html +="    <td><img name='productimg' src='"+item.productImg+"' width='60' height='60'></td>";
            	html +="    <td><span name='title'>"+item.prodcutTitle+"</span></td>";
            	html +="    <td>";
            	html +="        <p>零售价：￥<span name='price'>"+item.price+"</span></p>";
            	html +="        <p>(含佣金：<span name='comminss'>"+item.comminss+"</span>)</p>";
            	html +="        <p>代理价：<span name='agentprice'>"+item.agentPrice+"</span></p>";
            	html +="        <p>落地价：<span name='shopprice'>"+item.shopPrice+"</span></p>";
            	html +="     </td>";
            	html +="    <td><span name='classname'>";
            	if(item.shopClassName!= undefined && item.shopClassName !=null){
            		html +=item.shopClassName
            	}            		
        		html +="	</span></td>";
            	html +="</tr>";
            });
            
            $("#tbcheckProduct tbody").append(html);
        },
        error: function () {
           
        }
    });
	
}


function initCheckedProductPage(){

	var pageSize =4;
	
	var checkedpage =new MyPage(checkedIDs.length,{
    	pageFooter:5,
    	parentID:"pagediv",
    	pageSize:pageSize,
    	initCallBack:true,
    	callBack:function(indexPage){
    		
    		//显示隐藏 翻页效果
    		$("#id_fbcp tr:not(:first)").hide();
    		$("#id_fbcp tr:not(:first)").slice((indexPage-1)*pageSize,indexPage*pageSize).show();   
    	}
	});
	
	//展示 前多少
	$("#id_gdcp div:not(:last)").remove();
	
	var showtr;
	if(checkedIDs.length >5){
		$("#id_gdcp div:last").show();
		showtr = $("#id_fbcp tr:not(:first)").slice(checkedIDs.length-6,checkedIDs.length-1);
	}else{
		if(checkedIDs.length >0){
			$("#id_gdcp div:last").show();
		}else{
			$("#id_gdcp div:last").hide();
		}		
		showtr = $("#id_fbcp tr:not(:first)");
	}

	var html="";
	showtr.each(function(){
		var tr = $(this).closest("tr");
		var productImg = tr.find("img[name='productimg']").attr("src");
		var title = tr.find("span[name='title']").html();
		
		html +="<div class='fl w130 mr_20 tc'>";
		html +="  <img width='80' height='80' src='"+productImg+"'>";
		html +="  <span class='fl mzh_width_100 ft_c6 mt_10'>"+title+"</span>";
		html +="</div>";
	});
	$("#id_gdcp").prepend(html).show();
}


/**
 * 获取店铺分类
 * @param level
 * @param parentID
 */
function getShopClass(level,parentID){
	
	$.ajax({
        url: "/demand/getProductClass",
        type: "post",
        data: {level:level,parentID:parentID },
        success: function (data) {          
            if(data ==null || data ==""){
            	return ;
            }
            data = eval(data);
            
            var html ="";
            $.each(data,function(index,item){
            	html +="<option value='"+item.classId+"'>"+item.className+"</option>";
            });  
            
            if(level==1){
            	$("#selShopClassOne").append(html);
            }else{
            	$("#selShopClassTwo").append(html);	
            }            
        },
        error: function () {
           
        }
    });
	
}


/** 弹窗调用函数 **/
function ShowWin(title,win_id,_this,callBack){ 
    
	var pagei = $.layer({
		   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
		   btns: 2,
		   btn: ['确定','取消'],
		   yes: function(index){
		        //按钮【按钮一】的回调
			   if(callBack !=null &&  $.isFunction(callBack)){			   
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
		   area: ['600px','530px'],
		   page: {dom : '#'+ win_id}
	   });
}

//文本框只能输入整数
function txtWriteint(objID){
	$("#"+objID).bind("keyup",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
}


function UpLoadImgInit() {
   $("#uploadFile").uploadify({
        'buttonText': '',
        'buttonClass': 'browser',
        'dataType': 'html',
        'removeCompleted': false,
        'swf': '/statics/js/lib/uploadify/uploadify.swf',//flash文件路径
        'debug': false,
        'width': '91',//按钮宽度
        'height': '28',//按钮高度
        'auto': true,
        'multi': true,
        'queueSizeLimit': 1,//图片最大上传数量
        'timeoutuploadLimit':1,//能同时上传的文件数目
        'fileTypeExts': '*.doc;*.docx;*.pdf;',//文件类型
        'fileTypeDesc': '文件类型(*.doc;*.docx;*.pdf;)',
        'formData':{cate:1,type:1},//参数
        'uploader': 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
        'fileSizeLimit': '1024',//文件最大大小
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
                	$("#txtFilePath").val("http://img1.okimgs.com/"+data.ImgUrl);
                	$("#txtFileName").val(file.name);
                }
            }
        },
        'onDialogClose': function (swfuploadifyQueue) {
        	
        }
    });
}

function UpLoadFileInit() {
	$("#btnUpLoadImg").uploadify({
        'buttonText':'',
        'buttonClass': 'browser',
        'dataType': 'html',
        'removeCompleted': false,
        'swf': '/statics/js/lib/uploadify/uploadify.swf',//flash文件路径
        'debug': false,
        'width': 120,//按钮宽度
        'height': 120,//按钮高度
        'auto': true,
        'multi': false,//是否多文件上传
        'queueSizeLimit': 1,//图片最大上传数量
        'timeoutuploadLimit':1,//能同时上传的文件数目
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

        },
        'onProgress ': function () {

        },
        'onUploadSuccess': function (file, data, response) {
            if (response == true) {
                data = eval("("+data+")");
                if(data !=null && data.Status ==1)
                {
                	$("#mainImage").attr("src","http://img1.okimgs.com/" +data.ImgUrl);
                }
            }
        },
        'onDialogClose': function (swfuploadifyQueue) {

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
    	initialFrameWidth :851,//设置编辑器宽度
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


/*------------------------*/
 function tips(node){
     layer.tips($(node).attr('name'), node, {
        guide:1,
        time: 0,
        maxWidth:240
    });
  }
 function tips_2(node){
     layer.tips($('#' + node).html(), $('#' + node), {
        guide: 0,
        time: 0,
        maxWidth:240
    });
  }
 /*tips提示层私有属性。
msg: 提示内容。
follow: 吸附目标选择器。
guide: 指引方向（0：上，1：右，2：下，3：左）。
isGuide: 是否显示默认三角形。 这个参数可配合msg帮助你自定义三角形icon
more: 是否允许多个tips
style: ['background-color:#FFF8ED; color:#000; border:1px solid #FF9900; 此处可用来自定义tips的css样式 ', '#FF9900']]。 数组第二个值，为三角形的颜色。  */





