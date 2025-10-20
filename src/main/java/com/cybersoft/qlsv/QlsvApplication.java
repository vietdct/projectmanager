package com.cybersoft.qlsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QlsvApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(QlsvApplication.class, args);
	}

}
