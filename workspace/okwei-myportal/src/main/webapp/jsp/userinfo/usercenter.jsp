<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String wapdomain=ResourceBundle.getBundle("domain").getString("wapdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微店中心</title>
<script type="text/javascript" src="/statics/js/share.js?v=2"></script>
<script type="text/javascript" src="/statics/js/common/cookieHelper.js"></script>
<script type="text/javascript">
	/* 首页-分享 */
	$(function() {
		$("#mzh_fenxiang").mouseover(function() {
			$(this).find("[id=aaa]").attr("class", "mzh_fenxiang_yes");
			$(".mzh_fenxiang").show();
		});
		$("#mzh_fenxiang").mouseout(function() {
			$(this).find("[id=aaa]").attr("class", "mzh_fenxiang_no");
			$(".mzh_fenxiang").hide();
		});
		txshare();
	});

	function txshare() {
		$.ajax({
			url : "/commons/shareReminders",
			type : "post",
			data : {
				type : 2
			},
			dataType : "json",
			success : function(data) {
				if (data.Statu == 1) {
					$("#win_div_1 div").html(data.StatusReson);
					var pagei = $.layer({
						type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
						btns : 2,
						btn : [ '去报名', '关闭' ],
						title : "活动提醒",
						border : [ 0 ],
						closeBtn : [ 0 ],
						closeBtn : [ 0, true ],
						shadeClose : true,
						area : [ '514', '300' ],
						page : {
							dom : '#win_div_1'
						},
						yes : function(index) {
							location.href = "/act/actlist";
						}
					});
				}
			}
		});
	}
	function shareto(type) {
		var title = "${userinfo.weiName}";
		var pageurl = "http://${userinfo.weiID}.okwei.com";
		var source = "${userinfo.weiName}的微店隶属于微店网总平台，普通网民可以在这里免费注册开微店，供应商可以从这里提交资料，把产品发到云端产品库，让像我一样的无数网民为他销售产品。";
		switch (type) {
		case 0: {
			ShareToQzone(title, pageurl, source);
			break;
		}
		case 1: {
			ShareToTencent(source, pageurl, source);
			break;
		}
		case 2: {
			ShareToSina(source, pageurl, source);
			break;
		}
		default: {
			alert("分享类型错误！");
			break;
		}
		}
	}

	
</script>
<script>
  $(function(){
	  var checksupplier=$("#issupplierinp").val();
	  var name="keyUserid-"+${userinfo.weiID};
	  if(checksupplier>0){
		  var cooVal=getCookie(name);
		  if(cooVal==null||cooVal==""){
			  var mzh_bgtm = $(document).height();
			    $(".mzh_bgtm").height(mzh_bgtm).show();
			    bWidth = 362;//弹出层的宽度
			    bHeight= 362;//弹出层的高度
			    aOffsetLeft = $(document).width()/2 - (parseInt(bWidth/2));
			    aOffsetTop = (window.document.documentElement.clientHeight/2) - (bHeight=="auto"?150:parseInt(bHeight/2)) +  $(document).scrollTop();
			    $(".mzh_syhttc").show();
			    $(".mzh_syhttc").css({display:"block",left:aOffsetLeft+"px",top:aOffsetTop+"px",width:bWidth,height:bHeight});
			    $(".mzh_bgtm").show();
		  }else{
			  $(".mzh_bgtm").hide();
		      $(".mzh_syhttc").hide();
		  }
	  }else{
		  $(".mzh_bgtm").hide();
	      $(".mzh_syhttc").hide();
	  }
	  
	  $(".mzh_syhttc_close").click(function(){
	      $(".mzh_bgtm").hide();
	      $(".mzh_syhttc").hide();
	  })
	  $("#mzh_bztsgxx").click(function(){
		  if($(this).is(':checked')){
			  setCookie(name,"12",1);
		  }else{
			  clearCookie(name);
		  }
		  
	  })
	  
	  

	  $("#mzh_fx").mouseover(function() {
	    $(this).find(".fx_WKX").show();
	  }).mouseout(function() {
	    $(this).find(".fx_WKX").hide();
	  });
  })
</script>
<style>
  .mzh_syhttc{position: absolute;left:0px;top: 0px;display: none;z-index: 100;background: url('http://base3.okimgs.com/images/img_httc.png') no-repeat;}
  .mzh_syhttc_close{position: absolute;right: 0px;top: 0px;cursor: pointer;}
  .mzh_bgtm{position: absolute;width: 100%;left: 0px;top:0px;background:url("http://base3.okimgs.com/images/bg_hetm.png") repeat;display: block;z-index: 99;height: 100%;}
  a.mzh_syhttc_cphd{display: inline-block;width: 130px;text-align: center;font-size: 14px;background: #60ff00;border-radius: 10px;line-height:36px;color: #000;}
  .txs_imgs{height:auto;}
  .portrait{width:1012px;}
  .mzh_fxdp {width: 110px;line-height: 30px;font-size: 16px;display: inline-block;border-radius: 5px;background: #1a9a5c;text-align: center;color: #fff;position: relative;}
  .mzh_fxdp{width: 315px;line-height: 40px;font-size: 18px;display: inline-block;border-radius: 5px;background: #1a9a5c;text-align: center;color:#fff;position: relative;}
  .mzh_fxdp img{vertical-align: baseline;margin-right: 5px;}
  .fx_WKX {background:#FFFFFF;border: 1px solid #1A9A5C;height: 40px;left: 0px;top: -41px;overflow: hidden;position: absolute;width: 107px;z-index: 1;display: none;}
  .ZF div {float: left;width: 22px; height: 21px; margin: 10px 0 0 10px; text-indent: 0;}
  .shouhup_news a{margin:0px;}
</style>

</head>
<body>
	<div class=" fl w bor_si" style="background: #fff;">
		<input type="hidden" id="issupplierinp" value="${issupplier }">
		<div class="txs_imgs bg-w fl" style="width: 1029px;">
			<div class="portrait fl">
				<div class="fl img_port">
					<a href="http://www.${userinfo.weiID }.<%=okweidomain %>"><img src="${userinfo.weiImg}" width="85" height="85" /></a>
				</div>
				<div class="fl wei_name">
					 
					<div class="name_one f14 ft_hui">
						<span><a href="http://www.${userinfo.weiID }.<%=okweidomain %>">${userinfo.weiName}的微店</a></span>
						<div class="fl" id="mzh_fenxiang">
							<div class="mzh_fenxiang_no" id="aaa">
								<img src="/statics/pic/fenxiang_icon.png" style="margin: 0px;" />
								<div class="mzh_fenxiang">
									<a href="javascript:shareto(0);"><img src="http://base.okimgs.com/images/TX_kj.gif"></a> 
									<a href="javascript:shareto(1);"><img src="http://base.okimgs.com/images/TX_wb.gif"></a> 
									<a href="javascript:shareto(2);"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
								</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="name_two">
						<h2 class="ft_hui f14">${userinfo.weiID}</h2>
					</div>
					<div class="name_thr">
						<div class="gygl_top_l_xx_ypr" style="margin-top: 4px; margin-left: 6px;">
							<c:if test="${uservo.batch==1 }">
								<span class="gygl_top_l_xx_hong mr_5 fw_b">批</span>
							</c:if>
							<c:if test="${uservo.yun==1 }">
								<span class="gygl_top_l_xx_huang mr_5 fw_b">厂</span>
							</c:if>
							<c:if test="${uservo.verifer==1 }">
								<span class="gygl_top_l_xx_huang mr_5 fw_b">认</span>
							</c:if>
						</div>
					</div>
				</div>
				<c:if test="${isIn==1 }">
					<div class="fr tc f12" style="width: 145px;">
		              <img src="http://api.okwei.com/api/v3/Client/VerifierQrcode?url=<%=wapdomain %>/shop/shop828?weiid=${userinfo.weiID }" width="110" height="110" class="mt10">
		              <p>扫一扫，分享到朋友圈</p>
		            </div>
					<div class="shouhup_news fr mr_10">
		              <div class="mzh_fxdp fr" style="width: 110px;line-height: 30px;font-size: 16px;" id="mzh_fx">
		                <img src="http://base.okwei.com/images/chengzhuimages/img_cz_10.jpg" width="12">分享
		                <div class="fx_WKX" style="display: none;">
		                  <div class="ZF">
		                    <div><a href="javascript:shareto(0);"><img src="http://base.okimgs.com/images/TX_kj.gif"></a></div>
		                    <div><a href="javascript:shareto(1);"><img src="http://base.okimgs.com/images/TX_wb.gif"></a></div>
		                    <div><a href="javascript:shareto(2);"><img src="http://base.okimgs.com/images/XL_wb.gif"></a></div>
		                  </div>
		                </div>
		              </div>
		            </div>	
				</c:if>
			</div>
			<div class="clear"></div>
			<%-- <c:if test="${isIn==1 }">
				<p class="f18 fl ml_20 mb_10 mt_10" style="color: #be0200;">
					<img src="http://base.okwei.com/images/chengzhuimages/img_xl.jpg" style="vertical-align: text-bottom;margin-right: 5px;">
					将报名购物全返的产品分享给你的朋友并收藏，邀他参与<b>购物全返</b>活动吧！
				</p>
			</c:if> --%>
			
		</div>
		<div class="fl purchase" style="width: 1006px;">
			<div class="fl img_fontme">
				<img src="/statics/pic/wdzx_img001.png" style="margin-left: 20px;" />
				<h6 class="ft_c6">我的购买订单</h6>
			</div>
			<div class="fl dfhs mar_t20" style="width: 860px;">
				<ul>
					<li>待付款<span><a href="/order/buylist?dt=1&ds=0">${uservo.needpaycount}</a></span></li>
					<li>待发货<span><a href="/order/buylist?dt=1&ds=1">${uservo.needsendcount}</a></span></li>
					<li>待收货<span><a href="/order/buylist?dt=1&ds=2">${uservo.needacceptcount}</a></span></li>
				</ul>
			</div>
		</div>
		<c:if test="${uservo.yun==1 ||uservo.batch==1 }">
			<div class="fl purchase" style="width: 1006px;">
				<div class="fl img_fontme">
					<img src="/statics/pic/wdzx_img002.png" style="margin-left: 20px;" />
					<h6 class="ft_c6">我的销售订单</h6>
				</div>
				<div class="fl dfhs mar_t20" style="width: 860px;">
					<ul>
						<li>待付款<span><a href="/seller/buylist?dt=1&ds=0">${uservo.waitPayCount}</a></span></li>
						<li>待发货<span><a href="/seller/buylist?dt=1&ds=1">${uservo.waitSendCount}</a></span></li>
						<li>待收货<span><a href="/seller/buylist?dt=1&ds=2">${uservo.waitAcceptCount}</a></span></li>
						<li>售后中<span><a href="/seller/buylist?dt=1&ds=5">${uservo.customeringCount}</a></span></li>
					</ul>
				</div>
			</div>
		</c:if>
		<div class="fl purchase" style="width: 1006px;">
			<div class="fl img_fontme">
				<img src="/statics/pic/wdzx_img003.png" style="margin-left: 20px;" />
				<h6 class="ft_c6">我的产品</h6>
			</div>
			<div class="fl dfhs mar_t20" style="width: 860px;">
				<ul>
					<li>销售中<span><a href="/myProduct/list/Showing/0/0?isClick=1">${uservo.oncount}</a></span></li>
					<li>已下架<span><a href="/myProduct/list/Drop/0/0?isClick=1&title=&type=-1&pageId=">${uservo.offcount}</a></span></li>
					<li>草稿箱<span><a href="/myProduct/list/OutLine/0/0?isClick=1&title=&type=-1&pageId=">${uservo.graftCount}</a></span></li>
					<c:if test="${userinfo.pth == 1}">
						<!-- 平台号待审核产品数量 -->
						<li>待审核<span><a href="/myProduct/list/Audit/0/0?isClick=1">${uservo.waitVerifyCount}</a></span></li>
					</c:if>
				</ul>
			</div>
		</div>

		<div class="fl purchase" style="width: 1006px;">
			<div class="fl img_fontme">
				<img src="/statics/pic/wdzx_img004.png" style="margin-left: 20px;" />
				<h6 class="ft_c6">我的上游供应</h6>
			</div>
			<div class="fl dfhs mar_t20" style="width: 860px;">
				<ul>
					<c:if test="${userinfo.pth == 1}">
						<!-- 平台号我的子供应商数量 -->
						<li>我的供应商<span><a href="/childrenAccount/getSelfSCList">${uservo.mySupplyCount}</a></span></li>
					</c:if>
					<li>我的关注<span><a href="/relationMgt/upstream">${uservo.attedtionCount}</a></span></li>
				</ul>
			</div>
		</div>

		<div class="fl purchase" style="width: 1006px; border-bottom: none;">
			<div class="fl img_fontme">
				<img src="/statics/pic/wdzx_img004_2.png" style="margin-left: 20px;" />
				<h6 class="ft_c6">我的下游分销</h6>
			</div>
			<div class="fl dfhs mar_t20" style="width: 860px;">
				<ul>
					<c:if test="${userinfo.pth == 1||userinfo.pph == 1}">
						<!-- 平台号，品牌号代理商，落地店数量 -->
						<li>代理商总量<span><a href="/agent/agentIndex?status=3">${uservo.agentCount}</a></span></li>
						<li>落地店总量<span><a href="/productShop/getDownstreamStoreList/1">${uservo.groundCount}</a></span></li>
					</c:if>
					<li>零售分销商总量<span><a href="/relationMgt/downstream/all">${uservo.attedtionedCount}</a></span></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 背景颜色 -->
<!-- <div class="mzh_bgtm"></div>

<div class="mzh_syhttc">
  <img src="http://base3.okimgs.com/images/img_close.png" class="mzh_syhttc_close"/>
  <div class="mzh_100 tc" style="margin-top: 270px">
    <input type="checkbox" id="mzh_bztsgxx" class="fl" style="margin: 5px 2px 0 122px;"/>
    <label for="mzh_bztsgxx" class="ft_white f14 fl">不再提示该信息</label>
    <div class="blank2"></div>
    <a href="/act/actlist" class="mzh_syhttc_cphd">设置参与活动商品</a>
  </div>
</div> -->
	
	<!-- 分享提醒 -->
	<div class="updata_tixian none layer_pageContent" id="win_div_1" style="display: none;">
		<div class="f18 fl outermost"></div>
	</div>
</body>
</html>