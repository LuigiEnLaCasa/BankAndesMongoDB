package com.uniandes.bancandes.repository;



import com.uniandes.bancandes.models.LogAccount;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.uniandes.bancandes.models.Account;

public interface AccountRepository extends MongoRepository<Account, ObjectId> {

    Optional<Account> findById(ObjectId id);

    @Query("{'id': ?0, 'balance': ?1, 'status': ?2, 'type':?3 ,'log_accounts': ?4}")
    Account saveNewAccount(ObjectId id, Double balance, String status,String type , List<LogAccount> log_accounts);


    
    @Query("{ '_id': ?0 }")
    Account findAccountById(ObjectId accountId);

    @Query("{ '_id': ?0, 'log_accounts': { $elemMatch: { $expr: { $and: [ { $eq: [ { $month: '$logAccounts.logdate' }, ?1 ] }, { $eq: [ { $year: '$logAccounts.logdate' }, ?2 ] } ] } } } }")
    List<LogAccount> consultarLogsPorCuentaYMesAnio(ObjectId idCuenta, Integer mes, Integer anio);

    @Query(value = "{'_id': ?0}", fields = "{'log_accounts': 1, '_id': 0}")
    List<LogAccount> consultarLogsPorCuenta(ObjectId idCuenta);




    //UPDATE
    @Query("{ '_id': ?0 }")
    @Update("{$set: {'status': ?1}}")
    void updateAccountStatus(ObjectId accountId, String status);
    
    @Query("{'_id': ?0}")
    @Update("{$push: {'log_accounts': {'idLog': ?1, 'ammount': ?2, 'logdate': ?3, 'typelog': ?4}}}")
    void addLogToAccount(ObjectId idAccount, int idLog, Double ammount, Instant logdate, String typelog);


    @Query("{'_id': ?0}")
    @Update("{$inc: {'balance': ?1}}")
    void withdrawMoney(ObjectId accountId, Double ammount);


    @Query("{'_id': ?0}")
    @Update("{$inc: {'balance': ?1}}")
    void depositMoney(ObjectId accountId, Double ammount);

    
}


