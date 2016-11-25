package com.okwei.myportal.web;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.bean.dto.share.SMainDataDTO;
import com.okwei.bean.dto.share.SMainDataDTOs;
import com.okwei.bean.enums.MainDataUserType;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.share.MainShare;
import com.okwei.bean.vo.share.SMainDataDetailsVO;
import com.okwei.bean.vo.share.ShareProduct;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.CountMainDataVO;
import com.okwei.myportal.bean.vo.CountShareDetailVO;
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.bean.vo.SMainDataVO;
import com.okwei.myportal.service.IShareService;
import com.okwei.myportal.service.ITest;
import com.okwei.service.share.IBasicShareServices;
import com.okwei.service.share.IShareAddService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.QRCodeUtil;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/share")
public class ShareController extends SSOController {

    @Autowired
    private IShareService shareService;

    @Autowired
    private IBasicShareServices basicShareServices;

    @Autowired
    private ITest itest;

    @RequestMapping(value = "/url")
    public void shareWapUrl(HttpServletResponse response, long share, Model model) {
	LoginUser user = super.getLoginUser();
	model.addAttribute("userinfo", user);
	try {
	    // TODO
	    BufferedImage bufImg = QRCodeUtil.qRCodeCommon("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/v5/share/sharedetails?shareId=" + share + "&w=" + user.getWeiID(), 8);
	    // 生成二维码QRCode图片
	    ImageIO.write(bufImg, "jpg", response.getOutputStream());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 分享列表
     */
    @RequestMapping(value = "/sharelist")
    public String sharelist(@ModelAttribute("queryParam") SMainDataDTO dto, @RequestParam(required = false, defaultValue = "-1") int pageId, Model model) {
	LoginUser user = super.getLoginUser();
	model.addAttribute("userinfo", user);
	PageResult<SMainDataVO> findSMainDataList = shareService.findSMainDataList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));
	model.addAttribute("pageResult", findSMainDataList);
	model.addAttribute("dto", dto);
	return "share/sharelist";
    }

    /**
     * 统计分享列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sharecount")
    public String sharelist1(@ModelAttribute("dto") SMainDataDTOs dto, @RequestParam(required = false, defaultValue = "-1") int pageId, Model model) {
		LoginUser user = super.getLoginUser();
		model.addAttribute("userinfo", user);
		Map<String, Object> count_Statics = null;
		count_Statics = (Map<String, Object>) RedisUtil.getObject(user.getWeiID() + "TimeTyp=" + dto.getDateTimeType() + "Title=" + dto.getTitle() + "pageId=" + pageId);
		if (count_Statics == null) {
		    count_Statics = shareService.count_Statics(user.getWeiID(), dto);
		    RedisUtil.setObject(user.getWeiID() + "TimeTyp=" + dto.getDateTimeType() + "Title=" + dto.getTitle() + "pageId=" + pageId, count_Statics, 10);
		}
		for (Entry<String, Object> map : count_Statics.entrySet()) {
		    model.addAttribute(map.getKey(), map.getValue());
		}
		PageResult<CountMainDataVO> findCountMainDataList = null;
		findCountMainDataList = (PageResult<CountMainDataVO>) RedisUtil.getObject("MainData=" + user.getWeiID() + "TimeTyp=" + dto.getDateTimeType() + "Title=" + dto.getTitle() + "pageId=" + pageId);
		if (findCountMainDataList == null) {
		    findCountMainDataList = shareService.findCountMainDataList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));
		    RedisUtil.setObject("MainData=" + user.getWeiID() + "TimeTyp=" + dto.getDateTimeType() + "Title=" + dto.getTitle() + "pageId=" + pageId, findCountMainDataList, 10);
		}
		model.addAttribute("pageResult", findCountMainDataList);
		model.addAttribute("dto", dto);
		return "share/sharecount";
    }

    /**
     * 统计明细
     */
    @RequestMapping(value = "/countdetails")
    public String sharelist1(@RequestParam(required = false, defaultValue = "1") int pageId, long shareId, Model model) {
	LoginUser user = super.getLoginUser();
	CountShareDetailVO countSharedetails = null;
	countSharedetails = (CountShareDetailVO) RedisUtil.getObject("countdetails" + user.getWeiID() + "shareId=" + shareId + "pageId=" + pageId);
	if (countSharedetails == null) {
	    countSharedetails = shareService.countSharedetails(user.getWeiID(), shareId, Limit.buildLimit(pageId, 10));
	    RedisUtil.setObject("countdetails" + user.getWeiID() + "shareId=" + shareId + "pageId=" + pageId, countSharedetails, 150);
	}
	model.addAttribute("userinfo", user);
	model.addAttribute("countSharedetails", countSharedetails);
	model.addAttribute("shareId", shareId);
	if (countSharedetails != null && countSharedetails.getProductsList() != null) {
	    model.addAttribute("pageResult", countSharedetails.getProductsList());
	} else {
	    model.addAttribute("pageResult", null);
	}
	return "share/countsharedetails";
    }

