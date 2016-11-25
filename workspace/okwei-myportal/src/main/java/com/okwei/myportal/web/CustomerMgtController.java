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

import com.okwei.bean.domain.PcRecommend;
import com.okwei.bean.domain.WebUserAdnotice;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.MyAgentOrProductShopListVO;
import com.okwei.bean.vo.PlatformProductShopListPCVO;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ProductAgentDataVO;
import com.okwei.bean.vo.ProductShopDataVO;
import com.okwei.common.AjaxUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ShopFaceDTO; 
import com.okwei.myportal.service.IShopFaceMgtService;
import com.okwei.myportal.service.ITest;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.web.base.SSOController;

@RequestMapping(value = "/myCustomerMgt")
@Controller
public class CustomerMgtController extends SSOController {

	private static int pageSize = 10;

	@Autowired
	private IBasicAgentOrProductShopService iBasicAgentOrProductShopService;
	
	@Autowired
	private ITest test;

	/**
	 * 我跟进的代理商列表
	 */
	@RequestMapping(value = "/agentPage")
	public String agentList(@ModelAttribute("dto") MyAgentOrProductShopListDTO dto, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
		LoginUser user = super.getUserOrSub();
//		 测试环境用
//		LoginUser user=test.getLoginUser();
		//模拟查询条件
		dto.setVerifierWeiId(user.getWeiID());
		PageResult<MyAgentOrProductShopListVO> pageResult = iBasicAgentOrProductShopService.getMyDevelopAgent(Limit.buildLimit(pageId, pageSize),dto);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("userinfo", user);
		return "customermgt/agentList";
	}
	/**
	 * 我跟进的落地店列表
	 * @param dto
	 * @param pageId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inloadPage")
	public String inloadList(@ModelAttribute("dto") MyAgentOrProductShopListDTO dto, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
		LoginUser user = super.getUserOrSub();
//		// 测试环境用
//		LoginUser user=test.getLoginUser();
		dto.setVerifierWeiId(user.getWeiID());
		dto.setAuditState((short)1);
		PageResult<PlatformProductShopListPCVO> pageResult = iBasicAgentOrProductShopService.getMyDevelopProductShop(Limit.buildLimit(pageId, pageSize),dto);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("userinfo", user);
		return "customermgt/inloadList";
	}
	
	@RequestMapping(value="/queryData/{id}")
	public String queryData(@PathVariable Integer id, Model model)
	{
		ProductShopDataVO pv=iBasicAgentOrProductShopService.getProductShopDataVO(id);
		model.addAttribute("data", pv);
		return "customermgt/querypopup";
	}
	@RequestMapping(value="/showData/{id}")
	public String showData(@PathVariable String id, Model model)
	{
		Integer agentid=0;
		boolean b= false;
		if(id.startsWith("1_"))//查看资料
		{
			b=true;
			agentid=Integer.parseInt(id.replace("1_", ""));
		}
		else //跟进记录
		{
			agentid=Integer.parseInt(id.replace("2_", ""));
		}
		
		ProductAgentDataVO pv=iBasicAgentOrProductShopService.getProductAgentDataVO(agentid, 1);
		if(b)
		{
			pv.setFollow(0);;
		}
		else
		{
			pv.setFollow(1);
		}
		model.addAttribute("data", pv);
		return "customermgt/showdata";
	}
	
	@ResponseBody
    @RequestMapping(value = "/saveFollowMsg",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String saveFollowMsg(String msg,Integer agentid)
    {
		LoginUser user = super.getUserOrSub();
//		// 测试环境用
//		LoginUser user=test.getUserOrSub();
        String returnData = "保存成功！";
        if(msg == null || msg == "")
        {
            return AjaxUtil.ajaxSuccess("参数有误!");
        }
        long weiid =user.getWeiID();
        iBasicAgentOrProductShopService.followAgent(agentid, msg, weiid);
        
        return AjaxUtil.ajaxSuccess(returnData);
    }
}
