$(function() {
	//活动中库存小于0标识已抢光
	if(proActId!=""){
		if($("#actCount").val()<=0){
			$(".proLarge_yqg").show();
		}
	}
	if(iCount==0){
		$(".proLarge_yqg").show();
	}
	selectedImg(0);
	//参与时间的倒计时
	show_date_time(); 
	$(".LeftBotton").click(function() {
		prePimg();
	});
	$(".RightBotton").click(function() {
		nextPimg();
	});
	$(".FX").mouseover(function() {
		$(this).find(".fx_WKX").show();
	}).mouseout(function() {
		$(this).find(".fx_WKX").hide();
	});
	// 定义库存加减事件
	// 减
	$(".jiajian-item b").eq(0).click(function() {
		var buyCount = parseInt($(".jiajian-item input").val());
		if (buyCount > 1) {
			$(".jiajian-item input").val(buyCount - 1)
		}

		//如果是参与活动中的产品，则限购数量为5
		if(proActId!=""){
			if(buyCount+parseInt(userLimitBuyCount)-1<5) 
			{
				$(".jiajian-item b").eq(1).css("background-color","");
				$("#errorTip").html("");
			}
		}
		
	});
	// 加
	$(".jiajian-item b").eq(1).click(function() {
		var buyCount = parseInt($(".jiajian-item input").val());
		
		
		//如果是参与活动中的产品，则限购数量为5
		if(proActId!=""){
			if(buyCount + 1+parseInt(userLimitBuyCount)==5){
				$(".jiajian-item b").eq(1).css("background-color","#eee");
				if (buyCount < parseInt($("#stock").html())) {
					$(".jiajian-item input").val(buyCount + 1)
				}
			}	
			if(buyCount + 1+parseInt(userLimitBuyCount)>5) 
			{
				$(".jiajian-item b").eq(1).css("background-color","#eee");
				if(userLimitBuyCount>0)
					$("#errorTip").html("提示：每个微店号限购5件 ,您已购买了"+userLimitBuyCount+"件");
				else
					$("#errorTip").html("提示：每个微店号限购5件 ");
				return;
			}
		}
		
		if (buyCount < parseInt($("#stock").html())) {
			$(".jiajian-item input").val(buyCount + 1)
		}
		
	});
	// 库存失去焦点事件
	$(".jiajian-item input").blur(function() {
		$(this).css("border", "");
		var buycount = parseInt($(this).val())
		if (buycount < 1 || isNaN(buycount)) {
			$(this).val(1);
		}
		var maxcount = parseInt($("#stock").html());
		if (buycount > maxcount && maxcount > 0) {
			$(this).val(maxcount);
		}
		
		//如果是参与活动中的产品，则限购数量为5
		if(proActId!=""){
			if(buycount+parseInt(userLimitBuyCount)>5) 
			{
				$(this).val(5-parseInt(userLimitBuyCount));
				if ((5-parseInt(userLimitBuyCount)) > maxcount && maxcount > 0) {
					$(this).val(maxcount);
				}
				if(userLimitBuyCount>0)
					$("#errorTip").html("提示：每个微店号限购5件 ,您已购买了"+userLimitBuyCount+"件");
				else
					$("#errorTip").html("提示：每个微店号限购5件 ");
			}else{
				$("#errorTip").html("");
			}
		}
			
	});
	Pages();
	$(".pro_right_color a").live("click", function() {
		// if (!$(this).hasClass("selected")) {
		$(this).parents('li.pricepropertyli').find("a").removeClass("selected");
		$(this).addClass("selected");
		var salePrice = changeTwoDecimal_f($(this).attr("saleprice"));
		if (salePrice != null && salePrice != "" && salePrice != "undefined") {
			$("#defprice").html(salePrice);
		}
		var agentPrice=$(this).attr("AgentPrice");
		if (agentPrice != null && agentPrice != "" && agentPrice != "undefined") {
			agentPrice=changeTwoDecimal_f($(this).attr("agentPrice"));
			$("#brandagent").html(agentPrice);
		}
		var deputyPrice=$(this).attr("DeputyPrice");
		//alert(deputyPrice+"c");
		if (deputyPrice != null && deputyPrice != "" && deputyPrice != "undefined") {
			deputyPrice=changeTwoDecimal_f($(this).attr("DeputyPrice"));
			$("#deputyduke").html(deputyPrice);
		}
		var dukeprice=$(this).attr("Dukeprice");
		//alert(deputyPrice+"c");
		if (dukeprice != null && dukeprice != "" && dukeprice != "undefined") {
			dukeprice=changeTwoDecimal_f($(this).attr("dukeprice"));
			$("#duke").html(dukeprice);
		}
		var form = $("#hform").val();
		// 添加落地价
		if (form == "1" && $("#showLandPrice").val() == "1") {
			var storePrice = $(this).attr("storeprice");
			var djqPrice = storePrice * 0.1;
			$("#ldprice").html(changeTwoDecimal_f(storePrice));
			$("#djqPrice").html(changeTwoDecimal_f(djqPrice));
		}
		// 添加代理价
		if (form == "2" && $("#showAgentPrice").val() == "1") {
			var agentprice = $(this).attr("agentprice");
			var djqPrice = agentprice * 0.1;
			$("#dlprice").html(changeTwoDecimal_f(agentprice));
		}

		$("#styleid").val($(this).attr("stylesid"));
		
		
		if(proActId!=""){
			//如果该产品在活动中，则显示产品活动库存数量
			$("#stock").html($("#actCount").val());
		}else{
			$("#stock").html($(this).attr("stockcount"));
		}
		PriceHtml.delPriceHtml($(this));
		PriceHtml.getpriceobj(PriceHtml.priceProperty, $(this).attr("propertyName"), $(this).attr("title"));
		$(this).parents('li.pricepropertyli').next("li.pricepropertyli").find(".pro_right_color a").eq(0).click();
		if($("#stock").html()==0){
			$(".proLarge_yqg").show();
		}else{
			$(".proLarge_yqg").hide();
		}
	});
	$(".pro_right_color1 a").live("click", function() {
		$(this).parents('li.pricepropertyli').find("a").removeClass("selected");
		$(this).addClass("selected");
		pfPriceHtml.delPriceHtml($(this));
		pfPriceHtml.getpriceobj(pfPriceHtml.priceProperty, $(this).attr("propertyName"), $(this).attr("title"));
	});
	// 批发数量键入触发
	$("input[name=pfcount]").live("keyup", function() {
		var buycount = parseInt($(this).val())
		if (buycount < 0 || isNaN(buycount)) {
			buycount = 0;
		}
		$(this).val(buycount);
		$(this).trigger("change");
	});
	$("li[name=pfjian]").live("click", function() {
		var input = $(this).next().find("input");
		var buyCount = parseInt(input.val());
		if (buyCount > 0) {
			input.val(buyCount - 1);
			input.trigger("change");
		}
	});
	$("li[name=pfjia]").live("click", function() {
		var input = $(this).prev().find("input");
		var buyCount = parseInt(input.val());
		input.val(buyCount + 1);
		input.trigger("change");
	});
	$("input[name=pfcount]").live("change", function() {
		sumpfnum($(this));
	});

	$("input[name=pfprice]").each(function() {
		var obj = new Object();
		obj.price = parseFloat($(this).attr("price"));
		obj.count = parseInt($(this).attr("count"));
		pricelist.push(obj);
	});

});
var pricelist = new Array();

