package com.okwei.myportal.bean.vo;

import java.util.ArrayList;
import java.util.List;

public class NoticeMsg {
	    private List<MsgKeyValue> glmsg;
	    private List<MsgKeyValue> imgmsg;
		public List<MsgKeyValue> getGlmsg() {
			if(glmsg==null)
				glmsg=new ArrayList<MsgKeyValue>();
			return glmsg;
		}
		public void setGlmsg(List<MsgKeyValue> glmsg) {
			this.glmsg = glmsg;
		}
		public List<MsgKeyValue> getImgmsg() {
			if(imgmsg==null)
				imgmsg=new ArrayList<MsgKeyValue>();
			return imgmsg;
		}
		public void setImgmsg(List<MsgKeyValue> imgmsg) {
			this.imgmsg = imgmsg;
		}
}
