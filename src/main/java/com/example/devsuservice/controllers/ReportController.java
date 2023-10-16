package com.example.devsuservice.controllers;

import com.example.devsuservice.repositories.ReportRepository;
import com.example.devsuservice.services.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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
    ResponseEntity<Object> createAccount(
            @RequestParam Integer clientId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
            String _fechaInicio = DateFor.format(fechaInicio);
            String _fechaFin = DateFor.format(fechaFin);
            List<Object> res = reportRepository.getTransactionReport(clientId, _fechaInicio, _fechaFin);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
