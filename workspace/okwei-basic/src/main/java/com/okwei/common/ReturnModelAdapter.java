package com.okwei.common;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.okwei.bean.vo.ReturnStatus;

public class ReturnModelAdapter extends TypeAdapter<ReturnStatus> {

	@Override
	public ReturnStatus read(JsonReader reader) throws IOException {
		if(reader.peek() == JsonToken.NULL)
		{
			reader.nextNull();
			return null;
		}
		String status=reader.nextString();
		if(status.indexOf("Success")>-1)
		{
			return ReturnStatus.Success;
		}
		if(status.indexOf("DataError")>-1)
		{
			return ReturnStatus.DataError;
		}
		if(status.indexOf("LoginError")>-1)
		{
			return ReturnStatus.LoginError;
		}
		if(status.indexOf("ParamError")>-1)
		{
			return ReturnStatus.ParamError;
		}
		if(status.indexOf("SystemError")>-1)
		{
			return ReturnStatus.SystemError;
		}
		return null;
	}

	@Override
	public void write(JsonWriter writer, ReturnStatus status) throws IOException {
		// TODO Auto-generated method stub
		if(status ==null)
		{
			writer.nullValue();
			return;
		}
		switch(status.toString()){
		case "1":
			writer.value("1");
			break;
		case "-1":
			writer.value("-1");
			break;
		case "-2":
			writer.value("-2");
			break;
		case "-3":
			writer.value("-3");
			break;
		case "-4":
			writer.value("-4");
			break;
		}
		
	}

}
