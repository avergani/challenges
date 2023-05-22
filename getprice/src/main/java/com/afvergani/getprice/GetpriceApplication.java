package com.afvergani.getprice;

import com.afvergani.getprice.repository.IPriceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GetpriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetpriceApplication.class, args);
    }

}
