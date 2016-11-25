package com.chinapay.util.connection;

public class Http extends CPHttpConnection
{
	
	/**
	 * ���췽������ý�������ĵ�ַ���˿ںźͳ�ʱʱ��
	 * 
	 * @param URL
	 *            http��ַ����http://��ʼ
	 * @param Port
	 *            http�˿ں�
	 * @param timeOut
	 *            http��ʱʱ��
	 */
	public Http(String httpURL, String timeOut)
	{
		URL = httpURL;
		
		this.timeOut = timeOut;
	}
	
	
	public byte[] getReceiveData()
	{
		return receiveData;
	}
}