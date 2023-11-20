package com.example.clienterest.service;

import com.example.clienterest.dto.ClienteDTO;
import com.example.clienterest.model.Cliente;
import com.example.clienterest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //CREAR
    public Cliente create(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //LEER
    public ClienteDTO findById(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            int edad = this.calcularEdad(cliente.getFechaNacimiento());
            return new ClienteDTO(cliente.getNombre(),cliente.getApellido(),edad); //Devolvemos solo lo que necesitamos
        }

        return null;
    }

    //BORRAR
    public void delete(Long id){
            clienteRepository.deleteById(id);
    }

    private int calcularEdad(LocalDate fechaNacimiento){
        LocalDate.parse(fechaNacimiento.toString());
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);

        return periodo.getYears();
    }

}
