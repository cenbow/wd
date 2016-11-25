package com.okwei.company.service.Impl;


import org.springframework.stereotype.Service;

import com.okwei.company.bean.vo.YHpageMainVO;
import com.okwei.company.service.IYContentService;
import com.okwei.company.service.IYStateService;
@Service
public class YContentService implements IYContentService{
    private IYStateService state;
    @Override
	public IYStateService getState() {
		return state;
	}
    @Override
	public void setState(IYStateService state) {
		this.state = state;
	}
    
	@Override
	public YHpageMainVO method(){
		if(state.getState().equals(0))
			return state.methodAllType();
		else if(state.getState().equals(1))
			return state.methodOnetLevel();
		else if(state.getState().equals(2))
			return state.methodTowLevel();
		else
			return null;
	}
}
