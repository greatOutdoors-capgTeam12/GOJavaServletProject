package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductIdentityEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2593500634779412169L;

	@Column(name="PRODUCT_ID" , unique = true, nullable = false, length=20)
	private String productId;
	
	@Column(name="PRODUCT_UIN", unique = true, nullable = false, length=20)
	private String productUIN;
	

}
