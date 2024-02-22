package com.greencoffee.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan({"com.greencoffee.common.entity", "com.greencoffee.admin.user"})
public class GreencoffeeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreencoffeeBackEndApplication.class, args);
		
	}

}
