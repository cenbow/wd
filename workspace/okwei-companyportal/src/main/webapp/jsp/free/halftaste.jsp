<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String cartdomain = ResourceBundle.getBundle("domain").getString("cartdomain");
String portdomain = ResourceBundle.getBundle("domain").getString("portdomain");
String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/statics/css/glbdy-free.css" />
<link rel="stylesheet" href="/statics/css/mzh_dd-free.css" />
<script src="/statics/js/jquery-1.7.1.min.js"></script>
<script src="/statics/js/layer/layer.min.js"></script>
<title>半价领取</title>
<script type="text/javascript" src="/statics/js/district.js"></script>
	<script type="text/javascript">
	var porturl='<%=portdomain%>';
	var payurl='<%=paydomain%>'
		$(function() {
			function zishiying(){
			    var aHeight = $(".mzh_sqdl").height();
			    var aWidth= $(document).width();
			    aWidth= (aWidth-1200)/2-92;
			    var aOffsetTop = $(window).height();
			    aOffsetTop= (aOffsetTop/2)-(aHeight/2);
			    $(".mzh_sqdl").css({right:aWidth+"px",top:aOffsetTop+"px"});
			  }
			  zishiying();
			  $(window).resize(function(){
			    zishiying();
			  })

			    $("[name=divaddress]").mouseover(function() {
			        $(this).find("[name=setasdefault]").show();
			        $(this).find("[name=mzh_mrdz]").show();

			    }).mouseout(function() {
			        $(this).find("[name=setasdefault]").hide();
			        $(this).find("[name=mzh_mrdz]").hide();
			    })

			    $(".mzh_tc_text").keypress(function(event) {
			        var keyCode = event.which;
			        if (keyCode == 46 || (keyCode >= 48 && keyCode <= 57))
			            return true;
			        else
			            return false;
			    }).focus(function() {
			        this.style.imeMode='disabled';
			    });

			    $("#mzh_tc_j").click(function(){
			        var val = $(".mzh_tc_text").val();
			        if(val >= 1){
			            val = val-1;
			            $(".mzh_tc_text").val(val);
			            var oneprice=$("#oneprice").html();
			            $("#payprice").html(val*oneprice);
			        }
			    })

			    $("#mzh_tc_add").click(function(){
			        var val = $(".mzh_tc_text").val();
			        val = val*1+1;
			        $(".mzh_tc_text").val(val);
			        var oneprice=$("#oneprice").html();
			        $("#payprice").html(val*oneprice);
			    })
			    
			    $(".mzh_tc_text").bind("blur", function() {
					var value = $(this).val();
					 var oneprice=$("#oneprice").html();
				        $("#payprice").html(value*oneprice);
				});
			InitCity();
				
			
			//无收货地址判断  
			if ("${fn:length(list)}" == 0) {
				openPopups('添加地址',null);
			}
			
			
			//更改收货地址 
		  $(".jbzl_shdz_yes_div_0").click(function() {
				if ($(this).parent().attr("caddrid") !=  $("#addressId_txt").val()) {
					if (!confirm("确定要更改收货地址吗？")) {
						return;
					}
				} else {
					return;
				}
				$(".jbzl_shdz div").each(function(){
					if ($(this).attr("class") == "jbzl_shdz_yes") {
						$(this).attr("class","jbzl_shdz_no");
					}
				});
				var $selAddrDiv = $(this).parent("div");
				$selAddrDiv.attr("class","jbzl_shdz_yes");

				$("#addressId_txt").val($(this).parent().attr("caddrid")); 
				//document.forms[1].submit();
			})  
			
			//提交订单
			$(".mzh_queding").click(function() {
				var addrId = $("#addressId_txt").val();//$(".jbzl_shdz .jbzl_shdzsiz_yes").attr("caddrId");
				if(addrId == "" || typeof(addrId) == "undefined"){
					alert("请选择收货地址!");
					return;
				}
				var weiid=$("#weiid").val();
				if(weiid<=0)//跳转登陆页面
				{
					url=porturl+"?back="+window.location.href;
					window.location.href=url;
				}
				var num= $(".mzh_tc_text").val();
				if(num<=0)
				{
					alert("请选择半价试用的数量!");
					return;
				}
				$.ajax({
					url : "/free/halfbuy",
					type : "post",
					async : false,
					data : {
						weiid:weiid,
						addrid:addrId,
						num:num
					},
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("服务器出现异常");
					},
					success : function(data) {
						data=eval(data);
						if (data.state == "Success") {
							//跳转到支付页
							//https://pay.okwei.com/pay/cashier?orderNo=1610171030390273
							window.location.href=payurl+'/pay/cashier?orderNo='+data.message;
						} else {
							alert(data.message);
						}

					}
				});
			
			});
			//收货地址鼠标特效
			$("[name=divaddress]").mouseover(function() {
				$(this).find("[name=setasdefault]").show();
				$(this).find("[name=mzh_mrdz]").show();

			}).mouseout(function() {
				$(this).find("[name=setasdefault]").hide();
				$(this).find("[name=mzh_mrdz]").hide();
			});
			
			$("#close").click(function() {
				$(this).parent().hide();
			});			
		
		});
		
		/*========城市选择下拉（地址管理）===================*/
		function InitCity() {
			//初始化省市区列表
			var province = $("#selProvince option:selected").text();
			var city = $("#selCity option:selected").text();
			var area = $("#selDistrict option:selected").text();
			var dis = new district();
			dis.init('#selProvince', '#selCity', '#selDistrict');
			dis.bind(province, city, area);
		}
		//编辑、添加地址
		function openPopups(title, obj) {
			if (obj != null) { 
				//修改
				var $divAddr = $(obj).parents("div[name=divaddress]");
				$("#txtcaddrId").val($divAddr.attr("caddrId"));
				$("#txtPhone").val($divAddr.find("[name=mobilePhone]").text());
				$("#txtLinkman").val($divAddr.find("[name=receiverName]").text());
				$("#selProvince option[value=" + $divAddr.find("[name=province]").val() + "]").attr("selected", true);
				$("#selProvince").change();
				$("#selCity option[value=" + $divAddr.find("[name=city]").val() + "]").attr("selected", true);
				$("#selCity").change();
				$("#selDistrict option[value=" + $divAddr.find("[name=district]").val() + "]").attr("selected", true);
				$("#txtDetailAdd").val($divAddr.find("[name=detailAddr]").text());
				$("#txtQQ").val($divAddr.find("[name=qq]").val());
				if ($divAddr.find("[name=isDefault]").val() == "1") {
					$("#checkisDef").attr("checked", true);
				} else {
					$("#checkisDef").attr("checked", false);
				}				


			} else {
				$("#txtcaddrId").val("");
			}
			var pagei = $.layer({
				type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
				btns : 2,
				btn : [ '确定', '取消' ],
				title : title,
				border : [ 0 ],
				closeBtn : [ 0 ],
				closeBtn : [ 0, true ],
				shadeClose : true,
				area : [ '700px', '520px' ],
				page : {
					dom : '#address_1'
				},
				yes : function(index) {
					//保存方法
					saveAddress();
				}
			});
		}

		function saveAddress() {
			var data = {
				caddrId : $("#txtcaddrId").val(),
				mobilePhone : $.trim($("#txtPhone").val()),
				receiverName : $.trim($("#txtLinkman").val()),
				province : $("#selProvince").val(),
				city : $("#selCity").val(),
				district : $("#selDistrict").val(),
				detailAddr : $.trim($("#txtDetailAdd").val()),
				qq : $.trim($("#txtQQ").val()),
				isDefault : $("#checkisDef").attr("checked") ? 1 : 0,
				weiId: $("#weiid").val()
			}
			if (!CheckData(data)) {
				return;
			}
			$.ajax({
				url : "/free/saveAddress",
				type : "post",
				async : false,
				data : data,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("服务器出现异常");
				},
				success : function(data) {					
					if (data.msg == "1") {
						window.location.href=window.location.href;
					} else {
						alert(data.msg);
					}

				}
			});
		}

		function CheckData(data) {
			if (data.mobilePhone == "") {
				alert("手机不能为空");
				return false;
			} else {
				var reg = /^1[34578]\d{9}$/;
				if (!reg.test(data.mobilePhone)) {
					alert("手机格式错误");
					return false;
				}
			}

			if (data.receiverName == "") {
				alert("收货人不能为空");
				return false;
			}
			if (data.province == "0") {
				alert("请选择省");
				return false;
			}
			if (data.city == "0") {
				alert("请选择市");
				return false;
			}
			if (data.detailAddr == "") {
				alert("详细地址不能为空");
				return false;
			}
			if (data.qq == "") {
				var re = /[1-9][0-9]{4,}/;
				if (!re.test(data.qq)) {
					alert("QQ格式错误");
					return false;
				}
			}
			return true;
		}
		//设为默认
  	function setDefaultAddr(caddrId) {
			$.ajax({
				url : "/free/setDefaultAddr",
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				async : false,
				data : {
					caddrId : caddrId,
					weiid: $("#weiid").val()
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("服务器出现异常");
				},
				success : function(data) {
					if (data.msg == "1") {
						window.location.href=window.location.href;
					} else {
						alert(data.msg);
					}

				}
			});
		} 

		function deleteAddr(caddrId) {
			if (!confirm("确定要删除该地址吗？")) {
				return;
			}
			$.ajax({
				url : "/free/deleteAddr",
				type : "post",
				async : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : {
					caddrId : caddrId,
					weiid: $("#weiid").val()
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("服务器出现异常");
				},
				success : function(data) {
					if (data.msg == "1") {
						window.location.href=window.location.href;
					} else {
						alert(data.msg);
					}

				}
			});
		}

	</script>
