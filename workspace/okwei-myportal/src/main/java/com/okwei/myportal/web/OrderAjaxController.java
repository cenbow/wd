package com.okwei.myportal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.common.AjaxUtil; 
import com.okwei.myportal.service.IOrderManageService;
import com.okwei.util.ObjectUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/order")
public class OrderAjaxController extends SSOController
{
    @Autowired
    private IOrderManageService orderService;

    @ResponseBody
    @RequestMapping(value = "/orderajax",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String orderajax(String key,String orderNo,Long refundId,String kuaidi,String danhao)
    {
        String returnData = "";
        if(key == null || key == "")
        {
            return AjaxUtil.ajaxSuccess("参数有误!");
        }
        long weiid = getUserOrSub().getWeiID();
        switch(key)
        {
            case "delete":// 删除订单操作
                returnData = deleteOrder(weiid,orderNo);
                break;
            case "cancel":// 取消订单操作
                returnData = cancelOrder(weiid,orderNo);
                break;
            case "confirmcargo":// 确认收货操作
                returnData = confirmcargo(weiid,orderNo);
                break;
            case "cancelrefund":// 取消退款操作
                returnData = cancelrefund(weiid,refundId);
                break;
            case "refunddelivery":// 退货发货
                returnData = refunddelivery(weiid,refundId,danhao,kuaidi);
                break;
            case "meddle":// 微店网介入
                returnData = meddle(weiid,refundId);
                break;
            default:
                return AjaxUtil.ajaxSuccess("参数有误!");
        }
        return AjaxUtil.ajaxSuccess(returnData);
    }

    /**
     * 微店网介入
     */
    public String meddle(Long weiId,Long refundId)
    {
        if(refundId == null || refundId <= 0)
        {
            return "参数不能为空";
        }
        String ref = orderService.OkweiIntervention(refundId,weiId);
        return ref;
    }

    /**
     * 取消退款申请
     */
    public String cancelrefund(Long weiId,Long refundId)
    {
        if(refundId == null || refundId <= 0)
        {
            return "参数不能为空";
        }
        String ref = orderService.cancelRefund(weiId,refundId);
        return ref;
    }

    /**
     ** 退货发货
     **/
    public String refunddelivery(Long weiid,Long refundId,String danhao,String kuaidi)
    {
        if(ObjectUtil.isEmpty(kuaidi) || ObjectUtil.isEmpty(kuaidi) || ObjectUtil.isEmpty(danhao) || ObjectUtil.isEmpty(refundId))
        {
            return "参数不能为空";
        }
        String ref = orderService.buyersDelivery(weiid,refundId,danhao,kuaidi);
        return ref;
    }// buyersDelivery

    /**
     * 确认收货
     */
    public String confirmcargo(Long weiid,String orderNo)
    {
        if(ObjectUtil.isEmpty(orderNo))
        {
            return "参数不能为空";
        }
        String ref = orderService.confirmationReceipt(weiid,orderNo);
        return ref;
    }

    /**
     * 删除订单
     */
    public String deleteOrder(Long weiid,String orderNo)
    {
        if(ObjectUtil.isEmpty(orderNo))
        {
            return "参数不能为空";
        }
        String ref = orderService.deleteOrder(weiid,orderNo);
        return ref;
    }

    /**
     * 取消订单
     */
    public String cancelOrder(Long weiid,String orderNo)
    {
        if(ObjectUtil.isEmpty(orderNo))
        {
            return "参数不能为空";
        }
        String ref = orderService.cancelOrder(weiid,orderNo);
        return ref;
    }
}
