package com.okwei.cartportal.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OOrderAddr;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.TCountStat;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.UMessage;
import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.OrderDel;
import com.okwei.bean.enums.ProductShelveStatu;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.ShoppingCarSourceEnum;
import com.okwei.bean.enums.ShoppingCartTypeEnum;
import com.okwei.bean.vo.address.AddressVO;
import com.okwei.bean.vo.shoppingcart.PProductStyleKVVO;
import com.okwei.cartportal.bean.JiesuanInfo;
import com.okwei.cartportal.bean.KuaiDi;
import com.okwei.cartportal.bean.PreSubmitCartList;
import com.okwei.cartportal.bean.ProductModel;
import com.okwei.cartportal.bean.ProductStyleParam;
import com.okwei.cartportal.bean.ShoppingCarList;
import com.okwei.cartportal.bean.ShoppingModel;
import com.okwei.cartportal.bean.enums.LogisticsType;
import com.okwei.cartportal.bean.enums.MsgType;
import com.okwei.cartportal.bean.enums.OrderFrom;
import com.okwei.cartportal.bean.enums.OrderProductType;
import com.okwei.cartportal.bean.enums.OrderState;
import com.okwei.cartportal.bean.enums.OrderType;
import com.okwei.cartportal.bean.enums.ProductBType;
import com.okwei.cartportal.bean.enums.ProductStatus;
import com.okwei.cartportal.bean.enums.ShoppingCarType;
import com.okwei.cartportal.bean.vo.ProdStylesVo;
import com.okwei.cartportal.bean.vo.ReturnOdertInfo;
import com.okwei.cartportal.bean.vo.StylesPriceVo;
import com.okwei.cartportal.bean.vo.StylesVo;
import com.okwei.cartportal.dao.IAdressDAO;
import com.okwei.cartportal.dao.IShopCartDAO;
import com.okwei.cartportal.service.IShopCartService;
import com.okwei.common.JsonUtil;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.dao.shoppingcart.IBasicPProductStylesMgtDAO;
import com.okwei.service.IRegionService;
import com.okwei.service.agent.IDAgentService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.shoppingcart.IBasicShoppingCartMgtService;
import com.okwei.util.GenerateOrderNum;
import com.okwei.util.ImgDomain;
@Service
public class ShopCartService extends BaseService implements IShopCartService {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IShopCartDAO shopCartDAO;
	@Autowired
    private IRegionService regionService;
	@Autowired
    private IAdressDAO adressDAO;
	@Autowired
    private IBasicOrdersDao basicOrdersDao;
	@Autowired
	private IBasicShoppingCartMgtService iBasicShoppingCartMgtService;
	@Autowired
	private IBasicPProductStylesMgtDAO iBasicPProductStylesMgtDAO;
	
	@Autowired
	private IDAgentService agentService;
	
