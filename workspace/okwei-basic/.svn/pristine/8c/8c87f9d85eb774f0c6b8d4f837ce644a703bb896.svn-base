package com.okwei.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;

/**
 * 
 * @ClassName: IBaseDAO
 * @Description: DAO基础接口
 * @author xiehz
 * @date 2015年5月5日 下午4:07:42
 *
 */
public interface IBaseDAO {

	/* read */

	/**
	 * 
	 * @Title: load
	 * @Description: 根据id加载PO实例
	 * @param @param entityClass
	 * @param @param id
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T load(Class<T> entityClass, Serializable id);

	/**
	 * 
	 * @Title: get
	 * @Description: 根据id获取PO实例
	 * @param @param entityClass
	 * @param @param id
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T get(Class<T> entityClass, Serializable id);

	/**
	 * 
	 * @Title: loadAll
	 * @Description: 加载PO的所有对象
	 * @param @param entityClass
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> loadAll(Class<T> entityClass);

	/**
	 * 
	 * @Title: find
	 * @Description: hql查询PO列表
	 * @param @param hql
	 * @param @param params
	 * @param @return
	 * @return List<?>
	 * @throws
	 */
	<T> List<T> find(String hql, Object... params);

	/**
	 * 
	 * @Title: findPage
	 * @Description: hql分页查询PO列表
	 * @param @param hql
	 * @param @param startIndex
	 * @param @param pageSize
	 * @param @param params
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> findPage(String hql, int startIndex, int pageSize, Object... params);

	/**
	 * 
	 * @Title: findPageResult
	 * @Description: hql分页获取PO页面
	 * @param @param hql
	 * @param @param limit
	 * @param @param params
	 * @param @return
	 * @return PageResult<T>
	 * @throws
	 */
	<T> PageResult<T> findPageResult(String hql, Limit limit, Object... params);

	/**
	 * 
	 * @Title: findPageResultTrans
	 * @Description: hql分页获取PO页面,自定义PO
	 * @param @param hql
	 * @param @param clazz
	 * @param @param limit
	 * @param @param params
	 * @param @return
	 * @return PageResult<T>
	 * @throws
	 */
	<T> PageResult<T> findPageResultTrans(String hql, Class<T> clazz, Limit limit, Object... params);

	/**
	 * 
	 * @Title: findByMap
	 * @Description: hql查询，传递map参数
	 * @param @param hql
	 * @param @param params
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> findByMap(String hql, Map<String, Object> params);
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	<T> List<T> findSQLByMap(String sql, Map<String, Object> params);
	/**
	 * 
	 * @Title: findPageByMap
	 * @Description: hql分页查询，传递map参数
	 * @param @param hql
	 * @param @param startIndex
	 * @param @param pageSize
	 * @param @param params
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> findPageByMap(String hql, int startIndex, int pageSize, Map<String, Object> params);

	/**
	 * 
	 * @Title: findPageResultByMap
	 * @Description: hql查询获取PO页面，传递map参数
	 * @param @param hql
	 * @param @param limit
	 * @param @param params
	 * @param @return
	 * @return PageResult<T>
	 * @throws
	 */
	<T> PageResult<T> findPageResultByMap(String hql, Limit limit, Map<String, Object> params);

	<T> PageResult<T> findPageResultTransByMap(String hql, Class<T> clazz, Limit limit, Map<String, Object> params);

	/**
	 * 
	 * @Title: queryBySql
	 * @Description: sql查询
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> queryBySql(String sql, Object... params);

	/**
	 * 
	 * @Title: queryPageBySql
	 * @Description: sql分页查询
	 * @param @param sql
	 * @param @param startIndex
	 * @param @param pageSize
	 * @param @param params
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> queryPageBySql(String sql, int startIndex, int pageSize, Object... params);

	/**
	 * 
	 * @Title: queryPageResultBySql
	 * @Description: sql分页查询，获取PO页面
	 * @param @param sql
	 * @param @param limit
	 * @param @param params
	 * @param @return
	 * @return PageResult<T>
	 * @throws
	 */
	<T> PageResult<T> queryPageResultBySql(String sql, Class<T> clazz, Limit limit, Object... params);

	<T> PageResult<T> queryPageResultByMap(String sql, Class<T> clazz, Limit limit, Map<String, Object> params);
	
	
	<T> PageResult<T> queryPageResultBySqlWithCount(String sql, Class<T> clazz, Limit limit,Integer count, Object... params);

