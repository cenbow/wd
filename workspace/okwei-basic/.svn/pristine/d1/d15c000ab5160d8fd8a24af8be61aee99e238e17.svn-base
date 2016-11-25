package com.okwei.service.impl.friendcircle;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.service.IBaseCommonService;
import com.okwei.service.friendcircle.IFriendCircleService;
import com.okwei.bean.domain.TWeiFans;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UFriends;
import com.okwei.bean.domain.UFriendsApply;
import com.okwei.bean.domain.UFriendsShare;
import com.okwei.bean.domain.UFriendsShareZanLog;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.FriendAttentionState;
import com.okwei.bean.enums.FriendReplyEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.FriendRequestVo;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.common.SendPushMessage;
import com.okwei.dao.friendcircle.IFriendCircleDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.PingYinUtil;
import com.okwei.util.RedisUtil;

@Service
public class FriendCircleService extends BaseService implements
		IFriendCircleService {

	@Autowired
	private IFriendCircleDAO basicFriendCircleDAO;
	@Autowired
	private IBaseCommonService baseCommonService;

	@Override
	public ReturnModel friendRequest(Long weiNo, Long friendId,
			String requestExtra) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("");
		if (weiNo.equals(friendId)) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("不能请求加自已为好友!");
			return rm;
		}
		//判断是否是好友关系
		UFriends  friend=basicFriendCircleDAO.getUniqueResultByHql(" from UFriends where status='1' and  weiId=? and friendId=? ", weiNo,friendId);
		if(friend!=null){
			rm.setStatusreson("对方已是你的好友！");
			rm.setStatu(ReturnStatus.DataError);
			return rm;
		}
		//先删除原来的好友请求
		basicFriendCircleDAO.deleteUfrindAppay(friendId,weiNo);
		UFriendsApply friendApply = new UFriendsApply();
		friendApply.setCreateTime(new Date());
		friendApply.setFriendId(friendId);
		friendApply.setRemark(requestExtra);
		friendApply.setStatus(Short.parseShort(FriendReplyEnum.Applying
				.toString()));
		friendApply.setWeiId(weiNo);
		
		basicFriendCircleDAO.save(friendApply);
		SendPushMessage sendPushMessage=new SendPushMessage();
		sendPushMessage.insertPushMsg(friendId, (short)12, "请求加你为好友！", "", "");
		rm.setStatusreson("成功");
		rm.setStatu(ReturnStatus.Success);
		Map<String, Long> result = new HashMap<String, Long>();
		result.put("requestCode", friendApply.getId());
		rm.setBasemodle(result);
		return rm;
	}

	@Override
	@Transactional
	public ReturnModel replyFriendRequest(Long weiNo, Long friendId,
			Short reply, String replyExtra, String requestCode) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		if (weiNo.equals(friendId)) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("不能加自已为好友!");
			return rm;
		}
		//判断是否是好友关系
		
		// ***** 防止并发 *****//
		String redisOrder = RedisUtil.getString(weiNo.toString()+"|"+friendId.toString()+"_lock");// 获取订单号的缓存
		if (ObjectUtil.isEmpty(redisOrder)) {// 判断缓存是否存在
		    RedisUtil.setString(weiNo.toString()+"|"+friendId.toString()+"_lock", "123456", 300);// 缓存不存在，添加缓存
		} else {
			rm.setStatusreson("不能频繁操作！");
			rm.setStatu(ReturnStatus.DataError);
			return rm;
		}
		UFriendsApply friendApply = basicFriendCircleDAO.get(UFriendsApply.class, ParseHelper.toLong(requestCode));
		UFriends  friend=basicFriendCircleDAO.getUniqueResultByHql(" from UFriends where status='1' and weiId=? and friendId=? ", weiNo,friendId);
		if(friend!=null){
			// 修改请求记录中的状态
			friendApply.setStatus(ParseHelper.toShort(FriendReplyEnum.Agree
					.toString()));
			basicFriendCircleDAO.update(friendApply);
			rm.setStatusreson("对方已是你的好友！");
			rm.setStatu(ReturnStatus.Success);
			return rm;
		}
		
		HashMap<String, String> jsonmap=new HashMap<String, String>();
		jsonmap.put("reply", reply.toString());
		jsonmap.put("replyExtra", replyExtra);
		jsonmap.put("requestCode", requestCode);
		SendPushMessage sendPushMessage=new SendPushMessage();
		
		// 同意加为好友
		if (reply == ParseHelper.toShort(FriendReplyEnum.Agree.toString())) {
			UFriends friend1 = new UFriends();
			UFriends friend2 = new UFriends();
			Date createTime = new Date();
			// 修改请求记录中的状态
			friendApply.setStatus(ParseHelper.toShort(FriendReplyEnum.Agree
					.toString()));
			basicFriendCircleDAO.update(friendApply);

			// 需要插入二条记录互为好友
			friend1.setCreateTime(createTime);
			friend1.setFriendId(friendId);
			friend1.setStatus((short) 1);// 状态暂时确定为1 2 为拉黑
			friend1.setWeiId(weiNo);
			friend1.setAttentionState(ParseHelper
					.toShort(FriendAttentionState.Attention.toString()));
			basicFriendCircleDAO.save(friend1);

			friend2.setCreateTime(createTime);
			friend2.setFriendId(weiNo);
			friend2.setStatus((short) 1);
			friend2.setWeiId(friendId);
			friend2.setAttentionState(ParseHelper
					.toShort(FriendAttentionState.Attention.toString()));
			basicFriendCircleDAO.save(friend2);
			//推送消息
			sendPushMessage.insertPushMsg(weiNo, (short)13, "同意你的好友请求！", "", JsonUtil.simpleMapToJsonStr(jsonmap));
		} else {// 拒绝加为好友
				// 修改请求记录中的状态
			friendApply.setStatus(ParseHelper.toShort(FriendReplyEnum.Refuse
					.toString()));
			basicFriendCircleDAO.update(friendApply);
			//推送消息
			sendPushMessage.insertPushMsg(weiNo, (short)13, "拒绝你的好友请求！", "",  JsonUtil.simpleMapToJsonStr(jsonmap));
		}
		
		rm.setStatusreson("成功");
		rm.setStatu(ReturnStatus.Success);
		return rm;
	}

	@Override
	@Transactional
	public ReturnModel deleteFriend(Long weiNo, Long friendId) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);

		// 逻辑删除好友
		UFriends friend = basicFriendCircleDAO.getUFriends(weiNo, friendId);
		if (friend != null) {
			basicFriendCircleDAO.delete(friend);
			// 对方好友列表也要删除
			UFriends relatefriend = basicFriendCircleDAO.getUFriends(friendId,
					weiNo);
			if(relatefriend!=null){
				basicFriendCircleDAO.delete(relatefriend);
			}
			rm.setStatusreson("成功");
			rm.setStatu(ReturnStatus.Success);
		} else {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("数据不存在,删除失败！");
		}
		return rm;
	}

	@Override
	public ReturnModel patAttentionTo(Long weiNo, Long friendId, Short attention) {

		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		// 关注
		if (attention == ParseHelper.toShort(FriendAttentionState.Attention.toString())) {
			if (weiNo.equals(friendId)) {
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("不能关注自己!");
				return rm;
			}
			UWeiSeller seller = basicFriendCircleDAO.get(UWeiSeller.class, weiNo);
			if (seller == null) {
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("请求关注的对象必须是注册用户！");
				return rm;
			}
			boolean isExistsTfans = basicFriendCircleDAO.IsExistsTfansAttention(weiNo, friendId);
			if(isExistsTfans){
				rm.setStatusreson("已经是关注关系");
				rm.setStatu(ReturnStatus.Success);
			}else{
				UAttention uattention =basicFriendCircleDAO.getUAttentionByparam(weiNo, friendId);
				if(uattention==null){
					uattention=new UAttention();
					uattention.setAttentioner(friendId);
					uattention.setAttTo(weiNo);
					
					String attToStr =baseCommonService.getShopNameByWeiId(weiNo);
					uattention.setAttToPY(PingYinUtil.getFirstFirstSpell(attToStr));
					uattention.setCreateTime(new Date());
					uattention.setStatus((short) 1);
				}else{
					if(uattention.getStatus()!=null&&uattention.getStatus().shortValue()!=2)
						uattention.setStatus((short) 1);
				}
				UAttentioned to =basicFriendCircleDAO.getUAttentionedByparam(weiNo, friendId);
				if(to==null){
					to = new UAttentioned();
					to.setAttentioner(friendId);
					to.setAttTo(weiNo);
					to.setCreateTime(new Date());
					String attenerStr = baseCommonService.getShopNameByWeiId(friendId);
					to.setAttentionerPY(PingYinUtil.getFirstFirstSpell(attenerStr));
					to.setStatus((short) 1);
				}else{
					if(uattention.getStatus()!=null&&uattention.getStatus().shortValue()!=2)
						uattention.setStatus((short) 1);
				}
				
				TWeiFans weifans = new TWeiFans();
				weifans.setFanWeiId(friendId);
				weifans.setFlag(attention);
				weifans.setWeiId(weiNo);
				basicFriendCircleDAO.saveOrUpdate(uattention);
				basicFriendCircleDAO.saveOrUpdate(to);
				basicFriendCircleDAO.save(weifans);
				rm.setStatusreson("成功");
				rm.setStatu(ReturnStatus.Success);
				
			}
			
		}
		// 取消关注
		if (attention == ParseHelper.toShort(FriendAttentionState.NoAttention
				.toString())) {
			TWeiFans weifans = basicFriendCircleDAO.getTWeiFans(weiNo, friendId);
			UAttention uattention =basicFriendCircleDAO.getUAttentionByparam(weiNo, friendId);
			UAttentioned to =basicFriendCircleDAO.getUAttentionedByparam(weiNo, friendId);
			if(uattention!=null){
				basicFriendCircleDAO.delete(uattention);
			}
			if(to!=null){
				basicFriendCircleDAO.delete(to);
			}
			if (weifans!= null) {
				basicFriendCircleDAO.delete(weifans);
				rm.setStatusreson("成功");
				rm.setStatu(ReturnStatus.Success);
			} else {
				rm.setStatu(ReturnStatus.Success);
				rm.setStatusreson("你已经对他取消关注了！");
			}
		}
		return rm;
	}

	@Override
	@Transactional
	public ReturnModel circleDeny(Long weiNo, Long friendId, Short deny) {

		
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		TWeiFans weifans = basicFriendCircleDAO.getTWeiFans(friendId, weiNo);
		UFriends friend = basicFriendCircleDAO.getUFriends(weiNo, friendId);
		if (weiNo.equals(friendId)) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("不能屏蔽自已!");
			return rm;
		}
		// 1为屏蔽
		if (deny == ParseHelper.toShort("1")) {
			//判断是否是上下级关系
			UAttention attention = basicFriendCircleDAO.getUAttentionByparam(weiNo, friendId);
			if (attention != null) {
				if(attention.getStatus()!=null&&attention.getStatus().shortValue()==2){
					rm.setStatusreson("分销商关系不能屏蔽！");
					rm.setStatu(ReturnStatus.Success);
					return rm;
				}
			}

			if (weifans != null)
				weifans.setFlag(ParseHelper
						.toShort(FriendAttentionState.NoAttention.toString()));
			if (friend != null)
				friend.setAttentionState(ParseHelper
						.toShort(FriendAttentionState.NoAttention.toString()));
		}
		// 0为取消屏蔽
		if (deny == ParseHelper.toShort("0")) {
			if (weifans != null)
				weifans.setFlag(ParseHelper
						.toShort(FriendAttentionState.Attention.toString()));
			if (friend != null)
				friend.setAttentionState(ParseHelper
						.toShort(FriendAttentionState.Attention.toString()));
		}
		if (weifans != null) {
			basicFriendCircleDAO.update(weifans);
		}
		if (friend != null) {
			basicFriendCircleDAO.update(friend);
		}
		rm.setStatusreson("成功");
		rm.setStatu(ReturnStatus.Success);
		return rm;
	}

	@Override
	public ReturnModel disturbDeny(Long myweiNO, Long weiNo, Short deny) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		if (deny == 0) {
			basicFriendCircleDAO.deleteDisturb(myweiNO, weiNo);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("取消免打扰成功！");
			return rm;
		}
		if (deny == 1) {
			basicFriendCircleDAO.saveDisturb(myweiNO, weiNo);
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("加入免打扰成功！");
			return rm;
		}
		return rm;
	}

	@Override
	public ReturnModel updateLikeSharePage(Long weiID, Long friendShareId, Short deny) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		if (weiID == 0) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("该微店号不存在！");
			return rm;
		}
		if (deny == 1) {//点赞
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson("失败！");
			String hql = "from UFriendsShareZanLog where friendShareId=? and weiId=?";
			UFriendsShareZanLog ufsz = basicFriendCircleDAO.getUniqueResultByHql(hql, friendShareId, weiID);
			// 当该用户未点过 “赞” 时
			if (ufsz == null) {
				// 添加点赞信息
				UFriendsShareZanLog zU = new UFriendsShareZanLog();
				zU.setCreateTime(new Date());
				zU.setFriendShareId(friendShareId);
				zU.setWeiId(weiID);
				basicFriendCircleDAO.save(zU);

			} else {
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("你已经对该分享,点过赞了。");
				return rm;
			}
			UFriendsShare ufsh = basicFriendCircleDAO.get(UFriendsShare.class,friendShareId);
			if(ufsh!=null){
				ufsh.setZanCount((ufsh.getZanCount()==null?0:ufsh.getZanCount())+1);
				basicFriendCircleDAO.update(ufsh);
				rm.setStatu(ReturnStatus.Success);
				rm.setStatusreson("成功");
			}else {
				rm.setStatu(ReturnStatus.DataError);
				rm.setStatusreson("此分享不存在！");
				return rm;
			}
		}
		else if (deny == 0) {
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("取消成功！");
			String hql = "from UFriendsShareZanLog where friendShareId=? and weiId=?";
			UFriendsShareZanLog ufsz = basicFriendCircleDAO
					.getUniqueResultByHql(hql, friendShareId, weiID);
			if (ufsz != null) {
				basicFriendCircleDAO.delete(ufsz);
				UFriendsShare ufsh = (UFriendsShare) basicFriendCircleDAO
						.getUniqueResultByHql("from UFriendsShare where id=?",friendShareId);
				Integer count = ufsh.getZanCount() - 1;
				ufsh.setZanCount(count);
				basicFriendCircleDAO.update(ufsh);
			} else {
				rm.setStatu(ReturnStatus.DataError);
				rm.setStatusreson("您对改分享没有点过赞");
				return rm;
			}

		}
		return rm;
	}
	
	@Override
	public ReturnModel  findfriendRequestlist(Long weiId,int pageIndex,int pageSize){
		ReturnModel rm=new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		if ( weiId==null ||weiId <= 0) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("该微店号不存在！");
			return rm;
		}

		String hql="from UFriendsApply u where u.friendId=? order by createTime desc  ";
		
		PageResult<FriendRequestVo> result=new PageResult<FriendRequestVo>();
		result.setPageIndex(pageIndex);
		result.setPageSize(pageSize);
		
		PageResult<UFriendsApply>  listResult=basicFriendCircleDAO.findPageResult(hql,  Limit.buildLimit(pageIndex, pageSize), weiId);
		List<FriendRequestVo> frinedRequestVoList=new ArrayList<FriendRequestVo>();
		Map<Long, Object> allUser=new HashMap<Long, Object>();
		if(listResult!=null){
			result.setTotalPage(listResult.getTotalPage());
			result.setTotalCount(listResult.getTotalCount());
			if (listResult.getList() != null && listResult.getList().size() > 0) {
				for (UFriendsApply friendapply : listResult.getList()) {
					if(friendapply == null)
						continue;
					//如果frinedRequestVoList 已经包括这个好友请求，则不加入了
					UWeiSeller user = basicFriendCircleDAO.get(UWeiSeller.class, friendapply.getWeiId());
					if(user == null)
						continue;
					if(allUser.containsKey(friendapply.getWeiId())){
						continue;
					}else{
						allUser.put(friendapply.getWeiId(), user);
					}
					FriendRequestVo friendRequestVo=new FriendRequestVo();
					friendRequestVo.setWeiNo(user.getWeiId());
					friendRequestVo.setShopName(user.getWeiName());
					friendRequestVo.setShopPicture(ImgDomain.GetFullImgUrl(user.getImages()));
					friendRequestVo.setRequestTime(DateUtils.format(friendapply.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					friendRequestVo.setRequestExtra(friendapply.getRemark());
					friendRequestVo.setRequestCode(friendapply.getId());
					friendRequestVo.setReplyExtra("");
					friendRequestVo.setState(friendapply.getStatus());
					
					frinedRequestVoList.add(friendRequestVo);
				}
				
			}
			result.setList(frinedRequestVoList);
		}
		rm.setBasemodle(result);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("获取加好友请求列表成功");
		return rm;
	
	}
	
	@Override
	public ReturnModel  doclearFriendRequest(Long friendId,Long weiId){
		ReturnModel rm=new ReturnModel();
		if (weiId == 0) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("该微店号不存在！");
			return rm;
		}
		//清除所有好友请求
		if(friendId==-1){
			basicFriendCircleDAO.deleteUfrindAppayByWeiId(friendId);
		}else{
			basicFriendCircleDAO.deleteUfrindAppay(friendId,weiId);
		}
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功");
		return rm;
		
	}
}
