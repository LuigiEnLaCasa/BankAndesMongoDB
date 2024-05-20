package com.uniandes.bancandes.models;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    List<LogAccount> log_accounts;
    
    

}
