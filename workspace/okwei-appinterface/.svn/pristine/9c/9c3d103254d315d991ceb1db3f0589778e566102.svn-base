package com.okwei.appinterface.enums;

public enum VerifierFollowMsgType {
	 /// <summary>
    /// 取消了
    /// </summary>
    Cancel(-1),
    /// <summary>
    /// 未填写
    /// </summary>
    NoWrite(0),
    /// <summary>
    /// 等待确认
    /// </summary>
    WaitSure(1),
    /// <summary>
    /// 打回
    /// </summary>
    Fail(2),
    /// <summary>
    /// 确认
    /// </summary>
    Pass(3),
    /// <summary>
    /// 进驻
    /// </summary>
    PayIn(4);
	private final int step; 

    private VerifierFollowMsgType(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
    
    
}
