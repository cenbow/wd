package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.DAgentApply;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.DCastellans;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.bean.dto.AgentDTO;
import com.okwei.myportal.bean.dto.DukeDTO;
import com.okwei.myportal.bean.dto.DupetyDTO;
import com.okwei.myportal.bean.vo.AgentBrandNameVO;
import com.okwei.myportal.bean.vo.DeputyListVO;
import com.okwei.myportal.bean.vo.DukeListVO;
import com.okwei.myportal.bean.vo.MyAgentBrandVO;
import com.okwei.myportal.service.IAgentTeamService;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.TRegionalService;
import com.okwei.service.agent.IDAgentService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;

@Service
public class AgentTeamServiceImpl  extends BaseService implements IAgentTeamService{
	@Autowired
	private IBaseDAO baseDAO;

	@Autowired
	private IBaseCommonService commonService;
	@Autowired
	private IDAgentService agentService;
	
	@Autowired
	private TRegionalService regionalService;
	@Override
	public PageResult<DukeListVO> getDukeList(long weiID, DukeDTO dto,
			Limit limit) {
		DBrands db=baseDAO.getUniqueResultByHql(" from DBrands b where b.weiId=?", weiID);
		if(db==null)
			return null;
		String hql=" select d.agentId as agentid, d.weiId as weiid,d.province as province,d.city as city,d.district as district,"
				+ "d.contactPhone as mobile,d.outOrIn as type,d.createTime as applytime,d.brandId as brandid"
				+ " from DCastellans d where d.porN=1 and d.brandId=:brandid";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("brandid", db.getBrandId());
		if(dto.getProvince()!=null && dto.getProvince()>0)
		{
			hql+=" and d.province=:province";
			map.put("province", dto.getProvince());
		}
		if(dto.getCity()!=null && dto.getCity()>0)
		{
			hql+=" and d.city=:city";
			map.put("city", dto.getCity());
		}
		if(dto.getDistrict()!=null && dto.getDistrict()>0)
		{
			hql+=" and d.district=:district";
			map.put("district", dto.getDistrict());
		}
		if(dto.getAgenttype()!=null && dto.getAgenttype()>0)
		{
			hql+=" and d.outOrIn=:agenttype";
			map.put("agenttype", dto.getAgenttype());
		}
		if(dto.getWeiid()!=null && dto.getWeiid()>0)
		{
			hql+=" and d.weiId=:weiid";
			map.put("weiid", dto.getWeiid());
		}
		PageResult<DukeListVO> pr= baseDAO.findPageResultTransByMap(hql, DukeListVO.class, limit, map);
		List<DukeListVO> list=pr.getList();
		for(DukeListVO dv:list)
		{
			
			dv.setShowapplytime(DateUtils.dateToString(dv.getApplytime(), "yyyy-MM-dd"));
			DAgentInfo di = baseDAO.get(DAgentInfo.class, dv.getAgentid());
			if(di!=null)
			{
				dv.setBond(di.getCosts());
				dv.setQq(di.getQq());
				dv.setBond(di.getCosts()==null?0.0:di.getCosts());
			}
			dv.setShopname(commonService.getShopNameByWeiId(dv.getWeiid()));
			dv.setShowarea(regionalService.getNameByCode(dv.getProvince())+"-"+regionalService.getNameByCode(dv.getCity())+"-"+regionalService.getNameByCode(dv.getDistrict()));
			
		}
		return pr;
	}
	@Override
	public PageResult<DeputyListVO> getDeputyList(long weiid, DupetyDTO dto,
			Limit limit) {
		
		if(dto.getIsbrand()==0)//城主
		{
			DCastellans dc=baseDAO.getUniqueResultByHql(" from DCastellans d where d.weiId=? and d.brandId=?", weiid,dto.getBrandid());
			if(dc==null)
				return null;
			dto.setProvince(dc.getProvince());
			dto.setCity(dc.getCity());
			dto.setDistrict(dc.getDistrict());
		}
		
		String hql=" select d.agentId as agentid, d.weiId as weiid,d.province as province,d.city as city,d.district as district,"
				+ "d.contactPhone as mobile,d.outOrIn as type,d.createTime as applytime,d.brandId as brandid"
				+ " from DCastellans d where d.porN=2 and d.brandId=:brandid";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("brandid", dto.getBrandid());
		if(dto.getProvince()!=null && dto.getProvince()>0)
		{
			hql+=" and d.province=:province";
			map.put("province", dto.getProvince());
		}
		if(dto.getCity()!=null && dto.getCity()>0)
		{
			hql+=" and d.city=:city";
			map.put("city", dto.getCity());
		}
		if(dto.getDistrict()!=null && dto.getDistrict()>0)
		{
			hql+=" and d.district=:district";
			map.put("district", dto.getDistrict());
		}
		if(dto.getAgenttype()!=null && dto.getAgenttype()>0)
		{
			hql+=" and d.outOrIn=:agenttype";
			map.put("agenttype", dto.getAgenttype());
		}
		if(dto.getWeiid()!=null && dto.getWeiid()>0)
		{
			hql+=" and d.weiId=:weiid";
			map.put("weiid", dto.getWeiid());
		}
		PageResult<DeputyListVO> pr= baseDAO.findPageResultTransByMap(hql, DeputyListVO.class, limit, map);
		List<DeputyListVO> list=pr.getList();
		for(DeputyListVO dv:list)
		{
			if(dto.getIsbrand()==0)//城主
			{
				dv.setUpweiid(weiid);
				dv.setUpweiname(commonService.getShopNameByWeiId(weiid));
			}
			else//供应商
			{
				DCastellans dc=baseDAO.getUniqueResultByHql(" from DCastellans d where d.brandId=? and d.province=? and d.city=? and d.district=? and d.porN=1", dto.getBrandid(),dv.getProvince(),dv.getCity(),dv.getDistrict());
				
				if(dc!=null){
					dv.setUpweiid(dc.getWeiId());
					dv.setUpweiname(commonService.getShopNameByWeiId(dc.getWeiId()));
				}
			}
			dv.setBond(dv.getBond()==null?0.0:dv.getBond());
			dv.setShowapplytime(DateUtils.dateToString(dv.getApplytime(), "yyyy-MM-dd"));
			DAgentInfo di = baseDAO.get(DAgentInfo.class, dv.getAgentid());
			if(di!=null)
			{
				dv.setBond(di.getCosts());
				dv.setQq(di.getQq());
			}
			dv.setShopname(commonService.getShopNameByWeiId(dv.getWeiid()));
			dv.setShowarea(regionalService.getNameByCode(dv.getProvince())+"-"+regionalService.getNameByCode(dv.getCity())+"-"+regionalService.getNameByCode(dv.getDistrict()));
			
		}
		return pr;
	}
	@Override
	public List<AgentBrandNameVO> getAgentBrandNameList(long weiid) {
		List<DCastellans> list = baseDAO.find(" from DCastellans d where d.status=1 and d.porN=1 and d.weiId=?", weiid);
		if(list==null || list.size()<=0)
			return null;
		List<AgentBrandNameVO> lists= new ArrayList<AgentBrandNameVO>();
		for(DCastellans dc : list)
		{
			AgentBrandNameVO bn = new AgentBrandNameVO();
			bn.setBrandid(dc.getBrandId());
			DBrands db=baseDAO.get(DBrands.class,dc.getBrandId());
			bn.setName(db.getBrandName());
			lists.add(bn);
		}
		return lists;
	}
	@Override
	public PageResult<DeputyListVO> getAgentList(long weiid, AgentDTO dto,
			Limit limit) {
		if(dto.getIsbrand()==0)//城主
		{
			DCastellans dc=baseDAO.getUniqueResultByHql(" from DCastellans d where d.weiId=? and d.brandId=?", weiid,dto.getBrandid());
			if(dc==null)
				return null;
			dto.setProvince(dc.getProvince());
			dto.setCity(dc.getCity());
			dto.setDistrict(dc.getDistrict());
		}
		else
		{
			DBrandSupplier ds= baseDAO.getUniqueResultByHql(" from DBrandSupplier d where d.weiId=? and d.type=1", weiid);
			if(ds!=null)
				dto.setBrandid(ds.getBrandId());
		}
		String hql=" select d.agentId as agentid, d.weiId as weiid,d.province as province,d.city as city,d.district as district,"
				+ "d.contactPhone as mobile,d.createTime as applytime,d.brandId as brandid,d.superWeiid as upweiid"
				+ " from DAgentInfo d where  d.brandId=:brandid";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("brandid", dto.getBrandid());
		if(dto.getProvince()!=null && dto.getProvince()>0)
		{
			hql+=" and d.province=:province";
			map.put("province", dto.getProvince());
		}
		if(dto.getCity()!=null && dto.getCity()>0)
		{
			hql+=" and d.city=:city";
			map.put("city", dto.getCity());
		}
		if(dto.getDistrict()!=null && dto.getDistrict()>0)
		{
			hql+=" and d.district=:district";
			map.put("district", dto.getDistrict());
		}
		
		if(dto.getWeiid()!=null && dto.getWeiid()>0)
		{
			hql+=" and d.weiId=:weiid";
			map.put("weiid", dto.getWeiid());
		}
		PageResult<DeputyListVO> pr= baseDAO.findPageResultTransByMap(hql, DeputyListVO.class, limit, map);
		List<DeputyListVO> list=pr.getList();
		for(DeputyListVO dv:list)
		{
			if(dv.getUpweiid()!=null)
			{
				dv.setUpweiname(commonService.getShopNameByWeiId(dv.getUpweiid()));
			}
			else
			{
				DCastellans dc=baseDAO.getUniqueResultByHql(" from DCastellans d where d.brandId=? and d.province=? and d.city=? and d.district=? and d.porN=1", dto.getBrandid(),dv.getProvince(),dv.getCity(),dv.getDistrict());
			
				if(dc!=null){
					dv.setUpweiid(dc.getWeiId());
					dv.setUpweiname(commonService.getShopNameByWeiId(dc.getWeiId()));
				}
			}
			dv.setBond(dv.getBond()==null?0.0:dv.getBond());
			dv.setShowapplytime(DateUtils.dateToString(dv.getApplytime(), "yyyy-MM-dd"));
			DAgentInfo di = baseDAO.get(DAgentInfo.class, dv.getAgentid());
			if(di!=null)
			{
				dv.setBond(di.getCosts());
				dv.setQq(di.getQq());
			}
			dv.setShopname(commonService.getShopNameByWeiId(dv.getWeiid()));
			if(dv.getProvince()!=null && dv.getCity() !=null && dv.getDistrict()!=null)
				dv.setShowarea(regionalService.getNameByCode(dv.getProvince())+"-"+regionalService.getNameByCode(dv.getCity())+"-"+regionalService.getNameByCode(dv.getDistrict()));
			
		}
		return pr;
	}
	
