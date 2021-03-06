package com.okwei.myportal.service;

import java.util.List;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.AgentDTO;
import com.okwei.myportal.bean.dto.DukeDTO;
import com.okwei.myportal.bean.dto.DupetyDTO;
import com.okwei.myportal.bean.vo.AgentBrandNameVO;
import com.okwei.myportal.bean.vo.DeputyListVO;
import com.okwei.myportal.bean.vo.DukeListVO;
import com.okwei.myportal.bean.vo.MyAgentBrandVO;

public interface IAgentTeamService {
	
	//获取城主列表
	public PageResult<DukeListVO> getDukeList(long weiID,DukeDTO dto,Limit limit);
	
	//获取副城主页面
	public PageResult<DeputyListVO> getDeputyList(long weiid,DupetyDTO dto,Limit limit);
	
	//获取代理页面
	public PageResult<DeputyListVO> getAgentList(long weiid,AgentDTO dto,Limit limit);
	/**
	 * 品牌商的 代理列表
	 * @param weiid
	 * @param dto
	 * @param limit
	 * @return
	 */
	public PageResult<DeputyListVO> find_AgentList(long weiid, AgentDTO dto,Limit limit);
	
	public List<MyAgentBrandVO> getMyAgentBrandList(long weiid);
	
	/**
	 * 获取我的代理品牌列表
	 * @param weiid
	 * @return
	 */
	public List<MyAgentBrandVO> find_MyAgentBrandList(long weiid);
	
	//获取代言品牌
	public List<AgentBrandNameVO> getAgentBrandNameList(long weiid);
	
}
