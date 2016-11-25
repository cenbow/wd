package com.okwei.detail.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActShowProducts;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductComment;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductKeyWords;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.SupplierStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.activity.ActProductInfo;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.JsonUtil;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.detail.bean.vo.Product;
import com.okwei.detail.bean.vo.ProductDetail;
import com.okwei.detail.bean.vo.SellPropertyName;
import com.okwei.detail.bean.vo.SellPropertyValue;
import com.okwei.detail.bean.vo.SupplierInfo;
import com.okwei.detail.dao.IProductDao;
import com.okwei.detail.service.IProductService;
import com.okwei.detail.util.LocalRedisUtil;
import com.okwei.service.IRegionService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.product.IHouseService;
import com.okwei.util.BitOperation;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;

@Service
public class ProductService extends BaseService implements IProductService {

    @Autowired
    private IProductDao productDao;
    
    @Autowired
    private IProductSearchDao iSearchDao;

    @Autowired
    private IRegionService regionService;
    
    @Autowired
    private IBaseActivityService activityService;
    
    /*
     * @Autowired private IproductDao productDao;
     */

    @Override
    public ProductDetail getDetail(LoginUser user, long sid, long pid, int f, long w) {
	long shopid = 0;
	if(sid==0 && pid==0)
		return null;
	// 优先使用上架ID
	if (sid > 0) {
	    PClassProducts sjProduct = productDao.get(PClassProducts.class, sid);
	    if (sjProduct != null) {
		pid = sjProduct.getProductId();
		shopid = sjProduct.getWeiId();// 店铺过来的佣金分给上架人
	    }
	}
	ProductDetail result = null;
	String keyName = "ProductInformation" + String.valueOf(pid);
	try {
	    result = (ProductDetail) LocalRedisUtil.getObject(keyName);
	} catch (Exception e) {

	}
	PProducts product = productDao.getProducts(pid);
	if (result == null) {
		if(product==null)
			return null;
	    result = new ProductDetail();
	    // 查询上架表
	    SupplierInfo supInfo = new SupplierInfo();
	    List<String> imageList = new ArrayList<String>();
	    List<String> bigImageList = new ArrayList<String>();
	    if (product != null) {
		result.setForm(f);
		// 判断该产品是否属于平台号，品牌号
		if(product.getSupperType()!=null){
			short supType = product.getSupperType();
			if (!(BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier) || BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier))) {
			    result.setForm(0);
			}
		}
		result.setProID(pid);
		result.setCount(product.getCount());
		result.setProTitle(product.getProductTitle());
		result.setProMinTitle(product.getProductMinTitle());
		result.setProDes(product.getPcdes());
		result.setSupType(product.getSupperType()==null ? 0:product.getSupperType());
		result.setTag(product.getTag()==null ? 0:product.getTag());
		result.setSupWeiID(product.getSupplierWeiId());
		//result.setOrignalPrice(product.getOriginalPrice() == null ? product.getDefaultPrice() : product.getOriginalPrice());
		if (null != product.getOriginalPrice()) {
			result.setOrignalPrice(product.getOriginalPrice());
		}else{
			double percent = 1.5;
			double displayPrice = product.getDefaultPrice() * percent;
			DecimalFormat df = new DecimalFormat("#.00");
    		displayPrice = Double.parseDouble(df.format(displayPrice));
    		result.setOrignalPrice(displayPrice);
		}
		
		imageList.add(ImgDomain.GetFullImgUrl(product.getDefaultImg(), 8));
		bigImageList.add(ImgDomain.GetFullImgUrl(product.getDefaultImg(), 75));

		// 查询该供应商的其他的产品
		List<Product> pList = new ArrayList<Product>();
		List<PProducts> proList = productDao.findPage("from PProducts where supplierWeiId=? and productId!=? and state=?", 1, 15, new Object[] { product.getSupplierWeiId(), pid, Short.parseShort(ProductStatusEnum.Showing.toString()) });
		if (proList != null && proList.size() > 0) {
		    for (PProducts pro : proList) {
			Product temp = new Product();
			temp.setProID(pro.getProductId());
			temp.setProTitle(pro.getProductTitle());
			temp.setProImg(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 16));
			temp.setPrice(pro.getDefaultPrice());
			pList.add(temp);
			break;
		    }
		}
		result.setProList(pList);
		// 产品属性*
		result.setProperty(GetSellAttrByProductId(pid));
		long supID = product.getSupplierWeiId();
		// 供应商信息
		result.setSupWeiID(supID);
		result.setState(product.getState());
		supInfo = getSupInfo(supID, product);// 
		// 查询是否实名认证
		UWallet wallet = productDao.getWallet(supID);
		if (wallet != null && wallet.getStatus() != null && "1".equals(wallet.getStatus().toString())) {
		    result.setIsRealName(1);
		}
		Integer ui=1;
		UUserAssist ua= productDao.get(UUserAssist.class, product.getSupplierWeiId());
		if(ua!=null)
		{
			ui=ua.getIdentity();
		}
		if (isSupplyer(ui)) {
		    result.setProductSupType(1);
		}

