package com.okwei.appinterface.web.share;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.service.share.IShareSvervice;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.util.AppSettingUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * @author fuhao
 *
 */
@RequestMapping("/SharePage")
@RestController
public class ShareController extends SSOController {

    private static final Log logger = LogFactory.getLog(ShareController.class);

    @Autowired
    public IShareSvervice shareSvervice;

    /**
     * 获取 收藏的分享列表
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/getCollectedSharePageList")
    public String getCollectedSharePageList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.findUCollectionStoreList(user.getWeiID(), Limit.buildLimit(pageIndex, pageSize));
	return JsonUtil.objectToJsonStr(rm);
    }

    

	@RequestMapping("/forwardSharePage")
	public String getShareUrl1(Long pageId,Long shareOne) throws MapperException{
		LoginUser user = getUserOrSub();
		ReturnModel rm = shareSvervice.getShareUrl1(pageId,shareOne,user);
		return JsonUtil.objectToJsonStr(rm);
	}
    
    
    
    /**
     * 对分享 点赞
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/likeSharePage")
    public String likeSharePage(long pageId, Long shareOne) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.updateLikeSharePage(user.getWeiID(), pageId, shareOne);
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取 首页分享列表
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/homeSharePage")
    public String homeSharePage(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.findHotShareList(user.getWeiID(), Limit.buildLimit(pageIndex, pageSize));
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取 热门分享列表
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/hotSharePage")
    public String findHotShareList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.findHotShareList(user.getWeiID(), Limit.buildLimit(pageIndex, pageSize));
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取 我的分享列表
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/mySharePage")
    public String findMyShareList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize, Long shareOne) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	if (shareOne == null || shareOne == 0) {
	    rm = shareSvervice.findMyShareList(user.getWeiID(), Limit.buildLimit(pageIndex, pageSize));
	} else {
	    rm = shareSvervice.findMyShareList(shareOne, Limit.buildLimit(pageIndex, pageSize));
	}
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取 我的分享详情
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/getSharePageDetail")
    public String getSharePageDetail(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize, Integer pageId, Long shareOne) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	if (shareOne==null) {
		shareOne=user.getWeiID();
	}
	rm = shareSvervice.GetShareDetails(user.getWeiID(), shareOne, pageId, Limit.buildLimit(pageIndex, pageSize));
	return JsonUtil.objectToJsonStr(rm);
    }

    @RequestMapping("/getSharePageProductList")
    public String getSharePageProductList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize, Integer pageId) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.findProductList(user.getWeiID(), pageId, Limit.buildLimit(pageIndex, pageSize));
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取 删除分享信息
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/deleteSharePage")
    public String deleteSharePage(Integer pageId, long shareOne) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.updateSharePage(user.getWeiID(), shareOne, pageId);
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 收藏分享页 取消分享页
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/collectSharePage")
    public String collectSharePage(@RequestParam(required = false, defaultValue = "0") Long pageId, @RequestParam(required = false, defaultValue = "") String key) throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	rm = shareSvervice.addcollectSharePage(user.getWeiID(), pageId, key);
	return JsonUtil.objectToJsonStr(rm);
    }

    /**
     * 获取分享提醒
     * 
     * @return
     * @throws MapperException
     */
    @RequestMapping("/getShareRemind")
    public String getShareRemind() throws MapperException {
	ReturnModel rm = new ReturnModel();
	LoginUser user = getUser();
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆已过期，请重新登陆");
	    return JsonUtil.objectToJsonStr(rm);
	}
	String remind = AppSettingUtil.getSingleValue("ShareReminders");
	if (remind != "") {
	    rm.setStatu(ReturnStatus.Success);
	    rm.setStatusreson(remind);
	} else {
	    rm.setStatu(ReturnStatus.DataError);
	    rm.setStatusreson("没有提醒");
	}
	return JsonUtil.objectToJsonStr(rm);
    }
}
