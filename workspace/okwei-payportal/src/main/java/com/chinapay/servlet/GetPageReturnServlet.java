/**
 * 
 */
package com.chinapay.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinapay.bean.PaymentBean;
import com.chinapay.util.Config;

import chinapay.PrivateKey;
import chinapay.SecureLink;

/**
 * @author Jackie.Gao
 *
 */
public class GetPageReturnServlet extends HttpServlet {

	private static final String KEY_CHINAPAY_PUBKEY_FILEPATH = "chinapay.pubkey.filepath";
	private static final String PAGE_RETURN_JSP = "/template/getPageReturn.jsp";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List errorList = new ArrayList();
		String PubKeyPath = null;
		try {
			Properties config = Config.getInstance().getProperties();
			PubKeyPath = config.getProperty(KEY_CHINAPAY_PUBKEY_FILEPATH);
		} catch (Exception e) {
			System.out.println("errors_properties_init_failed");
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(PAGE_RETURN_JSP).forward(req, resp);
			return;
		}
		System.out.println("<====Receive PageReturnData Start!");
		//֧����������׼��
		System.out.println("RESPONSE === " + req);
		String Version = req.getParameter("version");
		String MerId = req.getParameter("merid");
		String OrdId = req.getParameter("orderno");
		String TransAmt = req.getParameter("amount");// 12
		String CuryId = req.getParameter("currencycode");// 3
		String TransDate = req.getParameter("transdate");// 8
		String TransType = req.getParameter("transtype");// 4
		String Status = req.getParameter("status");//status��ʾ����״ֻ̬��"1001"��ʾ֧���ɹ�������״̬����ʾδ�ɹ��Ľ��ס������֤ǩ������ͨ���󣬻���Ҫ�ж�����״̬�����Ƿ�Ϊ"1001"
		String BgRetUrl = req.getParameter("BgRetUrl");
		String PageRetUrl = req.getParameter("PageRetUrl");
		String GateId = req.getParameter("GateId");
		String Priv1 = req.getParameter("Priv1");
		/*
		try {
			Priv1 = Base64Util.base64Decoder(Priv1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		String ChkValue = req.getParameter("checkvalue");
		
		System.out.println(MerId+OrdId+TransAmt+CuryId+TransDate+TransType+Status+ChkValue);

		//����汾����ʹ������5�����ݣ����ڰ汾����Ҫ
		/*
		String TransTime = req.getParameter("TransTime");
		String CountryId = req.getParameter("CountryId");
		String TimeZone = req.getParameter("TimeZone");
		String DSTFlag = "1";
		String ExtFlag = "00";
		*/

		boolean buildOK = false;
		boolean res = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey("999999999999999", KeyUsage, PubKeyPath);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("build error!");
			errorList.add("build error!");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(PAGE_RETURN_JSP).forward(req, resp);
			return;
		}
		
		try {
			SecureLink sl = new SecureLink(key);
			res=sl.verifyTransResponse(MerId, OrdId, TransAmt, CuryId, TransDate, TransType, Status, ChkValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(PAGE_RETURN_JSP).forward(req, resp);
			return;
		}

		PaymentBean pay = new PaymentBean();
		pay.setMerId(MerId);
		pay.setOrdId(OrdId);
		pay.setTransAmt(TransAmt);
		pay.setTransDate(TransDate);
		pay.setTransType(TransType);
		pay.setVersion(Version);
		pay.setCuryId(CuryId);
		pay.setGateId(GateId);
		pay.setPageRetUrl(PageRetUrl);
		pay.setBgRetUrl(BgRetUrl);
		pay.setPriv1(Priv1);
		pay.setChkValue(ChkValue);
		
		//����汾����ʹ������5�����ݣ����ڰ汾����Ҫ
		/*
		pay.setTransTime(TransTime);
		pay.setCountryId(CountryId);
		pay.setDSTFlag(DSTFlag);
		pay.setExtFlag(ExtFlag);
		pay.setTimeZone(TimeZone);
		*/
	
		if (res){
			System.out.println("PageReturn Check OK!");
			//��֤ǩ������ͨ����һ��Ҫ�ж�����״̬�����Ƿ�Ϊ"1001"��ʵ�ִ������̻����б�д
			req.setAttribute("payResult", pay);
		}
		req.getRequestDispatcher(PAGE_RETURN_JSP).forward(req, resp);
		System.out.println("Receive PageReturnData End!====>");
		return;
	}

}
