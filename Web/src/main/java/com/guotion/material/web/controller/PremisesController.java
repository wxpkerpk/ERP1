package com.guotion.material.web.controller;

import com.google.gson.Gson;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.entity.Premises;
import com.guotion.material.service.service.AddressService;
import com.guotion.material.service.service.PremisesService;
import com.guotion.material.web.message.MapMessage;
import com.guotion.material.web.message.NetMessage;
import com.guotion.material.web.util.response.ResponseClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wx on 15/11/28.
 */
@Controller
@RequestMapping(value = "premises.do")
public class PremisesController {

    @Autowired
    private PremisesService premisesService;


    @Autowired
    private AddressService addressService;


    @RequestMapping(value = "add.do",method = RequestMethod.POST)

    public void addPremises(HttpServletRequest request, HttpServletResponse response)
    {
        Integer addressId=Integer.parseInt( request.getParameter("address[]"));
        Address address=addressService.getById(addressId);
        String name=request.getParameter("name[]");

        NetMessage message=new MapMessage();
        if(address!=null){
            Premises premises=new Premises();
            Premises premisest=premisesService.getById(name);
            if(premisest==null) {
                premises.setName(name);
            }else
            {
                premises.setName(premisest.getName());
            }
            premises.setAddress(address);
            premisesService.add(premises);
            message.putData("msg","ok");

        }else
        {
            message.putData("msg","fail");
        }

        ResponseClientUtil.response(response,message);
    }

    @RequestMapping(value = "getByAddress.do",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getByAddress(HttpServletRequest request,HttpServletResponse response)
    {

        Integer addressId=Integer.parseInt( request.getParameter("address"));
        List<Premises>premisesList=premisesService.getByAdress(addressId);
        return new Gson().toJson(premisesList);
    }

}
