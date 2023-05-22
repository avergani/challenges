package com.afvergani.getprice.service;

import com.afvergani.getprice.model.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IProductService {


    Product getPrice(LocalDateTime date, Long productId, Long brandId);

    Optional<Product> findProductsByInputData(Long productId, Long brandId);

    void loadPricesFromCSV() throws IOException;
}
