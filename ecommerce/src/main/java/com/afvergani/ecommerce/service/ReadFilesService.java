package com.afvergani.ecommerce.service;


import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Setter
public class ReadFilesService implements IReadFilesService {

    private static final Logger logger = LogManager.getLogger(ReadFilesService.class);

    private  String productFile = "src/main/resources/product.csv";
    private  String sizeFile = "src/main/resources/size.csv";
    private  String stockFile = "src/main/resources/stock.csv";



    @Override
    public List<Product> readProducts() {
        logger.info("readProducts");
        logger.debug("productFile: " + productFile);
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(productFile))) {
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
        logger.debug("products: " + products);
        return products;
    }

        @Override
        public List<Size> readProductSize () {
        logger.info("readProductSize");
        logger.debug("sizeFile: " + sizeFile);
            List<Size> sizes = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(sizeFile))) {
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
            logger.debug("sizes: " + sizes);
            return sizes;
        }

        @Override
        public Map<Integer,Integer> readStock () {
        logger.info("readStock");
        logger.debug("stockFile: " + stockFile);
        Map<Integer,Integer> stockMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stockFile))) {
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
        logger.debug("stockMap: " + stockMap);
        return stockMap;
        }
}
