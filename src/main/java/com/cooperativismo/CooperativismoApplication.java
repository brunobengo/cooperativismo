package com.cooperativismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class CooperativismoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperativismoApplication.class, args);
	}

}
