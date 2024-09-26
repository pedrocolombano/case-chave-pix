package br.com.itau.userms.exception.handler;

import br.com.itau.userms.exception.InvalidEnumConstantException;
import br.com.itau.userms.exception.ResourceNotFoundException;
import br.com.itau.userms.exception.response.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                         HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        StandardError error = getStandardError("Há erros de validação nos dados enviados.",
                                               status.value(),
                                               request.getRequestURI());
        ex.getFieldErrors()
          .forEach(fieldError -> error.addError(fieldError.getField(),
                                                fieldError.getDefaultMessage()));

        return ResponseEntity.status(status)
                             .body(error);
    }

    @ExceptionHandler(InvalidEnumConstantException.class)
    public ResponseEntity<StandardError> invalidEnumConstantException(InvalidEnumConstantException ex,
                                                                      HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError error = getStandardError(ex.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status)
                             .body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException ex,
                                                                   HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = getStandardError(ex.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status.value())
                             .body(error);
    }

    private StandardError getStandardError(String message, int statusCode, String path) {
        StandardError error = new StandardError();

        error.setCode(statusCode);
        error.setMessage(message);
        error.setPath(path);

        return error;
    }

}
