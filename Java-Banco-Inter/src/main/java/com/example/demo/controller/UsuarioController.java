package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        Usuario usuario1 = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario>findById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> usuarioList = usuarioService.findAll();
        return ResponseEntity.ok(usuarioList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateById(@RequestBody Usuario usuario,@PathVariable Long id){
        Usuario usuario1 = usuarioService.updateById(usuario,id);
        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
