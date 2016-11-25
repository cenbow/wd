package com.okwei.myportal.web;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.AjaxUtil;
import com.okwei.flow.FlowMethod;
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.bean.vo.OrderDetailsVO;
import com.okwei.myportal.bean.vo.ProductPriceEditParam;
import com.okwei.myportal.bean.vo.SupplyBookOrderParam;
import com.okwei.myportal.dao.ISellerOrderManagerDAO;
import com.okwei.myportal.service.IOrderManageService;
import com.okwei.myportal.service.ISellerOrderManagerService;
import com.okwei.myportal.service.ITest;
import com.okwei.service.IConfirmRefundService;
import com.okwei.util.ObjectUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/seller")
public class SellerAjaxController extends SSOController {
    @Autowired
    private ISellerOrderManagerService sellerOrderService;
    @Autowired
    private ISellerOrderManagerDAO sellerOrderBase;
    @Autowired
    private IConfirmRefundService confirmRefundService;
    @Autowired
    private IOrderManageService orderManageService;
    @Autowired
    private ITest itest;
    
    private Log logger = LogFactory.getLog(this.getClass());

	  
    @ResponseBody
    @RequestMapping(value = "/orderajax", method = { RequestMethod.POST, RequestMethod.GET })
    public String orderajax(String key, String orderNo, String msg, Long cadid, String danhao, Long processId, String kuaidi, String postage,
    		                String totalPrice, String kuaidiname,Double commission,Double jg) {
	String returnData = "";
	if (key == null || key == "") {
	    return AjaxUtil.ajaxSuccess("参数有误!");
	}
	if(commission != null && jg !=null)
	{
		if(commission>=jg){
			return AjaxUtil.ajaxSuccess("参数有误!");
		}
	}
	LoginUser user = super.getUserOrSub(); 
	if (user == null || user.getWeiID() == 0) {
	    return AjaxUtil.ajaxSuccess("请登录!");
	}
	long weiid = user.getWeiID();
	switch (key) {
	    case "delivery":// 发货
		MsgResult resultMsgResult = sellerOrderService.delivery(orderNo, weiid, kuaidi, kuaidiname, danhao);
		if (resultMsgResult.getState() == 1)
		    returnData = "1";
		else {
		    returnData = resultMsgResult.getMsg();
		}
		break;
	    case "updateorder":// 修改价格
	    
		returnData = updateorder(weiid, postage, orderNo, totalPrice);
	
		break;
	    case "confirmsh":// 确认收货地址*
		returnData = confirmsh(processId, weiid, cadid);
		break;
	    case "delete":// 删除订单操作*
		returnData = delete(weiid, orderNo);
		break;
	    case "agreed":// 同意退款*
		returnData = agreed(weiid, processId);
		break;
	    case "notagreed":// 不同意退款*
		returnData = notagreed(weiid, processId, msg);
		break;
	    case "refused":// 拒绝订单*
		returnData = refused(weiid, orderNo);
		break;
	    case "confirmcargo":// 确认收货操作*
		returnData = confirmcargo(weiid, processId);
		break;
	    case "finalpayment":// 支付尾款确认
		returnData = finalpayment(weiid, orderNo);
		break;

	    default:
		return AjaxUtil.ajaxSuccess("参数有误!");
	}
	return AjaxUtil.ajaxSuccess(returnData);
    }

    /**
     * 支付尾款确认
     */
    public String finalpayment(Long weiId, String orderNo) {
	MsgResult mr = sellerOrderService.bookOrderComplete(weiId, orderNo);
	return String.valueOf(mr.getState());
    }

    /**
     * 确认收货操作
     */
    public String confirmcargo(Long weiId, Long processId) {
	if (processId == null) {
	    return "参数有误";
	}
	/*
	 * MsgResult mr = sellerOrderService.saveRefundRecieved(processId,
	 * weiId); return String.valueOf(mr.getState());
	 */

	// 退款
	try {
	    TOrderBackTotal refund = sellerOrderBase.getEntity(TOrderBackTotal.class, processId);
	    confirmRefundService.refundPenny(weiId, refund);
	} catch (Exception e) {
	    return e.getMessage();
	}
	return "1";
    }

