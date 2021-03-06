package com.okwei.myportal.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.vo.LoginUser;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.enums.BillingTypeEnum;
import com.okwei.myportal.bean.enums.CourierTypeEnum;
import com.okwei.myportal.bean.enums.PostageHandleEnum; 
import com.okwei.myportal.bean.vo.FreightDetailsVO;
import com.okwei.myportal.bean.vo.FreightVO; 
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.service.IFreightService;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/freight")
public class FreightController extends SSOController
{
    @Autowired
    IFreightService freightService;

    /**
     * （分页）查询分类列表
     * 
     * @param pageId
     *            当前页数
     * @param pageSize
     *            每页显示的行数
     * @param model
     * @return
     */
    @RequestMapping(value = "/freightList")
    public String freightList(@RequestParam(required = false,defaultValue = "1") int pageId,@RequestParam(required = false,defaultValue = "10") int pageSize,Model model)
    {
        LoginUser user = getUserOrSub();
        model.addAttribute("userinfo",user);
        long weiid = user.getWeiID();
        model.addAttribute("pageId",pageId);
        PageResult<FreightVO> fvopr = freightService.getFreightList(weiid,pageId,pageSize);
        model.addAttribute("fvopr",fvopr);
        return "freight/freightList";
    }

    @RequestMapping(value = "/addorupd")
    public String addorupd(@RequestParam(required = false,defaultValue = "0") int fid,Model model)
    {
        LoginUser user = getUserOrSub();
        model.addAttribute("userinfo",user);
        long weiid = user.getWeiID();
        // 判断是新增还是编辑
        int addorupd = 0;// 默认新增（0新增，1编辑）
        FreightVO fvo = new FreightVO();
        fvo.setProvince(0);
        fvo.setCity(0);
        fvo.setFreightId(fid);
        fvo.setDistrict(0);
        fvo.setDeliverytime(Short.parseShort("8"));// 默认八小时
        fvo.setBillingType(Short.parseShort(BillingTypeEnum.Number.toString()));// 运费计算方式
        boolean express = false;// 快递
        FreightDetailsVO kdmr = new FreightDetailsVO();
        FreightDetailsVO wlmr = new FreightDetailsVO();
        boolean logistics = false;// 物流
        if(fid > 0)
        {// 编辑
            FreightVO tempfvo = freightService.getFreightVO(weiid,fid);
            if(tempfvo != null)
            {
                addorupd = 1;
                fvo = tempfvo;
                for(FreightDetailsVO fdvo : fvo.getDetailsList())
                {
                    if(fdvo.getStatus() == 0)
                    {
                        if(fdvo.getCourierType().toString().equals(CourierTypeEnum.Express.toString()))
                        {
                            kdmr = fdvo;
                            express = true;
                        }
                        if(fdvo.getCourierType().toString().equals(CourierTypeEnum.Logistics.toString()))
                        {
                            wlmr = fdvo;
                            logistics = true;
                        }
                    }
                }
            }
            else
            {
                fvo.setFreightId(0);
            }
        }
        model.addAttribute("express",express);
        model.addAttribute("logistics",logistics);
        model.addAttribute("addorupd",addorupd);
        model.addAttribute("fvo",fvo);
        model.addAttribute("kdmr",kdmr);
        model.addAttribute("wlmr",wlmr);
        return "freight/addorupd";
    }

