package com.uniandes.bancandes.repository;



import com.uniandes.bancandes.models.LogAccount;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.uniandes.bancandes.models.Account;

public interface AccountRepository extends MongoRepository<Account, ObjectId> {

    @Query("{'id': ?0, 'balance': ?1, 'status': ?2, 'type':?3 ,'log_accounts': ?4}")
    Account saveNewAccount(ObjectId id, Double balance, String status,String type , List<LogAccount> log_accounts);

    @Query("{'_id': ?0}")
    @Update("{$push: {'log_accounts': {'id':?1 ,'ammount': ?2, 'logdate': ?3, 'typelog': ?4}}}")
    void addLogToAccount(ObjectId accountId, Integer id ,Double ammount, Date logdate, String typelog);

    
    @Query("{ '_id': ?0 }")
    Account findAccountById(ObjectId accountId);

    @Query("{ '_id': ?0, 'logAccounts': { $elemMatch: { $expr: { $and: [ { $eq: [ { $month: '$logAccounts.logdate' }, ?1 ] }, { $eq: [ { $year: '$logAccounts.logdate' }, ?2 ] } ] } } } }")
    List<LogAccount> consultarLogsPorCuentaYMesAnio(ObjectId idCuenta, Integer mes, Integer anio);
    
}


