$(function(){
    /* 推广二维码 */
    $("#Mostype").hover(function() {
        $("#Jong").show();
    }, function() {
        $("#Jong").hide();
    });
    //二维码推广
    $("[name=mzh_sjwd]").mouseover(function() {
        $(".div-posabues").show();
    })
    $("[name=mzh_sjwd]").mouseout(function() {
        $(".div-posabues").hide();
    })


    /* 头部导航栏 */
	
	$('.bords1:last-child').css('border-bottom','none');
	$(".navs ul li").each(function(){
		$(this).click(function(){
			$(".navs ul li").attr("class","");
			$(this).attr("class","li_img");
		})
	})
	/* 右侧栏目 */
	$(".conter_left ul li").each(function(){
		$(this).click(function(){
		})
	})
	
	/************ 发布产品 商品参数隐藏效果 ***************/
	$("[name=BoxCheck]").each(function(){
		$(this).click(function(){
			 if($("[name=BoxCheck]").attr("andio")==0){
				$("[name=BoxCheck]").attr("andio","1");
                $("[name=BoxCheck]").attr("src","images/mzh_2015_6_15_zk.png");
				$("#ShopCoun").show();
			 }else if($(this).attr("andio")==1){
			 	$("[name=BoxCheck]").attr("andio","0");
				$("#ShopCoun").hide();
                 $("[name=BoxCheck]").attr("src","images/mzh_2015_6_15_sq.png");
			 }
		})
	})

	$("#box_2").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
                $(this).attr("src","images/mzh_2015_6_15_sq.png");
				$("#mzh_two").hide();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#mzh_two").show();
                 $(this).attr("src","images/mzh_2015_6_15_zk.png");
			 }
		})
	})

	$("#box_3").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
                $(this).attr("src","images/mzh_2015_6_15_sq.png");
				$("#mzh_three").hide();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#mzh_three").show();
                 $(this).attr("src","images/mzh_2015_6_15_zk.png");
			 }
		})
	})

	$("[name=box_1]").each(function(){
		$(this).click(function(){
			 if($("[name=box_1]").attr("andio")==0){
				$("[name=box_1]").attr("andio","1");
                $("[name=box_1]").attr("src","images/mzh_2015_6_15_sq.png");
				$("#mzh_one").hide();
			 }else if($(this).attr("andio")==1){
			 	$("[name=box_1]").attr("andio","0");
				$("#mzh_one").show();
                 $("[name=box_1]").attr("src","images/mzh_2015_6_15_zk.png");
			 }
		})
	})


	$("#HideImg").each(function(){
		$(this).click(function(){
			$("#ShopCoun").hide();
			$("#BoxCheck").attr("andio","0");
			$("#BoxCheck").attr("checked",false);
			
		})
	})

    /* 首页-分享 */
    $("#mzh_fenxiang").mouseover(function(){
        $(this).find("[id=aaa]").attr("class","mzh_fenxiang_yes");
        $(".mzh_fenxiang").show();
    })
    $("#mzh_fenxiang").mouseout(function(){
        $(this).find("[id=aaa]").attr("class","mzh_fenxiang_no");
        $(".mzh_fenxiang").hide();
    })
	
	$(".del_inputimg").live('click',function(){
		$(this).parent(".input_cols ").remove();
	})



    /*** 添加尺寸事件 ****/
	$("#Cun").keyup(function(event){
	  if(event.keyCode ==13){
		 var value = $(this).val();
		 if(value!=""){
		 	$("#Cicun").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
		 	$(this).val('');
		 } 
	  }
	});
	/**** 添加颜色事件 ****/
	$("#Cor").keyup(function(event){
	  if(event.keyCode ==13){
		 var value = $(this).val();
		 if(value!=""){
		 	$("#Coor").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
		 	$(this).val('');
		 } 
	  }
	});
	
	$(".add_heistimg").each(function(i){
		$(this).click(function(){
			var value = $(this).prev('input').val();
			if(i==0){
				if(value!=""){
					$("#Coor").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
					$(this).prev('input').val('');
				 } 
			}else if(i==1){
				if(value!=""){
					$("#Cicun").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
					$(this).prev('input').val('');
				 } 
			}
		})
	}) 
})

  //-----------弹出层 http://sentsin.com/jquery/layer/1.8.5/---------
 function win(title,win_id){
	 var pagei = $.layer({
    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
    btns: 2,
	btn: ['确定','取消'],
    title: title,
    border: [0],
    closeBtn: [0],
	closeBtn: [0, true],
    shadeClose: true,
    area: ['574px','600px'],
    page: {dom : '#'+ win_id}
    
});
 }
 
 //确认窗口
  function alert_txt(win_id,math){
	 layer.alert(win_id, math);
 }
 //弹出IFRAME 
	 function winiframe(title,url){
		 var paget = $.layer({
		 type: 2,
		    title:title,
		    area: ['910px', '700px'],
		    fix: false,
		    offset: [($(window).height() - 700)/2+'px', ''], //上下垂直居中
		    border: [0],
		    shade : [0.9, '#000'],
		    iframe: {src: 'http://im.qq.com/online/flash/flash20140304.swf'}
		    
	    
	});
	 }
/*---------信息提示------------*/
 function tips(node){    
     layer.tips($(node).attr('name'), node, {
        guide: 0,
        time: 1,
        maxWidth:240
    });
	  }
 function tips_2(node){    
     layer.tips($('#' + node).html(), $('#' + node), {
        guide: 0,
        time: 1,
        maxWidth:240
    });
	  }
 /*tips提示层私有属性。
msg: 提示内容。
follow: 吸附目标选择器。
guide: 指引方向（0：上，1：右，2：下，3：左）。
isGuide: 是否显示默认三角形。 这个参数可配合msg帮助你自定义三角形icon
more: 是否允许多个tips
style: ['background-color:#FFF8ED; color:#000; border:1px solid #FF9900; 此处可用来自定义tips的css样式 ', '#FF9900']]。 数组第二个值，为三角形的颜色。  */