	public PageResult<DeputyListVO> find_AgentList(long weiid, AgentDTO dto,Limit limit) {
		PageResult<DAgentInfo> agentPage= agentService.find_DAgents(weiid, dto.getWeiid(), limit.getPageId(), 10);
		List<DeputyListVO> list=new ArrayList<DeputyListVO>();
		if(agentPage!=null&&agentPage.getList()!=null&&agentPage.getList().size()>0){
			for (DAgentInfo dd : agentPage.getList()) {
				DeputyListVO vo=new DeputyListVO();
				vo.setAgentid(dd.getAgentId());
				vo.setBond(dd.getCosts());
				vo.setMobile(dd.getContactPhone());
				vo.setQq(dd.getQq());
				vo.setLevel(dd.getLevel());
				vo.setApplytime(dd.getCreateTime());
				vo.setWeiid(dd.getWeiId()); 
				vo.setShopname(commonService.getShopNameByWeiId(dd.getWeiId())); 
				vo.setShowapplytime(DateUtils.dateToString(dd.getCreateTime(), "yyyy-MM-dd"));
				vo.setUpweiid(dd.getSuperWeiid());
				vo.setUpweiname(commonService.getShopNameByWeiId(dd.getSuperWeiid())); 
				list.add(vo);
			}
		}
		return new PageResult<DeputyListVO>(agentPage.getTotalCount(), limit, list); 
	}
	
