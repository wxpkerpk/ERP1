package com.guotion.material.web.util.response;



import com.guotion.material.web.constant.CommonConstant;
import com.guotion.material.web.message.MapMessage;
import com.guotion.material.web.message.NetMessage;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2014/9/8.
 */
public class ResponseDataHandler {

    //final static String ERROR_CODE = "err_code";

    public static void dealResponse(Object object,int attach,HttpServletResponse response){
        NetMessage message = new MapMessage();

        if(object!=null){
            message.setState(CommonConstant.VALUE.RESULT_SUCCESS);
            message.putData(CommonConstant.KEY.RESPONSE_DATA_KEY,object);
        }else{
            message.setState(attach);
            //message.putData(ERROR_CODE,attach);
        }
        ResponseClientUtil.response(response,message);
    }

    public static void dealBoolean(boolean bool,int attach,HttpServletResponse response){
        NetMessage message = new MapMessage();
        if(bool)
            message.setState(CommonConstant.VALUE.RESULT_SUCCESS);
        else {
            message.setState(attach);
            //message.putData(ERROR_CODE,attach);
        }
        ResponseClientUtil.response(response,message);
    }
}
