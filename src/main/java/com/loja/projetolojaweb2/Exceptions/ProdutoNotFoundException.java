package com.loja.projetolojaweb2.Exceptions;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}


