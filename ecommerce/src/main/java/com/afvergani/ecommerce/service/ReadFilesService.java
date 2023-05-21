package com.afvergani.ecommerce.service;


import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import com.afvergani.ecommerce.model.Stock;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadFilesService implements IReadFilesService {


    @Override
    public List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/product.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                int id = Integer.parseInt(attributes[0].trim());
                int sequence = Integer.parseInt(attributes[1].trim());
                products.add(new Product(id, sequence));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return products;
    }

        @Override
        public List<Size> readProductSize () {
            List<Size> sizes = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/size.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] attributes = line.split(",");
                    int id = Integer.parseInt(attributes[0].trim());
                    int product_id = Integer.parseInt(attributes[1].trim());
                    boolean backSoon = Boolean.parseBoolean(attributes[2].trim());
                    boolean special = Boolean.parseBoolean(attributes[3].trim());
                    sizes.add(new Size(id, product_id, backSoon, special));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return sizes;
        }

        @Override
        public Map<Integer,Integer> readStock () {
        Map<Integer,Integer> stockMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stock.csv"))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] attributes = line.split(",");
                int sizeId = Integer.parseInt(attributes[0].trim());
                int quantity = Integer.parseInt(attributes[1].trim());
                stockMap.put(sizeId, quantity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stockMap;
        }
}
