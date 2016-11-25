package com.okwei.appinterface.web.friendcircle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.service.friendcircle.IFriendCircleListService;
import com.okwei.service.friendcircle.IFriendCircleService;
import com.okwei.appinterface.bean.vo.share.MyShareVO;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * @author fuhao
 *
 */
@RequestMapping("/friendCircle")
@RestController
public class FriendCircleController extends SSOController {

	private static final Log logger = LogFactory.getLog(FriendCircleController.class);

	@Autowired
	public IFriendCircleService friendCircleService;

	@Autowired
	public IFriendCircleListService flistService;

	/**
	 * 朋友圈点“赞”
	 * 
	 */
	@RequestMapping("/likeFriendShare")
    public String likeSharePage(Long friendShareId, Short deny) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = friendCircleService.updateLikeSharePage(user.getWeiID(), friendShareId,deny);
	return JsonUtil.objectToJsonStr(rm);
    }
	
	
	/**
	 * 请求加好友
	 * 
	 * @param weiNo
	 *            用户微店号
	 * @param requestExtra
	 *            请求附加信息，留做备用，可为空
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/friendRequest")
	public String friendRequest(Long weiNo, String requestExtra) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		Long friendId = weiNo;// 请求加为好友的微店号
		long myWeiNo = user.getWeiID();// 自已的微店号
		rm = friendCircleService.friendRequest(myWeiNo, friendId, requestExtra);
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 回复加好友请求
	 * 
	 * @param weiNo
	 *            用户微店号 (请求好友微店号)
	 * @param reply
	 *            =1 请求回复，1为同意，2为拒绝
	 * @param replyExtra
	 *            回复的附加信息，如拒绝理由，留做备用，可为空
	 * @param requestCode
	 *            加好友请求代码
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/replyFriendRequest")
	public String replyFriendRequest(Long weiNo, Short reply, String replyExtra, String requestCode) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}

		Long myweiNO = user.getWeiID();
		rm = friendCircleService.replyFriendRequest(myweiNO, weiNo, reply, replyExtra, requestCode);
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 删除好友
	 * 
	 * @param friendId
	 *            用户微店号
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/deleteFriend")
	public String deleteFriend(Long weiNo) throws MapperException {
		ReturnModel rm = new ReturnModel();
		Long friendId = weiNo;
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		Long myweiNo = user.getWeiID();
		rm = friendCircleService.deleteFriend(myweiNo, friendId);
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 关注/取消关注微店
	 * 
	 * @param weiNo
	 *            用户微店号
	 * @param attention
	 *            关注微店，1为关注，0为取消关注
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/patAttentionTo")
	public String patAttentionTo(Long weiNo, Short attention) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		Long friendId = user.getWeiID();
		rm = friendCircleService.patAttentionTo(weiNo, friendId, attention);
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 屏蔽/取消屏蔽微店圈
	 * 
	 * @param weiNo
	 *            用户微店号
	 * @param deny
	 *            屏蔽微店圈，1为屏蔽，0为允许
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/circleDeny")
	public String circleDeny(Long weiNo, Short deny) throws MapperException {
		ReturnModel rm = new ReturnModel();
		Long friendId = weiNo;
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		Long myweiNo = user.getWeiID();
		rm = friendCircleService.circleDeny(myweiNo, friendId, deny);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
     * 免打扰/取消免打扰
     * @param weiNo 用户微店号 
     * @param deny  设置为免打扰，1为免打扰，0为取消免打扰
     * @return
     * @throws MapperException
     */
    @RequestMapping("/notifyDeny")
	public String notifyDeny(Long weiNo, Short deny)
			throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}

		Long myweiNO = user.getWeiID();
		rm = friendCircleService.disturbDeny(myweiNO, weiNo, deny);
		return JsonUtil.objectToJsonStr(rm);
	}
    
    /**
	 * FC07 获取加好友请求列表
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/friendRequestList")
	public String getfriendRequestList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "20") int pageSize) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = friendCircleService.findfriendRequestlist(user.getWeiID(), pageIndex, pageSize);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * FC08 清除加好友请求列表
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/clearFriendRequest")
	public String doclearFriendRequest(Long weiNo) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = friendCircleService.doclearFriendRequest(user.getWeiID(),weiNo);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * FC09 获取联系人列表（含搜索）
	 * 
	 * @param keyword
	 * @param scope
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/contactList")
	public String contactList(String keyword, int scope, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getLoginUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = flistService.find_ContactList(user.getWeiID(), keyword, scope, pageIndex, pageSize);

		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * FC10 微店圈分享列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/sharePageList")
	public String sharePageList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getLoginUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = flistService.find_sharelist(user.getWeiID(), pageIndex, pageSize);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * FC11 发分享页到微店圈
	 * @param pageList
	 * @param scope
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/forwardSharePage")
	public String forwardSharePage(String pageList,int scope) throws MapperException {
		ReturnModel rm=new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		List<MyShareVO> list=(List<MyShareVO>)JsonUtil.json2Objectlist(pageList, MyShareVO.class);
		List<Long> idsList=new ArrayList<Long>();
		
		if(list!=null&&list.size()>0){
			if(list.size()>3){
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("最多只能选择3个分享页！");
			}else {
				for (MyShareVO ss : list) {
					idsList.add(ss.getPageId());
				}
				rm  = flistService.add_FriendShare(idsList, scope, user.getWeiID());
			}
		}else {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数有误");
		}
		return JsonUtil.objectToJsonStr(rm);
	}
}
