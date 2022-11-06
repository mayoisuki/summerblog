package com.panxudong.summerblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.panxudong.summerblog.mapper")
@SpringBootApplication
public class SummerBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummerBlogApplication.class, args);
	}

}
