<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>
<%
			String joindomain = ResourceBundle.getBundle("domain").getString(
					"joindomain");
			String okweidomain = ResourceBundle.getBundle("domain").getString(
				"okweidomain");
%> 
<script type="text/javascript">
$(function(){
    /* 鼠标放上 */
    $("#mzh_hover").mouseenter(function(){
        $("#mzh_QQ").show();
    })

    /* 鼠标离开 */
    $("#mzh_hover").mouseleave(function(){
         $("#mzh_QQ").hide();
    })
    $("#mzh_QQ a").click(function(){
        var index = $(this).parent().index();
        switch(index)
        {
            case 0:window.open("http://wpa.qq.com/msgrd?v=3&uin=2792985013&site=qq&menu=yes");break;
             case 1:window.open("http://wpa.qq.com/msgrd?v=3&uin=3130213554&site=qq&menu=yes");break;
              case 2:window.open("http://wpa.qq.com/msgrd?v=3&uin=2936150637&site=qq&menu=yes");break;
               case 3:window.open("http://wpa.qq.com/msgrd?v=3&uin=2256734804&site=qq&menu=yes");break;
        }
    });
});
</script>
<!-- 底部 -->
<div class="blank"></div>
<footer>
<div class="blank bg-hui_eb"></div>
<div class="bottom_di">

  <div class="wdwrap">
    <div class="bottom-text">
      <div class="weix_jyi fl">
        <ul>
          <li class="icon_one">微信支付</li>
          <li class="icon_two"><a target="_blank" href="http://www.<%=okweidomain %>/help/us#qtbt">七天包退</a></li>
          <li class="icon_thr"><a target="_blank" href="http://www.<%=okweidomain %>/help/us#dbjy">担保交易</a></li>
        </ul>
      </div>
      <dl style="margin-left:82px;">
        <dt>关于微店网</dt>
        <dd><a href="http://www.<%=okweidomain %>/help/us#qywh" target="_blank">企业文化</a></dd>
        <dd><a href="http://www.<%=okweidomain %>/help/us#lxwm" target="_blank">联系我们</a></dd>
        <dd class="bg_blue"><a href="<%=joindomain %>/supplier/apply?w=${user.tgWeiID}" target="_blank">供应商进驻</a></dd>
      </dl>
      <dl style="margin-left:82px;">
        <dt>微店网客服</dt>
        <dd>
          <p>4006-136-086</p>
         <span>周一至周五（9:00-18:00）</span>
          <div class="QQbtn" id="mzh_hover" style="position:relative;" >企业QQ留言
          	<!-- <div id="mzh_QQ" style="background-color: rgb(255, 255, 255); display:none; position: absolute; left: 0px; bottom: 34px; z-index: 999; width: 151px; box-shadow: rgb(180, 180, 180) 0px 0px 3px 0px; padding-bottom: 10px;  background-position: initial initial; background-repeat: initial initial;">
            <div style="float: left; width: 151px; margin-top: 10px;">
              <div style="float: left; margin: 0px 10px; color: #666; text-indent:0;  font-size:12px; line-height:26px;"> 客服一：</div>
              <a target="_blank" href="javascript:void(0);"> <img src="http://wpa.qq.com/pa?p=2:1059854962:51" style="float:left; text-indent:0;"></a> </div>
            <div style="float: left; width: 151px; margin-top: 10px;">
              <div style="float: left; margin: 0px 10px; color: #666;  text-indent:0;  font-size:12px;  line-height:26px;"> 客服二：</div>
              <a target="_blank" href="javascript:void(0);"> <img src="http://wpa.qq.com/pa?p=2:1059854962:51" style="float:left; text-indent:0;"></a> </div>
            <div style="float: left; width: 151px; margin-top: 10px;">
              <div style="float: left; margin: 0px 10px; color: #666;  text-indent:0;  font-size:12px; line-height:26px;"> 客服三：</div>
              <a target="_blank" href="javascript:void(0);"> <img src="http://wpa.qq.com/pa?p=2:1059854962:51" style="float:left; text-indent:0;"></a> </div>
            <div style="float: left; width: 151px; margin-top: 10px;">
              <div style="float: left; margin: 0px 10px; color: #666;  text-indent:0; font-size:12px; line-height:26px;"> 客服四：</div>
              <a target="_blank" href="javascript:void(0);"> <img src="http://wpa.qq.com/pa?p=2:1059854962:51" style="float:left; text-indent:0;"></a> </div>
          </div>
           -->
          
          </div>
          
        </dd>
      </dl>
      <dl style="margin-left:82px;">
        <dt>微店网招商</dt>
        <dd>专线：0755-36852441</dd>
        <dd>专线：0755-33100081</dd>
        <dd>专线：0755-33275303</dd>
      </dl>
      <dl style=" margin-left:82px; float:left;display:inline;">
        <dt>手机微店网</dt>
        <dd><img src="/statics/images/wd-wx1.png" width="80" height="80" alt="手机微店网" /></dd>
      </dl>
      <dl style=" margin-left:60px; float:left;display:inline;">
        <dt>微店网服务号</dt>
        <dd><img src="/statics/images/wd-wx.png" width="80" height="80" alt="微店网服务号" /></dd>
      </dl>
      <dl style="float:right;display:inline;">
        <dt>微店网APP下载</dt>
        <dd><img src="/statics/images/wd-wx2.png" width="80" height="80" alt="微店网服务号" /></dd>
      </dl>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="foot fl">
  <div class="wdwrap">
    <ul class="foot-text">
      <li><span>Copyright2012深圳市云商微店网络技术有限公司</span><span>地址：深圳市罗湖区国威路68号互联网产业园2栋5层</span></li>
      <li><span>电话：0755-33100081</span><span>传真：0755-23910940</span><span>E-mail：<a href="mailto:kefu@okwei.com" title="E-mail">kefu@okwei.com</a></span></li>
      <li><span>经营许可证：粤ICP备<a href="#" title="经营许可证" target="_blank" style="text-decoration:underline;">10203293号-3</a></span><span>增值电信业务经营许可证：<a href="#" title="增值电信业务经营许可证" target="_blank" style="text-decoration:underline;">粤B2-20130803</a></span></li>
      <li><a href="#" target="_blank" title="法律顾问">法律顾问：广东德盈律师事务所 欧雄灿</a></li>
    </ul>
    <p class="foot-pic"> <a target="_blank" href="#" title="营业执照"><img alt="营业执照" src="/statics/images/foot1.png"></a> <a target="_blank" href="#" title="税务登记"><img alt="税务登记" src="/statics/images/foot2.png"></a> <a target="_blank" href="#" title="工商网监"><img alt="工商网监" src="/statics/images/foot3.png"></a> <a target="_blank" href="#" title="众信网络身份现场认证"><img alt="众信网络身份现场认证" src="/statics/images/foot4.png"></a> <a target="_blank" href="#" title="可信网站认证"><img alt="可信网站认证" src="/statics/images/foot5.png"></a> <a target="_blank" href="#" title="电信增值服务许可证"><img alt="电信增值服务许可证" src="/statics/images/foot6.png"></a> <a target="_blank" href="#" title="财付通特约商户"><img alt="财付通特约商户" src="/statics/images/foot7-1.png"></a> <a target="_blank" href="#" title="银联特约商户"><img alt="银联特约商户" src="/statics/images/foot8-1.png"></a> </p>
  </div>
</div>
</footer>
<div style="display: none;">       
   <!--  <script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fffa4dda2fc67ab38b1530f33200ae384' type='text/javascript'%3E%3C/script%3E"));
    //QQ在线咨询
//    BizQQWPA.addCustom({aty: '0', a: '0', nameAccount: 4006136086, selector: 'BizQQWPA'});
    </script> -->
</div>
