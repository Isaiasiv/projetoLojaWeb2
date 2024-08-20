package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.ModelAssembler.FuncionarioModelAssembler;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final FuncionarioModelAssembler assembler;

    @Operation(summary = "Busca funcionário", description = "Busca um funcionário através do login.", tags = "Funcionário")
    @GetMapping(path = "/{login}")
    public ResponseEntity<EntityModel<Funcionario>> findById(@PathVariable String login) {
        Funcionario funcionario = funcionarioService.encontrarPorIdOuLancarExcecao(login);
        return ResponseEntity.ok(assembler.toModel(funcionario));
    }

    @Operation(summary = "Criar funcionário", description = "Cria uma conta de funcionário.", tags = "Funcionário")
    @PostMapping()
    public ResponseEntity<EntityModel<Funcionario>> save(@RequestBody FuncionarioPostRequest funcionarioPostRequest) {
        funcionarioPostRequest.setTipoConta(2);
        Funcionario funcionario = funcionarioService.salvar(funcionarioPostRequest);
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(FuncionarioController.class).findById(funcionario.getLogin()))
                        .toUri())
                .body(assembler.toModel(funcionario));
    }

    @Operation(summary = "Lista funcionários", description = "Lista todos os funcionários cadastrados.", tags = "Funcionário")
    @GetMapping()
    public ResponseEntity<List<EntityModel<Funcionario>>> list() {
        List<Funcionario> funcionarios = funcionarioService.encontrarTodos();
        List<EntityModel<Funcionario>> funcionariosModel = funcionarios.stream()
                .map(assembler::toModel)
                .toList();
        return ResponseEntity.ok(funcionariosModel);
    }

    @Operation(summary = "Edita funcionário", description = "Atualiza dados de um funcionário através do login.", tags = "Funcionário")
    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody FuncionarioPutRequest funcionarioPutRequest) {
        funcionarioService.atualizar(funcionarioPutRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir funcionário", description = "Exclui conta de um funcionário através do login.", tags = "Funcionário")
    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Void> delete(@PathVariable String login) {
        funcionarioService.delete(login);
        return ResponseEntity.noContent().build();
    }
}
