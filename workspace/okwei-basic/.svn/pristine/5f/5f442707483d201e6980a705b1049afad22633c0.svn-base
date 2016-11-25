package com.okwei.service.user;

import com.okwei.bean.enums.UserIdentityType;

/**
 * 用户权限管理
 * 
 * @author Administrator
 *
 */
public interface IUserIdentityManage
{
    /**
     * 给用户添加权限
     * 
     * @param weiid
     *            用戶微店号
     * @param type
     *            用户权限标识
     * @return
     */
    boolean addIdentity(long weiid,UserIdentityType type);

    /**
     * 移除用户权限
     * 
     * @param weiid
     *            用户微店号
     * @param type
     *            用户权限标识
     * @return
     */
    boolean removeIdentity(long weiid,UserIdentityType type);
    /**
     * 获取用户身份值
     * @param weiid
     * @return
     */
    int getIdentityType(long weiid);
    /**
     * 添加UUserAssist表
     * @param weiid 微店号
     * @return
     */
    boolean insertUUserAssist(long weiid);
}
