package br.com.itau.accountms.model.entity;

import br.com.itau.accountms.model.enumerated.PersonType;
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
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    @Column(length = 14, nullable = false, unique = true)
    private String taxId;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private PersonType personType;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "holder")
    private Account account;

}