    /********************************************** 异步 *********************************************/
    /**
     * 提交数据
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submitData",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String submitData(String data,@RequestParam(required = false,defaultValue = "0") int fid)
    {
        MsgResult mr = new MsgResult();
        LoginUser user = getUserOrSub();
        long weiid = user.getWeiID();
        boolean isadd = true;
        FreightVO fvo = (FreightVO) JsonUtil.json2Object(data,FreightVO.class);
        if(fvo == null || fvo.getDetailsList() == null || fvo.getDetailsList().size() <= 0)
        {
            mr.setMsg("您输入的格式有误");
            mr.setState(-1);
            return JsonUtil.objectToJson(mr);
        }
        boolean haskd = false;
        for(FreightDetailsVO fdvo : fvo.getDetailsList())
        {
            if(CourierTypeEnum.Express.toString().toString().equals(fdvo.getCourierType().toString()))
            {
                haskd = true;
                break;
            }
        }
        if(!haskd)
        {
            mr.setMsg("快递是必选项，请填写快递");
            mr.setState(-1);
            return JsonUtil.objectToJson(mr);
        }
        if(fid > 0)
        {
            FreightVO tempfvo = freightService.getFreightVO(weiid,fid);
            if(tempfvo != null)
            {
                // 编辑
                isadd = false;
                fvo.setFreightId(fid);
                fvo.setUpDateTime(new Date());
                fvo.setCreateTime(tempfvo.getCreateTime());
                fvo.setSupplierWeiId(weiid);
                fvo.setFree(Double.parseDouble(PostageHandleEnum.BuBaoYou.toString()));
                fvo.setRemark(tempfvo.getRemark());
                fvo.setStatus(Short.parseShort("1"));
                if(freightService.updateFreightVO(fvo))
                {
                    mr.setMsg("修改成功");
                    mr.setState(1);
                }
                else
                {
                    mr.setMsg("修改失败");
                    mr.setState(-2);
                }
            }
        }
        if(isadd)
        {
            // 新增
            fvo.setCreateTime(new Date());
            fvo.setFree(Double.parseDouble(PostageHandleEnum.BuBaoYou.toString()));
            fvo.setStatus(Short.parseShort("1"));
            fvo.setSupplierWeiId(weiid);
            fvo.setUpDateTime(new Date());
            if(freightService.insertFreightVO(fvo))
            {
                mr.setMsg("添加成功");
                mr.setState(1);
            }
            else
            {
                mr.setMsg("添加失败");
                mr.setState(-2);
            }
        }
        return JsonUtil.objectToJson(mr);
    }

    /**
     * 获取运费模板　
     */
    @ResponseBody
    @RequestMapping(value = "/getFreight",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String getFreight(@RequestParam(required = false,defaultValue = "0") int freightId)
    {
        MsgResult mr = new MsgResult();
        if(freightId == 0)
        {
            mr.setState(-1);
            mr.setMsg("获取的运费不能为空");
        }
        else
        {
            LoginUser user = getUserOrSub();
            long weiid = user.getWeiID();
            FreightVO fdvo = freightService.getFreightVO(weiid,freightId);
            mr.setState(1);
            mr.setMsg(JsonUtil.objectToJson(fdvo));
        }
        return JsonUtil.objectToJson(mr);
    }

    /**
     * 添加运费模板
     */
    @ResponseBody
    @RequestMapping(value = "/addFreight",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String addFreight(String freightJson)
    {
        MsgResult mr = new MsgResult();
        FreightVO fvo = (FreightVO) JsonUtil.json2Object(freightJson,FreightVO.class);
        if(fvo != null)
        {
            LoginUser user = getUserOrSub();
            long weiid = user.getWeiID();
            fvo.setSupplierWeiId(weiid);
            if(freightService.insertFreightVO(fvo))
            {
                mr.setState(1);
                mr.setMsg("成功");
            }
            else
            {
                mr.setState(-2);
                mr.setMsg("添加失败");
            }

        }
        else
        {
            mr.setMsg("提交的数据格式不正确");
            mr.setState(-1);
        }
        return JsonUtil.objectToJson(mr);
    }

    /**
     * 修改运费模板
     */
    @ResponseBody
    @RequestMapping(value = "/updFreight",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String updFreight(String freightJson)
    {
        MsgResult mr = new MsgResult();
        FreightVO fvo = (FreightVO) JsonUtil.json2Object(freightJson,FreightVO.class);
        if(fvo != null)
        {
            LoginUser user = getUserOrSub();
            long weiid = user.getWeiID();
            fvo.setSupplierWeiId(weiid);
            if(freightService.insertFreightVO(fvo))
            {
                mr.setState(1);
                mr.setMsg("修改成功");
            }
            else
            {
                mr.setState(-2);
                mr.setMsg("修改失败");
            }

        }
        else
        {
            mr.setMsg("提交的数据格式不正确");
            mr.setState(-1);
        }
        return JsonUtil.objectToJson(mr);
    }

    /**
     * 删除运费模板
     */
    @ResponseBody
    @RequestMapping(value = "/delFreight",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String delFreight(@RequestParam(required = false,defaultValue = "0") int fid)
    {
        MsgResult mr = new MsgResult();
        if(fid <= 0)
        {
            mr.setMsg("运费模板不存在");
            mr.setState(-1);
            return JsonUtil.objectToJson(mr);
        }
        LoginUser user = getUserOrSub();
        long weiid = user.getWeiID();
        int count = freightService.getCountByFid(weiid,fid);
        if(count > 0)
        {
            mr.setMsg("该运费模版正被产品使用，无法删除！");
            mr.setState(-3);
            return JsonUtil.objectToJson(mr);
        }
        FreightVO fvo = new FreightVO();
        fvo.setSupplierWeiId(weiid);
        fvo.setFreightId(fid);
        if(freightService.deleteFreightVO(fvo))
        {
            mr.setMsg("删除成功");
            mr.setState(1);
        }
        else
        {
            mr.setMsg("删除失败");
            mr.setState(-2);
        }
        return JsonUtil.objectToJson(mr);
    }

}
