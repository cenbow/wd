package com.okwei.appinterface.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.RegionalParamDTO;
import com.okwei.appinterface.service.BaseCommonService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.IBasicCommonService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * 订单列表
 * 
 * @author fuhao
 *
 */
@RequestMapping("/Common")
@RestController
public class CommonController extends SSOController  {
	
	@Autowired
	public BaseCommonService baseCommonService;
	
	@Autowired
	private IBasicCommonService basicCommonService;
	/**
	 * 获取 分享url
	 * @param list
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getShareUrl")
	public String getShareUrl(HttpServletRequest request,String param,int type) throws MapperException {
		ReturnModel rq=new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rq.setStatu(ReturnStatus.LoginError);
			rq.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rq);
		}else{
			List<Map<String, String>>list= AppSettingUtil.getMaplist("IShareMsg");
			Map<String, Object> mp = new HashMap<String, Object>();
			boolean isok=false;
			if(list!=null&&list.size()>0){
				for (Map<String, String> map : list) {
					int shareTypeId=ParseHelper.toInt(map.get("typeId"));
					if(type==shareTypeId){
						isok=true;
						mp.put("title", map.get("name"));
						mp.put("imageUrl", map.get("imgsrc"));
						mp.put("content", map.get("content"));
						String shareUrl=map.get("shareUrl")+ "?w="+user.getWeiID();
						String paramStr=map.get("paramName");
						if(!"".equals(paramStr)&&null!=paramStr){
							String[] paramArr=paramStr.split(",");
							if(paramArr!=null&&paramArr.length>1){
								for (String ss : paramArr) {
									String val=request.getParameter(ss);
									if(val!=null&&!"".equals(val)){
										shareUrl+="&"+ss+"="+val;
									}
								}
							}else {
								String valString=request.getParameter(paramStr);
								if(null!=valString&&!"".equals(valString)){
									shareUrl+="&"+paramStr+"="+valString;
								}else {
									shareUrl+="&"+paramStr+"="+param;
								}
							}
						}
						mp.put("shareUrl",  shareUrl);
						continue;
					}
				}
			}
			if(isok){
				rq.setBasemodle(mp);
				rq.setStatu(ReturnStatus.Success);
			}else {
				try {
					rq=baseCommonService.getShareUrl(type, user, param);
				} catch (Exception e) {
					rq.setStatu(ReturnStatus.ParamError);
					rq.setStatusreson("参数有误"); 
					rq.setBasemodle(e); 
				}
			}
		}
		
		return JsonUtil.objectToJsonStr(rq); 
		}
	
	
	
	 
	
	/**
	 * 获取公共配置
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getConfig")
	public String getConfig() throws MapperException{
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		try {
			List<Map<String, String>> urls = AppSettingUtil.getMaplist("wapurl");
			rm.setStatu(ReturnStatus.Success);
			rm.setStatusreson("成功！");
			rm.setBasemodle(urls);
		} catch (Exception ex) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson(ex.getMessage());
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 获取区域信息
	 * @return
	 * @throws MapperException
	 */
    @RequestMapping("/getRegional")
    public String getRegional(RegionalParamDTO queryParm) throws MapperException{
	    ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
	    rm =basicCommonService.getRegionalModel(queryParm.getType(), queryParm.getVer());
		
	    return JsonUtil.objectToJsonStr(rm);
    }
}
