package com.thiagorodrigues.osworks.domain.service.client;

import com.thiagorodrigues.osworks.domain.exception.DomainException;
import com.thiagorodrigues.osworks.domain.model.Client;
import com.thiagorodrigues.osworks.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        var findClient = clientRepository.findByEmail(client.getEmail());

        if(findClient != null) {
            throw new DomainException("E-mail already registered.");
        }

        return clientRepository.save(client);
    }
}
