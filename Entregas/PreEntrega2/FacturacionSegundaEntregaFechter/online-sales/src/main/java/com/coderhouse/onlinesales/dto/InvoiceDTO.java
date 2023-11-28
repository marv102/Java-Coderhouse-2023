package com.coderhouse.onlinesales.dto;

import com.coderhouse.onlinesales.model.Client;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class InvoiceDTO {
    public InvoiceDTO(Integer id, LocalDateTime date, Double total, Integer client_id) {
        this.date = date;
        this.total = total;
        this.client_id = client_id;
    }

    public InvoiceDTO() {
    }
    private Integer id;
    private LocalDateTime date;
    private Double total;
    private Integer client_id;
}
