package com.okwei.appinterface.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.okwei.common.PropertyFactory;
import com.okwei.util.SerializeUtil;

/**
 * 
 * @ClassName: RedisUtil
 * @Description: redis操作工具类
 * @author xiehz
 * @date 2015年6月4日 下午3:55:43
 *
 */
public class RedisUtil {

	private static Log logger = LogFactory.getLog(RedisUtil.class);

	// Redis服务器IP
	private static String ADDR_ARRAY = PropertyFactory.getPropertyValue("redis", "redis.ip");

	// Redis的端口号
	private static int PORT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "redis.port"));

	// 访问密码
	private static String AUTH = PropertyFactory.getPropertyValue("redis", "redis.auth");

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxActive"));;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxIdle"));;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxWait"));;

	// 超时时间
	private static int TIMEOUT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "timeOut"));;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnBorrow"));

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_RETURN = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnReturn"));

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWait(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
		config.setTestOnReturn(TEST_ON_RETURN);
		try {
			jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT);
			Jedis js = jedisPool.getResource();
			jedisPool.returnResource(js);
		} catch (Exception ex) {
			jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT, AUTH);
		}

	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool == null) {
				poolInit();
			}
			return jedisPool.getResource();
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 设置string
	 * @param @param key
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value) {
		value = StringUtils.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error("setString(key,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 设置string及过期时间
	 * @param @param key
	 * @param @param seconds
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value, int seconds) {
		value = StringUtils.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("setString(key,seconds,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 获取String值
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getString(String key) {
		Jedis jedis = getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			logger.error("getString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error("setByte(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value, int seconds) {
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("setByte(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static byte[] getByte(byte[] key) {
		Jedis jedis = getJedis();
		try {
			byte[] value = jedis.get(key);
			return value;
		} catch (Exception e) {
			logger.error("getByte(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value) {
		if (value == null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			logger.error("setObject(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value, int seconds) {
		if (value == null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(value));
		} catch (Exception e) {
			logger.error("setObject(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Object getObject(String key) {
		Jedis jedis = getJedis();
		try {
			byte[] object = jedis.get(key.getBytes());
			return (Object) SerializeUtil.unserialize(object);
		} catch (Exception ex) {
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(byte[] key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(String key, int secondes) {
		Jedis jedis = getJedis();
		try {
			Object obj = new Object();
			try {
				byte[] object = jedis.get(key.getBytes());
				obj = (Object) SerializeUtil.unserialize(object);
			} catch (Exception e) {
				obj = null;
			}
			if (obj != null)
				jedis.expire(key.getBytes(), secondes);
		} catch (Exception e) {
			logger.error("setExpire(key) error : " + e);
		}
		// 释放连接
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(byte[] key, int secondes) {
		Jedis jedis = getJedis();
		try {
			if (key != null)
				jedis.expire(key, secondes);
		} catch (Exception e) {
			logger.error("setExpire(key) error : " + e);
		}
		// 释放连接
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 设置缓存信息
	 * 
	 * @param tiket
	 * @param object
	 */
	public static void SetTiketObject(String tiket, Object object) {
		// 从连接池获取Jedis连接

		if (object == null)
			return;
		Jedis jedisConn = getJedis();
		try {
			jedisConn.set(tiket.getBytes(), SerializeUtil.serialize(object));
			// jedisConn.set(tiket, JsonUtil.objectToJson(object));
			// 1小时过期
			jedisConn.expire(tiket.getBytes(), 3600);
		} catch (Exception ex) {

		} finally {
			// 释放连接
			if (jedisConn != null)
				returnResource(jedisConn);
		}

	}

	/**
	 * 设置缓存信息
	 * 
	 * @param tiket
	 * @param object
	 */
	public static void SetTiketObject(String tiket, Object object, int secondes) {
		if (object == null)
			return;
		// 从连接池获取Jedis连接
		Jedis jedisConn = getJedis();
		try {
			jedisConn.set(tiket.getBytes(), SerializeUtil.serialize(object));
			jedisConn.expire(tiket.getBytes(), secondes);
		} catch (Exception ex) {

		}
		// 释放连接
		finally {
			if (jedisConn != null)
				returnResource(jedisConn);
		}

	}

	public static void SetExpireObject(String tiket, int secondes) {
		// 从连接池获取Jedis连接
		Jedis jedisConn = getJedis();
		try {
			/*
			 * jedisConn.set(tiket.getBytes(), SerializeUtil.serialize(object));
			 * jedisConn.expire(tiket.getBytes(), secondes);
			 */
			Object ob = new Object();
			try {
				byte[] object = jedisConn.get(tiket.getBytes());
				ob = (Object) SerializeUtil.unserialize(object);
			} catch (Exception e) {
				ob = null;
			}

			if (ob != null)
				jedisConn.expire(tiket.getBytes(), secondes);

		} catch (Exception ex) {

		}
		// 释放连接
		finally {
			if (jedisConn != null)
				returnResource(jedisConn);
		}
	}

	/**
	 * 获取缓存信息
	 * 
	 * @param tiket
	 * @return
	 */
	public static Object GetObjectByTiket(String tiket) {
		Jedis jedis = getJedis();
		try {
			byte[] object = jedis.get(tiket.getBytes());
			return (Object) SerializeUtil.unserialize(object);

		} catch (Exception ex) {
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}

	}

	/**
	 * 删掉缓存
	 * 
	 * @param tiket
	 * @return
	 */
	public static boolean removeObject(String tiket) {
		Jedis jedis = getJedis();
		boolean result = false;
		try {
			jedis.del(tiket);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
		return result;
	}

	/******** 欧阳增加 begin ********/

	/**
	 * 缓存字符串
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void setValue(String key, String value, int seconds) {
		// 从连接池获取Jedis连接
		Jedis jedisConn = getJedis();
		try {
			jedisConn.setex(key, seconds, value);
		} catch (Exception ex) {

		}
		// 释放连接
		finally {
			if (jedisConn != null)
				returnResource(jedisConn);
		}

	}

	/**
	 * 缓存字符串
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void setValue(String key, String value) {
		// 从连接池获取Jedis连接
		Jedis jedisConn = getJedis();
		try {
			jedisConn.set(key, value);
		} catch (Exception ex) {

		}
		// 释放连接
		finally {
			if (jedisConn != null)
				returnResource(jedisConn);
		}

	}

	/**
	 * 获取缓存的字符串
	 * 
	 * @param tiket
	 * @return
	 */
	public static String getValue(String key) {
		Jedis jedis = getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} catch (Exception ex) {
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}

	}
	/******** 欧阳增加end ********/
}
