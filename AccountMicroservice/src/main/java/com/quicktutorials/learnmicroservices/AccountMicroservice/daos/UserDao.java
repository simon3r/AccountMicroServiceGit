package com.quicktutorials.learnmicroservices.AccountMicroservice.daos;

import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,String> { //Tipo User e tipologia Id
    /*Abbiamo creato un interfaccia che estende un interfaccia tipica di Spring che verrà implementata da Spring automaticamente all'avvio dell'applicazione
    non saremo quindi noi a creare per la prima volta l'implementazione dell'interfaccia.
    Spring creerà le classi concrete che le implementano e le renderà disponibili per essere iniettate.
/*
Estrende Jpa repository che contiene una serie di metodi che spring andrà ad implementare concretamente.
https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
 */

    @Override
    Optional<User> findById(String s);
    //Spring automaticamente capisce che dovrà trovare L'user tramite L'id, nella tabella User avendola Specificata sopra
    //Nota optional vuol dire eventualmente, non è detto che mi ritorni un risultato User, potrebbe non trovarlo
}
