package com.uniandes.bancandes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    private Integer id;
    private String state;
    private Double valueloan;
    private Double initialfee;
    private int pendingfees;
    private Double pendingvalueloan;
    private Double valuefee;
    private Double interest;
    private Long payday;
    private String type;

    @ManyToOne
    @JoinColumn(name = "Customer_idcard", referencedColumnName = "idCard")
    private Customer customer;

}
