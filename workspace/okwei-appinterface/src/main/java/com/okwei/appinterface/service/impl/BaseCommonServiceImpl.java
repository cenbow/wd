package com.okwei.appinterface.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.BaseImageMsg;
import com.okwei.appinterface.bean.vo.ShareModel;
import com.okwei.appinterface.dao.IBaseCommonDao;
import com.okwei.appinterface.service.BaseCommonService;
import com.okwei.appinterface.util.RedisUtil;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PPurchases;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.ShareOnHomePage;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.share.IBasicShareServices;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;

@Service
public class BaseCommonServiceImpl extends BaseDAO  implements BaseCommonService {
	
	@Autowired
	public IBaseCommonDao baseDao;
	
	@Autowired
	public IBaseCommonService baseCommonService;
	 
	@Autowired
	public IBasicShareServices basicShareServices;
	/**
	 * wap 使用
	 * 
	 * @return
	 */	
	/*
	 * 统一获取昵称，规则如下： 身份为云商通供应商，显示 "云商通"进驻公司名身份为云商通+批发号，显示"云商通"进驻公司名
	 * 身份为批发号，显示"批发号"进驻店铺名非供应商，微店昵称我的里的昵称，显示"微店昵称"，不与身份挂钩
	 */
	@Override
	public String getNickNameById(Long weiNo) {
		if (weiNo == null) {
			return "";
		} 
		UShopInfo shopInfo=super.get(UShopInfo.class, weiNo);
		if(shopInfo!=null&&shopInfo.getShopName()!=null)
			return shopInfo.getShopName();
		UYunSupplier ysup = super.get(UYunSupplier.class, weiNo);
		if (ysup != null && ysup.getStatus()!=null && ysup.getStatus()==4) {
			USupplyer sup = super.get(USupplyer.class, weiNo);
			if (sup != null && !"".equals(sup.getCompanyName()))
				return sup.getCompanyName();
		}
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getStatus()!=null ) { //&& bsup.getStatus()==4
			return bsup.getShopName();
		}
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		return seller==null?"":seller.getWeiName();
	} 
	
	/*
	 * 云商通，显示"云商通"进驻时头像 云商通+批发号，显示"云商通"头像 批发号，显示"档口实拍" 非供应商，我的头像
	 * 我的昵称，显示"昵称"，不与身份挂钩
	 */
	@Override
	public String getImageById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo=super.get(UShopInfo.class, weiNo);
		if(shopInfo!=null&&shopInfo.getShopImg()!=null&&!"".equals(shopInfo.getShopImg()))
			return ImgDomain.GetFullImgUrl(shopInfo.getShopImg());
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getShopPic()!=null && !"".equals(bsup.getShopPic()))
		{
			return ImgDomain.GetFullImgUrl(bsup.getShopPic());
		}
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		return seller==null?"":ImgDomain.GetFullImgUrl(seller.getImages());
	}
	
	
	public String getBusContentById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo=super.get(UShopInfo.class, weiNo);
		if(shopInfo!=null&&shopInfo.getShopBusContent()!=null&&!"".equals(shopInfo.getShopBusContent()))
			return shopInfo.getShopBusContent();
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getBusContent()!=null && !"".equals(bsup.getBusContent()))
		{
			return bsup.getBusContent();
		}
		return "";
	}

	
	/*
	 * 是否能发布能销售的产品
	 */
	@Override
	public boolean isSaleProduct(Long weiNo) {
		if(weiNo == null)
			return false;
		USupplyer us = super.get(USupplyer.class, weiNo); //userDao.get(suhql, gys);
		if (us != null && us.getType()!=null&& us.getType() >0) {
			return true;
		}
		return false;
//		UYunSupplier ysup = super.get(UYunSupplier.class, weiNo);
//		if (ysup != null) {
//			if(ysup.getStatus()!=null && ysup.getStatus()==4)
//				return true;
//		}
//		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
//		if (bsup != null) {
//			if(bsup.getStatus()!=null && bsup.getStatus()==4)
//				return true;
//		}
//		
//		return false;
	}
 
	@Override
	public boolean isNoPayShop(Long weiNo) {
		if(weiNo == null)
			return false;
		boolean b=false;
		UYunSupplier ysup = super.get(UYunSupplier.class, weiNo);
		if (ysup != null) {
			if(ysup.getStatus()!=null && ysup.getStatus()==3)
			{
				b= true;
			}
		}
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null) {
			if(bsup.getStatus()!=null && bsup.getStatus()==3)
			{
				b= true;
			}
		}
		
		return b;
	}

	
	
	/**
	 * 图片路径返回
	 * @param imgurl
	 * @return
	 */
	public BaseImageMsg getImageMsg(String imgurl) {
		BaseImageMsg imageMsg = new BaseImageMsg();
		imageMsg.setUrl(ImgDomain.GetFullImgUrl(imgurl));
		return imageMsg;
	}
	

	public ReturnModel getShareUrl(int type,LoginUser user, String param){
		ReturnModel rqModel=new ReturnModel();
		rqModel.setStatu(ReturnStatus.ParamError); 
		ShareModel result=new ShareModel();
		long loginWeiid=user.getWeiID();
		switch (type) {
		case 0://分享微店
			long weiid=ParseHelper.toLong(param);
			if(weiid<=0)
				weiid=loginWeiid;
			return getShopShareUrl(loginWeiid, weiid);
		case 1:// 分享产品
			JSONObject jo = new JSONObject().fromObject(param);
			long proid = ParseHelper.toLong(String.valueOf(jo.get("pid")));// jo.getLong("pid");
			String shopno =String.valueOf(jo.get("shopNo"));//jo.getString("shopNo");
			return getProductShareUrl(proid, loginWeiid, shopno);
		case 8://市场分享
			int marketId = ParseHelper.toInt(param);
			return getShareUrl(marketId, loginWeiid, type); 
		case 10://分享发布需求
			long cid=ParseHelper.toLong(param);
			if(cid>0){
				PPurchases model=super.get(PPurchases.class, cid);
				if(model!=null){
					result.setContent(model.getContent());
					result.setImageUrl(baseCommonService.getShopImageByWeiId(loginWeiid)); 
					result.setTitle("采购需求"); 
					result.setShareUrl("http://"+AppSettingUtil.getSingleValue("wapDomain")+"/shejiao/sharepur?orderid="+cid);
					rqModel.setStatu(ReturnStatus.Success);
					rqModel.setBasemodle(result);
					return rqModel;
				}
			}
			break;
		case 11://工厂号进驻
			long shareWid=ParseHelper.toLong(param);
			if(shareWid>0){
				result.setContent("微店工厂号是专门针对拥有实体工厂和产品的商户，为他们提供云销售电子商务系统");
				result.setImageUrl(baseCommonService.getShopImageByWeiId(shareWid)); 
				result.setTitle("供应商篇之工厂号商家攻略"); 
				result.setShareUrl("http://"+AppSettingUtil.getSingleValue("wapDomain")+"/gchin/gchmj?w="+shareWid);
				rqModel.setStatu(ReturnStatus.Success); 
				rqModel.setBasemodle(result);
				return rqModel;
			}
			break;
		case 13://招商需求分享
			int SupplyDemandId=ParseHelper.toInt(param);
			rqModel =getShareUrlSupplyDemand(loginWeiid,SupplyDemandId);
			break;
		case 14:
			// 分享 平台号 子供应商商城。
			UChildrenUser uChildrenUser = super.get(UChildrenUser.class,user.getAccount());
			if (uChildrenUser!=null) {
				weiid=uChildrenUser.getParentId();
				if(weiid<=0)
					weiid=loginWeiid;
				return getShopShareUrl(loginWeiid, weiid);
				
			}else{
				rqModel.setStatusreson("找不到子账号！");
				rqModel.setStatu(ReturnStatus.ParamError);
				return rqModel;
			}
		case 15://分享系列 商品。
			int  demandId=ParseHelper.toInt(param);
			if(demandId>0){
				// 获取招商条件
				USupplyDemand demand = super.get(USupplyDemand.class, demandId);
				// 获取招商系列产品
				List<UDemandProduct> demList = super.find("from UDemandProduct where demandId=?", demandId);
				if (demList != null && demList.size() > 0) {
				  Long productId = demList.get(0).getProductId();
					PProducts pro = super.get(PProducts.class, productId);
					if (pro!=null) { 
						result.setContent("该系列产品采购满"+demand.getOrderAmout()+"元即可成为落地店，享落地店权益哦~~");
						result.setImageUrl(ImgDomain.GetFullImgUrl_product(pro.getDefaultImg(), 24)); 
						result.setTitle(demand.getTitle()); 
						result.setShareUrl("http://"+AppSettingUtil.getSingleValue("wapDomain")+"/v5/pph/jhlist?type=3&parid="+demandId+"&source=2");
						rqModel.setStatu(ReturnStatus.Success); 
						rqModel.setBasemodle(result);
						return rqModel;
					}
					} 
			}
		
		case 16:{//分享分享页
			int shareCount=500;//总共分享次数
			long shareId=0;   //分享页Id
			String from=null; //首页来的 为from= 1 
			String disalbeForward="0"; //首页来的 为from= 1 
			int between=0; //距离第二天的还有多少秒
			try {
				jo = new JSONObject().fromObject(param);
				shareId = ParseHelper.toLong(String.valueOf(jo.get("sharePageId"))); 
				from = String.valueOf(jo.get("from")); 
				disalbeForward = String.valueOf(jo.get("disalbeForward"));
			} catch (Exception e1) {
				shareId=ParseHelper.toLong(param);
			}   
			if (shareId > 0 ) {
				SMainData shareMain = super.get(SMainData.class, shareId);
				if(shareMain!=null){ 
					result.setContent(shareMain.getDescrible());
					SProducts sProduct = (SProducts) super.findPage("from SProducts where shareId=? order by spid asc", 1, 2, shareId).get(0);
					String defualtImg="";
					if (sProduct!=null) {
						PProducts products=basicShareServices.getById(PProducts.class, sProduct.getProductId());
						defualtImg=ImgDomain.GetFullImgUrl(products.getDefaultImg());
					}else{
						defualtImg=ImgDomain.GetFullImgUrl_base("/images/okwei_201602.png");
					} 
					result.setImageUrl(defualtImg);
					result.setTitle(shareMain.getTitle());
//					if (from != null && !"null".equals(from) && !"0".equals(from)) {
						//每天可以发布5次(中每条可以无限发) 分享
						Integer i=0; 
						List<Long> arr =(List<Long>) RedisUtil.getObject("arr+weiID1="+loginWeiid);
						if (arr==null||arr.size()==0) {
							arr=new ArrayList<Long>();
						}  
						i = RedisUtil.getObject("weiID="+loginWeiid)==null ?  0 : Integer.parseInt(RedisUtil.getObject("weiID="+loginWeiid).toString());
							//start
		
								try {
									SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss");
									 Date date = new Date();
									 Date begin=dfs.parse(dfs.format(date.getTime()));
									 Date end = dfs.parse("24:59:59");
									 //计算里第二天还有多少秒 将其设为redis的有效时间
									 between=(int) ((end.getTime()-begin.getTime())/1000);//除以1000是为了转换成秒
									if (arr!=null&&arr.size()>0&&arr.contains(shareId)&&shareMain.getOnHomePage()!=null
											&&shareMain.getOnHomePage().shortValue()==ParseHelper.toShort(ShareOnHomePage.Yes.toString())) {
										//分享过的 
									}else{
										if (i==null||i.intValue()<shareCount) { 
											arr.add(shareId);
//											RedisUtil.setObject("arr+weiID2="+loginWeiid,arr, between);
											RedisUtil.setObject("arr+weiID1="+loginWeiid,arr, 100);
											//没有分享过的
											RedisUtil.setObject("weiID="+loginWeiid, i==null?1:i+1, between);
										}else{
											rqModel.setStatu(ReturnStatus.ParamError); 
											rqModel.setStatusreson("每天最多可分享5条!");
											return rqModel;
										}
									}			
									//无效     disalbeForward为1表示禁用自动转发分享页功能，默认值为0    
									if (disalbeForward!=null&&"0".equals(disalbeForward)) {
										basicShareServices.addShareCount(loginWeiid, shareId, 3);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								} 
//							}
							//end
						
//						result.setShareUrl("http://"+loginWeiid+"."+AppSettingUtil.getSingleValue("wapDomain")+"/v5/share/sharedetails?shareId="+shareId+"&w="+loginWeiid+"&homePage="+from);
//					}else{
						//homePage  的参数无意义
						result.setShareUrl("http://"+loginWeiid+"."+AppSettingUtil.getSingleValue("wapDomain")+"/v5/share/sharedetails?shareId="+shareId+"&w="+loginWeiid+"&homePage=0");
//					}
					rqModel.setStatu(ReturnStatus.Success); 
					rqModel.setBasemodle(result);
					return rqModel;
				}else{
					rqModel.setStatusreson("不存在该分享信息!");
				}
			}else{
				rqModel.setStatusreson("不存在该分享信息!");
			}
			break;}
		case 17:
			result.setImageUrl(ImgDomain.GetFullImgUrl_base("/images/okwei_201602.png"));
			result.setTitle("微店网大战阿里"); 
			result.setContent("与阿里大战，让我们死里逃生，常回头看看，才能在品味得失和甘苦中升华。向前看是梦想和目标；向后看是检验和修正。虽然我们失去宝贵的成长期，但我们经过一年多的休整，我们将整装待发。");
			if (loginWeiid<1) {
				loginWeiid=1;
			}
			result.setShareUrl("http://"+loginWeiid+"."+AppSettingUtil.getSingleValue("wapDomain")+"/wap/apphtml/battle.html?w="+loginWeiid);
			rqModel.setStatu(ReturnStatus.Success); 
			rqModel.setStatusreson("成功！");
			rqModel.setBasemodle(result);
			return rqModel;
		case 18:
			result.setImageUrl("http://base.okimgs.com/images/weidianlogo.jpg");
			result.setTitle("全国各地招募城主"); 
			result.setContent("我组团一起干");
			jo = new JSONObject().fromObject(param);
			String brandid = String.valueOf(jo.get("brandId")); 
			String superid = String.valueOf(jo.get("superid")); 
			result.setShareUrl("http://1.m.okwei.com/v5/agentshare?brandId="+brandid+"&superid="+superid);
			rqModel.setStatu(ReturnStatus.Success); 
			rqModel.setStatusreson("成功！");
			rqModel.setBasemodle(result);
			return rqModel;
		default:
			long weiNo = ParseHelper.toLong(param);
			if(weiNo<=0)
				weiNo=loginWeiid;
			rqModel = getShareUrl(weiNo, type);
			break;
		}
		return rqModel;
	}
	 
	
	
	
	/**
	 * 分享招商需求
	 * @return
	 */
	private ReturnModel getShareUrlSupplyDemand(long weiid, int supplyDemandId) {
		ReturnModel rqModel = new ReturnModel();  
		String hql=" from USupplyDemand where demandId=?"; 
		USupplyDemand uq = super.getUniqueResultByHql(hql,supplyDemandId);
		if (uq!=null) {
			ShareModel result=new ShareModel();
			result.setContent("品牌产品拥有城市独家代理权限，就等你来~");
			result.setImageUrl(ImgDomain.GetFullImgUrl(uq.getMainImage(), 24));  
			result.setTitle(uq.getTitle()); 
			result.setShareUrl("http://"+AppSettingUtil.getSingleValue("wapDomain")+"/v5/pph/zsxq?parid="+supplyDemandId+"&w="+weiid+"&weiid="+uq.getWeiId());
			rqModel.setStatu(ReturnStatus.Success); 
			rqModel.setBasemodle(result); 
		}else{
			rqModel.setStatusreson("没有改招商需求");
			rqModel.setStatu(ReturnStatus.ParamError);
		}
		return rqModel;
	}

	public ReturnModel getShareUrl(Long weiNo, int type) {
		ReturnModel rm = new ReturnModel();
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		if (seller == null) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("数据有误，请稍后再试！");
			rm.setBasemodle(null);
			return rm;
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		switch (type) {
		case 2:
			mp.put("title", "批发号服务点入驻");
			mp.put("imageUrl", "http://base1.okimgs.com/images/pifahao-fenxiang.png");
			mp.put("content", "分享批发号服务点申请");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("renzhenpointshareurl") + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		case 3:
			mp.put("title", "供应商篇之批发号商家攻略");
			mp.put("imageUrl", "http://base.okimgs.com/images/banner_11_15.png");
			mp.put("content", "微店批发号专门针对实体批发市场商户，提供云销售电子商务系统。帮助他们建设分销渠道、线上批发交易。");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("pifahaoshareurl") + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		case 4:
			mp.put("title", "微店网介绍 五句话读懂微店");
			mp.put("imageUrl", "http://base.okimgs.com/images/banner_11_09.png");
			mp.put("content", "微店网云销售模式：供应商发布产品到云端，微店主0成本开店。");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("wujuhuashareurl") + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		case 5:
			mp.put("title", "微店网APP下载");
			mp.put("imageUrl", "http://base.okimgs.com/images/banner_11_16.png");
			mp.put("content", "安装微店网APP，供应商轻松管理微店，微店主快乐做分销。");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("appshareurl"));// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		case 6:
			mp.put("title", "微店主攻略，0成本走上微店创业之路");
			mp.put("imageUrl", "http://base.okimgs.com/images/banner_11_11.png");
			mp.put("content", "5秒注册开微店，云端产品库海量优质快速上架，轻松做分销赚佣金。");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("zhugonglueshareurl") + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		case 7:
			mp.put("title", "微店网品牌影响力");
			mp.put("imageUrl", "http://base2.okimgs.com/images/xh-addtop004.png");
			mp.put("content", "牛B网站背后的牛B事儿");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("pingpaishareurl") + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
			break;
		default:
			break;
		}
		return rm;
	}
	
	public ReturnModel getShareUrl(int marketid, Long weiNo, int type) {
		ReturnModel rm = new ReturnModel();
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		if (seller == null) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("数据有误，请稍后再试！");
			rm.setBasemodle(null);
			return rm;
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		TBatchMarket bk = super.get(TBatchMarket.class, marketid);
		if(bk!=null){
			mp.put("title", bk.getName() + "搬到手机上了");
			mp.put("imageUrl", ImgDomain.GetFullImgUrl(baseCommonService.getShopImageByWeiId(weiNo), 16) );
//			mp.put("imageUrl", ImgDomain.GetFullImgUrl(bk.getImage(),24));
			mp.put("content", bk.getName() + "已经开通微店批发号，市场商户现在开通，享受手机做批发");
			mp.put("shareUrl", AppSettingUtil.getSingleValue("marketshareurl") + marketid + "&w=" + seller.getWeiId());// ?
			rm.setBasemodle(mp);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("");
		}else {
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson("市场不存在");
		}
		return rm;
	}
	public ReturnModel getShopShareUrl(Long weiNo, Long shareNo) {
		ReturnModel rm = new ReturnModel();
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		if (seller == null) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("数据有误，请稍后再试！");
			rm.setBasemodle(null);
			return rm;
		}
		Map<String, Object> mp = new HashMap<String, Object>();

		String username = getNickNameById(shareNo);
		mp.put("title",  username);
		String img = "http://base1.okimgs.com/images/defaultPhotp.png";
		String userimg = ImgDomain.GetFullImgUrl(getImageById(shareNo),24);
		if (!"".equals(userimg)) {
			img = userimg;
		}
		mp.put("imageUrl", img);

		mp.put("content", username + " 微店号：" + shareNo + " - 新品来袭！正品低价、厂家直供、批发代理价更优！");
		mp.put("shareUrl", "http://" + shareNo + "." + AppSettingUtil.getSingleValue("shopshareurl") + weiNo );// ?
		rm.setBasemodle(mp);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("");
		return rm;
	}
	public ReturnModel getProductShareUrl(Long productid, Long weiNo, String shopNo) {
		ReturnModel rm = new ReturnModel();
		PProducts p = super.get(PProducts.class, productid);
		if (p == null) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("输入的产品号有误，请重新输入！");
			rm.setBasemodle(null);
			return rm;
		}
		Long shopWeiNo = 0L;
		if (shopNo != null && !"".equals(shopNo)) {
			shopWeiNo = ParseHelper.toLong(shopNo) ;
		} else {
			shopWeiNo = weiNo;
		}
		UWeiSeller seller = super.get(UWeiSeller.class, shopWeiNo);
		String hqlString = " from PClassProducts p where p.productId=? and p.weiId=?";
		PClassProducts pp = super.getUniqueResultByHql(hqlString, productid,shopWeiNo);
		if (seller == null) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("数据有误，请稍后再试！");
			rm.setBasemodle(null);
			return rm;
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("title", getNickNameById(shopWeiNo));
		String img = "http://base1.okimgs.com/images/defaultPhotp.png";
		if (null!=p.getDefaultImg()&&!"".equals(p.getDefaultImg())) {
			img = ImgDomain.GetFullImgUrl_product(p.getDefaultImg(), 24); //ImgDomain.GetFullImgUrl(p.getDefaultImg(),24);
		}
		mp.put("imageUrl", img);
		String strContent = p.getProductTitle();
		if (strContent.length() > 30) {
			strContent = strContent.substring(0, 26) + "...";
		}

		strContent += "￥:" + p.getDefaultPrice() + " 批发/代理更多优惠";
		mp.put("content", strContent);
		mp.put("shopNo", shopWeiNo);
		if(pp==null)
		{
			mp.put("shareUrl", "http://"+ AppSettingUtil.getSingleValue("productshareurl")+"/v5/prodetails?productid="+productid+"&source=0&w="+weiNo);
		}
		else {
			mp.put("shareUrl", "http://" + AppSettingUtil.getSingleValue("productshareurl") + pp.getId() + ".html?w=" + weiNo);// ?
		}
		rm.setBasemodle(mp);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("");
		return rm;
	}

}
