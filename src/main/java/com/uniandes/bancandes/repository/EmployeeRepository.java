package com.uniandes.bancandes.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.uniandes.bancandes.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Employee (idCard, type, salary, User_IDCard) VALUES (:idCard, :type, :salary, :userIdCard)", nativeQuery = true)
    void addEmployee(@Param("idCard") String idCard, @Param("type") String type,
            @Param("salary") Double salary, @Param("userIdCard") String userIdCard);

    @Query(value = "select * from Employee WHERE user_idcard NOT IN (SELECT employee_idcard from OFFICE) AND employee.type = 'GERENTE_OFICINA'", nativeQuery = true)
    Iterable<Employee> findEmployeesWithoutOffice();

}
