package org.demo.evotor.web;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;
import org.demo.evotor.exception.CustomerNotExistException;
import org.demo.evotor.exception.CustomerUnathorizedException;
import org.demo.evotor.service.CustomerService;
import org.demo.evotor.web.dto.Balance;
import org.demo.evotor.web.dto.ClientCommand;
import org.demo.evotor.web.dto.ClientResult;
import org.demo.evotor.web.dto.ClientResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	/* Class */

	public static DecimalFormatSymbols decimalFormatSymbols;
	public static DecimalFormat decimalFormat;

	static {
		decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setCurrencySymbol("");
		decimalFormatSymbols.setDecimalSeparator('.');

		decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		decimalFormat.setGroupingUsed(false);
	}

	public static String formatBalance(int balance, int point) {
		return decimalFormat.format((double) balance / (Math.pow(10, point)));
	}

	/* Instance */

	protected CustomerService customerService;

	/**
	 * Default
	 */
	public CustomerController(@Autowired CustomerService customerService) {
		super();
		LOG.trace("CustomerController(@Autowired CustomerService customerService = {})", customerService);
		this.customerService = customerService;
	}

	/* ***** Implementation ***** */

	@PostMapping(path = "/create")
	@ResponseBody
	public ClientResult doCreate(@RequestBody ClientCommand clientCommand) {
		LOG.debug(">> doCreate(Customer customer = {})", clientCommand);

		ClientResult result;

		try {
			if (!"create".equals(clientCommand.getType()))
				throw new IllegalArgumentException("Type [" + clientCommand.getType() + "] is not allowed. ");

			Customer customer = new Customer().setLogin(clientCommand.getLogin())
					.setPassword(clientCommand.getPassword());
			customer = this.customerService.addCustomer(customer);
			result = new ClientResult(ClientResultCode.OK);
		} catch (CustomerAlreadyExistException e) {
			LOG.warn("Fail to create new customer. Customer already exist. ", e);
			result = new ClientResult(ClientResultCode.EXIST);
		} catch (IllegalArgumentException e) {
			LOG.warn("Fail to create new customer. Bad request. ", e);
			result = new ClientResult(ClientResultCode.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Unexpected error while creating new customer. ", e);
			result = new ClientResult(ClientResultCode.ERROR);
		}

		LOG.debug("<< ClientResult doCreate(): {}", result);
		return result;
	}

	@GetMapping(path = "/balance")
	@ResponseBody
	public ClientResult getBalance(@RequestBody ClientCommand clientCommand) {
		LOG.debug(">> ClientResult getBalance(Customer customer = {})", clientCommand);

		ClientResult result = null;

		try {
			if (!"get-balance".equals(clientCommand.getType()))
				throw new IllegalArgumentException("Type [" + clientCommand.getType() + "] is not allowed. ");
			
			Customer customer = new Customer().setLogin(clientCommand.getLogin())
					.setPassword(clientCommand.getPassword());
			CustomerAccount customerAccount = this.customerService.getCustomerAccounts(customer);
			Balance balance = new Balance(customerAccount.getBalance(), customerAccount.getCurrency().getPoint());
			result = new ClientResult(ClientResultCode.OK).setBalance(balance);
		} catch (CustomerUnathorizedException e) {
			LOG.warn("Unathorized balance request. ", e);
			result = new ClientResult(ClientResultCode.AUTH_FAILED);
		} catch (CustomerNotExistException e) {
			LOG.warn("Unathorized balance request. ", e);
			result = new ClientResult(ClientResultCode.NOT_EXIST);
		} catch (IllegalArgumentException e) {
			LOG.warn("Fail to get balance. Bad request. ", e);
			result = new ClientResult(ClientResultCode.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Unexpected error while balance request. ", e);
			result = new ClientResult(ClientResultCode.ERROR);
		}

		// TODO

		LOG.debug("<< ClientResult getBalance(): {}", result);
		return result;
	}

}
