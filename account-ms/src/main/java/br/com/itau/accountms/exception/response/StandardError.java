package br.com.itau.accountms.exception.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StandardError {

    private int code;
    private String message;
    private String path;
    private final LocalDateTime timestamp = LocalDateTime.now();

}
