package com.mindtree.productcartbackend.config.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author M1034075
 *
 */
@Configuration(value = "DtoUtilsConfig")
public class DtoUtilsConfig {

	@Bean(name = {"ModelMapper"})
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
