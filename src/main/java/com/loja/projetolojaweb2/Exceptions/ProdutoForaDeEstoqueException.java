package com.loja.projetolojaweb2.Exceptions;

public class ProdutoForaDeEstoqueException extends RuntimeException {
        public ProdutoForaDeEstoqueException(String mensagem) {
            super(mensagem);
        }
    }