    /**
     * 添加分享次数
     * 
     * @param shareId
     *            分享Id
     * @param shareOne
     *            分享人WeiId
     * @param makeWeiID
     *            制作人weiId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addShareCount")
    public String addShareCount(long shareId, Model model) {
	LoginUser user = super.getLoginUser();
	MsgResult mr = new MsgResult();
	if (shareId <= 0) {
	    mr.setMsg("提交的数据有误！");
	    mr.setState(-1);
	} else {
	    basicShareServices.addShareCount(user.getWeiID(), shareId, 1);
	}
	return JsonUtil.objectToJson(mr);
    }

    /**
     * 删除分享信息
     * 
     * @param shareId
     *            分享Id
     * @param shareOne
     *            分享人WeiId
     * @param makeWeiID
     *            制作人weiId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteShare")
    public String updateShare(long shareId, long makeWeiID, long shareOne, Model model) {
	LoginUser user = super.getLoginUser();
	MsgResult mr = new MsgResult();
	if (shareId <= 0 || shareOne < 1) {
	    mr.setMsg("提交的数据格式不正确");
	    mr.setState(-1);
	} else {
	    ReturnModel updateSMainData = basicShareServices.updateSMainData(user.getWeiID(), shareOne, shareId);
	    if (ParseHelper.toInt(updateSMainData.getStatu().toString()) == 1) {
		mr.setState(1);
	    } else {
		mr.setState(-1);
	    }
	    mr.setMsg(updateSMainData.getStatusreson());
	}
	return JsonUtil.objectToJson(mr);
    }

    /**
     * 分享详情
     * 
     * @param shareId
     * @param pageId
     * @param model
     * @return
     */
    @RequestMapping(value = "/sharedetails")
    public String sharedetails(Integer shareId, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	LoginUser user = super.getLoginUser();
	model.addAttribute("userinfo", user);
	if (shareId != null && shareId > 0) {
	    SMainDataDetailsVO sMainDataDetails = basicShareServices.getSMainDataDetails(shareId, user.getWeiID(), Limit.buildLimit(pageId, 10), 1);
	    model.addAttribute("sMainDataDetails", sMainDataDetails);
	    model.addAttribute("shareId", shareId);
	}
	return "share/sharedetails";
    }

    @Autowired
    private IShareAddService shareAddService;

    @RequestMapping(value = "/add")
    public String add(@RequestParam(required = false, defaultValue = "0") Long shareid, Model model) {
	LoginUser user = super.getLoginUser();
	List<PShopClass> claList = shareAddService.getShopClasses(user.getWeiID());
	MainShare share = shareAddService.getMainShare(user.getWeiID(), shareid);
	model.addAttribute("claList", claList);
	model.addAttribute("share", share);
	model.addAttribute("userinfo", user);
	return "share/add";
    }

