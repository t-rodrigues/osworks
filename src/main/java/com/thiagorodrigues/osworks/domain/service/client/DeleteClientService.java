package com.thiagorodrigues.osworks.domain.service.client;

import com.thiagorodrigues.osworks.domain.exception.DomainException;
import com.thiagorodrigues.osworks.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void delete(Long clientId) {
        var findClient = clientRepository.findById(clientId);

        if(findClient != null) {
            throw new DomainException("User not found");
        }
        clientRepository.deleteById(clientId);
    }
}
