package org.demo.evotor.web;

import javax.validation.Valid;

import org.demo.evotor.domain.Customer;
import org.demo.evotor.domain.CustomerAccount;
import org.demo.evotor.exception.CustomerAlreadyExistException;
import org.demo.evotor.exception.CustomerNotExistException;
import org.demo.evotor.exception.CustomerUnathorizedException;
import org.demo.evotor.service.CustomerService;
import org.demo.evotor.web.dto.Balance;
import org.demo.evotor.web.dto.ClientCommand;
import org.demo.evotor.web.dto.ClientCommandType;
import org.demo.evotor.web.dto.ClientResult;
import org.demo.evotor.web.dto.ClientResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@RestController
@RequestMapping(path = CustomerController.PATH)
public class CustomerController {

	private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	/* Class */

	public static final String PATH = "/customer";
	public static final String PATH_SERVICE1 = "";
	public static final String PATH_SERVICE2 = "/";
	public static final String PATH_CREATE = "/create";
	public static final String PATH_BALANCE = "/balance";

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

	/**
	 * 
	 * @param clientCommand
	 * @return
	 */
	@PostMapping(path = PATH_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ClientResult doCreate(@Valid @RequestBody ClientCommand clientCommand) {
		LOG.debug(">> doCreate(ClientCommand clientCommand = {})", clientCommand);

		ClientResult result;

		try {
			if (!ClientCommandType.CREATE.equals(clientCommand.getType()))
				throw new IllegalArgumentException("Type [" + clientCommand.getType() + "] is not allowed. ");

			Customer customer = new Customer().setLogin(clientCommand.getLogin())
					.setPasswordClear(clientCommand.getPassword());
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

		LOG.debug("<< doCreate(): {}", result);
		return result;
	}

	/**
	 * 
	 * @param clientCommand
	 * @return
	 */
	@GetMapping(path = PATH_BALANCE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ClientResult getBalance(@Valid @RequestBody ClientCommand clientCommand) {
		LOG.debug(">> getBalance(ClientCommand clientCommand = {})", clientCommand);

		ClientResult result;

		try {
			if (!ClientCommandType.GET_BALANCE.equals(clientCommand.getType()))
				throw new IllegalArgumentException("Type [" + clientCommand.getType() + "] is not allowed. ");

			Customer customer = new Customer().setLogin(clientCommand.getLogin())
					.setPasswordClear(clientCommand.getPassword());
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

		LOG.debug("<< getBalance(ClientCommand clientCommand): {}", result);
		return result;
	}

	/**
	 * 
	 * @param clientCommand
	 * @return
	 */
	@PostMapping(path = { PATH_SERVICE1,
			PATH_SERVICE2 }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ClientResult service(@Valid @RequestBody ClientCommand clientCommand) {
		LOG.debug(">> service(ClientCommand clientCommand = {})", clientCommand);

		ClientResult result;

		try {
			switch (clientCommand.getType()) {
			case CREATE:
				result = this.doCreate(clientCommand);
				break;
			case GET_BALANCE:
				result = this.getBalance(clientCommand);
				break;
			default:
				result = new ClientResult(ClientResultCode.ERROR);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Unexpected error while service. ", e);
			result = new ClientResult(ClientResultCode.ERROR);
		}

		LOG.debug("<< service(): {}", result);
		return result;
	}

}
