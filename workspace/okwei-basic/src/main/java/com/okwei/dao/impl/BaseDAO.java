package com.okwei.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.common.transfor.MyTransformer;
import com.okwei.dao.IBaseDAO;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;

/**
 * 
 * @ClassName: BaseDAO
 * @Description: DAO基础类
 * @author xiehz
 * @date 2015年5月5日 下午4:09:11
 *
 */
@Repository
public class BaseDAO implements IBaseDAO {

//	@Autowired
//	private GetHibernateTemplate getHibernateTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;
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
	@Override
	public <T> T load(Class<T> entityClass, Serializable id) {
		return hibernateTemplate.load(entityClass, id);
	}

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
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return hibernateTemplate.get(entityClass, id);
	}

	/**
	 * 
	 * @Title: loadAll
	 * @Description: 加载PO的所有对象
	 * @param @param entityClass
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		return hibernateTemplate.loadAll(entityClass);
	}

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
	@Override
	public <T> List<T> find(String hql, Object... params) {
		return (List<T>) hibernateTemplate.find(hql, params);
	}

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
	@Override
	public <T> List<T> findPage(String hql, int startIndex, int pageSize, Object... params) {
		
		return (List<T>) hibernateTemplate.execute(new HibernateCallback<List<T>>() {			
			public List<T> doInHibernate(Session session) throws HibernateException {
				int start= startIndex-1;
				if(start<0)
					start=0;
				Query query = session.createQuery(hql);
				buildParameters(query, params);
				query.setFirstResult(start);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

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
	@Override
	public <T> PageResult<T> findPageResult(String hql, Limit limit, Object... params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		int totalCount = (int) count("select count(*) " + hql.substring(hql.toLowerCase().indexOf("from")), params);
		return new PageResult<T>(totalCount, limit, list);
	}

	@Override
	public <T> PageResult<T> findPageResultTrans(String hql, Class<T> clazz, Limit limit, Object... params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		int totalCount = (int) count("select count(*) " + hql.substring(hql.toLowerCase().indexOf("from")), params);
		return new PageResult<T>(totalCount, limit, list);
	}

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
	@Override
	public <T> List<T> findByMap(String hql, Map<String, Object> params) {
		return hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				buildParameters(query, params);
				return query.list();
			}
		});
	}

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
	@Override
	public <T> List<T> findPageByMap(String hql, int startIndex, int pageSize, Map<String, Object> params) {
		return hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				int start= startIndex-1;
				if(start<0)
					start=0;
				Query query = session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(pageSize);
				buildParameters(query, params);
				return query.list();
			}
		});
	}

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
	@Override
	public <T> PageResult<T> findPageResultByMap(String hql, Limit limit, Map<String, Object> params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		int totalCount = (int) countByMap("select count(*) " + hql.substring(hql.toLowerCase().indexOf("from")), params);
		return new PageResult<T>(totalCount, limit, list);
	}

	@Override
	public <T> PageResult<T> findPageResultTransByMap(String hql, Class<T> clazz, Limit limit, Map<String, Object> params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		int totalCount = (int) countByMap("select count(*) " + hql.substring(hql.toLowerCase().indexOf("from")), params);
		return new PageResult<T>(totalCount, limit, list);
	}

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
	@Override
	public <T> List<T> queryBySql(String sql, Object... params) {
		return (List<T>) hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				buildParameters(query, params);
				return query.list();
			}
		});
	}

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
	@Override
	public <T> List<T> queryPageBySql(String sql, int startIndex, int pageSize, Object... params) {
		return (List<T>) hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				int start= startIndex-1;
				if(start<0)
					start=0;
				SQLQuery query = session.createSQLQuery(sql);
				query.setFirstResult(start);
				query.setMaxResults(pageSize);
				buildParameters(query, params);
				return query.list();
			}
		});
	}

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
	@Override
	public <T> PageResult<T> queryPageResultBySql(String sql, Class<T> clazz, Limit limit, Object... params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		Integer totalCount=0;
		String keynum=RedisUtil.getString(this.getClass().getCanonicalName()+JsonUtil.objectToJson(params));
		if(!ObjectUtil.isEmpty(keynum)){
			totalCount=Integer.parseInt(keynum);
		}
		else
		{
		  totalCount = (int) countBySql("select count(1) " + sql.substring(sql.toLowerCase().indexOf("from")), params);
		  RedisUtil.setString(keynum, totalCount.toString(), 300);
		}
		return new PageResult<T>(totalCount, limit, list);
	}

	@Override
	public <T> PageResult<T> queryPageResultByMap(String sql, Class<T> clazz, Limit limit, Map<String, Object> params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		Integer totalCount=0;
		String keynum=RedisUtil.getString(this.getClass().getCanonicalName()+JsonUtil.objectToJson(params));
		if(!ObjectUtil.isEmpty(keynum)){
			totalCount=Integer.parseInt(keynum);
		}
		else
		{
		  totalCount = (int) countBySqlMap("select count(1) " + sql.substring(sql.toLowerCase().indexOf("from")), params);
		  RedisUtil.setString(keynum, totalCount.toString(), 300);
		}
		return new PageResult<T>(totalCount, limit, list);
	}

	@Override
	public <T> PageResult<T> queryPageResultBySqlWithCount(String sql, Class<T> clazz, Limit limit,Integer count,Object... params) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		
		
		return new PageResult<T>(count, limit, list);
	}

	@Override
	public <T> PageResult<T> queryPageResultByMapWithCount(String sql, Class<T> clazz, Limit limit,Integer count, Map<String, Object> params ) {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(clazz));
				query.setFirstResult(limit.getStart());
				query.setMaxResults(limit.getSize());
				buildParameters(query, params);
				return query.list();
			}
		});
		
		return new PageResult<T>(count, limit, list);
	}
	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria动态组装HQL查询
	 * @param @param criteria
	 * @param @return
	 * @return List<T>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) hibernateTemplate.findByCriteria(criteria);
	}

	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria动态组装HQL分页查询
	 * @param @param criteria
	 * @param @param startIndex
	 * @param @param size
	 * @param @return
	 * @return List<T>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(DetachedCriteria criteria, int startIndex, int size) {
		return (List<T>) hibernateTemplate.findByCriteria(criteria, startIndex, size);
	}

	/**
	 * @Title: findByExample
	 * @Description: 
	 *               根据对象example查询，简单的查询可以采用该方法，不用写HQL，避免属性名修改带来的隐性错误，仅限于做=查询，不支持排序控制
	 * @param @param t
	 * @param @return
	 * @param @throws DataAccessException
	 * @return List<T>
	 */
	@Override
	public <T> List<T> findByExample(T t) {
		return hibernateTemplate.findByExample(t);
	}

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
	@Override
	public <T> List<T> findByExample(T t, int startIndex, int size) {
		return hibernateTemplate.findByExample(t, startIndex, size);
	}

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
	@Override
	public <T> Map<?, ?> findMapBySql(String sql, Object... params) {
		return (Map<?, ?>) hibernateTemplate.execute(new HibernateCallback<Map<?, ?>>() {
			public Map<?, ?> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				buildParameters(query, params);
				query.setResultTransformer(MyTransformer.ALIAS_TO_ENTITY_MAP);
				List<Map<?, ?>> list = query.list();
				Map<Object, Object> map = new HashMap<Object, Object>();
				for (Map<?, ?> map2 : list) {
					map.put(map2.keySet().toArray()[0], map2.values().toArray()[0]);
				}
				return map;
			}
		});
	}

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
	@Override
	public <T> T getUniqueResultByHql(String hql, Object... objs) {
		List<T> list = (List<T>) find(hql, objs);
		if (list == null || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

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
	@Override
	public <T> T getUniqueSQLResult(String sql, Object... params) {
		List<T> list = (List<T>) queryBySql(sql, params);
		if (list == null || list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

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
	@Override
	public long count(String hql, Object... params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		buildParameters(query, params);
		return (long) query.uniqueResult();
	}

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
	@Override
	public long countBySql(String sql, Object... params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		buildParameters(query, params);
		Object result = query.uniqueResult();
		if (null != result) {
			return Long.parseLong(result.toString());
		}
		return (long) 0;
	}

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
	@Override
	public long countByMap(String hql, Map<String, Object> params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		buildParameters(query, params);
		return (long) query.uniqueResult();
	}

	@Override
	public long countBySqlMap(String sql, Map<String, Object> params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		buildParameters(query, params);
		Object result = query.uniqueResult();
		if (null != result) {
			return Long.parseLong(result.toString());
		}
		return (long) 0;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除PO
	 * @param @param entity
	 * @return void
	 * @throws
	 */
	@Override
	public <T> void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

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
	@Override
	public <T> int delete(Class<T> objClass, Serializable id) {
		return update("delete from " + objClass.getName() + " where id=?", id);
	}

	/**
	 * 
	 * @Title: deleteAll
	 * @Description: 删除PO所有实例
	 * @param @param objClass
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Override
	public <T> int deleteAll(Class<T> objClass) {
		return executeHql("delete from " + objClass.getName());
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param entity
	 * @param @param lockMode
	 * @return void
	 * @throws
	 */
	@Override
	public <T> void delete(T entity, LockMode lockMode) {
		hibernateTemplate.delete(entity, lockMode);
	}

	/**
	 * 
	 * @Title: deleteAll
	 * @Description: 删除PO所有实例
	 * @param @param entities
	 * @return void
	 * @throws
	 */
	@Override
	public <T> void deleteAll(Collection<Object> entities) {
		hibernateTemplate.deleteAll(entities);
	}

	@Override
	public <T> void update(T entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public void update(Object entity, String entityName) {
		hibernateTemplate.update(entityName, entity);
	}

	@Override
	public <T> int update(String hql, Object... params) {
		return executeHql(hql, params);
	}

	@Override
	public <T> int updateBySql(String sql, Object... params) {
		return executeSql(sql, params);
	}

	@Override
	public <T> Serializable save(T entity) {
		return hibernateTemplate.save(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public void initialize(Object entity) {
		this.hibernateTemplate.initialize(entity);
	}

	/**
	 *  由于 executeHqlByMap(hql, map)  executeHql(hql, map) 编译不报错，这2个方法极容易搞混，其他类似
	 */
	@Override
	public int executeHql(String hql, Object... params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		buildParameters(query, params);
		return query.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object... params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		buildParameters(query, params);
		return query.executeUpdate();
	};

	/**
	 *  由于 executeHqlByMap(hql, map)  executeHql(hql, map) 编译不报错，这2个方法极容易搞混，其他类似
	 */
	@Override
	public int executeHqlByMap(String hql, Map<String, Object> params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		buildParameters(query, params);
		return query.executeUpdate();
	}

	@Override
	public int executeSqlByMap(String sql, Map<String, Object> params) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		buildParameters(query, params);
		return query.executeUpdate();
	}

	/*
	 * @Override public HibernateTemplate getHibernateTemplate() { // TODO
	 * Auto-generated method stub return hibernateTemplate; }
	 * 
	 * @Override public void setHibernateTemplate(HibernateTemplate
	 * hibernateTemplate) { this.hibernateTemplate = hibernateTemplate; }
	 */

	/* private method */

	private Session getCurrentSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	private void buildParameters(Query query, Object[] params) {
		int flag = 0;
		if (params == null || params.length == 0) {
			return;
		}
		for (Object item : params) {
			query.setParameter(flag++, item);
		}
	}

	private void buildParameters(Query query, Map<String, Object> params) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getKey().indexOf("date") > -1) {
				query.setTimestamp(entry.getKey(), (Date) entry.getValue());
			} else {
				Object obj = entry.getValue();
				if (obj instanceof Collection<?>) {
					query.setParameterList(entry.getKey(), (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(entry.getKey(), (Object[]) obj);
				} else {
					query.setParameter(entry.getKey(), obj);
				}
			}
		}
	}
	/**
	 * 
	 * @Title: getUniqueResultByHql
	 * @Description: hql查询，获取PO
	 * @param @param hql
	 * @param @param objs
	 * @param @return
	 * @return T
	 * @throws
	 */
	@Override
	public <T> T getNotUniqueResultByHql(String hql, Object... objs) {
		// TODO Auto-generated method stub
		List<T> list = (List<T>) find(hql, objs);
		if (list == null || list.size() == 0 ) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public <T> List<T> findSQLByMap(String sql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return (List<T>) hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				buildParameters(query, params);
				return query.list();
			}
		});
	}

}