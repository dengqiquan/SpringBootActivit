package com.fcore.boot.domain;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository  
public class RedisDao {  
  
    @Autowired  
    RedisTemplate<Object,Object> redisTemplate;  
      
    @Resource(name="redisTemplate")  
    ValueOperations<Object,Object> valOps;  
      
    public void setValue(Object key,Object value){  
        valOps.set(key, value);
    }  
      
    public Object getValue(String id){  
        return valOps.get(id);  
    }     
}  