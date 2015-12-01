package com.guotion.material.service.service.impl;

import com.google.gson.Gson;
import com.guotion.material.service.bean.AreaNode;
import com.guotion.material.service.bean.QueryResult;
import com.guotion.material.service.dao.UserDAO;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.entity.User;
import com.guotion.material.service.service.UserSerivice;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wx on 15/11/27.
 */
@Service
@Transactional
public class UserServiceImpl implements UserSerivice {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SessionFactory sessionFactory;

    void init()
    {
         List<Address>addressList=userDAO.find(Address.class,null,null);
        for(Address address:addressList){
            String hql="o.parentno="+address.getId();
            List<Address>addresses=userDAO.find(Address.class,hql,null);
            if(addresses.size()>0){
                address.setLeaf(0);

            }else
            {
                address.setLeaf(1);
            }
            userDAO.update(Address.class,address);

        }
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User getByid(String id) {
        return userDAO.find(User.class,id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User getByAccount(String account) {
        String hql="o.account=?";
        Object []objects=new Object[1];
        objects[0]=account;
        List<User>userList=userDAO.find(User.class,hql,objects);

        return userList.size()>0?userList.get(0):null;
    }

    @Override
    public void add(User user) {
        userDAO.add(User.class,user);
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<User> search(int skip, int num, int type, String keyWord) {
        StringBuilder stringBuilder=new StringBuilder();
        List<Object>paramlist=new ArrayList<>();
        if(type!=-1)
        {
            stringBuilder.append("o.type= ? ");
            paramlist.add(type);
        }

        if(!keyWord.equals(""))
        {
            if(type!=-1)
            {
                stringBuilder.append(" and ");
            }
            stringBuilder.append(" ( o.tel like  ?");
            stringBuilder.append(" or o.account like ?");
            stringBuilder.append(" or o.name like ?)");
            paramlist.add("%"+keyWord+"%");
            paramlist.add("%"+keyWord+"%");
            paramlist.add("%"+keyWord+"%");


        }
        LinkedHashMap<String,String>order=new LinkedHashMap<>();
        order.put("o.id","desc");
        if(stringBuilder.length()==0)
        {
            return userDAO.getScrollData(User.class,skip, num,order);
        }else
        {
            String hql=stringBuilder.substring(0);
            return  userDAO.getScrollData(User.class,skip,num,hql, paramlist.toArray(),order);
        }
    }

    @Override
    public void update(User user) {
        userDAO.update(User.class,user);
    }

    @Override
    public void delete(String[] ids) {
        String hql="delete from t_user o where o.id in (:ids)";
        sessionFactory.getCurrentSession().createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
