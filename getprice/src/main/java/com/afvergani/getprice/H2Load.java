package com.afvergani.getprice;

import com.afvergani.getprice.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Load {


    private final IProductService productService;
    private final String filePath = "src/main/resources/initialData.csv";
    public H2Load(IProductService productService) {
        this.productService = productService;
    }


    @Bean
    public void loadPricesFromCSV() {
        try {
            productService.loadPricesFromCSV(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
