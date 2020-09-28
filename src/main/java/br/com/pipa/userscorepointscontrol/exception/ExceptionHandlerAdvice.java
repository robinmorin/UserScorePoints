package br.com.pipa.userscorepointscontrol.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @SneakyThrows
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Object> handleHttpStatusCodeException(HttpStatusCodeException ex, WebRequest request) {
        log.error("HttpStatus error: Code {} Message {}",ex.getStatusCode().value(),ex.getLocalizedMessage());
        return ResponseEntity.status(ex.getStatusCode().value()).body(new ObjectMapper().readTree(ex.getResponseBodyAsString()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        log.error("Not found error: Code 404 Message {}",ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleRuntimeException(Exception ex, WebRequest request) {
        log.error("Internal server error: Code 500 Message {}",ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu erro: ".concat(ex.getLocalizedMessage()));
    }

}
