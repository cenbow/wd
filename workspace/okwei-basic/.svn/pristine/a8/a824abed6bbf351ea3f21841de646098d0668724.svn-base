package com.okwei.service.impl.friendcircle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.lang.ObjectUtils;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.domain.TFansNums;
import com.okwei.bean.domain.TWeiFans;
import com.okwei.bean.domain.UFriends;
import com.okwei.bean.domain.UFriendsShare;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.FriendAttentionState;
import com.okwei.bean.enums.FriendScope;
import com.okwei.bean.enums.MobileBindEnum;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.share.ProductListModel;
import com.okwei.bean.vo.share.ProductsVO;
import com.okwei.bean.vo.share.SharePageDetailModel;
import com.okwei.bean.vo.user.ContactModel;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IUUserAssistMgtDAO;
import com.okwei.service.friendcircle.IFriendCircleListService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.BitOperation;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

@Service
public class FriendCircleListService  extends BaseService implements IFriendCircleListService{

	@Autowired IBaseDAO baseDAO;
	@Autowired IUUserAssistMgtDAO userDAO;
	
	public ReturnModel  find_sharelist(Long weiid,int pageIndex,int pageSize){
		UWeiSeller userSeller=baseDAO.get(UWeiSeller.class, weiid);
		StringBuilder sb=new StringBuilder();
		//作为下游分销可见
		sb.append(" from UFriendsShare u where (u.weiId=:weiid and u.childSee=1) or u.weiId=:userid");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("weiid", userSeller.getSponsorWeiId());
		map.put("userid", weiid);
		//作为粉丝可见
		List<Long> fanslist=find_upContact(weiid, 2);
		if(fanslist!=null&&fanslist.size()>0){
			sb.append(" or (u.weiId in(:fans) and u.fansSee=1)");
			map.put("fans", fanslist);
		}
		//作为好友可见
		List<Long> friendslist= find_upContact(weiid, 3);
		if(friendslist!=null&&friendslist.size()>0){
			sb.append(" or (u.weiId in(:friends) and u.friendSee=1)");
			map.put("friends", friendslist);
		}
		sb.append(" order by u.id desc");
		PageResult<UFriendsShare> resultList=baseDAO.findPageResultByMap(sb.toString(), Limit.buildLimit(pageIndex, pageSize), map);
		ReturnModel rqModel=new ReturnModel();
		rqModel.setBasemodle(resultList);
		rqModel.setStatu(ReturnStatus.Success); 
		PageResult<SharePageDetailModel> result=new PageResult<SharePageDetailModel>();
		result.setPageIndex(pageIndex);
		result.setTotalPage(resultList.getTotalPage());
		result.setPageSize(pageSize); 
		if(resultList.getList()!=null&&resultList.getList().size()>0){
			List<SharePageDetailModel> pagelist=new ArrayList<SharePageDetailModel>();
			List<Long> pageIds=new ArrayList<Long>();
			List<Long> userIds=new ArrayList<Long>();
			for (UFriendsShare ff : resultList.getList()) {
				pageIds.add(ff.getShareId());
				userIds.add(ff.getWeiId());
			}
			List<UShopInfo> shopList=userDAO.find_shoplist(userIds);
			//TODO  获取分享id 
			String hql_share=" from SMainData s where s.shareId in(:shareids)";
			Map<String, Object> map_sha=new HashMap<String, Object>();
			map_sha.put("shareids", pageIds);
			List<SMainData> shaDatas=baseDAO.findByMap(hql_share, map_sha);
			
			//分享包含的产品
			String hql_pro=" from SProducts s where s.shareId in(:shareids)";
			Map<String, Object> map_pro=new HashMap<String, Object>();
			map_pro.put("shareids", pageIds);
			List<SProducts> prolist=baseDAO.findByMap(hql_pro, map_pro);
			//获取产品分享图片集合
			List<Long> sids=new ArrayList<Long>();
			List<SSingleDesc> imglist=null;
			if(prolist!=null&&prolist.size()>0){
				for (SProducts pp : prolist) {
					sids.add(pp.getSpid());
				}
				String hql_imgs=" from SSingleDesc s where s.spid in(:spids)";
				Map<String, Object> map_proImgs=new HashMap<String, Object>();
				map_proImgs.put("spids", sids);
				imglist=baseDAO.findByMap(hql_imgs, map_proImgs);
			}
			
			for (UFriendsShare ff :  resultList.getList()) {
				SharePageDetailModel mo=new SharePageDetailModel();
				mo.setShareDate(getTime( ff.getCreateTime()));
				mo.setLastEditDate(DateUtils.format(ff.getCreateTime(), "yyyy-MM-dd HH:mm:ss")); 
				mo.setShareOne(ff.getWeiId()); 
				mo.setFriendShareId(ff.getId()); 
				for (UShopInfo shop : shopList) {
					if(shop.getWeiId().longValue()==ff.getWeiId().longValue()){
						mo.setShareOneShopName(shop.getShopName());
						mo.setShareOneImgUrl(ImgDomain.GetFullImgUrl(shop.getShopImg()));
					}
				}
				for (SMainData ss : shaDatas) {
					if(ss.getShareId().longValue()==ff.getShareId().longValue()){
						mo.setPageId(ss.getShareId());//分享id
						mo.setPageProducer(ss.getWeiId());//制作人
						mo.setPageDescription(ss.getDescrible());
						mo.setPageTitle(ss.getTitle());
						mo.setPageTemplate(ss.getShareType());
						
						SStatics sta=baseDAO.get(SStatics.class, ss.getShareId());
						if(sta!=null){
							mo.setShareCount((sta.getWebSv()==null?0:sta.getWebSv())+(sta.getWapSv()==null?0:sta.getWapSv())+(sta.getAppSv()==null?0:sta.getAppSv()));// 分享数
							mo.setLikeCount(sta.getZanCount()==null?0:sta.getZanCount()); 
						}
						List<ProductListModel> pList=new ArrayList<ProductListModel>();
						for (SProducts pp : prolist) {
							if(pp.getShareId().longValue()==ff.getShareId().longValue()){
								PProducts product=baseDAO.get(PProducts.class, pp.getProductId());
								if(product==null)
									continue;
								ProductListModel pro=new ProductListModel();
								pro.setProductId(pp.getProductId());
								pro.setProductPicture(ImgDomain.GetFullImgUrl(product.getDefaultImg(),75));
								pro.setProductName(product.getProductTitle());
								pro.setRetailPrice(product.getDefaultPrice()); 
								pro.setDisplayPrice(product.getOriginalPrice());//原价
								pro.setCommission(product.getDefaultConmision()); 
								if(ss.getShareType()!=null&&ss.getShareType().shortValue()==Short.parseShort(ShareTypeEnum.SingleQuality.toString())){
									List<ProductsVO> imgs=new ArrayList<ProductsVO>();
									for (SSingleDesc mm : imglist) {
										if(mm.getSpid().longValue()==pp.getSpid().longValue()){
											ProductsVO im=new ProductsVO();
											im.setProductPicture(ImgDomain.GetFullImgUrl(mm.getImageUrl()));
											im.setShareDescription(mm.getDescription());
											imgs.add(im);
										}
									}
									pro.setProductPictureList(imgs); 
								}
								pList.add(pro);
							}
						}
						mo.setProductList(pList);
					}
				}
				
				pagelist.add(mo);
			}
			result.setList(pagelist); 
		}
		rqModel.setBasemodle(result);
		rqModel.setStatu(ReturnStatus.Success); 
		return rqModel;
	}
	
