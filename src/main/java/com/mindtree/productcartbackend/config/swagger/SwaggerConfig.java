package com.mindtree.productcartbackend.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author M1034075
 *
 */
@Profile(value = { "dev" })
@EnableSwagger2
@Configuration(value = "SwaggerConfig")
@SuppressWarnings("unused")
public class SwaggerConfig {

	@Value("${app.swagger.application.title}")
	private String applicationTitle;

	@Value("${app.swagger.application.version}")
	private String applicationVersion;
	
	/*@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.mindtree.productcartbackend.controller"))
                .paths(regex("/api.*"))
                .build();
    }
		
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(applicationTitle + " APIs")
				.description("This page lists all the rest apis for " + applicationTitle + " App.")
				.version(applicationVersion).build();
	}*/
	
	@Bean(name = { "Docket" })
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				// .paths(paths())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(applicationTitle + " APIs")
				.description("This page lists all the rest apis for " + applicationTitle + " App.")
				.version(applicationVersion).build();
	}

	// Only select apis that matches the given Predicates.
	private Predicate<String> paths() {
		// Match all paths except /error
		return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error.*")));
	}
}
