package com.okwei.appinterface.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.Accumulative;
import com.okwei.appinterface.bean.vo.Trench;
import com.okwei.appinterface.bean.vo.WaitToDeal;
import com.okwei.appinterface.dao.IBrandDao;
import com.okwei.appinterface.enums.PPHDataType;
import com.okwei.appinterface.service.IBrandSvervice;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.enums.RegionLevelEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.dao.user.IBaseSupplyDemandDAO;

@Service("brandServiceImpl")
public class BrandServiceImpl implements IBrandSvervice {
	@Autowired
	private IBrandDao brandDao;
	@Autowired
	private IBaseSupplyDemandDAO dao;

	@Override
	public ReturnModel queryBrandCount(Long weiId, String pageType) {
		ReturnModel returnModel = new ReturnModel();
		returnModel.setStatu(ReturnStatus.Success);
		if (weiId == null || pageType == null || "".equals(pageType)) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("参数有误！weiid=" + weiId + "pagetype="
					+ pageType);
		}
		Map<String, Object> reMap = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		// 待发货数量
		String waitShipments = "select count(1) from O_SupplyerOrder where state=1 and SupplyerID="
				+ weiId;
		// 退款中数量
		String refunding = "select count(1) from O_SupplyerOrder where state=5 and SupplyerID="
				+ weiId;
		// 代理商数量
		String agent = "select count(distinct weiid) from U_ProductAgent where state=1 and SupplyID="
				+ weiId;
		// 待审核代理商数量
		String waitAgent = "select count(distinct weiid) from U_AgentApply where state=0 and SupplyID="
				+ weiId;
		// 落地店数量
		String floorShop = "select count(1) from U_ProductShop where state=1  and SupplyID="
				+ weiId;
		// 待支付悬赏
		String waitPay = "select count(1) from U_ProductShop where state=1 and PayedReward=0 and SupplyID="
				+ weiId;
		// 已完成订单数
		String orderNum = "select count(1) from O_SupplyerOrder where state=4 and SupplyerID="
				+ weiId;
		// 已完成订单总成交额
		String turnover = "select sum(TotalPrice) from O_SupplyerOrder where state=4 and SupplyerID="
				+ weiId;
		// 月订单数
		String orderByMonth = "select count(1) from O_SupplyerOrder where month(ReceptTime) = month(NOW()) and year(ReceptTime)=year(NOW()) and state=4 and SupplyerID="
				+ weiId;
		// 子供应商未通过审核产品 待定
		String sonSupplierPros = "select count(1) from P_ProductSup where state=2 and ChildrenID="
				+ weiId;
		if (PPHDataType.PPSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中，待审核代理商，待支付悬赏
			reMap.put("waitShipments", brandDao.queryBySql(waitShipments)
					.get(0));
			reMap.put("refunding", brandDao.queryBySql(refunding).get(0));
			reMap.put("waitAgent", brandDao.queryBySql(waitAgent).get(0));
			reMap.put("waitPay", brandDao.queryBySql(waitPay).get(0));
			// 累计： 代理商，落地数，订单数，成交额
			reMap.put("agent", brandDao.queryBySql(agent).get(0));
			reMap.put("floorShop", brandDao.queryBySql(floorShop).get(0));
			reMap.put("orderNum", brandDao.queryBySql(orderNum).get(0));
			reMap.put("turnover", brandDao.queryBySql(turnover).get(0));
		} else if (PPHDataType.PZSY.toString().equals(pageType)) {
			// 待处理： 待发货，未通过审核
			reMap.put("sonSupplierPros", brandDao.queryBySql(sonSupplierPros)
					.get(0));
			reMap.put("waitShipments", brandDao.queryBySql(waitShipments)
					.get(0));
			// 累计： 月订单数，成交额
			reMap.put("orderByMonth", brandDao.queryBySql(orderByMonth).get(0));
			reMap.put("turnover", brandDao.queryBySql(turnover).get(0));
		} else if (PPHDataType.DLSSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中
			reMap.put("waitShipments", brandDao.queryBySql(waitShipments)
					.get(0));
			reMap.put("refunding", brandDao.queryBySql(refunding).get(0));
			// 累计： 落地数，订单数，成交额
			reMap.put("floorShop", brandDao.queryBySql(floorShop).get(0));
			reMap.put("orderNum", brandDao.queryBySql(orderNum).get(0));
			reMap.put("turnover", brandDao.queryBySql(turnover).get(0));
		} else if (PPHDataType.LDDSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中
			reMap.put("waitShipments", brandDao.queryBySql(waitShipments)
					.get(0));
			reMap.put("refunding", brandDao.queryBySql(refunding).get(0));
			// 累计： 订单数，成交额
			reMap.put("orderNum", brandDao.queryBySql(orderNum).get(0));
			reMap.put("turnover", brandDao.queryBySql(turnover).get(0));
		}
		returnModel.setBasemodle(reMap);
		return returnModel;
	}

	@Override
	public ReturnModel queryRankingResults(Long weiId, int countType,
			int pageIndex, int pageSize) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		try {
			if (weiId == null || weiId < 1) {
				return null;
			}
			Integer dayNum = -1; // 区分七天三十天个和累计
			if (countType == 1) {
				dayNum = 7;
			} else if (countType == 2) {
				dayNum = 30;
			}
			List<ChannelRegionVO> result = new ArrayList<ChannelRegionVO>();// 返回对象
			List<Integer> codeIDs = new ArrayList<Integer>();// 去重标识
			List<Object[]> agentObjs = dao.getAgentsCountByRegion(weiId, null,
					0, RegionLevelEnum.Province, dayNum);
			if (agentObjs != null && agentObjs.size() > 0) {
				for (Object[] objs : agentObjs) {
					if (objs[0] == null) {
						break;
					}
					ChannelRegionVO cr = new ChannelRegionVO();
					cr.setCode((Integer) objs[0]);
					cr.setAgentCount(Integer.parseInt(objs[1].toString()));
					cr.setShopCount(0);
					result.add(cr);
					codeIDs.add((Integer) objs[0]);
				}
			}
			List<Object[]> shopObjs = dao.getShopCountByRegion(weiId, null, 0,
					RegionLevelEnum.Province, dayNum);
			if (shopObjs != null && shopObjs.size() > 0) {
				for (Object[] objs : shopObjs) {
					if (objs[0] == null) {
						break;
					}

					// 此区域是否已创建 不存在 添加
					if (!codeIDs.contains((Integer) objs[0])) {
						ChannelRegionVO cr = new ChannelRegionVO();
						cr.setCode((Integer) objs[0]);
						cr.setShopCount(Integer.parseInt(objs[1].toString()));
						cr.setAgentCount(0);
						result.add(cr);

						codeIDs.add((Integer) objs[0]);
						continue;
					}
					// 此区域存在的话 找到该区域 添加落地店数量
					for (ChannelRegionVO regionVO : result) {
						if (regionVO.getCode().equals((Integer) objs[0])) {
							regionVO.setShopCount(Integer.parseInt(objs[1]
									.toString()));
							break;
						}
					}

				}
			}
			// 获取招商需求的当前招商区域
			List<USupplyDemandArea> demandAreas = dao.getDemandAreas(0, 0);
			if (demandAreas != null && demandAreas.size() > 0) {
				for (USupplyDemandArea area : demandAreas) {
					if (area.getCode() == null) {
						break;
					}

					if (!codeIDs.contains(area.getCode())) {
						ChannelRegionVO cr = new ChannelRegionVO();
						cr.setCode(area.getCode());
						cr.setAgentCount(0);
						cr.setShopCount(0);
						result.add(cr);
						codeIDs.add(area.getCode());
					}
				}
			}

			// 获取区域名称
			List<TRegional> regionals = dao.getRegionals((Integer[]) codeIDs
					.toArray(new Integer[codeIDs.size()]));
			if (regionals != null && regionals.size() > 0) {
				// 组装区域名称
				for (ChannelRegionVO regionVO : result) {
					for (TRegional tRegional : regionals) {
						if (regionVO.getCode().equals(tRegional.getCode())) {
							regionVO.setCodeName(tRegional.getName());
							break;
						}
					}
				}
			}
			List<Trench> ammList = new ArrayList<Trench>();
			for (ChannelRegionVO regionVO : result) {
				if (regionVO.getAgentCount() != 0
						|| regionVO.getShopCount() != 0) {
					Trench amm = new Trench();
					amm.setProvince(regionVO.getCodeName());
					amm.setTotalAgent(regionVO.getAgentCount());
					amm.setTotalStore(regionVO.getShopCount());
					ammList.add(amm);
				}
			}
			//根据代理商数量和落地店数量降序
			if(ammList!=null&&ammList.size()>0){
				ammList.sort(new Comparator<Trench>() {
					public int compare(Trench o1, Trench o2) {
						if((o1.getTotalAgent()+o1.getTotalStore())>(o2.getTotalAgent()+o2.getTotalStore())){
							return -1;
						}else{
							return 0;
						}
						
					}
				});	
			}
			rm.setBasemodle(ammList);
		} catch (NumberFormatException e) {
			rm.setStatu(ReturnStatus.DataError);
			rm.setStatusreson(e.getMessage());
		}
		return rm;

	}

	@Override
	public ReturnModel queryWaitDeal(Long weiId, String pageType) {
		ReturnModel returnModel = new ReturnModel();
		WaitToDeal deal = new WaitToDeal();
		returnModel.setStatu(ReturnStatus.Success);
		if (weiId == null || pageType == null || "".equals(pageType)) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("参数有误！weiid=" + weiId + "pagetype="
					+ pageType);
		}
		// TODO Auto-generated method stub
		// 待发货数量
		String waitShipments = "select count(1) from O_SupplyerOrder where state=1 and SupplyerID="
				+ weiId;
		// 退款中数量
		String refunding = "select count(1) from O_SupplyerOrder where state=5 and SupplyerID="
				+ weiId;
		// 待审核代理商数量
		String waitAgent = "select count(1) from U_AgentApply where state=0 and SupplyID="
				+ weiId;
		// 待支付悬赏
		String waitPay = "select count(weiId) from U_SupplyChannel where  (payedReward=0 or payedReward is NULL)  and state=1 and supplyId ="
				+ weiId;
		// 已完成订单数
		// 子供应商未通过审核产品 待定
		String sonSupplierPros = "select count(1) from P_ProductSup where state=2 and ChildrenID="
				+ weiId;
		if (PPHDataType.PPSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中，待审核代理商，待支付悬赏
			deal.setWaitSendOrder(((BigInteger) brandDao.queryBySql(
					waitShipments).get(0)).intValue());
			deal.setWaitRefund(((BigInteger) brandDao.queryBySql(refunding)
					.get(0)).intValue());
			deal.setWaitAuditAgent(((BigInteger) brandDao.queryBySql(waitAgent)
					.get(0)).intValue());
			deal.setWaitPayReward(((BigInteger) brandDao.queryBySql(waitPay)
					.get(0)).intValue());
		} else if (PPHDataType.PZSY.toString().equals(pageType)) {
			// 待处理： 待发货，未通过审核
			deal.setWaitSendOrder(((BigInteger) brandDao.queryBySql(
					waitShipments).get(0)).intValue());
			deal.setWaitAuditProduct(((BigInteger) brandDao.queryBySql(
					sonSupplierPros).get(0)).intValue());
		} else if (PPHDataType.DLSSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中
			deal.setWaitSendOrder(((BigInteger) brandDao.queryBySql(
					waitShipments).get(0)).intValue());
			deal.setWaitRefund(((BigInteger) brandDao.queryBySql(refunding)
					.get(0)).intValue());
		} else if (PPHDataType.LDDSY.toString().equals(pageType)) {
			// 待处理： 待发货，退款中
			deal.setWaitSendOrder(((BigInteger) brandDao.queryBySql(
					waitShipments).get(0)).intValue());
			deal.setWaitRefund(((BigInteger) brandDao.queryBySql(refunding)
					.get(0)).intValue());
		}
		returnModel.setBasemodle(deal);
		return returnModel;
	}

	@Override
	public ReturnModel queryStatistics(Long weiId, String pageType) {
		ReturnModel returnModel = new ReturnModel();
		returnModel.setStatu(ReturnStatus.Success);
		if (weiId == null || pageType == null || "".equals(pageType)) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("参数有误！weiid=" + weiId + "pagetype="
					+ pageType);
		}
		Accumulative accumulative = new Accumulative();
		// TODO Auto-generated method stub
		// 代理商数量
		String agent = "select count(DISTINCT(WeiID)) from U_SupplyChannel WHERE State=1 AND ChannelType=1 AND SupplyID="
				+ weiId;
		// 落地店数量 平台号
		String floorShopbypth = "select count(DISTINCT(WeiID)) from U_SupplyChannel WHERE State=1 AND ChannelType=2 AND SupplyID="
				+ weiId;
		// 落地店数量 代理商
		String floorShopbydls = "select count(DISTINCT(WeiID)) from U_SupplyChannel WHERE State=1 AND ChannelType=2 AND upweiid="
				+ weiId;
		// 已完成订单数
		String orderNum = "select count(1) from O_SupplyerOrder where state=4 and SupplyerID="
				+ weiId;
		// 已完成订单总成交额
		String turnover = "select sum(TotalPrice) from O_SupplyerOrder where state=4 and SupplyerID="
				+ weiId;
		/*
		 * // 月订单数 String orderByMonth =
		 * "select count(1) from O_SupplyerOrder where month(ReceptTime) = month(NOW()) and year(ReceptTime)=year(NOW()) and state=4 and SupplyerID="
		 * + weiId;
		 */
		if (PPHDataType.PPSY.toString().equals(pageType)) {
			// 累计： 代理商，落地数，订单数，成交额
			accumulative.setTotalAgent(((BigInteger) brandDao.queryBySql(agent)
					.get(0)).intValue());
			accumulative.setTotalStore(((BigInteger) brandDao.queryBySql(
					floorShopbypth).get(0)).intValue());
			accumulative.setTotalOrder(((BigInteger) brandDao.queryBySql(
					orderNum).get(0)).intValue());

		} else if (PPHDataType.PZSY.toString().equals(pageType)) {
			// 累计：订单数，成交额
			accumulative.setTotalOrder(((BigInteger) brandDao.queryBySql(
					orderNum).get(0)).intValue());
		} else if (PPHDataType.DLSSY.toString().equals(pageType)) {
			// 累计： 落地数，订单数，成交额
			accumulative.setTotalStore(((BigInteger) brandDao.queryBySql(
					floorShopbydls).get(0)).intValue());
			accumulative.setTotalOrder(((BigInteger) brandDao.queryBySql(
					orderNum).get(0)).intValue());
		} else if (PPHDataType.LDDSY.toString().equals(pageType)) {
			// 累计： 订单数，成交额
			accumulative.setTotalOrder(((BigInteger) brandDao.queryBySql(
					orderNum).get(0)).intValue());
		}
		if (brandDao.queryBySql(turnover) != null
				&& brandDao.queryBySql(turnover).size() > 0) {
			if (brandDao.queryBySql(turnover).get(0) != null) {
				accumulative.setTotalOrderAmount(((BigDecimal) brandDao
						.queryBySql(turnover).get(0)).intValue());
			} else {
				accumulative.setTotalOrderAmount(0);
			}

		} else {
			accumulative.setTotalOrderAmount(0);
		}
		returnModel.setBasemodle(accumulative);
		return returnModel;
	}

	@Override
	public ReturnModel queryUpManagerStatic(Long weiId) {
		ReturnModel returnModel = new ReturnModel();
		returnModel.setStatu(ReturnStatus.Success);
		returnModel.setStatusreson("成功");
		if (weiId == null) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("参数有误！weiid=" + weiId);
			return returnModel;
		}
		// 推荐供应商数量
		String recommendsupplyer = "select count(1) from U_SupplyChannel where ChannelType=1 and State=1 and Verifier="
				+ weiId;
		// 我的供应商数量
		String mysupplyer = "select count(1) from U_SupplyChannel where ChannelType=1 and State=1  and SupplyID="
				+ weiId;
		// 关注店铺数量
		String attentionshop = "select count(1) from U_CollectionStore where State=1 and WeiID="
				+ weiId;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recommendMerchant",
				((BigInteger) brandDao.queryBySql(recommendsupplyer).get(0))
						.intValue());
		map.put("childMerchantCount",
				((BigInteger) brandDao.queryBySql(mysupplyer).get(0))
						.intValue());
		map.put("followMerchantCount",
				((BigInteger) brandDao.queryBySql(attentionshop).get(0))
						.intValue());
		returnModel.setBasemodle(map);
		return returnModel;
	}

}
