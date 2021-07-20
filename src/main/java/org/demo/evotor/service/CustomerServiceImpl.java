package org.demo.evotor.service;

import java.util.Calendar;
import java.util.List;

import org.demo.evotor.domain.Currency;
import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;
import org.demo.evotor.exception.CustomerNotExistException;
import org.demo.evotor.exception.CustomerUnathorizedException;
import org.demo.evotor.repositry.CustomerAccountRepository;
import org.demo.evotor.repositry.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	protected CustomerAccountRepository customerAccountRepository;

	/**
	 * Default
	 * 
	 * @param customerRepository
	 * @param customerAccountRepository
	 */
	public CustomerServiceImpl(@Autowired CustomerRepository customerRepository,
			@Autowired CustomerAccountRepository customerAccountRepository) {
		super();
		LOG.debug(
				"CustomerServiceImpl(CustomerRepository customerRepository = {}, AccountRepository customerAccountRepository = {})",
				customerRepository, customerAccountRepository);

		this.customerRepository = customerRepository;
		this.customerAccountRepository = customerAccountRepository;
	}

	/* ***** Implementation ***** */

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Customer addCustomer(Customer customer)
			throws CustomerAlreadyExistException, IllegalArgumentException, IllegalStateException {
		LOG.debug(">> addCustomer(Customer customer = {})", customer);

		if (customer == null)
			throw new IllegalArgumentException("Argumet must be not NULL. ");
		if (customer.getLogin() == null)
			throw new IllegalArgumentException("Argumet not valid. ");
		if (customer.getPassword() == null)
			throw new IllegalArgumentException("Argumet not valid. ");

		Customer customerExist = this.customerRepository.readByLogin(customer.getLogin());
		if (customerExist != null)
			throw new CustomerAlreadyExistException("Customer is already in database. ");

		customer.setId((long) customer.getLogin().hashCode());
		customer.setVersion(1);
		customer.setTimestamp(Calendar.getInstance().getTime());
		customer.setActiveAfter(Calendar.getInstance().getTime());
		customer.setActiveUntil(null);

		int inserted = this.customerRepository.insert(customer);

		if (inserted != 1)
			throw new IllegalStateException("Database error. ");

		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setId((long) customer.getLogin().hashCode());
		customerAccount.setVersion(1);
		customerAccount.setTimestamp(Calendar.getInstance().getTime());
		customerAccount.setCurrency(Currency.RUB);
		customerAccount.setBalance(0);
		customerAccount.setCustomer(customer);

		int insertedAccount = this.customerAccountRepository.insert(customerAccount);

		if (insertedAccount != 1)
			throw new IllegalStateException("Database error. ");

		customer.setCustomerAccount(customerAccount);

		LOG.debug(">> addCustomer(Customer customer): {}", customer);
		return customer;
	}

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CustomerAccount getCustomerAccounts(Customer customer) throws CustomerUnathorizedException,
			CustomerNotExistException, IllegalArgumentException, IllegalStateException {
		LOG.debug(">> getBalance(Customer customer = {})", customer);

		if (customer == null)
			throw new IllegalArgumentException("Argumet must be not NULL. ");
		if (customer.getLogin() == null)
			throw new IllegalArgumentException("Argumet not valid. ");
		if (customer.getPassword() == null)
			throw new IllegalArgumentException("Argumet not valid. ");

		Customer customerLocal = this.customerRepository.readByLogin(customer.getLogin());
		if (customerLocal == null)
			throw new CustomerNotExistException("Customer not found in database. ");
		if (customerLocal.getActiveUntil() != null
				&& customerLocal.getActiveUntil().before(Calendar.getInstance().getTime()))
			throw new CustomerNotExistException("Customer not found in database. ");
		if (customerLocal.getPassword() == null || customer.getPassword() == null
				|| !customerLocal.getPassword().equals(customer.getPassword()))
			throw new CustomerUnathorizedException("Customer unathorized. ");		

		List<CustomerAccount> customerAccountList = this.customerAccountRepository.selectBy(customerLocal);

		CustomerAccount result = new CustomerAccount().setBalance(0).setCurrency(Currency.RUB);
		for (CustomerAccount customerAccount : customerAccountList) {
			switch (customerAccount.getCurrency()) {
			case RUB:
				result.setBalance(result.getBalance() + customerAccount.getBalance());
				break;
			// TODO: Currency conversion.
			default:
				break;
			}
		}

		LOG.debug("<< getBalance(Customer customer): {}", result);
		return result;
	}

	/* ***** Override ***** */

	@Override
	public String toString() {
		return "CustomerServiceImpl [customerRepository=" + customerRepository + ", customerAccountRepository="
				+ customerAccountRepository + "]";
	}

}
