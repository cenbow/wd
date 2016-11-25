package com.okwei.myportal.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.common.AjaxUtil;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.flow.FlowMethod;
import com.okwei.myportal.bean.vo.BuyOrderListVO;
import com.okwei.myportal.bean.vo.LogisticsVO;
import com.okwei.myportal.bean.vo.OrderCountVO;
import com.okwei.myportal.bean.vo.OrderDetailsVO;
import com.okwei.myportal.bean.vo.PreOrderListVO;
import com.okwei.myportal.bean.vo.RefundVO;
import com.okwei.myportal.bean.vo.ResultMsg;
import com.okwei.myportal.service.IOrderManageService;
import com.okwei.myportal.service.IRepaymentService;
import com.okwei.myportal.service.ITest;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/order")
public class OrderMgtController extends SSOController {

    @Autowired
    private IOrderManageService orderService;

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    ITest itest;
    /**
     * 购买订单列表
     * 
     * @param datatype 订单类型
     * @param datastate  订单状态
     * @param pageNum    当前页数
     * @param model
     * @return
     */
    @RequestMapping(value = "/buylist", method = { RequestMethod.POST, RequestMethod.GET })
    public String buylist(@ModelAttribute("queryParam") ParamOrderSearch param,@RequestParam(required = false, defaultValue = "-1") int dt,
    		@RequestParam(required = false, defaultValue = "-1") short ds,
    		@RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	LoginUser user = super.getUserOrSub();
	long weiid = user.getWeiID();
	model.addAttribute("dt", dt);
	model.addAttribute("ds", ds);
	 if (dt!=-1) {
		 param.setOrderType(dt);
	 }
	 param.setState(ds);
	   // 开始时间
	   if(param.getBeginTimeStr() != null && !"".equals(param.getBeginTimeStr()))
	   {
	       param.setBeginTime(DateUtils.format(param.getBeginTimeStr(),"yyyy-MM-dd HH:mm:ss"));
	   }
	   // 结束时间
	   if(param.getEndTimeStr() != null && !"".equals(param.getEndTimeStr()))
	   {
	       param.setEndTime(DateUtils.format(param.getEndTimeStr(),"yyyy-MM-dd HH:mm:ss"));
	   }
	PageResult<BuyOrderListVO> pageRes = orderService.getOrderList(weiid, Limit.buildLimit(pageId, 10), param);
	OrderCountVO orderCount = orderService.getOrderCount(weiid, dt);// 订单数量
	model.addAttribute("pageRes", pageRes);// 订单列表
    model.addAttribute("param",param);//查询条件
	model.addAttribute("orderCount", orderCount);// 订单数量
	model.addAttribute("userinfo", user);// 用户信息
	return "order/buylist";
    }

    /**
     * 购买订单详情
     * 
     * @param orderNo
     *            订单号
     * @param model
     *            返回实体
     * @return
     */
    @RequestMapping(value = "/buydetails", method = { RequestMethod.POST, RequestMethod.GET })
    public String buydetails(String orderNo, Model model) {
	if (orderNo == null || orderNo == "0") {
	    return "redirect:/order/buylist";// 参数为空，跳转到列表页
	}
	LoginUser user = super.getUserOrSub();
	long weiId = user.getWeiID();
	OrderDetailsVO details = orderService.getOrderDetails(weiId, orderNo);
	if(weiId!=details.getBuyerId()&&weiId==details.getSellerId()){//代理商详情
		model.addAttribute("sel", 1);
	}else {
		model.addAttribute("sel", 0);
	}
	model.addAttribute("details", details);
	model.addAttribute("userinfo", user);// 用户信息
	return "order/buydetails";
    }

    /**
     * 预定/铺货列表
     * 
     * @param datatype
     * @param datastate
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "/reservelist", method = { RequestMethod.POST, RequestMethod.GET })
    public String reservelist(@ModelAttribute("queryParam") ParamOrderSearch param,
    		@RequestParam(required = false, defaultValue = "-1") short ds,
    		@RequestParam(required = false, defaultValue = "-1") int dt, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
	model.addAttribute("dt", 3);
	model.addAttribute("ds", ds);
    LoginUser user = super.getUserOrSub();
    model.addAttribute("userinfo",user);
    // 开始时间
    if(param.getBeginTimeStr() != null && "" != param.getBeginTimeStr())
    {
        param.setBeginTime(DateUtils.format(param.getBeginTimeStr(),"yyyy-MM-dd HH:mm:ss"));
    }
    // 结束时间
    if(param.getEndTimeStr() != null && "" != param.getEndTimeStr())
    {
        param.setEndTime(DateUtils.format(param.getEndTimeStr(),"yyyy-MM-dd HH:mm:ss"));
    }
    param.setOrderType(3);
    param.setState(ds);
	long weiid = user.getWeiID();
	PageResult<PreOrderListVO> pageRes = null;
	OrderCountVO orderCount = null;
	if (weiid>0) {
		pageRes = orderService.getPreOrderList(weiid, Limit.buildLimit(pageId, 10), param);
		orderCount =	orderService.getOrderCount(weiid, 3);// 订单数量
	} 
	model.addAttribute("pageRes", pageRes);// 订单列表
	model.addAttribute("orderCount", orderCount);// 订单数量
	 model.addAttribute("param",param);//查询条件
	model.addAttribute("userinfo", user);// 用户信息
	return "order/reservelist";
    }

    /**
     * 查看物流
     */
    @RequestMapping(value = "/logistics", method = { RequestMethod.GET })
    public String logistics(String orderNo, Model model) {
	LoginUser user = super.getUserOrSub();
	LogisticsVO logistics = orderService.getLogistics(orderNo);
	model.addAttribute("logisticsT", logistics);// 订单列表
	model.addAttribute("orderNo",orderNo);
	model.addAttribute("userinfo", user);// 用户信息
	if(user!=null&&logistics!=null){
		if(user.getWeiID().longValue()==logistics.getSellerWeiid().longValue()&&(logistics.getOrderState().intValue()==Integer.parseInt(OrderStatusEnum.Deliveried.toString()))){
			model.addAttribute("canModify", 1);// 卖家已发货的状态 ，可以修改物流信心
		}
		
	}
	return "order/logistics";
    }

