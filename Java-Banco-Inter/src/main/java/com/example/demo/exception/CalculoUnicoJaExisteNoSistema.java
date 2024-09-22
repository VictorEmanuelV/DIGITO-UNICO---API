package com.example.demo.exception;

public class CalculoUnicoJaExisteNoSistema extends RuntimeException{
    public CalculoUnicoJaExisteNoSistema(String message){
        super(message);
    }
}
