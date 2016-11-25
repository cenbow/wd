$(function(){	
	//上架模块显示隐藏
	$('#TanLeft').click(function(){
		if($(this).attr('icon')==0){
			ShowShelvesDIV();
		}else{
			HiddenShelvesDiv();
		} 
	});
	//关闭上架弹出层
	$('#ImgClose').click(function(){
		HiddenShelvesDiv();
	});
	
	
	
	//批发价 价格文本框限制
	$("#shelvesPriceDiv li[name='newbatchprice'] input").live("keyup",function(){   
		$(this).val($(this).val().replace(/[^0-9.]/g,''));    
	}).live("paste",".shuru_priceon input",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/[^0-9.]/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	
	
	//上架商品分类选择
	$('.zhuang_delssic li').live('click',function(){
		if ($(this).attr('class') == 'red_borup') {
			return;
		}
		$('.zhuang_delssic li').each(function(){
			$(this).attr('class','');
		})
		$(this).attr('class','red_borup');
	});
});

//普通微店单件商品上架
function UserShelves(_this){
	var objli = _this.closest("li");
	var html = CreateShelvesHtml(objli);
	$("#shelvesDIV").html(html);
	$("#divReason").show();
	$("#btnSubmitShelves").removeClass("btn_bluese_hui").addClass("btn_bluese");
	ShelvesDivTop(1);
	ShowShelvesDIV();
	
	var productID = objli.attr("value");
	var param ={type:1,productID:productID};
	submitShelves(param,_this);

}

//供应商单件商品上架
function SupplyShelves(_this){
	var objli = _this.closest("li");
	//上架商品信息
	var html = CreateShelvesHtml(objli);
	$("#shelvesDIV").html(html);
	//批发价信息展示
	$("#shelvesPriceDiv").find(".news_input li[name='newbatchprice']").remove();
	if(objli.find(".ladder-price-item",$("#shangpin_iframepage")[0].contentWindow).length>0){
		//批发价区间
		var priceRegion = objli.find("[name='priceRegion']").attr("value");
		$("#shelvesPriceDiv").find(".two_titles span").html(priceRegion);
		//每一等级的批发价格
		var batchhtml="";		
		objli.find(".ladder-price-item",$("#shangpin_iframepage")[0].contentWindow).each(function(){
			var price = $(this).find("[name='batchPrice']").attr("value");
			var num =  $(this).find("[name='tabchNum']").attr("value");
			
			batchhtml +="<li name='newbatchprice'><span>"+num+"件以上</span>";
			batchhtml +=" <input type='text' class='btn_h28' num='"+num+"' minprice='"+price+"'  value='"+price+"'/> 元</li>";	            
		});
		$("#shelvesPriceDiv").find(".news_input ul").append(batchhtml);		
		$("#shelvesPriceDiv").show();
	}else{
		$("#shelvesPriceDiv").hide();
	}	
	$("#btnSubmitShelves").removeClass("btn_bluese_hui").addClass("btn_bluese");
	ShelvesDivTop(3);
	ShowShelvesDIV();
	
	//提交事件
	var productID = objli.attr("value");
	var param ={type:2,productID:productID};
	submitShelves(param,_this);
}

function submitShelves(param,ts){
	$("#btnSubmitShelves").unbind().bind("click",function(){
		if($(".zhuang_delssic .red_borup").length<1){
			alert("请选择要上架的分类");
			return;
		}

		var submitParam;
		//添加选中的分类属性
		var shopClassID =$(".zhuang_delssic .red_borup").attr("value");
		/*var reason = $("#txtReason").val();
		if(reason =="" || reason==null){
			alert("请填写评论");
			return;
		}*/
		/*if(reason.length >= 140){
			alert("评论字数不能超过140个字符");
			return;
		}*/
		//普通微店单件商品上架
		if(param.type ==1){
			submitParam ={shopClassID:shopClassID};
		}else{
			//供应商上架
			var batchPrices=[];
			if($("#shelvesPriceDiv li[name='newbatchprice'] input").length >0){				
				var isSuccess = true;
				$("#shelvesPriceDiv li[name='newbatchprice'] input").each(function(){
					var price = $(this).val();
					var minprice = $(this).attr("minprice");
					var num = $(this).attr("num");
					if(price =="" || parseFloat(price) <0.01){
						alert("请正确填写价格");
						isSuccess = false;
						$(this).val("");
						$(this)[0].focus();
						return false;
					}						
					if(parseFloat(price)<=parseFloat(minprice)){
						alert("上架区间价格必须"+price+"大于初始批发价"+minprice);
						isSuccess = false;
						$(this).val("");
						$(this)[0].focus();
						return false;
					}	
					
					batchPrices.push({Count:num,Price:price});
				});
				if(!isSuccess){
					return;
				}													
			}
			
			submitParam ={shopClassID:shopClassID,strProductIDs:obj2Str(batchPrices)};	
		}		
		//继承参数属性
		submitParam = extend(param,submitParam);
		$.ajax({
		    url: "/wholesale/submitShelves",
		    type: "post",
		    data: submitParam,
		    dataType : 'json',
		    success: function (result) {
				if(result.state =="Success"){
					alert(result.message,true);
					//清空上架列表的产品
					$("#shelvesDIV").html('');
					/*$("#txtReason").val('');*/
					$("#shelvesPriceDiv").find(".news_input ul").html('<li class="news_title">新批发价</li>');
					//最好添加一个不允许提交的样式
					//解除所有事件
					$("#btnSubmitShelves").unbind();
					$("#btnSubmitShelves").removeClass("btn_bluese").addClass("btn_bluese_hui");
					ts.removeClass("btn_small").addClass("btn_small_hui");
					HiddenShelvesDiv();				
				}else{
					alert(result.message);
				}
		    },
		    error: function () {
		    	alert("系统异常,请稍后重试");
		    }
		});			
	});
}
//合并对象 返回子对象
function extend(superObj,subObj){ 
	//获得父对象的原型对象 
	subObj.getSuper = superObj.prototype; 
	//将父对象的属性给子对象 
	for(var i in superObj){ 
		subObj[i] = superObj[i]; 
	} 
	return subObj;
} 

function CreateShelvesHtml(objli){
	var productID = objli.attr("value");
	var commision = objli.attr("commision");
	var title = objli.find("[name='productTitle']",$("#shangpin_iframepage")[0].contentWindow).html();
	var img = objli.find("[name='productImg']",$("#shangpin_iframepage")[0].contentWindow).attr("src");
	var price = objli.find("[name='productPrice']",$("#shangpin_iframepage")[0].contentWindow).html();
	
	var html ='<div class="one_left" value="'+productID+'">';
	html +=' <div class="imgfloates"><img width="60" src="'+img+'"></div>';
	html +=' <div class="fotrightes">';
	html +='   <div class="title_shop pr10">'+title+'</div>';
	html +='   <div class="title_price">零售价：<span>￥'+price+' </span>佣金：<i>￥'+commision+'</i></div>';
	html +='</div>';
	html +='<div class="blank"></div>';
	html +='</div>';
	return html;
}

//去登录
function goLogin() {
    var tempurl = window.location;
    location.href = "http://port.okwei.com/login.aspx?back=" + tempurl;
}

function ShelvesDivTop(type){
	var top =0;
	if(type ==1){
		$("#txtReason").css("height","80px");
		top = ($(window).height()-$('.pos_rabls').height())/2;
	}
	var nisTop = ($('.pos_rabls').height()-86)/2;
	$('.pos_rabls').css('top',top+'px');
	$('.clicimgs').css('top',nisTop+'px')
};

function HiddenShelvesDiv(){
	$("#TanLeft").attr('icon','0');
	$("#TanLeft").find('img').attr('src','/statics/images/ing_sjdown.png')
	$('.pos_rabls').animate({right:'-350px'});
}

function ShowShelvesDIV(){
	$("#TanLeft").attr('icon','1');
	$("#TanLeft").find('img').attr('src','/statics/images/ing_sjup.png')
	$('.pos_rabls').animate({right:'0px'});
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

function alert(msg,bool){
	if(bool){
		layer.msg(msg, 2, 1);//绿色的钩钩
	}else{
		layer.msg(msg, 2, 8);//不高兴的脸
	}	
}
$(function(){	
	//初始化店铺分类
	getShopClassList();
	//初始化上架分类
	$("#refreshShopList").click(function(){
		getShopClassList();
	});
});
//获取用户店铺分类
function getShopClassList(){
	var isLogin =$("#loginID").val();
	if(isLogin =="" || isLogin <1){
		return;
	}
	
	$.ajax({
	    url: "/wholesale/getShopClassList",
	    type: "post",
	    data: { },
	    dataType : 'json',
	    success: function (data) {
			var data = eval(data);
			if(data !=null && data.length>0){
				var html ="";
				$.each(data,function(index,item){
					html +="<li value='"+item.sid+"'>"+item.sname+"</li>";					
				});
				
				$(".zhuang_delssic ul").html(html);
			}
	    }
	});	
	
}
