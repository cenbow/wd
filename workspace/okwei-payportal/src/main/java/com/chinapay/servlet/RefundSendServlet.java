/**
 * 
 */
package com.chinapay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinapay.bean.RefundBean;
import com.chinapay.util.Config;
import com.chinapay.util.connection.CPHttpConnection;
import com.chinapay.util.connection.Http;
import com.chinapay.util.connection.HttpSSL;

import chinapay.PrivateKey;
import chinapay.SecureLink;

/**
 * @author Jackie.Gao
 * 
 */
public class RefundSendServlet extends HttpServlet {

	private static final String KEY_CHINAPAY_MERID = "chinapay.merid";
	private static final String KEY_CHINAPAY_MERKEY_FILEPATH = "chinapay.merkey.filepath";
	private static final String REFUND_URL = "chinapay.refund.url";
	private static final String REFUND_INPUT_JSP = "/template/refundInput.jsp";
	private static final String REFUND_OK_JSP = "/template/refundOK.jsp";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String MerId = null;
		List errorList = new ArrayList();
		try {
			Properties config = Config.getInstance().getProperties();
			MerId = config.getProperty(KEY_CHINAPAY_MERID);
		} catch (Exception e) {
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(REFUND_INPUT_JSP).forward(req, resp);
			return;
		}
		req.setAttribute("merId", MerId);
		req.getRequestDispatcher(REFUND_INPUT_JSP).forward(req, resp);
		return;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String MerKeyPath = null;
		String refund_url = null;
		List errorList = new ArrayList();
		try {
			Properties config = Config.getInstance().getProperties();
			MerKeyPath = config.getProperty(KEY_CHINAPAY_MERKEY_FILEPATH);
			refund_url = config.getProperty(REFUND_URL);
		} catch (Exception e) {
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(REFUND_INPUT_JSP).forward(req, resp);
			return;
		}

		// �˿������׼��
		String MerId = req.getParameter("MerID");
		String Version = req.getParameter("Version");
		String OrderId = req.getParameter("OrderId");
		String RefundAmount = req.getParameter("RefundAmount");// 12
		String TransDate = req.getParameter("TransDate");// 8
		String TransType = req.getParameter("TransType");// 4
		String ReturnURL = req.getParameter("ReturnURL");
		String Priv1 = req.getParameter("Priv1");
		/*
		 * try { Priv1 = Base64Util.base64Decoder(Priv1); } catch (Exception e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		String ChkValue = null;

		boolean buildOK = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey(MerId, KeyUsage, MerKeyPath);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("build error!");
			errorList.add("build error!");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(REFUND_INPUT_JSP).forward(req, resp);
			return;
		}
		try {
			SecureLink sl = new SecureLink(key);
			System.out.println(MerId + TransDate + TransType + OrderId
					+ RefundAmount + Priv1);
			ChkValue = sl.Sign(MerId + TransDate + TransType + OrderId
					+ RefundAmount + Priv1);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(REFUND_INPUT_JSP).forward(req, resp);
			return;
		}

		RefundBean refund = new RefundBean();
		refund.setMerId(MerId);
		refund.setOrdId(OrderId);
		refund.setRefundAmount(RefundAmount);
		refund.setTransDate(TransDate);
		refund.setTransType(TransType);
		refund.setVersion(Version);
		refund.setReturnUrl(ReturnURL);
		refund.setPriv1(Priv1);
		refund.setChkValue(ChkValue);

		String httpType = "SSL";
		String timeOut = "60000";
		String res = sendHttpMsg(refund_url, refund.toString(), httpType,
				timeOut);
		/**
		 * ���յ�����֮�󣬿ɶԱ������ݽ�����ǩ���˴����ԡ�
		 * ������ǩ�����ɲο�getRefundResultServlet.java�е���ǩ����
		 * 
		 */
		req.setAttribute("result", res);
		req.getRequestDispatcher(REFUND_OK_JSP).forward(req, resp);
		return;

	}

	/**
	 * ����http post���ģ����ҽ�����Ӧ��Ϣ
	 * 
	 * @param strMsg
	 *            ��Ҫ���͵Ľ��ױ���,��ʽ��ѭhttppost������ʽ
	 * @return String ������������Ӧ����,�������ʧ�ܣ����ؿ��ַ���
	 */
	private String sendHttpMsg(String URL, String strMsg, String httpType,
			String timeOut) {
		String returnMsg = "";
		CPHttpConnection httpSend = null;
		if (httpType.equals("SSL")) {
			httpSend = new HttpSSL(URL, timeOut);
		} else {
			httpSend = new Http(URL, timeOut);
		}
		// ���û����Ӧ���������
		httpSend.setLenType(0);
		// �����ַ�����
		httpSend.setMsgEncoding("GBK");
		int returnCode = httpSend.sendMsg(strMsg);
		if (returnCode == 1) {
			try {
				returnMsg = new String(httpSend.getReceiveData(), "GBK")
						.trim();
				System.out.println("���յ���Ӧ����,returnMsg=[" + returnMsg + "]");
			} catch (UnsupportedEncodingException e) {
				System.out.println("[getReceiveData Error!]");
			}
		} else {

			System.out.println(new StringBuffer("���Ĵ���ʧ��,ʧ�ܴ���=[").append(
					returnCode).toString());
		}
		return returnMsg;
	}

}
