package com.pharmacy.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.pharmacy.management.dao")
public class PharmacyMagApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyMagApplication.class, args);
    }

}
