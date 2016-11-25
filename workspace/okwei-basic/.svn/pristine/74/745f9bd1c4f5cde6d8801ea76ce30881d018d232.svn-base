package com.okwei.service.impl.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DAgentTop;
import com.okwei.bean.domain.DBrandAgentInfo;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.DBrandsInfo;
import com.okwei.bean.domain.PBrandShevle;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TCountStat;
import com.okwei.bean.domain.TTasteApply;
import com.okwei.bean.domain.TTasteSummer;
import com.okwei.bean.dto.TasteApplyDTO;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.vo.AgentProductVO;
import com.okwei.bean.vo.AgentUserInfoVO;
import com.okwei.bean.vo.AgentVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.agent.IDAgentMgtDao;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.dao.user.IAgentDAO;
import com.okwei.service.agent.IAgentBrandService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ImgDomain;
import com.okwei.util.RedisUtil;
import com.okwei.util.StringHelp;

@Service
public class AgentBrandServiceImpl extends BaseService implements IAgentBrandService {

	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private IBaseProductDao productDao;
	@Autowired
	private IDAgentMgtDao agentDao;
	
	public List<AgentVO> getAgentList(int type) {
		String AgentRedis = "AGENT_List1021_" + type;
		List<AgentVO> li = (List<AgentVO>) RedisUtil.getObject(AgentRedis);
		if (li != null && li.size() > 0) {
			return li;
		}
		List<DBrands> bli = baseDAO.find(" from DBrands b where b.type=? and b.status=1", type);
		if (bli == null || bli.size() <= 0) {
			return null;
		}
		List<AgentVO> list = new ArrayList<AgentVO>();
		for (DBrands db : bli) {
			AgentVO av = new AgentVO();
			av.setBrandid(db.getBrandId());

			AgentUserInfoVO ai = new AgentUserInfoVO();
			ai.setWeiid(db.getWeiId());
			ai.setBrandname(db.getBrandName());
			// 鑾峰彇鍒嗛攢鍙蜂俊鎭�
			DBrandsInfo dbi = baseDAO.get(DBrandsInfo.class, db.getWeiId());
			if (dbi == null)
				continue;
			ai.setQq(dbi.getQq());// ?娌℃湁qq
			String qrurl = AppSettingUtil.getSingleValue("AgentQrUrl");
			ai.setQrurl(qrurl.replace("{weiid}", db.getWeiId().toString()));// 浜岀淮鐮乽rl
																			// 搴楅摵鍦板潃
			ai.setImgurl(db.getLogo());
			String agenturl = AppSettingUtil.getSingleValue("AgentApplyUrl");
			ai.setAgenturl(agenturl + db.getBrandId());
			// 缁熻浠ｇ悊鍟嗘暟閲�
			long agentcount = baseDAO.count(" select count(*) from DAgentInfo d where d.brandId=?", db.getBrandId());
			ai.setAgentcount(Integer.parseInt(String.valueOf(agentcount)));

			// 缁熻浜у搧鏁伴噺
			long productcount = baseDAO.count(" select count(*) from PBrandShevle p where p.brandId=? and p.type=1", db.getBrandId());
			ai.setProductcount(Integer.parseInt(String.valueOf(productcount)));
			av.setUser(ai);
			ResourceBundle bundle = ResourceBundle.getBundle("domain");
			String domain = bundle.getString("okweidomain");
			av.setAgenturl("http://" + db.getWeiId() + "." + domain);// 搴楅摵椤�

			List<AgentProductVO> product = new ArrayList<AgentProductVO>();
			List<PBrandShevle> pbli = baseDAO.findPage(" from PBrandShevle p where p.brandId=? and p.type=1 order by p.createTime desc", 0, 4, db.getBrandId());
			if (pbli != null && pbli.size() > 0) {
				for (PBrandShevle p : pbli) {
					AgentProductVO apv = new AgentProductVO();
					PProducts pp = baseDAO.get(PProducts.class, p.getProductId());
					if(pp!=null){
						apv.setImgurl(ImgDomain.GetFullImgUrl(pp.getDefaultImg(), 24));
						apv.setPrice(pp.getDefaultPrice());
						apv.setProductid(p.getProductId());
						apv.setTitle(pp.getProductTitle());
						apv.setPid(p.getBid());
						product.add(apv);
					}
				}
			}
			av.setProduct(product);
			list.add(av);
			continue;
		}
		RedisUtil.setObject(AgentRedis, list, 1800);
		return list;
	}
	
