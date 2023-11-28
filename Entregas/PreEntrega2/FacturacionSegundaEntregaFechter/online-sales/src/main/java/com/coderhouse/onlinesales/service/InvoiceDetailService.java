package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDetailDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.InvoiceDetail;
import com.coderhouse.onlinesales.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailDTO create(InvoiceDetail invoiceDetail) {
        InvoiceDetail invoiceDetailSaved = invoiceDetailRepository.save(invoiceDetail);
        return new InvoiceDetailDTO(invoiceDetailSaved.getId(), invoiceDetailSaved.getProductAmount(),invoiceDetailSaved.getSubtotal(), invoiceDetailSaved.getInvoice(),invoiceDetailSaved.getProduct());
    }

    public InvoiceDetailDTO findById(Integer id) {
        Optional<InvoiceDetail> invoiceDetailOptional = invoiceDetailRepository.findById(id);
        if(invoiceDetailOptional.isPresent()){
            InvoiceDetail invoiceDetail = invoiceDetailOptional.get();
            return new InvoiceDetailDTO(invoiceDetail.getId(), invoiceDetail.getProductAmount(),invoiceDetail.getSubtotal(), invoiceDetail.getInvoice(),invoiceDetail.getProduct());
        }
        return null;
    }

    public void deleteById(Integer id){
        invoiceDetailRepository.deleteById(id);
    }
}
