$(function() {
	selectedImg(0);
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
	});
	// 加
	$(".jiajian-item b").eq(1).click(function() {
		var buyCount = parseInt($(".jiajian-item input").val());
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
	});
	Pages();
	PriceHtml.init();
	InitCity();
	GetPostAge();
	$(".pro_right_color select").change(function() {
		GetPostAge();
	});
});

var PriceHtml = {
	init : function() {
		// 获取商品属性josn串
		this.priceProperty = eval("(" + $("#priceproperty").val() + ")");
		// 生成第一行商品价格属性
		PriceHtml.getPriceHtml(this.priceProperty);
		// 定义价格属性点击事件
		$(".pro_right_color a").live("click", function() {
			if (!$(this).hasClass("selected")) {
				$(this).parents('li.pricepropertyli').find("a").removeClass("selected");
				$(this).addClass("selected");
				var salePrice = changeTwoDecimal_f($(this).attr("saleprice"));
				if (salePrice != null && salePrice != "" && salePrice != "undefined") {
					$("#defprice").html(salePrice);
				}

				PriceHtml.delPriceHtml($(this));
				PriceHtml.getpriceobj(PriceHtml.priceProperty, $(this).attr("propertyName"), $(this).attr("title"));

				$(this).parents('li.pricepropertyli').next("li.pricepropertyli").find(".pro_right_color a").eq(0).click();
			}
		});
		// 默认选择第一个
		$(".pricepropertyli").eq(0).find(".pro_right_color a").eq(0).click();
	},
	delPriceHtml : function(del) {
		var perentli = del.parents('li.pricepropertyli');
		var li = $(".pro_right_txt li");
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
				priceHtml += "<b><a href='javascript:void(0);' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "' StylesId='" + item.stylesId + "' >" + item.proteryValue + "</a></b>";
			} else {
				priceHtml += "<b><a href='javascript:void(0);' propertyName='" + obj.propertyName + "' title='" + item.proteryValue + "'  SalePrice='" + item.salePrice + "' StylesId='" + item.stylesId + "' ><img src='" + imageDamin + item.priceImg + "' style=\"border:0px; width:24px; height:24px;\"/></a></b>";
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
		var pricelicount = $("..pro_right_txt li.pricepropertyli");
		if (pricelicount.length > 0) {
			pricelicount.eq(pricelicount.length - 1).after(html);
		} else {
			$(".pro_right_txt li").eq($(".pro_right_txt li").length - 4).after(html);
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
function shareTo(type, title, proID) {
	var url = window.basePath + "/product/detail/" + proID;
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
			var top2 = document.getElementById("tj").offsetTop + toop;
			var top3 = document.getElementById("spxq").offsetTop + toop;
			var top4 = document.getElementById("pjsd").offsetTop + toop;
			var top5 = document.getElementById("jybz").offsetTop + toop;

			if (scrollTop < top1) {
				$("#firstTitle ul li").eq(0).addClass("hover");
			} else if (scrollTop >= top1 && scrollTop < top2) {
				$("#firstTitle ul li").eq(0).addClass("hover");
			} else if (scrollTop >= top2 && scrollTop < top3) {
				$("#firstTitle ul li").eq(1).addClass("hover");
			} else if (scrollTop >= top3 && scrollTop < top4) {
				$("#firstTitle ul li").eq(2).addClass("hover");
			} else if (scrollTop >= top4 && scrollTop < top5) {
				$("#firstTitle ul li").eq(3).addClass("hover");
			} else if (scrollTop >= top5) {
				$("#firstTitle ul li").eq(4).addClass("hover");
			}

		} else {

			$("#firstTitle").css("position", "relative");
			$("#firstTitle").css("top", "auto");
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
					alert("取消关注成功");
				} else {
					$(obj).attr("data", "1");
					$(obj).html("取消关注");
					alert("关注成功");
				}
			} else if (data.msg == "-1") {
				alert("登陆超时，请重新登陆")
			} else if (data.msg == "0") {
				alert("操作失败");
			}
		}
	});
}
