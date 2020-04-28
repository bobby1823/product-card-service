package com.mindtree.productcartbackend.config.db;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author M1034075
 *
 */
/**
 * custom repository implementation classes can extend this class to be able to
 * use hibernate specific session factory object. However please avoid using
 * hibernate specific session object. Better autowire jpa entity manager factory
 * in these custom repository implementation classes (having name pattern as
 * SomethingRepositoryImpl) and use jpa standard methods to write database
 * interaction methods.
 *
 */
@Configuration(value = "HibernateSessionFactoryProvider")
public abstract class HibernateSessionFactoryProvider {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	protected SessionFactory getHibernateSessionFactory() {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		return sessionFactory;
	}
}
