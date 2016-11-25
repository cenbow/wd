package com.okwei.company.bean.vo;

import java.io.Serializable;
import java.util.List;

public class WholesaleCount implements Serializable{

	private static final long serialVersionUID = 2949260244514326478L;
    // 总的数量
    public long count;
    public List<KeyValue> areaList;
//    public List<KeyValue> tradeList;
    // 地区count
    public long areaCount;
    public List<KeyValue> selList;
    //选中的地区
    public String selCode;
    
    
    public String getSelCode() {
        return selCode;
    }

    public void setSelCode(String selCode) {
        this.selCode = selCode;
    }

    public List<KeyValue> getSelList() {
        return selList;
    }

    public void setSelList(List<KeyValue> selList) {
        this.selList = selList;
    }

    public long getAreaCount() {
	return areaCount;
    }

    public void setAreaCount(long areaCount) {
	this.areaCount = areaCount;
    }

    public long getCount() {
	return count;
    }

    public void setCount(long count) {
	this.count = count;
    }

    public List<KeyValue> getAreaList() {
	return areaList;
    }

    public void setAreaList(List<KeyValue> areaList) {
	this.areaList = areaList;
    }

//    public List<KeyValue> getTradeList() {
//	return tradeList;
//    }
//
//    public void setTradeList(List<KeyValue> tradeList) {
//	this.tradeList = tradeList;
//    }
}
