package com.okwei.company.bean.enums;
/**
 * 认证状态
 * @author yangjunjun
 *
 */
public enum VerfierStatusEnum {
	//申请中
	Applying(0),
	//审核不通过
	Pass(1),
	//审核不通过 shenm
	Fail(2),
	//缴费进驻
	PayIn(3);
	private final int step; 

    private VerfierStatusEnum(int step) { 
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
