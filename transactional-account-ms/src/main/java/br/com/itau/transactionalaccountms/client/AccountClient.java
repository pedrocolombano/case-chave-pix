package br.com.itau.transactionalaccountms.client;

import br.com.itau.transactionalaccountms.model.dto.response.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "API-GATEWAY", path = "/accounts")
public interface AccountClient {

    @GetMapping("/{id}")
    AccountDto findById(@PathVariable UUID id);

}
