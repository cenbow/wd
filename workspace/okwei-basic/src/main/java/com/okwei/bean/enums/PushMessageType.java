package com.okwei.bean.enums;

public enum PushMessageType {
//	 1.消息类型(type)定义：
	offline(10),
//	    2.聊天离线消息：10
	team(11),
//	    3.团队消息：11
	system(20),
//	    4.系统公告：20
	activity(21),
//	    5.活动推送：21
	wallet(30),
//	    6.微店钱包：30
	buyorder(31),
//	    7.购买订单：31
	sellorder(32),
//	    8.销售订单：32
	logistics(33),
//	    9.物流通知：33
	apply(40),
//	    10.供应商申请：40
	insteadapply(41),
//	    11.供应商代申请：41
	verifyport(42);
//	    12.认证点申请：42
	private final int step; 

    private PushMessageType(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }

}
