package com.chill.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.chill.test.mapper")
public class TokenTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenTestApplication.class, args);
    }

}
