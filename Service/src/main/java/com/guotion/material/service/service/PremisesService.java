package com.guotion.material.service.service;

import com.guotion.material.service.entity.Premises;

import java.util.List;

/**
 * Created by wx on 15/11/27.
 */
public interface PremisesService {
    void add(Premises premises);
    void delete(String []ids);
    List<Premises>getByAdress(Integer addressId);
    Premises getById(String id);
}
