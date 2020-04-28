package com.mindtree.productcartbackend.services;

import java.util.Collection;

import com.mindtree.productcartbackend.payloads.responses.ProductCartVO;

/**
 * 
 * @author M1034075
 *
 */
public interface ProductCartService {

	public Collection<ProductCartVO> getCartDetailsByUserId(Long userId);
	
	public ProductCartVO deleteCartProductByName(Long userId, String productName);
	
	public ProductCartVO updateCartProductByCount(Long userId, Long productCount, Long productId);
}
