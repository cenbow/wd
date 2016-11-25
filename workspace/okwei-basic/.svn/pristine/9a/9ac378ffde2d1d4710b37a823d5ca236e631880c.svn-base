package com.okwei.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.UCancleOrderAmoutDetail;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.LtwoTypeEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.ReFundStatusEnum;
import com.okwei.bean.enums.RefundTypeEnum;
import com.okwei.bean.enums.ShoppingCarSourceEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserAmountStatus;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.bean.enums.WalletMainTypeEnum;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.service.IConfirmRefundService;
import com.okwei.util.BitOperation;

@Service
public class ConfirmRefundService extends BaseService implements IConfirmRefundService {
    @Autowired
    private BaseDAO baseDAO;

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
	OSupplyerOrder supOrder = baseDAO.get(OSupplyerOrder.class, supOrderID);
	if (supOrder == null) {
	    throw new RuntimeException();
	}
	// 退款金额
	double refundAmout = refOrder.getRefundAmout();
	// 订单总金额
	double totalAmount = supOrder.getTotalPrice() + supOrder.getPostage();

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

	// 需要操作微钱包的金额list
	List<UTradingDetails> newTradingList = new ArrayList<UTradingDetails>();

	// 1.先退佣金
	for (OProductOrder pro : proList) {
	    for (UTradingDetails trading : tradList) {
		if (pro.getProductOrderId().equals(trading.getProductOrder()) && trading.getType().equals(Short.parseShort(UserAmountType.orderYj.toString()))) {
		    trading.setState(Short.parseShort(UserAmountStatus.yiTuiKuan.toString()));
		    trading.setSurplusAmout(0d);
		    trading.setInTime(new Date());
		    baseDAO.update(trading);
		    deductedAmount += trading.getAmount();
		    // TODO 未到账 -
		    newTradingList.add(trading);
		}
	    }
	}

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
		    double surAmout = trading.getAmount() - tkcc;
		    surAmout = surAmout < 0 ? 0 : surAmout;
		    trading.setSurplusAmout(surAmout);
		    trading.setInTime(new Date());
		    // 退款完成时抽成还有剩余的时候需要扣税
		    if (surAmout > 0) {
			trading.setState(Short.parseShort(UserAmountStatus.waitTax.toString()));
		    } else {
			trading.setState(Short.parseShort(UserAmountStatus.yiFangKuan.toString()));
		    }
		    baseDAO.update(trading);
		    // TODO 未到账 -
		    newTradingList.add(trading);
		    // 插入一条退款抽成的记录
		    UTradingDetails temp = new UTradingDetails();
		    temp.setWeiId(trading.getWeiId());
		    temp.setSupplyOrder(trading.getSupplyOrder());
		    temp.setOrderNo(trading.getOrderNo());
		    temp.setAmount(tkcc * -1);// 退款负的金额
		    temp.setType(Short.parseShort(UserAmountType.refund.toString()));
		    temp.setInTime(new Date());
		    if (surAmout > 0) {
			temp.setState(Short.parseShort(UserAmountStatus.waitTax.toString()));
		    } else {
			temp.setState(Short.parseShort(UserAmountStatus.yiFangKuan.toString()));
		    }
		    temp.setLtwoType(trading.getType());
		    temp.setSurplusAmout(0d);
		    temp.setCreateTime(new Date());
		    baseDAO.save(temp);
		    deductedAmount += tkcc;
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
		amount = amount < 0d ? 0d : amount;

		double surAmout = trading.getAmount() - amount;// 货款还需要进的帐
		surAmout = surAmout < 0d ? 0d : surAmout;

