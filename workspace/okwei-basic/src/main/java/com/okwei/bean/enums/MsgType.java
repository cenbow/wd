package com.okwei.bean.enums;

public enum MsgType {
	
//	1.	发布产品；
//	供应商发布新产品时，在下游分销商的新品频道推送消息
	publishproduct(1),
//	2.	供应商主动推送某产品；
//	供应商可在自己的已发布产品列表内，选择已发布的产品推送给下游分销商的新品频道
	activepush(2),
//	3.	新品推送通知
//	供应商扫微店主的二维码后，在微店主的新品频道>上游供应商内
	newproductpush(3),
//	新客的动态消息
//	4.	下游关注；
//	下游分销商关注上游供应商时，在上游供应商的新客频道推送一条消息，并把该分销商添加至供应商的 下游分销通讯录
	underattention(4),
//	5.	订单成交；
//	下游分销商产生订单时，在供应商的新客频道推送一条相关消息
	ordersuccess(5),
//	6.	发展分销商；
//	产生新的分销商时，在供应商的新客频道推送一条相关消息
	developbussiness(6),
//	7.	获得佣金；
//	新的佣金到帐时，在供应商的新客频道推送一条相关消息
	getcommision(7),
//	8.	上架新品；
//	下游分销商上架新品时，在供应商的新客频道推送一条相关消息
	putonsale(8),
//
//	9.	发布需求(暂停)
//	下游发布需求时，即以聊天的形式把需求推送至已关注的供应商聊天会话
	publishrequestment(9);

	
	private final int step;  
    private MsgType(int step) {  
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }

}
