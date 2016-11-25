/**
 * 版本 1.0
 * 标语 太操蛋了
 * 
 */
var mypagePath =LoadMyPage();
//加载分页 数据准备
function LoadMyPage(){
	//加载CSS
	
	//图片路径
   var scripts = document.getElementsByTagName('script');//获所有script标签
   var script = scripts[scripts.length - 1];//获取当前加载到的script标签
   //如果是ID，src=document.getElementById('script的Id').src
   var mypagePath = script.src;//获取当前加载到的script标签的src属性
   mypagePath =mypagePath.substring(0,mypagePath.lastIndexOf("/")+1);
   return mypagePath;
}
//构造函数
var MyPage = function(taotalSize, settings) {
				
	this.taotalSize=taotalSize;
    this.defaults = {
    		parentID:"pageParentDiv",
    		pageSize:10,	//每页显示数量
    		pageFooter:7,	//显示页脚数量   		
    		taotalPageCount:0,	//数据总页数
    		pageIndex:1,	//展示第几页
    		callBack:undefined, //回掉函数
    		showGoPageBtn:true,//是否展示确定跳转按钮
    		showPrevMore:false,//是否展示前面...
    		showNextMore:true,//是否展示后面的...
    		initCallBack:false,//初始化是否回调    	
    		pagePath:mypagePath
    };

	this.settings =  $.extend({}, this.defaults, settings);//初始化设置	
	this.settings.taotalPageCount =  Math.ceil(taotalSize/this.settings.pageSize);//计算数据总页数
	if((this.settings.pageFooter % 2) ==0 ){//页脚只能是单数
		this.settings.pageFooter = this.settings.pageFooter -1;
	}
	
	this.parentDiv = $("#"+this.settings.parentID);
	if(this.parentDiv.length <1){
		alert("未能找到parentDiv");
		return;
	}	
	this.parentDiv.html("");
	if(taotalSize >0){
		this.init();
	}	
}

