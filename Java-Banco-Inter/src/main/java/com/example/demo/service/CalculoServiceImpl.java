package com.example.demo.service;

import com.example.demo.entity.Calculo;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.CalculoUnicoJaExisteNoSistema;
import com.example.demo.interfaces.*;
import com.example.demo.repository.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculoServiceImpl implements CalculoService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private Cache cache;
    @Autowired
    private DigitoUnico digitoUnico;
    @Autowired
    private CalculoRepository calculoRepository;
    @Override
    public int DigitoUnico(String n,String k,Long id) {
        int r = cache.buscarResultado(n,k);

        int resultado = 0;

        if(r != -1){
            resultado = r;
        }else {
             resultado =  digitoUnico.digitoUnico(n,k);
             cache.inserir(n,k,resultado);
        }

        if(id != null){
            findDigitoUnico(n,k);
            Usuario usuario = usuarioService.findById(id);
            calculoRepository.save(new Calculo(n,k,resultado,usuario));
        }
        return resultado;
    }

    @Override
    public List<Calculo> findAllById_Usuario(Long id) {
        return calculoRepository.findAllByIdUsuarioId(id);
    }

    public void findDigitoUnico(String n,String k){
        Calculo c = calculoRepository.findByNAndK(n,k);
        if(c != null){
            throw new CalculoUnicoJaExisteNoSistema("Calculo ja existe no sistema");
        }
    }

}
