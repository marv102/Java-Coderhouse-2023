package com.coderhouse.onlinesales.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class InvoiceResponseDTO {
    public InvoiceResponseDTO(Integer id, LocalDateTime date, Double total, ClientDTO client, Set<InvoiceDetailResponseDTO> invoiceDetails) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.client = client;
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceResponseDTO(LocalDateTime date, Double total, ClientDTO client, Set<InvoiceDetailResponseDTO> invoiceDetails) {
        this.date = date;
        this.total = total;
        this.client = client;
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceResponseDTO() {
    }

    private Integer id;
    private LocalDateTime date;
    private Double total;
    private ClientDTO client;
    private Set<InvoiceDetailResponseDTO> invoiceDetails;
    private List<Integer> stockList;
}
