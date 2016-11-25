package com.okwei.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.enums.FansStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IFansBasicService;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

@Service
public class FansBasicService extends BaseService implements IFansBasicService{
	@Autowired
	private IBaseDAO baseDAO;
	
	public List<TRegional> find_FansCityslist(){
		List<Object[]> citys= baseDAO.queryBySql(" SELECT City,COUNT(City) from T_FansApply where Status=? GROUP BY City  ", Integer.parseInt(FansStatusEnum.Pass.toString()));
		if(citys!=null&&citys.size()>0){
			List<TRegional> result=new ArrayList<TRegional>();
			for (Object[] oo : citys) {
				TRegional cc= baseDAO.get(TRegional.class, ParseHelper.toInt(String.valueOf(oo[0]))) ;
				if(cc!=null){
					cc.setParent(ParseHelper.toInt(String.valueOf(oo[1])));
					result.add(cc);
				}
			}
			return result;
		}
		return null;
	}
	
	/**
	 * 获取粉丝列表
	 * @param provinceCode
	 * @param cityCode
	 * @param districtCode
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<TFansApply> find_TFansResult(Integer provinceCode, Integer cityCode,Integer districtCode, int pageIndex,int pageSize)
	{
		StringBuilder sb=new StringBuilder();
		java.util.Map<String, Object> paramMap=new HashMap<String, Object>();
		sb.append(" from TFansApply t where t.status=:status ");
		paramMap.put("status", Integer.parseInt(FansStatusEnum.Pass.toString()));
		if(provinceCode!=null&&provinceCode>0){
			sb.append(" and t.province=:provice");
			paramMap.put("provice", provinceCode);
		}
		if(cityCode!=null&&cityCode>0){
			sb.append(" and t.city=:city");
			paramMap.put("city", cityCode);
		}
		if(districtCode!=null&&districtCode>0){
			sb.append(" and t.district=:districtCode");
			paramMap.put("districtCode", districtCode);
		}
		sb.append(" order by t.fansId desc ");
		return baseDAO.findPageResultByMap(sb.toString(), Limit.buildLimit(pageIndex, pageSize), paramMap);
	}
	
	
	public long count_fansAll(Integer status){
		if(status!=null){
			String keyString=" fansCount_status_"+status;
			long count=ParseHelper.toLong(String.valueOf(RedisUtil.getObject(keyString))) ; 
			if(count<=0){
				count=baseDAO.count(" select count(*) from TFansApply where status=? ", status);
				if(count>0){
					RedisUtil.setObject(keyString, count, 120); 
				}
			}
			return count;
		}else {
			return baseDAO.count(" select count(*) from TFansApply ");
		}
	}

}
