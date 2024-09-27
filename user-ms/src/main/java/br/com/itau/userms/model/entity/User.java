package br.com.itau.userms.model.entity;

import br.com.itau.userms.model.enumerated.PersonType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 45, nullable = false)
    private String surname;

    @Column(length = 77, nullable = false, unique = true)
    private String email;

    @Column(length = 9, nullable = false)
    private String document;

    @Column(length = 14, nullable = false)
    private String taxId;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private PersonType personType;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime updatedAt;

}
