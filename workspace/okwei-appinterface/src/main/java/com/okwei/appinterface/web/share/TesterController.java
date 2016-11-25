package com.okwei.appinterface.web.share;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.share.MyShareVO;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.vo.PageInfo;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.friendcircle.IFriendCircleListService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
@RequestMapping("/tester")
public class TesterController extends SSOController {

//	@Autowired
//	private IUserBasicService userBasicService;
	@Autowired
	private IFriendCircleListService friendService;

	@RequestMapping("/contactList")
	public String contactList(String keyword, long userid, int scope, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {

		ReturnModel rm = friendService.find_ContactList(userid, keyword, scope, pageIndex, pageSize);
	
		return JsonUtil.objectToJsonStr(rm);
	}
	
	@RequestMapping("/sharePageList")
	public String sharePageList( long userid, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {

		ReturnModel rm  = friendService.find_sharelist(userid, pageIndex, pageSize);
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	@RequestMapping("/forwardSharePage")
	public String forwardSharePage(String shareJson,long userid,int scope, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
		List<MyShareVO> list=(List<MyShareVO>)JsonUtil.json2Objectlist(shareJson, MyShareVO.class);
		List<Long> idsList=new ArrayList<Long>();
		if(list!=null&&list.size()>0){
			for (MyShareVO ss : list) {
				idsList.add(ss.getPageId());
			}
		}
		ReturnModel rm  = friendService.add_FriendShare(idsList, scope, userid);
		return JsonUtil.objectToJsonStr(rm);
	}
}
