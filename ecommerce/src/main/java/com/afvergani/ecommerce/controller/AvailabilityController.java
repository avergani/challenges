package com.afvergani.ecommerce.controller;


import com.afvergani.ecommerce.service.IProductsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailabilityController {

    private final IProductsService productsService;
    private static final Logger logger = LogManager.getLogger(AvailabilityController.class);

    public AvailabilityController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/productsAvailability")
    public ResponseEntity<?> getProductsAvailability() {
        logger.info("Entering availability controller");
        return  productsService.getProductsAvailable();
    }


}
