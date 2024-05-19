package com.uniandes.bancandes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.uniandes.bancandes.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Account (id, balance, status, Customer_IDCard, office_id) VALUES (account_seq.nextval , :balance, :status, :customerId, :officeId)", nativeQuery = true)
    void insertAccount(@Param("balance") Double balance, @Param("status") String status,
            @Param("customerId") String customerId, @Param("officeId") int officeId);

    @Query(value = "SELECT * FROM Account", nativeQuery = true)
    Collection<Account> getAccounts();

    @Query(value = "SELECT * FROM Account WHERE Customer_IDCard = :customerIdCard", nativeQuery = true)
    Account findAcoountByCustomerIDCard(@Param("customerIdCard") String customerIdCard);

    @Query(value = "SELECT * FROM Account WHERE id = :accountId", nativeQuery = true)
    Account findAccountById(@Param("accountId") Integer accountId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Account SET status = :newStatus WHERE id = :accountId", nativeQuery = true)
    void updateAccountStatus(@Param("accountId") Long accountId, @Param("newStatus") String newStatus);


    @Modifying
    @Transactional
    @Query("UPDATE Account SET balance = balance - :amountToWithdraw WHERE id = :accountId")
    void withdrawMoney(Long accountId, Double amountToWithdraw);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET balance = balance + :amountToDeposit WHERE id = :accountId")
    void depositMoney(Long accountId, Double amountToDeposit);

    @Query(value = "SELECT * FROM Account WHERE Customer_IDCard = :customerIdCard", nativeQuery = true)
    Collection<Account> findAccountsByCustomerIDCard(@Param("customerIdCard") String customerIdCard);

}

