package com.example.devsuservice.services;

import com.example.devsuservice.models.Account;
import com.example.devsuservice.models.Transaction;
import com.example.devsuservice.repositories.AccountRepository;
import com.example.devsuservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    public ResponseEntity<Transaction> getTransactionById(long id) {
        Optional<Transaction> student = transactionRepository.findById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> createTransaction(Transaction trx) {
        try {
            Optional<Account> accountData = accountRepository.findByAccountNumber(trx.getAccountNumber());
            if (accountData.isEmpty()) {
                final Map<String, String> err = new HashMap<>();
                err.put("message", "Número de cuenta no existe");
                return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                Account account1 = accountData.get();
                if(!account1.getStatus()){
                    final Map<String, String> err = new HashMap<>();
                    err.put("message", "No se puede registrar la transacción para un cuenta inactiva");
                    return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
                }
                if(trx.getValue().doubleValue() < 0 && account1.getBalance().add(trx.getValue()).doubleValue() < 0){
                    final Map<String, String> err = new HashMap<>();
                    err.put("message", "Saldo no disponible");
                    return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            // Transfer data
            Account account1 = accountData.get();
            trx.setType(account1.getType());
            trx.setInitialBalance(account1.getBalance());
            trx.setTransactionDate(LocalDateTime.now());
            BigDecimal newBalance = account1.getBalance().add(trx.getValue());
            account1.setBalance( newBalance );
            trx.setPostBalance( newBalance );
            accountRepository.save(account1);
            Transaction res = transactionRepository.save(trx);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
