package com.coderhouse.onlinesales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {

    public InvoiceDetail(Integer id, Integer productAmount, Double subtotal, Invoice invoice, Product product) {
        this.id = id;
        this.productAmount = productAmount;
        this.subtotal = subtotal;
        this.invoice = invoice;
        this.product = product;
    }

    public InvoiceDetail(Integer productAmount, Double subtotal, Product product) {
        this.productAmount = productAmount;
        this.subtotal = subtotal;
        this.product = product;
    }

    public InvoiceDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_amount")
    private Integer productAmount;
    private Double subtotal;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "invoice_id",nullable = false)
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

}
