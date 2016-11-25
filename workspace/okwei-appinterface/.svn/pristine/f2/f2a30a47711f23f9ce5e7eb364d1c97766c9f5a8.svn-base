package com.okwei.appinterface.web.share;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.service.share.IShareSvervice;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.enums.MainDataUserType;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.share.ShareProduct;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.share.IShareAddService;
import com.okwei.web.base.SSOController;

@RestController
@RequestMapping("/addShare")
public class ShareAddController extends SSOController {

	@Autowired
	private IShareAddService shareService;
	@Autowired
	private IShareSvervice iShareService;

	/**
	 * 获取上架的产品列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param classId
	 * @param excludeProducts
	 * @return
	 */
	@RequestMapping("/getShopProductByClass")
	public String getShopProductByClass(
			@RequestParam(required = false, defaultValue = "1") int pageIndex,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int classId,
			@RequestParam(required = false, defaultValue = "") String excludeProducts) {
		ReturnModel result = new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if (user == null) {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		Long[] sids = null;
		try {
			if (!"".equals(excludeProducts)) {
				String[] strs = excludeProducts.split(",");
				sids = new Long[strs.length];
				for (int i = 0; i < strs.length; i++) {
					sids[i] = Long.parseLong(strs[i]);
				}
			}
		} catch (Exception e) {

		}
		PageResult<ShareProduct> page = shareService.getShareProListForApp(
				user.getWeiID(), "", (short) 0, sids,
				Limit.buildLimit(pageIndex, pageSize), classId);
		if (page != null) {
			PageResult<ProductInfo> pageResult = new PageResult<ProductInfo>();
			pageResult.setPageCount(page.getPageCount());
			pageResult.setPageIndex(page.getPageIndex());
			pageResult.setPageSize(page.getPageSize());
			pageResult.setTotalCount(page.getTotalCount());
			pageResult.setTotalPage(page.getTotalPage());
			List<ShareProduct> list = page.getList();
			if (list != null && list.size() > 0) {
				List<ProductInfo> proList = new ArrayList<ProductInfo>();
				for (ShareProduct pro : list) {
					ProductInfo temp = new ProductInfo();
					temp.setProductId(pro.getProID() == null ? 0 : pro
							.getProID().longValue());
					temp.setProductName(pro.getProTitle());
					temp.setProductPicture(pro.getProImage());
					temp.setRetailPrice(pro.getPrice() == null ? 0.0 : pro
							.getPrice().doubleValue());
					temp.setCommission(pro.getCommission() == null ? 0.0 : pro
							.getCommission().doubleValue());
					temp.setDisplayPrice(pro.getYprice() == null ? 0.0 : pro
							.getYprice().doubleValue());
					temp.setShelveId(pro.getID() == null ? 0 : pro.getID()
							.longValue());
					temp.setSellerWid(user.getWeiID());
					temp.setType(pro.getType().intValue());
					proList.add(temp);
				}
				pageResult.setList(proList);
			}
			result.setBasemodle(pageResult);
		}
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		return JsonStr(result);
	}

	/**
	 * 添加或者编辑分享
	 * 
	 * @param shareId
	 * @param title
	 * @param des
	 * @param proList
	 * @return
	 */
	@RequestMapping("/addorEdit")
	public String addShare(String pageModel) {
		LoginUser user = super.getUserOrSub();
		ReturnModel result = new ReturnModel();
		result.setStatu(ReturnStatus.DataError);
		if (user == null) {
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		String pageTitle = "";
		String pageDescription = "";
		Long pageId = -1L;
		JSONObject obj;
		obj = JSONObject.fromObject(pageModel);
		if (obj != null) {
			if (obj.containsKey("pageId"))
				pageId = obj.getLong("pageId");
			pageTitle = obj.getString("pageTitle");
			pageDescription = obj.getString("pageDescription");
		} else {
			result.setStatusreson("参数传入有误！");
			return JsonStr(result);
		}
		ReturnModel rm=new ReturnModel();
		rm=iShareService.addOrEdit(pageId);
		if(rm.getStatu()==ReturnStatus.DataError){
			return JsonStr(rm);
		}
		if (pageTitle == null || "".equals(pageTitle)) {
			result.setStatusreson("分享标题不能为空！");
			return JsonStr(result);
		}
		if (pageDescription == null || "".equals(pageDescription)) {
			result.setStatusreson("分享推广语不能为空！");
			return JsonStr(result);
		}

		List<SProducts> list = new ArrayList<SProducts>();

		JSONArray array = obj.getJSONArray("productList");

		if (array != null && array.size() > 0) {
			for (Object pro : array) {
				JSONObject tempobj = JSONObject.fromObject(pro.toString());
				if (tempobj == null)
					continue;
				SProducts temp = new SProducts();
				temp.setShelveId(tempobj.getLong("shelveId"));
				temp.setProductId(tempobj.getLong("productId"));
				list.add(temp);
			}
		}

		if (list == null || list.size() <= 0) {
			result.setStatusreson("请选择要分享的产品！");
			return JsonStr(result);
		} 
		else {
			if (list.size() < 5 || list.size() > 100) {
				result.setStatusreson("分享的产品数量需在5到100个之间！");
				return JsonStr(result);
			}
		}
		MainDataUserType type = MainDataUserType.user;
		if (shareService.getIsSupper(user.getIdentity()))
			type = MainDataUserType.supplier;
		result = shareService.addShare(user.getWeiID(), pageId, pageTitle,
				pageDescription, list, type, null,Integer.parseInt(ShareTypeEnum.SimpleType.toString()),null);
		return JsonStr(result);
	}

	// wulyang
	/*
	@RequestMapping("/addorEdit1")
	public String addNewShare(String pageModel){
		LoginUser user=super.getUserOrSub();
		ReturnModel result=new ReturnModel();
		result.setStatu(ReturnStatus.DataError);
		if(user==null){
			result.setStatusreson("登录过期，请重新登录");
			return JsonStr(result);
		}
		Long pageId=-1L;
		String pageTitle="";
		//推广语
		String description="";
		String pageDescription="";
		Short pageTemplate=1;
		List<Object> imgs=new ArrayList<Object>();
		List<SSingleDesc> single=new ArrayList<SSingleDesc>();
		JSONObject obj;
		obj=JSONObject.fromObject(pageModel);
		if(obj!=null){
			if(obj.containsKey("pageId"))
				pageId=obj.getLong("pageId");
				pageTitle=obj.getString("pageTitle");
				pageDescription=obj.getString("pageDescription");
				//图片
				//imgs=obj.getJSONArray("productPictureList");
				pageTemplate=(short) obj.getInt("pageTemplate");
		}else{
			result.setStatusreson("参数传入有误！");
			return JsonStr(result);
		}
		if(pageTitle==null||"".equals(pageTitle)){
			result.setStatusreson("分享标题不能为空！");
			return JsonStr(result);
		}
		if(pageDescription==null||"".equals(pageDescription)){
			result.setStatusreson("分享推广语不能为空!");
			return JsonStr(result);
		}
		List<SProducts> list=new ArrayList<SProducts>();
		JSONArray array=obj.getJSONArray("productList");
		if(array!=null&&array.size()>0){
			for(Object pro:array){
				JSONObject tempobj = JSONObject.fromObject(pro.toString());
				if(tempobj==null)
					continue;
					SProducts temp=new SProducts();
				temp.setShelveId(tempobj.getLong("shelveId"));
				temp.setProductId(tempobj.getLong("productId"));
				JSONArray arrayPic= tempobj.getJSONArray("productPictureList");
				//System.out.println(arrayPic);
				//获取图片集
				if(arrayPic!=null&&!"".equals(arrayPic)){
					for(Object obj1:arrayPic ){
						JSONObject  imgsobj=JSONObject.fromObject(obj1.toString());
						if(imgsobj==null)
							continue;
						SSingleDesc ssc=new SSingleDesc();
						//设置图片地址
						ssc.setImageUrl(imgsobj.getString("productPicture"));
						//产品描述
						ssc.setDescription(imgsobj.getString("shareDescription"));
						single.add(ssc);
					}
				}
				
				list.add(temp);
			}
		}
		if(list==null||list.size()<=0){
			result.setStatusreson("请选择要分享的产品！");
			return JsonStr(result);
		}
		else{
			if(list.size()<5||list.size()<100){
				result.setStatusreson("分享的产品数量应该在5到100之间");
				return JsonStr(result);
			}
		}
		MainDataUserType type = MainDataUserType.user;
		if (shareService.getIsSupper(user.getIdentity()))
			type = MainDataUserType.supplier;
		// 简易型
		
		if(pageTemplate==ParseHelper.toShort(ShareTypeEnum.SimpleType.toString())){
			result = shareService.addShare(user.getWeiID(), pageId, pageTitle,
					pageDescription, list, type, null);
			return JsonStr(result);
		}
		//单个精品或多个精品
		if(pageTemplate==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())||pageTemplate==
				ParseHelper.toShort(ShareTypeEnum.MultipleQuality.toString())){
			result=shareService.addSingleOrManyShare(user.getWeiID(),pageId,pageTitle,
					pageDescription,list,type,single,null);
		}
		return JsonStr(result);
		
		
	}
	*/
}
