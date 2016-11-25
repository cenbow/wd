package com.okwei.cartportal.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.address.AddressVO;
import com.okwei.bean.vo.shoppingcart.ShoppingCar;
import com.okwei.cartportal.bean.OrderSubmit;
import com.okwei.cartportal.bean.PreSubmitCart;
import com.okwei.cartportal.bean.PreSubmitCartList;
import com.okwei.cartportal.bean.ProductModel;
import com.okwei.cartportal.bean.enums.ShoppingCarType;
import com.okwei.cartportal.bean.vo.ReturnOdertInfo;
import com.okwei.cartportal.bean.vo.SubmitOrderResult;
import com.okwei.cartportal.service.IShopCartService;
import com.okwei.cartportal.service.ITest;
import com.okwei.common.JsonUtil;
import com.okwei.service.IRegionService;
import com.okwei.service.address.IBasicAdressService;
import com.okwei.service.shoppingcart.IBasicShoppingCartMgtService;
import com.okwei.util.ImgDomain;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value="/shopCartMgt")
public class ShopCartMgtController extends SSOController {

	private final static Log logger = LogFactory.getLog(ShopCartMgtController.class);
	
	@Autowired
	private IShopCartService shopCartService;
	@Autowired
    private IRegionService regionService;
	@Autowired
    private IBasicAdressService basicAdressService;
	//basic 购物车service
	@Autowired
	IBasicShoppingCartMgtService iBasicShoppingCartMgtService;
	@Autowired
	private ITest itest;
	/*@RequestMapping(value = "/test")
	public String test( Model model) {
		return "shopcart/testPre";
	}*/
	
	public void common(String w,LoginUser user) throws Exception {
		if (w != null && w != "") {
			user.setTgWeiID(w);
		} else {
			if (user.getWeiID() > 0) {
				user.setTgWeiID(String.valueOf(user.getWeiID()));
			}
		}
		user.setCartCount(shopCartService.getCartCount(user.getWeiID()));
	}