	@Override
	public List<MyAgentBrandVO> getMyAgentBrandList(long weiid) {
		List<DAgentInfo> list=baseDAO.find(" from DAgentInfo d where d.weiId=? and d.status=1", weiid);
		if(list==null || list.size()<=0)
			return null;
		List<MyAgentBrandVO> li= new ArrayList<MyAgentBrandVO>();
		for(DAgentInfo di : list)
		{
			MyAgentBrandVO av= new MyAgentBrandVO();
			av.setApplytime(di.getCreateTime());
			av.setShowapplytime(DateUtils.dateToString(di.getCreateTime(), "yyyy-MM-dd"));
			av.setBond(di.getCosts()==null?0.0:di.getCosts());
			av.setBrandid(di.getBrandId());
			av.setCity(di.getCity());
			av.setDistrict(di.getDistrict());
			av.setProvince(di.getProvince());
			if(di.getProvince()!=null && di.getCity()!=null && di.getDistrict()!=null)
				av.setShowarea(regionalService.getNameByCode(di.getProvince())+"-"+regionalService.getNameByCode(di.getCity())+"-"+regionalService.getNameByCode(di.getDistrict()));
			av.setWeiid(weiid);
			DBrands db=baseDAO.get(DBrands.class, di.getBrandId());
			av.setBrandname(db.getBrandName());
			DCastellans dc=baseDAO.getUniqueResultByHql(" from DCastellans d where d.weiId=? and d.brandId=?", weiid,di.getBrandId());
			if(dc==null)
			{
				av.setAgenttype("代理");
			}
			else
			{
				if(dc.getPorN()==1)
					av.setAgenttype("城主");
				else
					av.setAgenttype("城主候选人");
			}
			li.add(av);
		}
		return li;
	}
	
	
	
