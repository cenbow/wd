<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="/statics/js/district.js"></script>
<script type="text/javascript">
	$(function() {
		InitCity();
		$("[name=divaddress]").mouseover(function() {
			$(this).find("[name=setasdefault]").show();
			$(this).find("[name=mzh_mrdz]").show();

		}).mouseout(function() {
			$(this).find("[name=setasdefault]").hide();
			$(this).find("[name=mzh_mrdz]").hide();
		})
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
			isDefault : $("#checkisDef").attr("checked") ? 1 : 0
		}
		if (!CheckData(data)) {
			return;
		}
		$.ajax({
			url : "/selleruserinfo/saveAddress",
			type : "post",
			async : false,
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == "1") {
					alert("保存成功");
					addreload();
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
		if (data.district == "0") {
			alert("请选择区");
			return false;
		}
		if (data.detailAddr == "") {
			alert("详细地址不能为空");
			return false;
		}
		if (data.qq == "") {
			alert("QQ不能为空");
			return false;
		} else {
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
			url : "/selleruserinfo/setDefaultAddr",
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			async : false,
			data : {
				caddrId : caddrId
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == "1") {
					addreload();
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
			url : "/selleruserinfo/deleteAddr",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				caddrId : caddrId
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == "1") {
					addreload();
				} else {
					alert(data.msg);
				}

			}
		});
	}
	
	function addreload(){ 
		window.location.href =window.location.href+"&sx=1";
	}
</script>
<div class="jbzl_shdz  p30">
	<c:forEach var="addr" items="${list}" varStatus="index">
		<div class='${addr.isDefault==1?"jbzl_shdz_yes":"jbzl_shdz_no" }'
			name="divaddress" caddrId="${addr.caddrId }">
			<div class="jbzl_shdz_yes_div">
				<b name="receiverName">${addr.receiverName }</b>
				<c:choose>
					<c:when test="${addr.isDefault==1 }">
						<span style="display: block;">默认地址</span>
					</c:when>
					<c:otherwise>
						<span name="setasdefault" style="display: none;"
							onclick="setDefaultAddr('${addr.caddrId }')">设为默认</span>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="isDefault" value="${addr.isDefault}" />
			</div>
			<div class="jbzl_shdz_yes_div_0">
				<span>${addr.address }</span> <span name="detailAddr">${addr.detailAddr }</span>
				<span name="mobilePhone">${addr.mobilePhone }</span>
			</div>
			<div class="jbzl_shdz_yes_div_1" name="mzh_mrdz"
				style="display: none;">
				<a href="javascript:deleteAddr('${addr.caddrId }');"
					class="jbzl_close">删除</a> <a href="javascript:;"
					class="jbzl_bainji mr_20" onclick="openPopups('编辑地址',this)">编辑</a>
			</div>
			<input type="hidden" name="province" value="${addr.province }" /> <input
				type="hidden" name="city" value="${addr.city }" /> <input
				type="hidden" name="district" value="${addr.district }" /> <input
				type="hidden" name="qq" value="${addr.qq }" />
		</div>
	</c:forEach>
	<div class="jbzl_shdz_add" onclick="openPopups('添加地址',null)"></div>
</div>

<style type="text/css">
.jbzl_dl dt{ width:400px;}
.jbzl_dl dt select {
	border:1px solid #ddd； 
}
</style>

<div id="address_1" style="display: none;">
	<input type="hidden" id="txtcaddrId" value="" />
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">手机：</dd>
		<dt>
			<input id="txtPhone" type="text" class="fl btn_h28 w250"
				maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" />
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
			<textarea id="txtDetailAdd" class="kud_boxs"></textarea>
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd">QQ号：</dd>
		<dt>
			<input id="txtQQ" type="text" class="fl btn_h28 w250" maxlength="20"
				onkeyup="this.value=this.value.replace(/\D/g,'')" />
		</dt>
	</dl>
	<dl class="jbzl_dl f14">
		<dd class="jbzl_dl_dd"></dd>
		<dt>
			<div class="jbzl_dl_swmr">
				<input type="checkbox" id="checkisDef" /> <label for="checkisDef">设为默认</label>
			</div>
		</dt>
	</dl>
</div>
