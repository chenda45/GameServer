/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.redis;

import javolution.util.FastMap;
import javolution.util.FastTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 基于redis 缓存接口
 */
public interface JCache {

    /**
     *
     * @return
     */
    public void info();

    /**
     * @param key
     * @return
     * @
     */
    public Object getList(String key);
    /**
     *
     * @param key
     * @param list
     */
    public void putList(String key, ArrayList<?> list);

    /**
     * Remove an item from the cache
     */
    public void removeList(String key) ;
    /**
     *
     * @param key
     * @return
     */
    public FastTable<?> getFastTable(String key);
    /**
     *
     * @param key
     * @param list
     */
    public void putFastTable(String key, FastTable<?> list);
    /**
     *
     * @param key
     * @return
     * @
     */
    public FastMap<?,?> getFastMap(String key) ;
    /**
     * Remove an item from the cache
     */
    public void removeFastMap(String key) ;
    /**
     *
     * @param key
     * @param fastMap
     */
    public void putFastMap(String key, FastMap<?, ?> fastMap);

    /**
	 * Get an item from the cache, nontransactionally
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws JRedisCacheException
	 */
	public Object getObject(String key) ;

	/**
	 * Add an item to the cache, nontransactionally, with
	 * failfast semantics
	 * @param key
	 * @param value
	 * @
	 */
	public void putObject(String key, Serializable value) ;

    /**
     * Remove an item from the cache
     */
    public void removeObject(String key) ;

    /**
     *
     * @return
     * @throws JRedisCacheException
     */
	public FastTable<String> keys()  ;


    public void destroy() ;

    /**
     *  Queue
     * @param key
     * @param value
     */
    public void addQueue(String key, Serializable value);

    /**
     * poll  from queue
     * @param key
     */
    public Object pollFromQueue(String key);
    /**
     * peek  from queue
     * @param key
     */
    public Object peekFromQueue(String key);

}
