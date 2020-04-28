package com.mindtree.productcartbackend.services;

import java.util.Collection;

import com.mindtree.productcartbackend.payloads.responses.UsersVo;

/**
 * 
 * @author M1034075
 *
 */
public interface UserService {

	public Collection<UsersVo> getAllUsers();
}
