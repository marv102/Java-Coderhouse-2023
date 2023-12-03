package com.coderhouse.onlinesales.dto;

import com.coderhouse.onlinesales.model.Invoice;
import lombok.Data;

@Data
public class InvoiceDetailDTO {
    public InvoiceDetailDTO(Integer id, Integer productAmount, Double subtotal, Integer idInvoice, Integer idProduct) {
        this.id = id;
        this.productAmount = productAmount;
        this.subtotal = subtotal;
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
    }

    public InvoiceDetailDTO(Integer productAmount, Double subtotal, Integer idInvoice, Integer idProduct) {
        this.productAmount = productAmount;
        this.subtotal = subtotal;
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
    }

    public InvoiceDetailDTO(Integer productAmount, Integer idProduct) {
        this.productAmount = productAmount;
        this.idProduct = idProduct;
    }

    public InvoiceDetailDTO() {
    }
    private Integer id;
    private Integer productAmount;
    private Double subtotal;
    private Integer idInvoice;
    private Integer idProduct;
}
