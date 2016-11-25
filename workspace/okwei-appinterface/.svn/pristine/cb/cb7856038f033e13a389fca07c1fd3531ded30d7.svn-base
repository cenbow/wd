package com.okwei.appinterface.service.impl.share;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.share.DianZanCountVO;
import com.okwei.appinterface.bean.vo.share.HotShareListVO;
import com.okwei.appinterface.bean.vo.share.HotShareVO;
import com.okwei.appinterface.bean.vo.share.MyShareListVO;
import com.okwei.appinterface.bean.vo.share.MyShareVO;
import com.okwei.appinterface.bean.vo.share.ProductVO;
import com.okwei.appinterface.bean.vo.share.ShareProductListVO;
import com.okwei.appinterface.dao.IShareDAO;
import com.okwei.appinterface.service.BaseCommonService;
import com.okwei.appinterface.service.share.IShareSvervice;
import com.okwei.appinterface.util.RedisUtil;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.domain.SZambiaActive;
import com.okwei.bean.domain.UCollectionStore;
import com.okwei.bean.enums.CollectionType;
import com.okwei.bean.enums.MainDataUserType;
import com.okwei.bean.enums.ShareOnHomePage;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.share.ProductsVO;
import com.okwei.common.CommonMethod;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.dao.share.IBasicShareDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.share.IBasicShareServices;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;

@Service
public class ShareSvervice extends BaseService implements IShareSvervice {

