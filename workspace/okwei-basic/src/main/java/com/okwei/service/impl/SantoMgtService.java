package com.okwei.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.DAdInfo;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrandAgentInfo;
import com.okwei.bean.domain.DBrandImgs;
import com.okwei.bean.domain.DBrandIndustry;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.DBrandsExt;
import com.okwei.bean.domain.DBrandsInfo;
import com.okwei.bean.domain.DCastellans;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.SantoMgtDTO;
import com.okwei.bean.dto.TrialStylesDTO;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.agent.AgentType;
import com.okwei.bean.enums.agent.BrandType;
import com.okwei.bean.enums.agent.CastellanType;
import com.okwei.bean.vo.PictureAdModel;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.SantoMgtVO;
import com.okwei.bean.vo.product.ProductAuditVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IUUserAssistMgtDAO;
import com.okwei.service.IBasicShoppingCartService;
import com.okwei.service.ISantoMgtService;
import com.okwei.service.TRegionalService;
import com.okwei.util.DateUtils;
import com.okwei.util.ParseHelper;

@Service
public class SantoMgtService extends BaseService implements ISantoMgtService {

	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private TRegionalService regionalService;
	@Autowired
	private IUUserAssistMgtDAO iUUserAssistMgtDAO;
	@Autowired
	private IBasicShoppingCartService iShoppingCartService;

