package com.afvergani.ecommerce.service;

import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;


@SpringBootTest
@TestPropertySource(properties = {"product.FilePath=src/test/resources/product.csv", "size.FilePath=src/test/resources/size.csv", "stock.FilePath=src/test/resources/stock.csv"})
public class ReadFilesServiceTest {

    @Autowired
    IReadFilesService readFilesService;

    @Autowired
    ReadFilesService filesService;

    @Test
    public void testReadProductsOk() {

        List<Product> products = readFilesService.readProducts();
        Assertions.assertEquals(5, products.size());
        Assertions.assertEquals(products.get(0).getId(), 1);
    }

    @Test
    public void testReadProductSizeOk() {

        List<Size> sizes = readFilesService.readProductSize();
        Assertions.assertEquals(17, sizes.size());
        System.out.println(sizes.get(0).getId());
        Assertions.assertEquals(sizes.get(0).getId(), 99);
    }

    @Test
    public void testReadStockOk() {

        Map<Integer, Integer> stock = readFilesService.readStock();
        Assertions.assertEquals(15, stock.size());
        Assertions.assertEquals(stock.get(31), 10);
    }

    @Test
    public void testReadProductsInvalidPath() {

        ReflectionTestUtils.setField(filesService, "productFilePath","src/test/resources/product1.csv");
                Assertions.assertThrows(RuntimeException.class, () -> {
                    readFilesService.readProducts();
                });
    }

    @Test
    public void testReadProductSizeInvalidPath() {

        ReflectionTestUtils.setField(filesService, "sizeFilePath","src/test/resources/size1.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            readFilesService.readProductSize();
        });
    }

    @Test
    public void testReadStockInvalidPath() {

        ReflectionTestUtils.setField(filesService, "stockFilePath","src/test/resources/stock1.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            readFilesService.readStock();
        });

    }

}
