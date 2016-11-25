package com.bfb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletConfig;


public class BfbSdkComm {
	/**
	 * ���ɰٸ�����ʱ����֧���ӿڶ�Ӧ��URL
	 *
	 * @param array $params	���ɶ����Ĳ������飬���������ȡֵ�μ��ӿ��ĵ�
	 * @param string $url   �ٸ�����ʱ����֧���ӿ�URL
	 * @return string �������ɵİٸ�����ʱ����֧���ӿ�URL
	 * @throws UnsupportedEncodingException 
	 */
	public String   create_baifubao_pay_order_url(String[] ary,String[] ary1 ,String url) throws UnsupportedEncodingException
	{
		//�����жϴ���ı�ѡ�����Ƿ�Ϊ��
		//����make_sign����������ǩ�����sign�������������ǩ�� 
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ary1.length; i++)
		{ 
			//�ж����Ե�ֵ�Ƿ�Ϊ��,ֵΪ�յĲ��ò���ƴ��
			String strTemp=ary1[i].substring(ary1[i].indexOf("=")+1, ary1[i].length());
			if(!strTemp.equals("") && strTemp!=null)
			{
               sb. append(ary1[i]+"&");
            }	
		} 
		String newStr =sb.toString();
        String sign=make_sign(ary);
        //�� URL get���ύ������ 
        //�ύ
        String urlGet=url+"?"+newStr+"sign="+sign;
		return  urlGet;
	}
	/**
	 * ���������ǩ�����������Ϊ���飬�㷨���£�
	 * 1.
	 * �����鰴KEY������������
	 * 2. ������������������̻���Կ������Ϊkey����ֵΪ�̻���Կ
	 * 3. ������ƴ�ӳ��ַ�������key=value&key=value����ʽ����ƴ�ӣ�ע�����ﲻ��ֱ�ӵ���
	 * http_build_query��������Ϊ�÷�����Բ�������URL����
	 * 4. Ҫ�����������е�$params ['sign_method']����ļ����㷨����ƴ�Ӻõ��ַ������м��ܣ����ɵı���ǩ����
	 * $params ['sign_method']����1ʹ��md5���ܣ�����2ʹ��sha-1����
	 *
	 * @param array $params ����ǩ��������
	 * @return string | boolean �ɹ���������ǩ����ʧ�ܷ���false
	 */
	public String   make_sign(String[] ary)
	{
		Arrays.sort(ary,String.CASE_INSENSITIVE_ORDER);   
		//�Բ���������а�key��������,Ȼ��ƴ�ӣ�������5ǩ������
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ary.length; i++)
		{  
				sb. append(ary[i]+"&");	
		}
		String newStrTemp = sb.toString()+"key="+GetKey().trim();
		//��ȡsign_method
		String signmethod= GetMethodSign(newStrTemp);
		//����sign_methodѡ��ʹ��MD5ǩ��1�����ǹ�ϣǩ��2
		String sign=null;
		if(signmethod.equals("1")){
		  sign =new MD5().md5Digest(newStrTemp); 
		}
		else if(signmethod.equals("2")){
	      sign =new SHA1().Digest(newStrTemp,"gbk").toLowerCase();
		}
		System.out.println("str��ǩ����: " + newStrTemp + ";ǩ���� sign=" + sign);
		return sign;
	}
	/**
	 * ��ѯ����������÷�����Ҫ�̻��Լ�ʵ�֣������ǲ�ѯ�̻��Լ���ϵͳ����֤�ö����Ƿ��Ѿ���������.
	 * ���ڰٸ����ĺ�̨֪ͨ�ӿڿ��ܻ���ö�Σ�����˴��̻������������ֱ�ӽ��м��˵Ⱥ���������
	 * ���ܻ�һ���������̻���ϵͳ���ظ���¼������̻����ʽ�ȱʧ.
	 *
	 * @param string $order_no        	
	 * @return int ����������ڵȴ�֧��״̬������sp_conf::SP_PAY_RESULT_WAITING
	 *         ��������û�Ҳ�����Լ�����
	 */
	public  String  query_order_state(String  order_no) {
		/*
		 * ������Ҫ�̻��Լ�ʵ�ֲ�ѯ�����ҵ���߼�,���ض���״̬,������ֻ�Ǽ򵥵ķ���1
		 */
		return "1";
	}
	/**
	 * ��־��ӡ����������������־�Ĵ��λ��
	 **/
	public Logger  printLog(String strName)
	{
		Logger logger = Logger. getLogger(strName);
		FileHandler fileHandler = null;
		try {
		       fileHandler = new FileHandler("e:/BaifubaoLog.txt", 0, 1, true);//
		       //����1��ָ����־����ļ�·�� ����2����ʾ�ļ�����ֽ��� 0��ʾ������
		       // ����3����־�ļ������Զ�� ����4���Ƿ���ԭ����־��׷����־,true��ʾ׷����־�ļ������������޸�
		} catch (Exception ex) {
		       ex.printStackTrace();
		}
		fileHandler.setLevel(Level.FINER);//������־�ļ����������
		fileHandler.setFormatter(new SimpleFormatter());//���������Ϣ��ʽΪ��ͨ��ʽ Ĭ��ΪXML��
		logger.addHandler(fileHandler);
		logger.setLevel(Level.FINER);//������־����
		return logger;
	}
	private  String GetKey(){  
		return BfbPaySetting.SP_KEY;
    }  
	private String GetMethodSign(String  sb){	
		int aa=sb.indexOf("sign_method=");
	    String signmethod= sb.substring(aa+12,aa+13);
		return signmethod;
	}

   
}
