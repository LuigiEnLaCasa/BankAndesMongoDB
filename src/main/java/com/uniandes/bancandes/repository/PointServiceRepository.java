package com.uniandes.bancandes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uniandes.bancandes.models.PointService;

import jakarta.transaction.Transactional;

public interface PointServiceRepository extends JpaRepository<PointService, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PointService ( id, type, address, office_id) VALUES (PointService_SEQ.nextVal, :type, :address, :office_id)", nativeQuery = true)
    void savePointService(@Param("type") String name, @Param("address") String address,
            @Param("office_id") int office_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PointService WHERE id = :id", nativeQuery = true)
    void deletePointService(@Param("id") int id);

    @Query(value = "SELECT * FROM PointService", nativeQuery = true)
    Collection<PointService> findAllPointServices();

}
