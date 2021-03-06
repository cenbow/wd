$(function(){
	//初始化上传控件
	uploadImg("btnUpLogo");
	uploadImg("btnUpAuto");
	
	
	//手机号 输入限制
	$("#txtPhone").bind("keyup",function(){    
		$(this).val($(this).val().replace(/\D|^0/g,''));  
	}).bind("paste",function(){  //CTR+V事件处理     
		$(this).val($(this).val().replace(/\D|^0/g,''));     
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	//提交事件
	$("#btnSubmit").bind("click",function(){
		sumbmitBrand();
	});
	//勾选品牌认证事件
	$("#ckbRead").bind("click",function(){
		if($("#ckbRead").prop("checked")){
			$("#btnSubmit").removeClass("hiu_subit");
		}else{
			$("#btnSubmit").addClass("hiu_subit");
		}
	});
	
	
	
});
//提交品牌信息
function sumbmitBrand(){
	if(!$("#ckbRead").prop("checked")){
		alert("请先阅读《品牌认证规则》");
		retrun;
	}
	
	var brandID = $("#txtBrandID").val();
	var brandName = $("#txtBrandName").val();
	var brandLogo = $("#imgBrandLogo").attr("src");
	var authorization = $("#imgBrandAuot").attr("src");
	var parentType=getCheckedValue();
	var brandStory =$("#txtBrandStory").val();
	var phone = $("#txtPhone").val();
	var linkMan =$("#txtLinkMan").val();
	var job = $("#txtJob").val();
	
	
	if(brandName ==""){
		alert("请填写品牌名称");
		$("#txtBrandName")[0].focus();
		return;
	}
	if(brandLogo==""){
		alert("请上传品牌Logo");
		return;
	}
	if(authorization==""){
		alert("请上传品牌授权书");
		return;
	}
	if(parentType==""){
		alert("请选择品牌所属分类");
		return;
	}
	if(brandStory.length<140){
		alert("品牌故事请不要少于140字");
		$("#txtBrandStory")[0].focus();
		return;
	}
	if(phone==""){
		alert("请填写联系方式");
		return;
	}
	if(linkMan ==""){
		alert("请填写联系人");
		return;
	}
	$("#btnSubmit").unbind("click");
	$.ajax({
	    url: "/brand/savebrand",
	    type: "post",
	    data: {
	    	brandId:brandID,
	    	brandName:brandName,
	    	brandLogo:brandLogo,
	    	authorization:authorization,
	    	parentType:parentType,
	    	brandStory:brandStory,
	    	phone:phone,
	    	linkMan:linkMan,
	    	job:job	    	
    	},
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Success"){
	        	location.href="/brand/brandinfo?brandID=" + data.message;	        	
	        }else{
	        	alert(data.message);
	        	$("#btnSubmit").bind("click",function(){
	        		sumbmitBrand();
	        	});
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    	$("#btnSubmit").bind("click",function(){
	    		sumbmitBrand();
	    	});
	    }
	});	
	
	
}

function getCheckedValue(){
	var parentType="";	
	$(".bel_chboc input:checked").each(function(){
		if(parentType ==""){
			parentType =$(this).val();
		}else{
			parentType +="|" +$(this).val() ;
		}
	});
	return parentType;
}

//图片上传控件初始化
function uploadImg(objID){
	$("#" + objID).uploadify({
		'buttonText' : '上传',
		'buttonClass' : 'browser',
		'dataType' : 'html',
		'removeCompleted' : false,
		'swf' : '/statics/js/uploadify/uploadify.swf',
		'debug' : false,
		'width' : '80',
		'height' : '30',
		'auto' : true,
        'multi': false,//是否多文件上传
        'queueSizeLimit': 1,//图片最大上传数量
        'timeoutuploadLimit':1,//能同时上传的文件数目
		'fileTypeExts' : '*.jpg;*.gif;*.png;*.jpeg',
		'fileTypeDesc' : '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
		'formData' : {
			'cate' : "1",
			'type' : "3"
		},
		'uploader' : 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
		'fileSizeLimit' : '512',
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
            if (response == true) {
                data = eval("("+data+")");
                if(data !=null && data.Status ==1)
                {
                	$("#" + objID).closest(".spaic2").find(".scimgs img").attr("src","http://img1.okimgs.com/"+data.ImgUrl);
                	$("#" + objID).closest(".spaic2").find(".scimgs").show();
                }
            }
		}
	});
}