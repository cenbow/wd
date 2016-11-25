package com.okwei.appinterface.web.activity;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.service.agent.IAgentApiService;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.activity.ActProductInfo;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
@RequestMapping("/test")
public class PrivateController extends SSOController{

	@Autowired
	private IProductSearchDao searchDao;
	@Autowired
	private IAgentApiService agentService;
	
	@Autowired
	private IBaseActivityService actService;
	
	@ResponseBody
	@RequestMapping(value = "/testA")
	public String testA(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize)
			throws MapperException {
		ReturnModel rq=new ReturnModel();
		rq.setStatu(ReturnStatus.SystemError);
		rq.setStatusreson("参数错误");
		return JsonUtil.objectToJsonStr(rq);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findProAct")
	public String findProductRedis(@RequestParam(required = false, defaultValue = "1") long productid,@RequestParam(required = false, defaultValue = "0") int type,@RequestParam(required = false, defaultValue = "0") long actid,@RequestParam(required = false, defaultValue = "1") int pageIndex,@RequestParam(required = false, defaultValue = "10") int pageSize)
			throws MapperException {
		ReturnModel rq=new ReturnModel();
		if(type==0){
			ActProductInfo info =searchDao.get_ProductAct(productid);
			if(info!=null){
				rq.setBasemodle(info);
			}
		}else if(type==1){
			searchDao.del_redis_productAct(productid);
			rq.setStatusreson("清除产品缓存");
		}else if(type==2){
			searchDao.set_redis_productAct(productid);
			rq.setStatusreson("加入缓存");
		}else if(type==3){
			PageResult<AActivityProducts> result= actService.find_ApplyProductListBySellerID(actid, null, pageIndex, pageSize);
			if(result.getList()!=null&&result.getList().size()>0){
				for (AActivityProducts pp : result.getList()) {
					searchDao.set_redis_productAct(pp.getProductId()); 
				}
			}
			rq.setBasemodle(result.getTotalPage()); 
		}
		rq.setStatu(ReturnStatus.Success);
		return JsonUtil.objectToJsonStr(rq);
	}
	/**
	 * 更新用户代理身份标示
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping(value = "/agentIdentity")
	public String agentIdentity(@RequestParam(required = false, defaultValue = "1") int pageIndex,@RequestParam(required = false, defaultValue = "10") int pageSize)
			throws MapperException {
		ReturnModel rq=new ReturnModel();
		long count= agentService.updateAgentUserIdentity(pageIndex, pageSize);
		rq.setStatu(ReturnStatus.Success);
		rq.setBasemodle(count);
		return JsonUtil.objectToJsonStr(rq);
	}
	
}
