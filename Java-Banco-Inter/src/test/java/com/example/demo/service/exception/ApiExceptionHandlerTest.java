package com.example.demo.service.exception;

import com.example.demo.exception.CalculoUnicoJaExisteNoSistema;
import com.example.demo.exception.EmailJaCadastradoNoSistema;
import com.example.demo.exception.StandardError;
import com.example.demo.exception.UsuarioNaoEncontadoNoSistema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionHandlerTest {

    public static final String MESSAGE = "Calculo Ja existe No Sistema";
    public static final String MESSAGE1 = "Email ja cadastrado no sistema";
    public static final String MESSAGE2 = "Usuario nao existe no sistema";
    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void caculoUnicoJaExisteNoSistema() {
        ResponseEntity<StandardError>
                response =
                apiExceptionHandler.caculoUnicoJaExisteNoSistema
                        (new CalculoUnicoJaExisteNoSistema
                                (MESSAGE), new MockHttpServletRequest());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(StandardError.class,response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        Assertions.assertEquals(MESSAGE,response.getBody().getErro());

    }

    @Test
    void emailJaCadastraoNoSistema() {
        ResponseEntity<StandardError>
                response =
                apiExceptionHandler.emailJaCadastraoNoSistema
                        (new EmailJaCadastradoNoSistema
                                (MESSAGE1),new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(StandardError.class,response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        Assertions.assertEquals(MESSAGE1,response.getBody().getErro());
    }

    @Test
    void usuarioNaoEncontradoNoSistema() {
       ResponseEntity<StandardError>
               response =
               apiExceptionHandler.usuarioNaoEncontradoNoSistema
                       (new UsuarioNaoEncontadoNoSistema
                               (MESSAGE2),new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(StandardError.class,response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        Assertions.assertEquals(MESSAGE2,response.getBody().getErro());
    }
}