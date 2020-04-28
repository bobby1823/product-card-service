package com.mindtree.productcartbackend.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.productcartbackend.entity.ProductCart;
import com.mindtree.productcartbackend.payloads.responses.ProductCartVO;
import com.mindtree.productcartbackend.repositories.ProductCartRepository;
import com.mindtree.productcartbackend.services.ProductCartService;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author M1034075
 *
 */
@Service(value = "ProductCartServiceImpl")
@Transactional
@Log4j2
public class ProductCartServiceImpl implements ProductCartService {
	
	@Autowired
	private ProductCartRepository cartRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Collection<ProductCartVO> getCartDetailsByUserId(Long userId) {
		log.debug("inside method : public Collection<ProductCartVO> getCartDetailsByUserId(Long userId)");
		Collection<ProductCartVO> productCartVOCollection = this.cartRepo.findAllByUserId(userId).stream()
																.map(cart -> modelMapper.map(cart, ProductCartVO.class))
																.collect(Collectors.toList());
		return productCartVOCollection;
	}
	
	@Override
	public ProductCartVO deleteCartProductByName(Long userId, String productName) {
		log.debug("inside method : public ProductCartVO deleteCartProductByName(Long userId, String productName)");
		Collection<ProductCartVO> productCartVOCollection = this.cartRepo.findAllByUserId(userId).stream()
				.map(cart -> modelMapper.map(cart, ProductCartVO.class))
				.collect(Collectors.toList());
			
		Long deletedCartId = null;
		for(ProductCartVO productCartVO : productCartVOCollection) {
			System.out.println("Hi "+productCartVO);
			if(productCartVO.getName().equals(productName)) {
				if(productCartVO.getCount() <= 1) {
					this.cartRepo.deleteById(productCartVO.getId());
					break;
				} else {
					System.out.println("Hi "+productCartVO);
					deletedCartId = productCartVO.getId();
					ProductCart productCart = this.cartRepo.findById(productCartVO.getId()).get();
					productCart.setProductCount((productCartVO.getCount()-1));
					this.cartRepo.saveAndFlush(productCart);
					break;
				}				
			}	
		}
		if(deletedCartId != null) {
			ProductCartVO productCartVOCollectionReturn = this.cartRepo.findAllByCartID(deletedCartId).stream().map(
					cartProduct -> modelMapper.map(cartProduct, ProductCartVO.class)
				).collect(Collectors.toList()).get(0);
			return productCartVOCollectionReturn;
		} else {
			return null;
		}
		
	}

	@Override
	public ProductCartVO updateCartProductByCount(Long userId, Long productCount, Long productId) {
		log.debug("inside method : public ProductCartVO deleteCartProductByName(Long userId, String productName)");
		ProductCart productCart = this.cartRepo.findOneByUserIdAndCartID(userId, productId).get();
			
		if(productCount == 0) {
			this.cartRepo.deleteById(productCart.getCartID());
			return null;
		}
		else {
			productCart.setProductCount(productCount.intValue());
			this.cartRepo.saveAndFlush(productCart);
			ProductCartVO productCartVO = this.modelMapper.map(
					this.cartRepo.findById(productCart.getCartID()).get(), ProductCartVO.class
					); 
			return productCartVO;
		}
	}

}