	@Override
	public PageResult<SantoMgtVO> findPageSanto(SantoMgtDTO sMgtDTO, Limit limit) {
		String hql = "select a.qq as qq,a.brandId as brandId,a.weiID as weiId,b.logo as logo,"
				+ "a.contact as headName,b.brandName as brandName,a.createTime as createTime,"
				+ "a.contactPhone as phoneNumber,a.landLine as landLine,"
				+ "a.province as province,a.city as city,a.district as district,a.addressDetail as addressDetail,"
				+ "a.main as main,a.securityDeposit as securityDeposit,a.costs as costs,a.payedType as payedType,"
				+ "a.companyName as companyName,a.companyProfile as companyProfile,a.characteristics as characteristics,"
				+ "a.conditions as conditions,a.agency as agency,a.bLicense as profitImg from DBrandsInfo as a,DBrands as b"
				+ " where a.brandId=b.brandId ";
		Map<String, Object> para = new HashMap<String, Object>();
		if (sMgtDTO.getProvince() != null && sMgtDTO.getProvince() > 0) {
			hql += " and a.province =:province";
			para.put("province", sMgtDTO.getProvince());
		}
		if (sMgtDTO.getBrandId() != null && sMgtDTO.getBrandId() > 0) {
			hql += " and a.brandId =:brandId";
			para.put("brandId", sMgtDTO.getBrandId());
		}
		if (sMgtDTO.getDistrict() != null && sMgtDTO.getDistrict() > 0) {
			hql += " and a.district =:district";
			para.put("district", sMgtDTO.getDistrict());
		}
		if (sMgtDTO.getCity() != null && sMgtDTO.getCity() > 0) {
			hql += " and a.city =:city";
			para.put("city", sMgtDTO.getCity());
		}     
		if (sMgtDTO.getCreateTime() != null && "" != sMgtDTO.getCreateTime()) {
			hql += " and a.createTime>=:createdate";
			para.put("createdate",
					DateUtils.format(sMgtDTO.getCreateTime(), "yyyy-MM-dd"));
		}
		if (sMgtDTO.getBrandName() != null && "" != sMgtDTO.getBrandName()) {
			hql += " and b.brandName like '%" + sMgtDTO.getBrandName() + "%'";
		}
		if (sMgtDTO.getWeiId() != null && sMgtDTO.getWeiId() > 0) {
			hql += " and a.weiID =:weiId";
			para.put("weiId", sMgtDTO.getWeiId());
		}
		hql += " ORDER BY a.createTime DESC";
		PageResult<SantoMgtVO> pageResult = baseDAO.findPageResultTransByMap(hql.toString(), SantoMgtVO.class, limit, para);
		List<Integer> brandId = new ArrayList<Integer>();
		if (pageResult != null && pageResult.getList() != null
				&& pageResult.getList().size() > 0) {
			for (SantoMgtVO stmMgtVO : pageResult.getList()) {
				if (stmMgtVO.getProvince() != null
						&& stmMgtVO.getDistrict() != null
						&& stmMgtVO.getProvince() > 0
						&& stmMgtVO.getDistrict() > 0) {
					stmMgtVO.setShowarea(regionalService.getNameByCode(stmMgtVO
							.getProvince())
							+ "-"
							+ regionalService.getNameByCode(stmMgtVO.getCity())
							+ "-"
							+ regionalService.getNameByCode(stmMgtVO
									.getDistrict()));
				}
				if (stmMgtVO.getBrandId() != null && stmMgtVO.getBrandId() > 0) {
					if (!brandId.contains(stmMgtVO.getBrandId())) {
						brandId.add(stmMgtVO.getBrandId());
					}
				}
			}
		} else {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (brandId != null && brandId.size() > 0) {
			params.put("brandId", brandId);
		}
		List<DBrandSupplier> dbslist = baseDAO.findByMap(
				"from DBrandSupplier where brandId in(:brandId)", params);
		List<DBrandImgs> dBrlist = baseDAO.findByMap(
				"from DBrandImgs where brandId in(:brandId)", params);

		List<DBrandIndustry> dBilist = baseDAO.findByMap(
				"from DBrandIndustry where brandId in(:brandId)", params);
		List<DBrandsExt> dbetList = baseDAO.findByMap(
				"from DBrandsExt where brandId in(:brandId)", params);
		List<DBrandAgentInfo> dInfList = baseDAO.findByMap(
				"from DBrandAgentInfo where brandId in(:brandId)", params);
		if (pageResult != null && pageResult.getList() != null
				&& pageResult.getList().size() > 0) {
			
			for (SantoMgtVO stmMgtVO : pageResult.getList()) {
				List<String> htimg=new ArrayList<String>();
				if (dBrlist != null && dBrlist.size() > 0) {
					for (DBrandImgs dimg : dBrlist) {
						if (dimg != null&& stmMgtVO.getBrandId().intValue() == dimg.getBrandId().intValue()&& dimg.getImg() != null&& dimg.getImg().toString() != null) {
							//stmMgtVO.getHtimg().add(dimg.getImg().toString());
							htimg.add(dimg.getImg().toString());
						}
					}
					stmMgtVO.setHtimg(htimg);
				}
				if (dbslist != null && dbslist.size() > 0) {
					for (DBrandSupplier dBs : dbslist) {
						if (dBs != null&& dBs.getBrandId().intValue() == stmMgtVO.getBrandId().intValue()) {
							stmMgtVO.setType(dBs.getType());
							break;
						}
					}
				}
				if (stmMgtVO.getCreateTime() != null) {
					stmMgtVO.setCreateTimes(DateUtils.formatDateTime(stmMgtVO.getCreateTime()));
				}
				if (dBilist != null && dBilist.size() > 0) {
					for (DBrandIndustry industry : dBilist) {
						if (stmMgtVO.getBrandId().intValue() == industry
								.getBrandId().intValue()) {
							stmMgtVO.setIndustry(industry.getName());
						}
					}
				}
				if(dbetList!=null&&dbetList.size()>0){
					for (DBrandsExt dBrandsExt : dbetList) {
						if (stmMgtVO.getBrandId().intValue() == dBrandsExt
								.getBrandId().intValue()) {
							stmMgtVO.setAgentOneCount(dBrandsExt.getAgentOneCount());
							stmMgtVO.setAgentTwoCount(dBrandsExt.getAgentTwoCount());
							stmMgtVO.setAgentThreeCount(dBrandsExt.getAgentThreeCount());
						}
					}
				}
				if(dInfList!=null&&dInfList.size()>0){
					for (DBrandAgentInfo dAgentInfo : dInfList) {
						if(stmMgtVO.getBrandId().intValue()==dAgentInfo.getBrandId().intValue()){
							stmMgtVO.setAgencyOne(dAgentInfo.getAgencyOne());
							stmMgtVO.setAgencyTwo(dAgentInfo.getAgencyTwo());
							stmMgtVO.setAgencyThree(dAgentInfo.getAgencyThree());
						}
					}
				}
			}
		}
		return pageResult;
	}

	@Override
	public ReturnModel getSantoDTO(String json) {
		// TODO Auto-generated method stub
		ReturnModel returnModel = new ReturnModel();
		try {
			SantoMgtVO stMgtVO = new SantoMgtVO();
			JSONObject obj;
			obj = JSONObject.fromObject(json);
			stMgtVO.setProvince(obj.getInt("province"));
			if (stMgtVO.getProvince() == null || stMgtVO.getProvince() < 1) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择省!");
				return returnModel;
			}
			stMgtVO.setCity(obj.getInt("city"));
			if (stMgtVO.getCity() == null || stMgtVO.getCity() < 1) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择市!");
				return returnModel;
			}
			stMgtVO.setDistrict(obj.getInt("district"));
			if (stMgtVO.getDistrict() == null || stMgtVO.getDistrict() < 1) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择区!");
				return returnModel;
			}
			// 微店号
			stMgtVO.setWeiId(ParseHelper.toLong(String.valueOf(obj.get("weiId"))));
			if (stMgtVO.getWeiId() == null
					|| stMgtVO.getWeiId().longValue() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入微店号!");
				return returnModel;
			}
			stMgtVO.setBrandId(ParseHelper.toInt(String.valueOf(obj.get("brandId"))));
			// 品牌名称
			stMgtVO.setBrandName(obj.getString("brandName"));
			if (stMgtVO.getBrandName() == null
					|| "".equals(stMgtVO.getBrandName())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入品牌名称!");
				return returnModel;
			}
			// 联合品牌分销
			stMgtVO.setType(obj.getInt("supplierType"));
			if (stMgtVO.getType() == 0) {
				stMgtVO.setType(Integer.parseInt(BrandType.personal.toString()));
				stMgtVO.setStatus(1);
			} else {
				stMgtVO.setStatus(Integer.parseInt(BrandType.join.toString()));
				stMgtVO.setType(ParseHelper.toInt(String.valueOf(obj.get("supplierType"))));
			}
			// 公司名称
			stMgtVO.setCompanyName(obj.getString("companyName"));
			if (stMgtVO.getCompanyName() == null
					|| "".equals(stMgtVO.getCompanyName())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入公司名称!");
				return returnModel;
			}
			// 负责人
			stMgtVO.setHeadName(obj.getString("linkMan"));
			if (stMgtVO.getHeadName() == null
					|| "".equals(stMgtVO.getHeadName())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入负责人!");
				return returnModel;
			}
			// 手机号码
			stMgtVO.setPhoneNumber(obj.getString("mobilePhone"));
			if (stMgtVO.getPhoneNumber() == null
					|| "".equals(stMgtVO.getPhoneNumber())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入正确的手机号码!");
				return returnModel;
			}
			// 电话号码
			stMgtVO.setLandLine(obj.getString("telephone"));
			/*
			 * if(stMgtVO.getLandLine() == null ||
			 * "".equals(stMgtVO.getLandLine())) {
			 * returnModel.setStatu(ReturnStatus.ParamError);
			 * returnModel.setStatusreson("请输入正确的电话号码!"); return returnModel; }
			 */

