package com.okwei.myportal.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.agent.BrandAgentInfo;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.AgentDTO;
import com.okwei.myportal.bean.dto.DukeDTO;
import com.okwei.myportal.bean.dto.DupetyDTO;
import com.okwei.myportal.bean.vo.AgentBrandNameVO;
import com.okwei.myportal.bean.vo.DeputyListVO;
import com.okwei.myportal.bean.vo.DukeListVO;
import com.okwei.myportal.bean.vo.MyAgentBrandVO;
import com.okwei.myportal.service.IAgentTeamService;
import com.okwei.service.agent.IDAgentService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/teamMgt")
public class TeamMgtController extends SSOController
{
	 
	@Autowired
	private IAgentTeamService service;
	@Autowired
	private IDAgentService agentService;
	
    private final static Log logger = LogFactory.getLog(TeamMgtController.class);

    @RequestMapping(value = "/dukeList")
    public String dukeList(@ModelAttribute("queryParam") DukeDTO queryParam,@RequestParam(required = false,defaultValue = "1") int pageId,Model model)
    {
    	LoginUser user = super.getUserOrSub();
    	
    	PageResult<DukeListVO> page=service.getDukeList(user.getWeiID(), queryParam, Limit.buildLimit(pageId, 10));
    	model.addAttribute("dto", queryParam);
    	model.addAttribute("userinfo", user);
    	model.addAttribute("page", page);
        return "team/dukeList";
    }
    
    @RequestMapping(value = "/deputyList")
    public String deputyList(@ModelAttribute("queryParam") DupetyDTO dto,@RequestParam(required = false,defaultValue = "1") int pageId,Model model)
    {
    	LoginUser user = super.getUserOrSub();
    	if(user.getDuke()==1) //城主
    	{
    		dto.setIsbrand((short) 0);
	    	List<AgentBrandNameVO> li=service.getAgentBrandNameList(user.getWeiID());
	    	model.addAttribute("agentlist", li);
	    	if(dto.getBrandid()==null)//默认第一个选中
	    	{
	    		if(li!=null && li.size()>0)
	    		{
	    			AgentBrandNameVO bv=li.get(0);
	    			dto.setBrandid(bv.getBrandid());
	    		}
	    	}
    	}
    	else if(user.getBrandsupplyer()==1) //品牌号供应商
    	{
    		dto.setIsbrand((short) 1);
    	}
    	PageResult<DeputyListVO> page=service.getDeputyList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));
    	model.addAttribute("dto", dto);
    	model.addAttribute("userinfo", user);
    	model.addAttribute("page", page);
        return "team/deputyList";
    }

    @RequestMapping(value = "/agentList")
    public String agentList(@ModelAttribute("queryParam") AgentDTO dto,@RequestParam(required = false,defaultValue = "1") int pageId,Model model) throws MapperException
    {
    	LoginUser user = super.getUserOrSub();
    	
    	if(user.getBrandsupplyer()==1){//品牌商的代理列表
    		PageResult<DeputyListVO> agentPage= service.find_AgentList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));
        	model.addAttribute("dto", dto);
        	model.addAttribute("userinfo", user);
        	model.addAttribute("page", agentPage);
            return "team/agentList";
    	}else {//普通代理的代理关系列表
    		List<BrandAgentInfo> list= agentService.find_BrandAgents(user.getWeiID());
    		model.addAttribute("userinfo", user);
        	model.addAttribute("list", list);
			return "team/AgentBrands";
		}
//    	if(user.getDuke()==1 || user.getDeputyduke()==1) //城主
//    	{
//    		dto.setIsbrand((short) 0);
//	    	List<AgentBrandNameVO> li=service.getAgentBrandNameList(user.getWeiID());
//	    	model.addAttribute("agentlist", li);
//	    	if(dto.getBrandid()==null)//默认第一个选中
//	    	{
//	    		if(li!=null && li.size()>0)
//	    		{
//	    			AgentBrandNameVO bv=li.get(0);
//	    			dto.setBrandid(bv.getBrandid());
//	    		}
//	    	}
//    	}
//    	else if(user.getBrandsupplyer()==1) //品牌号供应商
//    	{
//    		dto.setIsbrand((short) 1);
//    	}
//    	PageResult<DeputyListVO> page=service.getAgentList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));

    	
    }
    
    
    @RequestMapping(value = "/myAgentList")
    public String myAgentList(Model model)
    {
    	LoginUser user = super.getUserOrSub();
    	
//    	PageResult<DeputyListVO> page=service.getAgentList(user.getWeiID(), dto, Limit.buildLimit(pageId, 10));
//    	List<MyAgentBrandVO> page=service.getMyAgentBrandList(user.getWeiID());
    	List<MyAgentBrandVO> page=service.find_MyAgentBrandList(user.getWeiID());
    	model.addAttribute("userinfo", user);
    	model.addAttribute("page", page);
        return "team/myAgentBrand";
    }
}
