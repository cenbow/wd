package com.okwei.appinterface.web.products;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.AdPicture;
import com.okwei.bean.vo.product.ProductQuery;
import com.okwei.bean.vo.product.Products;
import com.okwei.bean.vo.product.ShopClassVO;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.service.product.IShopManagerService;
import com.okwei.web.base.SSOController;

/**
 * 店铺管理
 * @author yangjunjun
 *
 */
@RequestMapping("/shopManager")
@RestController
public class ShopManagerController extends SSOController {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private IShopManagerService shopManager;
	
	/**
	 * 根据店铺一级分类获取二级分类列表
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/findChildrenShopClass", method=RequestMethod.POST)
	public String findChildrenShopClass(Integer sid) {
		ReturnModel rs = new ReturnModel();
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				List<PShopClass> shopclassList = shopManager.getShopClassList(loginUser.getWeiID(),sid);
				if(shopclassList == null)
				{
					rs.setStatu(ReturnStatus.ParamError);
					rs.setStatusreson("该分类下没有二级分类");
				}
				else
				{
					rs.setBasemodle(shopclassList);
					rs.setStatu(ReturnStatus.Success);
					rs.setStatusreson("");
				}
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("根据店铺一级分类获取二级分类列表异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	/**
	 * 获取店铺轮播图列表
	 * @return
	 */
	@RequestMapping(value="/findShopAdPicture", method=RequestMethod.POST)
	public String findShopAdPicture(Long weiId) {
		ReturnModel rs = new ReturnModel();
		rs.setStatu(ReturnStatus.Success);
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				List<PcUserAdnotice> list = shopManager.findPcUserAdnoticeByWeiid(weiId);
				if(list != null)
				{
					List<AdPicture> adList = new ArrayList<AdPicture>();
					for (PcUserAdnotice pa : list) {
						AdPicture ad = new AdPicture();
						ad.setAdId(pa.getUadId());
						ad.setAdName(pa.getTitle());
						ad.setImg(pa.getImgLog());
						ad.setUrl(pa.getUrl());
						adList.add(ad);
					}
					rs.setBasemodle(adList);
					rs.setStatusreson("");
				}
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("获取店铺轮播图列表异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	/**
	 * 平台号店铺-获取产品列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/findShopProducts", method=RequestMethod.POST)
	public String findShopProducts(ProductQuery param) {
		ReturnModel rs = new ReturnModel();
		rs.setStatu(ReturnStatus.ValidCodeError);
		try {
			if (param.getShopWeiId() != null && param.getShopWeiId() > 0) {
				LoginUser loginUser = getUserOrSub();
				PageResult<Products> pageResult = shopManager.getProductsList(param,loginUser);
				if(pageResult != null)
				{
					rs.setStatu(ReturnStatus.Success);
					rs.setBasemodle(pageResult);
					rs.setStatusreson("");
				} else {
					rs.setStatu(ReturnStatus.Success);
					rs.setBasemodle("");
					rs.setStatusreson("");
				}
			}
		} catch (Exception e) {
			LOG.error("平台号、品牌号店铺-获取产品列表异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	/**
	 * 店铺分类新增修改
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/shopClassEdit", method=RequestMethod.POST)
	public String shopClassEdit(String model) {
		ReturnModel rs = new ReturnModel();
		rs.setStatu(ReturnStatus.DataError);
		rs.setStatusreson("操作失败");
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				ShopClassVO scVo = (ShopClassVO) JsonUtil.jsonToBean(model,ShopClassVO.class);
				rs = shopManager.editShopClass(scVo,loginUser);
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("店铺分类修改异常:"+e.getMessage(), e);
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	

}
