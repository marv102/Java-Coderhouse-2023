package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.InvoiceDTO;
import com.coderhouse.onlinesales.dto.InvoiceDetailDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.model.Invoice;
import com.coderhouse.onlinesales.model.InvoiceDetail;
import com.coderhouse.onlinesales.model.Product;
import com.coderhouse.onlinesales.repository.ClientRepository;
import com.coderhouse.onlinesales.repository.InvoiceRepository;
import com.coderhouse.onlinesales.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDTO create(InvoiceDTO invoiceDTO) {

        boolean clientExists = clientExits(invoiceDTO.getIdClient());

        boolean productsExist = productsExist(invoiceDTO.getInvoiceDetails());

        boolean stockExists = stockExists(invoiceDTO.getInvoiceDetails());

        if (clientExists && productsExist && stockExists) {

            Invoice invoice = createInvoice(invoiceDTO);

            List<InvoiceDetailDTO> invoiceDetailsDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());

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
            List<InvoiceDetailDTO> invoiceDetailsDTO = this.getDTOFromInvoiceDetails(invoice.getInvoiceDetails());
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

    public boolean productsExist(List<InvoiceDetailDTO> invoiceDetails){
        for(InvoiceDetailDTO invoiceDetail : invoiceDetails){

            Optional<Product> productOptional = productRepository.findById(invoiceDetail.getIdProduct());

            if(productOptional.isEmpty()) return false;
        }
        return true;
    }

    public boolean stockExists(List<InvoiceDetailDTO> invoiceDetails){
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

        LocalDateTime date = LocalDateTime.now();

        List<InvoiceDetail> invoiceDetails = getInvoiceDetailsFromDTO(invoiceDTO.getInvoiceDetails());

        Double total = calculateTotal(invoiceDetails);

        Client client = clientRepository.findById(invoiceDTO.getIdClient()).get();

        Invoice invoice = new Invoice(date,total,client,invoiceDetails);

        invoice.addDetails(invoiceDetails);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        this.updateStock(invoiceDetails);

        return savedInvoice;
    }

    public Double calculateTotal(List<InvoiceDetail> invoiceDetails){
        Double total = 0d;

        for(InvoiceDetail invoiceDetail : invoiceDetails){
            try{
                total+=invoiceDetail.getSubtotal();
            }catch (NullPointerException ne){
                System.out.println("No se pudo calcular el total de los detalles de Factura, los subtotales estan vacios");
            }
        }

        return total;
    }

    public List<InvoiceDetail> getInvoiceDetailsFromDTO(List<InvoiceDetailDTO> invoiceDetailsDTO){
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();

        for(InvoiceDetailDTO invoiceDetailDTO : invoiceDetailsDTO){
            Integer productAmount = invoiceDetailDTO.getProductAmount();
            Product product = productRepository.findById(invoiceDetailDTO.getIdProduct()).get();
            Double subtotal = productAmount * product.getUnitPrice();

            invoiceDetails.add(new InvoiceDetail(productAmount,subtotal,product));
        }
        return invoiceDetails;
    }

    public List<InvoiceDetailDTO> getDTOFromInvoiceDetails(List<InvoiceDetail> invoiceDetails){
        List<InvoiceDetailDTO> invoiceDetailsDTO = new ArrayList<>();

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


    public void updateStock(List<InvoiceDetail> invoiceDetails){
        for(InvoiceDetail invoiceDetail : invoiceDetails){
            Product productDB = productRepository.findById(invoiceDetail.getProduct().getId()).get();
            productDB.setStock(productDB.getStock() - invoiceDetail.getProductAmount());
            productRepository.save(productDB);
        }
    }

}
