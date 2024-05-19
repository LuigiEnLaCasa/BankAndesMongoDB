package com.uniandes.bancandes.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uniandes.bancandes.models.Account;
import com.uniandes.bancandes.models.Customer;
import com.uniandes.bancandes.models.Loan;
import com.uniandes.bancandes.models.User;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    public interface CustomerAggregateData {
        Customer getCUSTOMER();

        User getUser();

        Account getACCOUNT();

        Loan getLOAN();

    }

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Customer (IDCard, User_IDCard) VALUES (:idCard, :userIdCard)", nativeQuery = true)
    void insertCustomer(@Param("idCard") String idCard, @Param("userIdCard") String userIdCard);

    @Query(value = "SELECT * FROM Customer WHERE Customer_id = :customerId", nativeQuery = true)
    List<Customer> findAccountsByCustomerId(String customerId);

    @Query(value = "SELECT * FROM Customer ", nativeQuery = true)
    Collection<Customer> getCustomers();

    @Query(value = "SELECT * FROM Customer WHERE User_IDCard = :id", nativeQuery = true)
    Customer getCustomerByUserId(@Param("id") String id);

    @Query(value = "SELECT * FROM CUSTOMER " +
    // "INNER JOIN \"User\" on customer.user_idcard = \"User\".IDCARD " +
            "LEFT OUTER JOIN ACCOUNT on customer.idcard = account.customer_idcard " +
            "LEFT OUTER JOIN LOAN on customer.idcard = loan.customer_idcard " +
            "WHERE CUSTOMER.IDCARD = :id", nativeQuery = true)
    CustomerAggregateData getCustomerAggregateData(@Param("id") int id);
}
