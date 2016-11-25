package com.okwei.bean.enums;

public enum PushMsgEnum {

	/**
	 * 聊天离线消息
	 */
	liaotian(10),
	/**
	 * 团队消息
	 */
	tuandui(11),
	/**
	 * 请求加好友消息
	 */
	requestFriend(12),
	/**
	 * 回复加好友消息
	 */
	replayFriend(13);
	
	private final int step; 

    private PushMsgEnum(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
	
}
