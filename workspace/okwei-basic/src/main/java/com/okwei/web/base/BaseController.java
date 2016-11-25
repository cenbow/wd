package com.okwei.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.okwei.service.IBaseService;

/**
 * 
 * @ClassName: BaseController
 * @Description: 基础控制器类
 * @author xiehz
 * @date 2015年5月5日 下午4:04:20
 *
 */
@Controller(value="baseController")
public class BaseController {
	
	@Autowired
	private IBaseService baseService;
	
	
	
}
