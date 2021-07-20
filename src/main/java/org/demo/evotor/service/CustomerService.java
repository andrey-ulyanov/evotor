package org.demo.evotor.service;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface CustomerService {

	/**
	 * 
	 * @param customer
	 */
	public boolean addCustomer(Customer customer) throws CustomerAlreadyExistException;
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public CustomerAccount getBalance(Customer customer);
	
}
