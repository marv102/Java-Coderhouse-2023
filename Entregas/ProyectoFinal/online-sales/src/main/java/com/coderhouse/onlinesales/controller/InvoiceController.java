package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.dto.InvoiceResponseDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.service.ClientService;
import com.coderhouse.onlinesales.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "invoice/")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/")
    public ResponseEntity<InvoiceResponseDTO> create (@RequestBody InvoiceDTO invoiceDTO) {
        return new ResponseEntity<InvoiceResponseDTO>(invoiceService.create(invoiceDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<InvoiceResponseDTO>(invoiceService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        invoiceService.deleteById(id);
    }
}
