package com.test.springweb;

import com.test.springweb.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductrestapiCacheApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	public void testGetProduct() {
		System.out.println("base url is-"+baseURL);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"3", Product.class);
		assertNotNull(product);
		assertEquals("one plus", product.getName());
	}
	@Test
	public void testCreateProduct() {
		Product product = new Product();
		//product.setId(5L);
		product.setName("Samsung Mobile");
		product.setDescription("Awesome");
		product.setPrice(BigDecimal.valueOf(35000d));
		RestTemplate restTemplate = new RestTemplate();
		Product product1 = restTemplate.postForObject(baseURL, product,Product.class);
		assertNotNull(product1);
		assertNotNull(product1.getId());
		assertEquals("Samsung Mobile", product1.getName());
	}
	@Test
	public void testUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"3", Product.class);
		product.setPrice(BigDecimal.valueOf(35000d));
		restTemplate.put("http://localhost:8080/productapi/products", product);
	}

}
