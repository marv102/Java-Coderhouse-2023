package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.dto.ProductDTO;
import com.coderhouse.onlinesales.exception.ClientException;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.service.ClientService;
import com.coderhouse.onlinesales.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientValidator clientValidator;

    @PostMapping("/")
    public ResponseEntity<ClientDTO> create(@RequestBody Client client) throws ClientException {
        clientValidator.validate(client);
        return new ResponseEntity<ClientDTO>(clientService.create(client), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<ClientDTO>(clientService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<List<Client>>(clientService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateById(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<ClientDTO>(clientService.updateById(id,clientDTO), HttpStatus.OK);
    }
}
