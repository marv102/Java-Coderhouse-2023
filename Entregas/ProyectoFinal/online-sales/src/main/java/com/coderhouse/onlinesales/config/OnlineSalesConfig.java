package com.coderhouse.onlinesales.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OnlineSalesConfig {

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}

}
