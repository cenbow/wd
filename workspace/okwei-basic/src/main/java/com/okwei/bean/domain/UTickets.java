package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UTickets entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Tickets")
public class UTickets implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -200536898915804184L;
	// Fields

	private Long ticketId;
	private Long weiId;
	private Double valueAmount;
	private String orderId;
	private Integer type;
	private Date createTime;
	private Integer status;

	// Constructors

	/** default constructor */
	public UTickets() {
	}

	/** full constructor */
	public UTickets(Long weiId, Double valueAmount, String orderId,
			Integer type, Date createTime, Integer status) {
		this.weiId = weiId;
		this.valueAmount = valueAmount;
		this.orderId = orderId;
		this.type = type;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TicketID", unique = true, nullable = false)
	public Long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	@Column(name = "WeiId")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ValueAmount", precision = 18)
	public Double getValueAmount() {
		return this.valueAmount;
	}

	public void setValueAmount(Double valueAmount) {
		this.valueAmount = valueAmount;
	}

	@Column(name = "OrderId", length = 64)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}