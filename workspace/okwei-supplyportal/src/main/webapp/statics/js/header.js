$(function(){
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
	/* 首页-分享 */
    $("#mzh_fenxiang").mouseenter(function(){
        $(this).attr("class","mzh_fenxiang_yes");
        $(".mzh_fenxiang").show();
    })
    $("#mzh_fenxiang").mouseleave(function(){
        $(this).attr("class","mzh_fenxiang_no");
        $(".mzh_fenxiang").hide();
    })
	
	
	
	/* 5.21 */
	$("#box_1").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
				$("#Ute_inp").show();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#Ute_inp").hide();
			 }
		})
	})
	
	
	$("#box_2").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
				$("#Ute_inpFic").show();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#Ute_inpFic").hide();
			 }
		})
	}) 
	
	$("#box_3").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
				$("#YuDing").show();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#YuDing").hide();
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

    
	
	

/************ 发布产品 商品参数隐藏效果 ***************/
	$("#BoxCheck").each(function(){
		$(this).click(function(){
			 if($(this).attr("andio")==0){
				$(this).attr("andio","1");
				$("#ShopCoun").show();
			 }else if($(this).attr("andio")==1){
			 	$(this).attr("andio","0");
				$("#ShopCoun").hide();
			 }
		})
	}) 
	
	
	
	/**** 添加颜色事件 回车事件 ****/
	$("#Cor").keyup(function(event){
	  if(event.keyCode ==13){
		 var value = $(this).val();
		 if(value!=""){
		 	$("#Coor").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
			$('#Cofic').append('<div class="col_scimgs fl"><div class="scimg_col fl"><span class="dis_b ml_10 dis_bgnews fl"></span><span class="fl ml_10 lh_40 ft_c3 dis_b">'+value+'</span></div><div class="scimg_del fl"><div class="btn_wear30_pre shou ml_10 mt_5 fl w150">上传图片</div><div class="fr imgsew"><img src="images/mzh-2014524-sp.gif" width="30" height="30" /><a class="ml_10 mr_20" name="delColor" href="javascript:;">删除</a></div></div></div>');
		 	$(this).val('');
			$('[name=delColor]').each(function(){
						$(this).click(function(){
							$(this).parent().parent().parent('.col_scimgs').remove();
						})
					})
		 } else{
			alert('请输入颜色！');	
		} 
	  }
	});
	
	/****** 删除事件 *****/
	$("body").on('click','.del_inputimg',function(){
		$(this).parent(".input_cols ").remove();
	})
	
	$("body").on('click','.red_botom>img',function(){
		$(this).parent().parent().remove();
	})
	
	
    /*** 添加尺寸事件 回车事件 ****/  
	$("#Cun").keyup(function(event){
	  if(event.keyCode ==13){
		 var value = $(this).val();
		 if(value!=""){
		 	$("#Cicun").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
		 	$('[name=CunLi]').append('<li><div class="red_botom ml_20 mt13 fl"><span>'+value+'</span> <img src="pic/del_ficfuncyes.png" /> </div></li>'); 
			$(this).val('');
		 }
	  }
	}); 
	
	/*** 添加尺寸颜色事件 点击事件 ****/   
	$(".add_heistimg").each(function(i){
		$(this).click(function(){
			var value = $(this).prev('input').val();
			if(i==0){
				if(value!=""){ //添加颜色
					$("#Coor").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>");
					$('#Cofic').append('<div class="col_scimgs fl"><div class="scimg_col fl"><span class="dis_b ml_10 dis_bgnews fl"></span><span class="fl ml_10 lh_40 ft_c3 dis_b">'+value+'</span></div><div class="scimg_del fl"><div class="btn_wear30_pre shou ml_10 mt_5 fl w150">上传图片</div><div class="fr imgsew"><img src="images/mzh-2014524-sp.gif" width="30" height="30" /><a class="ml_10 mr_20" name="delColor" href="javascript:;">删除</a></div></div></div>');
					$(this).prev('input').val('');
					$('[name=delColor]').each(function(){
						$(this).click(function(){
							$(this).parent().parent().parent('.col_scimgs').remove();
						})
					})
				 }else{ alert("请输入颜色！"); }
			}else if(i==1){
				if(value!=""){ //添加尺寸
					$("#Cicun").append("<div class=\"input_cols fl\"><input type=\"text\" value="+value+" /><div class=\"fl del_inputimg\"><img src=\"images/delimg_hua.png\" /></div></div>"); 
					$(this).prev('input').val('');
				 }else{
					alert("请输入尺寸！");
				} 
			}
		})
	})
	//自定义参数设置
	$('body').on('click','[name=Add_imgs]',function(){
		$(this).remove();
		$('#Fice_inp').append('<div class="self_one fl"><div class="once_inps fl"><input type="text" class="btn_h42 tr pr_10 w104 f14"  />  ： </div><div class="once_inps fl"><input type="text" class="btn_h42 f14 w222" /></div> <div class="fl xiao_icon"><a href="javascript:;" name="Add_imgs"><img src="pic/tianjia_icon.png" /></a><a href="javascript:;" name="Del_img"><img src="pic/delete_icon.png" /></a></div></div>');
		$('[name=Del_img]').each(function(){
			$(this).click(function(){
				$(this).parent().parent().remove();
				if($('.self_one').index()<=1){ 
					$('.xiao_icon').append('<a href="javascript:;" name="Add_imgs"><img src="pic/tianjia_icon.png" />');
					back;
				}
				
			})
		})
	})
	
	$('#AddPrice').click(function(){
		$('#PriceTjia').append('<div class="h40 title_shuru"><div class="shuru_price fl"><input type="text" class="btn_h30 w98" />&nbsp;件级以上： </div><div class="shuru_priceon fl"><input type="text" class="btn_h30 w98" /> &nbsp;元/件<a href="javascript:;" name="DelPrice"><img src="pic/delete_icon.png" /></a> </div></div>');
		$('[name=DelPrice]').each(function(){
			$(this).click(function(){
				$(this).parent().parent().remove();
			})
		})
		
	})
	
	
	
	
	
	
	
	
	
	
	
	
	
	//5.20 发布产品 xh
	$('[name=PcWap]').each(function(i){
		$(this).click(function(){
			$('[id^=PcNew_]').hide();
			$('[name=PcWap]').attr('class','tba_onefic');
			$(this).attr('class','tba_twofic');
			$('#PcNew_'+(i+1)).show();
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
