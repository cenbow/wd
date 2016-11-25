package com.okwei.walletportal.web;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UPublicBanks;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.SupplierStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.common.AjaxUtil;
import com.okwei.service.IRegionService;
import com.okwei.util.ImgDomain;
import com.okwei.walletportal.bean.enums.PublicBanksStatusEnum;
import com.okwei.walletportal.bean.vo.BaseSSOController;
import com.okwei.walletportal.service.ICompanyAccountService;

 

@Controller
@RequestMapping(value = "/companyAccount")
public class CompanyAccountController extends BaseSSOController{
	private final static Log logger = LogFactory.getLog(CompanyAccountController.class);
	
	@Autowired
	private ICompanyAccountService companyAccountService;
	@Autowired
    private IRegionService tBatchMarket;
	
	@RequestMapping(value = "/info")
	public String info(Model model) {
		LoginUser user = super.getLoginUser();
		UPublicBanks accountInfo = null;
		String banckArea = "";
		try {
			accountInfo = companyAccountService.getCompanyAccount(user.getWeiID());
			if (accountInfo != null) {
				//地区
				StringBuffer area = new StringBuffer();
	            area.append(tBatchMarket.getNameByCode(accountInfo.getProvince()==null?0:accountInfo.getProvince()));
	            area.append(tBatchMarket.getNameByCode(accountInfo.getCity()==null?0:accountInfo.getCity()));
	            banckArea = area.toString();
	            //对公账号
	            if (StringUtils.isNotEmpty(accountInfo.getBanckNo())) {
	            	accountInfo.setBanckNo("****************"+accountInfo.getBanckNo().substring(accountInfo.getBanckNo().length()-4));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("accountInfo", accountInfo);
		model.addAttribute("userinfo", user);
		model.addAttribute("banckArea", banckArea);
		return "company/accountInfo";
	}
	
	@RequestMapping(value = "/addAcount")
	public String addAcount(Model model) {
		LoginUser user = super.getLoginUser();
		String businessLicenceImg = "";
		UPublicBanks accountInfo = null;
		boolean isPassed = false;//对公账户审核通过修改
		boolean isSupImg = false;//供应商营业执照已有
		try {
			accountInfo = companyAccountService.getCompanyAccount(user.getWeiID());
			//编辑
			if (accountInfo != null) {
				if (StringUtils.isNotEmpty(accountInfo.getLicense())) {
					businessLicenceImg = ImgDomain.GetFullImgUrl(accountInfo.getLicense());
				}
				isPassed = companyAccountService.getPublicBanksPassCount(user.getWeiID());
			} 
			//新增
			else {
				if (user.getYunS() == 1 || user.getBatchS() == 1) {
					UYunSupplier yunsupplyer = companyAccountService.getById(UYunSupplier.class, user.getWeiID());
					if (yunsupplyer != null) {
						if (Short.valueOf(SupplierStatusEnum.PayIn.toString()).equals(yunsupplyer.getStatus())) {
							USupplyer supplyer = companyAccountService.getById(USupplyer.class, user.getWeiID());
							if (supplyer != null) {
								if (StringUtils.isNotEmpty(supplyer.getBusinessLicence())) {
									businessLicenceImg = ImgDomain.GetFullImgUrl(supplyer.getBusinessLicence());
									accountInfo = new UPublicBanks();
									accountInfo.setLicense(supplyer.getBusinessLicence());
									isSupImg = true;
								}
							}
						}
					}
				} else {
					return "company/accountInfo";
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("accountInfo", accountInfo);
		model.addAttribute("businessLicenceImg", businessLicenceImg);
		model.addAttribute("userinfo", user);
		model.addAttribute("isPassed", isPassed);
		model.addAttribute("isSupImg", isSupImg);
		return "company/addAcount";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addOrUpdateCompanyAccount(UPublicBanks bc, Model model) {
		LoginUser user = super.getLoginUser();
		if (bc == null) {
			bc = new UPublicBanks();
		}
		try {
			boolean flag = false;
			UPublicBanks accountInfo = companyAccountService.getCompanyAccount(user.getWeiID());
			if (accountInfo != null) {
				flag = true;
				bc.setPid(accountInfo.getPid());
				bc.setProcessTime(accountInfo.getProcessTime());
				//对公账号已是申请中， 返回提示
				if (Short.valueOf(PublicBanksStatusEnum.Applying.toString()).equals(accountInfo.getState())) {
					return AjaxUtil.ajaxFail("您的对公账号已经在申请中，不能重复添加!");
				}
				else if (Short.valueOf(PublicBanksStatusEnum.Pass.toString()).equals(accountInfo.getState())) {
					bc.setState(Short.valueOf(PublicBanksStatusEnum.Pass.toString()));
				} else {
					bc.setState(Short.valueOf(PublicBanksStatusEnum.Applying.toString()));
				}
			}
			String msg = companyAccountService.addOrUpdateCompanyAccount(bc,user,flag);
			return AjaxUtil.ajaxSuccess(msg);
		} catch (Exception e) {
			logger.error("保存对公账号出错："+e.getMessage());
			return AjaxUtil.ajaxFail("操作失败");
		}
	}
	
}