</head>
<body style="background: #fdffee;">
<div class="mzh_ymzssy">
    <div class="mar_au">
        <img src="/statics/images/img_9_19_2.png" class="fl w"/>
    </div>
</div>
<div class="fl w">
    <div class="mar_au">
        <div class="mzh_ymzssy_bt">请认真确认信息，以便快递小哥能送到您手中~</div>

        <div class="fl w mt_20">
            <div class="fl ml_20 mr_20" style="width: 330px;"><img src="${product.defaultimg }" width="330" height="300"/></div>

            <div class="fl" style="width:800px;">
                <div class="fl w">
                    <div class="fl f14">
                        <div class="fl">
                            <b class="fl ft_c9 mt_10">半价：</b>
                            <b class="ft_red f24">￥<span id="oneprice">${product.halfprice }</span></b>
                        </div>
                        <div class="fl" style="margin-left: 40px;">
                            <b class="fl ft_c9 mt_10">原价：</b>
                            <font class="f12 ft_c9 fl" style="text-decoration:line-through;margin-top: 12px;">${product.price }</font>
                        </div>
                    </div>
                </div>

                <div class="fl w mt_30">
                    <b class="fl ft_c9 mt_5">数量：</b>
                    <div class="mzh_tc_js" id="mzh_tc_j">-</div>
                    <input type="text" class="mzh_tc_text" value="0"/>
                    <div class="mzh_tc_js" id="mzh_tc_add">+</div>
                    <span class="ft_c9 fl ml_20 mt_5">首次购买均为半价，多买多赚哦~</span>
                </div>

                <div class="fl w mt_20">
                    <b class="fl w ft_c9 mt_5 mb_10">收货地址：</b>
					<div class="jbzl_shdz">
					<input type="hidden" id="addressId_txt" name="addressId" value="${addrId}">
					<c:forEach var="addr" items="${list}" varStatus="index">
                    <div class='${addr.addressId==address.addressId?"jbzl_shdz_yes":"jbzl_shdz_no"}' name="divaddress" caddrid="${addr.addressId }">
                        <div class="jbzl_shdz_yes_div">
                            <b name="receiverName">${addr.receiveName }</b>
                        </div>
                        <div class="jbzl_shdz_yes_div_0">
                            <span>${addr.address }</span> <span name="detailAddr">${addr.detailAddr }</span> <span name="mobilePhone">${addr.phone }</span>
                            <div class="mzh_span">
							<c:choose>
								<c:when test="${addr.isDefault==1 }">
									默认收货地址
								</c:when>
								</c:choose>
                            </div>
                            <input type="hidden" name="isDefault" value="${addr.isDefault}" /> 

                        </div>
                        <div class="jbzl_shdz_yes_div_1" name="mzh_mrdz" style="display: none;">
                            <a href="javascript:deleteAddr('${addr.addressId }');" class="jbzl_close">删除</a> <a href="javascript:;" class="jbzl_bainji mr_20" onclick="openPopups('编辑地址',this)">编辑</a>
                        </div>
                        <input type="hidden" name="province" value="${addr.province }" /> <input type="hidden" name="city" value="${addr.city }" /> <input type="hidden" name="district" value="${addr.district }" /> <input type="hidden" name="qq" value="${addr.qq }" /> <input type="hidden" name="province" value="${addr.province }" /> <input type="hidden" name="city" value="${addr.city }" /> <input type="hidden" name="district" value="${addr.district }" /> <input type="hidden" name="qq" value="${addr.qq }" /> <input type="hidden" name="address" value="${addr.address }" /> <input type="hidden" name="detail_addr" value="${addr.detailAddr }" /> <input type="hidden" name="receiver_name" value="${addr.receiveName }" /> <input
						type="hidden" name="mobile_phone" value="${addr.phone }" />
                    </div>                   
                    </c:forEach>
                    
                    <div class="jbzl_shdz_add " onclick="openPopups('添加地址',null)"></div>
                    </div>
                </div>
			
				
                <div class="fl w mt_20">
                    <div class="fl f14">
                        <div class="fl">
                            <b class="fl ft_c9 mt_10">实付款（含运费）：</b>
                            <b class="ft_red f24">￥<span id="payprice">0.00</span></b>
                        </div>
                    </div>
                </div>
                <div class="fl w mt_20"><a href="javascript:;" class="mzh_queding">确定</a></div>
                <div class="blank"></div>
                <div class="blank"></div>
                <div class="blank"></div>
                <div class="blank"></div>
                <div class="blank"></div>
            </div>
        </div>
    </div>
