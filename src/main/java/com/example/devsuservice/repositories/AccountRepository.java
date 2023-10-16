package com.example.devsuservice.repositories;

import com.example.devsuservice.models.Account;
import com.example.devsuservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(Integer accountNumber);
}
