package com.mindtree.shoppingapp.dto;

public class UserDto {
	private String name;
	private int age;
	private String gender;
	private String mobileNumber;

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

	public UserDto(String name, int age, String gender, String mobileNumber) {

		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
	}

	public UserDto() {

	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", age=" + age + ", gender=" + gender + ", mobileNumber=" + mobileNumber + "]";
	}

}