	/**
	 * 获取时间差
	 * @param time
	 * @return
	 */
	public String getTime(Date time){
		Date nowtime=new Date();
		long minite=(nowtime.getTime()-time.getTime())/(1000*60);
		if(minite<60){
			return minite+"分钟前";
		}
		else if ((minite/60)<24) {
			return (minite/60)+"小时前";
		}
		else if ((minite/(60*24))<2) {
			return "昨天";
		}
		else if ((minite/(60*24))<30) {
			return (minite/(60*24))+"天前";
		}else {
			return DateUtils.dateToString(time, "yyyy-MM-dd");
		}
	}
	
	
	/**
	 * 获取自己 关注的人
	 * @param weiid
	 * @param type 1上游，2关注的人（自己是他的粉丝），3好友
	 * @return
	 */
	public List<Long> find_upContact(Long weiid,int type){
		String keyName="attened_type_"+type +"_user_"+ weiid;
		List<Long> resultList = (List<Long>) RedisUtil.getObject(keyName);
		if (resultList == null || resultList.size() <= 0) {
			resultList = new ArrayList<Long>();
			if(type==1){
				UWeiSeller user = baseDAO.get(UWeiSeller.class, weiid);
				if (user != null) {
					resultList.add(user.getSponsorWeiId());
				}
			}
			else if (type==2) {
				String hql_atted = " from TWeiFans w where w.fanWeiId=? and w.flag=?";
				List<TWeiFans> fans = baseDAO.find(hql_atted, weiid,Short.parseShort(FriendAttentionState.Attention.toString()));
				for (TWeiFans ff : fans) { 
					resultList.add(ff.getWeiId());
				}
			}
			else if(type==3){
				String hql_friend = "from UFriends u where u.weiId=? and u.status=1 and u.attentionState=?";
				List<UFriends> friends = baseDAO.find(hql_friend, weiid,Short.parseShort(FriendAttentionState.Attention.toString()));
				for (UFriends ff : friends) { 
					resultList.add(ff.getFriendId());	
				}
			}
			RedisUtil.setObject(keyName, resultList, 300);
		}
		return resultList;
	}
	
