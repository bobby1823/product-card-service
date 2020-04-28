package com.mindtree.productcartbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductCartBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCartBackendApplication.class, args);
	}

}
