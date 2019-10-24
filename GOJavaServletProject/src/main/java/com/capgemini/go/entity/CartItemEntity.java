package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Cart-Item")
public class CartItemEntity {	
	
	@EmbeddedId
	@Column(name= "CartItemUniqueKey", unique=true, nullable=false)
	private CartItemUniqueKey Id;
	
	@Column(name = "quantity", unique = false, nullable = false)
		private String quantity;
	
	
	//getters

	public CartItemUniqueKey getId() {return Id;}
	public String getQuantity() {return quantity;}

	//setters
	public void setId(CartItemUniqueKey id) {Id = id;}
	public void setQuantity(String quantity) {this.quantity = quantity;}

	//constructors
	
	public CartItemEntity() {
	
	}
	
	public CartItemEntity(CartItemUniqueKey id, String quantity) {
		super();
		Id = id;
		this.quantity = quantity;
	}
	
	
}
