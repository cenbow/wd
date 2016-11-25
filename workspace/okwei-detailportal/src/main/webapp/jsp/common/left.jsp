<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<div id="leftMenu" class="fl conter_left">
	<div class="p10">
		<div name="menu_top">
			<h2>
				<img class="ico_1" src="/statics/images/space.gif"><span
					name="node">认证管理</span><i></i>
			</h2>
			<ul>
				<li name="leaf_node"><a href="/verifierTeam/getVerified">我的认证团队</a></li>
				<li name="leaf_node"><a href='/yunSupplierMgt/list'>供应商审核记录</a></li>
				<li name="leaf_node"><a href='/verify/JoinedCommpany'>已进驻的供应商</a></li>
			</ul>
		</div>
	</div>
</div>