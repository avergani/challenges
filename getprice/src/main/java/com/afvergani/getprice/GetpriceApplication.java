package com.afvergani.getprice;

import com.afvergani.getprice.repository.IPriceRepository;
import com.afvergani.getprice.service.IProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
@PropertySource(value = "src/main/resources:log4j2.xml", ignoreResourceNotFound = true)
public class GetpriceApplication {


    public static void main(String[] args) {
        SpringApplication.run(GetpriceApplication.class, args);
    }

}
