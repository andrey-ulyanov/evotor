package org.demo.evotor.web;

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

@SpringBootTest
class CustomerControllerCreateTest {

	@Autowired
	CustomerController customerController;

	@Test
	void testOK() {
		ClientCommand clientCommand = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerCreateTestOk", "password");

		ClientResult clientResult = customerController.doCreate(clientCommand);
		assertNotNull(clientResult);
		assertNotNull(clientResult.getResult());
		assertNull(clientResult.getBalance());
		assertEquals(ClientResultCode.OK, clientResult.getResult());
	}

	@Test
	void testEXIST() {
		ClientCommand clientCommand1 = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerCreateTestExist", "password");
		ClientCommand clientCommand2 = new ClientCommand(ClientCommandType.CREATE, "CustomerControllerCreateTestExist", "password");

		ClientResult clientResult1 = customerController.doCreate(clientCommand1);
		assertNotNull(clientResult1);
		assertNotNull(clientResult1.getResult());
		assertNull(clientResult1.getBalance());
		assertEquals(ClientResultCode.OK, clientResult1.getResult());

		ClientResult clientResult2 = customerController.doCreate(clientCommand2);
		assertNotNull(clientResult2);
		assertNotNull(clientResult2.getResult());
		assertNull(clientResult2.getBalance());
		assertEquals(ClientResultCode.EXIST, clientResult2.getResult());
	}

}
