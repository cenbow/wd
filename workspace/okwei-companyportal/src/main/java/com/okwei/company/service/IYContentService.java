package com.okwei.company.service;

import com.okwei.company.bean.vo.YHpageMainVO;

public interface IYContentService {
	public IYStateService getState();
	public void setState(IYStateService state);
	public YHpageMainVO method();
}
