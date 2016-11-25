package com.okwei.service.impl.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.activity.IBasePerfectProductService;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActShowProducts;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.AHomeMain;
import com.okwei.bean.domain.AHomeProducts;
import com.okwei.bean.domain.ARedPacMoneyAgLog;
import com.okwei.bean.domain.ARedPacMoneyDetail;
import com.okwei.bean.domain.ARedPacMoneyTypes;
import com.okwei.bean.domain.ARedPackageMoney;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UTicketUseLog;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiDianCoinLog;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.UTicketUseTypeEnum;
import com.okwei.bean.enums.WeiDianCoinTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.activity.APerfectProductsAppVO;
import com.okwei.bean.vo.activity.ARedPacMoneyAgLogVO;
import com.okwei.bean.vo.activity.ARedPacMoneyDetailVO;
import com.okwei.bean.vo.activity.ActProductInfo;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.activity.IActivityDao;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;



@Service
public class BasePerfectProductService extends BaseService implements IBasePerfectProductService {

	@Autowired
	public IBaseProductDao baseProductDao;
	
	@Autowired
	private IProductSearchDao productDao;
	
	@Autowired
	private IActivityDao actDao;

	@Override
	public ReturnModel  findPerfectProductlist(int pageIndex,int pageSize){
		ReturnModel rm=new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		String keyname="perfectProductlist_index_"+pageIndex+"_size_"+pageSize;
		PageResult<APerfectProductsAppVO> listResult=(PageResult<APerfectProductsAppVO>)RedisUtil.getObject(keyname);
		if(listResult==null||listResult.getList()==null||listResult.getList().size()<=0){
			String hql=" Select a.perPid as perPid,a.title as title,a.productId as productId,a.url as url,a.sort as sort,a.productImg as productImg   from APerfectProducts a where a.state=:state order by a.sort";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("state", (short)1);
			listResult=baseProductDao.findPageResultTransByMap(hql, APerfectProductsAppVO.class, Limit.buildLimit(pageIndex, pageSize), params);
			if(listResult.getList()!=null&&listResult.getList().size()>0){
				RedisUtil.setObject(keyname, listResult, 600); 
			}
		}
		if(listResult!=null){
			List<APerfectProductsAppVO> list = listResult.getList() ;
			if (list != null && list.size() > 0) {
				for (APerfectProductsAppVO perfectProduct : list) {
					if(perfectProduct==null) continue;
					PProducts product=baseProductDao.get(PProducts.class, perfectProduct.getProductId());
					if(product==null){
						rm.setStatu(ReturnStatus.DataError);
						rm.setStatusreson("产品ID不存在!");
						return rm;
					}
					perfectProduct.setProductPicture(ImgDomain.GetFullImgUrl(perfectProduct.getProductImg()));
					perfectProduct.setProductImg(ImgDomain.GetFullImgUrl(perfectProduct.getProductImg()));
					if (null != product.getOriginalPrice()) {
						perfectProduct.setDisplayPrice(product.getOriginalPrice());
    		    	} else {
    		    		double percent = 1.5;
    		    		double displayPrice = product.getDefaultPrice() * percent;
    		    		DecimalFormat df = new DecimalFormat("#.00");
    		    		displayPrice = Double.parseDouble(df.format(displayPrice));
    		    		perfectProduct.setDisplayPrice(product.getOriginalPrice() == null ? displayPrice : product.getOriginalPrice());
    		    	}
					perfectProduct.setRetailPrice(product.getDefaultPrice());
					perfectProduct.setProductId(product.getProductId());
					perfectProduct.setProductName(product.getProductTitle());
					perfectProduct.setCommission(product.getDefaultConmision());
					perfectProduct.setSellerWid(product.getSupplierWeiId());
					perfectProduct.setProviderWid(product.getSupplierWeiId());
				}
				listResult.setList(list);
			}
			
		}
		rm.setBasemodle(listResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("获取精品单选列表成功！");
		return rm;
	
	}
	
	@Override
	public PageResult<ProductInfo> getAllAHomeMainProducts(Limit limit) {
		String keyName = "AllHomeMainPage_" + limit.getPageId() + "_" + limit.getSize();
		PageResult<ProductInfo> result = (PageResult<ProductInfo>) RedisUtil.getObject(keyName);
		if (result==null||result.getList()==null||result.getList().size()<=0) {
			List<Long> proids = new ArrayList<Long>();
			String hql="from AHomeMain where position=1 and state=1 and type=1 order by homeId";
			List<AHomeMain> AHomeMainlist=baseProductDao.find(hql);
			if(AHomeMainlist!=null&&AHomeMainlist.size()>0){
				for (AHomeMain homemain : AHomeMainlist) {
					List<AHomeProducts> list=baseProductDao.findPage("from AHomeProducts where homeId=? ORDER BY sort", 0, 20, homemain.getHomeId());
					for (AHomeProducts pro : list) {
					    proids.add(pro.getProductId());
					}
				}
			}
			if(proids!=null&&proids.size()>0){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("proids", proids);
				params.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
				hql = "from PProducts where productId in(:proids) and state=:state";	 
				PageResult<PProducts> page= baseProductDao.findPageResultByMap(hql, limit, params);
				if (page != null) {
					List<PProducts> list = page.getList();
					List<ProductInfo>  proList=new ArrayList<ProductInfo>();
					for (PProducts pro : list) {
						ProductInfo temp = new ProductInfo();
						temp.setProductId(pro.getProductId());
						temp.setProductName(pro.getProductTitle());
						temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
						temp.setRetailPrice(pro.getDefaultPrice());
						temp.setSellerWid(pro.getSupplierWeiId());
						double percent = 1.5;
						double displayPrice = pro.getDefaultPrice() * percent;
						DecimalFormat df = new DecimalFormat("#.00");
						displayPrice = Double.parseDouble(df.format(displayPrice));
						temp.setDisplayPrice(pro.getOriginalPrice() == null ? displayPrice : pro.getOriginalPrice());
						//判断是否是828活动产品
						ActProductInfo actnew=productDao.get_ProductAct(pro.getProductId());
						if(actnew!=null){
							AActivityProducts actProducts=baseProductDao.get(AActivityProducts.class, actnew.getProActId());
							//AActShowProducts actProducts=baseProductDao.get(AActShowProducts.class, actnew.getProActId());
							temp.setActivity((short)actnew.getActiveType());
							if(actProducts!=null){
								temp.setRetailPrice(actProducts.getPrice());
								temp.setSellerWid(actProducts.getSellerId());
								//temp.setStockCount(actProducts.getStockCount());//添加活动库存
							}
						}
						proList.add(temp);
					}
					result = new PageResult<ProductInfo>(page.getTotalCount(), limit, proList);
					RedisUtil.setObject(keyName, result, 1800);	
				}
			}	  
		}
		
		//这里不能使用缓存，剩余库存每次都要取最新值
		if(result!=null&&result.getList()!=null&&result.getList().size()>0){
			List<ProductInfo>  proListlast=result.getList();
			if(proListlast!=null&&proListlast.size()>0){
				for (ProductInfo pp : proListlast) {
					ActProductInfo actnew=productDao.get_ProductAct(pp.getProductId());
					if(actnew!=null){
						AActShowProducts actProducts=baseProductDao.get(AActShowProducts.class, actnew.getProActId());
						if(actProducts!=null){
							pp.setActivity((short)actnew.getActiveType());
							pp.setStockCount(actProducts.getStockCount()==null?0:actProducts.getStockCount());//添加活动库存
						}
					}
				}
				result = new PageResult<ProductInfo>(result.getTotalCount(), limit, proListlast);
			}	
		}
		return result;
	}
	
	@Override
	public PageResult<ProductInfo> getActSecondProducts(Integer classId,Integer pclassId,Limit limit) {
		String keyName = "ActSecondPage_"+classId+"_"+pclassId+"_"+ limit.getPageId() + "_" + limit.getSize();
		PageResult<ProductInfo> result = (PageResult<ProductInfo>) RedisUtil.getObject(keyName);
		if (result==null||result.getList()==null||result.getList().size()<=0) {
			String hql="from AHomeMain where  classId=?  and state=1 and type=1 order by homeId";
			List<AHomeMain> AHomeMainlist=baseProductDao.find(hql,classId);
			List<Integer> homeMainId = new ArrayList<Integer>();
			if(AHomeMainlist!=null&&AHomeMainlist.size()>0){
				for (AHomeMain homemain : AHomeMainlist) {
					homeMainId.add(homemain.getHomeId());
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("homeId", homeMainId);
				params.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
				StringBuffer sb=new StringBuffer();
//				sb.append("from PProducts as a where  a.productId in (select productId from AHomeProducts where homeId in (:homeId))  and a.state=:state");
				sb.append("from AHomeProducts as a where a.homeId in (:homeId) and a.productId in (select productId from PProducts where state=:state ");
				if(pclassId!=null&&pclassId!=-1){
					List<Integer> list=new ArrayList<Integer>();
					list.add(pclassId);
					List<PProductClass> a=findClassId(list);
					List<Integer> ls=new ArrayList<Integer>();

					for(PProductClass b:a){
						ls.add(b.getClassId());
					}
					sb.append(" and classId in (:classId) ");
					params.put("classId", ls);
				}
				sb.append(" ) order by a.homeId,a.sort");
				PageResult<AHomeProducts> page=baseProductDao.findPageResultByMap(sb.toString(), limit, params);
				if(page!=null){
					List<AHomeProducts> list = page.getList();
					List<ProductInfo>  proList=new ArrayList<ProductInfo>();
					for (AHomeProducts hp : list) {
						ProductInfo temp = new ProductInfo();
						PProducts pro=baseProductDao.get(PProducts.class, hp.getProductId());
						if(pro==null) continue;
						temp.setProductId(pro.getProductId());
						temp.setProductName(pro.getProductTitle());
						temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
						temp.setRetailPrice(pro.getDefaultPrice());
						temp.setSellerWid(pro.getSupplierWeiId());
						double percent = 1.5;
						double displayPrice = pro.getDefaultPrice() * percent;
						DecimalFormat df = new DecimalFormat("#.00");
						displayPrice = Double.parseDouble(df.format(displayPrice));
						temp.setDisplayPrice(pro.getOriginalPrice() == null ? displayPrice : pro.getOriginalPrice());
						//判断是否是828活动产品
						ActProductInfo actnew=productDao.get_ProductAct(pro.getProductId());
						if(actnew!=null){
							AActivityProducts actProducts=baseProductDao.get(AActivityProducts.class, actnew.getProActId());
							temp.setActivity((short)actnew.getActiveType());
							if(actProducts!=null){
								temp.setRetailPrice(actProducts.getPrice());
								temp.setSellerWid(actProducts.getSellerId());
							}
						}
						proList.add(temp);
					}
					result = new PageResult<ProductInfo>(page.getTotalCount(), limit, proList);
					RedisUtil.setObject(keyName, result, 1800);	
				}
			}
			
		}
		if(result!=null&&result.getList()!=null&&result.getList().size()>0){
			//这里不能使用缓存，剩余库存每次都要取最新值
			List<ProductInfo>  proListlast=result.getList();
			if(proListlast!=null&&proListlast.size()>0){
				for (ProductInfo pp : proListlast) {
					ActProductInfo actnew=productDao.get_ProductAct(pp.getProductId());
					if(actnew!=null){
						AActShowProducts actProducts=baseProductDao.get(AActShowProducts.class, actnew.getProActId());
						if(actProducts!=null){
							pp.setStockCount(actProducts.getStockCount()==null?0:actProducts.getStockCount());//添加活动库存
						}
					}
				}
				result = new PageResult<ProductInfo>(result.getTotalCount(), limit, proListlast);
			}	
		}
		return result;
	}
	
	@Override
	public PageResult<ProductInfo> getExchangeProduct(Long weiId,Limit limit) {
		String keyName = "ExchangeProduct_"+weiId+"_"+ limit.getPageId() + "_" + limit.getSize();
		PageResult<ProductInfo> result = (PageResult<ProductInfo>) RedisUtil.getObject(keyName);
		if (result==null||result.getList()==null||result.getList().size()<=0) {
			String hql="select p.productId as productId,p.productTitle as productName,p.defaultImg as productPicture,p.defaultPrice as retailPrice,p.originalPrice as displayPrice,p.supplierWeiId as sellerWid,p.count as stockCount from PProducts as p where p.supplierWeiId=? and state=?";
			PageResult<ProductInfo> page = baseProductDao.findPageResultTrans(hql, ProductInfo.class, limit, weiId,Short.parseShort(ProductStatusEnum.Showing.toString()));
			if(page!=null){
				List<ProductInfo> prolist=page.getList();
				for (ProductInfo pro : prolist) {
					
					pro.setProductPicture(ImgDomain.GetFullImgUrl(pro.getProductPicture(), 24));
					double percent = 1.5;
					double displayPrice = pro.getRetailPrice()* percent;
					DecimalFormat df = new DecimalFormat("#.00");
					displayPrice = Double.parseDouble(df.format(displayPrice));
					pro.setDisplayPrice(pro.getDisplayPrice() == null ? displayPrice : pro.getDisplayPrice());
				}    
				result = new PageResult<ProductInfo>(page.getTotalCount(), limit, prolist);
				RedisUtil.setObject(keyName, result, 600);	
			}
		}
		return result;
	}
	
	@Override
	public List<ARedPacMoneyDetailVO> findRedPackageMoneyList(){
		List<ARedPacMoneyDetailVO> redPacList=new ArrayList<ARedPacMoneyDetailVO>();
		
		String keyName="ARedPackageMoney_"+DateUtils.getCurrentDate();
		Date nowdate=DateUtils.format(DateUtils.getCurrentDate(), "");
		ARedPackageMoney redpacket=(ARedPackageMoney) RedisUtil.getObject(keyName);
		if(redpacket==null){
			redpacket=actDao.find_todayRedPackageMoney(nowdate, 1);
			RedisUtil.setObject(keyName, redpacket, 1800);
		}
		
		if(redpacket!=null){
			Map<String, Object> params = new HashMap<String, Object>();
			//String sql="select a.redDetailId as redDetailId ,a.pid as pid,a.amount as amount,a.redtypeid as redTypeId,a.count as count,a.createtime as createTime,a.status as status,a.restcount as restCount,t.Amount as redTypeName from A_RedPacMoneyDetail as a inner join A_RedPacMoneyTypes as t on a.RedTypeId=t.RTypeId where a.pid=:pid and a.status=:status order by a.RedTypeId ";
			//PageResult<ARedPacMoneyDetailVO> page=baseProductDao.queryPageResultByMap(sql, ARedPacMoneyDetailVO.class, limit, params);
			//if(page!=null) redPacList=page.getList();
			String hql="from ARedPacMoneyDetail as a where a.pid=:pid and a.status=:status";
			params.put("pid", redpacket.getPid());
			params.put("status", 1);
			List<ARedPacMoneyDetail> list=baseProductDao.findByMap(hql, params);
			if(list!=null&&list.size()>0){
				for (ARedPacMoneyDetail aa : list) {
					ARedPacMoneyDetailVO temp=new ARedPacMoneyDetailVO();
					ARedPacMoneyTypes type=baseProductDao.get(ARedPacMoneyTypes.class, aa.getRedTypeId());
					temp.setAmount(aa.getAmount());
					temp.setCount(aa.getCount());
					temp.setCreateTime(DateUtils.format(aa.getCreateTime().toString(), ""));
					temp.setPid(aa.getPid());
					temp.setRedDetailId(aa.getRedDetailId());
					temp.setRedTypeName(type.getAmount().intValue());
					temp.setRestCount(aa.getRestCount()==null?0:aa.getRestCount());
					temp.setStatus(aa.getStatus());
					redPacList.add(temp);
				}
			}
		}
		return redPacList;
	}
	
	@Override
	public List<ARedPacMoneyAgLogVO> findPacketGetlogList(){
		List<ARedPacMoneyAgLogVO> logvolist=new ArrayList<ARedPacMoneyAgLogVO>();
		Date nowdate=DateUtils.format(DateUtils.getCurrentDate(), "");
		String sql="select a.WeiId,a.Amount,a.CreateTime from A_RedPacMoneyAgLog as a where  a.CreateTime>=?   order by a.TakeID desc limit 4";	
		List<Object[]>loglist=baseProductDao.queryBySql(sql, nowdate);
		if(loglist!=null) {
			for (Object[] log : loglist) {
				ARedPacMoneyAgLogVO logvo=new ARedPacMoneyAgLogVO();
				//UWeiSeller seller = baseProductDao.get(UWeiSeller.class, ParseHelper.toLong(log[0].toString()));
				//vo.setQq("****"+agent.getQq().substring(agent.getQq().length()-4,agent.getQq().length()));
				
				logvo.setWeiId(ParseHelper.toLong(log[0].toString()));
				logvo.setAmount(ParseHelper.toDouble(log[1].toString()));
				
				logvo.setWeiName("****"+log[0].toString().substring(log[0].toString().length()-3,log[0].toString().length()));
				logvo.setCreateTime(log[2].toString());
				//if(seller!=null){
				//	logvo.setWeiName(seller.getWeiName());
				//}
				logvolist.add(logvo);
			}
		}
		return logvolist;
	}
	
	
	@Override
	public ReturnModel openRedPacket(Long weiId){
		ReturnModel result=new ReturnModel();
		String currentdate=DateUtils.getCurrentDate();
		result.setStatu(ReturnStatus.Success);
		result.setStatusreson("");
		if (weiId==null||weiId==0) {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录!");
			return result;
	    } 
		
		//查询当天的用户抢红包记录
		String hql="from ARedPacMoneyAgLog as a  where a.weiId=? and TO_DAYS(a.createTime) = TO_DAYS(NOW())";
		List<ARedPacMoneyAgLog> log=baseProductDao.find(hql, weiId);
		if(log!=null&&log.size()>0){
			result.setStatu(ReturnStatus.DataExists);
			result.setStatusreson("每人每天仅限一次抽取机会，请明天再来！");
			return result;
		}if(log==null||log.size()==0){
			RedisUtil.delete(weiId.toString()+"|"+currentdate+"_lock");
		}
				
		// ***** 防止并发 *****//
		
		String redisOrder = RedisUtil.getString(weiId.toString()+"|"+currentdate+"_lock");// 获取订单号的缓存
		if (ObjectUtil.isEmpty(redisOrder)) {// 判断缓存是否存在
		    RedisUtil.setString(weiId.toString()+"|"+currentdate.toString()+"_lock", "123456", 300);// 缓存不存在，添加缓存
		} else {
			result.setStatusreson("不能频繁操作！");
			result.setStatu(ReturnStatus.DataError);
			return result;
		}

		//查询用户剩余可用的优惠券
		UWallet wt=baseProductDao.get(UWallet.class, weiId);
		if(wt == null)
		{
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("您尚未实名认证，请先认证！");
			return result;
		}
		if(wt.getAbleTicket()==null||wt.getAbleTicket()<=0){
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("亲，你暂无权限参加此活动，\r\n下次再来！");
			return result;
		}
		//随机抓取红包
		//得到可用的红包类型数组
		String keyName="ARedPackageMoney_"+DateUtils.getCurrentDate();
		Date nowdate=DateUtils.format(DateUtils.getCurrentDate(), "");
		ARedPackageMoney redpacket=(ARedPackageMoney) RedisUtil.getObject(keyName);
		if(redpacket==null){
			redpacket=actDao.find_todayRedPackageMoney(nowdate, 1);
			RedisUtil.setObject(keyName, redpacket, 1800);
		}
		if(redpacket==null){
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("亲，今天没有发放红包活动！");
			return result;
		}
		String sql="select a.RTypeId,b.RedDetailId from A_RedPacMoneyTypes as a inner join A_RedPacMoneyDetail as b where a.RTypeId=b.RedTypeId and a.Status=1 and b.RestCount>0 and b.PID="+redpacket.getPid()+"";
		List<Object[]> obList = baseProductDao.queryBySql(sql);
		if(obList==null||obList.size()==0){
			result.setStatu(ReturnStatus.ParamError);
			result.setStatusreson("已抢光");
			return result;
		}
		int  len = obList.size();
		Random random = new Random();//创建随机对象
		int arrIdx =random.nextInt(len);//随机数组索引
		Integer RTypeId =ParseHelper.toInt(obList.get(arrIdx)[0].toString()) ;//获取数组值
		Integer redDetailId=ParseHelper.toInt(obList.get(arrIdx)[1].toString());
		ARedPacMoneyTypes moneytype=baseProductDao.get(ARedPacMoneyTypes.class, RTypeId);
		Double amount=moneytype.getAmount();
		if(wt.getAbleTicket()<amount){
			amount=wt.getAbleTicket();
		}
		//用户绑定相应红包金额的微金币
		//插入抢红包记录
		ARedPacMoneyAgLog newGetLog=new ARedPacMoneyAgLog();
		newGetLog.setAmount(amount);
		newGetLog.setCreateTime(new Date());
		newGetLog.setRedDetailId(redDetailId);
		newGetLog.setWeiId(weiId);
		baseProductDao.save(newGetLog);
		//相应红包数量减1
		ARedPacMoneyDetail pacMoneyDetal=baseProductDao.get(ARedPacMoneyDetail.class, redDetailId);
		if(pacMoneyDetal!=null){
			pacMoneyDetal.setRestCount(pacMoneyDetal.getRestCount()-1);
			baseProductDao.saveOrUpdate(pacMoneyDetal);
		}
		
		if(amount>0)
		{
			UWeiDianCoinLog wc= new UWeiDianCoinLog();
			wc.setBalanceAmount((wt.getWeiDianCoin()==null?0:wt.getWeiDianCoin())+amount);
			wc.setConsumeAmount(0.0+amount);
			wc.setCreateTime(new Date());
			wc.setRemark("抽红包获得的微金币");
			wc.setType(ParseHelper.toShort(WeiDianCoinTypeEnum.shouru.toString()));
			wc.setWeiId(wt.getWeiId());
			baseProductDao.save(wc);
			
			UTicketUseLog ut=new UTicketUseLog();
			ut.setAmount(0.0-amount);
			ut.setCreateTime(new Date());
			ut.setRemark("兑换红包支出的购物券");
			ut.setType(ParseHelper.toInt(UTicketUseTypeEnum.zhichu.toString()));
			ut.setWeiId(wt.getWeiId());
			baseProductDao.save(ut);
			
			//减去用户钱包中相应的优惠券
			hql = "update UWallet  set ableTicket=?,weiDianCoin=? where weiId=?";
			baseProductDao.executeHql(hql, new Object[] { wt.getAbleTicket()-amount, (wt.getWeiDianCoin()==null?0:wt.getWeiDianCoin())+amount, weiId });
			//wt.setAbleTicket(wt.getAbleTicket()-amount);
			//wt.setWeiDianCoin((wt.getWeiDianCoin()==null?0:wt.getWeiDianCoin())+amount);
			//baseProductDao.saveOrUpdate(wt);
			
		}
		
		
		//moneytype.setAmount(amount);
		
		
		result.setBasemodle(amount);
		result.setStatu(ReturnStatus.Success);
		result.setStatusreson("恭喜你！成功抢到一个红包");
		return result;
	}
	@Override
	public List<PProductClass> findSecondPClassId(Integer classId){
		String hql="from PProductClass where step=2 and parentId=? order by sort";
		List<PProductClass> pclasslist =baseProductDao.find(hql, classId);
		return pclasslist;
		
	}
	
	public List<PProductClass> findClassId(List<Integer> sysSid){
		if (sysSid != null && sysSid.size() > 0) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("parentId", sysSid);
			List<PProductClass> a = baseProductDao.findByMap("from PProductClass where parentId in(:parentId)", m);
			if (a != null && a.size() > 0) {
				if (a.get(0).getStep()!=null&&a.get(0).getStep() == 3) {
					return a;
				} else {
					List<Integer> b = new ArrayList<Integer>();
					for (PProductClass c : a) {
						b.add(c.getClassId());
					}
					return  findClassId(b);
				}
			}
		}
		return null;
		
	}
}
