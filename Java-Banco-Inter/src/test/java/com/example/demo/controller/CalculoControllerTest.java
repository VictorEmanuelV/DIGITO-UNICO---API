package com.example.demo.controller;

import com.example.demo.dto.CalculoDto;
import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;
import com.example.demo.interfaces.CalculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CalculoControllerTest {
    public static final String N = "9875";
    public static final String K = "4";
    public static final int RESULTADO = 8;
    public static final long ID = 1L;
    public static final String NOME = "Victor";
    public static final String EMAIL = "Victo@gmail.com";
    public static final int INDEX = 0;
    @Mock
    private CalculoService calculoService;
    @InjectMocks
    private CalculoController calculoController;
    private CalculoDto calculoDto;
    private Calculo calculo;
    private Usuario usuario;
    List<Calculo> calculoList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCalculo();
    }

    @Test
    void createThenReturnCalculoWithSucess() {
        Mockito.when(calculoService.
                DigitoUnico(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(RESULTADO);
        ResponseEntity<Integer> response = calculoController.create(calculoDto);
        Assertions.assertEquals(RESULTADO,response.getBody());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void whenFindAllCalculosByidThenReturnListOfCalculos() {
        Mockito.when(calculoService.findAllById_Usuario(Mockito.anyLong())).thenReturn(calculoList);

        ResponseEntity<List<Calculo>>response = calculoController.findAllCalculosByid(ID);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(ID,response.getBody().get(INDEX).getId());
        Assertions.assertEquals(N,response.getBody().get(INDEX).getN());
        Assertions.assertEquals(K,response.getBody().get(INDEX).getK());
        Assertions.assertEquals(RESULTADO,response.getBody().get(INDEX).getResultado());
    }
    void startCalculo(){
        this.calculoDto = new CalculoDto(N,K,ID);
        this.usuario = new Usuario(ID,NOME,EMAIL,calculoList);
        this.calculo = new Calculo(ID,N,K,RESULTADO,usuario);
        this.calculoList.add(calculo);

    }
}