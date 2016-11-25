package com.okwei.myportal.service;

import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.FreightVO;

public interface IFreightService
{
    /**
     * 查询运费模板分页
     * 
     * @param weiid
     *            微店号
     * @param pageId
     *            页码
     * @param pageSize
     *            显示条数
     * @return 分页结果
     */
    public PageResult<FreightVO> getFreightList(long weiid,int pageId,int pageSize);

    /**
     * 获取运费模板详情
     * 
     * @param weiid
     *            微店号
     * @param fid
     *            运费模板ID
     * @return
     */
    public FreightVO getFreightVO(long weiid,int fid);

    /**
     * 添加
     * 
     * @param fvo
     *            要添加的对象
     * @return
     */
    public boolean insertFreightVO(FreightVO fvo);

    /**
     * 修改
     * 
     * @param fvo
     *            要修改的对象
     * @return
     */
    public boolean updateFreightVO(FreightVO fvo);

    /**
     * 删除
     * 
     * @param fvo
     *            要删除的对象
     * @return
     */
    public boolean deleteFreightVO(FreightVO fvo);
    
    /**
     * 查询运费模板下的产品数量
     * @param weiid
     * @param fid
     * @return
     */
    public int getCountByFid(long weiid, int fid);
}
