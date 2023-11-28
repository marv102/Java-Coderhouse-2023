package com.coderhouse.onlinesales.repository;

import com.coderhouse.onlinesales.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Integer> {
}
