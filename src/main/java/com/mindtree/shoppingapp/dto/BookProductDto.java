package com.mindtree.shoppingapp.dto;

public class BookProductDto {
	private String name;
	private float price;
	private String genre;
	private String author;
	private String publications;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	public BookProductDto(String name, float price, String genre, String author, String publications) {
		
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}
	
	public BookProductDto()
	{
		
	}
	
}
