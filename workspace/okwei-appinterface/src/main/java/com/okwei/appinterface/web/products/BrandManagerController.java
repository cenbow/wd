package com.okwei.appinterface.web.products;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.BrandVO;
import com.okwei.common.PageResult;
import com.okwei.service.product.IBrandManagerService;
import com.okwei.web.base.SSOController;

/**
 * 品牌管理
 * @author yangjunjun
 *
 */
@RequestMapping("/brandManager")
@RestController
public class BrandManagerController extends SSOController {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private IBrandManagerService brandManager;
	
	/**
	 * 获得品牌列表
	 * @param classId
	 * @return
	 */
	@RequestMapping(value="/findBrandList", method=RequestMethod.POST)
	public String findBrandList(Integer pageIndex,Integer pageSize,Integer classId) {
		ReturnModel rs = new ReturnModel();
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				PageResult<BrandVO> list = brandManager.getBrandPageResult(loginUser.getWeiID(),classId,pageIndex,pageSize);
				if(list == null)
				{
					rs.setStatu(ReturnStatus.ParamError);
					rs.setStatusreson("该供应商没有品牌");
				}
				else
				{
					rs.setBasemodle(list);
					rs.setStatu(ReturnStatus.Success);
					rs.setStatusreson("");
				}
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("获得品牌列表列表异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	
	

}
