package com.mindtree.shoppingapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "products_db")
//@MappedSuperclass

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_category", 
discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "productseq", initialValue = 20210, allocationSize = 1)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productseq")
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private float price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private CartProduct cart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public CartProduct getCart() {
		return cart;
	}

	public void setCart(CartProduct cart) {
		this.cart = cart;
	}
	
	
}
