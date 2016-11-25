package com.okwei.bean.enums;

import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;

public class ScoreEnum {

	/**
	 * 1元+1分，300分/单封顶
	 */
	public static int order=ParseHelper.toInt(AppSettingUtil.getSingleValue("scoreOrder"),3000);
	/**
	 *  1条+5分，每用户对每个商品每天只计    算一次有效评论
	 */
	public static int pinglun=ParseHelper.toInt(AppSettingUtil.getSingleValue("scorePinglun"),5);
	/**
	 *  1次+2分，每用户对每个商品只点1次
	 */
	public static int zan=ParseHelper.toInt(AppSettingUtil.getSingleValue("scoreZan"),2);
	/**
	 *  1次+3分，每用户每商品只算1次
	 */
	public static int onshelve=ParseHelper.toInt(AppSettingUtil.getSingleValue("scoreOnshelve"),3);
	/**
	 *  1次+3分，每用户每商品只算1次
	 */
	public static int collect=ParseHelper.toInt(AppSettingUtil.getSingleValue("scoreCollect"),3);
	
}
