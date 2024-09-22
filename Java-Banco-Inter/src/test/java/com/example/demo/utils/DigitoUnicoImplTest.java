package com.example.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DigitoUnicoImplTest {
    private static String N = "9875";
    private static String K = "4";
    private static int RESULTADO = 8;
    private static int RESULTADO1 = 2;
    @InjectMocks
    private DigitoUnicoImpl digitoUnico;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void digitoUnicoComSucesoPassandoSoUmaString() {
        int response = digitoUnico.digitoUnico(N,null);
        Assertions.assertEquals(response,RESULTADO1);
    }
    @Test
    void digitoUnicoComSucesoPassando2String() {
        int response = digitoUnico.digitoUnico(N,K);
        Assertions.assertEquals(response,RESULTADO);
    }
}