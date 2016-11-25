package com.okwei.myportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.dao.IFreightDAO;

@Repository
public class FreightDAO extends BaseDAO implements IFreightDAO
{

    @Override
    public PageResult<PPostAgeModel> getPPostAgeModelList(long weiid,Limit limit)
    {
        String hql = "from PPostAgeModel p where p.supplierWeiId=? and p.status!=3";
        Object[] b = new Object[1];
        b[0] = weiid;
        PageResult<PPostAgeModel> ppam = findPageResult(hql,limit,b);
        return ppam;
    }

    @Override
    public PPostAgeModel getPostAgeModel(long weiid,int fid)
    {
        String hql = "from PPostAgeModel p where p.supplierWeiId=? and p.freightId=?  and p.status!=3";
        Object[] b = new Object[2];
        b[0] = weiid;
        b[1] = fid;
        PPostAgeModel ppad = get(PPostAgeModel.class,fid);
        if(ppad != null && ppad.getSupplierWeiId() == weiid)
        {
            return ppad;
        }
        return null;
    }

    @Override
    public List<PPostAgeDetails> getpPostAgeDetails(long weiid,int fid)
    {
        PPostAgeModel ppam = getPostAgeModel(weiid,fid);
        if(ppam == null)
        {
            return null;
        }
        String hql = "from PPostAgeDetails p where p.freightId=?";
        Object[] b = new Object[1];
        b[0] = fid;
        List<PPostAgeDetails> ppadList = find(hql,b);
        return ppadList;
    }

    @Override
    public boolean deletePPostAgeModel(long weiid,int fid)
    {
        String hql = "from PPostAgeModel p where p.supplierWeiId=? and p.freightId=?  and p.status!=3";
        Object[] b = new Object[2];
        b[0] = weiid;
        b[1] = fid;
        PPostAgeModel ppad = get(PPostAgeModel.class,fid);
        if(ppad != null && ppad.getSupplierWeiId() == weiid)
        {
            delete(ppad);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePPostAgeDetails(PPostAgeDetails ppad)
    {
        if(ppad != null && ppad.getPostDetailsId() != null && ppad.getPostDetailsId() > 0)
        {
            delete(ppad);
            return true;
        }
        return false;
    }

    @Override
    public int insertPPostAgeModel(PPostAgeModel ppam)
    {
        if(ppam != null)
        {
            save(ppam);
            return ppam.getFreightId();
        }
        return 0;
    }

    @Override
    public boolean insertPPostAgeDetails(PPostAgeDetails ppad)
    {
        if(ppad != null)
        {
            save(ppad);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePPostAgeModel(PPostAgeModel ppam)
    {
        if(ppam != null && ppam.getFreightId() != null && ppam.getFreightId() > 0)
        {
            update(ppam);
            return true;
        }
        return false;
    }

    @Override
    public int getProductCountByFid(long weiid,int fid)
    {
        String hql = "select count(1) from PProducts p where p.supplierWeiId=? and p.freightId=? and p.state!=?"; 
        Long count = super.count(hql,weiid,fid,Short.parseShort(ProductStatusEnum.Deleted.toString()));
        return count.intValue();
    }

}
