package com.coderhouse.onlinesales.dto;

import com.coderhouse.onlinesales.model.Invoice;
import lombok.Data;

@Data
public class InvoiceDetailResponseDTO {
    public InvoiceDetailResponseDTO(Integer idDetail, Integer productDescription, String code, Integer unitPrice, Integer productAmount, Double subtotal) {
        this.idDetail = idDetail;
        this.productDescription = productDescription;
        this.code = code;
        this.unitPrice = unitPrice;
        this.productAmount = productAmount;
        this.subtotal = subtotal;
    }

    private Integer idDetail;
    private Integer productDescription;
    private String  code;
    private Integer unitPrice;
    private Integer productAmount;
    private Double  subtotal;
}