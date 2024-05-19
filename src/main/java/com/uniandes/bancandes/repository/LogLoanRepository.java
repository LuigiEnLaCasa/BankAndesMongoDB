package com.uniandes.bancandes.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.uniandes.bancandes.models.LogLoan;

public interface LogLoanRepository extends JpaRepository<LogLoan, Integer> {
        @Modifying
        @Transactional
        @Query(value = "INSERT INTO LogLoan (id, amount, typeLog, dateLog, Loan_id) " +
                        "VALUES (logloan_seq.nextval,:amount, :typeLog, :dateLog, :loanId)", nativeQuery = true)
        void insertLogLoan(@Param("amount") Double amount, @Param("typeLog") String typeLog,
                        @Param("dateLog") Date dateLog, @Param("loanId") int loanId);
}
