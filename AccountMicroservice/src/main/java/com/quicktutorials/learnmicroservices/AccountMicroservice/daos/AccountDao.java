package com.quicktutorials.learnmicroservices.AccountMicroservice.daos;

import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDao extends JpaRepository<Account, String> {

    @Query(value = "select * from accounts WHERE FK_USER=:user",nativeQuery = true)
    List<Account> getAllAccountsPerUser(@Param("user") String user);
    //Definiamo il valore della query

    List<Account> findByFKUser(String fkUser); //named query, specifichiamo nel nome quello che vogliamo
}
