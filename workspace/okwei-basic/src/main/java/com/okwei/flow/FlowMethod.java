package com.okwei.flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.okwei.bean.vo.FFlowMsg;
import com.okwei.bean.vo.FTask;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.util.HttpClientUtil;

import net.sf.json.JSONObject;



public class FlowMethod {

	public  static boolean ActionFlow(String taskid,Long userid,String operate,String content)	
	{
		if("".equals(taskid) || userid ==null || "".equals(operate))
		{
			return false;
		}
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("taskid", taskid);
		map.put("operate", operate);
		map.put("content", content);
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/ActionFlow";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
			return false;
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
			return false;
		if("1".equals(json.get("Statu").toString()))					
			return true;
		else
			return false;
	}
	
	public  static boolean StartFlow(String flowname,Long userid,String busname,String bipk,String parentid)	
	{
		if("".equals(flowname) || userid ==null || "".equals(busname) || "".equals(bipk))
		{
			return false;
		}
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("flowname", flowname);
		map.put("busname", busname);
		map.put("bipk", bipk);
		map.put("parentid", parentid);
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/StartFlow";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
			return false;
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
			return false;
		if("1".equals(json.get("Statu").toString()))					
			return true;
		else
			return false;
	}
	
	public  static boolean CancelFlow(String flowid,Long userid)	
	{
		if("".equals(flowid) || userid ==null )
		{
			return false;
		}
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("flowid", flowid);
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/CanceFlow";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
			return false;
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
			return false;
		if("1".equals(json.get("Statu").toString()))					
			return true;
		else
			return false;
	}
	
	public static ReturnModel QueryHistory(String flowid,Long userid,int pageindex,int pagesize)
	{
		
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("flowid", flowid);
		map.put("pageindex", String.valueOf(pageindex));
		map.put("pagesize", String.valueOf(pagesize));
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/QueryHistory";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("网络异常");
			return rs;
		}
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
		
		String strData=json.getString("BaseModle");
		String strStatu=json.getString("Statu");
		String strReson=json.getString("StatusReson");
		if("1".equals(strStatu))
		{
			JSONObject jo= JSONObject.fromObject(strData);
			String strData2=jo.getString("Data");
//			List<FFlowMsg> lists=(List<FFlowMsg>) JsonUtil.json2Objectlist(strData2, FFlowMsg.class);
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(strData2);
			rs.setStatu(ReturnStatus.Success);
			rs.setStatusreson(strReson);
			return rs;
		}
		else
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
	}
	
	public static ReturnModel QueryTask(Long userid,int pageindex,int pagesize)
	{
		
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("pageindex", String.valueOf(pageindex));
		map.put("pagesize", String.valueOf(pagesize));
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/QueryTask";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("网络异常");
			return rs;
		}
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
		
		String strData=json.getString("BaseModle");
		String strStatu=json.getString("Statu");
		String strReson=json.getString("StatusReson");
		if("1".equals(strStatu))
		{
			JSONObject jo= JSONObject.fromObject(strData);
			String strData2=jo.getString("Data");
//			List<FTask> lists=(List<FTask>) JsonUtil.json2Objectlist(strData2, FTask.class);
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(strData2);
			rs.setStatu(ReturnStatus.Success);
			rs.setStatusreson(strReson);
			return rs;
		}
		else
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
	}
	
	
	public static ReturnModel QueryTask(String flowid,Long userid)
	{
		
		Map<String,String> map= new HashMap<String,String>();
		map.put("userid", userid.toString());
		map.put("flowid", flowid);
		ResourceBundle bundle = ResourceBundle.getBundle("flow");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[flow.properties] is not found!");
		}
		String url=bundle.getString("FlowURL")+"/services/QueryTaskByFlow";
		String returnMsg=HttpClientUtil.doGetMethod(url, map);
		if("调用外部链接异常！".equals(returnMsg))
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("网络异常");
			return rs;
		}
		JSONObject json= JSONObject.fromObject(returnMsg);
		if(json==null)
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
		String strData=json.getString("BaseModle");
		String strStatu=json.getString("Statu");
		String strReson=json.getString("StatusReson");
		if("1".equals(strStatu))
		{
//			FTask lists=(FTask) JsonUtil.json2Objectlist(strData, FTask.class);
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(strData);
			rs.setStatu(ReturnStatus.Success);
			rs.setStatusreson(strReson);
			return rs;
		}
		else
		{
			ReturnModel rs= new ReturnModel();
			rs.setBasemodle(null);
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("数据异常");
			return rs;
		}
	
	}
}
