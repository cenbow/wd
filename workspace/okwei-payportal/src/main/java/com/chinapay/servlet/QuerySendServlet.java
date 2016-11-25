/**
 * 
 */
package com.chinapay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinapay.bean.QueryBean;
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
public class QuerySendServlet extends HttpServlet {
	private static final String KEY_CHINAPAY_MERID = "chinapay.merid";
	private static final String KEY_CHINAPAY_MERKEY_FILEPATH = "chinapay.merkey.filepath";
	private static final String KEY_CHINAPAY_PUBKEY_FILEPATH = "chinapay.pubkey.filepath";
	private static final String QUERY_URL = "chinapay.query.url";
	private static final String QUERY_URL_2014 = "chinapay.query.url.new";
	private static final String QUERY_INPUT_JSP = "/template/queryInput.jsp";
	private static final String QUERY_OK_JSP = "/template/queryOK.jsp";

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
			req.getRequestDispatcher(QUERY_INPUT_JSP).forward(req, resp);
			return;
		}
		req.setAttribute("merId", MerId);
		req.getRequestDispatcher(QUERY_INPUT_JSP).forward(req, resp);
		return;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String MerKeyPath = null;
		String query_url = null;
		List errorList = new ArrayList();
		
		String Version = req.getParameter("Version");
		try {
			Properties config = Config.getInstance().getProperties();
			MerKeyPath = config.getProperty(KEY_CHINAPAY_MERKEY_FILEPATH);
			if("20060831".equals(Version)){
				query_url = config.getProperty(QUERY_URL);
			}else if("20140521".equals(Version)){
				query_url = config.getProperty(QUERY_URL_2014);
			}
		} catch (Exception e) {
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(QUERY_INPUT_JSP).forward(req, resp);
			return;
		}

		// ��ѯ��������׼��
		String MerId = req.getParameter("MerID");
		//String Version = req.getParameter("Version");
		String OrderId = req.getParameter("OrdId");
		String TransDate = req.getParameter("TransDate");// 8
		String TransType = req.getParameter("TransType");// 4
		String Resv = req.getParameter("Resv");
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
			// e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("build error!");
			errorList.add("build error!");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(QUERY_INPUT_JSP).forward(req, resp);
			return;
		}
		try {
			SecureLink sl = new SecureLink(key);
			System.out.println(MerId + TransDate + OrderId + TransType);
			ChkValue = sl.Sign(MerId + TransDate + OrderId + TransType);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(QUERY_INPUT_JSP).forward(req, resp);
			return;
		}

		QueryBean query = new QueryBean();
		query.setMerId(MerId);
		query.setOrdId(OrderId);
		query.setTransDate(TransDate);
		query.setTransType(TransType);
		query.setVersion(Version);
		query.setResv(Resv);
		query.setChkValue(ChkValue);

		String httpType = "SSL";
		String timeOut = "60000";
		String res = sendHttpMsg(query_url, query.toString(), httpType,
				timeOut);
		/**
		 * ���յ�����֮�󣬿ɶԱ������ݽ�����ǩ���˴����ԡ� 
		 * ������ǩ�����ɲο��̻��ֲ��е��ʲ�ѯ����ǩ����
		 * 
		 */
		// ��ǩ
		HashMap<String, String> returnMap = new HashMap<String, String>();
		String[] retSplit = res.split("&");
		for (int i = 0; i < retSplit.length; i++) {
			int eqaulIndex = retSplit[i].indexOf("=");
			String keyName = retSplit[i].substring(0, eqaulIndex);
			String value =  retSplit[i].substring(eqaulIndex+1);
			returnMap.put(keyName, value);
		}
		Properties config = Config.getInstance().getProperties();
		String pubKeyPath = config.getProperty(KEY_CHINAPAY_PUBKEY_FILEPATH);
		boolean verifyRet = verify(Version, returnMap, pubKeyPath);
		res = "ǩ����֤���=["+verifyRet+"] ���ʲ�ѯ����=["+res+"]";
		req.setAttribute("result", res);
		req.getRequestDispatcher(QUERY_OK_JSP).forward(req, resp);
		return;

	}

private boolean verify(String version, HashMap<String, String> map, String pubKeyPath) {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(map.get("merid"))
		.append(map.get("orderno"))
		.append(map.get("amount"))
		.append(map.get("currencycode"))
		.append(map.get("transdate"))
		.append(map.get("transtype"))
		.append(map.get("status"));
		
		if("20140520".equals(version)) {
			sbf.append(map.get("cpdate"))
			.append(map.get("cpseqid"));
		}
		
		String chkValue = map.get("checkvalue");

		boolean buildOK = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey("999999999999999", KeyUsage, pubKeyPath);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("verify build error!");
		}
		try {
			SecureLink sl = new SecureLink(key);
			System.out.println("verify plain=["+sbf.toString()+"]");
			return sl.verifyAuthToken(sbf.toString(), chkValue);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return false;
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
				returnMsg = new String(httpSend.getReceiveData(), "GBK").trim();
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
