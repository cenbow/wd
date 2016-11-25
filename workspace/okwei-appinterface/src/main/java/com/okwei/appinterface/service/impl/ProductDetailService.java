package com.okwei.appinterface.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.BatchPrice;
import com.okwei.appinterface.bean.vo.EnsureType;
import com.okwei.appinterface.bean.vo.MsgProductInfo;
import com.okwei.appinterface.bean.vo.ProductPrice;
import com.okwei.appinterface.bean.vo.SellPropertyName;
import com.okwei.appinterface.bean.vo.SellPropertyValue;
import com.okwei.appinterface.dao.IProductDao;
import com.okwei.appinterface.enums.ProductStatus;
import com.okwei.appinterface.service.IProductDetailService;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActShowProducts;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.ActProductVerState;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ProductTagType;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.VerfierStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.activity.ActProductInfo;
import com.okwei.bean.vo.activity.ActivityModel;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.CommonMethod;
import com.okwei.common.JsonUtil;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.product.IHouseService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.BitOperation;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

@Service
public class ProductDetailService extends BaseService implements IProductDetailService {

	@Autowired
	private IProductDao baseDao;

	@Autowired
	private IHouseService houseService;
	@Autowired
	private IBaseActivityService actService;
	@Autowired
	private IBaseProductDao productDao;
	
	@Autowired
	private IProductSearchDao productSearchDao;
	@Autowired
	private IBaseCommonService commonService;

	@Override
	public MsgProductInfo GetProductDetail(Long productid, LoginUser user, Long shopWeiNo, String tiket, int source, boolean isShowDetail) {
		MsgProductInfo info = null;
		PProducts product = baseDao.get(PProducts.class, productid);
		if (product != null) {
			info = new MsgProductInfo();
			
			String domain = AppSettingUtil.getSingleValue("wapDomain");
			info.setWebUrl("http://" + domain + "/shejiao/zrl?tiket=" + tiket + "&productid=" + productid);

			// 供应商微店号
			long supWeiID = product.getSupplierWeiId();
			UUserAssist supplierAssist=baseDao.get(UUserAssist.class, supWeiID);
			PPreOrder pPreOrder = baseDao.get(PPreOrder.class, productid);
			if (pPreOrder != null) {
				info.setExplain(pPreOrder.getContent());
			}
			info.setWeiName(commonService.getShopNameByWeiId(supWeiID));
			info.setWeiNo(supWeiID);
			// 查询供应商
			USupplyer supplyer = baseDao.get(USupplyer.class, supWeiID);
			if (supplyer != null && supplyer.getBusContent() != null && supplyer.getBusContent() != "") {
				info.setBusContent("主营：" + supplyer.getBusContent());
				if (info.getWeiName() == null) {
					info.setWeiName(supplyer.getCompanyName());
				}
			}
			//当前用户（登陆用户）
			Long weiNo = user.getWeiID()==null?0:user.getWeiID();
			if (product.getState().shortValue() == Short.parseShort(ProductStatus.Showing.toString())) {
				info.setIsOnSale((short) 1);
				if (isSupplyer(supplierAssist.getIdentity())) {
					 info.setIsCanShevles((short) 1);
					 info.setIsOnSale((short) 1);
					 if(product.getVerStatus()!=null&&product.getVerStatus()!=1&&product.getPublishType()!=null&&product.getPublishType()==1){
					    	 info.setIsCanShevles((short) 0);
							 info.setIsOnSale((short) 0);
							 info.setIsCanShevlesMsg("此商品暂未审核,为了保证消费者权益,暂不能购买。");
							 info.setNoSaleReason("此商品暂未审核,为了保证消费者权益,暂不能购买。");
					  }
				} else {
					info.setIsCanShevles((short) 0);
					info.setIsOnSale((short) 0);
					info.setIsCanShevlesMsg("供应商暂未缴纳保证金，为保证消费者权益，该商品暂不支持分销。");
					info.setNoSaleReason("供应商暂未缴纳保证金，为保证消费者权益，该商品暂不支持交易。");
				}
			} else {
				info.setIsOnSale((short) 0);
				info.setIsCanShevles((short) 0);
				info.setIsCanShevlesMsg("该产品已下架。");
				info.setNoSaleReason("该产品已下架。");
			}
			
			info.setProductId(product.getProductId());
			info.setTitle(product.getProductTitle());
			info.setMinTitle(product.getProductMinTitle());
			info.setPublishType(product.getPublishType()==null?0:product.getPublishType());
			   
			if (shopWeiNo != null && shopWeiNo.longValue() > 0) {
				info.setCurrentWeiId(shopWeiNo);// 成交微店
			} else {
				if (weiNo > 0) {
					info.setCurrentWeiId(weiNo);// 成交微店
				}
			}

			info.setIsOnShevles((short) 0);// 是否上架
			if (product.getDefaultPrice() == null) {
				info.setPrice(0.0);
			} else {
				info.setPrice(product.getDefaultPrice());
			}
			// 添加商品原价
			if (null != product.getOriginalPrice()) {
				info.setOriginalPrice(product.getOriginalPrice());
			} else {
				double percent = 1.3;
				if (null != product.getPercent())
					percent = product.getPercent();
				double displayPrice = product.getDefaultPrice() * percent;
				DecimalFormat df = new DecimalFormat("#.00");
				displayPrice = Double.parseDouble(df.format(displayPrice));
				info.setOriginalPrice(displayPrice);
			}
			// 获取图片
			String hql = " from PProductImg w where w.productId=?";
			Object[] b = new Object[1];
			b[0] = productid;
			List<PProductImg> list = baseDao.find(hql, b);
			List<String> lists = new ArrayList<String>();
			lists.add(ImgDomain.GetFullImgUrl(product.getDefaultImg()));
			List<String> lar = new ArrayList<String>();
			lar.add(ImgDomain.GetFullImgUrl(product.getDefaultImg(), 75));
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					PProductImg img = list.get(i);
					lists.add(ImgDomain.GetFullImgUrl(img.getImgPath()));
					lar.add(ImgDomain.GetFullImgUrl(img.getImgPath(), 75));
				}
			}
			info.setImgList(lists);
			info.setImgListLarge(lar);
			// 店铺信息
			String logo = getImageById(supWeiID);
			info.setWeiImg(ImgDomain.GetFullImgUrl(logo));

