package com.afvergani.getprice.controller;


import com.afvergani.getprice.model.Product;
import com.afvergani.getprice.service.IProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PriceServiceController {

    IProductService productService;

public PriceServiceController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getPrice")
    public Product getPrice(@RequestParam("date")LocalDateTime date, @RequestParam("productId")Long productId, @RequestParam("brandId")Long brandId) {
        return new Product();
    }
}
