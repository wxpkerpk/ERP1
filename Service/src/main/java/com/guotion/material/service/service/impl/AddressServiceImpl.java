package com.guotion.material.service.service.impl;

import com.google.gson.Gson;
import com.guotion.material.service.bean.AreaNode;
import com.guotion.material.service.dao.AddressDAO;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wx on 15/11/28.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDAO addressDAO;


    @Override
    @Cacheable(value = "gtCache")
    public String getAllArea() {
        List<AreaNode> areaNodeList=new ArrayList<>();
        List<Address>addressList=addressDAO.find(Address.class,null,null);
        for(Address a:addressList)
        {
            AreaNode areanode=new AreaNode();
            areanode.set_parentId(a.getParentno());
            areanode.setId(a.getId());
            if(a.getLeaf()==0){
                areanode.setState("closed");
            }
            else{
                areanode.setState("open");
            }
            areanode.setName(a.getAreaname());
            areaNodeList.add(areanode);
        }
        return new Gson().toJson(areaNodeList);
    }

    @Override
    public Address getById(Integer id) {
        return addressDAO.find(Address.class,id);
    }
}
