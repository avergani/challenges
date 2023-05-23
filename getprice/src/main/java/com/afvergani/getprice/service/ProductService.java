package com.afvergani.getprice.service;

import com.afvergani.getprice.model.PriceResponse;
import com.afvergani.getprice.model.Product;
import com.afvergani.getprice.repository.IPriceRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private IPriceRepository priceRepository;
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Override
    public ResponseEntity<PriceResponse> getPrice(LocalDateTime date, Long productId, Long brandId) {

    List<Product> products;
    PriceResponse response = new PriceResponse();
    response.setProductId(productId.intValue());
    response.setBrandId(brandId.intValue());

        logger.info("productId: " + productId);
        logger.info("brandId: " + brandId);
        logger.info("date: " + date);

    Product applicablePrice = null;
    int highestPriority = Integer.MIN_VALUE;

    products = findProductsByInputData(productId, brandId).stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Product.class::cast)
            .collect(Collectors.toList());
        logger.debug("Products: " + products);

    for (Product product : products) {
        logger.debug("product: " + product);
        if (product.getProductId().equals(productId) && product.getBrandId() == brandId && (date.isAfter(product.getStartDate()) && date.isBefore(product.getEndDate()))){
            if(product.getPriority() > highestPriority) {
                applicablePrice = product;
                highestPriority = product.getPriority();
                logger.debug("applicablePrice: " + applicablePrice);
                logger.debug("highestPriority: " + highestPriority);
            }
        }
    }
        logger.debug("applicablePrice: " + applicablePrice);

    if (applicablePrice != null) {

        BeanUtils.copyProperties(applicablePrice, response);

        logger.info("response: " + response);
    }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<Optional> findProductsByInputData(Long productId, Long brandId) {
        logger.info("Finding products by productId: " + productId + " and brandId: " + brandId);
        return priceRepository.findByProductIdAndBrandId(productId, brandId);
    }

    @Override
    public void loadPricesFromCSV(String filePath) {

        logger.info("Loading initial data from CSV file");
        logger.debug("filePath: " + filePath);
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if(data.length == 8){
                    Product product = new Product();
                    product.setBrandId(Integer.parseInt(data[0]));
                    product.setStartDate(LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    product.setEndDate(LocalDateTime.parse(data[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    product.setPriceList(Integer.parseInt(data[3]));
                    product.setProductId(Long.parseLong(data[4]));
                    product.setPriority(Integer.parseInt(data[5]));
                    product.setPrice(Double.parseDouble(data[6]));
                    product.setCurrency(data[7]);

                    priceRepository.save(product);

                }else{
                    logger.error("Error loading initial data from CSV file: " + "Wrong number of columns");
                    throw new RuntimeException("Wrong number of columns");
                }
            }
        } catch (IOException e) {
            logger.error("Error loading initial data from CSV file: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            logger.error("Error parsing data from CSV file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Database loaded successfully");
    }
}