    @Autowired
    private IShareDAO shareDao;
    @Autowired
    private IBasicShareDAO basicShareDAO;
    @Autowired
    public BaseCommonService baseCommonService;
    @Autowired
    public IBasicShareServices shareServices;
    @Autowired
    private IBaseProductDao baseProductDao;
    @Autowired
    public CommonMethod commonMethod;
//	@Autowired
//	public IBaseCommonService basicShareServices;
    @Override
    public ReturnModel updateLikeSharePages(long weiId, Long pageId, Long shareOne) {
	ReturnModel rm = new ReturnModel();
	Map<String, Integer> map = new HashMap<String, Integer>();
	if (weiId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("该微店号不存在！");
	    return rm;
	}
	SMainData sm = shareDao.get(SMainData.class, pageId);
	if (sm==null) {
		rm.setStatu(ReturnStatus.ParamError);
		rm.setStatusreson("该分享页不存在！");
		return rm;
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	String hql = "from SZambiaActive s where s.shareId=? and s.shareWeiId=? and s.weiId=? ";
	SZambiaActive zambiaActive = shareDao.getUniqueResultByHql(hql, pageId, shareOne, weiId);
	map.put("likeCount", 0);// 点赞数
	  //在SStatics表中查询
    SStatics statics=shareDao.get(SStatics.class, pageId);
	// 当该用户未点过 “赞” 时
	if (zambiaActive == null) {
		 if(statics!=null){
		    	statics.setZanCount((statics.getZanCount()==null?0:statics.getZanCount())+1);
		    	map.put("likeCount", statics.getZanCount());
		    	shareDao.update(statics);
		 }else{
		    	rm.setStatu(ReturnStatus.DataError);
		    	rm.setStatusreson("此分享不存在");
		 }
	    // 添加点赞信息
	    SZambiaActive zA = new SZambiaActive();
	    zA.setCreateTime(new Date());
	    zA.setShareId(pageId);
	    zA.setShareWeiId(shareOne);
	    zA.setWeiId(weiId);
	    shareDao.save(zA);
	    
	    
//	    if (sm != null && sm.getOnHomePage().shortValue() == 1) {
//	    	UWeiDianCoinLog uwdc = new UWeiDianCoinLog();
//	    	UWallet uWallet = shareDao.get(UWallet.class, shareOne);
//	    	uwdc.setCreateTime(new Date());
//	    	uwdc.setType((short) 1);
//	    	uwdc.setWeiId(shareOne);
//	    	uwdc.setRemark("被人点赞");
//	    	uwdc.setShareId(pageId);
//	    	uwdc.setConsumeAmount(1d);
//	    	uwdc.setBalanceAmount(uWallet.getWeiDianCoin() == null ? 0d : uWallet.getWeiDianCoin() + 1);
//	    	shareDao.save(uwdc);
//	    	uWallet.setWeiDianCoin(uWallet.getWeiDianCoin() == null ? 0d : uWallet.getWeiDianCoin() + 1);
//	    	shareDao.update(uWallet);
//		}
	} else {
		map.put("likeCount", statics.getZanCount());
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("你已经对该分享,点过赞了。");
	    return rm;
	}
	
	List<Long> shareIdList = new ArrayList<Long>();
	shareIdList.add(pageId);
//	List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);
//	
//	if (dianZanCount != null && dianZanCount.size() > 0) {
//	    // 从点赞表中获取点赞的数据
//	    for (DianZanCountVO dianZanCountVO : dianZanCount) {
//		if (dianZanCountVO.getShareId().intValue() == pageId.intValue()) {
//		    map.put("likeCount", (long) dianZanCountVO.getCount());// 点赞数
//		    break;
//		}
//	    }
//	}
	rm.setBasemodle(map);
	return rm;
    }

    @Override
    public ReturnModel updateLikeSharePage(long weiId, Long pageId, Long shareOne) {
	ReturnModel rm = new ReturnModel();
	if (weiId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("该微店号不存在！");
	    return rm;
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	String hql = "from SZambiaActive s where s.shareId=? and s.shareWeiId=? and s.weiId=? ";
	SZambiaActive zambiaActive = shareDao.getUniqueResultByHql(hql, pageId, shareOne, weiId);
	List<Long> shareIdList = new ArrayList<Long>();
	shareIdList.add(pageId);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("shareCount", 0l);// 分享数
	map.put("likeCount", 0l);// 点赞数
    //在SStatics表中查询
    SStatics statics=shareDao.get(SStatics.class, pageId);
	// 当该用户未点过 “赞” 时
	if (zambiaActive == null) {
	    // 添加点赞信息
	    SZambiaActive zA = new SZambiaActive();
	    zA.setCreateTime(new Date());
	    zA.setShareId(pageId);
	    zA.setShareWeiId(shareOne);
	    zA.setWeiId(weiId);
	    shareDao.save(zA); 
	    if(statics!=null){
	    	statics.setZanCount((statics.getZanCount()==null?0:statics.getZanCount())+1);
	    	map.put("likeCount", statics.getZanCount());
	    	map.put("shareCount", (statics.getWebSv()==null?0:statics.getWebSv())+(statics.getWapSv()==null?0:statics.getWapSv())+(statics.getAppSv()==null?0:statics.getAppSv()));// 分享数
	    	shareDao.update(statics);
	    }else{
	    	rm.setStatu(ReturnStatus.DataError);
	    	rm.setStatusreson("此分享不存在");
	    }

	} else {
		map.put("likeCount", statics.getZanCount()==null?0:statics.getZanCount());// 点赞数
    	map.put("shareCount", (statics.getWebSv()==null?0:statics.getWebSv())+(statics.getWapSv()==null?0:statics.getWapSv())+(statics.getAppSv()==null?0:statics.getAppSv()));// 分享点赞数
    
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("你已经对该分享,点过赞了。");
	}
 
	
//	if (dianZanCount != null && dianZanCount.size() > 0) {
//	    // 从点赞表中获取点赞的数据
//	    for (DianZanCountVO dianZanCountVO : dianZanCount) {
//		if (dianZanCountVO.getShareId().intValue() == pageId.intValue()) {
//		    map.put("likeCount", (long) dianZanCountVO.getCount());// 点赞数
//		    break;
//		}
//	    }
//	}
	map.put("pageId", pageId);// 分享页Id
	map.put("shareOne", ParseHelper.toLong(shareOne.toString()));// 分享人微店号
	rm.setBasemodle(map);
	return rm;
    }

    @Override
   public ReturnModel findHotShareList(long weiId, Limit limit) {
	ReturnModel rm = new ReturnModel();
	if (weiId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("该微店号不存在！");
	    return rm;
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	HotShareListVO hslv = new HotShareListVO();
	PageResult<SMainData> findSMainDataList = shareDao.findSMainDataList(limit);
	List<HotShareVO> HSVList = new ArrayList<HotShareVO>();
	List<SMainData> mainDataList = findSMainDataList.getList();
	List<Long> shareIdList = new ArrayList<Long>();
	if (mainDataList != null && mainDataList.size() > 0) {
	    for (SMainData sMainData : mainDataList) {
		shareIdList.add(sMainData.getShareId());
		HotShareVO hsv = new HotShareVO();
		hsv.setIsForward(0);
		hsv.setPageTemplate(sMainData.getShareType()==null?(short)1:sMainData.getShareType());
		hsv.setPageId(sMainData.getShareId());
		hsv.setPageTitle(sMainData.getTitle());
		hsv.setPageDescription(sMainData.getDescrible());
		if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
			hsv.setShareOne(111L);
			hsv.setPageProducer(111L);
		}else{
			hsv.setShareOne(sMainData.getWeiId());
			hsv.setPageProducer(sMainData.getWeiId());
		}
		//首页分享  分享人
//		hsv.setShareOne(111);
		//制作人为微店网
//		hsv.setPageProducer(111);
		List<ProductVO> imgList = new ArrayList<ProductVO>();
		// 获取商品ID
		List<Long> productIdList = new ArrayList<Long>();
		List<Long> shelveIds = new ArrayList<Long>();
		List<SProducts> productsList = shareDao.findSProductsList(sMainData.getShareId());
		for (SProducts sProducts : productsList) {
		    productIdList.add(sProducts.getProductId());
		    shelveIds.add(sProducts.getShelveId());
		}
		List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
		// 获取图片信息
		List<PProducts> findPProductsList = shareDao.findPProductsList(productIdList);
		for (SProducts sProducts : productsList) {
			for (PProducts pro : findPProductsList) {
				if (sProducts.getProductId().longValue()==pro.getProductId().longValue()) {
				    ProductVO temp = new ProductVO();
					if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
					    // 获取上架ID
					    temp.setShelveId(sProducts.getShelveId());
					}
				    if (findClassProducts != null && findClassProducts.size() > 0) {
					for (PClassProducts pClassProducts : findClassProducts) {
					    if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
						// 获取上架人
						temp.setSellerWid(pClassProducts.getWeiId());
						break;
					    }
					}
				    }
				    temp.setCommission(pro.getDefaultConmision());
				    temp.setProductId(pro.getProductId());
				    temp.setProductName(pro.getProductTitle());
				    temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
				    temp.setRetailPrice(pro.getDefaultPrice());
				    temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
				    imgList.add(temp);
				    break;
				}
			}
		}
		hsv.setProductList(imgList);
		
		if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
			Map<String, String> getyunYingInfos = AppSettingUtil.getyunYingInfos();
			for (Entry<String, String> info : getyunYingInfos.entrySet()) {
				if (info.getKey().equals(sMainData.getCtsUser())) {
					hsv.setShareOneImgUrl(info.getValue());
					// 店铺名称
					hsv.setShareOneShopName(info.getKey());
				}
			}
			// 店铺图片路径
		}else{
			// 店铺图片路径
			hsv.setShareOneImgUrl(baseCommonService.getImageById(sMainData.getWeiId()));
			// 店铺名称
			hsv.setShareOneShopName(baseCommonService.getNickNameById(sMainData.getWeiId()));
		}
		// 如果它更新了
		hsv.setShareDate(TransformationDate(sMainData.getCreateTime()));
		if (sMainData.getUpdateTime() != null) {
		    hsv.setLastEditDate(TransformationDate(sMainData.getUpdateTime()));
		} else {
		    hsv.setLastEditDate(TransformationDate(sMainData.getCreateTime()));
		}
		HSVList.add(hsv);
	    }

	    if (shareIdList != null && shareIdList.size() > 0) {
		List<SStatics> findSStaticsList = shareDao.findSStaticsList(shareIdList);
//		List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);
		List<SZambiaActive> findSZambiaActiveList = null;
		if (weiId != 0) {
		    findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
		}

		for (HotShareVO hotShareVO : HSVList) {
			if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
				// 未点赞
				hotShareVO.setIsLike(0);
				for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
					if (hotShareVO.getPageId() == sZambiaActive.getShareId()&& sZambiaActive.getShareWeiId()!=null && sZambiaActive.getShareWeiId().longValue() == hotShareVO.getPageProducer()) {
						// 已点赞
						hotShareVO.setIsLike(1);
						break;
					}
				}
			}
			if (findSStaticsList != null && findSStaticsList.size() > 0) {
				hotShareVO.setShareCount(0);
				// 获取分享数量
				for (SStatics sStatics : findSStaticsList) {
					if (hotShareVO.getPageId() == sStatics.getShareId().intValue()) {
						hotShareVO.setShareCount(
								(sStatics.getWebSv()==null?0:sStatics.getWebSv())+(sStatics.getWapSv()==null?0:sStatics.getWapSv())+(sStatics.getAppSv()==null?0:sStatics.getAppSv()));
						hotShareVO.setLikeCount(sStatics.getZanCount()==null?0:sStatics.getZanCount());
						break;
					}
				}
			}
//			if (dianZanCount != null && dianZanCount.size() > 0) {
//				hotShareVO.setLikeCount(0);
			
//				// 从点赞表中获取点赞的数据
//				for (DianZanCountVO dianZanCountVO : dianZanCount) {
//					if (dianZanCountVO.getShareId().intValue() == hotShareVO.getPageId() ) {
//						hotShareVO.setLikeCount(dianZanCountVO.getCount());
//						break;
//					}
//				}
//			}
		}

	    }

	} else {
	    rm.setStatusreson("数据为空！");
	    return rm;
	}

	hslv.setList(HSVList);
	hslv.setPageIndex(findSMainDataList.getPageIndex());
	hslv.setPageSize(findSMainDataList.getPageSize());
	hslv.setTotalPage(findSMainDataList.getTotalPage());
	hslv.setTotalCount(findSMainDataList.getTotalCount());
	rm.setBasemodle(hslv);
	return rm;
    }

    
    public ReturnModel getShareUrl1(Long shareId,Long shareOne,LoginUser user){
		ReturnModel rqModel=new ReturnModel();
		rqModel.setStatu(ReturnStatus.Success); 
		rqModel.setStatusreson("成功！");
		int shareCount=500;//总共分享次数
		
		int between=0; //距离第二天的还有多少秒
		long loginWeiid=user.getWeiID();
		if(shareId==null)shareId=1L;
		if(shareOne==null) shareOne=1L;
		shareServices.addShareCount(shareOne, shareId, 3);
		//每天可以发布5次(中每条可以无限发) 分享
		Integer i=0; 
		//记录 分享的Id
		List<Long> arr =(List<Long>) RedisUtil.getObject("arr+weiID1="+loginWeiid);
		if (arr==null||arr.size()==0) {
			arr=new ArrayList<Long>();
		}  //分享了几次
		i = RedisUtil.getObject("weiID="+loginWeiid)==null?0: Integer.parseInt(RedisUtil.getObject("weiID="+loginWeiid).toString());
		SMainData shareMain = shareDao.get(SMainData.class, shareId);
		if(shareMain!=null){ 
			try {
				SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss");
				 Date date = new Date();
				 Date begin=dfs.parse(dfs.format(date.getTime()));
				 Date end = dfs.parse("24:59:59");
				 //计算里第二天还有多少秒 将其设为redis的有效时间
				 between=(int) ((end.getTime()-begin.getTime())/1000);//除以1000是为了转换成秒
				if (arr!=null&&arr.size()>0&&arr.contains(shareId)&&shareMain.getOnHomePage()!=null&&shareMain.getOnHomePage().shortValue()==ParseHelper.toShort(ShareOnHomePage.Yes.toString())) {
					//分享过的  
				}else{
					if (i==null||i.intValue()<shareCount) { 
						arr.add(shareId);
						RedisUtil.setObject("arr+weiID1="+loginWeiid,arr, between);
						//没有分享过的
						RedisUtil.setObject("weiID="+loginWeiid, i==null?1:i+1, between);
					}else{
						rqModel.setStatu(ReturnStatus.ParamError); 
						rqModel.setStatusreson("每天只可以分享5次!");
						return rqModel;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}else{
			rqModel.setStatu(ReturnStatus.ParamError); 
			rqModel.setStatusreson("不存在该分享信息!");
		}
		RedisUtil.setObject("weiID="+loginWeiid, i==null?1:i+1, between);
		return rqModel;
	}
    
    public ReturnModel findUCollectionStoreList(long weiId, Limit limit) {
	ReturnModel rm = new ReturnModel();
	if (weiId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("该微店号不存在！");
	    return rm;
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	HotShareListVO hslv = new HotShareListVO();
	List<Long> shareIdList = new ArrayList<Long>();
	PageResult<UCollectionStore> findUCollectionStoreList = shareDao.findUCollectionStoreList(weiId, limit);
	List<UCollectionStore> list = findUCollectionStoreList.getList();
	if (list != null && list.size() > 0) {
	    for (UCollectionStore uCollectionStore : list) {
		shareIdList.add(uCollectionStore.getThingId());
	    }
	    List<HotShareVO> HSVList = new ArrayList<HotShareVO>();
	    List<SMainData> mainDataList = shareDao.findSMainDataList(shareIdList);
	    if (mainDataList != null && mainDataList.size() > 0) {
		for (SMainData sMainData : mainDataList) {
		    HotShareVO hsv = new HotShareVO();
		    hsv.setIsForward(0);
		    hsv.setPageId(sMainData.getShareId());
		    hsv.setPageTitle(sMainData.getTitle());
		    hsv.setPageDescription(sMainData.getDescrible());
		    hsv.setShareOne(sMainData.getWeiId());
		    hsv.setPageProducer(sMainData.getWeiId());
		    List<ProductVO> imgList = new ArrayList<ProductVO>();
		    // 获取商品ID
		    List<Long> productIdList = new ArrayList<Long>();
		    List<Long> shelveIds = new ArrayList<Long>();
		    List<SProducts> productsList = shareDao.findSProductsList(sMainData.getShareId());
		    for (SProducts sProducts : productsList) {
			productIdList.add(sProducts.getProductId());
			shelveIds.add(sProducts.getShelveId());
		    }
		    List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
		    // 获取图片信息
		    List<PProducts> findPProductsList = shareDao.findPProductsList(productIdList);
		    for (PProducts pro : findPProductsList) {
			ProductVO temp = new ProductVO();
			for (SProducts sProducts : productsList) {
			    if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
				// 获取上架ID
				temp.setShelveId(sProducts.getShelveId());
				temp.setSpid(sProducts.getSpid());
			    }
			}
			if (findClassProducts != null && findClassProducts.size() > 0) {
			    for (PClassProducts pClassProducts : findClassProducts) {
				if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
				    // 获取上架人
				    temp.setSellerWid(pClassProducts.getWeiId());
				    break;
				}
			    }
			}
			temp.setCommission(pro.getDefaultConmision());
			temp.setProductId(pro.getProductId());
			temp.setProductName(pro.getProductTitle());
			temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
			temp.setRetailPrice(pro.getDefaultPrice());
			temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
			 short shareType=sMainData.getShareType()==null?(short)1:sMainData.getShareType().shortValue();
	    	    //如果是单品时  添加一个新的对象  图片详情和描述  
	    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
	    	    	List<ProductsVO> list1=new ArrayList<ProductsVO>();
	    	    	//根据产品Id获取 商品详情
	    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(temp.getSpid());
	    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
	    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
	    	    			ProductsVO vo=new ProductsVO();
	    	    			vo.setSid(sSingleDesc.getSid());
	    	    			vo.setShareDescription(sSingleDesc.getDescription());
	    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
	    	    			list1.add(vo);
	    				}
	    	    		temp.setProductPictureList(list1);
	    	    	}
	    	    }
			imgList.add(temp);
		    }
		    hsv.setProductList(imgList);
		    // 店铺图片路径
		    hsv.setShareOneImgUrl(baseCommonService.getImageById(sMainData.getWeiId()));
		    // 店铺名称
		    hsv.setShareOneShopName(baseCommonService.getNickNameById(sMainData.getWeiId()));
		    // 如果它更新了
		    hsv.setShareDate(TransformationDate(sMainData.getCreateTime()));
		    /*
		     * if (sMainData.getUpdateTime()!=null) {
		     * hsv.setLastEditDate
		     * (TransformationDate(sMainData.getUpdateTime())); }else{
		     * hsv
		     * .setLastEditDate(TransformationDate(sMainData.getCreateTime
		     * ())); }
		     */
		    HSVList.add(hsv);
		} 
				if (shareIdList != null && shareIdList.size() > 0) {
					List<SStatics> findSStaticsList = shareDao.findSStaticsList(shareIdList);
					List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);

					List<SZambiaActive> findSZambiaActiveList = null;
					if (weiId != 0) {
						findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
					}

					for (HotShareVO hotShareVO : HSVList) {
						for (UCollectionStore collectionStore : list) {
							if (hotShareVO.getPageId() == collectionStore.getThingId()) {
								// 收藏时间
								hotShareVO.setLastEditDate(TransformationDate(collectionStore.getCreateTime()));
								hotShareVO.setIsCollected(1);
								break;
							}
						}

						if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
							// 未点赞
							hotShareVO.setIsLike(0);
							for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
								if (hotShareVO.getPageId() == sZambiaActive.getShareId() && sZambiaActive.getShareWeiId().longValue() == hotShareVO.getPageProducer()) {
									// 已点赞
									hotShareVO.setIsLike(1);
									break;
								}
							}
						}
						if (findSStaticsList != null && findSStaticsList.size() > 0) {
							hotShareVO.setShareCount(0);
							// 获取分享数量
							for (SStatics sStatics : findSStaticsList) {
								if (hotShareVO.getPageId() == sStatics.getShareId().intValue()) {
									hotShareVO.setShareCount(
											(sStatics.getWebSv()==null?0:sStatics.getWebSv())+(sStatics.getWapSv()==null?0:sStatics.getWapSv())+(sStatics.getAppSv()==null?0:sStatics.getAppSv()));
									break;
								}
							}
						}
						if (dianZanCount != null && dianZanCount.size() > 0) {
							hotShareVO.setLikeCount(0);
							// 从点赞表中获取点赞的数据
							for (DianZanCountVO dianZanCountVO : dianZanCount) {
								if (dianZanCountVO.getShareId().intValue() == hotShareVO.getPageId()) {
									hotShareVO.setLikeCount(dianZanCountVO.getCount());
									break;
								}
							}
						}
					}

				}

	    } else {
		rm.setStatusreson("数据为空！");
		return rm;
	    }

	    hslv.setList(HSVList);
	}
	hslv.setPageIndex(findUCollectionStoreList.getPageIndex());
	hslv.setPageSize(findUCollectionStoreList.getPageSize());
	hslv.setTotalPage(findUCollectionStoreList.getTotalPage());
	hslv.setTotalCount(findUCollectionStoreList.getTotalCount());
	rm.setBasemodle(hslv);
	return rm;
    }

    /**
     * 转换时间
     * 
     * @return
     */
    public String TransformationDate(Date date) {
	String dateString = "";
	// 获取系统当前时间
	// Date now = new Date();
	// long endTime = now.getTime();
	Calendar cals = Calendar.getInstance();
	cals.setTime(date);
	// long currentTime= cals.getTimeInMillis();
	// // 计算两个时间点相差的秒数
	// long seconds = (endTime - currentTime) / 1000;
	// //小时差
	// long h = seconds % (3600 * 24) / 3600;
	// //分钟差
	// long m = seconds % (3600 * 24) % 3600 / 60;
	// if (h==0&&m<60&&m>=0) {
	// //这个if是防止用户提交的时间没满一分钟则分钟加1
	// if(m==0)m++;
	// dateString= m +"分钟前";
	// }else{
	// 超过一小时直接展示具体时间
	dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cals.getTime());
	// }
	return dateString;
    }

    @Override
    public ReturnModel findMyShareList(Long weiId, Limit limit) {
	ReturnModel rm = new ReturnModel();
	if (weiId == null || weiId.longValue() == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("该微店号不存在！");
	    return rm;
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	List<MyShareVO> MyShareVOList = new ArrayList<MyShareVO>();
	MyShareListVO msv = new MyShareListVO();
	// 分享ID集合
	List<Long> shareIdList = new ArrayList<Long>();
	// 1、分析 因为是查自己的分享记录 所以以 S_ShareActive 为主进行分页操作
	PageResult<SShareActive> findSShareActiveList = shareDao.findSShareActiveList(weiId, limit);
	List<SShareActive> list = findSShareActiveList.getList();
	if (list != null && list.size() > 0) {
	    for (SShareActive sShareActive : list) {
		// 2、从上一步骤中获取分享记录的ID
		shareIdList.add(sShareActive.getShareId());
	    }
	} else {
	    rm.setStatusreson("没有数据！");
	    return rm;
	} 
	// 3、然后通过分享记录的ID 从S_MainData 中获取 分享的标题等信息
	List<SMainData> findSMainDataList = shareDao.findSMainDataList(shareIdList);
	// 4、然后通过分享记录的ID 从 S_Products 中获取 产品Id
	for (SShareActive sShareActive : list) {
	    MyShareVO mv = new MyShareVO();
	    if (sShareActive.getMakeWeiId()!=null&&sShareActive.getWeiId().longValue() == sShareActive.getMakeWeiId()) {
		// 原创分享 为0
		mv.setIsForward(0);
	    } else {
		// 转的分享 为1
		mv.setIsForward(1);
	    }
	    mv.setPageId(sShareActive.getShareId());
	    mv.setPageProducer(sShareActive.getMakeWeiId());
	    if (findSMainDataList != null && findSMainDataList.size() > 0) {
		    for (SMainData sMainData : findSMainDataList) {
				if (sShareActive.getShareId().intValue() == sMainData.getShareId().intValue()) {
				    // 标题
				    mv.setPageTitle(sMainData.getTitle());
				    mv.setPageTemplate(sMainData.getShareType()==null?1:sMainData.getShareType());
				    // 推广语
				    mv.setPageDescription(sMainData.getDescrible());
				    if (sMainData.getUpdateTime() != null) {
					mv.setLastEditDate(DateUtils.format(sMainData.getUpdateTime(), ""));
				    } else {
					mv.setLastEditDate(DateUtils.format(sMainData.getCreateTime(), ""));
				    }
				    break;
				}
		    }
	    }
	    List<ProductVO> imgList = new ArrayList<ProductVO>();
	    // 获取商品ID
	    List<Long> productIdList = new ArrayList<Long>();
	    List<Long> shelveIds = new ArrayList<Long>();
	    List<SProducts> productsList = shareDao.findSProductsList(sShareActive.getShareId());
	    for (SProducts sProducts : productsList) {
			productIdList.add(sProducts.getProductId());
			shelveIds.add(sProducts.getShelveId());
	    }
	    List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
	    // 获取图片信息
	    List<PProducts> findPProductsList = shareDao.findPProductsList(productIdList);
	    if (findPProductsList != null && findPProductsList.size() > 0) {	
		    for (SProducts sProducts : productsList) {
			    for (PProducts pro : findPProductsList) {
					ProductVO temp = new ProductVO();
					if (sProducts.getProductId().longValue()==pro.getProductId().longValue()) {
					    if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
						// 上架Id
						temp.setShelveId(sProducts.getShelveId());
					    }
						if (findClassProducts != null && findClassProducts.size() > 0) {
						    for (PClassProducts pClassProducts : findClassProducts) {
							if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
							    // 获取上架人
							    temp.setSellerWid(pClassProducts.getWeiId());
							    break;
							}
						    }
						}
						temp.setCommission(pro.getDefaultConmision());
						temp.setProductId(pro.getProductId());
						temp.setProductName(pro.getProductTitle());
						temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
						temp.setRetailPrice(pro.getDefaultPrice());
						temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
						imgList.add(temp);
					}
			    }
		    }
	    }
	    mv.setProductList(imgList);
	    MyShareVOList.add(mv);
	}
	List<SZambiaActive> findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
	if (MyShareVOList != null && MyShareVOList.size() > 0) {
	for (MyShareVO hotShareVO : MyShareVOList) {
	    if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
		hotShareVO.setIsLike(0);
		for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
		    // 未点赞
		    if (hotShareVO.getPageId() == sZambiaActive.getShareId() && sZambiaActive.getShareWeiId().longValue() == weiId.longValue()) {
			// 已点赞
			hotShareVO.setIsLike(1);
			break;
		    }
		}
	    }
	}
	}
	msv.setList(MyShareVOList);
	msv.setPageIndex(findSShareActiveList.getPageIndex());
	msv.setPageSize(findSShareActiveList.getPageSize());
	msv.setTotalPage(findSShareActiveList.getTotalPage());
	msv.setTotalCount(findSShareActiveList.getTotalCount());
	rm.setBasemodle(msv);
	return rm;
    }

    @Override
    public ReturnModel findProductList(long weiId, long shareId, Limit limit) {
	ReturnModel rm = new ReturnModel();
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	if (shareId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("参数错误");
	    return rm;
	}
	ShareProductListVO splv = new ShareProductListVO();
	PageResult<SProducts> findSProductsList = basicShareDAO.findSProductsList(shareId, limit);
	List<SProducts> list = findSProductsList.getList();
	List<ProductVO> resultList = new ArrayList<ProductVO>();
	// 获取排序完成后的商品Id信息
	List<Long> productIds = new ArrayList<Long>();
	List<Long> shelveIds = new ArrayList<Long>();
	if (list != null && list.size() > 0) {
	    for (SProducts sProducts : list) {
		productIds.add(sProducts.getProductId());
		shelveIds.add(sProducts.getShelveId());
	    }
	} else {
	    rm.setStatu(ReturnStatus.Success);
	    rm.setStatusreson("数据为空！");
	    return rm;
	}
	List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
	if (productIds != null && productIds.size() > 0) {
	    // 查询产品辅助表
	    Map<String, Object> paramRel = new HashMap<String, Object>();
	    paramRel.put("proids", productIds);
	    // 根据商品Id获取商品信息
	    List<PProducts> proList = baseProductDao.findProductlistByIds(productIds, null);
	    for (int i=0;i<list.size();i++) {
	    	SProducts sProducts = list.get(i);
		    for (PProducts pro : proList) {
			    if (sProducts.getProductId().longValue()==pro.getProductId().longValue()) {
				ProductVO temp = new ProductVO();
			    if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
				// 上架Id
				temp.setShelveId(sProducts.getShelveId());
			    }
			
				if (findClassProducts != null && findClassProducts.size() > 0) {
				    for (PClassProducts pClassProducts : findClassProducts) {
					if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
					    // 获取上架人
					    temp.setSellerWid(pClassProducts.getWeiId());
					    break;
					}
				    }
				}
				temp.setCommission(pro.getDefaultConmision());
				temp.setProductId(pro.getProductId());
				temp.setProductName(pro.getProductTitle());
				temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 75));
				
				temp.setShareDescription(sProducts.getDescription());
				temp.setRetailPrice(pro.getDefaultPrice());
				temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
				resultList.add(temp);
			    }
			}
		}
	}
	splv.setList(resultList);
	splv.setPageIndex(findSProductsList.getPageIndex());
	splv.setPageSize(findSProductsList.getPageSize());
	splv.setTotalCount(findSProductsList.getTotalCount());
	splv.setTotalPage(findSProductsList.getTotalPage());
	rm.setBasemodle(splv);
	return rm;
    }

    @Override
	public ReturnModel GetWapShareDetails(long weiId, long shareOne, long shareId, Limit limit) {
    	ReturnModel rm = new ReturnModel();
    	SStatics staticsModel = basicShareDAO.get(SStatics.class, shareId);
    	if (staticsModel!=null) { 
    		staticsModel.setWapPv(staticsModel.getWapPv()==null?1:staticsModel.getWapPv()+1);
    		basicShareDAO.update(staticsModel);
    	}
    	SShareActive sShareActive = basicShareDAO.getSShareActive(shareId, shareOne);
    	if (sShareActive == null) {
    	    sShareActive = new SShareActive();
    	    sShareActive.setMakeWeiId(111l);
    	    // 默认分给微店网
    	    sShareActive.setWeiId(111l);
    	    sShareActive.setCreateTime(new Date());
    	}
    	rm.setStatu(ReturnStatus.Success);
    	rm.setStatusreson("成功！");
    	HotShareVO hsv = new HotShareVO();
    	// 该分享信息是否转发
    	if (sShareActive.getMakeWeiId().longValue() == sShareActive.getWeiId()) {
    	    // 否
    	    hsv.setIsForward(0);
    	} else {
    	    // 是
    	    hsv.setIsForward(1);
    	}
    	List<Long> shareIdList = new ArrayList<Long>();
    	SMainData sMainData = shareDao.get(SMainData.class, shareId);
    	if (sMainData != null) {
    	    shareIdList.add(sMainData.getShareId());
    	    hsv.setPageTemplate(sMainData.getShareType()==null?(short)1:sMainData.getShareType());
    	    hsv.setPageId(sMainData.getShareId());
    	    hsv.setPageTitle(sMainData.getTitle());
    	    hsv.setPageDescription(sMainData.getDescrible());
    	    hsv.setShareOne(sShareActive.getWeiId());
    	    hsv.setShareDate(DateUtils.format(sShareActive.getCreateTime() == null ? new Date() : sShareActive.getCreateTime(), ""));
    	    //userType 为3 是运营发布   制作人归微店网
    	    if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
    	    	hsv.setPageProducer(111l);
    		}else{
    			hsv.setPageProducer(sMainData.getWeiId());
    		}
    	    // 获取商品ID
    	    List<Long> productIdList = new ArrayList<Long>();
    	    PageResult<SProducts> findSProductsList = basicShareDAO.findSProductsList(sMainData.getShareId(), limit);
    	    List<SProducts> productsList = findSProductsList.getList();
    	    if (productsList != null && productsList.size() > 0) {
    		for (SProducts sProducts : productsList) {
    		    productIdList.add(sProducts.getProductId());
    		}
    	    }
    	    // 店铺图片路径
//    	    hsv.setShareOneImgUrl(baseCommonService.getImageById(sMainData.getWeiId()));
//    	    // 店铺名称
    	    hsv.setShareOneShopName(baseCommonService.getNickNameById(sMainData.getWeiId()));
    	    // 如果它更新了
    	    if (sMainData.getUpdateTime() != null) {
    		hsv.setLastEditDate(TransformationDate(sMainData.getUpdateTime()));
    	    } else {
    		hsv.setShareDate(TransformationDate(sMainData.getCreateTime()));
    	    }
    	    List<ProductVO> productList = new ArrayList<ProductVO>();
    	    if (productIdList != null && productIdList.size() > 0) {
	    		// 根据商品Id获取商品信息
	    		List<PProducts> findProductlistByIds = baseProductDao.findProductlistByIds(productIdList, null);
	    		for (SProducts sProducts : productsList) {
		    		for (PProducts pProducts : findProductlistByIds) {
		    		    ProductVO spv = new ProductVO();
		    		    if (sProducts.getProductId().longValue()==pProducts.getProductId().longValue()) {
		    		    	spv.setShelveId(sProducts.getShelveId());
		    		    	spv.setProductPicture(ImgDomain.GetFullImgUrl(pProducts.getDefaultImg(),24));
		    		    	spv.setProductName(pProducts.getProductTitle());
		    		    	spv.setRetailPrice(pProducts.getDefaultPrice());
		    		    	if (null != pProducts.getOriginalPrice()) {
		    		    		spv.setDisplayPrice(pProducts.getOriginalPrice());
		    		    	} else {
		    		    		double percent = 1.3;
		    		    		if (null != pProducts.getPercent())
		    		    			percent = pProducts.getPercent();
		    		    		double displayPrice = pProducts.getDefaultPrice() * percent;
		    		    		DecimalFormat df = new DecimalFormat("#.00");
		    		    		displayPrice = Double.parseDouble(df.format(displayPrice));
		    		    		spv.setDisplayPrice(displayPrice);
		    		    	}
		    		    	spv.setProductId(pProducts.getProductId());
		    		    	  // 商品描述
						    spv.setShareDescription(sProducts.getDescription());
				    	    short shareType=sMainData.getShareType()==null?(short)1:sMainData.getShareType().shortValue();
				    	    //如果是单品时  添加一个新的对象  图片详情和描述  
				    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
				    	    	List<ProductsVO> list=new ArrayList<ProductsVO>();
				    	    	//根据产品Id获取 商品详情
				    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(sProducts.getSpid());
				    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
				    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
				    	    			ProductsVO vo=new ProductsVO();
				    	    			vo.setSid(sSingleDesc.getSid());
				    	    			vo.setShareDescription(sSingleDesc.getDescription());
				    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
				    	    			list.add(vo);
				    				}
				    	    		spv.setProductPictureList(list);
				    	    	}
				    	    }
		    		    	productList.add(spv);
		    		    	break;
						}
		    		}
	    		}
    	    }
    	    hsv.setProductList(productList);
    	
    	    if (shareIdList != null && shareIdList.size() > 0) {
//    		List<SStatics> findSStaticsList = shareDao.findSStaticsList(shareIdList);
//    		List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);
    		List<SZambiaActive> findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
    		if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
    		    for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
    			// 未点赞
    			hsv.setIsLike(0);
    			if (hsv.getPageId() == sZambiaActive.getShareId()) {
    			    // 已点赞
    			    hsv.setIsLike(1);
    			    break;
    			}
    		    }
    		}
