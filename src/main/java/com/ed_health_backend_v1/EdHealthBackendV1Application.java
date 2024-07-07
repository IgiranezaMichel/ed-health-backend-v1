package com.ed_health_backend_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableAsync
@SpringBootApplication
public class EdHealthBackendV1Application {

	public static void main(String[] args) {
		SpringApplication.run(EdHealthBackendV1Application.class, args);
	}

}
