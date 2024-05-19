package com.uniandes.bancandes.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
@ToString
@Table(name = "User")
public class User implements Serializable {

    @Id
    private String idcard;

    private String typeid;

    private String name;

    private String login;

    private String password;

    private String nationality;

    private String address;

    private String email;

    private String phone;

    private String country;

    private String department;

}
