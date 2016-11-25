package com.okwei.appinterface.service.impl.agent;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.okwei.appinterface.service.agent.IAgentApiService;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DCastellans;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.agent.CastellanType;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IUUserAssistMgtDAO;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class AgentApiService extends BaseDAO implements IAgentApiService {

	private static final String[] RELATIONSHIP_ARRAY = {"", "代理", "队长", "城主候选人", "城主"};

	@Autowired
	private IUUserAssistMgtDAO userDao;

	public long updateAgentUserIdentity(int pageIndex,int pageSize) {
		String hql = " from DAgentInfo a where a.agentId>? order by a.agentId asc ";
		PageResult<DAgentInfo> pageResult = super.findPageResult(hql, Limit.buildLimit(pageIndex, pageSize), 0l);
		List<Long> weiidList=new ArrayList<Long>();
		if (pageResult.getList() != null && pageResult.getList().size() > 0) {
			for (DAgentInfo dd : pageResult.getList()) {
				if(dd.getWeiId()!=null&&dd.getWeiId()>0){
					weiidList.add(dd.getWeiId());
					userDao.addIdentity(dd.getWeiId(), UserIdentityType.AgentBrandAgent);
				}
			}
		}
		String hql_co=" from DCastellans a where a.weiId in(:weiids) ";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("weiids", weiidList);
		List<DCastellans> listCastellans=super.findByMap(hql_co,map);
		if(listCastellans!=null&&listCastellans.size()>0){
			for (DCastellans dd : listCastellans) {
				if(dd.getPorN()==Integer.parseInt( CastellanType.castellan.toString())){
					userDao.addIdentity(dd.getWeiId(), UserIdentityType.AgentDuke);
				}else {
					userDao.addIdentity(dd.getWeiId(), UserIdentityType.AgentDeputyDuke);
				}
			}
		}
		return pageResult.getTotalPage();
	}
}
