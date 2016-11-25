package com.okwei.bean.vo.agent;

import java.util.List;

public class LeftMenu {
    public int id;
    public String name;
    public List<LeftMenu> list;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<LeftMenu> getList() {
        return list;
    }
    public void setList(List<LeftMenu> list) {
        this.list = list;
    }
}
