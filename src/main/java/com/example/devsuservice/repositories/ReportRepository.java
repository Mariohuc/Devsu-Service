package com.example.devsuservice.repositories;

import com.example.devsuservice.models.TransactionReportItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<TransactionReportItem> getTransactionReport(Integer clientId, String startDate, String endDate) {
        List<TransactionReportItem> list;
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("transaction_report_get", "TransactionReportItem");
        try {
            query.registerStoredProcedureParameter( "$client_id",  Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter( "$start_date", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("$end_date", String.class, ParameterMode.IN);
            /*set parameter value*/
            query.setParameter("$client_id", clientId);
            query.setParameter("$start_date", startDate);
            query.setParameter("$end_date", endDate);
            query.execute();
            // Execute query
            query.execute();
            list = query.getResultList();
        } finally {
            try {
                query.unwrap(ProcedureOutputs.class).release();
            } catch (Exception e) {
            }
        }
        return list;
    }
}
