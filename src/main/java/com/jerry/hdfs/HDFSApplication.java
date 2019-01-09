package com.jerry.hdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.jerry")
public class HDFSApplication {

	public static void main(String[] args) {
		SpringApplication.run(HDFSApplication.class, args);
	}

}

