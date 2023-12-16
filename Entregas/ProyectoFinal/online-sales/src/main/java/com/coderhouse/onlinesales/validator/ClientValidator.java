package com.coderhouse.onlinesales.validator;

import com.coderhouse.onlinesales.exception.ClientException;
import com.coderhouse.onlinesales.exception.InvoiceException;
import com.coderhouse.onlinesales.model.Client;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ClientValidator {

    // DNI format: 8 digits
    Pattern dniPattern = Pattern.compile("\\d{8}");


    public void validate(Client client) throws ClientException {

        String clientDocument = client.getDocumentNumber();

        if(clientDocument==null){

            throw new ClientException("ERROR: The clients document number cannot be empty");

        }else {
            Matcher dniRegex = dniPattern.matcher(clientDocument);

            if(!dniRegex.matches()){
                throw new ClientException("ERROR: The clients document number is in the wrong format, "+"\n"+
                                          "Format corresponds to 8 digits");
            }
        }


    }
}
