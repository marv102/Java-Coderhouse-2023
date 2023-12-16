package com.coderhouse.onlinesales.dto.request;

import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.model.Invoice;
import lombok.Data;

@Data
public class InvoiceDetailDTO {
    public InvoiceDetailDTO(Integer productAmount, ProductDTO product) {
        this.productAmount = productAmount;
        this.product = product;
    }

    public InvoiceDetailDTO() {
    }

    private Integer productAmount;
    private ProductDTO product;
}
