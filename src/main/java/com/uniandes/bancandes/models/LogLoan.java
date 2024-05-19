package com.uniandes.bancandes.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="LogLoan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogLoan {
    @Id
    private Integer id;
    private Double amount;
    private String typeLog;
    private Date dateLog;
    @OneToOne
    @JoinColumn(name = "Loan_id", referencedColumnName = "id")
    private Loan loan;

}
