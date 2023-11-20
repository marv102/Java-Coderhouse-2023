package com.example.clienterest;

import com.example.clienterest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ClienteApiRestFechter {

	@Autowired
	ClienteService clienteService;

	public static void main(String[] args) {
		SpringApplication.run(ClienteApiRestFechter.class, args);
	}

}
