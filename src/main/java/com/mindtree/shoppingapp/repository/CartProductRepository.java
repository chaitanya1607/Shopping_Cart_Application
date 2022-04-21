package com.mindtree.shoppingapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.embed.CartProductKey;
import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.CartProduct;
import com.mindtree.shoppingapp.entity.Product;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
	@Transactional
	@Modifying
	void deleteByCart(Cart cart);

	@Transactional
	@Modifying
	void deleteByCartAndProduct(Cart cart, Product product);

	@Transactional
	@Modifying
	void updateTheQuantityOfProductsInCart(Cart cart, Product product, int updatedQuantity);
}
