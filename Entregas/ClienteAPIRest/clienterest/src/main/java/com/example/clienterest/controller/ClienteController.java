package com.example.clienterest.controller;

import com.example.clienterest.dto.ClienteDTO;
import com.example.clienterest.model.Cliente;
import com.example.clienterest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "cliente") //Expongo URI
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    //CREAR
    @PostMapping("/")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        return new ResponseEntity<>( clienteService.create(cliente), HttpStatus.OK);
    }

    //LEER
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>( clienteService.findById(id), HttpStatus.OK);
    }

    //BORRAR
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
           clienteService.delete(id);
    }

}
