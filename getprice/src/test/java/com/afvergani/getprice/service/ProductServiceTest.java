package com.afvergani.getprice.service;

import com.afvergani.getprice.model.PriceResponse;
import com.afvergani.getprice.repository.IPriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Autowired
    private IPriceRepository priceRepository;


    @Test
    public void testGetPriceOK() {

        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceResponse> response = productService.getPrice(date, productId, brandId);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getPrice(), 35.5);
    }

    @Test
    public void testGetPriceNotFound() {

        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35456L;
        Long brandId = 1L;

        ResponseEntity<PriceResponse> response = productService.getPrice(date, productId, brandId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(0.0, response.getBody().getPrice());
        Assertions.assertEquals(productId, response.getBody().getProductId());
        Assertions.assertEquals(0, response.getBody().getPriceList());
    }

    @Test
    public void testLoadPricesFromCSVOK() throws IOException {

        String filePath = "src/test/resources/initialData.csv";
        LocalDateTime date = LocalDateTime.parse("2020-06-13T16:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        productService.loadPricesFromCSV(filePath);
        ResponseEntity<PriceResponse> response = productService.getPrice(date, productId, brandId);
        Assertions.assertEquals(35.5, response.getBody().getPrice());
    }

    @Test
    public void testLoadPricesFromCSVFileNotFound() {

        String filePath = "src/test/resources/prices.csv";
        Assertions.assertThrows(RuntimeException.class, () -> {
            productService.loadPricesFromCSV(filePath);
        });

    }


}
