package br.com.itau.userms.exception.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
