package com.coderhouse.onlinesales.dto;

import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class InvoiceDetailDTO {
    public InvoiceDetailDTO(Integer id, Integer productAmount, Double subtotal, Invoice invoice, Product product) {
        this.id = id;
        this.productAmount = productAmount;
        this.subtotal = subtotal;
        this.invoice = invoice;
        this.product = product;
    }

    public InvoiceDetailDTO() {
    }
    private Integer id;
    private Integer productAmount;
    private Double subtotal;
    private Invoice invoice;
    private Product product;
}
