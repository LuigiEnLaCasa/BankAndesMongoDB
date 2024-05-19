package com.uniandes.bancandes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "office")
public class Office {
    @Id
    private int id;

    private String name;

    private String address;

    @OneToOne
    @JoinColumn(name = "employee_idcard", referencedColumnName = "idcard")
    private Employee employee;
}
