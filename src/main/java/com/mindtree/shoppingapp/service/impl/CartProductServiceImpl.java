package com.mindtree.shoppingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingapp.embed.CartProductKey;
import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.CartProduct;
import com.mindtree.shoppingapp.entity.Product;
import com.mindtree.shoppingapp.repository.CartProductRepository;
import com.mindtree.shoppingapp.repository.CartRepository;
import com.mindtree.shoppingapp.repository.ProductRepository;
import com.mindtree.shoppingapp.service.CartProductService;

@Service
public class CartProductServiceImpl implements CartProductService {
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartProduct addProductToCart(Cart cart, Product product) {

		CartProductKey cartProductKey = new CartProductKey(cart.getId(), product.getId());

		CartProduct cartProduct = cartProductRepository.findById(cartProductKey).orElse(null);
		if (cartProduct == null) {
			cartProduct = new CartProduct(cartProductKey, cart, product);
			cartProduct.setQuantity(1);
		} else {
			cartProduct.setQuantity(cartProduct.getQuantity() + 1);
		}
		cart.getProductList().add(cartProduct);
		cartRepository.save(cart);
		product.setCart(cartProduct);
		productRepository.save(product);
		return cartProductRepository.save(cartProduct);
	}

	@Override
	public void removeAllProductsFromCart(Cart cart) {
		cartProductRepository.deleteByCart(cart);

	}

	@Override
	public void removeSpecificProductFromCart(Cart cart, Product product) {
		cartProductRepository.deleteByCartAndProduct(cart, product);

	}

	@Override
	public void updateTheQuantityOfProductsInCart(Cart cart, Product product, int updatedQuantity) {

		cartProductRepository.updateTheQuantityOfProductsInCart(cart, product, updatedQuantity);
	}

}
