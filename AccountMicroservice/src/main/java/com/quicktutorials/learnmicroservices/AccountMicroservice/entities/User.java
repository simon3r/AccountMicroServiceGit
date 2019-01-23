package com.quicktutorials.learnmicroservices.AccountMicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//Per mappare questo Pojo con le JPA bisogna indicare che questo è un @Entity, quindi un entità delle JPA, che mappa una tabella del database con nome users
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name= "users")
public class User {
    @Id
    @Column(name="ID")
    @NotEmpty @NotBlank @NotNull //JSR 303 Java validation
    @Getter @Setter //lombook, automatizza getter e setter
    private String id;

    @Column(name="USERNAME")
    @NotEmpty @NotBlank @NotNull
    @Getter @Setter
    private String username;

    @Column(name="PASSWORD")
    @NotEmpty @NotBlank @NotNull
    @Getter @Setter
    private String password;

    @NotEmpty @NotBlank @NotNull
    @Column(name="PERMISSION")
    @Getter @Setter
    private String permission;
}
