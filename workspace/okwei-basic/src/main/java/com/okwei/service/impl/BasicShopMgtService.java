package com.okwei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.DCastellans;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.service.IBrandAgentService;

@Repository
public class BasicShopMgtService extends BaseDAO implements IBrandAgentService{
	private static final String[] RELATIONSHIP_ARRAY = {"", "代理", "队长", "城主候选人", "城主"};

	@Override
	public PageResult<DAgentInfo> findDAgentInfos(long weiid, int brandid, int scope, int pageIndex, int pageSize){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandId", brandid);
		map.put("weiid", weiid);
		String hql = "SELECT a FROM DAgentInfo a WHERE a.brandId=:brandId AND (a.superWeiid=:weiid OR a.weiId=:weiid) ";
		switch(scope) {
			case 0:
				break;
			case 1:
				hql += " AND (a.weiId NOT IN (SELECT b.weiId FROM DAgentMembers b WHERE b.brandId=:brandId AND b.superWeiid=:weiid))";
				hql += " AND (a.weiId NOT IN (SELECT c.weiId FROM DCastellans c WHERE c.porN=1 AND c.brandId=:brandId AND c.weiId=:weiid))";
				hql += " AND (a.weiId NOT IN (SELECT d.weiId FROM DCastellans d WHERE d.porN=2 AND d.brandId=:brandId AND d.weiId=:weiid))";
				break;
			case 2: //队长
				hql += " AND (a.weiId IN (SELECT b.weiId FROM DAgentMembers b WHERE b.brandId=:brandId AND b.superWeiid=:weiid))";
				break;
			case 4: //城主候选人
				hql += " AND (a.weiId IN (SELECT c.weiId FROM DCastellans c WHERE c.porN=1 AND c.brandId=:brandId AND c.weiId=:weiid))";
				break;
			case 8: //城主
				hql += " AND (a.weiId IN (SELECT d.weiId FROM DCastellans d WHERE d.porN=2 AND d.brandId=:brandId AND d.weiId=:weiid))";
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
	public DBrands getBrands(long weiid){
		DBrandSupplier supplier=super.get(DBrandSupplier.class, weiid);
		if(supplier!=null&&supplier.getBrandId()!=null){
			return super.get(DBrands.class, supplier.getBrandId());
		}
		return null;
	}

	@Override
	public List<DBrands> findAgentBrands(long weiid) {
		Map<String, Object> hqlParams = new HashMap<>();
		hqlParams.put("weiid", weiid);
		List<Integer> agentBrandIds = super.findByMap("SELECT a.brandId FROM DAgentInfo a WHERE a.superWeiid=:weiid OR a.weiId=:weiid", hqlParams);
		if (agentBrandIds != null && agentBrandIds.size() > 0) {
			hqlParams = new HashMap<>();
			hqlParams.put("brandids", agentBrandIds);
			List<DBrands> list = super.findByMap("FROM DBrands a WHERE a.brandId IN (:brandids) ORDER BY a.createTime DESC", hqlParams);
			return list;
		}
		return null;
	}

	@Override
	public String getAddress(Integer... regionCodes) {
		String result = "";
		List<Integer> regionCodeList = new ArrayList<Integer>();
		for (Integer regionCode: regionCodes) {
			if (regionCode != null)
				regionCodeList.add(regionCode);
		}
		if (regionCodeList.size() > 0) {
			Map<String, Object> hqlParams = new HashMap<>();
			hqlParams.put("codes", regionCodeList);
			List<String> regionNames = super.findByMap("SELECT a.name FROM TRegional a WHERE code IN (:codes)", hqlParams);
			for (String regionName : regionNames) {
				result += regionName;
			}
		}
		return result;
	}

	@Override
	public int getAgentRelationshipType(long weiid, int brandid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandId", brandid);
		map.put("weiid", weiid);
		String hql;
		hql = "FROM DCastellans c WHERE c.porN=1 AND c.brandId=:brandId AND c.weiId=:weiid";
		List<DCastellans> castellansList = super.findByMap(hql, map);
		if (castellansList != null && castellansList.size() > 0) {
			DCastellans castellans = castellansList.get(0);
			return castellans.getPorN() == 1 ? 4 : 3; //城主或副城主
		}
		hql = "SELECT count(*) FROM DAgentMembers b WHERE b.brandId=:brandId AND b.superWeiid=:weiid";
		if (super.countByMap(hql, map) > 0)
			return 2; //队长
		hql = "SELECT count(*) FROM DAgentInfo a WHERE a.brandId=:brandId AND (a.superWeiid=:weiid OR a.weiId=:weiid)";
		if (super.countByMap(hql, map) > 0)
			return 1; //代理
		return 0;
	}

	@Override
	public String getRelationshipName(int relationshipType) {
		if (relationshipType >= 0 && relationshipType < RELATIONSHIP_ARRAY.length)
			return RELATIONSHIP_ARRAY[relationshipType];
		return "";
	}
}
