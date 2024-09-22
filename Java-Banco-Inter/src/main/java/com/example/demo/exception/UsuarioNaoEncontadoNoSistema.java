package com.example.demo.exception;

public class UsuarioNaoEncontadoNoSistema extends RuntimeException{
    public UsuarioNaoEncontadoNoSistema(String message){
        super(message);
    }
}
