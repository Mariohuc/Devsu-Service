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

    public List<Object> getTransactionReport(Integer clientId, String startDate, String endDate) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("transaction_report_get")
                .registerStoredProcedureParameter(1, Integer.class,
                        ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class,
                        ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class,
                        ParameterMode.IN);
        query.setParameter(1, clientId);
        query.setParameter(2, startDate);
        query.setParameter(3, endDate);
        query.execute();
        List<Object> postComments = query.getResultList();
        return postComments;
    }
}
