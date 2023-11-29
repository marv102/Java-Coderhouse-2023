package com.coderhouse.onlinesales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
@Table(name="invoices")
public class Invoice {
    public Invoice(Integer id, LocalDateTime date, Double total, Client client, List<InvoiceDetail> invoiceDetails) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.client = client;
        this.invoiceDetails = invoiceDetails;
    }

    public Invoice(LocalDateTime date, Double total, Client client, List<InvoiceDetail> invoiceDetails) {
        this.date = date;
        this.total = total;
        this.client = client;
        this.invoiceDetails = invoiceDetails;
    }

    public Invoice() {
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
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;

    public void addDetails(List<InvoiceDetail> newInvoiceDetails) {
        for (InvoiceDetail invoiceDetail : newInvoiceDetails) {
            invoiceDetail.setInvoice(this);
        }
        this.invoiceDetails.addAll(newInvoiceDetails);
    }
}
