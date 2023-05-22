package com.afvergani.getprice.repository;


import com.afvergani.getprice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPriceRepository extends JpaRepository<Product, Long> {

    List<Optional> findByProductIdAndBrandId(Long productId, Long brandId);
}
