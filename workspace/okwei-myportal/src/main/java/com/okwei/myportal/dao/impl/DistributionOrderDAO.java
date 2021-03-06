package com.okwei.myportal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.ReFundStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.bean.dto.DistributionOrderDTO;
import com.okwei.myportal.bean.vo.ProductOrderVO;
import com.okwei.myportal.bean.vo.SaleOrderVO;
import com.okwei.myportal.dao.IDistributionOrderDAO;
import com.okwei.myportal.service.IDistributionOrderService;
import com.okwei.util.ObjectUtil;

@Repository
public class DistributionOrderDAO extends BaseDAO implements IDistributionOrderDAO
{

    @Override
    public PageResult<SaleOrderVO> getDistributionOrders(long weiID,DistributionOrderDTO dto,Limit limit)
    {
        if(weiID < 1)
        {
            return null;
        }
        String sql = "select  ";
        sql += "p.SupplierOrderID as orderNo, ";// 1 (供应商订单ID)
        sql += "p.SupplyeriID as supplierId, ";// 2 （供应商微店号）
        sql += "p.ProductImg as proImg, ";// 3 （商品图片）
        sql += "p.ProdcutTitle as proTitle,  ";// 4（商品标题）
        sql += "p.StyleDes as property, ";// 5（商品属性）
        sql += "p.Price as price, ";// 6（商品价格）
        sql += "p.Count as count, ";// 7（商品数量）
        sql += "p.Commision as commission, ";// 8（商品佣金）
        sql += "o.BalanceTime as orderTime, ";// 9（放款时间）
        sql += "u.CompanyName as supplierName, ";// 10（供应商名称）
        sql += "p.ProductID as proId, ";//11(产品ID)
        sql += "o.OrderType as orderType ";//12(产品类型)
        sql += " from O_ProductOrder p ";
        sql += " LEFT JOIN O_SupplyerOrder o on p.SupplierOrderID = o.SupplierOrderID ";
        sql += " LEFT JOIN U_Supplyer u on p.SupplyeriID=u.WeiID ";
        sql += " LEFT JOIN T_OrderBackTotal t on p.BackOrder=t.BackOrder ";
        sql += " where p.SellerWeiID=:sellerWeiid and o.State=:state and (t.BackStatus=:backStatus or t.BackStatus IS NULL) ";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("sellerWeiid",weiID);
        params.put("state",Short.parseShort(OrderStatusEnum.Completed.toString()));
        params.put("backStatus",Short.parseShort(ReFundStatusEnum.ShenQingClose.toString()));
        if(dto != null)
        {
            if(!ObjectUtil.isEmpty(dto.getCompanyName()))
            {
                sql += " and u.CompanyName like :companyName ";
                params.put("companyName","%" + dto.getCompanyName() + "%");
            }
            if(!ObjectUtil.isEmpty(dto.getProductTitle()))
            {
                sql += " and p.ProdcutTitle like :prodcutTitle ";
                params.put("prodcutTitle","%" + dto.getProductTitle() + "%");
            }
            if(!ObjectUtil.isEmpty(dto.getBeginDate()))
            {
                sql += " and o.BalanceTime>:begins ";
                params.put("begins",dto.getBeginDate());
            }
            if(!ObjectUtil.isEmpty(dto.getEndDate()))
            {
                sql += " and o.BalanceTime<:ends ";
                params.put("ends",dto.getEndDate());
            }
        }
        sql += " ORDER BY o.BalanceTime DESC";
        PageResult<SaleOrderVO> prSale = queryPageResultByMap(sql,SaleOrderVO.class,limit,params); 
        return prSale;
//        int pageIndex = (limit.getPageId() - 1) * limit.getSize();
//        params.put("indexs",pageIndex);
//        params.put("pageSize",limit.getSize());
//        List<Object[]> list = q
//        PageResult<Object[]> result = findPageResultTransByMap(sql,Object[].class,limit,params);
//
//        if(result == null || result.getList() == null || result.getList().size() <= 0)
//        {
//            return null;
//        }
//        List<SaleOrderVO> saleList = new ArrayList<SaleOrderVO>();
//        for(Object[] bs : result.getList())
//        {
//            SaleOrderVO sale = new SaleOrderVO();
//            sale.setOrderNo(bs[0].toString());// 1
//            sale.setSupplierId(Long.parseLong(bs[1].toString()));// 2
//            sale.setProImg(bs[2].toString());// 3
//            sale.setProTitle(bs[3].toString());// 4
//            sale.setProperty(bs[4].toString());// 5
//            sale.setPrice(Double.parseDouble(bs[5].toString()));// 6
//            sale.setCount(Integer.parseInt(bs[6].toString()));// 7
//            sale.setCommission(Double.parseDouble(bs[7].toString()));// 8
//            sale.setOrderTime(bs[8].toString());// 9
//            sale.setSupplierName(bs[9].toString());// 10
//            saleList.add(sale);
//        }
//        PageResult<SaleOrderVO> prSale = new PageResult<SaleOrderVO>();
//        prSale.setList(saleList);
//        prSale.setPageCount(result.getPageCount());
//        prSale.setPageIndex(result.getPageIndex());
//        prSale.setPageSize(result.getPageSize());
//        prSale.setTotalCount(result.getTotalCount());
    }

}
