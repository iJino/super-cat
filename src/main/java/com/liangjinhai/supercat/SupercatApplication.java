package com.liangjinhai.supercat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.liangjinhai.supercat.sys.mapper")
public class SupercatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupercatApplication.class, args);
	}
}
