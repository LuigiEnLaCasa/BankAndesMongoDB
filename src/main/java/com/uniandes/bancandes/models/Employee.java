package com.uniandes.bancandes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private String idcard;

    private enum Type {
        GERENTE_GENERAL,
        GERENTE_OFICINA,
        CAJERO
    };

    @Enumerated(EnumType.STRING)
    private Type type;

    private Double salary;

    private String user_idcard;
}
