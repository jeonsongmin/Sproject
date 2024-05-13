package com.smhrd.blurbla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EntityScan(basePackages = {"com.smhrd.blurbla.model"})
//@EnableJpaRepositories(basePackages  = {"com.smhrd.blurbla.model"})
@SpringBootApplication
public class BlurblaApplication {
	// 초기실행 파일. WAS 파일이 내장되어 있음.
	public static void main(String[] args) {
		SpringApplication.run(BlurblaApplication.class, args);
	}

}
