package com.capgemini.go.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ORDER")
public class OrderEntity {
	@Id
	@Column(name="ORDER_ID",unique = false, nullable = false)
	private String orderId;
	
	@Id
	@Column(name="ORDER_DISPATCH_STATUS", unique=false, nullable= false)
	private byte orderDispatchStatus;
	
	@Id
	@Column(name="ORDER_DISPATCH_TIME", unique=false,nullable=false)
	private Date orderDispatchTime; 
	
	@Id
	@Column(name="USER_ID",unique=false,nullable=false)
	private String userId;
	
	@Id
	@Column(name="ADDRESS_ID",unique=false,nullable=false)
	private String addressId;
	
	@Id
	@Column(name="ORDER_INITIATE_TIME",unique=false,nullable=false)
	private Date orderInitiateTime;
}
