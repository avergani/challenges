package com.afvergani.getprice.service;

import com.afvergani.getprice.model.PriceResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IProductService {


    ResponseEntity<PriceResponse> getPrice(LocalDateTime date, Long productId, Long brandId);

    List<Optional> findProductsByInputData(Long productId, Long brandId);

    void loadPricesFromCSV(String filePath) throws IOException;
}
