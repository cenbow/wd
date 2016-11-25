package com.okwei.company.web;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.dto.TasteApplyDTO;
import com.okwei.bean.enums.agent.BaseResultStateEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.agent.BaseResultVO;
import com.okwei.bean.vo.agent.HalfProductVO;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.common.AjaxUtil;
import com.okwei.common.JsonUtil;
import com.okwei.service.IAgentCommonService;
import com.okwei.service.address.IBasicAdressService;
import com.okwei.service.agent.IAgentBrandService;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.StringHelp;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/free")
public class TasteController  extends SSOController {
	
	@Autowired
	private IAgentBrandService agent;
	@Autowired
    private IAgentCommonService commonService;
	
	@Autowired
    private IBasicAdressService basicAdressService;
//	@Autowired
//	private ITest test;
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(Model model)
	{
		LoginUser user = getUserOrSub();
//		LoginUser user=test.getLoginUser();
		model.addAttribute("user", user);
		int num=agent.getTasteCount();
		model.addAttribute("num", num);
		return "free/index";
	}
	@RequestMapping(value = "/taste", method = { RequestMethod.GET })
	public String taste(Model model)
	{
		LoginUser user = getUserOrSub();
		//LoginUser user=test.getLoginUser();
		model.addAttribute("user", user);
		return "free/taste";
	}
	@RequestMapping(value = "/halftaste", method = { RequestMethod.GET })
	public String halftaste(Model model,String addressId)
	{
		LoginUser user = getUserOrSub();
		//LoginUser user=test.getLoginUser();
		HalfProductVO hp=commonService.getProductInfo();
		List<BAddressVO> list = basicAdressService.getBAddressList(user.getWeiID());
		//商品寄送到的地址
		BAddressVO vo =null;
		if(list!=null&&list.size()>0){
			if (!ObjectUtil.isEmpty(addressId)) {
				for (BAddressVO ad : list) {
					if(ad.getAddressId().equals(addressId))
						vo=ad;
				}
			} 
			if (vo == null) {
				for (BAddressVO ad : list) {
					if (ad.getIsDefault() == 1) {
						vo = ad;
						if(ObjectUtil.isEmpty(addressId)){
							addressId = ad.getAddressId();
						}
					}
				}
			}
		}
		model.addAttribute("list", list);//收货地址清单
		model.addAttribute("product", hp);
		model.addAttribute("user", user);
		model.addAttribute("address", vo);//寄送的地址
        model.addAttribute("addrId", addressId);//选择的收货地址
		return "free/halftaste";
	}
	@ResponseBody
	@RequestMapping(value = "/CheckTaste", method = { RequestMethod.POST, RequestMethod.GET })
	public String checktaste(long weiid,short type)
	{
		LoginUser user = getUserOrSub();
		//LoginUser user=test.getLoginUser();
		if (user == null || user.getWeiID() < 1) {
			return JsonUtil.objectToJson("2");// 登录已过期,请重新登录！
		}
		if(type==1)//免费领取
		{
			int num=agent.getTasteCount();
			if(num>=10000)
			{
				return JsonUtil.objectToJson("3");
			}
		}
		boolean b=agent.checkTaste(weiid, type);
		if(b)
			return JsonUtil.objectToJson("-1"); //已领取
		else
			return AjaxUtil.ajaxSuccess("1");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/halfbuy", method = { RequestMethod.POST, RequestMethod.GET })
	public String halfBuy(long weiid,int addrid,int num)
	{
		BaseResultVO resultVO = new BaseResultVO();
		resultVO.setState(BaseResultStateEnum.Failure);
		resultVO.setMessage("参数异常！");
		LoginUser user = getUserOrSub();
		//LoginUser user=test.getLoginUser();
		if (user == null || user.getWeiID() < 1) {
			return JsonUtil.objectToJson(resultVO);// 登录已过期,请重新登录！
		}
		
		ReturnModel rm =commonService.halfBuy(weiid, addrid, num);
		resultVO.setMessage(rm.getStatusreson());
		if(rm.getStatu() == ReturnStatus.Success)
		{
			resultVO.setState(BaseResultStateEnum.Success);
		}
		
		
		return JsonUtil.objectToJson(resultVO);
	}
	 /**
     * 获取图片全路径
     * 
     * @param img
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fullImgUrl",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getFullImgUrl(String img)
    {
        String imgUrl = "";
        if(!StringHelp.IsNullOrEmpty(img))
        {
            imgUrl = ImgDomain.GetFullImgUrl(img);
        }
        return AjaxUtil.ajaxSuccess(imgUrl);
    }
	
    
    /**
	 * 处理提交代理请求 created by zlp at 2016/10/11
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveTasteApply")
	public String saveTasteApply( @ModelAttribute("ta") TasteApplyDTO ta) throws Exception{
		//ReturnModel rq=new ReturnModel();
		ReturnModel rq=agent.saveTasteApply(ta);
		return JsonUtil.objectToJson(rq);
	}
		
	/**
     * 设为默认
     */
    @ResponseBody
    @RequestMapping(value = "/setDefaultAddr", method = { RequestMethod.POST, RequestMethod.GET })
    public String setDefaultAddr(int caddrId,long weiid) {
	if (caddrId <= 0) {
	    return AjaxUtil.ajaxSuccess("地址ID获取失败");
	}
	String result = "";
	try {
	    if (basicAdressService.setDefault(weiid, caddrId) > 0) {
		result = "1";
	    } else {
		result = "设为地址操作失败";
	    }
	} catch (Exception e) {
	    result = "设为地址操作失败";
	}
	return AjaxUtil.ajaxSuccess(result);
    }
    
    /**
     * 保存收货地址
     */
    @ResponseBody
    @RequestMapping(value = "/saveAddress",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String submitApply(UCustomerAddr entity)
    {
        // 验证
        String check = checkAddrEntity(entity);
        if(check != "ok")
        {
            return AjaxUtil.ajaxSuccess(check);
        }
        String result = "";
        try
        {
            
            if(basicAdressService.saveOrUpdateAdd(entity) > 0)
            {
                result = "1";
            }
            else
            {
                result = "保存收货地址失败";
            }
        }
        catch(Exception e)
        {
            result = "保存收货地址失败";
        }
        return AjaxUtil.ajaxSuccess(result);
    }
    
    /**
     * 删除地址
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddr", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteAddr(int caddrId,long weiid) {
		if (caddrId <= 0) {
			return AjaxUtil.ajaxSuccess("地址ID获取失败");
		}
		String result = "";
		try {
			if (basicAdressService.deleteAddress(weiid, caddrId) > 0) {
				result = "1";
			} else {
				result = "删除失败";
			}
		} catch (Exception e) {
			result = "删除失败";
		}
		return AjaxUtil.ajaxSuccess(result);
	}
    
    private String checkAddrEntity(UCustomerAddr entity)
    {
        if(StringUtils.isEmpty(entity.getMobilePhone()))
        {
            return "手机不能为空";
        }
        else
        {
            if(!Pattern.matches("^[1][3,4,5,7,8][0-9]{9}$",entity.getMobilePhone()))
            {
                return "手机格式错误";
            }
        }
        if(StringUtils.isEmpty(entity.getReceiverName()))
        {
            return "收货人不能为空";
        }
        if(entity.getProvince() == null || entity.getProvince().intValue() == 0)
        {
            return "请选择省";
        }
        if(entity.getCity() == null || entity.getCity().intValue() == 0)
        {
            return "请选择市";
        }
        if(entity.getDistrict() == null || entity.getDistrict().intValue() == 0)
        {
            return "请选择区";
        }
        if(StringUtils.isEmpty(entity.getDetailAddr()))
        {
            return "详细地址不能为空";
        }
        return "ok";
    }
	
}
