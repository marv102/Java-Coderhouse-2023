package com.coderhouse.onlinesales.repository;

import com.coderhouse.onlinesales.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
