package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "OrderProductMapEntity")
@Table(name = "order_product_map", uniqueConstraints = { @UniqueConstraint(columnNames = "product_uin") })
public class OrderProductMapEntity implements Serializable {

	private static final long serialVersionUID = -5870696027119904888L;

	@EmbeddedId
	@Column(name = "OrderProductMapPK")
	private OrderProductMapPK Id;

	@Column(name = "product_status", unique = false, length = 20)
	private int productStatus;

	@Column(name = "gift_status", unique = false, length = 20)
	private int giftStatus;

	public OrderProductMapEntity() {
		super();
	}

	public OrderProductMapEntity(OrderProductMapPK id, int productStatus, int giftStatus) {
		super();
		Id = id;
		this.productStatus = productStatus;
		this.giftStatus = giftStatus;
	}

	public OrderProductMapPK getId() {
		return Id;
	}

	public void setId(OrderProductMapPK id) {
		Id = id;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public int getGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


}
