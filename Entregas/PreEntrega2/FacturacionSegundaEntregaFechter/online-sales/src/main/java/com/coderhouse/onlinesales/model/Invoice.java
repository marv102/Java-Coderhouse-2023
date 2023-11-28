package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="invoices")
public class Invoice {
    public Invoice(Integer id, LocalDateTime date, Double total, Client client) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private LocalDateTime date;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
}
