package com.coderhouse.onlinesales.repository;

import com.coderhouse.onlinesales.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
