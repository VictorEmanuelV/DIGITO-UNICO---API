package com.example.demo.interfaces;


import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    public Usuario save(Usuario usuario);
    public Usuario findById(Long id);
    public Usuario updateById(Usuario usuario,Long id);
    public List<Usuario> findAll();
    public void deleteById(Long id);


}
