/**
 * 
 */
$(function(){
	//$("#txtState").val("");
	//分页
   	var page = new Pagination( {
		formId: "searcherForm",
		isAjax : true,
		targetId : "navTab",
		submitId:"submitBtn",
		validateFn:function(){
			return true;
		}
	});
   	page.init();
   	
   	InitCity();
   	
});	
/*========城市选择下拉（地址管理）===================*/
function InitCity() {
	//初始化省市区列表
	var province = $("#hidprovince").val();
	var city = $("#hidcity").val();
	var area = $("#hiddistrict").val();
	var dis = new district();
	dis.init('#province', '#city', '#district');
	dis.bdbycode(province, city, area);
}

