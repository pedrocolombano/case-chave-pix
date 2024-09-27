package br.com.itau.accountms.model.dto.response;

import br.com.itau.accountms.model.enumerated.PersonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String firstName;
    private String surname;
    private String email;
    private String document;
    private String taxId;
    private PersonType personType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