    /**
     * 申请退款
     */
    @RequestMapping(value = "/applyrefund", method = { RequestMethod.GET })
    public String applyrefund(String orderNo, Model model) {
	if (orderNo == null || orderNo == "") {
	    return "redirect:/order/buylist";
	}
	LoginUser user = super.getUserOrSub();
	long weiid = user.getWeiID();
	OrderDetailsVO details = orderService.getOrderDetails(weiid, orderNo);
	// 只有订单状态在 已付款 已发货 已收货 才能退款
	int orderState = details.getOrderState();
	if (!(orderState == Integer.parseInt(OrderStatusEnum.Payed.toString()) || orderState == Integer.parseInt(OrderStatusEnum.Deliveried.toString()) || orderState == Integer.parseInt(OrderStatusEnum.Accepted.toString()))) {
	    return "redirect:/order/buylist";
	}
	model.addAttribute("details", details);// 订单详情
	model.addAttribute("userinfo", user);// 用户信息
	return "order/applyrefund";
    }

    /**
     * 退款详情
     * 
     * @param refundID
     * @param model
     * @return
     */
    @RequestMapping(value = "/refundetail", method = { RequestMethod.GET })
    public String refundetail(Long refundId, Model model) {
	if (refundId == null || refundId.longValue() <= 0) {
	    return "redirect:/order/buylist";
	}
	LoginUser user = super.getUserOrSub();
	long weiid = user.getWeiID();
	RefundVO refund = orderService.getRefundInfo(weiid, refundId);
	model.addAttribute("details", refund);// 退款详情
	model.addAttribute("userinfo", user);// 用户信息
	return "order/refundetail";
    }

    /**
     * 退货发货
     * 
     * @param orderNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/refunddelivery", method = { RequestMethod.GET })
    public String refunddelivery(Long refundId, Model model) {
	LoginUser user = super.getUserOrSub();
	long weiid = user.getWeiID();
	RefundVO refund = orderService.getRefundInfo(weiid, refundId);
	model.addAttribute("details", refund);// 退款详情
	List<Map<String, String>> list = AppSettingUtil.getMaplist("transports");
	model.addAttribute("wuliu", list);
	model.addAttribute("userinfo", user);// 用户信息
	return "order/refunddelivery";
    }

    /**
     * 支付跳转
     */
    @ResponseBody
    @RequestMapping(value = "/orderpayjump", method = { RequestMethod.GET, RequestMethod.POST })
    public String orderpayjump(String orderNo) {
	ResultMsg result = orderService.returnPayOrderID(orderNo);
	return JsonUtil.objectToJson(result);
    }
    
    @Autowired
    private IRepaymentService repaymentService;
    
    /**
     * 重新生成支付订单
     */
    @ResponseBody
    @RequestMapping(value = "/Repayment", method = { RequestMethod.GET, RequestMethod.POST })
    public String Repayment(String orderNo) {
	ResultMsg result = repaymentService.getNewPayOrder(orderNo);
	return JsonUtil.objectToJson(result);
    }

    /**
     * 提交退款申请
     */
    @ResponseBody
    @RequestMapping(value = "/submitApply", method = { RequestMethod.POST, RequestMethod.GET })
    public String submitApply(String supOrderID, String proOrderID, short tkType, String tkReason, String tkImage, double tkMoney) {
	long weiid = getUser().getWeiID();
	if (proOrderID == null || proOrderID == "") {
	    return AjaxUtil.ajaxSuccess("请选择退款的产品");
	}
	if (tkReason == null || tkReason == "") {
	    return AjaxUtil.ajaxSuccess("请输入退款原因");
	}
	if (tkMoney <= 0) {
	    return AjaxUtil.ajaxSuccess("退款金额必须大于0");
	}
	String result = "";
	try {
	    BuyOrderListVO entity = orderService.applyRefund(weiid, supOrderID, proOrderID, tkType, tkReason, tkImage, tkMoney);
	    if (entity.getOrderState() == 1) {
		result = "1";
		// 启动工作流
		logger.error("启动工作流：流程名" + entity.getCreateTimeStr() + "weiid:" + weiid + "BIPK:" + entity.getSupplyId());
		FlowMethod.StartFlow(entity.getCreateTimeStr(), weiid, "IBS", String.valueOf(entity.getSupplyId()), null);
	    } else {
		result = entity.getSupplyerName();
	    }
	} catch (Exception e) {
	    result = "提交退款申请失败："+e.getMessage();
	}
	return AjaxUtil.ajaxSuccess(result);
    }
}
