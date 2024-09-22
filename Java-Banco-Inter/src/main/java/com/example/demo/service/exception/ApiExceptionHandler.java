package com.example.demo.service.exception;

import com.example.demo.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(CalculoUnicoJaExisteNoSistema.class)
    public ResponseEntity<StandardError>caculoUnicoJaExisteNoSistema(CalculoUnicoJaExisteNoSistema ex,
                                                                     HttpServletRequest request){
        StandardError error =
                new StandardError(LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(EmailJaCadastradoNoSistema.class)
    public ResponseEntity<StandardError>emailJaCadastraoNoSistema(EmailJaCadastradoNoSistema ex,HttpServletRequest request){
        StandardError error =
                new StandardError(LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(UsuarioNaoEncontadoNoSistema.class)
    public ResponseEntity<StandardError>usuarioNaoEncontradoNoSistema
            (UsuarioNaoEncontadoNoSistema ex,HttpServletRequest request){

        StandardError error = new StandardError
                (LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(), ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(404).body(error);
    }

}
