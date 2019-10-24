package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Cart-Item")
public class CartItemEntity {	
	
	@EmbeddedId
	@Column(name= "RetailerCartUniqueKey", unique=true, nullable=false)
	private RetailerCartUniqueKey Id;
	
	@Column(name = "quantity", unique = false, nullable = false)
		private String quantity;
	
	
	//getters

	public RetailerCartUniqueKey getId() {return Id;}
	public String getQuantity() {return quantity;}

	//setters
	public void setId(RetailerCartUniqueKey id) {Id = id;}
	public void setQuantity(String quantity) {this.quantity = quantity;}

	//constructors
	
	public CartItemEntity() {
	
	}
	
	public CartItemEntity(RetailerCartUniqueKey id, String quantity) {
		super();
		Id = id;
		this.quantity = quantity;
	}
	
	
	
	
	
	
	
}
