package com.ds.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：redis工具类
 *
 * <p> 创建时间：Apr 29, 2018 10:07:30 PM </p> 
 *
 *@作者 小D课堂  小D
 */
@Component
public class RedisClient {

	
	
	@Autowired
	private StringRedisTemplate redisTpl; //jdbcTemplate


	/**
	 * 功能描述：设置key-value到redis中
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key ,String value){
		try{
			redisTpl.opsForValue().set(key, value);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**
	 * 功能描述：通过key获取缓存里面的值
	 * @param key
	 * @return
	 */
	public String get(String key){
		return redisTpl.opsForValue().get(key);
	}

	/**
	 * 功能描述：设置某个key过期时间
	 * @param key
	 * @param time
	 * @return
	 */
	  public boolean expire(String key,long time){
	        try {
	            if(time>0){
					redisTpl.expire(key, time, TimeUnit.SECONDS);
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }




	  /**
	   * 功能描述：根据key 获取过期时间
	   * @param key
	   * @return
	   */
	  public long getExpire(String key){
	        return redisTpl.getExpire(key,TimeUnit.SECONDS);
	    }


	  	/**
	     * 递增
	     * @param key 键
	     * @return
	     */
	    public long incr(String key, long delta){
	        return redisTpl.opsForValue().increment(key, delta);
	    }


	    /**
	     * 递减
	     * @param key 键
	     * @param delta 要减少几
	     * @return
	     */
	    public long decr(String key, long delta){
	        return redisTpl.opsForValue().increment(key, -delta);
	    }

	    //==============Map结构=====================


	    //==============List结构=====================



	    
}
