package com.afvergani.getprice.controller;


import com.afvergani.getprice.model.PriceResponse;
import com.afvergani.getprice.service.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceServiceController {

    private IProductService productService;
    private static final Logger logger = LogManager.getLogger(PriceServiceController.class);


    public PriceServiceController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getPrice")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam("date")LocalDateTime date, @RequestParam("productId")Long productId, @RequestParam("brandId")Long brandId) {
        logger.info("Entering price inquiry controller with the following parameters: date: " + date + " - productId: " + productId + " - brandId: " + brandId);
        return productService.getPrice(date, productId, brandId);
    }
}
