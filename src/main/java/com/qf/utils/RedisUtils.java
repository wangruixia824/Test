package com.qf.utils;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtils {
    private RedisTemplate<String,String> redisTemplate;
    //写set方法
    public void setRedisTemplate(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    //新增数据
    public void ladd(String key,String value){
        redisTemplate.opsForList().rightPush(key,value);
    }

    //返回Redis中的List集合的最后一个元素
    public String getList(String key){
        return redisTemplate.opsForList().index(key,redisTemplate.opsForList().size(key)-1);
    }

    //验证是否存在指定的key
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
}
