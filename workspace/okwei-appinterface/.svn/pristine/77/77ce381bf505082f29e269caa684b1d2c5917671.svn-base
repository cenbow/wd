package com.okwei.appinterface.web.products;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.enums.OrderFrom;
import com.okwei.bean.enums.PubProductTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.UserInfo;
import com.okwei.bean.vo.product.InvestmentProducts;
import com.okwei.bean.vo.product.P_PrductsClassList;
import com.okwei.bean.vo.product.PostAgeModelVO;
import com.okwei.bean.vo.product.ProductEditInfo;
import com.okwei.bean.vo.product.ProductParam;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.cons.UserPowerConstants;
import com.okwei.service.product.IBasicProductService;
import com.okwei.service.product.IHouseService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * 产品管理
 * @author yangjunjun
 *
 */
@RequestMapping("/productManager")
@RestController
public class ProductManagerController extends SSOController {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private IBasicProductService productService;
	@Autowired
    private IHouseService houseService;
	
	/**
	 * 到达编辑、发布产品
	 * @param param
	 */
	@RequestMapping(value="/productEdit", method=RequestMethod.POST)
	public String productEdit(Long productId) {
		ReturnModel rs = new ReturnModel();
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser == null) {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			} else {
				if(loginUser.getBatchS()==0 && loginUser.getPph()==0 && loginUser.getPth()==0 && loginUser.getPthSupply()==0 && loginUser.getYunS()==0 && loginUser.getBrandsupplyer()==0)
				{
					rs.setBasemodle(null);
					rs.setStatu(ReturnStatus.DataError);
					rs.setStatusreson("您的身份不是供应商,不能编辑发布商品");
					return JsonStr(rs);
				}
				if (productId == null || productId <= 0) {
					rs.setStatu(ReturnStatus.ParamError);
					rs.setStatusreson("参数错误");
				} else {
					if(!productService.isEditByMobile(productId))
					{
						rs.setBasemodle(null);
						rs.setStatu(ReturnStatus.NewOldVersion);
						rs.setStatusreson("该商品不支持在手机端编辑！");
					}
					else
					{
						if (loginUser.getIsChildren() == 1 && Short.valueOf("1").equals(loginUser.getPthSupply())) {//子供应商身份 只能编辑临时产品表
							ProductEditInfo psinfo = productService.getEditProductSubInfoByID(productId);
							if(psinfo == null)
							{
								rs.setStatu(ReturnStatus.ParamError);
								rs.setStatusreson("该商品不存在！");
							}
							else
							{
								rs.setBasemodle(psinfo);
								rs.setStatu(ReturnStatus.Success);
								rs.setStatusreson("");
							}
						} else if (loginUser.getIsChildren() == 0 || Short.valueOf("1").equals(loginUser.getPthSub())) {
							int idtype;
							if (Short.valueOf("1").equals(loginUser.getPph())) {
								idtype = Integer.parseInt(PubProductTypeEnum.Brand.toString());
							} else if (Short.valueOf("1").equals(loginUser.getPth())) {
								idtype = Integer.parseInt(PubProductTypeEnum.Platform.toString());
							} else if (Short.valueOf("1").equals(loginUser.getYunS()) || Short.valueOf("1").equals(loginUser.getBatchS())) {
								idtype = Integer.parseInt(PubProductTypeEnum.Supplyer.toString());
							} else if(Short.valueOf("1").equals(loginUser.getBrandsupplyer())) {
								idtype = Integer.parseInt(PubProductTypeEnum.BrandSupplyer.toString());
							} else {
								idtype = Integer.parseInt(PubProductTypeEnum.Ordinary.toString());
							}
							//子账号
							if (Short.valueOf("1").equals(loginUser.getPthSub())) {
								UPlatformSupplyer platform = productService.getById(UPlatformSupplyer.class, loginUser.getWeiID());
								if (platform != null) {
									idtype = Integer.parseInt(PubProductTypeEnum.Platform.toString());
								} else {
									UBrandSupplyer brand = productService.getById(UBrandSupplyer.class, loginUser.getWeiID());
									if (brand != null) {
										idtype = Integer.parseInt(PubProductTypeEnum.Brand.toString());
									} else {
										rs.setStatu(ReturnStatus.ParamError);
										rs.setStatusreson("身份错误");
										return JsonStr(rs);
									}
								}
							}
							ProductEditInfo pi= productService.getEditProductInfoByID(productId,idtype);
							if(pi == null)
							{
								rs.setStatu(ReturnStatus.ParamError);
								rs.setStatusreson("该商品不存在！");
							}
							else
							{
								rs.setBasemodle(pi);
								rs.setStatu(ReturnStatus.Success);
								rs.setStatusreson("");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("获取编辑产品信息异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("该商品异常！");
		}
		return JsonStr(rs);
	}
	
	
	/**
	 * 编辑、发布产品
	 * @param product
	 */
	@RequestMapping(value="/productAdd", method=RequestMethod.POST)
	public String productAdd(String product,String doType) {
		ReturnModel rs = new ReturnModel();
		try {
			ProductParam param = (ProductParam) JsonUtil.jsonToBean(product, ProductParam.class);
			param.setType(doType);
			LoginUser loginUser = getUserOrSub();
			if (loginUser == null) {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			} else {
				if(loginUser.getBatchS()==0 && loginUser.getPph()==0 && loginUser.getPth()==0 && loginUser.getPthSupply()==0 && loginUser.getYunS()==0 && loginUser.getBrandsupplyer()==0)
				{
					rs.setStatu(ReturnStatus.DataError);
					rs.setStatusreson("您的身份不是供应商,不能编辑发布商品");
					return JsonStr(rs);
				}
				//定义登录用户信息
				UserInfo userInfo = new UserInfo();
				if (loginUser.getIsChildren() == 0 || Short.valueOf("1").equals(loginUser.getPthSub())) {
					//判断登陆用户发布编辑的权限
					if (param.getProductId() != null && param.getProductId() > 0) {//编辑
						if (!loginUser.judgePower(UserPowerConstants.Product_Edit_Power)) {
							rs.setStatu(ReturnStatus.ParamError);
							rs.setStatusreson("没有编辑产品的权限");
							return JsonStr(rs);
						}
					} 
					//新增
					else {
						if (!loginUser.judgePower(UserPowerConstants.Product_Add_Power)) {
							rs.setStatu(ReturnStatus.ParamError);
							rs.setStatusreson("没有发布产品的权限");
							return JsonStr(rs);
						}
					}
					userInfo.setWeiId(loginUser.getWeiID());
					userInfo.setIdentityType(loginUser.getIdentity());
					userInfo.setPthdls(loginUser.getPthdls());
					userInfo.setPthldd(loginUser.getPthldd());
					if (Short.valueOf("1").equals(loginUser.getPph())) {
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Brand.toString()));
					} else if (Short.valueOf("1").equals(loginUser.getPth())) {
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Platform.toString()));
					} else if (Short.valueOf("1").equals(loginUser.getYunS()) || Short.valueOf("1").equals(loginUser.getBatchS())) {
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Supplyer.toString()));
					} else if(Short.valueOf("1").equals(loginUser.getBrandsupplyer())) {
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.BrandSupplyer.toString()));
					} else {
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Ordinary.toString()));
					}
				} else if (loginUser.getIsChildren() == 1) {
					//子帐号
					if (Integer.valueOf("1").equals(loginUser.getAccountType())) {
						UPlatformSupplyer platform = productService.getById(UPlatformSupplyer.class, loginUser.getWeiID());
						if (platform != null) {
							userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Platform.toString()));
						} else {
							UBrandSupplyer brand = productService.getById(UBrandSupplyer.class, loginUser.getWeiID());
							if (brand != null) {
								userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Brand.toString()));
							} else {
								rs.setStatu(ReturnStatus.ParamError);
								rs.setStatusreson("身份错误");
								return JsonStr(rs);
							}
						}
					} else {
						userInfo.setWeiId(loginUser.getParentWeiId());
						userInfo.setSub(true);
						userInfo.setSubNo(loginUser.getAccount());//子供应商账号
						userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.SubAccount.toString()));//定义用户编辑、发布产品时的身份
					}
				}
