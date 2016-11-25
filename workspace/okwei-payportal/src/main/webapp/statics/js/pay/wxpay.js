/**
 * 微信支付
 */

$(function(){
	
 var lun =window.setInterval(function(){
		var orderNo = $("#orderNo").html();		
		$.ajax({
		     type:'POST',
		     url: "/wxpay/QueryOrderState",
		     data:{orderNo:orderNo},
		     dataType:"text",
		     success:function(result){
		         if(result =="success")
		         {
		        	 alert("支付成功",true);
		        	 clearInterval(lun);             
		             setInterval(function(){
		                 location.href="/pay/success?orderNo="+orderNo;
		             },2000);		             
		         }
		     },
		     error:function(){

		     }
		    });
		
	}, 10000);
 
 
 
});