    @ResponseBody
    @RequestMapping(value = "/count")
    public String getShareProCount(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "0") short type, @RequestParam(required = false, defaultValue = "0") int sid, @RequestParam(required = false, defaultValue = "") String ids) {
	LoginUser user = super.getLoginUser();
	Long[] sids = null;
	try {
	    if (!"".equals(ids)) {
		String[] strs = ids.split(",");
		sids = new Long[strs.length];
		for (int i = 0; i < strs.length; i++) {
		    sids[i] = Long.parseLong(strs[i]);
		}
	    }
	} catch (Exception e) {

	}
	Long count = shareAddService.getShareProCount(user.getWeiID(), title, type, sids, sid);
	return String.valueOf(count);
    }

    @ResponseBody
    @RequestMapping(value = "/product")
    public String getproduct(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "0") short type, @RequestParam(required = false, defaultValue = "0") int sid, @RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "") String ids) throws MapperException {
	LoginUser user = super.getLoginUser();
	Long[] sids = null;
	try {
	    if (!"".equals(ids)) {
		String[] strs = ids.split(",");
		sids = new Long[strs.length];
		for (int i = 0; i < strs.length; i++) {
		    sids[i] = Long.parseLong(strs[i]);
		}
	    }
	} catch (Exception e) {

	}
	List<ShareProduct> list = shareAddService.getShareProList(user.getWeiID(), title, type, sids, Limit.buildLimit(index, 10), sid);
	return JsonUtil.objectToJsonStr(list);
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/addshare")
    public String addshare(@RequestParam(required = false, defaultValue = "1") int shareType,
	    @RequestParam(required = false, defaultValue = "0") Long shareid,
	    @RequestParam(required = false, defaultValue = "") String title,
	    @RequestParam(required = false, defaultValue = "") String des,
	    @RequestParam(required = false, defaultValue = "") String prolist,
	    @RequestParam(required = false, defaultValue = "") String imglist) throws MapperException {
	LoginUser user = super.getLoginUser();
	ReturnModel result = new ReturnModel();
	result.setStatu(ReturnStatus.DataError);
	if ("".equals(title)) {
	    result.setStatusreson("分享标题不能为空！");
	    return JsonUtil.objectToJsonStr(result);
	}
	if ("".equals(des)) {
	    result.setStatusreson("分享推广语不能为空！");
	    return JsonUtil.objectToJsonStr(result);
	}
	List<SProducts> list = null;
	if (!"".equals(prolist)) {
		list = (List<SProducts>) JsonUtil.json2Objectlist(prolist, SProducts.class);
	}
	if (list == null || list.size() <= 0) {
	    result.setStatusreson("请选择要分享的产品！");
	    return JsonUtil.objectToJsonStr(result);
	}
	List<SSingleDesc> singlist = null;
	if (shareType == Integer.parseInt(ShareTypeEnum.SingleQuality.toString())) {
	    if (list.size() != 1) {
		result.setStatusreson("只能选择一个商品！");
		return JsonUtil.objectToJsonStr(result);
	    }
	    singlist = (List<SSingleDesc>) JsonUtil.json2Objectlist(imglist, SSingleDesc.class);
	} else {
	    if (list.size() < 5 || list.size() > 100) {
		result.setStatusreson("分享的产品数量需在5到100个之间！");
		return JsonUtil.objectToJsonStr(result);
	    }
	}
	MainDataUserType type = MainDataUserType.user;
	if (shareAddService.getIsSupper(user.getIdentity()))
	    type = MainDataUserType.supplier;
	result = shareAddService.addShare(user.getWeiID(), shareid, title, des, list, type, null, shareType,singlist);
	return JsonUtil.objectToJsonStr(result);
    }

    @RequestMapping(value = "/view")
    public String shareview(@RequestParam(required = false, defaultValue = "") String shareType, @RequestParam(required = false, defaultValue = "") String formTitle, @RequestParam(required = false, defaultValue = "") String formDes, @RequestParam(required = false, defaultValue = "") String formList,@RequestParam(required = false, defaultValue = "")String imgList, Model model) {
	LoginUser user = super.getLoginUser();
	model.addAttribute("title", formTitle);
	model.addAttribute("des", formDes);
	model.addAttribute("shareType", shareType);
	List<ProductInfo> list = null;
	List<SSingleDesc> singList = null;
	if("2".equals(shareType)){
	    if (!"".equals(imgList)) {
	        try {
	    	    singList = (List<SSingleDesc>) JsonUtil.json2Objectlist(imgList, SSingleDesc.class);
	        } catch (Exception e) {

	        }
	    }
	}
	if (!"".equals(formList)) {
	    try {
		list = (List<ProductInfo>) JsonUtil.json2Objectlist(formList, ProductInfo.class);
	    } catch (Exception e) {
	    }
	}
	model.addAttribute("list", list);
	model.addAttribute("singList", singList);
	model.addAttribute("userinfo", user);
	return "share/view";
    }

}
