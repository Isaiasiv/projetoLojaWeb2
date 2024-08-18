package com.loja.projetolojaweb2.Exceptions;


import com.loja.projetolojaweb2.domain.Carrinho;

import java.util.Optional;

public class CarrinhoNotFoundException  extends RuntimeException{
    public CarrinhoNotFoundException(String mensagem){
        super(mensagem);
    }
}
