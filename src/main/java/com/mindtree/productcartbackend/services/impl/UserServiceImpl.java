package com.mindtree.productcartbackend.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mindtree.productcartbackend.payloads.responses.UsersVo;
import com.mindtree.productcartbackend.repositories.UsersRepository;
import com.mindtree.productcartbackend.services.UserService;

/**
 * 
 * @author M1034075
 *
 */
@Service(value = "UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Collection<UsersVo> getAllUsers() {		
		Collection<UsersVo> users = this.userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UsersVo.class)).collect(Collectors.toList());
		
		return users;
	}

}
