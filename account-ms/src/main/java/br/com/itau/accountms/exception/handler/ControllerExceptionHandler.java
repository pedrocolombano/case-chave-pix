package br.com.itau.accountms.exception.handler;

import br.com.itau.accountms.exception.ResourceNotFoundException;
import br.com.itau.accountms.exception.response.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

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
