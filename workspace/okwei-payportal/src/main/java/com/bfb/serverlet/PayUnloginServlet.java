package com.bfb.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bfb.util.BfbSdkComm;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class PayUnloginServlet extends HttpServlet {
 
	
	private ServletConfig scon = null;
	
	public PayUnloginServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 *����pay_unlogin.htmlҳ���post����
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 /**
         * 1�����ñ���
         */
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");  
		response.setHeader("content-type","text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		//��ӡ��־
		Logger logger =new  com.bfb.util.BfbSdkComm().printLog("PayUnloginServlet");

		/**
		 * 2����ȡ web.xml�ڵĳ���ֵ
	     */
	    //��Ʒ�����
	    String  service_code=
	    		"service_code="+scon.getServletContext().getInitParameter("BFB_PAY_INTERFACE_SERVICE_ID");
	    //�̻���
	    String sp_no="sp_no=" +scon.getServletContext().getInitParameter("SP_NO");
	    //���׵ĳ�ʱʱ��,��ǰʱ���2��
	    Calendar   c   =   Calendar.getInstance(); 
	    c.add(Calendar.DAY_OF_MONTH, 2);  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");  
        String mDateTime=formatter.format(c.getTime());  
	    String strExpire=mDateTime.substring(0, 14);//
	    String expire_time="expire_time=" +strExpire;
	    //��������ʱ��
	    String order_create_time1=formatter.format(System.currentTimeMillis()); 
	    //������
	    String order_no="order_no=" +order_create_time1+(int)(Math.random() * 100); ;
	    String order_create_time="order_create_time=" +order_create_time1;
	    //����
	    String currency="currency=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_CURRENTCY");
	    //����
	    String input_charset="input_charset=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_ENCODING");
	    //�汾
	    String version="version=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_VERSION");
	    //���ܷ�ʽmd5����hash
	    String sign_method="sign_method=" +scon.getServletContext().getInitParameter("SIGN_METHOD_MD5");
	    //��Կ
	    String SP_KEY=scon.getServletContext().getInitParameter("SP_KEY");
	    //�ύ��ַ
	    String BFB_PAY_DIRECT_NO_LOGIN_URL=scon.getServletContext().getInitParameter("BFB_PAY_DIRECT_NO_LOGIN_URL");
	    
	    /**
	     * 3��pay_unlogin.htmlҳ��post�ύ�ı���ֵ
	     */
	    //��Ʒ����
	    String goods_category ="goods_category="+request.getParameter("goods_category");
	    //��Ʒ����
	    String tempgoods_name=request.getParameter("goods_name");
	    String goods_name ="goods_name="+tempgoods_name;
	    String goods_name1="goods_name="+ URLEncoder.encode(request.getParameter("goods_name"),"gbk");
	    //String goods_ame1 ="goods_name="+tempgoods_name;

	    //��Ʒ����
	    String tempgoods_desc=request.getParameter("goods_desc");
	    String goods_desc ="goods_desc="+tempgoods_desc;
	    String goods_desc1= "goods_desc="+URLEncoder.encode(request.getParameter("goods_desc"),"gbk");
	    //String goods_desc1 ="goods_desc="+tempgoods_desc;
	   //��Ʒ���̻���վ�ϵ�URL
	    String goods_url ="goods_url="+request.getParameter("goods_url");
	    String goods_url1="goods_url="+URLEncoder.encode(request.getParameter("goods_url"),"gbk");
	    
	    //����
	    String unit_amount ="unit_amount="+request.getParameter("unit_amount");
	    //����
	    String unit_count ="unit_count="+request.getParameter("unit_count");
	    //�˷�
	    String transport_amount ="transport_amount="+request.getParameter("transport_amount");
	    //�ܽ��
	    String total_amount ="total_amount="+request.getParameter("total_amount");
	   //������̻���վ���û���
	    String tempSPUserName=request.getParameter("buyer_sp_username");
	    String buyer_sp_username ="buyer_sp_username="+tempSPUserName;
        String buyer_sp_username1 ="buyer_sp_username="+URLEncoder.encode(tempSPUserName,"gbk");
	   //��̨֪ͨ��ַ
	    String return_url ="return_url="+request.getParameter("return_url");
	    String return_url1="return_url="+URLEncoder.encode(request.getParameter("return_url"),"gbk");
	   //ǰ̨֪ͨ��ַ
	    String page_url ="page_url="+request.getParameter("page_url");
	    String page_url1="page_url="+URLEncoder.encode(request.getParameter("page_url"),"gbk");
       //֧����ʽ
	    String pay_type ="pay_type="+request.getParameter("pay_type");
	    //Ĭ�����еı���
	    String bank_no ="bank_no="+request.getParameter("bank_no");
	    //�û����̻��˵��û�ID
	    String sp_uno ="sp_uno="+request.getParameter("sp_uno");
	    //�̻��Զ�������
	    String tempextra=request.getParameter("extra");
	    String extra ="extra="+tempextra;
	    String extra1="extra="+URLEncoder.encode(tempextra,"gbk");
	  
	    //ǩ����ƴ������
		String[]array={
			    service_code,
				sp_no,
				order_create_time,
				order_no,
				goods_category,
				goods_name,
				goods_desc,
				goods_url,
				unit_amount,
				unit_count,
				transport_amount,
				total_amount,
				currency,
				buyer_sp_username ,
				return_url,
				page_url,
				pay_type,
				bank_no,
				expire_time,
				input_charset,
				version,
				sign_method
				,extra
				};
		//���������ƴ������
		String[]array1={
			    service_code,
				sp_no,
				order_create_time,
				order_no,
				goods_category,
				goods_name1,
				goods_desc1,
				goods_url1,
				unit_amount,
				unit_count,
				transport_amount,
				total_amount,
				currency,
				buyer_sp_username1,
				return_url1,
				page_url1,
				pay_type,
				bank_no,
				expire_time,
				input_charset,
				version,
				sign_method
				,extra1
				};
		/**
		 * 4������bfb_sdk_comm��create_baifubao_pay_order_url�������ɰٸ�����ʱ����֧���ӿ�URL(����Ҫ��¼)
		 *   array�Ǵ�ǩ����
		 *   array1��ַ��ƴ�Ӵ�
		 */
		String getURL=new BfbSdkComm().create_baifubao_pay_order_url(array,array1,BFB_PAY_DIRECT_NO_LOGIN_URL);
		//����־�����ӡ�ύ��
		logger.log(Level.INFO, "��ʱ֧��������Ҫ��½��getURL�ύ��ַ����"+getURL);
		//��ӡ��ɹر���־
		logger.setLevel(Level.OFF);
		/**
		 * 5���ύ֧��������ת���ٸ�������̨
		 */
		response.sendRedirect(getURL); 
	    out.flush();
	    out.close();	
	}
    
	public String getServletInfo() {
		return "PayUnloginServlet";
	}
	public void init(ServletConfig config) throws ServletException {
		scon = config;
	}

}
