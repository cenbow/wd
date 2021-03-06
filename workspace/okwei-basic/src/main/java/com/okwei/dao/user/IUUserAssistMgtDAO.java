package com.okwei.dao.user;
import java.util.List;

import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.dao.IBaseDAO;


public interface IUUserAssistMgtDAO extends IBaseDAO {
	 
	 /**
	  * 这里只针对品牌号和平台号
	  * @param weiid
	  * @param identity
	  */
	 void saveUserAssist(long weiid,int identity,int trueOrFalse);
	 /**
	  * 查询用户辅助表
	  * @param weiId
	  * @return
	  */
	 UUserAssist getUUserAssist(long weiId);
	 /**
	  * 获取用户辅助表
	  * @param weiids
	  * @return
	  */
	 public List<UUserAssist> find_UUserAssist(List<Long> weiids);
	 /**
	  * 获取分销商数量
	  * @param weiid
	  * @return
	  */
	 public Long count_children(Long weiid);
	 /**
	  * 好友的数量
	  * @param weiid
	  * @return
	  */
	 public Long count_friends(Long weiid);
	 /**
	  * 获取粉丝数量
	  * @param weiid
	  * @return
	  */
	 public Long count_fans(Long weiid);
	 /**
	  * 获取店铺信息
	  * @param userids
	  * @return
	  */
	 public List<UShopInfo> find_shoplist(List<Long> userids);
	 /**
	  * 获取用户店铺电信
	  * @param weiid
	  * @return
	  */
	 public UShopInfo getShopInfo(Long weiid);
	 /**
	  * 新增用户身份
	  * zy(2016-7-7)
	  * @param weiid
	  * @param type
	  * @return
	  */
	 public boolean addIdentity(Long weiid,UserIdentityType type);
	 /**
	  * 删除身份
	  * @param weiid
	  * @param type
	  * @return
	  */
	 public boolean edit_RemoveIdentity(Long weiid,UserIdentityType type);
	 
	 
}
