package com.example.demo.utils;

import com.example.demo.interfaces.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class CacheImpl implements Cache {
    private int tamanho = 0;
    private Resultado[] list = new Resultado[10];
    @Override
    public int buscarResultado(String n,String k){
        int resul = -1;

        for(int i = 0; i<list.length; i++){
                if(list[i] != null){
                  if(k == null && (list[i].getK() == null)){
                      if(list[i].getN().equals(n)){
                          resul = list[i].getResultado();
                          break;
                      }
                  }
                  if(list[i].getK() != null){
                      if(list[i].getN().equals(n) && list[i].getK().equals(k)){
                          resul = list[i].getResultado();
                          break;
                      }
                  }

                }
           }

        return resul;
    }
    @Override
    public void inserir(String n,String k,int resultado){
        for(int i = 0; i<list.length-1; i++){
            list[i] = list[i+1];
        }
        list[list.length-1] = new Resultado(n,k,resultado);
        this.tamanho++;
        log.info("Inserindo objeto numero "+tamanho);
    }
}
