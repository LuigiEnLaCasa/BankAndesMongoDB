package com.uniandes.bancandes.repository;

import com.uniandes.bancandes.models.Employee;
import com.uniandes.bancandes.models.Office;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;



public interface EmployeeRepository extends MongoRepository <Employee,ObjectId>{

    @Query("{'name': ?0, 'department': ?1, 'login': ?2, 'password': ?3, 'nationality': ?4, 'address': ?5, 'email': ?6, 'phone': ?7, 'country': ?8, 'type': ?9, 'salary': ?10, 'offices': ?11}")
    Employee saveNewEmployee(String name, String department, String login, String password, String nationality, String address, String email, String phone, String country, String type, Double salary, List<Office> offices);

}