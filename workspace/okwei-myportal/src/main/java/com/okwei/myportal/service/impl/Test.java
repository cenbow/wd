package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.okwei.bean.vo.LoginUser;
import com.okwei.myportal.service.ITest;
@Service
public class Test implements ITest
{

    @Override
    public LoginUser getLoginUser()
    {
        LoginUser user = new LoginUser();
        user.setWeiID(1036799L);//微店号
        user.setIdentity(16385);//2的14次方减1
        user.setPthSub((short)0);//子帐号
        user.setPthSupply((short)0);//子帐号供应商
        user.setWeiName("**测试用的号**");
        user.setWeiType((short)16385);//2的14次方减1
        user.setWeiImg("http://base3.okimgs.com/images/xh_logo.gif");
        List<String> list=new ArrayList<String>();
        list.add("Product_Edit");
        list.add("Product_Add");
        list.add("Product_Buy");
        list.add("Product_Yun");
        user.setPower(list);//设置权限
        return user;
    }

}