// 计算批发数量
function sumpfnum(obj) {
	var propertyName = obj.attr("propertyName");
	$("#tabpfnum tr[propertyName=" + propertyName + "]").remove();
	var count = 0;
	var addhtml = "";
	$("input[name=pfcount]").each(function() {
		var tempcount = parseInt($(this).val());
		if (tempcount > 0) {
			count += tempcount;
			var property = propertyName + "/" + $(this).attr("proteryValue");
			addhtml += "<li><span>" + $(this).attr("proteryValue") + "</span> <span>（<em saleprice='" + $(this).attr("saleprice") + "' property='" + property + "' stylesid='" + $(this).attr("stylesid") + "' class='value'>" + tempcount.toString() + "</em>）</span></li>";
		}
	});
	var html = "";
	html += "<tr propertyName='" + propertyName + "'>";
	html += "<td class='tb c3' width='70'>" + propertyName + "</td>";
	html += "<td width='80'><span name='pftrnum' class='f12 c6'>" + count.toString() + "</span><span class='unit'>件</span></td>";
	html += "<td>";
	html += "<div class='quayeid'>";
	html += "<ul>";
	html += addhtml;
	html += "</ul></div></td></tr>";
	$("#tabpfnum").append(html);
	// 计算总数量总价
	var allcount = 0;
	$("span[name=pftrnum]").each(function() {
		allcount += parseInt($(this).html());
	});
	$("#allcount").html(allcount);
	var allprice = 0;
	for (var i = 0; i < pricelist.length; i++) {
		if (i < (pricelist.length - 1)) {
			if (allcount < pricelist[i].count) {
				allprice = pricelist[0].price;
				break;
			} else if (allcount >= pricelist[i].count && allcount < pricelist[i + 1].count) {
				allprice = pricelist[i].price;
				break;
			} else {
				continue;
			}
		} else {
			allprice = pricelist[pricelist.length - 1].price;
		}
	}
	$("#defpfprice").val(allprice);
	$("#allprice").html(changeTwoDecimal_f(allcount * allprice));
}
var PriceHtml = {
	init : function() {
		// 获取商品属性josn串
		this.priceProperty = eval("(" + $("#priceproperty").val() + ")");
		// 生成第一行商品价格属性
		PriceHtml.getPriceHtml(this.priceProperty);
		// 定义价格属性点击事件

		// 默认选择第一个
		$(".pricepropertyli").eq(0).find(".pro_right_color a").eq(0).click();
	},
	delPriceHtml : function(del) {
		var perentli = del.parents('li.pricepropertyli');
		var li = $(".pro_right_txt>li");
		var index = perentli.index();
		li.slice(index + 1, li.length - 3).remove();
	},
	getPriceHtml : function(obj) {
		if (obj == null || obj.proteryValuesList == null)
			return;
		var priceHtml = "";
		priceHtml += "<li class='pricepropertyli'>";
		priceHtml += "<dl class='pro_right_color'>";
		priceHtml += "<dt>" + obj.propertyName + "</dt>";
		priceHtml += "<dd>";
		priceHtml += "<span>";
		$.each(obj.proteryValuesList, function(count, item) {
			if (item.priceImg == null || item.priceImg == "") {
				priceHtml += "<b><a href='javascript:void(0);' name='countName' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "' StorePrice='" + item.storePrice + "' AgentPrice='" + item.agentPrice + "'DeputyPrice='"+item.deputyPrice+"'Dukeprice='"+item.dukePrice+"' stockCount='" + item.stockCount + "'  StylesId='" + item.stylesId + "' >" + item.proteryValue + "</a></b>";
			} else {
				priceHtml += "<b><a href='javascript:void(0);' name='countName' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "' StorePrice='" + item.storePrice + "' AgentPrice='" + item.agentPrice + "'DeputyPrice='"+item.deputyPrice+"'Dukeprice='"+item.dukePrice+"' stockCount='" + item.stockCount + "' StylesId='" + item.stylesId + "' ><img src='" + item.priceImg + "' style=\"border:0px; width:24px; height:24px;\"/></a></b>";
			}
		});
		priceHtml += "</span>";
		priceHtml += "</dd>";
		priceHtml += "</dl>";
		priceHtml += "</li>";
		PriceHtml.appendHtml(priceHtml);
	},
	getpriceobj : function(priceObj, pName, pValue) {
		if (priceObj.propertyName == pName) {
			$.each(priceObj.proteryValuesList, function() {
				if (this.proteryValue == pValue) {
					if (!$.isEmptyObject(this.priceProperty) && this.priceProperty.propertyName != null) {
						PriceHtml.getPriceHtml(this.priceProperty);
						return false;
					}
				}
			});
		} else {
			$.each(priceObj.proteryValuesList, function() {
				if (this.priceProperty != null && this.priceProperty.propertyName != undefined && this.priceProperty.propertyName == pName) {
					PriceHtml.getpriceobj(this.priceProperty, pName, pValue);
				}
			})
		}
	},
	appendHtml : function(html) {
		var pricelicount = $(".pro_right_txt>li.pricepropertyli");
		if (pricelicount.length > 0) {
			pricelicount.eq(pricelicount.length - 1).after(html);
		} else {
			// $(".pro_right_txt>li").eq($(".pro_right_txt>li").length -
			// 4).after(html);
			$("#liaddress").after(html);
		}
	},
	isEmptyObject : function(obj) {
		for ( var name in obj) {
			return false;
		}
		return true;
	}

};
var pfPriceHtml = {
	init : function() {
		// 获取商品属性josn串
		this.priceProperty = eval("(" + $("#priceproperty").val() + ")");
		// 生成第一行商品价格属性
		pfPriceHtml.getPriceHtml(this.priceProperty);

		// 定义价格属性点击事件

		// 默认选择第一个
		$(".pricepropertyli").eq(0).find(".pro_right_color1 a").eq(0).click();
	},
	delPriceHtml : function(del) {
		var perentli = del.parents('li.pricepropertyli');
		var li = $(".pro_right_txt>li");
		var index = perentli.index();
		li.slice(index + 1, li.length - 3).remove();
	},
	getPriceHtml : function(obj, pValue) {
		if (obj == null || obj.proteryValuesList == null)
			return;
		if (obj.proteryValuesList.length <= 0) {
			return;
		}
		if (pValue == undefined) {
			pValue = obj.propertyName;
		}
		var priceHtml = "";
		priceHtml += "<li class='pricepropertyli'>";
		priceHtml += "<dl class='pro_right_color1'>";
		priceHtml += "<dt>" + obj.propertyName + "</dt>";
		priceHtml += "<dd>";
		if (obj.proteryValuesList[0].stylesId == "-1") {
			priceHtml += "<span>";
			$.each(obj.proteryValuesList, function(count, item) {
				if (item.priceImg == null || item.priceImg == "") {
					priceHtml += "<b><a href='javascript:void(0);' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "'DgentPrice'"+item.agentPrice+"'DeputyPrice='"+item.deputyPrice+"'Dukeprice='"+item.dukePrice+"' StylesId='" + item.stylesId + "' >" + item.proteryValue + "</a></b>";
				} else {
					priceHtml += "<b><a href='javascript:void(0);' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "'DgentPrice'"+item.agentPrice+"'DeputyPrice='"+item.deputyPrice+"'Dukeprice='"+item.dukePrice+"' StylesId='" + item.stylesId + "' ><img src='" + item.priceImg + "' style=\"border:0px; width:24px; height:24px;\"/></a></b>";
				}
			});
			priceHtml += "</span>";
		} else {
			priceHtml += "<table class='tab_types'>";
			$.each(obj.proteryValuesList, function(count, item) {
				priceHtml += "<tr>";
				priceHtml += "<td width='50'><font class='tb f14'>";
				if (item.priceImg == null || item.priceImg == "") {
					priceHtml += item.proteryValue;
				} else {
					priceHtml += "<img src='" + item.priceImg + "' style=\"border:0px; width:24px; height:24px;\"/>";
				}
				priceHtml += "</font></td>";
				priceHtml += "<td>";
				priceHtml += "<div class='counts'>";
				priceHtml += "<ul>";
				priceHtml += "<li name='pfjian' class='untiesj'>-</li>";
				var objvalue = $("#tabpfnum").find(".value[stylesid=" + item.stylesId + "]").html();
				if (objvalue == null) {
					objvalue = "0";
				}
				priceHtml += "<li class='untiesel'><input maxlength='5' name='pfcount' SalePrice='" + item.salePrice + "' propertyName='" + pValue + "' proteryValue='" + item.proteryValue + "' StylesId='" + item.stylesId + "' type='text' value='" + objvalue + "' onkeyup=\"javascript:this.value = this.value.replace(/[^0-9]+/,'');\" /></li>";
				priceHtml += "<li name='pfjia' class='untiesj'>+</li>"
				priceHtml += "</ul>";
				priceHtml += "</div>";
				priceHtml += "</td>";
				priceHtml += "</tr>";
			});
			priceHtml += "</table>";
		}
		priceHtml += "</dd>";
		priceHtml += "</dl>";
		priceHtml += "</li>";
		pfPriceHtml.appendHtml(priceHtml);
	},
	getpriceobj : function(priceObj, pName, pValue) {
		if (priceObj.propertyName == pName) {
			$.each(priceObj.proteryValuesList, function() {
				if (this.proteryValue == pValue) {
					if (!$.isEmptyObject(this.priceProperty) && this.priceProperty.propertyName != null) {
						pfPriceHtml.getPriceHtml(this.priceProperty, pValue);
						return false;
					}
				}
			});
		} else {
			$.each(priceObj.proteryValuesList, function() {
				if (this.priceProperty != null && this.priceProperty.propertyName != undefined && this.priceProperty.propertyName == pName) {
					pfPriceHtml.getpriceobj(this.priceProperty, pName, pValue);
				}
			})
		}
	},
	appendHtml : function(html) {
		var pricelicount = $(".pro_right_txt>li.pricepropertyli");
		if (pricelicount.length > 0) {
			pricelicount.eq(pricelicount.length - 1).after(html);
		} else {
			// $(".pro_right_txt>li").eq($(".pro_right_txt>li").length -
			// 4).after(html);
			$("#liaddress").after(html);
		}
	},
	isEmptyObject : function(obj) {
		for ( var name in obj) {
			return false;
		}
		return true;
	}

};

