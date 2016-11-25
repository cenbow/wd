package com.okwei.myportal.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okwei.bean.vo.LoginUser;
import com.okwei.common.Limit;
import com.okwei.common.PageResult; 
import com.okwei.myportal.bean.vo.SaleOrderVO;
import com.okwei.myportal.service.ISaleOrderService;
import com.okwei.myportal.service.impl.SaleOrderService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/sale")
public class SaleOrderController extends SSOController
{
    @Autowired
    ISaleOrderService saleOrderService;

    @RequestMapping(value = "/saleOrder")
    public String saleOrder(@RequestParam(required = false,defaultValue = "1") int pageId,@RequestParam(required = false,defaultValue = "10") int pageSize,String title,
            String name,String start,String end,Model model)
    {
        LoginUser user = super.getUserOrSub();
        long weiid = user.getWeiID();
        model.addAttribute("userinfo",user);
        model.addAttribute("title",title);
        model.addAttribute("name",name);
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        Date beginTime;
        Date endTime = new Date();
        if(ObjectUtil.isEmpty(start))
        {
            beginTime = null;
            // beginTime = DateUtils.format("2012-01-01 01:01:01","yyyy-MM-dd HH:mm:ss");
        }
        else
        {
            beginTime = DateUtils.format(start,"yyyy-MM-dd HH:mm:ss");
        }
        if(ObjectUtil.isEmpty(end))
        {
            endTime = null;
        }
        else
        {
            endTime = DateUtils.format(end,"yyyy-MM-dd HH:mm:ss");
        }
        Limit limit = Limit.buildLimit(pageId,pageSize);
        PageResult<SaleOrderVO> saleList = saleOrderService.getSaleOrderVOList(weiid,limit,title,name,beginTime,endTime);
        if(saleList!=null && saleList.getList() != null && saleList.getList().size() > 0)
        {
            for(SaleOrderVO vo : saleList.getList())
            {
                vo.setProImg(ImgDomain.GetFullImgUrl(vo.getProImg()));
            }
        }
        model.addAttribute("saleList",saleList);
        return "sale/saleOrder";
    }
}
