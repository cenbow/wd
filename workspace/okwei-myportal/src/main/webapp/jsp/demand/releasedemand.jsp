<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布招商需求</title>
<link rel="stylesheet" type="text/css" href="/statics/css/glbdy.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/index.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/mzh_dd.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/regions.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/regions.css" />

<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/statics/js/lib/uploadify/jquery.uploadify-3.1.js"></script>

<script type="text/javascript" src="/statics/js/jquery.regions2.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="/statics/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/statics/ueditor/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="/statics/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/statics/js/MyPage/mypage.js?v=201511141429"></script>
<script type="text/javascript" src="/statics/js/demand/releasedemand.js"></script>
<style>
.leimu_msg ul li {
	width: 100%;
	height: 26px;
	line-height: 26px;
	text-indent: 20px;
	font-family: "宋体";
	font-size: 12px;
	color: #3a3a3a;
}
.ul_fls span:hover{color:#F00;}
.left_color:hover{color: #f10;}
.updata_tixian{width: 100%;}
#id_fbcp td{padding : 5px 0px;}
#id_baocunhou p{margin-left: 0px;}
.bg_ffc{background: #ffc;padding: 5px 0px;margin-right: 10px;width: 230px;}
.hover_yes{position: absolute;right: 0px;top: -137px;width: 360px;background: #fff;border: 1px solid #898989;padding:10px;display: none;height: 100px;}
.hover_yes_0{display: none;}
.hover_yes_0 span{float: left;width: 100%;text-align: center;padding-bottom: 5px;line-height: 20px;}
.hover_yes_0_0{float: left;width: 100%;line-height: 16px;color: #333;}
.hover_yes_1{position: absolute;left: 300px;bottom: -30px;width: 30px;height: 30px;background: url("/statics/images/jiantou_11_7.png") no-repeat;background-size: 80%;}
.fbxq_jbxx dt p{margin-left:10px;}
.updata_tixian{overflow-y: scroll;height:350px;}
a.ndfxs_1-2_fenye_no:link{height:35px;}
</style>

</head>
<body>
 <div class="fl fw_1024 ">
      <div class="fl  bo bg_white bor_si" style="width: 100%;">
      <div class="gygl_xxk_t f16 ndfxs_1-2_border">
          <b class="gygl_xxk_yes" name="mzh_xxk" style="color: #000;">基本信息<span style="width: 64px;"></span></b>
      </div>
      <dl class="fbxq_jbxx f14 mt_20">
          <dd>招商加盟标题：</dd>
          <dt><input type="text" id="txtTitle" value="${demand.title }" maxlength="30" class="fbxq_jbxx_text" placeholder="30字以内，如：黄土高原特产大量招商"/></dt>
      </dl>
      <dl class="fbxq_jbxx f14 mt_20">
          <dd>招商加盟主图：</dd>
          <dt>
          <p style="margin-left: 0px;line-height: 32px;color: #999;">（注：800px X 800px，文件大小500k以内）</p>
          <div class="own_img_one">
          <img class="shou" id="mainImage" src="${demand.mainImage!=null && demand.mainImage !='' ?demand.mainImage:'/statics/pic/xin_inselimg.png'}" width="120" height="120"/>
			<div class='transparentDiv'>
				<span id="btnUpLoadImg"></span>
			</div>
			</div>
          </dt>
      </dl>
      <dl class="fbxq_jbxx f14 mt_20">
          <dd>招商加盟产品：</dd>
          <dt><input type="submit" id="btnSelectProduct" value="选择产品" class="dis_b mt_5 fl  btn_sel28_pre bor_cls shou"></dt>
      </dl>
      <div class="fbxq_jbxx f14 mt_20" id="id_gdcp" style="display: none;">    	
<!--           <div class="fl w130 mr_20 tc">
              <img src="/statics/images/134O45911A0-15G918.jpg" width="80" height="80">
              <span class="fl mzh_width_100 ft_c6 mt_10">风扇</span>
          </div> -->
          
          <div class="fl w130 mr_20 tc">
              <a id="btnMoreProduct" href="javascript:;" class="mt_47 fl">已选产品</a>
          </div>
      </div>
      <div class="blank"></div>
      <div class="blank"></div>
      </div>

      <div id="requiredpanle" class="fl  mt_20 bg_white bor_si" style="width: 100%;">
      <div class="gygl_xxk_t f16 ndfxs_1-2_border" >
          <b class="gygl_xxk_yes" name="mzh_xxk" style="color: #000;">要求及条件设置<span style="width: 112px;"></span></b>
      </div>
      <dl class="fbxq_jbxx f14 mt_20 ">
          <dd><span><b>代理商设置</b><font class="ft_c9 f12 ml_20">(针对代理商加盟设置)</font></span></dd>
      </dl>
      <!-- 保存前 -->
      <div class="fl mzh_width_100" id="id_baocunqian">
          <dl class="fbxq_jbxx f14 mt_20">
              <dd>选择地区：</dd>
              <dt>
              	<input type="submit" value="选择" class="dis_b mt_5 fl  btn_sel28_pre bor_cls shou" id="id_xuanze">
              	<input type="hidden" id="selectedRegion" value="">
              	<input type="hidden" id="selectedRegionName" value="">
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20">
              <dd>指定地区：</dd>
              <dt>
              	<div class="fl ft_c3"><span class="ft_c3" id="showRegName"></span></div>
              	<!-- <input type="submit" id="btnEditReg" style="width: 40px;" class="dis_b mt_5 fl ml_10 f12 btn_sel28_pre bor_cls shou" value="更改"> -->
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20 pb20">
              <dd style="line-height: 22px;">代理条件：</dd>
              <dt style="width: 920px;">
                  <textarea id="txtAgentRequired" class="fbxq_jbxx_textarea"></textarea>
              <div class="fr bg_ffc">
                  <p><b>TIPS   代理条件</b></p>
                  <p>如：</p>
                  <p>1、代理商所在地为市及以上地区；</p>
                  <p>2、代理商前期需投资一定资金运营；</p>
                  <p>3、代理商需有过同行业经营经验；</p>
              </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20  pb20">
              <dd style="line-height: 22px;">扶持政策：</dd>
              <dt style="width: 920px;">
                  <textarea id="txtSupport" class="fbxq_jbxx_textarea"></textarea>
              <div class="fr bg_ffc">
                  <p><b>TIPS   扶持政策</b></p>
                  <p>如：(成为代理商之后)</p>
                  <p>1、享受代理价进货；</p>
                  <p>2、铺货支持，先铺货后付款；</p>
                  <p>3、城市独家，落地店进货订单自动按城市分配到代理商；</p>
              </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20  pb20">
              <dd>量化要求：</dd>
              <dt style="width: 920px;">
                  <div class="fl" style="width: 433px;" id="id_addLH">
                      <div class="fl mb_10">
                          <input name="txtRequiredKey" class="fbxq_jbxx_text2">
                          <span class="fl tc" style="width: 24px;color: #000;">：</span>
                          <input name="txtRequiredValue" class="fbxq_jbxx_text2">
                      </div>
                  </div>
                  <div class="fbxq_jbxx_tjlhyq shou" id="id_anniuLH">+ 添加量化要求</div>
                  <div class="fr bg_ffc">
                      <p><b>TIPS   量化要求</b></p>
                      <p>如：</p>
                      <p>·代理商首单要求：3万元</p>
                      <p>·成为代理商：7天内需下首单</p>
                      <p>·月销要求：4万元  </p>
                      <p>·是否要求有公司：有限责任公司</p>
                      <p>其它： 有服装代理渠道经营者优先</p>
                      <p>注：量化要求系统不做限制；</p>
                  </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20 pb20">
              <dd>代理合同：</dd>
              <dt style="width: 920px;">
                  <div class="fl">
                      <input id="txtFileName" type="text"  readonly="readonly"  class="fbxq_jbxx_text" placeholder="可上传代理合同或协议">
                      <input id="txtFilePath" type="hidden" value="" />
                      <br>
                      <font class="fl" style="color: #3a3a3a;">合同文件支持格式：.doc、.docx、pdf</font>
                  </div>
                  <div class="ml_48" style="position: relative;float:left;">
	                  <input id="upFile" type="submit" value="上传" class="dis_b fl  btn_sel28_pre bor_cls shou">
	                  <div style="position: absolute;">
	                  	<em id="uploadFile"></em>
	                  </div>
                  </div>
                  
                  <div class="fr ml_20 bg_ffc">
                      <p><b>TIPS   扶持政策</b></p>
                      <p>如：(成为代理商之后)</p>
                      <p>1、享受代理价进货；</p>
                      <p>2、铺货支持，先铺货后付款；</p>
                      <p>3、城市独家，落地店进货订单自动按城市分配到代理商；</p>
                  </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20">
              <dd>悬赏设置</dd>
              <dt style="width: 934px;position: relative;">
                  <div class="fl f16" style="width: 560px;">
                      <font class="fl ml_20" style="color: #3a3a3a;">成功招募一个代理商悬赏奖励</font>
                      <input type="text" class="fbxq_jbxx_text fl ml_10 mr_10" id="txtAgentReward" placeholder="金额" style="width: 80px;">
                      <font class="fl" style="color: #3a3a3a;">元</font>
                      <font class="fl ml_20 f12">（建议最低额度为500元）</font>
                      <font class="fl ml_20 f12">申请者提交资料，买卖双方进行沟通洽谈，并通过您的审核通过后，您需一次性支付认证员悬赏金额。</font>
                  </div>
                  <div class="fr lin20 bg_ffc ft_c3 f12" style="padding: 5px;width: 220px;">微店网在全国拥有20万的实地
                      <a href="#" class="ft_lan" name="name_rzy">认证员</a> ，他们能够帮助您在全国范围招募
                      <a href="#" class="ft_lan" name="name_rzy">代理商</a>和
                      <a href="#" class="ft_lan" name="name_rzy">落地店</a>。尊重他们的劳动，可以为他们设置合理额度的奖励。</div>
                  <div class="hover_yes">
                      <div class="hover_yes_0" id="hover_yes_0" style="display: block;">
                          <span class="ft_c3 fw_b">什么是认证员</span>
                          <div class="hover_yes_0_0">微店网认证员分布在全国各大城市，专为微店网平台号、品牌号、工厂号、批发号提供招商、培训、政策指导等服务。微店网认证员是微店网生态中的重要一环，他为微店网和供应商建立了承上启下的服务桥梁。</div>
                      </div>
                      <div class="hover_yes_0" id="hover_yes_1">
                          <span class="ft_c3 fw_b">什么是代理商</span>
                          <div class="hover_yes_0_0">代理商是平台号、品牌号的一级销售渠道，代理商帮助平台号、品牌号在全国各个城市建立完整的销售渠道，并对代理区域的落地店进行管理。微店网的代理商设置为城市独家，微店网订单会根据收货区域自动帮助平台号、品牌号将订单匹配给区域代理商。</div>
                      </div>
                      <div class="hover_yes_0" id="hover_yes_2">
                          <span class="ft_c3 fw_b">什么是落地店</span>
                          <div class="hover_yes_0_0">落地店是商品的零售终端，包括实体店和个人自由创业者。在我们的渠道架构中，落地店统一在所在城市的代理商进货，进货后在所在的城市区域销售给消费者，从而赚取差价。</div>
                      </div>
                      <div class="hover_yes_1"></div>
                  </div>
              </dt>
              <div class="blank"></div>
          </dl>
          <div class="fl mzh_width_100 ndfxs_1-2_border"><input id="btnSaveRequired" type="submit" value="保存" class="dis_b mt_5 fl btn_sel28_pre bor_cls shou" style="margin:0 400px 30px;"></div>
      </div>

      <!-- 保存后 -->
		<c:if test="${demand.dRequiredVOs !=null && demand.dRequiredVOs.size() >0 }">
		<c:forEach items="${demand.dRequiredVOs }" var="item">
       <div id="divrequired${item.requiredId }" data="${item.requiredId }" name='divrequired' class="fl mzh_width_100 f14 bor_bo pt5 pb5 line_42" style="background: #FFFAEC;">
          <span class="fl" style="text-indent: 20px;">指定地区：${item.numRequired}</span>
          <div class="fr mr_20">
              <input type="checkbox" id="showRequired${item.requiredId}" name="showRequired">
              <label for="showRequired${item.requiredId}">展开</label>
          </div>
          <a href="javascript:;" name='deleteRequired' class="fr ft_lan mr_10">删除</a>
          <a href="javascript:;" name='editRequired' class="fr ft_lan mr_10">修改</a>
          <a href="javascript:;" name='addRequired' class="fr ft_lan mr_10">新增</a>
          <input type="hidden" name="txtRegionIDs" value="${item.codeStr }">
			<input type="hidden" name="txtRegionNames" value="${item.codeName } ">
			<input type="hidden" name="txtAgentRequired" value="${item.agentRequired }">
			<input type="hidden" name="txtSupport" value="${item.support }">
			<input type="hidden" name="txtrequiredKVs" value="${item.requiredKVStr }">
			<input type="hidden" name="txtFileName" value="${item.contract }">
			<input type="hidden" name="txtFilePath" value="${item.contractPath }">
			<input type="hidden" name="txtAgentReward" value="${item.agentReward }">
      </div>
      </c:forEach>
      </c:if>
      <div class="fl mzh_width_100" id="id_baocunhou" style="display: none;">
          <dl class="fbxq_jbxx f14 mt_20">
              <dd>指定地区：</dd>
              <dt>
              <div class="fl ft_c3"><span class="ft_c3" id="showRegionNames"></span></div>
              <!-- <input  type="submit" style="width: 40px;" class="dis_b mt_5 fl ml_10 f12 btn_sel28_pre bor_cls shou" value="更改"> -->
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20  pb20">
              <dd style="line-height: 22px;">代理条件：</dd>
              <dt>
              <div class="fl fbxq_jbxx_textarea">
                  <p><b>TIPS   代理条件</b></p>
                  <p id="showAgentRequired">如：</p>
<!--                   <p>1、代理商所在地为市及以上地区；</p>
                  <p>2、代理商前期需投资一定资金运营；</p>
                  <p>3、代理商需有过同行业经营经验；</p> -->
              </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20  pb20">
              <dd style="line-height: 22px;">扶持政策：</dd>
              <dt>
              <div class="fl fbxq_jbxx_textarea">
                  <p><b>TIPS   扶持政策</b></p>
                  <p id="showSupport">如：(成为代理商之后)</p>
<!--                   <p>1、享受代理价进货；</p>
                  <p>2、铺货支持，先铺货后付款；</p>
                  <p>3、城市独家，落地店进货订单自动按城市分配到代理商；</p> -->
              </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20  pb20">
              <dd>量化要求：</dd>
              <dt>
              <div id="showRequiredKVs" class="fl fbxq_jbxx_textarea">
<!--                   <p>量化要求1：XXXXXXXXXXX</p>
                  <p>量化要求2：XXXXXXXXXXX</p>
                  <p>量化要求3：XXXXXXXXXXX</p>
                  <p>量化要求4：XXXXXXXXXXX</p>
                  <p>量化要求5：XXXXXXXXXXX</p> -->
              </div>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20 pb20">
              <dd>代理合同：</dd>
              <dt>
              <div class="fl">
                  	<span id="showFileName">加盟商合同.doc</span>
                  <br>
                  <font class="fl" style="color: #3a3a3a;">合同文件支持格式：.doc、.docx、pdf</font>
              </div>
              <a href="#" target="_blank" id="showFilePath">下载</a>
              </dt>
          </dl>
          <dl class="fbxq_jbxx f14 mt_20">
              <dd>悬赏设置</dd>
              <dt>
              <div class="fl f16" style="width: 560px;">
                  <font class="fl ml_20" style="color: #3a3a3a;">成功招募一个代理商悬赏奖励：<span id="showAgentReward">500</span>元</font>
                  <font class="fl ml_20 f12">（建议最低额度为500元）</font>
                  <font class="fl ml_20 f12">申请者提交资料，买卖双方进行沟通洽谈，并通过您的审核通过后，您需一次性支付认证员悬赏金额。</font>
              </div>
              </dt>
              <div class="blank"></div>
          </dl>
          <div class="fl mzh_width_100 ndfxs_1-2_border"><input type="submit" name='addRequired' value="新增" class="dis_b mt_5 fl btn_sel28_pre bor_cls shou" style="margin:0 400px 30px;"></div>
      </div>





      <dl class="fbxq_jbxx f14 mt_20">
          <dd><span><b>落地店设置</b><font class="ft_c9 f12 ml_20">(针对落地店加盟设置)</font></span></dd>
      </dl>
      <dl class="fbxq_jbxx f14 mt_20 pb20">
          <dd>首单要求</dd>
          <dt>
          <div class="fl f16">
              <font class="fl ml_20" style="color: #3a3a3a;">落地店首单要求</font>
              <input type="text" class="fbxq_jbxx_text fl ml_10 mr_10" value="${demand.orderAmout }" id="txtOrderAmout" placeholder="金额" style="width: 80px;">
              <font class="fl" style="color: #3a3a3a;">元</font>
              <font class="fl ml_20 f12">（当用户下单金额满足首单要求后，则直接成为落地店）</font>
          </div>
          </dt>
      </dl>
      <dl class="fbxq_jbxx f14 mt_20">
          <dd>悬赏设置</dd>
          <dt>
          <div class="fl f16" style="width: 600px;">
              <font class="fl ml_20" style="color: #3a3a3a;">成功招募一个落地店悬赏奖励</font>
              <input type="text" class="fbxq_jbxx_text fl ml_10 mr_10" value="${demand.shopReward }" id="txtShopReward" placeholder="金额" style="width: 80px;">
              <font class="fl" style="color: #3a3a3a;">元</font>
              <font class="fl ml_20 f12">（建议最低额度为200元）</font>
              <font class="fl ml_20 f12">用户首笔订单金额满足落地店首单要求后，您需一次性支付认证员悬赏金额。</font>
          </div>
          </dt>
          <div class="blank"></div>
      </dl>

      </div>

      <div class="fl  mt_20 bg_white bor_si" style="width: 100%;">
      <div class="gygl_xxk_t f16  ndfxs_1-2_border">
          <b class="gygl_xxk_yes" name="mzh_xxk" style="color: #000;">详细说明<span style="width: 64px;"></span></b>
      </div>
      <div class="gygl_xxsm mt_30 mb_30">
          <div class="gygl_xxsm_left ml_30">
              <div class="gygl_xxsm_left_yes" id="btnpctxt" name="gygl_xqd">PC端详情</div>
              <div class="gygl_xxsm_left_no" id="btnwaptxt" name="gygl_xqd">移动端详情</div>
          </div>
      </div>
      <div class="gygl_xxsm_pc ml_30 mt_20">
		<div id="PcNew_1" class="fl">
			<div class=" fl" id="pcEditor">
				<!-- 这里写你的初始化内容 -->
				<script id="pcContent" name="pcContent" type="text/plain">${demand.pcDetails }</script>
				<!-- pc端详细 百度编辑器 -->
			</div>
			<div class="blank2"></div>
		</div>
		<div id="PcNew_2" class="fl" style="display: none;">
			<!-- 这里写你的初始化内容 -->
			<script id="wapContent" name="wapContent" type="text/plain">${demand.appDetails }</script>
			<div class="datais_boxwap bor_si" id="wapEditor">
				<!-- WAP端     端详细 百度编辑器 -->
			</div>
		</div>
      </div>


      <div class="gygl_xxsm mt_30 mb_10">
          <div id="btnCreateWapDes" class="gygl_xxsm_right_ydd shou tc ml_30" style="float: left;height: 42px;line-height: 42px;">生成移动端详情</div>
      </div>
      <div class="sub_btns fl bor_cls" style="margin-left: 230px;width:auto;">
          <div class="bc_draft fl" style="margin-left: 30px;">
              <a id="btnSaveDraft" href="javascript:void(0);" class="dis_b bg_white">保存草稿</a>
          </div>
          <div class="bc_submit fl">
              <input type="submit" id="btnSaveDemand" value="发布需求" class="btn_yes42_pre bor_cls shou">
          </div>
      </div>
      </div>
      
 </div>    
   <!-- hidden -->
   <input type="hidden" id="txtdemandID" value="${demand.demandId }" />
  
  <!-- 选择招商产品 -->
<div class="updata_tixian" style="display:none;" id="win_div_1">
    <div class="fl mzh_width_100 bor_bo mb_10">
        <input type="submit" onclick="javascript:location.href='/publishProduct/index'" value="发布产品" class="dis_b  mb_10 fl  btn_sel28_pre bor_cls shou">
    </div>
    <div class="fl mzh_width_100 mb_10">
        <span class="fl line_30">产品名称：</span>
        <input id="txtProductName" type="text" class="fbxq_jbxx_text" style="width: 120px;">
        <span class="fl line_30 ml_20">商城分类：</span>
        <select id="selShopClassOne" class="fl w80 mt_5">
            <option value="-1">一级分类</option>
        </select>
        <select id="selShopClassTwo" class="fl w80 mt_5 ml_10">
            <option value="-1">二级分类</option>
        </select>
        <input type="submit" id="btnSearchProduct" value="搜索" class="dis_b fl ml_10 btn_sel28_pre bor_cls shou">
    </div>
    <table class="bor_si fl mzh_width_100 f14" id="tbcheckProduct">
        <tr class="bor_bo fw_b">
            <td><input name="checkall" type="checkbox" class="ml_10"></td>
            <td>图片</td>
            <td>标题</td>
            <td>价格</td>
            <td>店铺分类</td>
        </tr>
<!--         <tr class="bor_bo">
            <td><input type="checkbox" class="ml_10"></td>
            <td><img src="/statics/images/134O45911A0-15G918.jpg" width="60" height="60"></td>
            <td>风扇</td>
            <td>
                <p>零售价：￥300.00</p>
                <p>(含佣金：10.00)</p>
                <p>代理价：250.00</p>
                <p>落地价：280.00</p>
            </td>
            <td>小家电</td>
        </tr> -->
       
    </table>
	<div id="pageParentDiv" >
		
	</div>
</div>
<!-- 选择招商产品 -->
<div class="updata_tixian" style="display:none;width:540px;" id="win_div_2">
  <!--   <div class="fl mzh_width_100 mb_10">
        <span class="fl line_30">产品名称：</span>
        <input type="text" class="fbxq_jbxx_text" style="width: 120px;">
        <span class="fl line_30 ml_20">商城分类：</span>
        <select class="fl w80 mt_5">
            <option>一级分类</option>
        </select>
        <select class="fl w80 mt_5 ml_10">
            <option>一级分类</option>
        </select>
        <input type="submit" value="搜索" class="dis_b fl ml_10 btn_sel28_pre bor_cls shou">
    </div> -->
    <table class="bor_si fl mzh_width_100 f14" id="id_fbcp">
        <tr class="bor_bo fw_b">
            <td><input type="checkbox" name="checkall" class="ml_10"></td>
            <td>图片</td>
            <td>标题</td>
            <td>价格</td>
            <td>店铺分类</td>
        </tr>
        <c:if test="${products!=null && products.size()>0 }">
        <c:forEach items="${products }" var="item">
        <tr class="bor_bo">
            <td><input type="checkbox" name='checkProduct'  value="${item.productID }" checked="checked" class="ml_10"></td>
            <td><img src="${item.productImg }" name="productimg" width="60" height="60"></td>
            <td><span name="title">${item.prodcutTitle }</span></td>
            <td>
                <p>零售价：￥<span name="price">${item.price }</span></p>
                <p>(含佣金：<span name="comminss">${item.comminss}</span>)</p>
                <p>代理价：<span name="agentprice">${item.agentPrice}</span></p>
                <p>落地价：<span name="shopprice">${item.shopPrice}</span></p>
            </td>
            <td><span name="classname">${item.shopClassName}</span></td>
        </tr> 
        </c:forEach>
        </c:if>
    </table>
	<div id="pagediv" >
		
	</div>
</div>
</body>
</html>