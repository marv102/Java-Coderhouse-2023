package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name="invoices")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private LocalDateTime date;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private ClientModel client;
}
