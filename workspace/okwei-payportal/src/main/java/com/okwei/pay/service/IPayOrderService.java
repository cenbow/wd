package com.okwei.pay.service;

import java.util.Date;
import java.util.List;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TSmsverification;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.pay.bean.vo.PayResult;
import com.okwei.service.IBaseService;
import com.okwei.pay.bean.vo.ResultMsg;


public interface IPayOrderService extends IBaseService {

	/**
	 * 校验app通知
	 * @param tiket
	 * @param orderNo
	 * @return
	 */
	public boolean checkAppNotify(String tiket,String orderNo,Integer notifyType,PayTypeEnum payType);
	
	
	/**
     * 不适用代金券 
     * @param payOrder
     */
    public void UP_CoinPayAmountDel(OPayOrder payOrder);
	/**
	 * 获取订单 代金券可用金额（并将可用金额写入订单辅助表）
	 * @param payOrder
	 * @return
	 */
	public double UP_getCoinPayAmount(OPayOrder payOrder);
    /**

     * 订单信息校验
     * 
     * @param orderNo
     *            支付订单号
     * @param payAmout
     *            支付金额
     * @return
     */
    public PayResult OrderPaymentSuccess(String orderNo, double payAmout,PayTypeEnum payType);

    /**
     * 订单支付成功数据处理
     * @param orderNo
     * @param payType
     * @return
     */
    public PayResult orderDataProcessing(String orderNo,PayTypeEnum payType);
    
    /***
     * 获取支付订单信息
     * 
     * @param orderNo
     * @return
     */
    public OPayOrder getPayOrder(String orderNo);

    /**
     * 获取订单信息，并把微钱包设置成0
     * 
     * @param orderNo
     * @return
     */
    public OPayOrder getPayOrderUpWallet(String orderNo);

    /**
     * 获取实名认证
     * 
     * @param weiid
     * @return
     */
    public UWallet getWallet(long weiid);

    /**
     * 获取用户信息
     * 
     * @param weiid
     * @return
     */
    public UWeiSeller getWeiSeller(long weiid);

    /**
     * 把发送的验证码添加到数据库
     * 
     * @param tv
     * @return
     */
    public boolean insertTVerificationId(TVerificationId tv);

    /**
     * 订单验证
     * 
     * @param orderNo
     * @return
     */
    public PayResult verifilyOrder(String orderNo, long weiid,boolean isGoBank);

    /**
     * 修改支付订单
     * 
     * @param order
     *            要修改的实体
     * @return
     */
    public boolean updateOPayOrder(OPayOrder order);

    /**
     * 获取绑定的银行卡（必须实名认证）
     * 
     * @param weiid
     * @return
     */
    public List<UBankCard> getBankCardList(long weiid);

    /**
     * 获取银行卡
     * 
     * @param cardId
     * @return
     */
    public UBankCard getBankCard(long cardId);

    /**
     * 添加记录
     * 
     * @param ts
     * @return
     */
    public boolean insertTSmsverification(TSmsverification ts);

    /**
     * 添加订单
     * 
     * @param order
     * @return
     */
    public boolean insertPayOrder(OPayOrder order);

    /**
     * 判斷是否正確
     * 
     * @param paypassword
     * @param code
     * @param orderNo
     * @return
     */
    public String verifySMS(String paypassword, String code, String orderNo, LoginUser user,String paytype);

    /**
     * 判断是否有支付锁
     * 
     * @param orderNo
     * @return
     */
    public ResultMsg getIszfs(String orderNo);

    
    /**
     * 根据支付订单号查询最新的支付订单号
     * @param oldPayID
     * @return
     */
    public String getLastPayOrderID(OPayOrder payOrder);
    
    /**
     * 设置供应商订单支付锁
     * @param payOrderID
     */
    public void lockSupplyOrder(OPayOrder payOrder);
    
    /**
     * 解锁供应商支付订单
     * @param orderNo
     */
    public void openLockSupplyOrder(OPayOrder payOrder);
    
    /**
     * 获取供应商订单列表
     * @param payOrder
     * @return
     */
    public List<OSupplyerOrder> getSupplyerOrderList(OPayOrder payOrder);
    
    /**
     * 
     */
    public Date getExpirationTime(String orderNo);
}
