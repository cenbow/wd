package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TFansApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_FansApply")

public class TFansApply  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -8643121896466229832L;
	 private Long fansId;
     private String headImg;
     private String name;
     private String age;
     private String phone;
     private String degree;
     private String qq;
     private String weiXin;
     private String weiBo;
     private Integer province;
     private Integer city;
     private Integer district;
     private String homeTown;
     private String introduce;
     private Date createTime;
     private Integer status;
     private String remark;


    // Constructors

    /** default constructor */
    public TFansApply() {
    }

	/** minimal constructor */
    public TFansApply(String headImg, String name, String phone, Integer province, Integer city, Integer district) {
        this.headImg = headImg;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.district = district;
    }
    
    /** full constructor */
    public TFansApply(String headImg, String name, String age, String phone, String degree, String qq, String weiXin, String weiBo, Integer province, Integer city, Integer district, String homeTown, String introduce, Date createTime, Integer status, String remark) {
        this.headImg = headImg;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.degree = degree;
        this.qq = qq;
        this.weiXin = weiXin;
        this.weiBo = weiBo;
        this.province = province;
        this.city = city;
        this.district = district;
        this.homeTown = homeTown;
        this.introduce = introduce;
        this.createTime = createTime;
        this.status = status;
        this.remark = remark;
    }

   
    // Property accessors
    @Id
    
    @Column(name="FansID", unique=true, nullable=false)

    public Long getFansId() {
        return this.fansId;
    }
    
    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }
    
    @Column(name="HeadImg", nullable=false, length=128)

    public String getHeadImg() {
        return this.headImg;
    }
    
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    @Column(name="Name", nullable=false)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="Age")

    public String getAge() {
        return this.age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    @Column(name="Phone", nullable=false)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="Degree")

    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    @Column(name="QQ")

    public String getQq() {
        return this.qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    @Column(name="WeiXin")

    public String getWeiXin() {
        return this.weiXin;
    }
    
    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }
    
    @Column(name="WeiBo")

    public String getWeiBo() {
        return this.weiBo;
    }
    
    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }
    
    @Column(name="Province")

    public Integer getProvince() {
        return this.province;
    }
    
    public void setProvince(Integer province) {
        this.province = province;
    }
    
    @Column(name="City")

    public Integer getCity() {
        return this.city;
    }
    
    public void setCity(Integer city) {
        this.city = city;
    }
    
    @Column(name="District")

    public Integer getDistrict() {
        return this.district;
    }
    
    public void setDistrict(Integer district) {
        this.district = district;
    }
    
    @Column(name="HomeTown")

    public String getHomeTown() {
        return this.homeTown;
    }
    
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
    
    @Column(name="Introduce", length=800)

    public String getIntroduce() {
        return this.introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    @Column(name="CreateTime", length=0)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="Status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="Remark")

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}