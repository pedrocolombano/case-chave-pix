package br.com.itau.transactionalaccountms.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAccountRegistrationDto {

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 5, max = 77, message = "{invalid.field.size}")
    private String key;

    @NotBlank(message = "{field.not.empty}")
    @Size(min = 3, max = 6, message = "{invalid.field.size}")
    private String keyType;

    @NotNull(message = "{account.not.empty}")
    private UUID accountId;

}
