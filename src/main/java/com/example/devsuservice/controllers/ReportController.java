package com.example.devsuservice.controllers;

import com.example.devsuservice.models.Transaction;
import com.example.devsuservice.models.TransactionReportItem;
import com.example.devsuservice.repositories.ReportRepository;
import com.example.devsuservice.repositories.TransactionRepository;
import com.example.devsuservice.services.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportController {

    @Autowired
    private TransactionService trxService;
    @Autowired
    private ReportRepository reportRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping
    ResponseEntity<Object> createAccount() {
        List<TransactionReportItem> res = reportRepository.getTransactionReport(88570, "2023-10-16", "2023-10-16");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
}
