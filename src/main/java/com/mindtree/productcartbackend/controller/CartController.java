package com.mindtree.productcartbackend.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.productcartbackend.exceptions.BadRequestException;
import com.mindtree.productcartbackend.payloads.responses.ProductCartVO;
import com.mindtree.productcartbackend.services.ProductCartService;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author M1034075
 *
 */
@RestController(value = "CartController")
@Log4j2
@RequestMapping("/api/cart")
@CrossOrigin(origins= {"http://A2ML15171:4200", "http://localhost:4200"})
public class CartController {

	@Autowired
	ProductCartService cartService;
	
	@RequestMapping(path = { "/get-details/{id}" }, method = { RequestMethod.GET })
	public Collection<ProductCartVO> getCartDetailsByUserId(@PathVariable(name = "id") Long userId, 
			HttpServletRequest request) {
		log.debug(String.format("inside request handler with URI : %s and method : %s", request.getRequestURI(),
				request.getMethod()));
		return this.cartService.getCartDetailsByUserId(userId);
	}
	
	@RequestMapping(path = { "/delete/{id}" }, method = { RequestMethod.DELETE })
	public ProductCartVO deleteCartProductByName(@PathVariable(name = "id") Long userId,
			@RequestParam(value="productName") String productName, HttpServletRequest request) {
		log.debug(String.format("inside request handler with URI : %s and method : %s", request.getRequestURI(),
				request.getMethod()));
		return this.cartService.deleteCartProductByName(userId, productName);
	}
	
	@RequestMapping(path = { "/update/{id}/{productCount}" }, method = { RequestMethod.PUT })
	public ProductCartVO updateCartProductByCountByProductId(@PathVariable(name = "id") Long userId,
			@RequestParam(value="productId") Long productId,
			@PathVariable(value="productCount") Long productCount, HttpServletRequest request) {
		log.debug(String.format("inside request handler with URI : %s and method : %s", request.getRequestURI(),
				request.getMethod()));
		if(productCount < 0) {
			throw new BadRequestException("Quantity should not be negative!");
		}
		return this.cartService.updateCartProductByCount(userId, productCount, productId);
	}
	
	@RequestMapping(path = { "/add/{id}" }, method = { RequestMethod.POST })
	public void addProductInCart(@PathVariable(name = "id") Long userId,
			@Valid @RequestBody Long productId, HttpServletRequest request) {
		log.debug(String.format("inside request handler with URI : %s and method : %s", request.getRequestURI(),
				request.getMethod()));
		
	}
}
