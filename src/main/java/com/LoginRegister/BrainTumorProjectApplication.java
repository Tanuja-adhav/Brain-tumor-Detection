package com.LoginRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.Image1","com.LoginRegister"})
public class BrainTumorProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainTumorProjectApplication.class, args);
	}

}
