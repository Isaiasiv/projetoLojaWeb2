package com.loja.projetolojaweb2.util;

public class ValidarCpf {

    public Long validarCpf(String cpf){
       String cpfValidado;
       String posicao;
       Long calc;
        for(int i = 1; i <= cpf.length()-2; i++){
            char texto = cpf.charAt(i);
            calc = Long.parseLong(String.valueOf(texto)) * i;
            //posicao =
        }
        return null;
    }

}
