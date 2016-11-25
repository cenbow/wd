package com.okwei.service.impl.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.AdminUser;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UPlatformSupplyerImg;
import com.okwei.bean.dto.AddCompanyDataDTO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IBasicPlatformSupplyerDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IBasicPlatformSupplyerService;
import com.okwei.util.ObjectUtil;

@Service
public class BasicPlatformSupplyerService extends BaseService implements IBasicPlatformSupplyerService {
	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private IBasicPlatformSupplyerDAO iBasicPlatformSupplyerDAO;
	@Override
	public ReturnModel addCompanyData(AddCompanyDataDTO param, AdminUser user) {
		ReturnModel model = new ReturnModel();
		if(param!=null){
			if(ObjectUtil.isEmpty(param.getSupplyName())){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("公司名不能为空");
				return model;
			}
		}else{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("参数为空");
			return model;
		}
		String[] imageArr = param.getImageArr(); 
		UPlatformSupplyer obj = new UPlatformSupplyer();
		//平台号供应商微店号
		obj.setWeiId(param.getWeiId());
		//品牌特色
		obj.setBrandFeature(param.getBrandFeature());
		//公司简介
		obj.setDetails(param.getDetails());
		//手机
		obj.setMobilePhone(param.getMobilePhone());
		//电话
		obj.setTelephone(param.getTelephone());
		//联系人
		obj.setLinkMan(param.getLinkMan());
		//公司名称
		obj.setSupplyName(param.getSupplyName());
		//公司地址
		obj.setAddress(param.getAddress());
		obj.setCreateTime(new Date());
		baseDAO.save(obj);
		//资历证书图片
		if(imageArr!=null&&imageArr.length>0){
			for(int i=0;i<imageArr.length;i++){
				UPlatformSupplyerImg imgObj = new UPlatformSupplyerImg();
				imgObj.setImage(imageArr[i]);
				imgObj.setWeiId(param.getWeiId());
				imgObj.setCreateTime(new Date());
				baseDAO.save(imgObj);
			}
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}
	@Override
	public ReturnModel editCompanyData(AddCompanyDataDTO param, AdminUser user) {
		ReturnModel model = new ReturnModel();
		if(param!=null){
			if(ObjectUtil.isEmpty(param.getWeiId())){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("微店号为空");
				return model;
			}
			if(ObjectUtil.isEmpty(param.getSupplyName())){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("公司名不能为空");
				return model;
			}
		}else{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("参数为空");
			return model;
		}
		UPlatformSupplyer obj = baseDAO.get(UPlatformSupplyer.class,param.getWeiId());
		//品牌特色
		obj.setBrandFeature(param.getBrandFeature());
		//公司简介
		obj.setDetails(param.getDetails());
		//手机
		obj.setMobilePhone(param.getMobilePhone());
		//电话
		obj.setTelephone(param.getTelephone());
		//联系人
		obj.setLinkMan(param.getLinkMan());
		//公司名称
		obj.setSupplyName(param.getSupplyName());
		//公司地址
		obj.setAddress(param.getAddress());
		//先删除原有的图片
		iBasicPlatformSupplyerDAO.deletePlatformSupplyerImg(param.getWeiId());
		//再重新添加资历证书图片
		String[] imageArr = param.getImageArr(); 
		if(imageArr!=null&&imageArr.length>0){
			for(int i=0;i<imageArr.length;i++){
				UPlatformSupplyerImg imgObj = new UPlatformSupplyerImg();
				imgObj.setImage(imageArr[i]);
				imgObj.setWeiId(param.getWeiId());
				imgObj.setCreateTime(new Date());
				baseDAO.save(imgObj);
			}
		}
		return model;
	}

}
