package com.gvendas.gestaovendas.excecao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendaExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String NOT_BLANK = "NotBlank";
    public static final String LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> errors = gerarListaDerros(ex.getBindingResult());

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST,request);
    }

    private List<Error> gerarListaDerros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<Error>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario  = tratarMessagemUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Error(msgUsuario,msgDesenvolvedor));
        });
        return erros;
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                          WebRequest request){
        String msgUsuario = "Recurso não encontrado.";
        String msgDesenvolvedor = ex.toString();
        List<Error> errors = Arrays.asList(new Error(msgUsuario,msgDesenvolvedor));
        return handleExceptionInternal(ex,errors,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(EmptyResultDataAccessException ex,
                                                                       WebRequest request){
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.toString();
        List<Error> errors = Arrays.asList(new Error(msgUsuario,msgDesenvolvedor));
        return handleExceptionInternal(ex,errors,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
    private String tratarMessagemUsuario(FieldError fieldError) {
        if(fieldError.getCode().equals(NOT_BLANK)) {
            return fieldError.getDefaultMessage().concat("é obrigatório.");
        }if(fieldError.getCode().equals(LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format("deve ter entre %s e %s caracteres.",
                    fieldError.getArguments()[2],fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }
}