	/**
	 * 搜索联系人
	 * @param weiid
	 * @param keyWord
	 * @param type
	 * @return
	 */
	public List<ContactModel> find_Contacts(Long weiid, String keyWord, int type) {
		if (ObjectUtil.isEmpty(keyWord))
			return null;
		List<ContactModel> list = new ArrayList<ContactModel>();
		List<UWeiSeller> userList = null;
		List<UShopInfo> shopList=null;//店铺
		if (ParseHelper.isMobile(keyWord)) {//手机号查询
			userList = baseDAO.find("from UWeiSeller u where  u.mobilePhone=? and u.mobileIsBind=2",  keyWord);
		}
		else if (ParseHelper.isNumeric(keyWord)) {//微店号查询
			userList = baseDAO.find("from UWeiSeller u where  u.weiId=? ",ParseHelper.toLong(keyWord));
		}else {//昵称查询
			shopList = baseDAO.find("from UShopInfo u where  u.shopName=? ",  keyWord);
			if(shopList==null||shopList.size()<=0){
				userList = baseDAO.find("from UWeiSeller u where  u.weiName=? ",  keyWord);
			}else {
				for (UShopInfo ss : shopList) {
					UWeiSeller seller=baseDAO.get(UWeiSeller.class, ss.getWeiId());
					seller.setWeiName(ss.getShopName());
					seller.setImages(ss.getShopImg()); 
					if(userList==null){
						userList=new ArrayList<UWeiSeller>();
						userList.add(seller);
					}else {
						userList.add(seller);
					}
				}
			}
		}
		List<Long> fanUserids=new ArrayList<Long>();
		if(userList!=null&&userList.size()>0){
			for (UWeiSeller uu : userList) {
				fanUserids.add(uu.getWeiId());
				if(shopList==null){
					UShopInfo shopInfo=baseDAO.get(UShopInfo.class, uu.getWeiId());
					if(shopInfo!=null){
						uu.setWeiName(shopInfo.getShopName());
						uu.setImages(shopInfo.getShopImg());
					}
				}
			}
		}
		if(fanUserids==null||fanUserids.size()<=0)
			return null;
		switch (type) {
		case 1:// 下游分销
			if (userList != null && userList.size() > 0) {
				for (UWeiSeller uu : userList) {
					if(uu.getSponsorWeiId().longValue()==weiid){
						ContactModel con = new ContactModel();
						con.setIsReseller(1);
						con.setIsNature(1);
						con.setNamePrefix(uu.getWeiName());
						con.setWeiId(uu.getWeiId());
						con.setLogo(ImgDomain.GetFullImgUrl(uu.getImages()));
						list.add(con);
					}
				}
			}
			break;
		case 2://粉丝列表
			String hql_fans="from TWeiFans t where t.weiId=:weiid and t.fanWeiId in(:userids)";
			Map<String, Object> map_fans=new HashMap<String, Object>();
			map_fans.put("weiid", weiid);
			map_fans.put("userids", fanUserids);
			List<TWeiFans> fanlist=baseDAO.findByMap(hql_fans, map_fans);
			for (TWeiFans ff : fanlist) {
				for (UWeiSeller user : userList) {
					if(user.getWeiId().longValue()==ff.getFanWeiId().longValue()) {
						ContactModel con=new ContactModel();
						con.setNamePrefix(user.getWeiName());
						con.setWeiId(ff.getFanWeiId());
						con.setLogo(ImgDomain.GetFullImgUrl(user.getImages()));
						list.add(con);
					}
				}
			}
			break;
		case 4://好友
			String hql_friend="from UFriends t where t.weiId=:weiid and t.friendId in(:userids)";
			Map<String, Object> map_friend=new HashMap<String, Object>();
			map_friend.put("weiid", weiid);
			map_friend.put("userids", fanUserids);
			List<UFriends> friends=baseDAO.findByMap(hql_friend, map_friend);
			for (UFriends ff : friends) {
				for (UWeiSeller uu : userList) {
					if(ff.getFriendId().longValue()==uu.getWeiId().longValue()){
						ContactModel con=new ContactModel();
						con.setIsReseller(1);
						con.setNamePrefix(uu.getWeiName());
						con.setWeiId(uu.getWeiId());
						con.setLogo(ImgDomain.GetFullImgUrl(uu.getImages()));
						list.add(con);
					}
				}
			}
			break;
		default:
			for (UWeiSeller uu : userList) {
				ContactModel con = new ContactModel();
				con.setIsReseller(1);
				con.setIsNature(1);
				con.setNamePrefix(uu.getWeiName());
				con.setWeiId(uu.getWeiId());
				con.setLogo(ImgDomain.GetFullImgUrl(uu.getImages()));
				list.add(con);
			}
			break;
		}
		return list;
	}
	
