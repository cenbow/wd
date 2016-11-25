package com.okwei.walletportal.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UCancleOrderAmoutDetail;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.vo.LoginUser;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.util.ObjectUtil;
import com.okwei.walletportal.bean.dto.WalletDTO;
import com.okwei.walletportal.bean.enums.BaseResultState;
import com.okwei.walletportal.bean.vo.ApplyBondInfo;
import com.okwei.walletportal.bean.vo.BankCardVO;
import com.okwei.walletportal.bean.vo.BaseResultVO;
import com.okwei.walletportal.bean.vo.BaseSSOController;
import com.okwei.walletportal.bean.vo.SettleAccountDetailVO;
import com.okwei.walletportal.bean.vo.VerifierInfo;
import com.okwei.walletportal.service.IApplyBondService;
import com.okwei.walletportal.service.ICompanyAccountService;
import com.okwei.walletportal.service.ITest;
import com.okwei.walletportal.service.IWalletMgtService;

 

@Controller
@RequestMapping(value = "/walletMgt")
public class WalletMgtController extends BaseSSOController {

    private final static Log logger = LogFactory.getLog(WalletMgtController.class);
    @Autowired
    private IWalletMgtService walletMgtService;
    @Autowired
	private ICompanyAccountService companyAccountService;
    @Autowired
	private ITest itest;
    

    @RequestMapping(value = "/index")
    public String index(@ModelAttribute("queryParam") WalletDTO queryParam, @RequestParam(required = false, defaultValue = "1") int pageId, Model model, @RequestParam(required = false, defaultValue = "0") int detailType, HttpServletRequest request) {
	logger.info("WalletMgtController index method starting ......");
	LoginUser user = super.getLoginUser();
	PageResult<UWalletDetails> pageResult = walletMgtService.getMyWalletDetailPage(user.getWeiID(), Limit.buildLimit(pageId, 10), detailType, queryParam.getBeginTime(), queryParam.getEndTime());
	model.addAttribute("pageResult", pageResult);
	model.addAttribute("type", 1); // 1.控制"全部"表格的显示
	model.addAttribute("action", request.getRequestURI());
	model.addAttribute("userinfo", user);	
	return "index";
    }

