$(document).ready(function(){
	
	/*面包屑、左菜单切换到"我的钱包"*/
	$("a").click(function(){
		if($(this).hasClass('skip')){
			var html ='当前位置：<a href="/walletMgt/index">钱包管理</a>><span>我的钱包</span>';
			$("#breadcrumb").empty().append(html);
			$("#leftMenu li.now").removeClass("now");
			$("#leftMenu li:first").addClass("now")
		}
	});
	
	getUserWallt(false);
	CheckWallt();
	
	/* 选择分类弹出框 */
	$("#txBtn").click(function(){		
		CheckWallt(function(){
			getUserWallt(true);	
		});		
	});
	
	
	//批发价 价格文本框限制
	$("#txtTxAmout").bind("keyup",function(){   
		$(this).val($(this).val().replace(/[^0-9.]/g,''));    
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/[^0-9.]/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	//提现金额限制
	$("#txtTxAmout").bind("blur",function(){
		var maxAmout = $("#txtMaxTxAmout").val();
		if(isNaN(maxAmout)){
			maxAmout =0;
		}else{
			maxAmout = parseFloat(maxAmout);
		}
		var txAmout =$(this).val();
		if(isNaN(txAmout)){
			$(this).val(0);
		}else{
			txAmout = parseFloat(txAmout);
		}
		
		if(txAmout>maxAmout){
			$("#tipTxAmout").html("您最多可提现"+maxAmout+"元");
			$("#tipTxAmout").show();
		}else if($("#txtIsFirstTx").val() =="true"){
			//计算手续费
			$("#tipTxAmout").html("（需要手续费：0元）");	
			$("#tipTxAmout").show();
		}else{
			var free = txAmout * 0.01;
			if(free<2){
				free =2;
			}
			$("#tipTxAmout").html("（需要手续费："+free+"元）");	
			$("#tipTxAmout").show();
		}
	});
	
	//获取短信验证码
	$("#btnGetCode").bind("click",function(){
		var result = checkSubmit();
		if(!result){
			return;
		}

		getMobileCode();
	});
	
});

function checkSubmit(){
	var amount = $("#txtTxAmout").val();
	if(amount ==""){
		alert("请填写提现金额！");
		$("#txtTxAmout")[0].focus();
		return false;
	}
	
	if(isNaN(amount) || amount<=0){
		alert("提现金额输入入错误！");
		$("#txtTxAmout")[0].focus();
		return false;
	}
	var maxAmout = $("#txtMaxTxAmout").val();
	amount = parseFloat(amount);
	maxAmout = parseFloat(maxAmout);
	if(amount>maxAmout){
		alert("您最多可提现"+maxAmout+"元");
		return false;
	}
	
	if($("#txtIsFirstTx").val() =="false"){
		//计算手续费
		var free = amount * 0.01;
		if(free<2){
			free =2;
		}
		if(amount <= free){
			alert("提现金额不能小于手续费");
			return false;
		}
	}
	
	var cardID = $(".radio_bank input[name='bankCard']:checked").val();
	if(cardID ==null || cardID <1){
		alert("请选择提现的银行卡！");
		return false;
	}
	
	return true;
}

function getMobileCode(){
	$("#btnGetCode").unbind("click");
	$.ajax({
	    url: "/walletMg/getMobileCode",
	    type: "post",
	    data: { },
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Success"){
	        	alert("验证码已发送,请注意查收！",true);
	        	var num =59;
	        	var tiemOut = setInterval(function(){
	        		$("#btnGetCode").html(num + "秒后重新发送");		
	        		if(num <=0){
	        			window.clearInterval(tiemOut);
	                	$("#btnGetCode").bind("click",function(){
	                		getMobileCode();
	                	});
	                	$("#btnGetCode").html("点击获取验证码");
	        		}	
	        		num --;
	        	},1000);
	        }else{
	        	alert(data.Message);
	        	$("#btnGetCode").bind("click",function(){
	        		getMobileCode();
	        	});
	        }
	    },
	    error: function () {
	        alert("短信发送失败！请稍后重试！");
	        $("#btnGetCode").bind("click",function(){
	    		getMobileCode();
	    	});
	    }
	});	
}

