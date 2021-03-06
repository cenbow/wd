package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.FreightDetailsVO;
import com.okwei.myportal.bean.vo.FreightVO;
import com.okwei.myportal.dao.IFreightDAO;
import com.okwei.myportal.service.IFreightService;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ObjectUtil;

@Service 
public class FreightService extends BaseService implements IFreightService
{
    @Autowired
    IFreightDAO freightDAO;

    @Autowired
    IRegionService regionService;

    @Override
    public PageResult<FreightVO> getFreightList(long weiid,int pageId,int pageSize)
    {
        if(weiid == 0)
        {
            return null;
        }
        Limit limit = Limit.buildLimit(pageId,pageSize);// 拼接条件
        PageResult<PPostAgeModel> ppam = freightDAO.getPPostAgeModelList(weiid,limit); // 获取分页数据
        if(ppam == null)
        {
            return null;
        }
        List<PPostAgeModel> ppamList = ppam.getList();
        // 判断获取的结是否为空
        if(ppamList == null || ppamList.size() <= 0)
        {
            return null;
        }
        PageResult<FreightVO> result = new PageResult<FreightVO>();
        result.setPageCount(ppam.getPageCount());
        result.setPageIndex(ppam.getPageIndex());
        result.setPageSize(ppam.getPageSize());
        result.setTotalCount(ppam.getTotalCount());
        List<FreightVO> fList = new ArrayList<FreightVO>();
        for(PPostAgeModel p : ppamList)
        {
            FreightVO f = new FreightVO();
            f.setBillingType(p.getBillingType());
            f.setCreateTime(p.getCreateTime());
            f.setDeliveryAddress(p.getDeliveryAddress());
            f.setDeliverytime(p.getDeliverytime());
            f.setDistrict(p.getDistrict());
            f.setFree(p.getFree());
            f.setFreightId(p.getFreightId());
            f.setPostAgeName(p.getPostAgeName());
            f.setProvince(p.getProvince());
            f.setRemark(p.getRemark());
            f.setStatus(p.getStatus());
            f.setSupplierWeiId(p.getSupplierWeiId());
            f.setUpDateTime(p.getUpDateTime());
            // 获取旗下的运费详情
            List<PPostAgeDetails> ppadList = freightDAO.getpPostAgeDetails(weiid,p.getFreightId());
            List<FreightDetailsVO> fdList = new ArrayList<FreightDetailsVO>();
            if(ppadList != null && ppadList.size() > 0)
            {
                for(PPostAgeDetails ad : ppadList)
                {
                    FreightDetailsVO fd = new FreightDetailsVO();
                    fd.setAreaName(getAreaName(ad.getDestination()));
                    fd.setCourierType(ad.getCourierType());
                    fd.setDestination(ad.getDestination());
                    fd.setFirstCount(ad.getFirstCount());
                    fd.setFirstpiece(ad.getFirstpiece());
                    fd.setFreightId(ad.getFreightId());
                    fd.setMoreCount(ad.getMoreCount());
                    fd.setMorepiece(ad.getMorepiece());
                    fd.setPostDetailsId(ad.getPostDetailsId());
                    fd.setStatus(ad.getStatus());
                    fdList.add(fd);
                }
            }
            f.setDetailsList(fdList);
            fList.add(f);
        }
        result.setList(fList);
        return result;
    }

    @Override
    public FreightVO getFreightVO(long weiid,int fid)
    {
        PPostAgeModel p = freightDAO.getPostAgeModel(weiid,fid);
        if(p == null)
        {
            return null;
        }
        FreightVO f = new FreightVO();
        f.setBillingType(p.getBillingType());
        f.setCreateTime(p.getCreateTime());
        f.setDeliveryAddress(p.getDeliveryAddress());
        f.setDeliverytime(p.getDeliverytime());
        f.setDistrict(p.getDistrict());
        f.setFree(p.getFree());
        f.setCity(p.getCity());
        f.setFreightId(p.getFreightId());
        f.setPostAgeName(p.getPostAgeName());
        f.setProvince(p.getProvince());
        f.setRemark(p.getRemark());
        f.setStatus(p.getStatus());
        f.setSupplierWeiId(p.getSupplierWeiId());
        f.setUpDateTime(p.getUpDateTime());
        List<PPostAgeDetails> ppadList = freightDAO.getpPostAgeDetails(weiid,p.getFreightId());
        List<FreightDetailsVO> fdList = new ArrayList<FreightDetailsVO>();
        if(ppadList != null && ppadList.size() > 0)
        {
            for(PPostAgeDetails ad : ppadList)
            {
                FreightDetailsVO fd = new FreightDetailsVO();
                fd.setAreaName(getAreaName(ad.getDestination()));
                fd.setCourierType(ad.getCourierType());
                fd.setDestination(ad.getDestination());
                fd.setFirstCount(ad.getFirstCount());
                fd.setFirstpiece(ad.getFirstpiece());
                fd.setFreightId(ad.getFreightId());
                fd.setMoreCount(ad.getMoreCount());
                fd.setMorepiece(ad.getMorepiece());
                fd.setPostDetailsId(ad.getPostDetailsId());
                fd.setStatus(ad.getStatus());
                fdList.add(fd);
            }
        }
        f.setDetailsList(fdList);
        return f;
    }

