package com.okwei.pay.bean.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.okwei.bean.domain.TSmsverification;
import com.okwei.bean.enums.VerifyCodeType;
import com.okwei.pay.bean.enums.VerifyCodeTypeEnum;
import com.okwei.pay.dao.IPayOrderDAO;
import com.okwei.pay.service.IPayOrderService;
import com.okwei.util.ObjectUtil;
import com.okwei.util.SMSClient;

public class SendSMSUtil {
    @Autowired
    IPayOrderService payOrderService;

    private Log logger = LogFactory.getLog(this.getClass());
    static String sn = "SDK-SKY-010-02291";
    static String pwd = "593887";
    static SMSClient client = null;

    /**
     * 发送验证码
     * 
     * @param phone
     *            手机号码
     * @param verifier
     *            发送的类型
     * @return
     */
    public boolean sendSMS(String[] param, VerifyCodeTypeEnum verifier) {
	return sendSMS(param, verifier, 0L);
    }

    public boolean sendSMS(String[] param, VerifyCodeTypeEnum verifier, long weiid) {
	String mobile = param[0];// 参数一：手机号码
	String content = "";
	TSmsverification ts = new TSmsverification();
	ts.setWeiId(weiid);
	ts.setCreateTime(new Date());
	ts.setStatus((short) 0);
	ts.setType(Short.parseShort(verifier.toString()));
	ts.setPhone(mobile);
	if (verifier == VerifyCodeTypeEnum.pay) {// 钱包支付
	    String code = param[1];// 参数二：验证码
	    content = "您的验证码为：" + code + "[微店网]";
	} else if (verifier == VerifyCodeTypeEnum.SupplyDeliver) {// 通知供应商发货
	    String amount = param[1];// 参数二：价格
	    content = "您刚有一个消费者成功购买了产品，总价为：" + amount + "元，请及时登录后台及时发货，以免影响消费者购买体验。下载微店app，手机也能发货了 http://app.okwei.com [微店网]";
	} else if (verifier == VerifyCodeTypeEnum.SendSellerWeiid) {// 订单成交通知成交微店
	    String seller = param[1];// 参数二：成交微店
	    String sellcomminss = param[2];// 参数三：成交佣金
	    content = "您的微店" + seller + "成交了一个订单，您获得了" + sellcomminss + "元佣金。加强推广，佣金不断。加油！下载微店app，推广更方便 http://app.okwei.com [微店网]";
	} else if (verifier == VerifyCodeTypeEnum.SendSellerUpWeiid) {// 订单成交通知成交微店上级
	    String seller = param[1];// 参数二：成交微店
	    String sellcomminss = param[2];// 参数三：成交佣金
	    content = "您的分销商（微店号：" + seller + "）成交一个订单，Ta为您赚得佣金" + sellcomminss + "元（占总佣金的30%）。加强推广，分销商越多，佣金越多！下载微店app，推广更方便 http://app.okwei.com [微店网]";
	} else if (verifier == VerifyCodeTypeEnum.SendWeiDeliver) {// 通知微店主等待发货
	    String payOrderNo = param[1];// 参数一：订单号
	    content = "亲爱的微粉，您的订单" + payOrderNo + "已经付款成功，供应商马上给您发货！下载微店app。订单信息早知道 http://app.okwei.com，感谢购买,祝您生活愉快！[微店网]";
	}
	// 手机和内容不能为空
	if (ObjectUtil.isEmpty(mobile) || ObjectUtil.isEmpty(content)) {
	    return false;
	}
	ts.setContent(content);
	//payOrderService.insertTSmsverification(ts);// 添加到数据库
	String smsId = (ts.getId() == null ? "520123456" : ts.getId().toString());
	// 初始化发送
	init();
	String content2 = "";
	try {
	    content2 = URLEncoder.encode(content, "utf8");
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	String result = client.mdsmssend(mobile, content2, "", "", smsId, "");// 第一次
	if (!result.equals(smsId)) {
	    result = client.mdsmssend(mobile, content2, "", "", smsId, "");// 第二次
	    if (!result.equals(smsId)) {
		result = client.mdsmssend(mobile, content2, "", "", smsId, "");// 第三次
		if (!result.equals(smsId)) {
		    return false;
		}
	    }
	}
	return true;
    }

    public static void init() {
	try {
	    client = new SMSClient(sn, pwd);
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
    }
}
