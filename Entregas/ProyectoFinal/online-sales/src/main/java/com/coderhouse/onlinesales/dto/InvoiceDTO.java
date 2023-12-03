package com.coderhouse.onlinesales.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class InvoiceDTO {
    public InvoiceDTO(Integer id, LocalDateTime date, Double total, Integer idClient, Set<InvoiceDetailDTO> invoiceDetails) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.idClient = idClient;
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceDTO(LocalDateTime date, Double total, Integer idClient, Set<InvoiceDetailDTO> invoiceDetails) {
        this.date = date;
        this.total = total;
        this.idClient = idClient;
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceDTO() {
    }

    private Integer id;
    private LocalDateTime date;
    private Double total;
    private Integer idClient;
    private Set<InvoiceDetailDTO> invoiceDetails;
}
