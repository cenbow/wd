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

import com.chinapay.bean.PaymentBean;
import com.chinapay.util.Config;

import chinapay.PrivateKey;
import chinapay.SecureLink;

/**
 * @author Jackie.Gao
 *
 */
public class GetBgReturnServlet extends HttpServlet {

	private static final String KEY_CHINAPAY_PUBKEY_FILEPATH = "chinapay.pubkey.filepath";

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

		System.out.println("<====Receive BgReturnData Start!");
		//֧����������׼��
		String Version = req.getParameter("version");
		String MerId = req.getParameter("merid");
		String OrdId = req.getParameter("orderno");
		String TransAmt = req.getParameter("amount");// 12
		String CuryId = req.getParameter("currencycode");// 3
		String TransDate = req.getParameter("transdate");// 8
		String TransType = req.getParameter("transtype");// 4
		String Status = req.getParameter("status");
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
		String TransTime = req.getParameter("TransTime");
		String CountryId = req.getParameter("CountryId");
		String TimeZone = req.getParameter("TimeZone");
		String DSTFlag = "1";
		String ExtFlag = "00";

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
			res=sl.verifyTransResponse(MerId, OrdId, TransAmt, CuryId, TransDate, TransType, Status, ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
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
		pay.setStatus(Status);
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
			System.out.println("BgReturn Check OK!");
			System.out.println(pay.toString());
			System.out.println("Receive BgReturnData End!====>");
			if (Status.equals("1001")){
				//1001��ʾ֧���ɹ���֮��Ϊ�̻�ϵͳ�Գɹ��������߼�����
			}
		}
	}

}
