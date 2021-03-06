package com.okwei.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.UTicketUseLog;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiDianCoinLog;
import com.okwei.bean.vo.MyWeiCoinList;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.WeiCoinTradeDetail;
import com.okwei.bean.vo.order.TicketInfoVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IBasicWalletService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;

@Service
public class BasicWalletService extends BaseService implements IBasicWalletService {
	@Autowired
	private IBaseDAO baseDAO;
	
	@Override
	public ReturnModel getMyWeiCoinList(Long weiId,Integer pageIndex, Integer pageSize,
			Integer status) {
		ReturnModel rm= new ReturnModel();
		if(weiId== null || weiId<=0)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数错误");
			return rm;
		}
		UWallet wt=baseDAO.get(UWallet.class, weiId);
		if(wt == null)
		{
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("您尚未实名认证，请先认证！");
			return rm;
		}
		Short type=Short.parseShort(status.toString());
		String strHql=" from  UWeiDianCoinLog w where w.weiId=? and w.type=? order by w.logId desc";			
		PageResult<UWeiDianCoinLog> result=baseDAO.findPageResult(strHql, Limit.buildLimit(pageIndex, pageSize), weiId,type);
		MyWeiCoinList my= new MyWeiCoinList();
		my.setPageIndex(pageIndex);
		my.setPageSize(pageSize);
		my.setTotalCount(result.getTotalCount());
		my.setTotalPage(result.getTotalPage());		
		my.setHelpUrl(AppSettingUtil.getSingleValue("HelpUrl"));
		my.setCountAmount(wt.getWeiDianCoin());
		List<UWeiDianCoinLog> list=result.getList();
		if(list==null || list.size()<=0)
			my.setList(null);
		else
		{
			List<WeiCoinTradeDetail> wli= new ArrayList<WeiCoinTradeDetail>();
			if(status == 1) //收入			
			{			
				for(UWeiDianCoinLog wc:list)
				{
//					SMainData md= baseDAO.get(SMainData.class, wc.getShareId());
//					if(md==null)
//						continue;
					WeiCoinTradeDetail wd= new WeiCoinTradeDetail();
					wd.setAmount(wc.getConsumeAmount());
					wd.setDate(DateUtils.dateToString(wc.getCreateTime(), ""));
					wd.setDetailUrl("");//详细信息
					wd.setImg("http://base1.okimgs.com/images/bank/shouru.png");//图片
					wd.setPageId(wc.getShareId()==null?0L:wc.getShareId());
//					wd.setShareOne(md.getWeiId());
//					wd.setName(md.getTitle());
					wd.setName(wc.getRemark());
					wli.add(wd);
				}	
				
			}else if(status == 2)//支出
			{
				for(UWeiDianCoinLog wc:list)
				{
					OPayOrder po= baseDAO.get(OPayOrder.class, wc.getPayOrderId());
					if(po==null)
						continue;
					WeiCoinTradeDetail wd= new WeiCoinTradeDetail();
					wd.setAmount(wc.getConsumeAmount());
					wd.setDate(DateUtils.dateToString(wc.getCreateTime(), ""));
					wd.setDetailUrl("");//详细信息
					wd.setImg("http://base1.okimgs.com/images/bank/zhichu.png");//图片					
					wd.setName("购物消费");
					wli.add(wd);
				}	
			}
			my.setList(wli);
		}		
		rm.setBasemodle(my);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("调用成功");
		return rm;
	}

	@Override
	public ReturnModel getMyExchangeCoupon(Long weiId, Integer pageIndex,
			Integer pageSize, Integer status) {
		ReturnModel rm= new ReturnModel();
		if(weiId== null || weiId<=0)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数错误");
			return rm;
		}
		UWallet wt=baseDAO.get(UWallet.class, weiId);
		if(wt == null)
		{
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson("您尚未实名认证，请先认证！");
			return rm;
		}
//		Short type=Short.parseShort(status.toString());
		String strHql=" from  UTicketUseLog w where w.weiId=? and w.type=? order by w.ticketLogId desc";			
		PageResult<UTicketUseLog> result=baseDAO.findPageResult(strHql, Limit.buildLimit(pageIndex, pageSize), weiId,status);
		Map<String,Object> my= new HashMap<String,Object>();
		my.put("pageIndex",pageIndex);
		my.put("pageSize",pageSize);
		my.put("totalCount",result.getTotalCount());
		my.put("totalPage",result.getTotalPage());		
		my.put("countAmount",wt.getAbleTicket()==null?0.0:wt.getAbleTicket());
		List<UTicketUseLog> list=result.getList();
		
		List<TicketInfoVO> wli= new ArrayList<TicketInfoVO>();
		if(status == 1) //收入			
		{			
			for(UTicketUseLog wc:list)
			{
				TicketInfoVO wd= new TicketInfoVO();
				wd.setAmount(wc.getAmount());
				wd.setDate(DateUtils.dateToString(wc.getCreateTime(), ""));
				wd.setDetailUrl("");//详细信息
				wd.setImg("http://base1.okimgs.com/images/bank/shouru.png");//图片
				wd.setName(wc.getRemark());
				wd.setSupplyOrderId(wc.getOrderId());
				wli.add(wd);
			}	
			
		}else if(status == 2)//支出
		{
			for(UTicketUseLog wc:list)
			{
				
				TicketInfoVO wd= new TicketInfoVO();
				wd.setAmount(wc.getAmount());
				wd.setDate(DateUtils.dateToString(wc.getCreateTime(), ""));
				wd.setDetailUrl("");//详细信息
				wd.setImg("http://base1.okimgs.com/images/bank/zhichu.png");//图片					
				wd.setName(wc.getRemark());
				wd.setSupplyOrderId(wc.getOrderId());
				wli.add(wd);
			}	
		}
		my.put("list",wli);
				
		rm.setBasemodle(my);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("调用成功");
		return rm;
	}

}
