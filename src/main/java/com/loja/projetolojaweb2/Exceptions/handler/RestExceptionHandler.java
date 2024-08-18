package com.loja.projetolojaweb2.Exceptions.handler;

import com.loja.projetolojaweb2.Exceptions.CarrinhoNotFoundException;
import com.loja.projetolojaweb2.Exceptions.PedidoNotFoundException;
import com.loja.projetolojaweb2.Exceptions.ProdutoForaDeEstoqueException;
import com.loja.projetolojaweb2.Exceptions.ProdutoNotFoundException;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<Object> handleProdutoNotFoundException(ProdutoNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoForaDeEstoqueException.class)
    public ResponseEntity<Object> handleProdutoForaDeEstoqueException(ProdutoForaDeEstoqueException ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<Object> handlePedidoNotFoundException(PedidoNotFoundException e){
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND,e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarrinhoNotFoundException.class)
    public ResponseEntity<Object> handlerCarrinhoNotFoundException(CarrinhoNotFoundException e){
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND,e.getMessage()),HttpStatus.NOT_FOUND);
    }


    @Data
    private static class ApiError{
        private final HttpStatus status;
        private final String message;

        public ApiError(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

    }
}
