package com.afvergani.ecommerce.service;

import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class ReadFilesServiceTest {

    @Autowired
    IReadFilesService readFilesService;

    @Autowired
    ReadFilesService filesService;

    @Test
    public void testReadProductsOk() {

        filesService.setProductFile("src/test/resources/product.csv");
        List<Product> products = readFilesService.readProducts();
        Assertions.assertEquals(5, products.size());
        Assertions.assertEquals(products.get(0).getId(), 1);
    }

    @Test
    public void testReadProductSizeOk() {

        filesService.setSizeFile("src/test/resources/size.csv");
        List<Size> sizes = readFilesService.readProductSize();
        Assertions.assertEquals(17, sizes.size());
        System.out.println(sizes.get(0).getId());
        Assertions.assertEquals(sizes.get(0).getId(), 99);
    }

    @Test
    public void testReadStockOk() {

        filesService.setStockFile("src/test/resources/stock.csv");
        Map<Integer, Integer> stock = readFilesService.readStock();
        Assertions.assertEquals(15, stock.size());
        Assertions.assertEquals(stock.get(31), 10);
    }

    @Test
    public void testReadProductsInvalidPath() {

        filesService.setProductFile("src/test/resources/products1.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            readFilesService.readProducts();
        });
    }

    @Test
    public void testReadProductSizeInvalidPath() {

        filesService.setSizeFile("src/test/resources/size1.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            readFilesService.readProductSize();
        });
    }

    @Test
    public void testReadStockInvalidPath() {

        filesService.setStockFile("src/test/resources/stock1.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            readFilesService.readStock();
        });

    }

}
