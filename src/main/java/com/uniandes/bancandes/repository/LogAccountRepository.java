package com.uniandes.bancandes.repository;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import com.uniandes.bancandes.models.LogAccount;


public interface LogAccountRepository extends JpaRepository<LogAccount, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO LogAccount (id, ammount, typeLog, logDate, ACCOUNT_ID) " +
            "VALUES (logaccount_seq.nextVal,:amount, :typeLog, :dateLog, :account_id)", nativeQuery = true)
    void insertLogAccount(@Param("amount") Double amount, @Param("typeLog") String typeLog,
                       @Param("dateLog") Date dateLog,  @Param("account_id") Integer account_id);


    @Query(value = "SELECT a.id AS \"Número de Cuenta\", a.balance AS \"Saldo Inicial\", " +
            "(SELECT SUM(la.ammount) FROM logaccount la WHERE " +
            "la.account_id = a.id AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "AND la.typelog IN ('Intereses', 'Consignacion', 'Consignación', 'Consignar')) AS \"Ingresos\", " +
            "(SELECT SUM(la.ammount) FROM logaccount la WHERE " +
            "la.account_id = a.id AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "AND la.typelog IN ('Retiro', 'Transferencia')) AS \"RetirosTransferencias\", " +
            "(a.balance + " +
            "(SELECT SUM(la.ammount) FROM logaccount la WHERE la.account_id = a.id " +
            "AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "AND la.typelog IN ('Intereses', 'Consignacion', 'Consignación','Consignar')) + " +
            "(SELECT SUM(la.ammount) FROM logaccount la WHERE " +
            "la.account_id = a.id AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "AND la.typelog IN ('Retiro', 'Transferencia'))) AS \"Saldo Final\" " +
            "FROM account a WHERE a.id = :idCuenta GROUP BY a.id, a.balance ORDER BY a.id", nativeQuery = true)
    List<Object[]> consultarEstadoCuenta(
            @Param("idCuenta") Long idCuenta,
            @Param("mes") Integer mes,
            @Param("anio") Integer anio
    );
    // Consulta serializable
    @Query(value = "SELECT la.id , la.ammount , la.logdate, " +
            "la.typelog , la.account_id " +
            "FROM logaccount la WHERE la.account_id = :idCuenta " +
            "AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "ORDER BY la.logdate", nativeQuery = true)
    @QueryHints(value = {@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.SERIALIZABLE)
    Collection<LogAccount> consultarLogsPorCuentaYMesAnioSerializable(
            @Param("idCuenta") Long idCuenta,
            @Param("mes") Integer mes,
            @Param("anio") Integer anio
    );

    @Query(value = "SELECT la.id , la.ammount , la.logdate, " +
            "la.typelog , la.account_id " +
            "FROM logaccount la WHERE la.account_id = :idCuenta " +
            "AND EXTRACT(MONTH FROM la.logdate) = :mes AND EXTRACT(YEAR FROM la.logdate) = :anio " +
            "ORDER BY la.logdate", nativeQuery = true)
    @QueryHints(value = {@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED)
    Collection<LogAccount> consultarLogsPorCuentaYMesAnioCommited(
            @Param("idCuenta") Long idCuenta,
            @Param("mes") Integer mes,
            @Param("anio") Integer anio
    );


}
