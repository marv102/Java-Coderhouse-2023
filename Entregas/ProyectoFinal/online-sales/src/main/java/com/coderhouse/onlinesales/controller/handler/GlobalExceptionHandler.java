package com.coderhouse.onlinesales.controller.handler;


import com.coderhouse.onlinesales.exception.ClientException;
import com.coderhouse.onlinesales.exception.InvoiceException;
import com.coderhouse.onlinesales.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler (InvoiceException.class)
    public ResponseEntity<?> invoiceExceptionHandler (InvoiceException invoiceException){
        return new ResponseEntity<>(invoiceException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ClientException.class)
    public ResponseEntity<?> clientExceptionHandler (ClientException clientException){
        return new ResponseEntity<>(clientException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ProductException.class)
    public ResponseEntity<?> productExceptionHandler (ProductException productException){
        return new ResponseEntity<>(productException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
