package com.coderhouse.onlinesales.dto.request;

import com.coderhouse.onlinesales.dto.ClientDTO;
import lombok.Data;

import java.util.Set;

@Data
public class InvoiceDTO {

    public InvoiceDTO(ClientDTO client, Set<InvoiceDetailDTO> invoiceDetails) {
        this.client = client;
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceDTO() {
    }

    private ClientDTO client;
    private Set<InvoiceDetailDTO> invoiceDetails;
}
