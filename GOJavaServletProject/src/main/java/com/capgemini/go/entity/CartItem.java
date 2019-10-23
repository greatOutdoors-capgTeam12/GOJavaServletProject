package com.capgemini.go.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



public class CartItem {
	@Embeddable
	public class group_Id implements Serializable{
		
		@Column(name = "retailerId", unique = false, nullable = false)
		private String retailerId;
		
		
		@Column(name = "productId", unique = false, nullable = false)
		private byte productCategory;
		
	}
	
	@Entity
	@Table(name="Cart-Item")
	public class RetailerInventoryEntity {
		// attributes
		
	@Column(name = "quantity", unique = true, nullable = false)
		private String quantity;
	
	@EmbeddedId
	group_Id id;

	//getters
	public group_Id getId() {return id;}
	public String getQuantity() {return quantity;}
	
	//setters

	public void setQuantity(String quantity) {this.quantity = quantity;}
	public void setId(group_Id id) {this.id = id;}
	
	
	//constructors
	public RetailerInventoryEntity() {
		
	}
	public RetailerInventoryEntity(String quantity, group_Id id) {
		this.id = id;
		this.quantity = quantity;
	}
}
}
