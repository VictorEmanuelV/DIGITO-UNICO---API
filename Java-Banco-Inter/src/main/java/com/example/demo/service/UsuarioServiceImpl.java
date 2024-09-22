package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.exception.EmailJaCadastradoNoSistema;
import com.example.demo.exception.UsuarioNaoEncontadoNoSistema;
import com.example.demo.interfaces.UsuarioService;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario save(Usuario usuario) {
        findByEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () ->  new UsuarioNaoEncontadoNoSistema("Usuario não existe no Sistema")
        );
    }
    @Override
    public Usuario updateById(Usuario usuario,Long id) {
        findByEmail(usuario,id);
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        usuarioRepository.deleteById(id);
    }

    private void findByEmail(String email){
        Usuario user = usuarioRepository.findByEmail(email);
        if(user != null){
            throw new EmailJaCadastradoNoSistema("Email ja cadastrado");
        }
    }
    private void findByEmail(Usuario usuario,Long id){
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());
        if(user != null && !user.getId().equals(id)){
            throw new EmailJaCadastradoNoSistema("Email ja cadastrado");
        }
    }
}
