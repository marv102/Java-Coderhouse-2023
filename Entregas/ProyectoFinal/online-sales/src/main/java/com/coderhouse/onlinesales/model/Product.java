package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {

    public Product(Integer id, String code, String description, Integer stock, Double unitPrice) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

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
