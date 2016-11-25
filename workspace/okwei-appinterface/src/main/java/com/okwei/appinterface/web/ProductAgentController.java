package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.VerifierProductAgentListParam;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.vo.MyAgentOrProductShopListVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.web.base.BaseController;
import com.sdicons.json.mapper.MapperException;

@RequestMapping("/ProductAgent")
@RestController
public class ProductAgentController extends BaseController{
	@Autowired
	private IBasicAgentOrProductShopService iBasicAgentOrProductShopService;
	
	/**
	 * 认证员的代理商列表
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getMyVerifyAgentList")
	public String getMyVerifyAgentList(VerifierProductAgentListParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		MyAgentOrProductShopListDTO dto = new MyAgentOrProductShopListDTO();
		//模拟查询条件
		dto.setAuditState(null);
		dto.setVerifierWeiId(1L);
		PageResult<MyAgentOrProductShopListVO> pageResult = iBasicAgentOrProductShopService.getMyDevelopAgent(Limit.buildLimit(param.getPageIndex(), param.getPageSize()),dto);
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
}
