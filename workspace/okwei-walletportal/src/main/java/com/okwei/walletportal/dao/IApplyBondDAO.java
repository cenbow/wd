package com.okwei.walletportal.dao;

import java.util.List;

import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UPublicBanks;
import com.okwei.bean.domain.UTuizhu;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.BondType;
import com.okwei.dao.IBaseDAO;

public interface IApplyBondDAO extends IBaseDAO {
    /**
     * 获取批发号供应商
     * 
     * @param weiid
     * @return
     */
    public UBatchSupplyer getBatchSupplyer(long weiid);

    /**
     * 获取工厂号批发商
     * 
     * @param weiid
     * @return
     */
    public UYunSupplier getYunSupplier(long weiid);

    /**
     * 获取供应商上架产品的数量
     * 
     * @param weiid
     * @return
     */
    public long getShelveCount(long weiid);

    /**
     * 获取未完成的订单的数量
     * 
     * @param weiid
     * @return
     */
    public long getOrderCount(long weiid);

    /**
     * 判断是否有保证金在申请中
     * 
     * @param type
     * @param weiid
     * @return
     */
    public long getIsApply(BondType type, long weiid);

    /**
     * 获取退驻申请记录
     * 
     * @param type
     * @param weiid
     * @return
     */
    public UTuizhu getTuizhu(int type, long weiid);

    /**
     * 获取对公账号
     * @param weiid
     * @return
     */
    public List<UPublicBanks> getPublicBanks(long weiid);
    
    public List<UBankCard> getBankCards(long weiid);
}
