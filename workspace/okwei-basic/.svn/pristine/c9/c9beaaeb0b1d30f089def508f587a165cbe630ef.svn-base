package com.okwei.dao.impl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.PBrandShevle;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UPlatformSupplyerImg;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.SupplierTypeEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shop.IBasicShopMgtDAO;
import com.okwei.util.BitOperation;
import com.okwei.util.ImgDomain;

@Repository
public class BasicShopMgtDAO extends BaseDAO implements IBasicShopMgtDAO {

	@Override
	public List<PShopClass> getSubShopClass(Integer parentId) {
		return super.find("from PShopClass where state = 1 and paretId = ? order by sort", parentId);
	}

	@Override
	public List<PShopClass> getShopClass(Integer parentId, Long weiId) {
		return super.find("from PShopClass where level=1 and state = 1 and weiid = ? order by sort", weiId);
	}

	@Override
	public List<UPlatformSupplyerImg> getImgs(Long weiId) {
		String hql = "from UPlatformSupplyerImg where weiId = ? ";
		return super.find(hql, weiId);
	}

	@Override
	public String getIndustry(Long weiId) {
		StringBuilder sb = new StringBuilder();
		String hql = "select b.name from USupplierBusCategory a,TBussinessClass b where a.categoryId=b.id and a.weiId = ?";
		List<String> list = super.find(hql, weiId);
		if (null != list && list.size() > 0) {
			for (String str : list) {
				sb.append(str + " ");
			}
		}
		return sb.toString();
	}

	@Override
	public boolean getIsHaveShopName(long weiID, String shopClassName, Short level, Integer sid) {
		boolean result = false;
		if (weiID < 1) {
			return result;
		}
		String hql = "select count(*) from PShopClass p where p.weiid =:weiID and p.sname=:sName and p.level=:level";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiID", weiID);
		params.put("sName", shopClassName);
		params.put("level", level);
		if (sid != null && sid > 0) {
			hql += " and p.sid != :sid";
			params.put("sid", sid);
		}
		if (countByMap(hql, params) > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public long productCountBySid(long weiId, long classId) {
		String hql = "select count(1) from PClassProducts where (state=0 or state=1) and weiId=? and classId=?";
		return super.count(hql, weiId, classId);
	}

	@Override
	public long productCountBySid(Long weiId, Long[] scids) {
		String hql = "select count(1) from PClassProducts where (state=0 or state=1) and weiId=:weiID and classId in(:scids)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("weiID", weiId);
		params.put("scids", scids);
		return super.countByMap(hql, params);
	}


	@Override
	public UAttention getUAttention(Long weiID, Long weiNo) {
		String hql = " from UAttention w where  w.attentioner=? and w.attTo=?";
		Object[] b = new Object[2];
		b[0] = weiID;
		b[1] = weiNo;
		UAttention atten = super.getUniqueResultByHql(hql, b);
		return atten;
	}

	@Override
	public UWeiSeller getUWeiSeller(Long weiNo) {
			if (weiNo <= 0)
				return null;
			try {
				String hql = " from UWeiSeller w where w.weiId=? ";
				Object[] b = new Object[1];
				b[0] = weiNo;
				return super.getNotUniqueResultByHql(hql, b);

			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
	}
	/*
	 * 统一获取昵称，规则如下： 身份为云商通供应商，显示 "云商通"进驻公司名身份为云商通+批发号，显示"云商通"进驻公司名
	 * 身份为批发号，显示"批发号"进驻店铺名非供应商，微店昵称我的里的昵称，显示"微店昵称"，不与身份挂钩
	 */
	@Override
	public String getNickNameById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo=super.get(UShopInfo.class, weiNo);
		if(shopInfo!=null&&shopInfo.getShopName()!=null)
			return shopInfo.getShopName();
		UYunSupplier ysup = super.get(UYunSupplier.class, weiNo);
		if (ysup != null && ysup.getStatus()!=null && ysup.getStatus()==4) {
			USupplyer sup = super.get(USupplyer.class, weiNo);
			if (sup != null && !"".equals(sup.getCompanyName()))
				return sup.getCompanyName();
		}
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getStatus()!=null ) { //&& bsup.getStatus()==4
			return bsup.getShopName();
		}
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		return seller==null?"":seller.getWeiName();
	}

	@Override
	public String getShopImageByWeiId(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo = super.get(UShopInfo.class, weiNo);
		if (shopInfo != null && shopInfo.getShopImg() != null && !"".equals(shopInfo.getShopImg()))
			return ImgDomain.GetFullImgUrl(shopInfo.getShopImg(),24);
		USupplyer supplyer = super.get(USupplyer.class, weiNo);
		if (supplyer != null && supplyer.getType() != null) {
			short valueSupply = supplyer.getType();
			if (BitOperation.isSupply(valueSupply, SupplierTypeEnum.BatchSupplier)) {
				UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
				if (bsup != null && bsup.getShopPic() != null && !"".equals(bsup.getShopPic())) {
					return ImgDomain.GetFullImgUrl(bsup.getShopPic(),24);
				}
			}
			if (supplyer.getSupplierLogo() != null && !"".equals(supplyer.getSupplierLogo())) {
				return ImgDomain.GetFullImgUrl(supplyer.getSupplierLogo(),24);
			}
		}
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		if(seller!=null&&seller.getImages()!=null&&!"".equals(seller.getImages()))
			return ImgDomain.GetFullImgUrl(seller.getImages(),24);
		return "";
	}

	@Override
	public String getImageById(Long weiNo) {
		if (weiNo == null) {
			return "";
		}
		UShopInfo shopInfo=super.get(UShopInfo.class, weiNo);
		if(shopInfo!=null&&shopInfo.getShopImg()!=null&&!"".equals(shopInfo.getShopImg()))
			return ImgDomain.GetFullImgUrl(shopInfo.getShopImg());
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiNo);
		if (bsup != null && bsup.getShopPic()!=null && !"".equals(bsup.getShopPic()))
		{
			return ImgDomain.GetFullImgUrl(bsup.getShopPic());
		}
		UWeiSeller seller = super.get(UWeiSeller.class, weiNo);
		return seller==null?"":ImgDomain.GetFullImgUrl(seller.getImages());
	}

	@Override
	public String getBusContentById(Long weiId) {
		if (weiId == null) {
			return "";
		}
		UShopInfo shopInfo=super.get(UShopInfo.class, weiId);
		if(shopInfo!=null&&shopInfo.getShopBusContent()!=null&&!"".equals(shopInfo.getShopBusContent()))
			return shopInfo.getShopBusContent();
		UBatchSupplyer bsup = super.get(UBatchSupplyer.class, weiId);
		if (bsup != null && bsup.getBusContent()!=null && !"".equals(bsup.getBusContent()))
		{
			return bsup.getBusContent();
		}
		return "";
	}

	@Override
	public List<PProducts> findDbrandProducts(Long weiNo) {
		DBrandSupplier supplier=super.get(DBrandSupplier.class, weiNo);
		if(supplier!=null){
			StringBuilder sb=new StringBuilder();
			sb.append(" from PBrandShevle a where a.brandId=:brandid ");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("brandid", supplier.getBrandId());
			sb.append(" order by a.bid desc limit 3 ");
		    List<PBrandShevle> result=super.findByMap(sb.toString(), map);
		    List<PProducts> list=null;
		    if(result!=null&&result.size()>0){
		    	for (PBrandShevle p : result) {
					list=new ArrayList<PProducts>();
					list.add(super.get(PProducts.class, p.getProductId()));
				}
		    	
		    }
		    return list;
		}
		return null;
	}

}
