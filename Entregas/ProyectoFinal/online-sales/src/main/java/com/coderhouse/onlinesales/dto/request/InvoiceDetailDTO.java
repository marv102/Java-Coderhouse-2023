package com.coderhouse.onlinesales.dto.request;

import com.coderhouse.onlinesales.model.Invoice;
import lombok.Data;

@Data
public class InvoiceDetailDTO {
    public InvoiceDetailDTO(Integer productAmount, Integer idProduct) {
        this.productAmount = productAmount;
        this.idProduct = idProduct;
    }

    public InvoiceDetailDTO() {
    }

    private Integer productAmount;
    private Integer idProduct;
}
