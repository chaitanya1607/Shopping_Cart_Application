package com.mindtree.shoppingapp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mindtree.shoppingapp.dto.ApparalProductDto;
import com.mindtree.shoppingapp.dto.BookProductDto;
import com.mindtree.shoppingapp.dto.UserDto;
import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.CartProduct;
import com.mindtree.shoppingapp.entity.Product;
import com.mindtree.shoppingapp.entity.User;
import com.mindtree.shoppingapp.service.CartProductService;
import com.mindtree.shoppingapp.service.ProductService;
import com.mindtree.shoppingapp.service.UserService;

@SpringBootApplication
public class SpringJpaProjApplication implements CommandLineRunner {

	private static Scanner input = new Scanner(System.in);

	private UserService userService;

	private ProductService productService;

	private CartProductService cartProductService;

	@Autowired
	public SpringJpaProjApplication(UserService userService, ProductService productService,
			CartProductService cartProductService) {
		this.userService = userService;
		this.productService = productService;
		this.cartProductService = cartProductService;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringJpaProjApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		int choice;
		do {
			choice = createMenu();
			switch (choice) {
			case 1:
				System.out.println("\n");
				User user = createUser();
				System.out.println("New User is created with User-id : " + user.getId() + " and his Cart-id : "
						+ user.getCart().getId());
				System.out.println("\n");
				break;
			case 2:
				System.out.println("\n");
				Product product = createProduct();
				System.out.println("New Product is created with Product-id : " + product.getId());
				System.out.println("\n");
				break;
			case 3:
				System.out.println("\n");

				User selectedUser = displayAllUserCarts();
				Product selectedProduct = displayAvaialableProducts();

				CartProduct productCartProduct = addProductToCart(selectedUser.getCart(), selectedProduct);
				System.out.println("The Product '" + productCartProduct.getProduct().getName()
						+ "' has been added to the cart " + productCartProduct.getCartProductKey().getCartId()
						+ " it's quantity in cart is " + productCartProduct.getQuantity());

				System.out.println("\n");
				break;
			case 4:
				System.out.println("\n");
				removeProductsFromCart();
				System.out.println("\n");
				break;
			case 5:
				System.out.println("\n");
				updateQuantity();
				System.out.println("\n");
				break;
			case 6:
				System.out.println("\n");
				viewCart();
				System.out.println("\n");
				break;
			case 7:
				System.out.println("\n");
				System.out.println("Exiting !!");
				break;
			default:
				System.out.println("Wrong choice,Try again");
			}
		} while (choice != 7);

	}

	private void viewCart() {
		System.out.println("Please select a Cart to View ");
		Cart cart = displayAllUserCarts().getCart();
		displayProductsWithPriceInCart(cart);
	}

	private void displayProductsWithPriceInCart(Cart cart) {
		Map<Integer, Product> productMap = new LinkedHashMap<>();
		float totalPrice = 0.0F;
//		cart.getProductList().stream().forEach(pro -> {
//			totalPrice += pro.getQuantity()*pro.getProduct().getPrice();
//		});

		for (CartProduct cp : cart.getProductList()) {
			totalPrice += cp.getQuantity() * cp.getProduct().getPrice();
		}
		List<Product> products = cart.getProductList().stream().map(pro -> pro.getProduct())
				.collect(Collectors.toList());
		productMap = products.stream().collect(LinkedHashMap<Integer, Product>::new,
				(map, streamValue) -> map.put(map.size() + 1, streamValue), (map, map2) -> {
				});
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|%6s|%20s|%10s|\n", "Sl.No", "Product-Name", "Product-Id");
		productMap.forEach((slNo, product) -> {
			for (int i = 0; i < 40; i++)
				System.out.printf("-");
			System.out.println();
			System.out.printf("|%6s|%20s|%10s|\n", slNo, product.getName(), product.getId());
		});
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|%9s|%26s|\n", "Total-Price", totalPrice);
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
	}

