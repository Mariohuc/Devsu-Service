package com.example.devsuservice.services;

import com.example.devsuservice.dto.ClientRequest;
import com.example.devsuservice.models.Client;
import com.example.devsuservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    public ResponseEntity<Client> getClientById(int id) {
        Optional<Client> student = clientRepository.findById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> createClient(Client client) {
        try {
            Client res = clientRepository.save(client);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateClient(int clientId, ClientRequest incoming) {
        try{
            Optional<Client> clientData = clientRepository.findById(clientId);
            if (clientData.isPresent()) {
                Client client1 = clientData.get();
                if(incoming.getName() != null) client1.setName(incoming.getName());
                if(incoming.getPhone() != null) client1.setPhone(incoming.getPhone());
                if(incoming.getAge() != null) client1.setAge(incoming.getAge());
                if(incoming.getGender() != null) client1.setGender(incoming.getGender());
                if(incoming.getAddress() != null) client1.setAddress(incoming.getAddress());
                if(incoming.getIdentificationCardNumber() != null) client1.setIdentificationCardNumber(incoming.getIdentificationCardNumber());
                if(incoming.getStatusPassword() != null) client1.setStatusPassword(incoming.getStatusPassword());
                if(incoming.getStatus() != null) client1.setStatus(incoming.getStatus());
                return new ResponseEntity<>(clientRepository.save(client1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteClient(int id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
