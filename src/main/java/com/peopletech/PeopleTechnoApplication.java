package com.peopletech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.peopletech.mapper")
public class PeopleTechnoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeopleTechnoApplication.class, args);
    }
}
