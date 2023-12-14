package com.coderhouse.onlinesales.dto.response;

import com.coderhouse.onlinesales.model.Invoice;
import lombok.Data;

@Data
public class InvoiceDetailResponseDTO {
    public InvoiceDetailResponseDTO(Integer idInvoiceDetail, String productDescription, String code, Double unitPrice, Integer amount, Double subtotal) {
        this.idInvoiceDetail = idInvoiceDetail;
        this.productDescription = productDescription;
        this.code = code;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.subtotal = subtotal;
    }

    public InvoiceDetailResponseDTO() {
    }

    private Integer idInvoiceDetail;
    private String productDescription;
    private String  code;
    private Double unitPrice;
    private Integer amount;
    private Double  subtotal;
}