package com.okwei.company.service;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PProductClass;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.company.bean.vo.AreaVo;
import com.okwei.company.bean.vo.BusKeyValue;
import com.okwei.company.bean.vo.YHpageMainVO;
import com.okwei.company.bean.vo.YsquerParam;
import com.okwei.company.bean.vo.YunSupParam;
import com.okwei.company.bean.vo.YunSupVo;
import com.okwei.service.IBaseService;

public interface IYunsupService extends IBaseService{
    public YHpageMainVO getMainVO(YsquerParam param);
    
    //by yangjunjun
    /**
     * 获得商品一级分类
     * @param parentId
     * @return
     * @throws Exception
     */
    public List<BusKeyValue> getProductClassOne(Integer parentId) throws Exception;
    /**
     * 获得商品热门二级分类
     * @return
     */
	public List<PProductClass> getHotClasstwoList() throws Exception;
	/**
     * 获得商品热门品牌
     * @return
     */
	public List<PBrand> getHotBrandList() throws Exception;

	/**
	 * 获取相关类目
	 * @param queryparam
	 * @return
	 */
	public List<PProductClass> getRelevantClassList(YunSupParam queryparam) throws Exception;
	/**
	 * 获取相关品牌
	 * @param queryparam
	 * @return
	 */
	public List<PBrand> getRelevantBrandList(YunSupParam queryparam) throws Exception;

	/**
	 *  根据筛选获取地区列表 
	 * @param queryparam
	 * @return
	 */
	public List<AreaVo> getAreaListByCondition(YunSupParam queryparam) throws Exception;

	/**
	 * 根据条件获得云供应商及积分最高的几个商品列表
	 * @param buildLimit
	 * @param queryparam
	 * @param weiId
	 * @return
	 */
	public PageResult<YunSupVo> getUBatchSupplyerList(Limit buildLimit, YunSupParam queryparam,Long weiId) throws Exception;

	/**
	 * 关注、取消关注
	 * @param weiID
	 * @param type
	 * @param supID
	 */
	public void attentionSup(long weiID, int type, long supID);
}
