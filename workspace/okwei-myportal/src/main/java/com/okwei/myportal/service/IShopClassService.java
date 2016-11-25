package com.okwei.myportal.service;

import java.util.List;

import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.ShopClassNewVO;
import com.okwei.myportal.bean.vo.ShopClassVO;

public interface IShopClassService
{
    /**
     * 查询分类下产品数量
     */
    public int getCountByClassId(long weiid,int cid);

    /**
     * 微店号下的分类个数
     */
    public int getClassCount(long weiid);

    /**
     * 修改分类名称
     * 
     * @param className
     *            要修改的分类名称
     * @param weiid
     *            微店号
     * @param cid
     *            分类ID
     * @return
     */
    public boolean updateClassByName(String className,long weiid,int cid);

    /**
     * 添加分类
     * 
     * @param weiid
     *            微店号
     * @param className
     *            分类名称
     * @param type
     *            类型（1是供应商分类，2是普通为店主分类）
     * @return
     */
    public boolean insertPShopClass(long weiid,String className,short type);

    /**
     * 删除分类
     * 
     * @param weiid
     *            微店号
     * @param cid
     *            分类id
     * @return
     */
    public boolean deleteClassByCid(long weiid,int cid)  throws Exception;

    /**
     * 置顶分类
     * 
     * @param weiid
     *            微店号
     * @param cid
     *            分类
     * @return
     */
    public boolean placedTop(long weiid,int cid);

    /**
     * 查询分类列表
     * 
     * @param weiid
     *            微店号
     * @param pageId
     *            页码
     * @param pageSize
     *            显示条数
     * @return
     */
    public PageResult<ShopClassNewVO> getShopClassListByCid(long weiid,int pageId,int pageSize);

    /**
     * 判断这个分类名称是否存在
     * 
     * @param weiid
     *            微店号
     * @param className
     *            分类名称
     * @return
     */
    public boolean judgeClassName(long weiid,String className);
}
