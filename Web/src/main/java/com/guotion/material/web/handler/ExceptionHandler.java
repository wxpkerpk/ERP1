package com.guotion.material.web.handler;

import com.guotion.material.web.util.response.ResponseDataHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/31 0031.
 */

/**
 * 捕捉运行期异常(1.记录日志;2.统一返回服务器异常)
 */

@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    private Log log = LogFactory.getLog("ERROR");

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        Map<String,String> map = httpServletRequest.getParameterMap();
       /* JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String,String> entry : map.entrySet()){
            try {
                jsonObject.put(entry.getKey(),entry.getValue());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }*/
        log.error("---------------------SPLIT LINE---------------------------");
        log.error("request uri :"+httpServletRequest.getRequestURI());
        log.error("request params :"+map.toString());
        log.error("err message :"+getErrorMessage(e));
        ResponseDataHandler.dealBoolean(false, 500, httpServletResponse);
        return null;
    }

    private String getErrorMessage(Exception e){
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        pw.close();
        return writer.toString();
    }
}
