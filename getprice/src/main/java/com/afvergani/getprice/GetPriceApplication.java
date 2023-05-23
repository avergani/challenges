package com.afvergani.getprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.afvergani")
@PropertySource(value = "src/main/resources:log4j2.xml", ignoreResourceNotFound = true)
public class GetPriceApplication {


    public static void main(String[] args) {
        SpringApplication.run(GetPriceApplication.class, args);
    }

}
