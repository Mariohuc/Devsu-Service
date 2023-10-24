package com.example.devsuservice.repositories;

import com.example.devsuservice.models.Transaction;
import com.example.devsuservice.models.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "CALL transaction_report_get(:page, :limit, :client_id, :start_date, :end_date);", nativeQuery = true)
    List<TransactionInfo> getTransactionReport(@Param("page") Integer page, @Param("limit") Integer limit, @Param("client_id") Integer clientId, @Param("start_date") String startDate, @Param("end_date") String endDate);
}
