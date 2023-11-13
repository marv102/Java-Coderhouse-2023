package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="product_code")
    private String code;
    @Column(name="product_description")
    private String description;
    private Integer stock;
    @Column(name="unit_price")
    private Double unitPrice;
}
