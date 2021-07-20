package org.demo.evotor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;
import org.demo.evotor.repository.AccountRepository;
import org.demo.evotor.repository.CustomerRepository;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/* Instance */

	protected CustomerRepository customerRepository;
	protected AccountRepository customerAccountRepository;

	/**
	 * Default
	 * 
	 * @param customerRepository
	 * @param customerAccountRepository
	 */
	public CustomerServiceImpl(@Autowired CustomerRepository customerRepository,
			@Autowired AccountRepository customerAccountRepository) {
		super();
		LOG.debug(
				">> CustomerServiceImpl(CustomerRepository customerRepository = {}, AccountRepository customerAccountRepository = {})",
				customerRepository, customerAccountRepository);

		this.customerRepository = customerRepository;
		this.customerAccountRepository = customerAccountRepository;

		LOG.debug(
				"<< CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository customerAccountRepository)");
	}
	
	/* ***** Implementation ***** */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addCustomer(Customer customer) throws CustomerAlreadyExistException {
		LOG.debug(">> addCustomer(Customer customer = {})", customer);
		
		boolean result = false;
		
		this.customerRepository.insert(customer);
		
		Customer customerLocal = this.customerRepository.readByLogin(customer.getLogin());		
		if (customerLocal != null) 
			throw new CustomerAlreadyExistException("Customer is already in database. ");
		
		
		
		LOG.debug(">> addCustomer(Customer customer): {}", result);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CustomerAccount getBalance(Customer customer) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
