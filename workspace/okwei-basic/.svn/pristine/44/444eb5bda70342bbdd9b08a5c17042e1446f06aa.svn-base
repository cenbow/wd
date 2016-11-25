package com.okwei.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.AppPscoreKing;
import com.okwei.bean.domain.AppPscoreLogTemp;
import com.okwei.bean.domain.AppPscoreStatics;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TBatchMarket;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.ScoreEnum;
import com.okwei.bean.enums.ScoreKingType;
import com.okwei.bean.enums.ScoreType;
import com.okwei.bean.enums.SupplierTypeEnum;
import com.okwei.dao.IScoreDao;
import com.okwei.service.IScoreManagerService;
import com.okwei.util.BitOperation;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;
@Service
public class ScoreManagerService extends BaseService implements IScoreManagerService{

	@Autowired
	private IScoreDao scoreDao;
	
	@Override
	public int UP_ScoreAdd_process(long productid, long weiid, long msgId, ScoreType type, int score) {
		Date nowtime = new Date();
		Calendar endDate = new GregorianCalendar();
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		long time1 = endDate.getTime().getTime();
		long time2 = nowtime.getTime();
		int restSecond = ParseHelper.toInt(String.valueOf((time1 - time2) / 1000));
		// 今天是否有加分
		String key_name = "score_weiid_" + weiid + "_pid_" + productid + "_t_" + type;
		// 今天是否已经加过分， 一个商品一天只能加分一次
		int key_val = ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key_name)));
		if (key_val <= 0 || type.equals(ScoreType.Dianzan) || type.equals(ScoreType.Order)) {
			switch (type) {
			case Order:
				if (score >= ScoreEnum.order)
					score = ScoreEnum.order;
				break;
			case Pinglun:
				score = ScoreEnum.pinglun;
				break;
			case Onshelve:
				score = ScoreEnum.onshelve;
				break;
			case Dianzan:
				score = ScoreEnum.zan;
				break;
			case Collect:
				score = ScoreEnum.collect;
				break;
			default:// 其他
				return 0;
			}
			
			PProducts product = scoreDao.get(PProducts.class, productid);
			if (product == null)
				return 0;
			// 插入 积分记录表 插入 当天 记录表
			AppPscoreLogTemp log = new AppPscoreLogTemp();
			log.setProductId(productid);
			log.setWeiID(weiid); 
			log.setScore((double) score);
			log.setScoreType(Short.parseShort(type.toString()));
			log.setCreateTime(nowtime);
			log.setSupplyWeiid(product.getSupplierWeiId());
			USupplyer supplyer = scoreDao.get(USupplyer.class, product.getSupplierWeiId());
			if (supplyer == null || supplyer.getType() == null || supplyer.getType() <= 1)
				return 0;
			short supplyType = 0;
			if (product.getSupperType() != null) {
				if (BitOperation.isSupplyNew(supplyer.getType(), SupplierTypeEnum.BatchSupplier)) {
					supplyType = 1;
				} else {
					supplyType = 2;
				}
			}
			log.setSupplyType(supplyType);
			scoreDao.save(log);
			RedisUtil.setObject(key_name, 1, restSecond);
			int bmid=0;
			int areaid=0;
			// 更新 得分统计表 （当日）
			AppPscoreStatics todayStatics = scoreDao.get_AppPscoreStatics(productid, ScoreKingType.Today);
			if (todayStatics != null) {
				todayStatics.setScore(todayStatics.getScore()==null?score:todayStatics.getScore() + score);
				scoreDao.update(todayStatics);
				if(supplyType==1){
					if(product.getBmid()!=null){
						bmid=product.getBmid();
					}else {
						return 0;
					}
				}else if (supplyType==2) {
					areaid=todayStatics.getAreaId()==null?0:todayStatics.getAreaId();
				}
			} else {
				todayStatics = new AppPscoreStatics();// 当天
				TBatchMarket market = null;
				UYunSupplier yunSupplier = null;
				if (supplyType == 1) {
					if(product.getBmid()!=null){
						bmid=product.getBmid();
						market = scoreDao.get(TBatchMarket.class, bmid);
					}else {
						return 0;
					}
					
				} else if (supplyType == 2) {
					yunSupplier = scoreDao.get(UYunSupplier.class, product.getSupplierWeiId());
					if(yunSupplier!=null){
						areaid=yunSupplier.getCity();
						todayStatics.setAreaId(areaid);
						todayStatics.setProvince(yunSupplier.getProvince());
						todayStatics.setCity(yunSupplier.getCity());
						todayStatics.setDistrict(yunSupplier.getDistrict()); 
					}
				}
				todayStatics.setBrandID(product.getBrandId()); 
				todayStatics.setProductId(productid);
				todayStatics.setScore((double) score);
				todayStatics.setType(Short.parseShort(ScoreKingType.Today.toString()));
				todayStatics.setSupplyType(supplyType);
				todayStatics.setSupplyWeiid(product.getSupplierWeiId());
				if (supplyType == 1 && market != null) {
					todayStatics.setMid(bmid);
				} else if (yunSupplier != null) {
					todayStatics.setAreaId(areaid);
				} 
				scoreDao.save(todayStatics);
			}
			AppPscoreKing king = scoreDao.get_AppPscoreKing(Short.parseShort(ScoreKingType.Today.toString()), productid);
			if (king != null) {
				king.setScore(king.getScore()==null?score:king.getScore() + score);
				scoreDao.update(king);
				return 1;
			}
			List<AppPscoreKing> kings = null;
			TBatchMarket market = null;// 市场信息（如果是批号发供应商，market不为null ）
			// short supType = 0;// 1 批发号得分王，2工厂号得分王
			if (supplyType == 1) {// 批发号得分王
				kings = scoreDao.find_AppPscoreKings(bmid, null);
				market = scoreDao.get(TBatchMarket.class, bmid);

			} else if (supplyType == 2) {// 工厂号 得分王
				kings = scoreDao.find_AppPscoreKings(null, areaid);
			} else {// 该产品的所有者既不是 批发号供应商，也不是工厂号供应商
				return 0;
			}
			boolean take = false;
			if (kings != null && kings.size() > 0) {
				for (AppPscoreKing kk : kings) {
					if (kk.getType() == Short.parseShort(ScoreKingType.Today.toString())) {
						if (todayStatics.getScore() > kk.getScore()) {
							take = true;
							scoreDao.delete(kk);
						}
					}
				}
			} else {
				take = true;
			}
			if (take) {// 插入新的得分王
				AppPscoreKing newKing = new AppPscoreKing();
				newKing.setProductId(productid);
				newKing.setSupplyWeiid(todayStatics.getSupplyWeiid());
				newKing.setMid(todayStatics.getMid());
				newKing.setSupplyType(supplyType);
				newKing.setAreaId(todayStatics.getAreaId());
				newKing.setScore(todayStatics.getScore());
				if (supplyType == 1 && market != null) {
					newKing.setProvince(market.getProvince());
					newKing.setCity(market.getCity());
					newKing.setDistrict(market.getDistrict());
				}
				newKing.setType(todayStatics.getType());
				scoreDao.save(newKing);
			}
			
		}
		return 1;
	}

}
