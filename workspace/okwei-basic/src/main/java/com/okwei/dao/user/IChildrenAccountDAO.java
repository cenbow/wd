package com.okwei.dao.user;

import java.util.List;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenCreate;
import com.okwei.bean.domain.UChildrenStaff;
import com.okwei.bean.domain.UChildrenSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.common.Limit;
import com.okwei.dao.IBaseDAO;

public interface IChildrenAccountDAO extends IBaseDAO {
	/**
	 * 获得子账号供应商列表
	 * @param weiId
	 * @return
	 */
	List<Object[]> getChildrenSupplyerList(Long weiId,Limit limit,Integer status,Short from);
	/**
	 * 获得子账号供应商列表(应用于PC)
	 * @param weiId
	 * @return
	 */
	List<Object[]> getChildrenSupplyerListPC(Long weiId,Limit limit,Integer status,Integer from);
	/**
	 * 获取推荐的子供应商列表
	 * @param weiId
	 * @param limit
	 * @param status
	 * @return
	 */
	List<Object[]> getChildrenSupplyerListByRecommand(Long weiId,Limit limit,Integer status);

	/**
	 * 获得子供应商(发布的)商品数
	 * @param childIdList
	 * @return
	 */
	List<Object[]> getPublishProductCountByChildSupplyer(List<String> childIdList);
	/**
	 * 获得子供应商发布的(待审核的)商品数
	 * @param childIdList
	 * @return
	 */
	List<Object[]> getPendingProductCountByChildSupplyer(List<String> childIdList);
	/**
	 * 获得子账号供应商列表的总记录数
	 * @param weiId
	 * @return
	 */
	long getChildrenSupplyerTotalAmount(Long weiId,Integer status,Integer from);
	
	/**
	 * 通过推荐人获取子供应商的总数
	 * @param weiId
	 * @param status
	 * @return
	 */
	long getChildrenSupplyerTotalAmountByRecommand(Long weiId,Integer status);
	/**
	 * 获得子账号员工列表
	 * @param weiId
	 * @return
	 */
	List<Object[]> getChildrenStaffList(Long weiId,Limit limit);
	/**
	 * 获得子账号员工列表的总记录数
	 * @param weiId
	 * @return
	 */
	long getChildrenStaffTotalAmount(Long weiId);
	/**
	 * 添加子账号用户
	 * @param param
	 */
	void addChildrenUser(UChildrenUser param);
	/**
	 * 添加子账号供应商
	 * @param param
	 */
	void addChildrenSupplyerInfo(UChildrenSupplyer param);
	/**
	 * 添加子账号员工
	 * @param param
	 */
	void addChildrenStaffInfo(UChildrenStaff param);
	/**
	 * 添加子账号生成对象
	 * @param param
	 */
	void addChildrenCreate(UChildrenCreate param);
	/**
	 * 获得子账号生成表的对象
	 * @param weiId
	 * @return
	 */
	UChildrenCreate getUChildrenCreate(Long weiId);
	/**
	 * 获得子账号用户
	 * @param childrenId
	 * @return
	 */
	UChildrenUser getUChildrenUser(String childrenId);
	/**
	 * 获得子账号供应商
	 * @param childrenId
	 * @return
	 */
	UChildrenSupplyer getUChildrenSupplyer(String childrenId);
	/**
	 * 获得子账号员工
	 * @param childrenId
	 * @return
	 */
	UChildrenStaff getUChildrenStaff(String childrenId);
	/**
	 * 编辑子账号用户
	 * @param param
	 */
	void editUChildrenUser(AddChildrenAccountDTO param);
	/**
	 * 编辑子账号供应商
	 * @param childrenId
	 * @param companyName
	 */
	void editUChildrenSupplyer(String childrenId,String companyName,Short isOrderSend);
	/**
	 * 编辑子账号员工
	 * @param childrenId
	 * @param department
	 */
	void editUChildrenStaff(String childrenId,String department);
	/**
	 * 删除子账号用户
	 * @param param
	 */
	void deleteUChildrenUser(String childrenId);
	/**
	 * 批量删除子账号用户
	 * @param idList
	 */
	void batchDeleteUChildrenUser(List<String> idList);
	/**
	 * 审核子供应商
	 * @param childrenId
	 */
	void verifyUChildrenUser(VerifySupplierDTO param);
	/**
	 * 批量审核子供应商
	 * @param idList
	 * @param statu
	 */
	void batchVerifyChildrenUser(List<String> idList,Integer statu);
	/**
	 * 删除子账号供应商
	 * @param childrenId
	 * @param companyName
	 */
	void deleteUChildrenSupplyer(String childrenId);
	/**
	 * 删除子账号员工
	 * @param childrenId
	 * @param department
	 */
	void deleteUChildrenStaff(String childrenId);
	/**
	 * 通过编码获取地区
	 * @param code
	 * @return
	 */
	List<TRegional> getTRegionalByCode(Integer code);
	/**
	 * 
	 * @param weiIdList
	 * @return
	 */
	List<UVerifier> getVerifierInfo(List<Long> weiIdList);
	/**
	 * 根据微店号查找平台号供应商列表
	 * @param supplerList
	 * @return
	 */
	List<UPlatformSupplyer> getPlatformSuppler(List<Long> supplerList);
	/**
	 * 获取子账号列表
	 * @param childrenList
	 * @return
	 */
	List<UChildrenUser> getChildrenById(String[] childrenList);
	
	/**
	 * 
	 * @param childrenList
	 * @return
	 */
	List<UChildrenSupplyer> getChildrenSupplyer(String[] childrenList);
	
	/**
	 * 获取全部平台号供应商
	 * @return
	 */
	List<UPlatformSupplyer> getPlatformSuppler();
	/**
	 * 根据微店号查找代理商信息
	 * @return
	 */
	List<USupplyChannel> getAgentSupplierByID(Long weiid);
	/**
	 * 通过weiid获取平台号供应商
	 * @param weiid
	 * @return
	 */
	UPlatformSupplyer getPSupplyerById(Long weiid);
	/**
	 * 通过weiid获取品牌号供应商
	 * @param weiid
	 * @return
	 */
	UBrandSupplyer getBSupplyerById(Long weiid);
}