	/**
	 * 购物车列表 
	 * @param stype 购物车类型（1-零售、2-预订单、3-批发单）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String forwardShopCartList(Model model) {
		LoginUser user =  getLoginUser(); //
//		LoginUser user =itest.getLoginUser();//
		//更新购物车状态
		ReturnModel returnModel = new ReturnModel();
		List<ShoppingCar> list = new ArrayList<ShoppingCar>();
//		iBasicShoppingCartMgtService.updateTShoppingCarState(user.getWeiID());//需改善
		returnModel = iBasicShoppingCartMgtService.getShoppingCartList(user.getWeiID());
		if(returnModel.getStatu().equals(ReturnStatus.Success))
		{
			list = (List<ShoppingCar>)returnModel.getBasemodle();
		}
		user.setCartCount(iBasicShoppingCartMgtService.getShoppingCartCount(user.getWeiID(), (short)1));
		model.addAttribute("list",list);
		model.addAttribute("user",user);
		return "shopcart/list";
	}
	/**
	 * 到达预提交页面
	 * @param stype 订单类型
	 * @param scidJson 选择的购物车商品数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/preSubmit")
	public String forwardPreSubmit(Integer addrId,Short stype,String w,String scidJson, Model model) {
		LoginUser user = super.getLoginUser();
		try {
			if (StringUtils.isNotEmpty(scidJson) && (stype == Short.valueOf(ShoppingCarType.Retail.toString())
				|| stype == Short.valueOf(ShoppingCarType.Presell.toString())
				||stype == Short.valueOf(ShoppingCarType.Wholesale.toString()))) {
				common( w,user);
				//商品寄送到的地址
				AddressVO vo = null;
				// 收货地址列表
				List<AddressVO> list = basicAdressService.getAddressList(user.getWeiID());
				if (addrId != null && addrId > 0) {
					UCustomerAddr addr = shopCartService.getById(UCustomerAddr.class, addrId);
					if (addr != null) {
						vo = new AddressVO();
						vo.setCaddrId(addr.getCaddrId());
						vo.setReceiverName(addr.getReceiverName());
						vo.setProvince(addr.getProvince());
						vo.setCity(addr.getCity());
						vo.setDistrict(addr.getDistrict());
						vo.setDetailAddr(addr.getDetailAddr());
						vo.setMobilePhone(addr.getMobilePhone());
						vo.setQq(addr.getQq());
						vo.setIsDefault(addr.getIsDefault());
						String address = "";
						int province = addr.getProvince() == null ? 0 : addr.getProvince();
						if (province > 0) {
						    address += regionService.getNameByCode(province);
						}
						int city = addr.getCity() == null ? 0 : addr.getCity();
						if (city > 0) {
						    address += regionService.getNameByCode(city);
						}
						int street = addr.getDistrict() == null ? 0 : addr.getDistrict();
						if (street > 0) {
						    address += regionService.getNameByCode(street);
						}
						vo.setAddress(address);
					}
				} else {
					if (list != null && list.size() > 0) {
						for (AddressVO ad : list) {
							if (ad.getIsDefault() == 1) {
								vo = ad;
								addrId = ad.getCaddrId();
							}
						}
					}
				}
				JSONArray data = JSONArray.fromObject(scidJson);
				List<PreSubmitCart> carList = data.toList(data,PreSubmitCart.class);
				if (carList != null && carList.size() > 0) {
					List<PreSubmitCartList> showList = new ArrayList<PreSubmitCartList>();
					ArrayList<Long> longlist = new ArrayList<Long>();// 定义一个list接收公司ID
					//判断是否为立即购买
					PreSubmitCart cart = carList.get(0);
					if (cart.getCompanyLogid() == null || cart.getCompanyLogid() <= 0) {//供应商id为空 是立即购买
						List<PreSubmitCart> pscList = new ArrayList<PreSubmitCart>(); 
						for (PreSubmitCart psc : carList) {
							if (psc.getProdId()==null || psc.getProdId() <=0 
									|| psc.getShopWeiId()==null || psc.getShopWeiId() <=0
									|| psc.getStyleId()==null || psc.getStyleId() <=0
									|| psc.getProdCount() <=0) {
								continue;
							}
							PProducts product = shopCartService.getPProducts(psc.getProdId());
							if (product != null) {
								PProductStyles pps = shopCartService.getPProductStylesById(psc.getStyleId());
								if (pps != null) {
									psc.setImg(ImgDomain.GetFullImgUrl(StringUtils.isEmpty(pps.getDefaultImg()) ? product.getDefaultImg() : pps.getDefaultImg()));// 图片
									psc.setProdNowprice(pps.getPrice());
								}
								if(stype.toString().equals(ShoppingCarType.Wholesale.toString()))
								{
							        PClassProducts pclassPro = shopCartService.getPClassProductsByWeiId(psc.getShopWeiId(), product.getProductId());
							        if (pclassPro != null) {
							        	List<PProductBatchPrice> ppbplist = shopCartService.getBatchPricess(pclassPro.getSendweiId(),product.getProductId());// 获取所有的批发价格
							        	if (ppbplist != null && ppbplist.size() > 0) {
							        		int pricount = 0;// 初始化总数
							        		for (PreSubmitCart c : carList) {// 遍历需要购买的购物车有几件
							        			if (psc.getProdId().equals(c.getProdId())) {
							        				pricount += c.getProdCount();// 件数相加
							        			}
							        		}
							        		psc.setProdNowprice(shopCartService.getshoppcartpricebycount(pricount, ppbplist));
							        	} else {
							        		psc.setProdNowprice(product.getBatchPrice());// 当前批发价格
							        	}
									}
					        		//如果批发数量未达到起批数量 返回空
					        		if (psc.getProdNowprice() == null || psc.getProdNowprice() <= 0) {
					        			model.addAttribute("error","批发价格未找到!");
										return "shopcart/cartError";
									}
								}
								else if(stype.toString().equals(ShoppingCarType.Presell.toString()))
								{
									psc.setProdNowprice(product.getBookPrice());// 当前预定价格
								}
								psc.setImg(product.getDefaultImg());
								psc.setProdTitle(product.getProductTitle());
								//查找供应商
								long supid = 0;
								// 查询上架表发货人
								PClassProducts classProduct = shopCartService.getClassProducts(psc.getShopWeiId(), psc.getProdId());
								if (classProduct != null && classProduct.getSendweiId() != null && classProduct.getSendweiId().longValue() > 0) {
								    supid = classProduct.getSendweiId();
								} else {
								    classProduct = shopCartService.getClassProducts(product.getSupplierWeiId(), psc.getProdId());
								    if (classProduct != null && classProduct.getSendweiId() != null && classProduct.getSendweiId().longValue() > 0) {
								    	supid = classProduct.getSendweiId();
								    }
								}
								USupplyer sup = shopCartService.getUSupplyer(supid);
								psc.setCompanyName(sup.getCompanyName());
								psc.setCompanyLogid(sup.getWeiId());
								if (psc.getShopWeiId() == null) {
									psc.setShopWeiId(user.getWeiID());
								}
//								logger.error(psc.getShopWeiId());
//								 if (psc.getShopWeiId() != null && psc.getShopWeiId() > 0) {
//									String shopname = shopCartService.getShopNameById(psc.getShopWeiId());
//									if (StringUtils.isNotEmpty(shopname)) {
//										psc.setShopWeiName(shopname);
//									}
//								} else {
//									psc.setShopWeiName(psc.getShopWeiId()+"");
//								} 
								psc.setShopWeiName(psc.getShopWeiId()+"");
								psc.setProperty(shopCartService.GetRroductStyleName(product.getProductId(), psc.getStyleId()));
								longlist.add(supid);
							}
							pscList.add(psc);
						}
						carList = pscList;
					}
					if (longlist.size() == 0) {
						// 遍历购物车列表
						for(PreSubmitCart sc : carList)
						{
							// 把公司的ID添加到list中		
							longlist.add(sc.getCompanyLogid());
						}
						// list去重复
						longlist = RidRepeatByLong(longlist);
					} else {
						longlist = RidRepeatByLong(longlist);
					}
					
					Double totalPrice = 0.0;//总价格
					// 遍历去重复后的公司id
					for (Long cid : longlist) {
						// 验证供应商
						USupplyer us = shopCartService.getUSupplyer(cid);
						if(us == null)
						{
							continue;
						}
						PreSubmitCartList pscList = new PreSubmitCartList();
						pscList.setTotalShopPrice(0.0);//店铺总价格（不含运费）
						List<ProductModel> productList = new ArrayList<ProductModel>();
						Long scid = 0l;//购物车id
						Long companyLogid = 0l;//店铺标识  
						String companyName = "";// 店铺名称
						// 判断是不是同一个公司的商品
						for (PreSubmitCart sc :  carList) {
							if (shopCartService.checkExistProduct(sc.getCompanyLogid(),sc.getProdId(),sc.getStyleId())) {
								if (cid.equals(sc.getCompanyLogid())) {
									if (StringUtils.isNotEmpty(sc.getLogisticsId())) {
										pscList.setLogisticsId(sc.getLogisticsId());//选择运输类型
									} 
									if (scid == 0 ) {
										scid = sc.getScid()==null?0:sc.getScid();
									} 
									if (companyLogid == 0l ) {
										companyLogid = sc.getCompanyLogid();
									} 
									if (companyName == "" ) {
										companyName = sc.getCompanyName();
									} 
									ProductModel pm = new ProductModel();
									pm.setImg(sc.getImg());
									pm.setProdCount(sc.getProdCount());
									pm.setProdId(sc.getProdId());
									pm.setProdTitle(sc.getProdTitle());
									pm.setShopWeiName(sc.getShopWeiName());
									pm.setStyleId(sc.getStyleId());
									pm.setProperty(sc.getProperty());
									pm.setShopWeiId(sc.getShopWeiId());
									pm.setScId(sc.getScid());
									//计算价格
									PProducts product = shopCartService.getPProducts(sc.getProdId());
									if (product != null) {
										PProductStyles pps = shopCartService.getPProductStylesById(sc.getStyleId());
										if (pps != null) {
											pm.setImg(ImgDomain.GetFullImgUrl(StringUtils.isEmpty(pps.getDefaultImg()) ? product.getDefaultImg() : pps.getDefaultImg()));// 图片
										}
										if(stype.toString().equals(ShoppingCarType.Wholesale.toString()))
										{
											List<PProductBatchPrice> ppbplist = shopCartService.getBatchPricess(sc.getCompanyLogid(),sc.getProdId());// 获取所有的批发价格
											if (ppbplist != null && ppbplist.size() > 0) {
												int pricount = 0;// 初始化总数
												for (PreSubmitCart c : carList) {// 遍历需要购买的购物车有几件
													if (sc.getProdId().equals(c.getProdId())) {
														pricount += c.getProdCount();// 件数相加
													}
												}
												pm.setProdNowprice(shopCartService.getshoppcartpricebycount(pricount, ppbplist));
											} 
											//如果批发数量未达到起批数量 返回空
											if (pm.getProdNowprice() == null || pm.getProdNowprice() <= 0) {
												model.addAttribute("error","批发价格未找到!");
												return "shopcart/cartError";
											}
										}
										else if(stype.toString().equals(ShoppingCarType.Presell.toString()))
										{
											pm.setProdNowprice(product.getBookPrice());// 当前预定价格
										} else {
											if (pps != null) {
												pm.setProdNowprice(pps.getPrice());
											}
										}
									}
									if (pm.getProdNowprice() == null || pm.getProdNowprice() <= 0) {
										model.addAttribute("error","价格未找到!");
										return "shopcart/cartError";
									}
									//上架商品id
									Object[] obs= new Object[3];
									obs[0]=pm.getShopWeiId();
									obs[1]=pm.getProdId();
									obs[2]=(short)1;
									PClassProducts classProduct = shopCartService.getPClassProductsByCondition(obs);
									if (classProduct != null) {
										pm.setShelvesId(classProduct.getId());
									}
									//商品价格小计
									pm.setTotalPrice(Double.valueOf(new DecimalFormat("######0.00").format(pm.getProdNowprice()*pm.getProdCount())));
									pscList.setTotalShopPrice(pscList.getTotalShopPrice()+pm.getTotalPrice());
									productList.add(pm);
								}
							}
						}
						totalPrice += pscList.getTotalShopPrice();
						//店铺价格小计
						pscList.setTotalShopPrice(Double.valueOf(new DecimalFormat("######0.00").format(pscList.getTotalShopPrice())));
						pscList.setScid(scid);;
						pscList.setCompanyLogid(companyLogid);
						pscList.setCompanyName(companyName);
						pscList.setProductList(productList);
						if (!stype.toString().equals(ShoppingCarType.Presell.toString())) {
							//获得供应商商品邮费可选列表
							pscList.setLogistics(shopCartService.getkdListBySC(pscList, user.getWeiID(), stype, vo));
							if (StringUtils.isEmpty(pscList.getLogisticsId())) {//给出默认运送方式
								if (pscList.getLogistics() != null && pscList.getLogistics().size() > 0) {
									pscList.setLogisticsId(pscList.getLogistics().get(0).getId()+"");
									pscList.setTotalShopYoufei(pscList.getLogistics().get(0).getAmount());
								} 
							}
						}
						showList.add(pscList);				
					}
					if (totalPrice > 0d) {
						totalPrice = Double.valueOf(new DecimalFormat("######0.00").format(totalPrice));
					}
					
					model.addAttribute("showList", showList);//商品清单
					model.addAttribute("list", list);//收货地址清单
					model.addAttribute("address", vo);//寄送的地址
					model.addAttribute("totalPrice", totalPrice);//总价格
					model.addAttribute("stype", stype);//订单类型
					model.addAttribute("scidJson", scidJson);//用于刷新提交页面的json
				}
			}
		} catch (Exception e) {
			logger.error("预提交页面报错:"+e.getMessage());
		}  
		model.addAttribute("user",user);
		return "shopcart/pre_submit";
	}
	
	
	/**
	 *  
	 *  测试购物车收货地址
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/preSubmit1")
//	public String forwardPreSubmit1(String addrId,Short stype,String w,String scidJson, Model model) {
////		LoginUser user = getUser();
//		LoginUser user = itest.getLoginUser();
//	//商品寄送到的地址
//		BAddressVO vo = null;
//		// 收货地址列表
//		List<BAddressVO> list = basicAdressService.getAddressList1(user.getWeiID());
//		if (addrId != null && ParseHelper.toInt(addrId) > 0) {
//			UCustomerAddr addr = shopCartService.getById(UCustomerAddr.class, addrId);
//			if (addr != null) {
//				vo = new BAddressVO();
//				vo.setAddressId(addr.getCaddrId().toString());
//				vo.setReceiveName(addr.getReceiverName());
//				vo.setProvince(addr.getProvince());
//				vo.setCity(addr.getCity());
//				vo.setDistrict(addr.getDistrict());
//				vo.setDetailAddr(addr.getDetailAddr());
//				vo.setPhone(addr.getMobilePhone());
//				vo.setQq(addr.getQq());
//				vo.setIsDefault(addr.getIsDefault());
//				String address = "";
//				int province = addr.getProvince() == null ? 0 : addr.getProvince();
//				if (province > 0) {
//				    address += regionService.getNameByCode(province);
//				}
//				int city = addr.getCity() == null ? 0 : addr.getCity();
//				if (city > 0) {
//				    address += regionService.getNameByCode(city);
//				}
//				int street = addr.getDistrict() == null ? 0 : addr.getDistrict();
//				if (street > 0) {
//				    address += regionService.getNameByCode(street);
//				}
//				vo.setAddress(address);
//			}
//		} else {
//			if (list != null && list.size() > 0) {
//				for (BAddressVO ad : list) {
//					if (ad.getIsDefault() == 1) {
//						vo = ad;
//						addrId = ad.getAddressId();
//					}
//				}
//			}
//		} 
//		model.addAttribute("list", list);//收货地址清单
//		model.addAttribute("address", vo);//寄送的地址
//		model.addAttribute("user",user);
//		return "shopcart/preSubmitNew";
//	}
	
	@RequestMapping(value = "/orderSubmit")
	public String orderSubmit(Integer addrId,Short stype,String orderJson, Model model) {
		SubmitOrderResult rmolde = new SubmitOrderResult();// 返回实体
		ReturnOdertInfo roinfo = null;
		LoginUser user = getUser();
		try {
			common(null, user);
			if (StringUtils.isNotEmpty(orderJson) && (stype == Short.valueOf(ShoppingCarType.Retail.toString())
					|| stype == Short.valueOf(ShoppingCarType.Presell.toString())
					||stype == Short.valueOf(ShoppingCarType.Wholesale.toString()))) {
				JSONArray data = JSONArray.fromObject(orderJson);
				List<OrderSubmit> orderList = data.toList(data,OrderSubmit.class);
				if (orderList != null && orderList.size() > 0) {
					List<PreSubmitCartList> submitList = new ArrayList<PreSubmitCartList>();
					ArrayList<Long> longlist = new ArrayList<Long>();// 定义一个list接收公司ID
					// 遍历购物车列表
					for(OrderSubmit sc : orderList)
					{
						// 把公司的ID添加到list中		
						longlist.add(sc.getCompanyId());
					}
					// list去重复
					longlist = RidRepeatByLong(longlist);
					Double totalPrice = 0.0;//总价格
					// 遍历去重复后的公司id
					for (Long cid : longlist) {
						// 验证供应商
						USupplyer us = shopCartService.getUSupplyer(cid);
						if(us == null)
						{
							continue;
						}
						PreSubmitCartList pscList = new PreSubmitCartList();
						pscList.setTotalShopPrice(0.0);
						List<ProductModel> productList = new ArrayList<ProductModel>();
						Long scid = 0l;//购物车id
						Long companyLogid = 0l;//店铺标识  
						String companyName = "";// 店铺名称
						// 判断是不是同一个公司的商品
						for (OrderSubmit sc : orderList) {
							if (shopCartService.checkExistProduct(sc.getCompanyId(),sc.getProductId(),sc.getStylesId())) {
								if (cid.equals(sc.getCompanyId())) {
									if (StringUtils.isEmpty(pscList.getLogisticsId())) {
										pscList.setLogisticsId(sc.getLogisticsId());
									}
									if (scid == 0 ) {
										scid = sc.getScId();
									} 
									if (companyLogid == 0l ) {
										companyLogid = sc.getCompanyId();
									} 
									ProductModel pm = new ProductModel();
									pm.setProdCount(sc.getCount());
									pm.setProdId(sc.getProductId());
									pm.setStyleId(sc.getStylesId());
									pm.setShopWeiId(sc.getShopWeiId());
									pm.setScId(sc.getScId());
									productList.add(pm);
								}
							}
						}
						totalPrice += pscList.getTotalShopPrice();
						//店铺价格小计
						pscList.setTotalShopPrice(Double.valueOf(new DecimalFormat("######0.00").format(pscList.getTotalShopPrice())));
						pscList.setScid(scid);;
						pscList.setCompanyLogid(companyLogid);
						pscList.setCompanyName(companyName);
						pscList.setProductList(productList);
						submitList.add(pscList);				
					}
					AddressVO address = new AddressVO();
					if (addrId != null && addrId > 0) {
						UCustomerAddr addr = shopCartService.getById(UCustomerAddr.class, addrId);
						if (addr != null) {
							address.setProvince(addr.getProvince());
							address.setCity(addr.getCity());
							address.setDistrict(addr.getDistrict());
							address.setDetailAddr(addr.getDetailAddr());
							address.setReceiverName(addr.getReceiverName());
							address.setMobilePhone(addr.getMobilePhone());
							address.setQq(addr.getQq());
						}
					}
					roinfo = shopCartService.savePlaceOrderByWeiNo(submitList,user.getWeiID(),address,stype);
					String rmolist = roinfo.getOrderno();
                    if(rmolist.equals("2"))
                    {
                        rmolde.setBaseModle("卖家上级不存在");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("3"))
                    {
                        rmolde.setBaseModle("收货地址不存在");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("4"))
                    {
                        rmolde.setBaseModle("购物的物品不能为空");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("5"))
                    {
                        rmolde.setBaseModle("购买的物品购物车不存在");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("6"))
                    {
                        rmolde.setBaseModle("购买的物品不存在");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("7"))
                    {
                        rmolde.setBaseModle("批量购买数据有误");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("8"))
                    {
                        rmolde.setBaseModle("产品订单为空");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("9"))
                    {
                        rmolde.setBaseModle("获取邮费异常/邮费方式选择错误");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals("10"))
                    {
                        rmolde.setBaseModle(" 购物类型有误");
                        rmolde.setStatusReson("数据异常");// 状态描述
                        rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
                    }
                    else if(rmolist.equals(""))
                    {
                        rmolde.setBaseModle("预订单成功");
                        rmolde.setStatu(ReturnStatus.Success.toString());// 返回状态
                        rmolde.setStatusReson("成功");// 状态描述
                    }
                    else
                    {
                        rmolde.setStatu(ReturnStatus.Success.toString());// 返回状态
                        rmolde.setBaseModle(rmolist);
                        rmolde.setStatusReson("成功");// 状态描述
                    }
                   
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			rmolde.setBaseModle(" 服务器异常");
            rmolde.setStatusReson("服务器异常");// 状态描述
            rmolde.setStatu(ReturnStatus.DataError.toString());// 返回状态
		}
		 model.addAttribute("rmolde",rmolde);
         model.addAttribute("roinfo",roinfo);
         model.addAttribute("user",user);
        if (stype == Short.valueOf(ShoppingCarType.Presell.toString())) {
        	return "shopcart/presell";
		}
        if (StringUtils.isNotEmpty(roinfo.getOrderno()) && ReturnStatus.Success.toString().equals(rmolde.getStatu())) {
        	String pay = ResourceBundle.getBundle("domain").getString("paydomain");
			return "redirect:"+pay+"/pay/cashier?orderNo="+roinfo.getOrderno();
		}
		return "shopcart/orderPay";
	}
	
	/**
     * 去重复
     * */
    public ArrayList<Long> RidRepeatByLong(ArrayList<Long> list)
    {
        return new ArrayList<Long>(new LinkedHashSet<Long>(list));
    }
    /**
     * 产品款式
     * @param request
     * @param productId
     * @param styleId
     * @return
     */
	@ResponseBody
	@RequestMapping(value="/getProductStyleParamAjax")
	public String getProductStyleParamAjax(HttpServletRequest request,long productId,long styleId){
		ReturnModel returnModel = new ReturnModel();
		returnModel.setBasemodle(iBasicShoppingCartMgtService.getBasicProductStyleParamModel(productId,styleId));
		returnModel.setStatu(ReturnStatus.Success);
		returnModel.setStatusreson("成功!");
		return JsonUtil.objectToJson(returnModel);
	}
}
