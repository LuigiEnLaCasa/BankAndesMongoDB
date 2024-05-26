package com.uniandes.bancandes.repository;

import com.uniandes.bancandes.models.Office;
import com.uniandes.bancandes.models.PointService;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;



public interface OfficeRepository extends MongoRepository <Office,ObjectId>{

    @Query("{'name': ?0, 'address': ?1, 'point_services': ?2}")
    Office saveNewOffice(String name, String address, List<PointService> pointServices);

    @Query("{'type': ?0, 'address': ?1}")
    PointService saveNewPointService(String type, String address);

    @Query("{'_id':?0}, {'point_services':1}")
    List<PointService> getOfficePointServices(ObjectId id);

    @Query("{'_id': ?0}")
    @Update("{$pull: {'point_services': {'idPoint': ?1}}}")
    void deletePointServiceFromOffice(ObjectId officeId, Integer idPoint);

    @Query("{'_id': ?0}")
    @Update("{$push: {'point_services': {'idPoint': ?1, 'type': ?2}}}")
    void addPointServiceToOffice(ObjectId officeId, Integer idPoint, String type);





    

}