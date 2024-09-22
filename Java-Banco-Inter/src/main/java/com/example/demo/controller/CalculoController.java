package com.example.demo.controller;

import com.example.demo.dto.CalculoDto;
import com.example.demo.entity.Calculo;
import com.example.demo.interfaces.CalculoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculo")
public class CalculoController {
    @Autowired
    private CalculoService calculoService;
    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody CalculoDto calculoDto){
        int response = calculoService.DigitoUnico(calculoDto.getN(),calculoDto.getK(),calculoDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Calculo>> findAllCalculosByid(@PathVariable Long id){
        List<Calculo> list = calculoService.findAllById_Usuario(id);
        return ResponseEntity.ok(list);
    }

}
