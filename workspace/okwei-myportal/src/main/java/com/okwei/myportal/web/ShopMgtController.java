package com.okwei.myportal.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.QueryParam;
import com.okwei.myportal.bean.vo.DistributorVO;
import com.okwei.myportal.service.IDistributorMgtService;
import com.okwei.web.base.BaseController;

@Controller
@RequestMapping(value = "/shopMgt")
public class ShopMgtController extends BaseController
{

    private final static Log logger = LogFactory.getLog(ShopMgtController.class);

    @RequestMapping(value = "/classify")
    public String classify(@ModelAttribute("queryParam") QueryParam queryParam,@RequestParam(required = false,defaultValue = "1") int pageId,Model model)
    {
        return "shopmgt/classify";
    }

}