/* ========城市选择下拉（地址管理）=================== */
function InitCity() {
	// 初始化省市区列表
	var province = remote_ip_info['province'] + "省";
	var city = remote_ip_info['city'] + "市";
	var area = remote_ip_info['district'] + "区";
	var dis = new district();
	dis.init('#selProvince', '#selCity', '#selDistrict');
	dis.bind(province, city, area);
}

/* =============加载邮费============== */
function GetPostAge() {
	
	//如果是参与活动中的产品，则限购数量为5
	var buyCount = parseInt($(".jiajian-item input").val());
	if(proActId!=""){
		if(buyCount+parseInt(userLimitBuyCount)>5) 
		{
			if(userLimitBuyCount>0)
				$("#errorTip").html("提示：每个微店号限购5件 ,您已购买了"+userLimitBuyCount+"件");
			else
				$("#errorTip").html("提示：每个微店号限购5件 ");
		}else{
			$("#errorTip").html("");
		}
	}
	
	var data = {
		proID : proID,
		province : $("#selProvince").val(),
		city : $("#selCity").val(),
		district : $("#selDistrict").val(),
		count : $(".jiajian-item input:text").val()
	};
	if (data.province == "0" && data.city == "0" && data.district == "0") {
		return;
	}
	$.ajax({
		type : "post",
		url : "/product/postage",
		data : data,
		success : function(result) {
			if (result.msg == "0") {
				$(".pro_right_color div.baoyou").text("商家包邮");
			} else if (result.msg == "-1") {
				$(".pro_right_color div.baoyou").text("获取邮费失败");
			} else {
				$(".pro_right_color div.baoyou").text(result.msg);
			}
		}
	});
}

