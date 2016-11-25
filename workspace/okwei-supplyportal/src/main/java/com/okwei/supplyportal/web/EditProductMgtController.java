package com.okwei.supplyportal.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.Result;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.common.JsonUtil;
import com.okwei.supplyportal.bean.enums.BaseResultStateEnum;
import com.okwei.supplyportal.bean.vo.BaseResultVO;
import com.okwei.supplyportal.bean.vo.BaseSSOController;
import com.okwei.supplyportal.bean.vo.EditProductVO;
import com.okwei.supplyportal.bean.vo.LoginUser;
import com.okwei.supplyportal.bean.vo.PostAgeModelVO;
import com.okwei.supplyportal.bean.vo.ProductBatchPriceVO;
import com.okwei.supplyportal.bean.vo.ProductClassVO;
import com.okwei.supplyportal.bean.vo.ProductParamModelVO;
import com.okwei.supplyportal.bean.vo.ProductSellKeyVO;
import com.okwei.supplyportal.bean.vo.ProductStylesVO;
import com.okwei.supplyportal.bean.vo.ShopClassVO;
import com.okwei.supplyportal.service.IEditProductInfoService;
import com.okwei.supplyportal.utility.ConfigSetting;
import com.okwei.web.base.BaseController;

@Controller
@RequestMapping(value = "/Product")
public class EditProductMgtController extends BaseSSOController {

	@Autowired
	private IEditProductInfoService productService;
	
	/**
	 * 
	 * @param productID 商品ID
	 * @param operation 操作类型
	 * @param model 返回实体
	 * @return
	 */
    @RequestMapping(value = "/editProductInfo",method =
    {RequestMethod.POST,RequestMethod.GET})
	public String editProductInfo(@RequestParam(required = true,defaultValue = "0") Long productId ,
			@RequestParam(required = false,defaultValue = "0") Short operation ,
			@RequestParam(required = false,defaultValue = "0") Integer classID,
			@RequestParam(required = false,defaultValue = "0") Integer tempID,Model model)
	{
    	LoginUser user = super.getLoginUser();
    	//operation 1 正常发布 2 从商品导入 3 修改类目后返回 4 修改商品信息
    	long weiID =user.getWeiID();
    	//weiID=5128217;
    	//classID =45;
    	//productId = (long) 30464;
    	/* 	
    	operation =4;
  	
    	
    	tempID=34;*/

    	if(operation !=1 && operation !=2 && operation!=3 && operation !=4){
    		return "redirect:/publishProduct/index"; 
    	}
    	
    	//判断如果没有类目信息 参数异常
    	if(operation ==1 || operation==3)
    	{
    		if(classID<1)
    		{
    			return "redirect:/publishProduct/index"; 
    		}
    	}	
    	//判断商品号参数 
    	if(operation ==2 || operation==3 || operation==4)
    	{
    		if(productId <1)
    		{
    			return "redirect:/publishProduct/index"; 
    		}else if(!productService.getIsMyProduct(productId, weiID)){
    			//判断该商品是否为该用户的
    			return "redirect:/publishProduct/index"; 
    		}
    	}
    	
    	//如果是正常发布 和  商品导入 判断是否有发布权限
    	if(operation ==1 || operation==2)
    	{
    		BaseResultVO resultVO =productService.getPower(weiID);
    		if(resultVO.getState() ==BaseResultStateEnum.Failure)
    		{
    			return "redirect:/manage/list"; 
    		}
    	}
    	//如果有商品ID 加载商品全部数据
    	EditProductVO product =new EditProductVO();
    	if(productId >0)
    	{
    		product = productService.getProductInfo(productId );
    	}
    	//如果有类目ID 加载类目信息  否则 加载该商品原有的类目信息
    	if(classID>0 && product.getClassId() != classID)
    	{
    		product.setClassId(classID);
    	}    	 	
    	ProductClassVO pClass =productService.getClassInfo(product.getClassId());
/*    	pClass = new ProductClassVO();
		pClass.setClssName("吊炸天");
		pClass.setOneClassName("炸天");
		pClass.setTwoClassName("天");*/
    	
    	ProductParamModelVO paramModel =new ProductParamModelVO();
    	//1.判断是否有模板传递过来 并且是否和 商品模板相同 如果不同 只加载该模板
    	if(tempID>0 && product.getmID() != tempID) 
    	{
    		paramModel = productService.getParamModel(tempID);
    		product.setmID(tempID);
    	}
    	else if(product.getProductId() !=null  && product.getProductId()>0)//如果相同 或者 没有传递 则需要加载该商品 自己的模板
    	{
    		if(product.getmID() ==null){
    			product.setmID(0);
    		}
    		paramModel = productService.getProductParamModel(product.getmID(),product.getProductId());
    	}
    	
    	//加载邮费模板列表
    	List<PostAgeModelVO> postAgeList = productService.getPostAgeList(weiID);
    	
    	//加载店铺分类列表
    	List<ShopClassVO> shopClassList = productService.getShopClassList(weiID);
    	
    	List<String> sellParams= new ArrayList<String>();
    	sellParams.addAll(Arrays.asList(ConfigSetting.sellParam()));
    	if(product.getSellKeyList() !=null &&  product.getSellKeyList().size()>0){
    		for (ProductSellKeyVO item : product.getSellKeyList()) {
				if(!sellParams.contains(item.getAttributeName())){
					sellParams.add(item.getAttributeName());
				}
			}
    	}
    	
    	model.addAttribute("product", product);
    	model.addAttribute("pClass", pClass);
    	model.addAttribute("paramModel", paramModel);
    	model.addAttribute("postAgeList", postAgeList);
    	model.addAttribute("shopClassList", shopClassList);
    	model.addAttribute("sellParams", sellParams);
    	model.addAttribute("operation", operation);
    	model.addAttribute("userinfo",user);
    	
    	return "productmgt/editProductInfo";
	}
    
    
	@ResponseBody
    @RequestMapping(value = "/saveShopClass",method ={RequestMethod.POST,RequestMethod.GET})
	public String saveShopClass(@RequestParam(required = true,defaultValue = "") String scName,Model model) {
		LoginUser user = super.getLoginUser();
		long weiID = user.getWeiID();
		BaseResultVO result = new BaseResultVO();
		if(scName ==null || scName ==""){
			result.setMessage("参数异常");
			result.setState(BaseResultStateEnum.Failure);
		}else {
			result = productService.saveShopClass(scName,weiID);
		}	
				
		return JsonUtil.objectToJson(result);
	}
	
