package com.okwei.dao.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenCreate;
import com.okwei.bean.domain.UChildrenStaff;
import com.okwei.bean.domain.UChildrenSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.bean.enums.ChildrenAccountTypeEnum;
import com.okwei.common.Limit;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IChildrenAccountDAO;
@Repository
public class ChildrenAccountDAO extends BaseDAO implements IChildrenAccountDAO {

	@Override
	public List<Object[]> getChildrenSupplyerListPC(Long weiId,Limit limit,Integer status,Integer from) {
		String sql="select u.ChildrenID,u.PassWord,u.UserName,u.MobilePhone,s.CompanyName,s.isOrderSend,u.State,s.Address,s.Province,s.City,s.Area,s.Verifier "
				+ " from U_ChildrenUser u,U_ChildrenSupplyer s where u.ChildrenID=s.ChildrenID and u.ParentID=? and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenSupply.toString());
		if(status!=null&&status!=-1){
			sql+=" and u.State="+status+"";
		}
		
		if(from !=null)
		{
			if(from == 0)
				sql += " and s.Verifier is null";
			else if(from ==1)
				sql += " and s.Verifier is not null";
		}
		
		sql+=" order by u.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
		return super.queryBySql(sql,weiId);
	}
	
	@Override
	public List<Object[]> getChildrenSupplyerListByRecommand(Long weiId,
			Limit limit, Integer status) {
		String sql="select u.ChildrenID,u.UserName,u.MobilePhone,s.CompanyName,u.State,s.Address,s.Province,s.City,s.Area,u.ParentID,u.NoPassReason  "
				+ " from U_ChildrenUser u,U_ChildrenSupplyer s where u.ChildrenID=s.ChildrenID and s.Verifier=? and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenSupply.toString());
		if(status!=null&&status!=-1){
			sql+=" and u.State="+status+"";
		}
		sql+=" order by u.CreateTime LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
		return super.queryBySql(sql,weiId);
	}
	
	@Override
	public List<Object[]> getChildrenSupplyerList(Long weiId,Limit limit,Integer status,Short from) {
		String sql="select u.ChildrenID,s.CompanyName,u.State,u.MobilePhone,s.Verifier "
				+ " from U_ChildrenUser u,U_ChildrenSupplyer s where u.ChildrenID=s.ChildrenID and u.ParentID=? and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenSupply.toString());
		if(status!=null&&status!=-1){
			sql+=" and u.State="+status+"";
		}
		if(from !=null)
		{
			if(from == 0)
				sql += " and s.Verifier is null";
			else if(from ==1)
				sql += " and s.Verifier is not null";
		}
		sql+=" order by u.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
		return super.queryBySql(sql,weiId);
	}
	
	@Override
	public List<Object[]> getChildrenStaffList(Long weiId,Limit limit) {
		String sql="select u.ChildrenID,u.UserName,s.Department,u.State,u.MobilePhone "
				+ " from U_ChildrenUser u,U_ChildrenStaff s where u.ChildrenID=s.ChildrenID and u.ParentID=? and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenStaff.toString());
		sql+=" order by u.CreateTime desc LIMIT "+(limit.getPageId()-1)*limit.getSize()+","+limit.getSize();
		return super.queryBySql(sql,weiId);
	}

	@Override
	public long getChildrenSupplyerTotalAmount(Long weiId,Integer status,Integer from) {
		if(weiId!=null&&weiId<1){
			return 0;
		}
		//通过type来区分是子账号供应商还是子账号员工
		String sql="select count(*) from U_ChildrenUser u,U_ChildrenSupplyer s where u.ChildrenID=s.ChildrenID and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenSupply.toString())+" and u.ParentID=?";
		if(status!=null&&status!=-1){
			sql+=" and u.State="+status+"";
		}
		
		if(from !=null)
		{
			if(from == 0)
				sql += " and s.Verifier is null";
			else if(from ==1)
				sql += " and s.Verifier is not null";
		}
		return super.countBySql(sql,weiId);
	}