//				userInfo.setPubProductType(Integer.parseInt(PubProductTypeEnum.Platform.toString()));//测试
				userInfo.setYun(loginUser.judgePower(UserPowerConstants.Product_IsYun_Power));
				rs = editProduct(param, userInfo);
			}
		} catch (Exception e) {
			LOG.error("发布产品异常:"+e.getMessage(), e);
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	public ReturnModel editProduct(ProductParam param,UserInfo user) throws Exception{
		//编辑商品
		ReturnModel rm = productService.editProduct(param, user,OrderFrom.APP.toString());
		if ("1".equals(rm.getStatu().toString())) {
			//子供应商发布的产品如果是新增的则删除借用的PProducts表中的数据
			if (Integer.valueOf(PubProductTypeEnum.SubAccount.toString()).equals(user.getPubProductType())) {
				Map<String, Object> map = (Map<String, Object>)rm.getBasemodle();
				if (map != null) {
					boolean isEdit = (boolean)map.get("isEdit");
					if (!isEdit) {
						Long productid = (Long)map.get("productid");
						productService.deleteById(PProducts.class, productid);
					}
				}
			}
		}
		return rm;
	}
	
	/**
	 * 获取招商系列商品列表(按产品ID)
	 * @param pageIndex
	 * @param pageSize
	 * @param productId
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping(value = "/getDemandProductListByProductId", method=RequestMethod.POST)
	public String getDemandProductList(
			@RequestParam(required = false, defaultValue = "1") int pageIndex,
			int pageSize, Long productId) throws MapperException {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUser();
		if (user != null) {
			InvestmentProducts model = houseService.getInvesProducts(user,
					Limit.buildLimit(pageIndex, pageSize), 0,productId);
			result.setStatu(ReturnStatus.Success);
			result.setBasemodle(model);
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(result);
	}
	
	/**
	 * 获取我的运费模板列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findPostAgeModel", method=RequestMethod.POST)
	public String findPostAgeModel(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,Integer pageSize) {
		ReturnModel rs = new ReturnModel();
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				PageResult<PostAgeModelVO> list = productService.getPostAgeModelPageResult(loginUser.getWeiID(),pageIndex,pageSize);
				if(list == null)
				{
					rs.setStatu(ReturnStatus.ParamError);
					rs.setStatusreson("运费模板列表为空");
				}
				else
				{
					rs.setBasemodle(list);
					rs.setStatu(ReturnStatus.Success);
					rs.setStatusreson("");
				}
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("获取我的运费模板列表异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	/**
	 * 获取云端产品库商品大分类
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping(value = "/findYunClassProduct", method=RequestMethod.POST)
	public String findYunClassProduct() throws MapperException {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUser();
		if (user != null) {
			List<P_PrductsClassList> list;
			try {
				list = productService.findYunProductClass();
				if (list != null && list.size() > 0) {
					result.setStatu(ReturnStatus.Success);
					result.setBasemodle(list);
				} else {
					result.setStatu(ReturnStatus.DataError);
					result.setBasemodle("");
				}
			} catch (Exception e) {
				LOG.error("获取云端产品库商品大分类异常："+e.getMessage());
				result.setStatu(ReturnStatus.SystemError);
				if (e.getMessage() != null)
					result.setStatusreson(e.getMessage());
				else
					result.setStatusreson("数据有误!");
			}
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(result);
	}

}