	@ResponseBody
    @RequestMapping(value = "/saveParamModel",method ={RequestMethod.POST,RequestMethod.GET})
	public String saveParamModel(@RequestParam(required = true,defaultValue = "") String josn,Model model){
		LoginUser user = super.getLoginUser();
		long weiID =user.getWeiID();
		BaseResultVO result = new BaseResultVO();
		result.setMessage("您提交的参数异常！请检查后重试！");
		result.setState(BaseResultStateEnum.Failure);
		String tempJosn = JsonUtil.objectToJson(result);
		
		ProductParamModelVO paramModel = new ProductParamModelVO();
		try {
			paramModel = (ProductParamModelVO) JsonUtil.jsonToBean(josn, ProductParamModelVO.class);	
		} catch (Exception e) {
			return tempJosn;
		}
		
		paramModel.setSupplierWeiId(weiID);	
			
		if(paramModel.getMname()==null || paramModel.getMname() ==""){
			return tempJosn;
		}
		if(paramModel.getClassId() ==null || paramModel.getClassId()  < 1){
			return tempJosn;
		}
		if(paramModel.getKeyList() ==null || paramModel.getKeyList().size() <1){
			return tempJosn;
		}		

		result = productService.saveProductParam(paramModel);
				
		return JsonUtil.objectToJson(result);
	}
	
