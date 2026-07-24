package com.peopletech;

import com.peopletech.service.AdminUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.peopletech.mapper")
public class PeopleTechnoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PeopleTechnoApplication.class, args);
        ctx.getBean(AdminUserService.class).initAdmin();
    }
}
