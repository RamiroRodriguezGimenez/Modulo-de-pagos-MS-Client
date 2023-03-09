package com.client;

import com.client.dto.response.AccountResponse;
import com.client.dto.response.ClientResponse;
import com.client.feignclient.AccountFeignClient;
import com.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ClientServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ClientService clientService;

	@MockBean
	private AccountFeignClient accountFeignClient;

	@Test
	public void testGetAccounts() {
		// Crear una cuenta de prueba
		AccountResponse testAccount = new AccountResponse();
		testAccount.setId(1L);
		testAccount.setNumber("0231123123123");
		List<AccountResponse> testAccounts=new ArrayList<>();
		testAccounts.add(testAccount);
		String accessToken="A";

		// Configurar el mock
		given(accountFeignClient.getByClient(4L, accessToken)).willReturn(testAccounts);

		// Llamar al servicio para obtener el cliente
		 ClientResponse client = clientService.getById(4L, accessToken);

		// Verificar que se devuelva el clientecorrecto
		assertEquals(1L,client.getAccounts().get(0).getId());
		assertEquals("0231123123123", client.getAccounts().get(0).getNumber());
		assertEquals("Adrian",client.getFirstName());
	}

}
