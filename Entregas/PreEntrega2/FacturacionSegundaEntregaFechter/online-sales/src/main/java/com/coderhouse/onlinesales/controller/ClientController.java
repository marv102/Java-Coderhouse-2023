package com.coderhouse.onlinesales.controller;

import com.coderhouse.onlinesales.dto.ClientDTO;
import com.coderhouse.onlinesales.model.Client;
import com.coderhouse.onlinesales.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<ClientDTO> create (@RequestBody Client client) {
        return new ResponseEntity<ClientDTO>(clientService.create(client), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<ClientDTO>(clientService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }
}
