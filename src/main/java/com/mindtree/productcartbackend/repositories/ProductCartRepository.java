package com.mindtree.productcartbackend.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productcartbackend.entity.ProductCart;

/**
 * 
 * @author M1034075
 *
 */
@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

	public Collection<ProductCart> findAllByUserId(Long userId);
	
	public Optional<ProductCart> findOneByUserIdAndCartID(Long userId, Long productId);
	
	public Collection<ProductCart> findAllByCartID(Long Id);
}
