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

    @Query("{'id': ?0, 'balance': ?1, 'status': ?2, 'log_accounts': ?3}")
    Account saveNewAccount(ObjectId id, Double balance, String status, List<LogAccount> logaccounts);

    @Query("{'_id': ?0}")
    @Update("{$push: {'log_accounts': {'ammount': ?1, 'logdate': ?2, 'typelog': ?3}}}")
    void addLogToAccount(ObjectId accountId, Double ammount, Date logdate, String typelog);

}

