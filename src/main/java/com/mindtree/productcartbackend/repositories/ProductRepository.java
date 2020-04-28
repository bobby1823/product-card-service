package com.mindtree.productcartbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productcartbackend.entity.Products;

/**
 * 
 * @author M1034075
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
