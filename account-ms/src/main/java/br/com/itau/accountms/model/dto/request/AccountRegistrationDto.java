package br.com.itau.accountms.model.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationDto {

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 7, max = 7, message = "{invalid.field.size}")
    private String accountType;

    @Digits(integer = 4, fraction = 0)
    private Integer branch;

    @Digits(integer = 8, fraction = 0, message = "{invalid.account.size}")
    private Integer number;

    @NotNull(message = "{holder.not.empty}")
    private UserRegistrationDto holder;

}
