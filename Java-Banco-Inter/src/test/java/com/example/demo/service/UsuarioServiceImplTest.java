package com.example.demo.service;

import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.EmailJaCadastradoNoSistema;
import com.example.demo.exception.UsuarioNaoEncontadoNoSistema;
import com.example.demo.interfaces.UsuarioService;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsuarioServiceImplTest {
    public static final long ID = 1L;
    public static final String NOME = "Victor";
    public static final String EMAIL = "Victo@gmail.com";
    public static final String N = "9875";
    public static final String K = "4";
    public static final int RESULTADO = 8;
    public static final int INDEX = 0;
    public static final String MESSAGE = "Usuario não existe no Sistema";
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    private Calculo calculo;
    private List<Calculo> calculoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }
    @Test
    void whenSaveThenReturnUsuarioSucess() {
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

        Usuario response = usuarioService.save(usuario);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NOME,response.getNome());
        Assertions.assertEquals(EMAIL,response.getEmail());

    }
    @Test
    void whenSaveThenReturnUsuarioEmailJaCadastradoNoSistema(){
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(usuario);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

        try {
            Usuario user = usuarioService.save(usuario);
        }catch (Exception ex){
            Assertions.assertEquals("Email ja cadastrado",ex.getMessage());
            Assertions.assertEquals(EmailJaCadastradoNoSistema.class,ex.getClass());
        }
    }
    @Test
    void whenFindByIdThenReturnUsuarioSucess() {
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuario));

        Usuario response = usuarioService.findById(ID);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NOME,response.getNome());
        Assertions.assertEquals(EMAIL,response.getEmail());
    }
    @Test
    void whenFindByIdThenReturnUsuarioNaoEncontradoNoSistema(){
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenThrow
                (new UsuarioNaoEncontadoNoSistema(MESSAGE));
        try {
            usuarioService.findById(ID);
        }catch(Exception ex){
            Assertions.assertEquals(UsuarioNaoEncontadoNoSistema.class,ex.getClass());
            Assertions.assertEquals(MESSAGE,ex.getMessage());
        }
    }
    @Test
    void whenUpdateByIdThenReturnSucess() {
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

        Usuario response = usuarioService.updateById(usuario,ID);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NOME,response.getNome());
        Assertions.assertEquals(EMAIL,response.getEmail());
    }

    @Test
    void whenfindAllThenReturnListOfUsuarioSucess() {
        Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> response = usuarioService.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.get(INDEX).getClass());
        Assertions.assertEquals(ID,response.get(INDEX).getId());
        Assertions.assertEquals(NOME,response.get(INDEX).getNome());
        Assertions.assertEquals(EMAIL,response.get(INDEX).getEmail());

    }

    @Test
    void whendeleteByIdThenReturnSucess() {
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuario));
        Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyLong());
        usuarioService.deleteById(ID);
        Mockito.verify(usuarioRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void whendeleteByIdThenReturnUsuarioNaoEncontradoNoSistema(){
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenThrow(new UsuarioNaoEncontadoNoSistema(MESSAGE));
        Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyLong());

        try {
            usuarioService.deleteById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(UsuarioNaoEncontadoNoSistema.class,ex.getClass());
            Assertions.assertEquals(MESSAGE,ex.getMessage());
        }
    }
    void startUsuario(){
        this.usuario = new Usuario(ID, NOME, EMAIL, calculoList);
        this.calculo = new Calculo(ID, N, K, RESULTADO,usuario);
        calculoList.add(calculo);
    }
}