	public ReturnModel find_ContactList(Long weiid,String keyword,int scope,int pageIndex,int pageSize){
		ReturnModel rqModel=new ReturnModel();
		PageResult<ContactModel> result=new PageResult<ContactModel>();
		result.setPageIndex(pageIndex);
		result.setPageSize(pageSize);
		List<ContactModel> list=new ArrayList<ContactModel>();
		if(ObjectUtil.isEmpty(keyword)){
			switch (scope) {
			case 1://下游分销
				PageResult<UWeiSeller> listResult = baseDAO.findPageResult(" from UWeiSeller u where u.sponsorWeiId=? ", Limit.buildLimit(pageIndex, pageSize), weiid);
				if (listResult != null) {
					result.setTotalPage(listResult.getTotalPage());
					result.setTotalCount(listResult.getTotalCount());
					if (listResult.getList() != null && listResult.getList().size() > 0) {
						for (UWeiSeller uu : listResult.getList()) {
							UShopInfo shopInfo=baseDAO.get(UShopInfo.class, uu.getWeiId());
							ContactModel con = new ContactModel();
							con.setIsReseller(1);
							con.setIsNature(1);
							if(shopInfo!=null){
								con.setNamePrefix(shopInfo.getShopName());
								con.setLogo(ImgDomain.GetFullImgUrl(shopInfo.getShopImg()));
							}else {
								con.setNamePrefix(uu.getWeiName());
								con.setLogo(ImgDomain.GetFullImgUrl(uu.getImages()));
							}
							con.setWeiId(uu.getWeiId());
							
							list.add(con);
						}
					}
					result.setList(list);
				}
				break;
			case 2://粉丝列表
				PageResult<TWeiFans> fansResult= baseDAO.findPageResult("from TWeiFans t where t.weiId=? ", Limit.buildLimit(pageIndex, pageSize), weiid);
				if(fansResult!=null){
					result.setTotalPage(fansResult.getTotalPage());
					result.setTotalCount(fansResult.getTotalCount());
					List<Long> fanidsList=new ArrayList<Long>();
					if(fansResult.getList()!=null&&fansResult.getList().size()>0){
						for (TWeiFans ff : fansResult.getList()) {
							fanidsList.add(ff.getFanWeiId());
							UShopInfo shopInfo=baseDAO.get(UShopInfo.class, ff.getFanWeiId());
							if(shopInfo!=null){
								ContactModel con=new ContactModel();
								con.setNamePrefix(shopInfo.getShopName());
								con.setWeiId(ff.getFanWeiId());
								con.setLogo(ImgDomain.GetFullImgUrl(shopInfo.getShopImg()));
								list.add(con);
							}else {
								UWeiSeller user=baseDAO.get(UWeiSeller.class, ff.getFanWeiId());
								if(user!=null){
									ContactModel con=new ContactModel();
									con.setNamePrefix(user.getWeiName());
									con.setWeiId(ff.getFanWeiId());
									con.setLogo(ImgDomain.GetFullImgUrl(user.getImages()));
									list.add(con);
								}
							}
							
						}
						result.setList(list);
					}
				}
				break;
			case 4://查找好友
				PageResult<UFriends> friendsResult = baseDAO.findPageResult("from UFriends u where u.weiId=? and u.status=1", Limit.buildLimit(pageIndex, pageSize), weiid);
				result.setTotalPage(friendsResult.getTotalPage());
				result.setTotalCount(friendsResult.getTotalCount());
				if (friendsResult.getList() != null && friendsResult.getList().size() > 0) {
					for (UFriends uu : friendsResult.getList()) {
						ContactModel con = new ContactModel();
						UShopInfo shopInfo=baseDAO.get(UShopInfo.class, uu.getFriendId());
						if(shopInfo!=null){
							con.setNamePrefix(shopInfo.getShopName());
							con.setWeiId(uu.getFriendId());
							con.setLogo(ImgDomain.GetFullImgUrl(shopInfo.getShopImg()));
							list.add(con);
						}else {
							UWeiSeller user = baseDAO.get(UWeiSeller.class, uu.getFriendId());
							con.setIsReseller(1);
							con.setNamePrefix(user.getWeiName());
							con.setWeiId(uu.getFriendId());
							con.setLogo(ImgDomain.GetFullImgUrl(user.getImages()));
							list.add(con);
						}
					}
				}
				result.setList(list);
				break;
			default:
				break;
			}
		}else {
			List<ContactModel> list_child=null;
			if(BitOperation.isFriendType(scope, FriendScope.child)){
				list_child=find_Contacts(weiid, keyword, Short.parseShort(FriendScope.child.toString()));
			}
			List<ContactModel> list_fans=null;
			if(BitOperation.isFriendType(scope, FriendScope.fans)){
				list_fans=find_Contacts(weiid, keyword, Short.parseShort(FriendScope.fans.toString()));
			}
			List<ContactModel> list_friends=null;
			if(BitOperation.isFriendType(scope, FriendScope.friends)){
				list_friends=find_Contacts(weiid, keyword,  Short.parseShort(FriendScope.friends.toString()));
			}
			if (!BitOperation.isFriendType(scope, FriendScope.child) && !BitOperation.isFriendType(scope, FriendScope.fans) && !BitOperation.isFriendType(scope, FriendScope.friends)) {
				List<ContactModel> searchlist = find_Contacts(weiid, keyword, scope);
				if (searchlist != null && searchlist.size() > 0) {
					list.addAll(searchlist);
				}
			}
			if(list_child!=null&&list_child.size()>0){
				list.addAll(list_child);
			}
			if(list_fans!=null&&list_fans.size()>0){
				for (ContactModel cc : list_fans) {
					boolean ishave=false;
					for (ContactModel ll : list) {
						if(ll.getWeiId().longValue()==cc.getWeiId().longValue())
							ishave=true;
					}
					if(!ishave){
						list.add(cc);
					}
				}
			}
			if(list_friends!=null&&list_friends.size()>0){
				for (ContactModel cc : list_friends) {
					boolean ishave=false;
					for (ContactModel ll : list) {
						if(ll.getWeiId().longValue()==cc.getWeiId().longValue())
							ishave=true;
					}
					if(!ishave){
						list.add(cc);
					}
				}
			}
			if(list!=null&&list.size()>0){
				result.setTotalCount(list.size());
				result.setPageCount(1);
				result.setList(list);
			}
		}
		rqModel.setStatu(ReturnStatus.Success);
		rqModel.setBasemodle(result);
		return rqModel;
	}
	
