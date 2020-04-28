package com.mindtree.productcartbackend.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.productcartbackend.payloads.responses.UsersVo;
import com.mindtree.productcartbackend.services.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author M1034075
 *
 */
@RestController(value = "UserController")
@Log4j2
@RequestMapping("/api/users")
@CrossOrigin(origins= {"http://A2ML15171:4200", "http://localhost:4200"})
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, path = {"/users"})
	@ResponseBody
	public Collection<UsersVo> getUsers(HttpServletRequest request) {
		log.debug(String.format("inside request handler with URI : %s and method : %s", request.getRequestURI(),
				request.getMethod()));
		return userService.getAllUsers();
	}
}
