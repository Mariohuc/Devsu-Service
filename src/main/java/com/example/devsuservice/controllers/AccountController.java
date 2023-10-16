package com.example.devsuservice.controllers;

import com.example.devsuservice.dto.AccountRequest;
import com.example.devsuservice.models.Account;
import com.example.devsuservice.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    ResponseEntity<Object> createAccount(@Valid @RequestBody Account incoming) {
        return accountService.createAccount(incoming);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> updateAccount(@PathVariable("id") String accountId, @Valid @RequestBody AccountRequest incoming) {
        return accountService.updateAccount(Integer.parseInt(accountId), incoming);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") String accountId) {
        return accountService.deleteAccount(Integer.parseInt(accountId));
    }

}
