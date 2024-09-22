package com.example.demo.service;

import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.CalculoUnicoJaExisteNoSistema;
import com.example.demo.interfaces.Cache;
import com.example.demo.interfaces.DigitoUnico;
import com.example.demo.interfaces.UsuarioService;
import com.example.demo.repository.CalculoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CalculoServiceImplTest {
    public static final long ID = 1L;
    public static final String NOME = "Victor";
    public static final String EMAIL = "Victo@gmail.com";
    public static final String N = "9875";
    public static final String K = "4";
    public static final int RESULTADO = 8;
    public static final int INDEX = 0;
    @InjectMocks
    private CalculoServiceImpl calculoServiceImpl;
    @Mock
    private Cache cache;
    @Mock
    private DigitoUnico digitoUnico;
    @Mock
    private CalculoRepository calculoRepository;
    @Mock
    private UsuarioService usuarioService;
    private Usuario usuario;
    private Calculo calculo;
    private List<Calculo>calculoList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenDigitoUnicoThenReturnResultadoWithoutIdAndWithCache() {
        Mockito.when(cache.buscarResultado(Mockito.anyString(),Mockito.anyString())).thenReturn(RESULTADO);
        int response  = calculoServiceImpl.DigitoUnico(N,K,null);
        Assertions.assertEquals(RESULTADO,response);
    }
    @Test
    void whenDigitoUnicoThenReturnResultadoWithIdAndWithoutCache(){
        Mockito.when(cache.buscarResultado(Mockito.anyString(),Mockito.anyString())).thenReturn(-1);
        Mockito.when(digitoUnico.digitoUnico(Mockito.anyString(),Mockito.anyString())).thenReturn(RESULTADO);
        Mockito.when(usuarioService.findById(Mockito.anyLong())).thenReturn(usuario);

        int response = calculoServiceImpl.DigitoUnico(N,K,ID);
        Assertions.assertEquals(RESULTADO,response);
    }
    @Test
    void whenFindAllById_UsuarioThenReturnListOfCalculos() {
        Mockito.when(calculoRepository.findAllByIdUsuarioId(Mockito.anyLong())).thenReturn(calculoList);
        List<Calculo> list = calculoServiceImpl.findAllById_Usuario(ID);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(N,list.get(INDEX).getN());
        Assertions.assertEquals(K,list.get(INDEX).getK());
        Assertions.assertEquals(RESULTADO,list.get(INDEX).getResultado());
        Assertions.assertEquals(1,list.size());
    }
    @Test
    void findDigitoUnico(){
        Mockito.when(calculoRepository.findByNAndK
                (Mockito.anyString(),Mockito.anyString())).thenReturn(calculo);;
        try {
            calculoServiceImpl.findDigitoUnico(N,K);
        }catch (Exception ex){
            Assertions.assertEquals("Calculo ja existe no sistema", ex.getMessage());
            Assertions.assertEquals(CalculoUnicoJaExisteNoSistema.class,ex.getClass());
        }

    }
    void startUsuario(){
        this.usuario = new Usuario(ID, NOME, EMAIL, calculoList);
        this.calculo = new Calculo(ID, N, K, RESULTADO,usuario);
        calculoList.add(calculo);
    }
}