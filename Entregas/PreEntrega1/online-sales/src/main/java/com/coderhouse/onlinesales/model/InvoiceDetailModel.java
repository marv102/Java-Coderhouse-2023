package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_amount")
    private Integer productAmount;
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "invoice_id",nullable = false)
    private InvoiceModel invoice;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private ProductModel product;
}
