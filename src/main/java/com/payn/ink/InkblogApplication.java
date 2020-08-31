package com.payn.ink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.payn.ink.mapper")//这样就不用每个类加上@Mapper注解
@EnableTransactionManagement
public class InkblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(InkblogApplication.class, args);
	}

}
