package br.com.itau.accountms.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 3, max = 30, message = "{invalid.field.size}")
    private String firstName;

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 3, max = 45, message = "{invalid.field.size}")
    private String surname;

    @NotBlank(message = "{field.not.empty}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotBlank(message = "{field.not.empty}")
    @Pattern(regexp = "(^(\\d{2}\\.\\d{3}\\.\\d{3}-[0-9X])|(\\d{9})$)",
             message = "{invalid.document}")
    private String document;

    @NotBlank(message = "{field.not.empty}")
    @Pattern(regexp = "(^(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2})|(\\d{14})$)|(^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$)",
             message = "{invalid.taxid}")
    private String taxId;

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 5, max = 7, message = "{invalid.field.size}")
    private String personType;

}
