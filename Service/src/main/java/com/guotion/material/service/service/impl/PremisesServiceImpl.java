package com.guotion.material.service.service.impl;

import com.guotion.material.service.dao.PremisesDAO;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.entity.Premises;
import com.guotion.material.service.service.PremisesService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.*;

/**
 * Created by wx on 15/11/27.
 */
@Service
@Transactional
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesDAO premisesDAO;
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add(Premises premises) {
        premisesDAO.add(Premises.class,premises);
    }

    @Override
    public void delete(String[] ids) {
        String hql="delete from "+getEntityName(Premises.class)+" o where o.id in (:ids)";
        sessionFactory.getCurrentSession().createQuery(hql).setParameterList("ids",ids).executeUpdate();

    }
    protected  <T> String getEntityName(Class<T> entityClass){
        String entityName = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if(entity.name() != null && !"".equals(entity.name())){
            entityName = entity.name();
        }
        return entityName;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Premises> getByAdress(Integer addressId) {

        String name=getEntityName(Premises.class);
        String hql="from "+name +" o where o.address.id in (:ids)";
        List<Integer>leafs=getAdressLeaf(addressId);
        List<Premises>premisesList=sessionFactory.getCurrentSession().createQuery(hql).setParameterList("ids",leafs).list();
        return premisesList;
    }

    public List<Integer>getAdressLeaf(Integer rootAddressId)
    {
        List<Integer>leafs=new ArrayList<>();
        leafs.add(-1);
        Queue<Address>addressQueue=new LinkedList<>();
        Address root=premisesDAO.find(Address.class,rootAddressId);
        String hql="o.parentno=?";
        Object[]objects=new Object[1];
        if(root!=null){

            addressQueue.add(root);
            while(!addressQueue.isEmpty()){
                Address front=addressQueue.poll();
                leafs.add(front.getId());
                objects[0]=front.getId();
                List<Address>layers=premisesDAO.find(Address.class,hql,objects);
                if(layers.size()>0){
                    addressQueue.addAll(layers);
                }
            }

        }
        return  leafs;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Premises getById(String id) {
        return premisesDAO.find(Premises.class,id);
    }
}