			// 获取开始佣金和结束佣金及价格
			hql = " from PProductStyles p where p.productId=?";
			List<PProductStyles> ls = baseDao.find(hql, b);
			Double startprice = 0.0;
			Double endprice = 0.0;
			Double startcommision = 0.0;
			Double endCommision = 0.0;
			for (int i = 0; i < ls.size(); i++) {
				PProductStyles pss = ls.get(i);
				if (i == 0) {
					startprice = pss.getPrice();
					endprice = pss.getPrice();
					startcommision = pss.getConmision();
					endCommision = pss.getConmision();
				}
				if (startprice > pss.getPrice()) {
					startprice = pss.getPrice();
				}
				if (endprice < pss.getPrice()) {
					endprice = pss.getPrice();
				}
				if (startcommision > pss.getConmision()) {
					startcommision = pss.getConmision();
				}
				if (endCommision < pss.getConmision()) {
					endCommision = pss.getConmision();
				}
			}
			info.setStartPrice(startprice);
			info.setStartCommision(startcommision);
			info.setEndCommision(endCommision);
			info.setEndPrice(endprice);
			// 品牌认证
			List<EnsureType> liste = new ArrayList<EnsureType>();
			EnsureType et = new EnsureType();
			et.setName("正品保障");
			et.setDesc("正品保障");
			et.setUrl("http://base.okimgs.com/appimages/zhengpinbaozhang.png");
			liste.add(et);
			EnsureType et1 = new EnsureType();
			et1.setName("人工质检");
			et1.setDesc("人工质检");
			et1.setUrl("http://base1.okimgs.com/appimages/rengongzhijian.png");
			liste.add(et1);
			EnsureType et2 = new EnsureType();
			et2.setName("担保交易");
			et2.setDesc("担保交易");
			et2.setUrl("http://base2.okimgs.com/appimages/danbaojiaoyi.png");
			liste.add(et2);
			EnsureType et3 = new EnsureType();
			et3.setName("闪电发货");
			et3.setDesc("闪电发货");
			et3.setUrl("http://base3.okimgs.com/appimages/shandianfahuo.png");
			liste.add(et3);
			if (product.getBrandId() != null && product.getBrandId() > 0) {
				info.setBrand(true);
			} else {
				info.setBrand(false);
			}

			// 供应商 5.0修改
			Short supType = product.getSupperType(); // 使用 UserIdentityType
			// 普通微店
			if (BitOperation.verIdentity(supType, UserIdentityType.commonUser)) {
				info.setSupType("普通微店");
			}
			// 云商通
			if (BitOperation.verIdentity(supType, UserIdentityType.yunSupplier)) {
				info.setSupType("工厂号供应商");
				info.setIdentityCategoryImg("http://base3.okimgs.com/images/factory.png");
			}
			// 批发号
			if (BitOperation.verIdentity(supType, UserIdentityType.batchSupplier)) {
				info.setSupType("批发号供应商");
				info.setIdentityCategoryImg("http://base3.okimgs.com/images/batch.png");
			}
			// 平台号
			if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier)) {
				info.setSupType("平台供应商");
			}
			// 品牌号
			if (BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier)) {
				info.setSupType("品牌号供应商");
			}
			info.setEnsure(liste);

			// 邮费信息
			PPostAgeModel pad = baseDao.get(PPostAgeModel.class, product.getFreightId());
			if (pad != null) {
				info.setPostFeeId(pad.getFreightId());
				String padString = " from PPostAgeDetails p where p.freightId= ? and p.status=0";
				Object[] pob = new Object[1];
				pob[0] = product.getFreightId();
				List<PPostAgeDetails> pads = baseDao.find(padString, pob);
				if (pads != null && pads.size() > 0) {
					info.setCustomPostFee(pads.get(0).getFirstpiece());
				}
			} else {
				info.setPostFeeId(-1);
				info.setCustomPostFee(product.getDefPostFee());
			}
			String wapUrl = AppSettingUtil.getSingleValue("productInfoUrl") + productid + "&pro_weino=" + String.valueOf(shopWeiNo) + "&tiket=" + tiket + "&t=4";
			info.setWapUrl(wapUrl);

			// 产品描述
			if (isShowDetail) {
				info.setProductDesc(product.getAppdes() == null ? product.getPcdes() : product.getAppdes());
			}

			// 获取批发属性
			// 选择自己发货时，批发订单可任意改价；选择上游发货时，批发订单价格不能低于上游设置的价格
			// 先判断是否上架

			// modify by tan 20150604
			long psbpid = 0L;// 批发价格ID
			hql = " from PClassProducts p where p.productId=? and p.weiId=?";
			Object[] cb = new Object[2];
			cb[0] = productid;
			cb[1] = supWeiID;
			PClassProducts pcp = baseDao.getUniqueResultByHql(hql, cb);
			if (pcp != null) {
				// 付豪加 销售量
				PProductAssist pProductAssist = baseDao.get(PProductAssist.class, productid);
				if (pProductAssist != null) {
					if (pProductAssist.getTotalCount() == null) {
						info.setSellNum(0);
					} else {
						info.setSellNum(pProductAssist.getTotalCount());
					}
				}
				// end
				psbpid = pcp.getId();

			}

			AActProductsShowTime asp = actService.getAActProductsShowTime(productid, true);
			// 关注表
			UAttention ua = null;
			if (weiNo > 0 && weiNo != supWeiID) {
				hql = " from UAttention w where  w.attentioner=? and w.attTo=?";
				Object[] obj = new Object[2];
				obj[0] = weiNo;
				obj[1] = supWeiID;
				ua = baseDao.getUniqueResultByHql(hql, obj);
			} // oba);
			if (ua != null || product.getSupplierWeiId().equals(user.getWeiID())) // 游客和没有关注的用户没有批发和预定属性
			{
				if (BitOperation.judageProductTag(product.getTag(), ProductTagType.Wholesale)) // 有没有批发
				{ // 批发价格属性
					hql = " from PShevleBatchPrice p where p.id=?";
					b[0] = psbpid;
					List<PShevleBatchPrice> listps = baseDao.find(hql, b);
					if (listps != null && listps.size() > 0) {
						List<BatchPrice> listbp = new ArrayList<BatchPrice>();
						info.setBatch(true);
						Double start = 0.0;
						Double end = 0.0;
						for (int i = 0; i < listps.size(); i++) {
							PShevleBatchPrice pbp = listps.get(i);
							BatchPrice bp = new BatchPrice();
							bp.setCount(pbp.getCount());
							bp.setPrice(pbp.getPrice());
							listbp.add(bp);
							if (i == 0) {
								start = pbp.getPrice();
								end = pbp.getPrice();
							} else {
								if (start > pbp.getPrice()) {
									start = pbp.getPrice();
								}
								if (end < pbp.getPrice()) {
									end = pbp.getPrice();
								}
							}

						}
						info.setBatch(true);
						if (asp != null) {
							info.setBatch(false);
						}
						info.setBatchPrice(listbp);
						info.setStartBatchPrice(start);
						info.setEndBatchPrice(end);
					}
				} else {
					info.setBatch(false);
				}
				// 5.0 修改去掉预订
				info.setBook(false);
			}
			hql = "select count(*) from PClassProducts p where p.productId=? and p.weiId=? and p.state=1";
			Object[] cp = new Object[2];
			cp[0] = productid;
			cp[1] = weiNo;
			Long lo = baseDao.count(hql, cp);
			if (lo.intValue() > 0) {
				info.setIsOnShevles((short) 1);
			} else {
				info.setIsOnShevles((short) 0);
			}

			// 5.0新增字段 汪宁波
			info.setIsHasAgentPrice(false);
			info.setIsHasStorePrice(false);
			int count = product.getCount() == null ? 0 : product.getCount();