    public String getAreaName(String area)
    {
        if(ObjectUtil.isEmpty(area))
        {
            return "全国";
        }
        String[] strs = area.split("\\|");
        // regionService
        String name = new String();
        for(String s : strs)
        {
            if(!ObjectUtil.isEmpty(s))
            {
                if(s.equals("|"))
                {
                    continue;
                }
                String an = regionService.getNameByCode(Integer.parseInt(s));
                if(!ObjectUtil.isEmpty(an))
                {
                    name += an + ",";
                }
            }
        }
        if(!ObjectUtil.isEmpty(name))
        {
            name = name.substring(0,name.length() - 1);
        }
        return name;
    }

    @Override
    public boolean insertFreightVO(FreightVO fvo)
    {
        if(fvo == null || fvo.getDetailsList() == null || fvo.getDetailsList().size() <= 0)
        {
            return false;
        }
        PPostAgeModel ppam = new PPostAgeModel();
        ppam.setBillingType(fvo.getBillingType());
        ppam.setCity(fvo.getCity());
        ppam.setCreateTime(new Date());
        ppam.setDeliveryAddress(fvo.getDeliveryAddress());
        ppam.setDeliverytime(fvo.getDeliverytime());
        ppam.setDistrict(fvo.getDistrict());
        ppam.setFree(fvo.getFree());
        ppam.setPostAgeName(fvo.getPostAgeName());
        ppam.setProvince(fvo.getProvince());
        ppam.setRemark(fvo.getRemark());
        ppam.setStatus(fvo.getStatus());
        ppam.setSupplierWeiId(fvo.getSupplierWeiId());
        ppam.setUpDateTime(fvo.getUpDateTime());
        int fid = freightDAO.insertPPostAgeModel(ppam);
        List<FreightDetailsVO> fdlist = fvo.getDetailsList();
        for(FreightDetailsVO fd : fdlist)
        {
            PPostAgeDetails pd = new PPostAgeDetails();
            pd.setCourierType(fd.getCourierType());
            pd.setFreightId(fid);
            pd.setDestination(fd.getDestination());
            pd.setFirstCount(fd.getFirstCount());
            pd.setFirstpiece(fd.getFirstpiece());
            pd.setMoreCount(fd.getMoreCount());
            pd.setMorepiece(fd.getMorepiece());
            pd.setStatus(fd.getStatus()); 
            freightDAO.insertPPostAgeDetails(pd);
        }
        return true;
    }

    @Override
    public boolean updateFreightVO(FreightVO fvo)
    {
        if(fvo == null || fvo.getDetailsList() == null || fvo.getDetailsList().size() <= 0)
        {
            return false;
        }
        PPostAgeModel ppam = freightDAO.getPostAgeModel(fvo.getSupplierWeiId(),fvo.getFreightId());  
        ppam.setBillingType(fvo.getBillingType());
        ppam.setCity(fvo.getCity()); 
        ppam.setDeliveryAddress(fvo.getDeliveryAddress());
        ppam.setDeliverytime(fvo.getDeliverytime());
        ppam.setDistrict(fvo.getDistrict());
        ppam.setFree(fvo.getFree());
        ppam.setPostAgeName(fvo.getPostAgeName());
        ppam.setProvince(fvo.getProvince());
        ppam.setRemark(fvo.getRemark()); 
        ppam.setUpDateTime(fvo.getUpDateTime());
        //freightDAO.updatePPostAgeModel(ppam);
        List<PPostAgeDetails> ppadlist = freightDAO.getpPostAgeDetails(ppam.getSupplierWeiId(),ppam.getFreightId());
        for(PPostAgeDetails p : ppadlist)
        {
            freightDAO.deletePPostAgeDetails(p);
        }
        List<FreightDetailsVO> fdlist = fvo.getDetailsList();
        for(FreightDetailsVO fd : fdlist)
        {
            PPostAgeDetails pd = new PPostAgeDetails();
            pd.setCourierType(fd.getCourierType());
            pd.setFreightId(ppam.getFreightId());
            pd.setDestination(fd.getDestination());
            pd.setFirstCount(fd.getFirstCount());
            pd.setFirstpiece(fd.getFirstpiece());
            pd.setMoreCount(fd.getMoreCount());
            pd.setMorepiece(fd.getMorepiece());
            pd.setStatus(fd.getStatus()); 
            freightDAO.insertPPostAgeDetails(pd);
        }
        return true;
    }

    @Override
    public boolean deleteFreightVO(FreightVO fvo)
    {
        if(fvo == null || fvo.getFreightId() == null || fvo.getFreightId() <= 0)
        {
            return false;
        }
        List<PPostAgeDetails> ppadlist = freightDAO.getpPostAgeDetails(fvo.getSupplierWeiId(),fvo.getFreightId());
        for(PPostAgeDetails p : ppadlist)
        {
            freightDAO.deletePPostAgeDetails(p);
        }
        freightDAO.deletePPostAgeModel(fvo.getSupplierWeiId(),fvo.getFreightId());
        return true;
    }

    @Override
    public int getCountByFid(long weiid,int fid)
    {
        int count = freightDAO.getProductCountByFid(weiid,fid);
        return count;
    }

}
