package com.okwei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.BChildrenPower;
import com.okwei.bean.domain.BFunModel;
import com.okwei.bean.domain.BFunPort;
import com.okwei.bean.domain.BModelName;
import com.okwei.bean.domain.BRolePower;
import com.okwei.bean.domain.BUserRole;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.service.IPowerService;
import com.okwei.util.RedisUtil;

@Service
public class PowerService  extends BaseService implements IPowerService {
	
	@Autowired
    private BaseDAO baseDAO;
	@Override
	public boolean checkPowerByModelTypeAndWeiid(Long weiid, String type,
			String funcode) {
		List<String> li=getPowerByWeiID(weiid);
		String power=type+"_"+funcode;
		if(li.contains(power))
			return true;
		return false;
	}

	public void getDataToRedis()
	{
		List<BRolePower> lirp=baseDAO.loadAll(BRolePower.class);
		if(lirp!=null && lirp.size()>0)
		{
			List<String> plist= new ArrayList<String>();
			for(BRolePower rp: lirp)
			{
				String[] funidset=rp.getFunIdset().split(",");
				Integer[] fset= new Integer[funidset.length];
				for(int i=0;i<funidset.length;i++)
				{
					fset[i]=Integer.parseInt(funidset[i]);
				}
				String strHql=" from BFunModel p where p.funId in (:funid)";
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("funid", fset);
				List<BFunModel> listfm=baseDAO.findByMap(strHql, map);
				if(listfm==null || listfm.size()<=0)
					continue;
				String strPortid="";
				for(BFunModel fm:listfm)
				{
					if("".equals(strPortid))
					{
						strPortid=fm.getFunCodeSet();
					}
					else
					{
						if(!"".equals(fm.getFunCodeSet()))
							strPortid=strPortid+","+fm.getFunCodeSet();
					}
				}
				String[] portset=strPortid.split(",");
				Integer[] pset = new Integer[portset.length];
				for(int i=0;i<portset.length;i++)
				{
					pset[i]=Integer.parseInt(portset[i]);
				}
				strHql=" from BFunPort p where p.portId in (:portid)";
				Map<String,Object> map2= new HashMap<String,Object>();
				map2.put("portid", pset);
				List<BFunPort> listfp= baseDAO.findByMap(strHql, map2);
				if(listfp ==null || listfp.size()<=0)
					continue;
				List<String> rolepower= new ArrayList<String>();
				for(BFunPort fp:listfp)
				{
					BModelName mn= baseDAO.get(BModelName.class, fp.getModelId());
					rolepower.add(mn.getModelCode()+"_"+fp.getPortCode());
				}
				String strRoleid="Role_"+rp.getRoleId();
				RedisUtil.setObject(strRoleid, rolepower);
				continue;							
			}
		}
	}
	