    /**
     * 拒绝预订单
     */
    public String refused(Long weiid, String orderNo) {
	SupplyBookOrderParam sb = new SupplyBookOrderParam();
	sb.setWeiid(weiid);
	sb.setSupplyOrderid(orderNo);
	sb.setSured(false);
	MsgResult mr = sellerOrderService.insertBookOrder(sb);
	return String.valueOf(mr.getState());
    }

    /**
     * 不同意退款
     */
    public String notagreed(Long weiId, Long processId, String msg) {
	if (processId == null) {
	    return "参数有误";
	}
	// boolean ret = editRefundState(weiId,processId,false,msg);
	if (ObjectUtil.isEmpty(msg)) {
	    msg = "供应商不同意";
	}

	MsgResult result = editRefundState(weiId, processId, false, msg);
	if (result.getState() == 1) {
	    return "1";
	} else {

	    return result.getMsg();
	}
    }

    /**
     * 同意退款
     */
    public String agreed(Long weiId, Long processId) {
	if (processId == null) {
	    return "参数有误";
	}
	// boolean ret = editRefundState(weiId, processId, true, "");
	// if (ret) {
	// return "1";
	// } else {
	//
	// return "0";
	// }
	MsgResult result = editRefundState(weiId, processId, true, "供应商同意");
	if (result.getState() == 1) {
	    return "1";
	} else {

	    return result.getMsg();
	}
    }

    /**
     * 删除订单
     */
    public String delete(Long weiId, String orderNo) {
	MsgResult ret = sellerOrderService.deleteSupplyOrder(weiId, orderNo);
	return String.valueOf(ret.getState());
    }

    /**
     * 确认收货地址
     */
    public String confirmsh(Long processId, Long weiId, Long addrid) {
	if (processId == null || addrid == null) {
	    return "参数有误";
	}
	MsgResult ret = sellerOrderService.saveRefundOrderAddr(processId, weiId, addrid);
	if (ret.getState() != 1) {
	    return ret.getMsg();
	}
	MsgResult result = editRefundState(weiId, processId, true, "供应商同意");
	if (result.getState() == 1) {
	    return "1";
	} else {

	    return result.getMsg();
	}

	// return String.valueOf(ret.getState());
    }

    /**
     * 修改价格
     */
    public String updateorder(long weiid, String postage, String supplyOrderId, String totalPrice) {
	if (ObjectUtil.isEmpty(postage) || ObjectUtil.isEmpty(supplyOrderId) || ObjectUtil.isEmpty(totalPrice)) {
	    return "参数有误";
	}
	OrderDetailsVO svo = orderManageService.getOrderDetails(weiid, supplyOrderId);
	if ((svo.getOrderType() == 1 || svo.getOrderType() == 3) && svo.getOrderState() != Integer.parseInt(OrderStatusEnum.Nopay.toString())) {
	    return "只能修改未支付的订单";
	}
	ProductPriceEditParam param = new ProductPriceEditParam();
	param.setPostPrice(Double.parseDouble(postage));
	param.setSupplyOrderId(supplyOrderId);
	param.setTotalPrice(Double.parseDouble(totalPrice));
	param.setSupplyWeiid(weiid);
	MsgResult mResult = sellerOrderService.editProductPrice(param);
	return String.valueOf(mResult.getState());
    }

