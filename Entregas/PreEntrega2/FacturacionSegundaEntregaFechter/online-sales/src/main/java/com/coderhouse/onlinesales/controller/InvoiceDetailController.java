package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDetailDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.InvoiceDetail;
import com.coderhouse.onlinesales.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "invoicedetail")
public class InvoiceDetailController {
    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @PostMapping("/")
    public ResponseEntity<InvoiceDetailDTO> create(@RequestBody InvoiceDetail invoiceDetail) {
        return new ResponseEntity<InvoiceDetailDTO>(invoiceDetailService.create(invoiceDetail), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<InvoiceDetailDTO>(invoiceDetailService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        invoiceDetailService.deleteById(id);
    }
}
