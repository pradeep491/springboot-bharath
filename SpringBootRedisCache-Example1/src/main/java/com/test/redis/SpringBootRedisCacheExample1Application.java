package com.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootRedisCacheExample1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheExample1Application.class, args);
	}

}
