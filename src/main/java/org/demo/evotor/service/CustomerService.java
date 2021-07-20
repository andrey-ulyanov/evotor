package org.demo.evotor.service;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;
import org.demo.evotor.exception.CustomerNotExistException;
import org.demo.evotor.exception.CustomerUnathorizedException;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface CustomerService {

	/**
	 * Create and persist new customer.
	 * 
	 * @param customer Contains login and password.
	 * @return Persistent customer instance with assigned ID.
	 * @throws CustomerAlreadyExistException Throws if customer's login is already
	 *                                       exist in database.
	 */
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistException;

	/**
	 * Retrieve customer's account balance.
	 * 
	 * @param customer Contains login and password.
	 * @return 
	 * @throws CustomerUnathorizedException Throws if customer's password is not
	 *                                      equals password in database.
	 * @throws CustomerNotExistException    Throws if customer's login is not exist
	 *                                      in database.
	 */
	public CustomerAccount getCustomerAccounts(Customer customer)
			throws CustomerUnathorizedException, CustomerNotExistException;

}
