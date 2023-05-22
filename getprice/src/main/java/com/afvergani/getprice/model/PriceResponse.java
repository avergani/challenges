package com.afvergani.getprice.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceResponse {

    private int productId;
    private int brandId;
    private int priceList;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

}
