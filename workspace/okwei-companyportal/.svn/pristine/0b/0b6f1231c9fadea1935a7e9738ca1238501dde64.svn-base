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
	
})