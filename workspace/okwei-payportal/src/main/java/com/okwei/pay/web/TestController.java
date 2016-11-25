package com.okwei.pay.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;




import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llpay.client.utils.LLPayUtil;
import com.okwei.util.SMSClient;

@Controller
@RequestMapping(value="/t")
public class TestController {
    static String sn = "SDK-SKY-010-02291";
    static String pwd = "593887";
    static SMSClient client = null;

    public static void init() {
	try {
	    client = new SMSClient(sn, pwd);
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
    }


    @ResponseBody
    @RequestMapping(value = "/t",method ={RequestMethod.POST,RequestMethod.GET})
    public String test(HttpServletRequest request,HttpServletResponse response) {
    	 return "";
    }

}
