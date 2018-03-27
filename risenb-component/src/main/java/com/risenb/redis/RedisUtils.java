package com.risenb.redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

public class RedisUtils<K, V> {
	@Autowired  
    protected RedisTemplate<K, V> redisTemplate;
	

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 *  新增
	 * @param demoBean
	 * @return
	 */
	public boolean add(DemoBean demoBean) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key = serializer.serialize(demoBean.getId());
				byte[] name = serializer.serialize(demoBean.getName());
				return connection.setNX(key, name);
			}
		});
		return result;
	}
	
	public boolean add(String key,String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(serializer.serialize(key), serializer.serialize(value));
			}
		});
		return result;
	}

	/**
	 * 批量新增 使用pipeline方式 <br>
	 * ------------------------------<br>
	 * 
	 * @param list
	 * @return
	 */
	public boolean add(List<DemoBean> list) {
		Assert.notEmpty(list);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				for (DemoBean demoBean : list) {
					byte[] key = serializer.serialize(demoBean.getId());
					byte[] name = serializer.serialize(demoBean.getName());
					connection.setNX(key, name);
				}
				return true;
			}
		}, false, true);
		return result;
	}
	
	public boolean add(Map<String,String> map) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				 RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				for (Map.Entry<String,String> entry : map.entrySet()) {
					
					connection.setNX(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
				}
				return true;
			}
		}, false, true);
		return result;
	}
	/** 
     * 修改  
     * <br>------------------------------<br> 
     * @param user 
     * @return  
     */  
	public boolean update(DemoBean demoBean) {  
        String key = demoBean.getId();  
        if (get(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        }  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] key  = serializer.serialize(demoBean.getId());  
                byte[] name = serializer.serialize(demoBean.getName());  
                connection.set(key, name);  
                return true;  
            }  
        });  
        return result;  
    } 
	
	public boolean update(String keys, String value) {  
        if (get(keys) == null) {  
            throw new NullPointerException("数据行不存在, key = " + keys);  
        }  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] key  = serializer.serialize(keys);  
                byte[] name = serializer.serialize(value);  
                connection.set(key, name);  
                return true;  
            }  
        });  
        return result;  
    }

	 /**  
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return 
     */  
    public DemoBean get(String keyId) {  
    	DemoBean result = redisTemplate.execute(new RedisCallback<DemoBean>() {  
            public DemoBean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] key = serializer.serialize(keyId);  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String name = serializer.deserialize(value);  
                return new DemoBean(keyId, name);  
            }  
        });  
        return result;  
    } 
    
    public String getValue(String keyId) {  
    	String result = redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] key = serializer.serialize(keyId);  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String values = serializer.deserialize(value);  
                return values;  
            }  
        });  
        return result;  
    } 
    
    /**  
     * 删除 
     * <br>------------------------------<br> 
     * @param key 
     */ 
    public void delete(K key) {  
        redisTemplate.delete(key);  
    } 
    
    /**
     * 批量删除
     * @param keys
     */
    public void delete(List<K> keys) {  
        redisTemplate.delete(keys);  
    }   
}
