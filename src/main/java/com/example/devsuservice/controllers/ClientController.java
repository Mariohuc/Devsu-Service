package com.example.devsuservice.controllers;

import com.example.devsuservice.dto.ClientRequest;
import com.example.devsuservice.models.Client;
import com.example.devsuservice.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    ResponseEntity<Object> createClient(@Valid @RequestBody Client incoming) {
        return clientService.createClient(incoming);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> updateClient(@PathVariable("id") String clientId, @Valid @RequestBody ClientRequest incoming) {
        return clientService.updateClient(Integer.parseInt(clientId), incoming);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") String clientId) {
        return clientService.deleteClient(Integer.parseInt(clientId));
    }

}