	@ResponseBody
    @RequestMapping(value = "/saveProduct",method ={RequestMethod.POST,RequestMethod.GET})
	public String saveProduct(@RequestParam(required = true,defaultValue = "") String josn,Model model){
		
		LoginUser user = super.getLoginUser();
		long weiID =user.getWeiID();
		BaseResultVO result = new BaseResultVO();
		result.setMessage("您提交的数据异常！请检查后重试！");
		result.setState(BaseResultStateEnum.Failure);
		String tempJosn = JsonUtil.objectToJson(result);
		
		EditProductVO product  = new EditProductVO();
		try {
			product = (EditProductVO) JsonUtil.jsonToBean(josn, EditProductVO.class);
		} catch (Exception e) {
			return tempJosn;
		}
		//初始值设定
		product.setSupplyerWeiid(weiID);
		
		//是否有操作权限
		if(product.getProductId()>0){
			PProducts pModel= productService.getProductModel(product.getProductId());
			if(pModel.getSupplierWeiId() != product.getSupplyerWeiid()){
				result.setState(BaseResultStateEnum.Failure);
				result.setMessage("您没有修改该商品的权限！");
				return JsonUtil.objectToJson(result);
			}
		}else{
			result= productService.getPower(weiID);
			if(result.getState() == BaseResultStateEnum.Failure){
				JsonUtil.objectToJson(result); 
			}
		}
		
		//如果商品的类型不是正常展示 其他全部为草稿
		if(product.getState() !=Short.parseShort(ProductStatusEnum.Showing.toString())){
			product.setState(Short.parseShort(ProductStatusEnum.OutLine.toString()));
		}
		
		//都需 校验	系统分类
		if(product.getClassId() ==null || product.getClassId() <1){
			return tempJosn;
		}
		//产品标题
		if(product.getProductTitle() ==null || product.getProductTitle().trim()==""){
			return tempJosn;
		}
		//如果是发布正式 需校验
		if(product.getState() ==Short.parseShort(ProductStatusEnum.Showing.toString())){
			//主图
			if(product.getProductImg() ==null || product.getProductImg() ==""){
				return tempJosn;
			}
			//如果有款式列表
			if(product.getStyleList() !=null && product.getStyleList().size()>0){
				//规格 名称
				if(product.getSellKeyList() ==null || product.getSellKeyList().size() ==0){
					return tempJosn;
				}
				//规格值
				for (ProductSellKeyVO keyItem : product.getSellKeyList()) {
					if(keyItem.getSellValueList() ==null || keyItem.getSellValueList().size() ==0){
						return tempJosn;
					}
				}
				//款式 价格 数量 
				for (ProductStylesVO styleItem : product.getStyleList()) {
					if(styleItem.getPrice()==null || !(styleItem.getPrice()>0) || styleItem.getConmision()==null
							|| !(styleItem.getConmision()>0))
					{					
						return tempJosn;
					}else {
						//价格不能小于 佣金
						if(styleItem.getConmision()>=styleItem.getPrice()){
							return tempJosn;
						}
					}
				}
			}
			else if(product.getCount() ==null || product.getCount() <1 || product.getPreOrder()==null ||
					product.getCommission() ==null || !(product.getPrice()>0) ||
					!(product.getCommission()>0) || product.getCommission()>= product.getPrice())
			{//如果没有款式列表
				return tempJosn;
			}
			//批发价格
			if(product.getBatchPriceList() !=null && product.getBatchPriceList().size()>0){
				for (ProductBatchPriceVO bpItem : product.getBatchPriceList() ) {
					if(bpItem.getCount()<1 || !(bpItem.getPirce()>0)){
						return tempJosn;
					}
				}
			}
			//预定价格
			if(product.getPreOrder() !=null && product.getPreOrder().getPreOrderPrice() !=null){
				if(!(product.getPreOrder().getPreOrderPrice()>0)){
					return tempJosn;
				}
			}
			//pc 详情
			if(product.getPcdes()==null || product.getPcdes() ==""){
				return tempJosn;
			}
			//邮费模板
			if(product.getFreightId() ==null || !(product.getFreightId()>0)){
				return tempJosn;
			}		
		}
			
		result = productService.editProductInfo(product);
		//如果是正常发布商品成功 则重新上架该
		if(result.getState() == BaseResultStateEnum.Success){
			//商品批发价格表 如果是新增商品 或 批发价格修改后 
			List<PProductBatchPrice> pbpList = new ArrayList<PProductBatchPrice>();		
				if(product.getBatchPriceList() !=null && product.getBatchPriceList().size()>0){
					for (ProductBatchPriceVO bpriceVo : product.getBatchPriceList()) {
						PProductBatchPrice bPModel = new PProductBatchPrice();
						bPModel.setCount(bpriceVo.getCount());
						bPModel.setPirce(bpriceVo.getPirce());
						bPModel.setProductId(Long.parseLong(result.getMessage()));
						pbpList.add(bPModel);
					}										
				}
				productService.setShelveMyself(weiID, Long.parseLong(result.getMessage()), product.getSid(), pbpList);	
		}

		return JsonUtil.objectToJson(result);
	}
	
	@ResponseBody
    @RequestMapping(value = "/getParamModel",method ={RequestMethod.POST,RequestMethod.GET})
	public String getParamModel(@RequestParam(required = true,defaultValue = "0") Integer pModelID,Model model){
		if(pModelID <1){
			return "";
		}
		LoginUser user = super.getLoginUser();
		long weiID =user.getWeiID();
		return JsonUtil.objectToJson(productService.getParamModel(pModelID));
	}
}
