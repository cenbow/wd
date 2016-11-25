package com.okwei.util;

import com.okwei.bean.enums.FriendScope;
import com.okwei.bean.enums.ProductTagType;
import com.okwei.bean.enums.SupplierTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.VerifierTypeEnum;

public class BitOperation {
	// / <summary>
	// / 将整数的某位置为0或1
	// / </summary>
	// / <param name="_Resource">整数</param>
	// / <param name="_Mask">整数的某位</param>
	// / <param name="flag">是否置1，TURE表示置1，FALSE表示置0</param>
	// / <returns>返回修改过的值</returns>
	public static short setIntegerSomeBit(short _Resource, int _Mask, boolean flag) {
		if (flag) {
			_Resource |= (0x1 << _Mask);
		} else {
			_Resource &= ~(0x1 << _Mask);
		}
		return _Resource;
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
	 * 用户关系判断（分销商、粉丝、好友）
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean isFriendType(int value,FriendScope type){
		int b=ParseHelper.toInt(type.toString());
		return b==(value&b);
	}
	// / <summary>
	// / 取整数的某一位
	// / </summary>
	// / <param name="_Resource">要取某一位的整数</param>
	// / <param name="_Mask">要取的位置索引，自右至左为0-7</param>
	// / <returns>返回某一位的值（0或者1）</returns>
	public static short getIntegerSomeBit(short _Resource, int _Mask) {
		return (short) (_Resource >> _Mask & 1);
	}

	
	public static int setIntegerSomeBit(int _Resource, int _Mask, boolean flag) {
		if (flag) {
			_Resource |= (0x1 << _Mask);
		} else {
			_Resource &= ~(0x1 << _Mask);
		}
		return _Resource;
	}
	
	
	public static int getIntegerSomeBit(int _Resource, int _Mask) {
		return (int) (_Resource >> _Mask & 1);
	}
	/**
	 * 验证供应商身份
	 * 
	 * @param value
	 *            当前用户值
	 * @param type
	 *            （需要验证的身份状态）
	 * @return
	 */
	public static boolean isSupply(short value, SupplierTypeEnum type) {
		int b = ParseHelper.toInt(type.toString());
		return b == (value & b);
	}

	/**
	 * 验证 认证员身份
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean isVerifier(Short value, VerifierTypeEnum type) {
		int b = ParseHelper.toInt(type.toString());
		return b == (value & b);
	}

	/**
	 * 
	 * @param value
	 *            值
	 * @param type
	 * @return
	 */
	public static boolean isSupplyNew(int value, SupplierTypeEnum type) {
		// String aaString=Integer.toBinaryString(value);
		// String
		// bbString=Integer.toBinaryString(ParseHelper.toInt(type.toString()));
		int a = (value & ParseHelper.toInt(type.toString()));
		int b = ParseHelper.toInt(type.toString());
		return b == (a & b);
	}
	/**
	 * 验证用户身份
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean verIdentity(int value,UserIdentityType type){
		int a = (value & ParseHelper.toInt(type.toString()));
		int b = ParseHelper.toInt(type.toString());
		return b == (a & b);
	}
	/**
	 * 产品tag
	 * @param value
	 * @param type
	 * @return
	 */
	public static boolean judageProductTag(short value, ProductTagType type) {
		int b = ParseHelper.toInt(type.toString());
		return b == (value & b);
	}
}