		if(product.getVerStatus()!=null && product.getVerStatus()!=1&& product.getPublishType()!=null && product.getPublishType()==1){
			result.setProductSupType(0);
		}
	    }
	    result.setSupInfo(supInfo);
	    // 产品图片
	    List<PProductImg> imgList = productDao.getProImgList(pid);
	    if (imgList != null && imgList.size() > 0) {
		for (PProductImg img : imgList) {
		    imageList.add(ImgDomain.GetFullImgUrl(img.getImgPath(), 8));
		    bigImageList.add(ImgDomain.GetFullImgUrl(img.getImgPath(), 75));
		}
	    }
	    result.setImageList(imageList);
	    result.setBigImageList(bigImageList);
	    // 产品参数
	    List<PProductParamKv> paramList = productDao.getParamList(pid);
	    result.setParamList(paramList);
	    // 产品相关数量
	    PProductAssist assist = productDao.getProductAssist(pid);
	    if (assist != null) {
		result.setShelvesCount(assist.getShelvesCount() == null ? 0 : assist.getShelvesCount());
		result.setShareCount(assist.getShareCount() == null ? 0 : assist.getShareCount());
	    }
	    // 查询评论的数量
//	    result.setEvaluateCount(productDao.count("select count(1) from PProductComment where productId=?", new Object[] { pid }));
	    // 查询系列产品
	    if (result.getForm() == 1 || result.getForm() == 2) {
		USupplyDemand demand = getSupplyDemand(product.getProductId());
		if (demand != null) {
		    result.setDemandId(demand.getDemandId());
		    result.setOrderAmount(demand.getOrderAmout());
		    result.setSeriesProduct(getDemandProduct(demand.getDemandId(), product.getProductId()));
		}
	    }
	    LocalRedisUtil.setObject(keyName, result);
	}
	// 如果从店铺过来,制作人为店铺 没有分享人，分享人为店铺
	if (sid > 0) {
	    result.setSharePageProducer(shopid);
	    if (w <= 0) {
		result.setShareOne(shopid);
	    }
	}
	short publishtype=0;
	if(product.getPublishType()!=null && product.getPublishType()>0)
	{
		publishtype=product.getPublishType();
	}
	result.setPublishtype(Integer.parseInt(String.valueOf(publishtype)));

	//是否为正参加活动产品
	//AActProductsShowTime actproduct=activityService.getAActProductsShowTime(pid, true);
	ActProductInfo actproduct=iSearchDao.get_ProductAct(pid);
	if(actproduct!=null){
		if(actproduct.getActiveType()==1){
			AActShowProducts products=productDao.get(AActShowProducts.class, actproduct.getProActId());
			result.setActiveType(actproduct.getActiveType());
			result.setProActId(actproduct.getProActId());
			result.setBeginTime(DateUtils.format(actproduct.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
			result.setEndTime(DateUtils.format(actproduct.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			result.setActPrice(products.getPrice());
			result.setActCount(products.getStockCount());
			result.setActPid(actproduct.getActPid());
			result.setState(1);
		}else{
			AActProductsShowTime act=activityService.getAActProductsShowTime(pid, true);
			if(act!=null){
				AActShowProducts products=productDao.get(AActShowProducts.class, actproduct.getProActId());
				result.setActiveType(actproduct.getActiveType());
				result.setProActId(actproduct.getProActId());
				result.setBeginTime(DateUtils.format(actproduct.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
				result.setEndTime(DateUtils.format(actproduct.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
				result.setActPrice(products.getPrice());
				result.setActCount(products.getStockCount());
				result.setActPid(actproduct.getActPid());
				result.setState(1);
			}	
		}
	}else{
		result.setActiveType(-1);
	}			
	return result;
    }

    /**
     * 获取供应商的信息
     * 
     * @return
     */
    private SupplierInfo getSupInfo(long supID, PProducts product) {
	SupplierInfo supInfo = null;
	String keyName = "SupplierInformation" + String.valueOf(supID) + String.valueOf(product.getSupperType());
	try {
	    supInfo = (SupplierInfo) LocalRedisUtil.getObject(keyName);
	} catch (Exception e) {

	}
	if (supInfo == null) {
	    supInfo = new SupplierInfo();
	    
//	    short supType = product.getSupperType()==null?0:product.getSupperType();
	    
	    int supType=0;
	    UUserAssist ua=productDao.get(UUserAssist.class, supID);
	    if(ua!=null)
	    {
	    	supType=ua.getIdentity();
	    }
	    // 供应商
	    UShopInfo shopInfo = productDao.get(UShopInfo.class, supID);
	    if (shopInfo != null) {
		supInfo.setShopName(shopInfo.getShopName());
		supInfo.setShopImg(ImgDomain.GetFullImgUrl(shopInfo.getShopImg()));
	    }
	    // 供应商的信息以工厂号的为主
	    USupplyer supplyer = productDao.get(USupplyer.class, supID);
	    if (supplyer != null) {
		if (supplyer.getServiceQQ() != null && supplyer.getServiceQQ() != "") {
		    String[] qqStr = supplyer.getServiceQQ().split("\\|");
		    if (qqStr.length > 0) {
			supInfo.setQq(qqStr[0]);
		    }
		}
	    }
	    if (BitOperation.verIdentity(supType, UserIdentityType.yunSupplier)) {
		UYunSupplier yunSupplyer = productDao.get(UYunSupplier.class, supID);
		if (yunSupplyer != null && yunSupplyer.getStatus().equals(Short.parseShort(SupplierStatusEnum.PayIn.toString()))) {
		    if (supInfo.getShopName() == null || supInfo.getShopName() == "") {
			if (supplyer != null) {
			    supInfo.setShopName(supplyer.getCompanyName());
			}
		    }
		    supInfo.setBond((int) (yunSupplyer.getBond() == null ? 0 : yunSupplyer.getBond()));// *
		    String area = "";
		    if (yunSupplyer.getProvince() != null && yunSupplyer.getProvince().intValue() > 0) {
			area += regionService.getNameByCode(yunSupplyer.getProvince());
		    }
		    if (yunSupplyer.getCity() != null && yunSupplyer.getCity().intValue() > 0) {
			area += regionService.getNameByCode(yunSupplyer.getCity());
		    }
		    if (yunSupplyer.getDistrict() != null && yunSupplyer.getDistrict().intValue() > 0) {
			area += regionService.getNameByCode(yunSupplyer.getDistrict());
		    }
		    supInfo.setArea(area);
		    if (supInfo.getQq() == null) {
			if (yunSupplyer.getServiceQq() != null && yunSupplyer.getServiceQq() != "") {
			    String[] qqStr = yunSupplyer.getServiceQq().split("\\|");
			    if (qqStr.length > 0) {
				supInfo.setQq(qqStr[0]);
			    }
			}
		    }
		}
	    } else if (BitOperation.verIdentity(supType, UserIdentityType.batchSupplier)) {
		UBatchSupplyer batchSupplyer = productDao.get(UBatchSupplyer.class, supID);
		if (batchSupplyer != null && batchSupplyer.getStatus().equals(Short.parseShort(SupplierStatusEnum.PayIn.toString()))) {
		    if (supInfo.getShopName() == null || supInfo.getShopName() == "") {
			supInfo.setShopName(batchSupplyer.getShopName());
		    }
		    if (supInfo.getShopImg() == null || supInfo.getShopImg() == "") {
			supInfo.setShopImg(ImgDomain.GetFullImgUrl(batchSupplyer.getShopPic()));
		    }
		    supInfo.setBond((int) (batchSupplyer.getBond() == null ? 0 : batchSupplyer.getBond()));
		    String area = "";
		    if (batchSupplyer.getProvince() != null && batchSupplyer.getProvince().intValue() > 0) {
			area += regionService.getNameByCode(batchSupplyer.getProvince());
		    }
		    if (batchSupplyer.getCity() != null && batchSupplyer.getCity().intValue() > 0) {
			area += regionService.getNameByCode(batchSupplyer.getCity());
		    }
		    if (batchSupplyer.getDistrict() != null && batchSupplyer.getDistrict().intValue() > 0) {
			area += regionService.getNameByCode(batchSupplyer.getDistrict());
		    }
		    supInfo.setArea(area);
		    supInfo.setAddress(batchSupplyer.getShopPosition());
		}
	    } else if (BitOperation.verIdentity(supType, UserIdentityType.PlatformSupplier)) {
		UPlatformSupplyer model = productDao.get(UPlatformSupplyer.class, supID);
		if (model != null) {
		    if (supInfo.getShopName() == null || supInfo.getShopName() == "") {
			supInfo.setShopName(model.getSupplyName());
		    }
		    if (supInfo.getShopImg() == null || supInfo.getShopImg() == "") {
			supInfo.setShopImg(ImgDomain.GetFullImgUrl(model.getLogo()));
		    }
		    supInfo.setBond((int) (model.getBond() == null ? 0 : model.getBond()));
		    String area = "";
		    if (model.getProvice() != null && model.getProvice().intValue() > 0) {
			area += regionService.getNameByCode(model.getProvice());
		    }
		    if (model.getCity() != null && model.getCity().intValue() > 0) {
			area += regionService.getNameByCode(model.getCity());
		    }
		    if (model.getArea() != null && model.getArea().intValue() > 0) {
			area += regionService.getNameByCode(model.getArea());
		    }
		    supInfo.setArea(area);
		    supInfo.setAddress(model.getAddress());
		}
	    } else if (BitOperation.verIdentity(supType, UserIdentityType.BrandSupplier)) {
		UBrandSupplyer model = productDao.get(UBrandSupplyer.class, supID);
		if (model != null) {
		    if (supInfo.getShopName() == null || supInfo.getShopName() == "") {
			supInfo.setShopName(model.getSupplyName());
		    }
		    if (supInfo.getShopImg() == null || supInfo.getShopImg() == "") {
			supInfo.setShopImg(ImgDomain.GetFullImgUrl(model.getLogo()));
		    }
		    supInfo.setBond((int) (model.getBond() == null ? 0 : model.getBond()));
		    String area = "";
		    if (model.getProvice() != null && model.getProvice().intValue() > 0) {
			area += regionService.getNameByCode(model.getProvice());
		    }
		    if (model.getCity() != null && model.getCity().intValue() > 0) {
			area += regionService.getNameByCode(model.getCity());
		    }
		    if (model.getArea() != null && model.getArea().intValue() > 0) {
			area += regionService.getNameByCode(model.getArea());
		    }
		    supInfo.setArea(area);
		    supInfo.setAddress(model.getAddress());
		}
	    }
	    UWeiSeller weiSeller = productDao.get(UWeiSeller.class, supID);
	    if (weiSeller != null) {
		supInfo.setShopTime(DateUtils.format(weiSeller.getRegisterTime(), "yyyy-MM-dd"));
	    }
	    // 查询供应商的产品数量
	    // supInfo.setProCount(productDao.getSupProCount(supID));
	    if (product.getBrandId() != null && product.getBrandId().intValue() > 0) {
		supInfo.setIsBrand(1);
	    }
	    if (supInfo.getShopName() == null || supInfo.getShopName() == "" && supInfo.getShopImg() == null || supInfo.getShopImg() == "") {
		if (weiSeller != null) {
		    if (supInfo.getShopName() == null || supInfo.getShopName() == "") {
			supInfo.setShopName(weiSeller.getWeiName());
		    }
		    if (supInfo.getShopImg() == null || supInfo.getShopImg() == "") {
			supInfo.setShopImg(ImgDomain.GetFullImgUrl(weiSeller.getImages()));
		    }
		}
	    }

	    LocalRedisUtil.setObject(keyName, supInfo);
	}
	return supInfo;
    }

    @Override
    public void saveShareCount(long proID) {
	if (proID > 0) {
	    PProductAssist entity = productDao.getProductAssist(proID);
	    if (entity != null) {
		int count = entity.getShareCount() == null ? 0 : entity.getShareCount();
		count += 1;
		entity.setShareCount(count);
		productDao.updateAssist(entity);
	    } else {
		entity = new PProductAssist();
		entity.setProductId(proID);
		PProducts product = productDao.getProducts(proID);
		if (product != null) {
		    entity.setClassId(product.getClassId());
		    entity.setSupplierId(product.getSupplierWeiId());
		}
		entity.setMonthCount(0);
		entity.setShelvesCount(0);
		entity.setTotalCount(0);
		entity.setEvaluateCount(0);
		entity.setShareCount(1);
		productDao.saveAssist(entity);
	    }
	}
    }

    @Override
    public String getCommentList(long proID, int index, int size) {
	String result = "";
	List<PProductComment> list = productDao.getComments(proID, index, size);
	if (list != null && list.size() > 0) {
	    Long[] weiids = new Long[list.size()];
	    for (int i = 0; i < list.size(); i++) {
		weiids[i] = list.get(i).getWeiid();
	    }
	    // 获取用户信息
	    List<UWeiSeller> sellers = productDao.getSellers(weiids);

	    for (PProductComment comment : list) {
		UWeiSeller temp = null;
		if (sellers != null && sellers.size() > 0) {
		    for (UWeiSeller seller : sellers) {
			if (comment.getWeiid().equals(seller.getWeiId())) {
			    temp = seller;
			    break;
			}
		    }
		}
		String weiName = "";
		String weiImg = "";
		if (temp != null) {
		    weiName = temp.getWeiName();
		    weiImg = ImgDomain.GetFullImgUrl(temp.getImages(), 5);
		}
		String time = DateUtils.format(comment.getCreateTime(), "yyyy年MM月dd日");
		result += "<div class='liuyan_one'><div class='liuyan_one_l'><a href='http://www." + comment.getWeiid() + ".okwei.com/'><img style='width: 45px; height: 45px' src='" + weiImg + "'></a></div><div class='liuyan_one_c'><ul><li class='liuyan_one_c_1'><a href='http://www." + comment.getWeiid() + ".okwei.com/'>" + weiName + "</a></li><li class='liuyan_one_c_2'>" + comment.getContent() + "</li></ul></div><div class='liuyan_one_r'>" + time + "</div></div>";
	    }
	}
	return result;
    }

    @Override
    public double getPostage(long proID, int province, int city, int district, int count) {
	double result = 0;
	PProducts product = productDao.getProducts(proID);
	if (product != null) {
	    int freightId = product.getFreightId() == null ? -1 : product.getFreightId();
	    if (freightId > 0) {
		List<PPostAgeDetails> list = productDao.getAgeDetails(freightId);
		if (list != null && list.size() > 0) {
		    List<PPostAgeDetails> tempList = new ArrayList<PPostAgeDetails>();
		    for (PPostAgeDetails model : list) {
			// 计算默认的邮费
			if ("0".equals(model.getStatus().toString())) {
			    tempList.add(model);
			    continue;
			}
			// 特殊地区的计算
			if (model.getDestination().indexOf("|" + String.valueOf(province) + "|") >= 0) {
			    tempList.add(model);
			    continue;
			} else if (model.getDestination().indexOf("|" + String.valueOf(city) + "|") >= 0) {
			    tempList.add(model);
			    continue;
			} else if (model.getDestination().indexOf("|" + String.valueOf(district) + "|") >= 0) {
			    tempList.add(model);
			    continue;
			}
		    }
		    if (tempList.size() > 0) {
			if (tempList.size() == 1) {
			    // 只有一条记录取第一条默认记录
			    result = getFreight(tempList.get(0), count);
			} else {
			    List<Double> dlist = new ArrayList<Double>();
			    for (int i = 0; i < tempList.size(); i++) {
				if (i > 0) {
				    double d = getFreight(tempList.get(i), count);
				    dlist.add(d);
				}
			    }
			    result = Collections.min(dlist);
			}
		    } else {
			result = -1;
		    }
		} else {
		    result = -1;// 获取邮费模板失败
		}
	    } else {
		// 判断是不是赠品
		if ("1".equals(product.getbType().toString())) {
		    return count * 10;// 赠品10元每件
		} else {
		    result = product.getDefPostFee() == null ? 0 : product.getDefPostFee();
		    if (result < 0)
			result = 0;
		}
	    }
	} else {
	    result = -1;// 邮费加载失败
	}
	return result;
    }

    private double getFreight(PPostAgeDetails detail, int count) {
	double d = detail.getFirstpiece();
	if (count - detail.getFirstCount() > 0) {
	    int newCount = (count - detail.getFirstCount()) / detail.getMoreCount();
	    int newYu = (count - detail.getFirstCount()) % detail.getMoreCount();
	    if (newYu > 0)
		newCount++;
	    d += newCount * detail.getMorepiece();
	}
	return d;
    }

    /**
     * 获取商品的销售属性
     * 
     * @param productid
     * @return
     */
    private String GetSellAttrByProductId(Long productid) {
	// 先获取销售属性里面的Key里面的值，然后递归
	SellPropertyName spn = new SellPropertyName();
	String hql = " from PProductSellKey p where p.productId=? order by p.sort";
	Object[] b = new Object[1];
	b[0] = productid;
	List<PProductSellKey> lists = productDao.find(hql, b);
	if (lists == null || lists.size() < 1)// 如果没有属性值返回为{}
	{
	    return "{}";
	}
	int listsize = lists.size();
	PProductSellKey first = lists.get(0);// 获取第一条记录
	lists.remove(0);// 把第一项去掉
	if ("-1".equals(first.getAttributeName())) {
	    spn.setPropertyName("默认");
	} else {
	    spn.setPropertyName(first.getAttributeName());
	}
	hql = " from PProductSellValue p where p.attributeId=? order by p.keyId";
	b[0] = first.getAttributeId();
	List<PProductSellValue> listkey = productDao.find(hql, b); // 获取销售属性key里面的value值
	List<SellPropertyValue> listsell = new ArrayList<SellPropertyValue>();
	for (int i = 0; i < listkey.size(); i++) {
	    PProductSellValue pv = listkey.get(i);
	    SellPropertyValue sv = new SellPropertyValue();
	    if ("-1".equals(pv.getValue())) {
		sv.setProteryValue("默认");
	    } else {
		sv.setProteryValue(pv.getValue());
	    }
	    if (listsize == 1)// 只有一个销售属性的value值
	    {
		hql = " from PProductStyleKv p where p.productId=? and p.attributeId=? and p.keyId=?";
		Object[] ob = new Object[3];
		ob[0] = productid;
		ob[1] = first.getAttributeId();
		ob[2] = pv.getKeyId();
		PProductStyleKv kv = productDao.getUniqueResultByHql(hql, ob); // 根据key
		// value里面的主键ID和
		// productid获取款式ID值
		// 8.12hjd修改
		if (kv == null) {
		    continue;
		}
		PProductStyles ps = productDao.get(PProductStyles.class, kv.getStylesId());
		sv.setCommision(ps.getConmision());
		sv.setPriceImg(ImgDomain.GetFullImgUrl(ps.getDefaultImg()));
		sv.setPriceProperty(null);
		sv.setSalePrice(ps.getPrice());
		sv.setStockCount(ps.getCount());
		sv.setStylesId(ps.getStylesId());
		sv.setStorePrice(ps.getLandPrice());
		sv.setAgentPrice(ps.getAgentPrice());
		sv.setDeputyPrice(ps.getDeputyPrice());
		sv.setDukePrice(ps.getDukePrice());
		listsell.add(sv);
		continue;
	    } else {
		Map<Long, Long> m = new HashMap<Long, Long>();// m为 key value值
		m.put(pv.getAttributeId(), pv.getKeyId());
		sv.setCommision(0.0);
		sv.setPriceImg("");
		sv.setSalePrice(0.0);
		sv.setStockCount(0);
		sv.setStylesId((long) -1);
		sv.setStorePrice(0.0);
		sv.setAgentPrice(0.0);
		sv.setDeputyPrice(0.0);
		sv.setDukePrice(0.0);
		List<PProductSellKey> listskey = new ArrayList<PProductSellKey>();
		listskey.addAll(lists);
		SellPropertyName sn = getSellPropertyList(listskey, productid, m); // 获取单个key
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

    private SellPropertyName getSellPropertyList(List<PProductSellKey> likey, Long productid, Map<Long, Long> m) {

	SellPropertyName current = new SellPropertyName();
	int listsize = likey.size();
	if (listsize < 1) // 错误处理
	    return null;
	PProductSellKey first = likey.get(0);// 获取第一条记录
	likey.remove(0);// 去掉第一条记录
	if ("-1".equals(first.getAttributeName())) {
	    current.setPropertyName("默认");
	} else {
	    current.setPropertyName(first.getAttributeName());
	}
	String hql = " from PProductSellValue p where p.attributeId=? order by p.keyId"; // 获取第一层的value值
	Object[] b = new Object[1];
	b[0] = first.getAttributeId();
	List<PProductSellValue> listkey = productDao.find(hql, b);
	List<SellPropertyValue> listsell = new ArrayList<SellPropertyValue>();
	for (int i = 0; i < listkey.size(); i++) {
	    PProductSellValue pv = listkey.get(i);
	    SellPropertyValue sv = new SellPropertyValue();
	    sv.setProteryValue(pv.getValue());
	    if ("-1".equals(pv.getValue())) {
		sv.setProteryValue("默认");
	    } else {
		sv.setProteryValue(pv.getValue());
	    }
	    if (listsize == 1)// 只有一个销售属性
	    {
		// 获取款色ID
		hql = " from PProductStyleKv p where p.productId=?";
		b[0] = productid;
		Map<Long, Long> newMap = new HashMap<Long, Long>();
		newMap.putAll(m);
		newMap.put(first.getAttributeId(), pv.getKeyId());
		List<PProductStyleKv> listkv = productDao.find(hql, b);
		Long styleId = GetStylesID(listkv, newMap);
		if (styleId == null)
		    continue;
		PProductStyles ps = productDao.get(PProductStyles.class, styleId);
		if (ps == null)
		    continue;
		sv.setCommision(ps.getConmision());
		sv.setPriceImg(ImgDomain.GetFullImgUrl(ps.getDefaultImg(), 8));
		sv.setPriceProperty(null);
		sv.setDeputyPrice(ps.getDeputyPrice()==null?0.0:ps.getDeputyPrice());
		sv.setSalePrice(ps.getPrice()==null?0.0:ps.getPrice());
		sv.setStockCount(ps.getCount()==null?0:ps.getCount());
		sv.setStylesId(ps.getStylesId());
		sv.setAgentPrice(ps.getAgentPrice()==null?0.0:ps.getAgentPrice());
		sv.setDukePrice(ps.getDukePrice()==null?0.0:ps.getDukePrice());
		sv.setStorePrice(ps.getLandPrice()==null?0.0:ps.getLandPrice());
		listsell.add(sv);
		continue;
	    } else {
		Map<Long, Long> newMap = new HashMap<Long, Long>();
		newMap.putAll(m);
		newMap.put(first.getAttributeId(), pv.getKeyId());
		sv.setCommision(0.0);
		sv.setPriceImg("");
		sv.setSalePrice(0.0);
		sv.setStockCount(0);
		sv.setDeputyPrice(0.0);
		sv.setDukePrice(0.0);
		sv.setAgentPrice(0.0);
		sv.setStorePrice(0.0);
		sv.setStylesId((long) -1);
		SellPropertyName sn = getSellPropertyList(likey, productid, newMap);
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
    public void attentionSup(long userID, int type, long supID) {
	if (type == 1) {
	    // 取消关注
	    productDao.deleteAttention(userID, supID);
	} else {
	    // 关注
	    if (!productDao.getIsAttention(userID, supID)) {
		UAttention entity = new UAttention();
		entity.setAttentioner(userID);
		entity.setAttTo(supID);
		entity.setStatus((short) 1);
		entity.setCreateTime(new Date());
		productDao.addAttention(entity);
	    }
	    if (!productDao.getIsAttentioned(userID, supID)) {
		UAttentioned entity = new UAttentioned();
		entity.setAttentioner(userID);
		entity.setAttTo(supID);
		entity.setStatus((short) 1);
		entity.setCreateTime(new Date());
		productDao.addAttention(entity);
	    }
	}
    }

    @Override
    public String addCart(List<TShoppingCar> cartlist, long weiid) {
	if (cartlist == null || cartlist.size() <= 0) {
	    return "0";
	}
	Long proid = cartlist.get(0).getProNum();
	// 先查询出产品
	PProducts product = productDao.getProducts(proid);
	if (product == null) {
	    return "0";
	}
	long supid = product.getSupplierWeiId();

	int i = 0;
	for (TShoppingCar cart : cartlist) {
	    cart.setWeiId(weiid);
	    cart.setSupplierWeiId(supid);
	    cart.setImage(product.getDefaultImg());
	    cart.setProTitle(product.getProductTitle());
	    cart.setStatus((short) 1);
	    cart.setCreateTime(new Date());
	    // 查询购物车是否有该产品
	    TShoppingCar shopCart = productDao.getShopCart(proid, weiid, cart.getStyleId(), supid, cart.getBuyType(), cart.getShopWeiID());
	    if (shopCart != null) {
		shopCart.setSellerWeiId(cart.getSellerWeiId());
		shopCart.setSupplierWeiId(cart.getSupplierWeiId());
		shopCart.setCount(shopCart.getCount() + cart.getCount());
		shopCart.setPrice(cart.getPrice());
		shopCart.setImage(cart.getImage());
		shopCart.setProTitle(cart.getProTitle());
		shopCart.setBuyType(cart.getBuyType());
		shopCart.setProperty1(cart.getProperty1());
		shopCart.setStatus(cart.getStatus());
		shopCart.setCreateTime(new Date());
		productDao.update(shopCart);
	    } else {
		productDao.save(cart);
		i++;
	    }
	}
	// 返回购物车数量
	String hql = "select count(1) from TShoppingCar where weiId=? and status=1";
	long count = productDao.count(hql, new Object[] { weiid });
	if (count == 0) {
	    count += i;
	}
	return String.valueOf(count);
    }

    @Override
    public boolean getIsAttention(long userID, long supID) {
	return productDao.getIsAttention(userID, supID);
    }

    @Override
    public List<PShevleBatchPrice> getBatchPrices(long proid, long supid) {
	List<PShevleBatchPrice> result = null;
	String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
	PClassProducts model = productDao.getUniqueResultByHql(hql, new Object[] { supid, proid });
	if (model != null) {
	    result = productDao.getBatchPrices(model.getId());
	}
	return result;
    }

    @Override
    public List<PShevleBatchPrice> getBatchPrices(long id) {
	return productDao.getBatchPrices(id);
    }

    @Override
    public PPreOrder getPreOrder(long proID) {
	return productDao.getPreOrder(proID);
    }

    @Override
    public List<String> getKeyWords(long proid) {
	if (proid <= 0)
	    return new ArrayList<String>();

	List<String> result = null;
	String KeyName = "ProductKeyWord" + String.valueOf(proid);
	try {
	    result = (List<String>) LocalRedisUtil.getObject(KeyName);
	} catch (Exception e) {
	}
	if (result == null) {
	    result = new ArrayList<String>();
	    String hql = "from PProductKeyWords where productId=?";
	    List<PProductKeyWords> list = productDao.find(hql, new Object[] { proid });
	    if (list != null && list.size() > 0) {
		for (PProductKeyWords kw : list) {
		    result.add(kw.getKeyWord());
		}
	    }
	    LocalRedisUtil.setObject(KeyName, result);
	}
	return result;
    }

    @Autowired
    private IHouseService houseService;

    @Override
    public long getJumpID(long w, long pNo) {
	String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
	PClassProducts model = productDao.getUniqueResultByHql(hql, new Object[] { w, pNo });
	if (model != null)
	    return model.getId();
	return 0;
    }

    @Override
    public ProductInfo getPriceVisble(LoginUser user, Integer demandId, long supWeiID) {
	ProductInfo info = new ProductInfo();
	List<USupplyChannel> chanList = productDao.find("from USupplyChannel where demandId=? and weiId=? and state=?", new Object[] { demandId, user.getWeiID(), Short.parseShort(AgentStatusEnum.Normal.toString()) });
	if (chanList != null && chanList.size() > 0) {
	    for (USupplyChannel chan : chanList) {
		if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
		    info.setCurrentUserIsAgent(1);
		}
		if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
		    info.setCurrentUserIsStore(1);
		}
	    }
	}
	PPriceShow visible = productDao.get(PPriceShow.class, supWeiID);
	info = houseService.getPriceShow(user, visible, info);
	return info;
    }

    @Override
    public USupplyDemand getSupplyDemand(long proID) {
	if (proID > 0) {
	    UDemandProduct product = productDao.getUniqueResultByHql("from UDemandProduct where productId=?", proID);
	    if (product != null) {
		return productDao.get(USupplyDemand.class, product.getDemandId());
	    }
	}
	return null;
    }

    @Override
    public List<Product> getDemandProduct(Integer demandID, Long proid) {
	List<UDemandProduct> proList = productDao.find("from UDemandProduct where demandId=? and productId!=?", new Object[] { demandID, proid });
	if (proList != null && proList.size() > 0) {
	    Long[] proids = new Long[proList.size()];
	    for (int i = 0; i < proList.size(); i++) {
		proids[i] = proList.get(i).getProductId();
	    }
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("productId", proids);
	    params.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
	    List<PProducts> list = productDao.findPageByMap("from PProducts where productId in (:productId) and state=:state order by createTime desc", 1, 5, params);
	    if (list != null && list.size() > 0) {
		// 获取产品的代理价 落地价
		Long[] rproids = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
		    rproids[i] = list.get(0).getProductId();
		}
		Map<String, Object> reaultParam = new HashMap<String, Object>();
		reaultParam.put("productId", rproids);
		List<PProductRelation> relList = productDao.findByMap("from PProductRelation where productId in(:productId)", reaultParam);

		List<Product> result = new ArrayList<Product>();
		for (PProducts pro : list) {
		    Product temp = new Product();
		    temp.setProID(pro.getProductId());
		    temp.setProTitle(pro.getProductTitle());
		    temp.setProImg(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
		    temp.setPrice(pro.getDefaultPrice());
		    if (relList != null && relList.size() > 0) {
			for (PProductRelation rel : relList) {
			    if (pro.getProductId().equals(rel.getProductId())) {
				temp.setAgentPrice(rel.getMinProxyPrice());
				temp.setLandPrice(rel.getMinFloorPrice());
				break;
			    }
			}
		    }
		    result.add(temp);
		}
		return result;
	    }
	}
	return null;
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
	if (BitOperation.verIdentity(supType, UserIdentityType.AgentBrandSupplier))
		return true;
	return false;
    }

    @Override
    public long getCartCount(long weiid) {
	// 返回购物车数量
	String hql = "select count(1) from TShoppingCar where weiId=? and status=1";
	long count = productDao.count(hql, new Object[] { weiid });
	return count;
    }
}
