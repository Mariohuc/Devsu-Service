package com.example.devsuservice.repositories;

import com.example.devsuservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByName(String name);
    Optional<Client> findByClientId(Integer clientId);

}
