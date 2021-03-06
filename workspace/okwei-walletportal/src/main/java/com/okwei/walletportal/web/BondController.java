package com.okwei.walletportal.web;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UTuizhu;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.enums.BondType;
import com.okwei.bean.enums.SupplierStatusEnum;
import com.okwei.bean.enums.VerifierTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.walletportal.bean.vo.BaseSSOController;
import com.okwei.walletportal.service.ICompanyAccountService;
import com.okwei.walletportal.service.IWalletMgtService;
 

@Controller
@RequestMapping(value = "/bondMgt")
public class BondController extends BaseSSOController {

	private final static Log logger = LogFactory.getLog(BondController.class);
	@Autowired
	private IWalletMgtService walletMgtService;
	@Autowired
	private ICompanyAccountService companyAccountService;

	@RequestMapping(value = "/bondList")
	public String deposit(Model model) {
		LoginUser user = getUser();
		try {
			/* 批发号保证金 */
			UBatchSupplyer bs = walletMgtService.getById(UBatchSupplyer.class,
					user.getWeiID());
			if (null != bs
					&& (null != bs.getStatus() && bs.getStatus() == Short
							.parseShort(SupplierStatusEnum.PayIn.toString()))) {
				// 查询批发号、认证服务点最后一次退驻记录
				UTuizhu tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
						Short.valueOf(BondType.BatchSupplier.toString()));
				if (tz == null) {
					tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
							Short.valueOf(BondType.Port.toString()));
				}
				if (tz != null) {
					model.addAttribute("bsTuizhuStatus", tz.getState());
//					model.addAttribute("bsProcessTime", tz.getPayTime());
				}
				model.addAttribute("pshDeposit", bs);
			}
			// 审核通过 缴费
			else if (null != bs
					&& bs.getStatus() == Short
							.parseShort(SupplierStatusEnum.Pass.toString())) {
				model.addAttribute("bsPassDeposit", bs);
			}
			// 已退驻
			else if (null != bs
					&& bs.getStatus() == Short
							.parseShort(SupplierStatusEnum.TuiZhu.toString())) {
				UTuizhu tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
						Short.valueOf(BondType.BatchSupplier.toString()));
				if (tz == null) {
					tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
							Short.valueOf(BondType.Port.toString()));
				}
				if (tz != null) {
					model.addAttribute("bsTuizhuStatus", tz.getState());
					model.addAttribute("bsProcessTime", tz.getPayTime());
				}
				model.addAttribute("bsTuizhuDeposit", bs);
			}
			/* 工厂号保证金 */
			UYunSupplier ys = walletMgtService.getById(UYunSupplier.class,
					user.getWeiID());
			if (null != ys
					&& ys.getStatus() != null
					&& ys.getStatus() == Short.valueOf(SupplierStatusEnum.PayIn
							.toString())) {
				if (ys.getPayTime() != null) {
					Date endTime = null;
					Calendar cal = Calendar.getInstance();
					cal.setTime(ys.getPayTime());
					if (null == ys.getServiceType() || ys.getServiceType() == 1) {// 加1年
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
					} else if (ys.getServiceType() == 2) {// 加3年
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 3);
					}
					endTime = cal.getTime();
					model.addAttribute("endTime", endTime);
				}
				model.addAttribute("gcDeposit", ys);
				UTuizhu tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
						Short.valueOf(BondType.YunSupplier.toString()));
				if (tz != null) {
					model.addAttribute("ysTuizhuStatus", tz.getState());
//					model.addAttribute("ysProcessTime", tz.getPayTime());
				}
			}
			// 审核通过 缴费
			else if (null != ys
					&& ys.getStatus() == Short
							.parseShort(SupplierStatusEnum.Pass.toString())) {
				model.addAttribute("ysPassDeposit", ys);
			}
			// 已退驻
			else if (null != ys
					&& ys.getStatus() == Short
							.parseShort(SupplierStatusEnum.TuiZhu.toString())) {
				UTuizhu tz = walletMgtService.getTuizhuRecord(user.getWeiID(),
						Short.valueOf(BondType.YunSupplier.toString()));
				if (tz != null) {
					model.addAttribute("ysTuizhuStatus", tz.getState());
					model.addAttribute("ysProcessTime", tz.getPayTime());
				}
				if (ys.getPayTime() != null) {
					Date endTime = null;
					Calendar cal = Calendar.getInstance();
					cal.setTime(ys.getPayTime());
					if (null == ys.getServiceType() || ys.getServiceType() == 1) {// 加1年
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
					} else if (ys.getServiceType() == 2) {// 加3年
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 3);
					}
					endTime = cal.getTime();
					model.addAttribute("endTime", endTime);
				}
				model.addAttribute("ysTuizhuDeposit", ys);
			}
			/* 正式认证员保证金 */
			UVerifier vf = walletMgtService.getById(UVerifier.class,
					user.getWeiID());
			if (null != vf && null != vf.getType()) {
				if (((short) vf.getType() & Short
						.parseShort(VerifierTypeEnum.percent.toString())) > 1) {
					UYunVerifier yv = walletMgtService.getById(UYunVerifier.class,user.getWeiID());
					if (yv != null) {
						if (yv.getBond() > 0) {
							model.addAttribute("rzyDeposit", vf); // 临时
							model.addAttribute("rzyBond", yv.getBond());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("保证金列表报错：" + e.getMessage());
		}
		logger.error("forward dplist");
		return "dpList";
	}
}
