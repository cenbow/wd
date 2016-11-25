package com.okwei.util;

import java.math.BigDecimal;


public class Tax {
	
	public static double calculationServiceTax(double income) {

		// 1.收入小于800 免税
		if (income <= 800) {
		    return 0;
		}
		// 2.计算应纳税所得额
		double taxableIncome = 0;
		// 应纳税所得额 = 收入 不超过4000元的，减除费用800
		if (income <= 4000) {
		    taxableIncome = income - 800;
		}// 应纳税所得额 = 收入扣除20%
		else {
		    taxableIncome = income * (1 - 0.2);
		}

		// 3.计算税额 应纳税额 = 应纳税所得额×适用税率 - 速算扣除数
		double tax = 0;
		if (taxableIncome <= 20000)// 应纳税所得额 小于20000 税率 20% 速算扣除数 0
		{
		    tax = taxableIncome * 0.2;
		}// 应纳税所得额 大于20000 小于等于50000 税率 30% 速算扣除数 2000
		else if (50000 >= taxableIncome && taxableIncome > 20000) {
		    tax = (taxableIncome * 0.3) - 2000;
		} else {// 应纳税所得额 大于50000 税率 40% 速算扣除数 7000
		    tax = (taxableIncome * 0.4) - 7000;
		}
		return tax;
	    }
	public static double getTwoDecimal(double amount,int bit) {
		BigDecimal bD = new BigDecimal(amount);
		return bD.setScale(bit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
