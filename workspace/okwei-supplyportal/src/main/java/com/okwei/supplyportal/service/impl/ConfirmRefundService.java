package com.okwei.supplyportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.UCancleOrderAmoutDetail;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.enums.LtwoTypeEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.enums.ReFundStatusEnum;
import com.okwei.bean.enums.RefundTypeEnum;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.bean.enums.WalletMainTypeEnum;
import com.okwei.bean.enums.userAmountStatus;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.supplyportal.service.IConfirmRefundService;

@Service("refundService")
public class ConfirmRefundService extends BaseService implements IConfirmRefundService {
    @Autowired
    private BaseDAO baseDAO;

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void refundPenny(long sellerID, TOrderBackTotal refOrder) {
	/*-------------------------------查询退款的信息---------------------------------*/
	// 首先查找退款记录
	if (refOrder == null)
	    throw new RuntimeException();
	String supOrderID = refOrder.getSupplierOrderId();
	// 验证该订单是否到了分钱的状态
	// 退款类型
	short type = refOrder.getType();
	// 订单状态
	short refundState = refOrder.getBackStatus();
	if (type == 1) {
	    // 1.退款
	    if (!(refundState == Short.parseShort(ReFundStatusEnum.ShenQingZhong.toString()) || refundState == Short.parseShort(ReFundStatusEnum.WeiDianIn.toString()))) {
		throw new RuntimeException();
	    }
	} else if (type == 2) {
	    // 2.退货
	    if (refundState != Short.parseShort(ReFundStatusEnum.BuyerFaHuo.toString())) {
		throw new RuntimeException();
	    }
	} else {
	    throw new RuntimeException();
	}
	// 查询供应商订单
	OSupplyerOrder supOrder = getSupOrder(supOrderID);
	if (supOrder == null) {
	    throw new RuntimeException();
	}
	// 退款金额
	double refundAmout = refOrder.getRefundAmout();
	// 订单总金额
	double totalAmount = supOrder.getTotalPrice() + supOrder.getPostage();
	// 已退款的金额
	double yRefundAmount = getRefundedAmout(supOrderID);
	// 查询该供应商已退款的金额
	if (refundAmout > (totalAmount - yRefundAmount)) {
	    throw new RuntimeException();
	}

	// 查询退款的产品订单
	List<OProductOrder> proList = getProList(supOrderID, refOrder.getBackOrder());
	if (proList == null || proList.size() <= 0) {
	    throw new RuntimeException();
	}
	/*-------------------------------退款金额处理---------------------------------*/
	List<UTradingDetails> tradList = getTradingList(supOrderID);
	if (tradList == null || tradList.size() < 0) {
	    throw new RuntimeException();
	}
	// 已处理退款金额
	double deductedAmount = 0;
	// 判断是否整单退（订单是否退款完成）
	boolean isCompleteRefund = false;
	if (refOrder.getSingle().shortValue() == 1) {
	    isCompleteRefund = true;
	}

	// 需要操作微钱包的金额list
	List<UTradingDetails> newTradingList = new ArrayList<UTradingDetails>();

	// 1.先退佣金
	for (OProductOrder pro : proList) {
	    boolean flag = false;
	    for (UTradingDetails trading : tradList) {
		if (pro.getProductOrderId().equals(trading.getProductOrder()) && trading.getType().equals(Short.parseShort(UserAmountType.orderYj.toString()))) {
		    flag = true;
		    trading.setState(Short.parseShort(userAmountStatus.yiTuiKuan.toString()));
		    trading.setSurplusAmout(0d);
		    trading.setInTime(new Date());
		    baseDAO.update(trading);
		    deductedAmount += trading.getAmount();
		    // TODO 退款修改佣金已退款，未到账需要扣钱 佣金
		    newTradingList.add(trading);
		}
	    }
	    if (!flag) {
		logger.error("退款：供应商订单号" + supOrder.getSupplierOrderId() + "，产品订单号：" + pro.getProductId() + "退佣金失败");
	    }
	}

	// 判断退款完成的时候，抽成的金额是否>0
	boolean isfgcc = false;
	// 2.不是云商通的单退抽成
	if (supOrder.getOrderType().intValue() != Integer.parseInt(OrderTypeEnum.Pt.toString())) {
	    // 按比例退抽成
	    double scale = refundAmout / totalAmount;
	    scale = scale > 1 ? 1 : scale;
	    for (UTradingDetails trading : tradList) {
		if (trading.getType().equals(Short.parseShort(UserAmountType.rzYj.toString())) && trading.getLtwoType().equals(Short.parseShort(LtwoTypeEnum.BookCut.toString()))) {
		    // 需要退款的抽成
		    double tkcc = trading.getAmount() * scale;
		    // 剩余抽成
		    double surAmout = trading.getSurplusAmout() - tkcc;
		    surAmout = surAmout < 0 ? 0 : surAmout;
		    trading.setSurplusAmout(surAmout);
		    // 如果是整单退款 状态为已放款
		    if (isCompleteRefund) {
			// 订单完成的时候修改到账时间
			trading.setInTime(new Date());
			// 退款完成时抽成还有剩余的时候需要扣税
			if (surAmout > 0) {
			    // 只要抽成里面有一条记录剩余>0 所有的抽成都有剩余(按比例抽成)
			    isfgcc = true;
			    trading.setState(Short.parseShort(userAmountStatus.waitTax.toString()));
			} else {
			    trading.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
			}
		    }
		    baseDAO.update(trading);
		    // 插入一条退款抽成的记录
		    UTradingDetails temp = new UTradingDetails();
		    temp.setWeiId(trading.getWeiId());
		    temp.setSupplyOrder(trading.getSupplyOrder());
		    temp.setOrderNo(trading.getOrderNo());
		    temp.setAmount(tkcc * -1);// 退款负的金额
		    temp.setType(Short.parseShort(UserAmountType.refund.toString()));
		    if (isCompleteRefund) {
			temp.setInTime(new Date());
			if (surAmout > 0) {
			    temp.setState(Short.parseShort(userAmountStatus.waitTax.toString()));
			} else {
			    temp.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
			}
		    } else {
			temp.setState(trading.getState());
		    }
		    temp.setLtwoType(trading.getType());
		    temp.setSurplusAmout(0d);
		    temp.setCreateTime(new Date());
		    baseDAO.save(temp);
		    deductedAmount += tkcc;
		    // TODO 退款修改抽成已退款，已到账需要扣未到账 抽成
		    newTradingList.add(temp);
		}
	    }
	}
	// 订单完成时修改已经退款的抽成的状态
	if (isCompleteRefund) {
	    for (UTradingDetails trading : tradList) {
		// 佣金类型为退款 二级类型为认证佣金
		if (trading.getType().equals(Short.parseShort(UserAmountType.refund.toString())) && trading.getLtwoType().equals(Short.parseShort(UserAmountType.rzYj.toString()))) {
		    trading.setInTime(new Date());
		    if (isfgcc) {
			trading.setState(Short.parseShort(userAmountStatus.waitTax.toString()));
		    } else {
			trading.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
		    }
		    baseDAO.update(trading);
		}
	    }
	}
	// 货款还需要扣的钱
	double amount = 0d;
	// 3.扣货款
	for (UTradingDetails trading : tradList) {
	    if (trading.getWeiId().equals(sellerID) && trading.getType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
		// 货款还需要退款的金额
		amount = refundAmout - deductedAmount;
		if (amount > 0) {
		    double surAmout = trading.getSurplusAmout() - amount;
		    surAmout = surAmout < 0 ? 0 : surAmout;
		    trading.setSurplusAmout(surAmout);
		    // 如果是整单退款 状态为已放款
		    if (isCompleteRefund) {
			trading.setInTime(new Date());
			trading.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
			if (trading.getSurplusAmout().doubleValue() > 0) {
			    // TODO 货款还有剩余，先不修改货款的状态，还需要反写 货款进
			    newTradingList.add(trading);
			} else {
			    baseDAO.update(trading);
			    // 插入一条退款的记录
			    UTradingDetails temp = new UTradingDetails();
			    temp.setWeiId(trading.getWeiId());
			    temp.setSupplyOrder(trading.getSupplyOrder());
			    temp.setOrderNo(trading.getOrderNo());
			    temp.setAmount(amount * -1);// 退款负的金额
			    temp.setType(Short.parseShort(UserAmountType.refund.toString()));
			    temp.setInTime(new Date());
			    temp.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
			    temp.setLtwoType(trading.getType());
			    temp.setSurplusAmout(0d);
			    temp.setCreateTime(new Date());
			    baseDAO.save(temp);
			    // TODO 货款还有剩余，先不修改货款的状态，未到账
			    newTradingList.add(temp);
			    for (UTradingDetails item : tradList) {
				// 佣金类型为退款 二级类型为货款
				if (item.getType().equals(Short.parseShort(UserAmountType.refund.toString())) && item.getLtwoType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
				    item.setInTime(new Date());
				    item.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
				    baseDAO.update(item);
				}
			    }
			}
		    } else {
			// 插入一条退款的记录
			UTradingDetails temp = new UTradingDetails();
			temp.setWeiId(trading.getWeiId());
			temp.setSupplyOrder(trading.getSupplyOrder());
			temp.setOrderNo(trading.getOrderNo());
			temp.setAmount(amount * -1);// 退款负的金额
			temp.setType(Short.parseShort(UserAmountType.refund.toString()));
			temp.setState(trading.getState());
			temp.setLtwoType(trading.getType());
			temp.setSurplusAmout(0d);
			temp.setCreateTime(new Date());
			baseDAO.save(temp);
			// TODO 货款还有剩余，先不修改货款的状态，未到账
			newTradingList.add(temp);
		    }

		} else {
		    if (isCompleteRefund) {
			trading.setInTime(new Date());
			trading.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
			// 订单完成时，货款还有剩余的
			if (trading.getSurplusAmout().doubleValue() > 0) {
			    // TODO 货款还有剩余，先不修改货款的状态，还需要反写 佣金记录ID
			    newTradingList.add(trading);
			} else {
			    // 退款完成时候，修改已经退款的记录为已完成
			    baseDAO.update(trading);
			    for (UTradingDetails item : tradList) {
				// 佣金类型为退款 二级类型为货款
				if (item.getType().equals(Short.parseShort(UserAmountType.refund.toString())) && item.getLtwoType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
				    item.setInTime(new Date());
				    item.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
				    baseDAO.update(item);
				}
			    }
			}
		    }
		}
		break;
	    }
	}

	// 4.给买家退款，钱从哪里来，回哪里去。
	// 查询支付订单
	OPayOrder payOrder = getParOrder(supOrder.getPayOrderId());
	if (payOrder == null)
	    throw new RuntimeException();

	// 微钱包支付
	// UWalletDetails temp = new UWalletDetails();
	// temp.setWeiId(supOrder.getBuyerID());
	// temp.setMainType(Short.parseShort(WalletMainTypeEnum.income.toString()));
	// temp.setPayOrder(supOrder.getPayOrderId());
	// temp.setSupplyOrder(supOrderID);
	// temp.setType(Short.parseShort(UserAmountType.buyerPrice.toString()));
	// temp.setCreateTime(new Date());

	// TODO 买家收到的退款
	UTradingDetails temp = new UTradingDetails();
	temp.setWeiId(supOrder.getBuyerID());
	temp.setSupplyOrder(supOrderID);
	temp.setOrderNo(supOrder.getPayOrderId());
	temp.setType(Short.parseShort(UserAmountType.buyerPrice.toString()));
	temp.setCreateTime(new Date());

	// 银行卡支付
	UCancleOrderAmoutDetail model = new UCancleOrderAmoutDetail();
	model.setWeiId(supOrder.getBuyerID());
	model.setrId(refOrder.getBackOrder());
	model.setTkorderNo(supOrderID);
	model.setOrderNo(supOrder.getPayOrderId());
	model.setPayType(payOrder.getPayType());
	model.setTransactionType((short) supOrder.getOrderType().intValue());
	model.setRefundType(Short.parseShort(RefundTypeEnum.gouwu.toString()));
	model.setCounterFee(0d);
	model.setCreateTime(new Date());
	model.setState((short) 0);
	model.setType((short) 2);

	if (payOrder.getPayType().equals(Short.parseShort(PayTypeEnum.WeiWallet.toString()))) {
	    // 插入一条退款到买家微钱包的记录
	    temp.setAmount(refundAmout);// 退款的金额
	    newTradingList.add(temp);
	} else {
	    // 银行卡支付金额
	    if (payOrder.getWalletmoney() > 0) {
		// 部分银行卡支付，部分微钱包支付
		// 银行卡剩余退款的金额
		double backAmount = payOrder.getTotalPrice() - payOrder.getWalletmoney() - yRefundAmount;
		if (backAmount > 0) {
		    // 说明银行卡支付的没有退完
		    if (refundAmout < backAmount) {
			// 如果退款金额小于银行卡剩余退款的金额，只需要退款到银行卡
			model.setAmount(refundAmout);
			baseDAO.save(model);
		    } else {
			// 其他情况一部分退到银行卡，一部分退到微钱包
			model.setAmount(backAmount);
			baseDAO.save(model);

			temp.setAmount(refundAmout - backAmount);
			newTradingList.add(temp);
		    }
		} else {
		    // 银行卡部分已经退完，剩余金额全部退微钱包
		    temp.setAmount(refundAmout);// 退款的金额
		    newTradingList.add(temp);
		}

	    } else {
		model.setAmount(refundAmout);
		baseDAO.save(model);
	    }
	}
	// 6.跟新微钱包
	if (newTradingList.size() > 0) {
	    List<Long> weiids = new ArrayList<Long>();
	    for (UTradingDetails item : newTradingList) {
		if (!weiids.contains(item.getWeiId())) {
		    weiids.add(item.getWeiId());
		}
	    }
	    // 查询所有的钱包
	    List<UWallet> walletList = getWalletList((Long[]) weiids.toArray(new Long[weiids.size()]));
	    for (Long weiid : weiids) {
		double balance = 0d;
		double accountNot = 0d;
		UWallet wallet = null;
		for (UWallet uWallet : walletList) {
		    if (weiid.equals(uWallet.getWeiId())) {
			wallet = uWallet;
			balance = uWallet.getBalance() == null ? 0d : uWallet.getBalance();
			accountNot = uWallet.getAccountNot() == null ? 0d : uWallet.getAccountNot();
			break;
		    }
		}
		for (UTradingDetails item : newTradingList) {
		    if (weiid.equals(item.getWeiId())) {
			// 1.佣金 +
			if (item.getType().equals(Short.parseShort(UserAmountType.orderYj.toString()))) {
			    accountNot -= item.getAmount();
			}
			// 退款
			else if (item.getType().equals(Short.parseShort(UserAmountType.refund.toString()))) {
			    // 2.抽成 -
			    if (item.getLtwoType().equals(Short.parseShort(UserAmountType.rzYj.toString()))) {
				accountNot += item.getAmount();
			    }
			    // 3.退款的货款 -
			    else if (item.getLtwoType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
				accountNot += item.getAmount();
			    }
			} else if (item.getType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
			    // 4.进账的货款
			    balance += item.getSurplusAmout();
			    if (amount > 0) {
				accountNot -= (item.getSurplusAmout() + amount);
			    } else {
				accountNot -= item.getSurplusAmout();
			    }
			    // 插入金额记录
			    UWalletDetails entity = new UWalletDetails();
			    entity.setWeiId(weiid);
			    entity.setMainType(Short.parseShort(WalletMainTypeEnum.income.toString()));
			    entity.setPayOrder(item.getOrderNo());
			    entity.setSupplyOrder(item.getSupplyOrder());
			    entity.setAmount(item.getSurplusAmout());
			    entity.setType(item.getType());
			    entity.setRestAmount(balance);
			    entity.setCreateTime(new Date());
			    baseDAO.save(entity);
			    // 反写当前记录状态
			    item.setWdetailsId(entity.getWdetailsId());
			    baseDAO.update(item);
			    if (amount > 0) {
				// 插入一条退款的记录
				UTradingDetails detail = new UTradingDetails();
				detail.setWeiId(weiid);
				detail.setSupplyOrder(supOrderID);
				detail.setOrderNo(item.getOrderNo());
				detail.setAmount(amount * -1);// 退款负的金额
				detail.setType(Short.parseShort(UserAmountType.refund.toString()));
				detail.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
				detail.setWdetailsId(entity.getWdetailsId());
				detail.setLtwoType(item.getType());
				detail.setSurplusAmout(0d);
				detail.setCreateTime(new Date());
				detail.setInTime(new Date());
				baseDAO.save(detail);
			    }
			    // 反写已经退款的货款的状态
			    for (UTradingDetails trading : tradList) {
				// 佣金类型为退款 二级类型为货款
				if (trading.getType().equals(Short.parseShort(UserAmountType.refund.toString())) && trading.getLtwoType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
				    trading.setInTime(new Date());
				    trading.setState(Short.parseShort(userAmountStatus.yiFangKuan.toString()));
				    trading.setWdetailsId(entity.getWdetailsId());
				    baseDAO.update(trading);
				}
			    }

			} else if (item.getType().equals(Short.parseShort(UserAmountType.buyerPrice.toString()))) {
			    // 5.买家收到的退款
			    balance += item.getAmount();
			    // 插入金额记录
			    UWalletDetails entity = new UWalletDetails();
			    entity.setWeiId(weiid);
			    entity.setMainType(Short.parseShort(WalletMainTypeEnum.income.toString()));
			    entity.setPayOrder(item.getOrderNo());
			    entity.setSupplyOrder(item.getSupplyOrder());
			    entity.setAmount(item.getAmount());
			    entity.setType(item.getType());
			    entity.setRestAmount(balance);
			    entity.setCreateTime(new Date());
			    baseDAO.save(entity);
			}
		    }
		}
		// 跟新钱包
		if (wallet == null) {
		    wallet = new UWallet();
		    wallet.setWeiId(weiid);
		    wallet.setBalance(balance);
		    wallet.setAccountNot(accountNot);
		    wallet.setAccountIng(0d);
		    wallet.setCreateTime(new Date());
		    baseDAO.save(wallet);
		} else {
		    wallet.setBalance(balance);
		    wallet.setAccountNot(accountNot);
		    baseDAO.update(wallet);
		    // String sql =
		    // "update UWallet SET balance=balance+?,accountNot=accountNot+? where weiId=?";
		    // baseDAO.executeHql(sql, new Object[] { balance,
		    // accountNot, weiid });
		}
	    }
	}

	// 5.修改订单状态
	if (isCompleteRefund) {
	    // 整单退 订单状态改为退款中
	    supOrder.setState(Short.parseShort(OrderStatusEnum.Refunded.toString()));
	} else {
	    // 非整单退 订单状态改为原先的状态
	    supOrder.setState(refOrder.getOistatus());
	}
	baseDAO.update(supOrder);
	// 修改退款记录的状态
	refOrder.setBackStatus(Short.parseShort(ReFundStatusEnum.GysQueRen.toString()));
	baseDAO.update(refOrder);

    }

