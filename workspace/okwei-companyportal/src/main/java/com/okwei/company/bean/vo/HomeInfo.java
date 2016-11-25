package com.okwei.company.bean.vo;

import java.io.Serializable;
import java.util.List;

public class HomeInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int classId;
    private String className;    
    private List<HomeMain> list;
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<HomeMain> getList() {
        return list;
    }
    public void setList(List<HomeMain> list) {
        this.list = list;
    }
}
