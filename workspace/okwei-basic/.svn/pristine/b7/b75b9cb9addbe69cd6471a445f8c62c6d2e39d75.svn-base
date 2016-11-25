package com.okwei.service;

import java.util.List;

import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.vo.ReturnModel;

public interface IBasicCommonService {
    /**
     * 关注店铺(取消关注)
     * 
     * @param userID
     * @param type
     *            1表示取消关注 其他表示关注
     * @param supID
     */
    public boolean attentionSup(long userID, int type, long supID);

    /**
     * 获取区域实体
     * 
     * @return
     */
    ReturnModel getRegionalModel(Integer type, Integer ver);

    /**
     * 查询行业列表
     * 
     * @return
     */
    public List<TBussinessClass> getBussinessClasses();

    /**
     * 查询是否提醒，及提醒文字
     * 
     * @param weiid
     * @param type
     * @return
     */
    public ReturnModel getShareRimand(Long weiid, Short type);

}
