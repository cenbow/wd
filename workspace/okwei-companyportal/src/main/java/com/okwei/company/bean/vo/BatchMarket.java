package com.okwei.company.bean.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class BatchMarket implements Serializable{

	private static final long serialVersionUID = 2949260244514326478L;
	
    public int id;
    public String img;
    public String name;
    public String bus;
    public BigInteger supCount;
    public BigInteger rzCount;
    public String address;
    public int province;
    public int city;
    public int district;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBus() {
        return bus;
    }
    public void setBus(String bus) {
        this.bus = bus;
    }
    public BigInteger getSupCount() {
        return supCount;
    }
    public void setSupCount(BigInteger supCount) {
        this.supCount = supCount;
    }
    public BigInteger getRzCount() {
        return rzCount;
    }
    public void setRzCount(BigInteger rzCount) {
        this.rzCount = rzCount;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getProvince() {
        return province;
    }
    public void setProvince(int province) {
        this.province = province;
    }
    public int getCity() {
        return city;
    }
    public void setCity(int city) {
        this.city = city;
    }
    public int getDistrict() {
        return district;
    }
    public void setDistrict(int district) {
        this.district = district;
    }
    
}
