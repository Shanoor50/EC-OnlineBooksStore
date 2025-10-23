package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcOnlineBooksStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcOnlineBooksStoreApplication.class, args);
	}

}
