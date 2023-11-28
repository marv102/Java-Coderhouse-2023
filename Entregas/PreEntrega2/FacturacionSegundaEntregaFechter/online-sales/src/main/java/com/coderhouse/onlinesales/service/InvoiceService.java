package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.repository.ClientRepository;
import com.coderhouse.onlinesales.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDTO create(InvoiceDTO invoiceDTO) {
        Optional<Client> clientOptional = clientRepository.findById(invoiceDTO.getClient_id());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Invoice invoice = new Invoice(invoiceDTO.getId(),invoiceDTO.getDate(),invoiceDTO.getTotal(), client);
            Invoice invoiceSaved = invoiceRepository.save(invoice);
            return new InvoiceDTO(invoiceSaved.getId(), invoiceSaved.getDate(), invoiceSaved.getTotal(), invoiceSaved.getClient().getId());
        }
            return null;
    }

    public InvoiceDTO findById(Integer id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if(invoiceOptional.isPresent()){
            Invoice invoice = invoiceOptional.get();
            return new InvoiceDTO(invoice.getId(), invoice.getDate(),invoice.getTotal(), invoice.getClient().getId());
        }
        return null;
    }

    public void deleteById(Integer id){
        invoiceRepository.deleteById(id);
    }
}
