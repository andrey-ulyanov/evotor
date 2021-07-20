package org.demo.evotor.repository;

import org.demo.evotor.domain.Customer;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface CustomerRepository extends IsRepository<Customer, Integer> {

	/**
	 * 
	 * @param login
	 * @return
	 */
	public Customer readByLogin(String login);

	
}
