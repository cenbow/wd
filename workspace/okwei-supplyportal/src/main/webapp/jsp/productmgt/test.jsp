<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
</head>

<body class="bg_f3">

	<script type="text/javascript">
		var threeSelectData = {
			"省份" : {
				val : "",
				items : {
					"城市" : {
						val : "",
						items : {
							"区县" : ""
						}
					}
				}
			},
			"beijing" : {
				val : "01",
				items : {
					"bj-01" : {
						val : "0101",
						items : {
							"bj-01-01" : "010101"
						}
					},
					"bj-02" : {
						val : "0102",
						items : {
							"bj-02-01" : "010201",
							"bj-02-02" : "010202"
						}
					}
				}
			},
			"shanxi" : {
				val : "02",
				items : {}
			},
			"guangzhou" : {
				val : "02",
				items : {}
			}
		};
		$(function() {
			var $s1 = $("#Select1");
			var $s2 = $("#Select2");
			var $s3 = $("#Select3");
			// var v1=null;
			// var v2=null;
			// var v3=null;
			var v1 = "01";
			var v2 = "0102";
			var v3 = "010202";
			$.each(threeSelectData, function(k, v) {
				appendOptionTo($s1, k, v.val, v1);
			});
			$s1.change(function() {
				$s2.html("");
				$s3.html("");
				if (this.selectedIndex == -1)
					return;
				var s1_curr_val = this.options[this.selectedIndex].value;
				$.each(threeSelectData, function(k, v) {
					if (s1_curr_val == v.val) {
						if (v.items) {
							$.each(v.items, function(k, v) {
								appendOptionTo($s2, k, v.val, v2);
							});
						}
					}
				});
				$s2.change();
			}).change();
			$s2.change(function() {
				$s3.html("");
				var s1_curr_val = $s1[0].options[$s1[0].selectedIndex].value;
				if (this.selectedIndex == -1)
					return;
				var s2_curr_val = this.options[this.selectedIndex].value;
				$.each(threeSelectData, function(k, v) {
					if (s1_curr_val == v.val) {
						if (v.items) {
							$.each(v.items, function(k, v) {
								if (s2_curr_val == v.val) {
									$.each(v.items, function(k, v) {
										appendOptionTo($s3, k, v, v3);
									});
								}
							});
						}
					}
				});
			}).change();
			function appendOptionTo($o, k, v, d) {
				var $opt = $("<option>").text(k).val(v);
				if (v == d) {
					$opt.attr("selected", "selected")
				}
				$opt.appendTo($o);
			}
		});
	</script>
	<style type="text/css" media="screen">
select {
	width: 80px;
}
</style>


	<select id="Select1" name="Select1"></select>
	<select id="Select2" name="Select2"></select>
	<select id="Select3" name="Select3"></select>
</body>