			// 详细地址
			stMgtVO.setAddressDetail(obj.getString("address"));
			if (stMgtVO.getAddressDetail() == null
					|| "".equals(stMgtVO.getAddressDetail())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入详细地址!");
				return returnModel;
			}
			// 合同附件
			String fjimg = obj.getString("fjimg");
			List<DBrandImgs> dList = new ArrayList<DBrandImgs>();
			JSONArray bArray = new JSONArray().fromObject(fjimg);
			for (int z = 0; z < bArray.size(); z++) // 遍历value
			{
				JSONObject bovalue = (JSONObject) bArray.get(z);
				String fjimgs = bovalue.getString("fjimgs");
				if (fjimgs == null || "".equals(fjimgs)) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("请上传合同附件!");
					return returnModel;
				}
				if (z > 8) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("只能上传9张合同附件!");
					return returnModel;
				}
				DBrandImgs dimg = new DBrandImgs();
				if (obj.getInt("brandId") > 0) {
					dimg.setBrandId(obj.getInt("brandId"));
				}
				dimg.setType(0);
				dimg.setImg(fjimgs.trim());
				dList.add(dimg);
			}
			// model合同附件List
			stMgtVO.setHtimgList(dList);
			// 行业
			String bClassJson = obj.getString("industryList");
			// List<DBrandIndustry> dbrList = new ArrayList<DBrandIndustry>();
			JSONArray bArrayValue = new JSONArray().fromObject(bClassJson);
			String name = "";
			DBrandIndustry dBrandIndustry = new DBrandIndustry();
			for (int z = 0; z < bArrayValue.size(); z++) // 遍历value
			{
				JSONObject bovalue = (JSONObject) bArrayValue.get(z);
				String businessClass = bovalue.getString("businessclass");
				String bussName = bovalue.getString("businessname");
				if (businessClass == null || "".equals(businessClass)
						|| !isLong(businessClass.trim())) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("请选择所属行业!");
					return returnModel;
				}
				if (bussName == null && "".equals(bussName)) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("请选择所属行业!");
					return returnModel;
				}
				dBrandIndustry.setBrandId(ParseHelper.toInt(String.valueOf(obj.get("brandId"))));
				// dBrandIndustry.setIndustryId(Integer.parseInt(businessClass.trim()));
				dBrandIndustry.setIndustryId(z);
				name += bussName.trim() + "、";
				dBrandIndustry.setName(name);
				// dbrList.add(dBrandIndustry);
			}
			if (name != null && !"".equals(name)) {
				stMgtVO.setIndustry(name);
				stMgtVO.setdBi(dBrandIndustry);
			}
			// model行业List
			// stMgtVO.setDbrList(dbrList);
			// 主营
			stMgtVO.setMain(obj.getString("saleType"));
			/*
			 * if(stMgtVO.getMain() == null ||"".equals(stMgtVO.getMain())) {
			 * returnModel.setStatu(ReturnStatus.ParamError);
			 * returnModel.setStatusreson("请输入主营!"); return returnModel; }
			 */
			// bond
			stMgtVO.setQq(String.valueOf(obj.getString("QQ")));
			//标识增加还是修改
			stMgtVO.setIstype(ParseHelper.toInt(String.valueOf(obj.get("type"))));
			stMgtVO.setSecurityDeposit(ParseHelper.toDouble(String.valueOf(obj
					.get("bond"))));
			if (stMgtVO.getSecurityDeposit() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("保证金参数错误!");
				return returnModel;
			}
			// 代理费用
			stMgtVO.setAgency(ParseHelper.toDouble(String.valueOf(obj
					.get("agency"))));
			if (stMgtVO.getAgency() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("代理费参数错误!");
				return returnModel;
			}
			// 入场费
			stMgtVO.setCosts(ParseHelper.toDouble(String.valueOf(obj
					.get("admission"))));
			if (stMgtVO.getCosts() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("入场费参数错误!");
				return returnModel;
			}
			// 支付类型
			/*
			 * stMgtVO.setPayedType(obj.getInt("payedType"));
			 * if(stMgtVO.getPayedType() < 0) {
			 * returnModel.setStatu(ReturnStatus.ParamError);
			 * returnModel.setStatusreson("支付类型参数错误!"); return returnModel; }
			 */
			// logo图
			stMgtVO.setLogo(obj.getString("logoImage"));
			if (stMgtVO.getLogo() == null || "".equals(stMgtVO.getLogo())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请上传Logo图片!");
				return returnModel;
			}
			// 营业执照
			stMgtVO.setProfitImg(obj.getString("profitImg"));
			if (stMgtVO.getProfitImg() == null
					|| "".equals(stMgtVO.getProfitImg())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请上传营业照图片!");
				return returnModel;
			}
			// 公司简介
			stMgtVO.setCompanyProfile(obj.getString("details"));
			// 换行符替换<br>
			stMgtVO.setCompanyProfile(stMgtVO.getCompanyProfile().replace("\n",
					"<br>"));
			// 品牌特色
			stMgtVO.setCharacteristics(obj.getString("brandFeature"));
			stMgtVO.setConditions(obj.getString("conditions"));
			// 换行符替换<br>
			stMgtVO.setConditions(stMgtVO.getConditions().replace("\n", "<br>"));
			stMgtVO.setCharacteristics(stMgtVO.getCharacteristics().replace(
					"\n", "<br>"));
			// if(stMgtVO.getBrandFeature() == null ||
			// "".equals(stMgtVO.getBrandFeature()))
			// {
			// returnModel.setStatu(ReturnStatus.ParamError);
			// returnModel.setStatusreson("品牌特色参数错误!");
			// return returnModel;
			// }
			// 返回对象
			returnModel.setBasemodle(stMgtVO);
			returnModel.setStatu(ReturnStatus.Success);
			returnModel.setStatusreson("成功!");
		} catch (Exception ex) {
			returnModel.setStatu(ReturnStatus.SystemError);
			returnModel.setStatusreson(ex.getMessage());
			return returnModel;
		}
		return returnModel;
	}

	public static boolean isLong(String str) {
		boolean Result = false;
		try {
			Long.parseLong(str);
			Result = true;
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	@Override
	public ReturnModel saveSantoDTO(SantoMgtVO vo) {
		ReturnModel returnModel = new ReturnModel();
		UWeiSeller usif = baseDAO.get(UWeiSeller.class, vo.getWeiId());
		if (usif == null) {
			returnModel.setStatu(ReturnStatus.DataError);
			returnModel.setStatusreson("该微店用户不存在");
			return returnModel;
		}
		//只有增加的时候才需要验证
		if(vo.getIstype()!=2){
			UUserAssist uast = baseDAO.getNotUniqueResultByHql("from UUserAssist where identity<>1 and weiId=?",vo.getWeiId());
			if(uast!=null){
				returnModel.setStatu(ReturnStatus.DataError);
				returnModel.setStatusreson("只有普通微店主才能添加!");
				return returnModel;
			}
		}
		String hql = "from DBrands where weiId=:weiId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiId", vo.getWeiId());
		// 判断数据是否已经存在
		
		if (vo.getIstype() != 2) {
			List<DBrands> dbrlist = baseDAO.findByMap(hql.toString(), params);
			if (dbrlist != null && dbrlist.size() > 0) {
				returnModel.setStatu(ReturnStatus.DataError);
				returnModel.setStatusreson("该品牌商户已存在!");
				return returnModel;
			}
		}
		DBrands dbds=baseDAO.get(DBrands.class, vo.getType());
		/*if(vo.getType()==0){*/
			if(dbds==null){
				dbds= new DBrands();
				dbds.setCreateTime(new Date());
			}
			dbds.setBrandName(vo.getBrandName());				
			dbds.setHeadName(vo.getHeadName());
			dbds.setIndustry(vo.getIndustry());
			dbds.setLogo(vo.getLogo());
			if(vo.getType()!=0){
				dbds.setType(1);
			}else{
				dbds.setType(vo.getType());
			}
			dbds.setStatus(1);
			dbds.setPhoneNumber(vo.getPhoneNumber());
			dbds.setWeiId(vo.getWeiId());
			baseDAO.saveOrUpdate(dbds);
		/*}*/
		if (vo.getWeiId() != null && vo.getWeiId().longValue() > 0) {
			DBrandSupplier dbs = baseDAO.get(DBrandSupplier.class, vo.getWeiId());
			if(dbs==null){
				dbs = new DBrandSupplier();
				dbs.setWeiId(vo.getWeiId());
			}
			if(vo.getType()==0){
				dbs.setBrandId(dbds.getBrandId());
				dbs.setState(1);
				dbs.setType(dbds.getType());
			}else{
				dbs.setBrandId(vo.getType());
				dbs.setState(Integer.parseInt(BrandType.join.toString()));
				dbs.setType(Integer.parseInt(BrandType.join.toString()));
			}
			baseDAO.saveOrUpdate(dbs);
		}
		iUUserAssistMgtDAO.addIdentity(vo.getWeiId(),UserIdentityType.AgentBrandSupplier);
		if (vo.getWeiId() != null && vo.getWeiId().longValue() > 0) {
			DBrandsInfo dbif = baseDAO.get(DBrandsInfo.class, vo.getWeiId());
			if (dbif == null) {
				dbif = new DBrandsInfo();
				dbif.setWeiID(vo.getWeiId());
				dbif.setIndustry(vo.getIndustry());
			}
			dbif.setQq(vo.getQq());
			if(vo.getType()!=0){
				dbif.setBrandId(vo.getType());
			}else{
				dbif.setBrandId(dbds.getBrandId().intValue());
			}
			dbif.setProvince(vo.getProvince());
			dbif.setDistrict(vo.getDistrict());
			dbif.setCity(vo.getCity());
			dbif.setAddressDetail(vo.getAddressDetail());
			if (vo.getCompanyProfile() != null
					&& !"".equals(vo.getCompanyProfile())) {
				dbif.setCompanyProfile(vo.getCompanyProfile());
			}
			if (vo.getLandLine() != null
					&& !"".equals(vo.getLandLine())) {
				dbif.setLandLine(vo.getLandLine());
			}
			dbif.setbLicense(vo.getProfitImg());
			dbif.setCompanyName(vo.getCompanyName());
			if (vo.getCharacteristics() != null
					&& !"".equals(vo.getCharacteristics())) {
				dbif.setCharacteristics(vo.getCharacteristics());
			}
			if (vo.getConditions() != null
					&& !"".equals(vo.getConditions())) {
				dbif.setConditions(vo.getConditions());
			}
			if (vo.getMain() != null && !"".equals(vo.getMain())) {
				dbif.setMain(vo.getMain());
			}
			dbif.setAgency(vo.getAgency());
			dbif.setCosts(vo.getCosts());
			dbif.setSecurityDeposit(vo.getSecurityDeposit());
			dbif.setCreateTime(new Date());
			dbif.setContactPhone(vo.getPhoneNumber());
			dbif.setContact(vo.getHeadName());
			dbif.setWeiID(vo.getWeiId());
			baseDAO.saveOrUpdate(dbif);
		}
		
		if (dbds.getBrandId() != null && dbds.getBrandId().intValue() > 0) {
			//先删除后插
			String hql_dString="delete from DBrandImgs o where o.brandId=?";
			if(vo.getType()!=0){
				baseDAO.executeHql(hql_dString, vo.getType());
			}else{
				baseDAO.executeHql(hql_dString, dbds.getBrandId());
			}
			for (DBrandImgs dImgs : vo.getHtimgList()) {
				if (dImgs != null) {
					DBrandImgs dImg = new DBrandImgs();
					if(vo.getType()!=0){
						dImg.setBrandId(vo.getType());
					}else{
						dImg.setBrandId(dbds.getBrandId());
					}
					dImg.setImg(dImgs.getImg());
					dImg.setType(dImgs.getType());
					baseDAO.saveOrUpdate(dImg);
				}
			}
		}
		
		String hql_dString="from DBrandIndustry o where o.brandId=?";
		DBrandIndustry diIndustry = baseDAO.getNotUniqueResultByHql(hql_dString, vo.getBrandId());
		if(diIndustry==null){
			diIndustry = new DBrandIndustry();
			diIndustry.setName(vo.getIndustry());
		}
		if(vo.getType()!=0){
			diIndustry.setBrandId(vo.getType());
		}else{
			diIndustry.setBrandId(dbds.getBrandId());
		}
		baseDAO.saveOrUpdate(diIndustry);	
		
		//成为代理区品牌商后清空改用户之前所有的商品
		
		returnModel.setStatu(ReturnStatus.Success);
		if (vo.getBrandId() != null && vo.getBrandId().intValue() > 0) {
			returnModel.setStatusreson("修改成功!");
		} else {
			returnModel.setStatusreson("保存成功!");
		}
		return returnModel;
	}

	@Override
	public PageResult<SantoMgtVO> findPageAgent(SantoMgtDTO sMgtDTO, Limit limit) {
		String hql = "select a.agentId as agentId,a.weiId as weiId,b.brandId as brandId,b.brandName as brandName,a.province as province,a.city as city,a.district as district,"
				+ "a.contactPhone as phoneNumber,a.qq as landLine,a.agentId as agentId,"
				+ "a.createTime as createTime,a.costs as costs from DAgentInfo as a,DBrands as b where a.brandId=b.brandId";
		Map<String, Object> para = new HashMap<String, Object>();
		if (sMgtDTO.getProvince() != null && sMgtDTO.getProvince() > 0) {
			hql += " and a.province =:province";
			para.put("province", sMgtDTO.getProvince());
		}
		if (sMgtDTO.getDistrict() != null && sMgtDTO.getDistrict() > 0) {
			hql += " and a.district =:district";
			para.put("district", sMgtDTO.getDistrict());
		}
		if (sMgtDTO.getCity() != null && sMgtDTO.getCity() > 0) {
			hql += " and a.city =:city";
			para.put("city", sMgtDTO.getCity());
		}
		if (sMgtDTO.getCreateTime() != null && "" != sMgtDTO.getCreateTime()) {
			hql += " and a.createTime>=:createdate";
			para.put("createdate",
					DateUtils.format(sMgtDTO.getCreateTime(), "yyyy-MM-dd"));
		}
		if (sMgtDTO.getBrandName() != null && "" != sMgtDTO.getBrandName()) {
			hql += " and b.brandName like '%" + sMgtDTO.getBrandName() + "%'";
		}
		if (sMgtDTO.getWeiId() != null && sMgtDTO.getWeiId() > 0) {
			hql += " and a.weiId =:weiId";
			para.put("weiId", sMgtDTO.getWeiId());
		}
		if (sMgtDTO.getStatus() != null && sMgtDTO.getStatus() > 0) {
			hql += " and a.agentId in (select agentId from DCastellans)";
		}
		hql += " ORDER BY a.createTime DESC";
		PageResult<SantoMgtVO> pageResult = baseDAO.findPageResultTransByMap(
				hql.toString(), SantoMgtVO.class, limit, para);
		List<Long> agentId = new ArrayList<Long>();
		List<Long> weiId = new ArrayList<Long>();
		if (pageResult != null && pageResult.getList() != null
				&& pageResult.getList().size() > 0) {
			for (SantoMgtVO stmMgtVO : pageResult.getList()) {
				if (stmMgtVO.getWeiId() != null
						&& stmMgtVO.getAgentId() != null) {
					if (stmMgtVO.getCreateTime() != null) {
						stmMgtVO.setCreateTimes(DateUtils.formatDateTime(stmMgtVO.getCreateTime()));
					}
					if (stmMgtVO.getProvince() != null&& stmMgtVO.getDistrict() != null&& stmMgtVO.getProvince() > 0&& stmMgtVO.getDistrict() > 0) {
						stmMgtVO.setOutOrIn(2);
						stmMgtVO.setStatus(0);
						stmMgtVO.setShowarea(regionalService
								.getNameByCode(stmMgtVO.getProvince())
								+ "-"
								+ regionalService.getNameByCode(stmMgtVO
										.getCity())
								+ "-"
								+ regionalService.getNameByCode(stmMgtVO
										.getDistrict()));
					}
					if (!agentId.contains(stmMgtVO.getAgentId())) {
						agentId.add(stmMgtVO.getAgentId());
					}
					if (!weiId.contains(stmMgtVO.getWeiId())) {
						weiId.add(stmMgtVO.getWeiId());
					}
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		String hqls = "from DCastellans a where  1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (agentId != null) {
			hqls += " and a.agentId in (:agentId)";
			params.put("agentId", agentId);
		}
		if (sMgtDTO.getStatus() != null && sMgtDTO.getStatus() > 0) {
			hqls += " and a.porN =:porN";
			params.put("porN", sMgtDTO.getStatus());
		}
		List<DCastellans> dcList = baseDAO.findByMap(hqls.toString(), params);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("weiId", weiId);
		List<UShopInfo> usInfos = baseDAO.findByMap(
				"from UShopInfo where weiId in(:weiId)", paramMap);
		if (pageResult != null && pageResult.getList() != null
				&& pageResult.getList().size() > 0) {
			for (SantoMgtVO stmMgtVO : pageResult.getList()) {
				if (dcList != null && dcList.size() > 0) {
					for (DCastellans dCastellans : dcList) {
						if (stmMgtVO.getAgentId().longValue() == dCastellans
								.getAgentId().longValue()) {
							if (dCastellans.getPorN() > 0&& dCastellans.getPorN() != null&& dCastellans.getOutOrIn() != null&& dCastellans.getOutOrIn() >= 0) {
								stmMgtVO.setOutOrIn(dCastellans.getOutOrIn() == null ? 2: dCastellans.getOutOrIn());
								stmMgtVO.setStatus(dCastellans.getPorN() == null ? 0: dCastellans.getPorN());
								break;
							}
							if (dCastellans.getPorN() == sMgtDTO.getStatus()&& sMgtDTO.getStatus() != null&& sMgtDTO.getStatus() > 0) {
								stmMgtVO.setOutOrIn(dCastellans.getOutOrIn() == null ? 2: dCastellans.getOutOrIn());
								stmMgtVO.setStatus(dCastellans.getPorN() == null ? 0: dCastellans.getPorN());
								break;
							}
						}
					}
				}
				if (usInfos != null && usInfos.size() > 0) {
					for (UShopInfo uShopInfo : usInfos) {
						if (uShopInfo.getShopName() != null
								&& "" != uShopInfo.getShopName()
								&& uShopInfo.getWeiId().longValue() == stmMgtVO
										.getWeiId().longValue()) {
							stmMgtVO.setShopName(uShopInfo.getShopName());
							break;
						}
					}
				}
			}
		}
		return pageResult;

	}

	@Override
	public List<DBrands> findDBrands(int stauts,int supType) {
		String hql = "from DBrands";
		if(stauts>0&&supType>0){
			hql+=" where status='"+stauts+"";
		}
		if(supType>=0){
			hql+="' and type='"+supType+"'";
		}
		List<DBrands> dBrands = baseDAO.find(hql);
		if (dBrands != null && dBrands.size() > 0) {
			return dBrands;
		}
		return null;
	}

	@Override
	public ReturnModel getAgentoDTO(String json) {
		// TODO Auto-generated method stub
		ReturnModel returnModel = new ReturnModel();
		try {
			SantoMgtVO stMgtVO = new SantoMgtVO();
			JSONObject obj;
			obj = JSONObject.fromObject(json);
			stMgtVO.setDistrict(obj.getInt("district"));
			if (stMgtVO.getDistrict() == null && stMgtVO.getDistrict() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择省市区!");
				return returnModel;
			}
			stMgtVO.setProvince(obj.getInt("province"));
			if (stMgtVO.getProvince() == null && stMgtVO.getProvince() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择省市区!");
				return returnModel;
			}
			stMgtVO.setCity(obj.getInt("city"));
			if (stMgtVO.getCity() == null && stMgtVO.getCity() < 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请选择省市区!");
				return returnModel;
			}
			// 微店号
			stMgtVO.setWeiId(obj.getLong("weiId"));
			// 手机号码
			stMgtVO.setPhoneNumber(obj.getString("contactPhone"));
			if (stMgtVO.getPhoneNumber() == null
					|| "".equals(stMgtVO.getPhoneNumber())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入正确的手机号码!");
				return returnModel;
			}
			// qq号码
			stMgtVO.setQq(obj.getString("qq"));
			if (stMgtVO.getQq()== null
					|| "".equals(stMgtVO.getQq())) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("请输入QQ号码!");
				return returnModel;
			}
			stMgtVO.setBrandId(obj.getInt("brandId"));
			// 返回对象
			returnModel.setBasemodle(stMgtVO);
			returnModel.setStatu(ReturnStatus.Success);
			returnModel.setStatusreson("成功!");
		} catch (Exception ex) {
			returnModel.setStatu(ReturnStatus.SystemError);
			returnModel.setStatusreson(ex.getMessage());
			return returnModel;
		}
		return returnModel;
	}

	@Override
	public ReturnModel saveAgento(SantoMgtVO vo) {
		ReturnModel returnModel = new ReturnModel();
		UWeiSeller usif = baseDAO.get(UWeiSeller.class, vo.getWeiId());
		if (usif == null) {
			returnModel.setStatu(ReturnStatus.DataError);
			returnModel.setStatusreson("该微店用户不存在");
			return returnModel;
		}
		String hql = "from DAgentInfo where weiId='"+vo.getWeiId()+"'";
		Map<String, Object> params = new HashMap<String, Object>();
		if(vo.getBrandId()!=null&&vo.getBrandId().intValue()>0){
			hql+=" and brandId=:brandId";
			params.put("brandId", vo.getBrandId());
		}
		List<DAgentInfo> dList = baseDAO.findByMap(hql, params);
		if (dList != null && dList.size() > 0) {
			for (DAgentInfo dAgentInfo : dList) {
				if(dAgentInfo.getBrandId()==vo.getBrandId()){
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("您申请已代理该品牌!");
					return returnModel;
				}
			}
		}
		DAgentInfo dInfo = new DAgentInfo();
		dInfo.setBrandId(vo.getBrandId());
		dInfo.setCity(vo.getCity());
		dInfo.setWeiId(vo.getWeiId());
		dInfo.setContactPhone(vo.getPhoneNumber());
		dInfo.setCreateTime(new Date());
		dInfo.setDistrict(vo.getDistrict());
		dInfo.setProvince(vo.getProvince());
		dInfo.setQq(vo.getQq());
		hql = "from DCastellans where brandId=? and province=? and city=? and district=?";
		List<DCastellans> dllist = baseDAO.find(hql,
						vo.getBrandId(), vo.getProvince(), vo.getCity(),
						vo.getDistrict());
		if (dllist != null && dllist.size() > 0) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("该地区城主已存在!");
			return returnModel;
		} else {
			baseDAO.save(dInfo);
			DCastellans dll = new DCastellans();
			dll.setAgentId(dInfo.getAgentId());
			dll.setBrandId(vo.getBrandId());
			dll.setCity(vo.getCity());
			dll.setWeiId(vo.getWeiId());
			dll.setPorN(Integer.parseInt(CastellanType.castellan.toString()));
			dll.setOutOrIn(Integer.parseInt(AgentType.in.toString()));
			dll.setContactPhone(vo.getPhoneNumber());
			dll.setCreateTime(new Date());
			dll.setDistrict(vo.getDistrict());
			dll.setProvince(vo.getProvince());
			baseDAO.save(dll);
			//增加编内城主数量
			DBrandsExt dExt = baseDAO.get(DBrandsExt.class, vo.getBrandId());
			if(dExt!=null){
				baseDAO.executeHql("update DBrandsExt set castellanInCount=castellanInCount+1 where brandId=?",vo.getBrandId());
			}else{
				DBrandsExt dBrandsExt = new DBrandsExt();
				dBrandsExt.setBrandId(vo.getBrandId());
				dBrandsExt.setCastellanInCount(1);
				baseDAO.save(dBrandsExt);
			}
			
		}
		if (vo.getWeiId() != null && vo.getWeiId().longValue() > 0) {
			iUUserAssistMgtDAO.addIdentity(vo.getWeiId(),
					UserIdentityType.AgentDuke);
			//baseDAO.executeHql("delete from PProducts where ", vo.getWeiId());
		}
		returnModel.setStatu(ReturnStatus.Success);
		returnModel.setStatusreson("保存成功!");
		return returnModel;
	}

	@Override
	public List<ProductAuditVO> findProductAudit(Long productId) {
		 DecimalFormat df = new DecimalFormat("#.00");  
		List<ProductAuditVO> AuditVOsList = new ArrayList<ProductAuditVO>();
		List<PProductStyles> pList = baseDAO.find("from PProductStyles where productId=?", productId);
		if (pList != null && pList.size() > 0) {
			for (PProductStyles pStyles : pList) {
				ProductAuditVO pVo = new ProductAuditVO();
				if (pStyles.getStylesId() != null&& pStyles.getStylesId().longValue() > 0) {
					String pName = iShoppingCartService.getProductStyleName(productId, pStyles.getStylesId());
					pVo.setStylesId(pStyles.getStylesId());
					pVo.setAttributeName(pName);
					pVo.setPrice(pStyles.getPrice());
					pVo.setSupplyPrice(pStyles.getSupplyPrice());
					if(pStyles.getDukePrice()==null){
						pStyles.setDukePrice((pStyles.getPrice().doubleValue()-pStyles.getSupplyPrice().doubleValue())*0.1+pStyles.getSupplyPrice().doubleValue());
						//四舍五入取整
						BigDecimal f1 =new BigDecimal(df.format(pStyles.getDukePrice())).setScale(0, BigDecimal.ROUND_HALF_UP);
						pVo.setDukePrice(f1.toString());
					}else{
						pVo.setDukePrice(df.format(pStyles.getDukePrice()));
					}
					if(pStyles.getDeputyPrice()==null){
						pStyles.setDeputyPrice((pStyles.getPrice().doubleValue()-pStyles.getSupplyPrice().doubleValue())*0.1+pStyles.getDukePrice().doubleValue());
						//四舍五入取整
						BigDecimal f2 =new BigDecimal(df.format(pStyles.getDeputyPrice())).setScale(0, BigDecimal.ROUND_HALF_UP);
						pVo.setDeputyPrice(f2.toString());
					}else{
						pVo.setDeputyPrice(df.format(pStyles.getDeputyPrice()));
					}
					if(pStyles.getAgentPrice()==null){
						pStyles.setAgentPrice((pStyles.getPrice().doubleValue()-pStyles.getSupplyPrice().doubleValue())*0.15+pStyles.getDeputyPrice().doubleValue());
						//四舍五入取整
						BigDecimal f3 =new BigDecimal(df.format(pStyles.getAgentPrice())).setScale(0, BigDecimal.ROUND_HALF_UP);
						pVo.setAgentPrice(f3.toString());
					}else{
						pVo.setAgentPrice(df.format(pStyles.getAgentPrice()));
					}
					AuditVOsList.add(pVo);
				}
			}
		}
		return AuditVOsList;
		/*
		 * List<Long> ids =
		 * baseDAO.find("SELECT stylesId from PProductStyles where productId=?",
		 * productId); Map<String, Object> pMap = new HashMap<String, Object>();
		 * if(ids!=null&&ids.size()>0){ pMap.put("ids", ids); } List<Long>
		 * ablist = baseDAO.findByMap(
		 * "select attributeId from PProductStyleKv where stylesId in (:ids)",
		 * pMap); Map<String, Object> pMaps = new HashMap<String, Object>();
		 * if(ablist!=null&&ablist.size()>0){ pMap.put("attributeId", pMaps); }
		 * String hql =
		 * "select a.attributeName as attributeName,b.value as value from PProductSellKey a,PProductSellValue b WHERE a.attributeId=b.attributeId and a.attributeId in(:pMaps)"
		 * ; return baseDAO.find(hql.toString(), productId);
		 */
	}

	@Override
	public ReturnModel saveProductAudit(List<TrialStylesDTO> dto) {
		ReturnModel returnModel = new ReturnModel();
		List min1 = new ArrayList();
		List min2 = new ArrayList();
		List min3 = new ArrayList();
		if (dto != null && dto.size() > 0) {
			for (TrialStylesDTO tDto: dto) {
				if(tDto.getDukePrice()!=null&&tDto.getDukePrice().doubleValue()>0&&tDto.getDeputyPrice()!=null&&tDto.getDeputyPrice().doubleValue()>0&&tDto.getAgentPrice()!=null&&tDto.getAgentPrice()>0){
					min1.add(tDto.getDukePrice());
					min2.add(tDto.getDeputyPrice());
					min3.add(tDto.getAgentPrice());
					baseDAO.update("update PProductStyles set dukePrice=?,deputyPrice=?,agentPrice=? where stylesId=? and productId=?,", tDto.getDukePrice(),tDto.getDeputyPrice(),tDto.getAgentPrice(),tDto.getStylesId(),tDto.getProductId());
				}else{
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("保存失败!价格不能小于零!");
					return returnModel;
				}
			}
			baseDAO.update("update PProducts set dukePrice=?,deputyPrice=?,agentPrice=? where productId=?",getMinDouble(min1),getMinDouble(min2),getMinDouble(min3), dto.get(0).getProductId());
		/*	baseDAO.update("update PProducts set publishType=2 where productId=?", dto
							.get(0).getProductId());*/
			returnModel.setStatu(ReturnStatus.Success);
			returnModel.setStatusreson("保存成功!");
			return returnModel;
		} else {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("保存失败!");
			return returnModel;
		}
	}
	@Override
	public List<PictureAdModel> findListAdModels() {
		List<PictureAdModel> list = new ArrayList<PictureAdModel>();
		List<DAdInfo> dlist = baseDAO.find("from DAdInfo");
		if(dlist!=null&&dlist.size()>0){
			for (DAdInfo dAdInfo : dlist) {
				PictureAdModel pModel = new PictureAdModel();
				pModel.setAdId(dAdInfo.getAdId());
				pModel.setImageUrl(dAdInfo.getImageUrl());
				pModel.setPositionType(dAdInfo.getPositionType());
				pModel.setPublishTime(DateUtils.format(dAdInfo.getPublishTime(), "yyyy-MM-dd"));
				pModel.setTitle(dAdInfo.getTitle());
				pModel.setUrl(dAdInfo.getWapUrl());
				list.add(pModel);
			}
		}
		return list;
	}

	@Override
	public ReturnModel saveDAdInfo(DAdInfo dAdInfo) {
		ReturnModel returnModel = new ReturnModel();
		if(dAdInfo!=null){
			if(dAdInfo.getAdId()!=null&&dAdInfo.getAdId().intValue()>0){
				baseDAO.delete(dAdInfo);
			}
			baseDAO.save(dAdInfo);
			returnModel.setStatu(ReturnStatus.Success);
			returnModel.setStatusreson("保存成功!");
			return returnModel;
		}else{
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("保存失败!");
			return returnModel;
		}
		
	}

	@Override
	public ReturnModel saveDBrandAgentInfo(DBrandAgentInfo dbaif) {
		ReturnModel returnModel = new ReturnModel();
		if(dbaif!=null&&dbaif.getBrandId()!=null&&dbaif.getBrandId().intValue()>0){
			DBrandAgentInfo dAgentInfo =baseDAO.get(DBrandAgentInfo.class, dbaif.getBrandId());
			if(dAgentInfo!=null){
				baseDAO.delete(dAgentInfo);
			}
			baseDAO.save(dbaif);
			returnModel.setStatu(ReturnStatus.Success);
			returnModel.setStatusreson("保存成功!");
			return returnModel;
		}else{
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("保存失败!");
			return returnModel;
		}
	}
	 public static double getMinDouble(List list){
	      double min = (double) Collections.min(list);
	      return min;
	 }

}
