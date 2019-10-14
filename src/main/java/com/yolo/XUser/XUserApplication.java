package com.yolo.XUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class XUserApplication {

	public static void main(String[] args) {


		SpringApplication.run(XUserApplication.class, args);
	}

}
