package com.guotion.material.web.constant;

public interface CommonConstant {

    interface KEY {
        /**
         * 附加消息的key
         */
        String ATTACH_TEXT_KEY = "attach_text";
        /**
         * 返回数据的Key
         */
        String RESPONSE_DATA_KEY = "response_data_key";
        /**
         * MapMessage中状态的key
         */
        String KEY_STATE = "state";
    }

     interface VALUE {
        /**
         * 操作成功
         */
         int RESULT_SUCCESS = 0;
        /**
         * 操作失败
         */
         int RESULT_FAILURE = 1;
    }
}
