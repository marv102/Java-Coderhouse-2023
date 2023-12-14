package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.model.Product;
import com.coderhouse.onlinesales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDTO create(Product product) {
        Product productSaved = productRepository.save(product);
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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductDTO updateById(Integer id, ProductDTO productDTO) {
        ProductDTO productFound = this.findById(id);

        if(!productFound.equals(null)){
            Product product = new Product();
            product.setId(id);
            product.setCode(productDTO.getCode());
            product.setDescription(productDTO.getDescription());
            product.setStock(productDTO.getStock());
            product.setUnitPrice(productDTO.getUnitPrice());
            productRepository.save(product);
            productDTO.setId(id);
            return productDTO;
        }

        return  null;
    }


}
