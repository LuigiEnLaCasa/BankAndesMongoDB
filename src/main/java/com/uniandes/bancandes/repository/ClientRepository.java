
package com.uniandes.bancandes.repository;

import com.uniandes.bancandes.models.Client;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;



public interface ClientRepository extends MongoRepository <Client,ObjectId>{


    @Query("{'id': ?0, 'idcard': ?1, 'typeid': ?2, 'name': ?3, 'login': ?4, 'password': ?5, 'nationality': ?6, 'address': ?7, 'email': ?8, 'phone': ?9, 'country': ?10, 'department': ?11, 'accounts': ?12}")
    Client saveNewClient(ObjectId id, String idcard, String typeid, 
                            String name, String login, String password, String nationality, 
                            String address, String email, String phone, String country,
                            String department, List<ObjectId> accounts
                            );
    
    @Query("{'_id': ?0}")
    @Update("{$push: {'accounts': ?1}}")
    void addAccountToClient(ObjectId customerId, ObjectId accountId);
    
    
}

          
