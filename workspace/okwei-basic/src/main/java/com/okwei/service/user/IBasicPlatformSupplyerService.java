package com.okwei.service.user;

import com.okwei.bean.domain.AdminUser;
import com.okwei.bean.dto.AddCompanyDataDTO;
import com.okwei.bean.vo.ReturnModel;

public interface IBasicPlatformSupplyerService {
	/**
	 * 添加公司资料
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel addCompanyData(AddCompanyDataDTO param,AdminUser user);
	/**
	 * 修改公司资料
	 * @param param
	 * @param user
	 * @return
	 */
	ReturnModel editCompanyData(AddCompanyDataDTO param,AdminUser user);
	
	
}
