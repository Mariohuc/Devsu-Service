package com.example.devsuservice.services;

import com.example.devsuservice.dto.AccountRequest;
import com.example.devsuservice.models.Account;
import com.example.devsuservice.models.Client;
import com.example.devsuservice.repositories.AccountRepository;
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
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public ResponseEntity<Account> getAccountById(int id) {
        Optional<Account> student = accountRepository.findById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> createAccount(Account account) {
        try {
            Optional<Client> clientData = clientRepository.findByClientId(account.getClientId());
            if (clientData.isEmpty()) {
                final Map<String, String> err = new HashMap<>();
                err.put("message", "ID de cliente no existe");
                return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                Client client1 = clientData.get();
                if(!client1.getStatus()){
                    final Map<String, String> err = new HashMap<>();
                    err.put("message", "No se puede procesar la creación de cuenta para un cliente inactivo");
                    return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            Account res = accountRepository.save(account);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateAccount(int accountId, AccountRequest incoming) {
        try{
            Optional<Account> accountData = accountRepository.findById(accountId);
            if (accountData.isPresent()) {
                Account account1 = accountData.get();
                if(incoming.getType() != null) account1.setType(incoming.getType());
                if(incoming.getBalance() != null) account1.setBalance(incoming.getBalance());
                if(incoming.getStatus() != null) account1.setStatus(incoming.getStatus());
                
                return new ResponseEntity<>(accountRepository.save(account1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteAccount(int id) {
        try {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
