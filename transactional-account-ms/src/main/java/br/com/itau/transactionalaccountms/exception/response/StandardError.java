package br.com.itau.transactionalaccountms.exception.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StandardError {

    private int code;
    private String message;
    private String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final List<FieldError> errors = new ArrayList<>();

    public void addError(String field, String error) {
        errors.add(new FieldError(field, error));
    }

}
