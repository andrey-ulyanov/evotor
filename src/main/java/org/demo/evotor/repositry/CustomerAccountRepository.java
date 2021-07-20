package org.demo.evotor.repositry;

import java.util.List;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface CustomerAccountRepository extends IsRepository<CustomerAccount, Integer> {
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public List<CustomerAccount> selectBy(Customer customer);
	
}
