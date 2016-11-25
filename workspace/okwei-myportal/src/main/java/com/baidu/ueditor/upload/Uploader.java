package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

/*		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else if("saveImg".equals(this.conf.get("isBase64"))){
			state =BinaryUploader.saveImg(this.request, this.conf);
		}
		else {
			state = BinaryUploader.save(this.request, this.conf);
		}*/

		if("saveImg".equals(this.conf.get("isBase64"))){
			state =BinaryUploader.saveImg(this.request, this.conf);
		}else{
			state = new BaseState(false, AppInfo.INVALID_ACTION);
		}
		
		return state;
	}
}
