package com.afvergani.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public interface IProductsService {

        ResponseEntity<String> getProductsAvailable();

}
