package com.coderhouse.onlinesales.repository;

import com.coderhouse.onlinesales.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
