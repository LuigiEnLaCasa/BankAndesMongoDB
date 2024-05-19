package com.uniandes.bancandes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="LogAccount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogAccount {
    @Id
    private Integer id;
    private Double ammount;
    private Date logdate;
    private String typelog;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account_id;


}