// 评论
function Pages() {
	var num_entries = parseInt($("#evaluateCount").html());
	if (num_entries == 0) {
		return;
	}
	var initPagination = function() { // 创建分页
		$("#Pagination").pagination(num_entries, {
			num_edge_entries : 2, // 边缘页数
			num_display_entries : 6, // 主体页数
			callback : pageselectCallback,
			items_per_page : 6, // 每页显示1项
			prev_text : "上一页",
			next_text : "下一页"
		});
	}();

	function pageselectCallback(page_index, jq) {
		$.ajax({
			type : "post",
			url : "/product/comment",
			data : {
				proID : proID,
				index : page_index,
				size : 6
			},
			success : function(data) {
				if (data.msg == "") {
					$("#Pagination").html("");
				} else {
					$("#pagess").html(data.msg);
				}
			}
		});
		return false;
	}
}

// 选择图片
function selectedImg(obj) {
	var imgsrc = $("#img_" + obj).attr("src");
	imgsrc = imgsrc.replace('_8', '_75');
	$("#productBigImg").attr("src", imgsrc);

	$("#productImgList li.current").removeClass("current");
	document.getElementById("img_" + obj).parentNode.className = "current";
}

function prePimg() {
	var currentImgID = $("#productImgList li.current img").attr("id");
	if (currentImgID.indexOf('_')) {
		var temp = currentImgID.split('_')[1];
		var intTemp = parseInt(temp);
		if (temp == "0") {
			return;
			temp = $("#productImgList li").length - 1;
		} else {
			if (intTemp % 5 == 0) {
				for (var i = 0; i < 5; i++) {
					if ((intTemp + i) < $("#productImgList li").length) {
						document.getElementById("img_" + (intTemp + i)).parentNode.style.display = "none";
					}
					document.getElementById("img_" + (intTemp - (i + 1))).parentNode.style.display = "inline";
				}
			}
			temp = parseInt(temp) - 1;
		}
		selectedImg(temp);
	}
}

