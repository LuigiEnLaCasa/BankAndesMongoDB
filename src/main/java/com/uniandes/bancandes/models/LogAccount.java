package com.uniandes.bancandes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor              
@NoArgsConstructor
public class LogAccount {
  
    private Double ammount;
    private Date logdate;
    private String typelog;
  


}
