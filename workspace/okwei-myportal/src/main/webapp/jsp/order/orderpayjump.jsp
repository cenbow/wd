<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title> 
<script type="text/javascript" src="/statics/js/jquery-1.7.1.min.js?_= "></script>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>

</head>
<body>
<div id="address_1" style="display:none;">
    <div class="fl tc f16 mt_20" style="width: 660px;">电脑端支付功能正在开发完善中，请到app客户端支付！</div>
    <a href="http://app.okwei.com/" class="f16 fr mt_47 mr_20">微店APP官方下载</a>
</div>
<script>
    function win(title,win_id){
        var pagei = $.layer({
            type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
            btns: 1,
            btn: ['知道了'],
            title: title,
            border: [0],
            closeBtn: [0],
            closeBtn: [0, true],
            shadeClose: true,
            area: ['700px','300px'],
            yes:function (){
            	history.go(-1);
            },
            page: {dom : '#'+ win_id}
			
        });
    }
    win('支付','address_1');
</script>
</body>
</html>