function nextPimg() {
	var currentImgID = $("#productImgList li.current img").attr("id");
	if (currentImgID.indexOf('_')) {
		var temp = currentImgID.split('_')[1];
		var intTemp = parseInt(temp);
		if (temp == $("#productImgList li").length - 1) {
			return;
			temp = 0;
		} else {
			if (intTemp % 5 == 4) {
				for (var i = 0; i < 5; i++) {
					if ((intTemp + i + 1) < $("#productImgList li").length) {
						document.getElementById("img_" + (intTemp + i + 1)).parentNode.style.display = "inline";
					}
					document.getElementById("img_" + (intTemp - i)).parentNode.style.display = "none";
				}
			}
			temp = parseInt(temp) + 1;
		}
		selectedImg(temp);
	}
}

// 压缩图片
function ZoomXQ(obj, width, height) {
	var img = new Image();
	img.src = obj.src;

	var scale; // 压缩比率，如果原图比现实的img小就不压缩。
	var wscale = width / img.width;
	var hscale = height / img.height;

	if (Math.max(wscale, hscale) < 1.0) {
		obj.width = img.width * Math.max(wscale, hscale);
		obj.height = img.height * Math.max(wscale, hscale);

	} else if (Math.min(wscale, hscale) >= 1.0) {
		obj.width = img.width;
		obj.height = img.height;

	} else if (wscale > 1.0) {
		obj.width = img.width * hscale;
		obj.height = img.height * hscale;

	} else if (hscale > 1.0) {
		obj.width = img.width * wscale;
		obj.height = img.height * wscale;

	}

	if (!(!+[ 1, ])) {
		obj.style.marginTop = (height - obj.height) / 2 + "px";
		obj.style.marginLeft = (width - obj.width) / 2 + "px";
	}
}

