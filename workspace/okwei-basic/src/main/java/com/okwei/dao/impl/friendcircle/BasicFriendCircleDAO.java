package com.okwei.dao.impl.friendcircle;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.TMsgShielded;
import com.okwei.bean.domain.TWeiFans;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UFriends;
import com.okwei.bean.domain.UFriendsApply;
import com.okwei.dao.friendcircle.IFriendCircleDAO;
import com.okwei.dao.impl.BaseDAO;


@Repository
public class BasicFriendCircleDAO extends BaseDAO implements IFriendCircleDAO  {

	@Override
	public UFriends getUFriends(long weiId, long friendId){
		 String hql=" from UFriends s where s.weiId=? and s.friendId=? ";
		 return super.getUniqueResultByHql(hql, weiId,friendId);
	}
	
	@Override
	public TWeiFans getTWeiFans(long weiId, long friendId){
		 String hql=" from TWeiFans s where s.weiId=? and s.fanWeiId=? ";
		 return super.getUniqueResultByHql(hql, weiId,friendId);
	}
	
	@Override
	public boolean deleteDisturb(Long myweiNO, Long weiNo) {
		TMsgShielded model= super.getNotUniqueResultByHql("from TMsgShielded u where u.weiId=? and u.toWeiId=?", myweiNO,weiNo);
		if(model!=null){
			super.delete(model);
		}
		return true;
	}
	
	@Override
	public boolean saveDisturb(Long myweiNO, Long weiNo) {
		TMsgShielded model= super.getNotUniqueResultByHql("from TMsgShielded u where u.weiId=? and u.toWeiId=?", myweiNO,weiNo);
		if(model!=null){
			super.delete(model);
		}
		TMsgShielded tsd=new TMsgShielded();
		tsd.setWeiId(myweiNO);
		tsd.setToWeiId(weiNo);
		super.save(tsd);
		return true;
		
	}
	@Override
    public int deleteUfrindAppayByWeiId(Long friendId) {
		String hql = "delete UFriendsApply where friendId=? ";
		Object[] params = new Object[1];
		params[0] = friendId;
		return super.executeHql(hql, params);
    }
	
	@Override
    public int deleteUfrindAppay(Long friendId,Long weiId) {
		
		String hql = "delete UFriendsApply where friendId=? and weiId=?";
		Object[] params = new Object[2];
		params[0] = friendId;
		params[1] = weiId;
		return super.executeHql(hql, params);
    }
	
	@Override
	public boolean IsExistsTfansAttention(Long weiId, Long friendId){
		String hql = " from TWeiFans w where w.weiId=? and w.fanWeiId=? ";
		TWeiFans weifans =super.getUniqueResultByHql(hql, weiId,friendId);
		if (weifans != null) {
			return true;
		} else
			return false;
	}
	
	@Override
	public UAttention getUAttentionByparam(Long weiId, Long friendId){
		String hql = " from UAttention w where w.attentioner=? and w.attTo=? ";

		UAttention uattention =super.getUniqueResultByHql(hql, friendId,weiId);
		if (uattention != null) {
			return uattention;
		} else
			return null;
	}
	
	@Override
	public UAttentioned getUAttentionedByparam(Long weiId, Long friendId){
		String hql = " from UAttentioned w where w.attentioner=? and w.attTo=? ";

		UAttentioned toattention =super.getUniqueResultByHql(hql, friendId,weiId);
		if (toattention != null) {
			return toattention;
		} else
			return null;
	}
	
}
