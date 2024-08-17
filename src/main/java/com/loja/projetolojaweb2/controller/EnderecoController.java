package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("api/v1/enderecos"))
@Data
@RequiredArgsConstructor
@Log4j2
public class EnderecoController {
    private final EnderecoService enderecoService;

    @Operation(summary = "Busca Endereço",description = "Busca Endereço atraves do id",tags = "Endereço")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @GetMapping(path = "/{id}")
    public Endereco findById(@PathVariable Long id) {
        return enderecoService.encontrarPorIdOuLancarExcecao(id);
    }

    @Operation(summary = "cadastro de Endereço",description = "cadastra Endereço",tags = "Endereço")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @PostMapping()
    public ResponseEntity<Endereco> save(@RequestBody EnderecoPostRequest enderecoPostRequest) {
        return new ResponseEntity<>(enderecoService.salvar(enderecoPostRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista de Endereço",description = "lista todos os Endereço",tags = "Endereço")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @GetMapping()
    public ResponseEntity<List<Endereco>> list() {
        return ResponseEntity.ok(enderecoService.encontrarTodos());
    }

    @Operation(summary = "Atualizar Endereço",description = "Alualiza dados do Endereço",tags = "Endereço")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @PutMapping()
    public ResponseEntity<Endereco> replace(@RequestBody EnderecoPutRequest enderecoPutRequest) {
        enderecoService.atualizar(enderecoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Excluir Endereço",description = "Eclui Endereço usando o id",tags = "Endereço")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Endereco> delete(@PathVariable Long id) {
        enderecoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
