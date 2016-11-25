package com.okwei.service.user;

import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UWeiSellerLoginLog;
import com.okwei.bean.enums.LoginTypeEnum;
import com.okwei.bean.enums.UserChildenType;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.LoginPublicResult;
import com.okwei.bean.vo.LoginResult;
import com.okwei.bean.vo.LoginReturnMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.user.LoginUserExtend;

public interface ILoginService
{
    /**
     * 判断返回的back链接是否合法
     * 
     * @param back
     * @return
     */
    public String getDomainByBack(String back);

    /**
     * 用户登陆
     * 
     * @param username
     *            用户密码
     * @param md5Pwd
     *            md5加密密码
     * @param aesPwd
     *            aes加密码
     * @return
     */
    public UWeiSeller getWeiSeller(Long username,String md5Pwd);

    /**
     * 把用户信息设置到Redis里面去,返回标识tiket
     * 
     * @param user
     * @return
     */
    public String setLoginUserByRedis(UWeiSeller seller);

    /**
     * 添加用戶登錄日記
     * 
     * @param log
     * @return
     */
    public boolean insertUWeiSellerLoginLog(UWeiSellerLoginLog log);

    /**
     * 判断手机是否绑定微店号
     * 
     * @param phone
     *            手机号码
     * @return 绑定的微店号
     */
    public Long getWeiSellerByPhone(String phone);

    /**
     * 设置密码
     * 
     * @param weiid
     *            微店号
     * @param md5pwd
     *            md5加密之后的密文
     * @return
     */
    public boolean updateUWeiSellerByPwd(Long weiid,String md5pwd);

    /**
     * 添加短信记录
     * 
     * @param tvid
     * @return
     */
    public boolean insertTVerificationId(TVerificationId tvid);

    /**
     * 用户登陆
     * 
     * @param username
     *            用户名
     * @param md5Pwd
     *            密码
     * @param ip
     *            用户IP
     * @param code
     *            用户输入的验证码
     * @param checkCode
     *            系统产生的验证码
     * @param loginType
     *            登陆类型（枚举）
     * @return
     */
    public LoginResult getUWeiSellerByLogin(String username,String md5Pwd,String ip,LoginTypeEnum loginType);
    public LoginResult getUWeiSellerByLogin(String username,String md5Pwd,String desPwd,String ip,LoginTypeEnum loginType);

    /**
     * 把子用户信息设置到Redis里面去,返回标识tiket
     * 
     * @param user
     * @return
     */
    public String setSubLoginUserByRedis(UChildrenUser user);

    /**
     * 判断微店号是否存在
     * 
     * @param weiid
     * @return
     */
    public UWeiSeller getUWeiSellerByWeiId(Long weiid);

    /**
     * 获取新的微店号
     * 
     * @return
     */
    public long getNewWeiNo();

    /**
     * 用户注册
     * 
     * @param ip
     *            来源 IP
     * @param sjWeiNo
     *            上级微店号
     * @param weiName
     *            用户名
     * @param mobile
     *            手机号码
     * @param pwd
     *            密码
     * @param code
     *            手机验证码
     * @return
     */
    public LoginResult registWeiUser(String ip,String sjWeiNo,String weiName,String mobile,String pwd,String code);

    /**
     * 第三方注册
     * 
     * @param token
     * @param sjWeiNo
     * @param mobile
     * @param pwd
     * @param code
     * @return
     */
    public ReturnModel otherRegist(String token,long sjWeiNo,String mobile,String pwd,String code);

    /**
     * 根据tiket获取app登陆实体
     * 
     * @param tiket
     * @return
     */
    public ReturnModel appLoginReturnMsg(String tiket);

    /**
     * 判读用户是否有这个身份
     * 
     * @param weiid
     * @param type
     * @return
     */
    public boolean judgeUserType(long weiid,UserIdentityType type);

    /**
     * 判断是否子帐号/子供应商
     * 
     * @param type
     * @return
     */
    public boolean judgeSub(String id,UserChildenType type);

    /**
     * 根据微店号获取登陆信息
     * 
     * @param weiid
     * @return
     */
    public LoginUser getLoginUserByWeiId(long weiid);

    /**
     * 根据tiket获取信息
     * 
     * @param tiket
     * @return
     */
    public LoginReturnMsg getLoginReturnMsgByTiekt(String tiket);

    /**
     * 第三登陆，返回tiket
     * 
     * @param login
     * @return
     */
    public String otherLogin(LoginPublicResult login);

    /**
     * 获取平台号信息
     * 
     * @param weiId
     * @param state
     * @return
     */
    public UPlatformSupplyer getU_PlatformSupplyer(Long weiId,Short state) throws Exception;

    /**
     * 获取品牌号信息
     * 
     * @param weiId
     * @param state
     * @return
     */
    public UBrandSupplyer getU_BrandSupplyer(Long weiId,Short state) throws Exception;

    /**
     * 第三方绑定
     * 
     * @param lpr
     * @return
     */
    public boolean bindLogin(LoginPublicResult lpr,Long weiID);

    /**
     * 修改店铺信息
     * 
     * @param info
     * @return
     */
    public boolean updateShopInfo(UShopInfo info);

    /**
     * APP第三方登陆
     * 
     * @param loginpr
     * @return
     */
    LoginUserExtend checkLoginByThird(LoginPublicResult lpr);
    /**
     * APP第三方绑定
     * 
     * @param loginpr
     * @return
     */
    LoginUserExtend checkLoginByBind(LoginPublicResult lpr,String phone,String psw);
    LoginUserExtend checkLoginByBind(String token,String tiket);

    /**
     * 第三方注册
     * 
     * @param login
     * @param sjWeiID
     *            上级微店号
     * @return -1 openID已经注册
     */
    public String otherRegist(LoginPublicResult login,Long sjWeiID);

    /**
     * APP第三方注册第二步
     * 
     * @param mobile
     * @param vcode
     * @param pwd
     * @param upweiid
     * @param tokent
     * @return
     */
    public ReturnModel appOtherRegist(String mobile,String vcode,String pwd,long upweiid,String tokent);

    /**
     * APP注册
     * 
     * @param mobile
     * @param vcode
     * @param pwd
     * @param upweiid
     * @param weiname
     * @return
     */
    public ReturnModel appRegist(String mobile,String vcode,String pwd,long upweiid,String weiname);

    /**
     * 把父类转换成子类(只支持简单类型)
     * @param user
     * @param cls
     * @return
     */
    public <T> Object getSubByLoginUser(LoginUser user,Class<T> subClass);
}
