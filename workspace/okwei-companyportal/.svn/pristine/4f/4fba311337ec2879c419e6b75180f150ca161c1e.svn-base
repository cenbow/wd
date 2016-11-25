<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云麻首页-领取</title>
<link href="http://base.okimgs.com/images/favicon.ico"	rel="shortcut icon">
<link rel="stylesheet" href="/statics/css/glbdy-free.css" />
<link rel="stylesheet" href="/statics/css/mzh_dd-free.css" />
<style>
.img_dele{ width:26px; height:26px; position:absolute; right:-13px; cursor:pointer; top:-13px;}
.own_img{ width:119px; height:119px; border:1px solid #cdcdcd; position:relative;float:left; margin-right:20px; margin-top:10px;}

</style>
<script src="/statics/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/statics/js/uploadify/jquery.uploadify-3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/statics/js/uploadify/uploadify.css" />
<script type="text/javascript" src="/statics/js/free/taste.js"></script>
<script type="text/javascript" src="/statics/js/district.js"></script>

</head>
<body style="background: #fdffee;">
<div class="mzh_ymzssy">
    <div class="mar_au">
        <img src="/statics/images/img_9_19_2.png" class="fl w"/>
    </div>
</div>
<div class="fl w">
	<form id="tasteApply_form" name="tasteApply_form"  method="post">
	<input type="hidden" name="weiid" id="weiid" value="${user.weiID }" />
    <div class="mar_au">
        <div class="mzh_ymzssy_bt">请申请人认真填写哦，试吃活动之后还有更多福利活动~</div>
        <div class="fl w">
            <dl class="mzh_ymzs">
                <dd>姓名：</dd>
                <dt>
                    <div class="fl w">
                        <input type="text" class="mzh_ymzs_text" name="name" id="name"/><b class="ft_red ml_10">*</b>
                    </div>
                    <span class="fl ft_c9 f12">保证快递小哥能送到您手中~</span>
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd>联系电话：</dd>
                <dt>
                    <div class="fl w">
                        <input type="text" class="mzh_ymzs_text" name="phone" id="phone"/><b class="ft_red ml_10">*</b>
                    </div>
                    <span class="fl ft_c9 f12">我们联系您的唯一方式~</span>
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd>邮箱：</dd>
                <dt>
                    <div class="fl w">
                        <input type="text" class="mzh_ymzs_text" name="email" id="email"/><b class="ft_red ml_10">*</b>
                    </div>
                    <span class="fl ft_c9 f12">当我们有重大活动时，方便与您取得联系~</span>
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd>是否本人申请试吃：</dd>
                <dt>
                    <div class="fl mr_20">
                        <input type="radio" class="ml_30 mr_5" id="mzh_sfsc_yes"  name="isMyself" value="1"/><label for="mzh_sfsc_yes">是</label>
                        <input type="radio" class="ml_30 mr_5" id="mzh_sfsc_no"  name="isMyself" value="0"/><label for="mzh_sfsc_no">否</label>
                    </div>
                    <div class="fl w ml_20" style="width: 240px;display: none;" name="mzh_sfsc_as">
                        <input type="text" class="mzh_ymzs_text" style="width: 240px;" name="relationship" id="relationship"/>
                        <span class="fl ft_c9 f12">填写与本人的关系</span>
                    </div>
                    <b class="ft_red ml_10" name="mzh_sfsc_as" style="display: none;">*</b>
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd style="height: 180px;">试吃人的三高数值是多少：</dd>
                <dt class="mb_20">
                    <div class="fl mr_20">
                        <input type="checkbox" class="ml_10 mr_5" value="1" name="isHighXueYa" id="isHighXueYa"/><label>高血压</label>
                    </div>
                    <div class="fl">
                        <span class="fl">高压数值</span>
                        <input type="text" class="mzh_ymzs_text ml_10" name="highNum" id="highNum" style="width: 80px;"/>
                    </div>

                    <div class="fl ml_20">
                        <span class="fl">低压数值</span>
                        <input type="text" class="mzh_ymzs_text ml_10" name="lowNum" id="lowNum" style="width: 85px;"/>
                    </div>
                    <b class="ft_red ml_10">*</b>
                </dt>
                <dt class="mb_20">
                    <div class="fl mr_20">
                        <input type="checkbox" class="ml_10 mr_5" value="1" name="isHighXueTang"/><label>高血糖</label>
                    </div>
                    <div class="fl">
                        <span class="fl">检测数值</span>
                        <input type="text" class="mzh_ymzs_text ml_10" name="xueTangNum" id="xueTangNum" style="width: 250px;"/>
                    </div>
                </dt>
                <dt class="mb_20">
                    <div class="fl mr_20">
                        <input type="checkbox" class="ml_10 mr_5" value="1" name="isHighXueZhi" id="isHighXueZhi"/><label>高血脂</label>
                    </div>
                    <div class="fl">
                        <span class="fl">检测数值</span>
                        <input type="text" class="mzh_ymzs_text ml_10" name="xueZhiNum" id="xueZhiNum" style="width: 250px;"/>
                    </div>
                </dt>
                <dt class="">
                    <div class="fl mr_20">
                        <input type="checkbox" class="ml_10 mr_5" value="1" name="isZhongFeng" name="isZhongFeng"/><label>中风</label>
                    </div>
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd>上传检测报告：</dd>
                <dt>
                	<div class="fl w"><div class="mzh_ymzs_sctp mb_10" id="uploadImg" name="uploadImg" >选择图片</div></div>
                    <div class="fl w" id="divImage">
                      
                    </div>
					 
					
                </dt>
            </dl>
            <dl class="mzh_ymzs">
                <dd>收件地址：</dd>
                <dt>
                    <div class="fl w">
                       
                        <select id="selProvince" class="mzh_ymzs_select" name="province">
							<option>省/自治区/直辖市</option>
						</select> <select id="selCity" class="mzh_ymzs_select ml_10" name="city">
							<option>市</option>
						</select> <select id="selDistrict" class="mzh_ymzs_select ml_10" name="district">
							<option>区</option>
						</select>
						
						
                        <b class="ft_red ml_10">*</b>
                    </div>
                    <div class="fl w mt_10">
                        <input type="text" class="mzh_ymzs_text" placeholder="详细地址" name="detailAddress" id="detailAddress"/>
                    </div>
                    <div class="fl w">
                        <span class="fl ft_c9 f12">请详细填写，方便快递小哥找到您</span>
                    </div>
                </dt>
            </dl>

            <div class="mzh_ymzs_bottom f14">
                <div class="fl w" style="margin: 50px 0 0 90px;">注：<span class="ft_red">前1000</span>名<span class="ft_red">免费</span>，1000名后邮费到付</div>
                <a href="javascript:;" class="mzh_ymzs_tjsy">提交试用</a>
            </div>

        </div>
    </div>
    </form>
</div>
<div class="blank"></div>
<div class="blank"></div>
<div class="blank"></div>

<!-- 背景颜色 -->
<div class="mzh_bgtm"></div>
<!-- 背景颜色 -->

<!-- 1 -->
<div class="mzh_zcz_tc tc" style="display:none;">
    <div class="fl w pr">
        <img src="/statics/images/img_9_19_34.png" class="fl w">
        <img src="/statics/images/img_9_19_36.png" class="mzh_ymzs_close" name="mzh_close">
    </div>
    <div class="fl w f18 mt_20 mb_20">亲，每人仅限试用一次，点击<a href="#" class="ft_lan">购买</a>产品。</div>
    <div class="fl w tc mt_20"><div class="mzh_ymzs_qd" name="mzh_close">确定</div></div>
</div>
<!-- 活动End -->

<!-- 2个弹窗叠在一起 -->
<div class="mzh_zcz_tc tc">
    <div class="fl w pr">
        <img src="/statics/images/img_9_19_34.png" class="fl w">
        <img src="/statics/images/img_9_19_36.png" class="mzh_ymzs_close" name="mzh_close">
    </div>
    <div class="fl w f18 mt_20"><img src="/statics/images/d-ico1.png" width="25" style="vertical-align: bottom;" class="mr_5"><b>亲，恭喜您申请试用成功!</b></div>
    <div class="fl w f14 mt_5 mb_20">　　　请保持电话畅通，以便后续联系您。</div>
    <div class="fl w tc mt_20"><div class="mzh_ymzs_qd" name="mzh_close">确定</div></div>
</div>
<!-- 活动End -->



</body>
</html>