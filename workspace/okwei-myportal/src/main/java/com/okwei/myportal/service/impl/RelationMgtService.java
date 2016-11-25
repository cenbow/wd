package com.okwei.myportal.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.FansDTO;
import com.okwei.myportal.bean.dto.RelationDTO;
import com.okwei.myportal.bean.vo.RelationVO;
import com.okwei.myportal.dao.IUAttentionDAO;
import com.okwei.myportal.service.IRelationMgtService;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ImgDomain;

@Service
public class RelationMgtService extends BaseService implements IRelationMgtService {

	@Autowired
	private IUAttentionDAO uAttentionDAO;

	@Autowired
	private IRegionService regionService;

	@Override
	public PageResult<RelationVO> getUpStream(RelationDTO dto, Limit limit) {
		PageResult<RelationVO> result = new PageResult<RelationVO>();
		UWeiSeller user = super.getById(UWeiSeller.class, dto.getUserId());
		// 上游供应包括：推荐人，关注的供应商
		// 推荐人weiId
		long sponsorWeiId = user.getSponsorWeiId();
		List<UAttention> attentions = uAttentionDAO.getUAttentions(dto.getUserId());
		// 关注的供应商的weiId
		List<Long> ids = new ArrayList<Long>();
		ids.add(sponsorWeiId);
		if (null != attentions) {
			for (UAttention u : attentions) {
				ids.add(u.getAttTo());
			}
		}
		if (ids.size() > 0) {
			result = uAttentionDAO.getSupplyer(dto, ids, limit);
			if (null != result.getList() && result.getList().size() > 0) {
				for (RelationVO vo : result.getList()) {
					// 判断当前供应商是什么身份: 批发号供应商/工厂号供应商
					USupplyer supplyer = super.getById(USupplyer.class, Long.valueOf(vo.getWeiId().toString()));
					if (null != supplyer) {
						Short type = supplyer.getType();
						Short[] YunSupplier = new Short[] { 2, 3, 6, 7, 10, 11, 14, 15 }; // 工厂号
						Short[] BatchSupplier = new Short[] { 4, 5, 6, 7, 12, 13, 14, 15 }; // 批发号
						if (Arrays.asList(YunSupplier).contains(type)) {// 工厂号
							vo.setIsYunSupplier((short) 1);
						}
						if (Arrays.asList(BatchSupplier).contains(type)) {// 批发号
							vo.setIsBatchSupplier((short) 1);
						} else {
							vo.setBmName("");// 如果已经退注了，不再是批发号供应商，页面不显示曾经的批发号市场名称
						}

						if (Arrays.asList(BatchSupplier).contains(type)) {// 省市区优先从批发号获取
							UBatchSupplyer batcher = super.getById(UBatchSupplyer.class, supplyer.getWeiId());
							if (null != batcher) {
								vo.setProvince(batcher.getProvince());
								vo.setCity(batcher.getCity());
								vo.setDistrict(batcher.getDistrict());
							}
						} else if (Arrays.asList(YunSupplier).contains(type)) {// 当批发号不存在，再从工厂号中获取省市区
							UYunSupplier ys = super.getById(UYunSupplier.class, supplyer.getWeiId());
							if (null != ys) {
								// vo.setQq(ys.getServiceQq());
								vo.setProvince(ys.getProvince());
								vo.setCity(ys.getCity());
								vo.setDistrict(ys.getDistrict());
							}
						}
					}

					// 设置省、市、区
					StringBuilder regionDesc = new StringBuilder();
					if (null != vo.getProvince()) {
						regionDesc.append(regionService.getNameByCode(vo.getProvince()==null?0:vo.getProvince()));
					}
					if (null != vo.getCity()) {
						regionDesc.append(regionService.getNameByCode(vo.getCity()==null?0:vo.getCity()));
					}
					if (null != vo.getDistrict()) {
						regionDesc.append(regionService.getNameByCode(vo.getDistrict()==null?0:vo.getDistrict()));
					}
					vo.setRegionDesc(regionDesc.toString());
					if (null != vo.getBmId()) {
						// 设置批发市场名称
						TBatchMarket market = super.getById(TBatchMarket.class, vo.getBmId());
						if (null != market) {
							vo.setBmName(market.getName());
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public PageResult<RelationVO> getDownStream(RelationDTO dto, Limit limit) {
		PageResult<UWeiSeller> result = new PageResult<UWeiSeller>();
		List<Long> ids = new ArrayList<Long>();
		// 我推荐注册的,也叫直接分销商
		switch (dto.getType()) {
		case distributor: {
			List<UWeiSeller> users = uAttentionDAO.getLower(dto.getUserId());
			if (null != users) {
				for (UWeiSeller user : users) {
					ids.add(user.getWeiId());
				}
			}
			break;
		}
		case attention: {
			// 关注我的微店号
			List<UAttentioned> attentioneds = uAttentionDAO.getUAttentioneds(dto.getUserId());
			if (null != attentioneds) {
				for (UAttentioned u : attentioneds) {
					ids.add(u.getAttentioner());
				}
			}
			break;
		}
		case all: {
			List<UWeiSeller> users = uAttentionDAO.getLower(dto.getUserId());
			if (null != users) {
				for (UWeiSeller user : users) {
					ids.add(user.getWeiId());
				}
			}
			// 关注我的微店号
			List<UAttentioned> attentioneds = uAttentionDAO.getUAttentioneds(dto.getUserId());
			if (null != attentioneds) {
				for (UAttentioned u : attentioneds) {
					ids.add(u.getAttentioner());
				}
			}
		}
		}

		if (ids.size() > 0) {
			result = uAttentionDAO.getDistributor(dto, ids, limit);
		}

		List<RelationVO> list = new ArrayList<RelationVO>();
		if (null != result && null != result.getList() && result.getList().size() > 0) {

			for (UWeiSeller seller : result.getList()) {
				RelationVO vo = new RelationVO();
				try {
					BeanUtils.copyProperties(vo, seller);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String img = getImageById(seller.getWeiId());
				String nickName = getNickNameById(seller.getWeiId());

				if (StringUtils.isEmpty(img)) { // 当不存在图片时，设置默认的图片
					img = "/images/logo.jpg";
				}
				vo.setImages(img);
				vo.setWeiName(nickName);
				list.add(vo);
			}
		}
		PageResult<RelationVO> pageResult = new PageResult<RelationVO>(result.getTotalCount(), limit, list);
		return pageResult;
	}

	private String getImageById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo = super.getById(UShopInfo.class, weiNo);
		if (shopInfo != null && shopInfo.getShopImg() != null && !"".equals(shopInfo.getShopImg()))
			return ImgDomain.GetFullImgUrl(shopInfo.getShopImg());
		UBatchSupplyer bsup = super.getById(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getShopPic() != null && !"".equals(bsup.getShopPic())) {
			return ImgDomain.GetFullImgUrl(bsup.getShopPic());
		}
		UWeiSeller seller = super.getById(UWeiSeller.class, weiNo);
		return seller == null ? "" : ImgDomain.GetFullImgUrl(seller.getImages());
	}

	private String getNickNameById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo = super.getById(UShopInfo.class, weiNo);
		if (shopInfo != null && shopInfo.getShopName() != null)
			return shopInfo.getShopName();
		UYunSupplier ysup = super.getById(UYunSupplier.class, weiNo);
		if (ysup != null && ysup.getStatus() != null && ysup.getStatus() == 4) {
			USupplyer sup = super.getById(USupplyer.class, weiNo);
			if (sup != null && !"".equals(sup.getCompanyName()))
				return sup.getCompanyName();
		}
		UBatchSupplyer bsup = super.getById(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getStatus() != null) { // &&
														// bsup.getStatus()==4
			return bsup.getShopName();
		}
		UWeiSeller seller = super.getById(UWeiSeller.class, weiNo);
		return seller == null ? "" : seller.getWeiName();
	}

	@Override
	public PageResult<TFansApply> getFans(FansDTO dto,Limit buildLimit) {
		PageResult<TFansApply> list=new PageResult<TFansApply>();
		list=uAttentionDAO.getFans(dto,buildLimit);
		return list;
	}

	@Override
	public ReturnModel changeStatus(Short state,Long weiId) {
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.SystemError);
		uAttentionDAO.changestatues(state,weiId);
		rm.setStatu(ReturnStatus.Success);
		return rm;
	}

}