	@Override
	public long getChildrenSupplyerTotalAmountByRecommand(Long weiId,
			Integer status) {
		if(weiId!=null&&weiId<1){
			return 0;
		}
		//通过type来区分是子账号供应商还是子账号员工
		String sql="select count(*) from U_ChildrenUser u,U_ChildrenSupplyer s where u.ChildrenID=s.ChildrenID and u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenSupply.toString())+" and s.Verifier=?";
		if(status!=null&&status!=-1){
			sql+=" and u.State="+status+"";
		}
		return super.countBySql(sql,weiId);
	}
	
	@Override
	public long getChildrenStaffTotalAmount(Long weiId) {
		if(weiId!=null&&weiId<1){
			return 0;
		}
		//通过type来区分是子账号供应商还是子账号员工
		String sql="select count(*) from U_ChildrenUser u where u.State!=0 and u.Type="+Integer.valueOf(ChildrenAccountTypeEnum.childrenStaff.toString())+" and u.ParentID=?";
		return super.countBySql(sql,weiId);
	}

	@Override
	public void addChildrenUser(UChildrenUser param) {
		super.save(param);
	}

	@Override
	public void addChildrenSupplyerInfo(UChildrenSupplyer param) {
		super.save(param);
	}

	@Override
	public void addChildrenStaffInfo(UChildrenStaff param) {
		super.save(param);
	}

	@Override
	public UChildrenCreate getUChildrenCreate(Long weiId) {
		return super.get(UChildrenCreate.class,weiId);
	}

	@Override
	public void addChildrenCreate(UChildrenCreate param) {
		super.save(param);
	}

	@Override
	public UChildrenUser getUChildrenUser(String childrenId) {
		return super.get(UChildrenUser.class,childrenId);
	}

	@Override
	public UChildrenSupplyer getUChildrenSupplyer(String childrenId) {
		return super.get(UChildrenSupplyer.class,childrenId);
	}

	@Override
	public UChildrenStaff getUChildrenStaff(String childrenId) {
		return super.get(UChildrenStaff.class,childrenId);
	}

	@Override
	public void editUChildrenUser(AddChildrenAccountDTO param) {
		String hql="update UChildrenUser set passWord=?,userName=?,mobilePhone=? where childrenId=?";
		super.executeHql(hql, param.getPassWord(),param.getUserName(),param.getMobilePhone(),param.getChildrenId());
	}

	@Override
	public void editUChildrenSupplyer(String childrenId, String companyName,Short isOrderSend) {
		if(childrenId==null||childrenId.equals("")){
			return;
		}
		String hql="update UChildrenSupplyer set companyName=?,isOrderSend=? where childrenId=?";
		super.executeHql(hql, companyName,isOrderSend,childrenId);
	}

	@Override
	public void editUChildrenStaff(String childrenId, String department) {
		if(childrenId==null||childrenId.equals("")){
			return;
		}
		String hql="update UChildrenStaff set department=? where childrenId=?";
		super.executeHql(hql, department,childrenId);
	}

	@Override
	public void deleteUChildrenUser(String childrenId) {
		if(childrenId==null||childrenId.equals("")){
			return;
		}
		//删除为逻辑删除
		String hql="update UChildrenUser set state=0 where childrenId=?";
		super.executeHql(hql,childrenId);
	}
	

