package com.chinapay.util.connection;

import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

/**
 * ��https�ķ�ʽ���ͱ���
 * 
 */
public class HttpSSL extends CPHttpConnection
{
	
	
	
	/**
	 * ���췽����������յ�ַ��http����
	 * 
	 * @param HttpsUrl
	 *            ���������https��ַ,��https��ͷ
	 * @param parameters
	 *            ��Ҫ���͵�http����,��ɸ�ʽΪkey=value&key=value
	 */
	public HttpSSL(String httpsURL, String timeOut)
	{
		URL = httpsURL;
		this.timeOut = timeOut;
		SSLContext sslContext = null;
		try
		{
			sslContext = SSLContext.getInstance("TLS");
			X509TrustManager[] xtmArray = new X509TrustManager[]
			{ new CPX509TrustManager() };
			sslContext.init(null, xtmArray, new java.security.SecureRandom());
		}
		catch (GeneralSecurityException gse)
		{
			System.out.println("gse=[" + gse.toString() + "]");
			//TraceLog.logStackTrace(this, (Throwable) gse);
		}
		if (sslContext != null)
		{
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		}
		HttpsURLConnection.setDefaultHostnameVerifier(new CPHostnameVerifier());
	}
	
	
	public byte[] getReceiveData()
	{
		return receiveData;
	}
}
