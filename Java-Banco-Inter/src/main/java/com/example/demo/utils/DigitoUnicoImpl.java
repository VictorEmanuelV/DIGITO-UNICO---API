package com.example.demo.utils;

import com.example.demo.interfaces.DigitoUnico;
import org.springframework.stereotype.Component;

@Component
public class DigitoUnicoImpl implements DigitoUnico {
    @Override
    public int digitoUnico(String digito, String digito2) {
        String aux = "";
        if(digito2 != null){
            for(int i = 0; i<Integer.parseInt(digito2); i++){
                aux += digito;
            }
            digito = aux;
            digito2 = null;
        }

        if(digito.length()>1){
            String vetor[] = digito.split("");

            int soma = 0;

            for(int i = 0; i<vetor.length; i++){
                soma+=Integer.parseInt(vetor[i]);
            }
            digito = String.valueOf(soma);
            soma = 0;

            return digitoUnico(digito,digito2);
        }
        return Integer.parseInt(digito);
    }
}
