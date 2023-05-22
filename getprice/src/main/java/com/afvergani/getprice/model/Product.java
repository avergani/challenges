package com.afvergani.getprice.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int brandId;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private int priceList;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private int priority;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String currency;

}
