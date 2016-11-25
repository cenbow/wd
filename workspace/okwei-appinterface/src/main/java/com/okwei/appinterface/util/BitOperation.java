package com.okwei.appinterface.util;

import com.okwei.appinterface.enums.UserIdentityType;
import com.okwei.appinterface.enums.VerifierType;
import com.okwei.bean.enums.SupplierTypeEnum;
import com.okwei.util.ParseHelper;

public class BitOperation {
	/// <summary>
    /// 将整数的某位置为0或1
    /// </summary>
    /// <param name="_Resource">整数</param>
    /// <param name="_Mask">整数的某位</param>
    /// <param name="flag">是否置1，TURE表示置1，FALSE表示置0</param>
    /// <returns>返回修改过的值</returns>
    public static short setIntegerSomeBit(short _Resource, int _Mask, boolean flag)
    {
        if (flag)
        {
        	_Resource |= (0x1 << _Mask);
        }
        else
        {
        	_Resource &= ~(0x1 << _Mask);
        }
        return _Resource;
    }
    
    /// <summary>
    /// 取整数的某一位
    /// </summary>
    /// <param name="_Resource">要取某一位的整数</param>
    /// <param name="_Mask">要取的位置索引，自右至左为0-7</param>
    /// <returns>返回某一位的值（0或者1）</returns>
    public static short getIntegerSomeBit(short _Resource, int _Mask)
    {
        return (short) (_Resource >> _Mask & 1);
    }
    
    
    /**
	 * 验证供应商身份
	 * @param value 当前用户值
	 * @param type （需要验证的身份状态）
	 * @return
	 */
	public static boolean isSupply(short value,SupplierTypeEnum type)
	{
		int b=ParseHelper.toInt(type.toString());
		return b==(value&b);
	}
	
	/**
	 * 验证 认证员身份
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean isVerifier(short value,VerifierType type)
	{
		int b=ParseHelper.toInt(type.toString());
		return b==(value&b);
	}
	
	/**
	 * 验证用户身份
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean isIdentity(int value,UserIdentityType type){
		int b=ParseHelper.toInt(type.toString());
		return b==(value&b);
	}
	
	/**
	 * 
	 * @param value 值
	 * @param type 
	 * @return
	 */
	public static boolean isSupplyNew(int value,SupplierTypeEnum type)
	{
//		String aaString=Integer.toBinaryString(value);
//		String bbString=Integer.toBinaryString(ParseHelper.toInt(type.toString()));
		int a= (value&ParseHelper.toInt(type.toString())); 
		int b=ParseHelper.toInt(type.toString());
		return b==(a&b);
	}
	
}