// 分享
function shareTo(type, title, proID, weiNo, from) {
	var url = window.basePath + "/product?pid=" + proID + "&f=" + from + "&w=" + weiNo;
	title += "【微店网】";
	if (type == "kj") {
		ShareToQzone(title, url, "");
	} else if (type == "tx") {
		ShareToTencent(title, url, "");
	} else if (type == "xl") {
		ShareToSina(title, url, "");
	}
	// 修改分享数量
	$.ajax({
		url : "/product/share",
		type : "post",
		data : {
			proID : proID
		},
		success : function(data) {
			if (data.msg == "1") {
				var count = parseInt($("#shareCount").html());
				count += 1;
				$("#shareCount").html(count);
			}
		}
	});
}

// 悬浮导航栏
$(document).ready(function() {

	$(window).bind("scroll", function() {
		var toop = $("#wrapTop").offset().top; // document.getElementById("wrapTop").offsetTop;
		var scrollTop = $(document).scrollTop();
		if (scrollTop >= toop) {
			$("#firstTitle").css("position", "fixed");
			$("#firstTitle").css("top", "0px");
			$("#firstTitle ul li").removeClass("hover");

			toop -= 50;
			var top1 = document.getElementById("cs").offsetTop + toop;
			//var top2 = document.getElementById("tj").offsetTop + toop;
			var top3 = document.getElementById("spxq").offsetTop + toop;
			//var top4 = document.getElementById("pjsd").offsetTop + toop;
			var top5 = document.getElementById("jybz").offsetTop + toop;

			if (scrollTop < top1) {
				$("#firstTitle ul li").eq(0).addClass("hover");
			} else if (scrollTop >= top1) {
				$("#firstTitle ul li").eq(0).addClass("hover");
			} else if (scrollTop < top3) {
				$("#firstTitle ul li").eq(1).addClass("hover");
			} else if (scrollTop >= top3) {
				$("#firstTitle ul li").eq(2).addClass("hover");
			} else if (scrollTop < top5) {
				$("#firstTitle ul li").eq(3).addClass("hover");
			} else if (scrollTop >= top5) {
				$("#firstTitle ul li").eq(4).addClass("hover");
			}

		} else {

			$("#firstTitle").css("position", "relative");
			$("#firstTitle").css("top", "auto");
		}
		// 立即购买
		var inputtop = 550;
		var top = $(window).scrollTop();
		if (inputtop > top) {
			$(".Pro_jrgwc").hide();
			$(".Pro_ljgm").hide();
		} else {
			$(".Pro_jrgwc").show();
			$(".Pro_ljgm").show();
		}
	});
});

// 转换成Decimal类型 2位小数
function changeTwoDecimal_f(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}

// 关注店铺
function attentionSup(obj) {
	var type = $(obj).attr("data");
	$.ajax({
		type : "post",
		url : "/product/attention",
		data : {
			type : type,
			supID : supWeiID
		},
		success : function(data) {
			if (data.msg == "1") {
				if (type == "1") {
					$(obj).attr("data", "0");
					$(obj).html("关注店铺");
					alert("取消成功", true);
					location.reload();
				} else {
					$(obj).attr("data", "1");
					$(obj).html("取消关注");
					alert("关注成功", true);
					location.reload();
				}
			} else if (data.msg == "-1") {
				alert("登陆之后才能关注哦", false);
			} else if (data.msg == "0") {
				alert("操作失败", false);
			}
		}
	});
}

