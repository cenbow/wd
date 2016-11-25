package com.okwei.service.impl.activity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.ARecruitPartner;
import com.okwei.bean.dto.RecruitPartnerDTO;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IBaseSupplyDemandDAO;
import com.okwei.service.activity.IBaseRecruitPartnerService;
import com.okwei.service.impl.BaseService;

@Service
public class BaseRecruitPartnerServiceImpl extends BaseService implements IBaseRecruitPartnerService {

    //@Autowired
    //private IBaseDAO dao;
    @Autowired
    private IBaseSupplyDemandDAO dao;
	
	@Override
	public boolean savePartner(RecruitPartnerDTO dto) {
		if(dto ==null){
			return false;
		}
		
		ARecruitPartner partner = new ARecruitPartner();
		partner.setAdvantage(dto.getAdvantage());
		partner.setCity(dto.getCity());
		partner.setClassType(dto.getClassType());
		partner.setCreateTime(new Date());
		partner.setName(dto.getName());
		partner.setProvince(dto.getProvince());
		partner.setReferee(dto.getReferee());
		partner.setPhone(dto.getPhone());
		dao.save(partner);
		return true;
	}

}
