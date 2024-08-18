package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.service.FuncionarioService;
import com.loja.projetolojaweb2.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/funcionarios")
@RestController
@Log4j2
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Operation(summary = "Busca funcionário",description = "Busca um funcionário atraves do nome " +
            "de usuario(login),",tags = "Funcionário")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return funcionarioService.encontrarPorIdOuLancarExcecao(login);
    }

    @Operation(summary = "Criar funcionário",description = "Cria uma conta de funcionário",tags = "Funcionário")
    @PostMapping()
    public ResponseEntity<Funcionario> save(@RequestBody FuncionarioPostRequest funcionarioPostRequest) {
        funcionarioPostRequest.setTipoConta(2);
        return new ResponseEntity<>(funcionarioService.salvar(funcionarioPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> list() {
        return ResponseEntity.ok(funcionarioService.encontrarTodos());
    }

    @Operation(summary = "Edita funcionário",description = "Atualiza dados de  funcionário" +
            "atraves do nome de usuario(login)",tags = "Funcionário")
    @PutMapping()
    public ResponseEntity<Funcionario> replace(@RequestBody FuncionarioPutRequest funcionarioPutRequest) {
        funcionarioService.atualizar(funcionarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Excluir funcionário",description = "exclui conta de um funcionário atraves " +
            "do nome de usuario(login)",tags = "Funcionário")
    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Funcionario> delete(@PathVariable String login) {
        funcionarioService.delete(login);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