	public List<MyAgentBrandVO> find_MyAgentBrandList(long weiid) {
		List<DAgentApply> list=baseDAO.find(" from DAgentApply d where d.weiId=? ", weiid);
		if(list==null || list.size()<=0)
			return null;
		List<MyAgentBrandVO> li= new ArrayList<MyAgentBrandVO>();
		for(DAgentApply di : list)
		{
			DBrands db=baseDAO.get(DBrands.class, di.getBrandId());
			if(db!=null){
				MyAgentBrandVO av= new MyAgentBrandVO();
				av.setBrandname(db.getBrandName());
				av.setApplytime(di.getCreateTime());
				av.setShowapplytime(DateUtils.dateToString(di.getCreateTime(), "yyyy-MM-dd"));
				av.setBond(di.getCosts()==null?0.0:di.getCosts());
				av.setBrandid(di.getBrandId());
				av.setLevel(di.getLevel()); 
				switch (di.getLevel()==null?3:di.getLevel()) {
				case 1:
					av.setAgenttype("一级代理");
					break;
				case 2:
					av.setAgenttype("二级代理");
					break;
				case 3:
					av.setAgenttype("三级代理");
					break;
				default:
					break;
				}
				av.setWeiid(weiid);
				av.setStatus(di.getStatus()==null?-9:di.getStatus()); 
				av.setPayOrderId(agentService.getDAgentInfoPayOrderId(di.getWeiId(), di.getBrandId())); 
				li.add(av);	
			}
		}
		return li;
	}

}
