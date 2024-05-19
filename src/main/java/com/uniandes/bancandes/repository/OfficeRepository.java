package com.uniandes.bancandes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uniandes.bancandes.models.Office;

import jakarta.transaction.Transactional;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OFFICE (id, name, address, employee_idCard) VALUES (OFFICE_SEQ.nextval, :name, :address, :employee_idCard)", nativeQuery = true)
    void saveOffice(@Param("name") String name, @Param("address") String address,
            @Param("employee_idCard") String employee_idCard);

    @Query(value = "SELECT * FROM OFFICE", nativeQuery = true)
    Collection<Office> findAllOffices();
}
