package com.guotion.material.service.dao;

/**
 * Created by wx on 2015/9/11.
 */
public interface RedisRepository {

    void add(String key, Object value,long expire);

    Object get(String key);

    void delete(String key);
    void addToQueue(final String key,final Object o);
    Object pollFromQueue(final String key);
    long getQueueSize(final String key);
    boolean isMember(String key);

}
