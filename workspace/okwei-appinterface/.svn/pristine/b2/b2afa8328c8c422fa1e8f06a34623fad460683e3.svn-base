package com.okwei.appinterface.service.impl.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.okwei.appinterface.bean.vo.agent.ApplyBrandAgentInfoVO;
import com.okwei.appinterface.bean.vo.agent.ApplyListAgentVO;
import com.okwei.appinterface.service.agent.IBrandAgentService;
import com.okwei.bean.domain.DAgentApply;
import com.okwei.bean.domain.DAgentApplyLog;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DAgentRelation;
import com.okwei.bean.domain.DBrandAgentInfo;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.DBrandsExt;
import com.okwei.bean.domain.DCastellans;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.agent.AgentStatus;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class BrandAgentService extends BaseDAO implements IBrandAgentService {
	private static final String[] RELATIONSHIP_ARRAY = { "", "代理", "队长",
			"城主候选人", "城主" };

	@Override
	public PageResult<DAgentInfo> findDAgentInfos(long weiid, int brandid,
			int scope, int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandid);
		map.put("weiid", weiid);
		String hql = "SELECT a FROM DAgentInfo a WHERE a.brandId=:brandId AND (a.superWeiid=:weiid OR a.weiId=:weiid) ";
		switch (scope) {
		case 0:
			break;
		case 1: // 代理
			hql += " AND (a.weiId NOT IN (SELECT b.weiId FROM DAgentTeam b WHERE b.brandId=:brandId AND b.weiId=:weiid))";
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId AND c.weiId=:weiid))";
			break;
		case 2: // 队长
			hql += " AND (a.weiId IN (SELECT b.weiId FROM DAgentTeam b WHERE b.brandId=:brandId AND b.weiId=:weiid))";
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId AND c.weiId=:weiid))";
			break;
		case 3: // 代理+队长
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId AND c.weiId=:weiid))";
			break;
		case 4: // 城主候选人
			hql += " AND (a.weiId IN (SELECT c.weiId FROM DCastellans c WHERE c.porN=2 AND c.brandId=:brandId AND c.weiId=:weiid))";
			break;
		case 8: // 城主
			hql += " AND (a.weiId IN (SELECT d.weiId FROM DCastellans d WHERE d.porN=1 AND d.brandId=:brandId AND d.weiId=:weiid))";
			break;
		default:
			hql += " AND (1<>1)";
			break;
		}
		hql += " ORDER BY a.agentId DESC";
		Limit limit = Limit.buildLimit(pageIndex, pageSize);
		return super.findPageResultByMap(hql, limit, map);
	}

	@Override
	public PageResult<DAgentInfo> findDAgentInfos(int brandid, int scope,
			int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandid);
		String hql = "SELECT a FROM DAgentInfo a WHERE a.brandId=:brandId ";
		switch (scope) {
		case 0:
			break;
		case 1: // 代理
			hql += " AND (a.weiId NOT IN (SELECT b.weiId FROM DAgentTeam b WHERE b.brandId=:brandId))";
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId))";
			break;
		case 2: // 队长
			hql += " AND (a.weiId IN (SELECT b.weiId FROM DAgentTeam b WHERE b.brandId=:brandId))";
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId))";
			break;
		case 3: // 代理+队长
			hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.brandId=:brandId))";
			break;
		case 4: // 城主候选人
			hql += " AND (a.weiId IN (SELECT c.weiId FROM DCastellans c WHERE c.porN=2 AND c.brandId=:brandId))";
			break;
		case 8: // 城主
			hql += " AND (a.weiId IN (SELECT d.weiId FROM DCastellans d WHERE d.porN=1 AND d.brandId=:brandId))";
			break;
		default:
			hql += " AND (1<>1)";
			break;
		}
		hql += " ORDER BY a.agentId DESC";
		Limit limit = Limit.buildLimit(pageIndex, pageSize);
		return super.findPageResultByMap(hql, limit, map);
	}

	@Override
	public DBrands getBrands(long weiid) {
		DBrandSupplier supplier = super.get(DBrandSupplier.class, weiid);
		if (supplier != null && supplier.getBrandId() != null) {
			return super.get(DBrands.class, supplier.getBrandId());
		}
		return null;
	}

	@Override
	public List<DBrands> findAgentBrands(long weiid) {
		Map<String, Object> hqlParams = new HashMap<>();
		hqlParams.put("weiid", weiid);
		List<Integer> agentBrandIds = super
				.findByMap(
						"SELECT a.brandId FROM DAgentInfo a WHERE a.superWeiid=:weiid OR a.weiId=:weiid",
						hqlParams);
		if (agentBrandIds != null && agentBrandIds.size() > 0) {
			hqlParams = new HashMap<>();
			hqlParams.put("brandids", agentBrandIds);
			List<DBrands> list = super
					.findByMap(
							"FROM DBrands a WHERE a.brandId IN (:brandids) ORDER BY a.createTime DESC",
							hqlParams);
			return list;
		}
		return null;
	}

	@Override
	public String getAddress(Integer... regionCodes) {
		String result = "";
		List<Integer> regionCodeList = new ArrayList<Integer>();
		for (Integer regionCode : regionCodes) {
			if (regionCode != null)
				regionCodeList.add(regionCode);
		}
		if (regionCodeList.size() > 0) {
			Map<String, Object> hqlParams = new HashMap<>();
			hqlParams.put("codes", regionCodeList);
			List<String> regionNames = super.findByMap(
					"SELECT a.name FROM TRegional a WHERE code IN (:codes)",
					hqlParams);
			for (String regionName : regionNames) {
				result += regionName;
			}
		}
		return result;
	}

	@Override
	public int getAgentRelationshipType(long weiid, int brandid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandid);
		map.put("weiid", weiid);
		String hql;
		hql = "FROM DCastellans c WHERE c.porN=1 AND c.brandId=:brandId AND c.weiId=:weiid";
		List<DCastellans> castellansList = super.findByMap(hql, map);
		if (castellansList != null && castellansList.size() > 0) {
			DCastellans castellans = castellansList.get(0);
			return castellans.getPorN() == 1 ? 4 : 3; // 城主或副城主
		}
		hql = "SELECT count(*) FROM DAgentTeam b WHERE b.brandId=:brandId AND b.weiId=:weiid";
		if (super.countByMap(hql, map) > 0)
			return 2; // 队长
		hql = "SELECT count(*) FROM DAgentInfo a WHERE a.brandId=:brandId AND (a.superWeiid=:weiid OR a.weiId=:weiid)";
		if (super.countByMap(hql, map) > 0)
			return 1; // 代理
		return 0;
	}

	@Override
	public String getRelationshipName(int relationshipType) {
		if (relationshipType >= 0
				&& relationshipType < RELATIONSHIP_ARRAY.length)
			return RELATIONSHIP_ARRAY[relationshipType];
		return "";
	}

	@Override
	public ReturnModel saveApplyAgent(Integer brandid, String name,
			String contactPhone, String qq, String weixin, Double cost,
			Long weiId, Long referencer, Integer level, String explain) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.DataError);
		DBrandAgentInfo dAgentInfo = super.get(DBrandAgentInfo.class, brandid);
		switch (level) {
		case 1:
			cost = dAgentInfo.getAgencyOne();
			break;
		case 2:
			cost = dAgentInfo.getAgencyTwo();
			break;
		case 3:
			cost = dAgentInfo.getAgencyThree();
			break;
		default:
			cost = 0d;
		}
		// DAgentInfo
		// dainfo=baseDAO.getNotUniqueResultByHql("from DAgentInfo a where a.brandId=? and a.weiId=?",
		// brandid,weiId);
		DAgentApply daifo = super.getNotUniqueResultByHql(
				"from DAgentApply a where a.brandId=? and a.weiId=?", brandid,
				weiId);
		if (daifo != null) {
			rm.setStatu(ReturnStatus.DataExists);
			rm.setStatusreson("已经参加了该品牌的代理申请！");
			return rm;
		}
		daifo = new DAgentApply();
		daifo.setBrandId(brandid);
		daifo.setName(name);
		daifo.setContactPhone(contactPhone);
		daifo.setQq(qq);
		daifo.setWeiXin(weixin);
		daifo.setCosts(cost);
		daifo.setWeiId(weiId);
		daifo.setAdvantage(explain);
		daifo.setReferencer(referencer);// 推薦人
		daifo.setCreateTime(new Date());
		daifo.setLevel(level);
		daifo.setStatus(3);
		super.save(daifo);

		DAgentApplyLog dalog = new DAgentApplyLog();
		dalog.setAgentApplyId(daifo.getAgentApplyId());// da.getAgentApplyId()
		dalog.setCreateTime(new Date());
		dalog.setOperator(weiId);
		dalog.setContent("代理申請");
		super.save(dalog);
		if (cost.compareTo(0d) == 0) {
			DAgentInfo dainfo = new DAgentInfo();
			dainfo.setBrandId(brandid);
			dainfo.setContactPhone(contactPhone);
			dainfo.setCosts(0.0);
			dainfo.setCreateTime(new Date());
			dainfo.setLevel(level);
			dainfo.setQq(qq);
			dainfo.setName(name);
			dainfo.setSuperWeiid(referencer);
			dainfo.setWeiId(weiId);
			super.save(dainfo);

			// 反写DAgentApply的状态及agentid字段
			daifo.setStatus(Integer.parseInt(AgentStatus.Ok.toString()));
			daifo.setAgentId(dainfo.getAgentId());
			super.saveOrUpdate(daifo);
			// 更新或插入品牌辅助表DBrandsExt 更新品牌代理数量
			DBrandsExt de = super.get(DBrandsExt.class, brandid);
			if (de == null) {
				de = new DBrandsExt();
				de.setBrandId(brandid);
				de.setCreateTime(new Date());
				de.setAgentOneCount(0);
				de.setAgentTwoCount(0);
				de.setAgentThreeCount(0);
			}
			UUserAssist ua = super.get(UUserAssist.class, weiId);
			if (ua == null) {
				ua = new UUserAssist();
				ua.setWeiId(weiId);
				ua.setIdentity(1);
			}
			if (daifo.getLevel().intValue() == 1) {
				ua.setIdentity(ua.getIdentity()
						+ Integer.parseInt(UserIdentityType.AgentDuke
								.toString()));
				de.setAgentOneCount(de.getAgentOneCount() + 1);
			} else if (daifo.getLevel().intValue() == 2) {
				ua.setIdentity(ua.getIdentity()
						+ Integer.parseInt(UserIdentityType.AgentDeputyDuke
								.toString()));
				de.setAgentTwoCount(de.getAgentTwoCount() + 1);
			} else if (daifo.getLevel().intValue() == 3) {
				ua.setIdentity(ua.getIdentity()
						+ Integer.parseInt(UserIdentityType.AgentBrandAgent
								.toString()));
				de.setAgentThreeCount(de.getAgentThreeCount() + 1);
			}
			super.saveOrUpdate(ua);
			super.saveOrUpdate(de);
			// 插入代理商关系表
			// DAgentRelation agentrelation=null;
			// 先更新DAgentRelation表数据
			//
			if (daifo.getLevel().intValue() == 1)// 申请一级代理
			{
				super.executeHql(
						" update DAgentRelation d set d.weiIdone=? where d.brandId=? and d.weiId in (select n.weiId from DAgentInfo n where n.brandId=? and n.superWeiid=?)",
						daifo.getWeiId(), daifo.getBrandId(),
						daifo.getBrandId(), daifo.getWeiId());
			} else if (daifo.getLevel().intValue() == 2)// 申请一级代理
			{
				super.executeHql(
						" update DAgentRelation d set d.weiIdsec=? where d.brandId=? and d.weiId in (select n.weiId from DAgentInfo n where n.brandId=? and n.superWeiid=?)",
						daifo.getWeiId(), daifo.getBrandId(),
						daifo.getBrandId(), daifo.getWeiId());
			}
			DAgentRelation dr = super.getUniqueResultByHql(
					" from DAgentRelation d where d.weiId=? and d.brandId=?",
					daifo.getWeiId(), daifo.getBrandId());
			if (dr == null) {
				dr = new DAgentRelation();
				dr.setWeiId(daifo.getWeiId());
				dr.setMyLevel(daifo.getLevel());
				dr.setSuperWeiId(daifo.getReferencer());
				dr.setBrandId(daifo.getBrandId());
				// 获取上级的上级数据
				DAgentRelation drs = super
						.getUniqueResultByHql(
								" from DAgentRelation d where d.weiId=? and d.brandId=?",
								daifo.getReferencer(), daifo.getBrandId());
				if (drs != null) {
					dr.setWeiIdone(drs.getWeiIdone());
					dr.setWeiIdsec(dr.getWeiIdsec());
					if (drs.getMyLevel().intValue() == 1) {
						dr.setWeiIdone(drs.getWeiId());
					} else if (drs.getMyLevel().intValue() == 2) {
						dr.setWeiIdsec(drs.getWeiId());
					}
				}
			}
			super.saveOrUpdate(dr);
		}

		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("申请成功！");
		return rm;
	}

	@Override
	public ApplyBrandAgentInfoVO getApplyAgent(Integer brandid) {
		ApplyBrandAgentInfoVO appagentVO = new ApplyBrandAgentInfoVO();
		List<ApplyListAgentVO> list = new ArrayList<ApplyListAgentVO>();
		DBrands brands = super.get(DBrands.class, brandid);
		DBrandAgentInfo dAgentInfo = super.get(DBrandAgentInfo.class, brandid);
		if (brands != null && dAgentInfo != null) {
			appagentVO.setBrandId(brands.getBrandId());
			appagentVO.setBrandName(brands.getBrandName());
			appagentVO.setAgentRuleText(dAgentInfo.getInstitution());
			for (int i = 0; i <3; i++) {
				ApplyListAgentVO agentVO = new ApplyListAgentVO();
				if (i == 0) {
					agentVO.setAgentLevelId(i+1);
					agentVO.setAgentLevelName("一级代理");
					agentVO.setPrice(dAgentInfo.getAgencyOne());
					agentVO.setDescription(dAgentInfo.getSummaryOne());
					list.add(agentVO);
				} else if (i == 1) {
					agentVO.setAgentLevelId(i+1);
					agentVO.setAgentLevelName("二级代理");
					agentVO.setPrice(dAgentInfo.getAgencyTwo());
					agentVO.setDescription(dAgentInfo.getSummaryTwo());
					list.add(agentVO);
				} else if (i == 2) {
					agentVO.setAgentLevelId(i+1);
					agentVO.setAgentLevelName("三级代理");
					agentVO.setPrice(dAgentInfo.getAgencyThree());
					agentVO.setDescription(dAgentInfo.getSummaryThree());
					list.add(agentVO);
					break;
				}
			}
			appagentVO.setPriceList(list);
		} else {
			appagentVO = null;
		}
		return appagentVO;
	}

}
