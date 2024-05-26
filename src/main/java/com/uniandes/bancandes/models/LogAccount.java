package com.uniandes.bancandes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.bson.types.ObjectId;

import jakarta.persistence.Id;


@Getter
@Setter
@AllArgsConstructor              
@NoArgsConstructor
public class LogAccount {
    @Id
    private Integer idLog;
    private Double ammount;
    private Date logdate;
    private String typelog;
  


}
