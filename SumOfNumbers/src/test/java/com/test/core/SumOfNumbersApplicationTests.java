package com.test.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SumOfNumbersApplicationTests {

	@Autowired
	private SumOfNumbersClass sum;

	@Test
	void testSum() {
		Assertions.assertEquals(4, sum.sum(2, 2), "sum.sum(2, 2) test failed");
	}

}
