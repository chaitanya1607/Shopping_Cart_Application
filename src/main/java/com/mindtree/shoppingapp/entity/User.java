package com.mindtree.shoppingapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users_db")
@SequenceGenerator(name = "userseq", initialValue = 100, allocationSize = 1)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mobile_no")
	private String mobileNumber;

	@OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
	private Cart cart;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobileNumber="
				+ mobileNumber + ", cart=" + cart + "]";
	}
	
	
}