		trading.setSurplusAmout(surAmout);
		trading.setInTime(new Date());
		trading.setState(Short.parseShort(UserAmountStatus.yiFangKuan.toString()));
		// TODO 未到账- 余额+
		newTradingList.add(trading);
		if (amount > 0) {
		    // 插入一条退款的记录
		    UTradingDetails temp = new UTradingDetails();
		    temp.setWeiId(trading.getWeiId());
		    temp.setSupplyOrder(trading.getSupplyOrder());
		    temp.setOrderNo(trading.getOrderNo());
		    temp.setAmount(amount * -1);// 退款负的金额
		    temp.setType(Short.parseShort(UserAmountType.refund.toString()));
		    temp.setInTime(new Date());
		    temp.setState(Short.parseShort(UserAmountStatus.yiFangKuan.toString()));
		    temp.setLtwoType(trading.getType());
		    temp.setSurplusAmout(0d);
		    temp.setCreateTime(new Date());
		    baseDAO.save(temp);
		}
		break;
	    }
	}

	// 4.给买家退款，钱从哪里来，回哪里去。
	// 查询支付订单
	OPayOrder payOrder = baseDAO.get(OPayOrder.class, supOrder.getPayOrderId());
	if (payOrder == null)
	    throw new RuntimeException();

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
	model.setRid(refOrder.getBackOrder());
	model.setTkOrderNo(supOrderID);
	model.setOrderNo(supOrder.getPayOrderId());
	model.setPayType(payOrder.getPayType());
	model.setTransactionType((short) supOrder.getOrderType().intValue());
	model.setRefundType(Short.parseShort(RefundTypeEnum.gouwu.toString()));
	model.setCounterFee(0d);
	model.setCreateTime(new Date());
	model.setState((short) 0);
	model.setType((short) 2);

	// 买家收到的退款，先退到银行卡，再退到微钱包，微金币不退
	double wjbpay = payOrder.getWeiDianCoin() == null ? 0d : payOrder.getWeiDianCoin().doubleValue();// 微金币支付金额
	double wqbpay = payOrder.getWalletmoney() == null ? 0d : payOrder.getWalletmoney().doubleValue();// 微钱包支付金额
	double yhkpay = payOrder.getTotalPrice() - wqbpay - wjbpay;// 银行卡支付
	if (yhkpay > 0) {
	    // 有银行卡支付原路退回到银行卡
	    if (refundAmout > yhkpay) {
		model.setAmount(yhkpay);
		baseDAO.save(model);
		double tempAmount = refundAmout - yhkpay;
		if (wqbpay > 0) {
		    temp.setAmount(tempAmount > wqbpay ? wqbpay : tempAmount);
		    newTradingList.add(temp);
		}
	    } else {
		model.setAmount(refundAmout);
		baseDAO.save(model);
	    }
	} else {
	    // 钱包支付退回到钱包
	    if (wqbpay > 0) {
		temp.setAmount(refundAmout > wqbpay ? wqbpay : refundAmout);// 只退微钱包部分，微金币部分不退
		newTradingList.add(temp);
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
		double ybalance = 0d;// 原先的余额
		double balance = 0d;// 增加的余额
		double accountNot = 0d;// 增加的未到账
		UWallet wallet = null;
		for (UWallet uWallet : walletList) {
		    if (weiid.equals(uWallet.getWeiId())) {
			wallet = uWallet;
			ybalance = uWallet.getBalance() == null ? 0d : uWallet.getBalance();
			break;
		    }
		}
		for (UTradingDetails item : newTradingList) {
		    if (weiid.equals(item.getWeiId())) {
			// 1.佣金 +
			if (item.getType().equals(Short.parseShort(UserAmountType.orderYj.toString()))) {
			    accountNot -= item.getAmount();
			}
			// 2.认证佣金 +
			else if (item.getType().equals(Short.parseShort(UserAmountType.rzYj.toString()))) {
			    accountNot -= (item.getAmount() - item.getSurplusAmout());
			}
			// 3.货款
			else if (item.getType().equals(Short.parseShort(UserAmountType.supplyPrice.toString()))) {
			    accountNot -= item.getAmount();
			    if (item.getSurplusAmout() > 0) {
				// 货款还没有退完，有进账
				balance += item.getSurplusAmout();
				// 插入金额记录
				UWalletDetails entity = new UWalletDetails();
				entity.setWeiId(weiid);
				entity.setMainType(Short.parseShort(WalletMainTypeEnum.income.toString()));
				entity.setPayOrder(item.getOrderNo());
				entity.setSupplyOrder(item.getSupplyOrder());
				entity.setAmount(item.getSurplusAmout());
				entity.setType(item.getType());
				entity.setRestAmount(ybalance + balance);
				entity.setCreateTime(new Date());
				baseDAO.save(entity);
				item.setWdetailsId(entity.getWdetailsId());
			    }
			    baseDAO.update(item);
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
			    entity.setRestAmount(ybalance + balance);
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
		    String sql = "update UWallet SET balance=balance+?,accountNot=accountNot+? where weiId=?";
		    baseDAO.executeHql(sql, new Object[] { balance, accountNot, weiid });
		}
	    }
	}

	// 5.修改订单状态
	supOrder.setState(Short.parseShort(OrderStatusEnum.Refunded.toString()));
	baseDAO.update(supOrder);
	// 修改退款记录的状态
	refOrder.setBackStatus(Short.parseShort(ReFundStatusEnum.GysQueRen.toString()));
	baseDAO.update(refOrder);

	// 5.0新增，如果是落地店首单取消落地店身份
	if (supOrder.getOrderType().intValue() == Integer.parseInt(OrderTypeEnum.Jinhuo.toString()) && supOrder.getSourse() != null && supOrder.getSourse().intValue() == Integer.parseInt(ShoppingCarSourceEnum.Landi.toString())) {
	    cancelLandShop(supOrderID, proList.get(0).getProductId(), supOrder.getBuyerID());
	}
    }

    /**
     * 取消落地店的身份
     */
    public void cancelLandShop(String supOrderID, Long proID, Long buyID) {
	// 判断是不是首单
	if (baseDAO.count("select count(1) from UWeiCoinLog where supplyOrderId=?", supOrderID) > 0) {
	    UDemandProduct demand = baseDAO.getUniqueResultByHql("from UDemandProduct where productId=?", proID);
	    if (demand != null) {
		// 删除购买者的落地店身份

		List<USupplyChannel> chanList = baseDAO.find("from USupplyChannel where weiId=? and channelType=? and state=?", new Object[] { buyID, Short.parseShort(SupplyChannelTypeEnum.ground.toString()), Short.parseShort(AgentStatusEnum.Normal.toString()) });
		if (chanList != null && chanList.size() > 0) {
		    for (USupplyChannel chan : chanList) {
			if (chan.getDemandId().equals(demand.getDemandId())) {
			    baseDAO.delete(chan);
			    UProductShop productShop = baseDAO.get(UProductShop.class, chan.getChannelId());
			    if (productShop != null) {
				baseDAO.delete(productShop);
			    }
			    break;
			}
		    }
		    // 删除落地店的身份
		    if (chanList.size() == 1) {
			UUserAssist assist = baseDAO.get(UUserAssist.class, buyID);
			assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 12, false));
			baseDAO.update(assist);
		    }
		}
	    }
	}
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

}