package com.mindtree.productcartbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productcartbackend.entity.Users;

/**
 * 
 * @author M1034075
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
