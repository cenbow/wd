$(function(){
	window.onload = function(){
		var top = ($(window).height()-$('.pos_rabls').height())/2;
		var nisTop = ($('.pos_rabls').height()-86)/2;
		$('.pos_rabls').css('top',top+'px');
		$('.clicimgs').css('top',nisTop+'px')
	} 
	$('#TanLeft').each(function(){
		$('#TanLeft').click(function(){
			if($(this).attr('icon')==0){
				$(this).attr('icon','1');
				$(this).find('img').attr('src','images/ing_sjup.png')
				$('.pos_rabls').animate({right:'0px'});
				$('.fixbghui').show();
			}else{
				$(this).attr('icon','0');
				$(this).find('img').attr('src','images/ing_sjdown.png')
				$('.pos_rabls').animate({right:'-350px'});
				$('.fixbghui').hide();
			} 
		}) 
		$('#ImgClose').click(function(){
			$('#TanLeft').attr('icon','0');
			$('#TanLeft').find('img').attr('src','images/ing_sjdown.png')
			$('.pos_rabls').animate({right:'-350px'});
			$('.fixbghui').hide();
		})
		$('.fixbghui').click(function(){
			$('#TanLeft').attr('icon','0');
			$('#TanLeft').find('img').attr('src','images/ing_sjdown.png')
			$('.pos_rabls').animate({right:'-350px'});
			$(this).hide(); 
		})
	
	})


    //二级地区-鼠标放上
    $("ul.address_ul>li").mouseenter(function(){
        $("ul.address_ul>li").find("i").hide();
        $(this).find("i").show();
        $("ul.address_ul>li").find("div").hide();
        $(this).find("div").show();
    })
    //二级地区-鼠标离开
    $("ul.address_ul>li").mouseleave(function(){
        $(this).find("i").hide();
        $("[class=w600]").hide();
    })

    //二维码-鼠标放上
    $(".erweima_8_18").mouseenter(function(){
        $(this).find("div.div-posabues").show();
    })
    //二维码-鼠标离开
    $(".erweima_8_18").mouseleave(function(){
        $(this).find("div.div-posabues").hide();
    })
})