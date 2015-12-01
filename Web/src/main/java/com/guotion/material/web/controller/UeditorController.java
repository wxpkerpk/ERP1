package com.guotion.material.web.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by wx on 2015/10/28.
 */
@Controller
@RequestMapping(value = "init.do")
public class UeditorController {

    @RequestMapping(value = "init.do",method = RequestMethod.GET)
    @ResponseBody
    public String init(HttpServletRequest request,HttpServletResponse response)
    {
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getRealPath( "/" );
       return      new ActionEnter( request, rootPath ).exec() ;
    }
}
