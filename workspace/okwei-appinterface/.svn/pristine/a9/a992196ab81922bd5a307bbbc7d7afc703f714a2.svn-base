package com.okwei.appinterface.web.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.activity.FlashSaleActivityModel;
import com.okwei.bean.domain.AActivityTimespans;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.activity.FlashSaleActivityVO;
import com.okwei.bean.vo.activity.SaleActivityProductModel;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.activity.IBasePerfectProductService;
import com.okwei.util.DateUtils;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
@RequestMapping("/mall")
public class ActController extends SSOController {
	@Autowired
	public IBasePerfectProductService basePerfectProductService;

	@Autowired
	public IBaseActivityService baseActService;
	/**
	 * M03 购物首页精选单品
	 * @param pageList
	 * @param scope
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/homeProductList")
	public String findPerfectProductList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "20") int pageSize) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		rm=basePerfectProductService.findPerfectProductlist(pageIndex, pageSize);
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/***
	 * M04 每日限时抢购活动列表
	 * 
	 * @param actId 当前选中id
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping(value = "/flashSaleList")
	public String flashSaleList(@RequestParam(required = false, defaultValue = "1") int actId) throws MapperException {
		List<AActivityTimespans> timelist = baseActService.find_ActTimeSpanslist();
		Date nowtime = new Date();
		List<FlashSaleActivityModel> resultList = new ArrayList<FlashSaleActivityModel>();
		//开始时间
		Calendar todayBegin = Calendar.getInstance();
		todayBegin.setTime(nowtime);
		//结束时间
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(nowtime);
		for (AActivityTimespans tt : timelist) {
			todayBegin.set(Calendar.HOUR_OF_DAY, tt.getShours()); 
			todayBegin.set(Calendar.MINUTE, tt.getSminutes()); 
			todayBegin.set(Calendar.SECOND, tt.getSseconds());
			todayEnd.set(Calendar.HOUR_OF_DAY, tt.getEhours()); 
			todayEnd.set(Calendar.MINUTE, tt.getEminutes()); 
			todayEnd.set(Calendar.SECOND, tt.getEseconds());
			
			FlashSaleActivityModel mo = new FlashSaleActivityModel();
			mo.setActId(tt.getId());
			mo.setBeginTime(DateUtils.format(todayBegin.getTime(), "yyyy-MM-dd HH:mm:ss"));
			mo.setEndTime(DateUtils.format(todayEnd.getTime(), "yyyy-MM-dd HH:mm:ss"));
			mo.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			mo.setTitle(DateUtils.format(todayBegin.getTime(), "HH:mm")); 
			if (todayBegin.getTime().getTime()>nowtime.getTime()) {
				mo.setStateText("即将开始");
				resultList.add(mo);
			} else if (todayBegin.getTime().getTime() <= nowtime.getTime() && todayEnd.getTime().getTime() > nowtime.getTime()) {
				mo.setStateText("抢购中");
				mo.setState(1);
				resultList.add(mo);
			}
			
		}
		//明天预告
		if (resultList != null && resultList.size() < timelist.size()) {
			Calendar tomorrowBegin = Calendar.getInstance();
			tomorrowBegin.setTime(nowtime);
			tomorrowBegin.add(Calendar.DAY_OF_YEAR, 1);
			Calendar tomorrowEnd = Calendar.getInstance();
			tomorrowEnd.setTime(nowtime);
			tomorrowEnd.add(Calendar.DAY_OF_YEAR, 1);
			int temp = resultList.size();
			int count = timelist.size();
			for (int i = 0; i < count - temp; i++) {
				tomorrowBegin.set(Calendar.HOUR_OF_DAY, timelist.get(i).getShours()); 
				tomorrowBegin.set(Calendar.MINUTE, timelist.get(i).getSminutes()); 
				tomorrowBegin.set(Calendar.SECOND, timelist.get(i).getSseconds());
				tomorrowEnd.set(Calendar.HOUR_OF_DAY, timelist.get(i).getEhours()); 
				tomorrowEnd.set(Calendar.MINUTE, timelist.get(i).getEminutes()); 
				tomorrowEnd.set(Calendar.SECOND, timelist.get(i).getEseconds());
				FlashSaleActivityModel mo = new FlashSaleActivityModel();
				mo.setActId(10 + timelist.get(i).getId());
				mo.setBeginTime(DateUtils.format(tomorrowBegin.getTime(), "yyyy-MM-dd HH:mm:ss"));
				mo.setEndTime(DateUtils.format(tomorrowEnd.getTime(), "yyyy-MM-dd HH:mm:ss"));
				mo.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				mo.setTitle(DateUtils.format(tomorrowBegin.getTime(), "HH:mm")); 
				mo.setStateText("明日预告");
				resultList.add(mo);
			}
		}
		ReturnModel rModel = new ReturnModel();
		rModel.setStatu(ReturnStatus.Success);
		rModel.setStatusreson("成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentActivityId", resultList.get(0).getActId());//可以不用，其实没什么用
		map.put("list", resultList);
		ReturnModel rqModel=new ReturnModel();
		rqModel.setStatu(ReturnStatus.Success);
		rqModel.setBasemodle(map);
		return JsonUtil.objectToJsonStr(rqModel);
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/flashSaleList2")
//	public String flashSaleList2(@RequestParam(required = false, defaultValue = "1") int actId) throws MapperException {
//		
//		ReturnModel rModel = new ReturnModel();
//		List<FlashSaleActivityVO> list= baseActService.flashSaleList();
//		rModel.setStatu(ReturnStatus.Success);
//		rModel.setBasemodle(list);
//		return JsonUtil.objectToJsonStr(rModel);
//	}

	/***
	 * M05 限时抢购活动商品列表
	 * 
	 * @param dto
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping(value = "/flashSaleProductList")
	public String flashSaleProductList(@RequestParam(required = false, defaultValue = "0") int actId, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize)
			throws MapperException {
		ReturnModel rq=new ReturnModel();
		List<AActivityTimespans> timelist = baseActService.find_ActTimeSpanslist();
		int days = actId / 10;
		int keyId = actId % 10;
		for (AActivityTimespans aa : timelist) {
			if(aa.getId()==keyId){
				//开始时间
				Calendar start = new GregorianCalendar();
				start.setTime(new Date());
				if(days>0){
					start.add(Calendar.DATE,days);
				}
				start.set(Calendar.HOUR_OF_DAY, aa.getShours());
				start.set(Calendar.MINUTE, aa.getSminutes()-1);
				start.set(Calendar.SECOND, aa.getSseconds());
			    //结束时间
			    Calendar end = new GregorianCalendar();
			    end.setTime(new Date());
			    if(days>0){
			    	 end.add(Calendar.DATE,days);
			    }
			    end.set(Calendar.HOUR_OF_DAY, aa.getEhours());
			    end.set(Calendar.MINUTE, aa.getEminutes()+1);
			    end.set(Calendar.SECOND, aa.getEseconds());
			    PageResult<SaleActivityProductModel> result=  baseActService.find_ActProductlist(start.getTime(), end.getTime(), pageIndex, pageSize);
			    rq.setStatu(ReturnStatus.Success);
			    rq.setBasemodle(result);
			    rq.setStatusreson("成功");
			    return JsonUtil.objectToJsonStr(rq);
			}
		}
		rq.setStatu(ReturnStatus.SystemError);
		rq.setStatusreson("参数错误");
		return JsonUtil.objectToJsonStr(rq);
	}
}
