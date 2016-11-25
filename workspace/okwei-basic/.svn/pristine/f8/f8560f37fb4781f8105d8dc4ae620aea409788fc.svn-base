package com.okwei.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.llpay.client.config.PartnerConfig;
import com.llpay.client.config.ServerURLConfig;
import com.llpay.client.conn.HttpRequestSimple;

/**
 * 
 * @ClassName: LLPayUtil
 * @Description: 连连支付工具类
 * @author xiehz
 * @date 2015年6月18日 下午4:59:33
 *
 */
public class LLPayUtil {

	private final static Log logger = LogFactory.getLog(LLPayUtil.class);

	/**
	 * 
	 * @Title: queryCardBin
	 * @Description: 银行卡卡bin信息查询
	 * @param @param cardNo
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String queryCardBin(String cardNo) {
		JSONObject reqObj = new JSONObject();
		reqObj.put("oid_partner", PartnerConfig.OID_PARTNER);
		reqObj.put("card_no", cardNo);
		reqObj.put("sign_type", PartnerConfig.SIGN_TYPE);
		String sign = com.llpay.client.utils.LLPayUtil.addSign(reqObj, PartnerConfig.TRADER_PRI_KEY, PartnerConfig.MD5_KEY);
		reqObj.put("sign", sign);
		String reqJSON = reqObj.toString();
		logger.info("银行卡卡bin信息查询请求报文[" + reqJSON + "]");
		String resJSON = HttpRequestSimple.getInstance().postSendHttp(ServerURLConfig.QUERY_BANKCARD_URL, reqJSON);
		logger.info("银行卡卡bin信息查询响应报文[" + resJSON + "]");
		return resJSON;
	}

	/**
	 * 
	 * @Title: queryBankcardList
	 * @Description: 用户已绑定银行列表查询
	 * @param @param userId
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String queryBankcardList(String userId) {
		JSONObject reqObj = new JSONObject();
		reqObj.put("oid_partner", PartnerConfig.OID_PARTNER);
		reqObj.put("user_id", userId);
		reqObj.put("offset", "0");
		reqObj.put("sign_type", PartnerConfig.SIGN_TYPE);
		String sign = com.llpay.client.utils.LLPayUtil.addSign(reqObj, PartnerConfig.TRADER_PRI_KEY, PartnerConfig.MD5_KEY);
		reqObj.put("sign", sign);
		String reqJSON = reqObj.toString();
		logger.info("用户已绑定银行列表查询请求报文[" + reqJSON + "]");
		String resJSON = HttpRequestSimple.getInstance().postSendHttp(ServerURLConfig.QUERY_USER_BANKCARD_URL, reqJSON);
		logger.info("用户已绑定银行列表查询响应报文[" + resJSON + "]");
		return resJSON;
	}

}
