package com.mindtree.shoppingapp.embed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CartProductKey implements Serializable {
	@Column(name = "cart_id")
	private long cartId;

	@Column(name = "product_id")
	private long productId;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public CartProductKey(long cartId, long productId) {

		this.cartId = cartId;
		this.productId = productId;
	}

	public CartProductKey() {

	}

}
