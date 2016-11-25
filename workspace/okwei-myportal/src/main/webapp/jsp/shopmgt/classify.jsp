<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
</head>

<body class="bg_f3">
	<div class="fr conter_right">
      
      <!-- 订单信息-操作 -->
      <div class="conter_right_xx_cz">
        <table class="conter_right_xx_cz_table">
          <tbody><tr class="ndfxs_1-2_color ndfxs_1-2_border">
           	<td class="ygmdcp_14" colspan="4">
            	<div class="btn_hui28_pre shou ml_20 fl">新建分类</div>
                <div class="btn_hui28_pre shou ml_20 w104 fl">编辑分类名称</div>
            
            </td> 
          </tr>
          <tr class="ndfxs_1-2_color ndfxs_1-2_border">
            <td class="ygmdcp_14_20">分类名称</td>
            <td class="ygmdcp_14">分类产品</td>
            <td class="ygmdcp_14 ti_30px">移动</td>
            <td class="ygmdcp_14">操作</td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
           
           
        </tbody></table> 
      </div>
      <div class="blank"></div>
      <div class="conter_right_xx_cz">
        <table class="conter_right_xx_cz_table">
          <tbody><tr class="ndfxs_1-2_color ndfxs_1-2_border">
           	<td class="ygmdcp_14" colspan="4">
            	<div class="btn_hui28_pre shou ml_20 fl">新建分类</div>
                <div class="btn_sel28_pre shou ml_20 w82 fl">保存</div>
            
            </td> 
          </tr>
          <tr class="ndfxs_1-2_color ndfxs_1-2_border">
            <td class="ygmdcp_14_20">分类名称</td>
            <td class="ygmdcp_14">分类产品</td>
            <td class="ygmdcp_14 ti_30px">移动</td>
            <td class="ygmdcp_14">操作</td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40"><input type="text" class="btn_h28 w150"></td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40"><input type="text" class="btn_h28 w150"></td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40"><input type="text" class="btn_h28 w150"></td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr> 
        </tbody></table> 
      </div>
      <div class="blank"></div>
      <div class="conter_right_xx_cz">
        <table class="conter_right_xx_cz_table">
          <tbody><tr class="ndfxs_1-2_color ndfxs_1-2_border">
           	<td class="ygmdcp_14" colspan="4">
            	<input type="text" class="btn_h28 fl w150 dis_b ml_20">
                <div class="btn_sel28_pre shou ml_20 w82 fl">保存</div>
                <div class="btn_hui28_pre shou ml_20 w82 fl">取消</div>
            
            </td> 
          </tr>
          <tr class="ndfxs_1-2_color ndfxs_1-2_border">
            <td class="ygmdcp_14_20">分类名称</td>
            <td class="ygmdcp_14">分类产品</td>
            <td class="ygmdcp_14 ti_30px">移动</td>
            <td class="ygmdcp_14">操作</td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
          <tr class="ndfxs_1-2_border">
            <td class="ygmdcp_14_20 lh_40">时尚女装</td>
            <td class="ygmdcp_14 lh_40">0</td>
            <td class="ygmdcp_14 lh_40">
            	<div class="icon_imgsi">
                	<a class="img_up1" href="#"></a>
                    <a class="img_up2" href="#"></a>
                    <a class="img_up3" href="#"></a>
                    <a class="img_up4" href="#"></a>
                </div>
            </td>
            <td class="ygmdcp_14 lh_40"><a name="mzh_remove" class="ft_lan" href="#">删除</a></td> 
          </tr>
        </tbody></table> 
      </div>
    </div>	
</body>