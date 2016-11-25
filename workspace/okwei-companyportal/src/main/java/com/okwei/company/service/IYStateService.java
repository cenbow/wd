package com.okwei.company.service;

import com.okwei.company.bean.vo.YHpageMainVO;

public interface IYStateService {
	public Integer getPageindex();
	public void setPageindex(Integer pageindex);
	public Integer getPagesize();
	public void setPagesize(Integer pagesize);
	public Integer getState();
	public void setState(Integer state);
	public Integer getClassid();
	public void setClassid(Integer classid);
	public YHpageMainVO methodAllType();
	public YHpageMainVO methodTowLevel();
	public YHpageMainVO methodOnetLevel();
}
