package com.uniandes.bancandes.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Account")

public class Account {
    @Id
    private Integer id;
    private Double balance;
    private String status;

    private String Customer_idcard;

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;

    public Account(){;}

    public Account(Double balance, String status, String Customer_idcard, Office office) {

        this.balance = balance;
        this.status = status;
        this.Customer_idcard = Customer_idcard;
        this.office = office;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return Customer_idcard;
    }

    public void setCustomer_idcard(String Customer_idcard) {
        this.Customer_idcard = Customer_idcard;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
