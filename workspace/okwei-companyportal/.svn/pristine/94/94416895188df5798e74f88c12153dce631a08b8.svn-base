/**
 * 
 */
$(function() {
	
	$(".mzh_ymzs_tjsy").click(function(){
		if(CheckData()){
			//提交用户资料
			$.ajax({
				url : "/free/saveTasteApply",
				type : "post",
				data : $("#tasteApply_form").serialize(),
				success : function(data) {			
					//alert(JSON.stringify(data));
					if (data.Statu=="Success") {
//					 	var mzh_bgtm = $(document).height();
//				        $(".mzh_bgtm").height(mzh_bgtm).show();
//				        bWidth = 640;//弹出层的宽度
//				        bHeight= 410;//弹出层的高度
//				        aOffsetLeft = $(document).width()/2 - (parseInt(bWidth/2));
//				        aOffsetTop = (window.document.documentElement.clientHeight/2) - (bHeight=="auto"?150:parseInt(bHeight/2)) +  $(document).scrollTop()/2;
//				        $(".mzh_zcz_tc").show();
//				        $(".mzh_zcz_tc").css({display:"block",left:aOffsetLeft+"px",top:aOffsetTop+"px",width:bWidth,height:bHeight});
//				        var a=$(window).height();
//				        
//				        
//				        $(':input','#tasteApply_form')
//				        .not(':button, :submit, :reset, :hidden')
//				        .val('')
//				        .removeAttr('checked')
//				        .removeAttr('selected');
						alert('提交申请成功！');
						window.location.href='/free/index';
				        
				        
					}else if (data.Statu == "LoginError"||data.Statu == "DataError") {
						alert(data.StatusReson);
						
					}
				},
				error : function() {
					alert("数据请求失败！请稍后重试！");
				}
			});
       
		}
    })

    $("[name=mzh_close]").click(function(){
        $(".mzh_bgtm").hide();
        $(".mzh_zcz_tc").hide();
    })

    $("#mzh_sfsc_yes").click(function(){
        $("[name=mzh_sfsc_as]").hide();
    })
    $("#mzh_sfsc_no").click(function(){
        $("[name=mzh_sfsc_as]").show();
    })
    
	//初始化省市区列表
	InitCity();
	// 上传图片
	$('#uploadImg').uploadify({
		'buttonText' : '选择图片',
		'buttonClass' : 'mzh_ymzs_sctp',
		'dataType' : 'html',
		'removeCompleted' : false,
		'swf' : '/statics/js/uploadify/uploadify.swf',
		'debug' : false,
		'width' : '100',
		'height' : '34',
		'auto' : true,
		'multi' : false,
		'queueSizeLimit' : 3,//图片最大上传数量
		'timeoutuploadLimit' : 5,//能同时上传的文件数目
		'fileTypeExts' : '*.jpg;*.gif;*.png;*.jpeg',
		'fileTypeDesc' : '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
		'formData':{FileUpload:"proimage",cate:1,type:1},//参数
		'uploader' : 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
		'fileSizeLimit' : '1024',
		'progressData' : 'speed',
		'removeCompleted' : true,
		'removeTimeout' : 0,
		'requestErrors' : true,
		'onFallback' : function() {
			alert("您的浏览器没有安装Flash");
		},
		'onUploadStart' : function(file) {
			$(".uploadify-queue").css("height", "50px");
		},
		'onUploadSuccess' : function(file, data, response) {
			var json = JSON.parse(data);
			if (json.Status != 1) {
				alert(json.StatusReason);
			} else {
				$.ajax({
					url : "/free/fullImgUrl",
					type : "get",
					async : false,
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : 'img=' + json.ImgUrl,
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("服务器出现异常");
					},
					success : function(data) {
						//alert(JSON.stringify(data));
						var html='<div class="own_img">';
						html +=' <div class="img_dele" ><img src="/statics/images/delete_imgs.png" class="img_delet"></div>'; 
						html += " <img src='" +data.msg + "'  width='121' height='121' class='fl mr_20'><input type='hidden' name='tkimages' value='" + data.msg + "'/>";
						html +=' </div>';  
						
						$("#divImage").append(html);
						//删除图片
						$(".img_dele").on("click",function(){
							$(this).closest(".own_img").remove();
							
						});
					},
					
				});
			}
		},
        'onDialogClose': function (swfuploadifyQueue) {
        	var len = $(".own_img").length;
            if (len>2) {
                alert("最多只能选择"+len+"张图片!");
                upImg.uploadify('cancel', '*')
                return;               
            }
        }
	});
	
	
});

/*========城市选择下拉（地址管理）===================*/
function InitCity() {
	//初始化省市区列表
	var province = $("#selProvince option:selected").text();
	var city = $("#selCity option:selected").text();
	var area = $("#selDistrict option:selected").text();
	var dis = new district();
	dis.init('#selProvince', '#selCity', '#selDistrict');
	dis.bind(province, city, area);
}

function CheckData() {
	if ($("#name").val() == "") {
		alert("姓名不能为空！");
		$("#name").focus();
		return false;
	}
	if ($("#phone").val() == "") {
		alert("联系电话不能为空！");
		$("#phone").focus();
		return false;
	}
	
	if ($("#email").val() == "") {
		alert("邮箱不能为空！");
		$("#email").focus();
		return false;
	}
	
	if ($("#highNum").val() == "") {
		alert("请输入高压数值！");
		$("#highNum").focus();
		return false;
	}
	if ($("#lowNum").val() == "") {
		alert("请输入低压数值！");
		$("#lowNum").focus();
		return false;
	}
	if ($("#selProvince").val()== "0") {
		alert("请选择省");
		return false;
	}
	if ($("#selCity").val() == "0") {
		alert("请选择市");
		return false;
	}
	if ($("#selDistrict") == "0") {
		alert("请选择区");
		return false;
	}
	if ($("#detailAddress").val() == "") {
		alert("请详细填写，方便快递小哥找到您！");
		$("#detailAddress").focus();
		return false;
	}
	if(JudgeAmount($("#highNum").val()) == false){
		alert("请输入高压数值！");
		$("#highNum").focus();
		return false;
	}
	if(JudgeAmount($("#lowNum").val()) == false){
		alert("请输入低压数值！");
		$("#lowNum").focus();
		return false;
	}
	if($("#xueTangNum").val()!=""&&JudgeAmount($("#xueTangNum").val()) == false){
		alert("请输入数值！");
		$("#xueTangNum").focus();
		return false;
	}
	if($("#xueZhiNum").val()!=""&&JudgeAmount($("#xueZhiNum").val()) == false){
		alert("请输入数值！");
		$("#xueZhiNum").focus();
		return false;
	}
	
	return true;
}

/**
 * 验证金额
 */
function JudgeAmount(amount)
{
   var regx;
   if(amount == null || amount == "")
		return false;
	regx = /^[0-9]*(\.[0-9]{1,2})?$/;
	return regx.test(amount);
}
