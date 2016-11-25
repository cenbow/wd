package com.okwei.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNum
{
    private static GenerateOrderNum goInstance = null;

    private GenerateOrderNum()
    {

    }

    public static GenerateOrderNum getInstance()
    {
        if(goInstance == null)
        {
            goInstance = new GenerateOrderNum();
        }
        return goInstance;
    }

    public static Integer ordersInt = 0;// 全局数

    public String GenerateOrder()
    {
        ordersInt++;// 自增
        Integer countInteger = 4 - ordersInt.toString().length();// 算补位
        String bu = "";
        for(int i = 0; i < countInteger; i++)
        {// 补字符串
            bu += "0";
        }
        bu += ordersInt.toString();
        if(ordersInt > 9999)
        {
            ordersInt = 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");// 时间戳
        String str = sdf.format(new Date());
        return str + bu;
    }
}