// 加入购物车
function joinshopcart(buyType, source) {
	var datalist = new Array();// 购物车数据
	var maxcount = parseInt($("#stock").html());
	if(maxcount<=0){
		alert("库存不足，不能加入购物车！");
		return;
	}
	//如果是零售并且是参与活动的产品
	if(proActId!=""){
		if(parseInt(userLimitBuyCount)>=5){
			alert("每个微店号限购5件，该微店号已抢购了5件，不能再抢了！");
			return;
		} 
	}
	
	// 零售区
	var type = $(".nesel").attr("data");
	if (type == null) {
		type = "1";
	}
	if (type == "3") {
		buyType = 3;
		var count = parseInt($("#allcount").html());
		var mincount = parseInt($("#minCount").val());
		if (count < mincount) {
			alert("起批数量为" + mincount + "件");
			return;
		}
		// 批发
		var price = $("#defpfprice").val();
		$("#tabpfnum").find(".value").each(function() {
			var data = new Object;
			data.proNum = proID;
			data.sellerWeiId = 0;
			data.source = source;
			data.buyType = buyType;
			data.count = $(this).html();
			data.styleId = $(this).attr("stylesid");
			data.property = $(this).attr("property");
			data.supplierID = supWeiID;
			data.makerWeiID = sharePageProducer;
			data.sharerWeiID = shareOne;
			data.shareID = sharePageId;
			datalist.push(data);
		});

	} else {
		var data = new Object();
		var count = parseInt($(".jiajian-item input").val());
		if (count <= 0) {
			alert("购买数量有误");
			return;
		}
		var price = 0;
		if (type == "1") {
			price = $("#defprice").text();
		} else {
			price = $("#preprice").html();
		}
		var styleid = $("#styleid").val();
		if (styleid == "") {
			alert("请选择款式");
			return;
		}
		var property = "";
		$(".selected").each(function() {
			property += $(this).attr("title") + "/";
		});
		if (property != "") {
			property = property.substring(0, property.length - 1);
		}
		data.proNum = proID;
		data.sellerWeiId = 0;
		data.source = source;
		data.buyType = buyType;
		data.count = count;
		data.styleId = styleid;
		data.property = property;
		data.supplierID = supWeiID;
		data.makerWeiID = sharePageProducer;
		data.sharerWeiID = shareOne;
		data.shareID = sharePageId;
		datalist.push(data);
	}
	// 加入购物车
	$.ajax({
		url : "/product/addcart",
		type : "post",
		data : {
			data : $.toJSON(datalist)
		},
		success : function(data) {
			if (data.msg == "-1") {
				alert("登陆后才能加入购物车哦！");
			} else if (data.msg == "0") {
				alert("加入购物车失败");
			} else {
				$("#cartCount").html(data.msg);
				if (buyType == 4) {
					alert("加入进货单成功", true);
				} else if (buyType == 5) {
					alert("加入铺货单成功", true);
				} else {
					alert(userLimitBuyCount);
					alert("加入购物车成功", true);
				}
			}
		}
	});
}
// 立即购买
function immediately(buyType, source) {
	var flag = false;
	// 判断有没有登陆
	$.ajax({
		url : "/commons/isLogin",
		type : "get",
		async : false,
		success : function(data) {
			if (data == "1") {
				flag = true;
			}
		}
	});
	if (!flag) {
		alert("你还没有登录，登录后才能购买哦！");
		return;
	}
	var maxcount = parseInt($("#stock").html());
	if(maxcount<=0){
		alert("库存不足，不能购买！");
		return;
	}
	//如果是零售并且是参与活动的产品
	if(buyType==1&&proActId!=""){
		if(parseInt(userLimitBuyCount)>=5){
			alert("每个微店号限购5件，该微店号已抢购了5件，不能再抢了！");
			return;
		} 
	}

	var type = $(".nesel").attr("data");
	if (type == null) {
		type = "1";
	}
	var scids = new Array();
	if (type == "3") {
		buyType = 3;
		var count = parseInt($("#allcount").html());
		var mincount = parseInt($("#minCount").val());
		if (count < mincount) {
			alert("起批数量为" + mincount + "件");
			return;
		}
		$("#tabpfnum").find(".value").each(function() {
			var cartProd = new Object;
			cartProd.proNum = 0;// 商品id
			cartProd.buyShopId = 0;// 来源微店id
			cartProd.styleId = $(this).attr("stylesid");// 商品款式id
			cartProd.count = $(this).html();// 商品数量
			cartProd.price = $(this).attr("saleprice");// 商品价格
			cartProd.sharePageProducer = sharePageProducer;
			cartProd.shareOne = shareOne;
			cartProd.sharePageId = sharePageId;
			scids.push(cartProd);
		});
	} else {
		var count = parseInt($(".jiajian-item input").val());
		var price = 0;
		if (source == 2) {
			price = $("#ldprice").html();
		} else if (source == 1) {
			price = $("#dlprice").html();
		} else {
			price = $("#defprice").html();
		}
		if (count <= 0) {
			alert("购买数量有误");
			return;
		}
		var styleid = $("#styleid").val();
		if (styleid == "") {
			alert("请选择款式");
			return;
		}
		var cartProd = new Object;
		cartProd.proNum = proID;// 商品id
		cartProd.buyShopId = 0;// 来源微店id
		cartProd.styleId = styleid;// 商品款式id
		cartProd.count = count;// 商品数量
		cartProd.price = price;// 商品数量
		cartProd.sharePageProducer = sharePageProducer;
		cartProd.shareOne = shareOne;
		cartProd.sharePageId = sharePageId;
		scids.push(cartProd);
	}
	var list = new Array();
	var listJson = new Object;
	listJson.supplierWeiId = supWeiID;
	listJson.buyType = buyType;
	listJson.source = source;
	listJson.demandId = demandid;
	listJson.productList = scids;
	list.push(listJson);
	$("#listJson").val($.toJSON(list));
	document.forms[0].submit();
}
function showdivpf() {
	if ($("#divselpf").is(":hidden")) {
		$("#divselpf").show();
	} else {
		$("#divselpf").hide();
	}
}

