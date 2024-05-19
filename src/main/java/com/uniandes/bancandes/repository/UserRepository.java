package com.uniandes.bancandes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uniandes.bancandes.models.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO \"User\" (idCard, typeId, name, login, password, nationality, address, email, phone, country, department) " +
            "VALUES (:idCard, :typeId, :name, :login, :password, :nationality, :address, :email, :phone, :country, :department)", nativeQuery = true)
    void saveUser(@Param("idCard") String idCard, @Param("typeId") String typeId, @Param("name") String name,
                  @Param("login") String login, @Param("password") String password, @Param("nationality") String nationality,
                  @Param("address") String address, @Param("email") String email, @Param("phone") String phone,
                  @Param("country") String country, @Param("department") String department);

    @Query(value = "SELECT * FROM \"User\"", nativeQuery = true)
    Collection<User> findAllUsers();

    @Query(value = "SELECT * FROM \"User\" WHERE idCard = :idCard", nativeQuery = true)
    User findUserByIdCard(@Param("idCard") String idCard);

}
