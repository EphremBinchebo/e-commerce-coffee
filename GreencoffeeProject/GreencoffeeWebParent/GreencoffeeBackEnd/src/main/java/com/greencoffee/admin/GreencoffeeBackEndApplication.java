package com.greencoffee.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class GreencoffeeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreencoffeeBackEndApplication.class, args);
	}

}
