package com.coderhouse.onlinesales.service;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO create(Client client) {
        Client clientSaved = clientRepository.save(client);
        return new ClientDTO(clientSaved.getId(), clientSaved.getFirstName(),clientSaved.getLastName(), clientSaved.getDocumentNumber());

    }

    public ClientDTO findById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            return new ClientDTO(client.getId(), client.getFirstName(),client.getLastName(), client.getDocumentNumber());
        }
        return null;
    }

    public void deleteById(Integer id){
        clientRepository.deleteById(id);
    }

}
