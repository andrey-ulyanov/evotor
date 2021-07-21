package org.demo.evotor.web;

import org.demo.evotor.web.dto.Balance;
import org.demo.evotor.web.dto.ClientCommand;
import org.demo.evotor.web.dto.ClientCommandType;
import org.demo.evotor.web.dto.ClientResult;
import org.demo.evotor.web.dto.ClientResultCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

@SpringBootTest
class CustomerControllerBalanceTest {

	@Autowired
	CustomerController customerController;

	@Test
	void testOK() {
		ClientCommand clientCommand = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerBalanceTestOk", "password");
		ClientResult clientResult = customerController.doCreate(clientCommand);

		clientCommand.setType(ClientCommandType.GET_BALANCE);
		clientResult = customerController.getBalance(clientCommand);
		
		assertNotNull(clientResult);
		assertNotNull(clientResult.getResult());
		assertEquals(ClientResultCode.OK, clientResult.getResult());
		assertNotNull(clientResult.getBalance());
		assertEquals(Balance.formatBalance(0, 2), clientResult.getBalance().getBalance());
	}

	@Test
	void testNotExist() {
		ClientCommand clientCommand = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerBalanceTestExist", "password");
		ClientResult clientResult = customerController.doCreate(clientCommand);
		
		clientCommand.setType(ClientCommandType.GET_BALANCE).setLogin("CustomerControllerBalanceTestNotExist");
		clientResult = customerController.getBalance(clientCommand);

		assertNotNull(clientResult);
		assertNotNull(clientResult.getResult());
		assertEquals(ClientResultCode.NOT_EXIST, clientResult.getResult());
		assertNull(clientResult.getBalance());
	}
	
	@Test
	void testNotauth() {
		ClientCommand clientCommand = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerBalanceTestAuth", "password");
		ClientResult clientResult = customerController.doCreate(clientCommand);
		
		clientCommand.setType(ClientCommandType.GET_BALANCE).setPassword("wrong password");
		clientResult = customerController.getBalance(clientCommand);

		assertNotNull(clientResult);
		assertNotNull(clientResult.getResult());
		assertEquals(ClientResultCode.AUTH_FAILED, clientResult.getResult());
		assertNull(clientResult.getBalance());
	}

}