//获取用户钱包数据
function getUserWallt(isShowWin){
	$.ajax({
	    url: "/walletMg/getWalletInfo",
	    type: "post",
	    data: { },
	    dataType : 'json',
	    success: function (data) {
	    	if(data.state =="Success"){
	    		var model = data.obj;
	    		$("#txtMaxTxAmout").val(model.balance);
	    		$("#txtMobile").html(model.mobile);
	    		$("#txtIsFirstTx").val(model.isFirst);
	    		$("#balanceAmount").html(model.balance);
	    		$("#accountIngAmount").html(model.accountIng);
	    		$("#accountNotAmount").html(model.accountNot);
	    		if(model.bond>0 || model.isTuizhu){
	    			$("#bondAmout").html(model.bond);
	    			$("#divbond").show();
	    		}
	    		    		
	    		$(".moile_two .radio_bank").remove();
	    		if(model.bankCardVOs.length>0){
	    			var html="";
	    			$.each(model.bankCardVOs,function(index,item){
	    				html +='<div class="radio_bank">';
	    				html +='	<input name="bankCard" value="'+item.cardID+'" type="radio" id="radis'+item.cardID+'">&nbsp;';
	    				html +='	<label for="radis'+item.cardID+'">';
	    				//html +='		<img width="14" height="14" src="/statics/pic/bank_icon_001.png">';
	    				html +=			item.bankName + '  尾号：<span>'+item.cardNo+'</span>';
	    				html +='	</label>';
	    				html +='	<span class="ml_30 ft_c9">限额49999 元/次</span>';
	    				html +='</div>';
	    			});	         	    	        
	    	        $(".moile_two .ahre_nes").before(html);
	    		}    		
	    			    		
	    		if(isShowWin){
	    			var heigth = $(".updata_tixian").height() + 160;
	    			ShowTxWin('提现','win_div_1','514px',heigth);
	    		}
	    		
			}else{
				alert("数据获取失败！请稍后重试！");
			}
	    },
	    error: function () {
	        alert("数据获取失败！请稍后重试！");
	    }
	});	
	
}

//检验用户是否可以使用微钱包
function CheckWallt(callBack){	
	$.ajax({
	    url: "/walletMg/checkWallet",
	    type: "post",
	    data: { },
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Failure"){
	        	ShowBind(data.message);
	        }else if(callBack !=null){
	        	callBack && callBack();
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    }
	});	
}

function ShowBind(type){
	var msg ="";
	var title="";
	var btn1txt ="";
	var btn2txt="";
	var url =$("#txtSetUrl").val();
	
	if(type==2){
		msg ="微钱包已更新升级,绑定手机后方可进行安全购物及提现!";
		title = "绑定手机";
		btn1txt ="马上绑定";
		btn2txt ="暂不绑定";
		url +="/userInfo/settings?set=phone";
	}else if(type==3){
		msg ="微钱包已更新升级,请设置支付密码,保障钱包安全!";
		title ="设置支付密码";
		btn1txt ="马上设置";
		btn2txt ="暂不设置";
		url +="/userInfo/settings?set=paylogin";
	}else if(type==1) {
		msg ="微钱包已更新升级，绑定手机后并设置支付密码后方可进行安全购物及提现!";
		title = "绑定手机";
		btn1txt ="马上绑定";
		btn2txt ="暂不绑定";
		url +="/userInfo/settings?set=phone";
	}else{
		msg ="微钱包已更新升级，实名认证后方可进行安全购物及提现!";
		title = "实名认证";
		btn1txt ="马上认证";
		btn2txt ="暂不认证";
		url +="/userInfo/settings?set=name";
	}
	
	var layerIndex = $.layer({
		title:title,
		shade: [0.2, '#000'],
	    area: ['auto','auto'],
	    border: [0],
	    dialog: {
	        msg: msg,
	        btns: 2,                    
	        type: 4,
	        btn: [btn1txt,btn2txt],
	        yes: function(){
	        	location.href=url;
	        }
	    }
	});	
}

function ShowTxWin(title,win_id,width,height){
	var pagei = $.layer({
	   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	   btns: 2,
	   btn: ['确定','取消'],
	   yes: function(){	
		   SubmitTx(pagei);
	    },
	   title: title,
	   border: [0],
	   closeBtn: [0],
	   closeBtn: [0, true],
	   shadeClose: true,
	   area: [width,height],
	   page: {dom : '#'+ win_id}
   });
}

function SubmitTx(index){
	if(!checkSubmit()){
		return;
	}
	var checkCode = $("#txtCheckCode").val();
	if(checkCode ==""){
		alert("请输入验证码！");
		return;
	}/*else if(checkCode.length !=6){
		alert("请输入正确的验证码！");
		return;
	}*/
	var amount = $("#txtTxAmout").val();
	var cardID = $(".radio_bank input[name='bankCard']:checked").val();
	$.ajax({
	    url: "/walletMg/submitTx",
	    type: "post",
	    data: {amount:amount,cardID:cardID,checkCode:checkCode},
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Success"){
	        	layer.close(index);
	        	getUserWallt(false);
	        	$("#txtTxAmout").val("");
	        	$("#txtCheckCode").val("");
	        	layer.alert("提现申请已提交,我们会尽快为您处理！" +
	        			"</br> 预计到账时间 ： 3-5 个工作日 " +
	        			"</br> 流水号 ："+data.message,1);
	        	
	        }else{
	        	alert(data.message);
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    }
	});	
	
}

function alert(msg,bool){
	if(bool){
		layer.msg(msg, 1, 1);//绿色的钩钩
	}else{
		layer.msg(msg, 1, 8);//不高兴的脸
	}	
}