    /**
     * 查询微钱包list
     * 
     * @param weiids
     * @return
     */
    private List<UWallet> getWalletList(Long[] weiids) {
	String hql = "from UWallet where weiId in(:weiId)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiId", weiids);
	return baseDAO.findByMap(hql, params);
    }

    /**
     * 已经退款的金额
     * 
     * @param supOrderID
     * @return
     */
    private double getRefundedAmout(String supOrderID) {
	double amout = 0d;
	String hql = "select sum(refundAmout) from TOrderBackTotal where supplierOrderId=? and backStatus=?";
	List<Object[]> list = baseDAO.find(hql, new Object[] { supOrderID, Short.parseShort(ReFundStatusEnum.GysQueRen.toString()) });
	if (list != null && list.size() > 0) {
	    Object b = list.get(0);
	    if (b != null) {
		amout = (double) b;
	    }
	}
	return amout;
    }

    /**
     * 查询该供应商的支付订单
     * 
     * @param payOrderID
     * @return
     */
    private OPayOrder getParOrder(String payOrderID) {
	String hql = "from OPayOrder where payOrderId=?";
	return baseDAO.getUniqueResultByHql(hql, new Object[] { payOrderID });
    }

    /**
     * 查询该供应商的佣金记录
     * 
     * @param supOrderID
     * @return
     */
    private List<UTradingDetails> getTradingList(String supOrderID) {
	String hql = "from UTradingDetails where supplyOrder=?";
	return baseDAO.find(hql, new Object[] { supOrderID });
    }

    /**
     * 查询退款的产品
     * 
     * @param supOrderID
     * @param backOrder
     * @return
     */
    private List<OProductOrder> getProList(String supOrderID, Long backOrder) {
	String hql = "from OProductOrder where supplierOrderId=? and backOrder=?";
	return baseDAO.find(hql, new Object[] { supOrderID, backOrder });
    }

    /**
     * 获取供应商订单
     * 
     * @param supOrderID
     * @return
     */
    private OSupplyerOrder getSupOrder(String supOrderID) {
	String hql = "from OSupplyerOrder where supplierOrderId=?";
	return baseDAO.getUniqueResultByHql(hql, new Object[] { supOrderID });
    }
}
