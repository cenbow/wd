package com.okwei.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.TRemind;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.vo.RegionalDataModel;
import com.okwei.bean.vo.RegionalModel;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.dao.ICommonDAO;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.service.IBasicCommonService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.OperateXML;

@Service
public class BasicCommonService extends BaseService implements IBasicCommonService {

    @Autowired
    private IBaseProductDao baseDao;

    @Autowired
    private ICommonDAO commonDao;

    @Override
    public boolean attentionSup(long userID, int type, long supID) {
	if (type == 1) {
	    // 先要判断是否天然上下级
	    if (userID < 1 || supID < 1) {
		return false;
	    }
	    UWeiSeller seller = baseDao.get(UWeiSeller.class, userID);
	    if (seller == null) {
		return false;
	    }
	    if (seller.getSponsorWeiId() == supID) {
		return false;
	    }
	    // 取消关注
	    Object[] params = new Object[2];
	    params[0] = userID;// 关注人
	    params[1] = supID;// 被关注人
	    baseDao.executeHql("delete from UAttention where attentioner=? and attTo=?", params);
	    baseDao.executeHql("delete from UAttentioned where attentioner=? and attTo=?", params);
	    return true;
	} else {
	    String hql = "from UAttention u where u.attentioner=? and u.attTo=?";
	    UAttention model = baseDao.getUniqueResultByHql(hql, userID, supID);
	    if (model != null) {
		return true;// 已经关注
	    }
	    // 关注
	    UAttention entity = new UAttention();
	    entity.setAttentioner(userID);
	    entity.setAttTo(supID);
	    entity.setStatus((short) 1);
	    entity.setCreateTime(new Date());
	    baseDao.save(entity);
	    UAttentioned entity1 = new UAttentioned();
	    entity1.setAttentioner(userID);
	    entity1.setAttTo(supID);
	    entity1.setStatus((short) 1);
	    entity1.setCreateTime(new Date());
	    baseDao.save(entity1);
	    return true;
	}
    }

    @Override
    public ReturnModel getRegionalModel(Integer type, Integer ver) {
	// TODO 自动生成的方法存根
	ReturnModel rm = new ReturnModel();

	if (type == null || ver == null || type < 0 || ver < 0) {
	    rm.setStatu(ReturnStatus.ParamError);
	    rm.setStatusreson("参数传递不正确！");
	    return rm;
	}

	int serverVer = Integer.parseInt(OperateXML.getVersion("regional"));
	RegionalModel regionalM = new RegionalModel();
	regionalM.setVer(serverVer);
	switch (type) {
	    case 1: {
		if (ver < serverVer) {
		    List<TRegional> provinceList = commonDao.getRegional((short) 2);
		    List<TRegional> cityList = commonDao.getRegional((short) 3);
		    List<TRegional> districtList = commonDao.getRegional((short) 4);

		    List<RegionalDataModel> returnList = new ArrayList<RegionalDataModel>();
		    for (TRegional province : provinceList) {
			RegionalDataModel pModle = new RegionalDataModel();
			pModle.setCode(province.getCode());
			pModle.setName(province.getName());
			pModle.setLevel(2);
			List<RegionalDataModel> cList = new ArrayList<RegionalDataModel>();

			for (TRegional city : cityList) {
			    if (city.getParent().equals(province.getCode())) {
				RegionalDataModel cModle = new RegionalDataModel();
				cModle.setCode(city.getCode());
				cModle.setName(city.getName());
				cModle.setLevel(3);
				List<RegionalDataModel> dList = new ArrayList<RegionalDataModel>();
				for (TRegional district : districtList) {
				    if (district.getParent().equals(city.getCode())) {
					RegionalDataModel dModle = new RegionalDataModel();
					dModle.setCode(district.getCode());
					dModle.setName(district.getName());
					dModle.setLevel(4);
					dList.add(dModle);
				    }
				}
				cModle.setNextLevelList(dList);
				cList.add(cModle);
			    }
			}
			pModle.setNextLevelList(cList);
			returnList.add(pModle);
		    }
		    regionalM.setRegionalList(returnList);
		    rm.setStatu(ReturnStatus.Success);
		    rm.setStatusreson("成功！");
		    rm.setBasemodle(regionalM);
		} else {
		    rm.setStatu(ReturnStatus.Success);
		    rm.setStatusreson("本地版本不需要更新！");
		}

		break;
	    }
	    case 2: {
		if (ver < serverVer) {
		    List<TRegional> regionalList = commonDao.getRegional();
		    regionalM.setRegionalList(regionalList);
		    rm.setBasemodle(regionalM);
		    rm.setStatu(ReturnStatus.Success);
		    rm.setStatusreson("成功！");
		} else {
		    rm.setStatu(ReturnStatus.Success);
		    rm.setStatusreson("本地版本不需要更新！");
		}

		break;
	    }
	    default:
		break;
	}
	return rm;
    }

    @Override
    public List<TBussinessClass> getBussinessClasses() {
	return baseDao.find("from TBussinessClass");
    }

    @Override
    public ReturnModel getShareRimand(Long weiid, Short type) {
	ReturnModel result = new ReturnModel();
	result.setStatu(ReturnStatus.ParamError);
	if (weiid.longValue() <= 0 || type.intValue() <= 0) {
	    return result;
	}
	TRemind remind = baseDao.getNotUniqueResultByHql("from TRemind where weiId=? and type=? and status=1", new Object[] { weiid, type });
	if (remind == null) {
	    // 没有说明没有提醒过
	    result.setStatu(ReturnStatus.Success);
	    String str = AppSettingUtil.getSingleValue("ShareReminders");
	    result.setStatusreson(str);
	    remind = new TRemind();
	    remind.setWeiId(weiid);
	    remind.setType(type);
	    remind.setStatus((short) 1);
	    baseDao.save(remind);
	}
	return result;
    }

}
