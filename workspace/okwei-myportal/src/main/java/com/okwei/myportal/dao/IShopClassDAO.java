package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.ShopClassVO;

public interface IShopClassDAO
{

    /**
     * 查询分类下产品数量
     */
    public int getCountByClassId(long weiid,int cid);

    /**
     * 查询上架的产品
     * 
     * @param weiid
     * @param cid
     * @param childrenSc 
     * @return
     */
    public List<PClassProducts> getPClassProductsList(int cid, Long[] childrenSc);

    /**
     * 根据产品ID数组获取上架产品
     * 
     * @param ids
     * @return
     */
    public List<PClassProducts> getClassProductsByProIds(List<Long> ids);

    /**
     * 微店号下的分类个数
     */
    public int getClassCount(long weiid);

    /**
     * 修改产品
     * 
     * @param pc
     * @return
     */
    public boolean updatePClassProducts(PClassProducts pc);

    /**
     * 删除上架产品
     * 
     * @param pc
     * @return
     */
    public boolean deletePClassProducts(PClassProducts pc);

    /**
     * 修改产品
     * 
     * @param pp
     * @return
     */
    public boolean updateProducts(PProducts pp);

    /**
     * 获取产品分类
     * 
     * @param weiid
     * @param cid
     * @return
     */
    public List<PProducts> getPProducts(long weiid,int cid);

    /**
     * 获取排序最大值
     * 
     * @param weiid
     *            微店号
     * @return
     */
    public short getMaxClassSort(long weiid);
    
    /**
     * 获取排序最小值
     * @param weiid
     * @return
     */
    public short getMinClassSort(Long weiid);

    /**
     * 添加
     * 
     * @param sc
     * @return
     */
    public boolean insertPShopClass(PShopClass sc);

    /**
     * 删除分类
     * 
     * @param weiid
     *            微店号
     * @param cid
     *            分类id
     * @return
     */
    public boolean deleteClassByCid(long weiid,int cid);

    /**
     * 获取分类信息
     * 
     * @param weiid
     *            微店号
     * @param cid
     *            分类ID
     * @return
     */
    public PShopClass getPShopClass(long weiid,int cid);

    /**
     * 修改分类
     */
    public boolean updatePShopClass(PShopClass sc);

    /**
     * 查询分类列表
     * 
     * @param weiid
     *            微店号
     * @return
     */
    public PageResult<PShopClass> getShopClassListByCid(long weiid,Limit limit);

    /**
     * 根据分类名称获取分类
     * 
     * @param weiid
     *            微店号
     * @param className
     *            分类名称
     * @return
     */
    public PShopClass getPShopClassByName(long weiid,String className);

	public List<PShopClass> getShopClassListById(int cid);
}
