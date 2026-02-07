package com.test.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.core.services.PaymentService;
import com.test.core.services.PaymentServiceImpl;

@SpringBootTest
class CoreApplicationTests {

	@Autowired
	PaymentServiceImpl service;

	@Autowired
	PaymentService service1;

	@Test
	void testDependencyInjection() {
		assertNotNull(service.getDao());
	}

	@Test
	void testDependencyInjectionService() {
		assertNotNull(service1);
	}
}
