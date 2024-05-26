package com.uniandes.bancandes.models;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="accounts")
public class Account {
    @Id
    private ObjectId id;
    private Double balance;
    private String status;
    private String type;
    
    @Field("log_accounts")
    List<LogAccount> log_accounts;
    
    

}