	/**
     * 批发价转换
     * 
     * @param dianNo
     * @param proId
     * @return
     */
    public List<PProductBatchPrice> getBatchPricess(Long dianNo,Long proId)
    {
        String hql = " from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
        Object[] b3 = new Object[2];
        b3[0] = dianNo;// 上架供应商ID
        b3[1] = proId;// 商品ID
        PClassProducts pclassPro = shopCartDAO.getUniqueResultByHql(hql,b3);// 获取这个上架信息
        if(pclassPro != null)
        {
            String hql2 = " from PShevleBatchPrice p where p.id=?  order by p.count";
            Object[] b = new Object[1];
            b[0] = pclassPro.getId();
            List<PShevleBatchPrice> psplist = shopCartDAO.find(hql2,b);
            if(psplist == null || psplist.size() <= 0)
            {
                return null;
            }
            List<PProductBatchPrice> ppplist = new ArrayList<PProductBatchPrice>();
            for(PShevleBatchPrice price : psplist)
            {
                PProductBatchPrice ppp = new PProductBatchPrice();
                ppp.setBid(price.getSbid());
                ppp.setCount(price.getCount());
                ppp.setPirce(price.getPrice());
                ppp.setProductId(proId);
                ppplist.add(ppp);
            }
            return ppplist;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 计算价格
     * 
     * @param count
     * @param pplist
     * @return
     */
    @Override
    public Double getshoppcartpricebycount(int count,List<PProductBatchPrice> pplist)
    {
        Collections.sort(pplist,new Comparator<PProductBatchPrice>()
        {
            public int compare(PProductBatchPrice arg0,PProductBatchPrice arg1)
            {
                return arg0.getCount().compareTo(arg1.getCount());
            }
        });
        Double proPrice = 0.0;// 梯度数量
        for(PProductBatchPrice p : pplist)
        {

            if(count >= p.getCount())
            {
                proPrice = p.getPirce();
            }
            else
            {
                return proPrice;
            }
        }
        return proPrice;
    }
    
    /**
     * 去重复
     * */
    public ArrayList<Long> RidRepeatByLong(ArrayList<Long> list)
    {
        return new ArrayList<Long>(new LinkedHashSet<Long>(list));
    }
    
    public String getRrogetuctStyleName2(Long proid, Long styleid) {
		/* 查商品款式KV */
		String hql = " from PProductStyleKv p where p.productId=? and p.stylesId=?  order by p.attributeId asc";
		Object[] b = new Object[2];
		b[0] = proid;
		b[1] = styleid;
		List<PProductStyleKv> listkey = shopCartDAO.find(hql, b);// 获取商品款式所属的属性列表
		if (listkey == null || listkey.size() <= 0)// 如果列表为空，返回空
		{
			return "";
		}
		String ret = "";
		for (PProductStyleKv ppsk : listkey) {
			hql = "from PProductSellValue p where p.productId=? and p.keyId=?";
			Object[] b2 = new Object[2];
			b2[0] = ppsk.getProductId();
			b2[1] = ppsk.getKeyId();
			PProductSellValue ppsv = shopCartDAO.getUniqueResultByHql(hql, b2);
			hql = "from PProductSellKey p where p.productId=? and p.attributeId=?";
			b2[1] = ppsk.getAttributeId();
			PProductSellKey ppkey = shopCartDAO.getUniqueResultByHql(hql, b2);
			if (ppsv == null || ppkey == null) {
				continue;
			}
			ret += ppkey.getAttributeName() + ":" + ppsv.getValue() + "|";
		}
		return ret;
	}
    
    /**
     * 用产品id，款式ID获取购物车选择的款式类型列表
     * */
    @Override
    public String GetRroductStyleName(Long proid,Long styleid)
    {
    	/* 获取商品所有属性 */
        String hql = " from PProductSellKey where productId=? ORDER BY sort ASC";
        Object[] param = new Object[1];
        param[0] = proid;
        List<PProductSellKey> prodStyleList = shopCartDAO.find(hql,param);
        if(prodStyleList == null || prodStyleList.size() <= 0) {
            return null;
        }
        /* 获取商品所选属性对应的款式关系 */
        String hql2 = " from PProductStyleKv WHERE productId=? AND stylesId=?";
        Object[] param2 = new Object[2];
        param2[0] = proid;
        param2[1] = styleid;
        List<PProductStyleKv> prodStyleKVList = shopCartDAO.find(hql2,param2);
        if(prodStyleKVList == null || prodStyleKVList.size() <= 0) {
            return null;
        }
        /* 获取商品所选属性对应的款式值 */
        String hql3 = " from PProductSellValue WHERE productId=? ";
        Object[] param3 = new Object[1];
        param3[0] = proid;
        List<PProductSellValue> prodSellValueList = shopCartDAO.find(hql3,param3);
        if(prodSellValueList == null || prodSellValueList.size() <= 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for(PProductSellKey prodStyle : prodStyleList) {
        	sb.append("-1".equals(prodStyle.getAttributeName()) ? "默认" : prodStyle.getAttributeName()).append(":");
           for (PProductStyleKv prodStyleKv : prodStyleKVList) {
        	   if (prodStyle.getAttributeId().equals(prodStyleKv.getAttributeId())) {
        		   for (PProductSellValue prodSellValue : prodSellValueList) {
        			   if (prodStyleKv.getKeyId().equals(prodSellValue.getKeyId())) {
        				   sb.append("-1".equals(prodSellValue.getValue()) ? "默认" : prodSellValue.getValue()).append("</br>");
        			   }
        		   }
			   }
           }
        }
        return sb.toString();
    }
    
    /**
	 * 获得商品的所有属性
	 * @param style 
	 * @param prodId 
	 * @return
	 */
	private List<ProductStyleParam> getProductStyleParamList(Long prodId,Long styleid) throws Exception{
		List<ProductStyleParam> styleList = new ArrayList<ProductStyleParam>();
		/* 获取商品所有属性 */
        String hql = " from PProductSellKey where productId=? ORDER BY sort ASC";
        Object[] param = new Object[1];
        param[0] = prodId;
        List<PProductSellKey> prodStyleList = shopCartDAO.find(hql,param);
        if(prodStyleList == null || prodStyleList.size() <= 0) {
            return null;
        }
        /* 获取商品所选属性对应的款式关系 */
        String hql2 = " from PProductStyleKv WHERE productId=? AND stylesId=?";
        Object[] param2 = new Object[2];
        param2[0] = prodId;
        param2[1] = styleid;
        List<PProductStyleKv> prodStyleKVList = shopCartDAO.find(hql2,param2);
        
        for (PProductSellKey selkey : prodStyleList) {
        	ProductStyleParam style = new ProductStyleParam();
        	style.setProSellKey(selkey);
        	/* 获取商品所选属性对应的款式值 */
            String hql3 = " from PProductSellValue WHERE productId=? and attributeId=?";
            Object[] param3 = new Object[2];
            param3[0] = prodId;
            param3[1] = selkey.getAttributeId();
            List<PProductSellValue> prodSellValueList = shopCartDAO.find(hql3,param3);//商品属性对应的款式
            if(prodSellValueList == null || prodSellValueList.size() <= 0) {
                continue;
            }
            style.setProSellValue(prodSellValueList);
            Map<Long,Long> selectStyle = new HashMap<Long,Long>();
            Long stylekey = 0l;
            //判断购物车已选的属性款式
            if(prodStyleKVList != null && prodStyleKVList.size() > 0) {
            	for (PProductStyleKv styleKv : prodStyleKVList) {
					if (selkey.getAttributeId().equals(styleKv.getAttributeId())) {
						stylekey = styleKv.getKeyId();
						break;
					}
				}
            }
            if (stylekey == 0l) {
            	stylekey = prodSellValueList.get(0).getKeyId();
			}
            selectStyle.put(selkey.getAttributeId(), stylekey);//保存购物车已选的款式 key-属性id，value-款式id
            style.setSelectStyle(selectStyle);
        	styleList.add(style);
		}
		return styleList;
	}
	
	public Double getYoufei(List<PPostAgeDetails> mlist,AddressVO uca,List<ProductModel> pgList,List<PProducts> ppList){
		Double money = 0.0; 
		/* 收货地址 */
        Integer province = uca.getProvince() == null ? 0 : uca.getProvince();// 获取省
        Integer city = uca.getCity() == null ? 0 : uca.getCity();// 获取市
        Integer district = uca.getDistrict() == null ? 0 : uca.getDistrict();// 获取区
		boolean isNotArea = false;// 默认不在区域外
    	PPostAgeDetails pasmodel = null;// 例外的模板
    	PPostAgeDetails defaultmodel = null;// 默认的模板
    	/* 判断用户购买区域在不在例外区域 */
    	for(PPostAgeDetails ppad : mlist)
    	{
    		if(ppad.getDestination().indexOf("|" + province.toString() + "|") >= 0)
    		{
    			isNotArea = true;// 确认为特殊区域
    			pasmodel = ppad;// 特殊区域模板
    			break;
    		}
    		else if(ppad.getDestination().indexOf("|" + city.toString() + "|") >= 0)
    		{
    			isNotArea = true;// 确认为特殊区域
    			pasmodel = ppad;// 特殊区域模板
    			break;
    		}
    		else if(ppad.getDestination().indexOf("|" + district.toString() + "|") >= 0)
    		{
    			isNotArea = true;// 确认为特殊区域
    			pasmodel = ppad;// 特殊区域模板
    			break;
    		}
    		if(ppad.getStatus() == 0)
    		{// 邮费默认模板为0，其余为1(非默认模板就是例外区域的模板)
    			defaultmodel = ppad;// 默认邮费详情模板
    		}
    	}
    	if(!isNotArea)
    	{
    		pasmodel = defaultmodel;
    	}
    	if(pasmodel != null)
    	{
    		Double youfei = 0.0;// 这个运费模板的金额数
    		Map<Long, Integer> prodMap = new HashMap<Long, Integer>();//同一个店铺 去除相同商品
    		for(PProducts pp : ppList)// 遍历商品列表
    		{
    			for(ProductModel pg : pgList){// 遍历购买对象
    				if(pp.getProductId().equals(pg.getProdId())){
    					if (prodMap.get(pp.getProductId()) != null && prodMap.get(pp.getProductId()) > 0) {
    						prodMap.put(pp.getProductId(), Integer.valueOf(prodMap.get(pp.getProductId())+pg.getProdCount()));
						}else {
							prodMap.put(pp.getProductId(), Integer.valueOf(pg.getProdCount()));
						}
    				}
    			}
    		}
    		
			for (Map.Entry<Long, Integer> entry : prodMap.entrySet()) {
				Integer countbypp = entry.getValue();// 初始化购买数量
				if (countbypp > pasmodel.getFirstCount())// 判断件数是否大于首件
				{
					Integer maths = countbypp - pasmodel.getFirstCount();// 剩下要计算的件数
					Integer morecount = Integer.parseInt(pasmodel.getMoreCount().toString());// 续件
					if (morecount > 0) {
						Integer xujian = maths / pasmodel.getMoreCount();// 算当前件数除于续件件数的商
						Integer yushu = maths % pasmodel.getMoreCount();// 求余数
						if (yushu > 0) {
							xujian++;
						}
						youfei += pasmodel.getFirstpiece()+ (xujian * pasmodel.getMorepiece());// 首费加续费
					} else {
						youfei += pasmodel.getFirstpiece();// 如果等于或小于首件只算首件费用
					}
				} else {
					youfei += pasmodel.getFirstpiece();// 如果等于或小于首件只算首件费用
				}
			} 
    		money += youfei;// 计入总价
    	}
    	return money;
	}

	@Override
	public List<ShoppingCarList> getShoppingCarList(long weiID,Short stype) throws Exception{
        String hql = " from TShoppingCar t where t.weiId=? and t.buyType=? order by t.createTime desc";
        Object[] b = new Object[2];
        b[0] = weiID;
        b[1] = stype;
        List<TShoppingCar> list = shopCartDAO.find(hql,b);// 查询返回结果
        ArrayList<Long> longlist = new ArrayList<Long>();// 定义一个list接收公司ID
        // 遍历购物车列表
        for(TShoppingCar sc : list)
        {
            // 把公司的ID添加到list中
            longlist.add(sc.getSupplierWeiId());
        }
        // list去重复
        longlist = RidRepeatByLong(longlist);
        // 定义一个返回的list
        List<ShoppingCarList> sclist = new ArrayList<ShoppingCarList>();
        // 遍历去重复后的公司id
        for(Long cid : longlist)
        {
            // 定义一个购物车列表
            List<ShoppingModel> tsclist = new ArrayList<ShoppingModel>();
            // 遍历查询出来的购物车
            for(TShoppingCar sc : list)
            {
                // 判断是不是同一个公司的商品
                if(sc.getSupplierWeiId().equals(cid))
                {
                    ShoppingModel sm = new ShoppingModel();
                    sm.setsCID(sc.getScid());// 主键
                    sm.setWeiID(sc.getWeiId());// 用户微店号
                    if(sc.getShopWeiID()!=null)
                    	sm.setShopWeiId(sc.getShopWeiID());// 来源微店号码
                    else {
                    	sm.setShopWeiId(sc.getSellerWeiId());
					}
                    //获取来源微店铺名称
                    if (sm.getShopWeiId() > 0) {
						sm.setShopWeiName(this.getShopNameById(sm.getShopWeiId()));
					}
                    sm.setProNum(sc.getProNum());// 产品编号
                    sm.setCount(sc.getCount());// 购买数量
                    sm.setSupplierID(sc.getSupplierWeiId());// 供应商微店号
                    sm.setSellerWeiId(sc.getSellerWeiId());// 成交微店
                    /* 供应商有没有上架这个产品 */
                    String hql2 = "from PClassProducts p where p.weiId=? and p.productId=?  and p.state=1";
                    Object[] b3 = new Object[2];
                    b3[0] = cid;// 上架供应商ID
                    b3[1] = sc.getProNum();// 商品ID
                    PClassProducts pclassPro = shopCartDAO.getUniqueResultByHql(hql2,b3);// 获取这个上架信息
                    if(pclassPro == null)
                    { // 这个供应商没有上架这个产品
                        sc.setStatus(Short.parseShort("0"));
                        shopCartDAO.update(sc);
                        logger.error("商品["+sc.getProNum()+"]未上架");
                        continue;
                    }

                    // 查询商品
                    String hqlpbString = "from PProducts p where p.productId=?  and p.state!=5 and p.state!=3";// 查询商品
                    Object[] bpp = new Object[1];
                    bpp[0] = sc.getProNum();
                    PProducts pros = shopCartDAO.getUniqueResultByHql(hqlpbString,bpp);// 商品不存在
                    hqlpbString = "from PProductStyles p where p.stylesId=?";// 查询商品款式
                    Object[] bn = new Object[1];
                    bn[0] = sc.getStyleId();// 通过款式ID
                    PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
                    if(pps == null || pros == null)
                    {// 判断款式是否为空
                     // /要加上把数据改成不可以使用
                        sc.setStatus(Short.parseShort("0"));
                        shopCartDAO.update(sc);
                        logger.error("商品["+sc.getProNum()+"]不存在或找不到款式");
                        continue;
                    }else{
                    	sm.setImage(ImgDomain.GetFullImgUrl(StringUtils.isEmpty(pps.getDefaultImg()) ? pros.getDefaultImg() : pps.getDefaultImg()));// 图片
                    	sm.setNowprice(pps.getPrice());// 当前价格(款式价格)
                    }
                    if(pros.getState().equals(Short.parseShort(ProductStatus.Showing.toString())))
                    {
                        if(sc.getStatus() == 0)
                        {
                            sc.setStatus(Short.parseShort("1"));
                            shopCartDAO.update(sc);
                        }
                    }
                    else
                    {
                        sc.setStatus(Short.parseShort("0"));
                        shopCartDAO.update(sc);
                        logger.error("商品["+sc.getProNum()+"]未展示");
                        continue;
                    }
                    sm.setProTitle(pros.getProductTitle());// 标题.
                    sm.setProperty(GetRroductStyleName(sc.getProNum(),sc.getStyleId()));// 属性
                    sm.setClassId(pros.getClassId());// 分类ID
                    sm.setStatus(sc.getStatus());// 状态
                    sm.setBuyType(sc.getBuyType());// 购物车类型
                    sm.setCreateTime(sc.getCreateTime());// 时间
                    sm.setStyleId(sc.getStyleId());// 款式ID
                    sm.setPrice(pros.getDefaultPrice());// 零售价
                    sm.setBatchprice(pros.getBatchPrice());//默认批发价
                    sm.setPrice2(sc.getPrice());// 加入购物车价格
                    List<ProductStyleParam> styleList = getProductStyleParamList(sc.getProNum(),sc.getStyleId()); 
                    sm.setStyles(styleList);//商品所有属性
                    if(stype.toString().equals(ShoppingCarType.Wholesale.toString()))
                    {
                        List<PProductBatchPrice> ppbplist = getBatchPricess(sc.getSupplierWeiId(),sc.getProNum());// 获取所有的批发价格
                        // 获取所有的批发价格
                        if(ppbplist == null || ppbplist.size() <= 0)
                        {
                        	sm.setNowprice(pros.getBatchPrice());
                        } else {
                        	sm.setPpbplist(ppbplist);
                        	sm.setNowprice(getshoppcartpricebycount(sm.getCount(), ppbplist));// 当前批发价格(款式价格)
                        	if (sm.getNowprice() == null || sm.getNowprice() <= 0.0) {
                        		sm.setNowprice(pros.getBatchPrice());
                        	}
                        }
                    }
                    else if(stype.toString().equals(ShoppingCarType.Presell.toString()))
                    {
                        sm.setNowprice(pros.getBookPrice());// 当前预定价格(款式价格)
                    }
                    //上架商品id
                    String strShevlesSQL= " from PClassProducts p where p.weiId=? and p.productId=? and p.state=?";
					Object[] obs= new Object[3];
					obs[0]=sm.getShopWeiId();
					obs[1]=sm.getProNum();
					obs[2]=(short)1;
					List<PClassProducts> pcps= shopCartDAO.find(strShevlesSQL, obs);
					if(pcps!=null && pcps.size()>0)
					{
						PClassProducts pClassProducts=pcps.get(0);
						sm.setShelvesId(pClassProducts.getId());
					}  
                    sm.setTotalPrice(Double.valueOf(sm.getNowprice()*sm.getCount()));//商品总价格
                    tsclist.add(sm);// 如果是同一个公司的商品添加进list
                }
            }
            ShoppingCarList scobj = new ShoppingCarList();
            //设置店铺名称
            scobj.setCompanyName(getShopNameById(cid));
            if(tsclist == null || tsclist.size() <= 0)
            {
            	continue;
            }
            scobj.setProdList(tsclist);
            sclist.add(scobj);
        }
        return sclist;
    }

	@Override
	public void updateCartPordCount(int prodCount, long scId) throws Exception{
		TShoppingCar shopcar = shopCartDAO.get(TShoppingCar.class, scId);
		if (shopcar != null) {
			shopcar.setCount(prodCount);
			shopCartDAO.update(shopcar);
		} else {
			throw new Exception();
		}
	}

	@Override
	public void delCartPord(String scids,long weiid) throws Exception {
		String[] scidsArr = scids.split(",");
        List<Long> ids = new ArrayList<Long>();
        for(String s : scidsArr)
        {
            if(StringUtils.isNotEmpty(s))
            {
                ids.add(Long.parseLong(s));
            }
        }
        Long[] arrylong = (Long[]) ids.toArray(new Long[ids.size()]);
		String hql2 = "from TShoppingCar t WHERE t.scid in (:ids) and t.weiId = :weiid";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ids",arrylong);
        map.put("weiid",weiid);
        // 查询部分字段只能返回List<Object[]>，传入数组参数
        List<TShoppingCar> sclist = shopCartDAO.findByMap(hql2,map);
        for(TShoppingCar sc : sclist)
        {
        	shopCartDAO.delete(sc);
        }
	}

	@Override
	public JiesuanInfo getJiesuanInfo(String scids, long weiID,Short stype) throws Exception{
		JiesuanInfo info = new JiesuanInfo();
		Double totalPrice = 0d;
		String[] scidsArr = scids.split(",");
        List<Long> ids = new ArrayList<Long>();
        for(String s : scidsArr)
        {
            if(StringUtils.isNotEmpty(s))
            {
                ids.add(Long.parseLong(s));
            }
        }
        Long[] arrylong = (Long[]) ids.toArray(new Long[ids.size()]);
        info.setProdCount(arrylong.length);
		String hql2 = "from TShoppingCar t WHERE t.scid in (:ids) and t.weiId = :weiid";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ids",arrylong);
        map.put("weiid",weiID);
        // 查询部分字段只能返回List<Object[]>，传入数组参数
        List<TShoppingCar> sclist = shopCartDAO.findByMap(hql2,map);
        if (stype == Short.valueOf(ShoppingCarType.Wholesale.toString())) {
        	for(TShoppingCar sc : sclist) {
        		Double jiageDouble = 0.0;// 初始化批发价
        		List<PProductBatchPrice> ppbplist = getBatchPricess(sc.getSupplierWeiId(), sc.getProNum());// 获取所有的批发价格
        		if (ppbplist != null && ppbplist.size() > 0) {
        			int pricount = 0;// 初始化总数
        			for (TShoppingCar btemp : sclist) {// 遍历需要购买的购物车有几件
        				if (btemp.getProNum().equals(sc.getProNum())) {
        					pricount += btemp.getCount();// 件数相加
        				}
        			}
        			jiageDouble = getshoppcartpricebycount(pricount, ppbplist);// 初始化批发价
        		}
        		if (jiageDouble <= 0.0) {
        			String hqlpbString = "from PProducts p where p.productId=?";// 查询商品
                    Object[] bpp = new Object[1];
                    bpp[0] = sc.getProNum();
                    PProducts pros = shopCartDAO.getUniqueResultByHql(hqlpbString,bpp);
                    if (pros != null ) {
    					jiageDouble = pros.getBatchPrice();
    				}
        			/*String hqlpbString = "from PProductStyles p where p.stylesId=?"; 
    				Object[] bn = new Object[1];
    				bn[0] = sc.getStyleId();// 通过款式ID
    				PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
    				if (pps != null ) {
    					jiageDouble = pps.getPrice();
    				} else 
    					jiageDouble = sc.getPrice();*/
				}
        		totalPrice += Double.valueOf(jiageDouble*sc.getCount());
			}
		} else if (stype == Short.valueOf(ShoppingCarType.Presell.toString())) { 
			for(TShoppingCar sc : sclist)
			{
				String hqlpbString = "from PProducts p where p.productId=?"; 
				Object[] bn = new Object[1];
				bn[0] = sc.getProNum();// 通过产品ID
				PProducts pp = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
				if (pp != null ) {
					totalPrice += Double.valueOf(pp.getBookPrice()*sc.getCount());
				} 
			}
		} else if (stype == Short.valueOf(ShoppingCarType.Retail.toString())){
			for(TShoppingCar sc : sclist)
			{
				String hqlpbString = "from PProductStyles p where p.stylesId=?"; 
				Object[] bn = new Object[1];
				bn[0] = sc.getStyleId();// 通过款式ID
				PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
				if (pps != null ) {
					totalPrice += Double.valueOf(pps.getPrice()*sc.getCount());
				} else {
					totalPrice += Double.valueOf(sc.getPrice()*sc.getCount());
				}
			}
		}
        info.setProdTotalPrice(Double.valueOf(new DecimalFormat("######0.00").format(totalPrice)));
		return info;
	}

	@Override
	public List<AddressVO> getAddressList(long weiID) throws Exception {
		List<UCustomerAddr> list = basicOrdersDao.getCustomerAddressByWeiID(weiID);
		
		List<AddressVO> result = new ArrayList<AddressVO>();
		if (list != null && list.size() > 0) {
		    for (UCustomerAddr addr : list) {
			AddressVO temp = new AddressVO();
			temp.setCaddrId(addr.getCaddrId());
			temp.setReceiverName(addr.getReceiverName());
			temp.setProvince(addr.getProvince());
			temp.setCity(addr.getCity());
			temp.setDistrict(addr.getDistrict());
			temp.setDetailAddr(addr.getDetailAddr());
			temp.setMobilePhone(addr.getMobilePhone());
			temp.setIsShopAddress(addr.getIsShopAddress());
			temp.setQq(addr.getQq());
			temp.setIsDefault(addr.getIsDefault());
			// TODO 获取详细地址 基类缓存还没有配置好
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
			temp.setAddress(address);
			result.add(temp);
		    }
		}
		return result;
	 }

	@Override
    @Transactional
	public int saveOrUpdateAdd(UCustomerAddr model)  throws Exception{
		// 如果修改的时候设为默认,修改其他的地址不为默认
		if (model.getIsDefault() != null
				&& model.getIsDefault().shortValue() == 1) {
			if (model.getCaddrId() == null || model.getCaddrId() <= 0) {
				adressDAO.cancelDefault(model.getWeiId());
			} else {
				adressDAO.cancelDefault(model.getWeiId(), model.getCaddrId());
			}
		}
		if (model.getCaddrId() != null && model.getCaddrId().intValue() > 0) {
			if (model != null && model.getWeiId() != null
					&& model.getWeiId().longValue() > 0) {
				model.setUpdateTime(new Date());
				return adressDAO.updateCustomerAddr(model);
			}
			return 0;
		} else {
			model.setRegisterTime(new Date());
			// 添加
			return adressDAO.addCustomerAddr(model);
		}
	}

	@Override
	public int deleteAddress(long weiid, int caddrID)  throws Exception{
		return adressDAO.deleteAddress(weiid, caddrID);
	}

	@Override
	public int setDefault(long weiid, int caddrID)  throws Exception{
		// 如果修改的时候设为默认,修改其他的地址不为默认
		adressDAO.cancelDefault(weiid);
		return adressDAO.setDefault(caddrID);
	}

	@Override
	public USupplyer getUSupplyer(Long companyLogid) {
		String yzhql = "from USupplyer u where u.weiId=? and u.type!=0";
        Object[] yz = new Object[1];
        yz[0] = companyLogid;
		return shopCartDAO.getUniqueResultByHql(yzhql,yz);
	}

	@Override
	public boolean checkExistProduct(Long weiid, Long prodId, Long styleId) {
		// 查询是否上架
        String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
        Object[] b2 = new Object[2];
        b2[0] = weiid;
        b2[1] = prodId;
        PClassProducts pcp = shopCartDAO.getUniqueResultByHql(hql,b2);
        if(pcp == null)
        {
            return false;
        }
        // 查询商品是否存在
        hql = "from PProducts p where p.productId=?  and p.state=?";
        Object[] b = new Object[2];
        b[0] = prodId;// 产品ID
        b[1] = Short.parseShort(ProductStatus.Showing.toString());// 展示中才可以购买
        PProducts pro = shopCartDAO.getUniqueResultByHql(hql,b);
        if(pro == null)
        {
        	return false;
        }
        // 查询款式是否存在
        hql = "from PProductStyles p where p.stylesId=?";
        Object[] b1 = new Object[1];
        b1[0] = styleId;// 款式ID
        PProductStyles pps = shopCartDAO.getUniqueResultByHql(hql,b1);
        if(pps == null)
        {
        	return false;
        }
		return true;
	}
	
	/**
	 * 查询邮费
	 */
    @Override
    public List<KuaiDi> getkdListBySC(PreSubmitCartList pur,long weiNo,Short cartType,AddressVO uca)
    {
    	List<KuaiDi> jskdlist = new ArrayList<KuaiDi>();
        // 验证地址是否为空
        if(uca == null)
        {
            return null;
        }
        long suId = pur.getCompanyLogid();// 供应商微店号
        List<ProductModel> pgList = pur.getProductList();// 购买商品列表
        // 验证供应商
        String yzhql = "from USupplyer u where u.weiId=? and u.type!=0";
        Object[] yz = new Object[1];
        yz[0] = suId;
        USupplyer us = shopCartDAO.getUniqueResultByHql(yzhql,yz);
        if(us == null)
        {
        	return null;
        }
        // 所有产品ID
        List<Long> prolist = new ArrayList<Long>();
        pgList.forEach((pg) -> {
        	prolist.add(pg.getProdId());
        });
        // 验证产品列表是否为空
        if(prolist == null || prolist.size() <= 0)
        {
        	return null;
        }
        /* 查询产品列表和上架列表 */
        String hql = "from PClassProducts p where p.sendweiId=:sendweiId and p.productId in (:ids) and p.state=1";
        Map<String,Object> parmMap = new HashMap<String,Object>();
        parmMap.put("ids",prolist.toArray());
        parmMap.put("sendweiId",suId);
        List<PClassProducts> pclasslist = shopCartDAO.findByMap(hql,parmMap);
        hql = "from PProducts p where p.productId in (:ids)  and p.state=:state";
        Map<String,Object> parmMap2 = new HashMap<String,Object>();
        parmMap2.put("ids",prolist.toArray());
        parmMap2.put("state",Short.parseShort(ProductStatus.Showing.toString()));
        List<PProducts> ppList = shopCartDAO.findByMap(hql,parmMap2);
        List<Integer> freightList = new ArrayList<Integer>();
        if(pclasslist == null || pclasslist.size() <= 0 || ppList == null || ppList.size() <= 0)
        {
        	return null;
        }
        Map<Long,PClassProducts> pcpMap = new HashMap<Long,PClassProducts>();
        pclasslist.forEach((pc) -> {
        	pcpMap.put(pc.getProductId(),pc);
        });
        Map<Long,PProducts> ppMap = new HashMap<Long,PProducts>();
        ppList.forEach((pp) -> {
        	ppMap.put(pp.getProductId(),pp);
        	if (!pp.getFreightId().equals(-1)) {
        		freightList.add(pp.getFreightId());
			}
        });
        /* 查询结束 */
        // 遍历购买商品的列表
        Double courierMoney = 0.0;// 初始化快递总金额
        /* 排除垃圾数据 */
        for(int i = 0; i < pgList.size(); i++)
        {
        	ProductModel pg = pgList.get(i);
        	int count = pg.getProdCount();
        	long proId = pg.getProdId();
        	long sellerId = pg.getShopWeiId();
        	long stylesId = pg.getStyleId();
        	// 验证参数
        	if(count <= 0 || proId <= 0 || sellerId <= 0 || stylesId <= 0)
        	{
//        		pgList.remove(i);
        		continue;
        	}
        	PClassProducts pcp = pcpMap.get(proId);
        	PProducts pp = ppMap.get(proId);
        	if(pcp == null || pp == null)
        	{
//        		pgList.remove(i);
        		continue;
        	}
        }
        /* 计算自定义快递邮费 */
        for(PProducts pp : ppList)
        {
        	if(pp.getFreightId().equals(-1))// 判断是否是自定义邮费
        	{
        		int procount = 0;// 总件数
        		for(ProductModel pg : pgList)// 遍历购买列表获取数量
        		{
        			if(pp.getProductId().equals(pg.getProdId()))// 是否同一件产品
        			{
        				procount += pg.getProdCount();
        			}
        		}
        		courierMoney += pp.getDefPostFee() * procount; // 计算自定义邮费（首费+续件*首费）
        	}
        }
        
        if (freightList.size() > 0) {
        	hql = "from PPostAgeDetails p where p.freightId in (:ids)";// 查询快递邮费详情模板
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("ids",freightList.toArray());
        	List<PPostAgeDetails> mlist = shopCartDAO.findByMap(hql,param); // 邮费详情列表
        	if(mlist == null || mlist.size() <= 0)
        	{
        		return null;// 快递邮费详情模板为空
        	}
        	List<PPostAgeDetails> courierList = new ArrayList<PPostAgeDetails>();
        	List<PPostAgeDetails> EMSList = new ArrayList<PPostAgeDetails>();
        	List<PPostAgeDetails> ordinaryList = new ArrayList<PPostAgeDetails>();
        	List<PPostAgeDetails> wuliuList = new ArrayList<PPostAgeDetails>();
        	for (PPostAgeDetails ageDetails : mlist) {
        		/* 快递 */
        		if (Short.valueOf(LogisticsType.Courier.toString()).equals(ageDetails.getCourierType())) {
        			courierList.add(ageDetails);
        		} 
        		/* EMS */
        		else if (Short.valueOf(LogisticsType.EMS.toString()).equals(ageDetails.getCourierType())) {
        			EMSList.add(ageDetails);
        		} 
        		/* 平邮 */
        		else if (Short.valueOf(LogisticsType.Ordinary.toString()).equals(ageDetails.getCourierType())) {
        			ordinaryList.add(ageDetails);
        		} 
        		/* 物流 */
        		else if (Short.valueOf(LogisticsType.Wuliu.toString()).equals(ageDetails.getCourierType())) {
        			wuliuList.add(ageDetails);
        		}    
        	}
        	
        	/* 计算快递 */
        	if (courierList.size() > 0) {
        		KuaiDi k = new KuaiDi();
        		k.setId(1);
        		k.setName("快递");
        		k.setAmount(courierMoney+getYoufei(courierList, uca, pgList, ppList));
        		jskdlist.add(k);
        	} else if (courierMoney > 0.0) {
        		KuaiDi k = new KuaiDi();
        		k.setId(1);
        		k.setName("快递");
        		k.setAmount(courierMoney);
        		jskdlist.add(k);
			}
        	/* 计算EMS */
        	if (EMSList.size() > 0) {
        		KuaiDi k = new KuaiDi();
        		k.setId(2);
        		k.setName("EMS");
        		k.setAmount(getYoufei(EMSList, uca, pgList, ppList));
        		jskdlist.add(k);
        	}
        	/* 计算平邮 */
        	if (ordinaryList.size() > 0) {
        		KuaiDi k = new KuaiDi();
        		k.setId(3);
        		k.setName("平邮");
        		k.setAmount(getYoufei(ordinaryList, uca, pgList, ppList));
        		jskdlist.add(k);
        	}
        	/* 计算物流 */
        	if (wuliuList.size() > 0) {
        		KuaiDi k = new KuaiDi();
        		k.setId(4);
        		k.setName("物流");
        		k.setAmount(getYoufei(wuliuList, uca, pgList, ppList));
        		jskdlist.add(k);
        	}
		} else {
			KuaiDi k = new KuaiDi();
    		k.setId(1);
    		k.setName("快递");
    		k.setAmount(courierMoney);
    		jskdlist.add(k);
		}
		if(jskdlist.size() <= 0)
        {
			KuaiDi k = new KuaiDi();
    		k.setId(1);
    		k.setName("快递");
    		k.setAmount(0.0);
    		jskdlist.add(k);
        }
        return jskdlist;
    }

	@Override
	public ReturnOdertInfo savePlaceOrderByWeiNo(List<PreSubmitCartList> submitList, long weiID, AddressVO ucaAddr,
			Short cartype) throws Exception{
		/* 初始化订单数据 */
		List<OSupplyerOrder> osolist = new ArrayList<OSupplyerOrder>();// 供应商订单表
		List<OProductOrder> opolist = new ArrayList<OProductOrder>();// 商品订单表
		ReturnOdertInfo roinfo = new ReturnOdertInfo();
		Double all_jiage = 0D;
		Double all_youfei = 0D;
		/* 收获地址处理 */
		if (ucaAddr == null) {
			roinfo.setOrderno("3");
			return roinfo;// 地址不存在，返回空
		}
		/* 邮费处理 */
		if (!cartype.equals(Short.parseShort(ShoppingCarType.Presell.toString()))) {// 预订单不计算邮费
			for (PreSubmitCartList c : submitList) {
				c.setLogistics(getkdListBySC(c, weiID, cartype, ucaAddr));//获得邮费列表
			}
		}
		OPayOrder opo = new OPayOrder();// 订单主记录
		String payorderid = GenerateOrderNum.getInstance().GenerateOrder();
		opo.setPayOrderId(payorderid);// 生成订单号
		opo.setBigOrderId(payorderid);// 组合订单号
		opo.setOrderTime(new Date());// 下单时间
		opo.setWeiId(weiID);// 买家微店号
		opo.setOrderDate(new Date());// 提高效率时间
		opo.setState(Short.parseShort(OrderState.Nopay.toString()));// 等待付款
		opo.setOrderFrom(Short.parseShort(OrderFrom.PC.getNo() + ""));
		if (cartype == Short.parseShort(ShoppingCarType.Retail.toString())) {// 零售订单
			opo.setTypeState(Short.parseShort(OrderType.BatchOrder.toString()));
		} else if (cartype == Short.parseShort(ShoppingCarType.Wholesale.toString())) {// 批发单
			opo.setTypeState(Short.parseShort(OrderType.BatchWholesale.toString()));
		}
		Double zongJia = 0.0D;// 初始化总价
		Double zongyoufei = 0.0D;// 初始化总邮费
		/* 遍历每个公司的数据 */
		for (PreSubmitCartList mo : submitList) {// 遍历列表
			/* 查供应商是否存在 */
			if (!shopCartDAO.isSaleProduct(mo.getCompanyLogid())) {
				continue;
			}
			/* 查供应商是否存在 */
			String suhql = "from USupplyer u where u.weiId=? and u.type!=0";
			Object[] gys = new Object[1];
			gys[0] = mo.getCompanyLogid();
			USupplyer us = shopCartDAO.getUniqueResultByHql(suhql, gys);
			if (us == null || us.getType() == 0) {
				continue;// 供应商不存在
			}
			OSupplyerOrder osoOrder = new OSupplyerOrder();// 供应商订单
			osoOrder.setSupplierOrderId(GenerateOrderNum.getInstance().GenerateOrder());// 生成订单号
			osoOrder.setOrderTime(new Date());// 下单时间
			osoOrder.setSupplyerId(mo.getCompanyLogid());// 设置供应商ID
			osoOrder.setMessage(mo.getMessage());// 买家留言
			osoOrder.setBuyerID(weiID);// 买家微店号
			osoOrder.setOrderDate(new Date());// 只存日期提高查询效率
			osoOrder.setSellerDel(Short.parseShort(OrderDel.NoDel.toString()));
			osoOrder.setBuyerDel(Short.parseShort(OrderDel.NoDel.toString()));
			osoOrder.setPayOrderId(payorderid);// 主订单订单号
			osoOrder.setOrderFrom(Short.parseShort(OrderFrom.PC.getNo() + ""));// 来源
			if (cartype.equals(Short.parseShort(ShoppingCarType.Presell.toString()))) {
				osoOrder.setOrderType(Integer.parseInt(OrderType.BookOrder.toString()));// 预订单
				osoOrder.setState(Short.parseShort(OrderState.WaitSure.toString()));// 等待确认
			} else if (cartype.equals(Short.parseShort(ShoppingCarType.Retail.toString()))) {
				if ((us.getType() >> 2 & 1) == 1) {
					osoOrder.setOrderType(Integer.parseInt(OrderType.BatchOrder.toString()));// 批发号零售
				} else if ((us.getType() >> 1 & 1) == 1) {
					osoOrder.setOrderType(Integer.parseInt(OrderType.Pt.toString()));// 云商通零售
				} else {
					osoOrder.setOrderType(Integer.parseInt(OrderType.Pt.toString()));// 云商通零售
				}
				osoOrder.setState(Short.parseShort(OrderState.Nopay.toString()));// 等待付款
			} else if (cartype.equals(Short.parseShort(ShoppingCarType.Wholesale.toString()))) {
				osoOrder.setOrderType(Integer.parseInt(OrderType.BatchWholesale.toString()));// 批发
				osoOrder.setState(Short.parseShort(OrderState.Nopay.toString()));// 等待付款
			} else {
				roinfo.setOrderno("10");
				return roinfo;// 购物类型有误
			}
			//判断邮费
			if (cartype.equals(Short.parseShort(ShoppingCarType.Presell.toString()))) {// 预订单不计算邮费
				osoOrder.setPostage(0D);
			} else {
				boolean flag = false;
				for (KuaiDi kd : mo.getLogistics()) {
					if (kd.getId().toString().equals(mo.getLogisticsId())) {
						osoOrder.setPostage(kd.getAmount());// 如果选择的是快递
						flag = true;
						break;
					}
				}
				if (!flag) {
					roinfo.setOrderno("9");
					return roinfo;// 邮费方式选择错误
				}
			}
			Double supplyZongJia = 0.0D;// 初始化总价
			Double supplyYongjing = 0.0D;// 初始化佣金
			Double supplyZongYuanJia = 0.0D;// 初始化总原价
			List<ProductModel> bscList = mo.getProductList();// 获取购物数据
			String prostring = "";// 初始化商品ID
			String stylestring = "";// 初始化商品ID
			if (bscList == null) {
				roinfo.setOrderno("4");
				return roinfo;// 购买的东西不能为空
			}
			// 判断数组大于0，循环拼接数组参数
			for (int i = 0; i < bscList.size(); i++) {
				if (i == bscList.size() - 1) {
					prostring += bscList.get(i).getProdId().toString();
					stylestring += bscList.get(i).getStyleId().toString();
				} else {
					prostring += bscList.get(i).getProdId().toString() + ",";
					stylestring += bscList.get(i).getStyleId().toString() + ",";
				}
			}
			/* 查询商品和商品详情 */
			String hql = "from PProductStyles p where p.stylesId in (" + stylestring + ")";// 查询商品款式语句
			List<PProductStyles> ppsList = shopCartDAO.find(hql);// 获取返回的商品款式列表
			if (ppsList == null || ppsList.size() <= 0) {
				roinfo.setOrderno("6");
				return roinfo;// 购买的物品不存在
			}
			Map<Long, PProductStyles> ppsmap = new HashMap<Long, PProductStyles>(); // 定义一个map用来存款式列表
			for (PProductStyles pps : ppsList) {
				ppsmap.put(pps.getStylesId(), pps);// 循环添加到map对象去
			}
			hql = "from PProducts p where p.productId in (" + prostring + ")  and p.state!=5 and p.state!=3";// 查询所有商品
			List<PProducts> ppList = shopCartDAO.find(hql);// 返回所有商品
			if (ppList == null || ppsList.size() <= 0) {
				roinfo.setOrderno("6");
				return roinfo;
			}
			Map<Long, PProducts> ppmap = new HashMap<Long, PProducts>();// 定义一个map用来存商品列表
			for (PProducts pProducts : ppList) {
				ppmap.put(pProducts.getProductId(), pProducts);// 循环添加到map对象去
			}
			for (ProductModel bsc : bscList) {// 购买列表
				/* 供应商有没有上架这个产品 */
				String hql2 = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
				Object[] b3 = new Object[2];
				b3[0] = mo.getCompanyLogid();// 上架供应商ID
				b3[1] = bsc.getProdId();// 商品ID
				PClassProducts pclassPro = shopCartDAO.getUniqueResultByHql(hql2, b3);// 获取这个上架信息
				if (pclassPro == null) { // 这个供应商没有上架这个产品
					continue;
				}
				/* 成交微店处理 */
				String selectOKseller = "from UWeiSeller u where u.weiId=?";// 定义查询成交微店的hql语句
				Object[] b = new Object[1];
				b[0] = bsc.getShopWeiId();
				UWeiSeller okwei = shopCartDAO.getUniqueResultByHql(selectOKseller, b); // 获取成交微店
				if (okwei == null) {
					UWeiSeller okwei2 = new UWeiSeller();
					okwei2.setWeiId(Long.parseLong("1"));
					okwei2.setSponsorWeiId(Long.parseLong("1"));
					okwei = okwei2;
				}
				PProductStyles pps = ppsmap.get(bsc.getStyleId());
				PProducts pp = ppmap.get(bsc.getProdId());
				if (pp == null || pps == null) {
					roinfo.setOrderno("6");
					return roinfo;
				}
				OProductOrder opoOrder = new OProductOrder();
				opoOrder.setSupplyType(pp.getSupperType());
				opoOrder.setSellerWeiid(bsc.getShopWeiId());// 成交微店
				opoOrder.setSellerUpweiid(okwei.getSponsorWeiId());// 成交微店上级
				opoOrder.setProductOrderId(GenerateOrderNum.getInstance().GenerateOrder());// 生成订单号
				opoOrder.setSupplyeriId(pclassPro.getSendweiId());// 设置供应商ID
				opoOrder.setCreateTime(new Date());// 创建时间
				opoOrder.setProductId(pp.getProductId());// 商品款式编号
				opoOrder.setSupplierOrderId(osoOrder.getSupplierOrderId());// 所属的供应商订单号
				opoOrder.setBuyerId(weiID);// 买家ID
				// TODO 店铺微店号
				opoOrder.setShopWeiID(bsc.getShopWeiId());
				opoOrder.setState(Short.parseShort(OrderState.Nopay.toString()));// 默认待付款
				opoOrder.setCount(bsc.getProdCount());// 设置数量
				opoOrder.setProdcutTitle(pp.getProductTitle());// 商品标题
				opoOrder.setProductImg(ImgDomain.GetFullImgUrl(StringUtils.isEmpty(pps.getDefaultImg()) ? pp.getDefaultImg() : pps.getDefaultImg()));// 商品图片
				opoOrder.setClassId(pp.getClassId());// 产品分类
				opoOrder.setProductMinTitle(pp.getProductMinTitle());// 产品副标题
				opoOrder.setProductDes(pp.getAppdes());// web/app 产品描述
				opoOrder.setStyleId(pps.getStylesId());// 产品款式ID
				opoOrder.setStyleDes(getRrogetuctStyleName2(bsc.getProdId(), bsc.getStyleId()));
				opoOrder.setSellerDel(Short.parseShort(OrderDel.NoDel.toString()));
				opoOrder.setBuyerDel(Short.parseShort(OrderDel.NoDel.toString()));

				if (cartype != Short.parseShort(ShoppingCarType.Presell.toString())) {
					opoOrder.setPayOrderId(payorderid);// 支付订单号
				}
				if (cartype.equals(Short.parseShort(ShoppingCarType.Wholesale.toString()))) {// 如果是批发订单
					List<PProductBatchPrice> ppbplist = getBatchPricess(mo.getCompanyLogid(), pps.getProductId());// 获取所有的批发价格
					Double jiageDouble = 0.0;// 初始化批发价
					if (ppbplist != null && ppbplist.size() > 0) {
						Integer pricount = 0;// 初始化总数
						for (ProductModel btemp : bscList) {// 遍历需要购买的购物车有几件
							if (btemp.getProdId().equals(bsc.getProdId())) {
								pricount += btemp.getProdCount();// 件数相加
							}
						}
						jiageDouble = getshoppcartpricebycount(pricount, ppbplist);
					} 
					// 算当前价格
					if (jiageDouble > 0) {
						opoOrder.setPrice(jiageDouble);// 单价
						opoOrder.setSourcePrice(jiageDouble);// 原价
					} else {
						roinfo.setOrderno("7");
						return roinfo;// 批量购买数据有误
					}
					opoOrder.setOrderType(Short.parseShort(OrderType.BatchWholesale.toString()));// 批发
					opoOrder.setCommision(0D);// 如果是预售订单设置佣金
				} else if (cartype.equals(Short.parseShort(ShoppingCarType.Presell.toString()))) {
					opoOrder.setPrice(pp.getBookPrice());// 预订价
					opoOrder.setSourcePrice(pp.getBookPrice());// 原价
					opoOrder.setOrderType(Short.parseShort(OrderType.BookOrder.toString()));// 预订单
					opoOrder.setState(Short.parseShort(OrderState.WaitSure.toString()));// 等待确定
					opoOrder.setCommision(0D);// 如果是预售订单设置佣金
				} else {
					/******/
					opoOrder.setPrice(pps.getPrice());// 单价
					opoOrder.setSourcePrice(pps.getPrice());// 原价
					if (pp.getbType().toString().equals(ProductBType.Gifts.toString())) {
						opoOrder.setOrderType(Short.parseShort(OrderProductType.BGifts.getNo() + ""));// 零售,赠品区
					} else {
						if ((us.getType() >> 2 & 1) == 1) {
							opoOrder.setOrderType(Short.parseShort(OrderType.BatchOrder.toString()));// 批发号零售
						} else if ((us.getType() >> 1 & 1) == 1) {
							opoOrder.setOrderType(Short.parseShort(OrderType.Pt.toString()));// 云商通零售
						} else {
							opoOrder.setOrderType(Short.parseShort(OrderType.Pt.toString()));// 云商通零售
						}
						opoOrder.setOrderType(Short.parseShort(OrderProductType.BRetail.getNo() + ""));// 零售**********************************
					}
					opoOrder.setCommision(pps.getConmision());// 如果是零售订单设置佣金
				}
				opolist.add(opoOrder);
				supplyYongjing += opoOrder.getCommision() * opoOrder.getCount();// 佣金*数量
				supplyZongJia += opoOrder.getPrice() * opoOrder.getCount();// 单价*数量
				supplyZongYuanJia += opoOrder.getSourcePrice() * opoOrder.getCount();// 原价*数量
			}
			osoOrder.setTotalPrice(supplyZongJia);// 供应商总价
			osoOrder.setCommision(supplyYongjing);// 供应商佣金
			osoOrder.setSourcePrice(supplyZongYuanJia);// 供应商总原价
			osolist.add(osoOrder);
			zongJia += osoOrder.getTotalPrice();// 这个供应商的总价
			zongyoufei += osoOrder.getPostage();// 这个供应商的总邮费
		}
		// logger.error("生成订单号3：" + opo.getPayOrderId());
		opo.setTotalPrice(zongJia + zongyoufei);// 订单总价 +订单总邮费
		all_jiage += zongJia;
		all_youfei += zongyoufei;
		/* 循环添加 */
		if (opolist == null || opolist.size() <= 0) {
			roinfo.setOrderno("8");
			return roinfo;// 如果产品订单为空，返回空
		}
		for (OProductOrder opo1 : opolist) {
			shopCartDAO.save(opo1);// 循环添加产品订单
		}
		//删除购物车
		for (PreSubmitCartList c : submitList) {
			if (c.getScid() != null && c.getScid() > 0) {
				String delString = "from TShoppingCar t where t.scid=?";
				Object[] bdel = new Object[1];
				bdel[0] = c.getScid();
				TShoppingCar tsctempCar = shopCartDAO.getUniqueResultByHql(delString, bdel);
				if (tsctempCar != null) {
					shopCartDAO.delete(tsctempCar);
				}
			}
		}
		OOrderAddr ooa1 = new OOrderAddr();// 订单地址
		ooa1.setWeiId(weiID);
		ooa1.setDetailAddr(ucaAddr.getDetailAddr());
		ooa1.setReceiverName(ucaAddr.getReceiverName());
		ooa1.setProvince(ucaAddr.getProvince());
		ooa1.setCity(ucaAddr.getCity());
		ooa1.setDistrict(ucaAddr.getDistrict());
		ooa1.setMobilePhone(ucaAddr.getMobilePhone());
		ooa1.setQq(ucaAddr.getQq());
		ooa1.setCreateTime(new Date());
		shopCartDAO.save(ooa1);
		
		String supplyOrderIDs ="";//供应商订单组合字符串 ，
		for (OSupplyerOrder oso1 : osolist) {
			
			if("".equals(supplyOrderIDs)){
				supplyOrderIDs = oso1.getSupplierOrderId();
			}else{
				supplyOrderIDs +=","+oso1.getSupplierOrderId();
			}
			
			oso1.setAddrId(ooa1.getOrderAddrId());
			shopCartDAO.save(oso1);// 循环添加供应商订单
			UMessage msg = new UMessage();
			msg.setCreateTime(new Date());
			msg.setMessage("新成交了1单");
			msg.setState(Short.parseShort("1"));
			msg.setToWeiId(oso1.getSupplyerId());
			msg.setWeiId(weiID);
			msg.setType(Short.parseShort(MsgType.ordersuccess.toString()));
			shopCartDAO.save(msg);

			OOrderFlow oof = new OOrderFlow();
			oof.setWeiId(weiID);
			oof.setOperateTime(new Date());
			oof.setSupplierOrderId(oso1.getSupplierOrderId());
			oof.setOperateContent("买家（" + weiID + "）生成了订单");
			shopCartDAO.save(oof);

			if (cartype.toString().equals(ShoppingCarType.Presell.toString())) {
				UPushMessage umes = new UPushMessage();
				umes.setCreateTime(new Date());
				umes.setMsgType(Short.parseShort("32"));
				umes.setPushContent("销售订单：您有一笔预订单等待确认");
				umes.setPushWeiId(Long.parseLong("1"));
				umes.setReciptWeiId(oso1.getSupplyerId());
				umes.setObjectId(oso1.getSupplierOrderId());
				umes.setExtra(oso1.getOrderType().toString());
				shopCartDAO.insertSendPushMsg(umes);
			}
		}
		if (cartype == Integer.parseInt(ShoppingCarType.Presell.toString())) {
			roinfo.setOrderno("");
			return roinfo;
		}
		shopCartDAO.save(opo);// 添加主订单
		//添加支付日志表
		OPayOrderLog orderLog = new OPayOrderLog();
		orderLog.setCreateTime(new Date());
		orderLog.setPayOrderId(opo.getPayOrderId());
		orderLog.setState((short)0);
		orderLog.setTotalAmout(opo.getTotalPrice());
		orderLog.setWeiId(opo.getWeiId());
		orderLog.setSupplyOrderIds(supplyOrderIDs);
		shopCartDAO.save(orderLog);
		
		roinfo.setOrderno(payorderid);// 返回一个主订单号
		roinfo.setAllpostage(all_youfei);
		roinfo.setAlltotal(all_jiage);
		return roinfo;
	}
	/* (non-Javadoc)
	 * @see com.okwei.cartportal.service.IShopCartService#getProdStylePrice(java.lang.String)
	 */
	@Override
	public StylesPriceVo getProdStylePrice(String styleJson)  throws Exception{
		StylesPriceVo sp = new StylesPriceVo();
		sp.setPrice(0.0);
		sp.setStyleId(0);
		JSONArray data = JSONArray.fromObject(styleJson);
		List<StylesVo> styleList = data.toList(data,StylesVo.class);
		if (styleList != null && styleList.size() > 0) {
			StylesVo style = styleList.get(0);//找出第一个
			String hql = " from PProductStyleKv p where p.productId=? and p.attributeId=? and p.keyId=?";
			Object[] b = new Object[3];
    		b[0] = style.getProductId();
    		b[1] = style.getAttrId();
    		b[2] = style.getKeyId();
    		List<PProductStyleKv> listkey = shopCartDAO.find(hql, b);
			if (listkey != null && listkey.size() > 0) {
				Long styleId = 0l;
				if (listkey.size() == 1) {//只有一个属性的产品
					PProductStyleKv kv1 = listkey.get(0);
					styleId = kv1.getStylesId();
				} else {
					List<Long> styleIds = new ArrayList<Long>();//存放相同的styleid
					for (int i = 1; i < styleList.size(); i++) {//其他的都跟第一个比较
						StylesVo st = styleList.get(i);
						if (style.getAttrId() > 0 && style.getProductId() > 0
								&& style.getKeyId() > 0) {
							Object[] p = new Object[3];
							p[0] = st.getProductId();
							p[1] = st.getAttrId();
							p[2] = st.getKeyId();
							List<PProductStyleKv> listkv = shopCartDAO.find(hql, p);
							for (PProductStyleKv kv2 : listkv) {
								for (PProductStyleKv kv1 : listkey) {
									if (kv1.getStylesId().equals(kv2.getStylesId())) {
										styleIds.add(kv1.getStylesId());
									}
								}
							}
						} else {
							break;
						}
					}
					if (styleIds.size() > 0) {
						Map<Long,Integer> map = new HashMap<Long,Integer>();
						for (Long temp : styleIds) {
							Integer count = map.get(temp);
							map.put(temp, (count == null) ? 1 : count + 1);
						}
						for (Map.Entry entry : map.entrySet()) {
							if (entry.getValue().equals(styleList.size() - 1)) {
								styleId = (Long)entry.getKey();
							}
						}
					}
				}
				if (styleId > 0) {
					sp.setStyleId(styleId);
					String hql2 = "from PProductStyles p where p.stylesId = ?";// 查询商品款式
					Object[] p = new Object[1];
					p[0] = styleId;
					PProductStyles styles = shopCartDAO.getUniqueResultByHql(hql2, p);
					if (styles != null) {
						sp.setPrice(iBasicShoppingCartMgtService.getStylePrice(styles,style.getSource()));
						if (StringUtils.isNotEmpty(styles.getDefaultImg())) {
							sp.setImg(ImgDomain.GetFullImgUrl(styles.getDefaultImg()));
						}
					}
				}
			}
		}
		return sp;
	}

	@Override
	public StylesPriceVo modifyProdStyleId(long scId, long styleId,long weiId,short source) throws Exception{
		StylesPriceVo sp = new StylesPriceVo();
		TShoppingCar shopcar = shopCartDAO.get(TShoppingCar.class, scId);
		if (shopcar != null) {//修改
		    PProductStyles pps = shopCartDAO.get(PProductStyles.class, styleId); //getUniqueResultByHql(hql,bn);
	        List<PProductStyleKVVO> pProductStyleKVVOList = iBasicShoppingCartMgtService.getPProductStyleKVVOList(styleId, shopcar.getProNum() != null ? shopcar.getProNum() : -1 );
	            
			String tchql = " from TShoppingCar p where p.weiId = ? and p.proNum=? and  p.status=? and p.buyType=?";
			Object[] b = new Object[4];
			b[0] = weiId;
    		b[1] = shopcar.getProNum() != null ? shopcar.getProNum() : -1;
    		b[2] = Short.parseShort(ProductStatusEnum.Showing.toString()); //Short.valueOf("1");
    		b[3] = shopcar.getBuyType() != null ? shopcar.getBuyType() : -1;
    		List<TShoppingCar> listcart = shopCartDAO.find(tchql, b);
    		int countP=0;
    		if (listcart != null && listcart.size() > 0) {//购物车存在相同的产品款式 则设置款式为0，页面删除该记录
    			for (TShoppingCar cc : listcart) {
    				countP+=cc.getCount();
					if(cc.getScid().longValue()==scId){
						cc.setStyleId(styleId); 
						//组合销售属性给app
			    		cc.setProperty1(iBasicShoppingCartMgtService.getAppProductKeyValue(pProductStyleKVVOList));
			    		//图片
		            	if (StringUtils.isNotEmpty(pps.getDefaultImg())) {
							cc.setImage(ImgDomain.GetFullImgUrl(pps.getDefaultImg()));
						}
		            	shopcar.setStyleId(styleId);
		            	shopcar.setProperty1(cc.getProperty1());
		            	shopcar.setImage(cc.getImage());
					}
				}
			}
            double price = 0.0;
            if (pps != null) {
            	//批发
            	if(shopcar.getBuyType()!=null&&shopcar.getBuyType()==Short.parseShort(ShoppingCartTypeEnum.Wholesale.toString())){
            		PProducts products=shopCartDAO.get(PProducts.class, shopcar.getProNum());
            		if(products!=null){
            			List<PProductBatchPrice> ppbplist =iBasicShoppingCartMgtService.getBatchPricess(products.getSupplierWeiId(), shopcar.getProNum(), Short.parseShort(String.valueOf(ProductShelveStatu.OnShelf)));
                		price = iBasicShoppingCartMgtService.getshoppcartpricebycount(countP, ppbplist);// 初始化批发价
                		for (TShoppingCar pcart : listcart) {
    						pcart.setPrice(price);
    						shopCartDAO.update(pcart);
    					} 
            		}
            		
            	}else {
            		//价格
            		if(source==Short.parseShort(ShoppingCarSourceEnum.share.toString())){
            			price=agentService.getProductPriceByWeiid(weiId, styleId);
            		}else {
            			price = iBasicShoppingCartMgtService.getStylePrice(pps,source);	
					}
                	
                	shopcar.setPrice(price);
                	shopCartDAO.update(shopcar); 
				}
            	
            	//图片
            	if (StringUtils.isNotEmpty(pps.getDefaultImg())) {
					sp.setImg(ImgDomain.GetFullImgUrl(pps.getDefaultImg()));
				}
			}
             //款式id
            sp.setStyleId(styleId);
            sp.setPrice(price); 
            //销售属性
            sp.setProperty(JsonUtil.objectToJson(pProductStyleKVVOList));
		} 
		return sp;
	}

	@Override
	public List<ProdStylesVo> getAvailableProdStyles(long productId,
			long attrId, long keyId) throws Exception{
		List<ProdStylesVo> psList = new ArrayList<ProdStylesVo>();
		String hql = " from PProductStyleKv p where p.productId=:productId and p.attributeId=:attrId and p.keyId=:keyId";
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("productId", productId);
		mp.put("attrId", attrId);
		mp.put("keyId", keyId);
		List<PProductStyleKv> listkey = shopCartDAO.findByMap(hql, mp);
		 if(listkey != null && listkey.size() > 0) {
			 /* 获取商品所有属性 */
			 String hql2 = " from PProductSellKey where productId=?";
			 Object[] param = new Object[1];
			 param[0] = productId;
			 List<PProductSellKey> prodStyleList = shopCartDAO.find(hql2,param);
			 if(prodStyleList != null && prodStyleList.size() > 0) {
				 for (PProductSellKey sk : prodStyleList) {
					 if (sk.getAttributeId().longValue() != attrId) {
						ProdStylesVo ps = new ProdStylesVo();
						ps.setAttrId(sk.getAttributeId());
						String hql3 = " from PProductStyleKv p where p.productId=:productId and p.attributeId=:attrId";
						Map<String, Object> mp2 = new HashMap<String, Object>();
						mp2.put("productId", productId);
						mp2.put("attrId", sk.getAttributeId());
						List<PProductStyleKv> listkey2 = shopCartDAO.findByMap(hql3, mp2);
						if (listkey2 != null && listkey2.size() > 0) {
							StringBuffer sb = new StringBuffer();
							for (PProductStyleKv pskv2 : listkey2) {
								for (PProductStyleKv pskv : listkey) {
									if (pskv.getStylesId().equals(pskv2.getStylesId())) {
										sb.append(pskv2.getKeyId()).append("_");
									}
								}
							}
							if (StringUtils.isNotEmpty(sb.toString())) {
								ps.setKeyIds(sb.toString().substring(0,sb.toString().length() - 1));
							} else {
								ps.setKeyIds("");
							}
						}
						psList.add(ps);
					 }
				 }
			 }
		 }
		return psList;
	}

	@Override
	public PProducts getPProducts(Long productId) throws Exception {
		String hqlpbString = "from PProducts p where p.productId=?  and p.state=?";// 查询商品
        Object[] bpp = new Object[2];
        bpp[0] = productId;
        bpp[1] = Short.parseShort(ProductStatusEnum.Showing.toString());
        PProducts pros = shopCartDAO.getUniqueResultByHql(hqlpbString,bpp);
		return pros;
	}

	@Override
	public PProductStyles getPProductStylesById(long styleId) throws Exception{
		String hqlpbString = "from PProductStyles p where p.stylesId=?";// 查询商品款式
        Object[] bn = new Object[1];
        bn[0] = styleId;// 通过款式ID
        PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
		return pps;
	}
	
	@Override
	public long getCartCount(long weiid) throws Exception{
		if (weiid > 0) {
			String hql = "select count(1) from TShoppingCar where weiId=? and status=1";
			return shopCartDAO.count(hql, new Object[] { weiid });
		}
		return 0;
	}
	
	@Override
	public long getUserCount() throws Exception{
		String hql = "from TCountStat where name='UserCount'";
		TCountStat entity = shopCartDAO.getUniqueResultByHql(hql);
		if (entity != null && entity.getCountNum().longValue() > 0)
			return entity.getCountNum() / 10000;
		return 0;
	}

	@Override
	public PClassProducts getClassProducts(Long supplierWeiId, Long prodId)
			throws Exception {
		
		return shopCartDAO.getClassProducts(supplierWeiId, prodId);
	}

	@Override
	public PClassProducts getPClassProductsByWeiId(Long shopWeiId,
			Long productId) throws Exception{
		String hql = " from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
        Object[] b3 = new Object[2];
        b3[0] = shopWeiId;// 上架WeiID
        b3[1] = productId;// 商品ID
		return shopCartDAO.getUniqueResultByHql(hql,b3);
	}

	@Override
	public long getShopCartCountByType(long weiID, Short stype) throws Exception{
		String hql = "select count(*) from TShoppingCar t where t.weiId=? and t.buyType=? and status=1";
        Object[] params = new Object[2];
        params[0] = weiID;
        params[1] = stype;
		return shopCartDAO.count(hql, params);
	}
	@Override
	public  PClassProducts getPClassProductsByCondition(Object[] obs){
		PClassProducts pClassProducts = null;
        String strShevlesSQL= " from PClassProducts p where p.weiId=? and p.productId=? and p.state=?";
		List<PClassProducts> pcps= shopCartDAO.find(strShevlesSQL, obs);
		if(pcps!=null && pcps.size()>0)
		{
			pClassProducts = pcps.get(0);
		}
		return pClassProducts;
	}

	@Override
	public String getShopNameById(Long weiNo) throws Exception{
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo = shopCartDAO.get(UShopInfo.class, weiNo);
		if (shopInfo != null && shopInfo.getShopName() != null
				&& !"".equals(shopInfo.getShopName()))
			return shopInfo.getShopName();
		UBatchSupplyer bsup = shopCartDAO.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getShopName() != null
				&& !"".equals(bsup.getShopName())) {
			return bsup.getShopName();
		}
		USupplyer uSupplyer = shopCartDAO.get(USupplyer.class, weiNo);
		if (uSupplyer != null && uSupplyer.getCompanyName() != null
				&& !"".equals(uSupplyer.getCompanyName())) {
			return uSupplyer.getCompanyName();
		}
		UWeiSeller seller = shopCartDAO.get(UWeiSeller.class, weiNo);
		return seller.getWeiName();
	}

	@Override
	public JiesuanInfo getNewJiesuanInfo(String scids, long weiID, Short stype)
			throws Exception {
		// TODO Auto-generated method stub
		JiesuanInfo info = new JiesuanInfo();
		Double totalPrice = 0d;
		String[] scidsArr = scids.split(",");
        List<Long> ids = new ArrayList<Long>();
        for(String s : scidsArr)
        {
            if(StringUtils.isNotEmpty(s))
            {
                ids.add(Long.parseLong(s));
            }
        }
        Long[] arrylong = (Long[]) ids.toArray(new Long[ids.size()]);
        info.setProdCount(arrylong.length);
		String hql2 = "from TShoppingCar t WHERE t.scid in (:ids) and t.weiId = :weiid";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ids",arrylong);
        map.put("weiid",weiID);
        // 查询部分字段只能返回List<Object[]>，传入数组参数
        List<TShoppingCar> sclist = shopCartDAO.findByMap(hql2,map);
        if (stype == Short.valueOf(ShoppingCarType.Wholesale.toString())) {
        	for(TShoppingCar sc : sclist) {
        		Double jiageDouble = 0.0;// 初始化批发价
        		List<PProductBatchPrice> ppbplist = getBatchPricess(sc.getSupplierWeiId(), sc.getProNum());// 获取所有的批发价格
        		if (ppbplist != null && ppbplist.size() > 0) {
        			int pricount = 0;// 初始化总数
        			for (TShoppingCar btemp : sclist) {// 遍历需要购买的购物车有几件
        				if (btemp.getProNum().equals(sc.getProNum())) {
        					pricount += btemp.getCount();// 件数相加
        				}
        			}
        			jiageDouble = getshoppcartpricebycount(pricount, ppbplist);// 初始化批发价
        		}
        		if (jiageDouble <= 0.0) {
        			String hqlpbString = "from PProducts p where p.productId=?";// 查询商品
                    Object[] bpp = new Object[1];
                    bpp[0] = sc.getProNum();
                    PProducts pros = shopCartDAO.getUniqueResultByHql(hqlpbString,bpp);
                    if (pros != null ) {
    					jiageDouble = pros.getBatchPrice();
    				}
        			/*String hqlpbString = "from PProductStyles p where p.stylesId=?"; 
    				Object[] bn = new Object[1];
    				bn[0] = sc.getStyleId();// 通过款式ID
    				PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
    				if (pps != null ) {
    					jiageDouble = pps.getPrice();
    				} else 
    					jiageDouble = sc.getPrice();*/
				}
        		totalPrice += Double.valueOf(jiageDouble*sc.getCount());
			}
		} else if (stype == Short.valueOf(ShoppingCarType.Presell.toString())) { 
			for(TShoppingCar sc : sclist)
			{
				String hqlpbString = "from PProducts p where p.productId=?"; 
				Object[] bn = new Object[1];
				bn[0] = sc.getProNum();// 通过产品ID
				PProducts pp = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
				if (pp != null ) {
					totalPrice += Double.valueOf(pp.getBookPrice()*sc.getCount());
				} 
			}
		} else if (stype == Short.valueOf(ShoppingCarType.Retail.toString())){
			for(TShoppingCar sc : sclist)
			{
				String hqlpbString = "from PProductStyles p where p.stylesId=?"; 
				Object[] bn = new Object[1];
				bn[0] = sc.getStyleId();// 通过款式ID
				PProductStyles pps = shopCartDAO.getUniqueResultByHql(hqlpbString,bn);
				if (pps != null ) {
					totalPrice += Double.valueOf(pps.getPrice()*sc.getCount());
				} else {
					totalPrice += Double.valueOf(sc.getPrice()*sc.getCount());
				}
			}
		}
		else if(stype == Short.valueOf(String.valueOf(ShoppingCartTypeEnum.Jinhuo) ) ||
				stype == Short.valueOf(String.valueOf(ShoppingCartTypeEnum.Puhuo) ) )
		{   
			//款式idlist
			List<Long> styleIdList = new ArrayList<Long>();
			for(TShoppingCar sc : sclist)
			{   
				styleIdList.add(sc.getStyleId() != null ? sc.getStyleId() : -1);
			}
			List<PProductStyles> pProductStylesList = iBasicPProductStylesMgtDAO.getPProductStyles(styleIdList);
			for(TShoppingCar sc : sclist)
			{   
				//款式id
				long styleId = sc.getStyleId() != null ? sc.getStyleId() : -1;
				//区id
				short source = sc.getSource() != null ? sc.getSource() : -1;
				//计算总价
				totalPrice += getBalancePriceBuyPurchasesAndMedic(styleId,source,pProductStylesList);
			}

		}
        info.setProdTotalPrice(Double.valueOf(new DecimalFormat("######0.00").format(totalPrice)));
		return info;
	}

	@Override
	public double getBalancePriceBuyPurchasesAndMedic(long styleId,
			short source, List<PProductStyles> list) {
		// TODO Auto-generated method stub
		if(list == null || list.size() < 1)
		{
			return 0.0;
		}
		double price = 0.0;
		for(PProductStyles  item : list)
		{
			long secondStyleId = item.getStylesId() != null ? item.getStylesId() : -11;
			if(styleId == secondStyleId)
			{
				switch (source) {
				//代理区
				case 1:
					price = item.getAgencyPrice() != null ? item.getAgencyPrice() : 0.0;
					break;
				//落地区
				case 2:
					price = item.getLandPrice() != null ? item.getLandPrice() : 0.0 ;
					break;
				default:
					price = item.getPrice() != null ?  item.getPrice() : 0.0;
					break;
				}
				return price;
			}
		}
		return price;
	}

}
