package br.com.itau.accountms.model.dto.response;

import br.com.itau.accountms.model.enumerated.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private UUID id;
    private Integer branch;
    private Integer number;
    private String taxId;
    private UUID holderId;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
