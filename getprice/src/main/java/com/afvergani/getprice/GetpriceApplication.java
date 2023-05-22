package com.afvergani.getprice;

import com.afvergani.getprice.repository.IPriceRepository;
import com.afvergani.getprice.service.IProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GetpriceApplication {


    public static void main(String[] args) {
        SpringApplication.run(GetpriceApplication.class, args);
    }

}