</div>
<div class="blank"></div>
<input type="hidden" id="weiid" value="${user.weiID }" />
<!-- 背景颜色 -->
<div class="mzh_bgtm"></div>
<!-- 背景颜色 -->

<div id="address_1" style="display: none;">
	<input type="hidden" id="txtcaddrId" value="" />
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">手机：</dd>
		<dt>
			<input id="txtPhone" type="text" class="fl btn_h28 w250" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" />
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">收货人：</dd>
		<dt>
			<input id="txtLinkman" type="text" class="fl btn_h28 w250" />
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">地区：</dd>
		<dt class=" fl">
			<select id="selProvince" class="mzh_select">
				<option>省</option>
			</select> <select id="selCity" class="mzh_select">
				<option>市</option>
			</select> <select id="selDistrict" class="mzh_select">
				<option>区</option>
			</select>
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">详细地址：</dd>
		<dt>
			<input type="text" maxlength="65" id="txtDetailAdd" class="kud_boxs" value="" />
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">QQ号：</dd>
		<dt>
			<input id="txtQQ" type="text" class="fl btn_h28 w250" maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')" />
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd"></dd>
		<dt>
			<label><input type="checkbox" checked="checked" name="checkisDef" id="checkisDef"></input>设为默认收货地址</label> 
			
		</dt>
	</dl>
</div>

</body>
</html>