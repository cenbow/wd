package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * USupplierOperateLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="U_SupplierOperateLog")
public class USupplierOperateLog  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -1471543696896957535L;
	// Fields    

     private Long id;
     private Long supplierWeiId;
     private Long operater;
     private String operateName;
     private String content;
     private Date operateTime;


    // Constructors

    /** default constructor */
    public USupplierOperateLog() {
    }

    
    /** full constructor */
    public USupplierOperateLog(Long operater, String operateName, String content, Date operateTime) {
        this.operater = operater;
        this.operateName = operateName;
        this.content = content;
        this.operateTime = operateTime;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue
    @Column(name="ID", unique=true, nullable=false)
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
   
    
    @Column(name="Operater")

    public Long getOperater() {
        return this.operater;
    }
    
   


	public void setOperater(Long operater) {
        this.operater = operater;
    }
    
    @Column(name="OperateName", length=16)

    public String getOperateName() {
        return this.operateName;
    }
    
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
    
    @Column(name="Content", length=256)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="OperateTime", length=19)

    public Date getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Column(name="SupplierWeiID")
	public Long getSupplierWeiId() {
		return supplierWeiId;
	}


	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}
   








}