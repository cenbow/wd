package com.okwei.common;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.okwei.bean.vo.ReturnStatus;

/**
 * 
 * @author Administrator
 *
 */
public class EnumSerializer implements JsonSerializer<ReturnStatus>, JsonDeserializer<ReturnStatus> {

	// 对象转为Json时调用,实现JsonSerializer<ReturnStatus>接口
	@Override
	public JsonElement serialize(ReturnStatus state, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(state.getValue());
	}

	// json转为对象时调用,实现JsonDeserializer<ReturnStatus>接口
	@Override
	public ReturnStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json.getAsInt() < ReturnStatus.values().length)
			return ReturnStatus.values()[json.getAsInt()];
		return null;
	}

}
