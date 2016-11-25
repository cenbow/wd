package com.okwei.company.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.domain.PcMarketLeftRec;
import com.okwei.bean.domain.PcMarketRecommand;
import com.okwei.bean.vo.PcMarketRecommandVO;

public class WholesaleList implements Serializable{

	private static final long serialVersionUID = 2949260244514326478L;
	
    public String bigClass;
    public List<PcMarketLeftRec> leftList;
    public List<PcMarketRecommand> mainList;
    List<PcMarketRecommandVO> mainListvo;
    public String getBigClass() {
        return bigClass;
    }
    public void setBigClass(String bigClass) {
        this.bigClass = bigClass;
    }
    public List<PcMarketLeftRec> getLeftList() {
        return leftList;
    }
    public void setLeftList(List<PcMarketLeftRec> leftList) {
        this.leftList = leftList;
    }
    public List<PcMarketRecommand> getMainList() {
        return mainList;
    }
    public void setMainList(List<PcMarketRecommand> mainList) {
        this.mainList = mainList;
    }
	public List<PcMarketRecommandVO> getMainListvo() {
		return mainListvo;
	}
	public void setMainListvo(List<PcMarketRecommandVO> mainListvo) {
		this.mainListvo = mainListvo;
	}
	
    
}