	@Override
	public List<AgentVO> getAgentList(int type,Long weiId) {
		String AgentRedis = "AGENT_List_" + type;
		List<AgentVO> li = (List<AgentVO>) RedisUtil.getObject(AgentRedis);
		if (li != null && li.size() > 0) {
			return li;
		}
		List<DBrands> bli = baseDAO.find(" from DBrands b where b.type=? and b.status=1", type);
		if (bli == null || bli.size() <= 0) {
			return null;
		}
		List<AgentVO> list = new ArrayList<AgentVO>();
		for (DBrands db : bli) {
			AgentVO av = new AgentVO();
			av.setBrandid(db.getBrandId());

			AgentUserInfoVO ai = new AgentUserInfoVO();
			ai.setWeiid(db.getWeiId());
			ai.setBrandname(db.getBrandName());
			// 获取分销号信息
			DBrandsInfo dbi = baseDAO.get(DBrandsInfo.class, db.getWeiId());
			if (dbi == null)
				continue;
			ai.setQq(dbi.getQq());// ?没有qq
			String qrurl = AppSettingUtil.getSingleValue("AgentQrUrl");
			ai.setQrurl(qrurl.replace("{weiid}", db.getWeiId().toString()));// 二维码url
																			// 店铺地址
			ai.setImgurl(db.getLogo());
			String agenturl = AppSettingUtil.getSingleValue("AgentApplyUrl");
			ai.setAgenturl(agenturl + db.getBrandId());
			// 统计代理商数量
			long agentcount = baseDAO.count(" select count(*) from DAgentInfo d where d.brandId=?", db.getBrandId());
			ai.setAgentcount(Integer.parseInt(String.valueOf(agentcount)));

			// 统计产品数量
			long productcount = baseDAO.count(" select count(*) from PBrandShevle p where p.brandId=? and p.type=1", db.getBrandId());
			ai.setProductcount(Integer.parseInt(String.valueOf(productcount)));
			av.setUser(ai);
			ResourceBundle bundle = ResourceBundle.getBundle("domain");
			String domain = bundle.getString("okweidomain");
			av.setAgenturl("http://" + db.getWeiId() + "." + domain);// 店铺页
			//判断登陆人的身份是否为代理
			DAgentInfo daifo=baseDAO.getNotUniqueResultByHql("from DAgentInfo where brandId=? and weiId=?", db.getBrandId(),weiId);
			if(daifo!=null){
				av.setDaili(1);
			}else{
				av.setDaili(0);
			}
			
			List<AgentProductVO> product = new ArrayList<AgentProductVO>();
			List<PBrandShevle> pbli = baseDAO.findPage(" from PBrandShevle p where p.brandId=? and p.type=1 order by p.createTime desc", 0, 4, db.getBrandId());
			if (pbli != null && pbli.size() > 0) {
				for (PBrandShevle p : pbli) {
					AgentProductVO apv = new AgentProductVO();
					PProducts pp = baseDAO.get(PProducts.class, p.getProductId());
					apv.setImgurl(ImgDomain.GetFullImgUrl(pp.getDefaultImg(), 24));
					if(daifo!=null&&daifo.getLevel()!=null){
						switch (daifo.getLevel()) {
						case 1:apv.setPrice(pp.getDukePrice());;
						
						case 2:apv.setPrice(pp.getDeputyPrice());;
						
						case 3:apv.setPrice(pp.getAgentPrice());;
							
						}
					}else{
						apv.setPrice(pp.getDefaultPrice());
					}
					apv.setProductid(p.getProductId());
					apv.setTitle(pp.getProductTitle());
					apv.setPid(p.getBid());
					product.add(apv);
				}
			}
			av.setProduct(product);
			list.add(av);
			continue;
		}
		RedisUtil.setObject(AgentRedis, list, 1800);
		return list;
	}

	@Override
	public List<AgentProductVO> getTopAgentList() {
		List<DAgentTop> list = baseDAO.loadAll(DAgentTop.class);
		if (list == null || list.size() <= 0)
			return null;
		List<AgentProductVO> li = new ArrayList<AgentProductVO>();
		for (DAgentTop da : list) {
			AgentProductVO av = new AgentProductVO();
			av.setPid(da.getPbid());
			PProducts pp = baseDAO.get(PProducts.class, da.getProductId());
			if (pp == null)
				continue;
			av.setImgurl(ImgDomain.GetFullImgUrl(pp.getDefaultImg(), 24));
			av.setProductid(da.getProductId());
			av.setPrice(pp.getDefaultPrice());
			li.add(av);
		}
		return li;
	}
	
	@Override
	public DBrandAgentInfo getDBrandAgentInfo(Integer brandId){
		DBrandAgentInfo brandAgentInfo=baseDAO.get(DBrandAgentInfo.class,brandId);
		return brandAgentInfo;
	}
	
