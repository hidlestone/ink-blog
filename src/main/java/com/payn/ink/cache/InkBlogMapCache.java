package com.payn.ink.cache;

import java.util.Map;

/**
 * map 缓存实现
 *
 * @author: payn
 * @date: 2020/8/22 22:54
 */
public class InkBlogMapCache {

	/**
	 * 默认存储1024个缓存
	 */
	private static final int DEFAULT_CACHES = 1024;

	private static final InkBlogMapCache INSTANCE = new InkBlogMapCache();

	/**
	 * 获取缓存单例
	 */
	public static InkBlogMapCache getInstance() {
		return INSTANCE;
	}

	/**
	 * 缓存容器
	 */
	private Map<String, CacheObject> cachePool;

	/**
	 * 读取一个缓存
	 */
	public <T> T get(String key) {
		CacheObject cacheObject = cachePool.get(key);
		if (null != cacheObject) {
			long cur = System.currentTimeMillis() / 1000;
			//未过期直接返回
			if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
				Object result = cacheObject.getValue();
				return (T) result;
			}
			//已过期直接删除
			if (cur > cacheObject.getExpired()) {
				cachePool.remove(key);
			}
		}
		return null;
	}

	/**
	 * 读取一个hash类型缓存
	 */
	public <T> T hget(String key, String field) {
		key = key + ":" + field;
		return this.get(key);
	}

	/**
	 * 设置一个缓存
	 */
	public void set(String key, Object value) {
		this.set(key, value, -1);
	}

	/**
	 * 设置一个缓存并带过期时间
	 */
	public void set(String key, Object value, long expired) {
		expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
		//cachePool大于800时，强制清空缓存池，这个操作有些粗暴会导致误删问题，后期考虑用redis替代MapCache优化
		if (cachePool.size() > 800) {
			cachePool.clear();
		}
		CacheObject cacheObject = new CacheObject(key, value, expired);
		cachePool.put(key, cacheObject);
	}

	/**
	 * 设置一个hash缓存
	 */
	public void hset(String key, String field, Object value) {
		this.hset(key, field, value, -1);
	}

	/**
	 * 设置一个hash缓存并带过期时间
	 */
	public void hset(String key, String field, Object value, long expired) {
		key = key + ":" + field;
		expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
		CacheObject cacheObject = new CacheObject(key, value, expired);
		cachePool.put(key, cacheObject);
	}

	/**
	 * 根据key删除缓存
	 */
	public void del(String key) {
		cachePool.remove(key);
	}

	/**
	 * 根据key和field删除缓存
	 */
	public void hdel(String key, String field) {
		key = key + ":" + field;
		this.del(key);
	}

	/**
	 * 清空缓存
	 */
	public void clean() {
		cachePool.clear();
	}

	static class CacheObject {
		private String key;
		private Object value;
		private long expired;

		public CacheObject(String key, Object value, long expired) {
			this.key = key;
			this.value = value;
			this.expired = expired;
		}

		public String getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}

		public long getExpired() {
			return expired;
		}
	}
}
