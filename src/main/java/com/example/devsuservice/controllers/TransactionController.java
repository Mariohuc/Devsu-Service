package com.example.devsuservice.controllers;

import com.example.devsuservice.models.Transaction;
import com.example.devsuservice.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movimientos")
public class TransactionController {

    @Autowired
    private TransactionService trxService;

    @PostMapping
    ResponseEntity<Object> createAccount(@Valid @RequestBody Transaction incoming) {
        return trxService.createTransaction(incoming);
    }
    
}
