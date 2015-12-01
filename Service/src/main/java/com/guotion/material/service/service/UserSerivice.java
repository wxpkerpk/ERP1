package com.guotion.material.service.service;

import com.guotion.material.service.bean.QueryResult;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.entity.User;

import java.util.List;
import java.util.Queue;

/**
 * Created by wx on 2015/10/31.
 */
public interface UserSerivice {
    User getByid(String id);
    User getByAccount(String account);
    void add(User user);
    QueryResult<User>search(int skip,int num,int type,String keyWord);
    void update(User user);
    void delete(String []ids);
}
