package com.coderhouse.onlinesales.dto;

import lombok.Data;

@Data
public class ClientDTO {

    public ClientDTO(Integer id, String firstName, String lastName, String documentNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
    }

    public ClientDTO(Integer id) {
        this.id = id;
    }

    public ClientDTO() {
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String documentNumber;
}
