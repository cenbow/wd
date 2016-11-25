package com.okwei.myportal.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.bean.vo.LoginUser;
import com.okwei.common.AjaxUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ShopFaceDTO;
import com.okwei.myportal.service.IShopFaceMgtService;
import com.okwei.web.base.SSOController;

@RequestMapping(value = "/myShopMgt")
@Controller
public class ShopFaceMgtController extends SSOController {

    private static int pageSize = 10;

    @Autowired
    private IShopFaceMgtService shopFaceMgtService;

    /**
     * 我的店铺首页推荐设置
     */
    @RequestMapping(value = "/homepage")
    public String list(@ModelAttribute("dto") ShopFaceDTO dto, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	LoginUser user = super.getUserOrSub();
	dto.setWeiId(user.getWeiID());

	PageResult<PcUserAdnotice> pageResult = shopFaceMgtService.getShopFaceImgs(dto, Limit.buildLimit(0, pageSize));
	model.addAttribute("pageResult", pageResult);
	model.addAttribute("userinfo", user);
	return "shopmgt/imgList";
    }

    @RequestMapping(value = "/popup/add/{flag}/{reId}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("flag") byte flag, @PathVariable("reId") Integer reId, Model model) {
	LoginUser user = super.getUserOrSub();
	if (flag == 2) {// 修改
	    PcUserAdnotice pcRecommend = shopFaceMgtService.getById(PcUserAdnotice.class, reId);
	    if (pcRecommend.getWeiId().equals(user.getWeiID())) {
		model.addAttribute("pcRecommend", pcRecommend);
	    }
	}
	model.addAttribute("flag", flag);
	return "shopmgt/popup";
    }

    @ResponseBody
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public String doAdd(PcUserAdnotice pcRecommend, Byte flag, Model model) {
	boolean result = false;
	LoginUser user = super.getUserOrSub();
	if (pcRecommend.getStatus() == 2) {
	    Integer count = shopFaceMgtService.getOpenCount(user.getWeiID());
	    if (null != count && count >= 5) {
		return AjaxUtil.ajaxFail("开启数量大于5");
	    }
	}

	if (null != flag && flag == 2) {// 修改
	    PcUserAdnotice pcr = shopFaceMgtService.getById(PcUserAdnotice.class, pcRecommend.getUadId());
	    if (null != pcr && pcr.getWeiId().equals(user.getWeiID())) {
		pcr.setTitle(pcRecommend.getTitle());
		pcr.setImgLog(pcRecommend.getImgLog());
		pcr.setSort(pcRecommend.getSort());
		pcr.setUrl(pcRecommend.getUrl());
		pcr.setStatus(pcRecommend.getStatus());
		result = shopFaceMgtService.update(pcr);
	    }
	} else {// 添加
	    pcRecommend.setWeiId(user.getWeiID());
	    pcRecommend.setCreateTime(new Date());
	    result = shopFaceMgtService.add(pcRecommend);
	}
	return result ? AjaxUtil.ajaxSuccess("操作成功") : AjaxUtil.ajaxFail(("操作失败"));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteImg/{id}", method = RequestMethod.POST)
    public String deleteImg(@PathVariable int id, Model model) {
	boolean result = false;
	LoginUser user = super.getUserOrSub();
	PcUserAdnotice pcRecommend = shopFaceMgtService.getById(PcUserAdnotice.class, id);
	if (null != pcRecommend && pcRecommend.getWeiId().equals(user.getWeiID())) {// 只能删除当前用户本人的
	    result = shopFaceMgtService.delete(pcRecommend);
	}
	return result ? AjaxUtil.ajaxSuccess("删除成功") : AjaxUtil.ajaxFail(("删除失败"));
    }

    @ResponseBody
    @RequestMapping(value = "/openImg/{id}", method = RequestMethod.POST)
    public String openImg(@PathVariable int id, Model model) {
	boolean result = false;
	LoginUser user = super.getUserOrSub();
	Integer count = shopFaceMgtService.getOpenCount(user.getWeiID());
	if (null != count && count >= 5) {
	    return AjaxUtil.ajaxFail("开启数量大于5");
	}

	PcUserAdnotice pcRecommend = shopFaceMgtService.getById(PcUserAdnotice.class, id);
	if (null != pcRecommend && pcRecommend.getWeiId().equals(user.getWeiID())) {// 只能开启当前用户本人的
	    pcRecommend.setStatus((short) 2);
	    result = shopFaceMgtService.update(pcRecommend);
	}
	return result ? AjaxUtil.ajaxSuccess("开启成功") : AjaxUtil.ajaxFail(("开启失败"));
    }

    @ResponseBody
    @RequestMapping(value = "/closeImg/{id}", method = RequestMethod.POST)
    public String closeImgImg(@PathVariable int id, Model model) {
	boolean result = false;
	LoginUser user = super.getUserOrSub();
	PcUserAdnotice pcRecommend = shopFaceMgtService.getById(PcUserAdnotice.class, id);
	if (null != pcRecommend && pcRecommend.getWeiId().equals(user.getWeiID())) {// 只能开启当前用户本人的
	    pcRecommend.setStatus((short) 3);
	    result = shopFaceMgtService.update(pcRecommend);
	}
	return result ? AjaxUtil.ajaxSuccess("关闭成功") : AjaxUtil.ajaxFail(("关闭失败"));
    }
}
