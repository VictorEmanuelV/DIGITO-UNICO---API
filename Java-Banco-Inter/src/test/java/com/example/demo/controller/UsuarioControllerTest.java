package com.example.demo.controller;

import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;
import com.example.demo.interfaces.UsuarioService;
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
class UsuarioControllerTest {
    public static final long ID = 1L;
    public static final String NOME = "Victor";
    public static final String EMAIL = "Victo@gmail.com";
    public static final String N = "9875";
    public static final String K = "4";
    public static final int RESULTADO = 8;
    public static final int INDEX = 0;
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private UsuarioController usuarioController;
    private Usuario usuario;
    private Calculo calculo;
    private List<Usuario> usuarioList = new ArrayList<>();
    private List<Calculo> calculoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenCreateThenReturnSucess() {
        Mockito.when(usuarioService.save(Mockito.any())).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.create(usuario);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID,response.getBody().getId());
        Assertions.assertEquals(NOME,response.getBody().getNome());
        Assertions.assertEquals(EMAIL,response.getBody().getEmail());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito.when(usuarioService.findById(Mockito.anyLong())).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID,response.getBody().getId());
        Assertions.assertEquals(NOME,response.getBody().getNome());
        Assertions.assertEquals(EMAIL,response.getBody().getEmail());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void whenFindAllThenReturnListOfUsuarios() {
        Mockito.when(usuarioService.findAll()).thenReturn(usuarioList);
        ResponseEntity<List<Usuario>> response = usuarioController.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID,response.getBody().get(INDEX).getId());
        Assertions.assertEquals(NOME,response.getBody().get(INDEX).getNome());
        Assertions.assertEquals(EMAIL,response.getBody().get(INDEX).getEmail());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void whenUpdateByIdThenReturnSucess() {
        Mockito.when(usuarioService.updateById(Mockito.any(),
                Mockito.anyLong())).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.updateById(usuario,ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID,response.getBody().getId());
        Assertions.assertEquals(NOME,response.getBody().getNome());
        Assertions.assertEquals(EMAIL,response.getBody().getEmail());
        Assertions.assertEquals(ResponseEntity.class,response.getClass());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void whenDeleteByIdThenReturnSucess() {
        Mockito.doNothing().when(usuarioService).deleteById(Mockito.anyLong());
        ResponseEntity<Usuario> response = usuarioController.deleteById(ID);
        Mockito.verify(usuarioService,Mockito.times(1)).deleteById(Mockito.anyLong());

        Assertions.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
    void startUsuario(){
        this.usuario = new Usuario(ID, NOME, EMAIL, calculoList);
        this.calculo = new Calculo(ID, N, K, RESULTADO,usuario);
        calculoList.add(calculo);
        usuarioList.add(usuario);
    }
}