    /**
     * 查询收支明细
     * 
     * @param type
     * @param pageId
     * @param staTime
     * @param endTime
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeDetailType/{type}")
    public String changeDetailType(@ModelAttribute("queryParam") WalletDTO queryParam, @PathVariable("type") Integer type, @RequestParam(required = false, defaultValue = "1") int pageId, Model model, HttpServletRequest request) {
	logger.info("WalletMgtController index method starting ......");
	LoginUser user = super.getLoginUser();
	PageResult<UWalletDetails> pageResult = walletMgtService.getMyWalletDetailPage(user.getWeiID(), Limit.buildLimit(pageId, 10), type, queryParam.getBeginTime(), queryParam.getEndTime());
	model.addAttribute("pageResult", pageResult);
	if (type == 1) {
	    model.addAttribute("type", 2); // 2.控制"收入"表格的显示
	} else {
	    model.addAttribute("type", 3); // 3.控制"支出"表格的显示
	}

	model.addAttribute("action", request.getRequestURI()); // 设置表单action是为了分页
	model.addAttribute("userinfo", user);
	if (type == 1) {
	    return "incomelist";
	} else {
	    return "outcomelist";
	}
    }

    /**
     * 查询结算明细
     * 
     * @param state
     * @param pageId
     * @param staTime
     * @param endTime
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeSettleState/{state}")
    public String changeSettleState(@PathVariable(value = "state") Integer state, @RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false) String staTime, @RequestParam(required = false) String endTime, Model model, HttpServletRequest request) {
	logger.info("WalletMgtController index method starting ......");
	LoginUser user =  super.getLoginUser();
	PageResult<UTradingDetails> pageResult = walletMgtService.getMyWalletSettleAccountPage(user.getWeiID(), Limit.buildLimit(pageId, 10), state, staTime, endTime);
	model.addAttribute("pageResult", pageResult);
	model.addAttribute("action", request.getRequestURI()); // 设置表单action是为了分页
	model.addAttribute("userinfo", user);
	model.addAttribute("staTime", staTime);
	model.addAttribute("endTime", endTime);
	if (state == 1) {
	    model.addAttribute("state", 1); // 1表示未到账
	}
	if (state == 2) {
	    model.addAttribute("state", 2); // 2表示已完成
	}
	return "settleaccount";
    }

    /**
     * 获得提现记录
     * 
     * @param pageId
     * @param staTime
     * @param endTime
     * @param model
     * @return
     */
    @RequestMapping(value = "/getWithdrawRecord")
    public String getWithdrawRecord(@RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false) String staTime, @RequestParam(required = false) String endTime, Model model) {
	LoginUser user = getUser();

	PageResult<UCancleOrderAmoutDetail> pageResult = walletMgtService.getMyWalletWithdrawRecordPage(user.getWeiID(), Limit.buildLimit(pageId, 10), staTime, endTime);
	if(pageResult != null && pageResult.getList()!=null && pageResult.getList().size()>0){
	    for(UCancleOrderAmoutDetail ad : pageResult.getList()){
	        String num = ad.getBankNum();
	        if(!ObjectUtil.isEmpty(num) && num.length()>3){
	            ad.setBankNum(num.substring(num.length()-4,num.length()));
	        }
	    }
	}
	model.addAttribute("pageResult", pageResult);
	model.addAttribute("userinfo", user);
	model.addAttribute("staTime", staTime);
	model.addAttribute("endTime", endTime);
	return "withdrawrecord";
    }

    @ResponseBody
    @RequestMapping(value = "/getSettleAccountDetail")
    public String getSettleAccountDetail(Long detailId) {
	LoginUser user = getUser();
	UTradingDetails details = walletMgtService.getTradeDetails(detailId, user.getWeiID());
	SettleAccountDetailVO detailVo = walletMgtService.getSettleAccountDetail(details);

	BaseResultVO result = new BaseResultVO();
	result.setObj(detailVo);
	result.setState(BaseResultState.Success);
	return JsonUtil.objectToJson(result);
    }

    @RequestMapping(value = "/markup")
    public String markup(@ModelAttribute("queryParam") WalletDTO queryParam, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	logger.info("WalletMgtController index method starting ......");

	return "walletmgt/markup";
    }

    @RequestMapping(value = "/withdraw")
    public String withdraw(@ModelAttribute("queryParam") WalletDTO queryParam, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	logger.info("WalletMgtController index method starting ......");

	return "walletmgt/withdraw";
    }

    @Autowired
    private IApplyBondService applyBondService;

    /**
     * 保证金申退流程
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/applybond")
    public String applybond(@RequestParam(required = false, defaultValue = "0") int type, Model model) {
	LoginUser user = getUser();
	long weiid = user.getWeiID();
	ApplyBondInfo bondinfo = applyBondService.getApplyBondInfo(type, weiid);
	model.addAttribute("bondinfo", bondinfo);
	if (bondinfo.getState() == -1 || bondinfo.getState() == 2) {
	    List<BankCardVO> banklist = applyBondService.getBankList(bondinfo.getBankNo(), weiid);
	    model.addAttribute("banklist", banklist);
	}
	// 获取认证员的信息
	VerifierInfo verifier = applyBondService.getVerifierInfo(bondinfo.getRzweiid());
	model.addAttribute("verifier", verifier);
	model.addAttribute("userinfo", user);
	return "walletmgt/applybond";
    }

    @ResponseBody
    @RequestMapping(value = "/SubmitApplyBond")
    public String SubmitApplyBond(@RequestParam(required = false, defaultValue = "0") int type, @RequestParam(required = false, defaultValue = "0") int tid, @RequestParam(required = false, defaultValue = "0") int cardid, @RequestParam(required = false, defaultValue = "0") int ispub, String imageback, String imagefront) {
	LoginUser user = getLoginUser();
	long weiid = user.getWeiID();
	BaseResultVO result = new BaseResultVO();
	result.setState(BaseResultState.Failure);
	if (weiid <= 0) {
	    result.setMessage("登陆超时");
	    return JsonUtil.objectToJson(result);
	}
	if (ispub != 1) {
	    if (imageback == null || imageback == "") {
		result.setMessage("请上传收款人正面照");
		return JsonUtil.objectToJson(result);
	    }
	    if (imagefront == null || imagefront == "") {
		result.setMessage("请上传授权证明");
		return JsonUtil.objectToJson(result);
	    }
	}
	result = applyBondService.getVerificationBond(type, weiid);
	if (result.getState().equals(BaseResultState.Failure))
	    return JsonUtil.objectToJson(result);
	result = applyBondService.saveApplyBond(weiid, type, tid, cardid, ispub, imageback, imagefront);
	return JsonUtil.objectToJson(result);
    }

    @ResponseBody
    @RequestMapping(value = "/cancelapply")
    public String cancelapply(@RequestParam(required = false, defaultValue = "0") int type, @RequestParam(required = false, defaultValue = "0") int tid) {
	LoginUser user = getLoginUser();
	long weiid = user.getWeiID();
	BaseResultVO result = new BaseResultVO();
	result.setState(BaseResultState.Failure);
	if (weiid <= 0) {
	    result.setMessage("登陆超时");
	    return JsonUtil.objectToJson(result);
	}
	int ret = applyBondService.updateCancelApply(weiid, type, tid);
	if (ret > 0) {
	    result.setState(BaseResultState.Success);
	} else if (ret == -1){
	    result.setMessage("取消失败");
	} else if (ret == -2){
		result.setMessage("申请已被审核，请重新刷新页面");
	}
	return JsonUtil.objectToJson(result);
    }
}