	private String getUserRoleSet(Integer id)
	{
		String strRole="";
		//普通微店主
		 if((id & Integer.parseInt(UserIdentityType.commonUser.toString())) > 0)
		 {
			 if("".equals(strRole))
			 {
				 strRole="1";
			 }
			 else
			 {
				 strRole+=",1";
			 }
		 }
		/**
		 *  云商通 供应商
		 */
		 if((id & Integer.parseInt(UserIdentityType.yunSupplier.toString())) > 0)
		 {
			 if("".equals(strRole))
			 {
				 strRole="2";
			 }
			 else
			 {
				 strRole+=",2";
			 }
		 }
		/**
		 * 批发号 供应商
		 */
		if((id & Integer.parseInt(UserIdentityType.batchSupplier.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="3";
			 }
			 else
			 {
				 strRole+=",3";
			 }
		 }
		/**
		 * Erp供应商
		 */
		if((id & Integer.parseInt(UserIdentityType.erpSupplier.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="13";
			 }
			 else
			 {
				 strRole+=",13";
			 }
		 }
		/**
		 * 工厂号 见习认证员
		 */
		if((id & Integer.parseInt(UserIdentityType.ordinary.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="10";
			 }
			 else
			 {
				 strRole+=",10";
			 }
		 }
		/**
		 * 工厂号 正式认证员
		 */
		if((id & Integer.parseInt(UserIdentityType.percent.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="9";
			 }
			 else
			 {
				 strRole+=",9";
			 }
		 }
		/**
		 * 批发号认证员
		 */
		if((id & Integer.parseInt(UserIdentityType.batchverifier.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="9";
			 }
			 else
			 {
				 strRole+=",9";
			 }
		 }
		/**
		 * 认证点
		 */
		if((id & Integer.parseInt(UserIdentityType.batchverifierport.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="11";
			 }
			 else
			 {
				 strRole+=",11";
			 }
		 }
		/**
		 * 市场管理员
		 */
		if((id & Integer.parseInt(UserIdentityType.market.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="12";
			 }
			 else
			 {
				 strRole+=",12";
			 }
		 }
		/**
		 * 平台号供应商
		 */
		if((id & Integer.parseInt(UserIdentityType.PlatformSupplier.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="4";
			 }
			 else
			 {
				 strRole+=",4";
			 }
		 }
		/**
		 * 品牌号供应商
		 */
		if((id & Integer.parseInt(UserIdentityType.BrandSupplier.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="5";
			 }
			 else
			 {
				 strRole+=",5";
			 }
		 }
		/**
		 * 平台号品牌号代理商
		 */
		if((id & Integer.parseInt(UserIdentityType.Agent.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="7";
			 }
			 else
			 {
				 strRole+=",7";
			 }
		 }
		/**
		 * 落地店
		 */
		if((id & Integer.parseInt(UserIdentityType.Ground.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="6";
			 }
			 else
			 {
				 strRole+=",6";
			 }
		 }
		/**
		 * 认证员代理商
		 */
		if((id & Integer.parseInt(UserIdentityType.VerifierAgent.toString())) > 0)
		 {
			if("".equals(strRole))
			 {
				strRole="8";
			 }
			 else
			 {
				 strRole+=",8";
			 }
		 }
		return strRole;
	}
	/**
	 * 根据用户获取所有的权限列表
	 */
	@Override
	public List<String> getPowerByWeiID(Long weiid) {
		
		
		List<String> returnList= new ArrayList<String>();
		String strRole="";
		UUserAssist ua=baseDAO.get(UUserAssist.class, weiid);
		if(ua != null && ua.getIdentity()!=null)
		{
			strRole=getUserRoleSet(ua.getIdentity());
		}
		
		if("".equals(strRole))//微店主角色
		{
			String strHql=" from BRolePower p where p.roleCode=?";
			Object[] b= new Object[1];
			b[0]="WeiMan";
			BRolePower rp= baseDAO.getUniqueResultByHql(strHql, b);
			if(rp!=null)
			{
				String roletiket="Role_"+rp.getRoleId();
				List<String> lists=(List<String>)RedisUtil.getObject(roletiket);
				if(lists == null || lists.size()<=0)//没有做缓存，先做缓存
				{
					//加载角色对应的权限逻辑
					getDataToRedis();
				}
				//再试获取Redis内容；
				if(lists == null || lists.size()<=0)
					lists=(List<String>)RedisUtil.getObject(roletiket);
				if(lists != null && lists.size()>0)
					returnList.addAll(lists);
			}
		}
		else
		{
			String[] roles=strRole.split(",");
			for(String role :roles)
			{
				String roletiket="Role_"+role;
				List<String> lists=(List<String>)RedisUtil.getObject(roletiket);
				if(lists == null || lists.size()<=0)//没有做缓存，先做缓存
				{
					//加载角色对应的权限逻辑
					getDataToRedis();
				}
				//再试获取Redis内容；
				if(lists == null || lists.size()<=0)
					lists=(List<String>)RedisUtil.getObject(roletiket);
				if(lists != null && lists.size()>0)
				{
					//returnList.addAll(lists);	
					for(String str:lists)//要去重复
					{
						if(!returnList.contains(str))
							returnList.add(str);
					}
				}				
			}
		}
		//特权模块
		BUserRole ur=baseDAO.get(BUserRole.class, weiid);	
		if(ur!=null && ur.getFunIdset() != null)
		{
			String[] funs=ur.getFunIdset().split(",");
			if(funs.length>0)
			{
				Integer[] fset= new Integer[funs.length];
				for(int i=0;i<funs.length;i++)
				{
					fset[i]=Integer.parseInt(funs[i]);
				}
				String strHql=" from BFunPort p where p.portId in (:portid)";
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("portid", fset); 
				List<BFunPort> listfp= baseDAO.findByMap(strHql, map);
				for(BFunPort fp:listfp)
				{
					BModelName mn= baseDAO.get(BModelName.class, fp.getModelId());
					if(returnList.contains(mn.getModelCode()+"_"+fp.getPortCode())) //并集
						continue;
					returnList.add(mn.getModelCode()+"_"+fp.getPortCode());
				}
			}
		}
		return returnList;
	}

	/**
	 * 根据子账号获取子账号权限
	 */
	@Override
	public List<String> getPowerByChildrenID(String weiid) {
		
		String[] man=weiid.split("_");
		if(man.length<2)//异常数据
			return null;
		BChildrenPower cp=baseDAO.get(BChildrenPower.class, weiid);
		if(cp==null)//默认返回全部权限
		{
			List<String> li=getPowerByWeiID(Long.parseLong(man[0]));
			return li;
		}
		List<String> returnList= new ArrayList<String>();
		
		List<String> li=getPowerByWeiID(cp.getParentId());
		String[] funs=cp.getFunIdset().split(",");
		Integer[] fset= new Integer[funs.length];
		for(int i=0;i<funs.length;i++)
		{
			fset[i]=Integer.parseInt(funs[i]);
		}
		String strHql=" from BFunPort p where p.portId in (:portid)";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("portid", fset); 
		List<BFunPort> listfp= baseDAO.findByMap(strHql, map);
		for(BFunPort fp:listfp)
		{
			BModelName mn= baseDAO.get(BModelName.class, fp.getModelId());
			returnList.add(mn.getModelCode()+"_"+fp.getPortCode());
		}
		returnList.retainAll(li); //获取两个list的交集
		return returnList;
	}
	

}