	@Override
	public void verifyUChildrenUser(VerifySupplierDTO param) {
		// TODO 自动生成的方法存根
		if(param.getAccountId()==null||param.getAccountId().equals("") || param.getIsPass()==null){
			return;
		}
		
		if(param.getIsPass()!=3 &&param.getIsPass()!=4)
			return;
		//删除为逻辑删除
		String hql="update UChildrenUser set state=:state,noPassReason=:noPassReason";
		if(param.getIsPass() == 3 && !param.getPassword().equals(""))
			hql +=",passWord=:passWord";
		hql += " where childrenId=:childrenId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", param.getIsPass());
		map.put("noPassReason", param.getNotPassReson());
		if(param.getIsPass() == 3 && !param.getPassword().equals(""))
			map.put("passWord", param.getPassword());
		map.put("childrenId", param.getAccountId());
		super.executeHqlByMap(hql, map);
	}

	@Override
	public void batchVerifyChildrenUser(List<String> idList, Integer statu) {
		// TODO 自动生成的方法存根
		if(idList==null || idList.size()<1){
			return;
		}
		
		if(statu==null || (statu!=3&&statu!=4))
			return;
		//删除为逻辑删除
		String hql="update UChildrenUser set state=:state where childrenId in (:idList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", statu);
		map.put("idList",idList);
		super.executeHqlByMap(hql, map);
	}

	@Override
	public void deleteUChildrenSupplyer(String childrenId) {
		//删除为逻辑删除
		//String hql="delete from UChildrenSupplyer where childrenId=?";
		//super.executeHql(hql,childrenId);
	}

	@Override
	public void deleteUChildrenStaff(String childrenId) {
		//删除为逻辑删除
		//String hql="delete from UChildrenStaff where childrenId=?";
		//super.executeHql(hql,childrenId);
	}

	@Override
	public List<Object[]> getPublishProductCountByChildSupplyer(
			List<String> childIdList) {
		//state=1表示是发布的商品
		String hql = "select count(s.childrenID),s.childrenID from PProductSup s where s.childrenID in (:childIdList) and s.state=1 group by s.childrenID";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("childIdList", childIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<Object[]> getPendingProductCountByChildSupplyer(
			List<String> childIdList) {
		//state=-1表示是待审核的商品
		String hql = "select count(s.childrenID),s.childrenID from PProductSup s where s.childrenID in (:childIdList) and s.state=-1 group by s.childrenID";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("childIdList", childIdList);
		return super.findByMap(hql, map);
	}
	@Override
	public List<TRegional> getTRegionalByCode(Integer code) {
		if(code==null){
			return null;
		}
		String hql = "from TRegional where code=?";
		return super.find(hql, code);
	}

	@Override
	public void batchDeleteUChildrenUser(List<String> idList) {
		if(idList==null){
			return;
		}
		//删除为逻辑删除
		String hql="update UChildrenUser set state=0 where childrenId in (:idList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idList",idList);
		super.executeHqlByMap(hql, map);
	}

	@Override
	public List<UVerifier> getVerifierInfo(List<Long> weiIdList) {
		if(weiIdList==null || weiIdList.size()<1){
			return null;
		}
		String hql="from UVerifier where weiId in (:weiIdList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<UPlatformSupplyer> getPlatformSuppler(List<Long> supplerList) {
		if(supplerList==null || supplerList.size()<1)
		   return null;
		String hql = "from UPlatformSupplyer where weiId in (:supplerList)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("supplerList", supplerList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<UChildrenUser> getChildrenById(String[] childrenList) {
		// TODO 自动生成的方法存根
		if(childrenList == null || childrenList.length<0)
		   return null;
		String hql =" from UChildrenUser where childrenId in (:childrenId)";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("childrenId", childrenList);
		return super.findByMap(hql, map);
	}
	
	@Override
	public List<UChildrenSupplyer> getChildrenSupplyer(String[] childrenList) {
		// TODO 自动生成的方法存根
		if(childrenList == null || childrenList.length<0)
			   return null;
			String hql =" from UChildrenSupplyer where childrenId in (:childrenId)";
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("childrenId", childrenList);
			return super.findByMap(hql, map);
	}
	@Override
	public List<UPlatformSupplyer> getPlatformSuppler() {
		// TODO 自动生成的方法存根
		String hql =" from UPlatformSupplyer where state!=-1";
		return super.find(hql);
	}

	@Override
	public List<USupplyChannel> getAgentSupplierByID(Long weiid) {
		// TODO 自动生成的方法存根
		if(weiid==null || weiid<0)
		   return null;
		String hql = " from USupplyChannel where weiId=? and channelType=1";
		return super.find(hql, weiid);
	}

	@Override
	public UPlatformSupplyer getPSupplyerById(Long weiid) {
		// TODO 自动生成的方法存根
		if(weiid==null || weiid<0)
			   return null;
		return super.get(UPlatformSupplyer.class, weiid);
	}

	@Override
	public UBrandSupplyer getBSupplyerById(Long weiid) {
		// TODO 自动生成的方法存根
		if(weiid==null || weiid<0)
			   return null;
		return super.get(UBrandSupplyer.class, weiid);
	}

	


}
