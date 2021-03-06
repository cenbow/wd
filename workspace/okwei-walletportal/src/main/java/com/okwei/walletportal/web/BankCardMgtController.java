package com.okwei.walletportal.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.vo.LoginUser;
import com.okwei.common.AjaxUtil;
import com.okwei.common.JsonUtil;
import com.okwei.common.PropertyFactory;
import com.okwei.util.HttpClientUtil;
import com.okwei.util.MD5Encrypt;
import com.okwei.walletportal.bean.vo.BaseSSOController;
import com.okwei.walletportal.service.IWalletMgtService;

 

@Controller
@RequestMapping(value = "/bankCardMgt")
public class BankCardMgtController extends BaseSSOController {

	private final static Log logger = LogFactory.getLog(BankCardMgtController.class);

	@Autowired
	private IWalletMgtService walletMgtService;

	@RequestMapping(value = "/bankCard")
	public String bankCard(Model model) {
		logger.info("WalletMgtController index method starting ......");
		LoginUser user = super.getLoginUser();
		List<UBankCard> cards = walletMgtService.getBankCards(user.getWeiID());
		model.addAttribute("cards", cards);
		model.addAttribute("userinfo", user);
		return "walletmgt/bankcard";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		logger.info("WalletMgtController toAdd method starting ......");
		LoginUser user = super.getLoginUser();
		UWallet wallet = walletMgtService.getById(UWallet.class, user.getWeiID());
		model.addAttribute("userName", wallet.getRealName());
		model.addAttribute("userinfo", user);
		return "walletmgt/addcard";
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(UBankCard bc, String password, Model model) {
		logger.info("WalletMgtController add method starting ......");
		/* String result = LLPayUtil.queryCardBin(bc.getBanckCard()); */
		String bankInfo = this.getBankInfo(bc.getBanckCard());
		Object value = JsonUtil.getJsonValue(bankInfo, "card_type");

		LoginUser user = super.getLoginUser();
		UWallet wallet = walletMgtService.getById(UWallet.class, user.getWeiID());

		/* 校验支付密码 */
		if (null == wallet || org.apache.commons.lang3.StringUtils.isEmpty(wallet.getPayPassword())) {
			logger.error("请先设置支付密码:"+wallet.getPayPassword());
			return AjaxUtil.ajaxFail("请先设置支付密码");
		}
		if (!MD5Encrypt.encrypt(password).equalsIgnoreCase(wallet.getPayPassword())) {
			logger.error("支付密码错误:"+password+"&&"+MD5Encrypt.encrypt(password));
			return AjaxUtil.ajaxFail("支付密码错误");
		}

		if (null != value && (Integer.parseInt((String) value) == 2 || Integer.parseInt((String) value) == 3)) {
			String bankName = (String) JsonUtil.getJsonValue(bankInfo, "bank_name");
			bc.setBanckName(bankName);
			bc.setName(wallet.getRealName());
			bc.setWeiId(user.getWeiID());
			bc.setIdcard(wallet.getIdCard());
			bc.setCreateTime(new Date());
			bc.setCardType(Short.parseShort(value.toString()));
			walletMgtService.add(bc);
			return AjaxUtil.ajaxSuccess("添加成功");
		} else {
			logger.error("银行号错误,请核对:"+bankInfo);
			return AjaxUtil.ajaxFail("银行号错误,请核对");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public String removeBankCard(@PathVariable("id") Long id, Model model) {
		logger.info("WalletMgtController removeBankCard method starting ......");
		// 删除银行卡 校验用户
		LoginUser user = super.getLoginUser();
		UBankCard bc = walletMgtService.getById(UBankCard.class, id);
		if (bc == null || !bc.getWeiId().equals(user.getWeiID())) {
			return AjaxUtil.ajaxFail("参数错误");
		}
		walletMgtService.deleteById(UBankCard.class, id);
		return AjaxUtil.ajaxSuccess("删除成功");
	}

	/**
	 * 
	 * @Title: checkPwd
	 * @Description: 验证支付密码
	 * @param @param password
	 * @param @return
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPwd", method = RequestMethod.GET)
	public String checkPwd(String password) {
		LoginUser user = super.getLoginUser();
		UWallet wallet = walletMgtService.getById(UWallet.class, user.getWeiID());
		String md5 = MD5Encrypt.encrypt(password);
		return md5.equalsIgnoreCase(wallet.getPayPassword()) ? "true" : "false";
	}

	@ResponseBody
	@RequestMapping(value = "/getCardInfo")
	public String getCardInfo(String cardId, Model model) {
		logger.info("WalletMgtController getCardInfo method starting ......");
		// return LLPayUtil.queryCardBin(cardId);
		return this.getBankInfo(cardId);
	}

	@ResponseBody
	@RequestMapping(value = "/checkWallet", method = { RequestMethod.POST, RequestMethod.GET })
	public String getParamModel(Model model) {
		LoginUser user = super.getLoginUser();
		UWallet wallet = walletMgtService.getById(UWallet.class, user.getWeiID());
		if (wallet == null) {
			return AjaxUtil.ajaxFail("未实名认证");
		}
		return (null != wallet.getStatus() && wallet.getStatus() > 0) ? AjaxUtil.ajaxSuccess("已实名认证") : AjaxUtil.ajaxFail("未实名认证");
	}

	/**
	 * 
	 * @Title: checkCard
	 * @Description: 判断银行卡是否已绑定
	 * @param @param banckCard
	 * @param @return
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/checkCard", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkCard(String banckCard) {
		LoginUser user = super.getLoginUser();
		boolean isExsit = walletMgtService.isExistCard(user.getWeiID(), banckCard);
		return !isExsit ? "true" : "false";
	}

	private String getBankInfo(String cardId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("card_no", cardId);
		String payUrl = PropertyFactory.getPropertyValue("domain", "paydomain");
		String returnMsg = HttpClientUtil.doGetMethod(payUrl + "/llpay/queryCardBin", map);
		return returnMsg;
	}

}
