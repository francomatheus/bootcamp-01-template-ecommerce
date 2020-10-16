package br.com.ecommerce.mercadolivre.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> erroDeValicacaoExceptionHandler(MethodArgumentNotValidException exception){
        List<String> erros = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            erros.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        });

        ErroPadraoAPI erroPadraoAPI = new ErroPadraoAPI(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), erros, exception.getMessage());

        return ResponseEntity.badRequest().body(erroPadraoAPI);
    }
}
