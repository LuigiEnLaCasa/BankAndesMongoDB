package com.uniandes.bancandes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.uniandes.bancandes.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Loan (id,state, valueLoan, initialFee, pendingFees, pendingValueLoan, valueFee, interest, payday, type, Customer_IDCard) "
                        +
                        "VALUES (loan_seq.nextval ,:state, :valueLoan, :initialFee, :pendingFee, :pendingValueLoan, :valueFee, :interest, :payday, :type, :customerIdCard)", nativeQuery = true)
        void insertLoan(@Param("state") String state, @Param("valueLoan") Double valueLoan,
                        @Param("initialFee") Double initialFee,
                        @Param("pendingFee") int pendingFee, @Param("pendingValueLoan") Double pendingValueLoan,
                        @Param("valueFee") Double valueFee, @Param("interest") Double interest,
                        @Param("payday") Long payday,
                        @Param("type") String type, @Param("customerIdCard") String customerIdCard);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Loan SET pendingValueLoan = :newPendingValueLoan, pendingFees = :newPendingFees " +
                        "WHERE Customer_IDCard = :customerIdCard", nativeQuery = true)
        void updatePendingValuesByCustomerIdCard(@Param("customerIdCard") String customerIdCard,
                        @Param("newPendingValueLoan") Double newPendingValueLoan,
                        @Param("newPendingFees") Double newPendingFees);

        @Query(value = "SELECT * FROM Loan", nativeQuery = true)
        Collection<Loan> getLoans();

        @Query(value = "SELECT * FROM Loan WHERE Customer_IDCard = :customerIdCard", nativeQuery = true)
        Collection<Loan> findLoansByCustomerIDCard(@Param("customerIdCard") String customerIdCard);

        @Query(value = "SELECT * FROM Loan WHERE id = :loanId", nativeQuery = true)
        Loan findLoanById(@Param("loanId") Integer loanId);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Loan SET state = :newState WHERE ID = :loanId", nativeQuery = true)
        void updateLoanState(@Param("loanId") Long loanId, @Param("newState") String newState);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Loan SET pendingValueLoan = :newPendingValueLoan, pendingFees = :newPendingFees " +
                        "WHERE ID = :loanId ", nativeQuery = true)
        void updatePendingValuesByLoanId(@Param("loanId") int loanId,
                        @Param("newPendingValueLoan") Double newPendingValueLoan,
                        @Param("newPendingFees") int newPendingFees);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Loan SET pendingValueLoan = :newPendingValueLoan WHERE ID = :id", nativeQuery = true)
        void updateExtraPendingValueLoan(@Param("id") int id, @Param("newPendingValueLoan") Double newPendingValueLoan);
}
