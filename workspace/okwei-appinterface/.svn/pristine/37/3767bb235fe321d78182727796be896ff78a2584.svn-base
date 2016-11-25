package com.okwei.appinterface.service.agent;


import java.util.List;

import com.okwei.appinterface.bean.vo.agent.ApplyBrandAgentInfoVO;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.PageResult;

public interface IBrandAgentService {

	
	/**
	 * BA04 我管理的代理商列表
	 * tzp (2016-07-20)
     */
	public PageResult<DAgentInfo> findDAgentInfos(long weiid, int brandid, int scope, int pageIndex, int pageSize);

	/**
	 * BA04 我管理的代理商列表(品牌商身份)
	 * tzp (2016-08-06)
	 */
	public PageResult<DAgentInfo> findDAgentInfos(int brandid, int scope, int pageIndex, int pageSize);
	/**
	 * BA06 申请代理商
	 * yhm (2016-11-1)
	 */
	public ReturnModel saveApplyAgent(Integer brandid, String name,
			String contactPhone, String qq, String weixin,Double cost,Long weiId,Long referencer,Integer level,String advantage);
	/**
	 * BA05 设置管理申请代理制度
	 * yhm (2016-11-1)
	 */
	public ApplyBrandAgentInfoVO getApplyAgent(Integer brandid);
	/**
	 * 通过微店号获取品牌
	 * @param weiid
	 * @return
     */
	public DBrands getBrands(long weiid);

	/**
	 * 获取某微店代理的品牌列表
	 * @param weiid
	 * @return
     */
	public List<DBrands> findAgentBrands(long weiid);

	/**
	 * 根据地址代码获取地址信息
	 * @param regionCodes
	 * @return
     */
	public String getAddress(Integer... regionCodes);

	/**
	 * 获取跟品牌的代理关系 (关系类型：0--无关系 1--代理 2--队长 3--城主候选人 4--城主)
	 * @param weiid
	 * @param brandid
     * @return
     */
	public int getAgentRelationshipType(long weiid, int brandid);

	/**
	 * 获取关系名称 (关系类型：0--无关系 1--代理 2--队长 3--城主候选人 4--城主)
	 * @param relationshipType
	 * @return
     */
	public String getRelationshipName(int relationshipType);
}
