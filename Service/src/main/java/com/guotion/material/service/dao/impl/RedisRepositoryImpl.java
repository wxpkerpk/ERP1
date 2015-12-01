package com.guotion.material.service.dao.impl;

import com.guotion.material.service.dao.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by wx on 2015/9/11.
 */
@Repository
public class RedisRepositoryImpl implements RedisRepository {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void add(final String key, final Object value,long expireTime) {
        redisTemplate.opsForSet().add(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

    }

    @Override
    public Object get(final String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    @Override
    public void delete(final String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void addToQueue(final String key,final Object o)
    {
        redisTemplate.opsForList().rightPush(key,o);
    }

    @Override
    public Object pollFromQueue(final String key)
    {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public long getQueueSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public boolean isMember(String key) {
        return redisTemplate.opsForSet().isMember(key,"1");
    }
}
