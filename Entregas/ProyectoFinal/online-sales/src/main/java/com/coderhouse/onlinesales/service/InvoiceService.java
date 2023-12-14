package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.request.InvoiceDTO;
import com.coderhouse.onlinesales.dto.request.InvoiceDetailDTO;
import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.response.InvoiceDetailResponseDTO;
import com.coderhouse.onlinesales.dto.response.InvoiceResponseDTO;
import com.coderhouse.onlinesales.model.*;
import com.coderhouse.onlinesales.repository.ClientRepository;
import com.coderhouse.onlinesales.repository.InvoiceRepository;
import com.coderhouse.onlinesales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public InvoiceResponseDTO create(InvoiceDTO invoiceDTO) {

        boolean clientExists = clientExits(invoiceDTO.getClient().getId());

        boolean productsExist = productsExist(invoiceDTO.getInvoiceDetails());

        boolean stockExists = stockExists(invoiceDTO.getInvoiceDetails());

        if (clientExists && productsExist && stockExists) {

            Invoice invoice = createInvoice(invoiceDTO);

            Set<InvoiceDetailResponseDTO> detailsResponseDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());

            Client client = clientRepository.findById(invoiceDTO.getClient().getId()).get();

            ClientDTO clientDTO = new ClientDTO(client.getId(), client.getFirstName(),client.getLastName(),client.getDocumentNumber());

            Invoice invoiceSaved = invoiceRepository.save(invoice);

            Integer productQuantity = calculateAmount(detailsResponseDTO);

            return new InvoiceResponseDTO( invoiceSaved.getId(),
                                           invoiceSaved.getDate(),
                                           invoiceSaved.getTotal(),
                                           productQuantity,
                                           clientDTO,
                                           detailsResponseDTO );
        }
            return null;
    }

    public InvoiceResponseDTO findById(Integer id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

        if(invoiceOptional.isPresent()){
            Invoice invoice = invoiceOptional.get();
            Set<InvoiceDetailResponseDTO> invoiceDetailsDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());
            Client client = clientRepository.findById(invoice.getClient().getId()).get();
            ClientDTO clientDTO = new ClientDTO(client.getId(), client.getFirstName(),client.getLastName(),client.getDocumentNumber());
            Integer productQuantity = calculateAmount(invoiceDetailsDTO);
            return new InvoiceResponseDTO(invoice.getId(), invoice.getDate(),invoice.getTotal(),productQuantity, clientDTO,invoiceDetailsDTO);
        }
        return null;
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
        LocalDateTime date;

        try {
            WorldClock worldClock = this.restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now",WorldClock.class);

            date = worldClock.getLocalDateTime();

        }catch (RestClientException re){
            date = LocalDateTime.now();
        }

        Set<InvoiceDetail> invoiceDetails = getInvoiceDetailsFromDTO(invoiceDTO.getInvoiceDetails());

        Double total = calculateTotal(invoiceDetails);

        Client client = clientRepository.findById(invoiceDTO.getClient().getId()).get();

        Invoice invoice = new Invoice(date,total,client,invoiceDetails);

        invoice.addDetails(invoiceDetails);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        this.updateStock(invoiceDetails);

        return savedInvoice;
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
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

    public Integer calculateAmount(Set<InvoiceDetailResponseDTO> invoiceDetails){
        Integer total = 0;

        for(InvoiceDetailResponseDTO invoiceDetail : invoiceDetails){
            try{
                total+=invoiceDetail.getAmount();
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

    public Set<InvoiceDetailResponseDTO> getDTOFromInvoiceDetails(Set<InvoiceDetail> invoiceDetails){
        Set<InvoiceDetailResponseDTO> invoiceDetailsDTO = new HashSet<>();

        for(InvoiceDetail invoiceDetail : invoiceDetails){
            Integer id = invoiceDetail.getId();
            Product product = productRepository.findById(invoiceDetail.getProduct().getId()).get();
            String productDescription = product.getDescription();
            String  code = product.getCode();
            Double unitPrice = product.getUnitPrice();
            Integer productAmount = invoiceDetail.getProductAmount();
            Double subtotal = invoiceDetail.getSubtotal();

            invoiceDetailsDTO.add(new InvoiceDetailResponseDTO(id,productDescription,code,unitPrice,productAmount,subtotal));
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
