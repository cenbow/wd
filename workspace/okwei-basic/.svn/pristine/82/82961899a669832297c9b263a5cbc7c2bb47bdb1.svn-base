package com.okwei.service.activity;

import com.okwei.bean.domain.AHomeApp;
import com.okwei.bean.dto.activity.AHomeAppDTO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.activity.AHomeAppVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IHomeAppService extends IBaseService{
	   /**
     * 添加APP首页控制
     * 
     * @param srdto
     * @return
     */
    public ReturnModel addAHomeApp(AHomeAppDTO srd);

    /**
     * 获取APP首页控制列表
     * 
     * @param title
     * @param state
     * @return
     */
    public PageResult<AHomeAppVO> findAHomeApp(Limit limit);
    /**
     * 获取APP专区列表
     * 
     * @param title
     * @param state
     * @return
     */
    public AHomeApp findAHomeApp(int areaId);
}
