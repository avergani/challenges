package com.afvergani.ecommerce.service;

import com.afvergani.ecommerce.model.Product;
import com.afvergani.ecommerce.model.Size;
import com.afvergani.ecommerce.model.Stock;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsService implements IProductsService {

    IReadFilesService readFilesService = new ReadFilesService();
    List<Integer> visibleProducts = new ArrayList<>();
    List<Product> products = readFilesService.readProducts();
    List<Size> sizes = readFilesService.readProductSize();
    Map<Integer, Integer> stocks = readFilesService.readStock();

    public String getProductsAvailable() {

        for (Product product : products) {

            boolean isSpecial = false;
            boolean isBackSoon = false;
            boolean isNonSpecialWithStock = false;
            boolean isSpecialWithStock = false;

            for (Size size : sizes) {
                if (product.getId() == size.getProductId()) {
                    if (size.isSpecial()) {
                        isSpecial = true;
                    }
                    if (size.isBackSoon()) {
                        isBackSoon = true;
                    }
                    if (stocks.getOrDefault(size.getId(),0) > 0) {
                        if (size.isSpecial()) {
                            isSpecialWithStock = true;
                        } else {
                            isNonSpecialWithStock = true;
                        }
                    }
                }
            }
            System.out.println("Producto: " + product.getId() + " isSpecial: " + isSpecial + " isBackSoon: " + isBackSoon + " isNonSpecialWithStock: " + isNonSpecialWithStock + " isSpecialWithStock: " + isSpecialWithStock);
            if (isSpecial && isSpecialWithStock && (isNonSpecialWithStock || isBackSoon)) {
                visibleProducts.add(product.getId());
            }else{
                if (isNonSpecialWithStock || (isBackSoon && !isSpecial)) {
                    visibleProducts.add(product.getId());
                }
            }

        }
        if (!visibleProducts.isEmpty()) {
            Collections.sort(visibleProducts);

            System.out.println("Productos disponibles para la venta: " + visibleProducts);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < visibleProducts.size(); i++) {
                result.append(visibleProducts.get(i));
                if (i < visibleProducts.size() - 1) {
                    result.append(",");
                }
            }
                return result.toString();

        }

        return "No existen productos diponibles para la venta";
    }
}
