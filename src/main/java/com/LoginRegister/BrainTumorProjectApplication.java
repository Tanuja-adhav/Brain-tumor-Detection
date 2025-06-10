package com.LoginRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.LoginRegister",
        "com.Image1",
        "com.appointment"
})
@EnableJpaRepositories(basePackages = {
        "com.LoginRegister.Repo",
        "com.appointment"
})
@EntityScan(basePackages = {
        "com.LoginRegister.Entity",  // ✅ corrected package
        "com.appointment"
})
public class BrainTumorProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrainTumorProjectApplication.class, args);
    }
}
