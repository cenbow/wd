package com.okwei.service.activity;

import java.util.List;




import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.activity.ARedPacMoneyAgLogVO;
import com.okwei.bean.vo.activity.ARedPacMoneyDetailVO;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

public interface IBasePerfectProductService {

	
	/**
     * 获取精品单选列表
     * @param weiNo 微店号 
     */
	public ReturnModel  findPerfectProductlist(int pageIndex,int pageSize);

	/**
	 * 得到衣食住用行玩学位置为1的前6个产品
	 * @author zlp at 20160726
	 * @param limit
	 * @return
	 */
	PageResult<ProductInfo> getAllAHomeMainProducts(Limit limit);

	/**
	 * 得到全返二级页面相关类型下的产品
	 * @author zlp at 20160726
	 * @param classId
	 * @param limit
	 * @return
	 */
	PageResult<ProductInfo> getActSecondProducts(Integer classId, Integer pclassId,Limit limit);

	/**
	 * 根据商品一级分类得到它的二级分类
	 * @author zlp at 20160726
	 * @param sysSid
	 * @return
	 */
	List<PProductClass> findSecondPClassId(Integer classId);

	/**
	 * 得到兑换区商品列表
	 * @author zlp at20160728
	 * @param limit
	 * @return
	 */
	PageResult<ProductInfo> getExchangeProduct(Long weiId,Limit limit);

	
	/**
	 * 得到今日红包数，剩余数
	 * @auther zlp at 20160728
	 * @return
	 */
	List<ARedPacMoneyDetailVO> findRedPackageMoneyList();

	/**
	 *  获得今日4个最新红包获奖情况
	 * @auther zlp at 20160728
	 * @return
	 */
	List<ARedPacMoneyAgLogVO> findPacketGetlogList();

	/**
	 * 抢红包
	 * @param weiId
	 * @return
	 */
	ReturnModel openRedPacket(Long weiId);

}