	private void updateQuantity() {
		Cart cart = displayAllUserCarts().getCart();
		Product product = displayProductsInCart(cart);
		System.out.println("Please enter the quantity you are going to update ");
		int updatedQuantity = getValidatedInteger("quantity");
		if (updatedQuantity == 0) {
			cartProductService.removeSpecificProductFromCart(cart, product);
			System.out
					.println("The Product '" + product.getName() + "' From Cart " + cart.getId() + " has been removed");
		} else {
			cartProductService.updateTheQuantityOfProductsInCart(cart, product, updatedQuantity);
			System.out.println("The Product '" + product.getName() + "' From Cart " + cart.getId()
					+ " has been updated to quantity " + updatedQuantity);
		}
	}

	private void removeProductsFromCart() {

		Cart cart = displayAllUserCarts().getCart();
		int choice = getValidatedInteger("remove");
		if (choice == 1) {
			cartProductService.removeAllProductsFromCart(cart);
			System.out.println("All Products From Cart " + cart.getId() + " removed");
		} else {
			Product product = displayProductsInCart(cart);
			cartProductService.removeSpecificProductFromCart(cart, product);
			System.out
					.println("The Product '" + product.getName() + "' From Cart " + cart.getId() + " has been removed");
		}

	}

	private Product displayProductsInCart(Cart cart) {

		Map<Integer, Product> productMap = new LinkedHashMap<>();
		List<Product> products = cart.getProductList().stream().map(pro -> pro.getProduct())
				.collect(Collectors.toList());
		productMap = products.stream().collect(LinkedHashMap<Integer, Product>::new,
				(map, streamValue) -> map.put(map.size() + 1, streamValue), (map, map2) -> {
				});
		int choice;
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|%6s|%20s|%10s|\n", "Sl.No", "Product-Name", "Product-Id");
		productMap.forEach((slNo, product) -> {
			for (int i = 0; i < 40; i++)
				System.out.printf("-");
			System.out.println();
			System.out.printf("|%6s|%20s|%10s|\n", slNo, product.getName(), product.getId());
		});
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		boolean isValidChoice = false;
		do {
			System.out.print("Please choose the product by Sl.No :");
			choice = getValidatedInteger("no");
			if (choice > 0 && choice <= productMap.size())
				isValidChoice = true;
			else
				System.out.println("The choice is overflow / underflow , please try again");
		} while (!isValidChoice);

		return productMap.get(choice);
	}

	private CartProduct addProductToCart(Cart cart, Product product) {
		return cartProductService.addProductToCart(cart, product);

	}

	private Product displayAvaialableProducts() {
		Map<Integer, Product> productMap = new LinkedHashMap<>();
		List<Product> products = productService.getAllProducts();
		productMap = products.stream().collect(LinkedHashMap<Integer, Product>::new,
				(map, streamValue) -> map.put(map.size() + 1, streamValue), (map, map2) -> {
				});
		int choice;
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|%6s|%20s|%10s|\n", "Sl.No", "Product-Name", "Product-Id");
		productMap.forEach((slNo, product) -> {
			for (int i = 0; i < 40; i++)
				System.out.printf("-");
			System.out.println();
			System.out.printf("|%6s|%20s|%10s|\n", slNo, product.getName(), product.getId());
		});
		for (int i = 0; i < 40; i++)
			System.out.print("-");
		System.out.println();
		boolean isValidChoice = false;
		do {
			System.out.print("Please choose the product by Sl.No :");
			choice = getValidatedInteger("no");
			if (choice > 0 && choice <= productMap.size())
				isValidChoice = true;
			else
				System.out.println("The choice is overflow / underflow , please try again");
		} while (!isValidChoice);

		return productMap.get(choice);

	}

