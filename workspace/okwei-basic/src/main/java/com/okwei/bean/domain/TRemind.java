package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TRemind entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_Remind")

public class TRemind  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
     private Long weiId;
     private Short type;
     private Short status;


    // Constructors

    /** default constructor */
    public TRemind() {
    }

    
    /** full constructor */
    public TRemind(Long weiId, Short type, Short status) {
        this.weiId = weiId;
        this.type = type;
        this.status = status;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy=IDENTITY)    
    @Column(name="ID", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="WeiID")

    public Long getWeiId() {
        return this.weiId;
    }
    
    public void setWeiId(Long weiId) {
        this.weiId = weiId;
    }
    
    @Column(name="Type")

    public Short getType() {
        return this.type;
    }
    
    public void setType(Short type) {
        this.type = type;
    }
    
    @Column(name="Status")

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }
   








}