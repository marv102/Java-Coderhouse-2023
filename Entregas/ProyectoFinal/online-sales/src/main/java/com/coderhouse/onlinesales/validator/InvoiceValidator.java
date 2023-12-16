package com.coderhouse.onlinesales.validator;

import com.coderhouse.onlinesales.exception.InvoiceException;
import org.springframework.stereotype.Component;

@Component
public class InvoiceValidator {

    public void validate(boolean clientExists, boolean stockExists, boolean productExists) throws InvoiceException {
        if(!clientExists){
            throw new InvoiceException("ERROR: The client linked to the invoice does not exist");
        }else if(!productExists){
            throw new InvoiceException("ERROR: A product listed in the invoice is nonexistent.");
        }else if (!stockExists){
            throw new InvoiceException("ERROR: The available stock is insufficient to meet the quantity of the product specified in the invoice.");
        }
    }
}
