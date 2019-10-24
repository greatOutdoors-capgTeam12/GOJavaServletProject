package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductIdentityEntity {
	
	@Column(name="PRODUCT_ID" , unique = true, nullable = false, length=20)
	private String productId;
	
	@Column(name="PRODUCT_UIN", unique = true, nullable = false, length=20)
	private String productUIN;
	

}