	private User displayAllUserCarts() {
		Map<Integer, User> userMap = new LinkedHashMap<>();
		List<User> users = userService.getAllUsers();
		userMap = users.stream().collect(LinkedHashMap<Integer, User>::new,
				(map, streamValue) -> map.put(map.size() + 1, streamValue), (map, map2) -> {
				});

		int choice;
		for (int i = 0; i < 45; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|%6s|%20s|%7s|%7s|\n", "Sl.No", "User-Name", "User-Id", "Cart-Id");
		userMap.forEach((slNo, user) -> {
			for (int i = 0; i < 45; i++)
				System.out.printf("-");
			System.out.println();
			System.out.printf("|%6s|%20s|%7s|%7s|\n", slNo, user.getName(), user.getId(), user.getCart().getId());
		});
		for (int i = 0; i < 45; i++)
			System.out.print("-");
		System.out.println();
		boolean isValidChoice = false;
		do {
			System.out.print("Please choose the user by Sl.No :");
			choice = getValidatedInteger("no");
			if (choice > 0 && choice <= userMap.size())
				isValidChoice = true;
			else
				System.out.println("The choice is overflow / underflow , please try again");
		} while (!isValidChoice);
		return userMap.get(choice);
	}

	private Product createProduct() {

		System.out.println("\n");
		System.out.println("Please choose category of product:");
		int productCategory = getValidatedInteger("product");
		if (productCategory == 1) {
			BookProductDto bookProductDto = new BookProductDto();
			System.out.print("Please enter Book name        :");
			bookProductDto.setName(getValidatedInput("name"));
			System.out.print("Please enter Book price       :");
			bookProductDto.setPrice(getValidatedFloat("price"));
			System.out.print("Please enter Book Autor       :");
			bookProductDto.setAuthor(getValidatedInput("name"));
			System.out.print("Please enter Book Genre       :");
			bookProductDto.setGenre(getValidatedInput("name"));
			System.out.print("Please enter Book Publications:");
			bookProductDto.setPublications(getValidatedInput("name"));
			System.out.println("\n");
			return productService.createBookProduct(bookProductDto);
		} else {
			ApparalProductDto apparalProductDto = new ApparalProductDto();
			System.out.print("Please enter Apparal name        :");
			apparalProductDto.setName(getValidatedInput("name"));
			System.out.print("Please enter Apparal price       :");
			apparalProductDto.setPrice(getValidatedFloat("price"));
			System.out.print("Please enter Apparal type        :");
			apparalProductDto.setType(getValidatedInput("name"));
			System.out.print("Please enter Apparal brand       :");
			apparalProductDto.setBrand(getValidatedInput("name"));
			System.out.print("Please enter Apparal design      :");
			apparalProductDto.setDesign(getValidatedInput("name"));
			System.out.println("\n");
			return productService.createApparalProduct(apparalProductDto);
		}

	}

	private User createUser() {
		System.out.println("\n");
		UserDto userDto = new UserDto();
		System.out.print("Please enter User name        :");
		userDto.setName(getValidatedInput("name"));
		System.out.print("Please enter User age         :");
		userDto.setAge(getValidatedInteger("age"));
		System.out.println("Please choose User gender     :");
		userDto.setGender(getValidatedInput("gender"));
		System.out.print("Please enter User mobileNumber :");
		userDto.setMobileNumber(getValidatedInput("mobileNumber"));
		System.out.println("\n");
		return userService.createUser(userDto);
	}

	private int createMenu() {
		int choice;
		System.out.println("\n" + "\n" + "*********************" + "*********************  ");
		System.out.println("** WELCOME TO My-Shopping" + " CART SERVICES **  ");
		System.out.println("*********************" + "*********************  ");

		System.out.println();
		String menu = "------------------------------------------\n" + "|  1  | Create User and his Cart         |\n"
				+ "------------------------------------------\n" + "|  2  | Create Product                   |\n"
				+ "------------------------------------------\n" + "|  3  | Add Product to Cart              |\n"
				+ "------------------------------------------\n" + "|  4  | Remove Product from Cart         |\n"
				+ "------------------------------------------\n" + "|  5  | Update Cart                      |\n"
				+ "------------------------------------------\n" + "|  6  | View Cart                        |\n"
				+ "------------------------------------------\n" + "|  7  | Exit                             |\n"
				+ "------------------------------------------\n";

		System.out.println(menu);
		System.out.print("       Enter your choice           :");

		choice = getValidatedInteger("no");
		return choice;
	}

	private float getValidatedFloat(String validationType) {
		float number;
		boolean isValid;
		switch (validationType) {
		case "Float":

			while (!input.hasNextFloat()) {
				String userInput = input.next();
				System.out.printf("\"%s\" is not a valid Floating number,try again.\nEnter an floating number :",
						userInput);
			}
			number = input.nextFloat();
			return number;
		case "price":
			do {
				isValid = true;

				number = getValidatedFloat("Float");
				if (number < 0 || number > 999999) {
					isValid = false;
					System.out.println("The price is invalid try again!!\nEnter valid price:");
				}
			} while (!isValid);
			return number;
		}
		return 0;

	}

	private int getValidatedInteger(String validationType) {
		int number;
		boolean isValid;
		switch (validationType) {
		case "Integer":

			while (!input.hasNextInt()) {
				String userInput = input.next();
				System.out.printf("\"%s\" is not a valid number,try again.\nEnter an integer :", userInput);
			}
			number = input.nextInt();
			return number;
		case "age":
			do {
				isValid = true;

				number = getValidatedInteger("Integer");
				if (number < 0 || number > 150) {
					isValid = false;
					System.out.println("The age is invalid try again!!\nEnter valid age:");
				}
			} while (!isValid);
			return number;
		case "no":
			do {
				isValid = true;

				number = getValidatedInteger("Integer");
				if (number < 0 || number > 99999) {
					isValid = false;
					System.out.println("The choice invalid try again!!Enter valid choice:");
				}
			} while (!isValid);
			return number;

		case "quantity":
			do {
				isValid = true;

				number = getValidatedInteger("Integer");
				if (number < 0 || number > 99999) {
					isValid = false;
					System.out.println("The quantity invalid try again!!Enter valid quantity:");
				}
			} while (!isValid);
			return number;

		case "product":
			do {
				isValid = true;

				System.out.println("1.Book");
				System.out.println("2.Apparal");
				number = getValidatedInteger("Integer");
				if (number != 1 && number != 2) {
					isValid = false;
					System.out.println("The choice invalid try again!!Enter valid choice:");
				}

			} while (!isValid);
			return number;

		case "remove":
			do {
				isValid = true;

				System.out.println("1.Remove all Products from Cart");
				System.out.println("2.Remove Specific Product from Cart");
				number = getValidatedInteger("Integer");
				if (number != 1 && number != 2) {
					isValid = false;
					System.out.println("The choice invalid try again!!Enter valid choice:");
				}

			} while (!isValid);
			return number;
		}
		return -1;
	}

	private String getValidatedInput(String validationType) {
		String inputString;
		boolean isValid;
		int inputChoice;
		switch (validationType) {
		case "name":
			do {
				isValid = true;
				input.nextLine();
				inputString = input.nextLine();
				for (int i = 0; i < inputString.length(); i++)
					if (!((inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'Z')
							|| (inputString.charAt(i) >= 'a' && inputString.charAt(i) <= 'z')
							|| (inputString.charAt(i) == ' '))) {
						System.out.println("You have not entered a alphastring!Try again!!\nEnter valid name :");
						isValid = false;
						break;
					}
			} while (!isValid);
			return inputString;
		case "mobileNumber":
			do {
				isValid = true;

				inputString = input.next();
				for (int i = 0; i < inputString.length(); i++)
					if (!((inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9'))) {
						System.out.println(
								"You have not entered a numericstring!Try again!!\nEnter valid mobileNumber :");
						isValid = false;
						break;
					}
			} while (!isValid);
			return inputString;
		case "gender":
			do {
				isValid = true;

				System.out.println("1.Male");
				System.out.println("2.Female");
				inputChoice = getValidatedInteger("Integer");
				if (inputChoice == 1)
					inputString = "Male";
				else if (inputChoice == 2)
					inputString = "Female";
				else {
					inputString = null;
					isValid = false;
					System.out.println("Wrong choice try again!!\nEnter valid choice:");
				}
			} while (!isValid);
			return inputString;

		}
		return null;
	}

}
