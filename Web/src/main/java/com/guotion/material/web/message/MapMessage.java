package com.guotion.material.web.message;


import com.guotion.material.web.constant.CommonConstant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 
 * @author xdy
 * @version 2
 */

public class MapMessage extends NetMessage {

	HashMap<String, Object> data;

	public MapMessage() {
		data = new HashMap<String, Object>();
	}

	@Override
	public int getState() {
		return (Integer) data.get(CommonConstant.KEY.KEY_STATE);
	}

	@Override
	public Map getDatas() {
		return data;
	}

	@Override
	public void setState(int state) {
		data.put(CommonConstant.KEY.KEY_STATE, state);
	}

	@Override
	public void putData(String key, Object value) {
		data.put(key, value);
	}

	@Override
	public Object getData(String key) {
		return data.get(key);
	}

}
