package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.ModelAssembler.UsuarioModelAssembler;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.peditoToUsuarioDto.PedidoToUsuarioDto;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("api/v1/usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    @Operation(summary = "lista usuário", description = "lista todos os usuários cadastrados",tags = "Usuário")
    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listAll() {
        List<EntityModel<Usuario>> usuarios = usuarioService.encontrarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());


        return ResponseEntity.ok(CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listAll()).withSelfRel()));
    }


    @Operation(summary = "Busca Usuário", description = "Busca um Usuário através do nome de usuário (login)", tags = "Usuário")
    //@ApiResponses(value = {
           // @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
           // @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
   // })
    @GetMapping(path="/{login}")
    public ResponseEntity<EntityModel<Usuario>> findById(@PathVariable String login) {
        Usuario usuario = usuarioService.encontrarPorIdOuLancarExcecao(login);
        return ResponseEntity.ok(assembler.toModel(usuario));
    }


    @Operation(summary = "Criar Usuário", description = "Cria uma nova conta de Usuário", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Usuário")
    })
    @PostMapping()
    public ResponseEntity<EntityModel<Usuario>> save(@RequestBody UsuarioPostRequest usuarioPostRequest) {
        usuarioPostRequest.setTipoConta(1);
        Usuario usuario = usuarioService.salvar(usuarioPostRequest);
        return new ResponseEntity<>(assembler.toModel(usuario), HttpStatus.CREATED);
    }


    @Operation(summary = "Excluir Usuário", description = "Exclui conta de um Usuário através do nome de usuário (login)", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Void> delete(@PathVariable String login) {
        usuarioService.delete(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Editar Usuário", description = "Atualiza dados de um Usuário através do nome de usuário (login)", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Usuário")
    })
    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizar(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Pedidos do usuário", description = "Lista de pedidos realizados por esse usuário", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedidos")
    })
    @PutMapping("/addPedido")
    public ResponseEntity<EntityModel<Usuario>> addPedidoToUsuario(@RequestBody PedidoToUsuarioDto pedidoToUsuarioDto) {
        Usuario usuario = usuarioService.addPedidoToUsuario(pedidoToUsuarioDto);
        return ResponseEntity.ok(assembler.toModel(usuario));
    }
}
