package com.okwei.service;

import java.io.Serializable;
import java.util.List;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;

/**
 * 
 * @ClassName: IBaseService
 * @Description: service基础接口
 * @author xiehz
 * @date 2015年5月5日 下午4:05:39
 *
 */
public interface IBaseService {

	/**
	 * @Title: add
	 * @Description: 添加记录
	 * @param @param obj
	 * @param @return
	 * @return boolean
	 */
	<T> boolean add(T obj);

	/**
	 * @Title: delete
	 * @Description: 删除记录
	 * @param @param obj
	 * @param @return
	 * @return boolean
	 */
	<T> boolean delete(T obj);

	/**
	 * @Title: deleteById
	 * @Description: 根据主键删除
	 * @param @param objClass
	 * @param @param id
	 * @param @return
	 * @return boolean
	 */
	<T> boolean deleteById(Class<T> objClass, Serializable id);

	/**
	 * @Title: deleteAll
	 * @Description: 删除所有
	 * @param @param objClass
	 * @param @return
	 * @return int
	 */
	<T> int deleteAll(Class<T> objClass);

	/**
	 * @Title: update
	 * @Description: 更新记录
	 * @param @param obj
	 * @param @return
	 * @return boolean
	 */
	<T> boolean update(T obj);

	/**
	 * 
	 * @Title: update
	 * @Description: 根据实体类与名字更新
	 * @param @param entity
	 * @param @param entityName
	 * @return void
	 * @throws
	 */
	void update(Object entity, String entityName);

	/**
	 * @Title: getById
	 * @Description: 根据主键获取实例
	 * @param @param objClass
	 * @param @param id
	 * @param @return
	 * @return T
	 */
	<T> T getById(Class<T> objClass, Serializable id);

	/**
	 * 
	 * @Title: findOneByExample
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T findOneByExample(T obj);

	/**
	 * @Title: getAll
	 * @Description: 获取所有记录
	 * @param @param objClass
	 * @param @return
	 * @return List<T>
	 */
	<T> List<T> getAll(Class<T> objClass);

	/**
	 * 
	 * @Title: findPage
	 * @Description: findPage
	 * @param @param objClass
	 * @param @param limit
	 * @param @return
	 * @return PageResult<T>
	 * @throws
	 */
	<T> PageResult<T> findPage(Class<T> objClass, Limit limit);

	/**
	 * 
	 * @Title: findByExample
	 * @Description: 根据模型查找
	 * @param @param obj
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> findByExample(T obj);

	/**
	 * 
	 * @Title: countAll
	 * @Description: 整表记录统计
	 * @param @param objClass
	 * @param @return
	 * @return int
	 * @throws
	 */
	<T> int countAll(Class<T> objClass);

}
