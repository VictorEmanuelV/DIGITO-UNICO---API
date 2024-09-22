package com.example.demo.interfaces;


import com.example.demo.dto.CalculoDto;
import com.example.demo.entity.Calculo;

import java.util.List;

public interface CalculoService {
    public int DigitoUnico(String n,String k,Long id);
    List<Calculo> findAllById_Usuario(Long id);
}
