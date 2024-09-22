package com.example.demo.exception;

public class EmailJaCadastradoNoSistema extends RuntimeException {
    public EmailJaCadastradoNoSistema(String message){
        super(message);
    }
}
