package com.afvergani.ecommerce.controller;


import com.afvergani.ecommerce.service.IProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailabilityController {

    private final IProductsService productsService;

    public AvailabilityController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/productsAvailability")
    public String getProductsAvailability() {
        return  productsService.getProductsAvailable();
    }


}
