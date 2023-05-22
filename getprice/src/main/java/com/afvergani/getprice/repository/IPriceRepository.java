package com.afvergani.getprice.repository;


import com.afvergani.getprice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPriceRepository extends JpaRepository<Product, Long> {

}
