package com.mindtree.shoppingapp.service;

import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.CartProduct;
import com.mindtree.shoppingapp.entity.Product;

public interface CartProductService {
	CartProduct addProductToCart(Cart cart, Product product);

	void removeAllProductsFromCart(Cart cart);

	void removeSpecificProductFromCart(Cart cart, Product product);
	
	void updateTheQuantityOfProductsInCart(Cart cart, Product product, int updatedQuantity);
}
