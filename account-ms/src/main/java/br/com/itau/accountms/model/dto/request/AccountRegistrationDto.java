package br.com.itau.accountms.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationDto {

    private String accountType;
    private Integer branch;
    private Integer number;
    private UserRegistrationDto holder;

}
