package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.model.Product;
import com.coderhouse.onlinesales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDTO create(Product invoice) {
        Product productSaved = productRepository.save(invoice);
        return new ProductDTO(productSaved.getId(), productSaved.getCode(),productSaved.getDescription(), productSaved.getStock(),productSaved.getUnitPrice());

    }

    public ProductDTO findById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return new ProductDTO(product.getId(), product.getCode(),product.getDescription(), product.getStock(),product.getUnitPrice());
        }
        return null;
    }

    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }
}
