package com.coderhouse.onlinesales.dto;

import lombok.Data;

@Data
public class ProductDTO {
    public ProductDTO(Integer id, String code, String description, Integer stock, Double unitPrice) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    public ProductDTO() {
    }

    private Integer id;
    private String code;
    private String description;
    private Integer stock;
    private Double unitPrice;
}
