package com.guotion.material.web.controller;

import com.google.gson.Gson;
import com.guotion.material.service.bean.AreaNode;
import com.guotion.material.service.bean.QueryResult;
import com.guotion.material.service.entity.Address;
import com.guotion.material.service.entity.Premises;
import com.guotion.material.service.entity.User;
import com.guotion.material.service.service.AddressService;
import com.guotion.material.service.service.PremisesService;
import com.guotion.material.service.service.UserSerivice;
import com.guotion.material.web.message.MapMessage;
import com.guotion.material.web.message.NetMessage;
import com.guotion.material.web.util.response.ResponseClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wx on 2015/11/1.
 */
@Controller
@RequestMapping(value = "user.do")
public class UserController {
    @Autowired
    private UserSerivice userSerivice;
    @Autowired
    private AddressService addressService;


    @Autowired
    private PremisesService premisesService;
    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    public void register(HttpServletRequest request,HttpServletResponse response, @RequestParam String account,@RequestParam String passWord,@RequestParam int type
            ,@RequestParam String name,@RequestParam String tel,@RequestParam int area,@RequestParam int sex)
    {

        NetMessage message=new MapMessage();

        if(userSerivice.getByAccount(account)!=null) {
            message.putData("msg","fail");
        }else
        {

            User user =new User();
            user.setAccount(account);
            user.setAddress(addressService.getById(area));

            user.setTel(tel);
            user.setSex(sex);
            user.setPassword(passWord);
            user.setState(1);
            user.setName(name);
            user.setType(type);
            String premisesId=request.getParameter("premises");
            user.setPremises(premisesService.getById(premisesId));
            userSerivice.add(user);
            message.putData("msg","ok");


        }
        ResponseClientUtil.response(response,message);
    }
    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public void update(HttpServletRequest request,HttpServletResponse response,@RequestParam String account,@RequestParam String passWord
            ,@RequestParam String name,@RequestParam String tel,@RequestParam int sex)
    {

        NetMessage message=new MapMessage();




            User user =userSerivice.getByAccount(account);
            if(user!=null) {
                user.setTel(tel);
                user.setSex(sex);
                user.setPassword(passWord);
                user.setState(1);
                user.setName(name);
                userSerivice.update(user);
            }
            message.putData("msg","ok");

        ResponseClientUtil.response(response,message);
    }


    @RequestMapping(value = "getAllArea.do",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getALLArea(HttpServletRequest request, HttpServletResponse response)
    {

       String areaNodeString=addressService.getAllArea();

        return areaNodeString;
    }

    @RequestMapping(value = "search.do",method = RequestMethod.GET)
    public void search(HttpServletRequest request,HttpServletResponse response,@RequestParam int page,@RequestParam int rows,@RequestParam String keyWord,@RequestParam int type)
    {
        NetMessage message=new MapMessage();
        QueryResult<User>userQueryResult=userSerivice.search((page-1)*rows,rows,type,keyWord);

        message.putData("rows",userQueryResult.getList());
        message.putData("total",userQueryResult.getTotalRecord());
        ResponseClientUtil.response(response,message);
    }

    @RequestMapping(value = "getUpdate.do",method = RequestMethod.GET)
    public ModelAndView getUpdate(HttpServletRequest request,HttpServletResponse response,@RequestParam String id)
    {
        User user=userSerivice.getByid(id);
        ModelAndView modelAndView=new ModelAndView();
        if(user!=null){
             modelAndView.addObject("user",user);
            modelAndView.setViewName("updateUser");

        }
        return modelAndView;

    }
    @RequestMapping(value = "updateArea.do",method = RequestMethod.POST)
    public void updateArea(HttpServletRequest request,HttpServletResponse response,@RequestParam int area,@RequestParam String premises,@RequestParam String account)
    {
        NetMessage message=new MapMessage();

        User user =userSerivice.getByAccount(account);
        if(user!=null) {
            user.setAddress(addressService.getById(area));
            user.setPremises(premisesService.getById(premises));
            userSerivice.update(user);
        }
        message.putData("msg","ok");

        ResponseClientUtil.response(response,message);

    }

    @RequestMapping(value = "delete.do",method = RequestMethod.POST)
    public void delete(HttpServletRequest request,HttpServletResponse response,@RequestParam String ids)
    {
        String []id=ids.split(",");
        userSerivice.delete(id);
        NetMessage message=new MapMessage();
        message.putData("msg","ok");
        ResponseClientUtil.response(response,message);



    }





}
