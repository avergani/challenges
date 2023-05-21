package com.afvergani.ecommerce.service;

import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import com.afvergani.ecommerce.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface IReadFilesService {

    List<Product> readProducts();

    List<Size> readProductSize();

    Map<Integer,Integer> readStock();
}
