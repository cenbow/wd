package com.okwei.service.impl.share;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.enums.ShareStatus;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.share.ProductsVO;
import com.okwei.bean.vo.share.SMainDataDetailsVO;
import com.okwei.bean.vo.share.SProductsVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.dao.share.IBasicShareDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.share.IBasicShareServices;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;


@Service
public class BasicShareService extends BaseService implements IBasicShareServices{
	
	@Autowired
	private IBasicShareDAO basicShareDAO;
	
	@Autowired
	private IBaseProductDao baseProductDao;
	  

	@Override
	public void addShareCount(long weiId,long shareId,int type) {
		SShareActive sShareActive = basicShareDAO.getSShareActive(shareId, weiId);
		if (sShareActive==null) {
			//添加分享记录
			SShareActive sa =new SShareActive();
			sa.setCreateTime(new Date());
			 SMainData sMainData = basicShareDAO.get(SMainData.class, shareId);
			 if (sMainData!=null) {
				 sa.setMakeWeiId(sMainData.getWeiId());
			}
			sa.setWeiId(weiId);
			sa.setStatus((short)1);
			sa.setShareId(shareId);
			basicShareDAO.save(sa);
			} 
			//添加分享次数
			SStatics sStatics = basicShareDAO.get(SStatics.class, shareId);
			if (sStatics!=null) {
				int intValue =0;
				if (type==1) { 
					intValue =sStatics.getWebSv()==null?1:sStatics.getWebSv()+1;
					sStatics.setWebSv(intValue);
				}else if (type==2) {
					intValue =sStatics.getWapSv()==null?1:sStatics.getWapSv()+1;
					sStatics.setWapSv(intValue);
				}else if (type==3) {
					intValue =sStatics.getAppSv()==null?1:sStatics.getAppSv()+1;
					sStatics.setAppSv(intValue);
				}   
				basicShareDAO.update(sStatics);
			}else{
				sStatics = new SStatics();
				sStatics.setWeiId(weiId);
				if (type==1) { 
					sStatics.setWebSv(1);
				}else if (type==2) {
					sStatics.setWapSv(1);
				}else if (type==3) {
					sStatics.setAppSv(1);
				} 
				sStatics.setWebPv(0);
				sStatics.setAppPv(1);
				sStatics.setShareId(shareId);
				sStatics.setWapPv(0);
				sStatics.setTurnover(0.0);
				sStatics.setVol(0);
				sStatics.setCommission(0.0);
				Long count=basicShareDAO.count("select count(*) from SProducts s where s.shareId=? and s.weiId=?", shareId,weiId); 
				sStatics.setPcount(ParseHelper.toInt(count.toString()));
				basicShareDAO.save(sStatics);
			} 
		
	}
	
	
	@Override
	public SMainDataDetailsVO getSMainDataDetails(long shareId,long weiId,Limit limit,int type) {
		SMainDataDetailsVO sddv=new SMainDataDetailsVO();
		PageResult<SProductsVO> productsList=new PageResult<SProductsVO>();
		if (shareId<1) {
			return null;
		}
		SMainData sMainData = basicShareDAO.get(SMainData.class, shareId);
		SStatics sStatics = basicShareDAO.get(SStatics.class, shareId);
		if (sStatics!=null) {
			if (type==1) {
				sStatics.setWebPv(sStatics.getWebPv()==null?1:sStatics.getWebPv()+1);
			}else if (type==2) {
				sStatics.setWapPv(sStatics.getWapPv()==null?1:sStatics.getWapPv()+1);
			}else if (type==3) {
				sStatics.setAppPv(sStatics.getAppPv()==null?1:sStatics.getAppPv()+1);
			} 
			basicShareDAO.update(sStatics);
		}else{
			sStatics = new SStatics();
			sStatics.setWeiId(weiId);
			if (type==1) {
				sStatics.setWebPv(1);
			}else if (type==2) {
				sStatics.setWapPv(1);
			}else if (type==3) {
				sStatics.setAppPv(1);
			} 
			sStatics.setWebSv(0);
			sStatics.setAppSv(0);
			sStatics.setShareId(shareId);
			sStatics.setWapSv(0);
			sStatics.setTurnover(0.0);
			sStatics.setVol(0);
			sStatics.setCommission(0.0);
			Long count=basicShareDAO.count("select count(*) from SProducts s where s.shareId=? and s.weiId=?", shareId,weiId); 
			sStatics.setPcount(ParseHelper.toInt(count.toString()));
			basicShareDAO.save(sStatics);
		}
		PageResult<SProducts> findSProductsList = basicShareDAO.findSProductsList(shareId, limit);
		List<SProducts> list = findSProductsList.getList();
		//将接受数据 展示出去
		List<SProductsVO> productsVOList=new ArrayList<SProductsVO>();
		if (sMainData!=null) {
		//TODO 二维码 暂时不用写入   直接接口获得
		sddv.setQRCodeUrl(""); 
		sddv.setDescrible(sMainData.getDescrible());
		sddv.setTitle(sMainData.getTitle());
		sddv.setShareType(sMainData.getShareType()==null?1:sMainData.getShareType());
		sddv.setShareId(sMainData.getShareId());
		SShareActive shareActive = basicShareDAO.getSShareActive(shareId,weiId);
		if (shareActive!=null) {
			sddv.setMakeWeiID(shareActive.getMakeWeiId());
		}
		//获取排序完成后的商品Id信息
		List<Long> productIds = new ArrayList<Long>();
		if (list!=null&&list.size()>0) {
			for (SProducts sProducts : list) {
				productIds.add(sProducts.getProductId());
			}
		} 
		if (productIds!=null&&productIds.size()>0) {
			//根据商品Id获取商品信息
			List<PProducts> findProductlistByIds = baseProductDao.findProductlistByIds(productIds, null);
			for (SProducts sProducts : list) {
			for (PProducts pProducts : findProductlistByIds) {
				if (sProducts.getProductId().longValue()==pProducts.getProductId().longValue()) {
					SProductsVO spv=new SProductsVO();
					spv.setDefaultImg(ImgDomain.GetFullImgUrl(pProducts.getDefaultImg(),24));
					spv.setPresentPrice(pProducts.getDefaultPrice());
					if(null!=pProducts.getOriginalPrice()){
						spv.setOriginalPrice(pProducts.getOriginalPrice());
				    }else{
				    	double percent=1.5;
				    	double displayPrice = pProducts.getDefaultPrice()*percent;
				    	DecimalFormat df = new DecimalFormat("#.00");
				    	displayPrice =Double.parseDouble(df.format(displayPrice));
				    	spv.setOriginalPrice(pProducts.getOriginalPrice() == null ? displayPrice : pProducts.getOriginalPrice());
				    } 
					spv.setProductTitle(pProducts.getProductTitle());
					spv.setProductId(pProducts.getProductId());
					spv.setShareDescription(sProducts.getDescription());
					short shareType=sMainData.getShareType()==null?(short)1:sMainData.getShareType().shortValue();
			    	    //如果是单品时  添加一个新的对象  图片详情和描述  
			    	    if (shareType==ParseHelper.toShort(ShareTypeEnum.SingleQuality.toString())) {
			    	    	List<ProductsVO> list1=new ArrayList<ProductsVO>();
			    	    	//根据产品Id获取 商品详情
			    	    	List<SSingleDesc> findSSingleDescByProducts = basicShareDAO.findSSingleDescByProducts(sProducts.getSpid());
			    	    	if (findSSingleDescByProducts != null && findSSingleDescByProducts.size() > 0) {
			    	    		for (SSingleDesc sSingleDesc : findSSingleDescByProducts) {
			    	    			ProductsVO vo=new ProductsVO();
			    	    			vo.setSid(sSingleDesc.getSid());
			    	    			vo.setShareDescription(sSingleDesc.getDescription());
			    	    			vo.setProductPicture(ImgDomain.GetFullImgUrl(sSingleDesc.getImageUrl(),75)); 
			    	    			list1.add(vo);
			    				}
			    	    		spv.setProductPictureList(list1);
			    	    	}
			    	    }
					productsVOList.add(spv);
					//TODO
					break;
				}
			}
		} 	
		}
		}
		productsList.setList(productsVOList);
		productsList.setPageCount(findSProductsList.getPageCount());
		productsList.setPageIndex(findSProductsList.getPageIndex());
		productsList.setPageSize(findSProductsList.getPageSize());
		productsList.setTotalCount(findSProductsList.getTotalCount());
		productsList.setTotalPage(findSProductsList.getTotalPage());
		sddv.setProductsList(productsList);
		 
		return sddv;
	}
 

	@Override
	public ReturnModel updateSMainData(long weiId,long shareOne,long shareId) {
		ReturnModel rm=new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("删除成功！"); 
		//逻辑删除分享信息  运营发布的分享页 发布人weiid都是 111L  
		SShareActive sShareActive = basicShareDAO.getSShareActive(shareId,shareOne);
		if (sShareActive!=null&&sShareActive.getStatus().shortValue()!=0) {
			sShareActive.setStatus(ParseHelper.toShort(ShareStatus.delete.toString()));
			basicShareDAO.update(sShareActive);
			//这里当不是自己制作的分享  就不能删除
			if (sShareActive.getMakeWeiId().longValue()==weiId) {
				SMainData sSMainData = basicShareDAO.get(SMainData.class, shareId);
				sSMainData.setStatus(ParseHelper.toShort(ShareStatus.delete.toString()));
				basicShareDAO.update(sSMainData);
				//如果制作人删除了该分享  则所有分享删除 
				basicShareDAO.updateShareActive(shareId, sShareActive.getMakeWeiId());
			}
		}else{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("数据不存在,删除失败！");
		} 
		return rm;
	}

 


	
 


}