//			if (count < 1)
//				count = 10000;
			// 产品数量
			AActShowProducts asproducts = new AActShowProducts();
			if (asp != null) {
				asproducts = productDao.get(AActShowProducts.class, asp.getProActId());
				if (asproducts != null) {
					info.setInventory(asproducts.getStockCount());
				}
			} else {
				info.setInventory(count);
			}
			info.setProductNum(count);
			info.setIsAgent(false);
			info.setIsStore(false);
			info.setSupplierWeiId(supWeiID);
			if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier) || BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier)) {
				ProductInfo proInfo = new ProductInfo();
				// 判断是不是该产品的代理商落地店
				UDemandProduct demand = baseDao.getUniqueResultByHql("from UDemandProduct where productId=?", productid);
				if (weiNo > 0) {
					if (demand != null) {
						List<USupplyChannel> chanList = baseDao.find("from USupplyChannel where demandId=? and weiId=? and state=?", new Object[] { demand.getDemandId(), weiNo, Short.parseShort(AgentStatusEnum.Normal.toString()) });
						if (chanList != null && chanList.size() > 0) {
							for (USupplyChannel chan : chanList) {
								if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
									info.setIsAgent(true);
									proInfo.setCurrentUserIsAgent(1);
								}
								if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
									info.setIsStore(true);
									proInfo.setCurrentUserIsStore(1);
								}
							}
						}
					}
				}
				PPriceShow visible = baseDao.get(PPriceShow.class, supWeiID);
				proInfo = houseService.getPriceShow(user, visible, proInfo);
				if (proInfo.getDlPriceVisibility() == 1)
					info.setIsHasAgentPrice(true);
				if (proInfo.getLdPriceVisibility() == 1)
					info.setIsHasStorePrice(true);

				// 查询代理价 落地价
				PProductRelation relation = baseDao.get(PProductRelation.class, productid);
				if (relation != null) {
					info.setAgentPrice(relation.getMinProxyPrice());
					info.setStorePrice(relation.getMinFloorPrice());
					if (info.getIsHasStorePrice()) {
						info.setCouponsPrice("现金券最高可抵￥" + new DecimalFormat("######0.00").format(relation.getMinFloorPrice() * 0.1));

					} else {
						info.setCouponsPrice("首单即赠等值现金券");
					}
				}
				// 落地店首次采购金额
				if (demand != null) {
					info.setDemandId(demand.getDemandId());
					info.setDemandProductUrl("http://" + domain + "/v5/pph/txllist?tiket=" + tiket + "&parid=" + String.valueOf(demand.getDemandId()) + "&source=" + String.valueOf(source));
					USupplyDemand supply = baseDao.get(USupplyDemand.class, demand.getDemandId());
					if (supply != null) {
						info.setStoreBuyAmount(supply.getOrderAmout());
					}
				}
			}
			// 获取商品的销售属性
			info.setSellAttr(GetSellAttrByProductId(product.getProductId(), info.getIsHasStorePrice()).trim());
			// 分享业务 添加原价
			info.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
			//验证用户是否拥有该产品的代理身份，获取查看代理价的权限
			if(product.getPublishType()!=null&&product.getPublishType()==1){
				DBrandSupplier dRelation= baseDao.getNotUniqueResultByHql("from DBrandSupplier where weiId=?", product.getSupplierWeiId());
				DAgentInfo dAgentInfo= baseDao.getNotUniqueResultByHql("from DAgentInfo where weiId=?", user.getWeiID());
				if(dRelation!=null&&dAgentInfo!=null&&dRelation.getBrandId().intValue()==dAgentInfo.getBrandId().intValue()){
					if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentDuke)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getDukePrice());
					}else if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentDeputyDuke)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getDeputyPrice());
					}else if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentBrandAgent)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getAgentPrice());
					}else{
						info.setIsHasAgentPrice(false);
					}
				}else{
					info.setIsHasAgentPrice(false);
				}
			}else{
				info.setIsHasAgentPrice(false);
			}
		}
		/*----------------判断是否828全返产品 兼容以前-------------------*/
		ActProductInfo actnew=productSearchDao.get_ProductAct(product.getProductId());
		if(actnew!=null&&actnew.getActiveType()==1){
				//AActivityProducts actProducts=baseDao.get(AActivityProducts.class, actnew.getProActId());	
				AActShowProducts actProducts=baseDao.get(AActShowProducts.class, actnew.getProActId());
				//if(actProducts!=null&&actProducts.getState()==Short.parseShort(ActProductVerState.Ok.toString())){
		     	if(actProducts!=null){
		     		AActivity actModel=baseDao.get(AActivity.class, actProducts.getActId());
		     		if(actModel!=null){ 
		     			ActivityModel model=new ActivityModel();
		         		model.setActId(actProducts.getActId().intValue());
		         		model.setTitle(actModel.getTitle());
		         		model.setBeginTime(DateUtils.format(actnew.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		         		model.setEndTime(DateUtils.format(actnew.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		         		model.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
		         		model.setActPrice(actProducts.getPrice());
		         		model.setState(Short.parseShort(ActProductVerState.Ok.toString()));
		         		model.setStateText("预热中");
		         		model.setActiveType(actnew.getActiveType());
		         		model.setStockCount(actProducts.getStockCount()==null?0:actProducts.getStockCount());
		         		String key="BuyLimitCount_"+user.getWeiID()+"_"+actnew.getActPid()+"_"+actnew.getProductId();
		         		int count= ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key))) ;
		         		if(count>=5){
		         			model.setBuyNumLimit(0);
		         		}else {
		         			model.setBuyNumLimit(5-count);
						}
		         		info.setActivityModel(model);
		     		}
		     	}
		}else{
				
			/*----------------限时抢购产品-------------------*/
			AActProductsShowTime act=actService.getAActProductsShowTime(productid, true);
			if(act!=null){
				AActivityProducts actProducts=baseDao.get(AActivityProducts.class, act.getProActId());	
			 	if(actProducts!=null&&actProducts.getState()==Short.parseShort(ActProductVerState.Ok.toString())){
			 		AActivity actModel=baseDao.get(AActivity.class, actProducts.getActId());
			 		if(actModel!=null){ 
			 			ActivityModel model=new ActivityModel();
			     		model.setActId(actProducts.getActId().intValue());
			     		model.setTitle(actModel.getTitle());
			     		model.setBeginTime(DateUtils.format(act.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
			     		model.setEndTime(DateUtils.format(act.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			     		model.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
			     		model.setActPrice(actProducts.getPrice());
			     		model.setState(1);
			     		model.setStateText("抢购中");
			     		model.setActiveType(actnew.getActiveType());
			     		
			     		String key="BuyLimitCount_"+user.getWeiID()+"_"+act.getActPid()+"_"+act.getProductId();
			     		int count= ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key))) ;
			     		if(count>=5){
			     			model.setBuyNumLimit(0);
			     		}else {
			     			model.setBuyNumLimit(5-count);
						}
			     		info.setActivityModel(model);
			 		}
			 	}
			}	
		}

		

		return info;
	    }
	
	
	public MsgProductInfo GetProductDetailNew(Long productid, LoginUser user, Long shopWeiNo, String tiket, int source, boolean isShowDetail) {
		MsgProductInfo info = null;
		PProducts product = baseDao.get(PProducts.class, productid);
		if (product != null) {
			info = new MsgProductInfo();
			String domain = AppSettingUtil.getSingleValue("wapDomain");
			info.setWebUrl("http://" + domain + "/shejiao/zrl?tiket=" + tiket + "&productid=" + productid);
			// 供应商微店号
			long supWeiID = product.getSupplierWeiId();
			UUserAssist supplierAssist=baseDao.get(UUserAssist.class, supWeiID);
			PPreOrder pPreOrder = baseDao.get(PPreOrder.class, productid);
			if (pPreOrder != null) {
				info.setExplain(pPreOrder.getContent());
			}
			info.setWeiName(commonService.getShopNameByWeiId(supWeiID));
			info.setWeiNo(supWeiID);
			info.setBusContent("主营："+commonService.getBusContentByWeiId(supWeiID));
			
			Long weiNo =  user.getWeiID()==null?0:user.getWeiID();
			if (product.getState().shortValue() == Short.parseShort(ProductStatus.Showing.toString())) {
				info.setIsCanShevles(0); 
				info.setIsOnSale(1);
				if(product.getPublishType()!=null&&product.getPublishType()>0){
					info.setIsCanShevlesMsg("代理商品不支持上架！");
					if(product.getVerStatus()==null||product.getVerStatus()!=Short.parseShort(VerfierStatusEnum.Pass.toString())){
						info.setIsOnSale(0);
						info.setNoSaleReason("此产品暂未审核，为了保证消费者权益，暂不能购买。");
					}
				}
				else if (isSupplyer(supplierAssist.getIdentity())) {//普通供应商
					info.setIsCanShevles(1);
					if(productDao.is_Onshelves(productid, user.getWeiID())){
						//产品已经上架过
						info.setIsOnShevles(1); 
					}
				}
				else if(BitOperation.isIdentity(supplierAssist.getIdentity()==null?0:supplierAssist.getIdentity(), UserIdentityType.AgentBrandSupplier)){
					if(product.getVerStatus()==null||product.getVerStatus()!=Short.parseShort(VerfierStatusEnum.Pass.toString())){
						//如果是品牌供应商
						info.setIsOnSale(0);
						info.setNoSaleReason("此产品暂未审核，为了保证消费者权益，暂不能购买。");
					}else {
						info.setIsOnSale(1);
					}
				}
				else{
					info.setIsCanShevles(0);
					info.setIsOnSale(0);
					info.setIsCanShevlesMsg("供应商暂未缴纳保证金，为保证消费者权益，该商品暂不支持分销。");
					info.setNoSaleReason("供应商暂未缴纳保证金，为保证消费者权益，该商品暂不支持交易。");
				}
			} else {
				info.setIsOnSale(0);
				info.setIsCanShevles(0);
				info.setIsCanShevlesMsg("该产品已下架。");
				info.setNoSaleReason("该产品已下架。");
			}
			//兑换商品供应商微店号
			long exSupplierWeiid=ParseHelper.toLong(AppSettingUtil.getSingleValue("supiler"));
			if(supWeiID==exSupplierWeiid){
				info.setIsCanExchange(1);//产品是否可以兑换（购物券兑换）
				info.setIsCanShevles(0);
				info.setIsOnSale(0); 
			}
			UUserAssist userAssist=baseDao.get(UUserAssist.class, user.getWeiID());
			if(userAssist!=null&&userAssist.getIdentity()!=null&&BitOperation.isIdentity(userAssist.getIdentity(), UserIdentityType.AgentBrandAgent)){
				info.setIsCanShevles(0);
				info.setIsCanShevlesMsg("代理身份无法上架产品。");
			}
			info.setProductId(product.getProductId());
			info.setTitle(product.getProductTitle());
			info.setMinTitle(product.getProductMinTitle());
			if (shopWeiNo != null && shopWeiNo.longValue() > 0) {
				info.setCurrentWeiId(shopWeiNo);// 成交微店
			} else {
				info.setCurrentWeiId(weiNo);// 成交微店
			}
			if (product.getDefaultPrice() == null) {
				info.setPrice(0.0);
			} else {
				info.setPrice(product.getDefaultPrice());
			}
			// 添加商品原价
			if (null != product.getOriginalPrice()) {
				info.setOriginalPrice(product.getOriginalPrice());
			} else {
				double percent = 1.3;
				if (null != product.getPercent())
					percent = product.getPercent();
				double displayPrice = product.getDefaultPrice() * percent;
				DecimalFormat df = new DecimalFormat("#.00");
				displayPrice = Double.parseDouble(df.format(displayPrice));
				info.setOriginalPrice(displayPrice);
			}
		
			List<PProductImg> list = baseDao.find(" from PProductImg w where w.productId=?", productid);
			List<String> lists = new ArrayList<String>();
			lists.add(ImgDomain.GetFullImgUrl(product.getDefaultImg()));
			List<String> lar = new ArrayList<String>();
			lar.add(ImgDomain.GetFullImgUrl(product.getDefaultImg(), 75));
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					PProductImg img = list.get(i);
					lists.add(ImgDomain.GetFullImgUrl(img.getImgPath()));
					lar.add(ImgDomain.GetFullImgUrl(img.getImgPath(), 75));
				}
			}
			info.setImgList(lists);
			info.setImgListLarge(lar);
			// 店铺信息
			String logo = getImageById(supWeiID);
			info.setWeiImg(ImgDomain.GetFullImgUrl(logo));

			//产品是否正在限时抢购中
//			AActProductsShowTime act = actService.getAActProductsShowTime(productid, true);
			// 获取开始佣金和结束佣金及价格
			List<PProductStyles> ls = baseDao.find(" from PProductStyles p where p.productId=?", productid);
			Double startprice = 0.0;
			Double endprice = 0.0;
			Double startcommision = 0.0;
			Double endCommision = 0.0;
			for (int i = 0; i < ls.size(); i++) {
				PProductStyles pss = ls.get(i);
				if (i == 0) {
					startprice = pss.getPrice();
					endprice = pss.getPrice();
					startcommision = pss.getConmision();
					endCommision = pss.getConmision();
				}
				if (startprice > pss.getPrice()) {
					startprice = pss.getPrice();
				}
				if (endprice < pss.getPrice()) {
					endprice = pss.getPrice();
				}
				if (startcommision > pss.getConmision()) {
					startcommision = pss.getConmision();
				}
				if (endCommision < pss.getConmision()) {
					endCommision = pss.getConmision();
				}
			}
			info.setStartPrice(startprice);
			info.setStartCommision(startcommision);
			info.setEndCommision(endCommision);
			info.setEndPrice(endprice);
			// 品牌认证
			List<EnsureType> liste = find_EnsureTypes();
			info.setEnsure(liste);
			if (product.getBrandId() != null && product.getBrandId() > 0) {
				info.setBrand(true);
			} else {
				info.setBrand(false);
			}

			// 供应商 5.0修改
			Integer supType = supplierAssist.getIdentity()==null?0:supplierAssist.getIdentity(); // 使用 UserIdentityType
			info= getMsgProductInfo(supType,info);
			// 邮费信息
			PPostAgeModel pad = baseDao.get(PPostAgeModel.class, product.getFreightId());
			if (pad != null) {
				info.setPostFeeId(pad.getFreightId());
				String padString = " from PPostAgeDetails p where p.freightId= ? and p.status=0";
				Object[] pob = new Object[1];
				pob[0] = product.getFreightId();
				List<PPostAgeDetails> pads = baseDao.find(padString, pob);
				if (pads != null && pads.size() > 0) {
					info.setCustomPostFee(pads.get(0).getFirstpiece());
				}
			} else {
				info.setPostFeeId(-1);
				info.setCustomPostFee(product.getDefPostFee());
			}
			String wapUrl = AppSettingUtil.getSingleValue("productInfoUrl") + productid + "&pro_weino=" + String.valueOf(shopWeiNo) + "&tiket=" + tiket + "&t=4";
			info.setWapUrl(wapUrl);

			// 产品描述
			if (isShowDetail) {
				info.setProductDesc(product.getAppdes() == null ? product.getPcdes() : product.getAppdes());
			}

			// 获取批发属性
			// 选择自己发货时，批发订单可任意改价；选择上游发货时，批发订单价格不能低于上游设置的价格
			// 先判断是否上架

			// modify by tan 20150604
			long psbpid = 0L;// 批发价格ID
			PClassProducts pcp = baseDao.getUniqueResultByHql(" from PClassProducts p where p.productId=? and p.weiId=?", productid,supWeiID);
			if (pcp != null) {
				// 付豪加 销售量
				PProductAssist pProductAssist = baseDao.get(PProductAssist.class, productid);
				if (pProductAssist != null) {
					if (pProductAssist.getTotalCount() == null) {
						info.setSellNum(0);
					} else {
						info.setSellNum(pProductAssist.getTotalCount());
					}
				}
				psbpid = pcp.getId();
			}

			// 关注表
			UAttention ua = null;
			if (weiNo > 0 && weiNo != supWeiID) {
				ua = baseDao.getUniqueResultByHql(" from UAttention w where  w.attentioner=? and w.attTo=?", weiNo,supWeiID);
			} // oba);
			if (ua != null || product.getSupplierWeiId().equals(user.getWeiID())) // 游客和没有关注的用户没有批发和预定属性
			{
				if (BitOperation.judageProductTag(product.getTag(), ProductTagType.Wholesale)) // 有没有批发
				{ // 批发价格属性
					List<PShevleBatchPrice> listps = baseDao.find(" from PShevleBatchPrice p where p.id=?", psbpid);
					if (listps != null && listps.size() > 0) {
						List<BatchPrice> listbp = new ArrayList<BatchPrice>();
						info.setBatch(true);
						Double start = 0.0;
						Double end = 0.0;
						for (int i = 0; i < listps.size(); i++) {
							PShevleBatchPrice pbp = listps.get(i);
							BatchPrice bp = new BatchPrice();
							bp.setCount(pbp.getCount());
							bp.setPrice(pbp.getPrice());
							listbp.add(bp);
							if (i == 0) {
								start = pbp.getPrice();
								end = pbp.getPrice();
							} else {
								if (start > pbp.getPrice()) {
									start = pbp.getPrice();
								}
								if (end < pbp.getPrice()) {
									end = pbp.getPrice();
								}
							}
						}
						info.setBatch(true);
//						if (act != null) {
//							info.setBatch(false);
//						}
						info.setBatchPrice(listbp);
						info.setStartBatchPrice(start);
						info.setEndBatchPrice(end);
					}
				} else {
					info.setBatch(false);
				}
				// 5.0 修改去掉预订
				info.setBook(false);
			}
			// 5.0新增字段 汪宁波
			info.setIsHasAgentPrice(false);
			info.setIsHasStorePrice(false);
			int count = product.getCount() == null ? 0 : product.getCount();
//			if (count < 1)
//				count = 10000;
			// 产品数量
//			AActShowProducts asproducts = null;//new AActShowProducts();
//			if (act != null) {
//				asproducts = productDao.get(AActShowProducts.class, act.getProActId());
//				if (asproducts != null) {
//					info.setInventory(asproducts.getStockCount());
//				}
//			} else {
				info.setInventory(count);
//			}
			info.setProductNum(count);
			info.setIsAgent(false);
			info.setIsStore(false);
			info.setSupplierWeiId(supWeiID);
			if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier) || BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier)) {
				ProductInfo proInfo = new ProductInfo();
				// 判断是不是该产品的代理商落地店
				UDemandProduct demand = baseDao.getUniqueResultByHql("from UDemandProduct where productId=?", productid);
				if (weiNo > 0) {
					if (demand != null) {
						List<USupplyChannel> chanList = baseDao.find("from USupplyChannel where demandId=? and weiId=? and state=?", new Object[] { demand.getDemandId(), weiNo, Short.parseShort(AgentStatusEnum.Normal.toString()) });
						if (chanList != null && chanList.size() > 0) {
							for (USupplyChannel chan : chanList) {
								if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
									info.setIsAgent(true);
									proInfo.setCurrentUserIsAgent(1);
								}
								if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
									info.setIsStore(true);
									proInfo.setCurrentUserIsStore(1);
								}
							}
						}
					}
				}
				PPriceShow visible = baseDao.get(PPriceShow.class, supWeiID);
				proInfo = houseService.getPriceShow(user, visible, proInfo);
				if (proInfo.getDlPriceVisibility() == 1)
					info.setIsHasAgentPrice(true);
				if (proInfo.getLdPriceVisibility() == 1)
					info.setIsHasStorePrice(true);

				// 查询代理价 落地价
				PProductRelation relation = baseDao.get(PProductRelation.class, productid);
				if (relation != null) {
					info.setAgentPrice(relation.getMinProxyPrice());
					info.setStorePrice(relation.getMinFloorPrice());
					if (info.getIsHasStorePrice()) {
						info.setCouponsPrice("现金券最高可抵￥" + new DecimalFormat("######0.00").format(relation.getMinFloorPrice() * 0.1));
					} else {
						info.setCouponsPrice("首单即赠等值现金券");
					}
				}
				// 落地店首次采购金额
				if (demand != null) {
					info.setDemandId(demand.getDemandId());
					info.setDemandProductUrl("http://" + domain + "/v5/pph/txllist?tiket=" + tiket + "&parid=" + String.valueOf(demand.getDemandId()) + "&source=" + String.valueOf(source));
					USupplyDemand supply = baseDao.get(USupplyDemand.class, demand.getDemandId());
					if (supply != null) {
						info.setStoreBuyAmount(supply.getOrderAmout());
					}
				}
			}
			//验证用户是否拥有该产品的代理身份，获取查看代理价的权限
			if(product.getPublishType()!=null&&product.getPublishType()==1){
				DBrandSupplier dRelation= baseDao.getNotUniqueResultByHql("from DBrandSupplier where weiId=?", product.getSupplierWeiId());
				DAgentInfo dAgentInfo= baseDao.getNotUniqueResultByHql("from DAgentInfo where weiId=?", user.getWeiID());
				if(dRelation!=null&&dAgentInfo!=null&&dRelation.getBrandId().intValue()==dAgentInfo.getBrandId().intValue()){
					if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentDuke)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getDukePrice());
					}else if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentDeputyDuke)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getDeputyPrice());
					}else if(BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentBrandAgent)){
						info.setIsHasAgentPrice(true);
						info.setAgentPrice(product.getAgentPrice());
					}else{
						info.setIsHasAgentPrice(false);
					}
				}else{
					info.setIsHasAgentPrice(false);
				}
			}else{
				info.setIsHasAgentPrice(false);
			}
			// 获取商品的销售属性
			info.setSellAttr(GetSellAttrByProductId(product.getProductId(), info.getIsHasStorePrice()).trim());
			// 分享业务 添加原价
			info.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
			
			/*----------------限时抢购产品-------------------*/ 