	<T> PageResult<T> queryPageResultByMapWithCount(String sql, Class<T> clazz, Limit limit,Integer count, Map<String, Object> params);

	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria动态组装HQL查询
	 * @param @param criteria
	 * @param @return
	 * @return List<T>
	 */
	<T> List<T> findByCriteria(DetachedCriteria criteria);

	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria动态组装HQL分页查询
	 * @param @param criteria
	 * @param @param startIndex
	 * @param @param size
	 * @param @return
	 * @return List<T>
	 */
	<T> List<T> findByCriteria(DetachedCriteria criteria, int startIndex, int size);

	/**
	 * @Title: findByExample
	 * @Description: 
	 *               根据对象example查询，简单的查询可以采用该方法，不用写HQL，避免属性名修改带来的隐性错误，仅限于做=查询，不支持排序控制
	 * @param @param t
	 * @param @return
	 * @param @throws DataAccessException
	 * @return List<T>
	 */
	<T> List<T> findByExample(T t);

	/**
	 * @Title: findByExample
	 * @Description: 
	 *               根据对象example查询，设置分页，简单的查询可以采用该方法，不用写HQL，避免属性名修改带来的隐性错误，仅限于做=查询
	 *               ，不支持排序控制
	 * @param @param t
	 * @param @param startIndex
	 * @param @param size
	 * @param @return
	 * @param @throws DataAccessException
	 * @return List<T>
	 */
	<T> List<T> findByExample(T t, int startIndex, int size);

	/**
	 * 
	 * @Title: findMapBySql
	 * @Description: sql查询，结果以map返回
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @return Map<?,?>
	 * @throws
	 */
	<T> Map<?, ?> findMapBySql(String sql, Object... params);

	/**
	 * 
	 * @Title: getUniqueResultByHql
	 * @Description: hql查询，获取唯一PO
	 * @param @param hql
	 * @param @param objs
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T getUniqueResultByHql(String hql, Object... objs);
	
	/**
	 * 
	 * @Title: getUniqueResultByHql
	 * @Description: hql查询，获取唯一PO
	 * @param @param hql
	 * @param @param objs
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T getNotUniqueResultByHql(String hql, Object... objs);
	/**
	 * 
	 * @Title: getUniqueSQLResult
	 * @Description: sql查询，获取唯一PO
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @return T
	 * @throws
	 */
	<T> T getUniqueSQLResult(String sql, Object... params);

	/**
	 * 
	 * @Title: count
	 * @Description: hql查询，获取count
	 * @param @param hql
	 * @param @param params
	 * @param @return
	 * @return int
	 * @throws
	 */
	long count(String hql, Object... params);

	/**
	 * 
	 * @Title: countBySql
	 * @Description: sql查询，获取count
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @return int
	 * @throws
	 */
	long countBySql(String sql, Object... params);

	/**
	 * 
	 * @Title: countByMap
	 * @Description: hql获取，获取count,传递map参数
	 * @param @param hql
	 * @param @param params
	 * @param @return
	 * @return int
	 * @throws
	 */
	long countByMap(String hql, Map<String, Object> params);
	
	long countBySqlMap(String sql, Map<String, Object> params);

	/* delete */

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除PO
	 * @param @param entity
	 * @return void
	 * @throws
	 */
	<T> void delete(T entity);

	/**
	 * 
	 * @Title: delete
	 * @Description: 根据id删除 PO
	 * @param @param objClass
	 * @param @param id
	 * @param @return
	 * @return int
	 * @throws
	 */
	<T> int delete(Class<T> objClass, Serializable id);

	/**
	 * 
	 * @Title: deleteAll
	 * @Description: 删除PO所有实例
	 * @param @param objClass
	 * @param @return
	 * @return int
	 * @throws
	 */
	<T> int deleteAll(Class<T> objClass);

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param entity
	 * @param @param lockMode
	 * @return void
	 * @throws
	 */
	<T> void delete(T entity, LockMode lockMode);

	/**
	 * 
	 * @Title: deleteAll
	 * @Description: 删除PO所有实例
	 * @param @param entities
	 * @return void
	 * @throws
	 */
	<T> void deleteAll(Collection<Object> entities);

	/* update */

	/**
	 * 
	 * @Title: update
	 * @Description: 更新PO实例
	 * @param @param entity
	 * @return void
	 * @throws
	 */
	<T> void update(T entity);

	void update(Object entity, String entityName);

	<T> int update(String hql, Object... params);

	<T> int updateBySql(String sql, Object... params);

	/* add */

	<T> Serializable save(T entity);

	void saveOrUpdate(Object entity);

	void initialize(Object entity);

	int executeHql(String hql, Object... params);

	int executeSql(String sql, Object... params);

	int executeHqlByMap(String hql, Map<String, Object> params);

	int executeSqlByMap(String sql, Map<String, Object> params);

	// HibernateTemplate getHibernateTemplate();

	// void setHibernateTemplate(HibernateTemplate hibernateTemplate);

}
