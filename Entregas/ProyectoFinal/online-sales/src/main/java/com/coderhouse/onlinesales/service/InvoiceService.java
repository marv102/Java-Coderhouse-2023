package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.dto.InvoiceDetailDTO;
import com.coderhouse.onlinesales.model.*;
import com.coderhouse.onlinesales.repository.ClientRepository;
import com.coderhouse.onlinesales.repository.InvoiceRepository;
import com.coderhouse.onlinesales.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class InvoiceService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    public InvoiceDTO create(InvoiceDTO invoiceDTO) {

        System.out.println("WWWWWWWWWWWW");

        boolean clientExists = clientExits(invoiceDTO.getIdClient());

        boolean productsExist = productsExist(invoiceDTO.getInvoiceDetails());

        boolean stockExists = stockExists(invoiceDTO.getInvoiceDetails());

        if (clientExists && productsExist && stockExists) {

            Invoice invoice = createInvoice(invoiceDTO);

            Set<InvoiceDetailDTO> invoiceDetailsDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());

            Invoice invoiceSaved = invoiceRepository.save(invoice);

            return new InvoiceDTO( invoiceSaved.getId(),
                                   invoiceSaved.getDate(),
                                   invoiceSaved.getTotal(),
                                   invoiceSaved.getClient().getId(),
                                   invoiceDetailsDTO);
        }
            return null;
    }

    public InvoiceDTO findById(Integer id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

        if(invoiceOptional.isPresent()){
            Invoice invoice = invoiceOptional.get();
            Set<InvoiceDetailDTO> invoiceDetailsDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());
            return new InvoiceDTO(invoice.getId(), invoice.getDate(),invoice.getTotal(), invoice.getClient().getId(),invoiceDetailsDTO);
        }
        return null;
    }

    public void deleteById(Integer id){
        invoiceRepository.deleteById(id);
    }

    public boolean clientExits(Integer id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.isPresent() ? true : false;
    }

    public boolean productsExist(Set<InvoiceDetailDTO> invoiceDetails){
        for(InvoiceDetailDTO invoiceDetail : invoiceDetails){

            Optional<Product> productOptional = productRepository.findById(invoiceDetail.getIdProduct());

            if(productOptional.isEmpty()) return false;
        }
        return true;
    }

    public boolean stockExists(Set<InvoiceDetailDTO> invoiceDetails){
        if(!productsExist(invoiceDetails)){
            return false;
        }

            for(InvoiceDetailDTO invoiceDetail : invoiceDetails){

                Product product = productRepository.findById(invoiceDetail.getIdProduct()).get();
                Integer stockAvailable = product.getStock();
                Integer quantityAsked = 0;

                for(InvoiceDetailDTO innerInvoiceDetail : invoiceDetails){

                    if(product.getId().equals(innerInvoiceDetail.getIdProduct())){
                        quantityAsked += innerInvoiceDetail.getProductAmount();
                    }
                }

                if(stockAvailable < quantityAsked) {
                    return false;
                }
            }
            return true;
    }

    public Invoice createInvoice(InvoiceDTO invoiceDTO){

        WorldTime worldTime = this.restTemplate.getForObject("https://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires",WorldTime.class);

        LocalDateTime date = worldTime.getLocalDateTime();

        Set<InvoiceDetail> invoiceDetails = getInvoiceDetailsFromDTO(invoiceDTO.getInvoiceDetails());

        Double total = calculateTotal(invoiceDetails);

        Client client = clientRepository.findById(invoiceDTO.getIdClient()).get();

        Invoice invoice = new Invoice(date,total,client,invoiceDetails);

        invoice.addDetails(invoiceDetails);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        this.updateStock(invoiceDetails);

        return savedInvoice;
    }

    public Double calculateTotal(Set<InvoiceDetail> invoiceDetails){
        Double total = 0d;

        for(InvoiceDetail invoiceDetail : invoiceDetails){
            try{
                total+=invoiceDetail.getSubtotal();
            }catch (NullPointerException ne){
                System.out.println("Total invoice details could not be calculated; the subtotals are empty");
            }
        }

        return total;
    }

    public Set<InvoiceDetail> getInvoiceDetailsFromDTO(Set<InvoiceDetailDTO> invoiceDetailsDTO){
        Set<InvoiceDetail> invoiceDetails = new HashSet<>();

        for(InvoiceDetailDTO invoiceDetailDTO : invoiceDetailsDTO){
            Integer productAmount = invoiceDetailDTO.getProductAmount();
            Product product = productRepository.findById(invoiceDetailDTO.getIdProduct()).get();
            Double subtotal = productAmount * product.getUnitPrice();

            invoiceDetails.add(new InvoiceDetail(productAmount,subtotal,product));
        }
        return invoiceDetails;
    }

    public Set<InvoiceDetailDTO> getDTOFromInvoiceDetails(Set<InvoiceDetail> invoiceDetails){
        Set<InvoiceDetailDTO> invoiceDetailsDTO = new HashSet<>();

        for(InvoiceDetail invoiceDetail : invoiceDetails){
            Integer id = invoiceDetail.getId();
            Integer productAmount = invoiceDetail.getProductAmount();
            Double subtotal = invoiceDetail.getSubtotal();
            Integer idInvoice = invoiceDetail.getInvoice().getId();
            Integer idProduct = invoiceDetail.getProduct().getId();

            invoiceDetailsDTO.add(new InvoiceDetailDTO(id,productAmount,subtotal,idInvoice,idProduct));
        }
        return invoiceDetailsDTO;
    }


    public void updateStock(Set<InvoiceDetail> invoiceDetails){
        for(InvoiceDetail invoiceDetail : invoiceDetails){
            Product productDB = productRepository.findById(invoiceDetail.getProduct().getId()).get();
            productDB.setStock(productDB.getStock() - invoiceDetail.getProductAmount());
            productRepository.save(productDB);
        }
    }

}
