package com.okwei.service;

import java.util.List;

public interface IPowerService {
	/**
	 * 通过微店号获取用户所有权限
	 * @param weiid
	 * @return
	 */
	public List<String> getPowerByWeiID(Long weiid);
	
	/**
	 * 通过子账号查询所有权限
	 * @param weiid
	 * @return
	 */
	public List<String> getPowerByChildrenID(String weiid);
	
//	/**
//	 * 通过用户id和模块名称获取权限列表
//	 * @param weiid
//	 * @param type
//	 * @return
//	 */
//	public String getPowerByModelType(Long weiid,String type);
//	
	/**
	 * 判断用户是否有某个功能点的权限
	 * @param weiid
	 * @param type
	 * @param funcode
	 * @return
	 */
	public boolean checkPowerByModelTypeAndWeiid(Long weiid,String type,String funcode);
}
