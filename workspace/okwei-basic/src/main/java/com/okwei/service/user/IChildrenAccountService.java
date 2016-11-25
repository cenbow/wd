package com.okwei.service.user;

import java.util.List;

import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.bean.vo.ChildAccountListVO;
import com.okwei.bean.vo.ChildrenAccountVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IChildrenAccountService extends IBaseService {
	/**
	 * 获得子账号供应商列表
	 * @param weiId
	 * @return
	 */
	PageResult<ChildAccountListVO> getChildrenSupplyerList(Long weiId,Limit limit,Integer status,Short from);
	
	/**
	 * 获取推荐的供应商列表
	 * @param weiId
	 * @param limit
	 * @param status
	 * @return
	 */
	PageResult<ChildAccountListVO> getChildrenSByRecommandAPP(Long weiId,Limit limit,Integer status);
	/**
	 * 获得子账号供应商列表(应用于PC)
	 * @param weiId
	 * @return
	 */
	PageResult<ChildrenAccountVO> getChildrenSupplyerListPC(Long weiId,Limit limit,Integer status,Integer from);
	/**
	 * 通过推荐人微店号获取子供应商列表
	 * @param weiId
	 * @param limit
	 * @param status
	 * @return
	 */
	PageResult<ChildrenAccountVO> getChildrenSupplyerListByRecommand(Long weiId,Limit limit,Integer status);
	/**
	 * 获得子账号员工列表
	 * @param weiId
	 * @return
	 */
	PageResult<ChildAccountListVO> getChildrenStaffList(Long weiId,Limit limit);
	/**
	 * 添加子账号供应商或员工
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel addChildrenSupplyerOrStaff(AddChildrenAccountDTO param,LoginUser user);
	/**
	 * 认证员推荐添加子供应商
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel addChildrenSupplyerByVerifier(AddChildrenAccountDTO param);
	/**
	 * 编辑子账号供应商或员工
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel editChildrenSupplyerOrStaff(AddChildrenAccountDTO param,LoginUser user);
	/**
	 * 删除子账号供应商或员工
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel deleteChildrenSupplyerOrStaff(String childrenId,Integer type,LoginUser user);
	/**
	 * 审核子供应商
	 * @param childrenId
	 * @param type
	 * @param user
	 * @return
	 */
	ReturnModel verifyChildrenSupply(VerifySupplierDTO parm,LoginUser user);
	/**
	 * 批量删除
	 * @param idList
	 * @return
	 */
	ReturnModel batchDeleteChildrenUser(String[] idList,LoginUser user);
	/**
	 * 批量审核子供应商
	 * @param idList
	 * @param user
	 * @return
	 */
	ReturnModel batchVerifyChildrenSupply(String[] idList,LoginUser user,Integer statu);
	/**
	 * 获得子账号供应商或员工信息
	 * @param childrenId
	 * @param type
	 * @return
	 */
	ReturnModel getChildrenSupplyerOrStaff(String childrenId,Integer type,LoginUser user);
	/**
	 * 修改密码
	 * @param childrenId
	 * @param password
	 * @return
	 */
	ReturnModel editPassword(String childrenId,String newPassword,String oldPassword);
	/**
	 * 获取
	 * @return
	 */
	ReturnModel getPlatformSupplierOption();
    
}
