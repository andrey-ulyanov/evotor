package org.demo.evotor.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.demo.evotor.repository.mysql.AccountRepositroyMysql;
import org.demo.evotor.web.dto.Balance;
import org.demo.evotor.web.dto.ClientResult;
import org.demo.evotor.web.dto.ClientResultCode;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	
	/* Instance */
	
	@Autowired
	public AccountRepositroyMysql accountRepositroyMysql;
	
	/**
	 * Default 
	 */
	public CustomerController() {
		super();
		LOG.trace("ClientAPI()");
	}
	
	/* ***** Implementation ***** */

	@PostMapping(path = "/create")
	public ClientResult doCreate() {
		LOG.debug(">> ClientResult doCreate()");
		ClientResult result = new ClientResult(ClientResultCode.OK).setBalance(new Balance("123.45"));
		
		LOG.debug("<< ClientResult doCreate(): {}", result);
		return result;
	}
	
	@GetMapping(path = "/balance")
	public ClientResult getBalance() {
		LOG.debug(">> ClientResult getBalance()");
		ClientResult result =  new ClientResult(ClientResultCode.OK).setBalance(new Balance("123.45"));		
		
		LOG.warn("accountRepositroyMysql: " + accountRepositroyMysql);
		LOG.warn("accountRepositroyMysql: " + accountRepositroyMysql.select());
		
		LOG.debug("<< ClientResult getBalance(): {}", result);
		return result;
	}
	
}
