package com.okwei.pay.dao;

import java.util.List;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OPayRecords;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.TSmsverification;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UBatchPort;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UBatchVerifier;
import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UVerifierFollowMsg;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.dao.IBaseDAO;

public interface IPayOrderDAO extends IBaseDAO {

    /**
     * 获取支付订单实体
     * 
     * @param orderNo
     * @return
     */
    public OPayOrder getOPayOrder(String orderNo);

    /**
     * 获取用户可用金额
     * 
     * @param weiid
     * @return
     */
    public double getUserAmountAvailable(long weiid);

    /**
     * 添加记录
     * 
     * @param rec
     * @return
     */
    public boolean insertOPayRecords(OPayRecords rec);

    /**
     * 添加钱包记录
     * 
     * @param details
     * @return
     */
    public boolean insertUWalletDetails(UWalletDetails details);

    /**
     * 获取用户钱包信息
     * 
     * @param weiid
     * @return
     */
    public UWallet getUWallet(long weiid);

    /**
     * 添加钱包
     * 
     * @param wallet
     * @return
     */
    public boolean insertUWallet(UWallet wallet);

    /**
     * 修改支付订单
     * 
     * @param order
     * @return
     */
    public boolean updateOPayOrder(OPayOrder order);

    /**
     * 根据支付订单号 获取供应商订单列表
     * 
     * @param supplierOrderId
     * @return
     */
    public List<OSupplyerOrder> getListBySupplyOrderID(String supplierOrderId);

    /**
     * 根据支付订单号 获取供应商订单列表
     * 
     * @param payOrderID
     * @return
     */
    public List<OSupplyerOrder> getListByPayOrderID(String payOrderID);

    /**
     * 修改供应商订单状态
     * 
     * @param order
     * @return
     */
    public boolean updateOSupplyerOrder(OSupplyerOrder order);

    /**
     * 根据供应商订单列表获取商品详情订单
     * 
     * @param supNoList
     * @return
     */
    public List<OProductOrder> getProductList(List<String> supNoList);

    /**
     * 获取该供应商的信息
     * 
     * @param weiid
     * @return
     */
    public UBatchSupplyer getUBatchSupplyer(long weiid);

    /**
     * 获取用户绑定的手机号 未绑定的不返回
     * 
     * @param weiid
     * @return
     */
    public String GetUserBindMobile(long weiid);

    /**
     * 获取市场实体
     * 
     * @param bmid
     * @return
     */
    public TBatchMarket getBatchMarket(int bmid);

    /**
     * 支付成功修改商品库存
     * 
     * @param styleID
     * @param count
     */
    public boolean updateProductStyleCount(long styleID, int count);

    /**
     * 发送消息
     * 
     * @param meg
     * @return
     */
    public boolean insertSendPushMsg(UPushMessage msg);

    /**
     * 获取批发号认证员实体
     * 
     * @param model
     * @return
     */
    public UBatchVerifier getBatchVerifier(long weiid);

    /**
     * 添加钱包
     * 
     * @param td
     * @return
     */
    public boolean insertUTradingDatails(UTradingDetails td);

    /**
     * 修改微钱包表
     * 
     * @param model
     * @return
     */
    public boolean updateUWallet(UWallet model);

    /**
     * 获取该认证点信息
     * 
     * @param weiid
     * @return
     */
    public UBatchPort getBatchPort(long weiid);

    /**
     * 添加流失记录
     * 
     * @param msg
     * @return
     */
    public boolean insertUVerifierFollowMsg(UVerifierFollowMsg msg);

    /**
     * 获取认证员信息
     * 
     * @param weiid
     * @return
     */
    public UVerifier getUVerifier(long weiid);

    /**
     * 获取供应商
     * 
     * @param weiid
     * @return
     */
    public USupplyer getSupplyer(long weiid);

    /**
     * 获取供应商产品列表
     * 
     * @param weiid
     * @return
     */
    public List<PProducts> getListByProducts(long weiid);

    /**
     * 修改产品列表
     * 
     * @param pp
     * @return
     */
    public boolean updateProducts(PProducts pp);

    /**
     * 修改用户上架表
     * 
     * @param weiid
     * @return
     */
    public boolean updateClassProduct(long weiid);

    /**
     * 获取云商通供应商
     * 
     * @param weiid
     * @return
     */
    public UYunSupplier getYunSupplier(long weiid);

    /**
     * 支付成功修改供应商商品状态
     * 
     * @param supplyerWeiID
     * @param identity
     */
    public boolean updatePayedEditType(long supplyerWeiID, short identity);

    /**
     * 支付成功后修改上架表类型
     * 
     * @param weiid
     * @return
     */
    public boolean updatePayedEditType(long weiid);

    /**
     * 获取认证员信息
     * 
     * @param weiid
     * @return
     */
    public UYunVerifier getYunVerifier(long weiid);

    /**
     * 随机分配
     * 
     * @return
     */
    public UYunVerifier getYunVerifierByCount();

    /**
     * 获取实体
     * 
     * @param productId
     * @return
     */
    public PProductAssist getPProductAssist(long productId);

    /**
     * 
     * @param model
     * @return
     */
    public boolean insertPProductAssist(PProductAssist model);

    /**
     * 获取产品
     * 
     * @param productId
     * @return
     */
    public PProducts getProducts(long productId);

    /**
     * 获取用户信息
     * 
     * @param weiid
     * @return
     */
    public UWeiSeller getWeiSeller(long weiid);

    /**
     * 添加发送短信记录
     * 
     * @param sms
     * @return
     */
    public boolean insertTSmsverification(TSmsverification sms);

    /**
     * 验证码入库
     * 
     * @param tv
     * @return
     */
    public boolean insertTVerificationId(TVerificationId tv);

    /**
     * 获取快捷支付银行卡
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
     * 添加订单
     * 
     * @param order
     * @return
     */
    public boolean insertPayOrder(OPayOrder order);
    
    /**
     * 修改支付订单日志为已支付状态
     * @param payOrderID
     */
    public void editPayOrderLog(String payOrderID,double payAmout);

    /**
     * 获取最新的支付订单号
     * @param supplyOrderIDs
     * @return
     */
    public OPayOrderLog getLastLog(String supplyOrderIDs);
}
