<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(function(){
	$(".li_img").removeClass("li_img").parent().find("li:eq(4)").addClass("li_img");
});
</script>
<div class="fl conter_fic">
	<div id="leftMenu" class="fl conter_left">
		<div class="p10">
			<h2>
				<img class="ico_2" src="/statics/images/space.gif" url="/userInfo/index"><span name="node">个人设置</span><i></i>
			</h2>
			<ul>
				<li name="leaf_node"><a href="/userInfo/index">基本资料</a></li>
				<li name="leaf_node"><a href="/userInfo/settings">安全设置</a></li>
				<li name="leaf_node"><a href="/userInfo/address">收货地址</a></li>
				<li name="leaf_node"><a href="/userInfo/bindAccount">绑定账户</a></li>
			</ul>
		</div>
	</div>
</div>




