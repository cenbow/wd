package com.okwei.detail.bean.vo;

import java.util.List;

public class HeadInfo {
    public long userCount;
    
    public List<LeftMenu> leftMenu;

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public List<LeftMenu> getLeftMenu() {
        return leftMenu;
    }

    public void setLeftMenu(List<LeftMenu> leftMenu) {
        this.leftMenu = leftMenu;
    }
    
    
}
