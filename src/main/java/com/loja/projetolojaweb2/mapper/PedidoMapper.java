package com.loja.projetolojaweb2.mapper;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPostRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PedidoMapper {
    public static final PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    public abstract Pedido toPedido(PedidoPostRequest pedidoPostRequest);

    public abstract Pedido toPedido(PedidoPutRequest pedidoPutRequest);
}