    /**
     * 发货
     */
    // public String delivery(String supplyOrderid,long weiid,String
    // dcomanpyNo,String deliveryCompany,String deliveryOrderNo)
    // {
    // OSupplyerOrder order =
    // orderBase.getEntity(OSupplyerOrder.class,supplyOrderid);
    // if(order != null &&
    // Short.parseShort(OrderStatusEnum.Refunding.toString()) ==
    // order.getState())
    // {
    // List<TOrderBackTotal> list =
    // orderBase.getTOrderBackTotals(order.getSupplierOrderId());
    // if(list != null && list.size() > 0)
    // {
    // for(TOrderBackTotal too : list)
    // {
    // ReturnModel reModel =
    // FlowMethod.QueryTask(too.getFlowId().toString(),order.getSupplyerId());
    // if(reModel != null && reModel.getStatu().equals(ReturnStatus.Success))
    // {
    // FTask task = (FTask) reModel.getBasemodle();
    // if(FlowMethod.ActionFlow(task.getTaskId(),task.getTaskUser(),"disagree","卖家已经发货"))
    // {
    // List<OProductOrder> opolist =
    // orderBase.getProductsByRefundId(too.getBackOrder());
    // for(OProductOrder opo : opolist)
    // {
    // opo.setState(Short.parseShort(OrderStatusEnum.Deliveried.toString()));
    // sellerOrderService.update(opo);
    // }
    // OSupplyerOrder oso =
    // orderBase.getEntity(OSupplyerOrder.class,order.getSupplierOrderId());
    // oso.setState(too.getOistatus());//
    // Short.parseShort(OrderStatusEnum.Deliveried.toString())
    // sellerOrderService.update(oso);
    // }
    // else
    // {
    // return "退款流程操作失败！";
    // }
    // }
    // }
    // }
    // }
    // MsgResult mr =
    // sellerOrderService.sendGoods(supplyOrderid,weiid,dcomanpyNo,deliveryCompany,deliveryOrderNo);
    // if(mr.getState() == 1)
    // {
    // return "1";
    // }
    // else
    // {
    // return mr.getMsg();
    // }
    // }

    public MsgResult editRefundState(long supplyweiid, long refundid, boolean isAgree, String content) {
	MsgResult result = new MsgResult();
	TOrderBackTotal refund = sellerOrderBase.getEntity(TOrderBackTotal.class, refundid);
	if (refund != null) {
		//如果是全返区的东西则要扣除返劵
		if(isAgree)
		{
			String supplyerOrderid=refund.getSupplierOrderId();
			//如果是活动全返订单，要扣除未到账数量
			UTradingDetails td=sellerOrderBase.getUniqueResultByHql(" from UTradingDetails u where u.weiId=? and  u.supplyOrder=? and u.type=14 and u.state=0", refund.getBuyerid(),supplyerOrderid);
			if(td!=null && td.getAmount()>0) //扣除返券
			{
				sellerOrderBase.executeHql(" update UWallet u set u.unAbleTicket=u.unAbleTicket-?  where u.weiId=? ",td.getAmount(),refund.getBuyerid());
			}
		}
	    logger.error("流程请求包体：flowid：" + refund.getFlowId() + "供应商id：" + supplyweiid);
	    ReturnModel reModel = FlowMethod.QueryTask(refund.getFlowId().toString(), supplyweiid);
	    if (reModel != null) {

		String strJson = reModel.getBasemodle().toString();
		logger.error("流程返回的包：" + strJson);
		JSONObject jo = JSONObject.fromObject(strJson);
		if (isAgree) {
		    if (refund.getType() == 1) {
			// 退款
			try {
			    confirmRefundService.refundPenny(supplyweiid, refund);
			    if ("".equals(content))
				content = "卖家同意退款";

			    if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "agree", content)) {
				result.setState(1);
			    } else {
				result.setState(-1);
				result.setMsg("操作不成功303");
				return result;
			    }

			} catch (Exception e) {
			    result.setState(-1);
			    result.setMsg(e.getMessage());
			}

		    } else {
			if ("".equals(content))
			    content = "卖家同意退款";
			if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "agree", content)) {
			    result.setState(1);
			} else {
			    result.setState(-1);
			    result.setMsg("操作不成功304");
			    return result;
			}
		    }

		} else {
		    if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "disagree", "卖家不同意退款")) {
			if ("".equals(content)) {
			    refund.setSReason(content);
			    orderManageService.updateOrderBackTotal(refund);
			}
			result.setState(1);
		    } else {
			result.setState(-1);
			result.setMsg("操作不成功305");
			return result;
		    }
		}
	    }
	}
	return result;
    }
}
