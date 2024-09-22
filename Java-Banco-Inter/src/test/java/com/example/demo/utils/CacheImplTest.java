package com.example.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CacheImplTest {
    private static int RESULTADO_SUCESSO = 8;
    private static int RESULTADO_FALHA = -1;
    private static String N = "9875";
    private static String K = "4";
    @InjectMocks
    private CacheImpl cache;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarEInserirResultadoComSucesso() {
       cache.inserir(N,K,RESULTADO_SUCESSO);
       int response = cache.buscarResultado(N,K);
       Assertions.assertEquals(response,RESULTADO_SUCESSO);
    }

    @Test
    void buscarEInserirResultadoComFalha() {
        int response = cache.buscarResultado(N,K);
        Assertions.assertEquals(RESULTADO_FALHA,response);
    }
    @Test
    void buscarEInserirResultadoComSucessoComNulo() {
        cache.inserir(N,null,RESULTADO_SUCESSO);
        int response = cache.buscarResultado(N,null);
        Assertions.assertEquals(response,RESULTADO_SUCESSO);
    }
}