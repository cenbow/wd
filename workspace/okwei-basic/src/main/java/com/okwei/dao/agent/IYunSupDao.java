package com.okwei.dao.agent;

import java.util.List;

import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.vo.agent.YunSupplierList;
import com.okwei.dao.IBaseDAO;

public interface IYunSupDao  extends IBaseDAO{
	
	/**
	 * 根据上级ID获取行业分类
	 * @param parentId
	 * @return
	 */
    public List<PProductClass> getProductClassByUpID(Integer parentId);
    /**
     * 根据classid查询工厂号供应商
     * @param classid
     * @param parentid
     * @return
     */
    public YunSupplierList getYunSuplierById(Integer classid,Integer parentid,Integer pageindex,Integer pagesize);
    /**
     * 根据位点号获取供应商基本信息
     * @param weiids
     * @return
     */
    public List<USupplyer> getSupBaseMsgByIds(Long[] weiids);
    /**
     * 根据微店号获取供应商的主营
     * @param weiids
     * @return
     */
    public List<Object[]> getCategoryByIds(Long[] weiids);
    /**
     * 根据code获取区域的名称
     * @param code
     * @return
     */
    public String getAreaNameByCode(Integer code);
    /**
     * 根据code获取分类信息
     * @param code
     * @return
     */
    public PProductClass getProductClassByCode(Integer code);
    /**
     * 根据微店号获取店铺信息
     * @param weiids
     * @return
     */
    public List<UShopInfo> getShopInfoByIds(Long[] weiids);
    
    /**
     * 获取供应商的默认产品图片
     * @param weiids
     * @return
     */
    public List<Object[]> getSupplierProImg(Long[] weiids);
    /**
     *  是否关注
     * @param userID
     * @param supID
     * @return
     */
    boolean getIsAttention(long userID, long supID);
    
    void deleteAttention(long userID, long supID);

    void addAttention(UAttention entity);

    void addAttention(UAttentioned entity);

    boolean getIsAttentioned(long userID, long supID);
}