//    		if (findSStaticsList != null && findSStaticsList.size() > 0) {
//    		    // 获取分享数量
//    		    for (SStatics sStatics : findSStaticsList) {
//    			if (hsv.getPageId() == sStatics.getShareId().intValue()) {
//    			    hsv.setShareCount(
//    			    		(sStatics.getWebSv()==null?0:sStatics.getWebSv())+(sStatics.getWapSv()==null?0:sStatics.getWapSv())+(sStatics.getAppSv()==null?0:sStatics.getAppSv()));
//    			    break;
//    			}
//    		    }
//    		}
    	 
    	    }
    	} else {
    	    rm.setStatusreson("数据为空！");
    	    return rm;
    	}
    	rm.setBasemodle(hsv);
    	return rm;

        }
    
    
    @Override
    public ReturnModel GetShareDetails(long weiId, long shareOne, long shareId, Limit limit) {
	ReturnModel rm = new ReturnModel();
	SStatics staticsModel = basicShareDAO.get(SStatics.class, shareId);
	if (staticsModel!=null) { 
		staticsModel.setAppPv(staticsModel.getAppPv()==null?1:staticsModel.getAppPv()+1);
		basicShareDAO.update(staticsModel);
	}
	else
	{
		staticsModel = new SStatics();
		staticsModel.setWeiId(weiId);
		staticsModel.setWebPv(0);
		staticsModel.setWebSv(0);
		staticsModel.setAppPv(1);
		staticsModel.setAppSv(0);
		staticsModel.setShareId(shareId);
		staticsModel.setWapPv(0);
		staticsModel.setWapSv(0);
		staticsModel.setTurnover(0.0);
		staticsModel.setVol(0);
		staticsModel.setCommission(0.0);
		Long count=basicShareDAO.count("select count(*) from SProducts s where s.shareId=? and s.weiId=?", shareId,weiId); 
		staticsModel.setPcount(ParseHelper.toInt(count.toString()));
		basicShareDAO.save(staticsModel);
	}
	rm.setStatu(ReturnStatus.Success);
	rm.setStatusreson("成功！");
	HotShareVO hsv = new HotShareVO();
	SShareActive sShareActive = basicShareDAO.getSShareActive(shareId, shareOne);
	if (sShareActive == null) {
	    sShareActive = new SShareActive();
	    sShareActive.setMakeWeiId(111l);
	    // 默认分给微店网
	    sShareActive.setWeiId(111l);
	    sShareActive.setCreateTime(new Date());
	}
	// 该分享信息是否转发
	if (sShareActive.getMakeWeiId().longValue() == sShareActive.getWeiId()) {
	    // 否
	    hsv.setIsForward(0);
	} else {
	    // 是
	    hsv.setIsForward(1);
	}
	List<Long> shareIdList = new ArrayList<Long>();
	SMainData sMainData = shareDao.get(SMainData.class, shareId);
	if (sMainData != null) {
	    shareIdList.add(sMainData.getShareId());
	    hsv.setPageTemplate(sMainData.getShareType()==null?(short)1:sMainData.getShareType());
	    hsv.setPageId(sMainData.getShareId());
	    hsv.setPageTitle(sMainData.getTitle());
	    hsv.setPageDescription(sMainData.getDescrible());
//	    hsv.setShareOne(sShareActive.getWeiId());
	    hsv.setShareOne(shareOne);
	    hsv.setShareDate(DateUtils.format(sShareActive.getCreateTime() == null ? new Date() : sShareActive.getCreateTime(), ""));
	    //userType 为3 是运营发布   制作人归微店网
	    if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
	    	hsv.setPageProducer(111l);
		}else{
			hsv.setPageProducer(sMainData.getWeiId());
		}
	    // 获取商品ID
	    List<Long> productIdList = new ArrayList<Long>();
	    List<Long> shelveIds = new ArrayList<Long>();
	    PageResult<SProducts> findSProductsList = basicShareDAO.findSProductsList(sMainData.getShareId(), limit);
	    List<SProducts> productsList = findSProductsList.getList();
	    if (productsList != null && productsList.size() > 0) {
		for (SProducts sProducts : productsList) {
		    productIdList.add(sProducts.getProductId());
			shelveIds.add(sProducts.getShelveId());
		}
	    }
	    //是否收藏   1 为已收藏
	    UCollectionStore uCollectionStore = shareDao.getUCollectionStore(shareId,weiId,ParseHelper.toShort(CollectionType.Share.toString()));
	    if (uCollectionStore!=null) {
	    	hsv.setIsCollected(1);
		}
	    // 店铺图片路径
//	    hsv.setShareOneImgUrl(baseCommonService.getImageById(sMainData.getWeiId()));
	    hsv.setShareOneImgUrl(baseCommonService.getImageById(shareOne));
	    // 店铺名称
//	    hsv.setShareOneShopName(baseCommonService.getNickNameById(sMainData.getWeiId()));
	    hsv.setShareOneShopName(baseCommonService.getNickNameById(shareOne));
	    // 如果它更新了
	    if (sMainData.getUpdateTime() != null) {
		hsv.setLastEditDate(TransformationDate(sMainData.getUpdateTime()));
	    } else {
		hsv.setShareDate(TransformationDate(sMainData.getCreateTime()));
	    }
	    List<ProductVO> productList = new ArrayList<ProductVO>();
	    if (productIdList != null && productIdList.size() > 0) {
	    	List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
			// 根据商品Id获取商品信息
			List<PProducts> findProductlistByIds = baseProductDao.findProductlistByIds(productIdList, null);
			for (int i=0;i<productsList.size();i++) {
				SProducts sProducts =productsList.get(i);
				for (PProducts pProducts : findProductlistByIds) {
					if (sProducts.getProductId().longValue()==pProducts.getProductId().longValue()) {
					    ProductVO spv = new ProductVO();
						if (findClassProducts != null && findClassProducts.size() > 0) {
						    for (PClassProducts pClassProducts : findClassProducts) {
							if (sProducts.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
							    // 获取上架人
								spv.setSellerWid(pClassProducts.getWeiId());
							    break;
							}
						    }
						}
					    spv.setSpid(sProducts.getSpid());
					    spv.setShelveId(sProducts.getShelveId());
					    if(i==0)
					    {
					    	spv.setProductPicture(ImgDomain.GetFullImgUrl(pProducts.getDefaultImg(),75));
					    }
					    else
					    {
					    	spv.setProductPicture(ImgDomain.GetFullImgUrl(pProducts.getDefaultImg()));
					    }
					    spv.setProductName(pProducts.getProductTitle());
					    spv.setRetailPrice(pProducts.getDefaultPrice());
					    if (null != pProducts.getOriginalPrice()) {
						spv.setDisplayPrice(pProducts.getOriginalPrice());
					    } else {
						double percent = 1.3;
						if (null != pProducts.getPercent())
						    percent = pProducts.getPercent();
						double displayPrice = pProducts.getDefaultPrice() * percent;
						DecimalFormat df = new DecimalFormat("#.00");
						displayPrice = Double.parseDouble(df.format(displayPrice));
						spv.setDisplayPrice(displayPrice);
					    }
					    spv.setProductId(pProducts.getProductId());
					    spv.setShareDescription(sProducts.getDescription());
			    	    short shareType=sMainData.getShareType()==null?(short)1:sMainData.getShareType().shortValue();
			    	    //如果是单品时  添加一个新的对象  图片详情和描述  
			    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
			    	    	List<ProductsVO> list=new ArrayList<ProductsVO>();
			    	    	//根据产品Id获取 商品详情
			    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(sProducts.getSpid());
			    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
			    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
			    	    			ProductsVO vo=new ProductsVO();
			    	    			vo.setSid(sSingleDesc.getSid());
			    	    			vo.setShareDescription(sSingleDesc.getDescription());
			    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
			    	    			list.add(vo);
			    				}
			    	    		spv.setProductPictureList(list);
			    	    	}
			    	    }
					    productList.add(spv);
						break;
					}
				}
			}
	    }
	    hsv.setProductList(productList);
	    if (shareIdList != null && shareIdList.size() > 0) {
		List<SStatics> findSStaticsList = shareDao.findSStaticsList(shareIdList);
		List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);
		List<SZambiaActive> findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);

		if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
		    for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
			// 未点赞
			hsv.setIsLike(0);
			if (hsv.getPageId() == sZambiaActive.getShareId()) {
			    // 已点赞
			    hsv.setIsLike(1);
			    break;
			}
		    }
		}
		if (findSStaticsList != null && findSStaticsList.size() > 0) {
		    // 获取分享数量
		    for (SStatics sStatics : findSStaticsList) {
			if (hsv.getPageId() == sStatics.getShareId().intValue()) {
			    hsv.setShareCount(
			    		(sStatics.getWebSv()==null?0:sStatics.getWebSv())+(sStatics.getWapSv()==null?0:sStatics.getWapSv())+(sStatics.getAppSv()==null?0:sStatics.getAppSv()));
			    break;
			}
		    }
		}
		if (dianZanCount != null && dianZanCount.size() > 0) {
		    // 从点赞表中获取点赞的数据
		    for (DianZanCountVO dianZanCountVO : dianZanCount) {
			if (dianZanCountVO.getShareId().intValue() == hsv.getPageId() ) {
			    hsv.setLikeCount(dianZanCountVO.getCount());
			    break;
			}
		    }
		}
	    }
	} else {
	    rm.setStatusreson("数据为空！");
	    return rm;
	}
	rm.setBasemodle(hsv);
	return rm;

    }

    @Override
    public ReturnModel updateSharePage(long weiId, long shareOne, Integer shareId) {
	ReturnModel rm = new ReturnModel();
	if (shareId == null || weiId == 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("参数错误");
	    return rm;
	}
	rm = shareServices.updateSMainData(weiId, shareOne, shareId);
	return rm;
    }

    @Override
    public ReturnModel addcollectSharePage(Long weiid, Long pageid, String key) {
	ReturnModel result = new ReturnModel();
	result.setStatu(ReturnStatus.DataError);
	if (pageid <= 0) {
	    result.setStatusreson("参数有误");
	    return result;
	}
	if ("add".equals(key)) {
	    // 判断有没有收藏过
	    UCollectionStore entity = shareDao.getUniqueResultByHql("from UCollectionStore where weiId=? and thingId=? and thingType=? and state=1", new Object[] { weiid, pageid, Short.parseShort(CollectionType.Share.toString()) });
	    if (entity == null) {
		// 添加收藏
		entity = new UCollectionStore();
		entity.setWeiId(weiid);
		entity.setThingId(pageid);
		entity.setThingType(Short.parseShort(CollectionType.Share.toString()));
		entity.setState((short) 1);
		entity.setCreateTime(new Date());
		shareDao.save(entity);
	    } else {
		result.setStatusreson("已经收藏过");
		return result;
	    }
	} else if ("cancel".equals(key)) {
	    // 取消收藏
	    UCollectionStore entity = shareDao.getUniqueResultByHql("from UCollectionStore where weiId=? and thingId=? and thingType=? and state=1", new Object[] { weiid, pageid, Short.parseShort(CollectionType.Share.toString()) });
	    if (entity != null) {
		shareDao.delete(entity);
	    } else {
		result.setStatusreson("数据有误");
		return result;
	    }
	} else {
	    result.setStatusreson("参数有误");
	    return result;
	}
	result.setStatu(ReturnStatus.Success);
	return result;
    }

    @Override
	public ReturnModel findWapHotShareList(long weiId, Limit limit) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		HotShareListVO hslv = new HotShareListVO();
		List<Long> shareIdList = new ArrayList<Long>();
		String redisCommenString = "weiId_"+weiId + "_index_" + limit.getPageId() + "_size_" + limit.getSize();
		PageResult<SMainData> findSMainDataList=(PageResult<SMainData>)RedisUtil.getObject("resfindSMainDataList_"+redisCommenString);
		List<HotShareVO> HSVList =(List<HotShareVO>) RedisUtil.getObject("reshsvlist_"+redisCommenString);
		if (HSVList == null || HSVList.size() <= 0) {
			findSMainDataList = shareDao.findSMainDataList(limit);
			RedisUtil.setObject("resfindSMainDataList_"+redisCommenString, findSMainDataList,600);
			List<SMainData> mainDataList = findSMainDataList.getList();
			HSVList = new ArrayList<HotShareVO>();
			if (mainDataList != null && mainDataList.size() > 0) {
				for (SMainData sMainData : mainDataList) {
					//shareIdList.add(sMainData.getShareId());
					HotShareVO hsv = new HotShareVO();
					hsv.setIsForward(0);
					hsv.setPageTemplate(sMainData.getShareType()==null?(short)1:sMainData.getShareType());
					hsv.setPageId(sMainData.getShareId());
					hsv.setPageTitle(sMainData.getTitle());
					hsv.setPageDescription(sMainData.getDescrible());
					if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
						hsv.setShareOne(111L);
						hsv.setPageProducer(111L);
					}else{
						hsv.setShareOne(sMainData.getWeiId());
						hsv.setPageProducer(sMainData.getWeiId());
					}
					//首页分享  分享人
//					hsv.setShareOne(111);
					//制作人为微店网
//					hsv.setPageProducer(111);
					List<ProductVO> imgList = new ArrayList<ProductVO>();
					// 获取商品ID
					List<Long> productIdList = new ArrayList<Long>();
					List<Long> shelveIds = new ArrayList<Long>();
					// 商品列表
					PageResult<SProducts> findSProductsList = basicShareDAO.findSProductsList(sMainData.getShareId(), Limit.buildLimit(1, 3));
					List<SProducts> productsList = findSProductsList.getList();
					for (SProducts sProducts : productsList) {
					    productIdList.add(sProducts.getProductId());
					    shelveIds.add(sProducts.getShelveId());
					}
					List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
					// 获取图片信息
					List<PProducts> findPProductsList = shareDao.findPProductsList(productIdList);
					for (PProducts pro : findPProductsList) {
					    ProductVO temp = new ProductVO();
					    for (SProducts sProducts : productsList) {
						if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
						    // 获取上架ID
						    temp.setShelveId(sProducts.getShelveId());
						    temp.setSpid(sProducts.getSpid());
						    break;
						}
					    }
					    if (findClassProducts != null && findClassProducts.size() > 0) {
						for (PClassProducts pClassProducts : findClassProducts) {
						    if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
							// 获取上架人
							temp.setSellerWid(pClassProducts.getWeiId());
							break;
						    }
						}
					    }
					    temp.setCommission(pro.getDefaultConmision());
					    temp.setProductId(pro.getProductId());
					    temp.setProductName(pro.getProductTitle());
					    temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
					    temp.setRetailPrice(pro.getDefaultPrice());
					    temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
			    	    short shareType=sMainData.getShareType()==null?(short)1:sMainData.getShareType().shortValue();
			    	    //如果是单品时  添加一个新的对象  图片详情和描述  
			    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
			    	    	List<ProductsVO> list=new ArrayList<ProductsVO>();
			    	    	//根据产品Id获取 商品详情
			    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(temp.getSpid());
			    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
			    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
			    	    			ProductsVO vo=new ProductsVO();
			    	    			vo.setSid(sSingleDesc.getSid());
			    	    			vo.setShareDescription(sSingleDesc.getDescription());
			    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
			    	    			list.add(vo);
			    				}
			    	    		temp.setProductPictureList(list);
			    	    	}
			    	    }
					    imgList.add(temp);
					    
					}
					hsv.setProductList(imgList);
					
					if (sMainData.getUserType()!=null&&sMainData.getUserType().intValue()==ParseHelper.toInt(MainDataUserType.operate.toString())) {
						Map<String, String> getyunYingInfos = AppSettingUtil.getyunYingInfos();
						for (Entry<String, String> info : getyunYingInfos.entrySet()) {
							if (info.getKey().equals(sMainData.getCtsUser())) {
								hsv.setShareOneImgUrl(info.getValue());
								// 店铺名称
								hsv.setShareOneShopName(info.getKey());
							}
						}
						// 店铺图片路径
					}else{
						// 店铺图片路径
						hsv.setShareOneImgUrl(baseCommonService.getImageById(sMainData.getWeiId()));
						// 店铺名称
						hsv.setShareOneShopName(baseCommonService.getNickNameById(sMainData.getWeiId()));
					}
					// 如果它更新了
					hsv.setShareDate(TransformationDate(sMainData.getCreateTime()));
					if (sMainData.getUpdateTime() != null) {
						//转换时间 
					    hsv.setLastEditDate(DateUtils.transformationDate(sMainData.getUpdateTime()));
					} else {
					    hsv.setLastEditDate(DateUtils.transformationDate(sMainData.getCreateTime()));
					} 
					HSVList.add(hsv);
					  
				 }
			}
			RedisUtil.setObject("reshsvlist_"+redisCommenString, HSVList,600);
		}
		

		if (HSVList != null && HSVList.size() > 0) {
			for (HotShareVO hotShareVO : HSVList) {
				shareIdList.add(hotShareVO.getPageId());
			}
		    if (shareIdList != null && shareIdList.size() > 0) {
			List<SStatics> findSStaticsList = shareDao.findSStaticsList(shareIdList);//暂时去分享
			//List<DianZanCountVO> dianZanCount = shareDao.getDianZanCount(shareIdList);

			List<SZambiaActive> findSZambiaActiveList = null;
			if (weiId != 0) {
			    findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
			}

			for (HotShareVO hotShareVO : HSVList) {
				if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
					// 未点赞
					hotShareVO.setIsLike(0);
					for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
						if (hotShareVO.getPageId() == sZambiaActive.getShareId()&& sZambiaActive.getShareWeiId()!=null && sZambiaActive.getShareWeiId().longValue() == hotShareVO.getPageProducer()) {
							// 已点赞
							hotShareVO.setIsLike(1);
							break;
						}
					}
				}
				 if (findSStaticsList != null && findSStaticsList.size() > 0) {
					hotShareVO.setShareCount(0);
					// 获取分享数量
					for (SStatics sStatics : findSStaticsList) {
						if (hotShareVO.getPageId() == sStatics.getShareId().intValue()) {
//							hotShareVO.setShareCount(
//									(sStatics.getWebSv()==null?0:sStatics.getWebSv())+(sStatics.getWapSv()==null?0:sStatics.getWapSv())+(sStatics.getAppSv()==null?0:sStatics.getAppSv()));
							hotShareVO.setLikeCount(sStatics.getZanCount()==null?0:sStatics.getZanCount());
							break;
						}
					}
				} 
			/*	if (dianZanCount != null && dianZanCount.size() > 0) {
					hotShareVO.setLikeCount(0);
					// 从点赞表中获取点赞的数据
					for (DianZanCountVO dianZanCountVO : dianZanCount) {
						if (dianZanCountVO.getShareId().intValue() == hotShareVO.getPageId() ) {
							hotShareVO.setLikeCount(dianZanCountVO.getCount());
							break;
						}
					}
				}*/
			}

		    }

		} else {
		    rm.setStatusreson("数据为空！");
		    return rm;
		}

		hslv.setList(HSVList);
		hslv.setPageIndex(findSMainDataList.getPageIndex());
		hslv.setPageSize(findSMainDataList.getPageSize());
		hslv.setTotalPage(findSMainDataList.getTotalPage());
		hslv.setTotalCount(findSMainDataList.getTotalCount());
		rm.setBasemodle(hslv);
		return rm;
	    }

	@Override
	public ReturnModel findWapMyShareList(Long weiId, Limit limit) {
		ReturnModel rm = new ReturnModel();
		if (weiId == null || weiId.longValue() == 0) {
		    rm.setStatu(ReturnStatus.ParamError);
		    rm.setStatusreson("该微店号不存在！");
		    return rm;
		}
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		List<MyShareVO> MyShareVOList = new ArrayList<MyShareVO>();
		MyShareListVO msv = new MyShareListVO();
		// 分享ID集合
		List<Long> shareIdList = new ArrayList<Long>();
		// 1、分析 因为是查自己的分享记录 所以以 S_ShareActive 为主进行分页操作
		PageResult<SShareActive> findSShareActiveList = shareDao.findSShareActiveList(weiId, limit);
		List<SShareActive> list = findSShareActiveList.getList();
		if (list != null && list.size() > 0) {
		    for (SShareActive sShareActive : list) {
			// 2、从上一步骤中获取分享记录的ID
			shareIdList.add(sShareActive.getShareId());
		    }
		} else {
		    rm.setStatusreson("没有数据！");
		    return rm;
		} 
		// 3、然后通过分享记录的ID 从S_MainData 中获取 分享的标题等信息
		List<SMainData> findSMainDataList = shareDao.findSMainDataList(shareIdList);
		// 4、然后通过分享记录的ID 从 S_Products 中获取 产品Id
		for (SShareActive sShareActive : list) {
		    MyShareVO mv = new MyShareVO();
		    if (sShareActive.getMakeWeiId()!=null&&sShareActive.getWeiId().longValue() == sShareActive.getMakeWeiId()) {
			// 原创分享 为0
			mv.setIsForward(0);
		    } else {
			// 转的分享 为1
			mv.setIsForward(1);
		    }
		    mv.setPageId(sShareActive.getShareId());
		    mv.setPageProducer(sShareActive.getMakeWeiId());
		    if (findSMainDataList != null && findSMainDataList.size() > 0) {
			    for (SMainData sMainData : findSMainDataList) {
					if (sShareActive.getShareId().intValue() == sMainData.getShareId().intValue()) {
					    // 标题
					    mv.setPageTitle(sMainData.getTitle());
					    mv.setPageTemplate(sMainData.getShareType()==null?(short)1:sMainData.getShareType());
					    // 推广语
					    mv.setPageDescription(sMainData.getDescrible());
					    if (sMainData.getUpdateTime() != null) {
						mv.setLastEditDate(DateUtils.format(sMainData.getUpdateTime(), ""));
					    } else {
						mv.setLastEditDate(DateUtils.format(sMainData.getCreateTime(), ""));
					    }
					    break;
					}
			    }
		    }
		    List<ProductVO> imgList = new ArrayList<ProductVO>();
		    // 获取商品ID
		    List<Long> productIdList = new ArrayList<Long>();
		    List<Long> shelveIds = new ArrayList<Long>();
		    
			String hql=" from SProducts s where s.shareId =? order by s.spid asc";
		    List<SProducts> productsList = shareDao.findPage(hql, 1, 3, sShareActive.getShareId());
		    for (SProducts sProducts : productsList) {
				productIdList.add(sProducts.getProductId());
				shelveIds.add(sProducts.getShelveId());
		    }
		    List<PClassProducts> findClassProducts = baseProductDao.findClassProducts(shelveIds);
		    // 获取图片信息
		    List<PProducts> findPProductsList = shareDao.findPProductsList(productIdList);
		    if (findPProductsList != null && findPProductsList.size() > 0) {	
			    for (SProducts sProducts : productsList) {
				    for (PProducts pro : findPProductsList) {
						ProductVO temp = new ProductVO();
						if (sProducts.getProductId().longValue()==pro.getProductId().longValue()) {
						    if (pro.getProductId().longValue() == sProducts.getProductId().longValue()) {
							// 上架Id
							temp.setShelveId(sProducts.getShelveId());
						    }
							if (findClassProducts != null && findClassProducts.size() > 0) {
							    for (PClassProducts pClassProducts : findClassProducts) {
								if (pro.getProductId().longValue() == pClassProducts.getProductId().longValue()) {
								    // 获取上架人
								    temp.setSellerWid(pClassProducts.getWeiId());
								    break;
								}
							    }
							}
							temp.setCommission(pro.getDefaultConmision());
							temp.setProductId(pro.getProductId());
							temp.setProductName(pro.getProductTitle());
							temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
							temp.setRetailPrice(pro.getDefaultPrice());
							temp.setDisplayPrice(commonMethod.getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
				    	    short shareType=mv.getPageTemplate()==null?(short)1:mv.getPageTemplate().shortValue();
				    	    //如果是单品时  添加一个新的对象  图片详情和描述  
				    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
				    	    	List<ProductsVO> list1=new ArrayList<ProductsVO>();
				    	    	//根据产品Id获取 商品详情
				    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(temp.getSpid());
				    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
				    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
				    	    			ProductsVO vo=new ProductsVO();
				    	    			vo.setSid(sSingleDesc.getSid());
				    	    			vo.setShareDescription(sSingleDesc.getDescription());
				    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
				    	    			list1.add(vo);
				    				}
				    	    		temp.setProductPictureList(list1);
				    	    	}
				    	    }
							imgList.add(temp);
						}
				    }
			    }
		    }
		    mv.setProductList(imgList);
		    MyShareVOList.add(mv);
		}
		List<SZambiaActive> findSZambiaActiveList = shareDao.findSZambiaActiveList(shareIdList, weiId);
		if (MyShareVOList != null && MyShareVOList.size() > 0) {
		for (MyShareVO hotShareVO : MyShareVOList) {
		    if (findSZambiaActiveList != null && findSZambiaActiveList.size() > 0) {
			hotShareVO.setIsLike(0);
			for (SZambiaActive sZambiaActive : findSZambiaActiveList) {
			    // 未点赞
			    if (hotShareVO.getPageId() == sZambiaActive.getShareId() && sZambiaActive.getShareWeiId().longValue() == weiId.longValue()) {
				// 已点赞
				hotShareVO.setIsLike(1);
				break;
			    }
			}
		    }
		}
		}
		msv.setList(MyShareVOList);
		msv.setPageIndex(findSShareActiveList.getPageIndex());
		msv.setPageSize(findSShareActiveList.getPageSize());
		msv.setTotalPage(findSShareActiveList.getTotalPage());
		msv.setTotalCount(findSShareActiveList.getTotalCount());
		rm.setBasemodle(msv);
		return rm;
	    }
	
	public ReturnModel addOrEdit(Long pageId) {
		// 根据shareId查找shareType
		ReturnModel result=new ReturnModel();
		List<SMainData> smd = new ArrayList<SMainData>();
		smd = shareDao.findSMainData(pageId);
		for (SMainData st : smd) {
			Short shareType = st.getShareType();
			if (shareType != 1) {
				result.setStatu(ReturnStatus.DataError);
				result.setStatusreson("您选择的不是简易型分享，若想设置其他类型请到电脑端设置！");
				return result;
			}
		}
		result.setStatu(ReturnStatus.Success);
		return result;
	}

	
}