	@Override
	public List<AgentProductVO> getAgentProductByPids(List<Long> productIds,Long weiid,Integer brandid,String type){
		
		String AgentProductRedis = "AGENT_ProductList_" + type+"_"+weiid+"_"+brandid;
		List<AgentProductVO> li = (List<AgentProductVO>) RedisUtil.getObject(AgentProductRedis);
		if (li != null && li.size() > 0) {
			return li;
		}
		List<AgentProductVO> list = new ArrayList<AgentProductVO>();
		List<PProducts> prodsS =productDao.findProductlistByIds(productIds, Short.parseShort(ProductStatusEnum.Showing.toString()));
		
		DAgentInfo agentinfo=agentDao.getDAgentInfo(weiid,brandid);
		if (prodsS == null || prodsS.size() <= 0)
			return null;
		for (PProducts pp : prodsS) {
			AgentProductVO av = new AgentProductVO();
			av.setImgurl(ImgDomain.GetFullImgUrl(pp.getDefaultImg(), 24));
			av.setProductid(pp.getProductId());
			av.setTitle(pp.getProductTitle());
			
			if(agentinfo!=null&&agentinfo.getLevel()==1){
				av.setPrice((pp.getDukePrice()==null)?0.0:pp.getDukePrice());
			}else if(agentinfo!=null&&agentinfo.getLevel()==2){
				av.setPrice((pp.getDeputyPrice()==null)?0.0:pp.getDeputyPrice());
			}else if(agentinfo!=null&&agentinfo.getLevel()==3){
				av.setPrice((pp.getAgentPrice()==null)?0.0:pp.getAgentPrice());
			}else{
				av.setPrice((pp.getDefaultPrice()==null)?0.0:pp.getDefaultPrice());
			}
			list.add(av);
		}
		RedisUtil.setObject(AgentProductRedis, li, 1800);
		return list;
	}
	

	@Override
	public int getTasteCount() {
		TCountStat tc=baseDAO.getUniqueResultByHql(" from TCountStat t where t.name=?", "FreeTaste");
		if(tc!=null)
		{
			return Integer.parseInt(String.valueOf(tc.getCountNum()));
		}
		else
		{
			tc= new TCountStat();
			tc.setCountNum(0L);
			tc.setName("FreeTaste");
			baseDAO.save(tc);
			return 0;
		}
	}

	@Override
	public boolean checkTaste(long weiid, short type) {
		boolean b=false;
		TTasteSummer ts= baseDAO.getUniqueResultByHql(" from TTasteSummer t where t.weiId=? and t.tasteType=?", weiid,type);
		if(ts!=null)
			return true;
		return false;
	}
	
	@Override
	public ReturnModel saveTasteApply(TasteApplyDTO ta){
		ReturnModel rm=new ReturnModel();
		TTasteApply tapply=new TTasteApply();
		tapply.setCity(ta.getCity());
		tapply.setCreateTime(new Date());
		tapply.setDetailAddress(ta.getDetailAddress());
		tapply.setDistrict(ta.getDistrict());
		tapply.setEmail(ta.getEmail());
		tapply.setHighNum(ta.getHighNum());
		tapply.setIsHighXueTang(ta.getIsHighXueTang()==null?0:ta.getIsHighXueTang());
		tapply.setIsHighXueYa(ta.getIsHighXueYa()==null?0:ta.getIsHighXueYa());
		tapply.setIsHighXueZhi(ta.getIsHighXueZhi()==null?0:ta.getIsHighXueZhi());
		tapply.setIsMyself(ta.getIsMyself()==null?0:ta.getIsMyself());
		tapply.setIsZhongFeng(ta.getIsZhongFeng()==null?0:ta.getIsZhongFeng());
		tapply.setLowNum(ta.getLowNum());
		tapply.setName(ta.getName());
		tapply.setPhone(ta.getPhone());
		tapply.setProvince(ta.getProvince());
		tapply.setRelationship(ta.getRelationship());
		tapply.setXueTangNum(ta.getXueTangNum());
		tapply.setXueZhiNum(ta.getXueZhiNum());
		String tkimages=ta.getTkimages();
		if(!StringHelp.IsNullOrEmpty(tkimages)){
			String images[]=tkimages.split(",");
			
			if(images!=null&&images.length>0){
				tapply.setImageOne(images.length>=1?images[0]:"");
				tapply.setImageTwo(images.length>=2?images[1]:"");
				tapply.setImageThree(images.length>=3?images[2]:"");
			}
		}
		baseDAO.save(tapply);
		TTasteSummer tt= new TTasteSummer();
		tt.setTasteType((short) 1);
		tt.setWeiId(ta.getWeiid());
		
		TCountStat tc=baseDAO.getUniqueResultByHql(" from TCountStat t where t.name=?", "FreeTaste");
		if(tc!=null)
		{
			tc.setCountNum(tc.getCountNum()+1);;
		}
		else
		{
			tc= new TCountStat();
			tc.setCountNum(1L);
			tc.setName("FreeTaste");
		}
		baseDAO.saveOrUpdate(tc);
		baseDAO.save(tt);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("提交资料成功");
		return rm;
	}

}
