package com.okwei.supplyportal.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.enums.TailPayTypeEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.supplyportal.bean.dto.QueryParam;
import com.okwei.supplyportal.bean.vo.AddressVO;
import com.okwei.supplyportal.bean.vo.BaseSSOController;
import com.okwei.supplyportal.bean.vo.LoginUser;
import com.okwei.supplyportal.bean.vo.MsgResult;
import com.okwei.supplyportal.bean.vo.ParamOrderSearch;
import com.okwei.supplyportal.bean.vo.RefundVO;
import com.okwei.supplyportal.bean.vo.ReservedConfirmVO;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderParam;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderStateCountVO;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderCountSumVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderDetailsVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderStateCountVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderVO;
import com.okwei.supplyportal.service.IOrderManageService;
import com.okwei.supplyportal.service.IUserInfoService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;
import com.okwei.util.ObjectUtil;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseSSOController
{
    private final static Log logger = LogFactory.getLog(OrderController.class);
    @Autowired
    private IOrderManageService orderService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/test")
    public String list(@ModelAttribute("queryParam") QueryParam queryParam,@RequestParam(required = false,defaultValue = "1") int pageId,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long weiid = user.getWeiID();
        PageResult<SupplyOrderVO> result = initlist();
        SupplyOrderCountSumVO sumVO = getCountSumVO(weiid);
        model.addAttribute("pageResult",result);
        model.addAttribute("lingshowCount",sumVO.getLingshowCount());
        return "order/test";
    }

    /**
     * *零售，批发订单列表
     */
    @RequestMapping(value = "/buylist")
    public String buylist(@ModelAttribute("queryParam") ParamOrderSearch param,@RequestParam(required = false,defaultValue = "1") int pageId,
            @RequestParam(required = false,defaultValue = "1") int dt,@RequestParam(required = false,defaultValue = "-1") short ds,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long supplyWeiid = user.getWeiID();// super.getLoginUser().getWeiID();
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
        param.setOrderType(dt);
        param.setState(ds);
        PageResult<SupplyOrderVO> result = orderService.getOrderListPageResult(param,supplyWeiid,Limit.buildLimit(pageId,10));
        SupplyOrderCountSumVO sorderTypeCount = orderService.getSupplyOrderCountSumVO(supplyWeiid);
        SupplyOrderStateCountVO sorderStateCount = orderService.getSupplyOrderStateCountVO(supplyWeiid,dt);
        model.addAttribute("pageRes",result);
        model.addAttribute("typeCount",sorderTypeCount);
        model.addAttribute("stateCount",sorderStateCount);
        return "order/buylist";
    }

    /**
     * 预订单列表
     */
    @RequestMapping(value = "/reservelist")
    public String reservelist(@ModelAttribute("queryParam") ParamOrderSearch param,@RequestParam(required = false,defaultValue = "1") int pageId,
            @RequestParam(required = false,defaultValue = "1") int dt,@RequestParam(required = false,defaultValue = "-1") short ds,Model model)
    {

        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long supplyWeiid = user.getWeiID();
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
        param.setOrderType(dt);
        param.setState(ds);
        PageResult<SupplyBookOrderVO> result = orderService.getBookOrderListPageResult(param,supplyWeiid,Limit.buildLimit(pageId,10));
        SupplyOrderCountSumVO sorderTypeCount = orderService.getSupplyOrderCountSumVO(supplyWeiid);
        SupplyBookOrderStateCountVO sorderStateCount = orderService.getSupplyBookOrderStateCountVO(supplyWeiid);
        model.addAttribute("pageRes",result);
        model.addAttribute("typeCount",sorderTypeCount);
        model.addAttribute("stateCount",sorderStateCount);
        return "order/reservelist";
    }

    /**
     * 预订单处理
     */
    @RequestMapping(value = "/reservedispose",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String reservedispose(String orderNo,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long supplyWeiid = user.getWeiID();
        if(orderNo == null || orderNo == "0")
        {
            return "redirect:/order/buylist";// 参数为空，跳转到列表页
        }
        SupplyOrderDetailsVO details = orderService.getOrderDetails(orderNo);
        if(supplyWeiid != details.getSupplyerWeiid())
        {
            return "redirect:/order/reservelist";// 参数为空，跳转到列表页
        }
        model.addAttribute("details",details);
        return "order/reservedispose";
    }

    /**
     * *预订单确认
     */
    @RequestMapping(value = "/reservedConfirm",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String reservedConfirm(@ModelAttribute("queryParam") ReservedConfirmVO param,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long weiId = user.getWeiID();
        SupplyBookOrderParam sbop = new SupplyBookOrderParam();
        sbop.setSupplyOrderid(param.getSupplyOrderid());// 供应商订单id
        sbop.setWeiid(weiId);// 当前登录者weiid(供应商weiid)
        sbop.setSured(true);// 确定、拒绝 预订单
        sbop.setEditPriceType(param.getEditPriceType());// 0不修改，1修改产品单价，2修改总价
        sbop.setEditPostPrice(param.getEditPostPrice());// 修改产品单价时，需填写运费
        sbop.setEditTotalPrice(param.getEditTotalPrice());// 修改总价时，填写
        sbop.setPayType(param.getPayType());// 0全额付款，1预付百分比，2预付指定金额

        sbop.setFirstPercent(param.getFirstPercent());// 预付百分比（如：20）
        sbop.setFirstPrice(param.getFirstPrice());// 预付金额

        sbop.setMessage(param.getMessage());// 给采购商的问候
        Date date = DateUtils.format(param.getPreDeliverTime(),"yyyy-MM-dd");
        sbop.setPreDeliverTime(date);// 预计发货时间
        List<OProductOrder> prolist = new ArrayList<OProductOrder>();
        for(OProductOrder pro : param.getProducts())
        {
            prolist.add(pro);
        }
        sbop.setProducts(prolist);
        if(param.getTailPayType() == 0)
        {// / 发货前
            sbop.setTailPayType(TailPayTypeEnum.predelivery);
        }
        else
        {// / 发货后
            sbop.setTailPayType(TailPayTypeEnum.afterdelivery);
        }
        MsgResult mr = orderService.insertBookOrder(sbop);
        if(mr.getState() == 1)
        {
            model.addAttribute("state","1");
        }
        else
        {
            model.addAttribute("state","0");
        }
        return "order/reservedConfirm";
    }

    /**
     * 订单详情
     * 
     * @param orderNo
     *            订单号
     * @param model
     *            返回实体
     * @return
     */
    @RequestMapping(value = "/buydetails",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String buydetails(String orderNo,Model model)
    {
        if(orderNo == null || orderNo == "0")
        {
            return "redirect:/order/buylist";// 参数为空，跳转到列表页
        }
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long weiId = user.getWeiID();
        SupplyOrderDetailsVO details = orderService.getOrderDetails(orderNo);
        if(details != null && weiId != details.getSupplyerWeiid())
        {
            return "redirect:/order/buylist";// 不是这个供应商的订单，跳转到列表页
        }
        model.addAttribute("details",details);
        return "order/buydetails";
    }

    /**
     * 退款详情
     */
    @RequestMapping(value = "/refund",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String refund(@RequestParam(required = false,defaultValue = "0") long refundId,Model model)
    {
        if(refundId == 0)
        {
            return "order/buylist";
        }
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long weiid = user.getWeiID();
        RefundVO ref = orderService.getRefundVO(refundId,weiid);
        model.addAttribute("details",ref);
        List<AddressVO> list = userInfoService.getAddressList(weiid);
        // 收货地址列表
        model.addAttribute("list",list);
        return "order/refund";
    }

    /**
     * 修改价格
     */
    @RequestMapping(value = "/modifyprice",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String modifyprice(String orderNo,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        SupplyOrderDetailsVO details = orderService.getOrderDetails(orderNo);
        model.addAttribute("details",details);
        return "order/modifyprice";
    }

    /**
     * 发货
     */
    @RequestMapping(value = "/delivery",method =
    {RequestMethod.GET})
    public String delivery(String orderNo,Model model)
    {
        LoginUser user = super.getLoginUser();
        model.addAttribute("userinfo",user);
        long weiId = user.getWeiID();
        SupplyOrderDetailsVO details = orderService.getOrderDetails(orderNo);
        boolean showorhidden = false;
        if(details.getLogistics() != null && details.getLogistics().getLogisticsNo() != null && !ObjectUtil.isEmpty(details.getLogistics().getLogisticsNo()))
        {
            showorhidden = true;
        }
        List<Map<String,String>> list = AppSettingUtil.getMaplist("transports");
        model.addAttribute("wuliu",list);// 快递列表
        model.addAttribute("showorhidden",showorhidden);
        model.addAttribute("details",details);
        return "order/delivery";
    }

    private PageResult<SupplyOrderVO> initlist()
    {
        ParamOrderSearch param = new ParamOrderSearch();
        param.setOrderType(1);
        long supplyWeiid = 1;
        try
        {
            PageResult<SupplyOrderVO> pageResult = orderService.getOrderListPageResult(param,supplyWeiid,Limit.buildLimit(1,10));
            return pageResult;
        }
        catch(Exception e)
        {
            // TODO: handle exception
        }
        return null;
    }

    private SupplyOrderCountSumVO getCountSumVO(Long weiid)
    {
        String msg = "";
        try
        {
            return orderService.getSupplyOrderCountSumVO(weiid);
        }
        catch(Exception e)
        {
            // TODO: handle exception
            msg = e.getMessage();
        }
        return null;
    }

}