	public long count_FriendsShareToday(Long weiid){
		String hqlString="select count(*) from UFriendsShare u where u.weiId=? and u.createTime>? and u.createTime<?";
		return baseDAO.count(hqlString,weiid,getStartTime(),getEndTime() );	
	}
	
	private Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	private Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}
	
	public ReturnModel add_FriendShare(List<Long> shareIds,int scope,Long weiid){
		ReturnModel rqmModel=new ReturnModel();
		int maxCount=10;
		if(shareIds!=null&&shareIds.size()>0){
			long countToday=count_FriendsShareToday(weiid);
			if(countToday>=maxCount){
				rqmModel.setStatu(ReturnStatus.ParamError);
				rqmModel.setStatusreson("每天最多只能发"+maxCount+"次微店圈，您今天已经发过"+countToday+"次了！");
				return rqmModel;
			}else if ((countToday+shareIds.size())>maxCount) {
				rqmModel.setStatu(ReturnStatus.ParamError);
				rqmModel.setStatusreson("您今天已经发了"+countToday+"次微店圈，此次您最多只能勾选"+(maxCount-countToday)+"个分享页！");
				return rqmModel;
			}
			for (Long ll : shareIds) {
				//获取分享信息
				SMainData shaData=baseDAO.get(SMainData.class, ll);
				if(shaData!=null){
					UFriendsShare model=new UFriendsShare();
					model.setShareId(ll);
					model.setWeiId(weiid);
					model.setCreateTime(new Date());
					if(BitOperation.isFriendType(scope, FriendScope.child)){
						model.setChildSee((short)1);
					}
					if(BitOperation.isFriendType(scope, FriendScope.fans)){
						model.setFansSee((short)1);
					}
					if(BitOperation.isFriendType(scope, FriendScope.friends)){
						model.setFriendSee((short)1);
					}
					baseDAO.save(model);
				}
			}
			Map<String, Object> result=new HashMap<String, Object>();
			if(BitOperation.isFriendType(scope, FriendScope.child)){
				result.put("resellerNum", userDAO.count_children(weiid));
			}
			if(BitOperation.isFriendType(scope, FriendScope.fans)){
				TFansNums nums=baseDAO.get(TFansNums.class, weiid);
				if(nums!=null){
					result.put("fanNum", nums.getNums());
				}else {
					long count= userDAO.count_fans(weiid);
					if(count>0){
						nums=new TFansNums();
						nums.setWeiId(weiid);
						nums.setNums((int)count);
						result.put("fanNum", count);
						baseDAO.save(nums);
					}
				}
			}
			if(BitOperation.isFriendType(scope, FriendScope.friends)){
				result.put("friendNum", userDAO.count_friends(weiid));
			}
			rqmModel.setBasemodle(result);
			rqmModel.setStatu(ReturnStatus.Success);
		}
		return rqmModel;
	}
	
	
}
