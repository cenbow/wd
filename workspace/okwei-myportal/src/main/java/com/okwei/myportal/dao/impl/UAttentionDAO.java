package com.okwei.myportal.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.bean.dto.FansDTO;
import com.okwei.myportal.bean.dto.RelationDTO;
import com.okwei.myportal.bean.vo.RelationVO;
import com.okwei.myportal.dao.IUAttentionDAO;

@Repository
public class UAttentionDAO extends BaseDAO implements IUAttentionDAO {

	@Override
	public List<UAttention> getUAttentions(long weiId) {
		String hql = "FROM UAttention WHERE status = 1 AND attentioner = ? ";
		return super.find(hql, weiId);
	}

	@Override
	public List<UAttentioned> getUAttentioneds(long weiId) {
		String hql = "FROM UAttentioned WHERE status = 1 AND attTo = ? ";
		return super.find(hql, weiId);
	}

	@Override
	public List<UWeiSeller> getLower(long weiId) {
		String hql = "FROM UWeiSeller WHERE states = 1 AND sponsorWeiId = ? ";
		return super.find(hql, weiId);
	}

	@Override
	public PageResult<RelationVO> getSupplyer(RelationDTO dto, List<Long> weiIds, Limit limit) {
		// 过滤否供应商的逻辑待定
		StringBuilder sb = new StringBuilder(
				"select t.weiId, t.weiName, t.images, t.province, t.city, t.district, t.qq, t.bmId,t2.name bmName FROM ("
				+ "SELECT a.WeiID as weiId,CASE WHEN d.ShopName IS NOT NULL AND d.ShopName != '' THEN d.ShopName "
				+ "WHEN b.ShopName IS NOT NULL AND b.ShopName != '' THEN b.ShopName "
				+ "WHEN f.CompanyName IS NOT NULL AND f.CompanyName != '' THEN f.CompanyName "
				+ "ELSE a.WeiName END AS weiName,a.Images as images,a.Province as province,a.City as city,a.District as district,a.QQ as qq,b.BMID as bmId "
						+ "FROM U_WeiSeller a LEFT JOIN U_ShopInfo d ON d.WeiID=a.WeiID "
						+ "LEFT JOIN U_Supplyer f ON f.WeiID=a.WeiID "
						+ "JOIN U_Supplyer c ON a.WeiID = c.WeiID AND c.Type > 0 ");
		// +
		// "from U_WeiSeller a LEFT JOIN U_BatchSupplyer b ON a.WeiID=b.WeiID where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != weiIds && weiIds.size() > 0) {
			sb.append(" AND a.WeiID IN (:weiIds)");
			params.put("weiIds", weiIds);
		}
		if (null != dto) {
			if (null != dto.getWeiId()) {
				sb.append(" AND a.WeiID = :weiId");
				params.put("weiId", dto.getWeiId());
			}
			if (null != dto.getProvince() && dto.getProvince() > 0) {
				sb.append(" AND a.Province = :province");
				params.put("province", dto.getProvince());
			}
			if (null != dto.getCity() && dto.getCity() > 0) {
				sb.append(" AND a.City = :city");
				params.put("city", dto.getCity());
			}
			if (null != dto.getDistrict() && dto.getDistrict() > 0) {
				sb.append(" AND a.District = :district");
				params.put("district", dto.getDistrict());
			}

			sb.append(" LEFT JOIN U_BatchSupplyer b ON a.WeiID = b.WeiID");
			/*if (null != dto.getBmName()) {
				// 页面输入批发市场进行模糊搜索
				List<TBatchMarket> markets = super.find("FROM TBatchMarket WHERE status = 1 AND name LIKE ?", "%" + dto.getBmName() + "%");
				if (null != markets && markets.size() > 0) {
					List<Integer> marketIds = new ArrayList<Integer>();
					for (TBatchMarket tbm : markets) {
						marketIds.add(tbm.getBmid());
					}
					sb.append(" WHERE b.BMID IN(:bmIds)");
					params.put("bmIds", marketIds);
				} else {
					// 当没有相应的批发市场时，返回空
					return new PageResult<RelationVO>(0, limit, new ArrayList<RelationVO>());
				}
			}*/
		}
		sb.append(" ) t LEFT JOIN T_BatchMarket t2 ON t2.BMID=t.bmId where 1=1");
		if (null != dto) {
			if (null != dto.getWeiName()) {
				sb.append(" and t.weiName LIKE :weiName");
				params.put("weiName", "%" + dto.getWeiName() + "%");
			}
			if (null != dto.getBmName()) {
				sb.append(" and t2.name LIKE :bmName");
				params.put("bmName", "%" + dto.getBmName() + "%");
			}
		}
		return super.queryPageResultByMap(sb.toString(), RelationVO.class, limit, params);
	}

	@Override
	public PageResult<UWeiSeller> getDistributor(RelationDTO dto, List<Long> weiIds, Limit limit) {
		StringBuilder sb = new StringBuilder("from UWeiSeller where states = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != weiIds && weiIds.size() > 0) {
			sb.append(" and weiId in (:weiIds)");
			params.put("weiIds", weiIds);
		}
		if (null != dto) {
			if (null != dto.getWeiId()) {
				sb.append(" and weiId = :weiId");
				params.put("weiId", dto.getWeiId());
			}
			if (null != dto.getWeiName()) {
				sb.append(" and weiName like :weiName");
				params.put("weiName", "%" + dto.getWeiName() + "%");
			}
			if (null != dto.getFromTime()) {
				sb.append(" and registerTime >= :fromTime");
				params.put("fromTime", dto.getFromTime());
			}
			if (null != dto.getToTime()) {
				sb.append(" and registerTime < :toTime");
				params.put("toTime", DateUtils.addDays(dto.getToTime(), 1));
			}
		}
		sb.append(" order by registerTime desc");
		return super.findPageResultByMap(sb.toString(), limit, params);
	}

	@Override
	public PageResult<TFansApply> getFans(FansDTO dto,Limit buildLimit) {
		PageResult<TFansApply> list=new PageResult<TFansApply>();
		if(dto!=null){
			if(dto.getWeiId()!=null&&dto.getWeiId()>0){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("weiId",dto.getWeiId());
				list=super.findPageResultByMap("from TFansApply where fansId like:weiId", buildLimit,params);
//				list=super.findPageResult("from TFansApply where fansId like:%?%", buildLimit,dto.getWeiId());
//				list=super.queryPageResultByMap("select * from T_FansApply where FansID like=:weiId",TFansApply.class,buildLimit,params);
				return list;
			}
			if(dto.getStatu()==null){
				list=super.findPageResult("from TFansApply where status=?", buildLimit,0);
				return list;
			}
			switch (dto.getStatu()) {
			case 0:
				list=super.findPageResult("from TFansApply where status=?", buildLimit,0);
				break;

			case 1:
				list=super.findPageResult("from TFansApply where status=?", buildLimit,1);
				break;
				
			case 2:
				list= super.findPageResult("from TFansApply where status=?", buildLimit,2);
				break;
			}
			
		}else{
			list=super.findPageResult("from TFansApply where status=?", buildLimit,0);
			return list;
		}
		return list;
	}

	@Override
	public void changestatues(Short state,Long weiId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("status", state);
//		params.put("weiId", weiId);
		super.update("update TFansApply set status=? where fansId=?",(int)state, weiId);
	}

}
