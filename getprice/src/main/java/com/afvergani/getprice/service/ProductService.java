package com.afvergani.getprice.service;

import com.afvergani.getprice.model.PriceResponse;
import com.afvergani.getprice.model.Product;
import com.afvergani.getprice.repository.IPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IPriceRepository priceRepository;

    @Override
    public Product getPrice(LocalDateTime date, Long productId, Long brandId) {


    List<Product> products = null;
    PriceResponse response = new PriceResponse();
    response.setProductId(productId.intValue());
    response.setBrandId(brandId.intValue());

    Product applicablePrice = null;
    int highestPriority = Integer.MIN_VALUE;


    for (Product product : products) {
        if (product.getProductId() == productId && product.getBrandId() == brandId && date.isAfter(product.getStartDate()) && date.isBefore(product.getEndDate())){
            if(product.getPriority() > highestPriority) {
                applicablePrice = product;
                highestPriority = product.getPriority();
            }
        }
    }

    if (applicablePrice != null) {
        response.setPriceList(applicablePrice.getPriceList());
        response.setStartDate(applicablePrice.getStartDate());
        response.setEndDate(applicablePrice.getEndDate());
        response.setPrice(applicablePrice.getPrice());
        response.setCurrency(applicablePrice.getCurrency());
    }
        return new Product();
    }


    @Override
    public Optional<Product> findProductsByInputData(Long productId, Long brandId) {
        return priceRepository.findById(productId);
    }

    @Override
    public void loadPricesFromCSV() throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/src/main/resources/prices.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
