package com.loja.projetolojaweb2.ModelAssembler;

import com.loja.projetolojaweb2.controller.FuncionarioController;
import com.loja.projetolojaweb2.domain.Funcionario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioModelAssembler implements RepresentationModelAssembler<Funcionario, EntityModel<Funcionario>> {

    @Override
    public EntityModel<Funcionario> toModel(Funcionario funcionario) {
        // Cria o EntityModel com o objeto Funcionario
        EntityModel<Funcionario> funcionarioModel = EntityModel.of(funcionario);

        // Adiciona links HATEOAS ao modelo
        funcionarioModel.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(FuncionarioController.class).findById(funcionario.getLogin()))
                .withSelfRel()
        );

        funcionarioModel.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(FuncionarioController.class).list())
                .withRel("funcionarios")
        );

        funcionarioModel.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(FuncionarioController.class).save(null))
                .withRel("criarFuncionario")
        );

        funcionarioModel.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(FuncionarioController.class).delete(funcionario.getLogin()))
                .withRel("deleteFuncionario")
        );

        return funcionarioModel;
    }
}
