package com.va.voucher_request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class VoucherRequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoucherRequestServiceApplication.class, args);
	}

}