function show_date_time(){ 
	var publishtype=$("#publishtype").val();
	//alert(actEndTime);
	if(actEndTime==null){
		$("#actPriceDiv").hide();
		$("#xianPriceDiv").show();
		$("#actInfoDiv").hide();
	}/*else{
		$("#actPriceDiv").hide();
		$("#xianPriceDiv").show();
	}*/
	if(actEndTime=="") return;
	var timeText="";
	var timeold;
	if(activeType==1){
		var beforetime=((new Date(Date.parse(beginTime.replace(/-/g, "/")))).getTime()-(new Date()).getTime());
		var aftertime=((new Date(Date.parse(actEndTime.replace(/-/g, "/")))).getTime()-(new Date()).getTime());
		//计算距活动开始时间
		if(beforetime>=0){
			timeText="距开始：";
			timeold=beforetime;
			$("#actPriceDiv").hide();
			$("#xianPriceDiv").show();
		}else if(beforetime<0&&aftertime>=0){
			timeText="距结束：";
			timeold=aftertime;
			$("#actPriceDiv").show();
			$("#actInfoDiv").show();
			$("#xianPriceDiv").hide();
		}else{
			timeText="距结束：";
			timeold=aftertime;
			$("#actPriceDiv").show();
			$("#actInfoDiv").show();
			$("#xianPriceDiv").hide();
		}
		if(timeold<0){
			//活动结束
			$("#actInfoDiv").hide();
			$("#actPriceDiv").hide();
			$("#xianPriceDiv").show();
			$("#actindex").hide();
			proActId="";//活动ID置为空
			//location.reload(); 
			return;
		}
	}
	if(activeType==0){	
		timeText="距结束：";
		$("#actInfoDiv").show();
		timeold=(new Date(actEndTime.replace(/-/g, "/"))).getTime()-(new Date()).getTime();
		if(timeold<0){
			//活动结束
			$("#actInfoDiv").hide();
			$("#actPriceDiv").hide();
			$("#xianPriceDiv").show();
			proActId="";//活动ID置为空
			//location.reload(); 
			return;
		}else{
		//活动进行中
			$("#actInfoDiv").show();
			$("#actPriceDiv").show();
			$("#xianPriceDiv").hide();
		}
		
	}
	if(publishtype>0){
		$("#actPriceDiv").hide();
		$("#xianPriceDiv").show();
	}
	//alert(timeold);
	/*else{
		//活动进行中
		$("#actInfoDiv").show();
		$("#actPriceDiv").show();
		$("#xianPriceDiv").hide();
	}*/
	window.setTimeout("show_date_time()", 1000); 
	
	var dd = parseInt(timeold / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
	var hh = parseInt(timeold / 1000 / 60 / 60 % 24, 10) + dd * 24;// 计算剩余的小时数
	var mm = parseInt(timeold / 1000 / 60 % 60, 10);// 计算剩余的分钟数
	var ss = parseInt(timeold / 1000 % 60, 10);// 计算剩余的秒数
	if (ss <= 0) {
		ss = 00;
	}
	if (mm <= 0) {
		mm = 00;
	}
	if (hh <= 0) {
		hh = 00;
	}
	
	hh = checkTime(hh);
	mm = checkTime(mm);
	ss = checkTime(ss);
	
	if (isNaN(ss) || ss == null || ss == "") {
		ss = "00";
	}
	if (isNaN(mm) || mm == null || mm == "") {
		mm = "00";
	}
	if (isNaN(hh) || hh == null || hh == "") {
		hh = "00";
	}

	var time="";
	if(dd>=1){
		var time=dd+"天";
	}else{
		var time=""+hh+":"+mm+":"+ss+"";
	}
	$("#timespan").html(time);
	$("#timetext").html(timeText);
} 

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
} 