//			ActivityModel model = new ActivityModel();
//			model.setStateImage("http://base.okwei.com/images/828/noacttip20160810.png"); //
//			if (act != null) {
//				AActivityProducts actProducts = baseDao.get(AActivityProducts.class, act.getProActId());
//				if (actProducts != null && actProducts.getState() == Short.parseShort(ActProductVerState.Ok.toString())) {
//					AActivity actModel = baseDao.get(AActivity.class, actProducts.getActId());
//					if (actModel != null) {
//						model.setActId(actProducts.getActId().intValue());
//						model.setTitle(actModel.getTitle());
//						model.setBeginTime(DateUtils.format(act.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
//						model.setEndTime(DateUtils.format(act.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
//						model.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//						if(product.getPublishType()!=null&&product.getPublishType()>0){
//							model.setActPrice(product.getDefaultPrice());
//						}else {
//							model.setActPrice(actProducts.getPrice());
//						}
//						model.setState(1);
//						model.setStateText("抢购中");
//						AActivity activity=baseDao.get(AActivity.class, act.getActId());
//						if(activity!=null&&activity.getType()!=null&&activity.getType()==1){
//							model.setStateImage("http://base.okwei.com/images/828/act828tip20160808.png");
//						}else {
//							model.setStateImage("http://base.okwei.com/images/828/acttip20160808.png");
//						}
//						String key = "BuyLimitCount_" + user.getWeiID() + "_" + act.getActPid() + "_" + act.getProductId();
//						int countAct = ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key)));
//						if (countAct >= 5) {
//							model.setBuyNumLimit(0);
//						} else {
//							model.setBuyNumLimit(5 - countAct);
//						}
//					}
//				}
//			}
//			info.setActivityModel(model);
			info=getActivityModel(user.getWeiID(),productid,info); 
		}
		
		return info;
	}

	//TODO 
	public MsgProductInfo getActivityModel(Long weiid, Long productid,MsgProductInfo info){
		PProducts product=baseDao.get(PProducts.class, productid);
		if(product==null)
			return info;
		ActivityModel model=new ActivityModel();
//		model.setStateImage("http://base.okwei.com/images/828/noacttip20160810.png"); //
		AActProductsShowTime act =  productSearchDao.get_ProductAct(productid); // actService.getAActProductsShowTime(productid, true);
		if (act != null) {
			Date nowtime=new Date(); 
			if(act.getBeginTime().getTime()<=nowtime.getTime()&&act.getEndTime().getTime()>=nowtime.getTime()){//已经开始了
				model.setState(1);
				model.setStateText("抢购中");
				info.setBatch(false);
				AActShowProducts asproducts = null;
				asproducts = productDao.get(AActShowProducts.class, act.getProActId());
				if (asproducts != null) {
					info.setInventory(asproducts.getStockCount());
				}
			}else {
				model.setStateText("未开始");
			}
			AActivityProducts actProducts = baseDao.get(AActivityProducts.class, act.getProActId());
			if (actProducts != null && actProducts.getState() == Short.parseShort(ActProductVerState.Ok.toString())) {
				AActivity actModel = baseDao.get(AActivity.class, actProducts.getActId());
				if (actModel != null) {
					model.setActId(actProducts.getActId().intValue());
					model.setTitle(actModel.getTitle());
					model.setBeginTime(DateUtils.format(act.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
					model.setEndTime(DateUtils.format(act.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
					model.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					if(product.getPublishType()!=null&&product.getPublishType()>0){
						model.setActPrice(product.getDefaultPrice());
						model.setActId(0); 
					}else {
						model.setActPrice(actProducts.getPrice());
					}
					
					AActivity activity=baseDao.get(AActivity.class, act.getActId());
					if(activity!=null&&activity.getType()!=null&&activity.getType()==1){
						model.setStateImage("http://base.okwei.com/images/828/act828tip20160808.png");
					}else {
						model.setStateImage("http://base.okwei.com/images/828/acttip20160808.png");
					}
					String key = "BuyLimitCount_" + weiid + "_" + act.getActPid() + "_" + act.getProductId();
					int countAct = ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key)));
					if (countAct >= 5) {
						model.setBuyNumLimit(0);
					} else {
						model.setBuyNumLimit(5 - countAct);
					}
				}
			}
		} 
		info.setActivityModel(model);
		return info;
	}
	
	private List<EnsureType> find_EnsureTypes(){
		List<EnsureType> liste = new ArrayList<EnsureType>();
		EnsureType et = new EnsureType();
		et.setName("正品保障");
		et.setDesc("正品保障");
		et.setUrl("http://base.okimgs.com/appimages/zhengpinbaozhang.png");
		liste.add(et);
		EnsureType et1 = new EnsureType();
		et1.setName("人工质检");
		et1.setDesc("人工质检");
		et1.setUrl("http://base1.okimgs.com/appimages/rengongzhijian.png");
		liste.add(et1);
		EnsureType et2 = new EnsureType();
		et2.setName("担保交易");
		et2.setDesc("担保交易");
		et2.setUrl("http://base2.okimgs.com/appimages/danbaojiaoyi.png");
		liste.add(et2);
		EnsureType et3 = new EnsureType();
		et3.setName("闪电发货");
		et3.setDesc("闪电发货");
		et3.setUrl("http://base3.okimgs.com/appimages/shandianfahuo.png");
		liste.add(et3);
		return liste;
	}
	
	private MsgProductInfo getMsgProductInfo(Integer supType,MsgProductInfo info){
		supType=supType==null?0:supType;
		// 供应商 5.0修改
		// 普通微店
		if (BitOperation.verIdentity(supType, UserIdentityType.commonUser)) {
			info.setSupType("普通微店");
		}
		if(BitOperation.isIdentity(supType, UserIdentityType.AgentBrandSupplier)){
			info.setSupType("代理区品牌供应商");
		}
		// 云商通
		if (BitOperation.verIdentity(supType, UserIdentityType.yunSupplier)) {
			info.setSupType("工厂号供应商");
			info.setIdentityCategoryImg("http://base3.okimgs.com/images/factory.png");
		}
		// 批发号
		if (BitOperation.verIdentity(supType, UserIdentityType.batchSupplier)) {
			info.setSupType("批发号供应商");
			info.setIdentityCategoryImg("http://base3.okimgs.com/images/batch.png");
		}
		// 平台号
		if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier)) {
			info.setSupType("平台供应商");
		}
		// 品牌号
		if (BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier)) {
			info.setSupType("品牌号供应商");
		}
		return info;
	}
	

	private String getImageById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo = baseDao.get(UShopInfo.class, weiNo);
		if (shopInfo != null && shopInfo.getShopImg() != null && !"".equals(shopInfo.getShopImg()))
			return ImgDomain.GetFullImgUrl(shopInfo.getShopImg());
		UBatchSupplyer bsup = baseDao.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getShopPic() != null && !"".equals(bsup.getShopPic())) {
			return ImgDomain.GetFullImgUrl(bsup.getShopPic());
		}
		UWeiSeller seller = baseDao.get(UWeiSeller.class, weiNo);
		return seller == null ? "" : ImgDomain.GetFullImgUrl(seller.getImages());
	}

	

	/**
	 * 获取商品的销售属性
	 * 
	 * @param productid
	 * @return
	 */

	public String GetSellAttrByProductId(Long productid, boolean isShowLandPrice) {

		// 先获取销售属性里面的Key里面的值，然后递归
		SellPropertyName spn = new SellPropertyName();
		String hql = " from PProductSellKey p where p.productId=? order by p.sort";
		Object[] b = new Object[1];
		b[0] = productid;
		List<PProductSellKey> lists = baseDao.find(hql, b);
		if (lists == null || lists.size() < 1)// 如果没有属性值返回为{}
		{
			return "{}";
		}
		int listsize = lists.size();
		PProductSellKey first = lists.get(0);// 获取第一条记录
		lists.remove(0);// 把第一项去掉
		spn.setPropertyName(first.getAttributeName());
		hql = " from PProductSellValue p where p.attributeId=? order by p.keyId";
		b[0] = first.getAttributeId();
		List<PProductSellValue> listkey = baseDao.find(hql, b); // 获取销售属性key里面的value值
		List<SellPropertyValue> listsell = new ArrayList<SellPropertyValue>();
		for (int i = 0; i < listkey.size(); i++) {
			PProductSellValue pv = listkey.get(i);
			SellPropertyValue sv = new SellPropertyValue();
			sv.setProteryValue(pv.getValue());
			if (listsize == 1)// 只有一个销售属性的value值
			{
				hql = " from PProductStyleKv p where p.productId=? and p.attributeId=? and p.keyId=?";
				Object[] ob = new Object[3];
				ob[0] = productid;
				ob[1] = first.getAttributeId();
				ob[2] = pv.getKeyId();
				PProductStyleKv kv = baseDao.getUniqueResultByHql(hql, ob); // 根据key
				if (kv != null) {
					// value里面的主键ID和
					// productid获取款式ID值
					PProductStyles ps = baseDao.get(PProductStyles.class, kv.getStylesId());
					sv.setConmision(ps.getConmision());
					sv.setPriceImg(ImgDomain.GetFullImgUrl(ps.getDefaultImg()));
					sv.setPriceProperty(null);
					sv.setSalePrice(ps.getPrice());
					int count = ps.getCount() != null ? ps.getCount() : 0;
					count = count < 0 ? 0 : count;
					AActProductsShowTime asp = actService.getAActProductsShowTime(productid, true);
					AActShowProducts asproducts = new AActShowProducts();
					if (asp != null) {
						asproducts = productDao.get(AActShowProducts.class, asp.getProActId());
						if (asproducts != null) {
							sv.setStockCount(asproducts.getStockCount());
						}
					} else {
						sv.setStockCount(count);
					}
					sv.setStylesId(ps.getStylesId());
					sv.setAgentPrice(ps.getAgentPrice());
					sv.setDeputyPrice(ps.getDeputyPrice());
					sv.setDukePrice(ps.getDukePrice());
					sv.setStorePrice(ps.getLandPrice());
					if (isShowLandPrice) {
						if (ps.getLandPrice() != null && ps.getLandPrice().doubleValue() > 0) {
							sv.setCouponsPrice("现金券最高可抵￥" + new DecimalFormat("######0.00").format(ps.getLandPrice() * 0.1));
						}
					} else {
						sv.setCouponsPrice("首单即赠等值现金券");
					}
					listsell.add(sv);
					continue;
				}
			} else {
				Map<Long, Long> m = new HashMap<Long, Long>();// m为 key value值
				m.put(pv.getAttributeId(), pv.getKeyId());
				sv.setConmision(0.0);
				sv.setPriceImg("");
				sv.setSalePrice(0.0);
				sv.setDeputyPrice(0.0);
				sv.setStockCount(0);
				sv.setDukePrice(0.0);
				sv.setStylesId((long) -1);
				sv.setAgentPrice(0.0);
				sv.setStorePrice(0.0);
				List<PProductSellKey> listskey = new ArrayList<PProductSellKey>();
				listskey.addAll(lists);
				SellPropertyName sn = getSellPropertyList(listskey, productid, m, isShowLandPrice); // 获取单个key
				// value值下的其它所有属性
				sv.setPriceProperty(sn);
				listsell.add(sv);
				continue;
			}
		}
		spn.setProteryValuesList(listsell);
		String returnMsg = JsonUtil.objectToJson(spn);

		return returnMsg;
	}

	public SellPropertyName getSellPropertyList(List<PProductSellKey> likey, Long productid, Map<Long, Long> m, boolean isShowLandPrice) {

		SellPropertyName current = new SellPropertyName();
		int listsize = likey.size();
		if (listsize < 1) // 错误处理
			return null;
		PProductSellKey first = likey.get(0);// 获取第一条记录
		likey.remove(0);// 去掉第一条记录
		current.setPropertyName(first.getAttributeName());
		String hql = " from PProductSellValue p where p.attributeId=? order by p.keyId"; // 获取第一层的value值
		Object[] b = new Object[1];
		b[0] = first.getAttributeId();
		List<PProductSellValue> listkey = baseDao.find(hql, b);
		List<SellPropertyValue> listsell = new ArrayList<SellPropertyValue>();
		for (int i = 0; i < listkey.size(); i++) {
			PProductSellValue pv = listkey.get(i);
			SellPropertyValue sv = new SellPropertyValue();
			sv.setProteryValue(pv.getValue());
			if (listsize == 1)// 只有一个销售属性
			{
				// 获取款色ID
				hql = " from PProductStyleKv p where p.productId=?";
				b[0] = productid;
				Map<Long, Long> newMap = new HashMap<Long, Long>();
				newMap.putAll(m);
				newMap.put(first.getAttributeId(), pv.getKeyId());
				List<PProductStyleKv> listkv = baseDao.find(hql, b);
				Long styleId = GetStylesID(listkv, newMap);
				if (styleId == null)
					continue;
				PProductStyles ps = baseDao.get(PProductStyles.class, styleId);
				sv.setConmision(ps.getConmision());
				sv.setPriceImg("");
				sv.setPriceProperty(null);
				sv.setSalePrice(ps.getPrice());
				int count = ps.getCount() != null ? ps.getCount() : 0;
				count = count < 0 ? 0 : count;
				sv.setStockCount(count);
				sv.setStylesId(ps.getStylesId());
				sv.setAgentPrice(ps.getAgentPrice());
				sv.setDeputyPrice(ps.getDeputyPrice());
				sv.setDukePrice(ps.getDukePrice());
				sv.setStorePrice(ps.getLandPrice());
				if (isShowLandPrice) {
					if (ps.getLandPrice() != null && ps.getLandPrice().doubleValue() > 0) {
						sv.setCouponsPrice("现金券最高可抵￥" + new DecimalFormat("######0.00").format(ps.getLandPrice() * 0.1));
					}
				} else {
					sv.setCouponsPrice("首单即赠等值现金券");
				}
				listsell.add(sv);
				continue;
			} else {
				Map<Long, Long> newMap = new HashMap<Long, Long>();
				newMap.putAll(m);
				newMap.put(first.getAttributeId(), pv.getKeyId());
				sv.setConmision(0.0);
				sv.setPriceImg("");
				sv.setSalePrice(0.0);
				sv.setStockCount(0);
				sv.setStylesId((long) -1);
				sv.setAgentPrice(0.0);
				sv.setDeputyPrice(0.0);
				sv.setDukePrice(0.0);
				sv.setStorePrice(0.0);
				SellPropertyName sn = getSellPropertyList(likey, productid, newMap, isShowLandPrice);
				sv.setPriceProperty(sn);
				listsell.add(sv);
				continue;
			}
		}
		current.setProteryValuesList(listsell);

		return current;
	}

	// 获取款色ID
	public Long GetStylesID(List<PProductStyleKv> kv, Map<Long, Long> m) {
		if (kv.size() == 1) // 款式KV只有一项
		{
			// modify by tan 20150616
			for (Long key : m.keySet()) {
				if (kv.get(0).getAttributeId().longValue() == key && kv.get(0).getKeyId().longValue() == m.get(key)) {
					return kv.get(0).getStylesId();
				}
			}
			return null;
		}
		// modify by tan 20150616 修改获取款色ID列表
		List<List<Long>> lists = new ArrayList<List<Long>>();
		for (Long key : m.keySet()) {
			List<Long> list = new ArrayList<Long>();
			for (PProductStyleKv ps : kv) {
				if (ps.getAttributeId().longValue() == key.longValue() && ps.getKeyId().longValue() == m.get(key).longValue()) {
					list.add(ps.getStylesId());
				}
			}
			lists.add(list);
		}
		if (lists.size() < 1) {
			return null;
		}
		List<Long> temp = lists.get(0);// 获取第一项
		for (int i = 1; i < lists.size(); i++) {
			temp.retainAll(lists.get(i));
		}
		if (temp.size() < 0)
			return null;
		else {
			if (temp.size() == 1)
				return temp.get(0);
			else
				return null;
		}
	}

	@Override
	public ProductPrice getProductPrice(Long productid, Long styleId) {
		ProductPrice result = null;
		AActProductsShowTime asp = actService.getAActProductsShowTime(productid, true);
		PProductStyles model = baseDao.getUniqueResultByHql("from PProductStyles where stylesId=? and productId=?", new Object[] { styleId, productid });
		if (model != null) {
			result = new ProductPrice();
			result.setPrice(model.getPrice());
			result.setStorePrice(model.getLandPrice());
			result.setAgentPrice(model.getAgentPrice());
			result.setDeputyPrice(model.getDeputyPrice());
			result.setDukePrice(model.getDukePrice());
			AActShowProducts asproducts = new AActShowProducts();
			if (asp != null) {
				asproducts = productDao.get(AActShowProducts.class, asp.getProActId());
				if (asproducts != null) {
					result.setInventory(asproducts.getStockCount());
				}
			} else {
				result.setInventory(model.getCount());
			}
		}
		return result;
	}

	/**
	 * 判断是否能够购买产品
	 * 
	 * @param supType
	 * @return
	 */
	private boolean isSupplyer(Integer supType) {
		if (supType == null)
			return false;
		if (BitOperation.verIdentity(supType, UserIdentityType.yunSupplier))
			return true;
		if (BitOperation.verIdentity(supType, UserIdentityType.batchSupplier))
			return true;
		if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier))
			return true;
		if (BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier))
			return true;
		if (BitOperation.verIdentity(supType, UserIdentityType.Agent))
			return true;
		if (BitOperation.verIdentity(supType, UserIdentityType.Ground))
			return true;
//		if (BitOperation.verIdentity(supType, UserIdentityType.AgentBrandSupplier))
//		    return true;
		return false;
	}
}
