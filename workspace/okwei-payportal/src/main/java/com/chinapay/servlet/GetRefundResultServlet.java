/**
 * 
 */
package com.chinapay.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinapay.util.Config;

import chinapay.PrivateKey;
import chinapay.SecureLink;

/**
 * @author Jackie.Gao
 * 
 */
public class GetRefundResultServlet extends HttpServlet {
	private static final String KEY_CHINAPAY_PUBKEY_FILEPATH = "chinapay.pubkey.filepath";
	private static final String STATUS_REFUND_SUCC = "3";
	private static final String STATUS_REFUND_FAIL = "8";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String PubKeyPath = null;
		try {
			Properties config = Config.getInstance().getProperties();
			PubKeyPath = config.getProperty(KEY_CHINAPAY_PUBKEY_FILEPATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("<====Receive RefundResultData Start!");
		// ��������׼��
		String ResCode = req.getParameter("ResponseCode");
		String MerId = req.getParameter("MerID");
		String OrdId = req.getParameter("OrderId");
		String TransType = req.getParameter("TransType");
		String RefundAmout = req.getParameter("RefundAmout");
		String ProcessDate = req.getParameter("ProcessDate");
		String SendTime = req.getParameter("SendTime");
		String Status = req.getParameter("Status");
		String Priv1 = req.getParameter("Priv1");
		String CheckValue = req.getParameter("CheckValue");
		/*
		 * try { Priv1 = Base64Util.base64Decoder(Priv1); } catch (Exception e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		
		//ResponseCodeΪ0����StatusΪ3��8��ʱ����Ҫ��chinapay�������ݽ�����ǩ��
		if (ResCode.equals("0")
				&& (Status.equals(STATUS_REFUND_SUCC) || Status
						.equals(STATUS_REFUND_FAIL))) {
			boolean buildOK = false;
			boolean res = false;
			int KeyUsage = 0;
			PrivateKey key = new PrivateKey();
			try {
				buildOK = key.buildKey("999999999999999", KeyUsage, PubKeyPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!buildOK) {
				System.out.println("build error!");
				return;
			}
			try {
				SecureLink sl = new SecureLink(key);
				String plainData = MerId + ProcessDate + TransType + OrdId
						+ RefundAmout + Status + Priv1;
				System.out.println("plainData=" + plainData);
				res = sl.verifyAuthToken(plainData, CheckValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (res) {
				System.out.println("RefundResultData Check OK!");
				System.out.println("Receive RefundResultData End!====>");
			}
		}

	}

}
