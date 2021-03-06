package com.okwei.myportal.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.AActSupplier;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.ActType;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.activity.AActivityProductsResult;
import com.okwei.bean.vo.activity.AActivityResult;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.myportal.service.IActivityService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.product.IShopManagerService;
import com.okwei.util.ImgDomain;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/act")
public class ActivityController extends SSOController {

	@Autowired
	IActivityService actService;
	@Autowired
	IBaseActivityService baseActService;
	@Autowired
	IShopManagerService shopService;

	/**
	 * 限时抢购 列表页
	 * 
	 * @param model
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/actlist")
	public String actlist(Model model, @RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		LoginUser user = super.getUserOrSub();
		model.addAttribute("userinfo", user);
		PageResult<AActivityResult> actResult = actService.find_Activitylist(user.getWeiID(), pageId, pageSize);
		model.addAttribute("pageRes", actResult);
		return "activity/activitylist";
	}

	@ResponseBody
	@RequestMapping("/celebration")
	public String getActCelebration(){
		ReturnModel rm=new ReturnModel();
		rm.setStatu(ReturnStatus.DataError);
		Long actId=actService.getgetActCelebration();
		rm.setBasemodle(actId);
		rm.setStatu(ReturnStatus.Success);
		return JsonUtil.objectToJson(rm);
	}
	/**
	 * 限时特价 详情页（报名产品列表）
	 * @param model
	 * @param actid
	 * @param pageId
	 * @param pageSize
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/actdetail")
	public String actdetail(Model model,@RequestParam(required = false, defaultValue = "0") long actid, @RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		LoginUser user = super.getUserOrSub();
		model.addAttribute("userinfo", user);
		AActivity activity=baseActService.getAActivity(actid);
		model.addAttribute("actModel",activity);
		PageResult<AActivityProductsResult> actProductsResult=actService.find_ApplyProductListBySellerID(actid, user.getWeiID(), pageId, pageSize);
		model.addAttribute("passCount", actService.count_actProductPass(actid, user.getWeiID())); 
		model.addAttribute("pageRes", actProductsResult);
		model.addAttribute("actid",actid);
		return "activity/actDetail";
	}
	
	/**
	 * 编辑活动详情页
	 * @param model
	 * @param pageId
	 * @param pageSize
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/editlist")
	public String editDetail(Model model,@RequestParam(required = false, defaultValue = "0") long actid, @RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		LoginUser user = super.getUserOrSub();
		model.addAttribute("userinfo", user);
		
		AActivity activity=baseActService.getAActivity(actid);
		model.addAttribute("actModel",activity);
		PageResult<AActivityProductsResult> actProductsResult=actService.find_ApplyProductListBySellerID(actid, user.getWeiID(), pageId, pageSize);
		model.addAttribute("passCount", actService.count_actProductPass(actid, user.getWeiID())); 
		model.addAttribute("pageRes", actProductsResult);
		model.addAttribute("actid",actid);
//		Date startime=activity.getStartTime();
		Date endtime=activity.getApplyEndTime();
		Date nowtime=new Date();
		if(nowtime.before(endtime)){
			return "activity/editDetail";
		}else{
			return "activity/actDetail";
		}
	}
	
	@ResponseBody
	@RequestMapping("/deleteDetail")
	public String deleteDetail(Long proActID,Long actID){
		ReturnModel result=baseActService.deleteActivityProducts(proActID, actID);
		return JsonUtil.objectToJson(result);
	}
	/**
	 * 限时抢购  产品报名
	 * @param model
	 * @param proActid
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/actProductPage")
	public String actProductPage(Model model,@RequestParam(required = false, defaultValue = "-1") long actid,@RequestParam(required = false, defaultValue = "-1") long proActid) throws MapperException {
		LoginUser user = super.getUserOrSub();
		model.addAttribute("userinfo", user);
		AActivity activity=baseActService.getAActivity(actid);
		model.addAttribute("actModel",activity);
		model.addAttribute("proActID", proActid);
		model.addAttribute("actid", actid);
		String keyName="actproClass_uid_"+user.getWeiID();
		List<PShopClass> cList=(List<PShopClass>)RedisUtil.getObject(keyName);
		if(cList==null||cList.size()<=0){
			cList=shopService.find_shopClasslist(user.getWeiID(), true, Short.parseShort(ProductStatusEnum.Showing.toString()));
			if(cList!=null&&cList.size()<=0){
				RedisUtil.setObject(keyName, cList, 1800);
			}
		}
		model.addAttribute("classlist",cList);
		if(proActid!=-1){
			AActivityProductsResult proMod= baseActService.getAActivityProductsResult(proActid);
			if(proMod!=null&&proMod.getSellerId().longValue()==user.getWeiID().longValue()){
				proMod.setProductImg(ImgDomain.GetFullImgUrl(proMod.getProductImg()));
				model.addAttribute("proModel",proMod);
			}
		}
		if(activity!=null&&activity.getType()!=null&&activity.getType().intValue()==Integer.parseInt(ActType.quanFan.toString())){
			AActSupplier supplier= baseActService.getAActSupplier(actid, user.getWeiID());
			model.addAttribute("amount",supplier==null?"":supplier.getAmount());
			List<AActivityProducts> prolist= baseActService.find_AActivityProductsBySellerID(actid, user.getWeiID(), null);
			double priceTotal=0d;
			if(prolist!=null&&prolist.size()>0){
				for (AActivityProducts aa : prolist) {
					if(proActid<=0||proActid!=aa.getProActId().longValue()){
						priceTotal += aa.getPrice()*aa.getTotalCount();
					}
				}
			}
			model.addAttribute("amountTotal",priceTotal);
			return "activity/upProducts828";
		}else {
			return "activity/upProducts";
		}
	}
	/**
	 * 限时抢购 保存报名产品
	 * @param productsArr
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/editpro")
	public String editpro(String productsArr,@RequestParam(required = false, defaultValue = "0") double amount) throws MapperException {
		LoginUser user = super.getUserOrSub();
		
		if(user!=null && (user.getYunS()==1||user.getBatchS()==1||user.getPth()==1||user.getPph()==1)){//
			List<AActivityProducts> list= (List<AActivityProducts>)JsonUtil.json2Objectlist(productsArr, AActivityProducts.class);
			ReturnModel rModel= baseActService.editAActivityProducts(user.getWeiID(),amount, list);//baseActService.editAActivityProducts(user.getWeiID(),list); 
			return JsonUtil.objectToJson(rModel);
		}else {
			ReturnModel rq=new ReturnModel();
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("您不是供应商，没有报名权限！");
			return JsonUtil.objectToJson(rq);
		}
		
		
	}
	/**
	 * 限时抢购 获取供应商自营产品列表
	 * @param classId
	 * @param index
	 * @param size
	 * @param title
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/prolist")
	public String prolist(@RequestParam(required = false, defaultValue = "-1") int classId,
			@RequestParam(required = false, defaultValue = "-1") long actid,
			@RequestParam(required = false, defaultValue = "1") int index,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "-1") int count,
			String title) throws MapperException {
		LoginUser user = super.getUserOrSub();
		String keyName="actProlist_i_"+index+"_s_"+size+"_cid_"+classId+"_uid_"+user.getWeiID()+"_name_"+title;
		PageResult<PProducts> listProducts=(PageResult<PProducts>)RedisUtil.getObject(keyName);
		if(listProducts==null||listProducts.getList()==null||listProducts.getList().size()<=0){
			listProducts=shopService.find_productlist(user.getWeiID(),classId,actid, title,index,size); 
			if(listProducts!=null&&listProducts.getList()!=null&&listProducts.getList().size()>0){
				for (PProducts pp : listProducts.getList()) {
					pp.setDefaultImg(ImgDomain.GetFullImgUrl(pp.getDefaultImg()));
				}
				RedisUtil.setObject(keyName, listProducts, 1800);
			}
		}
		if(count<0){
			List<AActivityProducts> proList = baseActService.find_AActivityProductsBySellerID(actid, user.getWeiID(), null);
			if(listProducts!=null&&listProducts.getList()!=null&&listProducts.getList().size()>0){
				 for (PProducts pp : listProducts.getList()) {
					pp.setMid(0); 
					if(pp.getOriginalPrice()==null)
						pp.setOriginalPrice(pp.getDefaultPrice()); 
					for (AActivityProducts actPro : proList) {
						if(actPro.getProductId().longValue()==pp.getProductId().longValue()){
							pp.setMid(1); 
						}
					}
				}
			}
		}
		ReturnModel rq=new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setBasemodle(listProducts); 
		rq.setStatusreson("成功"+listProducts.getTotalCount());
		return JsonUtil.objectToJson(rq);
	}
}
