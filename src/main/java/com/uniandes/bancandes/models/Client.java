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
@Document(collection = "clients")
public class Client {
    @Id
    private ObjectId id;

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

    private List<ObjectId> accounts;
}