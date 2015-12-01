package com.guotion.material.web.util.response;


import com.google.gson.Gson;
import com.guotion.material.web.message.NetMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/8/24.
 */
public class ResponseClientUtil {
    private static Gson gson = new Gson();
    private static final String RESPONSE_CHARSET ="UTF-8";

    public static void response(HttpServletResponse response,NetMessage message){
        //打印相应消息
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString()+ " 响应消息:"+gson.toJson(message.getDatas()));
        //response.setHeader("Access-Control-Allow-Origin","*");//资源交叉访问问题,会出现session问题
        //response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        try {
            response.setContentType("text/html;charset="+RESPONSE_CHARSET);
            PrintWriter writer = response.getWriter();
            writer.write(gson.toJson(message.getDatas()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
