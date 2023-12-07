package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.model.Product;
import com.coderhouse.onlinesales.service.InvoiceService;
import com.coderhouse.onlinesales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "product/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> create (@RequestBody Product product) {
        return new ResponseEntity<ProductDTO>(productService.create(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<ProductDTO>(productService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findAll(@PathVariable Integer id) {
        return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
    }


}
