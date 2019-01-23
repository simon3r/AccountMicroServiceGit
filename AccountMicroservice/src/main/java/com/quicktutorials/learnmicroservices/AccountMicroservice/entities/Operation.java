package com.quicktutorials.learnmicroservices.AccountMicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="operations")
@AllArgsConstructor @NoArgsConstructor
public class Operation { //mappati glo oggetti del database con le varie regole,

    //String ID, Date DATE, Double VALUE, String DESCRIPTION, String FK_ACCOUNT1, String FK_ACCOUNT2

    @Id
    @Column(name="ID")
    @Getter @Setter
    @NotBlank
    @NotNull @NotEmpty
    private String id;

    @Column(name="DATE")
    @Getter @Setter
    private Date date;

    @Column(name="DESCRIPTION")
    @Getter @Setter
    private String description;

    @Column(name="VALUE")
    @Getter @Setter
    @NotNull
    private Double value;

    @Column(name="FK_ACCOUNT1")
    @Getter @Setter
    @NotNull @NotBlank @NotEmpty
    private String fkAccount1;

    @Column(name="FK_ACCOUNT2")
    @Getter @Setter
    private String fkAccount2;

    @PrePersist
    void getTimeOperation() {
        this.date = new Date();
    }
}