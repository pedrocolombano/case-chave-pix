package br.com.itau.transactionalaccountms.model.entity;

import br.com.itau.transactionalaccountms.model.enumerated.AccountType;
import br.com.itau.transactionalaccountms.model.enumerated.PersonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Integer branch;

    @Column(nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private AccountType accountType;

    @Column(length = 30, nullable = false)
    private String holderName;

    @Column(length = 45, nullable = false)
    private String holderSurname;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private PersonType holderType;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime updatedAt;

}
