package org.demo.evotor.repositry;

import org.demo.evotor.domain.Customer;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface CustomerRepository extends IsRepository<Customer, Long> {

	/**
	 * 
	 * @param login
	 * @return
	 */
	public Customer readByLogin(String login);

	
}
