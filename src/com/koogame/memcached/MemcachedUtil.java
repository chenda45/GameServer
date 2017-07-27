package com.koogame.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "MemcachedUtil")
public class MemcachedUtil {

	private Logger log = Logger.getLogger(MemcachedUtil.class); 

	@Autowired
	private MemcachedClient memcachedClient;
	
	public <T> T findFromCached(String type, int uid) {
		T obj;
		try { 
			obj = memcachedClient.get(type + uid);
		} catch (TimeoutException e) {
			log.error(e);
			obj = null;
		} catch (InterruptedException e) {
			log.error(e);
			obj = null;
		} catch (MemcachedException e) {
			log.error(e);
			obj = null;
		}  
		return obj;
	}


	public <T> boolean setCache(String type, int key, T value) {
		try {            
			return memcachedClient.set(type + key, 0, value); 
		} catch (TimeoutException e) { 
			log.error(e.getMessage(), e); 
		} catch (InterruptedException e) { 
			log.error(e.getMessage(), e); 
		} catch (MemcachedException e) { 
			log.error(e.getMessage(), e); 
		} 
		return false; 
	}


	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}


}
