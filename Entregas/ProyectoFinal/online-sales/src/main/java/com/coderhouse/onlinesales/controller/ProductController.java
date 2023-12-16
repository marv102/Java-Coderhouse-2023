package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.exception.ProductException;
import com.coderhouse.onlinesales.model.Product;
import com.coderhouse.onlinesales.service.ProductService;
import com.coderhouse.onlinesales.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> create (@RequestBody Product product) throws ProductException {
        productValidator.validate(product);
        return new ResponseEntity<ProductDTO>(productService.create(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<ProductDTO>(productService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateById(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
            return new ResponseEntity<ProductDTO>(productService.updateById(id,productDTO), HttpStatus.OK);
    }


}
