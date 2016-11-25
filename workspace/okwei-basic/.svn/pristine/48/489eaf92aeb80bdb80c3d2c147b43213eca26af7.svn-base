package com.okwei.dao.user;

import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBatchPort;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenStaff;
import com.okwei.bean.domain.UChildrenSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UOtherLogin;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.enums.OtherLoginType;
import com.okwei.dao.IBaseDAO;

public interface ILoginDAO extends IBaseDAO
{
    /**
     * 根据手机、微店号。密码查询用户
     * 
     * @param username
     *            用户名
     * @param md5Pwd
     *            md5密码
     * @param aesPwd
     *            aes密码
     * @return
     */
    UWeiSeller getUWeiSeller(String username,String md5Pwd);
    UWeiSeller getUWeiSeller(String username,String md5Pwd,String desPwd);

    UWeiSeller getUWeiSeller(long weiid);

    /**
     * 修改密码
     * 
     * @param weiid
     * @param passWord
     * @return
     */
    boolean updateUWeiSeller(Long weiid,String passWord);

    /**
     * 根据微店号查询供应商
     * 
     * @param weiid
     *            微店号
     * @return
     */
    USupplyer getUSupplyer(Long weiid);

    /**
     * 根据微店号查询店铺信息
     * 
     * @param weiid
     *            微店号
     * @return
     */
    UShopInfo getUShopInfo(Long weiid);

    /**
     * 根据微店号查询认证员公用信息
     * 
     * @param weiid
     *            微店号
     * @return
     */
    UVerifier getUVerifier(Long weiid);

    /**
     * 根据微店号查询认证点
     * 
     * @param weiid
     *            微店号
     * @return
     */
    UBatchPort getUBatchPort(Long weiid);

    /**
     * 根据微店号查询云商通认证员
     * 
     * @param weiid
     * @return
     */
    UYunVerifier getUYunVerifier(Long weiid);

    /**
     * 登陆历史记录
     * 
     * @param log
     */
    void insetUWeiSellerLoginLog(UWeiSellerLoginLog log);

    /**
     * 根据手机查微店号
     * 
     * @param phone
     *            手机号码
     * @return 微店号
     */
    Long getUWeiSellerByPhone(String phone);

    /**
     * 修改微店密码
     * 
     * @param weiid
     * @param md5Pwd
     * @return
     */
    boolean updateUWeiSellerByPwd(Long weiid,String md5Pwd);

    /**
     * 添加发送记录
     * 
     * @param model
     * @return
     */
    boolean insertTVerificationId(TVerificationId model);

    /**
     * 子账户获取
     * 
     * @param username
     *            用户名
     * @param md5Pwd
     *            密码
     * @return
     */
    UChildrenUser getChildrenUser(String username,String md5Pwd);

    /**
     * 用户是否存在
     * 
     * @param weiid
     * @return
     */
    UWeiSeller getUWeiSellerByWeiId(Long weiid);

    /**
     * 添加用户
     * 
     * @param seller
     * @return
     */
    boolean insertUWeiSeller(UWeiSeller seller);

    /**
     * 判断是否绑定第三方登陆
     * 
     * @param weiid
     *            微店号
     * @return
     */
    boolean getUOtherLogin(long weiid,OtherLoginType type);
    boolean getUOtherLogin(long weiid,short  type);
    UOtherLogin getOtherLogin(long weiid,short type);
    boolean delUOtherLogin(long weiid,short  type);

    /**
     * 根据子帐号获取子帐号信息
     * 
     * @param childId
     * @return
     */
    UChildrenUser getChildrenUser(String childId);

    UChildrenSupplyer getChildrenSupplyer(String childId);

    UChildrenStaff getChildrenStaff(String childId);

    /**
     * 获取微店用户的信息
     * 
     * @param weiid
     * @return
     */
    UUserAssist getUUserAssist(long weiid);

    /**
     * 根据第三方openid和type获取第三方登陆实体
     * 
     * @param openID
     * @param type
     * @return
     */
    UOtherLogin getOtherLoginByOpenIDAndType(String openID,short type);

    /**
     * 添加一个第三方登录
     * 
     * @param login
     * @return
     */
    boolean insertOtherLogin(UOtherLogin login);

    /**
     * 添加用户标识
     * 
     * @param assist
     * @return
     */
    boolean insertUserAssist(UUserAssist assist); 
    
    /**
     * 添加店铺信息
     * @param info
     * @return
     */
    boolean insertShopInfo(UShopInfo info);
    
	UPlatformSupplyer selectU_PlatformSupplyer(Long weiId, Short state);
	
	UBrandSupplyer selectU_BrandSupplyer(Long weiId, Short state);
	
	UPlatformSupplyer getPlatform(Long weiId);
	
	UBrandSupplyer getBrand(Long weiId);
	
	void insertPShelverCount(PShelverCount psc);
}
