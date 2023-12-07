package com.coderhouse.onlinesales;

import com.coderhouse.onlinesales.model.WorldTime;
import org.aspectj.weaver.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class FacturacionPrimeraEntregaFechter{

	public static void main(String[] args) {
		SpringApplication.run(FacturacionPrimeraEntregaFechter.class, args);
	}
}
