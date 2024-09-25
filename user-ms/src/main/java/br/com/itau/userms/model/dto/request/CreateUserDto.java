package br.com.itau.userms.model.dto.request;

import br.com.itau.userms.model.enumerated.PersonType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String firstName;
    private String surname;
    private String email;
    private String document;
    private String taxId;
    private PersonType personType;

}
