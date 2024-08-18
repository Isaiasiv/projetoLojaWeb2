package com.loja.projetolojaweb2.ModelAssembler;


import com.loja.projetolojaweb2.controller.UsuarioController;
import com.loja.projetolojaweb2.domain.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {


    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class).findById(usuario.getLogin())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listAll()).withRel("usuarios"),
                linkTo(methodOn(UsuarioController.class).addPedidoToUsuario(null)).withRel("addPedido"));
    }
}
