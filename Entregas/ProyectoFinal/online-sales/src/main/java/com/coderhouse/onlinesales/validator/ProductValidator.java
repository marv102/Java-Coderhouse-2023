package com.coderhouse.onlinesales.validator;

import com.coderhouse.onlinesales.exception.ClientException;
import com.coderhouse.onlinesales.exception.ProductException;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validate(Product product) throws ProductException {

        if(product.getCode()==null){
            throw new ProductException("ERROR: The product code cannot be empty");
        }
        else if(product.getStock()==null){
            throw new ProductException("ERROR: The product stock cannot be empty");
        }
        else if(product.getStock()<0){
            throw new ProductException("ERROR: The product stock cannot be negative");
        }
        else if(product.getUnitPrice()==null){
            throw new ProductException("ERROR: The product unit price cannot be empty");
        }
        else if(product.getUnitPrice()<0){
            throw new ProductException("ERROR: The product unit price cannot be negative");
        }


    }
}
