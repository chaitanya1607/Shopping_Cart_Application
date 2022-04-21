package com.mindtree.shoppingapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart_db")
@SequenceGenerator(name = "cartseq", initialValue = 1200, allocationSize = 1)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartseq")
	@Column(name = "id")
	private long id;

	@JoinColumn(name = "user_id")
	@OneToOne
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	private List<CartProduct> productList = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<CartProduct> productList) {
		this.productList = productList;
	}

}
