package br.com.itau.accountms.client;

import br.com.itau.accountms.model.dto.request.UserRegistrationDto;
import br.com.itau.accountms.model.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "API-GATEWAY")
public interface UserClient {

    @PostMapping("/users")
    UserDto register(@RequestBody UserRegistrationDto userDto);

}
