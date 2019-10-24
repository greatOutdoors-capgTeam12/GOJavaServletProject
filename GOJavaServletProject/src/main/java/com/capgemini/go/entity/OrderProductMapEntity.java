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

	}

	public OrderProductMapEntity(int productstatus, int giftstatus) {
		super();
		this.productStatus = productstatus;
		this.giftStatus = giftstatus;
	}

	public int getProductstatus() {
		return productStatus;
	}

	public void setProductstatus(int productstatus) {
		this.productStatus = productstatus;
	}

	public int getGiftstatus() {
		return giftStatus;
	}

	public void setGiftstatus(int giftstatus) {
		this.giftStatus = giftstatus;
	}

	@Override
	public String toString() {

		return "OrderProductMap [orderId=" + Id.getOrderId() + ", productid=" + Id.getProductId() + ", productuin="
				+ Id.getProductUIN() + ", productstatus=" + productStatus + ", giftstatus=" + giftStatus + "]";

	}

}
