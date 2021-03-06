package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.enums.ShopClassStatusEnum;
import com.okwei.myportal.bean.vo.ShopClassNewVO;
import com.okwei.myportal.bean.vo.ShopClassVO;
import com.okwei.myportal.dao.IShopClassDAO;
import com.okwei.myportal.service.IShopClassService;
import com.okwei.util.ObjectUtil;

@Service
public class ShopClassService implements IShopClassService
{
    @Autowired
    IShopClassDAO shopClassDAO;

    @Override
    public int getCountByClassId(long weiid,int cid)
    {
        return shopClassDAO.getCountByClassId(weiid,cid);
    }

    @Override
    public int getClassCount(long weiid)
    {
        return shopClassDAO.getClassCount(weiid);
    }

    @Override
    public boolean updateClassByName(String className,long weiid,int cid)
    {
        PShopClass sc = shopClassDAO.getPShopClass(weiid,cid);
        if(ObjectUtil.isEmpty(className))
        {
            return false;
        }
        if(sc == null)
        {
            return false;
        }
        sc.setSname(className);
        shopClassDAO.updatePShopClass(sc);
        return true;
    }

    @Override
    public boolean deleteClassByCid(long weiid,int cid)
    		  throws Exception{
        PShopClass sc = shopClassDAO.getPShopClass(weiid,cid);
        if(sc == null || sc.getWeiid() != weiid)
        {
            return false;
        }
        Long[] childrenSc = null;
        if (Short.valueOf("1").equals(sc.getLevel())) {
			List<PShopClass> pcList = shopClassDAO.getShopClassListById(cid);
			if (pcList != null && pcList.size() > 0) {
				childrenSc = new Long[pcList.size()];
				int i = 0;
				for (PShopClass pc : pcList) {
					childrenSc[i] = Long.valueOf(pc.getSid()+"");
					i++;
				}
			}
		}
        List<PClassProducts> list = shopClassDAO.getPClassProductsList(cid,childrenSc);// 分类上架list
        if(list != null && list.size() > 0)
        {
            for(PClassProducts pc : list)
            {
                shopClassDAO.deletePClassProducts(pc);
            }
        }
        List<PProducts> pplist = shopClassDAO.getPProducts(weiid,cid);// 产品list
        if(pplist != null && pplist.size() > 0)
        {
            List<Long> ids = new ArrayList<Long>();
            for(PProducts pp : pplist)
            {
                ids.add(pp.getProductId());
            }
            List<PClassProducts> list2 = shopClassDAO.getClassProductsByProIds(ids);
            if(list2 != null && list2.size() > 0)
            {
                for(PClassProducts pc : list2)
                {
                    shopClassDAO.deletePClassProducts(pc);
                }
            }
            for(PProducts pp : pplist)
            {
                pp.setState(Short.parseShort(ProductStatusEnum.Deleted.toString()));
                shopClassDAO.updateProducts(pp);
            }
        }
        return shopClassDAO.deleteClassByCid(weiid,cid);
    }

    @Override
    public boolean placedTop(long weiid,int cid)
    {
        short min = shopClassDAO.getMinClassSort(weiid);
        PShopClass sc = shopClassDAO.getPShopClass(weiid,cid);
        if(sc == null)
        {
            return false;
        }
        // if(top == sc.getSort())
        // {
        // return false;//已经在最前面
        // }
        min--;
        sc.setSort(min);
        shopClassDAO.updatePShopClass(sc);
        return true;
    }

    @Override
    public PageResult<ShopClassNewVO> getShopClassListByCid(long weiid,int pageId,int pageSize)
    {
        Limit limit = Limit.buildLimit(pageId,pageSize);
        PageResult<PShopClass> scpr = shopClassDAO.getShopClassListByCid(weiid,limit);
        if(scpr == null)
        {
            return null;
        }
        List<ShopClassNewVO> voList = new ArrayList<ShopClassNewVO>();
        List<PShopClass> sclist = scpr.getList();
        for(PShopClass pc : sclist)
        {
            ShopClassNewVO vo = new ShopClassNewVO();
            vo.setCount(shopClassDAO.getCountByClassId(weiid,pc.getSid()));
            vo.setSid(pc.getSid());
            vo.setSname(pc.getSname());
            vo.setSort(pc.getSort());
            vo.setState(pc.getState());
            vo.setType(pc.getType());
            vo.setWeiid(weiid);
            vo.setLevel(pc.getLevel());
            voList.add(vo);
        }
        PageResult<ShopClassNewVO> newscvo = new PageResult<ShopClassNewVO>();
        newscvo.setList(voList);
        newscvo.setPageCount(scpr.getPageCount());
        newscvo.setPageIndex(scpr.getPageIndex());
        newscvo.setPageSize(scpr.getPageSize());
        newscvo.setTotalCount(scpr.getTotalCount());
        return newscvo;
    }

    @Override
    public boolean insertPShopClass(long weiid,String className,short type)
    {
        if(weiid <= 0 || ObjectUtil.isEmpty(className))
        {
            return false;
        }
        Integer count = shopClassDAO.getClassCount(weiid);
        if(count > 100)
        {
            return false;
        }
        short max = shopClassDAO.getMaxClassSort(weiid) ;
        max++;
        PShopClass sc = new PShopClass();
        sc.setCreateTime(new Date());
        sc.setSname(className);
        sc.setSort(max);
        sc.setWeiid(weiid);
        sc.setType(type);
        sc.setState(Short.parseShort(ShopClassStatusEnum.Enable.toString()));
        sc.setLevel((short)1);
        return shopClassDAO.insertPShopClass(sc);
    }

    @Override
    public boolean judgeClassName(long weiid,String className)
    {
        PShopClass sc = shopClassDAO.getPShopClassByName(weiid,className);
        if(sc != null)
        {
            return true;
        }
        return false;
    }

}
