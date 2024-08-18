package com.loja.projetolojaweb2.Exceptions;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
