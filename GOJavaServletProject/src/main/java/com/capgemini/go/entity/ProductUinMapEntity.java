package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "PRODUCT_UIN_MAP")
public class ProductUinMapEntity{
	
	@EmbeddedId
	private ProductIdentityEntity productUniqueIdentity;
	
	@Column(name="PRODUCT_IS_PRESENT" , nullable=false, unique = false)
	private boolean productIsPresent;

	/**
	 * @param productUniqueIdentity
	 * @param productIsPresent
	 */
	public ProductUinMapEntity(ProductIdentityEntity productUniqueIdentity, boolean productIsPresent) {
		super();
		this.productUniqueIdentity = productUniqueIdentity;
		this.productIsPresent = productIsPresent;
	}
	
	public ProductIdentityEntity getProductUniqueIdentity() {
		return productUniqueIdentity;
	}

	public void setProductUniqueIdentity(ProductIdentityEntity productUniqueIdentity) {
		this.productUniqueIdentity = productUniqueIdentity;
	}

	public boolean isProductIsPresent() {
		return productIsPresent;
	}

	public void setProductIsPresent(boolean productIsPresent) {
		this.productIsPresent = productIsPresent;
	}

	
	}