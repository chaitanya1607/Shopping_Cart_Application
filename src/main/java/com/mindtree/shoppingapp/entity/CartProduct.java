package com.mindtree.shoppingapp.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mindtree.shoppingapp.embed.CartProductKey;

@Entity
@Table(name = "cart_product_db")

@NamedQueries({

		@NamedQuery(name = "CartProduct.deleteByCart", query = "DELETE FROM CartProduct cartproduct WHERE cartproduct.cart = :cart"),
		@NamedQuery(name = "CartProduct.deleteByCartAndProduct", query = "DELETE FROM CartProduct cartproduct WHERE cartproduct.cart = ?1 AND cartproduct.product = ?2"),
		@NamedQuery(name = "CartProduct.updateTheQuantityOfProductsInCart", query = "UPDATE CartProduct cartproduct SET cartproduct.quantity =?3 WHERE cartproduct.cart = ?1 AND cartproduct.product = ?2")
})
public class CartProduct {

	@EmbeddedId
	private CartProductKey cartProductKey;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@OneToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	public CartProductKey getCartProductKey() {
		return cartProductKey;
	}

	public void setCartProductKey(CartProductKey cartProductKey) {
		this.cartProductKey = cartProductKey;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartProduct(CartProductKey cartProductKey, Cart cart, Product product) {

		this.cartProductKey = cartProductKey;
		this.cart = cart;
		this.product = product;
	}

	public CartProduct() {

	}
}