//定义MyPage的方法
MyPage.prototype = {
		init: function() {	
			var _this = this;	

			var html ="<div class='ndfxs_1-2_fenye'>";
			if(_this.settings.showGoPageBtn){
				html +="<a name='btnGoPage' class='ndfxs_1-2_fenye_qd mt_7' href='javascript:void(0);'>确定</a>";
				html +="<span class='ndfxs_1-2_fenye_span'>页</span>";
				html +="<div class='stepper '>";
				html +="<input name='txtPageIndex' type='number' class='ndfxs_1-2_fenye_text mt_7 stepper-input' step='1' max='9999' min='1'>";
				html +="<span class='stepper-arrow up'></span><span class='stepper-arrow down'></span></div>";
				html +="<span class='ndfxs_1-2_fenye_span'>，到第</span>";
				
			}
			html +="<span class='ndfxs_1-2_fenye_span'>共<font>"+_this.settings.taotalPageCount +"</font>页</span> ";
			html +=	"<a name='btnGoNext' class='ndfxs_1-2_fenye_no ml_5' href='javascript:void(0);'>";
			html +="	<img src='"+_this.settings.pagePath+"image/jiantou_3_19.jpg'>" +
					"</a>";
			
			html +="<div name='pageBtnDiv'>";
			
	        html +="</div>";  
	        html +="<a  name='btnGoPrev' class='ndfxs_1-2_fenye_no mr_5' href='javascript:void(0);'>" +
	        			"<img src='"+_this.settings.pagePath+"image/jiantou_l_3_19.jpg'>" +
					"</a>";
	        html +="</div>";
	        	        
	        _this.parentDiv.html(html);	    	
	        _this.goPage(_this.settings.pageIndex,_this.settings.initCallBack);
	        
	        _this.parentDiv.find("a[name='btnGoPrev']").bind("click",function(){
	        	_this.goPage(_this.settings.pageIndex - 1,true);
	        });
	        
	        _this.parentDiv.find("a[name='btnGoNext']").bind("click",function(){
	        	_this.goPage(_this.settings.pageIndex + 1,true);
	        });
	        
	        _this.parentDiv.find("a[name='btnGoPage']").bind("click",function(){
	        	_this.goPage( _this.parentDiv.find("input[name='txtPageIndex']").val(),true);
	        });
	        
	        _this.parentDiv.find("input[name='txtPageIndex']").bind("keyup",function(){    
	    		$(this).val($(this).val().replace(/\D|^0/g,''));  
	    	}).bind("paste",function(){  //CTR+V事件处理     
	    		$(this).val($(this).val().replace(/\D|^0/g,''));     
	    	}).bind("blur",function(){
	    		var value = $(this).val();
	    		if(value > _this.settings.taotalPageCount){
	    			$(this).val(_this.settings.taotalPageCount);
	    		}
	    	}).css("ime-mode", "disabled"); //CSS设置输入法不可用  
	        	        
    },
    goPage:function(pageIndex,isCallBack){
    	var _this = this;
    	pageIndex = parseInt(pageIndex);
    	//最多只能是最后一页
    	if(pageIndex > this.settings.taotalPageCount){
    		pageIndex = this.settings.taotalPageCount;
    	}else if(pageIndex <1 ){
    		pageIndex =1;
    	} 
    	    	
    	if(isCallBack && $.isFunction(_this.settings.callBack)){
    		_this.settings.callBack && _this.settings.callBack(pageIndex);
    	}
   	
    	this.settings.pageIndex  = pageIndex;   
    	var tempFooter=(this.settings.pageFooter-1)/2;//当前页标 前后页码数量    	
    	   
    	var html="";
    	var startIndex =this.settings.pageFooter;
    	var endIndex=1;
    	   	  	
    	//如果页码 大于 页脚数量的一半 
    	if(pageIndex > tempFooter){
    		startIndex = pageIndex +tempFooter;
    		endIndex = pageIndex - tempFooter;
    	}
    	//如果页码 大于 总页数 减去页脚数量
    	if(pageIndex > (_this.settings.taotalPageCount - tempFooter)){
    		startIndex = _this.settings.taotalPageCount;
    		endIndex =_this.settings.taotalPageCount - (this.settings.pageFooter -1);
    	}
    	
    	//页标限制
    	if(startIndex > _this.settings.taotalPageCount){
    		startIndex = _this.settings.taotalPageCount;
    	}
    	if(endIndex <1){
    		endIndex =1;
    	}
    	
    	var pageBtnDiv =_this.parentDiv.find("[name='pageBtnDiv']");
    	var moreHtml ="<span name='more' class='ndfxs_1-2_fenye_span'>" +
    			"<img src='"+_this.settings.pagePath+"MyPage/image/slh_1_19.jpg'></span>";
    	//是否显示 ...
    	if(_this.settings.showNextMore){   		   	
	    	if(startIndex < _this.settings.taotalPageCount){
	    		if(pageBtnDiv.prevAll("span[name='more']").length <1){
	    			pageBtnDiv.before(moreHtml);
	    		}    		
	    	}
	    	else{
	    		pageBtnDiv.prevAll("span[name='more']").remove();
	    	}
    	}
    	if(_this.settings.showPrevMore){   
	    	if(endIndex >1){
	    		if(pageBtnDiv.nextAll("span[name='more']").length <1){
	    			pageBtnDiv.after(moreHtml);
	    		}
	    	}else{
	    		pageBtnDiv.nextAll("span[name='more']").remove();
	    	}
    	}
    	//页码html   	
    	for(i =startIndex;i>=endIndex;i-- ){
			if(i != _this.settings.pageIndex){
				 html +="<a name='otherPage' class='ndfxs_1-2_fenye_no' href='javascript:void(0);'>"+i+"</a>";
			}else{
				 html +="<a name='nowPage' class='ndfxs_1-2_fenye_yes' href='javascript:void(0);'>"+i+"</a>";
			}
    	}
    	  	
    	pageBtnDiv.html(html);
    	pageBtnDiv.find("a[name='otherPage']").bind("click",function(){
    		_this.goPage($(this).html(),true);
    	});
    }
}


